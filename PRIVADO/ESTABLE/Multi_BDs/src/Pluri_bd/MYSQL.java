/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pluri_bd;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class MYSQL {

    private Contenedor abc;
    private String version;
    String datos[] = new String[2];

    MYSQL(Contenedor aThis, String version) {
        abc = aThis;
        this.version = version;
    }

    private void SetDRIVER_MYSQL() {
        try {
            ModificadorClassPath md = new ModificadorClassPath();
            if (version.equals("4")) {//verifica la version de mysql
                md.addArchivo(new File(".").getCanonicalPath() + "\\Librerias\\mysql-connector-java-5.0.8-bin.jar");
            } else {
                md.addArchivo(new File(".").getCanonicalPath() + "\\Librerias\\mysql-connector-java-5.1.22-bin.jar");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            Class.forName("com.mysql.jdbc.Driver"); // 2005 version
            abc.setEnlace(DriverManager.getConnection(abc.getUrl_Servidor(), datos[0], datos[1]));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage() + "  " + ex.getErrorCode());
            if (ex.getMessage().contains("Communications link failure")) {
                JOptionPane.showMessageDialog(null, "Error en la direccion del servidor", "Error general", JOptionPane.ERROR_MESSAGE);
            } else if (ex.getMessage().contains("Access denied for user")) {
                JOptionPane.showMessageDialog(null, "Error con las credenciales \nRevise el usuario y contraseña", "Error general", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
            }
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param servidor Direccion del servidor
     * @param catalogo Es la base de datos a acceder
     * @param usuario_root Especifique el usuario de autentificacion facilitado
     * por MYSQL
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * MYSQL
     */
    public void SetGestorMYSQL(String servidor, String catalogo, String usuario_root, String contrasena) {
        String url_Servidor = "jdbc:mysql://" + servidor + "/" + catalogo;
        abc.setUrl_Servidor(url_Servidor);
        datos[0] = usuario_root;
        datos[1] = contrasena;
        SetDRIVER_MYSQL();
    }

    /**
     * @param servidor Direccion del servidor
     * @param catalogo Es la base de datos a acceder
     * @param puerto Especifica el numero del puerto si es diferente a 3306
     * @param usuario_root Especifique el usuario de autentificacion facilitado
     * por MYSQL
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * MYSQL
     */
    public void SetGestorMYSQL(String servidor, String catalogo, String usuario_root, String contrasena, int puerto) {
        String url_Servidor = "jdbc:mysql://" + servidor + ":" + puerto + "/" + catalogo;
        abc.setUrl_Servidor(url_Servidor);
        datos[0] = usuario_root;
        datos[1] = contrasena;
        SetDRIVER_MYSQL();
    }
}
