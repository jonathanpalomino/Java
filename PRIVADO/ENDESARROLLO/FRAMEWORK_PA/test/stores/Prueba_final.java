/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import palomino.base_datos.stores.gestores.Unido;

/**
 *
 * @author JONATHAN
 */
public class Prueba_final {

    OutputStream os = null;
    Integer valor = 1;

    public Prueba_final() {
        Unido.getConeccion(); //Inicia la coneccion unica (SID)
        /*try {
         os = new FileOutputStream("E:\\fichero2.pptx");
         } catch (FileNotFoundException ex) {
         Logger.getLogger(Prueba_final.class.getName()).log(Level.SEVERE, null, ex);
         }
         Unido gen = new Unido();
         gen.setFuncionValidacion("?:=RECUPERA_BLOB");
         gen.addParametros(os, "OUT", 0, 2);
         gen.addParametros(valor, "IN", 1, 2);
         boolean estado = gen.lanzaValidacionOracle();
         System.out.println(estado);*/

        /*Unido abc = new Unido();
        Unido val2 = new Unido();
        Unido val3 = new Unido();
        Unido val4 = new Unido();
        val3.setValor(456);
        //val2.setValor("ggg");
        val2.setText("uuuu");
        val4.setValor("ddd");
        abc.setValor(666);
        val2.setFuncionValidacion("TR");
        val2.addParametros(val2, "IN", 0, 3);
        val2.addParametros(val4, "OUT", 1, 3);
        val2.addParametros(abc, "OUT", 2, 3);
        val2.lanzaValidacionOracle();
        System.out.println(val4.getValor());
        System.out.println(abc.getValor());*/
        
        Unido abc = new Unido();
        Unido abc1 = new Unido();
        Unido abc2 = new Unido();
        //abc.setValor("");
        //abc2.setValor(1);
        abc.setFuncionValidacion("PRUEBA");
        abc.addParametros("", "IN", 0, 3);
        abc.addParametros(abc, "OUT", 1, 3);
        abc.addParametros(abc2, "IN OUT", 2, 3);
        abc.lanzaValidacionOracle();
        System.out.println(abc.getValor()+" -- "+abc2.getValor());
    }

    public static void main(String args[]) {
        new Prueba_final();
        /*
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
            }*/
    }
}
