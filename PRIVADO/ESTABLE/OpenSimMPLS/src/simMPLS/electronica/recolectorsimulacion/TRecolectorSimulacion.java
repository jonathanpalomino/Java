//**************************************************************************
// Nombre......: TRecolectorSimulacion.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un objeto que recoge todos los eventos
// ............: de simulaci�n generado para luego realizar la simulaci�n
// ............: visual de lo que ha ocurrido.
// Fecha.......: 06/03/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.electronica.recolectorsimulacion;

import simMPLS.escenario.*;
import simMPLS.interfaz.simulador.*;
import java.util.*;
import simMPLS.utiles.*;

/** Esta clase implementa la interfaz IEventoSimulacionListener. Las instancias de
 * esta clase son recolectores de eventos de simulaci�n generados por los objetos a
 * los que estan suscritos.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TRecolectorSimulacion implements IEventoSimulacionListener {

    /** Este m�todo es el constructor de la clase. Crea una nueva instanci de
     * TRecolectorSimulacion.
     * @since 1.0
     */
    public TRecolectorSimulacion() {
        bufferDeSimulacion = new TreeSet();
        cerrojo = new TMonitor();
        panel = null;
    }

    /**
     * Este m�todo establece el panel de simulaci�n donde se deben mostrar los eventos
     * de simulaci�n que el recolector vaya recibiendo.
     * @since 1.0
     * @param ps Panel de simulaci�n donde se mostrar�n los eventos.
     */    
    public synchronized void ponerPanelSimulacion(JPanelSimulacion ps) {
        panel = ps;
    }
    
    /** Este m�todo es utilizado por el objeto al que se est� suscrito, para que pueda
     * enviar los eventos de simulaci�n cuando sea necesario.
     * @param evt El evento de simulaci�n emitido por el objeto al que estamos suscritos.
     * @since 1.0
     */    
    public synchronized void capturarEventoSimulacion(TEventoSimulacion evt) {
        switch (evt.obtenerSubtipo()) {
            case TEventoSimulacion.PAQUETE_GENERADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_ENVIADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_RECIBIDO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_CONMUTADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_DESCARTADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_EN_TRANSITO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.PAQUETE_ENCAMINADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.LSP_ESTABLECIDO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.LSP_ELIMINADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ETIQUETA_ASIGNADA: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ETIQUETA_DENEGADA: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ETIQUETA_ELIMINADA: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ETIQUETA_RECIBIDA: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ETIQUETA_SOLICITADA: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.NODO_CONGESTIONADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ENLACE_CAIDO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
            case TEventoSimulacion.ENLACE_RECUPERADO: {
                panel.ponerEvento(evt);
//                System.out.println(evt.toString());
                break;
            }
        }
//        bufferDeSimulacion.add(evt);
    }
    
    /**
     * Este m�todo reinicia los atributos de la clase y deja la instancia como si
     * acabase de ser creada por el constructor de la clase.
     */    
    public void reset() {
        cerrojo.bloquear();
        Iterator it = this.bufferDeSimulacion.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        cerrojo.liberar();
    }
    
    private TMonitor cerrojo;
    
    private TreeSet bufferDeSimulacion;
    private JPanelSimulacion panel;
}
