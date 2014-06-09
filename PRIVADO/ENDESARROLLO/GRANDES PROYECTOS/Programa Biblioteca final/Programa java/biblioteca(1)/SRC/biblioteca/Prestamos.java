
package biblioteca;

import java.sql.*;
import javax.swing.JOptionPane;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class Prestamos extends javax.swing.JFrame {
String direccion="contactobiblioteca@gmail.com";
String host = "smtp.gmail.com";
String autentificar = "false";
String puerto="587";
String contrasena = "initiald";
        String contraseña;
        String usuario;
        String url="jdbc:mysql://localhost:3306/programa_biblioteca";
        Connection conexion=null;
        
    public Prestamos(String user,String pass,String urll)
    {
        usuario=user;
        contraseña= pass;
        url= urll;
        setLocation(200, 300);
        initComponents();
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree();
        lblLibro = new javax.swing.JLabel();
        ComboxLibro = new javax.swing.JComboBox();
        txtLibro = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        ComboxUsuario = new javax.swing.JComboBox();
        txtUsuario = new javax.swing.JTextField();
        lblPrestamo = new javax.swing.JLabel();
        lblCaducidad = new javax.swing.JLabel();
        txtFecha1 = new javax.swing.JTextField();
        txtFecha2 = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblLibro1 = new javax.swing.JLabel();
        ComboxAutor = new javax.swing.JComboBox();
        txtAutor = new javax.swing.JTextField();
        lblCaducidad1 = new javax.swing.JLabel();
        lblCaducidad2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jScrollPane1.setViewportView(jTree1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Prestamo de Libro");

        lblLibro.setText("Libro");

        ComboxLibro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nuevo Libro", "Localizar Libro" }));

        lblUsuario.setText("Usuario");

        ComboxUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nuevo Usuario", "Localizar Usuario" }));

        lblPrestamo.setText("Fecha Prestamo");

        lblCaducidad.setText("Fecha Caducidad");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblLibro1.setText("Autor");

        ComboxAutor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nuevo Autor", "Localizar Autor" }));

        lblCaducidad1.setText("(año-mes-dia)");

        lblCaducidad2.setText("(año-mes-dia)");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/biblioteca_virtual.jpg"))); // NOI18N

        jButton1.setText("Enviar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLibro1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblUsuario)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtFecha2, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblPrestamo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(lblCaducidad2))
                                    .addComponent(lblCaducidad))))
                        .addGap(119, 119, 119))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCaducidad1, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(216, 216, 216))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(ComboxAutor, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ComboxUsuario, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtLibro, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAutor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)))
                    .addComponent(ComboxLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(32, 32, 32)
                                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblLibro, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboxLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtLibro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addComponent(lblLibro1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ComboxAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAutor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblUsuario)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblPrestamo)
                            .addComponent(lblCaducidad))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCaducidad1)
                            .addComponent(lblCaducidad2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAceptar))))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
         
    try {
         if (ComboxLibro.getSelectedItem().equals("Nuevo Libro")& ComboxAutor.getSelectedItem().equals("Nuevo Autor")& ComboxUsuario.getSelectedItem().equals("Nuevo Usuario"))
         {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if (conexion !=null)
            {
             Busqueda_General(txtUsuario.getText());
           Statement st = conexion.createStatement();
           st.executeUpdate("INSERT INTO registro (Titulo, Autor, Apellidos, FPrestamo, FCaducidad,Condicion) VALUES ('"+txtLibro.getText()+"','"+txtAutor.getText()+"','"+txtUsuario.getText()+"','"+txtFecha1.getText()+"','"+txtFecha2.getText()+"' ,'prestado')");
           ResultSet resp  = st.executeQuery("SELECT * FROM registro");
           while(resp.next())
           {
              System.out.println(resp.getObject("Titulo") +"\t\t"+ resp.getObject("Autor")+"\t\t"+resp.getObject("Apellidos")+"\t\t"+resp.getObject("FPrestamo")+"\t\t"+resp.getObject("FCaducidad")+"\t\t"+resp.getObject("Condicion"));
           }
      
          resp.close();
          st.close();
       }
   conexion.close();
         }

 if (ComboxLibro.getSelectedItem().equals("Localizar Libro"))
         {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         Busqueda_General(txtUsuario.getText());
         if (conexion !=null)
            {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM registro");
           resp = st.executeQuery("Select *from registro where(Titulo='"+txtLibro.getText()+"')");
           while(resp.next())
           {
             String a=(String)resp.getObject("Titulo");
             String b=(String)resp.getObject("Autor");
             String c = (String)resp.getObject("Apellidos");
             txtLibro.setText(a);
             txtAutor.setText(b);
             txtUsuario.setText(c);
             txtFecha1.setText(String.valueOf(resp.getObject("FPrestamo")));
             txtFecha2.setText(String.valueOf(resp.getObject("FCaducidad")));
           }
      
          resp.close();
          st.close();
       }
   conexion.close();
          }

 if (ComboxAutor.getSelectedItem().equals("Localizar Autor"))
         {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if (conexion !=null)
            {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM registro");
           resp = st.executeQuery("Select *from registro where(Autor='"+txtAutor.getText()+"')");
           while(resp.next())
           {
             String a=(String)resp.getObject("Titulo");
             String b=(String)resp.getObject("Autor");
             String c = (String)resp.getObject("Apellidos");
             txtLibro.setText(a);
             txtAutor.setText(b);
             txtUsuario.setText(c);
             txtFecha1.setText(String.valueOf(resp.getObject("FPrestamo")));
             txtFecha2.setText(String.valueOf(resp.getObject("FCaducidad")));
           }

          resp.close();
          st.close();
       }
   conexion.close();
  }


 if (ComboxUsuario.getSelectedItem().equals("Localizar Usuario"))
         {
         Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if (conexion !=null)
            {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM registro");
           resp = st.executeQuery("Select *from registro where(Apellidos='"+txtUsuario.getText()+"')");
           while(resp.next())
           {
             String a=(String)resp.getObject("Titulo");
             String b=(String)resp.getObject("Autor");
             String c = (String)resp.getObject("Apellidos");
             txtLibro.setText(a);
             txtAutor.setText(b);
             txtUsuario.setText(c);
             txtFecha1.setText(String.valueOf(resp.getObject("FPrestamo")));
             txtFecha2.setText(String.valueOf(resp.getObject("FCaducidad")));
           }

          resp.close();
          st.close();
       }
   conexion.close();
        }


    }
    catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");
        txtLibro.setText("");
        txtAutor.setText("");
        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        EnvaiarALCorreo();
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboxAutor;
    private javax.swing.JComboBox ComboxLibro;
    private javax.swing.JComboBox ComboxUsuario;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTree1;
    private javax.swing.JLabel lblCaducidad;
    private javax.swing.JLabel lblCaducidad1;
    private javax.swing.JLabel lblCaducidad2;
    private javax.swing.JLabel lblLibro;
    private javax.swing.JLabel lblLibro1;
    private javax.swing.JLabel lblPrestamo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JTextField txtAutor;
    private javax.swing.JTextField txtFecha1;
    private javax.swing.JTextField txtFecha2;
    private javax.swing.JTextField txtLibro;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    boolean Busqueda_General(String text) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
            Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");
           while(resp.next())
           {
               if(resp.getObject("Codigo")!=text)
               {
                   JOptionPane.showMessageDialog(null,"Valor no Encontrado");
                   return false;
               }

           }
            }
        }

        catch(SQLException ex)
        {
        JOptionPane.showMessageDialog(null,"Error");
        txtLibro.setText("");
        txtAutor.setText("");
        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
        return true;

    }
    public void EnvaiarALCorreo()
    {
         String libro = txtLibro.getText();
 String autor = txtAutor.getText();
 String usuario = txtUsuario.getText();
 String FPrestamo= txtFecha1.getText();
         String FCaducidad = txtFecha2.getText();
        try
        {
            // Propiedades de la conexión
            Properties props = new Properties();
            props.setProperty("mail.smtp.host",host);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", puerto);
            props.setProperty("mail.smtp.user", direccion);
            props.setProperty("mail.smtp.auth", autentificar);

            // Preparamos la sesion
            Session session = Session.getDefaultInstance(props);

            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("Usuario@gmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(direccion));
            message.setSubject("Pedido");
            message.setText("Libro: "+txtLibro.getText()+"\nAutor: "+txtAutor.getText()+"\nUsuario: "+txtUsuario.getText()+"\n\n"+"\nFecha de Inicio"+txtFecha1.getText()+"\n\n"+"\nFecha de Fin"+txtFecha2.getText());

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(direccion, contrasena);
            t.sendMessage(message, message.getAllRecipients());

            // Cierre.
            t.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
