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
public class Esquema_Sentencia {

    public static void main(String args[]) {
        String cSentencia = "TRON2000.PRUEBA";
        String[] aFormatos = {"IN", "OUT", "IN OUT"};
        Object[] aValores = {"DATA","DATA2",20};
        int[] aTipos = {Unido.VARCHAR, Unido.VARCHAR, Unido.INTEGER};
        Unido test = new Unido();

        Object[] vaso= test.ejecutaSentencia(cSentencia, aFormatos, aValores);
        for(int i=0;i<vaso.length;i++){
            System.out.println(((Object[]) vaso[i])[0] +" *** "+((Object[]) vaso[i])[1]);
        }
        Object[] vaso2 =test.ejecutaSentencia(cSentencia, aFormatos, aValores, aTipos);
        for(int i=0;i<vaso2.length;i++){
            System.out.println(((Object[]) vaso2[i])[0] +" *** "+((Object[]) vaso2[i])[1]);
        }
    }
}
