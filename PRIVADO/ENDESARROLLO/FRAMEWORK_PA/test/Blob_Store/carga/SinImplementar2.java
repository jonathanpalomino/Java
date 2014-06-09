/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.carga;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author JONATHAN
 */
public class SinImplementar2 {
static InputStream io ;
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user = "TRON2000";
        String password = "TRON2000";
        FileInputStream fileInputStream = null ;
        File resume =null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, user, password);
            //LLamado del procedimiento al estilo pl/sql
            String sql = "{call P_CARGA_BLOB(?,?,?)}";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setObject(1, 2);
            callableStatement.setObject(2, "EXPO_OPM3.pptx");


            //callableStatement.setInt(1, 2);
            //callableStatement.setString(2, "EXPO_OPM3.pptx");

            /*File fi = new File("D:\\EXPO_OPM3.pptx");

             FileInputStream fis = new FileInputStream(fi);

             byte[] zipped = new byte[(int) fi.length()];
             fis.read(zipped);
             BLOB blob = BLOB.createTemporary(connection, true, BLOB.DURATION_SESSION);
             OutputStream blob_os = blob.getBinaryOutputStream();
             blob_os.write(zipped);
             blob_os.flush();*/
            try {
                 resume = new File("D:\\EXPO_OPM3.pptx");
                byte[] fileContent = new byte[(int) resume.length()];
                try {
                    fileInputStream = new FileInputStream(resume);

                    fileInputStream.read(fileContent);
                    //fileInputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            //callableStatement.setObject(3, myBlob);
           // callableStatement.setObject(3, OracleTypes.BLOB);
            callableStatement.setBlob(3, fileInputStream,(int)resume.length());
            //callableStatement.setBinaryStream(3, fileInputStream,resume.length());
            // callableStatement.setBlob(3, blob);

            callableStatement.execute();
            callableStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
