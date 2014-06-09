/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.Contenedores;

import java.awt.Component;
import javax.swing.JRootPane;
import paquete.TButtonAceptar;

/**
 *
 * @author synccon
 */
public abstract interface TVentana {

    public abstract Component getComponenteActual();

    public abstract void setComponenteActual(Component paramComponent);

    public abstract TButtonAceptar getMiBotonAceptar();

    public abstract void setMiBotonAceptar(TButtonAceptar paramTButtonAceptar);

    public abstract void setAceptado(boolean paramBoolean);

    public abstract boolean getAceptado();

    public abstract boolean ejecutaMenuOpciones();

    public abstract String getMenuOpciones();

    public abstract String getMetodoPostMenu();

    public abstract String getMetodoPreMenu();

    public abstract boolean isVentanaAuxiliar();

    public abstract JRootPane getRootPane();

    public abstract void liberarCursores();

    public abstract boolean isAutoCommit();

    public abstract boolean isPadreSesion();

    public abstract void setPadreSesion(boolean paramBoolean);

    public abstract void setPadreSesion();
}
