
import Pluri_bd.Contenedor;
import java.sql.Connection;

/**
 *
 * @author JONATHAN
 */
public class Pruebas2 {
static Contenedor variable = new Contenedor("MYSQL","4");
static Connection enlace;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        variable.getCONECTOR_Mysql().SetGestorMYSQL("free-mysql.BizHostNet.com", "1322958745", "1322958745", "777jonathan");
        System.out.println(variable.getUrl_Servidor());
        enlace = variable.getEnlace();
    }
}
