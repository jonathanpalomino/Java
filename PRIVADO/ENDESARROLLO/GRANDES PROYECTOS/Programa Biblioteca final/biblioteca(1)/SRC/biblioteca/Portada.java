
package biblioteca;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author Jonathan
 */
public class Portada extends JFrame implements ActionListener
{
        JLabel lblUsuario = new JLabel("Usuario");
        JLabel lblContraseña = new JLabel("Contraseña");
        JLabel vacio = new JLabel("");
        JTextField txtUsuario = new JTextField();
        JPasswordField passContraseña = new JPasswordField();
        JButton btnAceptar = new JButton("Aceptar");
        JButton btnCancelar = new JButton("Cancelar");
    public Portada()
   {
        setTitle("Sistema de Biblioteca.......Ingreso");
        setSize(400,400);
        setLocation(250, 300);

        JMenuBar barra = new JMenuBar();
        JMenu menu = new JMenu("Archivo");
        JMenuItem sub = new JMenuItem("Ayuda");
        sub.addActionListener(this);
        menu.add(sub);

        sub = new JMenuItem("Salir");
        sub.addActionListener(this);
        menu.add(sub);

        lblContraseña.setBounds(100,120, 100, 20);
        lblUsuario.setBounds(100,80, 100, 20);
        txtUsuario.setBounds(200, 80, 100, 20);
        txtUsuario.setToolTipText("Ingrese su Usuario");
        passContraseña.setBounds(200, 120, 100, 20);
        passContraseña.setToolTipText("Ingrese su Contraseña");
        btnAceptar.setBounds(80, 200, 100, 20);
        btnAceptar.setToolTipText("Acepta y prosige");
        btnCancelar.setBounds(220, 200, 100, 20);
        btnCancelar.setToolTipText("Cancela y chau");

        add(txtUsuario);
        add(lblContraseña);
        add(lblUsuario);
        add(passContraseña);
        add(btnAceptar);
        add(btnCancelar);
        add(vacio);
        
        barra.add(menu);
        setJMenuBar(barra);

        btnAceptar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) 
            {
                if(txtUsuario.getText().equals("Administrador") && passContraseña.getText().equals("123"))
                {
                Principal obj = new Principal(txtUsuario.getText(),passContraseña.getText());
                obj.show();
                dispose();
                }
                else if(txtUsuario.getText().equals("") && passContraseña.getText().equals(""))
                {
                Principal obj = new Principal(txtUsuario.getText(),passContraseña.getText());
                obj.show();
                dispose();
                }
                
            }
        });

        btnCancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                txtUsuario.setText("");
                passContraseña.setText("");
            }
        });
   }
public static void main(String[] args)
{
    Portada obj = new Portada();
    obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    obj.show();
}

    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Ayuda"))
        {
            JOptionPane.showMessageDialog(rootPane,"Si no tienes Usuario solo pulsa Aceptar");
        }
        else if(e.getActionCommand().equals("Salir"))
        {
            System.exit(0);
        }
    }
}
