/*
 *Realizar el programa que asigne aleatoriamente los espacios y tiempos uniformes
 * por tres vehiculos se pide calculara imprimir el vehiculo tuvo mayor
 * Velocidad y el que tuvo menor velocidad.
 * Espacio desde 10 km a 50 km tiempo de 15 a 30 minutos
 */

package velocidades;

/**
 *
 * @author JONATHAN PALOMINO VILCA
 * LINUX OPEN SOURCE
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double e1,e2,e3,t1,t2,t3,v1,v2,v3;
        e1=Math.random()*50+1;
        e2=Math.random()*50+1;
        e3=Math.random()*50+1;
        t1=Math.random()*16+15;
        t2=Math.random()*16+15;
        t3=Math.random()*16+15;
        v1=e1/t1*60;
        v2=e2/t2*60;
        v3=e3/t3*60;
        { if (v1>v2 && v1>v3)
        {
            System.out.println("El vehiculo 1 es más veloz con :"+v1+" km/h");
        }
            else
                if (v2>v1 && v2>v3){
            System.out.println("El vehiculo 2 es más veloz con :"+v2+" km/h");
                                    }
                else
                    System.out.println("El vehiculo 3 es más veloz con :"+v3+" km/h");
        }
if (v2<v1 && v2<v3)
    System.out.println("Y el vehiculo 2 es el mas lento con :"+v2+" Km/h");

else
    if (v1<v2 && v1<v3)
        System.out.println("Y el vehiculo 1 es el mas lento con :"+v1+" Km/h");
    else
        System.out.println("Y el vehiculo 3 es el mas lento con :"+v3+" Km/h");

}
}