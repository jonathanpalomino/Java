package velocidadesver2;

public class velocidadesver2 {


    public static void main(String[] args) {
        double  v1, v2, v3;

        double e1, e2, e3, t1, t2, t3;
        e1 = Math.random() * 50 + 1;
        if (e1>50)
            e1=50;
        e2 = Math.random() * 50 + 1;
        if (e2>50)
            e2=50;
        e3 = Math.random() * 50 + 1;
        if (e2>50)
            e2=50;
        t1 = Math.random() * 16 + 15;
        if (t1>30)
            t1=30;
        t2 = Math.random() * 16 + 15;
        if (t2>30)
            t2=30;
        t3 = Math.random() * 16 + 15;
        if (t2>30)
            t2=30;
        v1 = e1/t1*60;
        v2 = e2/t2*60;
        v3 = e3/t3*60;
            {
                if (v1==v2 && v2==v3){
            System.out.println("Las velocidades son iguales no se puede procesar");
            }
            else
                if (v1==v2 || v1==v3)
            System.out.println("Existen 2 velocidades iguales no se puede procesar");
             else
                if (v1>v2 && v2>v3){
            System.out.println("El Vehiculo 1 es el mas veloz con:"+v1+" kn/h");
            System.out.println("El Vehiculo 3 es el mas lento con:"+v3+" kn/h");
            }
            else
            if (v1>v3 && v3>v2){
            System.out.println("El Vehiculo 1 es el mas veloz con:"+v1+" kn/h");
            System.out.println("El Vehiculo 2 es el mas lento con:"+v2+" kn/h");
            }
            else
                if (v2>v1 && v1>v3){
            System.out.println("El Vehiculo 2 es el mas veloz con:"+v2+" kn/h");
            System.out.println("El Vehiculo 3 es el mas lento con:"+v3+" kn/h"); }
            else
                if (v2>v3 && v3>v1){
            System.out.println("El Vehiculo 2 es el mas veloz con:"+v2+" kn/h");
            System.out.println("El Vehiculo 1 es el mas lento con:"+v1+" kn/h");}
            else
                if (v3>v1 && v1>v2){
            System.out.println("El Vehiculo 3 es el mas veloz con:"+v3+" kn/h");
            System.out.println("El Vehiculo 2 es el mas lento con:"+v2+" kn/h");}
            else
                if(v3>v2 && v2>v1){
            System.out.println("El Vehiculo 3 es el mas veloz con:"+v3+" kn/h");
            System.out.println("El Vehiculo 1 es el mas lento con:"+v1+" kn/h");}
        }
}
}