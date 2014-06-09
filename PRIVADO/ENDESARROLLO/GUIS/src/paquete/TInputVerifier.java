/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.beans.Beans;
import java.io.Serializable;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import paquete.Contenedores.TVentana;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import ultimo.JPTextField;

public class TInputVerifier extends InputVerifier
        implements Serializable {

    public boolean verify(JComponent j) {

        if (Beans.isDesignTime()) {
            return true;
        }

        boolean aux = true;

        if (j.getInputVerifier() == null) {
            return true;
        }

        TInputVerifier verificador = (TInputVerifier) j.getInputVerifier();
        j.setInputVerifier(null);

        if ((j instanceof JPTextField)) {
            JPTextField t = (JPTextField) j;

            Herramientas.setCursorEspera();
            aux = verifyJPTextField(t);      //ERROR AQUI

            if (!aux) {
                t.setBackground(JPTextField.COLOR_ERROR);

                if (t.isEnabled()) {
                    t.selectAll();
                }

            } else if (t.getText().length() != 0) {
                t.select(0, 0);
            }

            Herramientas.setCursorNormal();
            t.setValidate(aux);
        }
        /*else if ((j instanceof TPasswordField))
         {
         TPasswordField t = (TPasswordField)j;

         Herramientas.setCursorEspera();
         aux = verifyTPasswordField(t);
         if (!aux)
         {
         t.setBackground(PTextField.COLOR_ERROR);
         }
         Herramientas.setCursorNormal();
         }*/

         j.setInputVerifier(verificador); //No borrar
        return aux;
    }

    private boolean verifyJPTextField(JPTextField t) {
        if (Beans.isDesignTime()) {
            return true;
        }
        boolean aux = true;

        t.activarFocusListener(false);

        Herramientas.iniciaCuentaDialogos();
        if (!t.lanzaValidacion(false)) {
            aux = false;
        } else if (!t.ejecutaMetodo(2, null)) {
            aux = false;
        }
        t.activarFocusListener(true);
        int numDialogos = Herramientas.totalCuentaDialogos();

        if ((aux) && (numDialogos > 0)) {
            if (!Beans.isDesignTime()) {
                t.lanzaFocusLost();
            }
        }
        Herramientas.terminaCuentaDialogos();

        if ((!aux) && (!t.isShowing())) {
            mostrar(t);
        }
        return aux;
    }
    /*
     private boolean verifyTPasswordField(TPasswordField t) {
     if (Beans.isDesignTime()) return true;
     return t.lanzaValidacion();
     }*/

    private void mostrar(Component comp) {
        Container c = comp.getParent();
        Component anterior = comp;
        boolean salir = false;
        while (!salir) {
            if (((c.getLayout() instanceof CardLayout))
                    || ((c instanceof JTabbedPane))
                    || ((c instanceof TVentana))
                    || (c.getParent() == null)) {
                salir = true;
            } else {
                anterior = c;
                c = c.getParent();
            }
        }
        if ((c.getLayout() instanceof CardLayout)) {
            anterior.setVisible(true);
        } else if ((c instanceof JTabbedPane)) {
            ((JTabbedPane) c).setSelectedComponent(anterior);
            anterior.setVisible(true);
        }

        Component padre = Globales.getPadre(comp);
        if (padre != null) {
            padre.validate();
        }
    }
}