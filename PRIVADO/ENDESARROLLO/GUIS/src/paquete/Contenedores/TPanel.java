/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.Beans;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.JPanel;
import paquete.TBarraEstado;
import paquete.TButton;
import paquete.TFocusListener;
import paquete.PRedefinidorTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;

/**
 *
 * @author JONATHAN
 */

public class TPanel extends JPanel
  implements PRedefinidorTeclas
{
  private boolean conProgramaMenu;
  private String _metodoPreMenu = "";

  private String _metodoPostMenu = "";

  private String _metodoPrePanel = "";

  private String _metodoPostPanel = "";

  private String posicionMenu = "";

  protected Object _padre = null;

  private String menuOpciones = "";

  private String programaMenu = "";

  private String numMenuOpcion = "";

  private String defectoMenu = "";

  private TButton btnDefecto = null;

  private boolean bEsCartas = false;

  private boolean hayMenu = false;
  private static final int METODO_PREMENU = 1;
  private static final int METODO_POSTMENU = 2;
  private static final int METODO_PREPANEL = 3;
  private static final int METODO_POSTPANEL = 4;
  protected boolean fLost = true;

  protected boolean fGained = true;

  private String CARDLAYOUT = "java.awt.CardLayout";

  private boolean hasFocusListener = false;

  private int iXMenu = -1;

  private int iYMenu = -1;

  private int iAnchoMenu = -1;

  private int iAltoMenu = -1;

  protected int anchuraComponente = 0;

  protected int alturaComponente = 0;

  protected JComponent grupoAnterior = null;

  protected JComponent grupoPosterior = null;

  private String sMetodoTeclas = "";

  protected Object oPadreMetodoTeclas = null;

  /** @deprecated */
  protected boolean centrado = false;

  TVentana ventanaPadre = null;

  public TPanel()
  {
    setLayout(new FlowLayout(1, 5, 5));
    setSize(23, 21);
  }

  public synchronized void addFocusListener(FocusListener l)
  {
    if (Beans.isDesignTime()) return;
    if (!this.hasFocusListener)
    {
      if ((l instanceof TFocusListener))
      {
        ((TFocusListener)l).registerContainer(this);
      }
      if (!Beans.isDesignTime())
      {
        super.addFocusListener(l);
        this.hasFocusListener = true;
      }
    }
  }

  public void setParametros(Vector pvParametros)
  {
  }

  public boolean setParametros(Vector pvParametros, boolean b)
  {
    return true;
  }

  public Vector getParametros()
  {
    return null;
  }

  public void TPanel_focusGained(FocusEvent e)
  {
    if (e.isTemporary()) return;
    this.fGained = true;
    this.fLost = false;

    if (getBotonDefecto() != null)
    {
      getBotonDefecto().setBorder(TButton.BORDE_RESALTADO);
    }

    if ((getMetodoPrePanel() != null) && (!getMetodoPrePanel().equals("")))
    {
      ejecutaMetodo(3, null);
    }
  }

  public void TPanel_focusLost(FocusEvent e)
  {
    if (e.isTemporary()) return;

    this.fGained = false;
    this.fLost = true;

    if (getBotonDefecto() != null)
    {
      getBotonDefecto().setBorder(TButton.BORDE_NORMAL);
    }

    if ((getMetodoPostPanel() != null) && (!getMetodoPostPanel().equals("")))
    {
      ejecutaMetodo(4, null);
    }
  }

  protected void finalize()
  {
    TFocusListener.getFocusListener().unregisterContainer(this);
  }

  public boolean isCartas()
  {
    return this.bEsCartas;
  }

  public void setCartas(boolean pbEsCartas)
  {
    this.bEsCartas = pbEsCartas;
  }

  public String getMetodoTeclas()
  {
    return this.sMetodoTeclas;
  }

  public JComponent getGrupoAnterior()
  {
    return this.grupoAnterior;
  }

  public void setGrupoAnterior(JComponent pGrupoAnterior)
  {
    this.grupoAnterior = pGrupoAnterior;
  }

  public void setGrupoAnterior(Component pGrupoAnterior)
  {
    this.grupoAnterior = ((JComponent)pGrupoAnterior);
  }

  public JComponent getGrupoPosterior()
  {
    if (this.grupoPosterior == null)
    {
      Component padre = getParent();
      while ((padre != null) && (!
        (padre instanceof TPanel)))
      {
        padre = padre.getParent();
      }
      if ((padre != null) && ((padre instanceof TPanel)))
      {
        this.grupoPosterior = ((TPanel)padre).getGrupoPosterior();
      }
    }
    return this.grupoPosterior;
  }

  public void setGrupoPosterior(JComponent pGrupoPosterior)
  {
    this.grupoPosterior = pGrupoPosterior;
  }

  public void setGrupoPosterior(Component pGrupoPosterior)
  {
    this.grupoPosterior = ((JComponent)pGrupoPosterior);
  }

  public Dimension getPreferredSize()
  {
    if ((this.anchuraComponente == 0) || (this.alturaComponente == 0))
    {
      return super.getPreferredSize();
    }
    if (this.alturaComponente == 1)
      this.alturaComponente = super.getPreferredSize().height;
    if (this.anchuraComponente == 1)
      this.anchuraComponente = super.getPreferredSize().width;
    return new Dimension(this.anchuraComponente, this.alturaComponente);
  }

  public Dimension getMaximumSize()
  {
    if ((this.anchuraComponente == 0) || (this.alturaComponente == 0))
    {
      return super.getMaximumSize();
    }
    return new Dimension(this.anchuraComponente, this.alturaComponente);
  }

  public void setAnchuraComponente(int anchuraComponente)
  {
    this.anchuraComponente = anchuraComponente;
  }

  public int getAnchuraComponente()
  {
    return this.anchuraComponente;
  }

  public void setAlturaComponente(int alturaComponente)
  {
    this.alturaComponente = alturaComponente;
  }

  public int getAlturaComponente()
  {
    return this.alturaComponente;
  }

  public TButton getBotonDefecto()
  {
    return this.btnDefecto;
  }

  public void setBotonDefecto(TButton pBtn)
  {
    if (!Beans.isDesignTime())
    {
      addFocusListener(TFocusListener.getFocusListener());
    }
    this.btnDefecto = pBtn;
  }

  public void setBotonDefecto(Component pBtn)
  {
    if (!Beans.isDesignTime())
    {
      addFocusListener(TFocusListener.getFocusListener());
    }
    this.btnDefecto = ((TButton)pBtn);
  }

  public Object getPadreMetodoTeclas()
  {
    return this.oPadreMetodoTeclas;
  }

  public String getValorMenuOpciones()
  {
    String devuelta = Globales.getVariable("devueltaPrograma");
    return devuelta;
  }

  public String getOpcionMenu()
  {
    return Globales.getVariable("devueltaOpcion");
  }

  public String getNombreOpcionMenu()
  {
    return Globales.getVariable("devueltaNombreOpcion");
  }

  public void setXMenuOpciones(int piX)
  {
    this.iXMenu = piX;
  }

  public int getXMenuOpciones()
  {
    return this.iXMenu;
  }

  public void setYMenuOpciones(int piY)
  {
    this.iYMenu = piY;
  }

  public int getYMenuOpciones()
  {
    return this.iYMenu;
  }

  public void setAnchoMenuOpciones(int piAncho)
  {
    this.iAnchoMenu = piAncho;
  }

  public int getAnchoMenuOpciones()
  {
    return this.iAnchoMenu;
  }

  public void setAltoMenuOpciones(int piAlto)
  {
    this.iAltoMenu = piAlto;
  }

  public int getAltoMenuOpciones()
  {
    return this.iAltoMenu;
  }

  public void setMetodoTeclas(Component poPadreMetodoTeclas, String psMetodoTeclas)
  {
    this.oPadreMetodoTeclas = poPadreMetodoTeclas;
    this.sMetodoTeclas = psMetodoTeclas;
  }

  public void setMetodoTeclas(Object poPadreMetodoTeclas, String psMetodoTeclas)
  {
    this.oPadreMetodoTeclas = poPadreMetodoTeclas;
    this.sMetodoTeclas = psMetodoTeclas;
  }

  /** @deprecated */
  public void setMetodoTeclas(String psMetodoTeclas)
  {
    this.sMetodoTeclas = psMetodoTeclas;
  }

  public String getMenuOpciones()
  {
    return this.menuOpciones;
  }

  public void setMenuOpciones(String menu)
  {
    this.menuOpciones = menu.toUpperCase();
    if (!this.menuOpciones.trim().equals(""))
    {
      StringTokenizer tok = new StringTokenizer(this.menuOpciones, ",");

      String dato1 = tok.hasMoreTokens() ? tok.nextToken() : "";
      String dato2 = tok.hasMoreTokens() ? tok.nextToken() : null;

      if (dato2 == null)
      {
        this.programaMenu = null;
        this.numMenuOpcion = dato1;
        this.defectoMenu = "";
        this.conProgramaMenu = false;
      }
      else
      {
        this.programaMenu = dato1;
        this.numMenuOpcion = dato2;
        this.defectoMenu = "";
        this.conProgramaMenu = true;
      }
    }

    this.hayMenu = true;
  }

  /** @deprecated */
  public void setMetodoPostMenu()
  {
  }

  public void setMetodoPostMenu(Component padre, String pmMetodo)
  {
    if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null")))
    {
      return;
    }

    this._padre = padre;
    this._metodoPostMenu = pmMetodo;
  }

  public String getMetodoPrePanel()
  {
    return this._metodoPrePanel;
  }

  public void setMetodoPrePanel(Component padre, String pmMetodo)
  {
    if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null")))
    {
      this._metodoPrePanel = "";
      return;
    }
    if (!Beans.isDesignTime())
    {
      addFocusListener(TFocusListener.getFocusListener());
    }
    this._padre = padre;
    this._metodoPrePanel = pmMetodo;
  }

  public String getMetodoPostPanel()
  {
    return this._metodoPostPanel;
  }

  public void setMetodoPostPanel(Component padre, String pmMetodo)
  {
    if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null")))
    {
      this._metodoPostPanel = "";
      return;
    }
    if (!Beans.isDesignTime())
    {
      addFocusListener(TFocusListener.getFocusListener());
    }
    this._padre = padre;
    this._metodoPostPanel = pmMetodo;
  }

  /** @deprecated */
  public void setMetodoPrePanel()
  {
  }

  /** @deprecated */
  public void setMetodoPrePanel(String pmMetodo)
  {
  }

  /** @deprecated */
  public void setMetodoPostPanel()
  {
  }

  /** @deprecated */
  public void setMetodoPostPanel(String pmMetodo)
  {
  }

  /** @deprecated */
  public void setMetodoPostMenu(String pmMetodo)
  {
  }

  public String getMetodoPostMenu()
  {
    if (this._metodoPostMenu == null)
    {
      return "";
    }
    return this._metodoPostMenu;
  }

  /** @deprecated */
  public void setMetodoPreMenu()
  {
  }

  public void setMetodoPreMenu(Component padre, String pmMetodo)
  {
    if ((pmMetodo == null) || (pmMetodo.trim().equals("")) || (pmMetodo.trim().equals("null")))
    {
      return;
    }

    this._padre = padre;
    this._metodoPreMenu = pmMetodo;
  }

  /** @deprecated */
  public void setMetodoPreMenu(String pmMetodo)
  {
  }

  public String getMetodoPreMenu()
  {
    if (this._metodoPreMenu == null)
    {
      return "";
    }
    return this._metodoPreMenu;
  }

  /** @deprecated */
  public boolean isCentradoAuto()
  {
    return this.centrado;
  }

  /** @deprecated */
  public void setCentradoAuto(boolean cen)
  {
    this.centrado = cen;
  }

  /** @deprecated */
  public void ejecutaCentrado()
  {
  }

  public boolean ejecutaBotonDefecto()
  {
    boolean ejecucionCorrecta = false;

    if (getBotonDefecto() == null)
    {
      setBotonDefecto(miraPadresBotonDefecto());
    }
    if (getBotonDefecto() != null)
    {
      getBotonDefecto().pulsaBoton();
      ejecucionCorrecta = true;
    }
    return ejecucionCorrecta;
  }

  private TButton miraPadresBotonDefecto()
  {
    Component padre = this;
    boolean encontrado = false;
    while ((padre.getParent() != null) && (!encontrado))
    {
      padre = padre.getParent();
      if ((padre instanceof TPanel))
      {
        encontrado = ((TPanel)padre).getBotonDefecto() != null;
      }
    }

    return encontrado ? ((TPanel)padre).getBotonDefecto() : null;
  }

  public boolean ejecutaMenuOpciones()
  {
    if (!this.menuOpciones.equals(""))
    {
      if (this.programaMenu == null)
      {
        String clase = getPadre().getClass().getName().toUpperCase();
        this.programaMenu = clase.substring(clase.lastIndexOf(".") + 1, clase.length());
      }

      ejecutaMetodo(1, null);
      VentanaListaMenu op = null;
      TVentana padre = getPadre();
      if ((padre instanceof Frame))
      {
        op = new VentanaListaMenu((Frame)padre);
      }
      else if ((padre instanceof Dialog))
      {
        op = new VentanaListaMenu((Dialog)padre);
      }

      op.setModal(true);
      op.inicializa((Container)getPadre(), this.programaMenu, this.numMenuOpcion, this.defectoMenu, this.posicionMenu, this, this.conProgramaMenu);

      Rectangle r = op.getBounds();
      r.x = (this.iXMenu < 0 ? r.x : this.iXMenu);
      r.y = (this.iYMenu < 0 ? r.y : this.iYMenu);
      r.width = (this.iAnchoMenu < 0 ? r.width : this.iAnchoMenu);
      r.height = (this.iAltoMenu < 0 ? r.height : this.iAltoMenu);
      op.setBounds(r);

      op.setVisible(true);
      ejecutaMetodo(2, null);
    }
    else
    {
      miraPadresMenu();
    }
    return true;
  }

  private void miraPadresMenu()
  {
    Object papa = this;
    boolean hayMenuPapa = false;

    while (papa != null)
    {
      if ((papa instanceof Component))
      {
        papa = ((Component)papa).getParent();
      }

      if ((papa instanceof TPanel))
      {
        if (!((TPanel)papa).getMenuOpciones().equals(""))
        {
          ((TPanel)papa).ejecutaMenuOpciones();
          break;
        }
      }
      else if ((papa instanceof TVentana))
      {
        if (((TVentana)papa).getMenuOpciones().equals(""))
        {
          break;
        }

        ((TVentana)papa).ejecutaMenuOpciones();
        break;

      }
    }
  }

  public TVentana getPadre()
  {
    if (this.ventanaPadre == null)
    {
      Component p = Globales.getPadre(this);
      if (p != null)
      {
        this.ventanaPadre = ((TVentana)p);
      }
    }

    return this.ventanaPadre;
  }

  private boolean ejecutaMetodo(int piCual, Integer piParametro)
  {
    try
    {
      return ejecutaMetodo(piCual, piParametro, true);
    }
    catch (NullPointerException e)
    {
      e.printStackTrace();
      TBarraEstado.setMensajeError(tipoMetodo(piCual) + " - " + Globales.getCodigoMensaje("trn", "10190003") + " (" + nombreMetodo(piCual) + ")");
    }return false;
  }

  private String tipoMetodo(int piCual)
  {
    if (piCual == 1) return "metodoPreMenu";
    if (piCual == 2) return "metodoPostMenu";
    if (piCual == 3) return "metodoPrePanel";
    if (piCual == 4) return "metodoPostPanel";
    return "NULL";
  }

  private String nombreMetodo(int piCual)
  {
    if (piCual == 1) return this._metodoPreMenu;
    if (piCual == 2) return this._metodoPostMenu;
    if (piCual == 3) return this._metodoPrePanel;
    if (piCual == 4) return this._metodoPostPanel;
    return "NULL";
  }

  private boolean ejecutaMetodo(int piCual, Integer piParametro, boolean trucho)
  {
    if (this._padre == null) return true;

    Boolean result = new Boolean(true);
    Method miMetodo = null;
    String lsNombreMetodo = "";
    Object[] parametros = (Object[])null;

    Class miClase = this._padre.getClass();
    try
    {
      if (piCual == 1)
      {
        lsNombreMetodo = this._metodoPreMenu;
        if (Herramientas.nvl(this._metodoPreMenu) == "") return true;
        miMetodo = miClase.getMethod(this._metodoPreMenu, null);
      }
      else if (piCual == 2)
      {
        lsNombreMetodo = this._metodoPostMenu;
        if (Herramientas.nvl(this._metodoPostMenu) == "") return true;
        miMetodo = miClase.getMethod(this._metodoPostMenu, null);
      }
      else if (piCual == 3)
      {
        lsNombreMetodo = this._metodoPrePanel;
        if (Herramientas.nvl(this._metodoPrePanel) == "") return true;
        miMetodo = miClase.getMethod(this._metodoPrePanel, null);
      }
      else if (piCual == 4)
      {
        lsNombreMetodo = this._metodoPostPanel;
        if (Herramientas.nvl(this._metodoPostPanel).equals("")) return true;
        miMetodo = miClase.getMethod(this._metodoPostPanel, null);
      }
      else
      {
        return false;
      }
      result = (Boolean)miMetodo.invoke(this._padre, parametros);
    }
    catch (Exception e)
    {
      TBarraEstado.setMensajeError(tipoMetodo(piCual) + " error: " + lsNombreMetodo);
      return false;
    }
    return result.booleanValue();
  }

  public void lanzaFocusLost(TPanel pnl)
  {
    FocusEvent fe = new FocusEvent(pnl, 1005, false);
    dispatchEvent(fe);
  }

  public boolean getfLost()
  {
    return this.fLost;
  }

  public boolean getfGained()
  {
    return this.fGained;
  }

  public int getNumCardActivo()
  {
    if (getLayout().getClass().getName().equals(this.CARDLAYOUT))
    {
      Component[] c = getComponents();
      int i = 0;
      int j = c.length;
      while (i < j)
      {
        if (c[i].isVisible())
        {
          break;
        }

        i++;
      }

      return i;
    }
    return -1;
  }

  public boolean inicioDatosVariables()
  {
    return true;
  }

  public Boolean tomaFocoDatosVariables() {
    return Boolean.TRUE;
  }

  public boolean getHaTratadoTodosLosDVDatosVariables() {
    return true;
  }

  public boolean validaPantallaDatosVariables() {
    return true;
  }
}
