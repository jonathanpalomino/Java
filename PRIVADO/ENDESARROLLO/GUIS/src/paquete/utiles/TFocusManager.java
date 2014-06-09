/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

/**
 *
 * @author JONATHAN
 */

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.beans.Beans;
import javax.swing.DefaultFocusManager;

public class TFocusManager extends DefaultFocusManager
{
  public void processKeyEvent(Component focusedComponent, KeyEvent anEvent)
  {
    processKeyEvent(focusedComponent, anEvent, true);
  }

  public void processKeyEvent(Component focusedComponent, KeyEvent anEvent, boolean desactiva)
  {
    if (Beans.isDesignTime()) return;

    if (desactiva)
    {
      if ((anEvent.getKeyCode() == 9) && 
        ((anEvent.getModifiers() & 0x2) == 2))
        return;
    }
    super.processKeyEvent(focusedComponent, anEvent);
  }
}