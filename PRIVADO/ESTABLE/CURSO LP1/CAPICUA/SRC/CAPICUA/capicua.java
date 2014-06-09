/*
 * Realizar el programa que asigne aleatoriamente el numero entero entre
 * 3 y 5 cifras , se pide calcular e imprimir cuantos nùmeros asignados 
 *son capicuas
 */

package capicua;

public class capicua {
   
    public static void main(String[] args) 
    {
     int n,num,i,cif,x,inv,cont;
     n=(int)(Math.random()*100); // veces a repetir
     for (i=0;i<n;i++)
         {
         num=(int)(Math.random()*99900+100);
         x=num;
         inv =0;
         cont=0;
         do
             {
             cif=num%10;// obtener ultima cifra
             inv=inv*10+cif; //invirtiendo el numero
             num= num/10; //Quitandole la ultima cifra
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
