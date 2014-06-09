/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.Component;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.beans.Beans;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import paquete.Contenedores.TPanel;
import ultimo.JPInputComponent;

public class TFocusListener
  implements FocusListener, Serializable
{
  private Component cFantasma;
  private Component cActual;
  private Vector registeredContainers;
  private Vector universalListeners;
  private TComparator myComparator;
  private static TFocusListener aFocusListener = new TFocusListener();

  private TFocusListener()
  {
    this.cFantasma = new TButton();
    this.cActual = this.cFantasma;
    this.registeredContainers = new Vector();
    this.universalListeners = new Vector();
    this.myComparator = new TComparator();
  }

  public static TFocusListener getFocusListener()
  {
    if (!Beans.isDesignTime())
    {
      return aFocusListener;
    }
    return null;
  }

  public void registerContainer(TPanel x)
  {
    registerContainer(x);
  }

  protected void registerContainer(Container pnl)
  {
    if (!this.registeredContainers.contains(pnl))
    {
      this.registeredContainers.addElement(pnl);
    }
  }

  protected void registerUniversalListener(FocusListener f)
  {
    if (!Beans.isDesignTime())
    {
      if (!this.universalListeners.contains(f))
      {
        this.universalListeners.addElement(f);
      }
    }
  }

  public static Component getComponenteActual()
  {
    if (!Beans.isDesignTime())
    {
      return aFocusListener.cActual == aFocusListener.cFantasma ? null : aFocusListener.cActual;
    }
    return null;
  }

  public void unregisterContainer(Container pnl)
  {
    if (this.registeredContainers.contains(pnl))
    {
      this.registeredContainers.removeElement(pnl);
    }
  }

  protected void unregisterUniversalListener(FocusListener f)
  {
    if (this.universalListeners.contains(f))
    {
      this.universalListeners.removeElement(f);
    }
  }

  public void focusGained(FocusEvent event)
  {
    if (Beans.isDesignTime()) return;

    TBarraEstado.setBloqueoBarra(false);

    Object object = event.getSource();

    mostrarComponente(event.getComponent());

    Component cReserva = this.cActual;

    this.cActual = ((Component)object);
    FocusEvent eventoLost = new FocusEvent(
      (Component)object, 
      1005, 
      event.isTemporary());
    fireFocusEvent(eventoLost, containers(cReserva, true));

    this.cActual = cReserva;
    fireFocusEvent(event, containers((Component)object, false));
    if (!Beans.isDesignTime())
    {
      if ((object instanceof JPInputComponent))
      {
        fireFocusEvent(event, this.universalListeners);

        ((JPInputComponent)object).focusGained(event);
      }

    }

    this.cActual = ((Component)object);
  }

  public void focusLost(FocusEvent event)
  {
    if (Beans.isDesignTime()) return;

    Object object = event.getSource();

    if ((object instanceof JPInputComponent))
    {
      fireFocusEvent(event, this.universalListeners);

      ((JPInputComponent)object).focusLost(event);
    }
  }

  private Vector containers(Component c, boolean ascending)
  {
    Vector vPnl = new Vector();

    for (int i = 0; i < this.registeredContainers.size(); i++)
    {
      Container aPnl = (Container)this.registeredContainers.elementAt(i);
      if ((SwingUtilities.isDescendingFrom(c, aPnl)) && 
        (!SwingUtilities.isDescendingFrom(this.cActual, aPnl)))
      {
        vPnl.addElement(aPnl);
      }

    }

    this.myComparator.setOrder(ascending);

    Collections.sort(vPnl, this.myComparator);

    return vPnl;
  }

  private void fireFocusEvent(FocusEvent event, Vector v)
  {
    if (Beans.isDesignTime())
    {
      return;
    }
    int tamV = v.size();
    for (int i = 0; i < tamV; i++)
    {
      Object aPnl = v.elementAt(i);
      if (event.getID() == 1004)
      {
        if ((aPnl instanceof TPanel))
        {
          if (((TPanel)aPnl).getfLost())
            ((TPanel)aPnl).TPanel_focusGained(event);
        }
       /* else if ((aPnl instanceof Serializable)) {
          ((Serializable)aPnl).focusGained(event);
        }*/

      }
      else if ((aPnl instanceof TPanel))
      {
        if (((TPanel)aPnl).getfGained())
          ((TPanel)aPnl).TPanel_focusLost(event);
      }
      /*else if ((aPnl instanceof Serializable))
        ((Serializable)aPnl).focusLost(event);*/
    }
  }

  private void mostrarComponente(Component c)
  {
    if ((c instanceof JComponent))
    {
      Rectangle r = c.getBounds();

      ((JComponent)c.getParent()).scrollRectToVisible(r);
    }
  }

  private class TComparator
    implements Comparator
  {
    boolean ascending = true;

    public TComparator() {
    }
    public void setOrder(boolean ascending) { this.ascending = ascending; }


    public int compare(Object o1, Object o2)
    {
      if (o1 == o2)
      {
        return 0;
      }

      Component c1 = (Component)o1;
      Component c2 = (Component)o2;

      boolean orden = SwingUtilities.isDescendingFrom(c1, c2);
      return orden == this.ascending ? -1 : 1;
    }

    public boolean equals(Object o)
    {
      return this == o;
    }
  }
}
