/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * NewJFrame.java
 *
 * Created on 27/03/2011, 07:49:49 PM
 */
package clave;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.security.NoSuchAlgorithmException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author jonathan
 */
public class Form_Principal extends javax.swing.JFrame {

    String ejemplo_BSSID = "34:94:1D:E4:D6:72";
    String ejemplo_ESSID = "WLAN_C34E";

    /** Creates new form NewJFrame */
    public Form_Principal() {
        initComponents();
        txtBSSID.setText(ejemplo_BSSID);
        txtESSID.setText(ejemplo_ESSID);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtESSID = new javax.swing.JTextField();
        txtBSSID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtResultado = new javax.swing.JTextField();
        txtLabelClave = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ESSID (Nombre de tu red)");

        jLabel2.setText("BSSID (Direccion Mac de tu router)");

        txtESSID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtESSIDMouseClicked(evt);
            }
        });
        txtESSID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtESSIDFocusLost(evt);
            }
        });

        txtBSSID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtBSSIDMouseClicked(evt);
            }
        });
        txtBSSID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtBSSIDFocusLost(evt);
            }
        });

        jButton1.setText("Genera la clave");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Archivo");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Salir");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem1.setText("Leeme");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtESSID, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                                    .addComponent(txtBSSID, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1)
                                .addGap(78, 78, 78))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtLabelClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtResultado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtESSID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtBSSID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                .addComponent(txtResultado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtLabelClave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LlamarClave(txtBSSID.getText(), txtESSID.getText(), txtResultado, txtLabelClave);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtESSIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtESSIDMouseClicked
        if (txtESSID.getText().equalsIgnoreCase(ejemplo_ESSID)) {
            clean(txtESSID);
        }
    }//GEN-LAST:event_txtESSIDMouseClicked

    private void txtBSSIDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtBSSIDMouseClicked
        if (txtBSSID.getText().equalsIgnoreCase(ejemplo_BSSID)) {
            clean(txtBSSID);
        }
    }//GEN-LAST:event_txtBSSIDMouseClicked

    private void txtESSIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtESSIDFocusLost
        if (txtESSID.getText().trim().length() == 0) {
            txtESSID.setText(ejemplo_ESSID);
        }
    }//GEN-LAST:event_txtESSIDFocusLost

    private void txtBSSIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtBSSIDFocusLost
        if (txtBSSID.getText().trim().length() == 0) {
            txtBSSID.setText(ejemplo_BSSID);
        }
    }//GEN-LAST:event_txtBSSIDFocusLost

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "Recupera la contraseña de tu wifi si es del tipo:"+"n"+"JAZZTEL_XXXX o WLAN_XXXX");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Form_Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JTextField txtBSSID;
    private javax.swing.JTextField txtESSID;
    private javax.swing.JLabel txtLabelClave;
    private javax.swing.JTextField txtResultado;
    // End of variables declaration//GEN-END:variables

    private void LlamarClave(String BSSID, String ESSID, JTextField txtResultado, JLabel txtLabelClave) {
        boolean error_essid = false;
        boolean error_bssid = false;
        String error = "";
        if (ESSID.contains("JAZZTEL")) {
            if (ESSID.length() != 12) {
                error_essid = true;
            }
        } else if (ESSID.contains("WLAN")) {
            if (ESSID.length() != 9) {
                error_essid = true;
            }
        } else {
            error_essid = true;
        }
        String cabecera = ESSID.trim().toUpperCase();
        cabecera = cabecera.substring(cabecera.length() - 4, cabecera.length());
        String bssid = BSSID.trim().replace(":", "").replace("-", "").toUpperCase();

        //Resolver aca
        for (char c : bssid.toCharArray()) {
            if ((!Character.isDigit(c)) && (c != 'A') && (c != 'B') && (c != 'C') && (c != 'D') && (c != 'E') && (c != 'F')) {
                error_bssid = true;
            }
        }
        //Fin de resolver aca

        if (bssid.length() != 12) {
            error_bssid = true;
        }
        if (error_essid) {
            error = "¿Metiste bien el nombre de la red?";
        }
        if ((!error_essid) && (error_bssid)) {
            error = "¿Metiste bien la MAC?";
        }
        if ((!error_bssid) && (!error_essid)) {
            String bssidp = bssid.substring(0, 8);
            try {
                String md5sum = JavaMD5Sum.computeSum("bcgbghgg" + bssidp + cabecera + bssid);
                txtResultado.setText(md5sum.substring(0, 20));
                StringSelection ss = new StringSelection(txtResultado.getText());
                Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
                txtLabelClave.setText("Clave copiada al portapapeles");
            } catch (NoSuchAlgorithmException ex) {
                txtResultado.setText("Opss!!! No pudimos calcular la clave. Intentalo de nuevo");
            }
        } else {
            txtResultado.setText(error);
        }
        System.out.println(txtResultado.getText());
    }

    private void clean(JTextField txtESSID) {
        txtESSID.setText("");
    }
}