/**
 *
 * @author JONATHAN
 */
import java.util.*;

public class Programa {

    public ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();

    public static void main(String[] args) {
        Programa p = new Programa();
        p.mostrarMenu();
    }

    public void mostrarMenu() {
        System.out.println("******* Francis H. Mota Valera *******");
        System.out.println("******** Matricula # 86766 ********\n");
        System.out.println("************* Menu de seleccion *************\n\n\n");
        System.out.println("1) Agregar Empleado");
        System.out.println("2) Consultar Empleado");
        System.out.println("3) Listar todos los Empleados");
        System.out.println("4) Mostrar ultimo Empleado");
//estas son las tres opciones mas
        System.out.println("5) Impresion de nomina de todos los empleados: ");
        System.out.println("6) Impresion de Empleado que menos salario * gana *: ");
        System.out.println("7) Impresion de Empleado que mas salario * gana *: ");
// estas la puse de ultimo por que se veria feo en el medio de las otras
        System.out.println("8) Salir\n\n\n");
        System.out.print("Digite aqui el numero de su opcion: ");
        Scanner l = new Scanner(System.in);

        int numeroOpcion = l.nextInt();

        switch (numeroOpcion) {

            case 1:
                double total = 0;
                double horasTrabajadas = 0;
                int id = 0;
                double pagoPorHora = 0;
                String nombre = "";
                String puesto = "";
                String fechaIngreso = "";
                System.out.print("Digite Fecha De Ingreso ");
                fechaIngreso = l.next();
//System.out.println(" Hoy " + new Date() );
                System.out.print("Digite aqui el ID del nuevo empleado: ");
                id = l.nextInt();
                System.out.print("Digite el nombre: ");
                nombre = l.next();
                System.out.print("Digite el puesto: ");
                puesto = l.next();
//esto es los atributos nuevos nuevo
                System.out.print("Digite aqui hora a trabajar ");
                horasTrabajadas = l.nextInt();
                System.out.print("Digite aqui Pago Por hora ");
                pagoPorHora = l.nextInt();
                total = horasTrabajadas * pagoPorHora;
                Empleado p = new Empleado(total, id, pagoPorHora, horasTrabajadas, nombre, puesto, fechaIngreso);
                listaEmpleados.add(p);
                System.out.println("Empleado agregado con exito!\n\n\n");
                break;

            case 2:

                int id2 = 0;
                System.out.print("Digite aqui el ID a consultar: ");
                id2 = l.nextInt();


                for (Empleado emp : listaEmpleados) {
                    if (id2 == emp.id) {
                        System.out.println("\n El empleado buscado es: " + emp.nombre + " trabaja en " + emp.puesto
                                + " y su suerdo sera:" + emp.total + "\n Fecha Inicial " + new Date() + "\n");

                    } else {
                        System.out.println(" \n******El ID buscado no Existe*****");
                    }
                }
                break;

            case 3:

                for (Empleado emp : listaEmpleados) {
                    System.out.println("\n Empleado # " + emp.id + "\t Nombre: " + emp.nombre + " \t Puesto: " + emp.puesto + "\n");
                }

                break;

            case 4:
                int cantidadEmpleados = listaEmpleados.size();
                Empleado emp = listaEmpleados.get(cantidadEmpleados - 1);
                System.out.println("\n El ultimo empleado es : " + emp.nombre + " trabaja en " + emp.puesto + "\n");

                break;
// esta es una de las nuevas ocpciones
            case 5:

                for (Empleado emp1 : listaEmpleados) {
                    System.out.println("\n Empleado " + "\t Nombre: " + emp1.nombre + " \t Puesto: "
                            + emp1.puesto + "\t" + " Salario : " + emp1.total + "\n");
                }
                break;

            case 6:
                CONSULTAR_MENOR_COSTE(listaEmpleados);
                break;
            case 7:
                CONSULTAR_MAYOR_COSTE(listaEmpleados);
                break;
            case 8:
                //ahora la optcion 8 es la de salir
                System.exit(0);
                break;
            default:
                System.out.println(" \n****** Opcion No Valida ********\n");
                break;
        }
        mostrarMenu();
    }

    private void CONSULTAR_MENOR_COSTE(ArrayList<Empleado> listaEmpleados) {
        int indice = RETORNA(listaEmpleados, "menor");
        System.out.println("el menor es " + listaEmpleados.get(indice).total + " la persona es " + listaEmpleados.get(indice).nombre);
    }

    private void CONSULTAR_MAYOR_COSTE(ArrayList<Empleado> listaEmpleados) {
        int indice = RETORNA(listaEmpleados, "mayor");
        System.out.println("el mayor es " + listaEmpleados.get(indice).total + " la persona es " + listaEmpleados.get(indice).nombre);
    }

    private int RETORNA(ArrayList<Empleado> listaEmpleados, String cadena) {
        int b = 0;
        if (cadena.equals("menor")) {
            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).total < listaEmpleados.get(b).total) {
                    b = i;
                }
            }
        } else {
            for (int i = 0; i < listaEmpleados.size(); i++) {
                if (listaEmpleados.get(i).total > listaEmpleados.get(b).total) {
                    b = i;
                }
            }
        }
        return b;
    }
}

class Empleado {

    public String fechaIngreso;
    public int id;
    public String nombre;
    public String puesto;
    public double horasTrabajadas;
    public double pagoPorHora;
    public double total;

    public Empleado(double total, int id, double pagoPorHora, double horasTrabajadas, String nombre, String puesto, String fechaIngreso) {

        this.id = id;
        this.nombre = nombre;
        this.puesto = puesto;
        this.horasTrabajadas = horasTrabajadas;
        this.pagoPorHora = pagoPorHora;
        this.fechaIngreso = fechaIngreso;
        this.total = total;
//estos son los dos nuevos atributos

    }
}
