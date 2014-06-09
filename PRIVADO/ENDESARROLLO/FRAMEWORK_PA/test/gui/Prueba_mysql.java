/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import palomino.base_datos.coneccion.Contenedor;
/**
 *
 * @author JONATHAN
 */
public class Prueba_mysql {
    static Connection enlace;
    static Contenedor variable = new Contenedor("MYSQL","5");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            variable.getCONECTOR_Mysql().SetGestorMYSQL("192.168.102.129", "mysql", "jonathan", "jonathan");
        } catch (Exception ex) {
            Logger.getLogger(Prueba_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(variable.getUrl_Servidor());
        enlace = variable.getEnlace();
    }
}
