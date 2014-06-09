/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package registrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import Multi_bd.Principal;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class Libreria{

    static Properties propiedades_conf = new Properties();
    String base_d_datos = "";
    private String url_Servidor, instancia, usuario, contrasena;
    private Connection enlace;
    int puerto;
    boolean integrada;
    Principal trans = new Principal();

    /**
     * @param args the command line arguments
     */
    public void Acceder_Conf() {
        try {
            String canonicalPath = new File(".").getCanonicalPath() + "\\cfg_rg_sg.dll";
            System.out.println(canonicalPath);
            propiedades_conf.load(new FileInputStream(canonicalPath));
        }
        catch (IOException ex) {
            System.out.println("No existe fichero se creara uno");
        }
    }

    void cargar_conf() {
        if (propiedades_conf.getProperty("CONTROLADOR").equals("0")) {
            System.out.println("llama sql");
            LLAMAR_ATRIBUTOS_SQL();
            cargar_SQL();
        }
        else if (propiedades_conf.getProperty("CONTROLADOR").equals("1")) {
            System.out.println("llama mysql");
            LLAMAR_ATRIBUTOS_MYSQL();
            cargar_MYSQL();
        }
        else if (propiedades_conf.getProperty("CONTROLADOR").equals("2")) {
            System.out.println("llama access");
            LLAMAR_ATRIBUTOS_ACCESS();
            cargar_ACCESS();
        }
        else if (propiedades_conf.getProperty("CONTROLADOR").equals("3")) {
            System.out.println("llama oracle");
            LLAMAR_ATRIBUTOS_ORACLE();
            cargar_ORACLE();
        }
    }

    void cargar_SQL() {
        trans.getCONECTOR_SQL().SetGestorSQL(url_Servidor, base_d_datos, true);
        setEnlace(trans.getEnlace());
    }

    void cargar_MYSQL() {
        trans.getCONECTOR_Mysql().SetGestorMYSQL(url_Servidor, base_d_datos, usuario, contrasena);
        setEnlace(trans.getEnlace());
    }

    void cargar_ACCESS() {
        trans.getCONECTOR_access().SetGestorACCES(url_Servidor);
        setEnlace(trans.getEnlace());
    }

    void cargar_ORACLE() {
        //("localhost",1521, "BASEORACLE", "JONATHAN","123456")
        trans.getCONECTOR_ORACLE().SetGestorORACLE(url_Servidor, puerto, base_d_datos, usuario, contrasena);
        setEnlace(trans.getEnlace());
    }

    /**
     * @return the url_Servidor
     */
    public String getUrl_Servidor() {
        return url_Servidor;
    }

    /**
     * @param url_Servidor the url_Servidor to set
     */
    public void setUrl_Servidor(String url_Servidor) {
        this.url_Servidor = url_Servidor;
    }

    /**
     * @return the enlace
     */
    public Connection getEnlace() {
        return enlace;
    }

    /**
     * @param enlace the enlace to set
     */
    public void setEnlace(Connection enlace) {
        this.enlace = enlace;
    }

    private void LLAMAR_ATRIBUTOS_SQL() {
        setUrl_Servidor(propiedades_conf.getProperty("SERVSQL").trim());
        try {
            instancia = propiedades_conf.getProperty("INSTANCIA");
        }
        catch (Exception e) {
        }
        try {
            puerto = Integer.parseInt(propiedades_conf.getProperty("PUERTO"));
        }
        catch (NumberFormatException numberFormatException) {
        }
        base_d_datos = propiedades_conf.getProperty("BASE_DATO_SQL");
        integrada = Boolean.parseBoolean(propiedades_conf.getProperty("INTEGRADA"));
        try {
            usuario = propiedades_conf.getProperty("USUARIOSQL");
            contrasena = propiedades_conf.getProperty("CONTRASENASQL");
        }
        catch (Exception e) {
        }
    }

    private void LLAMAR_ATRIBUTOS_MYSQL() {
        setUrl_Servidor(propiedades_conf.getProperty("SERVMYSQL").trim());
        try {
            puerto = Integer.parseInt(propiedades_conf.getProperty("PUERTOM"));
        }
        catch (NumberFormatException numberFormatException) {
        }
        base_d_datos = propiedades_conf.getProperty("BASE_DATO_MYSQL");
        usuario = propiedades_conf.getProperty("USUARIO_MYSQL");
        contrasena = propiedades_conf.getProperty("CONTRASENA_MYSQL");
    }

    private void LLAMAR_ATRIBUTOS_ACCESS() {
        base_d_datos = propiedades_conf.getProperty("ARCHIVO").trim();
        try {
            String url_Servidor1 = new File(".").getCanonicalPath() + "\\" + base_d_datos;
            this.setUrl_Servidor(url_Servidor1);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LLAMAR_ATRIBUTOS_ORACLE() {
        setUrl_Servidor(propiedades_conf.getProperty("SERVORACLE").trim());
        puerto = Integer.parseInt(propiedades_conf.getProperty("PUERTO_O"));
        base_d_datos = propiedades_conf.getProperty("SID_ORACLE");
        usuario = propiedades_conf.getProperty("USUARIO_ORACLE");
        contrasena = propiedades_conf.getProperty("CONTRASENA_ORACLE");
    }
}
