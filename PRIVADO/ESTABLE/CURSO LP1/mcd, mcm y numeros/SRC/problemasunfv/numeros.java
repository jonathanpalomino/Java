/*
 * Realizar el programa que asigen aleatoriamente n numeros enteros se pide
 * calcular e imprimir cuantos numeros empiesan y terminan en la misma cifra.
 */

package problemasunfv;

public class numeros {
        public static void main(String[] args) {
            int n,i,d,d1=0,contador = 0;

            n=(int)(Math.random()*20);
            if (n==0)
                n=1;
            i=1;
            while (i<=n)
            {
                d=(int)(Math.random()*1000);
                System.out.println(d);
                int r=1,k=0;
                k=d;
                d1=k%10;
                while (d!=0)
                {
                    r=d%10;
                    d=d/10;
                 }
                //System.out.println(r+" y "+d1);
                if (r==d1)
                {
                    //System.out.println("Si cumple la condicion");
                    contador++;
                }
                else
                {
                    //System.out.println("No cumple la condicion");
                }
                i++;
            }
            System.out.println("Son en total "+contador+" los que cumplen con la condicion");
   }
}