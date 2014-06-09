package herencia1;
public class Triangulo extends Figura
{
        String tipo;
	double area()
	{
		return ancho*alto/2;
	}
	void mostrarTipo()
	{
		System.out.println("Triangulo es "+tipo);
	}
}
