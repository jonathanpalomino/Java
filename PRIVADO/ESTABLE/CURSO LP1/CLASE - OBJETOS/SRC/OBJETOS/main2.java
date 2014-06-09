package objetos;
public class main2 {
public static void main(String[] args) {
        long i,n,num;
         n=(int) (Math.random() * 100);
         
         for(i=1;i<=n;i++)
             {
             num =(int) (Math.random() * 20);
             Secundaria2 obj=new Secundaria2(num);
             obj.factorial();
             obj.sumatoria();
         }
         
    }

}
