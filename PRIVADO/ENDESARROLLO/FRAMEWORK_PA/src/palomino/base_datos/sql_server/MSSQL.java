/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.sql_server;

import java.sql.DriverManager;
import palomino.base_datos.coneccion.Contenedor;

/**
 *
 * @author JONATHAN
 */
public final class MSSQL {

    private Contenedor contenedor;
    private String contrasena;
    private String usuario;

    public MSSQL(Contenedor aThis) {
        //contenedor = aThis;
        setContenedor(aThis);
    }

    /**
     * @param servidor Direccion del servidor
     * @param instancia Si conectas por otra instancia diferente a /SQLEXPRESS
     * @param catalogo Es la base de datos a acceder
     * @param puerto Especifica el numero del puerto si es diferente a 1433
     * @param usuario Especifique el usuario de autentificacion facilitado por
     * SQL SERVER
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * SQL SERVER
     */
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, String usuario, String contrasena) throws Exception {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo;
        this.setUsuario(usuario);
        this.setContrasena(contrasena);
        getContenedor().setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    /**
     * @param servidor Direccion del servidor
     * @param instancia Si conectas por otra instancia diferente a /SQLEXPRESS
     * @param catalogo Es la base de datos a acceder
     * @param puerto Especifica el numero del puerto si es diferente a 1433
     * @param usuario Especifique el usuario de autentificacion facilitado por
     * SQL SERVER
     * @param contrasena Especifique la contraseña del usuario facilitado por
     * SQL SERVER
     * @param tiempo_limite Especifica el tiempo limite de coneccion finalizado
     * este se cierra la coneccion tiempo en segundos
     */
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, String usuario, String contrasena, int tiempo_limite) throws Exception {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo + ";Connect Timeout=" + tiempo_limite;
        this.setUsuario(usuario);
        this.setContrasena(contrasena);
        getContenedor().setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    /**
     * @param servidor Le agrega automaticamente la instancia /SQLEXPRESS
     * @param instancia Si conectas por otra instancia diferente a /SQLEXPRESS
     * @param puerto Especifica el numero del puerto si es diferente a 1433
     * @param catalogo Es la base de datos a acceder
     * @param seguridad_integrada especifica si quieres que se autentifique con
     * windows o sino tienes un usuario
     */
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, boolean seguridad_integrada) throws Exception {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo + ";integratedSecurity =" + seguridad_integrada;
        getContenedor().setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    /**
     * @param servidor_instanciapredeterminada A la direccion del servidor se
     * agrega automaticamente la instancia /SQLEXPRESS
     * @param catalogo Es la base de datos a acceder
     * @param seguridad_integrada especifica si quieres que se autentifique con
     * windows o sino tienes un usuario
     */
    public void SetGestorMSSQL(String servidor_instanciapredeterminada, String catalogo, boolean seguridad_integrada) throws Exception {
        String url_Servidor = "jdbc:sqlserver://" + servidor_instanciapredeterminada + "\\SQLEXPRESS" + ";databaseName=" + catalogo + ";integratedSecurity =" + seguridad_integrada;
        getContenedor().setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    private void SetDRIVER_MSSQL() throws Exception {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 2005 version
        if(getUsuario().isEmpty() || getContrasena().isEmpty()){
            getContenedor().setEnlace(DriverManager.getConnection(getContenedor().getUrl_Servidor()));
        }
        else{
            getContenedor().setEnlace(DriverManager.getConnection(getContenedor().getUrl_Servidor(), getUsuario(), getContrasena()));
        }
        

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
