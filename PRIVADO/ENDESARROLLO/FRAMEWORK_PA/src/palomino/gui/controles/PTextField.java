package palomino.gui.controles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.swing.JPanel;
import javax.swing.JTextField;
import palomino.herramientas.variables_entorno.*;

public class PTextField extends JTextField {

    private String tipoDato = "TEXTO";
    private boolean bHayLongitud = false;
    private boolean bHayTipoDato = false;
    Font miFuente = new Font("Dialog", 0, 12);
    private String mascaraFecha = "";
    private String datosLongitud = "";
    protected int longitudVisible = 0;
    protected int longitudReal = 0;
    protected int longitudMinima = 0;
    private boolean _signo = false;
    private boolean _puntuacion = false;
    protected int numDec;
    private boolean decimalesDef = false;
    private double numMinimo = 0.0D;
    private boolean reponerMensaje = false;
    private static boolean validacionTemporal = false;
    private int ultimaTecla = 0;
    private JPanel panelGrupo = null;
    private String valorPrevio = null;
    private boolean validatePrevio = false;
    private String valorInicial = null;
    private boolean isValidate = false;
    protected boolean mayusculas = true;
    private boolean validando = false;
//protected static TPopupMenu pop = null;
    private boolean bEraOpaco = true;
    private boolean hayMensajeError = false;
    private boolean _validaNulo = true;
    protected boolean obligatorio;
    boolean ejecutandoMenu = false;
    private double numMaximo = 1.7976931348623157E+308D;
    private boolean estaValidando = false;
    private boolean bValidarContraListaOpciones = false;
//ListaOpciones listaOpc = null;
    public static final Color GRIS_CLARO = new Color(220, 220, 220);
    public static final Color GRIS_ROTO = new Color(192, 192, 192);
    public static final Color COLOR_ERROR = new Color(255, 192, 192);
    public static final Color COLOR_OBLIGA = new Color(255, 255, 192);
    public static final Color COLOR_NORMAL = new Color(192, 255, 255);

    private TrataMascara _TrataMascara = new TrataMascara();
    public PTextField() {
        setFont(miFuente);
        setSize(0, 0);
    }

    /**
     * @return the tipoDato
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(String pTipoDato) {
        bHayTipoDato = true;
        tipoDato = pTipoDato;
        miFuente = new Font("dialog", 0, 12);
        setFont(miFuente);

        if (tipoDato.trim().equals("FECHA")) {
            if ((getMascaraFecha() == null) || (getMascaraFecha().trim().equals(""))) {
                setMascaraFecha("dd-MM-yyyy");
            }
        } else {
            setMascaraFecha("");
        }
        if (tipoDato.trim().equals("NUMERICO")) {
            miFuente = new Font("Arial", 0, 12);
            setFont(miFuente);
        }

        if (bHayLongitud) {
            setLongitud(datosLongitud);
        }
    }

    /**
     * @param datosLongitud the datosLongitud to set
     */
    public void setLongitud(String psLongitud) {
        datosLongitud = psLongitud;
        bHayLongitud = true;

        StringTokenizer tok = new StringTokenizer(psLongitud, ",");
        try {
            longitudVisible = new Integer(tok.nextToken()).intValue();
            longitudReal = new Integer(tok.nextToken()).intValue();
            longitudMinima = new Integer(tok.nextToken()).intValue();
        } catch (NoSuchElementException localNoSuchElementException) {
        } catch (NumberFormatException localNumberFormatException) {
        }
        if (longitudVisible == 1) {
            longitudVisible = 2;
            setHorizontalAlignment(0);
        }
        if (longitudReal == 0) {
            longitudReal = longitudVisible;
        }
        if (longitudMinima > longitudReal) {
            longitudMinima = longitudReal;
        }
        int cols = longitudVisible;
        if (tipoDato.equals("NUMERICO")) {
            cols++;
            if ((getNumDecimal() > 0) ) {
                longitudVisible += 1;
            }

            if (isPuntuacion()) {
                longitudVisible = (longitudVisible / 3);
            }

        }

        if (cols > 0) {
            super.setColumns(cols);
        }
    }

    /**
     * @return the mascaraFecha
     */
    public String getMascaraFecha() {
        return mascaraFecha;
    }

    /**
     * @param mascaraFecha the mascaraFecha to set
     */
    public void setMascaraFecha(String pMascara) {
        if ((pMascara == null) || (pMascara.trim().equals("")) || (pMascara.trim().equals("null"))) {
            if (tipoDato.equals("FECHA")) {
                if (Global.getFormatoFecha().toUpperCase().equals("MM-DD-YYYY")) {
                    mascaraFecha = "MM-dd-yyyy";
                } else {
                    mascaraFecha = "dd-MM-yyyy";
                }
            } else {
                return;
            }
        } else {
            if (Global.getFormatoFecha().toUpperCase().equals("MM-DD-YYYY")) {
                if (pMascara.equals("dd-MM-yyyy")) {
                    pMascara = "MM-dd-yyyy";
                }
            }
            mascaraFecha = pMascara;
        }
        int lon = Herramientas.quitaMascaraFecha(pMascara).length();
        if (tipoDato.equals("FECHA")) {
            setLongitud(pMascara.length() + "," + lon + "," + lon);
            setSigno(false);
        }
    }

    public void setSigno(boolean pbSigno) {
        _signo = pbSigno;
        if (pbSigno) {
            setNumMinimo(-1.797693134862316E+30);
        } else {
            setNumMinimo(0.0D);
        }
    }

    private int getNumDecimal() {
        return numDec;
    }

    private boolean isPuntuacion() {
        return _puntuacion;
    }

    private boolean getDecimalesDef() {
        return decimalesDef;
    }

    public void setNumMinimo(double pNumMinimo) {
        if ((isSigno()) || (pNumMinimo >= 0.0D)) {
            numMinimo = pNumMinimo;
        } else {
            numMinimo = 0.0D;
        }
    }

    private boolean isSigno() {
        return _signo;
    }
    
      public synchronized void cut(Clipboard clipboard)
  {
    if (puedeCortar())
    {
      StringBuffer newData = new StringBuffer();
      int selStart = getSelectionStart();
      String clipboardData = _TrataMascara.cut(getText(), selStart, getSelectionEnd(),newData);
      StringSelection ss = new StringSelection(clipboardData);
      clipboard.setContents(ss, ss);
      super.setText(newData.toString());
      setCaretPosition(selStart);
    }
  }

  public boolean ejecutaCortar()
  {
    super.cut();
    return puedeCortar();
  }

  public boolean ejecutaCopiar()
  {
    copy();
    return puedeCopiar();
  }

  public void paste()
  {
    ejecutaPegar();
  }

  public boolean ejecutaPegar()
  {
    boolean ejecucionCorrecta = false;

    if (puedePegar())
    {
      String data = "";
      Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
      Transferable contents = clip.getContents(this);
      if ((contents != null) && (contents.isDataFlavorSupported(DataFlavor.stringFlavor)))
      {
        try
        {
          data = (String)contents.getTransferData(DataFlavor.stringFlavor);
        }
        catch (Exception e)
        {
          return false;
        }
      }
      if (tipoDato.equals("NUMERICO"))
      {
        if ((getSelectionStart() + data.indexOf('-') > 0) || (super.getText().indexOf('-') > -1) || 
          (!Herramientas.esNumerico(data)) || 
          (data.length() + super.getText().length() > longitudReal) || 
          ((data.indexOf(Global.getSepDecimal()) > -1) && 
          (super.getText().indexOf(Global.getSepDecimal()) > -1)) || 
          ((data.indexOf(Global.getSepDecimal()) > -1) && (getNumDecimal() == 0)) || (
          (data.indexOf('-') > -1) && (!isSigno())))
        {
          PBarraEstado.setMensajeError(Global.getCodigoMensaje("trn", "10190001"));

          return false;
        }

      }
      else if ((data.length() > longitudReal) || (data.indexOf('\n') > -1))
      {
        PBarraEstado.setMensajeError(Global.getCodigoMensaje("trn", "10190002"));

        return false;
      }
      String contenidoAnt = getText();
      super.paste();

      int tamanoTotal = getText().length();

      if (tamanoTotal > longitudReal)
      {
        PBarraEstado.setMensajeError(Global.getCodigoMensaje("trn", "10190002"));
        setText(contenidoAnt);
        return false;
      }

      ejecucionCorrecta = true;
    }

    return ejecucionCorrecta;
  }

    private boolean puedeCortar() {
        return (isEditable()) && (puedeCopiar());
    }

    private boolean puedePegar() {
    Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    return clip.getContents(this) != null;
    }

    private boolean puedeCopiar() {
        return getCaret().getMark() != getCaret().getDot();
    }
}