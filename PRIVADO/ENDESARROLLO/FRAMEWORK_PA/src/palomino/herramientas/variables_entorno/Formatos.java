/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.herramientas.variables_entorno;

import java.util.StringTokenizer;

/**
 *
 * @author JONATHAN
 */
public class Formatos {

    public static String FormatoHoras(double tiempo_en_segundos) {
        double ensegundos = tiempo_en_segundos; //tiene 2 decimales
        int minuto = 0, horas = 0, minutofinal = 0, segundos = 0, segundofinal = 0;
        double enminutos = (int) (ensegundos / 60 * 100) / 100.0;
        minuto = (int) enminutos;
        if (minuto >= 60) {
            horas = minuto / 60;
            minutofinal = minuto - (horas * 60);
        } else {
            minutofinal = minuto;
        }
        segundos = (int) Math.round((enminutos - minuto) * 60);
        segundofinal = segundos;
        return DevolverFormateado(horas, minutofinal, segundofinal);
    }

    public static String DevolverFormateado(int horas, int minutofinal, int segundofinal) {
        String hora, minutos, segundo;
        hora = String.valueOf(horas);
        minutos = String.valueOf(minutofinal);
        segundo = String.valueOf(segundofinal);
        if (hora.length() == 1) {
            hora = "0" + hora;
        }
        if (minutos.length() == 1) {
            minutos = "0" + minutos;
        }
        if (segundo.length() == 1) {
            segundo = "0" + segundo;
        }
        return (hora + ":" + minutos + ":" + segundo);

    }
    public static String retiraTexto(String texto_origen, String valor_retirar) {
        String valor = null;
        if (texto_origen.length() == 0) {
            valor = "";
        } else {
            valor = texto_origen.replace(valor_retirar, "");
        }

        return valor;
    }

    public static String invierteCadena(String cadena_origen) {
        String sCadenaInvertida = null;
        for (int x = cadena_origen.length() - 1; x >= 0; x--) {
            sCadenaInvertida = sCadenaInvertida + cadena_origen.charAt(x);
        }
        return sCadenaInvertida;
    }
    public static String retiraEspacios(String texto_origen) {
        String valor = null;
        valor = texto_origen.replace("", "");
        return valor;
    }

    public static String palabraMasGrande(String texto_origen) {

        String palabraMasGrande = "";
        int palabraMasGrandeSize = 0;
        StringTokenizer stTexto = new StringTokenizer(texto_origen);
        int palabras = stTexto.countTokens();

        for (int x = 0; x < palabras; x++) {
            texto_origen = stTexto.nextToken();
            if (texto_origen.length() > palabraMasGrandeSize) {
                palabraMasGrande = texto_origen;
                palabraMasGrandeSize = texto_origen.length();
            }
        }
        return palabraMasGrande;
    }

    public static int contar_palabras(String texto_origen) {
        StringTokenizer st = new StringTokenizer(texto_origen);
        return st.countTokens();
    }
}
