/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blobs;

/*
 * InsertarBlobOracle.java
 * @author rayala
 * Created on 1 de agosto de 2007, 16:00
 *
 * Inserta una imagen en tabla oracle y campo del tipo BLOB
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class InsertarBlobOracle {

    public static void main(String[] args) {
        System.out.println("Inicializando programa ...");
        Connection conn = null;
        GestorDeConexiones gc = null;
        try {
            gc = new GestorDeConexiones("TRON2000", "TRON2000");
            conn = gc.getConnection();
            String path = "D:\\";
            InsertaBLOB.InsertarBLOB(conn, "impresora.pdf", path);
            //RecuperadorBLOB.RecuperarBLOB(conn,"000003",path);
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
