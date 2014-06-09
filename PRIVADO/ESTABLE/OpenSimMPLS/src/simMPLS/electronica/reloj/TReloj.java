//**************************************************************************
// Nombre......: TReloj.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un reloj que emite eventos de sincro-
// ............: nizaci�n que son usados por todos los elementos de la to-
// ............: polog�a para llevar a cabo la simulaci�n.
// Fecha.......: 19/01/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.electronica.reloj;

import simMPLS.escenario.*;
import simMPLS.utiles.*;
import java.util.*;
import javax.swing.*;

/** Esta clase implementa un reloj que sincronizar� toda la simulaci�n desde el
 * comienzo hasta el final.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TReloj implements Runnable {

    /** Este m�todo es el constructor de la clase. Crea una nueva instancia de TReloj.
     * @since 1.0
     */
    public TReloj() {
        hilo = null;
        nodosSuscriptores = new TreeSet();
        enlacesSuscriptores = new TreeSet();
        suscriptorProgreso = null;
        generadorIdentificadorLargo = new TIdentificadorLargo();
        tActual = new TMarcaTiempo(0, 0);
        tAnterior = new TMarcaTiempo(0, 0);
        tLimite = new TMarcaTiempo(0, 100000);
        tActualAux = new TMarcaTiempo(0, 0);
        tAnteriorAux = new TMarcaTiempo(0, 0);
        tLimiteAux = new TMarcaTiempo(0, 100000);
        paso = 1000;
        enFuncionamiento = false;
        fin = true;
        pausa = false;
    }
    
    /**
     * Este m�todo reinicia el reloj y lo deja como si acabase de ser creado por el
     * constructor de la clase.
     * @since 1.0
     */    
    public void reset() {
        tActual = new TMarcaTiempo(0, 0);
        tAnterior = new TMarcaTiempo(0, 0);
        enFuncionamiento = false;
        generadorIdentificadorLargo.reset();
        fin = true;
        pausa = false;
        lanzarEventoProgreso();        
    }

    /** Este m�todo permite establecer el l�mite temporal a partir del cual el reloj se
     * parar� y dejar� de emitir eventos.
     * @param l Marca de tiempo indicando el l�mite donde el reloj (y por tanto la simulacion) se parar�.
     * @since 1.0
     */    
    public void ponerLimite(TMarcaTiempo l) {
        tLimite.ponerMilisegundo(l.obtenerMilisegundo());
        tLimite.ponerNanosegundo(l.obtenerNanosegundo());
    }

    /** Este m�todo permite establecer cada cuanto tiempo debe generar eventos el reloj.
     * @since 1.0
     * @param p El intervalo de tiempo que deseamos que pase entre que el reloj genera un evento
     * y el siguiente.
     */    
    public void ponerPaso(int p) {
        paso = p;
    }

    /** Este evento permite que se pueda suscribir para recibir eventos de reloj
     * cualquier elemento de la topolog�a.
     * @param suscriptor Elemento de la topolog�a que queremos suscribir para recibir eventos de reloj.
     * @since 1.0
     */    
    public void addListenerReloj(TElementoTopologia suscriptor) {
        if (suscriptor.obtenerTipoElemento() == TElementoTopologia.ENLACE)
            enlacesSuscriptores.add(suscriptor);
        else
            nodosSuscriptores.add(suscriptor);
    }

    /** Este evento permite eliminar la suscripci�n uno de los elementos de la topolog�a
     * que se encuentre suscrito para recibir eventos de reloj.
     * @param suscriptor El elemento de la topolog�a cuya suscripci�n para recibir eventos de reloj
     * deseamos eliminar.
     * @since 1.0
     */    
    public void removeListenerReloj(TElementoTopologia suscriptor) {
        if (suscriptor.obtenerTipoElemento() == TElementoTopologia.ENLACE) {
            Iterator ite = enlacesSuscriptores.iterator();
            TEnlaceTopologia e;
            TEnlaceTopologia parametro = (TEnlaceTopologia) suscriptor;
            while (ite.hasNext()) {
                e = (TEnlaceTopologia) ite.next();
                if (e.obtenerIdentificador() == parametro.obtenerIdentificador()) {
                    ite.remove();
                }
            }
        }
        else {
            Iterator itn = nodosSuscriptores.iterator();
            TNodoTopologia n;
            TNodoTopologia parametro = (TNodoTopologia) suscriptor;
            while (itn.hasNext()) {
                n = (TNodoTopologia) itn.next();
                if (n.obtenerIdentificador() == parametro.obtenerIdentificador()) {
                    itn.remove();
                }
            }
        }
    }

    /** Este m�todo permite eliminar la suscripci�n de uno ovarios elementos de la
     * topolog�a que se hayan marcado para desuscribirse desde cualquier otra parte del
     * programa. As� se evita una excepci�n de acceso concurrente.
     * @since 1.0
     */    
    public void purgarListenerReloj() {
        Iterator ite = enlacesSuscriptores.iterator();
        TEnlaceTopologia e;
        while (ite.hasNext()) {
            e = (TEnlaceTopologia) ite.next();
            if (e.obtenerPurgar()) {
                ite.remove();
            }
        }
        Iterator itn = nodosSuscriptores.iterator();
        TNodoTopologia n;
        while (itn.hasNext()) {
            n = (TNodoTopologia) itn.next();
            if (n.obtenerPurgar()) {
                itn.remove();
            }
        }
    }

    /** Este elemento permite a un actualizador de progreso suscribirse para recibir
     * eventos de progresi�n.
     * @param ap El actualizador de progreso que deseamos susccribir.
     * @throws EProgresoUnSoloSuscriptor Excepci�n que se lanza cuando se intenta suscribir nu actualizador de progreso y
     * ya hay uno suscrito. S�lo se permite un actualizador de progreso suscrito en un
     * momento dado.
     * @since 1.0
     */    
    public void addListenerProgreso(TActualizadorDeProgreso ap) throws EProgresoUnSoloSuscriptor {
        if (suscriptorProgreso == null) {
            suscriptorProgreso = ap;
        } else {
            throw new EProgresoUnSoloSuscriptor();
        }
    }

    /** Este m�todo permite anular la suscripci�n de un actualizador de progreso para
     * recibir eventos de progresi�n.
     * @since 1.0
     */    
    public void removeListenerProgreso() {
        suscriptorProgreso = null;
    }

    /** Este m�todo genera y remite un evento de reloj a todos los suscriptores que lo
     * est�n esperando.
     * @since 1.0
     */    
    private void lanzarEventoReloj() {
        Iterator ite = enlacesSuscriptores.iterator();
        Iterator itn = nodosSuscriptores.iterator();
        TNodoTopologia nodoAux;
        TEnlaceTopologia enlaceAux;
        TMarcaTiempo i = new TMarcaTiempo(tAnterior.obtenerMilisegundo(), tAnterior.obtenerNanosegundo());
        TMarcaTiempo s = new TMarcaTiempo(tActual.obtenerMilisegundo(), tActual.obtenerNanosegundo());
        while (itn.hasNext()) {
            nodoAux = (TNodoTopologia) itn.next();
            switch (nodoAux.obtenerTipo()) {
                case TNodoTopologia.EMISOR: {
                    nodoAux = (TNodoEmisor) nodoAux;
                    break;
                }
                case TNodoTopologia.LER: {
                    nodoAux = (TNodoLER) nodoAux;
                    break;
                }
                case TNodoTopologia.LERA: {
                    nodoAux = (TNodoLERA) nodoAux;
                    break;
                }
                case TNodoTopologia.LSR: {
                    nodoAux = (TNodoLSR) nodoAux;
                    break;
                }
                case TNodoTopologia.LSRA: {
                    nodoAux = (TNodoLSRA) nodoAux;
                    break;
                }
                case TNodoTopologia.RECEPTOR: {
                    nodoAux = (TNodoReceptor) nodoAux;
                    break;
                }
            }
            try {
                TEventoReloj evtReloj = new TEventoReloj(this, generadorIdentificadorLargo.obtenerNuevo(), i, s);
                nodoAux.capturarEventoReloj(evtReloj);
            } catch (EDesbordeDelIdentificador e) {
                e.printStackTrace(); 
            }
        }
        while (ite.hasNext()) {
            enlaceAux = (TEnlaceTopologia) ite.next();
            switch (enlaceAux.obtenerTipo()) {
                case TEnlaceTopologia.EXTERNO: {
                    enlaceAux = (TEnlaceExterno) enlaceAux;
                    break;
                }
                case TEnlaceTopologia.INTERNO: {
                    enlaceAux = (TEnlaceInterno) enlaceAux;
                    break;
                }
            }
            try {
                enlaceAux.capturarEventoReloj(new TEventoReloj(this, generadorIdentificadorLargo.obtenerNuevo(), i, s));
            } catch (EDesbordeDelIdentificador e) {
                e.printStackTrace(); 
            }
        }
    }

    /** Este m�todo genera y remite un evento de progresi�n al suscriptor (solo uno) que
     * lo est� esperando.
     * @since 1.0
     */    
    public void lanzarEventoProgreso() {
        int p=0;
        long total = tLimite.obtenerTotalEnNanosegundos();
        long actual = tActual.obtenerTotalEnNanosegundos();
        if (total != 0)
            p = (int) Math.round((actual*100) / total);
        try {
            if (suscriptorProgreso != null) {
                suscriptorProgreso.capturarEventoProgreso(new TEventoProgresion(this, generadorIdentificadorLargo.obtenerNuevo(), p));
            }
        } catch (EDesbordeDelIdentificador e) {
            e.printStackTrace(); 
        }
    }

    /**
     * Este m�todo <B>sincronizado</B> reanuda la ejecuci�n del reloj una vez que ha sido
     * detenida.
     * @since 1.0
     */    
    public synchronized void reanudar() {
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
    
    /** Este m�todo inicia la ejecuci�n del hilo del reloj. Se debe llamar para comenzar
     * la cuenta y generaci�n de eventos. El m�todo est� <B>sincronizado</B>.
     * @since 1.0
     */    
    public synchronized void iniciar() {
        if (hilo == null) {
            reset();
            hilo = new Thread(this);
            this.hilo.start();
        } else {
            if (!hilo.isAlive()) {
                reset();
                hilo = new Thread(this);
                this.hilo.start();
            }
        }
    }

    /**
     * Este m�todo detiene la ejecuci�n del reloj y por tanto de la simulaci�n.
     * @param p TRUE, indica que el reloj se  tiene que parar. FALSE indica que el reloj tiene
     * que reanudar su ejecuci�n.
     * @since 1.0
     */    
    public void ponerPausa(boolean p) {
        if ((pausa) && (p)) {
            // No hago nada. Ya estoy en pausa.
        } else if ((pausa) && (!p)) {
            tActual.ponerMarca(tActualAux);
            tAnterior.ponerMarca(tAnteriorAux);
            tLimite.ponerMarca(tLimiteAux);
            pausa = p;
            this.reanudar();
        } else if ((!pausa) && (p)) {
            tActualAux.ponerMarca(tActual);
            tAnteriorAux.ponerMarca(tAnterior);
            tLimiteAux.ponerMarca(tLimite);
            pausa = p;
            this.fin = true;
        } else if ((!pausa) && (!p)) {
            // No hago nada. Ya estoy funcionando.
        }
    }
    
    /**
     * Este m�todo comprueba si actualmente el reloj est� en pausa o no.
     * @since 1.0
     * @return TRUE, indica que el reloj est� en pausa. FALSE indica lo contrario.
     */    
    public boolean obtenerPausa() {
        return pausa;
    }
    
    /** Si el hilo del reloj est� activo y funcionando, este m�todo es el que se ejecuta
     * ciclicamente. B�sicamente se van contando el tiempo en pasos hasta llegar al
     * l�mite impuesto y en cada uno de los pasos se generan los eventos de reloj y de
     * progresi�n que son necesarios.
     * @since 1.0
     */    
    public void run() {
        enFuncionamiento = true;
        long totalActual;
        long totalAnterior;
        long totalLimite;
        boolean ultimoPasoDado = false;
        fin = false;
        tActual.sumarNanosegundo(paso);
        totalActual = tActual.obtenerTotalEnNanosegundos();
        totalLimite = tLimite.obtenerTotalEnNanosegundos();
        if (totalActual == totalLimite) {
            ultimoPasoDado = true;
        }
        while ((tActual.comparar(tLimite) != TMarcaTiempo.MENOR_EL_PARAMETRO) && (!fin)) {
            // Acciones a llevar a cabo
            lanzarEventoProgreso();
            lanzarEventoReloj();
            // Acciones a llevar a cabo
            tAnterior.ponerMilisegundo(tActual.obtenerMilisegundo());
            tAnterior.ponerNanosegundo(tActual.obtenerNanosegundo());
            totalActual = tActual.obtenerTotalEnNanosegundos();
            totalLimite = tLimite.obtenerTotalEnNanosegundos();
            if (totalActual+paso > totalLimite) {
                if (!ultimoPasoDado) {
                    tActual.ponerMilisegundo(tLimite.obtenerMilisegundo());
                    tActual.ponerNanosegundo(tLimite.obtenerNanosegundo());
                    ultimoPasoDado = true;
                } else {
                    tActual.sumarNanosegundo(paso);
                }
            } else {
                tActual.sumarNanosegundo(paso);
            }
            totalActual = tActual.obtenerTotalEnNanosegundos();
            totalAnterior = tAnterior.obtenerTotalEnNanosegundos();
            if (totalAnterior == totalActual) {
                fin = true;
            }
            esperarSuscriptoresReloj();
        }
        enFuncionamiento = false;
    }

    /** Este m�todo espera a que todos los suscriptores de eventos de reloj a los que se
     * ha remitido un evento lo hayan consumido. As� se puede sincronizar y no enviar
     * m�s eventos hasta que hayan finalizado con el anterior. El m�todo est�
     * <B>sincronizado</B>.
     * @since 1.0
     */    
    private synchronized void esperarSuscriptoresReloj() {
        Iterator itn = nodosSuscriptores.iterator();
        Iterator ite = enlacesSuscriptores.iterator();
        TNodoTopologia nodo;
        TEnlaceTopologia enlace;
        while (itn.hasNext()) {
            nodo = (TNodoTopologia) itn.next();
            nodo.esperarFinalizacion();
        }
        while (ite.hasNext()) {
            enlace = (TEnlaceTopologia) ite.next();
            enlace.esperarFinalizacion();
        }
    }

    /** Este m�todo permite a cualquier objeto del simulador detectar y esperar a que el
     * reloj haya finalizado su ejecuci�n antes de hacer una operaci�n. El m�todo est�
     * <B>sincronizado</B>.
     * @since 1.0
     */    
    public synchronized void esperarFinalizacion() {
        if (hilo != null) {
            try {
                this.hilo.join();
            } catch (Exception e) {
                System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TReloj.ErrorAlEsperarFinalizacionDelReloj") + e.toString());
            };
        }
    }

    /** Este m�todo permite saber si en un momento dado el reloj est� activo, esto es,
     * contando y generando eventos de reloj y simulaci�n, o por el contrario est�
     * detenido.
     * @return TRUE, si el reloj est� funcionando. FALSE si el reloj est� parado.
     * @since 1.0
     */    
    public boolean estaEnFuncionamiento() {
        return enFuncionamiento;
    }

    /** Este atributo contiene el conjunto de nodos que est�n suscritos para recibir los
     * eventos de reloj que se vayan produciendo.
     * @since 1.0
     */    
    private TreeSet nodosSuscriptores;
    /** Este atributo contiene el conjunto de enlaces que est�n suscritos para recibir los
     * eventos de reloj que se vayan produciendo.
     * @since 1.0
     */    
    private TreeSet enlacesSuscriptores;
    /** Este atributo es el actualizador de progreso que est� a la espera de los eventos
     * de progresi�n que genere esta instancia.
     * @since 1.0
     */    
    private TActualizadorDeProgreso suscriptorProgreso;
    /** Este atributo es el generador de identificadores largos que se usar� para
     * asignar a cada evento generado un identificador distinto.
     * @since 1.0
     */    
    private TIdentificadorLargo generadorIdentificadorLargo;
    /** Este atributo indica cada cuanto tiempo se debe generar un evento de reloj y de
     * progresi�n.
     */    
    private int paso;
    /** Este atributo es el hilo sobre el que correr� la instancia de TReloj.
     * @since 1.0
     */    
    private Thread hilo;    
    /** Este atributo almacena el l�mite superior que se usar� en el siguiente evento
     * de reloj que se genere.
     * @since 1.0
     */    
    private TMarcaTiempo tActual;
    /** Este atributo almacena el l�mite inferior que se usar� en el siguiente evento
     * de reloj que se genere.
     * @since 1.0
     */    
    private TMarcaTiempo tAnterior;
    /** Este atributo almacena el l�mite superior de la simulaci�n. Todas las
     * simulaciones comienzan en tiempo t=0 y acaban en el instante que indica este
     * atributo.
     * @since 1.0
     */    
    private TMarcaTiempo tLimite;
    /** Este atributo indicar� si el reloj (esta instancia) est� en proceso de ejecuci�n
     * y, por tanto, generando eventos, o si por el contrario est� parado.
     * @since 1.0
     */    
    private boolean enFuncionamiento;

    private boolean fin;
    private boolean pausa;
    
    private TMarcaTiempo tActualAux;
    private TMarcaTiempo tAnteriorAux;
    private TMarcaTiempo tLimiteAux;
}
