/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.paquete;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.beans.Beans;
import java.util.Hashtable;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.text.Document;

/**
 *
 * @author synccon
 */
public class TTextField extends JTextField
        implements PRedefinidorTeclas, PInputComponent {

    protected String posicionMenu = "";
    private Component _padre = null;
    private String _metodoOnError = "";
    private static boolean validacionTemporal = false;
    private int ultimaTecla = 0;
    protected static TPopupMenu pop = null;
    private boolean reponerMensaje = false;
    Font miFuente = new Font("Dialog", 0, 12);
    TFocusListener aSymFocus = null;

    public TTextField() {
        setFont(this.miFuente);

        setSize(0, 0);

        if (!Beans.isDesignTime()) {
            this.aSymFocus = TFocusListener.getFocusListener();
            addFocusListener(this.aSymFocus);
            SymMouse aSymMouse = new SymMouse();
            addMouseListener(aSymMouse);
            SymKey aSymKey = new SymKey();
            addKeyListener(aSymKey);
            SymMouseMotion aSymMouseMotion = new SymMouseMotion();
            addMouseMotionListener(aSymMouseMotion);
            SymCaret lSymCaret = new SymCaret();
            addCaretListener(lSymCaret);
        }
        super.setAutoscrolls(false);

        setDisabledTextColor(Color.darkGray);

        if (!Beans.isDesignTime()) {
            SymAction lSymAction = new SymAction();
            addActionListener(lSymAction);
        }
    }

    public boolean validaContraCache() {
        return true;
    }

    public void setValidarConOpciones(boolean pbValidarConOpciones) {
    }

    public boolean getValidarConOpciones() {
        return false;

    }

    public void setListaOpciones(String sCampoRamo) {
    }

    public Component getCampoAsociado() {
        return null;

    }

    public void setCampoAsociado(Component pCampoAsociado) {
    }

    public void setCampoAsociado(TTextField pCampoAsociado) {
    }

    public String getListaOpciones() {
        return null;

    }

    public String getTipoAnchura() {
        return null;

    }

    public void setTipoAnchura(String s) {
    }

    public Component getComponenteModelo() {
        return null;

    }

    public void setComponenteModelo(Component c) {
    }

    public void setRango(String pRangoNumeros) {
    }

    /**
     * @deprecated
     */
    public void setRangoNumeros(String pRangoNumeros) {
    }

    public boolean isManagingFocus() {
        return false;

    }

    /**
     * @deprecated
     */
    public String getRangoNumeros() {
        return null;

    }

    public String getRango() {
        return null;

    }

    public void setNumMaximo(double pNumMaximo) {
    }

    public double getNumMaximo() {
        return 0;

    }

    public void setNumMinimo(double pNumMinimo) {
    }

    public double getNumMinimo() {
        return 0;

    }

    public void setEjecutarFuncion(boolean pbValor) {
    }

    public Dimension getPreferredSize() {
        return null;

    }

    public void activarFocusListener(boolean b) {
    }

    public Dimension getMaximumSize() {
        return null;

    }

    public void setValidaNulo(boolean pbValidaNulo) {
    }

    public boolean isGranEdicion() {
        return false;

    }

    public void setGranEdicion(boolean pGranEdicion) {
    }

    public boolean isEnterGranEdicion() {
        return false;

    }

    public void setEnterGranEdicion(boolean pEnterGranEdicion) {
    }

    public boolean isValidaNulo() {
        return false;

    }

    public boolean isNull() {
        return false;

    }

    public TTextField(int numberOfColumns) {
    }

    public TTextField(String initialText) {
    }

    public void setSigno(boolean pbSigno) {
    }

    public boolean isSigno() {
        return false;

    }

    public void setPuntuacion(boolean pbPuntuacion) {
    }

    public boolean isPuntuacion() {
        return false;

    }

    public boolean isPrimerAcceso() {
        return false;

    }

    public void setPrimerAcceso(boolean pPrimerAcceso) {
    }

    public TTextField(String initialText, int numberOfColumns) {
    }

    public void paint(Graphics g) {
    }

    public TTextField(Document doc, String initialText, int numberOfColumns) {
    }

    public String getText() {
        return null;

    }

    public String toString() {
        return null;

    }

    public synchronized void setText(String t) {
    }

    public void setEnabled(boolean pbHabilitado) {
    }

    public synchronized void removeNotify() {
    }

    public synchronized void cut(Clipboard clipboard) {
    }

    public boolean ejecutaCortar() {
        return false;

    }

    public boolean ejecutaCopiar() {
        return false;

    }

    public void paste() {
    }

    public boolean ejecutaPegar() {
        return false;
    }

    public void setDocument(Document d) {
    }

    protected void docChange(DocumentEvent e) {
    }

    protected void docRemove(DocumentEvent e) {
    }

    protected void docInsert(DocumentEvent e) {
    }

    public void setFuncionValidacion(String funcionValidacion) {
    }

    public String getFuncionValidacion() {
        return null;

    }

    public void setLongitud(String psLongitud) {
    }

    public String getLongitud() {
        return null;

    }

    public int getLongitudReal() {
        return 0;

    }

    public int getLongitudVisible() {
        return 0;

    }

    public void setObligatorio(boolean obligatorio) {
    }

    public boolean isObligatorio() {
        return false;

    }

    public void setMascaraFecha(String pMascara) {
    }

    public String getMascaraFecha() {
        return null;

    }

    public void setTipoDato(String pTipoDato) {
    }

    public String getTipoDato() {
        return null;

    }

    public String getLiteral() {
        return null;

    }

    public void setLiteral(String parametros) {
    }

    public void setMayusculasLiteral(boolean mayusculas) {
    }

    public boolean isMayusculasLiteral() {
        return false;

    }

    public void setCache(String cache) {
    }

    public String getCache() {
        return null;

    }

    public void setRellenarLiteral(boolean rellenar) {
    }

    public boolean isRellenarLiteral() {
        return false;

    }

    public boolean isValidate() {
        return false;

    }

    public void setNombreReal(String psNombreReal) {
    }

    public String getNombreReal() {
        return null;

    }

    /**
     * @deprecated
     */
    public boolean estaValidado() {
        return false;

    }

    public void setValidate(boolean pbIsValidate) {
    }

    public void setMetodoPreLista(Component padre, String pmMetodo) {
    }

    public void setMetodoPreLista(String pmMetodo) {
    }

    public String getMetodoPreLista() {
        return null;

    }

    /**
     * @deprecated
     */
    public void setMetodoPostLista() {
    }

    public void setMetodoPostLista(Component padre, String pmMetodo) {
    }

    public void setMetodoPostLista(String pmMetodo) {
    }

    public String getMetodoPostLista() {
        return null;
    }

    /**
     * @deprecated
     */
    public void setMetodoPostMenu() {
    }

    public void setMetodoPostMenu(Component padre, String pmMetodo) {
    }

    public void setMetodoPostMenu(String pmMetodo) {
    }

    public String getMetodoPostMenu() {
        return null;

    }

    /**
     * @deprecated
     */
    public void setMetodoPreMenu() {
    }

    public void setMetodoPreMenu(Component padre, String pmMetodo) {
    }

    public void setMetodoPreMenu(String pmMetodo) {
    }

    public String getMetodoPreMenu() {
        return null;

    }

    /**
     * @deprecated
     */
    public void setMetodoPreLista() {
    }

    /**
     * @deprecated
     */
    public void setMetodoPostCampo() {
    }

    public void setMetodoPostCampo(Component padre, String pmMetodo) {
    }

    public void setMetodoPostCampo(String pmMetodo) {
    }

    public String getMetodoPostCampo() {
        return null;

    }

    public void setMetodoPreValidacion(Component padre, String pmMetodo) {
    }

    /**
     * @deprecated
     */
    public void setMetodoPreValidacion() {
    }

    public void setMetodoPreValidacion(String pmMetodo) {
    }

    public String getMetodoPreValidacion() {
        return null;

    }

    /**
     * @deprecated
     */
    public void setMetodoPreCampo() {
    }

    public void setMetodoPreCampo(Component padre, String pmMetodo) {
    }

    public void setMetodoPreCampo(String pmMetodo) {
    }

    public String getMetodoPreCampo() {
        return null;

    }

    /**
     * @deprecated
     */
    public void setMetodoTeclas() {
    }

    public void setMetodoTeclas(Component padre, String pmMetodo) {
    }

    public Object getPadreMetodoTeclas() {
        return null;

    }

    public void setMetodoTeclas(String pmMetodo) {
    }

    public String getMetodoTeclas() {
        return null;

    }

    public boolean ejecutaMetodo(int piCual, Integer piParametro) {
        return false;
    }

    private String nombreMetodo(int piCual) {
        return null;

    }

    private boolean ejecutaMetodo(int piCual, Integer piParametro, boolean trucho) {
        return false;
    }

    private boolean validaObligatorio() {
        return false;

    }

    public JPanel getPanelGrupo() {
        return null;

    }

    public void setPanelGrupo(JPanel pPanelGrupo) {
    }

    private boolean validaLongitud() {
        return false;
    }

    private boolean validaTipoDato() {
        return false;
    }

    public boolean lanzaValidacion(boolean remoto) {
        return false;
    }

    /**
     * @deprecated
     */
    private void ponCursor(Cursor cursor) {
    }

    public void focusLost(FocusEvent event) {
    }

    void TTextField_mouseReleased(MouseEvent event) {
    }

    public void focusGained(FocusEvent event) {
    }

    public void setToolTip(String psCodToolTip) {
    }

    public String getToolTip() {
        return null;

    }

    public void addParametro(Object param, String io, int piNumero) {
    }

    public void addParametro(Object param, String io, int piNumero, int piTotal) {
    }

    public void inicializaParametros() {
    }

    public void setParametros(Hashtable pv) {
    }

    public boolean lanzaValidacionOracle() {
        return false;
    }

    public boolean lanzaValidacionOracle(Component p) {
        return false;

    }

    private boolean lanzaValidacionOracleInterno() {
        return false;
    }

    public boolean validacionMetodo() {
        return false;
    }

    public Object[] devuelveValores() {
        return null;

    }

    public Object devuelveValor(int i) {
        return null;

    }

    public String getValorMenuOpciones() {
        return null;

    }

    public String getOpcionMenu() {
        return null;

    }

    public String getNombreOpcionMenu() {
        return null;

    }

    public String getListaVal() {
        return null;

    }

    public void setListaVal(String lista) {
    }

    public String getMenuOpciones() {
        return null;

    }

    public void setMenuOpciones(String menu) {
    }

    public void setXMenuOpciones(int piX) {
    }

    public int getXMenuOpciones() {
        return 0;

    }

    public void setYMenuOpciones(int piY) {
    }

    public int getYMenuOpciones() {
        return 0;

    }

    public void setAnchoMenuOpciones(int piAncho) {
    }

    public int getAnchoMenuOpciones() {
        return 0;

    }

    public void setAltoMenuOpciones(int piAlto) {
    }

    public int getAltoMenuOpciones() {
        return 0;

    }

    public void setXLista(int piX) {
    }

    public int getXLista() {
        return 0;

    }

    public void setYLista(int piY) {
    }

    public int getYLista() {
        return 0;

    }

    public void setAnchoLista(int piAncho) {
    }

    public int getAnchoLista() {
        return 0;

    }

    public void setAltoLista(int piAlto) {
    }

    public int getAltoLista() {
        return 0;

    }

    public void setValoresLista(String[] valores) {
    }

    public String[] getValoresLista() {
        return null;

    }

    public void teclaTipeada(KeyEvent event) {
    }

    public void TTextField_keyPressed(KeyEvent event) {
    }

    void TTextField_mouseMoved(MouseEvent event) {
    }

    public void setNumDecimal(int dec) {
    }

    public int getNumDecimal() {
        return 0;

    }

    public void setMayusculas(boolean pbMayusculas) {
    }

    public boolean isMayusculas() {
        return false;

    }

    void TTextField_caretUpdate(CaretEvent event) {
    }

    void TTextField_keyTyped(KeyEvent event) {
    }

    void TTextField_actionPerformed(ActionEvent event) {
    }

    void TTextField_mouseClicked(MouseEvent event) {
    }

    private boolean pulsaListaValores(MouseEvent e) {
        return false;
    }

    public int getHeight() {
        return 0;

    }

    public String getNombreCampo() {
        return null;

    }

    public void setNombreCampo(String nomC) {
    }

    public boolean ejecutaDescartar() {
        return false;

    }

    public void setMetodoPostValidacion(Component padre, String pmMetodo) {
    }

    /**
     * @deprecated
     */
    public void setMetodoPostValidacion() {
    }

    public void setMetodoPostValidacion(String pmMetodo) {
    }

    public String getMetodoPostValidacion() {
        return null;

    }

    public void setCodigoListaOpciones(ListaOpciones lis) {
    }

    public void setDescripcionListaOpciones(ListaOpciones lis) {
    }

    private void mostrarIconoLista(Graphics g2) {
    }

    private void miraPadresMenu() {
    }

    public void setPosicionMenu(String pos) {
        this.posicionMenu = pos;
    }

    public String getPosicionMenu() {
        return this.posicionMenu;
    }

    public void setMetodoOnError(Component padre, String pmMetodo) {
        if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null"))) {
            return;
        }
        this._padre = padre;
        this._metodoOnError = pmMetodo;
    }

    /**
     * @deprecated
     */
    public void setMetodoOnError() {
    }

    public void setMetodoOnError(String pmMetodo) {
        this._metodoOnError = pmMetodo;
    }

    public String getMetodoOnError() {
        if (this._metodoOnError == null) {
            return "";
        }
        return this._metodoOnError;
    }

    public void procesarTecla(KeyEvent event) {
        TTextField_keyPressed(event);
    }

    /**
     * @deprecated
     */
    public void ejecutarTecla(KeyEvent event) {
    }

    public boolean ejecutaMenuOpciones() {
        return false;
    }

    public boolean ejecutaGranEdicion() {
        return false;

    }

    public boolean ejecutaAutoValor() {
        return false;

    }

    public void ponAutoValor() {
    }

    public void ponAutoValor(int i) {
    }

    public boolean ejecutaListaValores() {
        return false;
    }

    public boolean ejecutaDobleClick() {
        return false;

    }

    public boolean getDecimalesDef() {
        return false;

    }

    public void setDecimalesDef(boolean pb) {
    }

    public void lanzaFocusLost() {
    }

    public boolean ejecutaCampoSig() {
        return false;
    }

    public boolean moverLeft() {
        return false;

    }

    public TVentana getPadre() {
        return null;
    }

    public boolean puedeCopiar() {
        return false;

    }

    public boolean puedeCortar() {
        return false;

    }

    public boolean puedePegar() {
        return false;

    }

    public boolean puedeDescartar() {
        return false;

    }

    public boolean esTTextField() {
        return false;

    }

    public boolean tieneAutoValor() {
        return false;

    }

    public boolean tieneLista() {
        return false;

    }

    public boolean tieneMenuOpciones() {
        return false;
    }

    public boolean tieneGranEdicion() {
        return false;

    }

    public void setDebug(boolean b) {
    }

    private void msg(String s) {
    }

    protected int getColumnWidth() {
        return 0;
    }

    protected void setHayMensajeError(boolean b) {
    }

    private void mostrarCampoRotoR(Graphics g2) {
    }

    private void mostrarCampoRotoL(Graphics g2) {
    }

    public void lanzaFocusGained() {
    }

    public void formateo() {
    }

    public void ponFormato() {
    }

    public boolean ejecutaRegistroAnterior() {
        return false;

    }

    public boolean ejecutaRegistroSiguiente() {
        return false;

    }

    public boolean isEjecutandoMenu() {
        return false;

    }

    private void setValidando(boolean bValidando) {
    }

    protected boolean isValidando() {
        return false;

    }

    private void addFocusListener(TFocusListener aSymFocus) {
        
    }

    class SymMouse extends MouseAdapter {

        SymMouse() {
        }

        public void mouseClicked(MouseEvent event) {
            TTextField.validacionTemporal = true;
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_mouseClicked(event);
            }
            TTextField.validacionTemporal = false;
        }

        public void mouseReleased(MouseEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_mouseReleased(event);
            }
        }
    }

    class SymKey extends KeyAdapter {

        SymKey() {
        }

        public void keyTyped(KeyEvent event) {
            TTextField.this.teclaTipeada(event);
        }

        public void keyPressed(KeyEvent event) {
            TTextField.this.ultimaTecla = event.getKeyCode();

            if (TTextField.pop != null) {
                if (TTextField.pop.isVisible()) {
                    TTextField.pop.setVisible(false);
                }
            }
            if (TTextField.this.reponerMensaje) {
                TBarraEstado.setMensaje(TTextField.this.getToolTipText());
                TTextField.this.reponerMensaje = false;
            }

            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_keyPressed(event);
            }
        }
    }

    class SymMouseMotion extends MouseMotionAdapter {

        SymMouseMotion() {
        }

        public void mouseMoved(MouseEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_mouseMoved(event);
            }
        }
    }

    class SymCaret
            implements CaretListener {

        SymCaret() {
        }

        public void caretUpdate(CaretEvent event) {
            TTextField.validacionTemporal = true;
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_caretUpdate(event);
            }
            TTextField.validacionTemporal = false;
        }
    }

    class SymAction
            implements ActionListener {

        SymAction() {
        }

        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField.this.TTextField_actionPerformed(event);
            }
        }
    }
}
