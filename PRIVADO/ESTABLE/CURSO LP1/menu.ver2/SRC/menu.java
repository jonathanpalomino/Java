/*
 * Realizar un programa que a travez de un menu de opciones enlace todos los
 * programas vistos hasta el momento
 */
/*
 *
 * @author JONATHAN PALOMINO VILCA
 * LINUX-OPEN SOURCE
 */

import java.io.*;
public class menu {

    @SuppressWarnings({"empty-statement", "fallthrough"})
public static void main(String args[]) throws IOException{
int op=0;
String linea;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
for(;;){
System.out.println("Ingrese una opcion");
System.out.println("1.- Sistema de notas");
System.out.println("2.- Sistema de sueldos");
System.out.println("3.- Sistema de pagos");
System.out.println("4.- Funciones Trigonometricas");
System.out.println("5.- Sistema de consumo");
System.out.println("6.- Funciones Trigonometricas ver.2");
System.out.println("7.- Velocidades");
System.out.println("8.- Velocidades. ver 2");
System.out.println("9.- Suma de cifras primas");
System.out.println("10.- mcm y mcd");
System.out.println("11.- mcm y mcd grafico");
System.out.println("12.- empezar y terminar en la misma cifra");
System.out.println("13.- capicua");
System.out.println("14.- Salir del Programa");
System.out.println("\n\n Opcion: ");
linea = br.readLine();
op = Integer.valueOf(linea).intValue();
switch (op){
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
    case 7:{
    double e1,e2,e3,t1,t2,t3,v1,v2,v3;
        e1=Math.random()*50+1;
        e2=Math.random()*50+1;
        e3=Math.random()*50+1;
        t1=Math.random()*16+15;
        t2=Math.random()*16+15;
        t3=Math.random()*16+15;
        v1=e1/t1*60;
        v2=e2/t2*60;
        v3=e3/t3*60;
        { if (v1>v2 && v1>v3)
        {
            System.out.println("El vehiculo 1 es más veloz con :"+v1+" km/h");
        }
            else
                if (v2>v1 && v2>v3){
            System.out.println("El vehiculo 2 es más veloz con :"+v2+" km/h");
                                    }
                else
                    System.out.println("El vehiculo 3 es más veloz con :"+v3+" km/h");
        }
if (v2<v1 && v2<v3)
    System.out.println("Y el vehiculo 2 es el mas lento con :"+v2+" Km/h");

else
    if (v1<v2 && v1<v3)
        System.out.println("Y el vehiculo 1 es el mas lento con :"+v1+" Km/h");
    else
        System.out.println("Y el vehiculo 3 es el mas lento con :"+v3+" Km/h");
    }
    break;
    case 8:{
        double e1, e2, e3, t1, t2, t3, v1, v2, v3;
        e1 = Math.random() * 50 + 1;
        if (e1>50)
            e1=50;
        e2 = Math.random() * 50 + 1;
        if (e2>50)
            e2=50;
        e3 = Math.random() * 50 + 1;
        if (e2>50)
            e2=50;
        t1 = Math.random() * 16 + 15;
        if (t1>30)
            t1=30;
        t2 = Math.random() * 16 + 15;
        if (t2>30)
            t2=30;
        t3 = Math.random() * 16 + 15;
        if (t2>30)
            t2=30;
        v1 = e1/t1*60;
        v2 = e2/t2*60;
        v3 = e3/t3*60;
            {
                if (v1==v2 && v2==v3){
            System.out.println("Las velocidades son iguales no se puede procesar");
            }
            else
                if (v1==v2 || v1==v3)
            System.out.println("Existen 2 velocidades iguales no se puede procesar");
             else
                if (v1>v2 && v2>v3){
            System.out.println("El Vehiculo 1 es el mas veloz con:"+v1+" kn/h");
            System.out.println("El Vehiculo 3 es el mas lento con:"+v3+" kn/h");
            }
            else
            if (v1>v3 && v3>v2){
            System.out.println("El Vehiculo 1 es el mas veloz con:"+v1+" kn/h");
            System.out.println("El Vehiculo 2 es el mas lento con:"+v2+" kn/h");
            }
            else
                if (v2>v1 && v1>v3){
            System.out.println("El Vehiculo 2 es el mas veloz con:"+v2+" kn/h");
            System.out.println("El Vehiculo 3 es el mas lento con:"+v3+" kn/h"); }
            else
                if (v2>v3 && v3>v1){
            System.out.println("El Vehiculo 2 es el mas veloz con:"+v2+" kn/h");
            System.out.println("El Vehiculo 1 es el mas lento con:"+v1+" kn/h");}
            else
                if (v3>v1 && v1>v2){
            System.out.println("El Vehiculo 3 es el mas veloz con:"+v3+" kn/h");
            System.out.println("El Vehiculo 2 es el mas lento con:"+v2+" kn/h");}
            else
                if(v3>v2 && v2>v1){
            System.out.println("El Vehiculo 3 es el mas veloz con:"+v3+" kn/h");
            System.out.println("El Vehiculo 1 es el mas lento con:"+v1+" kn/h");
                }
    }
    }break;
    case 9:{
        int m,i,z=0,t,res;
       m=(int)(Math.random()*50000);
       int epsilon[] = new int [m+1];
        //Inicio de operaciones
       for (i=0;i<m;i++)
       {
           epsilon[i]=(int)(Math.random()*10000);

           t=0;
           //Verificando si tiene cifras impares
           boolean tienePar=false;
           int y=10;
           int x=0, n = epsilon[i];
           while (y>0)
           {
               x=n%10; //resto
               y=n/10; //cociente;
               n=y;
               if ((x%2)==0)
               {
                   tienePar = true; break;

               }
           }
           if (tienePar ) break;
          System.out.println(epsilon[i]);
           //iniciando conteo de cifras
           while (epsilon[i]!=0)
           {
               z=epsilon[i]%10;
               epsilon[i] = epsilon[i]/10;
               t=t+z;

           }//Fin del conteo de cifras

            //Verificando si es primo
           res=0;
           for (int ir=1;ir<=t;ir++)
             {
               if (t%ir==0)
               {
                   res++;
               }
             }

           if (res==2)
                System.out.println("la suma da " +t+" por lo tanto es primo");
           else
               System.out.println("la suma da " +t+" por lo tanto no es primo");

            //Fin de verificacion

       }
       System.out.println("El arreglo cuenta con "+m+ " elementos");
    }break;
    case 10:{
        int a,b,t,t1,x;
        a=10;
        b=20;
        t=1;
        x=2;

        while (x<=a && x<=b)
        {
            while (a%x==0 && b%x==0)
            {
                a=a/x;
                b=b/x;
                t=t*x;
            }
            x++;
        }
        t1=t;
        t=t*a*b;
        System.out.println("El mcm es :" +t);
        System.out.println("El mcd es :" +t1);
    }break;
    case 11:{
//Mcd y mcm grafico
        
    }break;
    case 12:{
        // Realizar el programa que asigen aleatoriamente n numeros enteros se pide
        //calcular e imprimir cuantos numeros empiesan y terminan en la misma cifra.

        int n,i,d,d1=0;

            n=(int)(Math.random()*20);
            if (n==0)
                n=1;
            i=1;
            while (i<=n)
            {
                d=(int)(Math.random()*1000);
                System.out.println(d);
                int r=1,k=0;
                k=d;
                d1=k%10;
                while (d!=0)
                {
                    r=d%10;
                    d=d/10;
                 }
                System.out.println(r+"y"+d1);
                if (r==k)
                    System.out.println("Si cumple la condicion");
                else
                    System.out.println("No cumple la condicion");

                i++;
            }
    }break;
    case 13:{
        //Realizar el programa que asigne aleatoriamente el numero entero entre
        // 3 y 5 cifras , se pide calcular e imprimir cuantos nùmeros asignados
        //son capicuas
         int n,num,i,cif,x,inv,cont;
     n=(int)(Math.random()*100);
     for (i=0;i<=n;i++)
         {
         num=(int)Math.random()* 99900+100;
         x=num;
         inv =0;
         cont=0;
         do
             {
             cif=num%10;
             inv=inv*10+cif;
             num= num/10;
         }
         while (num!=0);
         if(inv==x)
            {
             cont++;
         System.out.println(x+" es capicua" );
            }
         else
             System.out.println(x+"  no es capicua");
         }
    }break;
case 14: System.exit(op);break;
}
}
}
}

