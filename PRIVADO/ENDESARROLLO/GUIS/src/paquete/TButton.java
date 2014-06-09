/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import paquete.Contenedores.TPanel;
import paquete.Contenedores.TVentana;
import paquete.utiles.ControlTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.Beans;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.swing.FocusManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import ultimo.JPTextField;

/**
 *
 * @author synccon
 */
public class TButton extends JButton implements PRedefinidorTeclas, TInputComponent {

    private static Border BORDE_JBUTTON = new JButton().getBorder();
    public static Border BORDE_NORMAL = new CompoundBorder(
            BORDE_JBUTTON,
            new EmptyBorder(2, 2, 2, 2));
    public static Border BORDE_RESALTADO = new CompoundBorder(
            BORDE_JBUTTON,
            new EtchedBorder());
    ImageIcon imagenNormal = null;
    String sImagenNormal = "";
    static final String DIR_IMAGENES = Herramientas.getRutarepLocal() + "/mapfre/trn/images/";
    private static final String PUNTOS_CADENA_CORTADA = "...";
    String textoReal = "";
    private String sugerencia = "";
    protected static final int METODO_PREBOTON = 0;
    protected static final int METODO_PREVALIDACION = 1;
    protected static final int METODO_POSTBOTON = 2;
    protected static final int METODO_TECLAS = 3;
    protected static final int METODO_ACCION = 4;
    protected static final int METODO_ACCION_CODIGO = 5;
    protected static final int METODO_ON_ERROR = 6;
    private boolean bMostrarTTT = true;
    Object[] aVal = null;
    private int _iTotalPara = 0;
    private Hashtable vParametros = new Hashtable();
    protected static JPTextField validador = null;
    protected String _metodoOnError = "";
    private boolean bEjecutarFuncion = true;
    String codModulo = "";
    String codTexto = "";
    String lsRestoTexto = "";
    protected boolean mayusculas = false;
    protected String parametros = "";
    private int _anchura = 0;
    protected boolean rellenar = false;
    private Component _padre = null;
    private String _metodoAccion = "";
    private String _metodoPostValidacion = "";
    private String _metodoPreBoton = "";
    private String _metodoTeclas = "";
    protected String funcionValidacion = "";
    private boolean hayMensajeError = false;
    private String _metodoPreValidacion = "";
    private String _metodoAccionCodigo = "";
    int tamCadena;
    int tamPunto;
    int numPuntos;
    TVentana miPadre = null;

    static {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception localException) {
        }
    }

    public TButton() {
        setSize(40, 30);

        setFont(new Font("SansSerif", 1, 12));
        if (!Beans.isDesignTime()) {
            SymKey aSymKey = new SymKey();
            addKeyListener(aSymKey);
            SymAction lSymAction = new SymAction();
            addActionListener(lSymAction);

            addFocusListener(TFocusListener.getFocusListener());
        }

        setBorder(BORDE_NORMAL);
    }

    public String getIcono() {
        return this.sImagenNormal;
    }

    public void setIcono(String psImagen) {
        this.sImagenNormal = psImagen;

        if ((psImagen != null) || (!psImagen.trim().equals(""))) {
            this.imagenNormal = Herramientas.getImagenIcono(psImagen);
        }
        setIcon(this.imagenNormal);
    }

    public String getMetodoOnError() {
        return this._metodoOnError;
    }

    public void setMetodoOnError(Component padre, String pmMetodo) {
        this._padre = padre;
        this._metodoOnError = pmMetodo;
    }

    /**
     * @deprecated
     */
    public String getParametros() {
        return getModuloCodigo();
    }

    public String getModuloCodigo() {
        return this.parametros;
    }

    public void setMostrarTTT(boolean pbMostrarTTT) {
        this.bMostrarTTT = pbMostrarTTT;
        if (!pbMostrarTTT) {
            setToolTipText(null);
        }
    }

    public boolean isMostrarTTT() {
        return this.bMostrarTTT;
    }

    public boolean isManagingFocus() {
        return true;
    }

    /**
     * @deprecated
     */
    public void setMetodoOnError(String pmMetodo) {
    }

    public void setEjecutarFuncion(boolean pbEjecuta) {
        this.bEjecutarFuncion = pbEjecuta;
    }

    public void setModuloCodigo(String parametros) {
        this.parametros = parametros;
        if (!Herramientas.nvl(parametros).equals("")) {
            try {
                StringTokenizer cadenas = new StringTokenizer(this.parametros, ",");
                this.codModulo = cadenas.nextToken().trim();
                this.codTexto = cadenas.nextToken().trim();
                if (cadenas.hasMoreTokens()) {
                    this.lsRestoTexto = cadenas.nextToken();
                }
            } catch (NoSuchElementException e) {
                Herramientas.trazar("TButton no tiene etiqueta definida", true);
                TBarraEstado.setMensajeError("TButton no tiene etiqueta definida");
                return;
            }
            recogeTexto();
        } else {
            this.textoReal = "";
            this.sugerencia = "";
        }
        formateaTexto();
    }

    /**
     * @deprecated
     */
    public void setParametros(String parametros) {
        setModuloCodigo(parametros);
    }

    public void setMayusculas(boolean mayusculas) {
        this.mayusculas = mayusculas;
        formateaTexto();
    }

    public boolean isMayusculas() {
        return this.mayusculas;
    }

    public void setAnchura(int piAnchura) {
        this._anchura = piAnchura;
    }

    public int getAnchura() {
        return this._anchura;
    }

    public void setRellenar(boolean rellenar) {
        this.rellenar = rellenar;
        formateaTexto();
    }

    public boolean isRellenar() {
        return this.rellenar;
    }

    /**
     * @deprecated
     */
    public void setMetodoPostValidacion() {
    }

    public void setMetodoPostValidacion(Component padre, String pmMetodo) {
        if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null"))) {
            return;
        }

        this._padre = padre;
        this._metodoPostValidacion = pmMetodo;
    }

    /**
     * @deprecated
     */
    public void setMetodoPostValidacion(String pmMetodo) {
    }

    public String getMetodoPostValidacion() {
        if (this._metodoPostValidacion == null) {
            return "";
        }
        return this._metodoPostValidacion;
    }

    public void setMetodoPreValidacion(Component padre, String psMetodo) {
        if ((psMetodo == null) || (psMetodo.trim().equals("")) || (psMetodo.trim().equals("null"))) {
            return;
        }
        this._padre = padre;
        this._metodoPreValidacion = psMetodo;
    }

    /**
     * @deprecated
     */
    public void setMetodoPreValidacion() {
    }

    /**
     * @deprecated
     */
    public void setMetodoPreValidacion(String pmMetodo) {
        if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null"))) {
            return;
        }
        this._metodoPreValidacion = pmMetodo;
    }

    public String getMetodoPreValidacion() {
        if (this._metodoPreValidacion == null) {
            return "";
        }
        return this._metodoPreValidacion;
    }

    /**
     * @deprecated
     */
    public void setMetodoPreBoton(Component padre, String pmMetodo) {
        if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null"))) {
            return;
        }
        this._padre = padre;
        this._metodoPreBoton = pmMetodo;
    }

    /**
     * @deprecated
     */
    public String getMetodoPreBoton() {
        if (this._metodoPreBoton == null) {
            return "";
        }
        return this._metodoPreBoton;
    }

    /**
     * @deprecated
     */
    public void setMetodoTeclas() {
    }

    public void setMetodoTeclas(Component padre, String pmMetodo) {
        if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null"))) {
            return;
        }
        this._padre = padre;
        this._metodoTeclas = pmMetodo;
    }

    /**
     * @deprecated
     */
    public void setMetodoTeclas(String pmMetodo) {
    }

    public Object getPadreMetodoAccion() {
        return this._padre;
    }

    public String getMetodoAccion() {
        return this._metodoAccion;
    }

    public String getMetodoAccionCodigo() {
        return this._metodoAccionCodigo;
    }

    public void setMetodoAccion(Component poPadre, String psMetodoAccion) {
        this._metodoAccion = psMetodoAccion;
        this._padre = poPadre;
    }

    public void setMetodoAccionCodigo(Component poPadre, String psMetodoAccion) {
        this._metodoAccionCodigo = psMetodoAccion;
        this._padre = poPadre;
    }

    /**
     * @deprecated
     */
    public void setMetodoAccion(String psMetodoAccion) {
        this._metodoAccion = psMetodoAccion;
    }

    /**
     * @deprecated
     */
    public void setMetodoAccionCodigo(String psMetodoAccion) {
        this._metodoAccionCodigo = psMetodoAccion;
    }

    public void setFuncionValidacion(String funcionValidacion) {
        this.funcionValidacion = funcionValidacion;
    }

    public String getFuncionValidacion() {
        return this.funcionValidacion;
    }

    public boolean ejecutaCampoSig() {
        if (getNextFocusableComponent() != null) {
            getNextFocusableComponent().requestFocus();
        } else {
            FocusManager.getCurrentManager().focusNextComponent(this);
        }

        return true;
    }

    public boolean moverLeft() {
        FocusManager.getCurrentManager().focusPreviousComponent(this);
        return true;
    }

    protected void recogeTexto() {
        String[] texto = Globales.getCodigoTextoHotKey(this.codModulo, new Integer(this.codTexto).intValue());
        this.textoReal = (texto[0] + " " + this.lsRestoTexto);
        this.sugerencia = Globales.getCodigoSugerencia(this.codModulo, new Integer(this.codTexto).intValue());
    }

    public void setText(String tex) {
        if (this.bMostrarTTT) {
            setToolTipText(this.sugerencia);
        } else {
            setToolTipText(null);
        }
        super.setText(tex);
    }

    private void formateaTexto() {
        if (Herramientas.nvl(this.textoReal).equals("")) {
            return;
        }
        String cadena = Herramientas.formateaTexto(this.textoReal, this.mayusculas, this.rellenar, getWidth());
        super.setText(cadena);
    }

    public void paint(Graphics g) {
        if (!super.getText().equals("")) {
            if ((getMnemonic() == 0) || (getParent() == null)) {
                char lCad = recuperaNemotecnico(super.getText());
                setMnemonic(lCad);
            }
        }
        super.paint(g);
    }

    String buscarBotones() {
        String lsCadena = new String("");
        Container lcont = getContenedorPadre(this);
        if (lcont != null) {
            lsCadena = buscarBotones(lcont, lsCadena);
        }
        return lsCadena;
    }

    String buscarBotones(Container pCont, String psCad) {
        Component[] pComps = pCont.getComponents();
        for (int x = 0; x < pComps.length; x++) {
            if (((pComps[x] instanceof Container)) && (((Container) pComps[x]).getComponentCount() > 0)) {
                psCad = buscarBotones((Container) pComps[x], psCad);
            } else if ((pComps[x] instanceof JButton)) {
                char lCar = Character.toUpperCase((char) ((JButton) pComps[x]).getMnemonic());

                if ((psCad.indexOf(lCar) < 0) && (Character.isLetter(lCar))) {
                    psCad = psCad + lCar;
                }
            }
        }
        return psCad;
    }

    Container getContenedorPadre(Component lBoton) {
        Container lUltContenedor = null;
        while (true) {
            Component lPadre = lBoton.getParent();
            if (lPadre == null) {
                return lUltContenedor;
            }
            if ((lPadre instanceof JPanel)) {
                lUltContenedor = (Container) lPadre;
            }
            lBoton = lPadre;
        }
    }

    char recuperaNemotecnico(String psCadena) {
        String lsRes = buscarBotones();

        for (int x = 0; x < psCadena.length(); x++) {
            char lCad = Character.toUpperCase(psCadena.charAt(x));
            if ((Character.isLetter(lCad))
                    && (lsRes.indexOf(lCad) < 0)) {
                return lCad;
            }
        }
        return psCadena.trim().charAt(0);
    }

    public int getWidth() {
        if (getAnchura() > 0) {
            return getAnchura();
        }
        return super.getWidth();
    }

    public void setBounds(Rectangle r) {
        super.setBounds(r);
        formateaTexto();
    }

    public Rectangle getBounds() {
        Rectangle miRect = super.getBounds();
        if (getAnchura() > 0) {
            miRect.width = getAnchura();
        }
        return miRect;
    }

    public Dimension getMaximumSize() {
        Dimension miDim = super.getMaximumSize();
        if (getAnchura() > 0) {
            miDim.width = getAnchura();
        }
        return miDim;
    }

    public Dimension getPreferredSize() {
        Dimension miDim = super.getPreferredSize();
        if (getAnchura() > 0) {
            miDim.width = getAnchura();
        }
        return miDim;
    }

    public void pulsaBoton() {
        TButton_actionPerformed(null);
    }

    public void addParametro(Object param, String io, int piNumero) {
        if (validador == null) {
            validador = new JPTextField();
        }
        validador.addParametro(param, io, piNumero);
    }

    public void addParametro(Object param, String io, int piNumero, int piTotal) {
        if (validador == null) {
            validador = new JPTextField();
        }
        validador.addParametro(param, io, piNumero, piTotal);
    }

    /**
     * @deprecated
     */
    public boolean validacionMetodo() {
        if (!this.bEjecutarFuncion) {
            this.bEjecutarFuncion = true;
            return true;
        }

        if (this.funcionValidacion.trim().equals("")) {
            return true;
        }

        validador.setFuncionValidacion(getFuncionValidacion());
        validador.setMetodoOnError(this._padre, getMetodoOnError());

        this.hayMensajeError = (!validador.lanzaValidacionOracle());

        return !this.hayMensajeError;
    }

    public Object[] devuelveValores() {
        return validador != null ? validador.devuelveValores() : null;
    }

    public Object devuelveValor(int i) {
        return validador != null ? validador.devuelveValor(i) : null;
    }

    public void lanzaFocusLost() {
        FocusEvent fe = new FocusEvent(this, 1005, false);
        processEvent(fe);
    }

    public String getActionCommand() {
        return "";
    }

    public boolean ejecutaMenuOpciones() {
        miraPadresMenu();
        return true;
    }

    public boolean tieneMenuOpciones() {
        Component papa = this;

        boolean salir = false;
        boolean hayMenuPapa = salir;

        while (!salir) {
            if (papa == null) {
                salir = true;
            } else {
                papa = papa.getParent();
            }

            if (((papa instanceof TPanel))
                    && (!((TPanel) papa).getMenuOpciones().equals(""))) {
                hayMenuPapa = true;
                salir = true;
            } else if ((papa instanceof TVentana)) {
                if (!((TVentana) papa).getMenuOpciones().equals("")) {
                    hayMenuPapa = true;
                }

                salir = true;
            }
        }
        return hayMenuPapa;
    }

    private void miraPadresMenu() {
        Object papa = this;
        boolean hayMenuPapa = false;

        while (papa != null) {
            if ((papa instanceof Component)) {
                papa = ((Component) papa).getParent();
            }

            if ((papa instanceof TPanel)) {
                if (!((TPanel) papa).getMenuOpciones().equals("")) {
                    ((TPanel) papa).ejecutaMenuOpciones();
                    break;
                }
            } else if ((papa instanceof TVentana)) {
                if (((TVentana) papa).getMenuOpciones().equals("")) {
                    break;
                }
                ((TVentana) papa).ejecutaMenuOpciones();
                break;

                // break;
            }
        }
    }
    ///

    @Override
    public String getMetodoTeclas() {
        if (this._metodoTeclas == null) {
            return "";
        }

        return this._metodoTeclas;
    }

    @Override
    public Object getPadreMetodoTeclas() {
        return this._padre;
    }

    @Override
    public void focusGained(FocusEvent event) {
        if ((event.isTemporary()) || (getPadre() == null) || (getPadre().getComponenteActual() == this)) {
            return;
        }
        TBarraEstado.setMensaje("");
        getPadre().setComponenteActual(this);
    }

    @Override
    public void focusLost(FocusEvent event) {
    }

    protected TVentana getPadre() {
        if (this.miPadre == null) {
            this.miPadre = ((TVentana) Globales.getPadre(this));
        }
        return this.miPadre;
    }

    public boolean isTButton() {
        return true;
    }

    /**
     * @deprecated
     */
    public boolean esTButton() {
        return true;
    }

    private boolean ejecutaMetodo(int piCual, Integer piParametro, boolean trucho) {
        if (!this.bEjecutarFuncion) {
            this.bEjecutarFuncion = true;
            return true;
        }

        Boolean result = new Boolean(true);
        Method miMetodo = null;
        String lsNombreMetodo = "";
        Object[] parametros = (Object[]) null;

        if (this._padre == null) {
            if (piCual == 3) {
                return false;
            }

            return true;
        }

        Class miClase = this._padre.getClass();
        try {
            if (piCual == 1) {
                lsNombreMetodo = this._metodoPreValidacion;
                if (Herramientas.nvl(this._metodoPreValidacion) == "") {
                    return true;
                }
                miMetodo = miClase.getMethod(this._metodoPreValidacion, null);
            } else if (piCual == 2) {
                lsNombreMetodo = this._metodoPostValidacion;
                if (Herramientas.nvl(this._metodoPostValidacion) == "") {
                    return true;
                }
                miMetodo = miClase.getMethod(this._metodoPostValidacion, null);
            } else if (piCual == 3) {
                lsNombreMetodo = this._metodoTeclas;
                if (Herramientas.nvl(this._metodoTeclas) == "") {
                    return false;
                }
                Class[] parameterTypes = {Integer.class};
                miMetodo = miClase.getMethod(this._metodoTeclas, parameterTypes);
                parametros = new Object[1];
                parametros[0] = piParametro;
            } else if (piCual == 5) {
                lsNombreMetodo = this._metodoAccionCodigo;
                if (Herramientas.nvl(this._metodoAccionCodigo) == "") {
                    return false;
                }
                Class[] parameterTypes = {Integer.class};
                miMetodo = miClase.getMethod(this._metodoAccionCodigo, parameterTypes);
                parametros = new Object[1];
                parametros[0] = piParametro;
            } else if (piCual == 4) {
                lsNombreMetodo = this._metodoAccion;
                if (Herramientas.nvl(this._metodoAccion) == "") {
                    return true;
                }
                miMetodo = miClase.getMethod(this._metodoAccion, null);
            } else {
                return false;
            }
            result = (Boolean) miMetodo.invoke(this._padre, parametros);
        } catch (Exception e) {
            e.printStackTrace();
            TBarraEstado.setMensaje("Error: " + lsNombreMetodo + " " + e.toString());
            this.hayMensajeError = true;
            return false;
        }
        return result.booleanValue();
    }

    protected boolean ejecutaMetodo(int piCual, Integer piParametro) {
        try {
            return ejecutaMetodo(piCual, piParametro, true);
        } catch (NullPointerException e) {
            TBarraEstado.setMensajeError("El método " + nombreMetodo(piCual) + " debe devolver tipo Boolean");
            this.hayMensajeError = true;
        }
        return false;
    }

    public void requestFocus() {
        if ((!isEnabled()) || (!isVisible())) {
            transferFocus();
        } else {
            super.requestFocus();
        }
    }

    private String nombreMetodo(int piCual) {
        if (piCual == 1) {
            return this._metodoPreValidacion;
        }
        if (piCual == 2) {
            return this._metodoPostValidacion;
        }
        if (piCual == 3) {
            return this._metodoTeclas;
        }
        if (piCual == 4) {
            return this._metodoAccion;
        }
        if (piCual == 5) {
            return this._metodoAccionCodigo;
        }
        return "NULL";
    }

    void TButton_keyPressed(KeyEvent event) {
        int tecla = ControlTeclas.setTecla(event);

        if (ControlTeclas.controlarTecla(tecla, this)) {
            event.consume();
        }
    }

    void TButton_actionPerformed(ActionEvent event) {
        Globales.setUltimaVentana((Container) getPadre());
        Herramientas.setCursorEspera();

        if (validador == null) {
            validador = new JPTextField();
        }

        validador.inicializaParametros();

        if (((this.funcionValidacion != null)
                && (!this.funcionValidacion.equals(""))
                && (ejecutaMetodo(1, null))
                && (validacionMetodo())
                && (ejecutaMetodo(2, null)))
                || (this.funcionValidacion == null)
                || (this.funcionValidacion.equals(""))) {
            try {
                ejecutaMetodo(4, null);
                ejecutaMetodo(5, new Integer(hashCode()));
            } catch (Exception localException) {
            }
        }
        Globales.setUltimaVentana((Container) getPadre());
        Herramientas.setCursorNormal();
    }

    class SymKey extends KeyAdapter {

        SymKey() {
        }

        public void keyPressed(KeyEvent event) {
            Object object = event.getSource();
            if (object == TButton.this) {
                TButton.this.TButton_keyPressed(event);
            }
        }
    }

    class SymAction
            implements ActionListener {

        SymAction() {
        }

        public synchronized void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == TButton.this) {
                TButton.this.TButton_actionPerformed(event);
            }
        }
    }
}
