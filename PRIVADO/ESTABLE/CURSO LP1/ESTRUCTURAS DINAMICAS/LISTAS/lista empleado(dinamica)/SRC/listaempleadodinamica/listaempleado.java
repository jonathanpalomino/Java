/*Crear un programa por el cual se ingrese datos a una lista teniendo codigo,nombre,apellido,categoria y sueldo,bonificacion,descuento,neto total;
 *se pide imprimir de forma ordenada todos los datos segun categoria.
 */
package listaempleadodinamica;
import java.util.Vector;
import javax.swing.JOptionPane;
public class listaempleado {
static class Nodo
   {
      String codigo,nombre,apellido,categoria;
      double sueldo,bonificacion,descuento,total;
      Nodo siguiente;
   }
    public static void main(String[] args)
            {
      Nodo comienzo = null;
      Nodo nuevo=null;
      Nodo q=null;
      String nom,ap,cat,suel,bo,des;
      int n = Integer.parseInt(JOptionPane.showInputDialog( "Ingrese la cantidad a repetir" ));
      String Cod;
     for(int i=0;i<n;i++)
     {
           nuevo = new Nodo ();
           Cod = JOptionPane.showInputDialog( "Ingrese Codigo: " );
           nuevo.codigo = Cod;
           nom = JOptionPane.showInputDialog( "Ingrese el nombre: " );
           nuevo.nombre =  nom ;
           ap = JOptionPane.showInputDialog( "Ingrese el apellido: " );
           nuevo.apellido = ap;
           cat = JOptionPane.showInputDialog( "Ingrese la categoria: " );
           nuevo.categoria = cat;
           suel = JOptionPane.showInputDialog( "Ingrese el sueldo: " );
           nuevo.sueldo = Double.parseDouble( suel );
           des = JOptionPane.showInputDialog( "Ingrese el descuento: " );
           nuevo.descuento = Double.parseDouble( des );
           bo = JOptionPane.showInputDialog( "Ingrese el bonificacion: " );
           nuevo.bonificacion = Double.parseDouble( bo );
           nuevo.total=nuevo.sueldo+nuevo.bonificacion-nuevo.descuento;
           if(comienzo==null)
            {
      	      comienzo=nuevo;
            }
            else
            {
      	      q= comienzo;
      	      while(q.siguiente!=null)
      	       {
      		     q=q.siguiente;
               }
              q.siguiente=nuevo;
            }
            nuevo.siguiente=null;
      }
      salto (comienzo);
  }
 static void salto (Nodo arribaNodo)
   {
      System.out.println("===============================================================================");
      System.out.println("CODIGO    Nombre   Apellido   Categoria   Sueldo   Descuento   Bonificacion   Total");
      System.out.println("===============================================================================");
Vector codA = new Vector(100,1);//Inicializando vectores con capacidad de 100 e incremento de 1
Vector codB = new Vector(100,1);
Vector codC = new Vector(100,1);
int a=0,b=0,c=0;
      while (arribaNodo != null)
      {
         if(arribaNodo.categoria.compareToIgnoreCase("a")==0)
         {
             codA.add(a,arribaNodo.codigo + "\t"+arribaNodo.nombre+"\t"+arribaNodo.apellido+"\t"+arribaNodo.categoria+"\t"+arribaNodo.sueldo+ "\t"+arribaNodo.descuento+ "\t"+arribaNodo.bonificacion+ "\t"+arribaNodo.total);
             System.out.println(codA.elementAt(a).toString());
             a++;
         }
         else if (arribaNodo.categoria.compareToIgnoreCase("b")==0)
         {
             codB.add(b,arribaNodo.codigo + "\t"+arribaNodo.nombre+"\t"+arribaNodo.apellido+"\t"+arribaNodo.categoria+"\t"+arribaNodo.sueldo+ "\t"+arribaNodo.descuento+ "\t"+arribaNodo.bonificacion+ "\t"+arribaNodo.total);
             System.out.println(codB.elementAt(b).toString());
             b++;
         }
         else if (arribaNodo.categoria.compareToIgnoreCase("c")==0)
         {
             codC.add(c,arribaNodo.codigo + "\t"+arribaNodo.nombre+"\t"+arribaNodo.apellido+"\t"+arribaNodo.categoria+"\t"+arribaNodo.sueldo+ "\t"+arribaNodo.descuento+ "\t"+arribaNodo.bonificacion+ "\t"+arribaNodo.total);
             System.out.println(codC.elementAt(c).toString());
             c++;
         }   
         arribaNodo = arribaNodo.siguiente;
      }
      System.out.println ("===============================================================================");
    }
}