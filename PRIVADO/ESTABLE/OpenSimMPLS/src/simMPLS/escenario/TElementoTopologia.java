//**************************************************************************
// Nombre......: TElementoTopologia.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Superclase abstracta que implementa un elemento cualquiera
// ............: de la topologia: un nodo o un enlace.
// Fecha.......: 27/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.escenario;

import simMPLS.escenario.*;
import simMPLS.electronica.reloj.*;
import simMPLS.electronica.recolectorsimulacion.*;
import simMPLS.utiles.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.data.*;

/**
 * Esta clase abstracta es la superclase de la que derivan todos los elementos que
 * se pueden insertar en una topolog�a.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public abstract class TElementoTopologia implements IEventoRelojListener, Runnable {

    /**
     * Crea una nueva instancia de TElementoTopologia
     * @param tipo Indica el tipo de elemento de que se trata. Una de las constantes de la clase.
     * @param il generador de identificadores de eventos.
     * @since 1.0
     */
    public TElementoTopologia(int tipo, TIdentificadorLargo il) {
        tipoElemento = tipo;
        eliminarDelReloj = false;
        hilo = null;
        suscriptorSimulacion = null;
        this.gILargo = il;
        this.nsDisponibles = 0;
        alive = true;
        bienConfigurado = false;
        instanteDeTiempo = 0;
        duracionTic = 0;
    }

    /**
     * Este m�todo devuelve el instante de tiempo en que se encuentra el elemento. Se
     * usa para asignar dicho instante a los eventos generados.
     * @return El instante de tiempo en que se encuentra el elemento.
     * @since 1.0
     */    
    public long obtenerInstanteDeTiempo() {
        return this.instanteDeTiempo;
    }
    
    /**
     * Este m�todo establece el instante de tiempo en que se encuentra el elemento.
     * @param i Instante de tiempo.
     * @since 1.0
     */    
    public void ponerInstanteDeTiempo(long i) {
        this.instanteDeTiempo = i;
    }

    /**
     * Este m�todo obtiene la duraci�n del tic del que dispone el elemento para operar.
     * @return La duraci�n del tic, en nanosegundos.
     * @since 1.0
     */    
    public int obtenerDuracionTic() {
        return this.duracionTic;
    }
    
    /**
     * Este m�todo establece la duraci�n del tic del que dispone el elemento.
     * @param dc Duraci�n del tic en nanosegundos.
     * @since 1.0
     */    
    public void ponerDuracionTic(int dc) {
        this.duracionTic = dc;
    }
    
    /**
     * Este m�todo permite establecer si se quiere dejar de recibir eventos de reloj o
     * no.
     * @param p TRUE, indica que se dejen de recibir eventos de reloj cuanto antes. FALSE indica
     * lo contrario.
     * @since 1.0
     */    
    public void ponerPurgar(boolean p) {
        eliminarDelReloj = p;
    }

    /**
     * Este m�todo devuelve si se ha decidido no recibir eventos del reloj o no.
     * @return TRUE, indica que se ha decidido dejar de recibir eventos del reloj. FALSE indica
     * lo contrario.
     * @since 1.0
     */    
    public boolean obtenerPurgar() {
        return eliminarDelReloj;
    }

    /**
     * Este m�todo devuelve el tipo de elemento de que se trata.
     * @return El tipo del elemento. Una de las constantes de la clase.
     * @since 1.0
     */    
    public int obtenerTipoElemento() {
        return tipoElemento;
    }

    /**
     * Este m�todo permite establecer el n�mero de nanosegundos de que dispone el
     * elemento para operar.
     * @param ns Nanosegundos de que dispone el elemento.
     * @since 1.0
     */    
    public void ponerNsDisponibles(long ns) {
        nsDisponibles = ns;
    }

    /**
     * Este m�todo permite obtener el n�mero de nanosegundos de que dispone el elemento
     * para operar.
     * @return El n�mero de nanosegundos de que dispone el elemento.
     * @since 1.0
     */    
    public double obtenerNsDisponibles() {
        return nsDisponibles;
    }

    /**
     * Este m�todo a�ade nanosegundos al n�mero de nanosegundos disponibles para el
     * elemento.
     * @param ns Nanosegundos que queremos a�adir al disponible para el elemento.
     * @since 1.0
     */    
    public void sumarNsADisponibles(long ns) {
        nsDisponibles += ns;
    }

    /**
     * Este m�todo resta nanosegundos al n�mero de nanosegundos disponibles para el
     * elemento.
     * @param ns nanosegundos que queremos descontar del disponible para el elemento.
     * @since 1.0
     */    
    public void restarNsADisponibles(long ns) {
        if (nsDisponibles < ns)
            nsDisponibles = 0;
        else
            nsDisponibles -= ns;
    }

    /**
     * Este m�todo pone en funcionamiento el hilo independiente que maneja al elemento.
     * @since 1.0
     */    
    public synchronized void iniciar() {
        if (hilo == null) {
            hilo = new Thread(this);
            this.hilo.start();
        } else {
            if (!hilo.isAlive()) {
                hilo = new Thread(this);
                this.hilo.start();
            }
        }
    }
    
    /**
     * Este m�todo se usa para sincronizar el hilo de este elemento con el de todos los
     * dem�s y con el hilo principal. Este m�todo es llamado por el reloj del
     * simulador, que sincroniza toda la simulaci�n.
     * @since 1.0
     */    
    public synchronized void esperarFinalizacion() {
        if (hilo != null) {
            try {
                this.hilo.join();
            } catch (Exception e) {
                System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TElementoTopologia.ErrorFinReloj") + e.toString());
            };
        }
    }

    /**
     * Este m�todo establece cu�l ser� el recolector de eventos de simulaci�n al que se
     * le deben enviar los eventos que genere este elemento.
     * @param rs Recolector de simulaci�n elegido.
     * @throws ESimulacionUnSoloSuscriptor Esta excepci�n salta si se intenta establecer un recolector de simulaci�n y ya
     * hay otro establecido.
     * @since 1.0
     */    
    public void addListenerSimulacion(TRecolectorSimulacion rs) throws ESimulacionUnSoloSuscriptor {
        if ( this.suscriptorSimulacion == null) {
            this.suscriptorSimulacion = rs;
        } else {
            throw new ESimulacionUnSoloSuscriptor();        
        }
    }

    /**
     * Este m�todo elimina el v�nculo con el recolector de eventos de simulaci�n al
     * que se le deben enviar los eventos que genere este elemento.
     * @since 1.0
     */    
    public void removeListenerSimulacion() {
        this.suscriptorSimulacion = null;
    }

    /**
     * Este m�todo env�a un evento de simulaci�n al recolector de simulaci�n al que
     * est� vinculado el elemento.
     * @param evt Evento que se quiere enviar al recolector.
     * @since 1.0
     */    
    public void generarEventoSimulacion(TEventoSimulacion evt) {
        if (suscriptorSimulacion != null) {
            suscriptorSimulacion.capturarEventoSimulacion(evt);
        }
    }

    /**
     * Este m�todo comprueba si el nodo est� "vivo" o no.
     * @return TRUE, si el nodo est� "vivo". FALSE en caso contrario.
     * @since 1.0
     */    
    public boolean estaVivo() {
        return alive;
    }

    /**
     * Este m�todo establece si el elemento est� bien configurado o no.
     * @param bc TRUE, si el elemento est� bien configurado. FALSE en caso contrario.
     * @since 1.0
     */    
    public void ponerBienConfigurado(boolean bc) {
        this.bienConfigurado = bc;
    }
    
    /**
     * Este m�todo recibe eventos de sincronizaci�n del reloj principal del simulador, que sincroniza
     * todo.
     * @param evt Evento que el reloj principal env�a al elemento.
     * @since 1.0
     */    
    public abstract void capturarEventoReloj(TEventoReloj evt);

    /**
     * Este m�todo es el que se ejecuta cuando se pone el hilo independiente del
     * elemento en funcionamiento.
     * @since 1.0
     */    
    public abstract void run();
    /**
     * Este m�todo calcula si el elemento est� bien configurado o no.
     * @return TRUE, si el elemento est� bien configurado. FALSE en otro caso.
     * @since 1.0
     */    
    public abstract boolean estaBienConfigurado();
    /**
     * Este m�todo genera un mensaje legible correspondiente al c�digo de error
     * especificado.
     * @param e C�digo de error que debe transformar el m�todo.
     * @return Mensaje de texto explicativo del c�digo de error.
     * @since 1.0
     */    
    public abstract String obtenerMensajeError(int e);
    /**
     * Este m�todo convierte el elemento completo en una cadena de texto almacenable en
     * disco.
     * @return La euivalencia en texto del elemento.
     * @since 1.0
     */    
    public abstract String serializar();
    /**
     * Este m�todo toma una cadena de texto que representa un elemento serializado, y
     * con ella configura correctamente el elemento.
     * @param elemento Representaci�n serializada (texto) de un elemento.
     * @return TRUE, si se ha podido deserializar correctamente el elemento textual. FALSE en
     * caso contrario.
     * @since 1.0
     */    
    public abstract boolean desSerializar(String elemento);
    /**
     * Este m�todo reinicia los atributos del elemento como si acabase de ser creado en
     * el constructor.
     * @since 1.0
     */    
    public abstract void reset();

    /**
     * Esta constante indica que el elemento es un enlace.
     * @since 1.0
     */    
    public static final int ENLACE = 0;
    /**
     * Esta constante indica que el elemento es un nodo.
     * @since 1.0
     */    
    public static final int NODO = 1;

    private int tipoElemento;
    private boolean eliminarDelReloj;
    private Thread hilo;
    /**
     * Este atributo es el recolector de eventos de simulaci�n al que se deben mandar
     * los eventos de simulaci�n que el elemento vaya generando.
     * @since 1.0
     */    
    public TRecolectorSimulacion suscriptorSimulacion;
    /**
     * Este atributo es el generador de identificadores que debe usar el elemento para
     * asignar un identificador unico a cada evento que genere.
     * @since 1.0
     */    
    public TIdentificadorLargo gILargo;
    /**
     * Este atributo almacena en cada momento los nanosegundos de los que dispone el
     * elemento para realizar las operaciones que necesite.
     * @since 1.0
     */    
    protected double nsDisponibles;
    private boolean alive;
    /**
     * Este atributo almacenar� en todo momento si el elemento est� bien configurado o
     * no.
     * @since 1.0
     */    
    protected boolean bienConfigurado;
    private long instanteDeTiempo;
    private int duracionTic;
}
