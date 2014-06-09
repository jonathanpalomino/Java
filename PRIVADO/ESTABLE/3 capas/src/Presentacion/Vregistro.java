/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

/**
 *
 * @author JONATHAN
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Logica_de_negocio.Cregistro;

public class Vregistro extends JFrame {

    JPanel jp, jp1;
    JLabel Nombre, Apellido, Cedula, validarCed;
    JTextField txtNombre, txtApellido, txtCedula;
    JButton guardar, cancelar;
    GridBagConstraints gridt;
    Cregistro ctrol;

    public Vregistro() {

        ctrol = new Cregistro(this);

        gridt = new GridBagConstraints();
        gridt.fill = GridBagConstraints.BOTH;
        gridt.insets = new Insets(2, 2, 4, 4);

        jp = new JPanel();
        jp.setLayout(new GridBagLayout());
        jp.setBackground(Color.WHITE);

        Cedula = new JLabel("Cedula:  ");
        Cedula.setFont(new Font("Century Gothic", Font.BOLD, 14));
        Cedula.setForeground(new Color(0, 0, 128));
        ord(0, 0, 1, 1);
        jp.add(Cedula, gridt);

        txtCedula = new JTextField(10);
        ord(1, 0, 1, 2);
        //txtCedula.addActionListener(ctrol);
        txtCedula.addKeyListener(ctrol);
        jp.add(txtCedula, gridt);

        /*Necesito que el mensaje de si la cedula
         *  esta o no registrada en la BD se
         *  muestra a traves de este JLabel */

        validarCed = new JLabel("");
        validarCed.setFont(new Font("Century Gothic", Font.BOLD, 14));
        validarCed.setForeground(new Color(0, 0, 128));
        ord(3, 0, 1, 1);
        jp.add(validarCed, gridt);

        Nombre = new JLabel("Nombre:  ");
        Nombre.setFont(new Font("Century Gothic", Font.BOLD, 14));
        Nombre.setForeground(new Color(0, 0, 128));
        ord(0, 2, 1, 1);
        jp.add(Nombre, gridt);

        txtNombre = new JTextField(10);
        ord(1, 2, 1, 3);
        jp.add(txtNombre, gridt);

        Apellido = new JLabel("Apellido: ");
        Apellido.setFont(new Font("Century Gothic", Font.BOLD, 14));
        Apellido.setForeground(new Color(0, 0, 128));
        ord(0, 4, 1, 1);
        jp.add(Apellido, gridt);

        txtApellido = new JPasswordField(10);
        ord(1, 4, 1, 3);
        jp.add(txtApellido, gridt);


        guardar = new JButton("Guardar");
        guardar.setFont(new Font("Century Gothic", Font.BOLD, 14));
        ord(2, 7, 1, 1);
        guardar.addActionListener(ctrol);
        jp.add(guardar, gridt);

        cancelar = new JButton("Cancelar");
        cancelar.setFont(new Font("Century Gothic", Font.BOLD, 14));
        ord(3, 7, 1, 1);
        cancelar.addActionListener(ctrol);
        jp.add(cancelar, gridt);


        add(jp);


        setSize(640, 420);
        setLocation(150, 100);
        setVisible(true);
        setResizable(false);

        repaint();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void ord(int x, int y, int h, int w) {

        gridt.gridx = x;
        gridt.gridy = y;
        gridt.gridheight = h;
        gridt.gridwidth = w;
    }

    public static void main(String[] args) {

        Vregistro vu = new Vregistro();

    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(JTextField txtNombre) {
        this.txtNombre = txtNombre;
    }

    public JTextField getTxtApellido() {
        return txtApellido;
    }

    public void setTxtApellido(JTextField txtApellido) {
        this.txtApellido = txtApellido;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public void setTxtCedula(JTextField txtCedula) {
        this.txtCedula = txtCedula;
    }

    public JButton getGuardar() {
        return guardar;
    }

    public void setGuardar(JButton guardar) {
        this.guardar = guardar;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }



}
