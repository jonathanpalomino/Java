/*
 * Realizar el programa que asigne aleatoriamente tres de vendedores;
 * se pide calcular e imprimir la Comisi?n que de recibir cada vendedor,
 * si se sabe que el monto de las ventas es menor de S/. 100 no hay Comisión
 * Si las ventas no superan los S 500 la comisión es del 8.5% De las ventas,
 * si las ventas son mayores o iguales a S 500 hasta S 1000 la Comisi?n,
 * es del 10% de las ventas más S 50, si el monto de las ventas el mayor a 1000 como
 * hasta 2000 la Comisión es S 100 más del 10% del monto de las ventas superiores a S 1000,
 * si el monto de las ventas es mayor a 2000 la Comisi¾n es el 20% del monto
 *Esde las ventas que superen los S 1000
 * and open the template in the editor.
 */

package consumo;
import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        double v1 = 0,v2 = 0,v3 = 0,m1,m2,m3;
        m1=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del 1°vendedor "));//(Math.random()*4001);
        if (m1>4000)
            m1=4000;
        m2=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del 2°vendedor "));
        if (m2>4000)
            m2=4000;
        m3=Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto del 3°vendedor "));
        if (m3>4000)
            m3=4000;

      if (m1<100)
            v1=0;
        else
            if (m1>=100 && m1<500)
                v1=m1*0.085;//Monto mayor a 100 y menor a 500
            else
                if (m1>=500 && m1<=1000)
                    v1=m1*(0.1)+50;//Monto mayor a 500 y menor a 1000
                else
                    if (m1>1000 && m1<=2000)
                        v1=(m1-1000)*0.2 +100;//Monto mayor a 1000 y menor igual a 2000
                    else
                        if (m1>2000)//Monto mayor a 2000
                            v1=(m1-1000)*0.2;


if (m2<100)
{ v2=0;}
        else
            if (m2>=100 && m2<500)
            { v2=m2*0.085;}
            else
                if (m2>=500 && m2<=1000)
                {v2=m2*(0.1)+50;}
                else
                    if (m2>1000 && m2<=2000)
                    { v2=(m2-1000)*0.2 +100;}
                    else
                        if (m2>2000)
                        { v2=(m2-1000)*0.2;}

       if (m3<100)
            v3=0;
        else
            if (m3>=100 && m3<500)
                v3=m3*0.085;
            else
                if (m3>=500 && m3<=1000)
                    v3=m3*(0.1)+50;
                else
                    if (m3>1000 && m3<=2000)
                        v3=(m2-1000)*0.2 +100;
                    else
                        if (m3>2000)
                            v3=(m3-1000)*0.2;


System.out.println("El vendedor 1 recibira :" +v1);
System.out.println("El vendedor 2 recibira :" +v2);
System.out.println("El vendedor 3 recibira :" +v3);
  }
 }
