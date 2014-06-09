/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;
import palomino.util.algoritmo_criptografia.DES.Principal;
/**
 *
 * @author JONATHAN
 */
public class Prueba_des {

    public Prueba_des() {
        Principal var = new Principal();
        var.Generar_llaves("1234567890");
        System.out.println(var.Procesa_Encriptacion("Jonathan"));
        System.out.println(var.Procesa_Encriptacion("Jonathan2"));
        System.out.println(var.Procesa_Encriptacion("Jonathan3"));
        
        //var.Generar_llaves("1234567890");
        System.out.println(var.Procesa_DesEncriptacion("#Î»¨È»ÎG"));
    }
    public static void main(String args[]){
        new Prueba_des();
    }
}
