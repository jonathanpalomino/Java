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
 public class MSSQL {

    private Contenedor abc;

    MSSQL(Contenedor aThis) {
        abc = aThis;
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
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, String usuario, String contrasena) {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo + ";User ID =" + usuario + ";Password =" + contrasena;
        abc.setUrl_Servidor(url_Servidor);
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
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, String usuario, String contrasena, int tiempo_limite) {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo + ";User ID =" + usuario + ";Password =" + contrasena + ";Connect Timeout=" + tiempo_limite;
        abc.setUrl_Servidor(url_Servidor);
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
    public void SetGestorMSSQL(String servidor, String instancia, int puerto, String catalogo, boolean seguridad_integrada) {
        String url_Servidor = "jdbc:sqlserver://" + servidor + "\\" + instancia + ":" + puerto + ";databaseName=" + catalogo + ";integratedSecurity =" + seguridad_integrada;
        abc.setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    /**
     * @param servidor_instanciapredeterminada A la direccion del servidor se
     * agrega automaticamente la instancia /SQLEXPRESS
     * @param catalogo Es la base de datos a acceder
     * @param seguridad_integrada especifica si quieres que se autentifique con
     * windows o sino tienes un usuario
     */
    public void SetGestorMSSQL(String servidor_instanciapredeterminada, String catalogo, boolean seguridad_integrada) {
        String url_Servidor = "jdbc:sqlserver://" + servidor_instanciapredeterminada + "\\SQLEXPRESS" + ";databaseName=" + catalogo + ";integratedSecurity =" + seguridad_integrada;
        abc.setUrl_Servidor(url_Servidor);
        SetDRIVER_MSSQL();
    }

    private void SetDRIVER_MSSQL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 2005 version
            abc.setEnlace(DriverManager.getConnection(abc.getUrl_Servidor()));
        } catch (com.microsoft.sqlserver.jdbc.SQLServerException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error de datos", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de coneccion", "Error general", JOptionPane.ERROR_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No se hallo la libreria", "Error general", JOptionPane.ERROR_MESSAGE);
        }
    }
}
