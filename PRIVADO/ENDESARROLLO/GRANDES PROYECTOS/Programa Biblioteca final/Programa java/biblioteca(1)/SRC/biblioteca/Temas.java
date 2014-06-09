

package biblioteca;

/**
 *
 * @author JONATHAN
 */
public class Temas extends javax.swing.JFrame {

    /** Creates new form Temas */
    public Temas(String Texto) {
        initComponents();
        setLocation(300, 250);

        if(Texto.equals("Visualizar"))
        {
            lbl1.setVisible(false);
            setTitle("Visualizar Existentes");
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Grupo1 = new javax.swing.ButtonGroup();
        Grupo2 = new javax.swing.ButtonGroup();
        lbl1 = new javax.swing.JLabel();
        rbTemas = new javax.swing.JRadioButton();
        rbSoportes = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JlistTema = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        JlistSoporte = new javax.swing.JList();
        btnSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lbl1.setText("Primero Selecciona una opcion Temas o Soportes.");

        Grupo1.add(rbTemas);
        rbTemas.setText("Temas");
        rbTemas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTemasActionPerformed(evt);
            }
        });

        Grupo1.add(rbSoportes);
        rbSoportes.setText("Soportes");
        rbSoportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSoportesActionPerformed(evt);
            }
        });

        JlistTema.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Enciclopedia", "Estadistica", "Fisica", "Industrias Quimicas", "Matematicas", "Ingenieria", "Otros" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(JlistTema);

        JlistSoporte.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Carpeta", "DVD", "Tesis", "Libro", "Revista" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(JlistSoporte);

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnSalir, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69))
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(rbSoportes))
                    .addComponent(rbTemas))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lbl1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbTemas)
                    .addComponent(rbSoportes))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnSalir)
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void rbTemasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTemasActionPerformed

        ListarTemas();
        JlistSoporte.setEnabled(false);
        JlistTema.setEnabled(true);
    }//GEN-LAST:event_rbTemasActionPerformed

    private void rbSoportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSoportesActionPerformed

        ListarSoportes();
        JlistTema.setEnabled(false);
        JlistSoporte.setEnabled(true);
    }//GEN-LAST:event_rbSoportesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Grupo1;
    private javax.swing.ButtonGroup Grupo2;
    private javax.swing.JList JlistSoporte;
    private javax.swing.JList JlistTema;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl1;
    private javax.swing.JRadioButton rbSoportes;
    private javax.swing.JRadioButton rbTemas;
    // End of variables declaration//GEN-END:variables

    private void ListarTemas() {
        
    }

    private void ListarSoportes() {

    }

}
