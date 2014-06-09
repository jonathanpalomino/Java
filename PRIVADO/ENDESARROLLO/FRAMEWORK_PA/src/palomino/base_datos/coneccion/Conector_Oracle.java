/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.coneccion;

import java.sql.Connection;

/**
 *
 * @author synccon
 */
public class Conector_Oracle {

    private static Contenedor Objeto_Conector = new Contenedor();
    private static String user = "TRON2000";
    private static String password = "TRON2000";
    private static String servicio = "SERVERORACLE";
    private static String servidor = "SERVER-JONATHAN";
    private static int puerto_defecto = 1521;
    private static Connection connection;
    private static String url_coneccion;
    /**
     * @return the user
     */
    public static String getUser() {
        return user;
    }

    /**
     * @param aUser the user to set
     */
    public static void setUser(String aUser) {
        user = aUser;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param aPassword the password to set
     */
    public static void setPassword(String aPassword) {
        password = aPassword;
    }

    /**
     * @return the servicio
     */
    public static String getServicio() {
        return servicio;
    }

    /**
     * @param aServicio the servicio to set
     */
    public static void setServicio(String aServicio) {
        servicio = aServicio;
    }

    /**
     * @return the servidor
     */
    public static String getServidor() {
        return servidor;
    }

    /**
     * @param aServidor the servidor to set
     */
    public static void setServidor(String aServidor) {
        servidor = aServidor;
    }

    /**
     * @return the puerto_defecto
     */
    public static int getPuerto_defecto() {
        return puerto_defecto;
    }

    /**
     * @param aPuerto_defecto the puerto_defecto to set
     */
    public static void setPuerto_defecto(int aPuerto_defecto) {
        puerto_defecto = aPuerto_defecto;
    }
   

    public static Connection getConecccion() throws Exception {
        getObjeto_Conector().getCONECTOR_ORACLE().SetGestorORACLE(getServidor(), getPuerto_defecto(), getServicio(), getUser(), getPassword());
        setConnection(getObjeto_Conector().getEnlace());
        setUrl_coneccion(getObjeto_Conector().getUrl_Servidor());
        return getConnection();
    }

    /**
     * @return the connection
     */
    public static Connection getConnection() {
        return connection;
    }

    /**
     * @param aConn the connection to set
     */
    public static void setConnection(Connection aConn) {
        connection = aConn;
    }

    /**
     * @return the url_coneccion
     */
    public static String getUrl_coneccion() {
        return url_coneccion;
    }

    /**
     * @param aUrl_coneccion the url_coneccion to set
     */
    public static void setUrl_coneccion(String aUrl_coneccion) {
        url_coneccion = aUrl_coneccion;
    }

    /**
     * @return the Objeto_Conector
     */
    public static Contenedor getObjeto_Conector() {
        return Objeto_Conector;
    }

    /**
     * @param aOb the Objeto_Conector to set
     */
    public static void setObjeto_Conector(Contenedor aOb) {
        Objeto_Conector = aOb;
    }
}
