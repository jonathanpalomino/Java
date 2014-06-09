package palomino.base_datos.stores;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;

public class Funcion extends Almacenado{
//Definir una constante String para denominar un parametro especial
    private static String RETURN_VALUE ="<<--RETURN_VALUE-->>";
    private FileOutputStream file_destino=null;
    public Funcion(String Nombre_procedimiento) {
        super(Nombre_procedimiento, FUNCION);
    }
    //con este metodo especificamos el tipo SQL del valor de retorno
    
    public void setTipo_Retorno(int i){
        //vemos que el valor de retorno de la funcion simplemente
        //lo manejamos como un aprametro de salida
        
        addParametro(new Parametro_OUT(RETURN_VALUE,i));
    }
    //obtener el valor de retorno como object
    public Object getValor_Retorno(){
        return getParametro(RETURN_VALUE);
    }
    
    //Hacemos un metodo que castee el valor de retorno a cada tipo
    //que podamos necesitar
    public Number getValor_Retorno_EsNumero(){
        return ((Number)getValor_Retorno()).floatValue();
    }
    public boolean getValor_Retorno_EsBoolean(){
        return ((Boolean)getValor_Retorno()).booleanValue();
    }
    public String getValor_Retorno_EsString(){
        return ((String)getValor_Retorno());
    }
    public double getValor_Retorno_EsDouble(){
        return ((Double)getValor_Retorno()).doubleValue();
    }
    public float getValor_Retorno_EsFloat(){
        return ((Float)getValor_Retorno()).floatValue();
    }
    public int getValor_Retorno_EsInt(){
        return ((Integer)getValor_Retorno()).intValue();
    }
    public long getValor_Retorno_EsLong(){
        return ((Long)getValor_Retorno()).longValue();
    }
    public short getValor_Retorno_EsShort(){
        return ((Short)getValor_Retorno()).shortValue();
    }
    public Date getValor_Retorno_EsFecha(){
        return ((Date)getValor_Retorno());
    }
    public Timestamp getValor_Retorno_EsTimestamp(){
        return ((Timestamp)getValor_Retorno());
    }
    /**
     * @return the file_destino
     */
    public FileOutputStream getFile_destino() {
        return file_destino;
    }
    /**
     * @param file_destino the file_destino to set
     */
    public void setFile_destino(FileOutputStream file_destino) {
        this.file_destino = file_destino;
    }
    public void setFile_Destino(Object file_destino) {
        this.file_destino =(FileOutputStream) file_destino;
    }
}