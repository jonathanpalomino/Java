/*
 * TEstadisticasEmisor.java
 *
 * Created on 10 de noviembre de 2004, 16:46
 */

package simMPLS.escenario;

import org.jfree.chart.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;
import org.jfree.data.*;
import simMPLS.protocolo.*;

/**
 * Esta clase implementa las estad�sticas de un nodo LSRA.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class TEstadisticasLSRA extends TEstadisticas {
    
    /**
     * Crea una nueva instancia de TEstadisticasLSRA
     * @since 1.0
     */
    public TEstadisticasLSRA() {
    	paquetesEntrantes = new XYSeriesCollection();
    	paquetesSalientes = new XYSeriesCollection();
    	paquetesDescartados = new XYSeriesCollection();
    	salientesMPLS = new XYSeries(TEstadisticas.MPLS);
    	salientesMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
    	salientesMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
    	salientesMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	salientesTLDP = new XYSeries(TEstadisticas.TLDP);
    	salientesGPSRP = new XYSeries(TEstadisticas.GPSRP);
    	entrantesMPLS = new XYSeries(TEstadisticas.MPLS);
    	entrantesMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
    	entrantesMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
    	entrantesMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	entrantesTLDP = new XYSeries(TEstadisticas.TLDP);
    	entrantesGPSRP = new XYSeries(TEstadisticas.GPSRP);
	descartadosMPLS = new XYSeries(TEstadisticas.MPLS);
	descartadosMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
	descartadosMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
	descartadosMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	descartadosTLDP = new XYSeries(TEstadisticas.TLDP);
    	descartadosGPSRP = new XYSeries(TEstadisticas.GPSRP);
        tEMPLS = 0;
        tEMPLS_GOS1 = 0;
        tEMPLS_GOS2 = 0;
        tEMPLS_GOS3 = 0;
        tETLDP = 0;
        tEGPSRP = 0;
        tSMPLS = 0;
        tSMPLS_GOS1 = 0;
        tSMPLS_GOS2 = 0;
        tSMPLS_GOS3 = 0;
        tSTLDP = 0;
        tSGPSRP = 0;
        tDMPLS = 0;
        tDMPLS_GOS1 = 0;
        tDMPLS_GOS2 = 0;
        tDMPLS_GOS3 = 0;
        tDTLDP = 0;
        tDGPSRP = 0;
        retransmisionesAtendidas = new DefaultCategoryDataset();
        solicitudesRecibidas = 0;
        retransmisionesRealizadas = 0;
        retransmisionesNoRealizadas = 0;
        recuperacionesLocales = new DefaultCategoryDataset();
        paquetesGoSPerdido = 0;
        solicitudesEmitidas = 0;
        paquetesGoSRecuperados = 0;
        paquetesGoSNoRecuperados = 0;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 1.
     * @return Datos de la gr�fica 1.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica1() {
        return this.paquetesEntrantes;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 2.
     * @return Datos de la gr�fica 2.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica2() {
        return this.paquetesSalientes;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 3.
     * @return Datos de la gr�fica 3.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica3() {
        return this.paquetesDescartados;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 4.
     * @return Datos de la gr�fica 4.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica4() {
        return this.retransmisionesAtendidas;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 5.
     * @return Datos de la gr�fica 5.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica5() {
        return this.recuperacionesLocales;
    }
    
    /**
     * Este m�todo permite obtener los datos necesario para generar la gr�fica 6.
     * @return Datos de la gr�fica 6.
     * @since 1.0
     */    
    public org.jfree.data.AbstractDataset obtenerDatosGrafica6() {
        return null;
    }

    /**
     * Este m�todo amplia las estad�sticas a�adiendo las correspondientes al paquete
     * especificado.
     * @param paquete Paquete que se desea contabilizar.
     * @param entrada ENTRADA, SALIDA o DESCARTE, dependiendo de si el paquete entra en el nodo, sale
     * de �l o es descartado.
     * @since 1.0
     */    
    public void crearEstadistica(TPDU paquete, int entrada) {
        if (this.estadisticasActivas) {
            int tipoPaquete = paquete.obtenerSubTipo();
            int GoS = 0;
            if (tipoPaquete == TPDU.TLDP) {
                if (entrada == TEstadisticas.SALIDA) {
                    this.tSTLDP++;
                } else if (entrada == TEstadisticas.DESCARTE) {
                    this.tDTLDP++;
                } else if (entrada == TEstadisticas.ENTRADA) {
                    this.tETLDP++;
                }
            } else if (tipoPaquete == TPDU.GPSRP) {
                TPDUGPSRP pGPSRP = (TPDUGPSRP) paquete;
                int mensaje = pGPSRP.obtenerDatosGPSRP().obtenerMensaje();
                if (mensaje == TDatosGPSRP.SOLICITUD_RETRANSMISION) {
                    if (entrada == TEstadisticas.SALIDA) {
                        this.tSGPSRP++;
                        solicitudesEmitidas++;
                    } else if (entrada == TEstadisticas.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TEstadisticas.ENTRADA) {
                        this.tEGPSRP++;
                        solicitudesRecibidas++;
                    }
                } else if (mensaje == TDatosGPSRP.RETRANSMISION_NO) {
                    if (entrada == TEstadisticas.SALIDA) {
                        this.tSGPSRP++;
                        retransmisionesNoRealizadas++;
                    } else if (entrada == TEstadisticas.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TEstadisticas.ENTRADA) {
                        this.tEGPSRP++;
                        paquetesGoSNoRecuperados++;
                    }
                } else if (mensaje == TDatosGPSRP.RETRANSMISION_OK) {
                    if (entrada == TEstadisticas.SALIDA) {
                        this.tSGPSRP++;
                        retransmisionesRealizadas++;
                    } else if (entrada == TEstadisticas.DESCARTE) {
                        this.tDGPSRP++;
                    } else if (entrada == TEstadisticas.ENTRADA) {
                        this.tEGPSRP++;
                        paquetesGoSRecuperados++;
                    }
                }
            } else if (tipoPaquete == TPDU.MPLS) {
                if (entrada == TEstadisticas.SALIDA) {
                    this.tSMPLS++;
                } else if (entrada == TEstadisticas.DESCARTE) {
                    this.tDMPLS++;
                } else if (entrada == TEstadisticas.ENTRADA) {
                    this.tEMPLS++;
                }
            } else if (tipoPaquete == TPDU.MPLS_GOS) {
                GoS = paquete.obtenerCabecera().obtenerCampoOpciones().obtenerNivelGoS();
                if (entrada == TEstadisticas.SALIDA) {
                    if ((GoS == TPDU.EXP_NIVEL0_SINLSP) || (GoS == TPDU.EXP_NIVEL0_CONLSP)) {
                        this.tSMPLS++;
                    } else if ((GoS == TPDU.EXP_NIVEL1_SINLSP) || (GoS == TPDU.EXP_NIVEL1_CONLSP)) {
                        this.tSMPLS_GOS1++;
                    } else if ((GoS == TPDU.EXP_NIVEL2_SINLSP) || (GoS == TPDU.EXP_NIVEL2_CONLSP)) {
                        this.tSMPLS_GOS2++;
                    } else if ((GoS == TPDU.EXP_NIVEL3_SINLSP) || (GoS == TPDU.EXP_NIVEL3_CONLSP)) {
                        this.tSMPLS_GOS3++;
                    }
                } else if (entrada == TEstadisticas.DESCARTE) {
                    paquetesGoSPerdido++;
                    if ((GoS == TPDU.EXP_NIVEL0_SINLSP) || (GoS == TPDU.EXP_NIVEL0_CONLSP)) {
                        this.tDMPLS++;
                    } else if ((GoS == TPDU.EXP_NIVEL1_SINLSP) || (GoS == TPDU.EXP_NIVEL1_CONLSP)) {
                        this.tDMPLS_GOS1++;
                    } else if ((GoS == TPDU.EXP_NIVEL2_SINLSP) || (GoS == TPDU.EXP_NIVEL2_CONLSP)) {
                        this.tDMPLS_GOS2++;
                    } else if ((GoS == TPDU.EXP_NIVEL3_SINLSP) || (GoS == TPDU.EXP_NIVEL3_CONLSP)) {
                        this.tDMPLS_GOS3++;
                    }
                } else if (entrada == TEstadisticas.ENTRADA) {
                    if ((GoS == TPDU.EXP_NIVEL0_SINLSP) || (GoS == TPDU.EXP_NIVEL0_CONLSP)) {
                        this.tEMPLS++;
                    } else if ((GoS == TPDU.EXP_NIVEL1_SINLSP) || (GoS == TPDU.EXP_NIVEL1_CONLSP)) {
                        this.tEMPLS_GOS1++;
                    } else if ((GoS == TPDU.EXP_NIVEL2_SINLSP) || (GoS == TPDU.EXP_NIVEL2_CONLSP)) {
                        this.tEMPLS_GOS2++;
                    } else if ((GoS == TPDU.EXP_NIVEL3_SINLSP) || (GoS == TPDU.EXP_NIVEL3_CONLSP)) {
                        this.tEMPLS_GOS3++;
                    }
                }
            }
        }
    }
    
    /**
     * Este m�todo permite obtener el n�mero de graficas del LSRA.
     * @return El n�mero de gr�ficas del LSRA.
     * @since 1.0
     */    
    public int obtenerNumeroGraficas() {
        return 5;
    }
    
    /**
     * Este m�todo reinicia los atributos de la clase, dejando las instancia como si
     * acabase de ser creada por el constructor.
     * @since 1.0
     */    
    public void reset() {
    	paquetesEntrantes = new XYSeriesCollection();
    	paquetesSalientes = new XYSeriesCollection();
    	paquetesDescartados = new XYSeriesCollection();
    	salientesMPLS = new XYSeries(TEstadisticas.MPLS);
    	salientesMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
    	salientesMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
    	salientesMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	salientesTLDP = new XYSeries(TEstadisticas.TLDP);
    	salientesGPSRP = new XYSeries(TEstadisticas.GPSRP);
    	entrantesMPLS = new XYSeries(TEstadisticas.MPLS);
    	entrantesMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
    	entrantesMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
    	entrantesMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	entrantesTLDP = new XYSeries(TEstadisticas.TLDP);
    	entrantesGPSRP = new XYSeries(TEstadisticas.GPSRP);
	descartadosMPLS = new XYSeries(TEstadisticas.MPLS);
	descartadosMPLS_GOS1 = new XYSeries(TEstadisticas.MPLS_GOS1);
	descartadosMPLS_GOS2 = new XYSeries(TEstadisticas.MPLS_GOS2);
	descartadosMPLS_GOS3 = new XYSeries(TEstadisticas.MPLS_GOS3);
    	descartadosTLDP = new XYSeries(TEstadisticas.TLDP);
    	descartadosGPSRP = new XYSeries(TEstadisticas.GPSRP);
        tEMPLS = 0;
        tEMPLS_GOS1 = 0;
        tEMPLS_GOS2 = 0;
        tEMPLS_GOS3 = 0;
        tETLDP = 0;
        tEGPSRP = 0;
        tSMPLS = 0;
        tSMPLS_GOS1 = 0;
        tSMPLS_GOS2 = 0;
        tSMPLS_GOS3 = 0;
        tSTLDP = 0;
        tSGPSRP = 0;
        tDMPLS = 0;
        tDMPLS_GOS1 = 0;
        tDMPLS_GOS2 = 0;
        tDMPLS_GOS3 = 0;
        tDTLDP = 0;
        tDGPSRP = 0;
        retransmisionesAtendidas = new DefaultCategoryDataset();
        solicitudesRecibidas = 0;
        retransmisionesRealizadas = 0;
        retransmisionesNoRealizadas = 0;
        recuperacionesLocales = new DefaultCategoryDataset();
        paquetesGoSPerdido = 0;
        solicitudesEmitidas = 0;
        paquetesGoSRecuperados = 0;
        paquetesGoSNoRecuperados = 0;
    }
    
    /**
     * Este m�todo actualiza las estad�sticas con los ultimos datos recogidos desde la
     * �ltima llamada a este m�todo.
     * @param instante Instante de tiempo al que se atribuir�n los ultimso datos.
     * @since 1.0
     */    
    public void asentarDatos(long instante) {
        if (this.estadisticasActivas) {
            if (tEMPLS > 0) {
                if (entrantesMPLS.getItemCount() == 0) {
                    this.entrantesMPLS.add(instante-1, 0);
                    this.entrantesMPLS.add(instante, tEMPLS);
                    this.paquetesEntrantes.addSeries(entrantesMPLS);
                } else {
                    this.entrantesMPLS.add(instante, tEMPLS);
                }
            }
            
            if (tEMPLS_GOS1 > 0) {
                if (entrantesMPLS_GOS1.getItemCount() == 0) {
                    this.entrantesMPLS_GOS1.add(instante-1, 0);
                    this.entrantesMPLS_GOS1.add(instante, tEMPLS_GOS1);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS1);
                } else {
                    this.entrantesMPLS_GOS1.add(instante, tEMPLS_GOS1);
                }
            }
            
            if (tEMPLS_GOS2 > 0) {
                if (entrantesMPLS_GOS2.getItemCount() == 0) {
                    this.entrantesMPLS_GOS2.add(instante-1, 0);
                    this.entrantesMPLS_GOS2.add(instante, tEMPLS_GOS2);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS2);
                } else {
                    this.entrantesMPLS_GOS2.add(instante, tEMPLS_GOS2);
                }
            }
            
            if (tEMPLS_GOS3 > 0) {
                if (entrantesMPLS_GOS3.getItemCount() == 0) {
                    this.entrantesMPLS_GOS3.add(instante-1, 0);
                    this.entrantesMPLS_GOS3.add(instante, tEMPLS_GOS3);
                    this.paquetesEntrantes.addSeries(entrantesMPLS_GOS3);
                } else {
                    this.entrantesMPLS_GOS3.add(instante, tEMPLS_GOS3);
                }
            }
            
            if (tETLDP > 0) {
                if (entrantesTLDP.getItemCount() == 0) {
                    this.entrantesTLDP.add(instante-1, 0);
                    this.entrantesTLDP.add(instante, tETLDP);
                    this.paquetesEntrantes.addSeries(entrantesTLDP);
                } else {
                    this.entrantesTLDP.add(instante, tETLDP);
                }
            }
            
            if (tEGPSRP > 0) {
                if (entrantesGPSRP.getItemCount() == 0) {
                    this.entrantesGPSRP.add(instante-1, 0);
                    this.entrantesGPSRP.add(instante, tEGPSRP);
                    this.paquetesEntrantes.addSeries(entrantesGPSRP);
                } else {
                    this.entrantesGPSRP.add(instante, tEGPSRP);
                }
            }
                    
            if (tSMPLS > 0) {
                if (salientesMPLS.getItemCount() == 0) {
                    this.salientesMPLS.add(instante-1, 0);
                    this.salientesMPLS.add(instante, tSMPLS);
                    this.paquetesSalientes.addSeries(salientesMPLS);
                } else {
                    this.salientesMPLS.add(instante, tSMPLS);
                }
            }
                    
            if (tSMPLS_GOS1 > 0) {
                if (salientesMPLS_GOS1.getItemCount() == 0) {
                    this.salientesMPLS_GOS1.add(instante-1, 0);
                    this.salientesMPLS_GOS1.add(instante, tSMPLS_GOS1);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS1);
                } else {
                    this.salientesMPLS_GOS1.add(instante, tSMPLS_GOS1);
                }
            }
                    
            if (tSMPLS_GOS2 > 0) {
                if (salientesMPLS_GOS2.getItemCount() == 0) {
                    this.salientesMPLS_GOS2.add(instante-1, 0);
                    this.salientesMPLS_GOS2.add(instante, tSMPLS_GOS2);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS2);
                } else {
                    this.salientesMPLS_GOS2.add(instante, tSMPLS_GOS2);
                }
            }
                    
            if (tSMPLS_GOS3 > 0) {
                if (salientesMPLS_GOS3.getItemCount() == 0) {
                    this.salientesMPLS_GOS3.add(instante-1, 0);
                    this.salientesMPLS_GOS3.add(instante, tSMPLS_GOS3);
                    this.paquetesSalientes.addSeries(salientesMPLS_GOS3);
                } else {
                    this.salientesMPLS_GOS3.add(instante, tSMPLS_GOS3);
                }
            }
                    
            if (tSTLDP > 0) {
                if (salientesTLDP.getItemCount() == 0) {
                    this.salientesTLDP.add(instante-1, 0);
                    this.salientesTLDP.add(instante, tSTLDP);
                    this.paquetesSalientes.addSeries(salientesTLDP);
                } else {
                    this.salientesTLDP.add(instante, tSTLDP);
                }
            }
                    
            if (tSGPSRP > 0) {
                if (salientesGPSRP.getItemCount() == 0) {
                    this.salientesGPSRP.add(instante-1, 0);
                    this.salientesGPSRP.add(instante, tSGPSRP);
                    this.paquetesSalientes.addSeries(salientesGPSRP);
                } else {
                    this.salientesGPSRP.add(instante, tSGPSRP);
                }
            }
                    
            if (tDMPLS > 0) {
                if (descartadosMPLS.getItemCount() == 0) {
                    this.descartadosMPLS.add(instante-1, 0);
                    this.descartadosMPLS.add(instante, tDMPLS);
                    this.paquetesDescartados.addSeries(descartadosMPLS);
                } else {
                    this.descartadosMPLS.add(instante, tDMPLS);
                }
            }
                    
            if (tDMPLS_GOS1 > 0) {
                if (descartadosMPLS_GOS1.getItemCount() == 0) {
                    this.descartadosMPLS_GOS1.add(instante-1, 0);
                    this.descartadosMPLS_GOS1.add(instante, tDMPLS_GOS1);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS1);
                } else {
                    this.descartadosMPLS_GOS1.add(instante, tDMPLS_GOS1);
                }
            }
                    
            if (tDMPLS_GOS2 > 0) {
                if (descartadosMPLS_GOS2.getItemCount() == 0) {
                    this.descartadosMPLS_GOS2.add(instante-1, 0);
                    this.descartadosMPLS_GOS2.add(instante, tDMPLS_GOS2);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS2);
                } else {
                    this.descartadosMPLS_GOS2.add(instante, tDMPLS_GOS2);
                }
            }
                    
            if (tDMPLS_GOS3 > 0) {
                if (descartadosMPLS_GOS3.getItemCount() == 0) {
                    this.descartadosMPLS_GOS3.add(instante-1, 0);
                    this.descartadosMPLS_GOS3.add(instante, tDMPLS_GOS3);
                    this.paquetesDescartados.addSeries(descartadosMPLS_GOS3);
                } else {
                    this.descartadosMPLS_GOS3.add(instante, tDMPLS_GOS3);
                }
            }
                    
            if (tDTLDP > 0) {
                if (descartadosTLDP.getItemCount() == 0) {
                    this.descartadosTLDP.add(instante-1, 0);
                    this.descartadosTLDP.add(instante, tDTLDP);
                    this.paquetesDescartados.addSeries(descartadosTLDP);
                } else {
                    this.descartadosTLDP.add(instante, tDTLDP);
                }
            }
                    
            if (tDGPSRP > 0) {
                if (descartadosGPSRP.getItemCount() == 0) {
                    this.descartadosGPSRP.add(instante-1, 0);
                    this.descartadosGPSRP.add(instante, tDGPSRP);
                    this.paquetesDescartados.addSeries(descartadosGPSRP);
                } else {
                    this.descartadosGPSRP.add(instante, tDGPSRP);
                }
            }
            
            this.retransmisionesAtendidas.addValue(this.solicitudesRecibidas, TEstadisticas.SOLICITUDES_RECIBIDAS, "");
            this.retransmisionesAtendidas.addValue(this.retransmisionesRealizadas, TEstadisticas.RETRANSMISIONES_REALIZADAS, "");
            this.retransmisionesAtendidas.addValue(this.retransmisionesNoRealizadas, TEstadisticas.RETRANSMISIONES_NO_REALIZADAS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSPerdido, TEstadisticas.PAQUETES_GOS_PERDIDOS, "");
            this.recuperacionesLocales.addValue(this.solicitudesEmitidas, TEstadisticas.SOLICITUDES_EMITIDAS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSRecuperados, TEstadisticas.PAQUETES_GOS_RECUPERADOS, "");
            this.recuperacionesLocales.addValue(this.paquetesGoSNoRecuperados, TEstadisticas.PAQUETES_GOS_NO_RECUPERADOS, "");
            int sinRespuesta = (solicitudesEmitidas - paquetesGoSRecuperados - paquetesGoSNoRecuperados);
            if (sinRespuesta < 0) {
                sinRespuesta = 0;
            }
            this.recuperacionesLocales.addValue(sinRespuesta, TEstadisticas.SOLICITUDES_SIN_RESPUESTA_AUN, "");
        }
    }    
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 1.
     * @return T�tulo de la gr�fica 1.
     * @since 1.0
     */    
    public String obtenerTitulo1() {
        return TEstadisticas.PAQUETES_ENTRANTES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 2.
     * @return T�tulo de la gr�fica 2.
     * @since 1.0
     */    
    public String obtenerTitulo2() {
        return TEstadisticas.PAQUETES_SALIENTES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 3.
     * @return T�tulo de la gr�fica 3.
     * @since 1.0
     */    
    public String obtenerTitulo3() {
        return TEstadisticas.PAQUETES_DESCARTADOS;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 4.
     * @return T�tulo de la gr�fica 4.
     * @since 1.0
     */    
    public String obtenerTitulo4() {
        return TEstadisticas.RETRANSMISIONES_ATENDIDAS;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 5.
     * @return T�tulo de la gr�fica 5.
     * @since 1.0
     */    
    public String obtenerTitulo5() {
        return TEstadisticas.RECUPERACIONES_LOCALES;
    }
    
    /**
     * Este m�todo permite obtener el t�tulo de la gr�fica 6.
     * @return T�tulo de la gr�fica 6.
     * @since 1.0
     */    
    public String obtenerTitulo6() {
        return null;
    }
    
    private int tEMPLS;
    private int tEMPLS_GOS1;
    private int tEMPLS_GOS2;
    private int tEMPLS_GOS3;
    private int tETLDP;
    private int tEGPSRP;
    private int tSMPLS;
    private int tSMPLS_GOS1;
    private int tSMPLS_GOS2;
    private int tSMPLS_GOS3;
    private int tSTLDP;
    private int tSGPSRP;
    private int tDMPLS;
    private int tDMPLS_GOS1;
    private int tDMPLS_GOS2;
    private int tDMPLS_GOS3;
    private int tDTLDP;
    private int tDGPSRP;
    private XYSeriesCollection paquetesEntrantes;
    private XYSeriesCollection paquetesSalientes;
    private XYSeriesCollection paquetesDescartados;
    private XYSeries entrantesMPLS;
    private XYSeries entrantesMPLS_GOS1;
    private XYSeries entrantesMPLS_GOS2;
    private XYSeries entrantesMPLS_GOS3;
    private XYSeries entrantesTLDP;
    private XYSeries entrantesGPSRP;
    private XYSeries salientesMPLS;
    private XYSeries salientesMPLS_GOS1;
    private XYSeries salientesMPLS_GOS2;
    private XYSeries salientesMPLS_GOS3;
    private XYSeries salientesTLDP;
    private XYSeries salientesGPSRP;
    private XYSeries descartadosMPLS;
    private XYSeries descartadosMPLS_GOS1;
    private XYSeries descartadosMPLS_GOS2;
    private XYSeries descartadosMPLS_GOS3;
    private XYSeries descartadosTLDP;
    private XYSeries descartadosGPSRP;
    private DefaultCategoryDataset retransmisionesAtendidas;
    private int solicitudesRecibidas;
    private int retransmisionesRealizadas;
    private int retransmisionesNoRealizadas;
    private DefaultCategoryDataset recuperacionesLocales;
    private int paquetesGoSPerdido;
    private int solicitudesEmitidas;
    private int paquetesGoSRecuperados;
    private int paquetesGoSNoRecuperados;
}
