package palomino.base_datos.stores;

public class Parametro_IN extends Parametro {

    public Parametro_IN(String nombre,int SQL,Object Valor_Parametro){
        //simplemente pasa todos los parametros a super seteando IN
        //como tipo de parametro
        super(nombre, SQL, IN, Valor_Parametro);
    }


}