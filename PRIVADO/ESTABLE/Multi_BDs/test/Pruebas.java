/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Pluri_bd.Contenedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author JONATHAN
 */
public class Pruebas{

    static Contenedor ob = new Contenedor();
    static Connection enlace;

    public static void main(String[] args) {
        /*ob.getCONECTOR_SQL().SetGestorSQL("localhost","instancia",1433,"catalogo","usuario","constraseña");
        System.out.println(ob.getUrl_Servidor());
        ob.getCONECTOR_SQL().SetGestorSQL("localhost","instancia",1433,"catalogo","usuario","constraseña", 120);
        System.out.println(ob.getUrl_Servidor());
        ob.getCONECTOR_SQL().SetGestorSQL("localhost","instancia",1433,"catalogo",true);
        System.out.println(ob.getUrl_Servidor());*/
        
        //ob.getCONECTOR_SQL().SetGestorSQL("localhost1", "SG_COMANDERSQL", true);
        ob.getCONECTOR_ORACLE().SetGestorORACLE("localhost",1521, "BASEORACLE", "JONATHAN","123456");
        //ob.getCONECTOR_SQL().SetGestorSQL("localhost","SQLEXPRESS",1433,"SG_COMANDERSQL","usuario","constraseña");
        System.out.println(ob.getUrl_Servidor());
        enlace = ob.getEnlace();
        CargarRazas();


/*
        ob.getCONECTOR_Mysql().SetGestorMYSQL("localhost","base_datos","root","123456",3306);
        System.out.println(ob.getUrl_Servidor());
        enlace = ob.getEnlace();
        CargarRazas();





        ob.getCONECTOR_access().SetGestorACCES("C:\\SG\\SG_COMANDERSQL.mdb");
        System.out.println(ob.getUrl_Servidor());
        enlace = ob.getEnlace();
        CargarRazas();*/

    }

    private static void CargarRazas() {
        if (enlace != null) {
            try {
                String url_consulta = "SELECT nomb_raza FROM Razas";
                Statement st = enlace.createStatement();
                ResultSet resp = st.executeQuery(url_consulta);
                while (resp.next()) {
                    System.out.println(resp.getObject(1));
                }
            }
            catch (SQLException ex) {
            }
        }
    }
}
