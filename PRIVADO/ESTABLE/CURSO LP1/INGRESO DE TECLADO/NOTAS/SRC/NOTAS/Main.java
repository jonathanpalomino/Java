/*
 *  Realizar el programa en java que ingrese las notas del examen
 * parcial final y promedio de practicas de un alumno.Se pide calcular
 * e imprimir el promedio final del alumno.
 */

package notas;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        int ep,ef,pp;
        double pf;
        ep=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Examen parcial"));//(int)(Math.random()*21);
        ef=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Examen parcial"));
        pp=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Examen parcial"));
        pf=(ep+ef+pp)/3.0;
        System.out.println("El promedio es: "+pf);


            }

}

