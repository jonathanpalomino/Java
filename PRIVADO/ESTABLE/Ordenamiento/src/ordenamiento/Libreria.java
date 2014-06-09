/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ordenamiento;

import java.util.ArrayList;
/**
 *
 * @author JONATHAN
 */
class Libreria {
    private int[] vector;
    private int elementos;

    public Libreria() {
    }
    //para imprimir el array de la forma / 0 / 1 / 2 / 3 / 4 /5

    public String Visualizar(int[] v) {
        String s = " / ";
        for (int i = 0; i < v.length; i++) {
            s += v[i] + " / ";
        }
        return s;
    }

    int[] ordenar(int[] v) {
        this.vector = v;
        elementos = vector.length;
        ArrayList uno = new ArrayList();
        for (int i = 0; i < elementos; i++)
        {
            uno.add(vector[i]);//Recorro el arreglo y agrego cada elemento al arraylist
        }
        for(int i=0;i<elementos;i++)
        {
            vector[i]=toEntero(uno.get(indice_Menor(uno)));//Asigno al arreglo
            //primitivo el elemento menor del arraylist
            uno.remove(toEntero(indice_Menor(uno)));
            //Proceso a eliminar dicho elemento del arraylist
            //Se ejecuta un bucle rescatando el menor elemento.
            //Asi ordenaria mi arreglo
        }
        return vector;
    }

    private int indice_Menor(ArrayList indice) {
        int b = 0;
        for (int i = 0; i < indice.size(); i++) {
            if (toEntero(indice.get(i)) < toEntero(indice.get(b))) {
                b = i;
            }
        }
        return b;
    }

    private int toEntero(Object get) {
        //los arraylist retornan objetos pero necesito trabajar con enteros
        //Simplemente convierto a entero un objeto cuando es llamada esta funcion
        return Integer.parseInt(get.toString());
    }

}
