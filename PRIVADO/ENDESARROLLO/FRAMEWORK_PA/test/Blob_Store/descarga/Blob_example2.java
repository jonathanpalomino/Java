/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.descarga;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import palomino.base_datos.stores.gestores.Unido;
        
/**
 *
 * @author JONATHAN
 */
public class Blob_example2 {

    OutputStream os = null;
    Integer valor = 1;

    public Blob_example2() {
        try {
            os = new FileOutputStream("D:\\fichero.pptx");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Blob_example2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Unido gen = new Unido();
        Unido.getConeccion();
        gen.setFuncionValidacion("?:=RECUPERA_BLOB");
        gen.addParametros(os, "OUT", 0, 2);
        gen.addParametros(valor, "IN", 1, 2);
        gen.lanzaValidacionOracle();

    }

    public static void main(String args[]) {
        new Blob_example2();
    }
}
