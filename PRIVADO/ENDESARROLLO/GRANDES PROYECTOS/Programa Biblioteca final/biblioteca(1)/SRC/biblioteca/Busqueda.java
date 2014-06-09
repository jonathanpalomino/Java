

package biblioteca;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author JONATHAN
 */
public class Busqueda extends JFrame {
int cantidad_filas;
String contraseña;
String usuario;
String tipo;
String url="";
Connection conexion=null;
String texto;
public DefaultTableModel miModelo;
public String[][] data = {};
JTextArea area = new JTextArea();
    //Codigo,nombre, apellido, escuela
    public Busqueda(String texto,String Combox1,String Combox2,String Combox3,String Combox4,String Cabecera1,String Cabecera2,String Cabecera3,String Cabecera4,String user,String pass,String urll) {
        usuario=user;
        contraseña=pass;
        url = urll;
        tipo=Combox1;
        this.texto = texto;
        area.setVisible(false);
        initComponents();
        setLocation(250, 200);
        ComboxIndice.setModel(new DefaultComboBoxModel(new String[] {
                Combox1, Combox2, Combox3, Combox4}));
        miModelo = new DefaultTableModel(data,new String [] {
                Cabecera1, Cabecera2, Cabecera3, Cabecera4
            });
            Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        btnMostrar.setVisible(false);
        if(texto.equals("Listar"))
        {
            setTitle("Listado de Usuarios");
            btnMostrar.setVisible(true);
            ComboxIndice.setVisible(false);
            lblIndice.setVisible(false);
            lblTitulo.setVisible(false);
            txtTitulo.setVisible(false);
            btnSeleccionar.setVisible(false);
        }
    }
    public JTable getMiTabla(){ return Tabla;}
    public DefaultTableModel getMiModelo(){ return miModelo;}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblIndice = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        ComboxIndice = new javax.swing.JComboBox();
        txtTitulo = new javax.swing.JTextField();
        btnSeleccionar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        btnMostrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Busqueda");

        lblIndice.setText("Indice");

        lblTitulo.setText("Titulo");

        ComboxIndice.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnSeleccionar.setText("Seleccionar");
        btnSeleccionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarActionPerformed(evt);
            }
        });

        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(Tabla);

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/LUPA.JPG"))); // NOI18N
        jLabel1.setText("..");

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardar)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnSeleccionar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMostrar)
                        .addGap(34, 34, 34)
                        .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 499, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIndice)
                            .addComponent(ComboxIndice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitulo)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIndice)
                            .addComponent(lblTitulo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ComboxIndice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionar)
                    .addComponent(btnSalir)
                    .addComponent(btnMostrar))
                .addGap(7, 7, 7)
                .addComponent(btnGuardar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        //setVisible(false);
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnSeleccionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarActionPerformed

        inicializaTabla();
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
             Statement st = conexion.createStatement();
             ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");
             if(texto.equals("Usuario"))
             {
                 if(tipo.equals("Apellido"))
                 {
                 resp = st.executeQuery("select *from usuarios where(Apellidos<='"+txtTitulo.getText()+"');");
                 while(resp.next())
                 {
                 Object[] dato ={resp.getObject("Codigo"),resp.getObject("Nombres"),resp.getObject("Apellidos"),resp.getObject("Email")};
                 getMiModelo().addRow(dato);
                 }
                 resp.close();
                 }
                 else if(tipo.equals("Codigo"))
                 {
                 resp = st.executeQuery("select *from usuarios where(Codigo<='"+txtTitulo.getText()+"');");
                 while(resp.next())
                 {
                 Object[] dato ={resp.getObject("Codigo"),resp.getObject("Nombres"),resp.getObject("Apellidos"),resp.getObject("Email")};
                 getMiModelo().addRow(dato);
                 }
                 }
             }

             else if(texto.equals("Libro"))
             {
             resp = st.executeQuery("select *from libro where("+ComboxIndice.getSelectedItem()+"<='"+txtTitulo.getText()+"');");
             while(resp.next())
             {
             Object[] dato ={resp.getObject("Codigo"),resp.getObject("Titulo"),resp.getObject("Autor"),resp.getObject("Editorial")};
             getMiModelo().addRow(dato);
             }
             }
                 
             st.close();
            }
            conexion.close();
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");

        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnSeleccionarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
inicializaTabla();
                try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(url,usuario,contraseña);
            if(conexion!=null)
            {
                Statement st = conexion.createStatement();
                ResultSet resp = st.executeQuery("Select *from usuarios");
                while(resp.next())
                {
                    Object[] datos ={resp.getObject("DNI"),resp.getObject("Nombres"),resp.getObject("Apellidos"),resp.getObject("Email")};
                    getMiModelo().addRow(datos);
                }
                st.close();
            }
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");

        }
  catch(ClassNotFoundException e){
        JOptionPane.showMessageDialog(null,e);
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       guardar();
    }//GEN-LAST:event_btnGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboxIndice;
    private javax.swing.JTable Tabla;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSeleccionar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblIndice;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JTextField txtTitulo;
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

 public void guardar()
 {
     String acu="";
     cantidad_filas=getMiModelo().getRowCount();

     for(int h=0;h<cantidad_filas;h++)
     {
         acu = acu+(String)getMiTabla().getValueAt(h, 0)+"   "+(String)getMiTabla().getValueAt(h, 1)+"   "+(String)getMiTabla().getValueAt(h, 2)+"   "+(String)getMiTabla().getValueAt(h, 3)+"\n";

     }
     area.setText(acu);
         	JFileChooser File = new JFileChooser("D:\\");
        File.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int result = File.showSaveDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
    	try {
    		FileOutputStream fos = new FileOutputStream( File.getSelectedFile() );
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
}
