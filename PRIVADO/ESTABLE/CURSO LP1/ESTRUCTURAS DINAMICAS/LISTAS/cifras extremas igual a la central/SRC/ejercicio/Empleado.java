/*
 * Realizar el programa que ingrese a una lista enlazada los datos de n trabajadores
 * de una nempresa codigo del trabajador,sueldo basico bonificacion descuentos 
 * y categoria del trabajador (auxiliar,tecnico, profesional ,funcionanrio o directivo)
 * se pide calcular e imprimir un reporte de salida como el siguiente:
 *                  REPORTE DE TRABAJADORES
 * ............................................................................
 * CODIGO   BASICO  BONIFICACION    DESCUENTO   SUELDO  CATEGORIA.
 * 
 * ............................................................................
 *          XXXXX   XXXXXXXXXXX     XXXXXXXXXX  XXXXXX
 * 
 * 
 * TOTAL SUELDO NETO 
 * TOTAL SUELDO NETO 
 * TOTAL SUELDO NETO 
 * TOTAL SUELDO NETO 
 * TOTAL SUELDO NETO 
 * 
 * 
 */

package ejercicio;
import javax.swing.JOptionPane; 
class Main 
{
   static class Nodo  
   {
      String cod;
      String cat;
      double bas;
      double bon;
      double des;
      double sn;
      Nodo siguiente;
   }

   public static void main (String [] args) 
   {
      Nodo comienzo = null;
      Nodo nuevo=null;
      Nodo q=null;
      String bon1,des1,bas1;     
      String Cod1 = JOptionPane.showInputDialog( "Ingrese Codigo: " );   
     do
     { 
           nuevo = new Nodo ();
           nuevo.cod= Cod1;
           bas1 = JOptionPane.showInputDialog( "Ingrese el Sueldo Basico: " );
           nuevo.bas = Double.parseDouble(bas1); 
           bon1 = JOptionPane.showInputDialog( "Ingrese la bonificacion: " );
           nuevo.bon = Double.parseDouble(bon1); 
           des1 = JOptionPane.showInputDialog( "Ingrese el descuento " );
           nuevo.des = Double.parseDouble( des1 ); 
           nuevo.cat=JOptionPane.showInputDialog("Ingrese la categoria: ");
           nuevo.sn=nuevo.bas+nuevo.bon-nuevo.des;
          
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
            
            Cod1 = JOptionPane.showInputDialog( "Ingrese Codigo: " );    
         
      }
      while (Cod1.compareTo("*")!=0);   
      salto (comienzo);
      System.exit( 0 );
  }


   static void salto (Nodo arribaNodo) 
   {
      double tbas=0, tbon=0, tdes=0, tsn=0;
      double tsnca=0, tsnct=0,tsncp=0, tsncf=0, tsncd=0;
      System.out.println();
      System.out.println("                       REPORTE DE TRABAJADORES");
      System.out.println("=================================================================================");

      System.out.println(" CODIGO\tBASICO\tBONIFIC.\tDESCTO.\tS. NETO\tCATEGORIA");
      System.out.println("=================================================================================");

      while (arribaNodo != null) 
      {
         System.out.println ("\t"+arribaNodo.cod + "\t"+arribaNodo.bas+"\t"+arribaNodo.bon+"\t"+arribaNodo.des+"\t"+arribaNodo.sn+"\t"+arribaNodo.cat);
          tbas=tbas+arribaNodo.bas;
          tbon=tbon+arribaNodo.bon;
          tdes=tdes+arribaNodo.des;
          tsn=tsn+arribaNodo.sn;
          if(arribaNodo.cat.compareTo("auxiliar")==0)
           {
               tsnca+=arribaNodo.sn;
           }  
           else
              if(arribaNodo.cat.compareTo("tecnico")==0)
              {   
                  tsnct+=arribaNodo.sn;
              }
              else
                 if(arribaNodo.cat.compareTo("profesional")==0)
                 {
                     tsncp+=arribaNodo.sn;
                 }
                 else
                     if(arribaNodo.cat.compareTo("funcionario")==0)
                     {
                         tsncf+=arribaNodo.sn;
                     }
                     else
                     {
                         tsncd+=arribaNodo.sn;
                     }
         arribaNodo = arribaNodo.siguiente;
      }
      System.out.println ("===================================================================================");
      System.out.println("\t"+tbas+"\t"+tbon+"\t"+tdes+"\t"+tsn);
      System.out.println ("===================================================================================");
      System.out.println("                                     Total sueldo neto categoria auxiliar "+tsnca);   
      System.out.println("                                     Total sueldo neto categoria tecnico "+tsnct);
      System.out.println("                                     Total sueldo neto categoria profesional "+tsncp);
      System.out.println("                                     Total sueldo neto categoria funcionario "+tsncf);
      System.out.println("                                     Total sueldo neto categoria directivo "+tsncd);
    }  
}
 
