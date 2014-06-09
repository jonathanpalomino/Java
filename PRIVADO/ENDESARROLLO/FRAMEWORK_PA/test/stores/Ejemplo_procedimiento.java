/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import palomino.base_datos.coneccion.Conector_Oracle;
import palomino.base_datos.stores.Parametro_IN;
import palomino.base_datos.stores.Parametro_OUT;
import palomino.base_datos.stores.Procedimiento;

/**
 *
 * @author synccon
 */
public class Ejemplo_procedimiento  extends Procedimiento{
public static final String NOMBRE_CURSOR=".";
public static final String NOMBRE_PROCEDIMIENTO="GET_EMAILS";

    public Ejemplo_procedimiento(String tipo) {
        super(NOMBRE_PROCEDIMIENTO);
        String iDeptno = new String(tipo);
        addParametro(new Parametro_OUT(NOMBRE_CURSOR,OracleTypes.CURSOR));
        addParametro(new Parametro_IN("",Types.VARCHAR,iDeptno));
    }

    public ResultSet getCURSOR(){
        return (ResultSet)getParametro(NOMBRE_CURSOR);
    }

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Connection conecccion = null;
        try {
            conecccion = Conector_Oracle.getConecccion();
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo_procedimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs=null;
        try {
            Ejemplo_procedimiento obj = new Ejemplo_procedimiento("EMISOR");
            obj.execute(conecccion);
            rs = obj.getCURSOR();
          
            while (rs.next()){
                //System.out.println(rs.getObject(0).toString());
                System.out.print(rs.getObject(1).toString()+" ");
                System.out.print(rs.getObject(2).toString()+" ");
                System.out.print(rs.getObject(3).toString()+" ");
                System.out.print(rs.getObject(4).toString()+" ");
                System.out.print(rs.getObject(5).toString()+" ");
                System.out.print(rs.getObject(6).toString()+" ");
                System.out.println();
            } 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
