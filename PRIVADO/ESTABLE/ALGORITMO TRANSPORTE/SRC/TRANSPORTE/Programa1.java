package TRANSPORTE;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author JONATHAN
 */
public class Programa1 extends javax.swing.JFrame {

    public DefaultTableModel miModelo;
    public String[][] data = {};
    public int numero_origenes = 0, nuevo_numero_origen = 0, indiceX, IndiceY;
    public int numero_destinos = 0, nuevo_numero_destino = 0, fila = 0, columna = 0;
    public Double arreglo[][],arreglo1[][],M= 200000.0,arreglo_inicial[][];
    public Double indice_demanda[],indice_demanda1[],indice_oferta[],indice_oferta1[];
    boolean vogel=false;
    public int demandaX=0,ofertaY=0;
    JTable getMiTabla() {
        return Tabla;
    }

    public DefaultTableModel getMiModelo() {
        return miModelo;
    }

    /** Creates new form Programa */
    public Programa1() {
        setLocation(300, 170);
        initComponents();
        setTitle("Transporte");
        miModelo = new DefaultTableModel(data, new String[]{""}) {
        };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);

    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        txtOrigen = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuEsquina = new javax.swing.JMenuItem();
        menuCostos = new javax.swing.JMenuItem();
        menuVogel = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnCrear.setText("Crear Tabla");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });

        jLabel1.setText("# Origenes");

        jLabel2.setText("# Destinos");

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

        jButton1.setText("Nuevo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jMenu1.setText("Metodos");

        menuEsquina.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        menuEsquina.setText("Esquina NO");
        menuEsquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEsquinaActionPerformed(evt);
            }
        });
        jMenu1.add(menuEsquina);

        menuCostos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        menuCostos.setText("Costos Minimos");
        menuCostos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCostosActionPerformed(evt);
            }
        });
        jMenu1.add(menuCostos);

        menuVogel.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, 0));
        menuVogel.setText("Vogel");
        menuVogel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuVogelActionPerformed(evt);
            }
        });
        jMenu1.add(menuVogel);

        Salir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        Salir.setText("Salir");
        Salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalirActionPerformed(evt);
            }
        });
        jMenu1.add(Salir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDestino)
                            .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCrear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCrear)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addGap(24, 24, 24)
                            .addComponent(jLabel2))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(9, 9, 9)
                            .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        try {
            numero_origenes = Integer.parseInt(txtOrigen.getText());
            numero_destinos = Integer.parseInt(txtDestino.getText());
            GenerarTitulos(numero_destinos);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Error Digite numeros enteros");
        }
    }//GEN-LAST:event_btnCrearActionPerformed

    private void menuEsquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEsquinaActionPerformed

        try {
          vogel=false;
        Dimensionar();
            AsignaData();
            ResultadoNO abc = new ResultadoNO(arreglo, nuevo_numero_origen, nuevo_numero_destino);
            abc.show();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ingrese valores validos a la tabla de costos","Error de Ingreso",JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_menuEsquinaActionPerformed

    private void menuCostosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCostosActionPerformed
 try {
        vogel=true;
            Dimensionar();
            Double menor = 0.0;
        for(int con=0;con<nuevo_numero_destino;con++)///Para las columnas
        {
       for(int j=0;j<indice_demanda.length;j++)
        {
            indice_demanda[j]=arreglo[j][con];
        }
       menor=Obtener_Menor1(indice_demanda);
       indice_oferta[con]=menor;
       System.out.println(indice_oferta[con]);
        }

        for(int con=0;con<nuevo_numero_origen;con++)///Para las filas
        {
       for(int j=0;j<indice_oferta.length;j++)
        {
            indice_oferta1[j]=arreglo[con][j];
        }
       menor=Obtener_Menor1(indice_oferta1);
       indice_demanda1[con]=menor;
       System.out.println(indice_demanda1[con]);
        }
          double valor1,valor2;
        valor1 = indice_oferta[demandaX];
        valor2=indice_demanda1[ofertaY];
        System.out.println("ab "+valor1+" d "+valor2);
        while(valor1>0 || valor2>0)
        {
        if(valor1==valor2)
         {//ambos valores son iguales buscar el menor elemento de ambos
             int menorvalor1,menorvalor2;
             for(int i=0;i<indice_oferta1.length;i++)//filas
            {
             indice_oferta1[i]=arreglo[ofertaY][i];
            System.out.println("ab1 "+indice_oferta1[i]);
            }
              for(int i=0;i<indice_demanda.length;i++)//columnas
            {
                indice_demanda[i]=arreglo[i][demandaX];
            System.out.println("ab12 "+indice_demanda[i]);
            }
             menorvalor1 = Obtener_Indice_menor(indice_oferta1);
             menorvalor2 = Obtener_Indice_menor(indice_demanda);

             System.out.println(arreglo[menorvalor2][demandaX]+" y "+arreglo[ofertaY][menorvalor1]);

             if(arreglo[menorvalor2][demandaX]<arreglo[ofertaY][menorvalor1])
             {
                //las coordenadas son menorvalor2,demandaX
                if(arreglo[ofertaY][nuevo_numero_destino]<arreglo[menorvalor2][demandaX])
                {
                arreglo1[menorvalor2][demandaX]=arreglo[menorvalor2][nuevo_numero_destino];
                arreglo[menorvalor2][demandaX]=arreglo[menorvalor2][demandaX]-arreglo[ofertaY][nuevo_numero_destino];
                arreglo[menorvalor2][nuevo_numero_destino]=0.0;
                Sobre_fila(arreglo, "fila", menorvalor2);
                }
                else if(arreglo[ofertaY][nuevo_numero_destino]==arreglo[menorvalor2][demandaX])
                  {
                    arreglo1[menorvalor2][demandaX]= arreglo[menorvalor2][demandaX];
                   arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[ofertaY][nuevo_numero_destino];
                   arreglo[menorvalor2][demandaX]=0.0;
                   Sobre_fila(arreglo, "columna", demandaX);
                   Sobre_fila(arreglo, "columna", menorvalor2);
                }
                else
                {//corregir si es que la interseccion da el mismo numero se matan columna y fila
                   arreglo1[menorvalor2][demandaX]= arreglo[menorvalor2][demandaX];
                   arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[ofertaY][nuevo_numero_destino];
                   arreglo[menorvalor2][demandaX]=0.0;
                   Sobre_fila(arreglo, "columna", demandaX);
                }
                System.out.println("arreglos "+arreglo1[menorvalor2][demandaX]);
             }
             else
                {
                 //los coordenadas son ofertaY,menorvalor1
                 System.out.println(" abc "+arreglo[nuevo_numero_origen][menorvalor1]);
                 System.out.println(" cde "+arreglo[ofertaY][nuevo_numero_destino]);
                 if(arreglo[ofertaY][nuevo_numero_destino]<arreglo[nuevo_numero_origen][menorvalor1])
                  {
                  arreglo1[ofertaY][menorvalor1] = arreglo[ofertaY][nuevo_numero_destino];
                  arreglo[nuevo_numero_origen][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1]-arreglo[ofertaY][nuevo_numero_destino];
                  arreglo[ofertaY][nuevo_numero_destino]=0.0;
                  Sobre_fila(arreglo,"fila",ofertaY);
                 }
                 else
                     {
                     arreglo1[ofertaY][menorvalor1] = arreglo[nuevo_numero_origen][menorvalor1];
                      arreglo[ofertaY][nuevo_numero_destino]= arreglo[ofertaY][nuevo_numero_destino]-arreglo[nuevo_numero_origen][menorvalor1];
                      arreglo[nuevo_numero_origen][menorvalor1]=0.0;
                      Sobre_fila(arreglo, "columna", menorvalor1);
                 }
                System.out.println("arreglos "+arreglo1[ofertaY][menorvalor1]);
                }
        }
        else if(valor1>valor2)
        {
        int menorvalor2;
              for(int i=0;i<indice_demanda.length;i++)//columnas
            {
            indice_demanda[i]=arreglo[i][demandaX];
            System.out.println("ab12 "+indice_demanda[i]);
            }
             menorvalor2 = Obtener_Indice_menor(indice_demanda);
             System.out.println(" coordenda x "+menorvalor2+" y1 "+demandaX);
             System.out.println(arreglo[menorvalor2][nuevo_numero_destino]+"  u "+arreglo[nuevo_numero_origen][demandaX]);
             if(arreglo[menorvalor2][nuevo_numero_destino]<arreglo[nuevo_numero_origen][demandaX])//si son iguales
              {
              arreglo1[menorvalor2][demandaX]=arreglo[menorvalor2][nuevo_numero_destino];
             arreglo[nuevo_numero_origen][demandaX]=arreglo[nuevo_numero_origen][demandaX]-arreglo[menorvalor2][nuevo_numero_destino];
             arreglo[menorvalor2][nuevo_numero_destino]=0.0;
            Sobre_fila(arreglo, "fila", menorvalor2);
             System.out.println("a");
             }
             else
             {
                arreglo1[menorvalor2][demandaX] = arreglo[nuevo_numero_origen][demandaX];
                arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[nuevo_numero_origen][demandaX];
                arreglo[nuevo_numero_origen][demandaX]=0.0;

             System.out.println("b");

             Sobre_fila(arreglo, "columna", demandaX);
             }
             System.out.println(" cooa x "+arreglo1[menorvalor2][demandaX]);
        }
        else//valor1<valor2
            {
            System.out.println(valor1+" val "+valor2);
            int menorvalor1;
             for(int i=0;i<indice_oferta1.length;i++)//filas
            {
             indice_oferta1[i]=arreglo[ofertaY][i];
            System.out.println("ab1 "+indice_oferta1[i]);
            }
            menorvalor1 = Obtener_Indice_menor(indice_oferta1);
            System.out.println(" coordenda x "+menorvalor1+" y "+ofertaY);
            System.out.println(arreglo[nuevo_numero_origen][menorvalor1]+"  "+arreglo[ofertaY][nuevo_numero_destino]);
            if(arreglo[nuevo_numero_origen][menorvalor1]<arreglo[ofertaY][nuevo_numero_destino])
            {
                arreglo1[ofertaY][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1];
                System.out.println("d d "+arreglo1[ofertaY][menorvalor1]);
                arreglo[ofertaY][nuevo_numero_destino]=arreglo[ofertaY][nuevo_numero_destino]-arreglo[nuevo_numero_origen][menorvalor1];
                arreglo[nuevo_numero_origen][menorvalor1]=0.0;
                Sobre_fila(arreglo,"columna",menorvalor1);//
                System.out.println("q");
            }
            else{
                arreglo1[ofertaY][menorvalor1]=arreglo[ofertaY][nuevo_numero_destino];
                arreglo[nuevo_numero_origen][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1]-arreglo[ofertaY][nuevo_numero_destino];
                arreglo[ofertaY][nuevo_numero_destino]=0.0;
                Sobre_fila(arreglo,"fila",ofertaY);
                System.out.println("x");
            }
            System.out.println(" coordx "+arreglo1[ofertaY][menorvalor1]);
            }
                          for(int i=0;i<nuevo_numero_origen;i++)
              {
             for(int j=0;j<nuevo_numero_destino;j++){

                  System.out.print(" "+arreglo[i][j]);
              }System.out.println();
          }
        //REingresar
        menor = 0.0;
        for(int con=0;con<nuevo_numero_destino;con++)///Para las columnas
        {
       for(int j=0;j<nuevo_numero_origen;j++)
        {
            indice_demanda[j]=arreglo[j][con];
        }

       menor=Obtener_Menor(indice_demanda);
       indice_oferta[con]=menor;
       System.out.println("dif "+indice_oferta[con]);
        }

        for(int con=0;con<nuevo_numero_origen;con++)///Para las filas
        {
       for(int j=0;j<nuevo_numero_destino;j++)
        {
            indice_oferta1[j]=arreglo[con][j];
        }
       menor=Obtener_Menor(indice_oferta1);
       indice_demanda1[con]=menor;
       System.out.println("dif 1 "+indice_demanda1[con]);
        }

        demandaX = Obtener_Indice_mayor(indice_oferta);//Retorna el indice del mayor valor de la fila demanda
        valor1 = indice_oferta[demandaX];
        ofertaY=Obtener_Indice_mayor(indice_demanda1);//Retorna el indice del mayor valor de la columna oferta
        valor2=indice_demanda1[ofertaY];
        System.out.println("ab "+valor1+" d "+valor2);
        }
      ResultadoNO abc = new ResultadoNO(arreglo1, nuevo_numero_origen, nuevo_numero_destino);
        abc.show();
        }
        catch (Exception e) {
           JOptionPane.showMessageDialog(null, "Ingrese valores validos a la tabla de costos","Error de Ingreso",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuCostosActionPerformed

    private void menuVogelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuVogelActionPerformed
        
        try{
            vogel=true;
        Dimensionar();//asignar el arreglo inicial
                  for(int i=0;i<nuevo_numero_origen;i++)
              {
             for(int j=0;j<nuevo_numero_destino;j++){

                  System.out.print(" "+arreglo[i][j]);
              }System.out.println();
          }
        Double diferencia = 0.0;
        for(int con=0;con<nuevo_numero_destino;con++)///Para las columnas
        {
       for(int j=0;j<nuevo_numero_origen;j++)
        {
            indice_demanda[j]=arreglo[j][con];
        }
       
       diferencia=Obtener_Menor(indice_demanda);
       indice_oferta[con]=diferencia;
       System.out.println(indice_oferta[con]);
        }
        
        for(int con=0;con<nuevo_numero_origen;con++)///Para las filas
        {
       for(int j=0;j<nuevo_numero_destino;j++)
        {
            indice_oferta1[j]=arreglo[con][j];
        }
       diferencia=Obtener_Menor(indice_oferta1);
       indice_demanda1[con]=diferencia;
       System.out.println(indice_demanda1[con]);
        }
        double valor1,valor2;
        demandaX = Obtener_Indice_mayor(indice_oferta);//Retorna el indice del mayor valor de la fila demanda
        valor1 = indice_oferta[demandaX];
        ofertaY=Obtener_Indice_mayor(indice_demanda1);//Retorna el indice del mayor valor de la columna oferta
        valor2=indice_demanda1[ofertaY];
        System.out.println("ab "+valor1+" d "+valor2);
        while(valor1>0 || valor2>0)
        {
        if(valor1==valor2)
         {//ambos valores son iguales buscar el menor elemento de ambos
             int menorvalor1,menorvalor2;
             for(int i=0;i<indice_oferta1.length;i++)//filas
            {
             indice_oferta1[i]=arreglo[ofertaY][i];
            System.out.println("ab1 "+indice_oferta1[i]);
            }
              for(int i=0;i<indice_demanda.length;i++)//columnas
            {
                indice_demanda[i]=arreglo[i][demandaX];
            System.out.println("ab12 "+indice_demanda[i]);
            }
             menorvalor1 = Obtener_Indice_menor(indice_oferta1);
             menorvalor2 = Obtener_Indice_menor(indice_demanda);

             System.out.println(arreglo[menorvalor2][demandaX]+" y "+arreglo[ofertaY][menorvalor1]);

             if(arreglo[menorvalor2][demandaX]<arreglo[ofertaY][menorvalor1])
             {
                //las coordenadas son menorvalor2,demandaX
                if(arreglo[ofertaY][nuevo_numero_destino]<arreglo[menorvalor2][demandaX])
                {
                arreglo1[menorvalor2][demandaX]=arreglo[menorvalor2][nuevo_numero_destino];
                arreglo[menorvalor2][demandaX]=arreglo[menorvalor2][demandaX]-arreglo[ofertaY][nuevo_numero_destino];
                arreglo[menorvalor2][nuevo_numero_destino]=0.0;
                Sobre_fila(arreglo, "fila", menorvalor2);
                }
                else if(arreglo[ofertaY][nuevo_numero_destino]==arreglo[menorvalor2][demandaX])
                  {
                    arreglo1[menorvalor2][demandaX]= arreglo[menorvalor2][demandaX];
                   arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[ofertaY][nuevo_numero_destino];
                   arreglo[menorvalor2][demandaX]=0.0;
                   Sobre_fila(arreglo, "columna", demandaX);
                   Sobre_fila(arreglo, "columna", menorvalor2);
                }
                else
                {//corregir si es que la interseccion da el mismo numero se matan columna y fila
                   arreglo1[menorvalor2][demandaX]= arreglo[menorvalor2][demandaX]; 
                   arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[ofertaY][nuevo_numero_destino];
                   arreglo[menorvalor2][demandaX]=0.0;
                   Sobre_fila(arreglo, "columna", demandaX);
                }
                System.out.println("arreglos "+arreglo1[menorvalor2][demandaX]);
             }
             else
                {
                 //los coordenadas son ofertaY,menorvalor1
                 System.out.println(" abc "+arreglo[nuevo_numero_origen][menorvalor1]);
                 System.out.println(" cde "+arreglo[ofertaY][nuevo_numero_destino]);
                 if(arreglo[ofertaY][nuevo_numero_destino]<arreglo[nuevo_numero_origen][menorvalor1])
                  {
                  arreglo1[ofertaY][menorvalor1] = arreglo[ofertaY][nuevo_numero_destino];
                  arreglo[nuevo_numero_origen][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1]-arreglo[ofertaY][nuevo_numero_destino];
                  arreglo[ofertaY][nuevo_numero_destino]=0.0;
                  Sobre_fila(arreglo,"fila",ofertaY);
                 }
                 else 
                     {
                     arreglo1[ofertaY][menorvalor1] = arreglo[nuevo_numero_origen][menorvalor1];
                      arreglo[ofertaY][nuevo_numero_destino]= arreglo[ofertaY][nuevo_numero_destino]-arreglo[nuevo_numero_origen][menorvalor1];
                      arreglo[nuevo_numero_origen][menorvalor1]=0.0;
                      Sobre_fila(arreglo, "columna", menorvalor1);
                 }  
                System.out.println("arreglos "+arreglo1[ofertaY][menorvalor1]);
                }
        }
        else if(valor1>valor2)
        {
        int menorvalor2;
              for(int i=0;i<indice_demanda.length;i++)//columnas
            {
            indice_demanda[i]=arreglo[i][demandaX];
            System.out.println("ab12 "+indice_demanda[i]);
            }
             menorvalor2 = Obtener_Indice_menor(indice_demanda);
             System.out.println(" coordenda x "+menorvalor2+" y1 "+demandaX);
             System.out.println(arreglo[menorvalor2][nuevo_numero_destino]+"  u "+arreglo[nuevo_numero_origen][demandaX]);
             if(arreglo[menorvalor2][nuevo_numero_destino]<arreglo[nuevo_numero_origen][demandaX])//si son iguales
              {
              arreglo1[menorvalor2][demandaX]=arreglo[menorvalor2][nuevo_numero_destino];
             arreglo[nuevo_numero_origen][demandaX]=arreglo[nuevo_numero_origen][demandaX]-arreglo[menorvalor2][nuevo_numero_destino];
             arreglo[menorvalor2][nuevo_numero_destino]=0.0;
            Sobre_fila(arreglo, "fila", menorvalor2);
             System.out.println("a");
             }
             else
             {
                arreglo1[menorvalor2][demandaX] = arreglo[nuevo_numero_origen][demandaX];
                arreglo[menorvalor2][nuevo_numero_destino]=arreglo[menorvalor2][nuevo_numero_destino]-arreglo[nuevo_numero_origen][demandaX];
                arreglo[nuevo_numero_origen][demandaX]=0.0;

             System.out.println("b");
             
             Sobre_fila(arreglo, "columna", demandaX);
             }
             System.out.println(" cooa x "+arreglo1[menorvalor2][demandaX]);
        }
        else//valor1<valor2
            {
            System.out.println(valor1+" val "+valor2);
            int menorvalor1;
             for(int i=0;i<indice_oferta1.length;i++)//filas
            {
             indice_oferta1[i]=arreglo[ofertaY][i];
            System.out.println("ab1 "+indice_oferta1[i]);
            }
            menorvalor1 = Obtener_Indice_menor(indice_oferta1);
            System.out.println(" coordenda x "+menorvalor1+" y "+ofertaY);
            System.out.println(arreglo[nuevo_numero_origen][menorvalor1]+"  "+arreglo[ofertaY][nuevo_numero_destino]);
            if(arreglo[nuevo_numero_origen][menorvalor1]<arreglo[ofertaY][nuevo_numero_destino])
            {
                arreglo1[ofertaY][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1];
                System.out.println("d d "+arreglo1[ofertaY][menorvalor1]);
                arreglo[ofertaY][nuevo_numero_destino]=arreglo[ofertaY][nuevo_numero_destino]-arreglo[nuevo_numero_origen][menorvalor1];
                arreglo[nuevo_numero_origen][menorvalor1]=0.0;
                Sobre_fila(arreglo,"columna",menorvalor1);//
                System.out.println("q");
            }
            else{
                arreglo1[ofertaY][menorvalor1]=arreglo[ofertaY][nuevo_numero_destino];
                arreglo[nuevo_numero_origen][menorvalor1]=arreglo[nuevo_numero_origen][menorvalor1]-arreglo[ofertaY][nuevo_numero_destino];
                arreglo[ofertaY][nuevo_numero_destino]=0.0;
                Sobre_fila(arreglo,"fila",ofertaY);
                System.out.println("x");
            }
            System.out.println(" coordx "+arreglo1[ofertaY][menorvalor1]);
            }
                          for(int i=0;i<nuevo_numero_origen;i++)
              {
             for(int j=0;j<nuevo_numero_destino;j++){

                  System.out.print(" "+arreglo[i][j]);
              }System.out.println();
          }
        //REingresar
        diferencia = 0.0;
        for(int con=0;con<nuevo_numero_destino;con++)///Para las columnas
        {
       for(int j=0;j<nuevo_numero_origen;j++)
        {
            indice_demanda[j]=arreglo[j][con];
        }

       diferencia=Obtener_Menor(indice_demanda);
       indice_oferta[con]=diferencia;
       System.out.println("dif "+indice_oferta[con]);
        }

        for(int con=0;con<nuevo_numero_origen;con++)///Para las filas
        {
       for(int j=0;j<nuevo_numero_destino;j++)
        {
            indice_oferta1[j]=arreglo[con][j];
        }
       diferencia=Obtener_Menor(indice_oferta1);
       indice_demanda1[con]=diferencia;
       System.out.println("dif 1 "+indice_demanda1[con]);
        }

        demandaX = Obtener_Indice_mayor(indice_oferta);//Retorna el indice del mayor valor de la fila demanda
        valor1 = indice_oferta[demandaX];
        ofertaY=Obtener_Indice_mayor(indice_demanda1);//Retorna el indice del mayor valor de la columna oferta
        valor2=indice_demanda1[ofertaY];
        System.out.println("ab "+valor1+" d "+valor2);
        }
        ResultadoNO abc = new ResultadoNO(arreglo1, nuevo_numero_origen, nuevo_numero_destino);
        abc.show();
        }
        catch(Exception e){
         JOptionPane.showMessageDialog(null,"INGRESE VALORES VALIDOS","ERROR DE INGRESO",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_menuVogelActionPerformed
    private void Sobre_fila(Double[][] arreglo, String tipo, int columna_fila)
    {
        if(tipo.equalsIgnoreCase("fila"))
           {
for(int h=0;h<=nuevo_numero_destino;h++)
    {
    arreglo[columna_fila][h]=2*M;
}
        }
        else//Columna
           {
for(int k=0;k<nuevo_numero_origen;k++)
    {
arreglo[k][columna_fila]=2*M;
}
        }
    }
    private void SalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_SalirActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       Nuevo(); // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Programa1().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Salir;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnCrear;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuCostos;
    private javax.swing.JMenuItem menuEsquina;
    private javax.swing.JMenuItem menuVogel;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtOrigen;
    // End of variables declaration//GEN-END:variables

    public void GenerarTitulos(int destino) {
        Object[] titulos = new Object[destino + 1];
        for (int i = 0; i < destino; i++) {
            titulos[i] = "Ciudad" + String.valueOf(i + 1);
        }
        titulos[destino] = "Recursos";
        getMiModelo().setColumnIdentifiers(titulos);
        getMiModelo().setColumnCount(destino + 1);
        getMiModelo().setRowCount(numero_origenes + 1);
        getMiModelo().setValueAt("<<<Disponibilidad", numero_origenes, destino);
    }

    public void AsignaData() {
        System.out.println(nuevo_numero_origen + "  " + nuevo_numero_destino);
        fila=0;columna=0;//Inicializa variables para poder ejecutar modificando el table inicial
        while (fila < nuevo_numero_origen) {
            while (columna < nuevo_numero_destino) {
                //System.out.println("Columna " + columna);
                //System.out.println("Destino " + arreglo[fila][nuevo_numero_destino] + " Origen " + arreglo[nuevo_numero_origen][columna]);
                if (arreglo[fila][nuevo_numero_destino] > arreglo[nuevo_numero_origen][columna])//Destino>origen
                {
                    arreglo[fila][columna] = arreglo[nuevo_numero_origen][columna];
                    arreglo[fila][nuevo_numero_destino] = arreglo[fila][nuevo_numero_destino] - arreglo[nuevo_numero_origen][columna];
                    arreglo[nuevo_numero_origen][columna] = 0.0;
                    columna++;
                } else if (arreglo[fila][nuevo_numero_destino] < arreglo[nuevo_numero_origen][columna])//origen>destino
                {
                    arreglo[fila][columna] = arreglo[fila][nuevo_numero_destino];
                    arreglo[nuevo_numero_origen][columna] = arreglo[nuevo_numero_origen][columna] - arreglo[fila][nuevo_numero_destino];
                    arreglo[fila][nuevo_numero_destino] = 0.0;
                    fila++;
                    break;
                } else //Si ambos son iguales
                {
                    arreglo[fila][columna] = arreglo[fila][nuevo_numero_destino];
                    arreglo[nuevo_numero_origen][columna] = arreglo[nuevo_numero_origen][columna] - arreglo[fila][nuevo_numero_destino];
                    arreglo[fila][nuevo_numero_destino] = 0.0;
                    fila++;
                    columna++;
                    break;
                }
            }
        }
    }

    public void AsignarValores(int nuevo_origen, int nuevo_destino) {
        indiceX = 0;
        IndiceY = 0;
        for (indiceX = 0; indiceX < numero_origenes; indiceX++)//columna de recursos
        {
            if(vogel){
               arreglo[indiceX][nuevo_destino-1]=M;
            }
            arreglo[indiceX][nuevo_destino] = Double.parseDouble(Tabla.getValueAt(indiceX, numero_destinos).toString());
            //System.out.println(arreglo[indiceX][nuevo_destino] + "  " + indiceX + " " + (nuevo_destino));
        }
        for (IndiceY = 0; IndiceY < numero_destinos; IndiceY++)//fila de demanda
        {
            if(vogel){
               arreglo[nuevo_origen-1][IndiceY]=M;
            }
            arreglo[nuevo_origen][IndiceY] = Double.parseDouble(Tabla.getValueAt(numero_origenes, IndiceY).toString());
            //System.out.println(arreglo[nuevo_origen][IndiceY] + " " + (nuevo_origen) + " " + IndiceY);
        }
        if(vogel)
          {
          for(int i=0;i<getMiTabla().getRowCount()-1;i++)//Asignar al arreglo los valores de la tabla
              {
             for(int j=0;j<getMiTabla().getColumnCount()-1;j++){
                  arreglo[i][j]=Double.parseDouble(getMiTabla().getValueAt(i, j).toString());
              }
          }
          }
    }

    public void AsignarArreglos(double suma_demanda, double suma_recursos) {
        if (suma_recursos == suma_demanda) {
            nuevo_numero_destino = numero_destinos;
            nuevo_numero_origen = numero_origenes;
            arreglo = new Double[nuevo_numero_origen + 1][nuevo_numero_destino + 1];
            AsignarValores(nuevo_numero_origen, nuevo_numero_destino);
        } else if (suma_demanda > suma_recursos) {
            nuevo_numero_destino = numero_destinos;
            nuevo_numero_origen = numero_origenes + 1;
            arreglo = new Double[nuevo_numero_origen + 1][nuevo_numero_destino + 1];
            AsignarValores(nuevo_numero_origen, nuevo_numero_destino);
            arreglo[indiceX][nuevo_numero_destino] = suma_demanda - suma_recursos;
            //System.out.println(arreglo[indiceX][nuevo_numero_destino] + "  " + indiceX + " " + (nuevo_numero_destino));
        } else {//suma_recursos>suma_demanda
            nuevo_numero_destino = numero_destinos + 1;
            nuevo_numero_origen = numero_origenes;
            arreglo = new Double[nuevo_numero_origen + 1][nuevo_numero_destino + 1];
            AsignarValores(nuevo_numero_origen, nuevo_numero_destino);
            arreglo[nuevo_numero_origen][IndiceY] = suma_recursos - suma_demanda;
            //System.out.println(arreglo[nuevo_numero_origen][IndiceY] + " " + (nuevo_numero_origen) + "  " + (IndiceY));
        }
        arreglo1=new Double[nuevo_numero_origen + 1][nuevo_numero_destino + 1];
        indice_demanda = new Double[nuevo_numero_origen];
        indice_demanda1 = new Double[nuevo_numero_origen];
        indice_oferta=new Double[nuevo_numero_destino];
        indice_oferta1=new Double[nuevo_numero_destino];
    }

    public void Dimensionar() 
    {
        double suma_recursos = 0;
        double suma_demanda = 0;
        try {
            for (int i = 0; i < numero_origenes; i++) {
                suma_recursos = suma_recursos + Double.parseDouble(Tabla.getValueAt(i, numero_destinos).toString());
            }
            for (int j = 0; j < numero_destinos; j++) {
                suma_demanda = suma_demanda + Double.parseDouble(Tabla.getValueAt(numero_origenes, j).toString());
            }
            System.out.println(suma_demanda + "  " + suma_recursos);
            AsignarArreglos(suma_demanda, suma_recursos);
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Ingrese valores validos a recursos y/o demanda");
        }
    }

        private double Obtener_Menor(Double[] indice)
    {
        int b=0;
        double valor1,valor2;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]<indice[b])
          {
              b=i;
          }
        }
        valor1 = indice[b];
        indice[b]=2*M;
        b=0;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]<indice[b])
          {
              b=i;
          }
        }
        valor2=indice[b];
        return Math.abs(valor1-valor2);
    }
    private int Obtener_Indice_mayor(Double[] indice)
    {
        int b=0;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]>indice[b])
          {
              b=i;
          }
        }
        return b;
    }
        private int Obtener_Indice_menor(Double[] indice)
    {
        int b=0;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]<indice[b])
          {
              b=i;
          }
        }
        return b;
    }
        private void Nuevo()  {
       txtOrigen.setText("");
        txtDestino.setText("");
        miModelo = new DefaultTableModel(data, new String[]{""}) {
        };
        Tabla.setModel(miModelo);
    }
                 private int Obtener_Indice_mayor1(Double[] indice)
    {
        int b=0;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]>indice[b])
          {
              b=i;
          }
        }
        return b;
    }
        private int Obtener_Indice_menor1(Double[] indice)
    {
        int b=0;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]<indice[b])
          {
              b=i;
          }
        }
        return b;
    }
             private double Obtener_Menor1(Double[] indice)
       {
        int b=0;
        double valor1;
        for(int i=0;i<indice.length;i++)
        {
          if(indice[i]<indice[b])
          {
              b=i;
          }
        }
        valor1 = indice[b];
        return Math.abs(valor1);
    }
}
