/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

/**
 *
 * @author JONATHAN
 */
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mregistro extends ModeloBD {

    private int Cedula;
    private String Nombre;
    private String Apellido;

    public int getCedula() {
        return Cedula;
    }

    public void setCedula(int cedula) {
        Cedula = cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public boolean registrarDatos() {

        String sent = "INSERT INTO datos VALUES (" + getCedula() + ",'" + getNombre() + "','" + getApellido() + "');";

        boolean rs = ejecutarUpdate(sent);
        return rs;

    }

    public boolean Buscar_BD(String texto) {
        boolean rs = false;
        try {
            String sent = "Select cedula from datos where cedula ='" + texto + "';";
            ResultSet resp = ejecutarQuery(sent);
            if (resp.first()) {
                resp.first();
                String origen = resp.getObject(1).toString();
                if (origen.equals(texto)) {
                    rs = true;
                }
            }
            
        } catch (SQLException ex) {
        }

        return rs;
    }
}
