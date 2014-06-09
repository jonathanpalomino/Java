/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package problemasunfv;

public class mcmymcdgrafico extends javax.swing.JFrame {

public mcmymcdgrafico() {
        initComponents();
    }

    private int mcd(int a, int b, int t, int x) {
        while (x <= a && x <= b) {
            while (a % x == 0 && b % x == 0) {
                a = a / x;
                b = b / x;
                t = t * x;
            }
            x++;
        }

        t = t * a * b;
        return (t);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txta = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtb = new javax.swing.JTextField();
        mcd = new javax.swing.JButton();
        mcm = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRespuesta = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("mcm y mcd");

        jLabel1.setText("1° número");

        txta.setText("0");

        jLabel2.setText("2° número");

        txtb.setText("0");

        mcd.setText("MCD");
        mcd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcdActionPerformed(evt);
            }
        });

        mcm.setText("MCM");
        mcm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mcmActionPerformed(evt);
            }
        });

        txtRespuesta.setColumns(20);
        txtRespuesta.setRows(5);
        jScrollPane1.setViewportView(txtRespuesta);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtb)
                            .addComponent(txta, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(mcd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(mcm, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mcd, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(mcm, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mcdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcdActionPerformed

        int a, b, t = 1, x = 2;
        try {
            a = Integer.parseInt(txta.getText());
        } catch (java.lang.NumberFormatException e) {
            txtRespuesta.setText("Ingrese un número entero");
            // Dar el foco al control
            txta.grabFocus();
            return; // Retorna si ejecutar las líneas de abajo
        }
        try {
            b = Integer.parseInt(txtb.getText());
        } catch (java.lang.NumberFormatException e) {
            txtRespuesta.setText("Ingrese un número entero");
            // Dar el foco al control
            txtb.grabFocus();
            return; // Retorna si ejecutar las líneas de abajo
        }
        //Validacion de datos
        if (a <= 0) {
            txtRespuesta.setText("debe ser mayor que 0");
            txta.grabFocus();
            return;
        }
        if (b <= 0) {
            txtRespuesta.setText("debe ser mayor que 0");
            txtb.grabFocus();
            return;
        }
        if (a == b) {
            txtRespuesta.setText("Ninguno debe ser igual");
            return;
        }
        int dias = mcd(a, b, t, x);
        // Mostrando la respuesta
        txtRespuesta.setText("El mcd es " + dias);
}//GEN-LAST:event_mcdActionPerformed

    private void mcmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mcmActionPerformed
        int a, b, t = 1, x = 2;
        try {
            a = Integer.parseInt(txta.getText());
        } catch (java.lang.NumberFormatException e) {
            txtRespuesta.setText("Ingrese un número entero");
            // Dar el foco al control
            txta.grabFocus();
            return; // Retorna si ejecutar las líneas de abajo
        }
        try {
            b = Integer.parseInt(txtb.getText());
        } catch (java.lang.NumberFormatException e) {
            txtRespuesta.setText("Ingrese un número entero");
            // Dar el foco al control
            txtb.grabFocus();
            return; // Retorna si ejecutar las líneas de abajo
        }
        //Validacion de datos
        if (a <= 0) {
            txtRespuesta.setText("debe ser mayor que 0");
            txta.grabFocus();
            return;
        }
        if (b <= 0) {
            txtRespuesta.setText("debe ser mayor que 0");
            txtb.grabFocus();
            return;
        }
        if (a == b) {
            txtRespuesta.setText("Ninguno debe ser igual");
            return;
        }
        int mcm1 = mcd(a, b, t, x);
        // Mostrando la respuesta
        txtRespuesta.setText("El mcm es " + mcm1 / a * b);
    }//GEN-LAST:event_mcmActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new mcmymcdgrafico().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton mcd;
    private javax.swing.JButton mcm;
    private javax.swing.JTextArea txtRespuesta;
    private javax.swing.JTextField txta;
    private javax.swing.JTextField txtb;
    // End of variables declaration//GEN-END:variables
}
