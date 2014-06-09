/*
 * Realizar el programa que ingrese a una estructura pila los datos de n alumnos
 * de una clse : codigo, examen parcial, examen final y promedio de practicas.
 * Se pide calcular e imprimir el promedio final por medio de un reporte de salidas
 *
 */

package pilas;
import javax.swing.JOptionPane;
class Main {
static class Pila{

       int codigo,ep,ef,pp;
       double pf;
       Pila anterior;
    }
    public static void main(String[] args) {
        Pila ultimo =null;
        Pila nuevo = null;
        Pila q=null;
        int i;
        System.out.println("Datos el el orden que han sido generados");
        System.out.println("========================================");
        for (i=1;i<=10;i++)
        {
        nuevo = new Pila ();
        nuevo.codigo= (int)(Math.random()*899+100);
        nuevo.ep = Integer.parseInt(JOptionPane.showInputDialog("ingrese el examen parcial"));
        nuevo.ef = Integer.parseInt(JOptionPane.showInputDialog("ingrese el examen final"));
        nuevo.pp = Integer.parseInt(JOptionPane.showInputDialog("ingrese promedio de practicas"));
        nuevo.pf = (nuevo.ep+nuevo.ef+nuevo.pp)/3.0;
        System.out.println("        "+nuevo.codigo+ "       "+nuevo.ep+"        "+nuevo.ef+"        "+nuevo.pp);
        nuevo.anterior=ultimo;
        ultimo=nuevo;
         }

        Salto(ultimo);
    }

    static void Salto (Pila abajoNodo)
     {
        System.out.println();
        System.out.println("Datos impresos segun la estructura");
        System.out.println("==================================");
        System.out.println("CODIGO      EX.PARC     EXA.FINAL       PROM.PRACT      PROM TOTAL");
        System.out.println("==================================================================");

        while(abajoNodo !=null)
         {
        System.out.println("        "+abajoNodo.codigo + "      "+abajoNodo.ep+ "       "+abajoNodo.ef+ "       "+abajoNodo.pp+"        "+abajoNodo.pf);
        abajoNodo = abajoNodo.anterior;

         }
        System.out.println("==================================================================");
    }
}

