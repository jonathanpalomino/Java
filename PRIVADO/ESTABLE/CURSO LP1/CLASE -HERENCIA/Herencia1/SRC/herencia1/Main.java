package herencia1;
public class Main 
{
    public static void main(String[] args) 
    {
                Triangulo t1= new Triangulo();
		Triangulo t2= new Triangulo();
		t1.ancho=4.0;
		t1.alto=4.0;
		t1.tipo="isosceles";
		
		t2.ancho=8.0;
		t2.alto=12.0;
		t2.tipo="rectangulo";
		
		System.out.println("Informacion para t1");
		t1.mostrarTipo();
		t1.mostrarDimension();
		System.out.println("El area es "+t1.area());
		System.out.println();
		System.out.println("Informancion para t2");
		t2.mostrarTipo();
		t2.mostrarDimension();
		System.out.println("El area es "+t2.area());
    }

}
