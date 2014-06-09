package biblioteca;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JONATHAN
 */
public class Devoluciones extends javax.swing.JFrame {
int Contador_de_celda=0;
String celdaquerida;
    public String[][] data = {};
String contraseña;
String usuario;
String url="";
String estado;
Connection conexion=null;
    private DefaultTableModel miModelo;
    public Devoluciones(String texto,String user,String pass,String urll)
    {
        usuario=user;
        contraseña = pass;
        url=urll;
        estado=texto;
        initComponents();
        setLocation(250, 200);
        miModelo = new DefaultTableModel(data,new String [] {
                "Codigo", "Titulo", "Autor", "Apellidos", "Codigo Usuario"
            });
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        if(texto.equals("General"))
        {
        setTitle("General");
        lblAutor.setVisible(false);
        lblNombre.setVisible(false);
        txtCampo.setVisible(false);
        lblFF.setVisible(false);
        lblFI.setVisible(false);
        txtFF.setVisible(false);
        txtFI.setVisible(false);
        txtTamaño.setVisible(false);
        lblTamaño.setVisible(false);
        btnBuscar.setVisible(true);
        btnFecha.setVisible(false);
        btnTiempo.setVisible(false);
        btnDevolver.setVisible(true);
        }
        else if(texto.equals("Autor"))
        {
        setTitle("Autor");
        lblNombre.setVisible(false);
        lblFF.setVisible(false);
        lblFI.setVisible(false);
        txtFF.setVisible(false);
        txtFI.setVisible(false);
        txtTamaño.setVisible(false);
        lblTamaño.setVisible(false);
        btnBuscar.setVisible(true);
        btnFecha.setVisible(false);
        btnTiempo.setVisible(false);
        btnDevolver.setVisible(true);
        }
        else if(texto.equals("Usuario"))
        {
        setTitle("Usuario");
        lblAutor.setVisible(false);
        lblFF.setVisible(false);
        lblFI.setVisible(false);
        txtFF.setVisible(false);
        txtFI.setVisible(false);
        txtTamaño.setVisible(false);
        lblTamaño.setVisible(false);
        btnBuscar.setVisible(true);
        btnFecha.setVisible(false);
        btnTiempo.setVisible(false);
        btnDevolver.setVisible(true);
        }
        else if(texto.equals("Totales"))
        {
        setTitle("Totales");

        lblAutor.setVisible(false);
        lblNombre.setVisible(false);
        txtCampo.setVisible(false);
        btnBuscar.setVisible(true);
        btnFecha.setVisible(false);
        btnTiempo.setVisible(false);
        btnDevolver.setVisible(false);
        lblFF.setVisible(false);
        lblFI.setVisible(false);
        txtFF.setVisible(false);
        txtFI.setVisible(false);
        txtTamaño.setVisible(false);
        lblTamaño.setVisible(false);
        }
        else if(texto.equals("Por Fechas"))
        {
        setTitle("Por Fechas");
        lblAutor.setVisible(false);
        lblNombre.setVisible(false);
        lblTamaño.setVisible(false);
        txtTamaño.setVisible(false);
        txtCampo.setVisible(false);
        btnFecha.setVisible(true);
        btnDevolver.setVisible(false);
        btnBuscar.setVisible(false);
        btnTiempo.setVisible(false);
        }
        else if(texto.equals("Por Morosos"))
        {
        setTitle("Por Morosos");
        txtFF.setVisible(false);
        txtFI.setVisible(false);
        lblFF.setVisible(false);
        lblFI.setVisible(false);
        lblAutor.setVisible(false);
        lblNombre.setVisible(false);
        txtCampo.setVisible(false);
        btnTiempo.setVisible(true);
        btnDevolver.setVisible(false);
        btnBuscar.setVisible(false);
        btnFecha.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnBuscar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblAutor = new javax.swing.JLabel();
        txtCampo = new javax.swing.JTextField();
        lblNombre = new javax.swing.JLabel();
        btnDevolver = new javax.swing.JButton();
        lblFI = new javax.swing.JLabel();
        lblFF = new javax.swing.JLabel();
        txtFF = new javax.swing.JTextField();
        txtFI = new javax.swing.JTextField();
        lblTamaño = new javax.swing.JLabel();
        txtTamaño = new javax.swing.JTextField();
        btnFecha = new javax.swing.JButton();
        btnTiempo = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Titulo", "Autor", "Materia", "Usuario", "Codigo Usuario"
            }
        ));
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblAutor.setText("Autor");

        lblNombre.setText("Nombres");

        btnDevolver.setText("Devolver");
        btnDevolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDevolverActionPerformed(evt);
            }
        });

        lblFI.setText("Fecha de Inicio");

        lblFF.setText("Fecha de Fin");

        lblTamaño.setText("Tamaño de fechas");

        btnFecha.setText("Por fechas");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });

        btnTiempo.setText("Tiempo");
        btnTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTiempoActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/raton-de-biblioteca.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btnFecha)
                        .addGap(6, 6, 6)
                        .addComponent(btnTiempo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBuscar)
                        .addGap(30, 30, 30)
                        .addComponent(btnDevolver)
                        .addGap(29, 29, 29)
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(lblNombre)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblAutor, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(320, 320, 320))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblTamaño)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFF)
                                        .addGap(37, 37, 37))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(lblFI)
                                        .addGap(26, 26, 26)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFF)
                                    .addComponent(txtFI, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblAutor)
                            .addComponent(lblNombre))
                        .addGap(37, 37, 37))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFI)
                            .addComponent(txtFI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCampo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblFF)
                            .addComponent(txtFF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTamaño)
                            .addComponent(txtTamaño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnDevolver)
                    .addComponent(btnBuscar)
                    .addComponent(btnFecha)
                    .addComponent(btnTiempo))
                .addGap(31, 31, 31))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTiempoActionPerformed
       BuscarTiempo();
    }//GEN-LAST:event_btnTiempoActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
       BuscarFecha();
    }//GEN-LAST:event_btnFechaActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
       DevolverLibro();
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void TablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMousePressed
        Contador_de_celda=getMiTabla().getSelectedRow();
        System.out.println("un "+Contador_de_celda);//Indica el lugar de la fila seleccionada el Contador_de_celda
        //String uno=getMiTabla().getColumnName(Contador_de_celda);//Entrega nombre d columna
        //System.out.println("aparecio "+uno);
        celdaquerida =(String)getMiTabla().getValueAt(Contador_de_celda, 0);
        System.out.println("buscando celda "+celdaquerida);//Nombre de la celda


    }//GEN-LAST:event_TablaMousePressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnTiempo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAutor;
    private javax.swing.JLabel lblFF;
    private javax.swing.JLabel lblFI;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTamaño;
    private javax.swing.JTextField txtCampo;
    private javax.swing.JTextField txtFF;
    private javax.swing.JTextField txtFI;
    private javax.swing.JTextField txtTamaño;
    // End of variables declaration//GEN-END:variables


        public void inicializaTabla()
    {
        // obtiene numero de filas de la tabla
	int filas = getMiTabla().getRowCount();

	// remueve todas las filas de la tabla
	for (int fila=0; fila<filas; fila++) {
            getMiModelo().removeRow(0);
        }
    }

public JTable getMiTabla(){ return Tabla;}
public DefaultTableModel getMiModelo(){ return miModelo;}

    private void Buscar()
    {
        inicializaTabla();
              try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
                Statement st = conexion.createStatement();
                ResultSet resp = st.executeQuery("select *from registro");
                if(estado.equals("Autor"))
                {
                resp = st.executeQuery("select *from registro where (Autor ='"+txtCampo.getText()+"');");
                  while(resp.next())
                {
                Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                getMiModelo().addRow(datos);
                }
                st.close();
                }
                else if(estado.equals("Usuario"))
                {
                resp = st.executeQuery("select *from registro where (Apellidos ='"+txtCampo.getText()+"');");
                  while(resp.next())
                {
                Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                getMiModelo().addRow(datos);
                }
                st.close();
                }
                else if(txtFI.getText().equals("") && txtFF.getText().equals(""))
                {
                while(resp.next())
                {
                Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                getMiModelo().addRow(datos);
                }
                st.close();
                }

            }
            conexion.close();
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,ex);

        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }

    public void BuscarTiempo()
    {
        inicializaTabla();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
            Statement st = conexion.createStatement();
            ResultSet resp = st.executeQuery("select *from registro");
            if(txtTamaño.getText().length()>0)
                {//Por una cantidad especifica de dias
                    resp=st.executeQuery("select *from registro where Datediff(FCaducidad,FPrestamo)<='"+txtTamaño.getText()+"';");
                while(resp.next())
                    {
                    Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                    getMiModelo().addRow(datos);
                    }
                    st.close();
                }
            }
            conexion.close();
        }
                catch(SQLException ex){
        JOptionPane.showMessageDialog(null,ex);

        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }

    private void BuscarFecha() {
               inicializaTabla();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion= DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
                Statement st = conexion.createStatement();
            ResultSet resp = st.executeQuery("select *from registro");
                if(txtFI.getText().length()>0 &&txtFF.getText().equals(""))
                {
                    resp= st.executeQuery("select *from registro where(FPrestamo>='"+txtFI.getText()+"');");
                    while(resp.next())
                    {
                    Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                    getMiModelo().addRow(datos);
                    }
                    st.close();
                }
                else if(txtFF.getText().length()>0 && txtFI.getText().equals(""))
                {
                    resp= st.executeQuery("select *from registro where (FCaducidad <='"+txtFF.getText()+"');");
                    while(resp.next())
                    {
                    Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                    getMiModelo().addRow(datos);
                    }
                    st.close();
                }
                else
                {
                    resp=st.executeQuery("select *from registro where (FPrestamo>='"+txtFI.getText()+"' and FCaducidad <='"+txtFF.getText()+"');");
                    while(resp.next())
                    {
                    Object[] datos = {resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Apellidos"),resp.getObject("Codigo_Usuario")};
                    getMiModelo().addRow(datos);
                    }
                    st.close();
                }
            }
            conexion.close();
        }
  catch(SQLException ex){
        JOptionPane.showMessageDialog(null,ex);

        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }
int g=0;
    private void DevolverLibro()
    {
      inicializaTabla();
      Buscar();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,contraseña);
            Statement st = conexion.createStatement();
            ResultSet resp = st.executeQuery("select *from registro");
            //si cumple condicio borrar registro
            //¿Como obtener dato de un elemento clikado????????????????
          st.executeUpdate("Delete from registro where(Codigo ='"+celdaquerida+"');");
        }
  catch(SQLException ex){
        JOptionPane.showMessageDialog(null,ex);
        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }

}
