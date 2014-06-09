/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.carga;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import palomino.base_datos.stores.gestores.Unido;
/**
 *
 * @author JONATHAN
 */
public class Insert_procedure {

    static InputStream input = null;

    public Insert_procedure() {
        try {
            File resume = new File("D:\\EXPO_OPM3.pptx");
            try {
                String doc = "D:\\EXPO_OPM3.pptx";
                input = new FileInputStream(resume);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        Unido gen = new Unido();
        gen.setFuncionValidacion("P_CARGA_BLOB");
        gen.addParametros(1, "IN", 0, 3);
        gen.addParametros("EXPO_OPM3.pptx", "IN", 1, 3);
        gen.addParametros(input, "IN", 2, 3);
        System.out.println(gen.lanzaValidacionOracle() +"  "+Unido.getMensajeError());
    }

    public static void main(String args[]) {
        new Insert_procedure();
    }
}
