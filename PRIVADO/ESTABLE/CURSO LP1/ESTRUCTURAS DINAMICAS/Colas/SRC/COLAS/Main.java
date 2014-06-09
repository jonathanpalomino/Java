/*
 * Realizar el programa que ingrese a una estructura cola los datos de n alumnos
 * de una clse : codigo, examen parcial, examen final y promedio de practicas.
 * Se pide calcular e imprimir el promedio final por medio de un reporte de salidas
 * 
 */

package colas;
class Main {
static class Cola 
    {
    int codigo,ep,ef,pp;
    double pf;
    Cola siguiente;
    }
    public static void main(String[] args) 
    {
    Cola p =null;
    Cola nuevo = null;
    Cola q =null;
    int i;
     System.out.println("Datos el el orden que han sido generados");
        System.out.println("========================================");
        for (i=1;i<=10;i++)
        {
        nuevo = new Cola ();
        nuevo.codigo= (int)(Math.random()*899+100);
        nuevo.ep=(int)(Math.random()*20);
        nuevo.ef =(int)(Math.random()*20);
        nuevo.pp =(int)(Math.random()*20);
        nuevo.pf = (nuevo.ep+nuevo.ef+nuevo.pp)/3.0;
        System.out.println("        "+nuevo.codigo+ "       "+nuevo.ep+"        "+nuevo.ef+"        "+nuevo.pp);
        if (p==null)
            {
             p=nuevo;
            }
        else
            {
            q = p;
            while(q.siguiente!=null)
                {
                q=q.siguiente;
                }
            q.siguiente =nuevo;
            }
        nuevo.siguiente=null;
        }
        salto (p);
        
                        
         }
         static void salto (Cola abajoNodo)
     {
        System.out.println();
        System.out.println("Datos impresos segun la estructura");
        System.out.println("==================================");
        System.out.println("CODIGO      EX.PARC     EXA.FINAL       PROM.PRACT      PROM TOTAL");
        System.out.println("==================================================================");
        
        while(abajoNodo !=null)
         {
        System.out.println("        "+abajoNodo.codigo + "      "+abajoNodo.ep+ "       "+abajoNodo.ef+ "       "+abajoNodo.pp+"        "+abajoNodo.pf);
        abajoNodo = abajoNodo.siguiente;
        
         }  
        System.out.println("==================================================================");
    }   
    }


