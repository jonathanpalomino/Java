package palomino.herramientas.variables_entorno;

import java.util.Hashtable;
import java.util.Locale;

public class Global {

    static String sFormatoFecha = "dd-MM-yyyy";
    private static char sepDecimal = ',';
    private static char sepMillar = '.';
    private static Locale localPais = new Locale("ES", "PE");
    private static Hashtable[] tablaGlobales = new Hashtable[4];
    static int iSesionActiva = -1;
    private static Hashtable vParametros;

    public static String getFormatoFecha() {
        return sFormatoFecha;
    }

    public static char getSepDecimal() {
        return sepDecimal;
    }

    public static Locale getLocal() {
        if ((getVariable("SEPDECIMALINST") != null) && (getVariable("SEPDECIMALINST").equals("."))) {
            localPais = Locale.US;
            sepDecimal = '.';
            sepMillar = ',';
        } else {
            localPais = new Locale("ES", "PE");
            sepDecimal = ',';
            sepMillar = '.';
        }

        return localPais;
    }

    public static String getCodigoMensaje(String codigo, String txt_mensaje) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static String getVariable(String pNombre) {

        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }
        return (String) tablaGlobales[iSesionActiva].get(pNombre.toUpperCase());
    }

    public static void setVariablesValidacion(Hashtable vParametros) {
        Global.vParametros=vParametros;
    }
    public static Hashtable getVariablesValidacion(){
        return vParametros;
    }
}