/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.base_datos.oracle;

import java.sql.DriverManager;
import palomino.base_datos.coneccion.Contenedor;

/**
 *
 * @author JONATHAN
 */
public final class ORACLE {
private Contenedor contenedor;
    public ORACLE(Contenedor aThis) {
        setContenedor(aThis);
    }
    private void setDiverOracle() throws Exception{
            Class.forName("oracle.jdbc.OracleDriver");
            getContenedor().setEnlace(DriverManager.getConnection(getContenedor().getUrl_Servidor()));
    }

    /**
     * @param servidor  Direccion del servidor
     * @param SID Es el servicio o sid
     * @param puerto Especifica el numero del puerto si es diferente a 1433
     * @param usuario Especifique el usuario de autentificacion facilitado por ORACLE O un Administrador
     * @param contrasena Especifique la contraseña del usuario facilitado por ORACLE O un Administrador
     */
    public void SetGestorORACLE(String servidor, int puerto, String SID, String usuario, String contrasena) throws Exception {
        //jdbc:oracle:thin:usuario/clave@host:puerto:sid
        String url_Servidor;
        url_Servidor = "jdbc:oracle:thin:" + usuario + "/" + contrasena + "@" + servidor + ":" + puerto + ":" + SID;
        getContenedor().setUrl_Servidor(url_Servidor);
        setDiverOracle();
    }

    /**
     * @return the contenedor
     */
    public Contenedor getContenedor() {
        return contenedor;
    }

    /**
     * @param contenedor the contenedor to set
     */
    public void setContenedor(Contenedor contenedor) {
        this.contenedor = contenedor;
    }
}
