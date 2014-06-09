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
 * @author synccon
 */
public class POSTGRESQL {
    static Connection enlace;
    static Contenedor variable = new Contenedor("POSTGRESQL");
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
            try {
            variable.getCONECTOR_POSTGRESQL().SetGestorPostgreSQL("server-jonathan", 5432, "Prueba", "postgres", "root");
        } catch (Exception ex) {
            Logger.getLogger(Prueba_mysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(variable.getUrl_Servidor());
        enlace = variable.getEnlace();
    }
}
