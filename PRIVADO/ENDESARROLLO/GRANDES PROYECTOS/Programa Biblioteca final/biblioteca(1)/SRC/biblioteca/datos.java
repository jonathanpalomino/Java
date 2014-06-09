
package biblioteca;
import java.util.Date;
import java.util.Properties;
import javax.swing.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.swing.ImageIcon;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
public class datos extends javax.swing.JFrame {
JLabel etiqueta;
ImageIcon barp = new ImageIcon("//SRC//biblioteca//bar.gif");
String direccion="contactobiblioteca@gmail.com";
    String host = "smtp.gmail.com";
    String puerto="587";
 String autentificar = "true";
  String contraseña = "initiald";
 
    /*String direccion = "jonathan@user";
    String contraseña ="123456";
    String host ="localhost";
    String puerto ="25";
    String autentificar = "false";*/
    /** Creates new form datos */
    public datos() {
        setLocation(250, 250);
        initComponents();
        etiqueta=  new JLabel("Barra de Progreso",barp,SwingConstants.LEFT);
        add( etiqueta);
        etiqueta.setToolTipText("Barra de progreso");
        etiqueta.setVisible(false);
        etiqueta.setBounds(100,100,126,22);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnenviar = new javax.swing.JButton();
        btncancelar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnenviar.setText("Envio");
        btnenviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnenviarActionPerformed(evt);
            }
        });

        btncancelar.setText("cancelar");
        btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelarActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Envio de base de datos a un servidor externo");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/biblioteca/bar.gif"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnenviar)
                            .addGap(74, 74, 74)
                            .addComponent(btncancelar))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnenviar)
                    .addComponent(btncancelar))
                .addGap(47, 47, 47))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnenviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnenviarActionPerformed
etiqueta.setVisible(true);
 Date d = new Date();
 String fe= String.valueOf(d);
try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", host);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", puerto);
            props.setProperty("mail.smtp.user", direccion);
            props.setProperty("mail.smtp.auth", autentificar);

            Session session = Session.getDefaultInstance(props, null);
            // session.setDebug(true);

            // Se compone la parte del texto
            BodyPart texto = new MimeBodyPart();
            texto.setText(fe+"\nBase de datos");

            // Se compone el adjunto con la imageno el archivo q nencesitas
            BodyPart adjunto = new MimeBodyPart();
            adjunto.setDataHandler(
                new DataHandler(new FileDataSource("C:/Respaldo.sql")));
            adjunto.setFileName("Respaldo.sql");

            
            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            multiParte.addBodyPart(adjunto);
   
          
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("animexq@hotmail.com"));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(direccion));
            message.setSubject("Base de Datos");
            message.setContent(multiParte);

            // Se envia el correo.
            Transport t = session.getTransport("smtp");
            t.connect(direccion, contraseña);
        
            t.sendMessage(message, message.getAllRecipients());
           etiqueta.setVisible(false);
            t.close();
        }
        catch (Exception e)
        {etiqueta.setVisible(false);
            e.printStackTrace();
        }
}//GEN-LAST:event_btnenviarActionPerformed

    private void btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelarActionPerformed
       setVisible(false);
}//GEN-LAST:event_btncancelarActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncancelar;
    private javax.swing.JButton btnenviar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

}
