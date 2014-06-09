package herencia5;
public class Main 
{
    public static void main(String[] args) 
    {
        Triangulo t1=new Triangulo();
		Triangulo t2=new Triangulo("rectangulo",8.0,12.0);
		Triangulo t3=new Triangulo(4.0);
		
		t1=t2;
		
		System.out.println("Informacion para t1");
		t1.mostrarTipo();
		t1.mostrarDimension();
		System.out.println("El area es "+t1.area());
		System.out.println();
		
		System.out.println("Informacion para t2");
		t2.mostrarTipo();
		t2.mostrarDimension();
		System.out.println("El area es "+t2.area());
		System.out.println();
			
		System.out.println("Informacion para t3");
		t3.mostrarTipo();
		t3.mostrarDimension();
		System.out.println("El area es "+t3.area());
		System.out.println();
    }

}
