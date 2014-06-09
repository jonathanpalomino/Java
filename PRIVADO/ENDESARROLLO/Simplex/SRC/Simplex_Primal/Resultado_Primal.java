package Simplex_Primal;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JONATHAN
 */
public class Resultado_Primal extends JFrame {
public DefaultTableModel miModelo;
public String[][] data = {};
public String[] vector;
String valXlbl="";
float valorZ=0;
String valMlbl=" ",tipo;
double total;
String[][] arreglo_trans;
String[][] captura;
int variable=0,restriccion=0;
int nuevoF1=0,nuevoF2=0;


    public  DefaultTableModel getMiModelo() {
        return miModelo;
    }

    public Resultado_Primal(String[][] arreglo, int variable, int restriccion,String Tipo, int holguras1, int artificiales1, String[][] captura)
    {
        setLocation(300, 370);
        initComponents();
        vector = new String[restriccion];
        this.variable = variable;
        this.restriccion = restriccion;
        this.captura = captura;
        tipo= Tipo;
        miModelo = new DefaultTableModel(data, new String[]{""}) {
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
 };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);     

        nuevoF1 = 3+variable+holguras1+artificiales1;
        nuevoF2 = restriccion+1;

        GenerarTitulos((nuevoF1-artificiales1),artificiales1);
        getMiModelo().setColumnCount(nuevoF1);
        getMiModelo().setRowCount(nuevoF2);

        for(int n=0;n<nuevoF2;n++)
        {
            for(int m=0;m<nuevoF1;m++)
            {
            Tabla.setValueAt(arreglo[n][m], n, m);
            }
        }
        
        AsignarEtiquetas(nuevoF2,Tipo);
     Determinar_multiple(restriccion,variable);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        valX = new javax.swing.JLabel();
        lblZ = new javax.swing.JLabel();
        Dtrans = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("RESULTADOS");
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

        valX.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        lblZ.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblZ.setText("Valor Z =");

        Dtrans.setText("Hacer Dual Transpuesto");
        Dtrans.setToolTipText("Transpone la tabla actual y obtiene el optimo dual");
        Dtrans.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DtransActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Dtrans)
                    .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                        .addComponent(valX, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(valX, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblZ, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Dtrans)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DtransActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DtransActionPerformed
        int F1 = 0,F2 = 0;
        F1 = 3+restriccion+variable;
        F2 = variable;
        arreglo_trans = new String[F2+1][F1];
        //llenar con 0 el arreglo
        for(int y=0;y<F2+1;y++)
           {
            for(int h=0;h<F1;h++)
                {
                arreglo_trans[y][h]="0";
            }
        }
//Escribe los numeros que contienen las variables de holgura que ahora seran basicas
        int contador=0;
        for(int t=3;t<(3+restriccion+variable);t++)
        {
           double val;
           val = Double.parseDouble(getMiModelo().getValueAt((restriccion), t).toString());//Si el valor zj-cj fue positivo solo coge los mayores que 0

           if(val>0)
           {
               if(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))<=variable)//Si el valor es positivo toma la columna
                   //Si esta demuestra una variable que era basica y sera luego de holgura asignamos su valor correctamente
               {
                   arreglo_trans[contador][1]="Y"+(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))+restriccion);
               }
                else
                {
                   //Si era de holgura la convierte en basica para el dual
                   arreglo_trans[contador][1]="Y"+(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))-variable);
                }
               //Inicio de asignar datos de Ck,Yk,Bi
            arreglo_trans[contador][2]=getMiModelo().getValueAt(restriccion, t).toString();
            
            for(int h=0;h<restriccion;h++)
                {
                //Aqui se extrae la variable que contiene este arreglo
                int colum;
                int fila = 0;
                if(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))<=variable)//Si la variable es basica
                {
                    colum=Integer.parseInt(getMiModelo().getColumnName(t).substring(1))+restriccion;//La convierto a holgura para el dual
                }
                else
                {
                   colum=Integer.parseInt(getMiModelo().getColumnName(t).substring(1));              
                }
                fila =(Integer.parseInt(captura[h][1])+variable);
                if(fila==colum)
                {
                    arreglo_trans[contador][0]=captura[h][0];//El vector captura obtiene los valores de la primera tabla
                      //Lo que seria los elementos del nuevo vector de la funcion objetivo
                }

                //Inicia a escribir las filas con el signo cambiado
                if(Integer.parseInt(getMiModelo().getValueAt(h,1).toString().substring(1))>variable)//Indica que es de holgura
                {
                    int columna=Integer.parseInt(getMiModelo().getValueAt(h,1).toString().substring(1))-variable+2;//obtiene la variable y da la columna
                    arreglo_trans[contador][columna]=getMiModelo().getValueAt(h,(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))-variable+4)).toString();
                    arreglo_trans[contador][columna]=String.valueOf(-1*Double.parseDouble(arreglo_trans[contador][columna]));
                    //Cambia el signo a negativo como sucede en la transpuesta
                    arreglo_trans[F2][columna]=getMiModelo().getValueAt(h, 2).toString();//Asignamos los que eran Bi a Zj-Cj
                }
                 else//Si no es basica
                 {
                    int columna2=Integer.parseInt(getMiModelo().getValueAt(h,1).toString().substring(1))+restriccion+2;
                    arreglo_trans[contador][columna2]=getMiModelo().getValueAt(h,(Integer.parseInt(getMiModelo().getColumnName(t).substring(1))-variable+4)).toString();
                 arreglo_trans[contador][columna2]=String.valueOf(-1*Double.parseDouble(arreglo_trans[contador][columna2]));
                    //Cambia el signo a negativo como sucede en la transpuesta
                 arreglo_trans[F2][columna2]=getMiModelo().getValueAt(h, 2).toString();//Asignamos los que eran Bi a Zj-Cj
                 }
            }
            //Fin de asignar datos de ck,Yk,Bi y filas necesarias
            contador++;//Aumenta en 1 por cada bucle que complio con la condicion(evita un for desmedido)
            //Evitamos asi hacer bucles innecesarios
           }
        }
        //Fin de escribir los 3 primeros
            for(int y=0;y<F2+1;y++)
           {
            for(int h=0;h<F1;h++)
                {
                System.out.print(" "+arreglo_trans[y][h]);
            }System.out.println();
        }
        Dual_Tabla_Transpuesto obj = new Dual_Tabla_Transpuesto(arreglo_trans,F1,F2);
        obj.show();
    }//GEN-LAST:event_DtransActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Dtrans;
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
        titulos[i] = "X"+String.valueOf(i-2);
        }
    for(int i=nuevoF1;i<(nuevoF1+artificiales1);i++)
  {
        titulos[i]="L"+String.valueOf(i-nuevoF1);
    }
    titulos[0]="Ck";
    titulos[1]="Xk";
    titulos[2]="Bi";
    getMiModelo().setColumnIdentifiers(titulos);
    }

    private void Determinar_multiple(int restriccion,int variable)
    {
        for(int k=0;k<restriccion;k++)
        {
            vector[k]=String.valueOf(Tabla.getValueAt(k,1));
            //System.out.println(vector[k]);
        }
        for(int i=3;i<(variable+restriccion+3);i++)
        {
            if(Tabla.getValueAt((restriccion), i).toString().equalsIgnoreCase("0.0") && Buscar(Tabla.getColumnName(i),vector)==false)
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
