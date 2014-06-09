package herencia3;
public class Main {
    public static void main(String[] args) 
    {
          	Triangulo t1=new Triangulo("isosceles",4.0,4.0);
		Triangulo t2=new Triangulo("rectangulo",8.0,12.0);
		System.out.println("Informacion para t1");
		t1.mostrarTipo();
		t1.mostrarDimension();
		System.out.println("El area es "+t1.area());
		System.out.println();
		
		System.out.println("Informacion para t2");
		t2.mostrarTipo();
		t2.mostrarDimension();
		System.out.println("El area es "+t2.area());
    }

}
