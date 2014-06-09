/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.herramientas.variables_entorno;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.GregorianCalendar;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import palomino.gui.controles.PBarraEstado;

/**
 *
 * @author synccon
 */
public class Herramientas {

    public static final String SEP = System.getProperties().getProperty("file.separator");

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

    static String ponMascaraFecha(String paramString1, String paramString2) {
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

    public static String ponFormatoImporte(String paramString, int paramInt) {
        return ponFormatoImporte(paramString, paramInt, true);
    }

    public static String ponFormatoImporte(String paramString, int paramInt, boolean paramBoolean) {
        if ((paramString == null) || (paramString == "")) {
            return "";
        }

        String str = paramString.replace(Global.getSepDecimal(), '.');

        NumberFormat localNumberFormat = NumberFormat.getNumberInstance(Global.getLocal());
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
    public void Centrar_Pantalla(Frame ab){
        ab.setLocationRelativeTo(null);
    }
    public void Sobre_todo(Frame ab,boolean es){
        ab.setAlwaysOnTop(es);
    }
    public static boolean moverDirectorio(String paramString1, String paramString2) {
        String[] arrayOfString = null;
        File localFile1 = new File(paramString1);
        File localFile2 = new File(paramString2);

        if (localFile1.isDirectory()) {
            if (localFile2.exists()) {
                if (!localFile2.isDirectory()) {
                    localFile2.delete();
                    localFile2.mkdir();
                }
            } else {
                localFile2.mkdir();
            }
        }

        arrayOfString = localFile1.list();
        if (arrayOfString == null) {
            return true;
        }

        for (int i = 0; i < arrayOfString.length; i++) {
            File localFile3 = new File(paramString1 + SEP + arrayOfString[i]);
            File localFile4 = new File(paramString2 + SEP + arrayOfString[i]);
            if (localFile3.isDirectory()) {
                moverDirectorio(paramString1 + SEP + arrayOfString[i], paramString2 + SEP + arrayOfString[i]);
            } else {
                if (localFile4.exists()) {
                    localFile4.delete();
                }
                if (localFile3.renameTo(localFile4)) {
                    continue;
                }
                System.err.println("DOCAUT(200) - Error al mover archivo");
            }
        }
        return true;
    }

    public static void copiarArchivos(File paramFile1, File paramFile2)
            throws IOException {
        File localFile = new File(paramFile2.getAbsolutePath());
        localFile.mkdirs();
        if (!paramFile1.exists()) {
            PBarraEstado.setMensajeError("Herramientas(254) - No encuentro " + paramFile1.getPath());
        }
        if (paramFile2.exists()) {
            paramFile2.delete();
        }
        RandomAccessFile localRandomAccessFile1 = new RandomAccessFile(paramFile1, "r");
        RandomAccessFile localRandomAccessFile2 = new RandomAccessFile(paramFile2, "rw");
        byte[] arrayOfByte = new byte[(int) localRandomAccessFile1.length()];
        localRandomAccessFile1.read(arrayOfByte);
        localRandomAccessFile2.write(arrayOfByte);
        localRandomAccessFile1.close();
        localRandomAccessFile2.close();
    }

    public static String[] ordenaVector(Vector paramVector, int paramInt, boolean paramBoolean) {
        return ordenaVector(paramVector, paramInt, paramBoolean, ',');
    }

    public static String[] ordenaVector(Vector paramVector, int paramInt, boolean paramBoolean, char paramChar) {
        if ((paramVector == null) || (paramVector.size() == 0)) {
            return null;
        }
        Vector localVector1 = (Vector) paramVector.elementAt(0);
        int i = localVector1.size();
        int j = paramVector.size();
        String[] arrayOfString = new String[j];
        int k = 0;
        while (paramVector.size() > 0) {
            int m = paramVector.size();
            Object localObject1 = "";
            int n = -1;
            Vector localObject0;
            for (int i1 = 0; i1 < m; i1++) {
                localObject0 = (Vector) paramVector.elementAt(i1);
                String str = (String) ((Vector) localObject0).elementAt(paramInt);
                if (n == -1) {
                    localObject1 = str;
                    n = i1;
                } else if (paramBoolean) {
                    if (((String) localObject1).compareTo(str) >= 0) {
                        continue;
                    }
                    localObject1 = str;
                    n = i1;
                } else {
                    if (((String) localObject1).compareTo(str) <= 0) {
                        continue;
                    }
                    localObject1 = str;
                    n = i1;
                }

            }

            Vector localVector2 = (Vector) paramVector.elementAt(n);
            Object localObject2 = new StringBuffer();
            for (int i2 = 0; i2 < i; i2++) {
                ((StringBuffer) localObject2).append(localVector2.elementAt(i2));
                if (i2 < i) {
                    ((StringBuffer) localObject2).append(" " + paramChar);
                }
            }
            arrayOfString[k] = ((StringBuffer) localObject2).toString();
            paramVector.removeElementAt(n);
            k++;
        }
        return arrayOfString;
    }

    public static boolean esNumerico(String paramString) {
        for (int i = 0; i < paramString.length(); i++) {
            if ((i == 0) && (paramString.charAt(i) == '-')) {
                continue;
            }
            if (paramString.charAt(i) == Global.getSepDecimal()) {
                continue;
            }

            if (!Character.isDigit(paramString.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static int contiene(String paramString1, String paramString2) {
        int i = paramString1.indexOf(paramString2);
        if (i > -1) {
            if (i > 0) {
                if (paramString1.charAt(i - 1) > '!') {
                    return -1;
                }
            }
            if (i + paramString2.length() < paramString1.length()) {
                if (paramString1.charAt(i + paramString2.length()) > '!') {
                    return -1;
                }
            }
            return i + paramString2.length() + 1;
        }
        return -1;
    }

    public static String rTrim(String paramString) {
        if ((paramString == null) || (paramString.equals(""))) {
            return "";
        }
        paramString = "#" + paramString;
        paramString = paramString.trim().substring(1);
        return paramString;
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

    public static int cuentaPosicion(String paramString, char paramChar, int paramInt) {
        int i = 0;
        for (int j = 0; j < paramString.length(); j++) {
            if (paramString.charAt(j) != paramChar) {
                continue;
            }
            i++;
            if (i == paramInt) {
                return j;
            }
        }

        return 0;
    }

    public static char[] aArreglo(String cadena_origen) {
        return cadena_origen.toCharArray();
    }

    

    public static String Obtener_ruta_archivo(String archivo) {
        File fichero = new File(archivo);
        return fichero.getAbsolutePath();
    }

    public static File[] Listar_unidades() {
        return File.listRoots();
    }

    public static int digito_verificador_RUT(String RUT) {
        int M = 0, S = 1;
        int T = Integer.parseInt(RUT);
        int digito;
        for (; T != 0; T /= 10) {
            S = (S + T % 10 * (9 - M++ % 6)) % 11;
        }
        String abc = (char) (S != 0 ? S + 47 : 75) + "";
        digito = Integer.parseInt(abc);
        return digito;
    }

    public static boolean esRUT_Valido(Object rut) throws Exception {
        Object[] array = null;
        int rutSumado = 0;
        boolean is = false;

        if (rut.toString().length() == 9) {
            array = new Object[8];
            int b = 1;

            for (int i = 0; i <= 7; i++) {
                array[i] = rut.toString().substring(i, b);
                b++;
            }
            array = invertir_arreglo(array);

            int a = 2;
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt((String) array[i]) * a;
                rutSumado += Integer.parseInt(String.valueOf(array[i]));
                if (a == 7) {
                    a = 1;
                }
                a++;
            }

            int resto = rutSumado % 11;
            String Digito = String.valueOf(11 - resto);

            for (int i = 0; i < array.length; i++) {
                Object object = array[i];
            }

            if (Digito.equals("11")) {
                Digito = "0";
            }
            if (Digito.equals("10")) {
                Digito = "K";
            }

            String digitoIngresado = rut.toString().substring(8, 9);

            if (digitoIngresado.equals(Digito)) {
                is = true;
            } else {
                is = false;
            }

        } else if (rut.toString().length() == 8) {
            array = new Object[7];
            int b = 1;

            for (int i = 0; i <= 6; i++) {
                array[i] = rut.toString().substring(i, b);
                b++;
            }
            array = invertir_arreglo(array);

            int a = 2;
            for (int i = 0; i < array.length; i++) {
                array[i] = Integer.parseInt((String) array[i]) * a;
                rutSumado += Integer.parseInt(String.valueOf(array[i]));
                if (a == 7) {
                    a = 1;
                }
                a++;
            }

            int resto = rutSumado % 11;
            String Digito = String.valueOf(11 - resto);

            for (int i = 0; i < array.length; i++) {
                Object object = array[i];
            }

            if (Digito.equals("11")) {
                Digito = "0";
            }
            if (Digito.equals("10")) {
                Digito = "K";
            }

            String digitoIngresado = rut.toString().substring(7, 8);

            if (digitoIngresado.equals(Digito)) {
                is = true;
            } else {
                is = false;
            }
        } else {
            throw new Exception("Error interno");
        }
        return is;
    }

    public static Object[] invertir_arreglo(Object[] array) {
        Object[] invertir_int = new Object[array.length];
        int maximo = array.length;

        for (int i = 0; i < array.length; i++) {
            Object j = array[maximo - 1];
            invertir_int[maximo - 1] = array[i];
            maximo--;
        }
        return invertir_int;
    }

    public static Dimension getResolucion_pantalla() {
        Toolkit t = Toolkit.getDefaultToolkit();
        return Toolkit.getDefaultToolkit().getScreenSize();
    }

    public static void Vaciar_archivo(File archivo) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            bw.write("");
            bw.close();
        } catch (IOException ex) {
            Logger.getLogger(Herramientas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Herramientas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static boolean Comparar_files_texto(String file1, String file2) throws FileNotFoundException, IOException {
        boolean iguales = false;
        boolean comparacion = false;
        String sCadena1, sCadena2;
        FileReader fr1 = new FileReader(file1);
        FileReader fr2 = new FileReader(file2);

        BufferedReader bf1 = new BufferedReader(fr1);
        BufferedReader bf2 = new BufferedReader(fr2);

        sCadena1 = bf1.readLine();
        sCadena2 = bf2.readLine();

        while ((sCadena1 != null) && (sCadena2 != null) && iguales) {

            if (!sCadena1.equals(sCadena2)) {
                iguales = false;
            }

            sCadena1 = bf1.readLine();
            sCadena2 = bf2.readLine();
        }
        if ((iguales) && (sCadena1 == null) && (sCadena2 == null)) {
            comparacion = true;
        } else {
            comparacion = false;
        }

        return comparacion;
    }

    public static boolean esBisiesto(Integer anio) {
        boolean result;
        GregorianCalendar calendar = new GregorianCalendar();
        if (calendar.isLeapYear(anio)) {
            result=true;
        } else {
            result=false;
        }
        return result;
    }
}
