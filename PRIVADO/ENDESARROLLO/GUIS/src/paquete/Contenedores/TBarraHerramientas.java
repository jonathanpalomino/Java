/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import paquete.TBarraEstado;
import paquete.utiles.ControlTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import ultimo.JPTextField;

public class TBarraHerramientas extends JPanel
        implements Serializable {

    protected static final int NORMAL = 0;
    protected static final int HUNDIDO = 1;
    protected static final int LEVANTADO = 2;
    public static final int CONTROL_DEFECTO = 0;
    public static final int SIEMPRE_ACTIVO = 1;
    public static final int SIEMPRE_DESACTIVADO = 2;
    static final String BARRA_VACIA = "DatosBHVacia";
    static final String CLASE_CONTROL_TECLAS = "mapfre.trn.ControlTeclas";
    static final String TECLA = "$tecla";
    static final String OBJETO = "$objeto";
    static final String COMPONENTE_ACTIVO = "Globales";
    static final String DIR_IMAGENES = Herramientas.getRutarepLocal() + "/images/";
    private int numeroSet = 0;
    private Hashtable htbCodigo = new Hashtable();
    private JLabel[] lblIcono;
    private TSeparador[] sprIcono;
    private int botonActual;
    private int botonPulsado;
    private Object propietarioFlags = null;
    private Object objetoPadre = null;
    private Object[] objetosPropietarios = null;
    private SymMouse aSymMouse = new SymMouse();
    private String botonesDefecto = "DatosBHVacia";
    private Botones botonesAutomatico = new Botones();
    private Botones botonesManual = new Botones();
    private Botones botones = new Botones();
    private Icon[] imgIcono = null;
    private EtchedBorder bordeInicio1 = new EtchedBorder();
    private EmptyBorder bordeVacio1 = new EmptyBorder(1, 1, 2, 2);
    private EmptyBorder bordeVacio2 = new EmptyBorder(1, 1, 0, 0);
    private EmptyBorder bordeVacio3 = new EmptyBorder(0, 0, 1, 1);
    private CompoundBorder bordeInicio = new CompoundBorder(this.bordeInicio1, this.bordeVacio1);
    private SoftBevelBorder bordeNormal1 = new SoftBevelBorder(0);
    private CompoundBorder bordeNormal = new CompoundBorder(this.bordeNormal1, this.bordeVacio3);
    private SoftBevelBorder bordePulsado1 = new SoftBevelBorder(1);
    private CompoundBorder bordePulsado = new CompoundBorder(this.bordePulsado1, this.bordeVacio2);
    private int iBotonActual;
    TVentana ventanaActual = null;

    public TBarraHerramientas() {
        setLayout(new FlowLayout(0, 5, 5));
        setSize(0, 0);

        inicio();
        refrescarBarra(Globales.getComponenteActual());
    }

    public TBarraHerramientas(Object padre) {
        this.objetoPadre = padre;
        setLayout(new FlowLayout(0, 5, 5));
        setSize(79, 26);
        refrescarBarra(Globales.getComponenteActual());
    }

    private void inicio() {
        setBotonesDefecto("DatosBHVacia");
    }

    public void setPropietarioFlags(Object poPropietarioFlags) {
        this.propietarioFlags = poPropietarioFlags;
    }

    public Object getPropietarioFlags() {
        return this.propietarioFlags;
    }

    public void setBotones(Object pObjetoPadre, String[] pIconos, String[] pPropietarios, String[] pFlags, String[] pMetodos, String[] pPulsados, String[] pTooltips) {
        setObjetoPadre(pObjetoPadre);
        setIconosBotones(pIconos);
        setPropietariosBotones(pPropietarios);
        setFlagsBotones(pFlags);
        setMetodosBotones(pMetodos);
        setTooltipsBotones(pTooltips);
        setPulsadosBotones(pPulsados);
    }

    public void setObjetoPadre(Object pObjetoPadre) {
        this.objetoPadre = pObjetoPadre;
    }

    public Object getObjetoPadre() {
        if (this.objetoPadre == null) {
            this.objetoPadre = Globales.getPadre(this);
        }
        return this.objetoPadre;
    }

    private void setObjetoMetodo(int indice, Object pPropietario, String pMetodo) {
        if ((indice >= this.botones.numPropietarios) || (indice < 0)) {
            return;
        }
        if (pPropietario == null) {
            return;
        }
        if (this.objetosPropietarios == null) {
            this.objetosPropietarios = new Object[this.botones.numPropietarios];
            for (int i = 0; i < this.botones.numPropietarios; i++) {
                this.objetosPropietarios[i] = null;
            }
        }
        this.objetosPropietarios[indice] = pPropietario;
        if ((!pMetodo.trim().equals("")) && (pMetodo != null)) {
            this.botones.metodos[indice] = pMetodo;
        }
    }

    public void setIconoBoton(int piBoton, String psIcono) {
        if (this.botones.iconos.length > piBoton) {
            this.botones.iconos[piBoton] = psIcono;
            this.imgIcono[piBoton] = Herramientas.getImagenIcono(this.botones.iconos[piBoton]);
            this.lblIcono[piBoton].setIcon(this.imgIcono[piBoton]);
            this.lblIcono[piBoton].setOpaque(false);
        }
    }

    public void setActivacionBoton(int piIndice, int piActivacion) {
        if (piIndice < this.botones.numActivos) {
            this.botones.activacion[piIndice] = piActivacion;
            if (piActivacion == 1) {
                this.lblIcono[piIndice].setEnabled(true);
            } else if (piActivacion == 2) {
                this.lblIcono[piIndice].setEnabled(false);
            } else if (piActivacion == 0) {
                refrescarBarra();
            } else {
                TBarraEstado.setMensajeError("Error: el 2º parametro solo permite valores 0, 1 y 2");
            }
        }
    }

    public int getActivacionBoton(int piIndice) {
        if (piIndice < this.botones.numActivos) {
            return this.botones.activacion[piIndice];
        }
        return -1;
    }

    private void setActivoBoton(int piIndice, boolean pbActivo) {
        if (piIndice < this.botones.numActivos) {
            this.botones.activos[piIndice] = pbActivo;
        }
        this.lblIcono[piIndice].setEnabled(pbActivo);
    }

    private boolean isActivoBoton(int piIndice) {
        if (piIndice < this.botones.numActivos) {
            return this.botones.activos[piIndice];
        }
        return false;
    }

    public void setPulsadoBoton(int piIndice, boolean pbPulsado) {
        Boolean bPulsado = new Boolean(pbPulsado);
        if (piIndice < this.botones.numPulsados) {
            this.botones.pulsados[piIndice] = bPulsado.toString();
            if (this.lblIcono[piIndice].isEnabled()) {
                if (pbPulsado) {
                    this.lblIcono[piIndice].setBorder(this.bordePulsado);
                } else {
                    this.lblIcono[piIndice].setBorder(this.bordeNormal);
                }
                this.lblIcono[piIndice].repaint();
            }
        }
    }

    private void setPulsadoBoton(int piIndice, int estado) {
        this.botones.estadoPulsacion[piIndice] = estado;
        actualizaPulsados(piIndice, estado);
    }

    public boolean isPulsadoBoton(int pnIndice) {
        if (pnIndice < this.botones.numPulsados) {
            return new Boolean(this.botones.pulsados[pnIndice]).booleanValue();
        }
        return false;
    }

    protected void iniciaTooltipsBotones(String[] pTooltips) {
        this.botonesAutomatico.tooltips = pTooltips;
        this.botones.tooltips = pTooltips;
        if (this.botones.tooltips == null) {
            this.botonesAutomatico.numTools = 0;
            this.botones.numTools = 0;
            return;
        }
        this.botonesAutomatico.numTools = pTooltips.length;
        this.botones.numTools = pTooltips.length;

        actualizaTooltips();
    }

    public void setTooltipsBotones(String[] pTooltips) {
        this.botonesManual.tooltips = pTooltips;
        if (Beans.isDesignTime()) {
            return;
        }
        if (this.botonesManual.tooltips == null) {
            this.botonesManual.numTools = 0;
            return;
        }
        this.botonesManual.numTools = pTooltips.length;
        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String[] getTooltipsBotones() {
        return this.botonesManual.tooltips;
    }

    protected void iniciaPulsadosBotones(String[] pPulsados) {
        if (pPulsados != null) {
            this.botones.estadoPulsacion = new int[pPulsados.length];
            for (int i = 0; i < this.botones.estadoPulsacion.length; i++) {
                this.botones.estadoPulsacion[i] = 0;
            }
        } else {
            this.botones.estadoPulsacion = new int[0];
        }
        this.botonesAutomatico.estadoPulsacion = this.botones.estadoPulsacion;

        this.botonesAutomatico.pulsados = pPulsados;
        this.botones.pulsados = pPulsados;
        if (this.botones.pulsados == null) {
            this.botonesAutomatico.numPulsados = 0;
            this.botones.numPulsados = 0;
            return;
        }
        this.botonesAutomatico.numPulsados = pPulsados.length;
        this.botones.numPulsados = pPulsados.length;
        actualizaPulsados();
    }

    public void setPulsadosBotones(String[] pPulsado) {
        this.botonesManual.pulsados = pPulsado;
        if (this.botonesManual.pulsados == null) {
            this.botonesManual.numPulsados = 0;
            return;
        }
        this.botonesManual.numPulsados = pPulsado.length;

        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String[] getPulsadosBotones() {
        return this.botonesManual.pulsados;
    }

    protected void iniciaPropietariosBotones(String[] pPropietarios) {
        this.botonesAutomatico.propietarios = pPropietarios;
        this.botones.propietarios = pPropietarios;
        if (this.botones.propietarios == null) {
            this.botonesAutomatico.numPropietarios = 0;
            this.botones.numPropietarios = 0;
            return;
        }
        this.botonesAutomatico.numPropietarios = pPropietarios.length;
        this.botones.numPropietarios = pPropietarios.length;
    }

    public void setPropietariosBotones(String[] pPropietarios) {
        this.botonesManual.propietarios = pPropietarios;
        if (this.botonesManual.propietarios == null) {
            this.botonesManual.numPropietarios = 0;
            return;
        }
        this.botonesManual.numPropietarios = pPropietarios.length;
        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String[] getPropietariosBotones() {
        return this.botonesManual.propietarios;
    }

    /**
     * @deprecated
     */
    protected void iniciaTiposBotones(String[] pTipos) {
        iniciaFlagsBotones(pTipos);
    }

    /**
     * @deprecated
     */
    public void setTiposBotones(String[] pTipos) {
        setFlagsBotones(pTipos);
    }

    /**
     * @deprecated
     */
    public String[] getTiposBotones() {
        return getFlagsBotones();
    }

    protected void iniciaFlagsBotones(String[] pFlags) {
        this.botonesAutomatico.flags = pFlags;
        this.botones.flags = pFlags;
        if (this.botones.flags == null) {
            this.botonesAutomatico.numActivos = 0;
            this.botones.numActivos = 0;
            return;
        }
        this.botonesAutomatico.numActivos = pFlags.length;
        this.botones.numActivos = pFlags.length;
        this.botones.activos = new boolean[this.botones.numActivos];
        this.botones.activacion = new int[this.botones.numActivos];
        for (int x = 0; x < this.botones.numActivos; x++) {
            this.botones.activacion[x] = 0;
        }
    }

    public void setFlagsBotones(String[] pFlags) {
        this.botonesManual.flags = pFlags;
        if (this.botonesManual.flags == null) {
            this.botonesManual.numActivos = 0;
            return;
        }
        this.botonesManual.numActivos = pFlags.length;
        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String[] getFlagsBotones() {
        return this.botonesManual.flags;
    }

    protected void iniciaMetodosBotones(String[] pMetodos) {
        this.botonesAutomatico.metodos = pMetodos;
        this.botones.metodos = pMetodos;
        if (this.botones.metodos == null) {
            this.botonesAutomatico.numMetodos = 0;
            this.botones.numMetodos = 0;
            return;
        }
        this.botonesAutomatico.numMetodos = pMetodos.length;
        this.botones.numMetodos = pMetodos.length;
    }

    /**
     * @deprecated
     */
    public void setMetodosBotones(String[] pMetodos) {
        setMetodosAccion(pMetodos);
    }

    public void setMetodosAccion(String[] pMetodos) {
        this.botonesManual.metodos = pMetodos;
        if ((this.botonesManual.metodos == null) || (this.botonesManual.metodos.equals(""))) {
            this.botonesManual.numMetodos = 0;
            return;
        }
        this.botonesManual.numMetodos = pMetodos.length;
        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    /**
     * @deprecated
     */
    public String[] getMetodosBotones() {
        return getMetodosAccion();
    }

    public String[] getMetodosAccion() {
        return this.botonesManual.metodos;
    }

    protected void iniciaIconosBotones(String[] pIconos) {
        this.botonesAutomatico.iconos = pIconos;
        this.botones.iconos = pIconos;
        if (this.botones.iconos == null) {
            this.botonesAutomatico.numIconos = 0;
            this.botones.numIconos = 0;
            return;
        }
        this.botonesAutomatico.numIconos = pIconos.length;
        this.botones.numIconos = pIconos.length;

        actualizaIconos();
    }

    public void setIconosBotones(String[] pIconos) {
        this.botonesManual.iconos = pIconos;
        if (pIconos == null) {
            this.botones.numIconos = 0;
            return;
        }
        this.botonesManual.numIconos = pIconos.length;
        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String[] getIconosBotones() {
        return this.botonesManual.iconos;
    }

    public void setBotonesDefecto(String psBotonesDefecto) {
        if ((psBotonesDefecto == "null") || (psBotonesDefecto == null)) {
            return;
        }
        this.botonesDefecto = psBotonesDefecto;

        if (getBotonesDefecto().equals("DatosBHVacia")) {
            iniciaIconosBotones(null);
            iniciaTooltipsBotones(null);
            iniciaPulsadosBotones(null);
            iniciaPropietariosBotones(null);
            iniciaFlagsBotones(null);
            iniciaMetodosBotones(null);
            removeAll();
            return;
        }

        Class clase = null;
        DatosBH datosBH = null;
        Class[] claseArg = new Class[1];
        String path = "mapfre.com.c.GUI.";
        claseArg[0] = getClass();
        Method metodo = null;
        try {
            clase = Class.forName(path + getBotonesDefecto().trim());
            datosBH = (DatosBH) clase.newInstance();
        } catch (Exception e) {
            TBarraEstado.setMensajeError("No se ha encontrado la clase " + getBotonesDefecto());
            e.printStackTrace();
        }

        datosBH.datosBH(this);

        if (this.numeroSet < 7) {
            this.numeroSet += 1;
        }
        if ((this.numeroSet == 7) || ((this.numeroSet == 6) && (getBotonesDefecto().equals("DatosBHVacia")))) {
            actualiza();
        }
    }

    public String getBotonesDefecto() {
        return this.botonesDefecto;
    }

    private void actualizaTooltips() {
        String sCodModulo = "";
        String sCodTexto = "";
        String sTextoTooltips = "";

        for (int x = 0; x < this.botones.numTools; x++) {
            StringTokenizer cadenas = new StringTokenizer(this.botones.tooltips[x], ",");
            sCodModulo = cadenas.nextToken();
            sCodTexto = cadenas.nextToken();
            sTextoTooltips = Globales.leeCodigoTexto(sCodModulo, new Integer(sCodTexto).intValue());
            this.lblIcono[x].setToolTipText(sTextoTooltips);
        }
    }

    private void actualizaPulsados(int i, int estado) {
        if (estado == 0) {
            this.lblIcono[i].setBorder(this.bordeInicio);
        } else if (estado == 2) {
            this.lblIcono[i].setBorder(this.bordeNormal);
        } else if (estado == 1) {
            this.lblIcono[i].setBorder(this.bordePulsado);
        }
    }

    private void actualizaPulsados() {
        if (this.botones.estadoPulsacion != null) {
            for (int x = 0; x < this.botones.estadoPulsacion.length; x++) {
                actualizaPulsados(x, this.botones.estadoPulsacion[x]);
            }
        }
    }

    private void actualizaIconos() {
        int nNumSeparadores = 0;
        int x;
        TSeparador sprSeparadorBarras = new TSeparador();
        removeAll();

        int nNumLineas = this.botones.iconos.length;

        for (x = 0; x < nNumLineas; x++) {
            if ((this.botones.iconos[x].trim().equals("|")) || (this.botones.iconos[x].trim().equals(""))) {
                nNumSeparadores++;
            }
        }
        this.botones.numIconos = (nNumLineas - nNumSeparadores);
        this.lblIcono = new JLabel[this.botones.numIconos];

        this.sprIcono = new TSeparador[nNumSeparadores];
        this.imgIcono = new ImageIcon[this.botones.numIconos];
        x = 0;
        int y = 0;
        for (int z = 0; x < this.botones.numIconos; y++) {
            if ((this.botones.iconos[y].trim().equals("|")) || (this.botones.iconos[y].trim().equals(""))) {
                this.sprIcono[z] = new TSeparador();
                if (this.botones.iconos[y].trim().equals("|")) {
                    this.sprIcono[z].setTipoSeparador("TSeparador.VERTICAL");
                } else {
                    this.sprIcono[z].setTipoSeparador("TSeparador.VACIO");
                }
                this.sprIcono[z].setLayout(new FlowLayout(1, 5, 5));
                add(this.sprIcono[z]);
                z++;
            } else {
                this.imgIcono[x] = Herramientas.getImagenIcono(this.botones.iconos[y]);
                this.lblIcono[x] = new JLabel();
                this.htbCodigo.put(this.lblIcono[x].hashCode(), new Integer(x));
                this.lblIcono[x].setHorizontalTextPosition(0);
                this.lblIcono[x].setHorizontalAlignment(0);
                this.lblIcono[x].setAlignmentY(0.0F);
                this.lblIcono[x].setOpaque(false);

                this.lblIcono[x].setBorder(this.bordeInicio);

                this.lblIcono[x].setIcon(this.imgIcono[x]);
                if (!Beans.isDesignTime()) {
                    this.lblIcono[x].addMouseListener(this.aSymMouse);
                }
                add(this.lblIcono[x]);
                x++;
            }
        }
    }

    private void ejecutaMetodo(MouseEvent event) {
        Integer miEnt = (Integer) this.htbCodigo.get(event.getComponent().hashCode());
        JLabel object = (JLabel) event.getSource();
        int indice = miEnt.intValue();

        if (!this.lblIcono[indice].isEnabled()) {
            return;
        }
        if (this.botonActual == this.botonPulsado) {
            object.setBorder(this.bordeNormal);
        }
        object.repaint();

        if (this.botonActual == this.botonPulsado) {
            this.botonPulsado = -1;
            pulsaBoton(this.botonActual);
        }
    }

    protected int getIndexBotonActual() {
        return this.iBotonActual;
    }

    public void pulsaBoton(int indice) {
        this.iBotonActual = indice;

        Class clase = null;
        Method metodo = null;
        Object result = null;
        Object objeto = null;

        if (indice > this.botones.numPropietarios) {
            return;
        }
        String propietario = this.botones.propietarios[indice];
        if ((this.objetosPropietarios != null) && (this.objetosPropietarios[indice] != null)) {
            objeto = this.objetosPropietarios[indice];
            clase = objeto.getClass();
        } else if ((propietario.equalsIgnoreCase("Globales")) || (propietario.equalsIgnoreCase("$tecla"))) {
            objeto = getPadre().getComponenteActual();
            clase = objeto.getClass();
        } else if (propietario.equalsIgnoreCase("$objeto")) {
            objeto = getObjetoPadre();
            clase = objeto.getClass();
        } else {
            objeto = null;
            try {
                clase = Class.forName(propietario);
            } catch (ClassNotFoundException e) {
                TBarraEstado.setMensajeError("No se ha encontrado la clase " + this.botones.propietarios[indice]);
            }
        }

        if (propietario.equalsIgnoreCase("$tecla")) {
            try {
                Component componente = (Component) objeto;
                Class claseEvento = Class.forName("mapfre.trn.ControlTeclas");
                Field campo = claseEvento.getDeclaredField(this.botones.metodos[indice]);
                int tecla = campo.getInt(null);
                int codigoTecla = ControlTeclas.getTecla(tecla);
                int modificadorTecla = ControlTeclas.getModificador(tecla);
                KeyEvent eventoTecla = new KeyEvent(componente, 401, 0L, modificadorTecla, codigoTecla);
                ControlTeclas.controlarTecla(tecla, (Component) objeto);
            } catch (Exception e) {
                e.printStackTrace();
                TBarraEstado.setMensajeError("Error en el evento de tecla nº " + (indice + 1) + ". " + e);
            }
            return;
        }

        try {
            if (indice > this.botones.numMetodos) {
                return;
            }
            metodo = clase.getMethod(this.botones.metodos[indice], null);
        } catch (Exception e) {
            TBarraEstado.setMensajeError("Error en llamada a getMetodo: " + this.botones.metodos[indice]);
        }
        try {
            result = metodo.invoke(objeto, null);
        } catch (Exception e) {
            TBarraEstado.setMensajeError("Error en llamada a metodo: " + this.botones.metodos[indice]);
            e.printStackTrace();
        }
    }

    public void refrescarBarra() {
        refrescarBarra(Globales.getComponenteActual());
    }

    public void refrescarBarra(Object pRealComponente) {
        Object pComponente = null;
        if (this.propietarioFlags != null) {
            pComponente = this.propietarioFlags;
        } else {
            pComponente = pRealComponente;
        }

        if (pComponente == null) {
            return;
        }

        for (int i = 0; i < this.botones.numActivos; i++) {
            if (getActivacionBoton(i) == 0) {
                Object[] o = ejecutaFlag(this.botones.flags[i], pComponente);

                if ((pComponente instanceof JPTextField)) {
                    if ((!((JPTextField) pComponente).isEnabled()) && (getBotonesDefecto().equals("DatosBHGeneral")) && (i == 3)) {
                        setActivoBoton(i, false);
                    } else {
                        setActivoBoton(i, ((Boolean) o[0]).booleanValue());
                    }
                } else {
                    setActivoBoton(i, ((Boolean) o[0]).booleanValue());
                }

                int estado = ((Integer) o[1]).intValue();
                if (estado != 0) {
                    setPulsadoBoton(i, estado);
                }
                if (o[2] != null) {
                    this.lblIcono[i].setIcon(Herramientas.getImagenIcono((String) o[2]));
                }
            }
        }
    }

    private Object[] ejecutaFlag(String flag, Object o) {
        Object[] resultado = {Boolean.FALSE, new Integer(0)};

        if (flag.equals("")) {
            resultado[0] = Boolean.TRUE;
        } else {
            try {
                Class clase = o.getClass();
                Class[] params = new Class[0];
                Method metodo = clase.getMethod(flag, params);
                Object aux = metodo.invoke(o, params);

                if ((aux instanceof Boolean)) {
                    resultado[0] = ((Boolean) aux);
                } else if ((aux instanceof Object[])) {
                    resultado = (Object[]) aux;
                }
            } catch (NoSuchMethodException nse) {
                resultado[0] = Boolean.FALSE;
            } catch (Exception e) {
                resultado[0] = Boolean.FALSE;
                e.printStackTrace();
                TBarraEstado.setMensajeError(e.toString());
            }
        }

        return resultado;
    }

    private void creaArraysBotones() {
        String[] temp = (String[]) null;
        int x;
        int nNumDefecto = 0;
        temp = this.botonesAutomatico.tooltips;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numTools = (nNumDefecto + this.botonesManual.numTools);

        this.botones.tooltips = new String[this.botones.numTools];
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.tooltips[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numTools; x++) {
            this.botones.tooltips[(x + nNumDefecto)] = this.botonesManual.tooltips[x];
        }

        nNumDefecto = 0;
        temp = this.botonesAutomatico.pulsados;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numPulsados = (nNumDefecto + this.botonesManual.numPulsados);
        this.botones.pulsados = new String[this.botones.numPulsados];
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.pulsados[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numPulsados; x++) {
            this.botones.pulsados[(x + nNumDefecto)] = this.botonesManual.pulsados[x];
        }

        nNumDefecto = 0;
        temp = this.botonesAutomatico.propietarios;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numPropietarios = (nNumDefecto + this.botonesManual.numPropietarios);
        this.botones.propietarios = new String[this.botones.numPropietarios];
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.propietarios[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numPropietarios; x++) {
            this.botones.propietarios[(x + nNumDefecto)] = this.botonesManual.propietarios[x];
        }

        nNumDefecto = 0;
        temp = this.botonesAutomatico.flags;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numActivos = (nNumDefecto + this.botonesManual.numActivos);
        this.botones.flags = new String[this.botones.numActivos];
        this.botones.activos = new boolean[this.botones.numActivos];
        this.botones.activacion = new int[this.botones.numActivos];
        for (x = 0; x < this.botones.numActivos; x++) {
            this.botones.activacion[x] = 0;
        }
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.flags[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numActivos; x++) {
            this.botones.flags[(x + nNumDefecto)] = this.botonesManual.flags[x];
        }

        nNumDefecto = 0;
        temp = this.botonesAutomatico.metodos;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numMetodos = (nNumDefecto + this.botonesManual.numMetodos);
        this.botones.metodos = new String[this.botones.numMetodos];
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.metodos[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numMetodos; x++) {
            this.botones.metodos[(x + nNumDefecto)] = this.botonesManual.metodos[x];
        }

        nNumDefecto = 0;
        temp = this.botonesAutomatico.iconos;
        if (temp != null) {
            nNumDefecto = temp.length;
        }
        this.botones.numIconos = (nNumDefecto + this.botonesManual.numIconos);
        this.botones.iconos = new String[this.botones.numIconos];
        for (x = 0; x < nNumDefecto; x++) {
            this.botones.iconos[x] = temp[x];
        }
        for (x = 0; x < this.botonesManual.numIconos; x++) {
            this.botones.iconos[(x + nNumDefecto)] = this.botonesManual.iconos[x];
        }
    }

    private void actualiza() {
        creaArraysBotones();
        actualizaIconos();
        actualizaTooltips();
        actualizaPulsados();
    }

    public static Vector getBarrasHerramientas(Container contenedor) {
        Vector barras = new Vector();
        if ((contenedor instanceof Container)) {
            almacenaBarras(contenedor, barras);
        }
        return barras;
    }

    private TVentana getPadre() {
        if (this.ventanaActual == null) {
            Component p = Globales.getPadre(this);
            if (p != null) {
                this.ventanaActual = ((TVentana) p);
            }
        }
        return this.ventanaActual;
    }

    public JLabel[] getLabelsBotones() {
        return this.lblIcono;
    }

    private static void almacenaBarras(Container contenedor, Vector pBarras) {
        Component[] componentes = contenedor.getComponents();
        if (componentes != null) {
            for (int i = 0; i < contenedor.getComponentCount(); i++) {
                if ((componentes[i] instanceof TBarraHerramientas)) {
                    pBarras.add(componentes[i]);
                }
                if ((componentes[i] instanceof Container)) {
                    almacenaBarras((Container) componentes[i], pBarras);
                }
            }
        }
    }

    class Botones {

        private int[] estadoPulsacion = null;
        private String[] propietarios = null;
        private String[] flags = null;
        private String[] metodos = null;
        private String[] tooltips = null;
        private String[] iconos = null;
        private boolean[] activos;
        private int[] activacion;
        /**
         * @deprecated
         */
        private String[] pulsados = null;
        private int numIconos = 0;
        private int numPropietarios = 0;
        private int numMetodos = 0;
        private int numActivos = 0;
        private int numPulsados = 0;
        private int numTools = 0;

        Botones() {
        }
    }

    class SymMouse extends MouseAdapter {

        SymMouse() {
        }

        public void mouseReleased(MouseEvent event) {
            TBarraHerramientas.this.ejecutaMetodo(event);
        }

        /**
         * Invocado al entrar el puntero del ratón en el botón
         */
        public void mouseEntered(java.awt.event.MouseEvent event) {

            try {

                javax.swing.JLabel label = (javax.swing.JLabel) event.getSource();

                Integer miEnt = (Integer) htbCodigo.get("" + event.getComponent().hashCode());

                botonActual = miEnt.intValue();

                if (lblIcono[botonActual].isEnabled() == false) {
                    return;
                }



                if (botonActual > botones.numPulsados) {

                    label.setBorder(bordeNormal);

                } else if (new Boolean(botones.pulsados[botonActual]).booleanValue() == false) {

                    label.setBorder(bordeNormal);

                } else {

                    label.setBorder(bordePulsado);

                }

                label.repaint();

            } catch (Exception e) {

                e.printStackTrace();

                TBarraEstado.setMensajeError("Estado de boton no definido. " + e);

            }

        }

        /**
         * Invocado al salir el puntero del ratón del botón
         */
        public void mouseExited(java.awt.event.MouseEvent event) {

            try {
                javax.swing.JLabel label = (javax.swing.JLabel) (event.getSource());
                Integer miEnt = (Integer) htbCodigo.get("" + event.getComponent().hashCode());
                if (botonActual > botones.numPulsados) {
                    label.setBorder(bordeNormal);
                } else if (new Boolean(botones.pulsados[miEnt.intValue()]).booleanValue() == false) {
                    label.setBorder(bordeInicio);
                } else {
                    label.setBorder(bordePulsado);
                }

                label.repaint();

            } catch (Exception e) {

                e.printStackTrace();

                TBarraEstado.setMensajeError("Estado de boton no definido. " + e);

            }

        }

        public void mousePressed(MouseEvent event) {
            Integer miEnt = (Integer) TBarraHerramientas.this.htbCodigo.get(event.getComponent().hashCode());
            TBarraHerramientas.this.botonPulsado = miEnt.intValue();
            if (!TBarraHerramientas.this.lblIcono[TBarraHerramientas.this.botonPulsado].isEnabled()) {
                return;
            }
            JLabel object = (JLabel) event.getSource();

            object.setBorder(TBarraHerramientas.this.bordePulsado);
            object.repaint();
        }
    }
}