/*
 * Dado 2 numeros enteros calcular su maximo comun divisor
 * y su minimo comun divisor.
 */

package problemasunfv;

/**
 *
 * @author JONATHAN
 */
public class mcmymcd {

    public static void main(String[] args) {
        int a,b,t,t1,x;
        a=10;
        b=20;
        t=1;
        x=2;

        while (x<=a && x<=b)
        {
            while (a%x==0 && b%x==0)
            {
                a=a/x;
                b=b/x;
                t=t*x;
            }
            x++;
        }
        t1=t;
        t=t*a*b;
        System.out.println("El mcm es :" +t);
        System.out.println("El mcd es :" +t1);
    }

}
