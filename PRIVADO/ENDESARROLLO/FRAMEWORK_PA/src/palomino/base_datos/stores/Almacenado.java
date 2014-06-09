package palomino.base_datos.stores;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Hashtable;
import oracle.jdbc.OracleTypes;

public class Almacenado {

    public static final int PROCEDIMIENTO = 1;
    public static final int FUNCION = 2;
    private String Nombre_procedimiento;
    private int tipo;   //Debe ser un procedimiento o una funcion
    private Hashtable parametros;   //coneccion de parametros
    private int indice_parametro = 1;   //posicion relativa de los parametros

    protected Almacenado(String Nombre_procedimiento, int tipo) {
        this.Nombre_procedimiento = Nombre_procedimiento;
        this.tipo = tipo;
        this.parametros = new Hashtable();
    }

    public void addParametro(Parametro p) {
        String Nombre_parametro = p.getNombre_Parametro();
        //cada elemento tiene una posicion relativa a la tabla
        p.setPosicion(indice_parametro++);
        Nombre_parametro=Nombre_parametro+p.getPosicion(); //Para que en caso de entrar vacio
        //o tener valor siempre se agregara el indice del parametro
        parametros.put(Nombre_parametro, p);
    }

    public Object getParametro(String pName) {
        Parametro p = (Parametro) parametros.get(pName);
        return p.getValor_Parametro();
    }

    public Hashtable getParametros() {
        return parametros;
    }

    public void execute(Connection conexion) {
        execute(conexion, null);
    }

    public void execute(Connection conexion, Object origen) {
        CallableStatement Llamado = null;
        try {
            //preparar sql
            String sql = _obtenerSQL(tipo);
            Llamado = conexion.prepareCall(sql);
            //Setear parametros
            Parametro p;
            String Nombre;
            int posicion;
            int Tipo_SQL;

            for (Enumeration e = parametros.keys(); e.hasMoreElements();) {
                Nombre = (String) e.nextElement();
                p = (Parametro) parametros.get(Nombre);
                posicion = p.getPosicion();
                Tipo_SQL = p.getTipo_Parametro_SQL();
                switch (p.getTipo_Parametro()) {
                    case Parametro.IN:
                        if (Tipo_SQL == OracleTypes.BLOB) {
                            Llamado.setBinaryStream(posicion, (InputStream) p.getValor_Parametro());
                        } else {
                            Llamado.setObject(posicion, p.getValor_Parametro());
                        }
                        break;
                    case Parametro.OUT:
                        Llamado.registerOutParameter(posicion, Tipo_SQL);
                        break;
                    case Parametro.INOUT:
                        //Los parametos entrada-salida se procesan se setean igual que los de salida
                        //pero se setea de entrada
                        
                        //registrar como parametro de salida
                        Llamado.registerOutParameter(posicion, Tipo_SQL);               
                        //seteao como valor de entrada
                        Llamado.setObject(posicion, p.getValor_Parametro());

                        break;
                }
            }

            //ejecutar
            Llamado.execute();

            //seteo en los parametros de la tabla los resultados de saida
            //del parametro

            for (Enumeration e = parametros.keys(); e.hasMoreElements();) {

                Nombre = (String) e.nextElement();
                p = (Parametro) parametros.get(Nombre);
                posicion = p.getPosicion();
                Tipo_SQL = p.getTipo_Parametro();
                if (Tipo_SQL == Parametro.OUT || Tipo_SQL == Parametro.INOUT) {
                    //tomo el valor del parametro en la posicion
                    Object Parametro_respuesta = Llamado.getObject(posicion);
                    if (Parametro_respuesta != null) {
                        if (Tipo_SQL == FUNCION && Parametro_respuesta instanceof oracle.sql.BLOB) {
                            //significa que retorna un blob
                            oracle.sql.BLOB blob = (oracle.sql.BLOB) Parametro_respuesta;
                            InputStream inStream = blob.getBinaryStream();
                            int size = (int) blob.length();
                            byte[] buffer = new byte[size];
                            int length = -1;
                            //escribe en disco el fichero pasado como parametro
                            while ((length = inStream.read(buffer)) != -1) {
                                ((Funcion) origen).getFile_destino().write(buffer, 0, length);
                            }
                        }
                        //seteo como valor en el objeto de la tabla
                        p.setValor_Parametro(Parametro_respuesta);
                    }
                    //System.out.println(" --- "+p.getValor_Parametro());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            try {
                if (Llamado == null) {
                    Llamado.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private String _obtenerSQL(int spType) {
        //"{? = call MIFUNCION(?,?,?)}";
        //"{ call MI_PROCEDIMIENTO(?,?,?,?) }";
        String SFuncion = (spType == FUNCION ? "?=" : "");
        int cantParametros = (spType == FUNCION) ? parametros.size() - 1 : parametros.size();

        String sql = "";
        sql += "{ " + SFuncion + " call ";
        sql += Nombre_procedimiento + "(";

        for (int i = 0; i < cantParametros; i++) {
            sql += "?" + ((i < cantParametros - 1) ? "," : ")}");
        }

        return sql;
    }
}