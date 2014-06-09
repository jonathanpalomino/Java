/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.stores.gestores;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import palomino.base_datos.coneccion.Conector_Oracle;
import palomino.base_datos.stores.Funcion;
import palomino.base_datos.stores.Parametro;
import palomino.base_datos.stores.Parametro_IN;
import palomino.base_datos.stores.Parametro_INOUT;
import palomino.base_datos.stores.Parametro_OUT;
import palomino.base_datos.stores.Procedimiento;
import palomino.herramientas.variables_entorno.Global;
/**
 *
 * @author JONATHAN
 */
public class Unido {
    private Procedimiento var_procedure;
    private boolean procedimiento = false;
    private Funcion var_function;
    private String funcionValidacion;
    private static Connection conecccion;
    private static String MensajeError;
    private Hashtable vParametros2 = new Hashtable();
    private int TotalParametros = 0;
    private boolean hayMensajeError = false;
    private String NOMBRE_CURSOR = "CURSOR";
    private int TipoDatoRetorno;
    private static boolean coneccion_correcta;
    private String _texto;
    private Object _valor;
    private int secuencia;
    Object[] Variables_Instancia = null;
    private Object retorno_defecto;
    private final static Logger log_Unido = Logger.getLogger(Unido.class);
    Object[] dataRetorno=null;
    int[] tipos_definidos=null;
    
    public static int CURSOR = OracleTypes.CURSOR;
    public static int BOOLEAN = Types.BOOLEAN;
    public static int VARCHAR = Types.VARCHAR;
    public static int DOUBLE = Types.DOUBLE;
    public static int INTEGER = Types.INTEGER;
    public static int NUMERIC = Types.NUMERIC;
    public static int BLOB = Types.BLOB;

    public Object[] ejecutaSentencia
        (
        String cSentencia, 
        String[] aFormatos, 
        Object[] aValores, 
        int[] aTipos
        ) 
    {
        tipos_definidos=new int[aValores.length];
        tipos_definidos=aTipos;
        return ejecutaSentencia(cSentencia, aFormatos, aValores);
    }
    public Object[] ejecutaSentencia
        (
        String cSentencia, 
        String[] aFormatos, 
        Object[] aValores
        ) 
    {
        getConeccion();
        setFuncionValidacion(cSentencia);
        for(int i=0;i<aValores.length;i++){
            addParametros(aValores[i], aFormatos[i], i,aValores.length);
        }
        lanzaValidacionOracle();
        Iterator it = vParametros2.entrySet().iterator();
        dataRetorno = new Object[vParametros2.size()];
        while (it.hasNext()) {
            Map.Entry e1 = (Map.Entry) it.next();
            Object[] datos2 = new Object[2];
            datos2 = (Object[]) e1.getValue();
            dataRetorno[Integer.valueOf(e1.getKey().toString())]=datos2;
        }
        return dataRetorno;
    }

    /**
     * @return the vParametros2
     */
    private Hashtable getvParametros(){
	return vParametros2;
    }
    /**
     * @param vParametros2 the vParametros2 to set
     */
    private void setvParametros(Hashtable vParametros2) {
        this.vParametros2 = vParametros2;
    }
    public void addParametros(Object param, String io, int piNumero) {
        if (piNumero == 0) {
            this.hayMensajeError = true;
            return;
        }
        addParametros(param, io, piNumero, this.TotalParametros);
    }
    public void addParametros(Object param, String io, int piNumero, int piTotal) {
        Object parametro;
        if (param == null) {
            parametro = " ";
        }
        if (param instanceof Unido) {
            parametro = ((Unido) param).getValor();
            if(parametro==null){
                parametro= new String("");
                ((Unido) param).setValor(parametro);
            }
            ((Unido) param).setClave(piNumero);

        } else {
            if (param == null) {
                parametro = "";
            }
            else{
                parametro = param;
            }
            
        }
        if (piNumero == 0) {
            this.vParametros2.clear();
            this.TotalParametros = piTotal;
        }
        Object[] datos2 = new Object[2];
        datos2[0] = parametro;
        //log_Unido.info("param ---  " + parametro);
        datos2[1] = io;
        this.vParametros2.put(piNumero, datos2);       
    }
    public boolean lanzaValidacionOracle() {
        if (this.funcionValidacion.trim().equals("")) {
            return true;
        }

        if (!isConeccion_correcta()) {
            this.hayMensajeError = true;
            log_Unido.info("Fallo lanzaValidacionOracle retorno "+false);
            return false;
        }

        //String cCadena = "";
        String cSentencia = funcionValidacion;
        Object[] para = (Object[]) null;
        
        if (cSentencia.contains(".")) {
            if (cSentencia.contains("?:=")) {
                String temp = cSentencia.replace("?:=", "");
                log_Unido.info("ES UNA FUNCION   " + temp);
                setVar_function(new Funcion(temp));
                setProcedimiento(false);
            } else {
                log_Unido.info("ES UN PROCEDURE   " + cSentencia);
                setVar_procedure(new Procedimiento(cSentencia));
                setProcedimiento(true);
            }
        } else {
            if (cSentencia.contains("?:=")) {
                String temp = cSentencia.replace("?:=", "");
                log_Unido.info("ES UNA FUNCION   " + temp);
                setVar_function(new Funcion(temp));
                setProcedimiento(false);
            } else {
                log_Unido.info("ES UN PROCEDURE  " + cSentencia);
                setVar_procedure(new Procedimiento(cSentencia));
                setProcedimiento(true);
            }
        }
        log_Unido.info(" TAMANIO DE BUCLE    " + TotalParametros);
        for (int liNumCampo = 0; liNumCampo < this.TotalParametros; liNumCampo++) {
            int tipo = 0;
            para = (Object[]) this.vParametros2.get(liNumCampo);
            //log_Unido.info(para);
            log_Unido.info(para[0] +" --- "+para[1]);
            if (para == null) {
                para = new Object[2];
                para[0] = new String();
                para[1] = new String("OUT");
            }
            if (para[0] == null) {
                continue;
            }
            
            if (para[0] != null) {
                /**
                 * *************************************
                 */
                //log_Unido.info("ENTRO A PARA DIS VACIO");
                if (para[0] instanceof Unido) {
                    tipo = ObtenerTipo(((Unido) para[0]).getValor());
                }
                 else {
                    //agregado arreglo de tipos por si se necesita pasar todo por parametros
                    if (tipos_definidos!=null){
                        if(para[0].toString().equals("")) 
                            //para correccion en caso de error por parametro null
                        {
                            tipo = ObtenerTipo(para[0]);
                        }
                        else{
                            tipo=tipos_definidos[liNumCampo];
                        }
                    }else{
                        tipo = ObtenerTipo(para[0]);
                    }
                }
                //log_Unido.info("FIN OBTENER TIPO");
                /**
                 * *************************************
                 */
                if (!isProcedimiento()) {
                    if (para[1].equals("IN")) {
                        getVar_function().addParametro(new Parametro_IN(para[0].toString(), tipo, para[0]));
                        log_Unido.info("ENTRO PARAMETRO IN--" + para[0].toString() + "  --  " + tipo + " --- " + para[0]);
                    } else if (para[1].equals("OUT")) {
                        setTipoDatoRetorno(tipo);
                        getVar_function().setTipo_Retorno(getTipoDatoRetorno());
                        if (para[0] instanceof OutputStream) {
                            getVar_function().setFile_Destino(para[0]);
                        }
                        log_Unido.info("ENTRO PARAMETRO OUT-- " + para[0].toString() + "  --  " + tipo);
                    }
                } else {
                    if (para[1].equals("IN")) {
                        getVar_procedure().addParametro(new Parametro_IN(para[0].toString(), tipo, para[0]));
                        log_Unido.info("ENTRO PARAMETRO IN-->" + para[0].toString() + "<-->" + tipo);
                    } else if (para[1].equals("OUT")) {
                        if (isParametroCursor(para[0], tipo)) {
                            setNOMBRE_CURSOR(getNOMBRE_CURSOR() + liNumCampo);
                            getVar_procedure().addParametro(new Parametro_OUT(getNOMBRE_CURSOR(), OracleTypes.CURSOR));
                        } else {
                            getVar_procedure().addParametro(new Parametro_OUT(para[0].toString(), tipo));
                            log_Unido.info("ENTRO PARAMETRO OUT-->" + para[0].toString() + "<-->" + tipo);
                        }
                    } else if (para[1].equals("IN OUT")) {
                        getVar_procedure().addParametro(new Parametro_INOUT(para[0].toString(), para[0], tipo));
                        log_Unido.info("ENTRO PARAMETRO IN OUT-->" + para[0].toString() + "<-->" + tipo);
                    }
                }
            }
        }//Fin de for
        
         if (isProcedimiento()) {
            log_Unido.info("isProcedimiento");
            getVar_procedure().execute(conecccion,getVar_procedure());

        } else {
            log_Unido.info("else isProcedimiento");
            getVar_function().execute(conecccion,getVar_function());
            setRetorno_defecto(getVar_function().getParametro("<<--RETURN_VALUE-->>"));
            //log_Unido.info("Valor retorno " + getVar_function().getParametro("<<--RETURN_VALUE-->>") + " -- ");
        }
        Recorrer();
        Global.setVariablesValidacion(vParametros2);
        return true;
    }
    public void lanzaValidacionOracle(boolean b) {
        if (b) {
            lanzaValidacionOracle();
        }
    }
    public void setFuncionValidacion(String funcionValidacion) {
        this.funcionValidacion = funcionValidacion;
    }

    /**
     * @return the var_function
     */
    private Funcion getVar_function() {
        return var_function;
    }

    /**
     * @param var_function the var_function to set
     */
    private void setVar_function(Funcion var_function) {
        this.var_function = var_function;
    }

    /**
     * @return the procedimiento
     */
    private boolean isProcedimiento() {
        return procedimiento;
    }

    /**
     * @param procedimiento the procedimiento to set
     */
    private void setProcedimiento(boolean procedimiento) {
        this.procedimiento = procedimiento;
    }
     private static boolean Conectar_BBDD() {
        boolean val_retorno = true;
        try {
            Conector_Oracle.setUser("TRON2000");
            Conector_Oracle.setPassword("TRON2000");
            Conector_Oracle.setPuerto_defecto(1521);
            Conector_Oracle.setServicio("SERVERORACLE");
            Conector_Oracle.setServidor("SERVER-JONATHAN");
            setConecccion(Conector_Oracle.getConecccion());
            Imprime(Conector_Oracle.getUrl_coneccion(),0);

        } catch (Exception e) {
            setMensajeError("--------------NO CONECTO-----------" + "\n" + e.getMessage());
            //log_Unido.error(getMensajeError());
            Imprime(getMensajeError(), 5);
            val_retorno = false;
        }

        return val_retorno;
    }

    /**
     * @param conecccion the conecccion to set
     */
    public static void setConecccion(Connection conecccion) {
        Unido.conecccion = conecccion;
    }

    /**
     * @return the MensajeError
     */
    public static String getMensajeError() {
        return MensajeError;
    }

    /**
     * @param MensajeError the MensajeError to set
     */
    public static void setMensajeError(String MensajeError) {
        Unido.MensajeError = MensajeError;
    }

    /**
     * @return the hayMensajeError
     */
    private boolean isHayMensajeError() {
        return hayMensajeError;
    }

    /**
     * @param hayMensajeError the hayMensajeError to set
     */
    private void setHayMensajeError(boolean hayMensajeError) {
        this.hayMensajeError = hayMensajeError;
    }

    /**
     * @return the var_procedure
     */
    private Procedimiento getVar_procedure() {
        return var_procedure;
    }

    /**
     * @param var_procedure the var_procedure to set
     */
    private void setVar_procedure(Procedimiento var_procedure) {
        this.var_procedure = var_procedure;
    }

    private static void Imprime(String url_coneccion,int nivel_mensaje) {
        if(nivel_mensaje==0){
          log_Unido.info(url_coneccion);  
        }
        if(nivel_mensaje==1){
          log_Unido.debug(url_coneccion);
        }
        if(nivel_mensaje==2){
          log_Unido.warn(url_coneccion);  
        }
        if(nivel_mensaje==3){
          log_Unido.error(url_coneccion);  
        }
        if(nivel_mensaje==4){
          log_Unido.trace(url_coneccion);  
        }
        if(nivel_mensaje==5){
          log_Unido.fatal(url_coneccion);  
        }

    }

    /**
     * @return Mediante un ResultSet retorna un cursor por defecto
     */
    public ResultSet getCursorDefecto() {
        if (isProcedimiento()) {
            return (ResultSet) getVar_procedure().getParametro(getNOMBRE_CURSOR());
        } else {
            return (ResultSet) getVar_function().getParametro(getNOMBRE_CURSOR());
        }
    }
    public void Recorrer() {
        String Nombre;
        Parametro p;
        int Tipo_SQL;
        if (getVar_function() != null) {
            for (Enumeration e = getVar_function().getParametros().keys(); e.hasMoreElements();) {
                Nombre = (String) e.nextElement();
                p = (Parametro) getVar_function().getParametros().get(Nombre);
                Tipo_SQL = p.getTipo_Parametro();
                if (Tipo_SQL == Parametro.OUT) {
                    Iterator it = vParametros2.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry e1 = (Map.Entry) it.next();
                        Object ob[] = (Object[]) e1.getValue();
                        if (ob[1].equals("OUT")) {
                            ob[0] = p.getValor_Parametro();
                            vParametros2.put(e1.getKey(), ob);
                        }
                    }
                    break;
                }
            }
        } else {
            for (Enumeration e = getVar_procedure().getParametros().keys(); e.hasMoreElements();) {
                Nombre = (String) e.nextElement();
                p = (Parametro) getVar_procedure().getParametros().get(Nombre);
                Tipo_SQL = p.getTipo_Parametro();
                if (Tipo_SQL == Parametro.INOUT) {
                    Iterator it = vParametros2.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry e1 = (Map.Entry) it.next();
                        Object ob[] = (Object[]) e1.getValue();
                        if (ob[1].equals("IN OUT") && Integer.valueOf(e1.getKey().toString())==(p.getPosicion()-1)) {
                            ob[0] = p.getValor_Parametro();
                            vParametros2.put(e1.getKey(), ob);
                        }

                    }
                }
                if (Tipo_SQL == Parametro.OUT) {
                    Iterator it = vParametros2.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry e1 = (Map.Entry) it.next();
                        Object ob[] = (Object[]) e1.getValue();
                        if (ob[1].equals("OUT") && Integer.valueOf(e1.getKey().toString())==(p.getPosicion()-1)) {
                            ob[0] = p.getValor_Parametro();
                            vParametros2.put(e1.getKey(), ob);
                        }

                    }
                }

            }
        }



    }

    /**
     * @return Devuelve mediante un ArrayList todos los ResultSet que se
     * pudieron haber invocado Recorralos y casteelos para imprimirlos ejemplo :
     * ResultSet rs1= (ResultSet)(var_refrencia.getParametros().get(i));
     */
    public List getCursores() {
        String Nombre;
        Parametro p;
        ArrayList lista = new ArrayList();
        for (Enumeration e = getVar_procedure().getParametros().keys(); e.hasMoreElements();) {
            Nombre = (String) e.nextElement();
            p = (Parametro) getVar_procedure().getParametros().get(Nombre);
            if (Nombre.contains("CURSOR")) {
                if (isProcedimiento()) {
                    lista.add((ResultSet) p.getValor_Parametro());
                } else {
                    lista.add((ResultSet) p.getValor_Parametro());
                }
            }
        }
        return lista;
    }

    /**
     * @return the TipoDatoRetorno
     */
    public int getTipoDatoRetorno() {
        return TipoDatoRetorno;
    }

    /**
     * @param TipoDatoRetorno Si dispones de una funcion especifica el tipo de
     * dato que retornara mediante la clase estatica Types
     */
    public void setTipoDatoRetorno(int TipoDatoRetorno) {
        this.TipoDatoRetorno = TipoDatoRetorno;
    }

    private boolean isParametroCursor(Object object, int tipo) {
        boolean estado;
        if (object instanceof Integer && Integer.parseInt(object.toString()) == OracleTypes.CURSOR) {
            estado = true;
        } else {
            estado = false;
        }
        return estado;
    }

    /**
     * @return the NOMBRE_CURSOR
     */
    private String getNOMBRE_CURSOR() {
        return NOMBRE_CURSOR;
    }

    /**
     * @param NOMBRE_CURSOR the NOMBRE_CURSOR to set
     */
    private void setNOMBRE_CURSOR(String NOMBRE_CURSOR) {
        this.NOMBRE_CURSOR = NOMBRE_CURSOR;
    }
    /**
     * @return the retorno_defecto
     */
    public Object getRetorno_defecto() {
        return retorno_defecto;
    }

    /**
     * @param retorno_defecto the retorno_defecto to set
     */
    public void setRetorno_defecto(Object retorno_defecto) {
        this.retorno_defecto = retorno_defecto;
    }
    /**
     * @return the coneccion_correcta
     */
    public static boolean isConeccion_correcta() {
        return coneccion_correcta;
    }

    /**
     * @param aConeccion_correcta the coneccion_correcta to set
     */
    public static void setConeccion_correcta(boolean aConeccion_correcta) {
        coneccion_correcta = aConeccion_correcta;
    }
    public void setText(String texto) {
        _texto = texto;
        setValor(_texto);
    }
    public static void getConeccion() {
        setConeccion_correcta(Conectar_BBDD());
    }
    public String getText() {
        String retorno;
        if (getValor() != null && _texto == null) {
            retorno = String.valueOf(getValor());
        } else {
            retorno = _texto;
        }
        return retorno;
    }

    public void setValor(Object valor) {
        _valor = valor;
    }

    public Object getValor() {
        if (Global.getVariablesValidacion() != null) {
            Iterator it = Global.getVariablesValidacion().entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e1 = (Map.Entry) it.next();
                Object ob[] = (Object[]) e1.getValue();
                if (String.valueOf(ob[1]).contains("OUT") && 
                        getClave() == Integer.valueOf(e1.getKey().toString()).intValue()) {
                    _valor = ob[0];
                }

            }
        }

        return _valor;
    }

    private void setClave(int piNumero) {
        secuencia = piNumero;
    }

    public int getClave() {
        return secuencia;
    }

    private int ObtenerTipo(Object para) {
        int tipo = -1;
        //log_Unido.info("****ObtenerTipo**** "+para);
        if ((para == (Object) OracleTypes.CURSOR)) {
            tipo = OracleTypes.CURSOR;
        }
        if (para instanceof Boolean) {
            //log_Unido.info("Es boolean");
            tipo = Types.BOOLEAN;
        }
        if (para instanceof Character) {
            //log_Unido.info("Es caracter");
            tipo = Types.VARCHAR;
        }
        if (para instanceof Integer) {
            //log_Unido.info("ES NUMERO");
            tipo = Types.INTEGER;
        }
        if (para instanceof String) {
            //log_Unido.info("ES CADENA");
            tipo = Types.VARCHAR;
        }
        if (para instanceof Double) {
            //log_Unido.info("ES DOUBLE");
            tipo = Types.DOUBLE;
        }
        if (para instanceof Float) {
            //log_Unido.info("ES FLOAT");
            tipo = Types.NUMERIC;
        }
        if (para instanceof Long) {
            //log_Unido.info("ES LONG");
            tipo = Types.NUMERIC;
        }
        if (para instanceof InputStream) {
            //log_Unido.info("ES BLOB");
            tipo = OracleTypes.BLOB;
        }
        if (para instanceof OutputStream) {
            //log_Unido.info("ES BLOB");
            tipo = OracleTypes.BLOB;
        }
        //log_Unido.info(tipo);
        return tipo;
    }

    static{
        PropertyConfigurator.configure("src/palomino/configuracion/LogStores.properties");
    }
}
