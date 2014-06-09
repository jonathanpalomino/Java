package herencia2;
public class Triangulo extends Figura
{
        String tipo;
	double area()
	{
		return darAncho()*darAltura()/2;
	}
	void mostrarTipo()
	{
		System.out.println("Triangulo es "+tipo);
	}
}
