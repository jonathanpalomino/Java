package herencia5;
public class Triangulo extends Figura
{
        private String tipo;
	//Un constructor por defecto
	Triangulo()
	{
		super();
		tipo="null";
	}
	
	//constructor
	Triangulo(String t,double a,double h)
	{
		super(a,h);  //llama a la superclase constructora
		tipo=t;
	}
	
	//constructor para triangulo isosceles
	Triangulo(double x)
	{
		super(x);  //llama a la superclase constructora
		tipo="isosceles";
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
