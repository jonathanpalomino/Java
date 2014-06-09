package encriptameintosimple;

import javax.swing.*;
import java.awt.event.*;
public class Encriptar extends JPanel {
        JLabel lblMensaje = new JLabel();
        JTextField jTextField1 = new JTextField();
        JButton btnEncriptar = new JButton();
        JButton btnNuevo = new JButton();
        JButton btnCerrar = new JButton();
        JTextField jTextField2 = new JTextField();
        JLabel lblClave = new JLabel();
        JLabel lblTextocifrado = new JLabel();
        JButton btnDesencriptar = new JButton();
        JLabel lblTextoNormal = new JLabel();
        JScrollPane jScrollPane1 = new JScrollPane();
        JTextArea jTextArea1 = new JTextArea();
        JScrollPane jScrollPane2 = new JScrollPane();
        JTextArea jTextArea2 = new JTextArea();
    private String clave;

    public Encriptar() {
        clave="";
        IniciarComponentes();
    }

    private void IniciarComponentes() {


        setLayout(null);

        setBackground(UIManager.getDefaults().getColor("textHighlight"));
        lblMensaje.setText("Mensaje:");
        add(lblMensaje);
        lblMensaje.setBounds(170, 30, 60, 14);

        add(jTextField1);
        jTextField1.setBounds(240, 30, 190, 19);

        btnEncriptar.setText("Encriptar!");
        btnEncriptar.setToolTipText("Permite Encriptar el texto con la clave seleccionada");
        btnEncriptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnEncriptar(evt);
            }
        });

        add(btnEncriptar);
        btnEncriptar.setBounds(440, 23, 90, 30);

        btnNuevo.setText("Nuevo");
        btnNuevo.setToolTipText("Nuevo");
        btnNuevo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnNuevo(evt);
            }
        });

        add(btnNuevo);
        btnNuevo.setBounds(440, 60, 90, 23);

        btnCerrar.setText("Cerrar");
        btnCerrar.setToolTipText("Salir del programa");
        btnCerrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnCerrar(evt);
            }
        });

        add(btnCerrar);
        btnCerrar.setBounds(440, 90, 90, 23);

        jTextField2.setColumns(5);
        jTextField2.setToolTipText("Se recomienda claves de 10 a 1000");
        add(jTextField2);
        jTextField2.setBounds(240, 80, 40, 19);

        lblClave.setText("Clave:");
        add(lblClave);
        lblClave.setBounds(180, 80, 40, 14);

        lblTextocifrado.setText("Texto Cifrado");
        add(lblTextocifrado);
        lblTextocifrado.setBounds(130, 160, 100, 14);

        btnDesencriptar.setText("Desencriptar!");
        btnDesencriptar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDesencriptar(evt);
            }
        });

        add(btnDesencriptar);
        btnDesencriptar.setBounds(210, 410, 130, 30);

        lblTextoNormal.setText("Texto Normal");
        add(lblTextoNormal);
        lblTextoNormal.setBounds(130, 420, 80, 14);

        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Courier", 1, 13));
        jTextArea1.setForeground(new java.awt.Color(51, 51, 51));
        jTextArea1.setLineWrap(true);
        jTextArea1.setBorder(new javax.swing.border.MatteBorder(new ImageIcon("C:\\graficos\\nike.jpg")));
        jScrollPane1.setViewportView(jTextArea1);

        add(jScrollPane1);
        jScrollPane1.setBounds(130, 180, 520, 220);

        jTextArea2.setEditable(false);
        jTextArea2.setFont(new java.awt.Font("Courier", 1, 13));
        jTextArea2.setForeground(new java.awt.Color(0, 102, 0));
        jTextArea2.setLineWrap(true);
        jTextArea2.setBorder(new javax.swing.border.MatteBorder(new ImageIcon("C:\\graficos\\20.png")));
        jScrollPane2.setViewportView(jTextArea2);

        add(jScrollPane2);
        jScrollPane2.setBounds(130, 450, 520, 200);

    }


    private void btnCerrar(ActionEvent evt) {

       setVisible(false);
        
        
    }

    private void btnNuevo(ActionEvent evt) {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextArea1.setText("");
        jTextArea2.setText("");
        clave ="";
    }

    private void btnDesencriptar(ActionEvent evt) {

         try{
        if(jTextArea1.getText().equals(""))
            JOptionPane.showMessageDialog( this,"Error: No existe texto encriptado", "E R R O R !",JOptionPane.ERROR_MESSAGE);
            
        if(clave.equals(jTextField2.getText()))
            jTextArea2.setText(Cifrar.desencriptar(jTextArea1.getText(), Integer.parseInt(jTextField2.getText())));
        else  
          JOptionPane.showMessageDialog( this,"Alerta: Clave cambiada, por favor ingrese \nsu clave anterior...", "A L E R T A !",JOptionPane.WARNING_MESSAGE );
   }catch (Exception e) {
       JOptionPane.showMessageDialog( this,"Error: No existe clave para desencriptar", "E R R O R !",JOptionPane.ERROR_MESSAGE );
   }
    }

    private void btnEncriptar(ActionEvent evt) {

        try{
        if(jTextField1.getText().equals(""))
           JOptionPane.showMessageDialog( this,"Error de texto: No existe texto a encriptar", "E R R O R!",JOptionPane.ERROR_MESSAGE );
        else if(jTextField2.getText().equals(""))
                JOptionPane.showMessageDialog( this,"Error de Clave: No existe clave", "E R R O R!",JOptionPane.ERROR_MESSAGE );
            if(Integer.parseInt(jTextField2.getText()) < 10 || Integer.parseInt(jTextField2.getText()) > 1000)
                JOptionPane.showMessageDialog( this,"Error de Clave: Rango no permitido \nIngrese clave: 10 a 1000", "A L E R T A !",JOptionPane.WARNING_MESSAGE );    
            else{
                    jTextArea1.setText(Cifrar.encriptar(jTextField1.getText(), Integer.parseInt(jTextField2.getText())));
                    clave = jTextField2.getText();
                 
            }
        }catch (NumberFormatException e){
                    JOptionPane.showMessageDialog( this,"Error: Dato no adecuado para la clave...ingrese un entero por favor", "Error de formato!",JOptionPane.ERROR_MESSAGE);
         }
    }
    
}
