/*
 * Diseñar la funcion Seno
 */

package figurasgraficasfuncionseno;
import java.awt.*;
import java.awt.event.*;
public class FuncionSeno_2 extends Frame
{
	public static void main(String args[])
	{
		new FuncionSeno_2();

	}
	public FuncionSeno_2()
	{
		addWindowListener(new Cierre());

		setTitle("GRAFICA DE LA FUNCION SENO");
		setSize(1000,700);
		setVisible(true);

	}
	public void paint(Graphics g)
	{
		int a;
		/*****varables del programa original*****/
		double x=0,tot=0,radianes=0;
		int  pot=0;

		/***** variables de la grafica*/

		double[] ey= new double[721];
		int indice=0;

		/******************************/

		Font letrero = new Font("SansSerif", Font.ITALIC,14);
		//setBackground(Color.cyan);
		g.setFont(letrero);
		g.drawString("GRAFICA DE LA FUNCION SENO",42,60);
		g.drawString("|",960,355);
		g.drawString("|",870,355);
		g.drawString("|",780,355);
		g.drawString("|",690,355);
		g.drawString("|",600,355);

		g.drawString("|",420,355);
		g.drawString("|",330,355);
		g.drawString("|",240,355);
		g.drawString("|",148,355);
		g.drawString("|",57,355);

		g.drawString("__",503,172);
		g.drawString("__",503,524);

		Font letrero2 = new Font("SansSerif", Font.ITALIC,9);
		g.setFont(letrero2);

		g.drawString("Y",512,55);
		g.drawString("-Y",512,655);
		g.drawString("-X",20,365);
		g.drawString("X (RADIANES)",922,335);
		g.drawString("(5/2)*pi",955,375);
		g.drawString("2*pi",850,375);
		g.drawString("(3/2)*pi",765,375);
		g.drawString("pi",685,375);
		g.drawString("(1/2)*pi",595,375);
		g.drawString("-(1/2)*pi",415,375);
		g.drawString("-pi",325,375);
		g.drawString("-(3/2)*pi",235,375);
		g.drawString("-2*pi",142,375);
		g.drawString("-(5/2)*pi",56,375);

		g.drawString("1",492,176);
		g.drawString("-1",492,528);

		/***formula de sumatoria para calc seno de x***/
for(int cont=-450; cont <=450;cont=cont+2)
          {
                 radianes=(cont*3.14159262)/180;
                 pot =0;
                 x=0;
                 tot=0;
                for(int k =0;k<111 ;k++)
        {
            pot=2*k+1;
                x=(double)((Math.pow(radianes,pot))/(factorial(pot)));
                tot=(-1)*(x+tot);
                  pot =0;
        }
        tot=(-1)*tot;//resultado de seno
        /***************/
         ey[indice]=tot;
         indice++;
         /**************/
           }//termina for de incrementos de 2
/**********************************************************/
            //DIBUJA EL EJE y
     g.drawLine(510,0,510,700);
   //DIBUJA EL EJE x
     g.drawLine(0,350,1000,350);
/****************** grafica la funcion con valores de ey ********************/
int ex2=60,ex3=60;
for(int ex = 0; ex<450;ex++)
    {
        ex3=ex3+2;
        g.drawLine((int)(ex2),(int)(350-(ey[ex])*175/1),ex3,(int)(350-(ey[ex+1])*175/1));
        ex2=ex3;
try{
Thread.sleep(5);
}catch(InterruptedException e){}
    }
/****************************************/
}

public static  double  factorial(int a)
{
    double num=1;
    for (int i =1;i<=a;i++)
             num= (double) num *i;
    return(num);
}
}
class Cierre extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}
