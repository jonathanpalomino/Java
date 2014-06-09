/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import oracle.jdbc.OracleTypes;
import palomino.base_datos.stores.gestores.Unido;

/**
 *
 * @author synccon
 */
public class Nuevo_Esquema {
Unido abc=new Unido();

Integer valor11=2;
String valor2="ddd";
String valor1="ggg";
    public Nuevo_Esquema() {
        abc.setFuncionValidacion("?:=MK_EMP");
        
        abc.addParametros(valor11, "OUT", 0, 4);
        abc.addParametros(valor2, "IN", 1, 4);
        abc.addParametros(valor1, "IN", 2, 4);
        abc.addParametros(valor11, "IN", 3, 4);
        abc.lanzaValidacionOracle();
        System.out.println(valor11+"  --  "+abc.getMensajeError());
        
        /*abc.setFuncionValidacion("GET_EMAILS");
        abc.addParametros(OracleTypes.CURSOR, "OUT", 0, 2);
        abc.addParametros(valor1, "IN", 1, 2);
        abc.lanzaValidacionOracle();
        
        ResultSet rs = abc.getCursorDefecto();
        try {
              System.out.println(" --- "+rs.getRow());
            while(rs.next()){
               System.out.print(rs.getObject(1).toString()+" ");
                System.out.print(rs.getObject(2).toString()+" ");
                System.out.print(rs.getObject(3).toString()+" ");
                System.out.print(rs.getObject(4).toString()+" ");
                System.out.print(rs.getObject(5).toString()+" ");
                System.out.print(rs.getObject(6).toString()+" ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Nuevo_Esquema.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }

    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Nuevo_Esquema();
    }
}
