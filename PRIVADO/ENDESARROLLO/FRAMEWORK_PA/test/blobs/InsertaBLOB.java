/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package blobs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import oracle.jdbc.OraclePreparedStatement;
import oracle.sql.BLOB;

/**
 *
 * @author JONATHAN
 */
public class InsertaBLOB {

    public static void InsertarBLOB(Connection cn, String id_archivo, String path)
            throws SQLException, IOException {
        FileOutputStream fos = null;
        Statement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO TEST_BLOB values(?,?,?)";

        try {
            //--------------------------------------------------

            OraclePreparedStatement pst = null;

            pst = (OraclePreparedStatement) cn.prepareStatement(sql);
            pst.setString(1, "1");
            pst.setString(2, id_archivo);

            File fi = new File(path+""+id_archivo);

            FileInputStream fis = new FileInputStream(fi);

            byte[] zipped = new byte[(int) fi.length()];
            fis.read(zipped);


            BLOB blob = BLOB.createTemporary(cn, true, BLOB.DURATION_SESSION);

            OutputStream blob_os = blob.getBinaryOutputStream();
            blob_os.write(zipped);
            blob_os.flush();
            pst.setBlob(3, blob);

            int i = pst.executeUpdate();
            System.out.println("Registros insertados: " + i);
            cn.commit();
            pst.close();
            fis.close();

            //--------------------------------------------------

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
