/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimo;

import java.awt.Component;
import javax.swing.JFrame;
import paquete.TButtonAceptar;
import paquete.utiles.Globales;

/**
 *
 * @author JONATHAN
 */
public class JPFrame extends JFrame implements JPRedefinidorTeclas,JPVentana, Cloneable{
private Component componenteActual = null;
    public JPFrame() {
    }

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
        return this.componenteActual;
    }

    @Override
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
    
}
