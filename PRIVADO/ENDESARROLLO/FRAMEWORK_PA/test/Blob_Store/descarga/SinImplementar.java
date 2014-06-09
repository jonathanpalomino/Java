/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Blob_Store.descarga;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author JONATHAN
 */
public class SinImplementar {

    public static void main(String[] args) {

        String url = "jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user = "TRON2000";
        String password = "TRON2000";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection connection = DriverManager.getConnection(url, user, password);
            //LLamado del procedimiento al estilo pl/sql
            String sql = "{?=call RECUPERA_BLOB(?)}";

            CallableStatement callableStatement = connection.prepareCall(sql);
            Integer valor=1;
            //Parametros de entrada
            callableStatement.registerOutParameter(1, OracleTypes.BLOB);
            callableStatement.setObject(2, valor);
            //callableStatement.setInt(2, valor);

            callableStatement.execute();

            FileOutputStream fos = null;
            File file = new File("E:\\fichero.pptx");
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(SinImplementar.class.getName()).log(Level.SEVERE, null, ex);
            }
            oracle.sql.BLOB blob = (oracle.sql.BLOB) callableStatement.getObject(1);
            InputStream inStream = blob.getBinaryStream();

            try {
                int size = (int) blob.length();
                byte[] buffer = new byte[size];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException ex) {
                        Logger.getLogger(SinImplementar.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
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
