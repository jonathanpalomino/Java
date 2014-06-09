/*
 * Realizar el programa que ingrese a una lista enlazada n numeros enteros
 * se pide calcular e imprimir cuantos numeros de la lista sus cifras extremas
 * son identicas a la cifra central 
 */

package ejercicio;
import javax.swing.JOptionPane;
public class ejercicio2 {
static class Nodo
   {
      int numero;
      Nodo siguiente;

   }
    public static void main(String[] args)
            {
      Nodo comienzo = null;
      Nodo nuevo=null;
      Nodo q=null;
      int n,i;
      
      n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de veces arepetir"));
     for(i=0;i<n;i++)
     {
           nuevo = new Nodo ();
           nuevo.numero=Integer.parseInt(JOptionPane.showInputDialog("ingrese las cifras a procesar"));//ingresandoi datos
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
    //revisar y ordenar por categoria
 static void salto (Nodo arribaNodo)

      {

      System.out.println();
      System.out.println("======================");
      System.out.println(" NÙMERO       CUMPLE");
      System.out.println("======================");

int g=0,g1=0,g2=0,n=0,cont=0,contcifmedio=0,res = 0,inic=0;
boolean cumple=false;
      while (arribaNodo != null)
      {
         g=arribaNodo.numero;
         g1=arribaNodo.numero;
         g2=arribaNodo.numero;
         n=g%10;//n cifra final del numero
         //iniciando conteo de cifras
           while (g!=0)
           {
               g = g/10;
               cont++;
           }//Fin del conteo de cifras
         if(cont%2!=0)
         {
           contcifmedio=((cont / 2)+1);//obtiene la posicion de la cifra del medio
         //buscar cifra medio
         for(int i=0;i<contcifmedio-1;i++)
         {
            g1=g1/10;
            res=g1%10;//res es la cifra del medio
         }
         for(int i=0;i<cont-1;i++)
         {
            g2=g2/10;
            inic=g2%10;//inic es la cifra inicial
         }

         if(n==res && res==inic)
         {
             cumple =true;
         }
           cont=0;//Resetea el contador
         }
         else
          {
             System.out.println (arribaNodo.numero+" tiene cantidad par de cifras no puedo procesar");

          }

         System.out.println ("  "+arribaNodo.numero+"\t"+cumple);
         arribaNodo = arribaNodo.siguiente;
         cumple = false;//Resetea la proposicion logica
    }
    System.out.println ("======================");
}
}