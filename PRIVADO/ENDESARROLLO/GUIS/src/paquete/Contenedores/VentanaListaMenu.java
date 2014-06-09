/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import paquete.utiles.ControlTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;

public class VentanaListaMenu extends TDialog
{
  boolean frameSizeAdjusted = false;

  PnlListaMenu pnlListaMenu = new PnlListaMenu()
  {
    public void accionAlSeleccionar()
    {
      ControlTeclas.controlarTecla(ControlTeclas.SALIR, VentanaListaMenu.this);
    }
  };

  public VentanaListaMenu(Frame paramFrame)
  {
    super(paramFrame);
    init();
  }

  public VentanaListaMenu(Dialog paramDialog)
  {
    super(paramDialog);
    init();
  }

  private void init()
  {
    getContentPane().setLayout(new BorderLayout(0, 0));

    setVisible(false);
    this.pnlListaMenu.setLayoutImpl(new CardLayout(0, 0));
    getContentPane().add("Center", this.pnlListaMenu);
  }

  /** @deprecated */
  public VentanaListaMenu()
  {
    this((Frame)null);
  }

  /** @deprecated */
  public static void main(String[] paramArrayOfString)
  {
    VentanaListaMenu localVentanaListaMenu = new VentanaListaMenu((Frame)null);
    TDialog localTDialog = new TDialog();

    localTDialog.setVisible(true);

    localVentanaListaMenu.inicializa(localTDialog, "AP502000", "2", "1", "", null, true);
    localVentanaListaMenu.setVisible(true);
  }

  public void addNotify()
  {
    Dimension localDimension = getSize();

    super.addNotify();

    if (this.frameSizeAdjusted)
      return;
    this.frameSizeAdjusted = true;

    Insets localInsets = getInsets();
    setSize(localInsets.left + localInsets.right + localDimension.width, localInsets.top + localInsets.bottom + localDimension.height);
  }

  public int inicializa(Container paramContainer, String paramString1, String paramString2, String paramString3, String paramString4, Component paramComponent, boolean paramBoolean)
  {
    situa(paramContainer, paramString4);

    int i = this.pnlListaMenu.inicializa(paramString1, paramString2, paramString3, paramComponent, paramBoolean);

    if (i != 0)
    {
      setSize(275, i + 50);
      this.pnlListaMenu.setVisible(true);
    }

    return i;
  }

  private void situa(Component paramComponent, String paramString)
  {
    if ((paramComponent != null) && (paramComponent.isVisible()))
    {
      Point localPoint = Herramientas.getPosicion(paramComponent, this, 0, 0);

      setLocation(localPoint);
    }
  }

  public boolean salida()
  {
    Globales.setVariable("devueltaPrograma", "");
    Globales.setVariable("devueltaOpcion", "0");
    Globales.setVariable("devueltaNombreOpcion", "");
    return super.salida();
  }
}