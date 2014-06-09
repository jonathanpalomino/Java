/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import palomino.base_datos.stores.gestores.Unido;

/**
 *
 * @author JONATHAN
 */
public class Ejemplo_Procedure {

    Unido abc = new Unido();
    Object var1="a";
    Object var2="b";
    Integer var3=1;

    public Ejemplo_Procedure() {
        Unido.getConeccion();
        abc.setFuncionValidacion("PRUEBA");
        abc.addParametros(var1, "IN", 0, 3);
        abc.addParametros(var2, "OUT", 1, 3);
        abc.addParametros(var3, "IN OUT", 2, 3);
        abc.lanzaValidacionOracle();
    }

    public static void main(String[] args) {
        new Ejemplo_Procedure();
    }
}
