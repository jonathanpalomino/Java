/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author JONATHAN
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

abstract class ModeloBD{
       private String driver="com.mysql.jdbc.Driver";
       private String urlBD="jdbc:mysql://localhost:3306/";
       private String bdNomb="bd_registro";
       private String login="root";
       private String pass="";
       Statement state=null;
       Connection con=null;

       public ModeloBD() {
               try {
                       Class.forName(driver);
                       con=DriverManager.getConnection(urlBD+bdNomb,login,pass);
                       state=con.createStatement();
               } catch (ClassNotFoundException e) {
                       System.out.println("Drivers no encontrados");
               } catch (SQLException e) {
                       System.out.println("Error de Conexión con Base de Datos: " +
                                       bdNomb);
               }
       }

       protected boolean ejecutarUpdate(String sql){
               try {
                       state.executeUpdate(sql);
                       return true;
               } catch (SQLException e) {
                       System.out.println("Error en la actualización");
               }
               return false;
       }

       protected ResultSet ejecutarQuery(String sql){
               ResultSet res=null;
               try {
                       res=state.executeQuery(sql);
               } catch (SQLException e) {
                       e.printStackTrace();
               }
               return res;
       }

       }
