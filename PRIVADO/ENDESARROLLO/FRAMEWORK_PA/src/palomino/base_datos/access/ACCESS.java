/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.access;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
import palomino.base_datos.coneccion.Contenedor;

/**
 *
 * @author JONATHAN
 */
public final class ACCESS {
private Contenedor contenedor;
    public ACCESS(Contenedor aThis) {
        setContenedor(aThis);
    }
     private void Setdriver_access()  throws Exception{
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ=" + getContenedor().getUrl_Servidor();
            getContenedor().setEnlace(DriverManager.getConnection(myDB, "", ""));

    }

    /**
     * @param direccion  Direccion del archivo access
     * en caso este en la misma carpeta del proyecto o planeas usarlo donde este el archivo jar
     * digita el nombre del archivo access (tuarchivo.mdb) caso contrario especifica la direccion completa del archivo
     * access. EJEMPLO direcion.mdb Ó C:\\tuarchivo.mdb Ó C:/tuarchivo.mdb
     *
     */
    public void SetGestorACCES(String direccion) throws Exception {
        if (!direccion.contains(":\\") && !direccion.contains(":/")) {
            try {
                String url_Servidor1 = new File(".").getCanonicalPath() + "\\" + direccion;
                getContenedor().setUrl_Servidor(url_Servidor1);
            }
            catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "No se hallo el archivo", "Error general", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            getContenedor().setUrl_Servidor(direccion);
        }
        Setdriver_access();
    }

    /**
     * @return the contenedor
     */
    public Contenedor getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }

}
