package objetos;
public class main4 {
public static void main(String[] args) {
        long i,n;
         n=(int) (Math.random() * 100);
         
         for(i=1;i<=n;i++)
             {
             
             Secundaria4 obj=new Secundaria4();
             
             System.out.println("El factorial de  "+obj.y+"   es :"+obj.factorial());
            
             System.out.println("la suma de cifras de "+obj.y+"  es: "+obj.sumatoria());
         }
         
    }

}
