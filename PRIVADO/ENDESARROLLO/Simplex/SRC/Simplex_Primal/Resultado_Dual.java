package Simplex_Primal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JONATHAN
 */
public class Resultado_Dual extends JFrame {
public DefaultTableModel miModelo;
public String[][] data = {};
public String[] vector;
String valXlbl="";
String valMlbl=" ";
double total;
float valorZ=0;

    public  DefaultTableModel getMiModelo() {
        return miModelo;
    }

    public Resultado_Dual(String[][] arreglo, int nuevoF1, int nuevoF2,String Tipo, int restricciones, int artificiales1, int holguras1)
    {

        setLocation(300, 370);
        initComponents();
        vector = new String[nuevoF2];
        miModelo = new DefaultTableModel(data, new String[]{""}) {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
 };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        GenerarTitulos((nuevoF1-artificiales1),artificiales1);
        getMiModelo().setColumnCount(nuevoF1);
        getMiModelo().setRowCount(nuevoF2+1);

        for(int n=0;n<(nuevoF2+1);n++)
        {
            for(int m=0;m<nuevoF1;m++)
            {
            Tabla.setValueAt(arreglo[n][m], n, m);
            }
        }
        
        AsignarEtiquetas((nuevoF2+1),Tipo);
        Determinar_multiple(nuevoF2,nuevoF1);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        valX = new javax.swing.JLabel();
        lblZ = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RESULTADOS EN DUAL");
        setResizable(false);

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
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addComponent(valX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(75, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblZ;
    private javax.swing.JLabel valX;
    // End of variables declaration//GEN-END:variables

    private void AsignarEtiquetas(int nuevoF2,String tipo)
    {
        for(int p=0;p<(nuevoF2-1);p++)//Aproximar al centecimo
        {
            valXlbl = valXlbl+"\n"+(String)Tabla.getValueAt(p, 1)+" = "+String.valueOf(Aproximar(Tabla.getValueAt(p, 2), 2))+"  ";
        valorZ = valorZ + Float.parseFloat(Tabla.getValueAt(p, 0).toString())*Float.parseFloat(Tabla.getValueAt(p, 2).toString());
        }
        valX.setText(valXlbl);
        lblZ.setText("Valor Z = "+String.valueOf(valorZ));
    }

    private void GenerarTitulos(int nuevoF1, int artificiales1)
    {
    Object[] titulos = new Object[nuevoF1+artificiales1];
        for(int i=3;i<nuevoF1;i++)
        {
        titulos[i] = "Y"+String.valueOf(i-2);
        }
    for(int i=nuevoF1;i<(nuevoF1+artificiales1);i++)
  {
        titulos[i]="L"+String.valueOf(i-nuevoF1);
    }
    titulos[0]="Ck";
    titulos[1]="Yk";
    titulos[2]="Bi";
    getMiModelo().setColumnIdentifiers(titulos);
    }

    private void Determinar_multiple(int filas,int columnas)
    {
        for(int k=0;k<filas;k++)
        {
            vector[k]=String.valueOf(Tabla.getValueAt(k,1));
            System.out.println(vector[k]);
        }
        for(int i=3;i<columnas;i++)
        {
            if(Tabla.getValueAt((filas), i).toString().equalsIgnoreCase("0.0") && Buscar(Tabla.getColumnName(i),vector)==false)
            {
                JOptionPane.showMessageDialog(this, "Resultado de solucion óptima multiple", "Aviso", JOptionPane.QUESTION_MESSAGE);
            }
        }
    }

    private boolean Buscar(String nombrecolumna, String[] vector)
    {
        boolean resultado=false;
        for(int h=0;h<vector.length;h++)
        {
            if(vector[h].equalsIgnoreCase(nombrecolumna))
            {
            resultado=true;
            }
        }
        return resultado;
    }
    private double Aproximar(Object numero, int decimales)
    {
        double potencia=Math.pow(10, decimales);
        double retorno=(int)(Double.parseDouble(String.valueOf(numero))*potencia)/potencia*1.0;
        return retorno;
    }
}
