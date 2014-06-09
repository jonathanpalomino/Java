/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blobs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JONATHAN
 */
public class RecuperadorBLOB {

    public static void RecuperarBLOB(Connection cn, String idBLOB, String path)
            throws SQLException, IOException {
        FileOutputStream fos = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "select ID, "
                + " NOMBRE, "
                + " FICHERO "
                + "from TEST_BLOB "
                + "WHERE ID = '" + idBLOB + "' ";
        try {
            st = cn.createStatement();
            rs = st.executeQuery(sql);
            if (rs.next()) {
                String pathname =
                        path + "\\" + rs.getString("NOMBRE");
                File file = new File(pathname);
                fos = new FileOutputStream(file);
                Blob bin = rs.getBlob("FICHERO");
                InputStream inStream = bin.getBinaryStream();
                int size = (int) bin.length();
                byte[] buffer = new byte[size];
                int length = -1;
                while ((length = inStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, length);
                }
            }
        } catch (IOException ioe) {
            throw new IOException(ioe.getMessage());
        } finally {
            if (fos != null) {
                fos.close();
            }
            if (rs != null) {
                rs.close();
            }
            rs = null;
            st = null;
        }
    }
}