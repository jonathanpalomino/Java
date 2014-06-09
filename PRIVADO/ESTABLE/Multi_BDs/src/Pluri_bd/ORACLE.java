/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pluri_bd;

import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class ORACLE {
Contenedor abc;
    ORACLE(Contenedor aThis) {
        abc = aThis;
    }
    private void setDiverOracle() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            abc.setEnlace(DriverManager.getConnection(abc.getUrl_Servidor()));
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * @param servidor  Direccion del servidor
     * @param SID Es el servicio o sid
     * @param puerto Especifica el numero del puerto si es diferente a 1433
     * @param usuario Especifique el usuario de autentificacion facilitado por ORACLE O un Administrador
     * @param contrasena Especifique la contraseña del usuario facilitado por ORACLE O un Administrador
     */
    public void SetGestorORACLE(String servidor, int puerto, String SID, String usuario, String contrasena) {
        //jdbc:oracle:thin:usuario/clave@host:puerto:sid
        String url_Servidor;
        url_Servidor = "jdbc:oracle:thin:" + usuario + "/" + contrasena + "@" + servidor + ":" + puerto + ":" + SID;
        abc.setUrl_Servidor(url_Servidor);
        setDiverOracle();
    }
}
