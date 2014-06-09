/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package animacionconlineasenjava;
//NOTA "NUNCA USAR BURBUJA"
public class arreglo{

    public static void main(String[] args) {
        int n, m, i, j;
        int empresa;
        n = 2;
        m = 12;

        empresa = (int) (Math.random() * 10 + 1);
        int arreglo[][] = new int[n + 1][m + 1];
        int temporal1[] = new int[m + 1];
        int temporal2[] = new int[m + 1];
        for (int h = 0; h < empresa; h++) {
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    arreglo[i][j] = (int) (Math.random() * 2000 + 1001);

                    System.out.println(arreglo[i][j]);
                }
                System.out.println("FIN DE AñO");
            }
            System.out.println("Fin de empresa ");

            for (int t = 0; t < m; t++)//Transfiere a un temporal empresa 1
            {
                temporal1[t] = arreglo[0][t];
            }
            for (int w = 0; w < m; w++)//Transfiere a un temporal empresa 2
            {
                temporal2[w] = arreglo[1][w];
            }
            quick_sort(temporal1, 0, temporal1.length - 1);
            quick_sort(temporal2, 0, temporal2.length - 1);
            System.out.println("la mayor venta en el 1° año es " + temporal1[m]);
            System.out.println("la menor venta en el 1° año " + temporal1[1]);
            System.out.println("la mayor venta en el 2° año " + temporal2[m]);
            System.out.println("la menor venta en el 2° año " + temporal2[1]);
            System.out.println();
        }
    }

//Aca esta el quick sort lo llame como metodo veran como se transfieren los datos
    public static void quick_sort(int array[], int low, int n) {
        int bajo = low;
        int alto = n;
        if (bajo >= n) {
            return;
        }
        int pivote = array[(bajo + alto) / 2];
        while (bajo < alto) {
            while (bajo < alto && array[bajo] < pivote) {
                bajo++;
            }
            while (bajo < alto && array[alto] > pivote) {
                alto--;
            }
            if (bajo < alto) {
                int T = array[bajo];
                array[bajo] = array[alto];
                array[alto] = T;
            }
        }
        if (alto < bajo) {
            int T = alto;
            alto = bajo;
            bajo = T;
        }
        quick_sort(array, low, bajo);
        quick_sort(array, bajo == low ? bajo + 1 : bajo, n);
    }
}
