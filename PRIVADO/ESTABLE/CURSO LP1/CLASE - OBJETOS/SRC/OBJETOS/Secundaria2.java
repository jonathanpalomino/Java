package objetos;
public class Secundaria2 {
    long y;
    Secundaria2(long z){
        y=z;
    }
 void factorial()
         {
     
     long fact=1;
     for(int j=1;j<=y;j++)
      {
      fact=fact*j;
  }
  System.out.println("El factorial de  "+y+"   es :"+fact);
 }
 void sumatoria()
         {
          long sumcif=0,res,x;
          x=y;
         while(y!=0)
       {  
          res=y%10;
          sumcif=sumcif+res;
          y=y/10;
      } 
              System.out.println("la suma de cifras de "+x+"  es: "+sumcif);
              System.out.println("");
}
}