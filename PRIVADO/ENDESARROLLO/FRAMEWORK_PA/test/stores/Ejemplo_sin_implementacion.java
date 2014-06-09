/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

/**
 *
 * @author synccon
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.OracleTypes;
 
/**
 * @author dgomez@vortexbird.com
 */
public class Ejemplo_sin_implementacion {
 
    public static void main(String[] args) {
 /*
        String url="jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user="TRON2000";
        String password="TRON2000";
 
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connection=DriverManager.getConnection(url,user,password);
                //LLamado del procedimiento al estilo pl/sql
                String sql="begin ProGetPrueba1(?,?); end;";
 
                CallableStatement callableStatement=connection.prepareCall(sql);
 
                //Parametros de entrada
                callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
                callableStatement.setLong(2, 1L);
 
                callableStatement.execute();
                //Se obtiene el cursor en forma de ResultSe
                ResultSet rs = (ResultSet)callableStatement.getObject(1);
                while (rs.next()){
                    System.out.println(rs.getLong("CODIGO"));
                    System.out.println(rs.getString("NOMBRE"));
                }
                rs.close();
                callableStatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                //Imprime el mensaje de la exception lanzada en pl/sql si el valor es diferente de 1
                e.printStackTrace();
            }*/
            
            String url="jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user="TRON2000";
        String password="TRON2000";
 
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                Connection connection=DriverManager.getConnection(url,user,password);
                //LLamado del procedimiento al estilo pl/sql
                String sql="begin PRUEBA(?,?,?); end;";
 
                CallableStatement callableStatement=connection.prepareCall(sql);

                callableStatement.setObject(1, OracleTypes.VARCHAR);
                callableStatement.registerOutParameter(2, OracleTypes.VARCHAR);
                callableStatement.registerOutParameter(3, OracleTypes.VARCHAR);
                callableStatement.execute();
                System.out.println(OracleTypes.VARCHAR);
                System.out.println(callableStatement.getObject(2));
                System.out.println(callableStatement.getObject(3));
                callableStatement.close();
                connection.close();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                //Imprime el mensaje de la exception lanzada en pl/sql si el valor es diferente de 1
                e.printStackTrace();
            }
    }
}
