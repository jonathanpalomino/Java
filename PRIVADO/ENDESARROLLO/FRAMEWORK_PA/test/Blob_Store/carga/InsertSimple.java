/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.carga;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JONATHAN
 */
public class InsertSimple {

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

            PreparedStatement pstmt = connection.prepareStatement("insert into TEST_BLOB values (?,?,?)");
            pstmt.setBinaryStream(3, input);
            pstmt.setInt(1, 1);
            pstmt.setString(2, "hhh");
            pstmt.execute();
        } catch (Exception ex) {
            Logger.getLogger(InsertSimple.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(InsertSimple.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
