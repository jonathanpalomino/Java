//**************************************************************************
// Nombre......: JVentanaEnlace.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Implementaci�n de una ventana que obtendr� los par�metros
// ............: necesarios para la configuraci�n de un enlace.
// Fecha.......: 19/02/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.interfaz.dialogos;

import simMPLS.interfaz.utiles.*;
import simMPLS.interfaz.dialogos.*;
import simMPLS.interfaz.simulador.*;
import simMPLS.escenario.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

/**
 * Esta clase implementa una ventana que permite configurar un enlace de la
 * topolog�a.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JVentanaEnlace extends javax.swing.JDialog {

    /**
     * Crea una nueva instancia de JVentanaEmisor
     * @param t Topolog�a dentro de la cual est� insertado el enlace.
     * @param di Dispensador de im�genes global de la aplicaci�n.
     * @param parent Ventana padre donde se ubicar� esta ventana de tipo JVentanaEnlace.
     * @param modal TRUE indica que la ventana impedir� que se pueda seleccionar nada de la interfza
     * hasta que se cierre. FALSE indica que esto no es asi.
     * @since 1.0
     */
    public JVentanaEnlace(TTopologia t, TDispensadorDeImagenes di, java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        ventanaPadre = parent;
        dispensadorDeImagenes = di;
        topo = t;
        configEnlace = null;
        initComponents();
        initComponents2();
    }

    /**
     * Este m�todo configura aspectos de la ventana que no han podido ser configurados
     * en el constructor.
     * @since 1.0
     */    
    public void initComponents2() {
        java.awt.Dimension tamFrame=this.getSize();
        java.awt.Dimension tamPadre=ventanaPadre.getSize();
        setLocation((tamPadre.width-tamFrame.width)/2, (tamPadre.height-tamFrame.height)/2);
        BCKUPNombre = null;
        BCKUPMostrarNombre = false;
        BCKUPCrearEstadisticas = false;
        BCKUPDelay = 1000;
        this.delayFacil.removeAllItems();
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Personalized"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Too_fast"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Fast"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Normal"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Low"));
        this.delayFacil.addItem(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaHija.Too_low"));
        this.delayFacil.setSelectedIndex(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        panelPrincipal = new javax.swing.JPanel();
        panelPestanias = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        iconoEnlace = new javax.swing.JLabel();
        etiquetaNombre = new javax.swing.JLabel();
        nombreEnlace = new javax.swing.JTextField();
        verNombre = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        selectorExtremoDerecho = new javax.swing.JComboBox();
        selectorExtremoIzquierdo = new javax.swing.JComboBox();
        selectorPuertoDerecho = new javax.swing.JComboBox();
        selectorPuertoIzquierdo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelRapido = new javax.swing.JPanel();
        iconoEnlace1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        delayFacil = new javax.swing.JComboBox();
        panelAvanzado = new javax.swing.JPanel();
        iconoEnlace2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        delayAvanzado = new javax.swing.JSlider();
        etiquetaDelay = new javax.swing.JLabel();
        panelBotones = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setTitle(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.titulo"));
        setModal(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        panelPrincipal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelPestanias.setFont(new java.awt.Font("Dialog", 0, 12));
        panelGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ENLACE));
        iconoEnlace.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.descripcion"));
        panelGeneral.add(iconoEnlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        etiquetaNombre.setFont(new java.awt.Font("Dialog", 0, 12));
        etiquetaNombre.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        etiquetaNombre.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.etiquetaNombre"));
        panelGeneral.add(etiquetaNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 110, -1));

        nombreEnlace.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.NombreEnlace"));
        panelGeneral.add(nombreEnlace, new org.netbeans.lib.awtextra.AbsoluteConstraints(135, 80, 110, -1));

        verNombre.setFont(new java.awt.Font("Dialog", 0, 12));
        verNombre.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.verNombre"));
        verNombre.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.VerNombre"));
        panelGeneral.add(verNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 80, -1, -1));

        jLabel1.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ENLACE));
        panelGeneral.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(158, 120, 50, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.etiquetaExtremoIzquierdo"));
        panelGeneral.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 135, 130, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.etiquetaExtremoDerecho"));
        panelGeneral.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 135, 130, -1));

        selectorExtremoDerecho.setFont(new java.awt.Font("Dialog", 0, 12));
        selectorExtremoDerecho.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        selectorExtremoDerecho.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.ExtremoIzquierdo"));
        selectorExtremoDerecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCambioNodoDerecho(evt);
            }
        });

        panelGeneral.add(selectorExtremoDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 90, -1));

        selectorExtremoIzquierdo.setFont(new java.awt.Font("Dialog", 0, 12));
        selectorExtremoIzquierdo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "" }));
        selectorExtremoIzquierdo.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.extremoDerecho"));
        selectorExtremoIzquierdo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCambioNodoIzquierdo(evt);
            }
        });

        panelGeneral.add(selectorExtremoIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 160, 90, -1));

        selectorPuertoDerecho.setFont(new java.awt.Font("Dialog", 0, 12));
        selectorPuertoDerecho.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.puertoEntrada"));
        panelGeneral.add(selectorPuertoDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, -1, -1));

        selectorPuertoIzquierdo.setFont(new java.awt.Font("Dialog", 0, 12));
        selectorPuertoIzquierdo.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.tooltip.puertosalida"));
        panelGeneral.add(selectorPuertoIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(115, 160, -1, -1));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.:"));
        panelGeneral.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(105, 165, 10, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.:"));
        panelGeneral.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 165, 10, -1));

        panelPestanias.addTab(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.tabs.General"), panelGeneral);

        panelRapido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace1.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ASISTENTE));
        iconoEnlace1.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Rapida.Descripcion"));
        panelRapido.add(iconoEnlace1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Link_speed"));
        panelRapido.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, -1));

        delayFacil.setFont(new java.awt.Font("Dialog", 0, 12));
        delayFacil.setMaximumRowCount(6);
        delayFacil.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Personalizado", "Very high", "High", "Normal", "Low", "Very low" }));
        delayFacil.setSelectedIndex(3);
        delayFacil.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Select_the_link_speed"));
        delayFacil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnDelayFacil(evt);
            }
        });

        panelRapido.add(delayFacil, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 180, -1));

        panelPestanias.addTab(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.tabs.Fast"), panelRapido);

        panelAvanzado.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        iconoEnlace2.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.AVANZADA));
        iconoEnlace2.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Advanced_and_complete_link_configuration."));
        panelAvanzado.add(iconoEnlace2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, 335, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Link_delay"));
        panelAvanzado.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 105, 100, -1));

        delayAvanzado.setMajorTickSpacing(1000);
        delayAvanzado.setMaximum(500000);
        delayAvanzado.setMinimum(1);
        delayAvanzado.setMinorTickSpacing(1000);
        delayAvanzado.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Slide_it_to_set_the_link_delay."));
        delayAvanzado.setValue(125000);
        delayAvanzado.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                clicEnDelayAvanzado(evt);
            }
        });

        panelAvanzado.add(delayAvanzado, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 105, 150, -1));

        etiquetaDelay.setFont(new java.awt.Font("Dialog", 0, 10));
        etiquetaDelay.setForeground(new java.awt.Color(102, 102, 102));
        etiquetaDelay.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.500_ns."));
        panelAvanzado.add(etiquetaDelay, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 105, 70, -1));

        panelPestanias.addTab(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.tabs.Advanced"), panelAvanzado);

        panelPrincipal.add(panelPestanias, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 370, 240));

        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton2.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ACEPTAR));
        jButton2.setMnemonic(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.botones.mne.Aceptar").charAt(0));
        jButton2.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.boton.Ok"));
        jButton2.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Adds_the_link_to_the_topology."));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnAceptar(evt);
            }
        });

        panelBotones.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 15, 115, -1));

        jButton3.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton3.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.CANCELAR));
        jButton3.setMnemonic(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.botones.mne.Cancelar").charAt(0));
        jButton3.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaEnlace.boton.Cancel"));
        jButton3.setToolTipText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace.Cancel_the_operation"));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnCancelar(evt);
            }
        });

        panelBotones.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 15, 115, -1));

        panelPrincipal.add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 255, 400, 55));

        getContentPane().add(panelPrincipal, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 310));

        pack();
    }//GEN-END:initComponents

private void clicEnCambioNodoDerecho(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCambioNodoDerecho
    this.selectorPuertoDerecho.removeAllItems();
    this.selectorPuertoDerecho.addItem("");
    this.selectorPuertoDerecho.setSelectedIndex(0);
    if (this.selectorExtremoDerecho.getSelectedIndex() != 0) {
        TNodoTopologia seleccionado = topo.obtenerPrimerNodoLlamado((String) selectorExtremoDerecho.getSelectedItem());
        Iterator it = topo.obtenerIteradorNodos();
        TNodoTopologia nt;
        if (seleccionado != null) {
//          Actualizar los puertos de dicho nodo
            int i=0;
            for (i=0; i<seleccionado.obtenerPuertos().obtenerNumeroDePuertos(); i++) {
                if (seleccionado.obtenerPuertos().obtenerPuerto(i).estaLibre())
                    this.selectorPuertoDerecho.addItem(""+i);
            }
//          Actualizar los puertos de dicho nodo
        }
    }
}//GEN-LAST:event_clicEnCambioNodoDerecho

private void clicEnCambioNodoIzquierdo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCambioNodoIzquierdo
    this.selectorExtremoDerecho.removeAllItems();
    this.selectorExtremoDerecho.addItem("");
    this.selectorExtremoDerecho.setSelectedIndex(0);
    this.selectorPuertoIzquierdo.removeAllItems();
    this.selectorPuertoIzquierdo.addItem("");
    this.selectorPuertoIzquierdo.setSelectedIndex(0);
    this.selectorPuertoDerecho.removeAllItems();
    this.selectorPuertoDerecho.addItem("");
    this.selectorPuertoDerecho.setSelectedIndex(0);
    if (this.selectorExtremoIzquierdo.getSelectedIndex() != 0) {
        TNodoTopologia seleccionado = topo.obtenerPrimerNodoLlamado((String) selectorExtremoIzquierdo.getSelectedItem());
        Iterator it = topo.obtenerIteradorNodos();
        TNodoTopologia nt;
        if (seleccionado != null) {
//          Actualizar los puertos de dicho nodo
            int i=0;
            for (i=0; i<seleccionado.obtenerPuertos().obtenerNumeroDePuertos(); i++) {
                if (seleccionado.obtenerPuertos().obtenerPuerto(i).estaLibre())
                    this.selectorPuertoIzquierdo.addItem(""+i);
            }
//          Actualizar los puertos de dicho nodo
            while (it.hasNext()) {
                nt = (TNodoTopologia) it.next();
                if (!nt.obtenerNombre().equals(seleccionado.obtenerNombre())) {
                    if (nt.tienePuertosLibres()) {
                        if (!topo.existeEnlace(nt.obtenerIdentificador(), seleccionado.obtenerIdentificador())) {
                            switch (seleccionado.obtenerTipo()) {
                                case TNodoTopologia.EMISOR: {
                                    if ((nt.obtenerTipo() == TNodoTopologia.LER) || (nt.obtenerTipo() == TNodoTopologia.LERA)) {
                                        selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    }
                                    break;
                                }
                                case TNodoTopologia.RECEPTOR: {
                                    if ((nt.obtenerTipo() == TNodoTopologia.LER) || (nt.obtenerTipo() == TNodoTopologia.LERA)) {
                                        selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    }
                                    break;
                                }
                                case TNodoTopologia.LER: {
                                    selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    break;
                                }
                                case TNodoTopologia.LERA: {
                                    selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    break;
                                }
                                case TNodoTopologia.LSR: {
                                    if ((nt.obtenerTipo() == TNodoTopologia.LER) || (nt.obtenerTipo() == TNodoTopologia.LERA)
                                       || (nt.obtenerTipo() == TNodoTopologia.LSR) || (nt.obtenerTipo() == TNodoTopologia.LSRA)) {
                                        selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    }
                                    break;
                                }
                                case TNodoTopologia.LSRA: {
                                    if ((nt.obtenerTipo() == TNodoTopologia.LER) || (nt.obtenerTipo() == TNodoTopologia.LERA)
                                       || (nt.obtenerTipo() == TNodoTopologia.LSR) || (nt.obtenerTipo() == TNodoTopologia.LSRA)) {
                                        selectorExtremoDerecho.addItem(nt.obtenerNombre());
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}//GEN-LAST:event_clicEnCambioNodoIzquierdo

/**
 * Este m�todo carga todos los nodos de la topolog�a en una lista para poder
 * seleccionar dentro de ella los dos nodos que va a unir el enlace.
 * @since 1.0
 */
public void cargarNodosPorDefecto() {
    Iterator it = topo.obtenerIteradorNodos();
    TNodoTopologia nt;
    while (it.hasNext()) {
        nt = (TNodoTopologia) it.next();
        if (nt.tienePuertosLibres()) {
            selectorExtremoIzquierdo.addItem(nt.obtenerNombre());
            selectorExtremoIzquierdo.setSelectedIndex(0);
        }
    }
}

private void clicEnDelayFacil(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnDelayFacil
    switch (this.delayFacil.getSelectedIndex()) {
        case 0: {
            // dejo como est� el delay en la configuraci�n avanzada
            this.delayFacil.setSelectedIndex(0);
            break;
        }
        case 1: {
            this.delayAvanzado.setValue(1000);
            this.delayFacil.setSelectedIndex(1);
            break;
        }
        case 2: {
            this.delayAvanzado.setValue(62500);
            this.delayFacil.setSelectedIndex(2);
            break;
        }
        case 3: {
            this.delayAvanzado.setValue(125000);
            this.delayFacil.setSelectedIndex(3);
            break;
        }
        case 4: {
            this.delayAvanzado.setValue(187500);
            this.delayFacil.setSelectedIndex(4);
            break;
        }
        case 5: {
            this.delayAvanzado.setValue(250000);
            this.delayFacil.setSelectedIndex(5);
            break;
        }
    }
    this.etiquetaDelay.setText(this.delayAvanzado.getValue() + java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace._ns."));
}//GEN-LAST:event_clicEnDelayFacil

private void clicEnDelayAvanzado(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_clicEnDelayAvanzado
    this.delayFacil.setSelectedIndex(0);
    this.etiquetaDelay.setText(this.delayAvanzado.getValue() + java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("JVentanaEnlace._ns."));
}//GEN-LAST:event_clicEnDelayAvanzado

private void clicEnCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnCancelar
    if (this.reconfigurando) {
        configEnlace.ponerNombre(BCKUPNombre);
        configEnlace.ponerMostrarNombre(BCKUPMostrarNombre);
        configEnlace.ponerDelay(BCKUPDelay);
        this.reconfigurando = false;
        configEnlace.ponerValida(true);
    } else {
        configEnlace.ponerValida(false);
    }
    this.setVisible(false);
    this.dispose();
}//GEN-LAST:event_clicEnCancelar

private void clicEnAceptar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnAceptar
    configEnlace.ponerValida(true);
    configEnlace.ponerNombre(nombreEnlace.getText());
    configEnlace.ponerMostrarNombre(verNombre.isSelected());
    configEnlace.ponerDelay(this.delayAvanzado.getValue());
    
    if (!this.reconfigurando) {
        configEnlace.ponerNombreExtremo1((String) selectorExtremoIzquierdo.getSelectedItem());
        configEnlace.ponerNombreExtremo2((String) selectorExtremoDerecho.getSelectedItem());
        configEnlace.calcularTipo(topo);
        if (((String)this.selectorPuertoIzquierdo.getSelectedItem()).equals("")) {
            configEnlace.ponerPuertoExtremo1(-1);
        } else {
            String aux = (String) this.selectorPuertoIzquierdo.getSelectedItem();
            int aux2 = Integer.valueOf(aux).intValue();
            configEnlace.ponerPuertoExtremo1(aux2);
        }
        if (((String)this.selectorPuertoDerecho.getSelectedItem()).equals("")) {
            configEnlace.ponerPuertoExtremo2(-1);
        } else {
            String aux = (String) this.selectorPuertoDerecho.getSelectedItem();
            int aux2 = Integer.valueOf(aux).intValue();
            configEnlace.ponerPuertoExtremo2(aux2);
        }
    }
    int error = configEnlace.comprobar(topo, this.reconfigurando);
    if (error != TConfigEnlace.CORRECTA) {
        JVentanaAdvertencia va = new JVentanaAdvertencia(ventanaPadre, true, dispensadorDeImagenes);
        va.mostrarMensaje(configEnlace.obtenerMensajeError(error));
        va.show();
    } else {
        this.setVisible(false);
        this.dispose();
    }
}//GEN-LAST:event_clicEnAceptar

    /** Closes the dialog */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        configEnlace.ponerValida(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    /**
     * Este m�todo permite cargar en la ventana la configuraci�n actual del enlace que estamos
     * configurando.
     * @since 1.0
     * @param recfg TRUE indica que se est� reconfigurando el enlace. FALSE indica que el enlace se
     * est� insertando nuevo.
     * @param tcenlace El enlace que estamos configurando.
     */    
    public void ponerConfiguracion(TConfigEnlace tcenlace, boolean recfg) {
        configEnlace = tcenlace;
        if (recfg) {
            this.reconfigurando = recfg;
            BCKUPNombre = tcenlace.obtenerNombre();
            BCKUPMostrarNombre = tcenlace.obtenerMostrarNombre();
            BCKUPDelay = tcenlace.obtenerDelay();

            this.nombreEnlace.setText(tcenlace.obtenerNombre());
            this.verNombre.setSelected(tcenlace.obtenerMostrarNombre());
            this.delayFacil.setSelectedIndex(0);
            this.delayAvanzado.setValue(tcenlace.obtenerDelay());
            
            this.selectorExtremoIzquierdo.setEnabled(false);
            this.selectorPuertoIzquierdo.setEnabled(false);
            this.selectorExtremoDerecho.setEnabled(false);
            this.selectorPuertoDerecho.setEnabled(false);
            
            this.selectorExtremoIzquierdo.removeAllItems();
            this.selectorExtremoIzquierdo.addItem(tcenlace.obtenerNombreExtremo1());
            this.selectorExtremoIzquierdo.setSelectedIndex(0);

            this.selectorPuertoIzquierdo.removeAllItems();
            this.selectorPuertoIzquierdo.addItem(""+tcenlace.obtenerPuertoExtremo1());
            this.selectorPuertoIzquierdo.setSelectedIndex(0);
            
            this.selectorExtremoDerecho.removeAllItems();
            this.selectorExtremoDerecho.addItem(tcenlace.obtenerNombreExtremo2());
            this.selectorExtremoDerecho.setSelectedIndex(0);

            this.selectorPuertoDerecho.removeAllItems();
            this.selectorPuertoDerecho.addItem(""+tcenlace.obtenerPuertoExtremo2());
            this.selectorPuertoDerecho.setSelectedIndex(0);
        }
    }

    private boolean reconfigurando;
    
    private String BCKUPNombre;
    private boolean BCKUPMostrarNombre;
    private boolean BCKUPCrearEstadisticas;
    private int BCKUPDelay;
    
    private TDispensadorDeImagenes dispensadorDeImagenes;
    private Frame ventanaPadre;
    private TTopologia topo;
    private TConfigEnlace configEnlace;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSlider delayAvanzado;
    private javax.swing.JComboBox delayFacil;
    private javax.swing.JLabel etiquetaDelay;
    private javax.swing.JLabel etiquetaNombre;
    private javax.swing.JLabel iconoEnlace;
    private javax.swing.JLabel iconoEnlace1;
    private javax.swing.JLabel iconoEnlace2;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField nombreEnlace;
    private javax.swing.JPanel panelAvanzado;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTabbedPane panelPestanias;
    private javax.swing.JPanel panelPrincipal;
    private javax.swing.JPanel panelRapido;
    private javax.swing.JComboBox selectorExtremoDerecho;
    private javax.swing.JComboBox selectorExtremoIzquierdo;
    private javax.swing.JComboBox selectorPuertoDerecho;
    private javax.swing.JComboBox selectorPuertoIzquierdo;
    private javax.swing.JCheckBox verNombre;
    // End of variables declaration//GEN-END:variables

}
