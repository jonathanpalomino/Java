/*
 *  Realizar un programa que asigne aleatoriamente a un arreglo de m x n (entero)
 *  se pide calcular e imprimir la cantidad de números
 *  que empiezan y terminan con las misma cifra en las dos diagonales
 *  principales del arreglo cuando m = n ..
 */

package arreglodiagonal;

public class arreglodiagonal {

    public static void main(String[] args) {
        int m,i,j,n=0,t=0,k=0,q=0,x = 0,c=0;
        m=3;
        int delta[][] = new int[m+1][m+1];
        for (i=0;i<m;i++)
        {
            for(j=0;j<m;j++)
            {
                delta[i][j] = (int)(Math.random()*1000);
                c=0;
                if(i==j)//buscando diagonal derecha y comprobacion
                {
                    	n=delta[i][j];
                        int y=10;
                        x=0;
                        t=0;
                        t=n;
                        {
                        t=t%10;
                         }
                        while (y>0)
                        {
                            x=n%10; //resto
                            y=n/10; //cociente;
                            n=y;
                        }
                System.out.println("es "+x+ " y "+t);
                }
                //Fin de condicional de diagonal
                k=x*10+t;
                

              }
         
    }

             
        System.out.println("es la cantidad de números que comiensan y terminan " +
                "con la misma cifra en la 1° diagonal son "+c);
}
}