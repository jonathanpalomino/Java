/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package registrador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class Libreria{

    static Properties propiedades_conf = new Properties();
    String base_d_datos="";
    private String url_Servidor;
    private Connection enlace;
    /**
     * @param args the command line arguments
     */
    public void Acceder_Conf() {
        try {
            String canonicalPath = new File(".").getCanonicalPath() + "/cfg_rg_sg.txt";
            propiedades_conf.load(new FileInputStream(canonicalPath));
            base_d_datos = propiedades_conf.getProperty("BASE_DATOS");
        }
        catch (IOException ex) {
            System.out.println("No existe fichero se creara uno");
        }
    }

    void cargar_conf() {
        if(propiedades_conf.getProperty("CONTROLADOR").equals("0")){
            cargar_SQL();
        }
        else if(propiedades_conf.getProperty("CONTROLADOR").equals("1"))
        {
            cargar_MYSQL();
        }
        else if (propiedades_conf.getProperty("CONTROLADOR").equals("2")) {
            cargar_ACCESS();
        }
    }
    void cargar_SQL(){
        String url_Servidor="jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName="+base_d_datos+";integratedSecurity =true";
        this.setUrl_Servidor(url_Servidor);
    }
    void cargar_MYSQL(){
        String url_Servidor="jdbc:mysql://localhost/"+ base_d_datos;
        this.setUrl_Servidor(url_Servidor);
    }
    void cargar_ACCESS(){
        try {
            String url_Servidor1 = new File(".").getCanonicalPath() + "\\" + base_d_datos + ".mdb";
            this.setUrl_Servidor(url_Servidor1);
        }
        catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }
    void Setdriver_sql(){
              try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 2005 version
            setEnlace(DriverManager.getConnection(url_Servidor));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }
    void Setdriver_mysql(){
                  try {
            Class.forName("com.mysql.jdbc.Driver"); // 2005 version
            setEnlace(DriverManager.getConnection(url_Servidor));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }
    void Setdriver_access(){
            try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String myDB = "jdbc:odbc:Driver={Microsoft Access Driver (*.mdb)};DBQ="+url_Servidor;
            setEnlace(DriverManager.getConnection(myDB, "", ""));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
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
}
