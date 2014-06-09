//**************************************************************************
// Nombre......: TSimulacion.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un objeto que contiene todos los datos
// ............: referentes a la simulaci�n del escenario actual.
// Fecha.......: 27/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.escenario;

import simMPLS.escenario.*;
import simMPLS.interfaz.simulador.*;
import simMPLS.electronica.reloj.*;
import simMPLS.electronica.recolectorsimulacion.*;

/**
 * Esta clase implementa un objeto que almacena los datos globales de la
 * simulaci�n.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TSimulacion {

    /**
     * Crea una nueva instancia de TEscenario
     * @param e Escenario al que pertenecen los datos de la simulaci�n.
     * @since 1.0
     */
    public TSimulacion(TEscenario e) {
        escenarioPadre = e;
        recolector = new TRecolectorSimulacion();
        duracion = 500;
        paso = 1;
    }

    /**
     * Este m�todo permite establecer el panel de simulaci�n en el cual se realizar� la
     * simulaci�n.
     * @since 1.0
     * @param ps El panel de simulaci�n.
     */    
    public void ponerPanelSimulacion(JPanelSimulacion ps) {
        recolector.ponerPanelSimulacion(ps);
    }
    
    /**
     * Este m�todo reinicia los atributos de la clase dej�ndolos como reci�n iniciados
     * por el constructor.
     * @since 1.0
     */    
    public void reset() {
        recolector.reset();
    }
    
    /**
     * Este m�todo permite establecer la duraci�n total de la simulaci�n.
     * @param d Duraci�n total de la simulaci�n en nanosegundos.
     * @since 1.0
     */    
    public void ponerDuracion(long d) {
        this.duracion = d;
    }
    
    /**
     * Este m�todo permite establecer la duraci�n del paso de simulaci�n.
     * @param p Duraci�n del paso de simulaci�n en nanosegundos.
     * @since 1.0
     */    
    public void ponerPaso(long p) {
        this.paso = p;
    }
    
    /**
     * Este m�todo permite obtener la duraci�n total de la simulaci�n-
     * @return Duraci�n total de la simulaci�n, en nanosegundos.
     * @since 1.0
     */    
    public long obtenerDuracion() {
        return this.duracion;
    }
    
    /**
     * Este m�todo permite obtener la duraci�n del paso de simulaci�n.
     * @return Duraci�n del paso de simulaci�n en nanosegundos.
     * @since 1.0
     */    
    public long obtenerPaso() {
        return this.paso;
    }
    
    /**
     * Este m�todo serializa la instancia, convirti�ndola en un texto que se puede
     * almacenar en disco.
     * @return Un texto que representa a la instancia actual.
     * @since 1.0
     */    
    public String serializarParametrosTemporales() {
        String serializada = "#Temporizacion#";
        serializada += this.duracion+"#";
        serializada += this.paso+"#";
        return serializada;
    }

    /**
     * Este m�todo deserializa un objeto TSimulacion previamente serializado,
     * reconstruy�ndolo en memoria.
     * @param pt El objeto de tipo TSimulaci�n serializado.
     * @return TRUE, si se ha conseguido serializar correctamente. FALSE en caso contrario.
     * @since 1.0
     */    
    public boolean deserializarParametrosTemporales(String pt) {
        String valores[] = pt.split("#");
        if (valores.length != 4) {
            return false;
        }
        this.duracion = Integer.valueOf(valores[2]).longValue();
        this.paso = Integer.valueOf(valores[3]).longValue();
        return true;
    }
    
    /**
     * Este m�todo permite acceder directamente al recolector de eventos de simulaci�n.
     * @return Recolector de eventos de simulaci�n el escenario.
     * @since 1.0
     */    
    public TRecolectorSimulacion obtenerRecolector() {
        return recolector;
    }

    private long duracion;
    private long paso;
    
    private TEscenario escenarioPadre;
    private TRecolectorSimulacion recolector;
}
