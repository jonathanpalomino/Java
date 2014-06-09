/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas_mysql;

import java.sql.*;

/**
 *
 * @author JONATHAN
 */
public class PRUEBAS_MYSQL {

    //variables
    private static Connection conexion;
    private static String bd="1322958745";
    private static String user="1322958745";
    private static String password="777jonathan";
    private static String host="free-mysql.BizHostNet.com:3306";
    private static String server="jdbc:mysql://"+host+"/"+bd;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //conectar
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server,user,password);

            System.out.println("Conexión a base de datos "+server+" ... OK");

        } catch (ClassNotFoundException ex) {
            System.out.println("Error cargando el Driver MySQL JDBC ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            System.out.println("Imposible realizar conexion con "+server+" ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //realizar consulta
        try {
            // Preparamos la consulta
            Statement s = conexion.createStatement();
            ResultSet rs = s.executeQuery ("select * from defensas");

            // Recorremos el resultado, mientras haya registros para leer, y escribimos el resultado en pantalla.
            while (rs.next())
            {
                System.out.println(
                        "ID: " +rs.getString(1) +
                        " tNombre: " + rs.getString (2)+
                        " tSexo: " + rs.getString(3)
                        );
            }
        } catch (SQLException ex) {
            System.out.println("Imposible realizar consulta ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        //desconectar
        try {
            conexion.close();
            System.out.println("Cerrar conexion con "+server+" ... OK");
        } catch (SQLException ex) {
            System.out.println("Imposible cerrar conexion ... FAIL");
            //Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
