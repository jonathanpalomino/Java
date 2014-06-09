
package biblioteca;

import java.io.*;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Notas extends JFrame {

    /** Creates new form Notas */
    public Notas() {
        initComponents();
        setLocation(200, 200);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtArea = new javax.swing.JTextArea();
        txtAbrir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notas o Registros");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        txtArea.setColumns(20);
        txtArea.setRows(5);
        txtArea.setText("\t\t\t====================\n\t\t\tTELÉFONOS DE INTERÉS\n\t\t\t====================");
        jScrollPane1.setViewportView(txtArea);

        txtAbrir.setText("Abrir");
        txtAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAbrirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(txtAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnCancelar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnCancelar)
                    .addComponent(txtAbrir))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        Guardar_En_Archivo(txtArea);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAbrirActionPerformed
        Abrir_Archivo(txtArea);
    }//GEN-LAST:event_txtAbrirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton txtAbrir;
    private javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
	public void Guardar_En_Archivo( JTextArea area )
    {
    	JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int result = fch.showSaveDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
    	try {
    		FileOutputStream fos = new FileOutputStream( fch.getSelectedFile() );
    		BufferedOutputStream bos = new BufferedOutputStream( fos );
            bos.write( area.getText().getBytes() );
            bos.flush();
        	fos.close();
        }

		catch( Exception e )
		{
			JOptionPane.showMessageDialog(this, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

        	public void Abrir_Archivo( JTextArea area)
	{
		JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode( JFileChooser.FILES_ONLY );
        String txtmsg="";

        int result = fch.showOpenDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
        try{
        	FileInputStream fis = new FileInputStream( fch.getSelectedFile() );
            BufferedInputStream bis = new BufferedInputStream( fis );

            int c = bis.read();
            while ( c != -1 )
            {
            	txtmsg = txtmsg + (char) c;
                c = bis.read();
            }

            area.setText( txtmsg );
            bis.close();
        }

        catch( Exception e )
        {
        	JOptionPane.showMessageDialog( this, "Error Cargando Archivo!", "Error",
        	                              JOptionPane.ERROR_MESSAGE );
		}
	}
}
