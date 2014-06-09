/*
 * Realizar la pila de articulos de un almacen teniendo en cuenta: codigo,precio unitario,cantidad,tipo de articulo(A,B,C) imprimir el
 * importe de cada articulo asi mismo como el total de importe de tipo de articulo.
 */

package piladearticulos;
import javax.swing.JOptionPane;
class Main {
static class Pila
{
int cant;
double pu,pro;
String tipo,cod;
Pila anterior;
}
    public static void main(String[] args) {
        Pila ultimo=null;
        Pila nuevo=null;
        String cod=JOptionPane.showInputDialog( "Ingrese Codigo: " );
    do
    {
    nuevo=new Pila();
    nuevo.cod = cod;
    nuevo.pu = Double.parseDouble(JOptionPane.showInputDialog( "Ingrese precio unitario: " ));
    nuevo.cant=Integer.parseInt(JOptionPane.showInputDialog("ingrese la cantidad"));
    nuevo.tipo=JOptionPane.showInputDialog("Ingrese el tipo A,B ó C");
    nuevo.pro=nuevo.pu*nuevo.cant;
    nuevo.anterior = ultimo;
    ultimo = nuevo;
    cod=JOptionPane.showInputDialog( "Ingrese Codigo: " );
    }
    while(cod.compareTo("*")!=0);
    salto (ultimo);
    System.exit( 0 );
    }
    static void salto(Pila nodo)
    {
        double sumA=0,sumB=0,sumC=0,max=0;
        String tipo="";
        System.out.println("Lista de articulos ingresados");
        System.out.println("CODIGO  PREC.UNIT   CANTIDAD    IMPORTE    TIPO");
        while (nodo!=null)
        {
            if(nodo.tipo.compareToIgnoreCase("a")==0)
            {
                sumA+=nodo.pro;
            }
            else if(nodo.tipo.compareToIgnoreCase("b")==0)
            {
                sumB+=nodo.pro;
            }
            else if(nodo.tipo.compareToIgnoreCase("c")==0)
            {
                sumC+=nodo.pro;
            }

        System.out.println(nodo.cod+"\t"+nodo.pu+"\t"+nodo.cant+"\t"+nodo.pro+"\t"+nodo.tipo);
        
        nodo=nodo.anterior;
        }
        System.out.println("El importe total es: "+sumA+sumB+sumC);
        System.out.println("El importe total de A es: "+sumA);
        System.out.println("El importe total de B es: "+sumB);
        System.out.println("El importe total de C es: "+sumC);
        if(sumA>sumB && sumA>sumC)
        {
            max=sumA;
            tipo="A";
        }
        else if(sumB>sumA && sumB>sumC)
        {
            max=sumB;
            tipo="B";
        }
        else if(sumC>sumA && sumC>sumB)
        {
            max=sumC;
            tipo="C";
        }
        System.out.println("El importe máximo de tipo"+tipo+" es: "+max);
    }

}
