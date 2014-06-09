/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;


import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.Beans;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import ultimo.JPTextField;

public class TPopupMenu extends JPopupMenu
{
  static final String DIR_IMAGENES = Herramientas.getRutarepLocal() + "/images/";
  Component textPadre;
  Container padreBarras = null;
  JMenuItem copiar;
  JMenuItem pegar;
  JMenuItem cortar;
  JMenuItem ayuda;
  JMenuItem lista;
  SymAction accion;
  Mouse mouse = null;

  public TPopupMenu()
  {
    this.accion = new SymAction();
    this.mouse = new Mouse();
    try
    {
      this.copiar = new JMenuItem(
        Globales.getCodigoTextoHotKey("trn", 172)[0], 
        new ImageIcon(DIR_IMAGENES + "copiar.gif"));

      this.pegar = new JMenuItem(
        Globales.getCodigoTextoHotKey("trn", 173)[0], 
        new ImageIcon(DIR_IMAGENES + "pegar.gif"));
      this.cortar = new JMenuItem(Globales.leeCodigoTexto("trn", 174), 
        new ImageIcon(DIR_IMAGENES + "cortar.gif"));

      this.ayuda = new JMenuItem(Globales.leeCodigoTexto("trn", 176));
      this.lista = new JMenuItem(Globales.leeCodigoTexto("trn", 175));
      if (!Beans.isDesignTime())
      {
        this.copiar.addActionListener(this.accion);
        this.copiar.addMouseListener(this.mouse);
        this.pegar.addActionListener(this.accion);
        this.pegar.addMouseListener(this.mouse);
        this.cortar.addActionListener(this.accion);
        this.cortar.addMouseListener(this.mouse);
      }
    }
    catch (NoSuchMethodError localNoSuchMethodError)
    {
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    if (!Beans.isDesignTime())
    {
      SymKey aSymKey = new SymKey();
      addKeyListener(aSymKey);

      addMouseListener(this.mouse);
    }
  }

  public TPopupMenu(JComponent componente)
  {
    this();
    this.textPadre = componente;
  }

  public void setCampo(JPTextField campo)
  {
    this.textPadre = campo;
  }

  public void setPadreBarras(Container pPadreBarras)
  {
    this.padreBarras = pPadreBarras;
  }

  public void muestraMenuText()
  {
    removeAll();

    Container lPadreBarras = this.padreBarras != null ? this.padreBarras : (Container)Globales.getPadre(getInvoker(), true);
    Vector vBarras = TBarraHerramientas.getBarrasHerramientas(lPadreBarras);

    int tam = vBarras.size();
    boolean bPegar;
    boolean bCortar;
    boolean bCopiar = bCortar = bPegar = (getInvoker() instanceof JTextField) ? false : true;

    for (int i = 0; i < tam; i++)
    {
      TBarraHerramientas brh = (TBarraHerramientas)vBarras.elementAt(i);
      brh.refrescarBarra(getInvoker());

      if (brh.isVisible())
      {
        JLabel[] lbls = brh.getLabelsBotones();
        JMenuItem[] items = new JMenuItem[lbls.length];
        boolean algunEnabled = false;

        for (int j = 0; (j < lbls.length) && (!algunEnabled); j++)
        {
          String toolTip = lbls[j].getToolTipText();
          if ((toolTip != null) && (!toolTip.trim().equals("")))
          {
            algunEnabled = (algunEnabled) || (lbls[j].isEnabled());
          }

        }

        if (algunEnabled)
        {
          for (int j = 0; j < lbls.length; j++)
          {
            String toolTip = lbls[j].getToolTipText();
            if ((toolTip != null) && (!toolTip.trim().equals("")))
            {
              items[j] = new JMenuItem(toolTip, lbls[j].getIcon());
              if (!Beans.isDesignTime())
              {
                items[j].addActionListener(new MiAccion(brh, j));
                items[j].addMouseListener(this.mouse);
              }
              items[j].setEnabled(lbls[j].isEnabled());

              add(items[j]);
              bCopiar = (bCopiar) || (items[j].getText().equals(this.copiar.getText()));
              bCortar = (bCortar) || (items[j].getText().equals(this.cortar.getText()));
              bPegar = (bPegar) || (items[j].getText().equals(this.pegar.getText()));
            }

          }

          add(new JPopupMenu.Separator());
        }

      }

    }

    if (getComponentCount() > 0) {
      remove(getComponentCount() - 1);
    }
    if ((!bCopiar) || (!bCopiar) || (!bPegar))
      add(new JPopupMenu.Separator());
    if (!bCopiar)
      add(this.copiar);
    if (!bCortar)
      add(this.cortar);
    if (!bPegar)
      add(this.pegar);
  }

  void copiar_actionPerformed(ActionEvent event)
  {
    ((JTextField)getInvoker()).copy();
  }

  void pegar_actionPerformed(ActionEvent event)
  {
    ((JTextField)getInvoker()).paste();
  }

  void cortar_actionPerformed(ActionEvent event)
  {
    ((JTextField)getInvoker()).cut();
    if ((getInvoker() instanceof JPTextField))
      ((JPTextField)getInvoker()).setPrimerAcceso(false);
  }

  void TPopupMenu_keyPressed(KeyEvent event)
  {
    setVisible(false);
  }

  public void setVisible(boolean b)
  {
    if ((b) && (!isVisible()))
    {
      muestraMenuText();
    }
    super.setVisible(b);
  }

  public void menuSelectionChanged(boolean isIncluded)
  {
    super.menuSelectionChanged(isIncluded);
  }

  void TPopupMenu_mouseReleased(MouseEvent event)
  {
  }

  class SymAction
    implements ActionListener
  {
    SymAction()
    {
    }

    public void actionPerformed(ActionEvent event)
    {
      Object object = event.getSource();
      if (object == TPopupMenu.this.copiar)
        TPopupMenu.this.copiar_actionPerformed(event);
      else if (object == TPopupMenu.this.pegar)
        TPopupMenu.this.pegar_actionPerformed(event);
      else if (object == TPopupMenu.this.cortar)
        TPopupMenu.this.cortar_actionPerformed(event);
    }
  }

  class MiAccion
    implements ActionListener
  {
    TBarraHerramientas brh;
    int indice;

    public MiAccion(TBarraHerramientas brh, int indice)
    {
      this.brh = brh;
      this.indice = indice;
    }

    public void actionPerformed(ActionEvent e) {
      this.brh.pulsaBoton(this.indice);
    }
  }

  class SymKey extends KeyAdapter
  {
    SymKey()
    {
    }

    public void keyPressed(KeyEvent event)
    {
      Object object = event.getSource();
      if (object == TPopupMenu.this)
        TPopupMenu.this.TPopupMenu_keyPressed(event);
    }
  }

  class Mouse extends MouseAdapter
  {
    Mouse()
    {
    }

    public void mouseReleased(MouseEvent event)
    {
      event.consume();
    }
  }
}
