/*
 * Realizar el programa que asigne aleatoriamente el sueldo basico,bonificacion
 * y descuento de un trabajador ; se pide calcular el sueldo neto de
 *dicho trabajador.
 * Sueldo entre 800 y 1500 soles.
 * Bonificación entre 200 y 600 soles.
 * Descuento menor igual a 500.
 */

package sueldo;

/**
 *
 * @author JONATHAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double sb,bo,de,sn;
        sb=(int)(Math.random()*701+800);
        bo=(int)(Math.random()*401+200);
        de=(int)(Math.random()*501);
        sn=sb+bo-de;
        System.out.println("El sueldo a recibir es :"+sn);
    }

}
