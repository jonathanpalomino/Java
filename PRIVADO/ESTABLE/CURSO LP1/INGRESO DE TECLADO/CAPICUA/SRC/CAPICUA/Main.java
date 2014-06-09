/*
 * Realizar el programa que asigne aleatoriamente el numero entero entre
 * 3 y 5 cifras , se pide calcular e imprimir cuantos nùmeros asignados
 *son capicuas
 */

package capicua;
import javax.swing.JOptionPane;
public class Main {


    public static void main(String[] args)
    {
     int n,num,i,cif,x,inv,cont;
     n=Integer.parseInt(JOptionPane.showInputDialog("ingrese en numero de veces a evaluar"));//(int)(Math.random()*100);
     for (i=0;i<=n;i++)
         {
         num=(int)(Math.random()* 99900+100);
         x=num;
         inv =0;
         cont=0;
         do
             {
             cif=num%10;
             inv=inv*10+cif;
             num= num/10;
             }
         while (num!=0);
         if(inv==x)
            {
             cont++;
         System.out.println(x+" es capicua" );
            }
         else
             System.out.println(x+"  no es capicua");
         }
    }

}

