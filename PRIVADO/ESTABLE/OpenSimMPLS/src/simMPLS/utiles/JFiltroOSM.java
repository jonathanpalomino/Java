package simMPLS.utiles;

import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Esta clase implementa un filtro de ficheros que se usar� en los cuadros de
 * di�logo de abrir y guardar archivos. Corresponde a los archivos *.OSM de
 * escenario de Open SimMPLS 1.0.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JFiltroOSM extends FileFilter {
    
    /**
     * Este m�todo es el constructor de la clase. Implementa un nuevo filtro para los
     * fichero *.OSM.
     * @since 1.0
     */
    public JFiltroOSM() {
    }
    
    /**
     * Este m�todo acepta un fichero que debe ser analizado para saber si se debe
     * mostrar en los di�logos o no.
     * @param f Fichero (enviado por el di�logo abrir/cerrar).
     * @return true, si el fichero se debe mostrar (pasa el filtro). false en caso contrario.
     * @since 1.0
     */
    public boolean accept(File f) {
        if (!f.isDirectory()) {
            String extension = this.getExtension(f);
            if (extension != null) {
                if (extension.equals(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JSelectorFicheros.ExtensionOsm"))) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return true;
        }
        return false;
    }
    
    private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        
        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        return ext;
    }
    
    /**
     * Este m�todo permite obtener una descripci�n detallada del fichero que pase el
     * filtro.
     * @return Descripci�n detallada del fichero que pasa el filtro. Se mostrar� en el di�logo
     * abrir/cerrar.
     * @since 1.0
     */
    public String getDescription() {
        return java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JSelectorFicheros.DescripcionOSM");
    }
}
