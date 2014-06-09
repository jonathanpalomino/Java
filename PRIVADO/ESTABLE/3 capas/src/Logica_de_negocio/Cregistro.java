/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica_de_negocio;

/**
 *
 * @author JONATHAN
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Datos.Mregistro;
import Presentacion.Vregistro;

public class Cregistro implements ActionListener,KeyListener {

    Vregistro vReg;
    Mregistro mReg;

    public Cregistro(Vregistro vReg) {
        this.vReg = vReg;
        mReg = new Mregistro();

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == vReg.getCancelar()) {
            System.exit(0);
        } else if (ae.getActionCommand() == "Guardar") {

            datosRegistrar();

        }
    }
    public void keyTyped(KeyEvent e) {
        
    }
    public void keyPressed(KeyEvent e) {
        
    }
    public void keyReleased(java.awt.event.KeyEvent evt) {
        if (evt.getSource() == vReg.getTxtCedula()) {
            BusquedaAproximada();
        }
    }
    private void BusquedaAproximada() {
        if (mReg.Buscar_BD(vReg.getTxtCedula().getText()) == true) {
            JOptionPane.showMessageDialog(null, "Registro existente","Anuncio",JOptionPane.INFORMATION_MESSAGE);
            System.out.println("encontrado");
        } else {
            System.out.println("no encontrado");
        }
    }
    private void datosRegistrar() {
        mReg.setCedula(Integer.parseInt(vReg.getTxtCedula().getText()));
        mReg.setNombre(vReg.getTxtNombre().getText());
        mReg.setApellido(vReg.getTxtApellido().getText());

        if (mReg.registrarDatos()) {
            JOptionPane.showMessageDialog(new JFrame(), "Registro Exitoso", "Registro", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(new JFrame(), "Datos no Registrados", "Registro", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

}
