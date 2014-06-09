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
public class Prueba_Sql {
    static Connection enlace;
    static Contenedor variable = new Contenedor("MSSQL");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
               try {
            variable.getCONECTOR_SQL().SetGestorMSSQL("192.168.102.129", "MSSQLSERVER", 1433, "PRUEBA", "JONATHAN", "123456");
        } catch (Exception ex) {
            Logger.getLogger(Prueba_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(variable.getUrl_Servidor());
        enlace = variable.getEnlace();
    }
}
