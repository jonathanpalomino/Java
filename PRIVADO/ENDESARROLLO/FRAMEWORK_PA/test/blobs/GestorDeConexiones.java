/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blobs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import oracle.jdbc.driver.OracleDriver;

/**
 *
 * @author JONATHAN
 */
public class GestorDeConexiones {

    private String user;
    private String password;
    private Connection conn = null;
    private boolean conectado = false;

    public GestorDeConexiones(String usr, String pwd) {
        user = usr;
        password = pwd;
    }

    public void closeConnection() throws SQLException {
        if (conectado) {
            conn.close();
        }
    }

    private void conectar() throws SQLException {
        String url;
        DriverManager.registerDriver(new OracleDriver());
        // url = "jdbc:oracle:oci:@";
        //url = "jdbc:oracle:thin:@//server-jonathan:1521/SERVERORACLE";
        url = "jdbc:oracle:thin:@server-jonathan:1521:SERVERORACLE";
        //url = "jdbc:oracle:oci:@ORACLEBD";
        conn = DriverManager.getConnection(url, user, password);
        System.out.println("Conexion correcta");
        conectado = true;
    }

    public Connection getConnection() throws SQLException {
        if (!conectado) {
            conectar();
        }
        return conn;
    }
}
