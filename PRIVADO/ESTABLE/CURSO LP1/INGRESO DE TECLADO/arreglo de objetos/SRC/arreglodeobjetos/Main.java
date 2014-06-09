/*
 * Crear un arreglo de objetos de N empleados de una empresa con las siguientes caracteristicas:(codigo,sueldo basico,bonificacion.descuento
 * categoria (auxiliar,tecnico,profesional,funcionario) sexo)  calcular el sueldo mas alto por categoria y sexo a la vez.
 */

package arreglodeobjetos;
import javax.swing.JOptionPane;
public class Main {

    public static void main(String[] args) {
    int n;
    int cod,suelb,bon,desc,neto;
    String categoria,sexo;
    n=Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de empleados"));
    secundaria[] obj = new secundaria[n+1];
    System.out.println("CODIGO  SUELDO.BAS  BONIF   DESCUENTO   NETO    CATEGORIA   SEXO");
    System.out.println("========================================================");
        for(int i=0;i<n;i++)
        {
            cod=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo"));
            suelb=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el sueldo basico"));
            bon=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el bonificacion"));
            desc=Integer.parseInt(JOptionPane.showInputDialog("Ingrese el descuento"));
            categoria=JOptionPane.showInputDialog("Ingrese la categoria");
            sexo=JOptionPane.showInputDialog("Ingrese el genero M ó F");
            neto=suelb+bon-desc;
            obj[i]=new secundaria(neto,categoria,sexo);
            System.out.println(cod+"\t"+suelb+"\t"+bon+"\t"+desc+"\t"+neto+"\t"+categoria+"\t"+sexo);
            
        }

    System.out.println("========================================================");


    }

}
