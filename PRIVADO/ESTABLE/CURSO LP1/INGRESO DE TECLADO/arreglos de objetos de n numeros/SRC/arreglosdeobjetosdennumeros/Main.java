/*
 * Crear el arreglo de objetos de N numeros enteros , calcular la cantidad de cifras pares e impares asi como los elementos con
 * 3 cifras impares.
 */

package arreglosdeobjetosdennumeros;
import javax.swing.JOptionPane;
public class Main {

   public static void main(String[] args) {
   int n;
   n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de numeros"));
   secundaria[] obj = new secundaria[n+1];
   for(int i=0;i<n;i++)
   {
   obj[i]=new secundaria();
   obj[i].tra();
    obj[i].h();
   }
   
    }

}
