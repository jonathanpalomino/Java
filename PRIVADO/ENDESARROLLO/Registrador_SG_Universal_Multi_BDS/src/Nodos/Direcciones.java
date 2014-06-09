/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodos;

import java.util.ArrayList;

/**
 *
 * @author JONATHAN1
 */
public class Direcciones {

    private String Nombre;
    private ArrayList Contenido;

    // constructor vacio
    public Direcciones() {
    }

    // constructor que recibe un objeto con datos
    public Direcciones(Direcciones obj) {
        Nombre = obj.getNombre();
        Contenido = obj.getContenido();
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public ArrayList getContenido() {
        return Contenido;
    }
    public void setContenido(ArrayList Direccion) {
        this.Contenido = Direccion;
    }
}
