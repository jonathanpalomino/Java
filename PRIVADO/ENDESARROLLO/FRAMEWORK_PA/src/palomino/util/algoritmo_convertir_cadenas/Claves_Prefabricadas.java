/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.util.algoritmo_convertir_cadenas;

import java.security.NoSuchAlgorithmException;

/**
 *
 * @author JONATHAN
 */
public class Claves_Prefabricadas {

    public Claves_Prefabricadas(String BSSID, String ESSID) {
    }
        public String LlamarClave(String BSSID, String ESSID) {
            String resultado;
        boolean error_essid = false;
        boolean error_bssid = false;
        String error = "";
        if (ESSID.contains("JAZZTEL")) {
            if (ESSID.length() != 12) {
                error_essid = true;
            }
        } else if (ESSID.contains("WLAN")) {
            if (ESSID.length() != 9) {
                error_essid = true;
            }
        } else {
            error_essid = true;
        }
        String cabecera = ESSID.trim().toUpperCase();
        cabecera = cabecera.substring(cabecera.length() - 4, cabecera.length());
        String bssid = BSSID.trim().replace(":", "").replace("-", "").toUpperCase();

        //Resolver aca
        for (char c : bssid.toCharArray()) {
            if ((!Character.isDigit(c)) && (c != 'A') && (c != 'B') && (c != 'C') && (c != 'D') && (c != 'E') && (c != 'F')) {
                error_bssid = true;
            }
        }
        //Fin de resolver aca

        if (bssid.length() != 12) {
            error_bssid = true;
        }
        if (error_essid) {
            error = "¿Metiste bien el nombre de la red?";
        }
        if ((!error_essid) && (error_bssid)) {
            error = "¿Metiste bien la MAC?";
        }
        if ((!error_bssid) && (!error_essid)) {
            String bssidp = bssid.substring(0, 8);
            try {
                String md5sum = MD5Sum.computeSum("bcgbghgg" + bssidp + cabecera + bssid);
                resultado=md5sum.substring(0, 20);

            } catch (NoSuchAlgorithmException ex) {
                resultado="Opss!!! No pudimos calcular la clave. Intentalo de nuevo";
            }
        } else {
            resultado=error;
        }
        
        return resultado;
    }
}
