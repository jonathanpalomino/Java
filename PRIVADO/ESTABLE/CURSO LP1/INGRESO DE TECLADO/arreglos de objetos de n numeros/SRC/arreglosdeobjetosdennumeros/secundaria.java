package arreglosdeobjetosdennumeros;
class secundaria {
int num=(int)(Math.random()*10000),impar,h;
boolean par1=false;

    secundaria()
    {
    }
    int c=0;
    int num1=num;
    int num2 =num;
    int y=10,x=0,par=0,cont;
   public void tra()

    {

    while(num1!=0)
    {
    num1=num1/10;
    c++;//cantidad de cifras
    }

    while (y>0)
           {
               x=num%10; //resto
               y=num/10; //cociente;
               num=y;
               if ((x%2)==0)
               {
                   par++;
               }
           }
    impar=c-par;
    if (impar==3)
    {
        cont++;

    }
    System.out.println("El numero "+num2+"tiene "+par+"cifras pares y "+impar+ "cifras impares");
  h = h+ cont;
    }
void h(){
    System.out.println(h);
}
}
