/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import javax.swing.JDialog;
import paquete.PRedefinidorTeclas;
import paquete.TButtonAceptar;
import paquete.utiles.Globales;

/**
 *
 * @author synccon
 */
public class TDialog extends JDialog implements PRedefinidorTeclas, TVentana, Cloneable {

    private Component componenteActual = null;

    public TDialog() {
    }

    public TDialog(Dialog parent) {
    }

    public TDialog(Frame parent) {
    }

    public Component getComponenteActual() {
        return this.componenteActual;
    }

    public void setComponenteActual(Component c) {
        this.componenteActual = c;
        Globales.setComponenteActual(c);
    }

    @Override
    public TButtonAceptar getMiBotonAceptar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setMiBotonAceptar(TButtonAceptar paramTButtonAceptar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setAceptado(boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean getAceptado() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean ejecutaMenuOpciones() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMenuOpciones() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMetodoPostMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMetodoPreMenu() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isVentanaAuxiliar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void liberarCursores() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isAutoCommit() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isPadreSesion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPadreSesion(boolean paramBoolean) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setPadreSesion() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getMetodoTeclas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getPadreMetodoTeclas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    boolean salida() {
        return false;

    }
}
