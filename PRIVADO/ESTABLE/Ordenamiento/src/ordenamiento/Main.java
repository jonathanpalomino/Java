/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamiento;

import java.util.Random;

/**
 *
 * @author JONATHAN
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        int[] arreglo_temp = null;
        arreglo_temp = Crear_lista(5);
        Libreria objeto = new Libreria();
        System.out.println("ORIGINAL : "+ objeto.Visualizar(arreglo_temp));
        System.out.println("ORDENADO : "+ objeto.Visualizar(objeto.ordenar(arreglo_temp)));
    }

    private static int[] Crear_lista(int n) {
        int[] arre = new int[n];
        Random generador = new Random();
        for (int i = 0; i < arre.length; i++)
        {
            arre[i] = generador.nextInt(10);
        }
        return arre;

    }
}
