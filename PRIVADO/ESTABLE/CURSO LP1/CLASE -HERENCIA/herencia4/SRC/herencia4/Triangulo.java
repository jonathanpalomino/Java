package herencia4;
public class Triangulo extends Figura
{
         private String tipo;
	//constructor
	Triangulo(String t,double a,double h)
	{
		super(a,h);  //llama a la superclase constructora
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
