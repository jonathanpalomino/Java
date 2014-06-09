/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimo;

import java.awt.Component;
import javax.swing.JRootPane;
import paquete.TButtonAceptar;

/**
 *
 * @author JONATHAN
 */
public abstract interface JPVentana {

    public abstract Component getComponenteActual();

    public abstract void setComponenteActual(Component paramComponent);

    public abstract TButtonAceptar getMiBotonAceptar();

    public abstract void setMiBotonAceptar(TButtonAceptar paramTButtonAceptar);

    public abstract void setAceptado(boolean paramBoolean);

    public abstract boolean getAceptado();

    public abstract JRootPane getRootPane();
}
