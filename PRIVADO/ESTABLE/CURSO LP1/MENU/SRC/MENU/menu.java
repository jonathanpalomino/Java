/*
 * Realizar un programa que a travez de un menu de opciones enlace todos los
 * programas vistos hasta el momento
 */

package menu;

/**
 *
 * @author JONATHAN PALOMINO VILCA
 * LINUX-OPEN SOURCE
 */

import java.io.*;
public class menu {

    @SuppressWarnings("empty-statement")
public static void main(String args[]) throws IOException{
int op=0;
String linea;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//ingreso de texto por teclado en el mismo bufer de salida
for(;;){
System.out.println("Ingrese una opcion");
System.out.println("1.- Sistema de notas");
System.out.println("2.- Sistema de sueldos");
System.out.println("3.- Sistema de pagos");
System.out.println("4.- Funciones Trigonometricas");
System.out.println("5.- Sistema de consumo");
System.out.println("6.- Funciones Trigonometricas ver.2");
System.out.println("7.- Salir del Programa");
System.out.println("\n\n Opcion: ");
linea = br.readLine();
op = Integer.valueOf(linea).intValue();
switch (op)
{
case 1: {
    int ep,ef,pp;
        double pf;
        ep=(int)(Math.random()*21);
        ef=(int)(Math.random()*21);
        pp=(int)(Math.random()*21);
        pf=(ep+ef+pp)/3.0;
        System.out.println("El promedio es: "+pf);
};
break;
case 2: {
    double sb,bo,de,sn;
        sb=(int)(Math.random()*701+800);
        bo=(int)(Math.random()*401+200);
        de=(int)(Math.random()*501);
        sn=sb+bo-de;
        System.out.println("El sueldo a recibir es :"+sn);
};
break;
case 3: {
    int p1,p2,p3,c1,c2,c3,importe,vuelto,pago;
        p1=(int)(Math.random()*11+10);
        p2=(int)(Math.random()*11+50);
        p3=(int)(Math.random()*21+25);
        c1=(int)(Math.random()*16+5);
        c2=(int)(Math.random()*16+5);
        c3=(int)(Math.random()*16+5);
        importe=p1*c1+p2*c2+p3*c3;
        pago=(int)(Math.random()*50+importe);
        vuelto=pago-importe;
        System.out.println("El importe a pagar es :"+importe);
        System.out.println("El pago realizado es :"+pago);
        System.out.println("Su vuelto es :"+vuelto);
};
break;
case 4: {
    double fpi;
        fpi=3.1415926535897932384626433832795/180;
        System.out.println("***********************************************************************************************************************************************************************************");
        System.out.println("*\t\t\t\t\t\t\t\tRazones trigonometricas\t\t\t\t\t\t\t\t\t\t\t  *");
        System.out.println("***********************************************************************************************************************************************************************************");
        System.out.println("Angulo  \tSeno\t\t\tCoseno\t\t\ttangente\t\t\tcotangente\t  \tsecante\t\t  cosecante");
        System.out.println(0+"       \t"+Math.sin(0)+" \t\t\t"+Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        System.out.println(15+"      \t"+Math.sin(15*fpi)+" \t"+Math.cos(15*fpi)+"   \t"+Math.tan(15*fpi)+"    \t"+(1/Math.tan(15*fpi))+"  \t"+(1/Math.cos(15*fpi))+" \t"+(1/Math.sin(15*fpi)));
        System.out.println(30+"      \t"+Math.sin(30*fpi)+" \t"+Math.cos(30*fpi)+"   \t"+Math.tan(30*fpi)+"    \t"+(1/Math.tan(30*fpi))+"  \t"+(1/Math.cos(30*fpi))+"\t"+(1/Math.sin(30*fpi)));
        System.out.println(37+"      \t"+Math.sin(37*fpi)+"  \t"+Math.cos(37*fpi)+"   \t"+Math.tan(37*fpi)+"    \t"+(1/Math.tan(37*fpi))+"    \t"+(1/Math.cos(37*fpi))+"\t"+(1/Math.sin(37*fpi)));
        System.out.println(45+"      \t"+Math.sin(45*fpi)+"  \t"+Math.cos(45*fpi)+"   \t"+Math.tan(45*fpi)+"    \t"+(1/Math.tan(45*fpi))+"  \t"+(1/Math.cos(45*fpi))+" \t"+(1/Math.sin(45*fpi)));
        System.out.println(53+"      \t"+Math.sin(53*fpi)+"  \t"+Math.cos(53*fpi)+"   \t"+Math.tan(53*fpi)+"    \t"+(1/Math.tan(53*fpi))+"  \t"+(1/Math.cos(53*fpi))+"\t"+(1/Math.sin(53*fpi)));
        System.out.println(60+"      \t"+Math.sin(60*fpi)+"  \t"+Math.cos(60*fpi)+"   \t"+Math.tan(60*fpi)+"    \t"+(1/Math.tan(60*fpi))+"   \t"+(1/Math.cos(60*fpi))+"\t"+(1/Math.sin(60*fpi)));
        System.out.println(75+"      \t"+Math.sin(75*fpi)+"  \t"+Math.cos(75*fpi)+"  \t"+Math.tan(75*fpi)+"    \t"+(1/Math.tan(75*fpi))+"  \t"+(1/Math.cos(75*fpi))+"\t"+(1/Math.sin(75*fpi)));
        System.out.println(90+"      \t"+Math.sin(90*fpi)+"         \t\t"+Math.cos(90*fpi)+"\t"+Math.tan(90*fpi)+"  \t"+(1/Math.tan(90*fpi))+"       "+(1/Math.cos(90*fpi))+"      "+(1/Math.sin(90*fpi)));
        System.out.println(120+"     \t"+Math.sin(120*fpi)+" \t"+Math.cos(120*fpi)+"   \t"+Math.tan(120*fpi)+"  \t"+(1/Math.tan(120*fpi))+"  \t"+(1/Math.cos(120*fpi))+"\t"+(1/Math.sin(120*fpi)));
        System.out.println(135+"     \t"+Math.sin(135*fpi)+" \t"+Math.cos(135*fpi)+"   \t"+Math.tan(135*fpi)+"  \t"+(1/Math.tan(135*fpi))+"  \t"+(1/Math.cos(135*fpi))+"       "+(1/Math.sin(135*fpi)));
        System.out.println(180+"     \t"+Math.sin(180*fpi)+"     "+Math.cos(180*fpi)+"  \t\t\t"+Math.tan(180*fpi)+"      "+(1/Math.tan(180*fpi))+"\t"+(1/Math.cos(180*fpi))+"      \t\t"+(1/Math.sin(180*fpi)));
        System.out.println(270+"    \t"+Math.sin(270*fpi)+"\t\t\t"+Math.cos(270*fpi)+"\t"+Math.tan(270*fpi)+" \t"+(1/Math.tan(270*fpi))+"\t"+(1/Math.cos(270*fpi))+"     "+(1/Math.sin(270*fpi)));
        System.out.println(360+"    \t"+Math.sin(360*fpi)+"      "+Math.cos(360*fpi)+"  \t\t\t"+Math.tan(360*fpi)+"      "+(1/Math.tan(360*fpi))+"\t"+(1/Math.cos(360*fpi))+"      \t\t"+(1/Math.sin(360*fpi)));
        System.out.println("***********************************************************************************************************************************************************************************");

};
break;
case 5: {
    double v1 = 0,v2 = 0,v3 = 0,m1,m2,m3;
        m1=(Math.random()*4001);
        if (m1>4000)
            m1=4000;
        m2=(Math.random()*4001);
        if (m2>4000)
            m2=4000;
        m3=(Math.random()*4001);
        if (m3>4000)
            m3=4000;

      if (m1<100)
            v1=0;
        else
            if (m1>=100 && m1<500)
                v1=m1*0.085;
            else
                if (m1>=500 && m1<=1000)
                    v1=m1*(0.1)+50;
                else
                    if (m1>1000 && m1<=2000)
                        v1=(m1-1000)*0.2 +100;
                    else
                        if (m1>2000)
                            v1=(m1-1000)*0.2;


if (m2<100)
{ v2=0;}
        else
            if (m2>=100 && m2<500)
            { v2=m2*0.085;}
            else
                if (m2>=500 && m2<=1000)
                {v2=m2*(0.1)+50;}
                else
                    if (m2>1000 && m2<=2000)
                    { v2=(m2-1000)*0.2 +100;}
                    else
                        if (m2>2000)
                        { v2=(m2-1000)*0.2;}

       if (m3<100)
            v3=0;
        else
            if (m3>=100 && m3<500)
                v3=m3*0.085;
            else
                if (m3>=500 && m3<=1000)
                    v3=m3*(0.1)+50;
                else
                    if (m3>1000 && m3<=2000)
                        v3=(m2-1000)*0.2 +100;
                    else
                        if (m3>2000)
                            v3=(m3-1000)*0.2;


System.out.println("El vendedor 1 recibira :" +v1);
System.out.println("El vendedor 2 recibira :" +v2);
System.out.println("El vendedor 3 recibira :" +v3);
};
break;
case 6: {
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
                        sen=(Math.sin(d));
                        cos=(Math.cos(d));
                        tan=(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
                        }
        for(i=37;i<=53;i=i+8){

            d=i*3.1415926535897932384626433832795/180;
                        sen=(Math.sin(d));
                        cos=(Math.cos(d));
                        tan=(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
        }

        for(i=60;i<90;i=i+15){
            d=i*3.1415926535897932384626433832795/180;
                        sen=(Math.sin(d));
                        cos=(Math.cos(d));
                        tan=(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
        }

        System.out.println(90+"      \t"+(Math.sin(0)+1)+"\t\t\t    "+(Math.cos(0)-1)+"\t\t\tinfinito"+  "\t\t\t"+0.0+"\t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        System.out.println(180+"       \t"+Math.sin(0)+" \t\t\t"+-Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(-1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        for(i=210;i<270;i=i+30){
             d=i*3.1415926535897932384626433832795/180;
                        sen=(Math.sin(d));
                        cos=(Math.cos(d));
                        tan=(Math.tan(d));
                        cotg=1/tan;
                        sec=1/cos;
                        csc=1/sen;

                  System.out.println(i+"\t"+sen+"\t"+cos+"\t"+tan+"\t"+cotg+"\t\t"+sec+"\t\t"+csc);
         System.out.println(270+"      \t"+-(Math.sin(0)+1)+"         \t\t"+(Math.cos(0)-1)+"\t\t\tinfinito"+  "\t\t\t"+0.0+"\t\t\t"+-(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));
        System.out.println(360+"       \t"+Math.sin(0)+" \t\t\t"+Math.cos(0)+"  \t\t\t"+Math.tan(0)+"   \t\t\t"+(1/Math.tan(0))+"    \t\t"+(1/Math.cos(0))+"\t\t\t"+(1/Math.sin(0)));

};
}
break;
case 7: System.exit(op);break;
}
}
}
}
