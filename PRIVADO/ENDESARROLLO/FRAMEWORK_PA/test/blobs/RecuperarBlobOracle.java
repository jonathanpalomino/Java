/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blobs;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author JONATHAN
 */
public class RecuperarBlobOracle {

    public static void main(String[] args) {
        System.out.println("Inicializando programa ...");
        Connection conn = null;
        GestorDeConexiones gc = null;
        try {
            gc = new GestorDeConexiones("TRON2000", "TRON2000");
            conn = gc.getConnection();
            String path = "E:\\";
            RecuperadorBLOB.RecuperarBLOB(conn, "1", path);
        } catch (SQLException sqle) {
            System.out.println("Error de acceso a BD:" + sqle.getMessage());
            sqle.printStackTrace();
        } catch (IOException ioe) {
            System.out.println("Error de acceso a disco:" + ioe.getMessage());
            ioe.printStackTrace();
        }
        try {
            if (gc != null && conn != null) {
                gc.closeConnection();
            }
        } catch (SQLException sqle) {
            System.out.println("Error de acceso a BD:" + sqle.getMessage());
            sqle.printStackTrace();
            conn = null;
            gc = null;
        }
        System.out.println("Finalizando programa ...");
    }
}
