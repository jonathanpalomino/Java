/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Pluri_bd;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class ACCESS {
private Contenedor abc;
    ACCESS(Contenedor aThis) {
        abc = aThis;
    }
     private void Setdriver_access() {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + abc.getUrl_Servidor();
            abc.setEnlace(DriverManager.getConnection(myDB, "", ""));
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        }
        catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param direccion  Direccion del archivo access
     * en caso este en la misma carpeta del proyecto o planeas usarlo donde este el archivo jar
     * digita el nombre del archivo access (tuarchivo.mdb) caso contrario especifica la direccion completa del archivo
     * access. EJEMPLO direcion.mdb Ó C:\\tuarchivo.mdb Ó C:/tuarchivo.mdb
     *
     */
    public void SetGestorACCES(String direccion) {
        if (!direccion.contains(":\\") && !direccion.contains(":/")) {
            try {
                String url_Servidor1 = new File(".").getCanonicalPath() + "\\" + direccion;
                abc.setUrl_Servidor(url_Servidor1);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se hallo el archivo", "Error general", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            abc.setUrl_Servidor(direccion);
        }
        Setdriver_access();
    }

}
