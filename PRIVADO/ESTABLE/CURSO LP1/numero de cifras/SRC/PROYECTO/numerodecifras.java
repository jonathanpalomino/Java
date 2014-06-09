/*
 * Realizar el programa que asigne aleatoriamente n nùmeros enteros se pide calcular e imprimir
 * la cantidad de cifras que tiene cada nùmero asignado.
 */

package proyecto;
public class numerodecifras {
public static void main(String[] args) {
    
       int n,num,cif,i,x;
       n=(int)(Math.random()*100);
       
       for(i=0; i<=0; i++)
       { 
           num=(int)(Math.random()*10000);
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
