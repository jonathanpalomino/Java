/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package funcionestrigonometricasver2;

/**
 *
 * @author JONATHAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double sen,cos,tan,cotg,sec,csc,d;
        int i=0;
       
        System.out.println("***********************************************************************************************************************************************************************************");
        System.out.println("*\t\t\t\t\t\t\t\tRazones trigonometricas\t\t\t\t\t\t\t\t\t\t\t  *");
        System.out.println("***********************************************************************************************************************************************************************************");
        System.out.println("Angulo  \tSeno\t\t\tCoseno\t\t\ttangente\t\t\tcotangente\t  \tsecante\t\t  cosecante");
        System.out.println("***********************************************************************************************************************************************************************************");
        System.out.println(0+"       \t"+Math.sin(0)+" \t\t\t"+Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        while (i<30) {
            i=i+15;
           
                        d=i*3.1415926535897932384626433832795/180;
                        sen=(double)(Math.sin(d));
                        cos=(double)(Math.cos(d));
                        tan=(double)(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;
                  
                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
                        }
        for(i=37;i<=53;i=i+8){
           
            d=i*3.1415926535897932384626433832795/180;
                        sen=(double)(Math.sin(d));
                        cos=(double)(Math.cos(d));
                        tan=(double)(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
        }

        for(i=60;i<90;i=i+15){
            d=i*3.1415926535897932384626433832795/180;
                        sen=(double)(Math.sin(d));
                        cos=(double)(Math.cos(d));
                        tan=(double)(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
        }
        
        System.out.println(90+"      \t"+(Math.sin(0)+1)+"\t\t\t    "+(Math.cos(0)-1)+"\t\t\tinfinito"+  "\t\t\t"+0.0+"\t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        System.out.println(180+"       \t"+Math.sin(0)+" \t\t\t"+-Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(-1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        for(i=210;i<270;i=i+30){
             d=i*3.1415926535897932384626433832795/180;
                        sen=(double)(Math.sin(d));
                        cos=(double)(Math.cos(d));
                        tan=(double)(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
         System.out.println(270+"      \t"+-(Math.sin(0)+1)+"         \t\t"+(Math.cos(0)-1)+"\t\t\tinfinito"+  "\t\t\t"+0.0+"\t\t\t"+-(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        System.out.println(360+"       \t"+Math.sin(0)+" \t\t\t"+Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        }
            }
}
    


