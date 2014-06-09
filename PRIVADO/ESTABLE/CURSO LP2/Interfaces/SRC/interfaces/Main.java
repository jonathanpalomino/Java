

package interfaces;

public class Main {

    public static void main(String[] args) {
              Salida s =new Salida();
        jua ss = s;
        s.saluda("Holita");
        Salida1 s2 =new Salida1();
        s2.saluda("Chaito");
    }

}

    class Salida implements jua{

        public void saluda(String n){
            System.out.println(n);
        }
    }

        class Salida1 extends Salida{
    }
interface jua{
     void saluda(String n);
     //int ped();
}
