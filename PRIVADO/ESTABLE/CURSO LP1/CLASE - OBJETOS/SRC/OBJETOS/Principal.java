/*
 * Realizar el programa que asigne aleatoriamente n numeros enteros se pide 
 * calcular e imprimir el factorial y la suma de cifras de cada numero asignado
 */

package objetos;
public class Principal {
public static void main(String[] args) {
        long i,n,num,fact,res,x,sumcif;
         n=(int) (Math.random() * 100);
         for(i=1;i<=n;i++)
             {
              num =(int) (Math.random() * 20);
              fact=1;
              for(int j=1;j<=num;j++)
                  {
                  fact=fact*j;
              }
              System.out.println("El factorial de "+num+"  es :"+fact);
              sumcif=0;
              x=num;
              while(num!=0)
               {  
                  res=num%10;
                  sumcif=sumcif+res;
                  num=num/10;
              } 
              System.out.println("la suma de cifras de  "+x+"  es "+sumcif);
    }

}
}