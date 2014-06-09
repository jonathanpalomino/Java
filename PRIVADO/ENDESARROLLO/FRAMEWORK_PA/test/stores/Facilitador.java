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
public class Facilitador {

    public Facilitador() {
        Unido.getConeccion();
        Unido abc = new Unido();
        Unido val2 = new Unido();
        Unido val3 = new Unido();
        Unido val4 = new Unido();
        val3.setValor(456);
        //val2.setValor("ggg");
        val2.setText("uuuu");
        val4.setValor("ddd");
        abc.setValor(666);
        /*abc.setFuncionValidacion("?:=MK_EMP");
        abc.addParametros(abc, "OUT", 0, 4);
        //abc.addParametros(valuador, "OUT", 0, 4);
        abc.addParametros(val2, "IN", 1, 4);
        abc.addParametros(val4, "IN", 2, 4);
        abc.addParametros(val3, "IN", 3, 4);
        //abc.lanzaValidacionOracle();
        abc.lanzaValidacionOracle(true);
        //System.out.println(" retorno0 "+val3.getValor() +" -- "+val3.getClave());
        //System.out.println(" retorno1 "+val2.getValor()  +" -- "+val2.getClave());
        //System.out.println(" retorno2 "+val4.getValor() +" -- "+val4.getClave() );
        System.out.println(" retorno3 "+abc.getValor() +" -- "+abc.getClave() );
        */
        val2.setFuncionValidacion("TR");
        val2.addParametros(val2, "IN", 0,3);
        val2.addParametros(val4, "OUT", 1,3);
        val2.addParametros(abc, "OUT", 2,3);
        val2.lanzaValidacionOracle();
        System.out.println(val4.getValor());
        System.out.println(abc.getValor());
    }

    
    public static void main(String[] args) {
        new Facilitador();
    }
}
