/*
 * Realizar el programa que asigne aleatoriamente n nùmeros enteros se pide calcular e imprimir
 * la cantidad de cifras que tiene cada nùmero asignado.
 */

package numerodecifras;
import javax.swing.JOptionPane;
public class Main {
public static void main(String[] args) {

       int n,num,cif,i,x;
       n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese las veces a probar"));//(int)(Math.random()*100);
        //(int)(Math.random()*100);
        int p = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el rango a probar"));
       for(i=0; i<=n; i++)
       {
           num=(int)(Math.random()*p);
           x=num;
           cif=0;
           while  (num!=0)
       {
               num= num/10;
               cif++;
       }
      System.out.println("El nùmero "+x+" tiene "+cif+" cifras");

        }
    }

}
