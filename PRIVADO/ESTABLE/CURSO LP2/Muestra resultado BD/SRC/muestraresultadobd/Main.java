/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package muestraresultadobd;
/*
 * Ejemplo.java
 *
 * Programa de prueba para conexión a una base de datos de MySQL.
 * Presupone que el servidor de base de datos está arrancado, disponible,
 * en el puerto por defecto.
 * El usuario y password de conexión con la base de datos debe cambiarse.
 * En la base de datos se supone que hay una base de datos llamada ejemplo y que
 * tiene una tabla alumno con tres campos, de esta manera:
 * mysql> create database ejemplo;
 * mysql> use ejemplo;
 * mysql> create table alumno(Codigo char(4) primary key not null,Nombre  varchar(20), apellido varchar(50));
 *
 */

import java.sql.*;
public class Main {
    public static void main(String[] args) {

     //Se realiza todo el código de conexión, consulta y muestra de resultados.

        String password="12345";
        String usuario="root";
        String url="jdbc:mysql://localhost:3306/ejemplo";
        Connection conexion=null;
        // Se mete todo en un try por los posibles errores de MySQL
        try {
            // Se registra el Driver de MySQL
   Class.forName("com.mysql.jdbc.Driver");
      // Se obtiene una conexión con la base de datos. Hay que
      // cambiar el usuario "root" y la clave "la_clave" por las
      // adecuadas a la base de datos que estemos usando.
   conexion = DriverManager.getConnection(url,usuario,password);
      if (conexion !=null)
      {
          // Se crea un Statement, para realizar la consulta
           Statement st = conexion.createStatement();
           // Se realiza la consulta. Los resultados se guardan en el
           ResultSet resp  = st.executeQuery("SELECT * FROM alumno");
           // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while(resp.next())
            {
               System.out.println(resp.getObject("codigo") +"\t\t"+ resp.getObject("nombre")+"\t\t"+resp.getObject("apellido"));
            }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex)
        {
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex)
        {
        System.out.println(ex);
        }
    }

}


