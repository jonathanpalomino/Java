/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problema1;

/**
 *
 * @author JONATHAN
 */
public class Pila {

public NodoSimple ultimo;
public NodoSimple nuevo;

Pila(){ultimo = null;nuevo=null;}

    public NodoSimple getUltimo() {
        return ultimo;
    }

    public void setUltimo(NodoSimple ultimo) {
        this.ultimo = ultimo;
    }

    public NodoSimple getNuevo() {
        return nuevo;
    }

    public void setNuevo(NodoSimple nuevo) {
        this.nuevo = nuevo;
    }

    void agrega(Triangulo obj)
    {
        NodoSimple novo;
        //...preparar nuevo nodo ...
        novo = new NodoSimple(obj);
        novo.getAnterior().setAnterior(ultimo);
        setUltimo(getNuevo());
    }
}
