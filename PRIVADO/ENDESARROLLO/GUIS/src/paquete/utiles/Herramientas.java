/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Stack;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import paquete.Contenedores.TDialog;

/**
 *
 * @author JONATHAN
 */
public class Herramientas {

    static Cursor cursorNormal = new Cursor(0);
    static Cursor cursorEspera = new Cursor(3);
    public static final String SEP = System.getProperties().getProperty("file.separator");
    public static final String HOME = System.getProperties().getProperty("user.home");
    public static final String USER = System.getProperties().getProperty("user.dir");
    private static boolean bEstaTrazaActiva = false;
    public static final int PANTALLA = 0;
    private static Stack cuentaDialogo = new Stack();

    private static void setTipoCursor(Cursor paramCursor) {
        if (Globales.getUltimaVentana() != null) {
            Globales.getUltimaVentana().setCursor(paramCursor);
        }
    }

    public static void setCursorEspera() {
        setTipoCursor(cursorEspera);
        if (Globales.getUltimaBarraEstado() != null) {
            Globales.getUltimaBarraEstado().iniciaReloj();
        }
    }

    public static void setCursorNormal() {
        setTipoCursor(cursorNormal);
        if (Globales.getUltimaBarraEstado() != null) {
            Globales.getUltimaBarraEstado().paraReloj();
        }
    }

    public static String nvl(String paramString) {
        if (paramString == null) {
            return new String("");
        }
        if (paramString.trim().toLowerCase().equals("null")) {
            return new String("");
        }
        return paramString.trim();

    }

    public static String normalizaRuta(String paramString) {
        char c1 = SEP.charAt(0);
        char c2 = '/';
        if (c1 == '/') {
            c2 = '\\';
        }
        return paramString.replace(c2, c1);
    }

    private static String nombreMin(String paramString) {
        if (paramString.lastIndexOf(SEP) < 0) {
            return paramString;
        }
        String str1 = paramString.substring(0, paramString.lastIndexOf(SEP));
        String str2 = paramString.substring(paramString.lastIndexOf(SEP)).toLowerCase();
        return str1 + str2;
    }

    public static String verificaImagen(String paramString) {
        String str = normalizaRuta(paramString);

        if (new File(str).exists()) {
            return str;
        }
        str = nombreMin(str);
        if (new File(str).exists()) {
            return str;
        }
        str = str.replace(' ', '_');
        if (new File(str).exists()) {
            return str;
        }
        str = str.replace('ñ', 'n');
        if (new File(str).exists()) {
            return str;
        }
        str = str.replace('Ñ', 'n');
        if (new File(str).exists()) {
            return str;
        }
        System.out.println("verificaImagen 2-- " + str);
        return null;
    }

    public static ImageIcon getImagenIcono(String paramString) {
        if (paramString.equals("")) {
            return null;
        }

        String str1 = getRutarepLocal() + SEP;
        String str2 = "images" + SEP;
        if (normalizaRuta(paramString).startsWith(normalizaRuta(str2))) {
            str2 = "";
        }
        String str3 = verificaImagen(str1 + str2 + paramString);
        if (str3 == null) {
            Properties localProperties = null;// = ConexionRepositorio.traerImagen(str2 + paramString);
            if (localProperties == null) {
                Trazar.error("No se encuentra Icono: " + str1 + " - " + str2 + " - " + paramString, "ATENCION");
                return new ImageIcon(str1 + str2 + "about.gif");
            }
            //descomprimeJar(localProperties);
        }
        str3 = verificaImagen(str1 + str2 + paramString);
        if (str3 == null) {
            return null;
        }

        return new ImageIcon(str3);
    }

    static int analizarContenido(String paramString) {
        if (paramString.indexOf("NoRouteToHostException") > -1) {
            return 0;
        }
        if (paramString.indexOf("Connection refused") > -1) {
            return 0;
        }
        if (paramString.indexOf("ORA-04068") > -1) {
            return 1;
        }
        return 0;
    }

    private static synchronized void fuerzaTraza() {
        throw new NullPointerException();
    }

    public static void setTrazar(boolean paramBoolean) {
        bEstaTrazaActiva = paramBoolean;
    }

    public static boolean isTrazar() {
        return bEstaTrazaActiva;
    }

    public static synchronized void trazar() {
        trazar(null, bEstaTrazaActiva);
    }

    public static synchronized void trazar(String paramString) {
        trazar(paramString, bEstaTrazaActiva);
    }

    public static synchronized void trazar(String paramString, boolean paramBoolean) {
        if (!paramBoolean) {
            Trazar.depurar(paramString, "TRAZA");
            return;
        }
        int i = analizarContenido(paramString);

        if (i > 0) {
            //mostrarMensaje(Globales.getCodigoMensaje("trn", "10190049"), 0, Globales.getUltimaVentana());
            switch (i) {
                case 0:
                    System.exit(0);
                    break;
                case 1:
                    // SapCon.desconexionSAP();
                    System.exit(0);
            }

        }

        String str1 = "";
        try {
            fuerzaTraza();
        } catch (NullPointerException localNullPointerException) {
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream localPrintStream = new PrintStream(localByteArrayOutputStream, true);
            localNullPointerException.printStackTrace(localPrintStream);
            str1 = localByteArrayOutputStream.toString();
            int j = str1.indexOf("trazar");
            if (j > -1) {
                j = str1.indexOf("at ", j) + 3;
                int k = str1.indexOf("\n", j + 1);
                String str2 = str1.substring(j, k - 1);
                str2 = str2.substring(0, str2.indexOf("(") + 1) + str2.substring(str2.indexOf(":") + 1);
                //Trazar.mensaje(str2, Globales.getCodUsr());
            }
        }
    }

    public static String formateaTexto(String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt) {
        String str1 = paramString;
        int i = 0;
        int j = 0;
        int k = 0;
        if (paramBoolean1) {
            str1 = paramString.toUpperCase();
            paramString = paramString.toUpperCase();
        }
        if (paramBoolean2) {
            FontMetrics localFontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(new Font("SansSerif", 0, 12));
            str1 = paramString;
            StringBuffer localStringBuffer = new StringBuffer("");
            j = localFontMetrics.stringWidth(".");
            i = localFontMetrics.stringWidth(str1 + ":");
            int m;
            if (i > paramInt) {
                m = 0;
                k = 0;
                String str2 = new String("");
                String str3 = null;
                for (int n = 0; n < str1.length(); n++) {
                    str3 = str2;
                    str2 = str2 + str1.substring(n, n + 1);
                    m = localFontMetrics.stringWidth(str2 + "...:");
                    if (m > paramInt) {
                        break;
                    }
                }
                str1 = str3 + "...:";
            } else {
                k = (paramInt - i) / j;
                for (m = 0; m < k; m++) {
                    localStringBuffer.append(".");
                }
                localStringBuffer.append(":");
                str1 = str1 + localStringBuffer.toString();
            }
        }
        return str1;
    }

    public static boolean verificarObjeto(String paramString) {
        System.out.println("verificarObjeto-- " + paramString);
        String str1 = getRutarepLocal() + SEP + "cache" + SEP + paramString.replace(SEP.charAt(0), '.');
        System.out.println("verificarObjeto- 2- " + str1);
        String str2 = "00000000";
        if (new File(str1).exists()) {
            FileInputStream localObject = null;
            byte[] arrayOfByte = null;
            try {
                localObject = new FileInputStream(str1);
                arrayOfByte = new byte[((FileInputStream) localObject).available()];
                ((FileInputStream) localObject).read(arrayOfByte);
                ((FileInputStream) localObject).close();
                str2 = new String(arrayOfByte).substring(0, 8);
            } catch (IOException localIOException) {
                System.err.println("Herramientas - VerificaClase - Error: " + localIOException.getMessage());
                return false;
            }
        }

        /* Object localObject = ConexionRepositorio.traerObjeto(paramString, str2);
         if (localObject != null) {
         descomprimeJar((Properties) localObject);
         return true;
         }*/
        return false;

    }

    public static String getRutarepLocal() {
        return USER;
    }

    public static Object desSerializar(String paramString) {
        return desSerializar(paramString, true);
    }

    public static Object desSerializar(String paramString, boolean paramBoolean) {
        FileInputStream localFileInputStream = null;
        ObjectInputStream localObjectInputStream = null;
        String str1 = "";
        if (paramBoolean) {
            str1 = getRutarepLocal() + SEP;
        }
        String str2 = str1 + paramString + ".ser";
        System.out.println("ruta total: " + str2);
        try {
            File localFile = new File(str2);
            if ((!localFile.exists()) || (!localFile.canWrite())) {
                System.out.println("no existe: ");
                return null;
            }
            localFileInputStream = new FileInputStream(str2);
            localObjectInputStream = new ObjectInputStream(localFileInputStream);
            Object localObject = localObjectInputStream.readObject();
            localObjectInputStream.close();
            localFileInputStream.close();

            return localObject;
        } catch (Exception localException1) {
            try {
                if (localFileInputStream != null) {
                    localFileInputStream.close();
                }
                if (localObjectInputStream != null) {
                    localObjectInputStream.close();
                }
            } catch (Exception localException2) {
            }

            System.err.println("Herramientas.desSerializar(2924) No devuelvo el objeto." + localException1.getMessage());
        }
        return null;
    }

    public static String quitaMascaraFecha(String paramString) {
        String str = "";
        for (int i = 0; i < paramString.length(); i++) {
            if (Character.isDigit(paramString.charAt(i))) {
                str = str + paramString.charAt(i);
            }
            if (!Character.isLetter(paramString.charAt(i))) {
                continue;
            }
            str = str + paramString.charAt(i);
        }

        return str;
    }

    public static Point getPosicionCentrado(Component paramComponent, TDialog paramTDialog) {
        return getPosicionCentrado(paramComponent, paramTDialog.getSize());
    }

    public static Point getPosicionCentrado(Component paramComponent, Dimension paramDimension) {
        return getPosicion(paramComponent, paramDimension, 0, 0);
    }

    public static Point getPosicionCentrado(Component paramComponent, TDialog paramTDialog, int paramInt1, int paramInt2) {
        return getPosicionCentrado(paramComponent, paramTDialog.getSize(), paramInt1, paramInt2);
    }

    public static Point getPosicionCentrado(Component paramComponent, Dimension paramDimension, int paramInt1, int paramInt2) {
        int i;
        int j;
        switch (paramInt1) {
            case 1:
                i = 0;
                j = paramInt2;
                break;
            case 2:
                j = 0;
                i = paramInt2;
                break;
            default:
                throw new IllegalArgumentException();
        }
        return getPosicion(paramComponent, paramDimension, i, j);
    }

    public static Point getPosicion(Component paramComponent, Dimension paramDimension, int paramInt1, int paramInt2) {
        if (((paramInt1 != 8) && (paramInt1 != 2) && (paramInt1 != 6) && (paramInt1 != 4) && (paramInt1 != 0)) || ((paramInt2 != 8) && (paramInt2 != 2) && (paramInt2 != 6) && (paramInt2 != 4) && (paramInt2 != 0))) {
            throw new IllegalArgumentException();
        }
        int i = paramComponent.getToolkit().getScreenSize().width;
        int j = paramComponent.getToolkit().getScreenSize().height - 26;

        Point localPoint1 = new Point(0, 0);
        Point localPoint2 = paramComponent.getLocationOnScreen();
        Dimension localDimension = paramComponent.getSize();
        Point localPoint3 = new Point();
        Point localPoint4 = new Point();

        paramDimension.height += 24;
        paramDimension.width += 8;

        int k = (paramInt2 == 2) || (paramInt2 == 4) ? 1 : 0;
        int m = (paramInt2 == 4) || (paramInt2 == 6) ? 1 : 0;
        int n = (paramInt1 == 2) || (paramInt1 == 4) ? 1 : 0;
        int i1 = (paramInt1 == 4) || (paramInt1 == 6) ? 1 : 0;

        if ((paramInt1 == 0) && (paramInt2 == 0)) {
            localPoint2.x += (localDimension.width - paramDimension.width) / 2;
            localPoint2.y += (localDimension.height - paramDimension.height) / 2;
        } else if (paramInt1 == 0) {
            localPoint2.x += (localDimension.width - paramDimension.width) / 2;
            localPoint1.y = (localPoint2.y + localDimension.height * m + paramDimension.height * (k - 1));
        } else if (paramInt2 == 0) {
            localPoint1.x = (localPoint2.x + localDimension.width * i1 + paramDimension.width * (n - 1));
            localPoint2.y += (localDimension.height - paramDimension.height) / 2;
        } else {
            localPoint1.x = (localPoint2.x + n * localDimension.width + (k - 1) * paramDimension.width);
            localPoint1.y = (localPoint2.y + i1 * localDimension.height + (m - 1) * paramDimension.height);
        }

        paramDimension.height -= 24;
        paramDimension.width -= 8;
        if (localPoint1.x < 0) {
            localPoint1.x = 0;
        } else if (localPoint1.x + paramDimension.width > i) {
            localPoint1.x = (i - paramDimension.width);
        }
        if (localPoint1.y < 0) {
            localPoint1.y = 0;
        } else if (localPoint1.y + paramDimension.height > j) {
            localPoint1.y = (j - paramDimension.height);
        }
        return localPoint1;
    }

    public static void iniciaCuentaDialogos() {
        cuentaDialogo.push("");
    }

    public static void terminaCuentaDialogos() {
        cuentaDialogo.pop();
    }

    public static int totalCuentaDialogos() {
        return cuentaDialogo.size() == 0 ? 0 : ((String) cuentaDialogo.peek()).length();

    }

    static void print(String padre_no_está_definido) {
        System.out.println(padre_no_está_definido);
    }

    public static String ponMascaraFecha(String paramString1, String paramString2) {
        String str1 = quitaMascaraFecha(paramString2);
        if (paramString1.equals("HH:mm")) {
            if (str1.length() < 4) {
                return "";
            }

            if (!validaHora(Integer.parseInt(str1.substring(0, 2)), Integer.parseInt(str1.substring(2)))) {
                return "";
            }
        }

        if (paramString1.equals("MM-yyyy")) {
            if (str1.length() < 6) {
                return "";
            }

            if (!validaFecha(Integer.parseInt(str1.substring(0, 2)), Integer.parseInt(str1.substring(2, 6)))) {
                return "";
            }
        }

        if (paramString1.equals("dd-MM-yyyy")) {
            if (str1.length() < 8) {
                return "";
            }

            if (!validaFecha(Integer.parseInt(str1.substring(0, 2)), Integer.parseInt(str1.substring(2, 4)), Integer.parseInt(str1.substring(4, 8)))) {
                return "";
            }
        }

        if (paramString1.equals("MM-dd-yyyy")) {
            if (str1.length() < 8) {
                return "";
            }

            if (!validaFecha(Integer.parseInt(str1.substring(2, 4)), Integer.parseInt(str1.substring(0, 2)), Integer.parseInt(str1.substring(4, 8)))) {
                return "";
            }

        }

        String str2 = "";
        int i = 0;
        for (int j = 0; (i < paramString1.length()) && (j < str1.length()); i++) {
            if (Character.isLetter(paramString1.charAt(i))) {
                str2 = str2 + str1.charAt(j++);
            } else {
                str2 = str2 + paramString1.charAt(i);
            }
        }
        return str2;
    }

    public static boolean validaHora(int paramInt1, int paramInt2) {
        int i = 60;
        int j = 60;

        return (paramInt1 >= 0) && (paramInt1 <= 23) && (paramInt2 >= 0) && (paramInt2 <= 60);
    }

    public static boolean validaFecha(int paramInt1, int paramInt2, int paramInt3) {
        int i;
        switch (paramInt2) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                i = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                i = 30;
                break;
            case 2:
                if (((paramInt3 % 4 == 0) && (paramInt3 % 100 != 0)) || (paramInt3 % 400 == 0)) {
                    i = 29;
                } else {
                    i = 28;
                }
                break;
            default:
                return false;
        }

        return (paramInt1 >= 1) && (paramInt1 <= i) && (paramInt3 >= 1900) && (paramInt3 <= 2100);
    }

    public static boolean validaFecha(int paramInt1, int paramInt2) {
        switch (paramInt1) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
                break;
            default:
                return false;
        }

        return (paramInt2 >= 1900) && (paramInt2 <= 2100);
    }

    public static String quitaFormatoImporte(String paramString, int paramInt) {
        if ((paramString == null) || (paramString == "")) {
            return "";
        }
        int i = 0;
        String str = new String("");

        for (int j = 0; j < paramString.length(); j++) {
            if (paramString.charAt(j) == Globales.getSepMillar()) {
                continue;
            }
            str = str + paramString.charAt(j);
        }

        if (paramInt == 1) {
            str = str.replace(Globales.getSepDecimal(), Globales.getSepDecimalBase());
        }
        return str;
    }

    public static String quitaFormatoImporte(String paramString, int paramInt, boolean paramBoolean) {
        boolean bool = paramBoolean;
        if ((paramString == null) || (paramString == "")) {
            return "";
        }
        int i = 0;

        String str1 = paramString;
        Object localObject = "";
        int j = 0;
        try {
            if (paramString.lastIndexOf(Globales.getSepDecimal()) != -1) {
                j = 1;
            }
            StringTokenizer localStringTokenizer = new StringTokenizer(str1, "" + Globales.getSepDecimal());
            String str2 = localStringTokenizer.nextToken();

            if (paramInt == 1) {
                str2 = quitaSepMillar(str2) + Globales.getSepDecimalBase();
            } else {
                str2 = quitaSepMillar(str2) + Globales.getSepDecimal();
            }
            localObject = str2;

            String str3 = localStringTokenizer.nextToken();
            localObject = str2;
            localObject = (String) localObject + str3;
        } catch (NoSuchElementException localNoSuchElementException) {
            if ((paramString.lastIndexOf(Globales.getSepMillar()) != -1) && (bool)) {
                localObject = quitaSepMillar(paramString.substring(0, paramString.lastIndexOf(Globales.getSepMillar()))) + Globales.getSepMillar() + paramString.substring(paramString.lastIndexOf(Globales.getSepMillar()) + 1);
            } else {
                localObject = quitaSepMillar(paramString);
            }
        }

        return (String) localObject;
    }

    private static String quitaSepMillar(String paramString) {
        String str = "";
        for (int i = 0; i < paramString.length(); i++) {
            if (paramString.charAt(i) == Globales.getSepMillar()) {
                continue;
            }
            str = str + paramString.charAt(i);
        }

        return str;
    }

    public static String ponFormatoImporte(String paramString, int paramInt) {
        return ponFormatoImporte(paramString, paramInt, true);
    }

    public static String ponFormatoImporte(String paramString, int paramInt, boolean paramBoolean) {
        if ((paramString == null) || (paramString == "")) {
            return "";
        }

        String str = paramString.replace(Globales.getSepDecimal(), '.');

        NumberFormat localNumberFormat = NumberFormat.getNumberInstance(Globales.getLocal());
        DecimalFormat localDecimalFormat = (DecimalFormat) localNumberFormat;

        if (paramBoolean) {
            localDecimalFormat.applyPattern("###,###" + ceros(paramInt));
        } else {
            localDecimalFormat.applyPattern("######" + ceros(paramInt));
        }
        localDecimalFormat.setMinimumFractionDigits(paramInt);
        localDecimalFormat.setMaximumFractionDigits(paramInt);
        try {
            str = localDecimalFormat.format(new Double(str).doubleValue());
        } catch (NumberFormatException localNumberFormatException) {
        }

        return str;
    }

    private static String ceros(int paramInt) {
        if (paramInt == 0) {
            return "";
        }
        String str = ".";
        for (int i = 0; i < paramInt; i++) {
            str = str + "#";
        }

        return str;
    }
}
