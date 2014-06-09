package Simplex_Primal;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JONATHAN
 */
public class SimplexFrame extends javax.swing.JFrame{

    public DefaultTableModel miModelo;
    public String[][] data = {};
    String arreglo[][];
    String arreglo_temporal[][];
    String arreglo_transpuesto[][];
    float Vector_solucion[], Vector_2_Auxiliar[];
    float indice[], indice1[], pivote, coef_pivotal, temp = 0, valor;
    int variable = 0, restricciones = 0, indiceY = 0, indiceX;
    int subY, subY1, subX, subX1, holguras1 = 0, artificiales1 = 0;
    String tipo = "", M = "20000";
    String[][] captura;
    public boolean multiple = false, Artificial = false, Solucion_infinita = false, usoDual = false;

    JTable getMiTabla() {
        return Tabla;
    }

    public DefaultTableModel getMiModelo() {
        return miModelo;
    }

    public SimplexFrame() {
        setLocation(300, 170);
        initComponents();
        miModelo = new DefaultTableModel(data, new String[]{""}){
        };
        Tabla.setModel(miModelo);
        jScrollPane2.setViewportView(Tabla);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lblVariable = new javax.swing.JLabel();
        txtVariable = new javax.swing.JTextField();
        lblRestricc = new javax.swing.JLabel();
        txtRestricc = new javax.swing.JTextField();
        btnCrear = new javax.swing.JButton();
        lblFuncionObj = new javax.swing.JLabel();
        Rmax = new javax.swing.JRadioButton();
        Rmin = new javax.swing.JRadioButton();
        btnIngresar = new javax.swing.JButton();
        Panel1 = new javax.swing.JPanel();
        btnCalcularPrimal = new javax.swing.JButton();
        lblRestriccion = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnCalcularDual = new javax.swing.JButton();
        lblEtiq = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menunuevo = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Simplex Primal");

        lblVariable.setText("Numero de Variantes");

        lblRestricc.setText("Numero de Restricciones");

        btnCrear.setText("Crear");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        lblFuncionObj.setText("Funcion Objetivo");

        buttonGroup1.add(Rmax);
        Rmax.setText("Maximizar");
        Rmax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RmaxActionPerformed(evt);
            }
        });

        buttonGroup1.add(Rmin);
        Rmin.setText("Minimizar");
        Rmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RminActionPerformed(evt);
            }
        });

        btnIngresar.setText("Ingresar");
        btnIngresar.setEnabled(false);
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        btnCalcularPrimal.setText("Calcular Primal");
        btnCalcularPrimal.setEnabled(false);
        btnCalcularPrimal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularPrimalActionPerformed(evt);
            }
        });

        lblRestriccion.setText("Restricciones");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(Tabla);

        btnCalcularDual.setText("Calcular Dual");
        btnCalcularDual.setEnabled(false);
        btnCalcularDual.setPreferredSize(new java.awt.Dimension(101, 23));
        btnCalcularDual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalcularDualActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel1Layout = new javax.swing.GroupLayout(Panel1);
        Panel1.setLayout(Panel1Layout);
        Panel1Layout.setHorizontalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRestriccion, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(393, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel1Layout.createSequentialGroup()
                .addContainerGap(233, Short.MAX_VALUE)
                .addComponent(btnCalcularPrimal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCalcularDual, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        Panel1Layout.setVerticalGroup(
            Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRestriccion, javax.swing.GroupLayout.DEFAULT_SIZE, 19, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(Panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCalcularPrimal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCalcularDual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        lblEtiq.setText(" ");

        jMenu1.setText("Archivo");

        menunuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        menunuevo.setText("Nuevo");
        menunuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menunuevoActionPerformed(evt);
            }
        });
        jMenu1.add(menunuevo);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        jMenu1.add(menuSalir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(Rmax)
                        .addGap(34, 34, 34)
                        .addComponent(Rmin)
                        .addGap(69, 69, 69)
                        .addComponent(btnIngresar)
                        .addGap(147, 147, 147))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEtiq, javax.swing.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                        .addGap(277, 277, 277))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblVariable)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVariable, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblRestricc)
                        .addGap(12, 12, 12)
                        .addComponent(txtRestricc, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCrear, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFuncionObj))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtRestricc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtVariable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblVariable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblRestricc))))
                    .addComponent(btnCrear))
                .addGap(14, 14, 14)
                .addComponent(lblFuncionObj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Rmax)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Rmin)
                        .addComponent(btnIngresar)))
                .addGap(18, 18, 18)
                .addComponent(lblEtiq, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RmaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RmaxActionPerformed
        tipo = "Max";
        btnIngresar.setEnabled(true);
    }//GEN-LAST:event_RmaxActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        try {
            variable = Integer.parseInt(txtVariable.getText());
            restricciones = Integer.parseInt(txtRestricc.getText());
            if (restricciones == 1 && variable == 1) {
                JOptionPane.showMessageDialog(null, "Debe ingresar almenos 2 restricciones", "Error Casual", JOptionPane.INFORMATION_MESSAGE);
                Nuevo();
            }
            else {
                GenerarTitulos(variable);
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error digite números");
            txtVariable.setText("");
            txtRestricc.setText("");
        }


    }//GEN-LAST:event_btnCrearActionPerformed

    private void menunuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menunuevoActionPerformed
        Nuevo();
    }//GEN-LAST:event_menunuevoActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
        String valor1 = null;
        String texto = null;
        try {
            if (ValidarCampos()) {
                JOptionPane.showMessageDialog(null, "Debe existir <= o >= ", "Error de usuario", JOptionPane.ERROR_MESSAGE);
            }
            else {
                try {
                    Verificar_y_Dimensionar_Primal();
                    texto = tipo;
                    valor1 = "";
                    for (int y = 0; y < (variable + holguras1 + artificiales1); y++) {
                        Vector_solucion[y] = 0;
                        Vector_2_Auxiliar[y] = 0;
                    }
                    try {
                        for (int i = 1; i <= variable; i++) {
                            valor1 = JOptionPane.showInputDialog("Ingrese X" + i);
                            Vector_solucion[i - 1] = Float.parseFloat(valor1);//Vector solucion de la funcion objetivo
                            Vector_2_Auxiliar[i - 1] = Vector_solucion[i - 1];
                            texto = texto + (" + " + valor1 + "X" + i);
                        }
                        setMultiple(false);
                        lblEtiq.setText(texto);
                        btnCalcularPrimal.setEnabled(true);
                    }
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error digite bien!");

                    }
                    if (Artificial)//Si es dual agregar los Ms
                    {
                        for (int y = (holguras1 + variable); y < (variable + holguras1 + artificiales1); y++) {
                            Vector_solucion[y] = Float.parseFloat(M);
                            Vector_2_Auxiliar[y] = Vector_solucion[y];

                        }
                    }
                    for (int y = 0; y < (variable + holguras1 + artificiales1); y++) {
                        System.out.println(" vector " + Vector_2_Auxiliar[y]);
                    }
                }
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Tipee en tabla", "Error de ingreso", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Debe escribir en tabla antes de iniciar la funcion objetivo", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }



    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnCalcularPrimalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularPrimalActionPerformed
        setMultiple(false);
        LlenarDatosTabla();
        try {

            if (Solucion_infinita) {
                JOptionPane.showMessageDialog(null, "Soluciones Infinitas");
            }
            Resultado_Primal obj = new Resultado_Primal(arreglo, variable, restricciones, tipo, holguras1, artificiales1, captura);
            obj.show();
            if (isMultiple()) {
                JOptionPane.showMessageDialog(null, "Es degenarada.. Se mostrara el 1° Tablero");
            }
            btnCalcularPrimal.setEnabled(false);
            btnCalcularDual.setEnabled(true);
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Llene los datos de la tabla", "Error de Tabla", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnCalcularPrimalActionPerformed

    private void RminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RminActionPerformed
        tipo = "Min";
        btnIngresar.setEnabled(true);
    }//GEN-LAST:event_RminActionPerformed

    private void btnCalcularDualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularDualActionPerformed
        if (Rmin.isSelected()) {
            JOptionPane.showMessageDialog(null, "No programado para minimizacion", "Casualidad", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            usoDual = true;
            Verificar_y_Dimensionar_Dual();
            Asignar_Dual();
            Trabajar_Simplex(arreglo_transpuesto, restricciones, variable);
            if (Solucion_infinita) {
                JOptionPane.showMessageDialog(null, "Soluciones Infinitas");
            }
            //Llamar frame
            Resultado_Dual obj = new Resultado_Dual(arreglo, (3 + holguras1 + artificiales1 + restricciones), (variable), tipo, restricciones, artificiales1, holguras1);
            obj.show();
            btnCalcularDual.setEnabled(false);
            btnIngresar.setEnabled(false);
            btnCrear.setEnabled(false);
        }

    }//GEN-LAST:event_btnCalcularDualActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable(){

            public void run() {
                new SimplexFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel1;
    private javax.swing.JRadioButton Rmax;
    private javax.swing.JRadioButton Rmin;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnCalcularDual;
    private javax.swing.JButton btnCalcularPrimal;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnIngresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblEtiq;
    private javax.swing.JLabel lblFuncionObj;
    private javax.swing.JLabel lblRestricc;
    private javax.swing.JLabel lblRestriccion;
    private javax.swing.JLabel lblVariable;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JMenuItem menunuevo;
    private javax.swing.JTextField txtRestricc;
    private javax.swing.JTextField txtVariable;
    // End of variables declaration//GEN-END:variables

    public void GenerarTitulos(int variables) {
        Object[] titulos = new Object[variables + 2];
        for (int i = 0; i < variables; i++) {
            titulos[i] = "X" + String.valueOf(i + 1);
        }
        titulos[variables] = "Signo";
        titulos[variables + 1] = "Valor";
        getMiModelo().setColumnIdentifiers(titulos);
        getMiModelo().setColumnCount(variables + 2);
        getMiModelo().setRowCount(restricciones);
    }

    public void LlenarDatosTabla() {
        if (ValidarCampos())//Retorna true si hay error de tipeo que alerte
        {
            JOptionPane.showMessageDialog(null, "Debe existir <= o >= ", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }
        else {
            Asignar_Primal();
            Trabajar_Simplex(arreglo, variable, restricciones);
            Tablero_Iterativo();
        }


    }

    public boolean BuscarXs(int sbI, String[][] arreglo) {
        boolean resultado = false;
        int nume = sbI + 1;
        for (int k = 0; k < restricciones; k++) {
            if (arreglo[k][1] == "X" + nume) {
                resultado = true;
            }
        }
        return resultado;
    }

    public void Tablero_Iterativo() {
        System.out.println("Llenar nueva tabla");
    }

    public boolean ValidarCampos() {
        boolean valido = false;
        for (int i = 0; i < restricciones; i++) {
            if (getMiTabla().getValueAt(i, variable).equals(">=") || getMiTabla().getValueAt(i, variable).equals("<=") || getMiTabla().getValueAt(i, variable).equals("=")) {
                valido = false;
            }
            else {
                valido = true;
            }
        }
        return valido;
    }

    public void Nuevo() {
        txtVariable.setText("");
        txtRestricc.setText("");
        lblEtiq.setText("");
        miModelo = new DefaultTableModel(data, new String[]{""}){
        };
        Tabla.setModel(miModelo);
        btnCalcularPrimal.setEnabled(false);
        btnIngresar.setEnabled(false);
        btnCrear.setEnabled(true);
    }

    public void Llenarzj_cj(String[][] arreglo1, int columna, int filas) {
        for (int a = 3; a < (3 + columna + holguras1 + artificiales1); a++) {
            temp = 0;
            for (int k = 0; k < filas; k++) {
                temp = temp + Float.parseFloat(arreglo1[k][0]) * Float.parseFloat(arreglo1[k][a]);
            }
            if (Rmax.isSelected() && usoDual) {
                temp = Vector_solucion[a - 3] - temp;
            }
            else if (Rmin.isSelected() && usoDual) {
                temp = temp - Vector_solucion[a - 3];
            }
            else if (Rmax.isSelected()) {
                temp = temp - Vector_solucion[a - 3];//Maximizacion
            }
            else if (Rmin.isSelected()) {
                temp = Vector_solucion[a - 3] - temp;//Minimizacion
            }
            System.out.println(" Zj-Cj " + temp);
            indice[a - 3] = temp;
            //arreglo1[restricciones][a]=String.valueOf(temp);
            Float au = (float) 0.0;
            au = temp;
            arreglo1[filas][a] = au.toString();
        }

    }

    public int Obtener_Indice(float[] indice, int p) {
        if (p == 0) {
            //p= 0 no procesar negativos ni iguales a 0
            for (int i = 0; i < indice.length; i++) {
                if (indice[i] <= 0) {
                    indice[i] = 1999999999;//Vuelve los negativos a un mayor positivo
                }
            }
        }
        int b = 0;
        for (int i = 0; i < indice.length; i++) {
            if (indice[i] < indice[b]) {
                b = i;
            }
        }
        return b;
    }

    public int Obtener_Indice1(float[] indice, int p) {
        if (p == 0) {
            //p= 0 no procesar negativos ni iguales a 0
            for (int i = 0; i < indice.length; i++) {
                if (indice[i] <= 0) {
                    indice[i] = 1999999999;//Vuelve los negativos a un mayor positivo
                }
            }
        }
        int b = 0, b2 = 0;
        for (int i = 0; i < indice.length; i++) {
            if (indice[i] < indice[b]) {
                b = i;
                b2 = b;
            }
            if (indice[i] == indice[b2]) {
                b2 = i;
            }
        }
        return b2;
    }

    public int Procesar_IndiceY(float[] indice, String[][] arreglo) {
        int valors = 0;
        subY = Obtener_Indice(indice, -1);
        subY1 = Obtener_Indice1(indice, -1);

        if (subY1 == subY) {
            valors = subY + 3;
        }
        else if (BuscarXs(subY1, arreglo) == false) {
            valors = subY + 3;
        }
        else if (BuscarXs(subY, arreglo) == false) {
            valors = subY1 + 3;
        }
        return valors;
    }

    public int Procesar_IndiceX(float[] indice1, int i) {
        int valors = 0;
        subX = Obtener_Indice(indice1, i);
        subX1 = Obtener_Indice1(indice1, i);
        if (subX1 == subX) {
            valors = subX;
        }
        else {
            valors = subX;
            setMultiple(true);
        }
        return valors;
    }

    public boolean isMultiple() {
        return multiple;
    }

    public void setMultiple(boolean multiple) {
        this.multiple = multiple;
    }

    public void Verificar_y_Dimensionar_Dual() {
        int holguras = 0;
        int artificiales = 0;
        for (int i = 0; i < variable; i++)//Validamos restricciones
        {
            holguras++;
            artificiales++;
            Artificial = true;
        }
        int nuevoF1 = 3 + holguras + artificiales + restricciones;
        int nuevoF2 = variable + 1;
        indice = new float[(holguras + artificiales + restricciones)];
        indice1 = new float[variable];
        arreglo_transpuesto = new String[nuevoF2][nuevoF1];
        arreglo_temporal = new String[nuevoF2][nuevoF1];
        Vector_solucion = new float[nuevoF1 - 3];
        Llenar0s((holguras + artificiales), restricciones, variable, arreglo_transpuesto);
        holguras1 = holguras;
        artificiales1 = artificiales;
    }

    public void Verificar_y_Dimensionar_Primal() {
        int holguras = 0;
        int artificiales = 0;
        for (int i = 0; i < restricciones; i++)//Validamos restricciones
        {
            if (getMiTabla().getValueAt(i, variable).equals(">=")) {
                holguras++;
                artificiales++;
                Artificial = true;
            }
            else if (getMiTabla().getValueAt(i, variable).equals("<=")) {
                holguras++;
            }
            else if (getMiTabla().getValueAt(i, variable).equals("=")) {
                artificiales++;
                Artificial = true;
            }
        }
        int nuevoF1 = 3 + variable + holguras + artificiales;
        int nuevoF2 = restricciones + 1;
        captura = new String[nuevoF2 - 1][2];
        arreglo = new String[nuevoF2][nuevoF1];
        arreglo_temporal = new String[nuevoF2][nuevoF1];
        Vector_solucion = new float[nuevoF1 - 3];
        Vector_2_Auxiliar = new float[nuevoF1 - 3];
        indice = new float[(variable + holguras + artificiales)];
        indice1 = new float[restricciones];
        Llenar0s((holguras + artificiales), variable, restricciones, arreglo);
        holguras1 = holguras;
        artificiales1 = artificiales;
    }

    public void Datos_menos_Holgura_mas_ArtificialT(int i, int columna, int contador, int contador2) {
        for (int j = 3; j < (columna + 3); j++) {
            arreglo_transpuesto[i][j] = String.valueOf(Integer.parseInt(getMiTabla().getValueAt((j - 3), i).toString()));
        }

        arreglo_transpuesto[i][2] = String.valueOf(Vector_2_Auxiliar[i]);//Vector solucion pasa a ser nuevo Bi
        arreglo_transpuesto[i][1] = "L" + (i);//Variable landa
        arreglo_transpuesto[i][0] = M; //landa mayor posible osea un numero grande
        arreglo_transpuesto[i][contador] = "-1";
        arreglo_transpuesto[i][contador2] = "1";

    }

    public void Datos_menos_Holgura_mas_Artificial(int i, int variable, int contador, int contador2) {
        for (int j = 3; j < (variable + 3); j++) {
            arreglo[i][j] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, (j - 3)))));
        }
        arreglo[i][contador] = "-1";
        arreglo[i][contador2] = "1";//Asignar artificial
        arreglo[i][2] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, variable + 1))));//Valor resultado;
        arreglo[i][1] = "L" + (i);
        arreglo[i][0] = M;
    }

    public void Datos_mas_Artificial(int i, int variable, int contador2) {
        for (int j = 3; j < (variable + 3); j++) {
            arreglo[i][j] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, (j - 3)))));
        }
        arreglo[i][contador2] = "1";//Asignar artificial
        arreglo[i][2] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, variable + 1))));//Valor resultado;
        arreglo[i][1] = "L" + (i);
        arreglo[i][0] = M;
    }

    public void Datos_mas_Holgura(int i, int variable, int contador) {
        for (int j = 3; j < (variable + 3); j++) {
            arreglo[i][j] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, (j - 3)))));
        }
        arreglo[i][2] = String.valueOf(Integer.parseInt(String.valueOf(getMiTabla().getValueAt(i, variable + 1))));//Valor resultado
        arreglo[i][1] = "X" + (i + variable + 1);
        arreglo[i][0] = "0";
        arreglo[i][contador] = "1";
    }

    public void Llenar0s(int valores, int columna, int filas, String[][] arreglo) {
        for (int i = 0; i < filas; i++) {
            for (int h = (columna + 3); h < (columna + valores + 3); h++) {
                arreglo[i][h] = "0";//Rellenando con 0's
            }
        }
    }

    public void Trabajar_Simplex(String[][] arreglo_interno, int columna, int filas) {
        Llenarzj_cj(arreglo_interno, columna, filas);
        indiceY = Procesar_IndiceY(indice, arreglo_interno);
        System.out.println("Indice Y " + indiceY);
        valor = Float.parseFloat(arreglo_interno[filas][indiceY]);
        System.out.println(" menor valor " + valor);
        while (valor < 0) {
            ///////////////// Inicia Obtener pivote,indiceX,y coef pivotal
            for (int z = 0; z < filas; z++) {
                Float abc = (float) 0.0;//Auxiliar de control
                indice1[z] = Float.parseFloat(arreglo_interno[z][2]) / Float.parseFloat(arreglo_interno[z][indiceY]);
                abc = indice1[z];
                if (abc.isInfinite())//Controlo y evito que salga infinito
                {
                    indice1[z] = (float) 10000000000.0;//Le asigno un número mayor posible
                }
            }
            indiceX = Procesar_IndiceX(indice1, 0);//Exigir valores minimos positivos el 0 elimina los negativos

            System.out.println(" indice x " + indiceX);
            pivote = Float.parseFloat(arreglo_interno[indiceX][indiceY]);
            System.out.println(" Pivote " + pivote);
            coef_pivotal = Float.parseFloat(arreglo_interno[indiceX][2]) / pivote;
            System.out.println(" Coef pivotal " + coef_pivotal);
            if (coef_pivotal <= 0) {
                Solucion_infinita = true;
                break;//Si existe un coef pivotal negativo o igual a 0 que pare
            }
            ////////////////// Finaliza Obtener pivote,indiceX,y coef pivotal

            //////////////////Inicia Rellenar nueva fila pivote
            arreglo_temporal[indiceX][0] = String.valueOf(Vector_solucion[indiceY - 3]);
            System.out.println(" in 0 " + arreglo_temporal[indiceX][0]);
            if (usoDual) {
                arreglo_temporal[indiceX][1] = "Y" + (indiceY - 2);//Si uso el dual pasar variables en forma de Y
            }
            else {
                arreglo_temporal[indiceX][1] = "X" + (indiceY - 2);//Si uso X´s usar extension de forma X
            }
            System.out.println(" in 1 " + arreglo_temporal[indiceX][1]);
            arreglo_temporal[indiceX][2] = String.valueOf(coef_pivotal);
            System.out.println(" in 2 " + arreglo_temporal[indiceX][2]);
            for (int h = 3; h < (3 + columna + holguras1 + artificiales1); h++) {
                arreglo_temporal[indiceX][h] = String.valueOf(Float.parseFloat(arreglo_interno[indiceX][h]) / pivote);
                System.out.println("nueva fila pivote " + arreglo_temporal[indiceX][h]);//La nueva fila pivote
            }
            ///////////////// Finaliza Rellenar nueva fila pivote
            ///////////////// Inicia Llenar el resto de la tabla
            for (int m = 0; m < filas; m++) {
                if (m != indiceX) {
                    arreglo_temporal[m][0] = arreglo_interno[m][0];//Primeros xk,ck
                    System.out.println(" Ck anterior " + arreglo_temporal[m][0]);
                    arreglo_temporal[m][1] = arreglo_interno[m][1];
                    System.out.println(" Xk anterior " + arreglo_temporal[m][1]);
                    arreglo_temporal[m][2] = String.valueOf(Float.parseFloat(arreglo_interno[m][2]) - Float.parseFloat(arreglo_interno[m][indiceY]) * coef_pivotal);
                    System.out.println(" Nuevo Bi " + arreglo_temporal[m][2]);
                    Float semipivote = Float.parseFloat(arreglo_interno[m][indiceY]);//Evitar que se chanke en el ultimo tablero
                    for (int n = 3; n < (3 + columna + holguras1 + artificiales1); n++) {
                        arreglo_temporal[m][n] = String.valueOf(Float.parseFloat(arreglo_interno[m][n]) - semipivote * Float.parseFloat(arreglo_temporal[indiceX][n]));
                        System.out.println("Nueva fila " + arreglo_temporal[m][n]);
                    }
                }
            }
            Llenarzj_cj(arreglo_temporal, columna, filas);
            //////////////Finaliza llenar el resto de la tabla
            indiceY = Procesar_IndiceY(indice, arreglo_temporal);
            System.out.println("Indice Y " + indiceY);
            valor = Float.parseFloat(arreglo_temporal[filas][indiceY]);
            System.out.println("menor valor" + valor);
            arreglo_interno = arreglo_temporal;//Asignar los valores del arreglo temporal al inicial
            //Repetir si valor es <0
        }
        arreglo = arreglo_interno;
    }

    public void Asignar_Dual() {
        //Dualizar es con minimizacion
        int contador = 3 + restricciones;
        int contador2 = 3 + restricciones + holguras1;
        for (int i = 0; i < variable; i++)//Validamos restricciones
        {
            Datos_menos_Holgura_mas_ArtificialT(i, restricciones, contador, contador2);//Solo programado para pasar
            //de maximizacion a minimizacion en el dual
            contador = contador + 1;
            contador2++;
        }

        //asignar nuevo vector solucion
        for (int a = 0; a < restricciones; a++)//Esto transfiere los valores al nuevo vector solucion
        {
            Vector_solucion[a] = Long.parseLong(getMiModelo().getValueAt(a, variable + 1).toString());
        }
        for (int y = (holguras1 + restricciones); y < (restricciones + holguras1 + artificiales1); y++) {
            Vector_solucion[y] = Float.parseFloat(M);
        }

    }

    public void Asignar_Primal() {

        int contador = 3 + variable;
        int contador2 = 3 + variable + holguras1;
        //System.out.println(contador2);
        for (int i = 0; i < restricciones; i++)//Validamos restricciones
        {
            if (getMiTabla().getValueAt(i, variable).equals(">=")) {
                Datos_menos_Holgura_mas_Artificial(i, variable, contador, contador2);//
                contador = contador + 1;
                contador2++;
            }
            else if (getMiTabla().getValueAt(i, variable).equals("<=")) {
                Datos_mas_Holgura(i, variable, contador);
                contador = contador + 1;
            }
            else if (getMiTabla().getValueAt(i, variable).equals("=")) {
                Datos_mas_Artificial(i, variable, contador2);
                contador2++;
            }
            captura[i][0] = getMiModelo().getValueAt(i, variable + 1).toString();
            captura[i][1] = "" + (i + 1);//Guardar datos Bi del tablero inicial para la comparacion en el dual
        }
        //Fin de asignar
        System.out.println("Tablero Inicial");
        for (int u = 0; u < (restricciones); u++) {
            for (int y = 0; y < (3 + variable + holguras1 + artificiales1); y++) {
                System.out.print(arreglo[u][y] + " ");
            }
            System.out.println();
        }
    }
}
