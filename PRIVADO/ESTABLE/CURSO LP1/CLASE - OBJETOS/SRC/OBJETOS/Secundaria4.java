package objetos;
public class Secundaria4 {
    long y;

       Secundaria4(){
        y=(int) (Math.random() * 20);
        
        }

       long factorial()

     {
     long fact=1;
     for(int j=1;j<=y;j++)
      {
      fact=fact*j;
      }

     return fact;
     }


 long sumatoria()
     {
      long sumcif=0,res,x;
      x=y;
     while(y!=0)
      {
      res=y%10;
      sumcif=sumcif+res;
      y=y/10;
      }
     return sumcif;
              
     }
}