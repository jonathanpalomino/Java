/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

import java.awt.Component;
import java.awt.Container;
import java.beans.Beans;
import javax.swing.JComponent;
import javax.swing.JPanel;
import paquete.TBarraEstado;
import ultimo.JPTextField;

/**
 *
 * @author synccon
 */
public class ValidacionBloques {

    private boolean encontrado = false;
    private boolean termina = false;
    private boolean validado = true;
    private Component anterior = null;
    private Component primero = null;
    private Component posterior = null;
    private Component ultimo = null;
    private static Component ultimoComponente = null;

    public boolean validaBloques(Container container) {
        if (container == null) {
            return true;
        }
        boolean correcto = true;
        Component[] comps = container.getComponents();
        for (int i = 0; (correcto) && (i < comps.length); i++) {
            if ((comps[i] instanceof JPanel)) {
                correcto = validaBloque((JPanel) comps[i]);
            } else if ((comps[i] instanceof Container)) {
                correcto = validaBloques((Container) comps[i]);
            }
        }
        return correcto;
    }

    public boolean validaBloque(JPanel panel) {
        if (panel == null) {
            TBarraEstado.setMensajeError("El componente no pertenece a un grupo.");
            return false;
        }
        this.termina = false;
        this.validado = true;
        validaBloque(panel, null);
        return this.validado;
    }

    public boolean validaBloque(JPanel panel, Component actual) {
        if (panel == null) {
            TBarraEstado.setMensajeError("El componente no pertenece a un grupo.");
            return false;
        }
        this.termina = false;
        this.validado = true;
        validaBloque(panel, actual, false);
        return this.validado;
    }

    private boolean validaBloque(JComponent panel, Component actual, boolean nulo) {
        if (Beans.isDesignTime()) {
            return true;
        }
        for (int x = 0; (x < panel.getComponentCount()) && (!this.termina); x++) {
            if ((actual != null) && (panel.getComponent(x).hashCode() == actual.hashCode())) {
                this.termina = true;
                return true;
            }

            if ((panel.getComponent(x) instanceof JPTextField)) {
                if (!((JPTextField) panel.getComponent(x)).estaValidado()) {
                    ((JPTextField) panel.getComponent(x)).formateo();
                    ((JPTextField) panel.getComponent(x)).lanzaValidacion(true);
                    if (!((JPTextField) panel.getComponent(x)).estaValidado()) {
                        String s = TBarraEstado.getMensaje();
                        ((JPTextField) panel.getComponent(x)).requestFocus();
                        ((JPTextField) panel.getComponent(x)).setBackground(JPTextField.COLOR_ERROR);

                        TBarraEstado.setMensajeError(s);
                        this.validado = false;
                        this.termina = true;
                    } else {
                        ((JPTextField) panel.getComponent(x)).ponFormato();
                    }

                }

            } else if ((panel.getComponent(x) instanceof JComponent)) {
                if (validaBloque((JComponent) panel.getComponent(x), actual, nulo)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void permiteSalirAtras(Container pnl, JComponent comp) {
        if (pnl == null) {
            TBarraEstado.setMensajeError("El componente no pertenece a un grupo.");
            return;
        }
        permiteSalirAtras(pnl, comp, false);
    }

    public void setUltimoComponente(Component pUltimoComponente) {
        ultimoComponente = pUltimoComponente;
    }

    public Component getUltimoComponente() {
        return ultimoComponente;
    }

    private boolean permiteSalirAtras(Container pnl, JComponent comp, boolean encontrado) {
        if (Beans.isDesignTime()) {
            return true;
        }
        for (int i = 0; i < pnl.getComponentCount(); i++) {
            Component c = pnl.getComponent(i);
            if ((c instanceof JPanel)) {
                encontrado = permiteSalirAtras((JPanel) c, comp, encontrado);
            } else {
                if (!(c instanceof JComponent)) {
                    continue;
                }
                JComponent j = (JComponent) c;
                encontrado = (encontrado) || (j.hashCode() == comp.hashCode());

                j.setVerifyInputWhenFocusTarget(encontrado);
            }
        }
        return encontrado;
    }

    public int esAnterior(Component actual, Container padre, boolean trucho) {
        if (ultimoComponente == null) {
            Herramientas.print("ultimocomponente no está definido.");
            return 0;
        }
        if (actual == null) {
            Herramientas.print("actual no está definido.");
            return 0;
        }
        if (padre == null) {
            Herramientas.print("padre no está definido.");
            return 0;
        }

        for (int x = 0; x < padre.getComponentCount(); x++) {
            if ((padre.getComponent(x) instanceof JPanel)) {
                int res = esAnterior(actual, (Container) padre.getComponent(x), trucho);
                if (res > 0) {
                    return res;
                }
            }
            if (padre.getComponent(x).hashCode() == actual.hashCode()) {
                return 2;
            }
            if (padre.getComponent(x).hashCode() == ultimoComponente.hashCode()) {
                return 1;
            }
        }
        return 0;
    }

  public void invalidaCampos(JPanel panel, JComponent campo)
  {
    if (panel == null)
    {
      TBarraEstado.setMensajeError("El componente no pertenece a un grupo.");
      return;
    }
    this.encontrado = false;
    invalidaCampos(panel, campo, 0);
  }

  public void invalidaCampos(JPanel panel, JComponent campo, int cuenta)
  {
    for (int x = 0; x < panel.getComponentCount(); x++)
    {
      if ((panel.getComponent(x) instanceof JPanel))
      {
        invalidaCampos((JPanel)panel.getComponent(x), campo, cuenta + 1);
      }
      else {
        if ((!this.encontrado) && (panel.getComponent(x).hashCode() == campo.hashCode()))
        {
          this.encontrado = true;
        }

        if (this.encontrado)
        {
          if (panel.getComponent(x).getClass().toString().equals("class paquete.JPTextField"))
          {
            if (panel.getComponent(x).isEnabled())
            {
              ((JPTextField)panel.getComponent(x)).setValidate(false);
            }
          }
        }
      }
    }
  }
}
