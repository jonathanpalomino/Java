package herencia4;
public class Figura 
{
        private double ancho; //Estos ahora
	private double altura; //son privados
	
	//constructores parametrizados
	Figura(double a,double h)
	{
		ancho=a;
		altura=h;
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
