//**************************************************************************
// Nombre......: TESPaqueteEnTr�nsito.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un evento que se emitir� cuando un pa-
// ............: quete circule por el interior de un enlace.
// Fecha.......: 27/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.escenario;

import simMPLS.protocolo.*;

/**
 * Esta clase implementa un evento que ser� usado para indecar que un paquete est�
 * circulando por un enlace.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TESPaqueteEnTransito extends TEventoSimulacion {

    /**
     * Crea una nueva instancia de TESPaqueteEnTransito
     * @since 1.0
     * @param inst Instante de tiempo en el que se produjo el evento.
     * @param pt Porcentaje de reccorido del enlace que lleva recorrido el paquete por el cual se
     * genero el evento.
     * @param emisor Enlace que gener� el evento.
     * @param id Identificador unico del evento.
     * @param tipoPaquete Tipo del paquete (de qu� trafico es) al que se refiere el evento.
     */
    public TESPaqueteEnTransito(Object emisor, long id, long inst, int tipoPaquete, long pt) {
        super(emisor, id, inst);
        tipoP = tipoPaquete;
        porcentajeTransito = pt;
    }

    /**
     * Este m�todo obtiene el porcentaje del enalce que ha recorrido ya el paquete al
     * que se refiete el evento.
     * @since 1.0
     * @return El porcentaje de recorrido del paquete.
     */    
    public long obtenerPorcentajeTransito() {
        return this.porcentajeTransito;
    }
    
    /**
     * Este m�todo obtiene el tipo de paquete al que se refiere el evento.
     * @return El tipo el paquete.
     * @since 1.0
     */    
    public int obtenerTipoPaquete() {
        return tipoP;
    }

    /**
     * Este m�todo obtiene el subtipo del evento, si los hubiese.
     * @return El subtipo del evento.
     * @since 1.0
     */    
    public int obtenerSubtipo() {
        return super.PAQUETE_EN_TRANSITO;
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
     * Este m�todo obtiene una representaci�n textual del tipo de paquete.
     * @return El tipo de paquete, en texto.
     * @since 1.0
     */    
    public String obtenerNombreTipoPaquete() {
        String strTipo = "";
        switch (tipoP) {
            case TPDU.IPV4: {
                strTipo = "IPv4";
                break;
            }
            case TPDU.IPV4_GOS: {
                strTipo = "IPv4 con GoS";
                break;
            }
            case TPDU.MPLS: {
                strTipo = "MPLS";
                break;
            }
            case TPDU.MPLS_GOS: {
                strTipo = "MPLS con GoS";
                break;
            }
            case TPDU.TLDP: {
                strTipo = "LDP";
                break;
            }
            case TPDU.GPSRP: {
                strTipo = "GPSRP";
                break;
            }
        }
        return(strTipo);
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
        cad += "transporta un paquete ";
        cad += this.obtenerNombreTipoPaquete();
        return(cad);
    }

    private long porcentajeTransito;
    private int tipoP;
}
