/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

import java.awt.Component;
import java.awt.Container;
import java.beans.Beans;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import paquete.Contenedores.TBarraHerramientas;
import paquete.TBarraEstado;
import ultimo.JPTextField;

/**
 *
 * @author JONATHAN
 */
public class Globales {

    private static boolean bIsCargando = false;
    private static boolean bIsServidor = false;
    static Hashtable htSugerencias = new Hashtable();
    static Hashtable htEtiquetas = new Hashtable();
    private static Hashtable[] tablaGlobales = new Hashtable[4];
    static int iSesionActiva = -1;
    static Vector globalAcumuladas = new Vector();
    static Hashtable htMensajes = null;
    static Container ultimaVentana = null;
    static TBarraEstado ultimaBarraEstado = null;
    static Component componenteAnterior = null;
    static Component componenteActual = null;
    static private char sepDecimal = ',';
    static private char sepMillar = '.';
    static String sFormatoFecha = "dd-MM-yyyy";
    private static char sepDecimalBase = '.';
    private static Locale localPais = new Locale("ES", "ES");

    public static boolean isServidor() {
        return bIsServidor;
    }

    public static boolean isCargando() {
        return bIsCargando;
    }

    /**
     * @deprecated
     */
    public static String leeCodigoTexto(String pCodigoModulo, int pCodigoTexto, String pTipo, String psFuncion) {
        return getCodigoTexto(pCodigoModulo, pCodigoTexto, pTipo, psFuncion);
    }

    public static String getCodigoTexto(String psCodigoModulo, int piCodigoTexto, String pTipo, String psFuncion) {
        int liTipo = 0;
        if (psFuncion.equals("leeEtiqueta")) {
            liTipo = 1;
        } else if (psFuncion.equals("leeSugerencia")) {
            liTipo = 2;
        }
        return getCodigoTexto(psCodigoModulo, piCodigoTexto, liTipo);
    }

    public static String getCodigoTexto(String psCodigoModulo, int piCodigoTexto, int piTipo) {
        switch (piTipo) {
            case 0:
                return getMensaje(piCodigoTexto);
            case 1:
                return getEtiqueta(psCodigoModulo, piCodigoTexto);
            case 2:
                return getSugerencia(psCodigoModulo, piCodigoTexto);
        }
        return "Error 1450";
    }
    ///////////////////////////////

    private static String getMensaje(int piCodigoTexto) {
        if (htMensajes == null) {
            String lsRuta = "Avisos" + getCodIdioma();
            if (!Beans.isDesignTime()) {
                if (!isServidor()) {
                    Herramientas.verificarObjeto(lsRuta + ".ser");
                }
            }
            htMensajes = (Hashtable) Herramientas.desSerializar(lsRuta, true);
            if (htMensajes == null) {
                return "*Mensaje " + piCodigoTexto;
            }
        }
        try {
            if (!htMensajes.containsKey(piCodigoTexto)) {
                return "-Mensaje " + piCodigoTexto;
            }
            return (String) htMensajes.get(piCodigoTexto);
        } catch (NullPointerException e) {
            Herramientas.trazar("Error en carga de Mensajes.", true);
        }
        return "Error 1";
    }

    private static String getSugerencia(String psCodigoModulo, int piCodigoTexto) {
        if (!htSugerencias.containsKey(psCodigoModulo)) {
            String lsRuta = "Sugerencias" + getCodIdioma();
            if (!Beans.isDesignTime()) {
                if (!isServidor()) {
                    Herramientas.verificarObjeto(lsRuta + ".ser");
                }
            }
            Hashtable lhtSugerencias = (Hashtable) Herramientas.desSerializar(lsRuta, true);
            if (lhtSugerencias == null) {
                return "Sugerencia " + psCodigoModulo + "," + piCodigoTexto;
            }
            htSugerencias.put(psCodigoModulo, lhtSugerencias);
        }
        return (String) ((Hashtable) htSugerencias.get(psCodigoModulo)).get(piCodigoTexto);
    }

    public static String leeCodigoMensaje(String psCodigoModulo, String psCodigoTexto) {
        String lsTexto = "";
        try {
            int liCodigoTexto = new Integer(psCodigoTexto).intValue();
            lsTexto = leeCodigoTexto(psCodigoModulo, liCodigoTexto, "Avisos", "leeMensaje");
            if (lsTexto == null) {
                lsTexto = "Error " + liCodigoTexto;
            }
        } catch (NumberFormatException e) {
            TBarraEstado.setMensajeError("Globales - leeCodigoMensaje - Error al convertir codigo de error a entero " + psCodigoTexto);
        }
        return lsTexto;
    }

    /**
     * @deprecated
     */
    public static String leeCodigoMensaje(String pCodigoModulo, int pCodigoTexto) {
        return leeCodigoTexto(pCodigoModulo, pCodigoTexto, "Avisos", "leeMensaje");
    }

    public static Component getPadre(Component pComponente) {
        return getPadre(pComponente, false);
    }

    public static Component getPadre(Component pComponente, boolean parcial) {
        Component padre = pComponente;
        while ((padre != null)
                && ((!parcial) || (!(padre instanceof JInternalFrame)))
                && (!(padre instanceof JDialog)) && (!(padre instanceof JFrame))) {
            padre = padre.getParent();
        }
        return padre;
    }

    public static Container getUltimaVentana() {
        return ultimaVentana;
    }

    public static void setUltimaVentana(Container pUltimaVentana) {
        ultimaVentana = pUltimaVentana;
    }

    private static String getEtiqueta(String psCodigoModulo, int piCodigoTexto) {
        if (isServidor()) {
            return psCodigoModulo + piCodigoTexto;
        }

        String lsRuta = "Etiquetas" + getCodIdioma();
        if (!htEtiquetas.containsKey(psCodigoModulo)) {
            if (!Beans.isDesignTime()) {
                if (!isServidor()) {
                    Herramientas.verificarObjeto(lsRuta + ".ser");
                }
            }
            Hashtable lhtEtiquetas = (Hashtable) Herramientas.desSerializar(lsRuta, true);
            if (lhtEtiquetas == null) {
                System.out.println("desSerializar vacio-- ");
                return "*Etiqueta " + psCodigoModulo + "," + piCodigoTexto;
            }
            htEtiquetas.put(psCodigoModulo, lhtEtiquetas);
        }
        return (String) ((Hashtable) htEtiquetas.get(psCodigoModulo)).get(piCodigoTexto);

    }

    public static String[] getCodigoTextoHotKey(String pCodigoModulo, int pCodigoTexto) {
        String[] aux = {"", ""};
        if (isServidor()) {
            aux[0] = (pCodigoModulo + pCodigoTexto);
            aux[1] = (pCodigoModulo + pCodigoTexto);
            return aux;
        }

        String etiqueta = getEtiqueta(pCodigoModulo, pCodigoTexto);

        if (etiqueta == null) {
            if (pCodigoTexto != 0) {
                Herramientas.trazar("No existe la etiqueta: " + pCodigoModulo + "," + pCodigoTexto, true);
            }
            return aux;
        }

        int index = etiqueta.indexOf("&");
        if (index > -1) {
            String hotKey = String.valueOf(etiqueta.charAt(index + 1));////
            StringBuffer buff = new StringBuffer(etiqueta);
            try {
                buff.deleteCharAt(index);
            } catch (Throwable localThrowable) {
            }

            String etiquetaSinHotKey = buff.toString();
            aux[0] = etiquetaSinHotKey;
            aux[1] = hotKey;
        } else {
            aux[0] = etiqueta;
        }
        return aux;

    }

    public static String getCodigoSugerencia(String pCodigoModulo, int pCodigoTexto) {
        return leeCodigoTexto(pCodigoModulo, pCodigoTexto, "Etiquetas", "leeSugerencia");
    }

    /**
     * @deprecated
     */
    public static String leeCodigoSugerencia(String pCodigoModulo, int pCodigoTexto) {
        return leeCodigoTexto(pCodigoModulo, pCodigoTexto, "Etiquetas", "leeSugerencia");
    }

    public static void setUltimaBarraEstado(TBarraEstado pUltimaBarraEstado) {
        ultimaBarraEstado = pUltimaBarraEstado;
    }

    public static TBarraEstado getUltimaBarraEstado() {
        return ultimaBarraEstado;
    }

    public static String nvl(String psOrigen) {
        if (psOrigen == null) {
            return new String("");
        }
        if (psOrigen.trim().toLowerCase().equals("null")) {
            return new String("");
        }
        return psOrigen.trim();
    }

    public static String getCodIdioma() {
        if (getVariable("CODIDIOMA") == null) {
            String lsIdioma = Locale.getDefault().getLanguage().toUpperCase();
            setVariable("CODIDIOMA", lsIdioma);
        }
        return getVariable("CODIDIOMA");
    }

    public static synchronized void setSesionActiva(int piSesion) {
        if (isCargando()) {
            return;
        }
        if (piSesion < 0) {
            piSesion = 0;
        }
        iSesionActiva = piSesion;

        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }
    }

    public static void setVariable(String psNombre, String psValor) {
        if (iSesionActiva < 0) {
            setSesionActiva(0);
        }
        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }

        tablaGlobales[iSesionActiva].put(psNombre.toUpperCase(), psValor);
    }

    public static void setVariable(String psNombre, int piValor) {
        if (iSesionActiva < 0) {
            setSesionActiva(0);
        }
        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }
        tablaGlobales[iSesionActiva].put(psNombre.toUpperCase(), piValor);
    }

    public static String getVariable(String pNombre) {
        if (iSesionActiva < 0) {
            setSesionActiva(0);
        }
        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }
        return (String) tablaGlobales[iSesionActiva].get(pNombre.toUpperCase());
    }

    public static void setObjetoGeneral(String pNombre, Object o) {
        setVariableGeneral(pNombre, getValor(o));
    }

    public static void setVariableGeneral(String pNombre, String pValor) {
        setVariable(pNombre, pValor);
        Object[] variableValor = {pNombre, pValor};
        globalAcumuladas.addElement(variableValor);
    }

    public static void setVariableGeneral(String pNombre, int piValor) {
        setVariable(pNombre, piValor);
        Object[] variableValor = {pNombre, piValor};
        globalAcumuladas.addElement(variableValor);
    }

    public static String getValor(Object o) {
        if (o == null) {
            return "";
        }

        if ((o instanceof JPTextField)) {
            if (((JPTextField) o).getTipoDato().equals("FECHA")) {
                String s = Herramientas.quitaMascaraFecha(((JPTextField) o).getText());
                return s;
            }
            return ((JPTextField) o).getText();
        }
        if ((o instanceof String)) {
            return (String) o;
        }

        return "";
    }

    public static String getCodigoMensaje(String psCodigoTexto) {
        return leeCodigoMensaje("trn", psCodigoTexto);
    }

    public static String getCodigoMensaje(int iCodigoMensaje) {
        return leeCodigoMensaje("trn", iCodigoMensaje);
    }

    public static String getCodigoMensaje(String psCodigoModulo, String psCodigoTexto) {
        return leeCodigoMensaje(psCodigoModulo, psCodigoTexto);
    }

    ///////////////
    public static void borraVariableGeneral(String pNombre) {
        /*try {
         SapCon.getConexion();
         SapTronObj.ejecutaSentencia(
         "begin  trn_k_global.borra_variable(?); end;",
         new String[]{"IN"},
         new String[]{pNombre},
         true);
         } catch (Exception e) {
         TBarraEstado.setMensajeError(e.toString());
         }*/
        if (iSesionActiva < 0) {
            setSesionActiva(0);
        }
        if (tablaGlobales[iSesionActiva] == null) {
            tablaGlobales[iSesionActiva] = new Hashtable();
        }

        if (tablaGlobales[iSesionActiva].containsKey(pNombre.toUpperCase())) {
            tablaGlobales[iSesionActiva].remove(pNombre.toUpperCase());
        }
    }

    public static String getVariableGeneral(String pNombre) {
        Object[] aValores = {"", pNombre};
        /*if (SapCon.getConexion() != null) {
         String cCadena = "";
         String cSentencia = "begin ? := trn_k_global.devuelve (?); end;";
         String[] aFormatos = {"OUT", "IN"};
         try {
         SapCon.getConexion();
         aValores = SapTronObj.ejecutaSentencia(cSentencia, aFormatos, aValores, true);
         } catch (Exception localException) {
         }
         }*/
        return (String) aValores[0];
    }

    public static void enviaVariables() {
        if (globalAcumuladas.isEmpty()) {
            return;
        }

        for (int x = 0; x < globalAcumuladas.size(); x++) {
            Object[] arrayOfObject = (Object[]) globalAcumuladas.elementAt(x);
        }
        /*if (SapCon.getConexion() != null) {
         try {
         SapCon.getConexion();
         SapTronObj.asignaGlobales(globalAcumuladas);
         } catch (Exception e) {
         System.err.println("Globales - enviaVariables(1489) Error: " + e);
         }

         globalAcumuladas.removeAllElements();
         }*/
    }

    /**
     * @deprecated
     */
    public static String leeCodigoTexto(String pCodigoModulo, int pCodigoTexto) {
        return getCodigoTexto(pCodigoModulo, pCodigoTexto);
    }

    public static String getCodigoTexto(String pCodigoModulo, int pCodigoTexto) {
        return getCodigoTextoHotKey(pCodigoModulo, pCodigoTexto)[0];
    }

    public static Component getComponenteActual() {
        return componenteActual;
    }

    public static Component getComponenteAnterior() {
        return componenteAnterior;
    }

    public static void setComponenteActual(Component pComponenteActual) {
        componenteAnterior = componenteActual;
        componenteActual = pComponenteActual;
        if (componenteActual != null) {
            Vector lBarra = TBarraHerramientas.getBarrasHerramientas((Container) getPadre(componenteActual));
            for (int x = 0; x < lBarra.size(); x++) {
                ((TBarraHerramientas) lBarra.elementAt(x)).refrescarBarra(componenteActual);
            }
        }
        setUltimaVentana((Container) getPadre(pComponenteActual));
        Herramientas.setCursorNormal();
    }

    /**
     * Método que devuelve el código de separación para millares en función del
     * idioma. <br>El carácter de separación depende del idioma de la aplicación
     * que se haya definido previamente con
     * <code>ponCodIdioma</code>
     *
     * @return caracter de separación (. o ,)
     * @see #setCodIdioma(java.lang.String)
     * @see #recuperaDatosBasicos()
     */
    static public char getSepMillar() {
        return sepMillar;
    }

    /**
     * Método que devuelve el código de separación para decimales que se
     * mostrará por pantalla en función del idioma. <br>El carácter de
     * separación depende del idioma de la aplicación que se haya definido
     * previamente con
     * <code>ponCodIdioma</code>
     *
     * @return caracter de separación (, o .)
     * @see #setCodIdioma(java.lang.String)
     * @see #recuperaDatosBasicos()
     */
    static public char getSepDecimal() {
        return sepDecimal;
    }

    static public int getNumDec() {
        return 0;

    }

    public static String getFormatoFecha() {
        return sFormatoFecha;
    }

    public static char getSepDecimalBase() {
        return sepDecimalBase;
    }
      public static Locale getLocal()
  {
    if ((getVariable("SEPDECIMALINST") != null) && (getVariable("SEPDECIMALINST").equals(".")))
    {
      localPais = Locale.US;
      sepDecimal = '.';
      sepMillar = ',';
    }
    else
    {
      localPais = new Locale("ES", "ES");
      sepDecimal = ',';
      sepMillar = '.';
    }

    return localPais;
  }
}
