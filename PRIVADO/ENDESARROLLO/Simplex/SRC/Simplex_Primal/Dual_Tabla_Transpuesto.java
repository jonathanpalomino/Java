package Simplex_Primal;

import javax.swing.table.DefaultTableModel;

public class Dual_Tabla_Transpuesto extends javax.swing.JFrame {
public DefaultTableModel miModelo;
public String[][] data = {};
float valorZ=0;
String valXlbl="";
    Dual_Tabla_Transpuesto(String[][] arreglo, int F1, int F2)
    {
        setLocation(300, 430);
        initComponents();
        miModelo = new DefaultTableModel(data, new String[]{""}) {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
 };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        GenerarTitulos(F1);
        getMiModelo().setColumnCount(F1);
        getMiModelo().setRowCount(F2+1);
        for(int n=0;n<F2+1;n++)
            {
            for(int m=0;m<F1;m++)
                {
                Tabla.setValueAt(arreglo[n][m], n, m);
                }
            }
        //Buscar unitarios
        for(int y=0;y<F2;y++)
          {
            for(int h=3;h<F1;h++)
            {
            if(getMiModelo().getValueAt(y, 1).equals(getMiModelo().getColumnName(h).toString()))//obtiene un elemento del vector solucion y lo compara con el nombre de la columna
            {
                Tabla.setValueAt("1", y, h);
            }
            }
        }
        //Fin de buscar i asignar unitarios
        AsignarEtiquetas(F2);
       // Determinar_multiple(F2,F1);
    }


public  DefaultTableModel getMiModelo() {
        return miModelo;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        valX = new javax.swing.JLabel();
        lblZ = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        jScrollPane1.setViewportView(Tabla);

        valX.setFont(new java.awt.Font("Tahoma", 1, 11));

        lblZ.setFont(new java.awt.Font("Tahoma", 1, 12));
        lblZ.setText("Valor Z =");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(valX, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE))
                        .addGap(28, 28, 28))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addComponent(valX, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void GenerarTitulos(int nuevoF1)
    {
    Object[] titulos = new Object[nuevoF1];
        for(int i=3;i<nuevoF1;i++)
        {
        titulos[i] = "Y"+String.valueOf(i-2);
        }
    titulos[0]="Ck";
    titulos[1]="Yk";
    titulos[2]="Bi";
    getMiModelo().setColumnIdentifiers(titulos);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblZ;
    private javax.swing.JLabel valX;
    // End of variables declaration//GEN-END:variables
    private void AsignarEtiquetas(int nuevoF2)
    {
        for(int p=0;p<nuevoF2;p++)//Aproximar al centecimo
        {
            valXlbl = valXlbl+"\n"+(String)Tabla.getValueAt(p, 1)+" = "+String.valueOf(Aproximar(Tabla.getValueAt(p, 2), 2))+"  ";
        valorZ = valorZ + Float.parseFloat(Tabla.getValueAt(p, 0).toString())*Float.parseFloat(Tabla.getValueAt(p, 2).toString());
        }
        valX.setText(valXlbl);
        lblZ.setText("Valor Z = "+String.valueOf(valorZ));

    }
        private double Aproximar(Object numero, int decimales)
    {
        double potencia=Math.pow(10, decimales);
        double retorno=(int)(Double.parseDouble(String.valueOf(numero))*potencia)/potencia*1.0;
        return retorno;
    }
}
