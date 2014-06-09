//**************************************************************************
// Nombre......: JVentanaError.java
// Proyecto....: Open SimMPLS
// Descripci�n.: Implementaci�n de una ventana de di�logo que muestra un
// ............: texo y un icono de error.
// Fecha.......: 26/01/2004
// Autor/es....: Manuel Dom�nguez Dorado
// ............: ingeniero@ManoloDominguez.com
// ............: http://www.ManoloDominguez.com
//**************************************************************************

package simMPLS.interfaz.dialogos;

import simMPLS.interfaz.utiles.*;

/** Esta clase implementa una ventana que muestra un mensaje de error al
 * usuario y que muestra adem�s un icono de error.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */
public class JVentanaError extends javax.swing.JDialog {
    
    /**
     * Crea una nueva ventana de error.
     * @param parent Ventana padre donde se ubicar� esta ventana de tipo JVentanaError.
     * @param modal TRUE indica que la ventana impedir� que se pueda seleccionar nada de la interfaz
     * de usuario hasta que se cierre. FALSE indica que esto no es as�.
     * @param di Dispensador de im�genes global de la aplicaci�n.
     * @since 1.0
     */
    public JVentanaError(java.awt.Frame parent, boolean modal, TDispensadorDeImagenes di) {
        super(parent, modal);
        dispensadorDeImagenes = di;
        initComponents();
        java.awt.Dimension tamFrame=this.getSize();
        java.awt.Dimension tamPantalla=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((tamPantalla.width-tamFrame.width)/2, (tamPantalla.height-tamFrame.height)/2);
    }
    
    /** Este m�todo se llama desde el constructor para dar los valores iniciales v�lidos
     * a los atributos de la clase.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jTextPane1 = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setTitle(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaError.titulo"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jLabel1.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ERROR));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 15, -1, -1));

        jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
        jButton1.setIcon(dispensadorDeImagenes.obtenerIcono(TDispensadorDeImagenes.ACEPTAR));
        jButton1.setMnemonic(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("VentanaError.ResaltadoBoton").charAt(0));
        jButton1.setText(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes").getString("OK"));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clicEnBoton(evt);
            }
        });

        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 80, 105, -1));

        jTextPane1.setBackground((java.awt.Color) javax.swing.UIManager.getDefaults().get("Button.background"));
        jTextPane1.setEditable(false);
        jTextPane1.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jTextPane1.setFocusCycleRoot(false);
        jTextPane1.setFocusable(false);
        jTextPane1.setEnabled(false);
        getContentPane().add(jTextPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(55, 15, 285, 50));

        jPanel1.setLayout(null);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 120));

        pack();
    }//GEN-END:initComponents
    
    /** Este m�todo captura un evento de rat�n sobre el boton de la ventana. Al
     * recibirlo, cierra la ventana y la elimina.
     */    
    private void clicEnBoton(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clicEnBoton
        setVisible(false);
        dispose();
    }//GEN-LAST:event_clicEnBoton
    
    /** Este m�todo captura un evento de rat�n sobre el boton [X] de la ventana. Al
     * recibirlo, cierra la ventana y la elimina.
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog
    
    /** Este m�todo recibe por par�metro un texto y hace que aparezca en la ventana.
     * @param texto El textoque se desea que aparezca en la ventana.
     * @since 1.0
     */    
    public void mostrarMensaje(java.lang.String texto) {
        java.awt.Toolkit.getDefaultToolkit().beep();
        jTextPane1.setText(texto);
    }
    
    private TDispensadorDeImagenes dispensadorDeImagenes;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
    
}
