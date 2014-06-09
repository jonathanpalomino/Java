//**************************************************************************
// Nombre......: TNodoLSR.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Clase que implementa un nodo LSR de la topolog�a.
// Fecha.......: 27/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.escenario;

import java.awt.*;
import java.util.*;
import simMPLS.escenario.*;
import simMPLS.electronica.reloj.*;
import simMPLS.electronica.puertos.*;
import simMPLS.utiles.*;
import simMPLS.electronica.tldp.*;
import simMPLS.protocolo.*;
import org.jfree.chart.*;
import org.jfree.data.*;

/**
 * Esta clase implementa un nodo LSR; un conmutador interno a un dominio MPLS.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TNodoLSR extends TNodoTopologia implements IEventoRelojListener, Runnable {
    
    /**
     * Crea una nueva instancia de TNodoLSR
     * @param identificador Identificador unico del nodo en la topolog�a.
     * @param d Direcci�n IP del nodo.
     * @param il Generador de identificadores para los eventos generados por el nodo.
     * @param t Topolog�a dentro de la cual se encuentra el nodo.
     * @since 1.0
     */
    public TNodoLSR(int identificador, String d, TIdentificadorLargo il, TTopologia t) {
        super(identificador, d, il, t);
        this.ponerPuertos(super.NUM_PUERTOS_LSR);
        matrizConmutacion = new TMatrizConmutacion();
        gIdent = new TIdentificadorLargo();
        gIdentLDP = new TIdentificador();
        potenciaEnMb = 512;
        estadisticas = new TEstadisticasLSR();
    }
    
    /**
     * Este m�todo obtiene el n�mero de nanosegundos que son necesarios para conmutar
     * un bit.
     * @return El n�mero de nanosegundos necesarios para conmutar un bit.
     * @since 1.0
     */
    public double obtenerNsPorBit() {
        long tasaEnBitsPorSegundo = (long) (this.potenciaEnMb*1048576L);
        double nsPorCadaBit = (double) ((double)1000000000.0/(long)tasaEnBitsPorSegundo);
        return nsPorCadaBit;
    }
    
    /**
     * Este m�todo calcula el n�mero de nanosegundos necesarios para conmutar un n�mero
     * determinado de octetos.
     * @param octetos N�mero de octetos que queremos conmutar.
     * @return N�mero de nanosegundos necesarios para conmutar los octetos especificados.
     * @since 1.0
     */
    public double obtenerNsUsadosTotalOctetos(int octetos) {
        double nsPorCadaBit = obtenerNsPorBit();
        long bitsOctetos = (long) ((long)octetos*(long)8);
        return (double)((double)nsPorCadaBit*(long)bitsOctetos);
    }
    
    /**
     * Este m�todo devuelve el n�mero de bits que se pueden conmutar con el n�mero de
     * nanosegundos de los que dispone actualmente el nodo.
     * @return N�mero de bits m�ximos que puede conmutar el nodo en un instante.
     * @since 1.0
     */
    public int obtenerLimiteBitsTransmitibles() {
        double nsPorCadaBit = obtenerNsPorBit();
        double maximoBits = (double) ((double)nsDisponibles/(double)nsPorCadaBit);
        return (int) maximoBits;
    }
    
    /**
     * Este m�todo calcula el n�mero m�ximo de octetos completos que puede conmtuar el
     * nodo.
     * @return El n�mero m�ximo de octetos que puede conmutar el nodo.
     * @since 1.0
     */
    public int obtenerOctetosTransmitibles() {
        double maximoBytes = ((double)obtenerLimiteBitsTransmitibles()/(double)8.0);
        return (int) maximoBytes;
    }
    
    /**
     * Este m�todo devuelve la potencia de conmutaci�n con la que est� configurado el
     * nodo.
     * @return Potencia de conmutaci�n en Mbps.
     * @since 1.0
     */
    public int obtenerPotenciaEnMb() {
        return this.potenciaEnMb;
    }
    
    /**
     * Este m�todo establece la potencia de conmutaci�n para el nodo.
     * @param pot Potencia de conmutaci�n en Mbps deseada para el nodo.
     * @since 1.0
     */
    public void ponerPotenciaEnMb(int pot) {
        this.potenciaEnMb = pot;
    }
    
    /**
     * Este m�todo permite obtener el tamanio el buffer del nodo.
     * @return Tamanio del buffer en MB.
     * @since 1.0
     */
    public int obtenerTamanioBuffer() {
        return this.obtenerPuertos().obtenerTamanioBuffer();
    }
    
    /**
     * Este m�todo permite establecer el tamanio del buffer del nodo.
     * @param tb Tamanio deseado para el buffer del nodo en MB.
     * @since 1.0
     */
    public void ponerTamanioBuffer(int tb) {
        this.obtenerPuertos().ponerTamanioBuffer(tb);
    }
    
    /**
     * Este m�todo reinicia los atributos del nodo hasta dejarlos como si acabasen de
     * ser creados por el Constructor.
     * @since 1.0
     */
    public void reset() {
        this.puertos.reset();
        matrizConmutacion.reset();
        gIdent.reset();
        gIdentLDP.reset();
        estadisticas.reset();
        estadisticas.activarEstadisticas(this.obtenerEstadisticas());
        this.restaurarPasosSinEmitir();
    }
    
    /**
     * Este m�todo permite obtener el tipo del nodo.
     * @return TNodoTopologia.LSR, indicando que se trata de un nodo LSR.
     * @since 1.0
     */
    public int obtenerTipo() {
        return super.LSR;
    }
    
    /**
     * Este m�todo permite obtener eventos de sincronizaci�n del reloj del simulador.
     * @param evt Evento de sincronizaci�n que env�a el reloj del simulador.
     * @since 1.0
     */
    public void capturarEventoReloj(TEventoReloj evt) {
        this.ponerDuracionTic(evt.obtenerDuracionTic());
        this.ponerInstanteDeTiempo(evt.obtenerLimiteSuperior());
        if (this.obtenerPuertos().hayPaquetesQueConmutar()) {
            this.nsDisponibles += evt.obtenerDuracionTic();
        } else {
            this.restaurarPasosSinEmitir();
            this.nsDisponibles = evt.obtenerDuracionTic();
        }
        this.iniciar();
    }
    
    /**
     * Este m�todo se llama cuando se inicia el hilo independiente del nodo y es en el
     * que se implementa toda la funcionalidad.
     * @since 1.0
     */
    public void run() {
        try {
            this.generarEventoSimulacion(new TESNodoCongestionado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), this.obtenerPuertos().obtenerCongestion()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        comprobarElEstadoDeLasComunicaciones();
        decrementarContadores();
        conmutarPaquete();
        estadisticas.asentarDatos(this.obtenerInstanteDeTiempo());
        // Acciones a llevar a cabo durante el tic.
    }
    
    /**
     * Este m�todo se encarga de comprobar que los enlaces que unen al nodo con sus
     * adyacentes, funcionan correctamente. Y si no es asi y es necesario, env�a la
     * se�alizaci�n correspondiente para reparar la situaci�n.
     * @since 1.0
     */
    public void comprobarElEstadoDeLasComunicaciones() {
        TEntradaMatrizConmutacion emc = null;
        int idPuerto = 0;
        TPuerto puertoSalida = null;
        TPuerto puertoEntrada = null;
        TEnlaceTopologia et = null;
        matrizConmutacion.obtenerCerrojo().bloquear();
        Iterator it = matrizConmutacion.obtenerIteradorEntradas();
        while (it.hasNext()) {
            emc = (TEntradaMatrizConmutacion) it.next();
            if (emc != null) {
                idPuerto = emc.obtenerPuertoSalida();
                if ((idPuerto >= 0) && (idPuerto < this.puertos.obtenerNumeroDePuertos())) {
                    puertoSalida = this.puertos.obtenerPuerto(idPuerto);
                    if (puertoSalida != null) {
                        et = puertoSalida.obtenerEnlace();
                        if (et != null) {
                            if ((et.obtenerEnlaceCaido()) && (emc.obtenerEtiqueta() != TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA)) {
                                eliminarTLDP(emc, emc.obtenerPuertoEntrada());
                            }
                        }
                    }
                }
                idPuerto = emc.obtenerPuertoEntrada();
                if ((idPuerto >= 0) && (idPuerto < this.puertos.obtenerNumeroDePuertos())) {
                    puertoEntrada = this.puertos.obtenerPuerto(idPuerto);
                    if (puertoEntrada != null) {
                        et = puertoEntrada.obtenerEnlace();
                        if (et != null) {
                            if ((et.obtenerEnlaceCaido()) && (emc.obtenerEtiqueta() != TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA)) {
                                eliminarTLDP(emc, emc.obtenerPuertoSalida());
                            }
                        }
                    }
                }
            } else {
                it.remove();
            }
        }
        matrizConmutacion.obtenerCerrojo().liberar();
    }
    
    /**
     * Este m�todo conmuta paquetes del buffer de entrada.
     * @since 1.0
     */
    public void conmutarPaquete() {
        boolean conmute = false;
        int puertoLeido = 0;
        TPDU paquete = null;
        int octetosQuePuedoMandar = this.obtenerOctetosTransmitibles();
        while (this.obtenerPuertos().puedoConmutarPaquete(octetosQuePuedoMandar)) {
            conmute = true;
            paquete = this.puertos.leerPaquete();
            puertoLeido = puertos.obtenerPuertoLeido();
            if (paquete != null) {
                if (paquete.obtenerTipo() == TPDU.TLDP) {
                    conmutarTLDP((TPDUTLDP) paquete, puertoLeido);
                } else if (paquete.obtenerTipo() == TPDU.MPLS) {
                    conmutarMPLS((TPDUMPLS) paquete, puertoLeido);
                } else if (paquete.obtenerTipo() == TPDU.GPSRP) {
                    conmutarGPSRP((TPDUGPSRP) paquete, puertoLeido);
                } else {
                    this.nsDisponibles += obtenerNsUsadosTotalOctetos(paquete.obtenerTamanio());
                    descartarPaquete(paquete);
                }
                this.nsDisponibles -= obtenerNsUsadosTotalOctetos(paquete.obtenerTamanio());
                octetosQuePuedoMandar = this.obtenerOctetosTransmitibles();
            }
        }
        if (conmute) {
            this.restaurarPasosSinEmitir();
        } else {
            this.incrementarPasosSinEmitir();
        }
    }
    
    /**
     * Este m�todo conmuta un paquete GPSRP.
     * @since 1.0
     * @param paquete Paquete GPSRP a conmutar.
     * @param pEntrada Puerto por el que ha llegado el paquete.
     */
    public void conmutarGPSRP(TPDUGPSRP paquete, int pEntrada) {
        if (paquete != null) {
            int mensaje = paquete.obtenerDatosGPSRP().obtenerMensaje();
            int flujo = paquete.obtenerDatosGPSRP().obtenerFlujo();
            int idPaquete = paquete.obtenerDatosGPSRP().obtenerIdPaquete();
            String IPDestinoFinal = paquete.obtenerCabecera().obtenerIPDestino();
            TPuertoNormal pSalida = null;
            if (IPDestinoFinal.equals(this.obtenerIP())) {
                // Un LSR no entiende peticiones GPSRP, por tanto no pueder
                // haber mensajes GPSRP dirigidos a �l.
                this.descartarPaquete(paquete);
            } else {
                String IPSalida = this.topologia.obtenerIPSalto(this.obtenerIP(), IPDestinoFinal);
                pSalida = (TPuertoNormal) this.puertos.obtenerPuertoDestino(IPSalida);
                if (pSalida != null) {
                    pSalida.ponerPaqueteEnEnlace(paquete, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                    try {
                        this.generarEventoSimulacion(new TESPaqueteEncaminado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.GPSRP));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    this.descartarPaquete(paquete);
                }
            }
        }
    }
    
    /**
     * Este m�todo trata un paquete TLDP que ha llegado.
     * @param paquete Paquete TLDP recibido.
     * @param pEntrada Puerto por el que se ha recibido el paquete TLDP.
     * @since 1.0
     */
    public void conmutarTLDP(TPDUTLDP paquete, int pEntrada) {
        if (paquete.obtenerDatosTLDP().obtenerMensaje() == TDatosTLDP.SOLICITUD_ETIQUETA) {
            this.tratarSolicitudTLDP(paquete, pEntrada);
        } else if (paquete.obtenerDatosTLDP().obtenerMensaje() == TDatosTLDP.SOLICITUD_OK) {
            this.tratarSolicitudOkTLDP(paquete, pEntrada);
        } else if (paquete.obtenerDatosTLDP().obtenerMensaje() == TDatosTLDP.SOLICITUD_NO) {
            this.tratarSolicitudNoTLDP(paquete, pEntrada);
        } else if (paquete.obtenerDatosTLDP().obtenerMensaje() == TDatosTLDP.ELIMINACION_ETIQUETA) {
            this.tratarEliminacionTLDP(paquete, pEntrada);
        } else if (paquete.obtenerDatosTLDP().obtenerMensaje() == TDatosTLDP.ELIMINACION_OK) {
            this.tratarEliminacionOkTLDP(paquete, pEntrada);
        }
    }
    
    /**
     * Este m�todo trata un paquete MPLS que ha llegado.
     * @param paquete Paquete MPLS recibido.
     * @param pEntrada Puerto por el que se ha recibido el paquete MPLS.
     * @since 1.0
     */
    public void conmutarMPLS(TPDUMPLS paquete, int pEntrada) {
        TEtiquetaMPLS eMPLS = null;
        TEntradaMatrizConmutacion emc = null;
        boolean conEtiqueta1 = false;
        if (paquete.obtenerPilaEtiquetas().obtenerEtiqueta().obtenerLabel() == 1) {
            eMPLS = paquete.obtenerPilaEtiquetas().obtenerEtiqueta();
            paquete.obtenerPilaEtiquetas().borrarEtiqueta();
            conEtiqueta1 = true;
        }
        int valorLABEL = paquete.obtenerPilaEtiquetas().obtenerEtiqueta().obtenerLabel();
        String IPDestinoFinal = paquete.obtenerCabecera().obtenerIPDestino();
        emc = matrizConmutacion.obtenerEntrada(pEntrada, valorLABEL, TEntradaMatrizConmutacion.LABEL);
        if (emc == null) {
            if (conEtiqueta1) {
                paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
            }
            descartarPaquete(paquete);
        } else {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA);
                solicitarTLDP(emc);
                if (conEtiqueta1) {
                    paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                }
                this.puertos.obtenerPuerto(emc.obtenerPuertoEntrada()).reencolar(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                if (conEtiqueta1) {
                    paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                }
                this.puertos.obtenerPuerto(emc.obtenerPuertoEntrada()).reencolar(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                if (conEtiqueta1) {
                    paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                }
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                if (conEtiqueta1) {
                    paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                }
                descartarPaquete(paquete);
            } else if ((etiquetaActual > 15) || (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_CONCEDIDA)) {
                int operacion = emc.obtenerOperacion();
                if (operacion == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                    if (conEtiqueta1) {
                        paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                    }
                    descartarPaquete(paquete);
                } else {
                    if (operacion == TEntradaMatrizConmutacion.PONER_ETIQUETA) {
                        TEtiquetaMPLS empls = new TEtiquetaMPLS();
                        empls.ponerBoS(false);
                        empls.ponerEXP(0);
                        empls.ponerLabel(emc.obtenerEtiqueta());
                        empls.ponerTTL(paquete.obtenerPilaEtiquetas().obtenerEtiqueta().obtenerTTL()-1);
                        paquete.obtenerPilaEtiquetas().ponerEtiqueta(empls);
                        if (conEtiqueta1) {
                            paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                        }
                        TPuerto pSalida = puertos.obtenerPuerto(emc.obtenerPuertoSalida());
                        pSalida.ponerPaqueteEnEnlace(paquete, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteConmutado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), paquete.obtenerSubTipo()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (operacion == TEntradaMatrizConmutacion.QUITAR_ETIQUETA) {
                        paquete.obtenerPilaEtiquetas().borrarEtiqueta();
                        if (conEtiqueta1) {
                            paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                        }
                        TPuerto pSalida = puertos.obtenerPuerto(emc.obtenerPuertoSalida());
                        pSalida.ponerPaqueteEnEnlace(paquete, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteConmutado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), paquete.obtenerSubTipo()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (operacion == TEntradaMatrizConmutacion.CAMBIAR_ETIQUETA) {
                        paquete.obtenerPilaEtiquetas().obtenerEtiqueta().ponerLabel(emc.obtenerEtiqueta());
                        if (conEtiqueta1) {
                            paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                        }
                        TPuerto pSalida = puertos.obtenerPuerto(emc.obtenerPuertoSalida());
                        pSalida.ponerPaqueteEnEnlace(paquete, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        if (emc.obtenerEntranteEsLSPDEBackup()) {
                            TEnlaceInterno ei = (TEnlaceInterno) pSalida.obtenerEnlace();
                            ei.ponerLSP();
                            ei.quitarLSPDeBackup();
                            emc.ponerEntranteEsLSPDEBackup(false);
                        }
                        try {
                            this.generarEventoSimulacion(new TESPaqueteConmutado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), paquete.obtenerSubTipo()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (operacion == TEntradaMatrizConmutacion.NOOP) {
                        TPuerto pSalida = puertos.obtenerPuerto(emc.obtenerPuertoSalida());
                        pSalida.ponerPaqueteEnEnlace(paquete, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteConmutado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), paquete.obtenerSubTipo()));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                if (conEtiqueta1) {
                    paquete.obtenerPilaEtiquetas().ponerEtiqueta(eMPLS);
                }
                descartarPaquete(paquete);
            }
        }
    }
    
    /**
     * Este m�todo trata una petici�n de etiquetas.
     * @param paquete Petici�n de etiquetas recibida de otro nodo.
     * @param pEntrada Puerto de entrada de la petici�n de etiqueta.
     * @since 1.0
     */
    public void tratarSolicitudTLDP(TPDUTLDP paquete, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        emc = matrizConmutacion.obtenerEntradaIDAntecesor(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP(), pEntrada);
        if (emc == null) {
            emc = crearEntradaAPartirDeTLDP(paquete, pEntrada);
        }
        if (emc != null) {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA);
                this.solicitarTLDP(emc);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                // no hago nada. Se est� esperando una etiqueta.);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                enviarSolicitudNoTLDP(emc);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_CONCEDIDA) {
                enviarSolicitudOkTLDP(emc);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                eliminarTLDP(emc, pEntrada);
            } else if (etiquetaActual > 15) {
                enviarSolicitudOkTLDP(emc);
            } else {
                descartarPaquete(paquete);
            }
        } else {
            descartarPaquete(paquete);
        }
    }
    
    /**
     * Este m�todo trata un paquete TLDP de eliminaci�n de etiqueta.
     * @param paquete Eliminaci�n de etiqueta recibida.
     * @param pEntrada Puerto por el que se recibi�n la eliminaci�n de etiqueta.
     * @since 1.0
     */
    public void tratarEliminacionTLDP(TPDUTLDP paquete, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        if (paquete.obtenerEntradaPaquete() == TPDUTLDP.ENTRADA) {
            emc = matrizConmutacion.obtenerEntradaIDAntecesor(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP(), pEntrada);
        } else {
            emc = matrizConmutacion.obtenerEntradaIDPropio(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP());
        }
        if (emc == null) {
            descartarPaquete(paquete);
        } else {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA);
                enviarEliminacionOkTLDP(emc, pEntrada);
                eliminarTLDP(emc, emc.obtenerPuertoOpuesto(pEntrada));
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA);
                enviarEliminacionOkTLDP(emc, pEntrada);
                eliminarTLDP(emc, emc.obtenerPuertoOpuesto(pEntrada));
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA);
                enviarEliminacionOkTLDP(emc, pEntrada);
                eliminarTLDP(emc, emc.obtenerPuertoOpuesto(pEntrada));
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                enviarEliminacionOkTLDP(emc, pEntrada);
            } else if (etiquetaActual > 15) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA);
                enviarEliminacionOkTLDP(emc, pEntrada);
                eliminarTLDP(emc, emc.obtenerPuertoOpuesto(pEntrada));
            } else {
                descartarPaquete(paquete);
            }
        }
    }
    
    /**
     * Este m�todo trata un paquete TLDP de confirmaci�n de etiqueta.
     * @param paquete Confirmaci�n de etiqueta.
     * @param pEntrada Puerto por el que se ha recibido la confirmaci�n de etiquetas.
     * @since 1.0
     */
    public void tratarSolicitudOkTLDP(TPDUTLDP paquete, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        emc = matrizConmutacion.obtenerEntradaIDPropio(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP());
        if (emc == null) {
            descartarPaquete(paquete);
        } else {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                emc.ponerEtiqueta(paquete.obtenerDatosTLDP().obtenerEtiqueta());
                if (emc.obtenerLabelFEC() == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                    emc.ponerLabelFEC(matrizConmutacion.obtenerEtiqueta());
                }
                TEnlaceInterno et = (TEnlaceInterno) puertos.obtenerPuerto(pEntrada).obtenerEnlace();
                if (et != null) {
                    if (emc.obtenerEntranteEsLSPDEBackup()) {
                        et.ponerLSPDeBackup();
                    } else {
                        et.ponerLSP();
                    }
                }
                enviarSolicitudOkTLDP(emc);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_CONCEDIDA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual > 15) {
                descartarPaquete(paquete);
            } else {
                descartarPaquete(paquete);
            }
        }
    }
    
    /**
     * Este m�todo trata un paquete TLDP de denegaci�n de etiqueta.
     * @param paquete Paquete de denegaci�n de etiquetas recibido.
     * @param pEntrada Puerto por el que se ha recibido la denegaci�n de etiquetas.
     * @since 1.0
     */
    public void tratarSolicitudNoTLDP(TPDUTLDP paquete, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        emc = matrizConmutacion.obtenerEntradaIDPropio(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP());
        if (emc == null) {
            descartarPaquete(paquete);
        } else {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ETIQUETA_DENEGADA);
                enviarSolicitudNoTLDP(emc);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_CONCEDIDA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual > 15) {
                descartarPaquete(paquete);
            } else {
                descartarPaquete(paquete);
            }
        }
    }
    
    /**
     * Este m�todo trata un paquete TLDP de confirmaci�n de eliminaci�n de etiqueta.
     * @param paquete Paquete de confirmaci�n e eliminaci�n de etiqueta.
     * @param pEntrada Puerto por el que se ha recibido la confirmaci�n de eliminaci�n de etiqueta.
     * @since 1.0
     */
    public void tratarEliminacionOkTLDP(TPDUTLDP paquete, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        if (paquete.obtenerEntradaPaquete() == TPDUTLDP.ENTRADA) {
            emc = matrizConmutacion.obtenerEntradaIDAntecesor(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP(), pEntrada);
        } else {
            emc = matrizConmutacion.obtenerEntradaIDPropio(paquete.obtenerDatosTLDP().obtenerIdentificadorLDP());
        }
        if (emc == null) {
            descartarPaquete(paquete);
        } else {
            int etiquetaActual = emc.obtenerEtiqueta();
            if (etiquetaActual == TEntradaMatrizConmutacion.SIN_DEFINIR) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_DENEGADA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ETIQUETA_CONCEDIDA) {
                descartarPaquete(paquete);
            } else if (etiquetaActual == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                TPuerto pSalida = puertos.obtenerPuerto(pEntrada);
                TEnlaceInterno ei = (TEnlaceInterno) pSalida.obtenerEnlace();
                if (emc.obtenerEntranteEsLSPDEBackup()) {
                    ei.quitarLSPDeBackup();
                } else {
                    ei.quitarLSP();
                }
                matrizConmutacion.borrarEntrada(emc.obtenerPuertoEntrada(), emc.obtenerLabelFEC(), emc.obtenerTipo());
            } else if (etiquetaActual > 15) {
                descartarPaquete(paquete);
            } else {
                descartarPaquete(paquete);
            }
        }
    }
    
    /**
     * Este m�todo env�a una etiqueta al nodo que indique la entrada en la
     * matriz de conmutaci�n especificada.
     * @param emc Entrada de la matriz de conmutaci�n especificada.
     * @since 1.0
     */
    public void enviarSolicitudOkTLDP(TEntradaMatrizConmutacion emc) {
        if (emc != null) {
            if (emc.obtenerIDLDPAntecesor() != TEntradaMatrizConmutacion.SIN_DEFINIR) {
                String IPLocal = this.obtenerIP();
                String IPDestino = puertos.obtenerIPDestinoDelPuerto(emc.obtenerPuertoEntrada());
                if (IPDestino != null) {
                    TPDUTLDP nuevoTLDP = null;
                    try {
                        nuevoTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPDestino);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (nuevoTLDP != null) {
                        nuevoTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.SOLICITUD_OK);
                        nuevoTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(emc.obtenerDestinoFinal());
                        nuevoTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPAntecesor());
                        nuevoTLDP.obtenerDatosTLDP().ponerEtiqueta(emc.obtenerLabelFEC());
                        if (emc.obtenerEntranteEsLSPDEBackup()) {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS_BACKUP);
                        } else {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS);
                        }
                        TPuerto pSalida = puertos.obtenerPuertoDestino(IPDestino);
                        pSalida.ponerPaqueteEnEnlace(nuevoTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, nuevoTLDP.obtenerTamanio()));
                            this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo env�a una denegaci�n de etiqueta al nodo que especifique la entrada
     * de la matriz de conmutaci�n correspondiente.
     * @param emc Entrada de la matriz de conmutaci�n correspondiente.
     * @since 1.0
     */
    public void enviarSolicitudNoTLDP(TEntradaMatrizConmutacion emc) {
        if (emc != null) {
            if (emc.obtenerIDLDPAntecesor() != TEntradaMatrizConmutacion.SIN_DEFINIR) {
                String IPLocal = this.obtenerIP();
                String IPDestino = puertos.obtenerIPDestinoDelPuerto(emc.obtenerPuertoEntrada());
                if (IPDestino != null) {
                    TPDUTLDP nuevoTLDP = null;
                    try {
                        nuevoTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPDestino);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (nuevoTLDP != null) {
                        nuevoTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.SOLICITUD_NO);
                        nuevoTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(emc.obtenerDestinoFinal());
                        nuevoTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPAntecesor());
                        nuevoTLDP.obtenerDatosTLDP().ponerEtiqueta(TEntradaMatrizConmutacion.SIN_DEFINIR);
                        if (emc.obtenerEntranteEsLSPDEBackup()) {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS_BACKUP);
                        } else {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS);
                        }
                        TPuerto pSalida = puertos.obtenerPuertoDestino(IPDestino);
                        pSalida.ponerPaqueteEnEnlace(nuevoTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, nuevoTLDP.obtenerTamanio()));
                            this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo env�a una confirmaci�n de eliminaci�n de etiqueta al nodo que
     * especifique la correspondiente entrada en la matriz de conmutaci�n.
     * @since 1.0
     * @param puerto Puerto por el que se debe enviar la confirmaci�n de eliminaci�n.
     * @param emc Entrada de la matriz de conmutaci�n especificada.
     */
    public void enviarEliminacionOkTLDP(TEntradaMatrizConmutacion emc, int puerto) {
        if (emc != null) {
            String IPLocal = this.obtenerIP();
            String IPDestino = puertos.obtenerIPDestinoDelPuerto(puerto);
            if (IPDestino != null) {
                TPDUTLDP nuevoTLDP = null;
                try {
                    nuevoTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPDestino);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (nuevoTLDP != null) {
                    nuevoTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.ELIMINACION_OK);
                    nuevoTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(emc.obtenerDestinoFinal());
                    nuevoTLDP.obtenerDatosTLDP().ponerEtiqueta(TEntradaMatrizConmutacion.SIN_DEFINIR);
                    if (emc.obtenerPuertoSalida() == puerto) {
                        nuevoTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPPropio());
                        nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ADELANTE);
                    } else if (emc.obtenerPuertoEntrada() == puerto) {
                        nuevoTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPAntecesor());
                        if (emc.obtenerEntranteEsLSPDEBackup()) {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS_BACKUP);
                        } else {
                            nuevoTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS);
                        }
                    }
                    TPuerto pSalida = puertos.obtenerPuerto(puerto);
                    pSalida.ponerPaqueteEnEnlace(nuevoTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                    try {
                        this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, nuevoTLDP.obtenerTamanio()));
                        this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo solicita una etiqueta al nodo que se especifica en la entrada de la
     * matriz de conmutaci�n correspondiente.
     * @param emc Entrada en la matriz de conmutaci�n especificada.
     * @since 1.0
     */
    public void solicitarTLDP(TEntradaMatrizConmutacion emc) {
        String IPLocal = this.obtenerIP();
        String IPDestinoFinal = emc.obtenerDestinoFinal();
        String IPSalto = topologia.obtenerIPSalto(IPLocal, IPDestinoFinal);
        if (IPSalto != null) {
            TPDUTLDP paqueteTLDP = null;
            try {
                paqueteTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPSalto);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (paqueteTLDP != null) {
                paqueteTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(IPDestinoFinal);
                paqueteTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.SOLICITUD_ETIQUETA);
                paqueteTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPPropio());
                if (emc.obtenerEntranteEsLSPDEBackup()) {
                    paqueteTLDP.ponerEsParaBackup(true);
                } else {
                    paqueteTLDP.ponerEsParaBackup(false);
                }
                paqueteTLDP.ponerSalidaPaquete(TPDUTLDP.ADELANTE);
                TPuerto pSalida = puertos.obtenerPuertoDestino(IPSalto);
                if (pSalida != null) {
                    pSalida.ponerPaqueteEnEnlace(paqueteTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                    try {
                        this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, paqueteTLDP.obtenerTamanio()));
                        this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo env�a una eliminaci�n de etiqueta al nodo especificado por le
     * entrada de la matriz de conmutaci�n correspondiente.
     * @since 1.0
     * @param puerto Puerto por el que se debe enviar la eliminaci�n.
     * @param emc Entrada en la matriz de conmutaci�n especificada.
     */
    public void eliminarTLDP(TEntradaMatrizConmutacion emc, int puerto) {
        if (emc != null) {
            if (emc.obtenerIDLDPAntecesor() != TEntradaMatrizConmutacion.SIN_DEFINIR) {
                emc.ponerEtiqueta(TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA);
                String IPLocal = this.obtenerIP();
                String IPDestinoFinal = emc.obtenerDestinoFinal();
                String IPSalto = puertos.obtenerIPDestinoDelPuerto(puerto);
                if (IPSalto != null) {
                    TPDUTLDP paqueteTLDP = null;
                    try {
                        paqueteTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPSalto);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (paqueteTLDP != null) {
                        paqueteTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(IPDestinoFinal);
                        paqueteTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.ELIMINACION_ETIQUETA);
                        if (emc.obtenerPuertoSalida() == puerto) {
                            paqueteTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPPropio());
                            paqueteTLDP.ponerSalidaPaquete(TPDUTLDP.ADELANTE);
                        } else if (emc.obtenerPuertoEntrada() == puerto) {
                            paqueteTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPAntecesor());
                            if (emc.obtenerEntranteEsLSPDEBackup()) {
                                paqueteTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS_BACKUP);
                            } else {
                                paqueteTLDP.ponerSalidaPaquete(TPDUTLDP.ATRAS);
                            }
                        }
                        TPuerto pSalida = puertos.obtenerPuerto(puerto);
                        if (pSalida != null) {
                            pSalida.ponerPaqueteEnEnlace(paqueteTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                            try {
                                this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, paqueteTLDP.obtenerTamanio()));
                                this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo reenv�a todas las peticiones pendientes de contestaci�n de una
     * entrada de la matriz de conmutaci�n.
     * @param emc Entrada de la matriz de conmutaci�n especificada.
     * @since 1.0
     */
    public void solicitarTLDPTrasTimeout(TEntradaMatrizConmutacion emc) {
        if (emc != null) {
            String IPLocal = this.obtenerIP();
            String IPDestinoFinal = emc.obtenerDestinoFinal();
            String IPSalto = puertos.obtenerIPDestinoDelPuerto(emc.obtenerPuertoSalida());
            if (IPSalto != null) {
                TPDUTLDP paqueteTLDP = null;
                try {
                    paqueteTLDP = new TPDUTLDP(gIdent.obtenerNuevo(), IPLocal, IPSalto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (paqueteTLDP != null) {
                    paqueteTLDP.obtenerDatosTLDP().ponerIPDestinoFinal(IPDestinoFinal);
                    paqueteTLDP.obtenerDatosTLDP().ponerMensaje(TDatosTLDP.SOLICITUD_ETIQUETA);
                    paqueteTLDP.obtenerDatosTLDP().ponerIdentificadorLDP(emc.obtenerIDLDPPropio());
                    if (emc.obtenerEntranteEsLSPDEBackup()) {
                        paqueteTLDP.ponerEsParaBackup(true);
                    } else {
                        paqueteTLDP.ponerEsParaBackup(false);
                    }
                    paqueteTLDP.ponerSalidaPaquete(TPDUTLDP.ADELANTE);
                    TPuerto pSalida = puertos.obtenerPuerto(emc.obtenerPuertoSalida());
                    if (pSalida != null) {
                        pSalida.ponerPaqueteEnEnlace(paqueteTLDP, pSalida.obtenerEnlace().obtenerDestinoLocal(this));
                        try {
                            this.generarEventoSimulacion(new TESPaqueteGenerado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP, paqueteTLDP.obtenerTamanio()));
                            this.generarEventoSimulacion(new TESPaqueteEnviado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), TPDU.TLDP));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo reenv�a todas las eliminaciones de etiquetas pendientes de una
     * entrada de la matriz de conmutaci�n.
     * @since 1.0
     * @param puerto Puerto por el que se debe enviar la eliminaci�n.
     * @param emc Entrada de la matriz de conmutaci�n especificada.
     */
    public void eliminarTLDPTrasTimeout(TEntradaMatrizConmutacion emc, int puerto){
        eliminarTLDP(emc, puerto);
    }
    
    /**
     * Este m�todo reenv�a todas las eliminaciones de etiquetas pendientes de una
     * entrada de la matriz de conmutaci�n a todos los puertos necesarios.
     * @param emc Entrada de la matriz de conmutaci�n especificada.
     * @since 1.0
     */
    public void eliminarTLDPTrasTimeout(TEntradaMatrizConmutacion emc){
        eliminarTLDP(emc, emc.obtenerPuertoEntrada());
        eliminarTLDP(emc, emc.obtenerPuertoSalida());
    }
    
    /**
     * Este m�todo decrementa los contadores para la retransmisi�n.
     * @since 1.0
     */
    public void decrementarContadores() {
        TEntradaMatrizConmutacion emc = null;
        this.matrizConmutacion.obtenerCerrojo().bloquear();
        Iterator it = this.matrizConmutacion.obtenerIteradorEntradas();
        while (it.hasNext()) {
            emc = (TEntradaMatrizConmutacion) it.next();
            if (emc != null) {
                emc.decrementarTimeOut(this.obtenerDuracionTic());
                if (emc.obtenerEtiqueta() == TEntradaMatrizConmutacion.SOLICITANDO_ETIQUETA) {
                    if (emc.hacerPeticionDeNuevo()) {
                        emc.reestablecerTimeOut();
                        emc.decrementarIntentos();
                        solicitarTLDPTrasTimeout(emc);
                    }
                } else if (emc.obtenerEtiqueta() == TEntradaMatrizConmutacion.ELIMINANDO_ETIQUETA) {
                    if (emc.hacerPeticionDeNuevo()) {
                        emc.reestablecerTimeOut();
                        emc.decrementarIntentos();
                        eliminarTLDPTrasTimeout(emc);
                    } else {
                        if (!emc.quedanIntentos()) {
                            it.remove();
                        }
                    }
                } else {
                    emc.reestablecerTimeOut();
                    emc.reestablecerIntentos();
                }
            }
        }
        this.matrizConmutacion.obtenerCerrojo().liberar();
    }
    
    /**
     * Este m�todo crea una nueva entrada en la matriz de conmutaci�n a partir de una
     * solicitud de etiqueta recibida.
     * @param paqueteSolicitud Solicitud de etiqueta recibida.
     * @param pEntrada Puerto por el que se ha recibido la solicitud.
     * @return La nueva entrada en la matriz de conmutaci�n, creda, insertada e inicializada.
     * @since 1.0
     */
    public TEntradaMatrizConmutacion crearEntradaAPartirDeTLDP(TPDUTLDP paqueteSolicitud, int pEntrada) {
        TEntradaMatrizConmutacion emc = null;
        int IdTLDPAntecesor = paqueteSolicitud.obtenerDatosTLDP().obtenerIdentificadorLDP();
        TPuerto puertoEntrada = puertos.obtenerPuerto(pEntrada);
        String IPDestinoFinal = paqueteSolicitud.obtenerDatosTLDP().obtenerIPDestinoFinal();
        String IPSalto = topologia.obtenerIPSalto(this.obtenerIP(), IPDestinoFinal);
        if (IPSalto != null) {
            TPuerto puertoSalida = puertos.obtenerPuertoDestino(IPSalto);
            emc = new TEntradaMatrizConmutacion();
            emc.ponerIDLDPAntecesor(IdTLDPAntecesor);
            emc.ponerDestinoFinal(IPDestinoFinal);
            emc.ponerPuertoEntrada(pEntrada);
            emc.ponerEtiqueta(TEntradaMatrizConmutacion.SIN_DEFINIR);
            emc.ponerLabelFEC(TEntradaMatrizConmutacion.SIN_DEFINIR);
            emc.ponerEntranteEsLSPDEBackup(paqueteSolicitud.obtenerEsParaBackup());
            if (puertoSalida != null) {
                emc.ponerPuertoSalida(puertoSalida.obtenerIdentificador());
            } else {
                emc.ponerPuertoSalida(TEntradaMatrizConmutacion.SIN_DEFINIR);
            }
            emc.ponerTipo(TEntradaMatrizConmutacion.LABEL);
            emc.ponerOperacion(TEntradaMatrizConmutacion.CAMBIAR_ETIQUETA);
            try {
                emc.ponerIDLDPPropio(gIdentLDP.obtenerNuevo());
            } catch (Exception e) {
                e.printStackTrace();
            }
            matrizConmutacion.insertar(emc);
        }
        return emc;
    }
    
    /**
     * Este m�todo descarta un paquete del ndo y refleja este descarte en las
     * estad�sticas del nodo.
     * @param paquete Paquete que queremos descartar.
     * @since 1.0
     */
    public void descartarPaquete(TPDU paquete) {
        try {
            this.generarEventoSimulacion(new TESPaqueteDescartado(this, this.gILargo.obtenerNuevo(), this.obtenerInstanteDeTiempo(), paquete.obtenerSubTipo()));
            this.estadisticas.crearEstadistica(paquete, TEstadisticas.DESCARTE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        paquete = null;
    }
    
    /**
     * Este m�todo permite acceder a los puertos del nodo directamtne.
     * @return El conjunto de puertos del nodo.
     * @since 1.0
     */
    public TPuertosNodo obtenerPuertos() {
        return this.puertos;
    }
    
    /**
     * Este m�todo devuelve si el nodo tiene puertos libres o no.
     * @return TRUE, si el nodo tiene puertos libres. FALSE en caso contrario.
     * @since 1.0
     */
    public boolean tienePuertosLibres() {
        return this.puertos.hayPuertosLibres();
    }
    
    /**
     * Este m�todo devuelve el peso del nodo, que debe ser tomado en cuenta por lo
     * algoritmos de encaminamiento para calcular las rutas.
     * @return El peso del LSR.
     * @since 1.0
     */
    public long obtenerPeso() {
        long peso = 0;
        long pesoC = (long) (this.puertos.obtenerCongestion() * (0.7));
        long pesoMC = (long) ((10*this.matrizConmutacion.obtenerNumeroEntradas())* (0.3));
        peso = pesoC + pesoMC;
        return peso;
    }
    
    /**
     * Este m�todo calcula si el nodo est� bien configurado o no.
     * @return TRUE, si el ndoo est� bien configurado. FALSE en caso contrario.
     * @since 1.0
     */
    public boolean estaBienConfigurado() {
        return this.bienConfigurado;
    }
    /**
     * Este m�todo devuelve si el nodo est� bien configurado y si no, la raz�n.
     * @param t La topolog�a donde est� el nodo incluido.
     * @param recfg TRUE, si se est� reconfigurando el LSR. FALSE si se est� configurando por
     * primera vez.
     * @return CORRECTA, si el nodo est� bien configurado. Un c�digo de error en caso
     * contrario.
     * @since 1.0
     */
    public int comprobar(TTopologia t, boolean recfg) {
        this.ponerBienConfigurado(false);
        if (this.obtenerNombre().equals(""))
            return this.SIN_NOMBRE;
        boolean soloEspacios = true;
        for (int i=0; i < this.obtenerNombre().length(); i++){
            if (this.obtenerNombre().charAt(i) != ' ')
                soloEspacios = false;
        }
        if (soloEspacios)
            return this.SOLO_ESPACIOS;
        if (!recfg) {
            TNodoTopologia tp = t.obtenerPrimerNodoLlamado(this.obtenerNombre());
            if (tp != null)
                return this.NOMBRE_YA_EXISTE;
        } else {
            TNodoTopologia tp = t.obtenerPrimerNodoLlamado(this.obtenerNombre());
            if (tp != null) {
                if (this.topologia.existeMasDeUnNodoLlamado(this.obtenerNombre())) {
                    return this.NOMBRE_YA_EXISTE;
                }
            }
        }
        this.ponerBienConfigurado(true);
        return this.CORRECTA;
    }
    
    /**
     * Este m�todo transforma el c�digo de error de configuraci�n del nodo en un
     * mensaje aclaratorio.
     * @param e C�digo de error.
     * @return Texto explicativo del c�digo de error.
     * @since 1.0
     */
    public String obtenerMensajeError(int e) {
        switch (e) {
            case SIN_NOMBRE: return (java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TConfigLSR.FALTA_NOMBRE"));
            case NOMBRE_YA_EXISTE: return (java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TConfigLSR.NOMBRE_REPETIDO"));
            case SOLO_ESPACIOS: return (java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("TNodoLSR.NombreNoSoloEspacios"));
        }
        return ("");
    }
    
    /**
     * Este m�todo permite transformar el nodo en una cadena de texto que se puede
     * volcar f�cilmente a disco.
     * @return Una cadena de texto que representa al nodo.
     * @since 1.0
     */
    public String serializar() {
        String cadena = "#LSR#";
        cadena += this.obtenerIdentificador();
        cadena += "#";
        cadena += this.obtenerNombre().replace('#', ' ');
        cadena += "#";
        cadena += this.obtenerIP();
        cadena += "#";
        cadena += this.obtenerEstado();
        cadena += "#";
        cadena += this.obtenerMostrarNombre();
        cadena += "#";
        cadena += this.obtenerEstadisticas();
        cadena += "#";
        cadena += this.obtenerPosicion().x;
        cadena += "#";
        cadena += this.obtenerPosicion().y;
        cadena += "#";
        cadena += this.potenciaEnMb;
        cadena += "#";
        cadena += this.obtenerPuertos().obtenerTamanioBuffer();
        cadena += "#";
        return cadena;
    }
    
    /**
     * Este m�todo permite construir sobre la instancia actual, un LSR partiendo de la
     * representaci�n serializada de otro.
     * @param elemento �lemento serializado que se desea deserializar.
     * @return TRUE, si se ha conseguido deserializar correctamente. FALSE en caso contrario.
     * @since 1.0
     */
    public boolean desSerializar(String elemento) {
        String valores[] = elemento.split("#");
        if (valores.length != 12) {
            return false;
        }
        this.ponerIdentificador(Integer.valueOf(valores[2]).intValue());
        this.ponerNombre(valores[3]);
        this.ponerIP(valores[4]);
        this.ponerEstado(Integer.valueOf(valores[5]).intValue());
        this.ponerMostrarNombre(Boolean.valueOf(valores[6]).booleanValue());
        this.ponerEstadisticas(Boolean.valueOf(valores[7]).booleanValue());
        int posX = Integer.valueOf(valores[8]).intValue();
        int posY = Integer.valueOf(valores[9]).intValue();
        this.ponerPosicion(new Point(posX+24, posY+24));
        this.potenciaEnMb = Integer.valueOf(valores[10]).intValue();
        this.obtenerPuertos().ponerTamanioBuffer(Integer.valueOf(valores[11]).intValue());
        return true;
    }
    
    /**
     * Este m�todo permite acceder directamente a las estad�sticas del nodo.
     * @return Las estad�sticas del nodo.
     * @since 1.0
     */
    public TEstadisticas accederAEstadisticas() {
        return estadisticas;
    }
    
    /**
     * Este m�todo permite establecer el n�mero de puertos que tendr� el nodo.
     * @param num N�mero de puertos del nodo. Como mucho 8.
     * @since 1.0
     */
    public synchronized void ponerPuertos(int num) {
        puertos = new TPuertosNodoNormal(num, this);
    }
    
    /**
     * Este m�todo no hace nada en un LSR. En un nodo activoPermitir� solicitar
     * a un nodo activo la retransmisi�n de un paquete.
     * @param paquete Paquete cuya retransmisi�n se est� solicitando.
     * @param pSalida Puerto por el que se enviar� la solicitud.
     * @since 1.0
     */
    public void solicitarGPSRP(TPDUMPLS paquete, int pSalida) {
    }
    
    /**
     * Esta constante indica que la configuraci�n del nodo es correcta.
     * @since 1.0
     */
    public static final int CORRECTA = 0;
    /**
     * Esta constante indica que en la configuraci�n del nodo, falta el nombre.
     * @since 1.0
     */
    public static final int SIN_NOMBRE = 1;
    /**
     * Esta constante indica que, en la configuraci�n del nodo, se ha elegido un nombre
     * que ya est� siendo usado.
     * @since 1.0
     */
    public static final int NOMBRE_YA_EXISTE = 2;
    /**
     * Esta constante indica que en la configuraci�n del nodo, el nombre elegido es
     * err�neo porque solo cuenta con espacios.
     * @since 1.0
     */
    public static final int SOLO_ESPACIOS = 3;
    
    private TMatrizConmutacion matrizConmutacion;
    private TIdentificadorLargo gIdent;
    private TIdentificador gIdentLDP;
    private int potenciaEnMb;
    private TEstadisticasLSR estadisticas;
}
