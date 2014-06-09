/*
 * Crear el programa que me permitan generar alatoriamente n numeros enteros ordenarlos y luego ingresar 10 por teclado
 * Uso de arreglos.
 *
 */

package naleatorioy10teclado;
import javax.swing.JOptionPane;
public class Numerosaleatoriosytecladodespuess {

    public static void main(String[] args) {
    int n,i;
    n=(int)(Math.random()*10);
    int arreglo [] = new int [n+11];
    for (i=0;i<n;i++)
    {
        arreglo[i]=(int)(Math.random()*100);
        System.out.println(arreglo[i]);
        
    }
    System.out.println("numero generado es "+i);
    //inicializando parametros de burbuja
    int m=0,aux=0,w=0;
    //llamando burbuja
    while (m<i && w==0)
    {w=1;
    for(int j=0;j<(i-m);j++)
    if(arreglo[j]<arreglo[j+1])
    {
    	aux = arreglo[j];
        arreglo[j] = arreglo[j+1];
        arreglo[j+1]=aux;
        w=0;
    }
    m++;
    }
   System.out.println("ordenado el primero es : "+arreglo[0]);
   for(int g=i+1;i<n+10;i++)
   {
       arreglo[i]=Integer.parseInt(JOptionPane.showInputDialog("Ingrese los numeros extras"));
   }

   //volver a ordenar y presentar en pantalla
    //inicializando parametros de burbuja
    int m1=0,aux1=0,w1=0;
    //llamando burbuja
    while (m1<i && w1==0)
    {w1=1;
    for(int j=0;j<(i-m);j++)
    if(arreglo[j]<arreglo[j+1])
    {
    	aux1 = arreglo[j];
        arreglo[j] = arreglo[j+1];
        arreglo[j+1]=aux1;
        w1=0;
    }
    m1++;
    }
    System.out.println("ordenado el primero final es : "+arreglo[0]);
}
}//fin de main


/*Se uso metodo de burbuja modificado*/