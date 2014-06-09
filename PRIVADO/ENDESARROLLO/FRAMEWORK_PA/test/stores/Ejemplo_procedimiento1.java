/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package stores;

import java.sql.Connection;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.jdbc.OracleTypes;
import palomino.base_datos.coneccion.Conector_Oracle;
import palomino.base_datos.stores.Parametro_IN;
import palomino.base_datos.stores.Parametro_INOUT;
import palomino.base_datos.stores.Parametro_OUT;
import palomino.base_datos.stores.Procedimiento;

/**
 *
 * @author synccon
 */
public class Ejemplo_procedimiento1  extends Procedimiento{
public static final String NOMBRE_CURSOR="c";
public static final String NOMBRE_PROCEDIMIENTO="TR";

    public Ejemplo_procedimiento1() {
        super(NOMBRE_PROCEDIMIENTO);
        String iDeptno = "7777";
        Integer iDeptno1 = 3;
        addParametro(new Parametro_IN("a",Types.VARCHAR,iDeptno));
        addParametro(new Parametro_OUT(NOMBRE_CURSOR,OracleTypes.VARCHAR));
        addParametro(new Parametro_INOUT("999", 10, OracleTypes.INTEGER));
        System.out.println(OracleTypes.INTEGER);
        //addParametro(new Parametro_OUTOUT("b", iDeptno1, OracleTypes.NUMBER));
    }


    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Connection conecccion = null;
        try {
            conecccion = Conector_Oracle.getConecccion();
        } catch (Exception ex) {
            Logger.getLogger(Ejemplo_procedimiento1.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Ejemplo_procedimiento1 obj = new Ejemplo_procedimiento1();
            obj.execute(conecccion);
System.out.println("ejecuta");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
