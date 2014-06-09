/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import stores.Ejemplo_Funcion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import palomino.base_datos.stores.Funcion;

/**
 *
 * @author synccon
 */
public class Example {
static Funcion abc;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@127.0.0.1:1521:SERVERORACLE";
        String user="TEST_ORACLE";
        String password="TEST_ORACLE";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn=DriverManager.getConnection(url,user,password);
            
            Ejemplo_Funcion obj = new Ejemplo_Funcion("Pablo", new Date(System.currentTimeMillis()), 10);
            abc = new Funcion("MK_EMP");
            
            
            obj.execute(conn);
            System.out.println("---------- "+obj.getValor_Retorno_EsInt());
        } catch (Exception e) {
        }
    }
}
