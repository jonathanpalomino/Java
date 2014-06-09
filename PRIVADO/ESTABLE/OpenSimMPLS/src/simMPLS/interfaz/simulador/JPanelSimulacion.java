//**************************************************************************
// Nombre......: JPanelSimulacion.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Implementaci�n de un panel de dibujo que responder� a los
// ............: eventos del rat�n a los eventos surgidos en la simulaci�n
// ............: y que se utilizar� como base para observar el transcurso de
// ............: la simulaci�n.
// Fecha.......: 19/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.interfaz.simulador;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.Toolkit.*;
import java.util.*;
import simMPLS.escenario.*;
import simMPLS.interfaz.utiles.*;
import simMPLS.protocolo.*;
import simMPLS.utiles.*;

/**
 * Esta clase implementa un panel que recibir� eventos de simulaci�n y los
 * representar� en pantalla dando la sensaci�n de continuidad a una simulaci�n
 * visual.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JPanelSimulacion extends javax.swing.JPanel {

    /**
     * Crea una nueva instancia de JPanelSimulacion
     * @since 1.0
     */
    public JPanelSimulacion() {
        initComponents();
    }

    /**
     * Crea una nueva instancia de JPanelSimulacion
     * @since 1.0
     * @param di Dispensador de im�genes de donde el panel tomar� las im�genes que necesite
     * mostrar en pantalla.
     */    
    public JPanelSimulacion(TDispensadorDeImagenes di) {
        dispensadorDeImagenes = di;
        initComponents();
    }

    /**
     * @since 1.0
     */    
    private void initComponents () {
        tamPantalla=Toolkit.getDefaultToolkit().getScreenSize();
	buffer = null;
	imagenbuf = null;
        g2Dbuf = null;
        topologia=null;
        maxX = 10;
        maxY = 10;
        bufferEventos = new TreeSet();
        bufferParaSimular = new TreeSet();
        ticActual = 0;
        mlsPorTic = 0;
        mostrarLeyenda = false;
        COLOR_LEYENDA = new Color(255, 255, 230);
        COLOR_NOMBRE_ENLACE = new Color(255, 255, 230);
        COLOR_BORDE_DOMINIO = new Color(232, 212, 197);
        COLOR_FONDO_DOMINIO = new Color(239, 222, 209);
        COLOR_LSP = new Color(0, 0, 200);
        cerrojo = new TMonitor();
        ficheroTraza = null;;
        streamFicheroTraza = null;
        streamTraza = null;
    }

    /**
     * Reincia todos los atributos de la clase a su valor de creaci�n.
     * @since 1.0
     */    
    public void reset() {
        cerrojo.bloquear();
        Iterator it = null;
        it = bufferEventos.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        it = bufferParaSimular.iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
        mostrarLeyenda = false;
        cerrojo.liberar();
        ticActual = 0;
        ficheroTraza = null;;
        streamFicheroTraza = null;
        streamTraza = null;
    }
    
    /**
     * Este m�todo env�a un evento al fichero de traza, para que quede registrado.
     * @param es Evento de simulaci�n que se desea tracear.
     * @since 1.0
     */    
    public void enviarATraza(TEventoSimulacion es) {
        String texto = "";
        texto += this.ticActual + ": ";
        if (this.streamTraza != null) {
            if (es.obtenerTipo() == TEventoSimMPLS.SIMULACION) {
                texto += es.toString();
                this.streamTraza.println(texto);
            }
        }
    }
    
    /**
     * Este m�todo establece el fichero de traza.
     * @param ft Fichero de traza.
     * @since 1.0
     */    
    public void ponerFicheroTraza(File ft) {
        if (this.ficheroTraza != null) {
            if (this.streamFicheroTraza != null) {
                try {
                    this.streamFicheroTraza.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (this.streamTraza != null) {
                this.streamTraza.close();
            }
        }
        this.ficheroTraza = ft;
        if (this.ficheroTraza != null) {
            if (this.ficheroTraza.exists()) {
                this.ficheroTraza.delete();
            }
            try {
                this.streamFicheroTraza = new FileOutputStream(ficheroTraza);
            } catch (Exception e) {
                e.printStackTrace();
            }
            this.streamTraza = new PrintStream(streamFicheroTraza, true);
        } else {
            this.streamFicheroTraza = null;
            this.streamTraza = null;
            this.ficheroTraza = null;
        }
    }
    
    /**
     * Este m�todo asocia al panel de simulaci�n una topologia concreta.
     * @since 1.0
     * @param t Topolog�a que se debe representar en el panel de simulaci�n.
     */    
    public void ponerTopologia(TTopologia t) {
        topologia = t;
    }

    /**
     * Este m�todo asigna un dispensador de im�genes al panel de forma que de ah�
     * tomar� todas las im�genes que deba mostrar en la pantalla.
     * @since 1.0
     * @param di El dispensador de im�genes.
     */    
    public void ponerDispensadorDeImagenes(TDispensadorDeImagenes di) {
        dispensadorDeImagenes = di;
    }

    /**
     * Este m�todo determina que grosor en p�xeles debe tener un enlace de la topolog�a
     * al ser mostrado, segun su delay.
     * @since 1.0
     * @param delay Retardo del enlace.
     * @return Grosor en p�xeles que se debe usar para mostrar el enlace.
     */    
    public double obtenerGrosorEnlace(double delay) {
        return (16/Math.log(delay+100));
    }
    
    /**
     * @param g2Dbuf
     * @since 1.0
     */    
    private void prepararImagen(Graphics2D g2Dbuf) {
        g2Dbuf.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2Dbuf.setColor(Color.WHITE);
        g2Dbuf.fillRect(0,0, tamPantalla.width, tamPantalla.height);
    }

    /**
     * @param g2Dbuf
     * @since 1.0
     */    
    private void dibujarDominio(Graphics2D g2Dbuf) {
        Iterator itd = topologia.obtenerIteradorNodos();
        TNodoTopologia nd;
        Polygon pol = new Polygon();
        int vertices = 0;
        while (itd.hasNext()) {
            nd = (TNodoTopologia) itd.next();
            if ((nd.obtenerTipo() == TNodoTopologia.LER) ||
               (nd.obtenerTipo() == TNodoTopologia.LERA)) {
                   pol.addPoint(nd.obtenerPosicion().x+24, nd.obtenerPosicion().y+24);
                   vertices ++;
               }
        };
        if (vertices > 2) {
            g2Dbuf.setColor(this.COLOR_FONDO_DOMINIO);
            g2Dbuf.fillPolygon(pol);
            g2Dbuf.setColor(this.COLOR_BORDE_DOMINIO);
            g2Dbuf.drawPolygon(pol);
        } else if (vertices == 2) {
            int x1 = Math.min(pol.xpoints[0], pol.xpoints[1]);
            int y1 = Math.min(pol.ypoints[0], pol.ypoints[1]);
            int x2 = Math.max(pol.xpoints[0], pol.xpoints[1]);
            int y2 = Math.max(pol.ypoints[0], pol.ypoints[1]);
            int ancho = x2-x1;
            int alto = y2-y1;
            g2Dbuf.setColor(this.COLOR_FONDO_DOMINIO);
            g2Dbuf.fillRect(x1, y1, ancho, alto);
            g2Dbuf.setColor(this.COLOR_BORDE_DOMINIO);
            g2Dbuf.drawRect(x1, y1, ancho, alto);
        } else if (vertices == 1) {
            g2Dbuf.setColor(this.COLOR_FONDO_DOMINIO);
            g2Dbuf.fillOval(pol.xpoints[0]-50, pol.ypoints[0]-40, 100, 80);
            g2Dbuf.setColor(this.COLOR_BORDE_DOMINIO);
            g2Dbuf.drawOval(pol.xpoints[0]-50, pol.ypoints[0]-40, 100, 80);
        }
    }

    /**
     * @param g2Dbuf
     * @since 1.0
     */    
    private void dibujarEnlaces(Graphics2D g2Dbuf) {
        Iterator ite = topologia.obtenerIteradorEnlaces();
        while (ite.hasNext()) {
            TEnlaceTopologia enlace = (TEnlaceTopologia) ite.next();
            Point inicio = enlace.obtenerExtremo1().obtenerPosicion();
            Point fin = enlace.obtenerExtremo2().obtenerPosicion();
            int del = enlace.obtenerDelay();
            g2Dbuf.setStroke(new BasicStroke((float) obtenerGrosorEnlace(del)));
            if (enlace.obtenerTipo() == TEnlaceTopologia.EXTERNO) {
                g2Dbuf.setColor(Color.GRAY);
            } else {
                g2Dbuf.setColor(Color.BLUE);
            }
            if (enlace.obtenerEnlaceCaido()) {
                    float dash1[] = {5.0f};
                    BasicStroke dashed = new BasicStroke(2.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
                    g2Dbuf.setColor(Color.RED);
                    g2Dbuf.setStroke(dashed);
            }
            g2Dbuf.drawLine(inicio.x+24, inicio.y+24, fin.x+24, fin.y+24);
            g2Dbuf.setStroke(new BasicStroke((float) 1));
//
            if (!enlace.obtenerEnlaceCaido()) {
                if (enlace.obtenerTipo() == TEnlaceTopologia.INTERNO) {
                    TEnlaceInterno ei = (TEnlaceInterno) enlace;
                    if (ei.tieneLSPs()) {
                        float dash1[] = {5.0f};
                        BasicStroke dashed = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
                        g2Dbuf.setColor(this.COLOR_LSP);
                        g2Dbuf.setStroke(dashed);
                        if (inicio.x == fin.x) {
                            g2Dbuf.drawLine(inicio.x+20, inicio.y+24, fin.x+20, fin.y+24);
                        }
                        else if (inicio.y == fin.y) {
                            g2Dbuf.drawLine(inicio.x+24, inicio.y+20, fin.x+24, fin.y+20);
                        }
                        else if (((inicio.x < fin.x) && (inicio.y > fin.y)) || ((inicio.x > fin.x) && (inicio.y < fin.y))) {
                            g2Dbuf.drawLine(inicio.x+20, inicio.y+20, fin.x+20, fin.y+20);
                        }
                        else if (((inicio.x < fin.x) && (inicio.y < fin.y)) || ((inicio.x > fin.x) && (inicio.y > fin.y))) {
                            g2Dbuf.drawLine(inicio.x+28, inicio.y+20, fin.x+28, fin.y+20);
                        }
                        g2Dbuf.setStroke(new BasicStroke(1));
                    }
                }
            }
//
//
            if (!enlace.obtenerEnlaceCaido()) {
                if (enlace.obtenerTipo() == TEnlaceTopologia.INTERNO) {
                    TEnlaceInterno ei = (TEnlaceInterno) enlace;
                    if (ei.tieneLSPsDeBackup()) {
                        float dash1[] = {10.0f, 5.0f, 0.2f, 5.0f};
                        BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
                        g2Dbuf.setColor(Color.BLACK);
                        g2Dbuf.setStroke(dashed);
                        if (inicio.x == fin.x) {
                            g2Dbuf.drawLine(inicio.x+28, inicio.y+24, fin.x+28, fin.y+24);
                        }
                        else if (inicio.y == fin.y) {
                            g2Dbuf.drawLine(inicio.x+24, inicio.y+28, fin.x+24, fin.y+28);
                        }
                        else if (((inicio.x < fin.x) && (inicio.y > fin.y)) || ((inicio.x > fin.x) && (inicio.y < fin.y))) {
                            g2Dbuf.drawLine(inicio.x+28, inicio.y+28, fin.x+28, fin.y+28);
                        }
                        else if (((inicio.x < fin.x) && (inicio.y < fin.y)) || ((inicio.x > fin.x) && (inicio.y > fin.y))) {
                            g2Dbuf.drawLine(inicio.x+20, inicio.y+28, fin.x+20, fin.y+28);
                        }
                        g2Dbuf.setStroke(new BasicStroke(1));
                    }
                }
            }
//
            if (enlace.obtenerMostrarNombre()) {
                FontMetrics fm = this.getFontMetrics(this.getFont());
                int anchoTexto = fm.charsWidth(enlace.obtenerNombre().toCharArray(), 0, enlace.obtenerNombre().length());
                int posX1 = enlace.obtenerExtremo1().obtenerPosicion().x+24;
                int posY1 = enlace.obtenerExtremo1().obtenerPosicion().y+24;
                int posX2 = enlace.obtenerExtremo2().obtenerPosicion().x+24;
                int posY2 = enlace.obtenerExtremo2().obtenerPosicion().y+24;
                int posX = Math.min(posX1, posX2) + ((Math.max(posX1, posX2) - Math.min(posX1, posX2)) / 2) - (anchoTexto / 2);
                int posY = Math.min(posY1, posY2) + ((Math.max(posY1, posY2) - Math.min(posY1, posY2)) / 2) + 5;
                g2Dbuf.setColor(this.COLOR_NOMBRE_ENLACE);
                g2Dbuf.fillRoundRect(posX-3, posY-13, anchoTexto+5, 17, 10, 10);
                g2Dbuf.setColor(Color.GRAY);
                g2Dbuf.drawString(enlace.obtenerNombre(), posX, posY);
                g2Dbuf.drawRoundRect(posX-3, posY-13, anchoTexto+5, 17, 10, 10);
            }
        }
    }

    /**
     * @param g2Dbuf
     * @since 1.0
     */    
    private void dibujarNodos(Graphics2D g2Dbuf) {
        maxX = 10;
        maxY = 10;
        Iterator ite = topologia.obtenerIteradorNodos();
        while (ite.hasNext()) {
            TNodoTopologia nodo = (TNodoTopologia) ite.next();
            Point posicion = nodo.obtenerPosicion();

            if ((posicion.x+48) > maxX)
                maxX = posicion.x+48;
            if ((posicion.y+48) > maxY)
                maxY = posicion.y+48;
            this.setPreferredSize(new Dimension(maxX, maxY));
            this.revalidate();
            
            int tipo = nodo.obtenerTipo();
            switch (tipo) {
                case TNodoTopologia.EMISOR: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.EMISOR), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.EMISOR_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
                case TNodoTopologia.RECEPTOR: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.RECEPTOR), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.RECEPTOR_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
                case TNodoTopologia.LER: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LER), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LER_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
                case TNodoTopologia.LERA: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LERA), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LERA_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
                case TNodoTopologia.LSR: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSR), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSR_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
                case TNodoTopologia.LSRA: {
                    if (nodo.obtenerEstado() == TNodoTopologia.DESELECCIONADO)
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSRA), posicion.x, posicion.y, null);
                    else
                        g2Dbuf.drawImage(dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSRA_MOVIENDOSE), posicion.x, posicion.y, null);
                    break;
                }
            }
            if (nodo.obtenerMostrarNombre()) {
                FontMetrics fm = this.getFontMetrics(this.getFont());
                int anchoTexto = fm.charsWidth(nodo.obtenerNombre().toCharArray(), 0, nodo.obtenerNombre().length());
                int posX = (nodo.obtenerPosicion().x + 24) - ((anchoTexto/2));
                int posY = nodo.obtenerPosicion().y+60;
                g2Dbuf.setColor(Color.WHITE);
                g2Dbuf.fillRoundRect(posX-3, posY-13, anchoTexto+5, 17, 10, 10);
                g2Dbuf.setColor(Color.GRAY);
                g2Dbuf.drawString(nodo.obtenerNombre(), posX, posY);
                g2Dbuf.drawRoundRect(posX-3, posY-13, anchoTexto+5, 17, 10, 10);
            }
        }
    }

    /**
     * Este m�todo permite establecer cuantos milisegundos de espera va a haber entre
     * que se muestra un tic en la simulaci�n, y se meustra el siguiente. A efectos
     * pr�cticos permite ralentizar la simulaci�n en tiempo real.
     * @param mls N�mero de milisegundos de retardo entre frames de una misma simulaci�n.
     * @since 1.0
     */    
    public void ponerMlsPorTic(int mls) {
        this.mlsPorTic = mls;
    }
    
    /**
     * Este m�todo permite a�adir un evento a la lista de eventos que se deben mostrar
     * en la ventana del simulador.
     * @param evt El nuevo evento que se debe mostrar en la simulaci�n.
     * @since 1.0
     */    
    public void ponerEvento(TEventoSimulacion evt) {
        cerrojo.bloquear();
        this.enviarATraza(evt);
        if (evt.obtenerInstante() <= ticActual) {
            bufferEventos.add(evt);
            cerrojo.liberar();
        } else {
            ticActual = evt.obtenerInstante();
            Iterator it = this.bufferParaSimular.iterator();
            TEventoSimulacion evento = null;
            while (it.hasNext()) {
                it.next();
                it.remove();
            }
            it = bufferEventos.iterator();
            evento = null;
            while (it.hasNext()) {
                evento = (TEventoSimulacion) it.next();
                bufferParaSimular.add(evento);
                it.remove();
            }
            cerrojo.liberar();
            repaint();
            bufferEventos.add(evt);
            try {
                Thread.currentThread().sleep(this.mlsPorTic);
            } catch (Exception e) {
                e.printStackTrace(); 
            }
        }
    }
    
    /**
     * Este m�todo permite dibujar los eventos relacionados con las PDU's que circulan
     * por la red.
     * @since 1.0
     * @param g2D El lienzo donde se mostrar� el evento.
     */    
    public void dibujarEventosPaquete(Graphics2D g2D) {
        cerrojo.bloquear();
        try {
            Iterator it = bufferParaSimular.iterator();
            TEventoSimulacion evento = null;
            while (it.hasNext()) {
                evento = (TEventoSimulacion) it.next();
                if (evento != null) {
                    if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_EN_TRANSITO) {
                        TESPaqueteEnTransito ept = (TESPaqueteEnTransito) evento;
                        TEnlaceTopologia et = (TEnlaceTopologia) ept.obtenerFuente();
                        Point p = et.obtenerCoordenadasPaquete(ept.obtenerPorcentajeTransito());
                        if (ept.obtenerTipoPaquete() == TPDU.GPSRP) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_GOS), p.x-14, p.y-14, null);
                        } else if (ept.obtenerTipoPaquete() == TPDU.TLDP) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_LDP), p.x-8, p.y-8, null);
                        } else if (ept.obtenerTipoPaquete() == TPDU.IPV4) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4), p.x-8, p.y-8, null);
                        } else if (ept.obtenerTipoPaquete() == TPDU.IPV4_GOS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4_GOS), p.x-8, p.y-8, null);
                        } else if (ept.obtenerTipoPaquete() == TPDU.MPLS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS), p.x-8, p.y-8, null);
                        } else if (ept.obtenerTipoPaquete() == TPDU.MPLS_GOS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS_GOS), p.x-8, p.y-8, null);
                        }
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_DESCARTADO) {
                        TESPaqueteDescartado epd = (TESPaqueteDescartado) evento;
                        TNodoTopologia nt = (TNodoTopologia) epd.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        if (epd.obtenerTipoPaquete() == TPDU.GPSRP) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_GOS_CAE), p.x, p.y+24, null);
                        } else if (epd.obtenerTipoPaquete() == TPDU.TLDP) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_LDP_CAE), p.x, p.y+24, null);
                        } else if (epd.obtenerTipoPaquete() == TPDU.IPV4) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4_CAE), p.x, p.y+24, null);
                        } else if (epd.obtenerTipoPaquete() == TPDU.IPV4_GOS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4_GOS_CAE), p.x, p.y+24, null);
                        } else if (epd.obtenerTipoPaquete() == TPDU.MPLS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS_CAE), p.x, p.y+24, null);
                        } else if (epd.obtenerTipoPaquete() == TPDU.MPLS_GOS) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS_GOS_CAE), p.x, p.y+24, null);
                        }
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.LSP_ESTABLECIDO) {
                        // Algo se har�.
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_GENERADO) {
                        TESPaqueteGenerado epg = (TESPaqueteGenerado) evento;
                        TNodoTopologia nt = (TNodoTopologia) epg.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_GENERADO), p.x+8, p.y-16, null);
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_ENVIADO) {
                        TESPaqueteEnviado epe = (TESPaqueteEnviado) evento;
                        TNodoTopologia nt = (TNodoTopologia) epe.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_EMITIDO), p.x+24, p.y-16, null);
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_RECIBIDO) {
                        TESPaqueteRecibido epr = (TESPaqueteRecibido) evento;
                        TNodoTopologia nt = (TNodoTopologia) epr.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_RECIBIDO), p.x-8, p.y-16, null);
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_CONMUTADO) {
                        TESPaqueteConmutado epr = (TESPaqueteConmutado) evento;
                        TNodoTopologia nt = (TNodoTopologia) epr.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_CONMUTADO), p.x+40, p.y-16, null);
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.PAQUETE_ENCAMINADO) {
                        TESPaqueteEncaminado epr = (TESPaqueteEncaminado) evento;
                        TNodoTopologia nt = (TNodoTopologia) epr.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_CONMUTADO), p.x+40, p.y-16, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        cerrojo.liberar();
    }

    
    /**
     * Este m�todo permite representar los eventos que tengan que ver con los nodos.
     * @since 1.0
     * @param g2D El lienzo donde se mostrar� el evento.
     */    
    public void dibujarEventosNodo(Graphics2D g2D) {
        cerrojo.bloquear();
        try {
            TEventoSimulacion evento = null;
            Iterator it = bufferParaSimular.iterator();
            while (it.hasNext()) {
                evento = (TEventoSimulacion) it.next();
                if (evento != null) {
                    if (evento.obtenerSubtipo() == TEventoSimulacion.NODO_CONGESTIONADO) {
                        TESNodoCongestionado enc = (TESNodoCongestionado) evento;
                        TNodoTopologia nt = (TNodoTopologia) enc.obtenerFuente();
                        Point p = nt.obtenerPosicion();
                        int tipo = nt.obtenerTipo();
                        long cong = enc.obtenerPorcentajeCongestion();
                        if ((cong >= 50) && (cong < 75)) {
                            if (tipo == TNodoTopologia.EMISOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.EMISOR_CONGESTIONADO_20), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.RECEPTOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.RECEPTOR_CONGESTIONADO_20), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LER) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LER_CONGESTIONADO_20), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LERA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LERA_CONGESTIONADO_20), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSR_CONGESTIONADO_20), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSRA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSRA_CONGESTIONADO_20), p.x, p.y, null);
                            }
                        } else if ((cong >= 75) && (cong < 95)) {
                            if (tipo == TNodoTopologia.EMISOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.EMISOR_CONGESTIONADO_60), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.RECEPTOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.RECEPTOR_CONGESTIONADO_60), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LER) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LER_CONGESTIONADO_60), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LERA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LERA_CONGESTIONADO_60), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSR_CONGESTIONADO_60), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSRA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSRA_CONGESTIONADO_60), p.x, p.y, null);
                            }
                        } else if (cong >= 95) {
                            if (tipo == TNodoTopologia.EMISOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.EMISOR_CONGESTIONADO), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.RECEPTOR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.RECEPTOR_CONGESTIONADO), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LER) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LER_CONGESTIONADO), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LERA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LERA_CONGESTIONADO), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSR) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSR_CONGESTIONADO), p.x, p.y, null);
                            } else if (tipo == TNodoTopologia.LSRA) {
                                g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.LSRA_CONGESTIONADO), p.x, p.y, null);
                            }
                        }
                        if (nt.obtenerPasosSinEmitir() > TNodoTopologia.MAX_PASOS_SIN_EMITIR) {
                            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.TRABAJANDO), p.x, p.y, null);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        cerrojo.liberar();
    }

    
    /**
     * Este m�todo permite tratar los eventos que tienen que ver con los enlaces de
     * comunicaciones.
     * @since 1.0
     * @param g2D El lienzo donde se mostrar� el evento.
     */    
    public void dibujarEventosEnlace(Graphics2D g2D) {
        cerrojo.bloquear();
        try {
            TEventoSimulacion evento = null;
            Iterator it = bufferParaSimular.iterator();
            while (it.hasNext()) {
                evento = (TEventoSimulacion) it.next();
                if (evento != null) {
                    if (evento.obtenerSubtipo() == TEventoSimulacion.ENLACE_CAIDO) {
                        TEnlaceTopologia ent = (TEnlaceTopologia) evento.obtenerFuente();
                        Point p = ent.obtenerCoordenadasPaquete(50);
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.ENLACE_CAIDO), p.x-41, p.y-41, null);
                    } else if (evento.obtenerSubtipo() == TEventoSimulacion.ENLACE_RECUPERADO) {
                        TEnlaceTopologia ent = (TEnlaceTopologia) evento.obtenerFuente();
                        Point p = ent.obtenerCoordenadasPaquete(50);
                        g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.ENLACE_RECUPERADO), p.x-41, p.y-41, null);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        cerrojo.liberar();
    }
    
    
    /**
     * Este m�todo muestra un contador en la ventana de simulaci�n que indica el
     * nanosegundo de simulaci�n que por el que se va.
     * @since 1.0
     * @param g2D El lienzo donde se mostrar� el contador.
     */    
    public void dibujarTicActual(Graphics2D g2D) {
        int posX = 8;
        int posY = 18;
        String textoTic = this.ticActual+ " " +java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Ns");
        FontMetrics fm = this.getFontMetrics(this.getFont());
        int anchoTexto = fm.charsWidth(textoTic.toCharArray(), 0, textoTic.length());
        g2Dbuf.setColor(Color.LIGHT_GRAY);
        g2Dbuf.fillRect(posX-2, posY-12, anchoTexto+6, 18);
        g2Dbuf.setColor(Color.WHITE);
        g2Dbuf.fillRect(posX-3, posY-13, anchoTexto+5, 17);
        g2Dbuf.setColor(Color.BLACK);
        g2Dbuf.drawString(textoTic, posX, posY);
        g2Dbuf.drawRect(posX-3, posY-13, anchoTexto+5, 17);
    }
    
    /**
     * Este m�todo dibuja en el panel de simulaci�n una leyenda con los objetos que
     * aparecer�n en la simulaci�n. Siempre, por supuesto, que la leyenda est�
     * configurada para aparecer.
     * @param g2D Lienzo del panel de simulaci�n, donde se dibujar� la leyenda.
     * @since 1.0
     */    
    public void dibujarLeyenda(Graphics2D g2D) {
        if (this.mostrarLeyenda) {
            int ancho = 0;
            int anchoTotal = 0;
            int alto = 0; 
            int xInicio = 0;
            int yInicio = 0;
            FontMetrics fm = this.getFontMetrics(this.getFont());
            if ((fm.charsWidth(PAQUETE_IPV4.toCharArray(), 0, PAQUETE_IPV4.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_IPV4.toCharArray(), 0, PAQUETE_IPV4.length());
            }
            if ((fm.charsWidth(PAQUETE_IPV4_GOS.toCharArray(), 0, PAQUETE_IPV4_GOS.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_IPV4_GOS.toCharArray(), 0, PAQUETE_IPV4_GOS.length());
            }
            if ((fm.charsWidth(PAQUETE_MPLS.toCharArray(), 0, PAQUETE_MPLS.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_MPLS.toCharArray(), 0, PAQUETE_MPLS.length());
            }
            if ((fm.charsWidth(PAQUETE_MPLS_GOS.toCharArray(), 0, PAQUETE_MPLS_GOS.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_MPLS_GOS.toCharArray(), 0, PAQUETE_MPLS_GOS.length());
            }
            if ((fm.charsWidth(PAQUETE_TLDP.toCharArray(), 0, PAQUETE_TLDP.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_TLDP.toCharArray(), 0, PAQUETE_TLDP.length());
            }
            if ((fm.charsWidth(PAQUETE_GPSRP.toCharArray(), 0, PAQUETE_GPSRP.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_GPSRP.toCharArray(), 0, PAQUETE_GPSRP.length());
            }
            if ((fm.charsWidth(LSP_NORMAL.toCharArray(), 0, LSP_NORMAL.length())) > ancho) {
                ancho = fm.charsWidth(LSP_NORMAL.toCharArray(), 0, LSP_NORMAL.length());
            }
            if ((fm.charsWidth(LSP_BACKUP.toCharArray(), 0, LSP_BACKUP.length())) > ancho) {
                ancho = fm.charsWidth(LSP_BACKUP.toCharArray(), 0, LSP_BACKUP.length());
            }
            if ((fm.charsWidth(PAQUETE_RECIBIDO.toCharArray(), 0, PAQUETE_RECIBIDO.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_RECIBIDO.toCharArray(), 0, PAQUETE_RECIBIDO.length());
            }
            if ((fm.charsWidth(PAQUETE_ENVIADO.toCharArray(), 0, PAQUETE_ENVIADO.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_ENVIADO.toCharArray(), 0, PAQUETE_ENVIADO.length());
            }
            if ((fm.charsWidth(PAQUETE_CONMUTADO.toCharArray(), 0, PAQUETE_CONMUTADO.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_CONMUTADO.toCharArray(), 0, PAQUETE_CONMUTADO.length());
            }
            if ((fm.charsWidth(PAQUETE_GENERADO.toCharArray(), 0, PAQUETE_GENERADO.length())) > ancho) {
                ancho = fm.charsWidth(PAQUETE_GENERADO.toCharArray(), 0, PAQUETE_GENERADO.length());
            }
            anchoTotal = 5+13+5+ancho+20+13+5+ancho+5;
            alto = 113;
            xInicio = this.getWidth()-anchoTotal-6;
            yInicio = this.getHeight()-alto-6;
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.fillRect(xInicio+2, yInicio+2, anchoTotal, alto);
            g2D.setColor(COLOR_LEYENDA);
            g2D.fillRect(xInicio, yInicio, anchoTotal, alto);
            g2D.setColor(Color.BLACK);
            g2D.drawRect(xInicio, yInicio, anchoTotal, alto);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4), xInicio+5, yInicio+5, null);
            g2D.drawString(this.PAQUETE_IPV4, xInicio+23, yInicio+18);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_IPV4_GOS), xInicio+5, yInicio+23, null);
            g2D.drawString(this.PAQUETE_IPV4_GOS, xInicio+23, yInicio+36);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS), xInicio+5, yInicio+41, null);
            g2D.drawString(this.PAQUETE_MPLS, xInicio+23, yInicio+54);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_MPLS_GOS), xInicio+5, yInicio+59, null);
            g2D.drawString(this.PAQUETE_MPLS_GOS, xInicio+23, yInicio+72);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PDU_LDP), xInicio+5, yInicio+77, null);
            g2D.drawString(this.PAQUETE_TLDP, xInicio+23, yInicio+90);
            g2D.setColor(Color.LIGHT_GRAY);
            g2D.drawOval(xInicio+5, yInicio+95, 13, 13);
            g2D.setColor(Color.BLACK);
            g2D.fillOval(xInicio+8, yInicio+98, 7, 7);
            g2D.setColor(Color.RED);
            g2D.fillOval(xInicio+9, yInicio+99, 5, 5);
            g2D.setColor(Color.YELLOW);
            g2D.fillOval(xInicio+10, yInicio+100, 3, 3);
            g2D.setColor(Color.BLACK);
            g2D.fillOval(xInicio+11, yInicio+101, 1, 1);
            g2D.setColor(Color.BLACK);
            g2D.drawString(this.PAQUETE_GPSRP, xInicio+23, yInicio+108);
            xInicio = xInicio + 5 + 13 + 5 + ancho + 20 - 5;

            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_RECIBIDO), xInicio+5, yInicio+5, null);
            g2D.drawString(this.PAQUETE_RECIBIDO, xInicio+23, yInicio+18);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_GENERADO), xInicio+5, yInicio+23, null);
            g2D.drawString(this.PAQUETE_GENERADO, xInicio+23, yInicio+36);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_EMITIDO), xInicio+5, yInicio+41, null);
            g2D.drawString(this.PAQUETE_ENVIADO, xInicio+23, yInicio+54);
            g2D.drawImage(this.dispensadorDeImagenes.obtenerImagen(TDispensadorDeImagenes.PAQUETE_CONMUTADO), xInicio+5, yInicio+59, null);
            g2D.drawString(this.PAQUETE_CONMUTADO, xInicio+23, yInicio+72);
            float dash1[] = {5.0f};
            BasicStroke dashed = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash1, 0.0f);
            g2Dbuf.setColor(this.COLOR_LSP);
            g2Dbuf.setStroke(dashed);
            g2D.drawLine(xInicio-5, yInicio+84, xInicio-5+30, yInicio+84);
            g2Dbuf.setColor(Color.BLACK);
            g2D.drawString(this.LSP_NORMAL, xInicio+35, yInicio+90);
            float dash2[] = {10.0f, 5.0f, 0.2f, 5.0f};
            BasicStroke dashed2 = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 5.0f, dash2, 0.0f);
            g2Dbuf.setColor(Color.BLACK);
            g2Dbuf.setStroke(dashed2);
            g2D.drawLine(xInicio-5, yInicio+102, xInicio-5+30, yInicio+102);
            g2Dbuf.setColor(Color.BLACK);
            g2D.drawString(this.LSP_BACKUP, xInicio+35, yInicio+108);
            g2Dbuf.setStroke(new BasicStroke(1.0f));
        }
    }
    
    /**
     * Este m�todo permite obtener una representaci�n de la simulaci�n en un momento,
     * en forma de imagen bitmap.
     * @since 1.0
     * @return Una imagen accesible, cuyo contenido es una captura de la simulaci�n.
     */    
    public BufferedImage capturaDeDisenio() {
        if (imagenbuf == null) {
            imagenbuf = new BufferedImage(tamPantalla.width, tamPantalla.height, BufferedImage.TYPE_4BYTE_ABGR);
            g2Dbuf = imagenbuf.createGraphics();
        }
        prepararImagen(g2Dbuf);
        if (topologia != null) {
            dibujarDominio(g2Dbuf);
            dibujarEnlaces(g2Dbuf);
            dibujarEventosPaquete(g2Dbuf);
            dibujarNodos(g2Dbuf);
            dibujarEventosNodo(g2Dbuf);
            dibujarEventosEnlace(g2Dbuf);
            dibujarTicActual(g2Dbuf);
            dibujarLeyenda(g2Dbuf);
        }
        return imagenbuf;
    }

    /**
     * Este m�todo redibuja el panel de simulaci�n cada vez que es necesario.
     * @since 1.0
     * @param g El lienzo donde se debe redibujar el panel de simulaci�n.
     */    
    public void paint(java.awt.Graphics g) {
        BufferedImage ima = this.capturaDeDisenio();
        g.drawImage(ima, 0, 0, null);
    }

    /**
     * Este m�todo permite averiguar si el simulador est� mostrando la leyenda o no.
     * @return TRUE, si se est� mostrando la leyenda. FALSE en caso contrario.
     * @since 1.0
     */    
    public boolean obtenerMostrarLeyenda() {
        return this.mostrarLeyenda;
    }
    
    /**
     * Este m�todo permite establecer si el simulador mostrar� la leyenda o no.
     * @param ml TRUE, si el simulador debe mostrar la leyenda. FALSE en caso contrario.
     * @since 1.0
     */    
    public void ponerMostrarLeyenda(boolean ml) {
        this.mostrarLeyenda = ml;
    }
    
    /**
     * @since 1.0
     */    
    private TDispensadorDeImagenes dispensadorDeImagenes;
    /**
     * @since 1.0
     */    
    private Image buffer;
    /**
     * @since 1.0
     */    
    private BufferedImage imagenbuf;
    /**
     * @since 1.0
     */    
    private Graphics2D g2Dbuf;
    /**
     * @since 1.0
     */    
    private TTopologia topologia;
    /**
     * @since 1.0
     */    
    private Dimension tamPantalla;  
    /**
     * @since 1.0
     */    
    private int maxX;
    /**
     * @since 1.0
     */    
    private int maxY;
    
    private File ficheroTraza;
    private FileOutputStream streamFicheroTraza;
    private PrintStream streamTraza;
    
    private TreeSet bufferEventos;
    private TreeSet bufferParaSimular;
    private long ticActual;
    private TMonitor cerrojo;
    private int mlsPorTic;
    private boolean mostrarLeyenda;
    
    private static Color COLOR_LEYENDA;
    private static Color COLOR_NOMBRE_ENLACE;
    private static Color COLOR_BORDE_DOMINIO;
    private static Color COLOR_FONDO_DOMINIO;
    private static Color COLOR_LSP;
    
    private static final String PAQUETE_IPV4 = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_IPv4");
    private static final String PAQUETE_IPV4_GOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_IPv4_GOS");
    private static final String PAQUETE_MPLS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_MPLS");
    private static final String PAQUETE_MPLS_GOS = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_MPLS_GOS");
    private static final String PAQUETE_TLDP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_TLDP");
    private static final String PAQUETE_GPSRP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_GPSRP");
    private static final String LSP_NORMAL = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.LSP");
    private static final String LSP_BACKUP = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.LSP_de_respaldo");
    private static final String PAQUETE_RECIBIDO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Paquete_recibido");
    private static final String PAQUETE_ENVIADO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_enviado");
    private static final String PAQUETE_CONMUTADO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_conmutado");
    private static final String PAQUETE_GENERADO = java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("JPanelSimulacion.Paquete_generado");
}
