/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.File;

/**
 *
 * @author JONATHAN
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String imagenpng = "abc.docxff";
        System.out.println(imagenpng.lastIndexOf("."));
        System.out.println(imagenpng.length());
        String nombreArchivo = imagenpng.substring(0, imagenpng.lastIndexOf("."));
        System.out.println(nombreArchivo);
        System.out.println(imagenpng.replace(nombreArchivo+".", ""));
        
        String ss="C:/imagen2.png";
        File abc = new File(ss);
        System.out.println(abc.getName());
        
    }
}
