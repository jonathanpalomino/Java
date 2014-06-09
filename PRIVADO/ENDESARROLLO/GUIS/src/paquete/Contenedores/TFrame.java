/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.Component;
import javax.swing.JFrame;
import paquete.TButtonAceptar;
import paquete.PRedefinidorTeclas;

/**
 *
 * @author JONATHAN
 */
public class TFrame extends JFrame
  implements PRedefinidorTeclas, TVentana{

    @Override
    public String getMetodoTeclas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getPadreMetodoTeclas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Component getComponenteActual() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setComponenteActual(Component paramComponent) {
        throw new UnsupportedOperationException("Not supported yet.");
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
    
}
