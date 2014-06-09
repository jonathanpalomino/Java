package herencia3;
public class Triangulo extends Figura
{
       private String tipo;
	//constructor
	Triangulo(String t,double a,double h)
	{
		asignarAncho(a);
		asignarAltura(h);
		tipo=t;
	}
	
	double area()
	{
		return darAncho()*darAltura()/2;
	}
	
	void mostrarTipo()
	{
		System.out.println("Triangulo es "+tipo);
	}
}
