package listasenlazadas;
import javax.swing.JOptionPane; 
public class Listaenlazada3 {
    static class Nodo  
   {
      String codigo;
      int ep;
      int ef;
      int pp;
      double pf;
      Nodo siguiente;
   }
   public static void main (String [] args) 
   {
      Nodo comienzo = null;
      Nodo nuevo=null;
      Nodo q=null;
      int i;
      String ep1,ef1,pp1;     
      String Cod = JOptionPane.showInputDialog( "Ingrese Codigo: " );   
     do
     { 
           nuevo = new Nodo ();
           nuevo.codigo = Cod;
           ep1 = JOptionPane.showInputDialog( "Ingrese el Examen Parcial: " );
           nuevo.ep = Integer.parseInt( ep1 ); 
           ef1 = JOptionPane.showInputDialog( "Ingrese el Examen Final: " );
           nuevo.ef = Integer.parseInt( ef1 ); 
           pp1 = JOptionPane.showInputDialog( "Ingrese el Promedio de Practicas: " );
           nuevo.pp = Integer.parseInt( pp1 ); 
           nuevo.pf=(nuevo.ep+nuevo.ef+nuevo.pp)/3;
           
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
            
            Cod = JOptionPane.showInputDialog( "Ingrese Codigo: " );    
         
      }
      while (Cod.compareTo("*")!=0);   
      salto (comienzo);
      System.exit( 0 );
  }


   static void salto (Nodo arribaNodo) 
   {
      System.out.println();     
      System.out.println("======================================================");
      System.out.println(" CODIGO    EX.PAR.  EX.FIN.   PR.PRAC.  PR.FIN");
      System.out.println("======================================================");
      while (arribaNodo != null) 
      {
         JOptionPane.showMessageDialog( null, "Codigo" + arribaNodo.codigo,
         "Resultados", JOptionPane.PLAIN_MESSAGE );
         
         System.out.println ("  "+arribaNodo.codigo + "       "+arribaNodo.ep+"         "+arribaNodo.ef+"          "+arribaNodo.pp+"       "+arribaNodo.pf);
         arribaNodo = arribaNodo.siguiente;
      }
      System.out.println ("=====================================================");
    }  

}
