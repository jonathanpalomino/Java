package listasenlazadas;
public class listasenlazadas2 {
   static class Nodo
   {
      int codigo;
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

     for(i=1;i<=10;i++)
     {
        nuevo = new Nodo ();
        nuevo.codigo = (int)(Math.random()*899+100);
        nuevo.ep=(int)(Math.random()*20);
        nuevo.ef=(int)(Math.random()*20);
        nuevo.pp=(int)(Math.random()*20);
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
      }

      salto (comienzo);
   }


   static void salto (Nodo arribaNodo)
   {

      System.out.println("======================================================");
      System.out.println(" CODIGO    EX.PAR.  EX.FIN.   PR.PRAC.  PR.FIN");
      System.out.println("======================================================");
      while (arribaNodo != null)
      {
         System.out.println ("  "+arribaNodo.codigo + "       "+arribaNodo.ep+"         "+arribaNodo.ef+"          "+arribaNodo.pp+"       "+arribaNodo.pf);
         arribaNodo = arribaNodo.siguiente;
      }
      System.out.println ("=====================================================");
   }
}
