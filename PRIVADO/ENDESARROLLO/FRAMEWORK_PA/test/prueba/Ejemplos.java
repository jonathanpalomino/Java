/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import palomino.base_datos.stores.gestores.Unido;



/**
 *
 * @author synccon
 */
public class Ejemplos {
static Unido obj = new Unido();
static String valor1="abc";
static String valor2="hhh";
static int valor3=125;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*obj.setFuncionValidacion("?:=PAQ.MK_EMP");
        
        obj.addParametros(valor1, "IN", 0, 3);
        obj.addParametros(valor2, "IN", 1, 3);
        obj.addParametros(valor3, "IN", 2, 3);
        obj.setTipoDatoRetorno(Types.INTEGER);
        obj.lanzaValidacionOracle();
        
        obj.setFuncionValidacion("PAQ.ABC");
        obj.addParametros(valor1, "IN", 0, 3);
        obj.addParametros(valor2, "OUT", 1, 3);
        obj.addParametros(valor3, "OUT", 2, 3);
        obj.lanzaValidacionOracle();*/

        obj.setFuncionValidacion("ABC_CURSOR");
        obj.addParametros(valor1, "IN", 0, 4);
        obj.addParametros(valor2, "IN OUT", 1, 4);
        obj.addParametros(OracleTypes.CURSOR, "OUT", 2, 4);
        obj.addParametros(OracleTypes.CURSOR, "OUT", 3, 4);
        obj.lanzaValidacionOracle();
        /*ResultSet rs=obj.getCursorDefecto();
        try {
            while (rs.next()){
                   System.out.print(rs.getObject(1).toString()+" ");
                   System.out.print(rs.getObject(2).toString()+" ");
                   System.out.print(rs.getObject(3).toString()+" ");
                   System.out.print(rs.getObject(4).toString()+" ");
                   System.out.print(rs.getObject(5).toString()+" ");
                   System.out.print(rs.getObject(6).toString()+" ");
                   System.out.println(); 
               }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplos.class.getName()).log(Level.SEVERE, null, ex);
        }*/

        for(int i = 0;i<obj.getCursores().size();i++){
            ResultSet rs1= (ResultSet)(obj.getCursores().get(i));
            try {
            while (rs1.next()){
                   System.out.print(rs1.getObject(1).toString()+" ");
                   System.out.print(rs1.getObject(2).toString()+" ");
                   System.out.print(rs1.getObject(3).toString()+" ");
                   System.out.print(rs1.getObject(4).toString()+" ");
                   System.out.print(rs1.getObject(5).toString()+" ");
                   System.out.print(rs1.getObject(6).toString()+" ");
                   System.out.println(); 
               }
            rs1.close();
            System.out.println("---------------"); 
        } catch (SQLException ex) {
            Logger.getLogger(Ejemplos.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
    }

}
