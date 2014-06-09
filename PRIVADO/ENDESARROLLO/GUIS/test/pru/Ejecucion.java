/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pru;

import java.util.StringTokenizer;

/**
 *
 * @author synccon
 */
public class Ejecucion {
    public static void main(String args[]){
        String a ="ESte\nes un texto\n\n\nHola";
        String f[] = a.split("\n");
        for(int i=0;i<f.length;i++){
            System.out.println(i+" - "+f[i]);
        }
        System.out.println("---------------------------------------");
        StringTokenizer token =new StringTokenizer(a,"", false);
        while(token.hasMoreElements()){
            System.out.println(token.nextElement());
        }
    }
}
