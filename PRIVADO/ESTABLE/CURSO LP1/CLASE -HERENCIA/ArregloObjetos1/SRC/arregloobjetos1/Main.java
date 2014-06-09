package arregloobjetos1;
public class Main 
{
    public static void main(String[] args) 
    {
               double h,b;
		secundaria[] tri = new secundaria[11];
		for(int i=0;i<10;i++)
		{
			b=Math.random()*10;
			h=Math.random()*10;
		        tri[i]=new secundaria(b,h);
			System.out.println("El area del triangulo de base : "+ b +" y altura "+h+
                    " es "+tri[i].area());
		}
    }

}
