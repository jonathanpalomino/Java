//**************************************************************************
// Nombre......: TESPaqueteConmutado.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un evento que se emitir� cuando un pa-
// ............: quete sea conmutado por un nodo de la red.
// Fecha.......: 27/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.escenario;

import simMPLS.protocolo.*;

/**
 * Esta clase implementa un evento que es usado para notificar que una etiqueta ha
 * sido eliminado (un LSP se ha disasociado).
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TESEtiquetaEliminada extends TEventoSimulacion {

    /**
     * Crea una nueva instancia de TESEtiquetaEliminada
     * @since 1.0
     * @param inst Instante de tiempo en el que se produjo el evento
     * @param emisor Nodo que gener� el evento
     * @param id identificador �nico para del evento.
     */
    public TESEtiquetaEliminada(Object emisor, long id, long inst) {
        super(emisor, id, inst);
    }

    /**
     * Este m�todo obtiene el subtipo del evento, si los hubiese.
     * @return El subtipo del evento.
     * @since 1.0
     */    
    public int obtenerSubtipo() {
        return super.ETIQUETA_ELIMINADA;
    }

    /**
     * Este m�todo obtiene el nombre del enlace que origin� el evento.
     * @return El nombre del enlace que origin� el evento.
     * @since 1.0
     */    
    public String obtenerNombre() {
        TElementoTopologia et = null;
        TEnlaceTopologia ent = null;
        TNodoTopologia nt = null;
        et = super.obtenerFuente();
        if (et.obtenerTipoElemento() == TElementoTopologia.ENLACE) {
            ent = (TEnlaceTopologia) et;
            return ent.obtenerNombre();
        } else if (et.obtenerTipoElemento() == TElementoTopologia.NODO) {
            nt = (TNodoTopologia) et;
            return nt.obtenerNombre();
        }
        return ("");
    }

    /**
     * Este m�todo obtiene un texto con el tipo de evento.
     * @return Un texto con el tipo de evento.
     * @since 1.0
     */    
    public String obtenerNombreTipo() {
        TElementoTopologia et = null;
        et = super.obtenerFuente();
        if (et.obtenerTipoElemento() == TElementoTopologia.ENLACE) {
            return ("Enlace ");
        } else if (et.obtenerTipoElemento() == TElementoTopologia.NODO) {
            return ("Nodo ");
        }
        return ("");
    }

    /**
     * Este m�todo explcia el evento en una l�nea de texto.
     * @return El texto explicando el evento.
     * @since 1.0
     */    
    public String toString() {
        String cad = "";
        cad += "[";
        cad += this.obtenerNombreTipo();
        cad += " ";
        cad += this.obtenerNombre();
        cad += "] ";
        cad += "ha eliminado una etiqueta";
        return(cad);
    }
}
