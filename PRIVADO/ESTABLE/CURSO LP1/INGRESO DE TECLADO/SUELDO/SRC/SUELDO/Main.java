/*
 * Realizar el programa que asigne aleatoriamente el sueldo basico,bonificacion
 * y descuento de un trabajador ; se pide calcular el sueldo neto de
 *dicho trabajador.
 * Sueldo entre 800 y 1500 soles.
 * Bonificación entre 200 y 600 soles.
 * Descuento menor igual a 500.
 */

package sueldo;
import javax.swing.JOptionPane;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double sb=0,bo=0,de=0,sn;
        
        sb=Integer.parseInt(JOptionPane.showInputDialog("Ingrese sueldo basico de 800 a 1500"));//(int)(Math.random()*701+800);

        bo=Integer.parseInt(JOptionPane.showInputDialog("Ingrese bonificacion de 200 a 600"));//(int)(Math.random()*401+200);
        de=Integer.parseInt(JOptionPane.showInputDialog("Ingrese descuento menor a 500"));//(int)(Math.random()*501);
        sn=sb+bo-de;
        System.out.println("El sueldo a recibir es :"+sn);
    }

}

