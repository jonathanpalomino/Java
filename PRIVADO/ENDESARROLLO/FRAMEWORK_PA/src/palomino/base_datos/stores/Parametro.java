package palomino.base_datos.stores;

public class Parametro {

public static final int IN =1;
public static final int OUT =2;
public static final int INOUT =3;

private int Tipo_Parametro;
private int Tipo_Parametro_SQL;
private String Nombre_Parametro;
private Object Valor_Parametro;

private int Posicion;

    public Parametro(String nombre, int SQL, int Tipo_Parametro, Object Valor_Parametro) {
        this.Tipo_Parametro = Tipo_Parametro;
        this.Tipo_Parametro_SQL = SQL;
        this.Nombre_Parametro = nombre;
        this.Valor_Parametro = Valor_Parametro;
    }

    /**
     * @return the Tipo_Parametro
     */
    public int getTipo_Parametro() {
        return Tipo_Parametro;
    }

    /**
     * @param Tipo_Parametro the Tipo_Parametro to set
     */
    public void setTipo_Parametro(int Tipo_Parametro) {
        this.Tipo_Parametro = Tipo_Parametro;
    }

    /**
     * @return the Tipo_Parametro_SQL
     */
    public int getTipo_Parametro_SQL() {
        return Tipo_Parametro_SQL;
    }

    /**
     * @param Tipo_Parametro_SQL the Tipo_Parametro_SQL to set
     */
    public void setTipo_Parametro_SQL(int Tipo_Parametro_SQL) {
        this.Tipo_Parametro_SQL = Tipo_Parametro_SQL;
    }

    /**
     * @return the Nombre_Parametro
     */
    public String getNombre_Parametro() {
        return Nombre_Parametro;
    }

    /**
     * @param Nombre_Parametro the Nombre_Parametro to set
     */
    public void setNombre_Parametro(String Nombre_Parametro) {
        this.Nombre_Parametro = Nombre_Parametro;
    }

    /**
     * @return the Valor_Parametro
     */
    public Object getValor_Parametro() {
        return Valor_Parametro;
    }

    /**
     * @param Valor_Parametro the Valor_Parametro to set
     */
    public void setValor_Parametro(Object Valor_Parametro) {
        this.Valor_Parametro = Valor_Parametro;
    }

    /**
     * @return the Posicion
     */
    public int getPosicion() {
        return Posicion;
    }

    /**
     * @param Posicion the Posicion to set
     */
    public void setPosicion(int Posicion) {
        this.Posicion = Posicion;
    }


}