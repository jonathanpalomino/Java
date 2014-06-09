/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.carga;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import oracle.sql.BLOB;

/**
 *
 * @author JONATHAN
 */
public class SinImplementar {
static Blob myBlob;
    public static void main(String[] args) {
        String url = "jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user = "TRON2000";
        String password = "TRON2000";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, user, password);
            //LLamado del procedimiento al estilo pl/sql
            String sql = "{call P_CARGA_BLOB(?,?,?)}";

            CallableStatement callableStatement = connection.prepareCall(sql);
            callableStatement.setObject(1, Types.NUMERIC);
            callableStatement.setObject(2, Types.VARCHAR);
            callableStatement.setObject(3, Types.BLOB);

            callableStatement.setInt(1, 2);
            callableStatement.setString(2, "EXPO_OPM3.pptx");

            File fi = new File("D:\\EXPO_OPM3.pptx");

            FileInputStream fis = new FileInputStream(fi);

            byte[] zipped = new byte[(int) fi.length()];
            fis.read(zipped);
            BLOB blob = BLOB.createTemporary(connection, true, BLOB.DURATION_SESSION);
            OutputStream blob_os = blob.getBinaryOutputStream();
            blob_os.write(zipped);
            blob_os.flush();
            callableStatement.setBlob(3, blob);

            callableStatement.execute();
            callableStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
