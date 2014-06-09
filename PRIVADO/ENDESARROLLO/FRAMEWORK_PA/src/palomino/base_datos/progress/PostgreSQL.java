/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.progress;

import java.sql.DriverManager;
import palomino.base_datos.coneccion.Contenedor;

/**
 *
 * @author synccon
 */
public final class PostgreSQL {

    private Contenedor contenedor;
    private String usuario;
    private String contrasena;

    public PostgreSQL(Contenedor aThis) {
        setContenedor(aThis);
    }
    /**
     * @param servidor Direccion del servidor
     * @param puerto   Numero de puerto tal vez distinto al 5432
     * @param esquema Si conectas por otra instancia
     * @param usuario Especifique el usuario de autentificacion facilitado por
     * PostgreSQL
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * PostgreSQL
     */
    public void SetGestorPostgreSQL(String servidor, int puerto, String esquema, String usuario, String contrasena) throws Exception {
        //jdbc:postgresql://server-jonathan:5432/Prueba
        String url_Servidor;
        setUsuario(usuario);
        setContrasena(contrasena);
        url_Servidor = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + esquema;
        getContenedor().setUrl_Servidor(url_Servidor);
        setDiverPostgreSQL();

    }
    /**
     * @param servidor Direccion del servidor
     * @param esquema Si conectas por otra instancia
     * @param usuario Especifique el usuario de autentificacion facilitado por
     * PostgreSQL
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * PostgreSQL
     */
        public void SetGestorPostgreSQL(String servidor, String esquema, String usuario, String contrasena) throws Exception {
        //jdbc:postgresql://server-jonathan:5432/Prueba
        String url_Servidor;
        setUsuario(usuario);
        setContrasena(contrasena);
        url_Servidor = "jdbc:postgresql://" + servidor + ":" + 5432 + "/" + esquema;
        getContenedor().setUrl_Servidor(url_Servidor);
        setDiverPostgreSQL();

    }
    private void setDiverPostgreSQL() throws Exception {
        Class.forName("org.postgresql.Driver");
        getContenedor().setEnlace(DriverManager.getConnection(getContenedor().getUrl_Servidor(), getUsuario(), getContrasena()));
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
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
