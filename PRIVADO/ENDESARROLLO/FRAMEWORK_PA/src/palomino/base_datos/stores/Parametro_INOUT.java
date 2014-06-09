package palomino.base_datos.stores;

public class Parametro_INOUT extends Parametro {

    public Parametro_INOUT(String nombre,Object Valor_Parametro, int SQL)
    {
        super(nombre, SQL, INOUT, Valor_Parametro);
    }


}