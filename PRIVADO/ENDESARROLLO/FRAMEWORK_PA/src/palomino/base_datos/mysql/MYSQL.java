/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.mysql;

import java.io.File;
import java.sql.DriverManager;
import palomino.base_datos.coneccion.Contenedor;
import palomino.base_datos.coneccion.ModificadorClassPath;
import palomino.base_datos.stores.gestores.Unido;

/**
 *
 * @author JONATHAN
 */
public final class MYSQL {

    private Contenedor contenedor;
    private String version;
    String datos[] = new String[2];

    public MYSQL(Contenedor aThis, String version) {
        setContenedor(aThis);
        setVersion(version);
    }

    private void SetDRIVER_MYSQL() throws Exception {
        try {
            ModificadorClassPath md = new ModificadorClassPath();
            if (getVersion().equals("4")) {//verifica la version de mysql
                md.addArchivo(new File(".").getCanonicalPath() + "\\Librerias\\Base_datos\\mysql\\mysql-connector-java-5.0.8-bin.jar");
            } else {
                md.addArchivo(new File(".").getCanonicalPath() + "\\Librerias\\Base_datos\\mysql\\mysql-connector-java-5.1.22-bin.jar");
            }
        } catch (Exception ex) {
            Unido.setMensajeError(ex.getMessage());
        }

        Class.forName("com.mysql.jdbc.Driver"); // 2005 version
        getContenedor().setEnlace(DriverManager.getConnection(getContenedor().getUrl_Servidor(), datos[0], datos[1]));

    }

    /**
     * @param servidor Direccion del servidor
     * @param catalogo Es la base de datos a acceder
     * @param usuario_root Especifique el usuario de autentificacion facilitado
     * por MYSQL
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * MYSQL
     */
    public void SetGestorMYSQL(String servidor, String catalogo, String usuario_root, String contrasena) throws Exception {
        String url_Servidor = "jdbc:mysql://" + servidor + "/" + catalogo;
        getContenedor().setUrl_Servidor(url_Servidor);
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
    public void SetGestorMYSQL(String servidor, String catalogo, String usuario_root, String contrasena, int puerto) throws Exception {
        String url_Servidor = "jdbc:mysql://" + servidor + ":" + puerto + "/" + catalogo;
        getContenedor().setUrl_Servidor(url_Servidor);
        datos[0] = usuario_root;
        datos[1] = contrasena;
        SetDRIVER_MYSQL();
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

    /**
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * @param version the version to set
     */
    public void setVersion(String version) {
        this.version = version;
    }
}
