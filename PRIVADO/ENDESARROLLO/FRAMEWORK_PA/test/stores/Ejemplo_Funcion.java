/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Types;
import palomino.base_datos.stores.Funcion;
import palomino.base_datos.stores.Parametro_IN;

/**
 *
 * @author synccon
 */
public class Ejemplo_Funcion extends Funcion{
String P_NAME ="P_NAME";
String P_HIREDATE ="P_HIREDATE";
String P_DEPTNO ="P_DEPTNO";
    public Ejemplo_Funcion(String ename,Date hiredate,int deptno) {
        super("MK_EMP");
        setTipo_Retorno(Types.INTEGER);
        
        addParametro(new Parametro_IN(P_NAME, Types.VARCHAR, ename));
        addParametro(new Parametro_IN(P_HIREDATE, Types.DATE, hiredate));
        addParametro(new Parametro_IN(P_DEPTNO, Types.INTEGER, new Integer(deptno)));
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        String url="jdbc:oracle:thin:@SERVER-JONATHAN:1521:SERVERORACLE";
        String user="TRON2000";
        String password="TRON2000";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn=DriverManager.getConnection(url,user,password);
            
            Ejemplo_Funcion obj = new Ejemplo_Funcion("Pablo", new Date(System.currentTimeMillis()), 10);
            obj.execute(conn);
            System.out.println("---------- "+obj.getValor_Retorno_EsInt());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
