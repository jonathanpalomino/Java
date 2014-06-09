/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.carga;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JONATHAN
 */
public class InsertComplejo {

    public static void main(String args[]) {
        InputStream input = null;
        try {
            String url = "jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
            String user = "TRON2000";
            String password = "TRON2000";
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, user, password);
            String doc = "D:\\EXPO_OPM3.pptx";
            input = new FileInputStream(doc);
            String sql = "{call P_CARGA_BLOB(?,?,?)}";
            CallableStatement callableStatement = connection.prepareCall(sql);
            
            callableStatement.setBinaryStream(3, input);
            callableStatement.setInt(1, 1);
            callableStatement.setString(2, "llllll");
            callableStatement.execute();
            callableStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
