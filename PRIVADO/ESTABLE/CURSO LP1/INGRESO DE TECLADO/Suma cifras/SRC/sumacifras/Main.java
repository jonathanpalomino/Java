/*
 * Realizar el programa que asigne aleatoriamente a un arreglo y lineal ene
 números enteros se pide calcular imprimida cuantos números
 * o elementos del arreglo si la suma de sus cifras impares da un numero primo.
 */

package sumacifras;
import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
       int m,i,z=0,t,res;
       m=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de elementos del arreglo a operar mayor a 50000 de preferencia"));
       int epsilon[] = new int [m+1];
        //Inicio de operaciones
       for (i=0;i<m;i++)
       {
           epsilon[i]=(int)(Math.random()*1000000000);

           t=0;
           //Verificando si tiene cifras impares
           boolean tienePar=false;
           int y=10;
           int x=0, n = epsilon[i];
           while (y>0)
           {
               x=n%10; //resto
               y=n/10; //cociente;
               n=y;
               if ((x%2)==0)
               {
                   tienePar = true; break;

               }
           }
           if (tienePar ) break;
          System.out.println(epsilon[i]);
           //iniciando conteo de cifras
           while (epsilon[i]!=0)
           {
               z=epsilon[i]%10;
               epsilon[i] = epsilon[i]/10;
               t=t+z;

           }//Fin del conteo de cifras

            //Verificando si es primo
           res=0;
           for (int ir=1;ir<=t;ir++)
             {
               if (t%ir==0)
               {
                   res++;
               }
             }

           if (res==2)
                System.out.println("la suma da " +t+" por lo tanto es primo");
           else
               System.out.println("la suma da " +t+" por lo tanto no es primo");

            //Fin de verificacion

       }
       System.out.println("El arreglo cuenta con "+m+ " elementos");
    }

}


