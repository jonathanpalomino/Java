package herencia5;
public class Figura 
{
        private double ancho; //Estos ahora
	private double altura; //son privados
	
	//Un constructor por defecto
	Figura()
	{
		ancho=altura=0.0;
	}
	//constructores parametrizados
	Figura(double a,double h)
	{
		ancho=a;
		altura=h;
	}
	
	//constructores con valores de ancho y altura al parametro
	Figura(double x)
	{
		ancho=altura=x;
	}
	
	//Metodos para accesar a ancho y altura
	
	double darAncho() {return ancho;}
	double darAltura() {return altura;}
        void asignarAncho(double a) {ancho=a;}
        void asignarAltura(double h) {altura=h;}
    
	void mostrarDimension()
	{
		System.out.println("El ancho y el alto son "+ancho+" y "+altura);
		
	}
}
