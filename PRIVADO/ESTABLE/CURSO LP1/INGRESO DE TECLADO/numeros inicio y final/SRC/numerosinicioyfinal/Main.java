/*
 * Realizar el programa que asigen aleatoriamente n numeros enteros se pide
 * calcular e imprimir cuantos numeros empiesan y terminan en la misma cifra.
 */

package numerosinicioyfinal;
import javax.swing.JOptionPane;
public class Main {
        public static void main(String[] args) {
            int n,i,d,d1=0;

            n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese las veces a probar"));
            int p=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el rango a probar"));
            if (n==0)
                n=1;
            i=1;
            while (i<=n)
            {
                d=(int)(Math.random()*p);
                System.out.println(d);
                int r=1,k=0;
                k=d;
                d1=k%10;
                while (d!=0)
                {
                    r=d%10;
                    d=d/10;
                 }
                System.out.println(r+"y"+d1);
                if (r==k)
                    System.out.println("Si cumple la condicion");
                else
                    System.out.println("No cumple la condicion");

                i++;
            }

   }
}