

package biblioteca;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author JONATHAN
 */
public class Lector_nuevo extends javax.swing.JFrame {
            String contraseña;
        String usuario;
        String url="";
        Connection conexion=null;
        String Estado;
    /** Creates new form Lector_nuevo */
    public Lector_nuevo(String texto,String texto1,String user,String pass,String urll) {
        usuario=user;
        contraseña = pass;
        url=urll;
        Estado=texto;
        setLocation(200,250);
        initComponents();
        setTitle(texto1);
        if(texto.equals("Buscar"))
        {
            btnAceptar.setVisible(false);
            btnEliminar.setVisible(false);
            btnEditar.setVisible(false);
        }
        else if(texto.equals("Eliminar"))
        {
            btnAceptar.setVisible(false);
            btnBuscar.setVisible(false);
            btnEditar.setVisible(false);
            txtApellidos.setEnabled(false);
            txtCorreo.setEnabled(false);
            txtDireccion.setEnabled(false);
            txtLocalidad.setEnabled(false);
            txtNace.setEnabled(false);
            txtNombre.setEnabled(false);
            txtProvincia.setEnabled(false);
            txtTelefono.setEnabled(false);

        }
        else if(texto.equals(""))
        {
            btnEliminar.setVisible(false);
            btnBuscar.setVisible(false);
            btnEditar.setVisible(false);
        }
        else if(texto.equals("Editar"))
        {
            btnEliminar.setVisible(false);
            btnAceptar.setVisible(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        lblDNI = new javax.swing.JLabel();
        lblNombres = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblTipo = new javax.swing.JLabel();
        lblProvincia = new javax.swing.JLabel();
        lblLocalidad = new javax.swing.JLabel();
        lblApellidos = new javax.swing.JLabel();
        txtDNI = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtLocalidad = new javax.swing.JTextField();
        txtProvincia = new javax.swing.JTextField();
        ComboxTipo = new javax.swing.JComboBox();
        lblTelefono = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        lblNace = new javax.swing.JLabel();
        txtNace = new javax.swing.JTextField();
        lblcorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblCodigo = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo Lector");

        lblDNI.setText("DNI");

        lblNombres.setText("Nombres");

        lblDireccion.setText("Direccion");

        lblTipo.setText("Tipo");

        lblProvincia.setText("Provincia");

        lblLocalidad.setText("Localidad");

        lblApellidos.setText("Apellidos");

        ComboxTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Alumno", "Profesor" }));

        lblTelefono.setText("Telefono");

        lblNace.setText("Año de Nacimiento");

        lblcorreo.setText("Correo Electronico");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblCodigo.setText("Codigo");

        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblProvincia)
                    .addComponent(lblLocalidad)
                    .addComponent(lblDireccion)
                    .addComponent(lblTipo)
                    .addComponent(lblDNI)
                    .addComponent(lblCodigo)
                    .addComponent(lblApellidos)
                    .addComponent(lblNombres))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtNombre, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtProvincia)
                                .addComponent(txtLocalidad)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEliminar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAceptar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCancelar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblcorreo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(lblNace)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtNace))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(lblTelefono)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDNI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDNI))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCodigo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblApellidos)
                                .addGap(12, 12, 12)
                                .addComponent(lblNombres)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblTipo)
                                .addGap(12, 12, 12))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lblTelefono)
                                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtNace, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lblNace)))
                                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(ComboxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDireccion)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblLocalidad)
                            .addComponent(txtLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblProvincia)
                            .addComponent(txtProvincia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblcorreo)
                            .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(75, 75, 75)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnAceptar)
                            .addComponent(btnBuscar)
                            .addComponent(btnEliminar)
                            .addComponent(btnEditar))))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       Envio();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Editar();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        Buscar();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       Eliminar();
    }//GEN-LAST:event_btnEliminarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ComboxTipo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblApellidos;
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblDNI;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblLocalidad;
    private javax.swing.JLabel lblNace;
    private javax.swing.JLabel lblNombres;
    private javax.swing.JLabel lblProvincia;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTipo;
    private javax.swing.JLabel lblcorreo;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtDNI;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtLocalidad;
    private javax.swing.JTextField txtNace;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtProvincia;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public void Envio()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if(conexion!=null)
         {
          Statement st = conexion.createStatement();
          
          st.executeUpdate("INSERT INTO usuarios (Codigo,DNI,Apellidos,Nombres,Telefono,Anio_Nacimiento,Tipo,Direccion,Email,Localidad,Provincia) VALUES ('"+txtCodigo.getText()+"','"+txtDNI.getText()+"','"+txtApellidos.getText()+"','"+txtNombre.getText()+"','"+txtTelefono.getText()+"','"+txtNace.getText()+"','"+ComboxTipo.getSelectedItem()+"','"+txtDireccion.getText()+"','"+txtCorreo.getText()+"','"+txtLocalidad.getText()+"','"+txtProvincia.getText()+"')");
          ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");
          while(resp.next())
          {
            //System.out.println(resp.getObject("Nombres"));
          }
          st.close();
         }
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");
        }
         catch(ClassNotFoundException e)
         {
        JOptionPane.showMessageDialog(null,e);
        }

    }

    public void Buscar()
    {
               try
        {
          Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if(conexion!=null)
         {

          Statement st = conexion.createStatement();
          ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");        

          if(txtDNI.getText().length()>0)
          {
              resp= st.executeQuery("select *from usuarios where (DNI='"+txtDNI.getText()+"');");
          }
          else if(txtCodigo.getText().length()>0)
          {
              resp= st.executeQuery("select *from usuarios where (Codigo='"+txtCodigo.getText()+"');");
          }
          while(resp.next())
          {
           txtApellidos.setText(String.valueOf(resp.getObject("Apellidos")));
           txtNombre.setText(String.valueOf(resp.getObject("Nombres")));
           txtCorreo.setText(String.valueOf(resp.getObject("Email")));
           txtDireccion.setText(String.valueOf(resp.getObject("Direccion")));
           txtDNI.setText(String.valueOf(resp.getObject("DNI")));
           txtLocalidad.setText(String.valueOf(resp.getObject("Localidad")));
           txtNace.setText(String.valueOf(resp.getObject("Anio_Nacimiento")));
           txtProvincia.setText(String.valueOf(resp.getObject("Provincia")));
           txtTelefono.setText(String.valueOf(resp.getObject("Telefono")));
           ComboxTipo.setSelectedItem(String.valueOf(resp.getObject("Tipo")));
          }
          resp.close();
          st.close();  


         }
         conexion.close();
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");
        }
         catch(ClassNotFoundException e)
         {
        JOptionPane.showMessageDialog(null,e);
        }
    }

    private void Editar()
    {
        try
        {
          Class.forName("com.mysql.jdbc.Driver");
          conexion = DriverManager.getConnection(url,usuario,contraseña);
         if(conexion!=null)
         {
          Statement st = conexion.createStatement();
          ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");
          st.executeUpdate("DELETE FROM usuarios WHERE (DNI='"+txtDNI.getText()+"')");
          st.executeUpdate("INSERT INTO usuarios (Codigo,DNI,Apellidos,Nombres,Telefono,Anio_Nacimiento,Tipo,Direccion,Email,Localidad,Provincia) VALUES ('"+txtCodigo.getText()+"','"+txtDNI.getText()+"','"+txtApellidos.getText()+"','"+txtNombre.getText()+"','"+txtTelefono.getText()+"','"+txtNace.getText()+"','"+ComboxTipo.getSelectedItem()+"','"+txtDireccion.getText()+"','"+txtCorreo.getText()+"','"+txtLocalidad.getText()+"','"+txtProvincia.getText()+"')");

          st.close();
         }
          conexion.close();

        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");
        }catch (ClassNotFoundException e) {
      System.out.println("Clase no encontrada");
    }

    }

    public void Eliminar()
{    try
        {
          Class.forName("com.mysql.jdbc.Driver");
         conexion = DriverManager.getConnection(url,usuario,contraseña);
         if(conexion!=null)
         {
          Statement st = conexion.createStatement();
          ResultSet resp  = st.executeQuery("SELECT * FROM usuarios");
if(txtCodigo.getText().length()>0)
{   st.executeUpdate("DELETE FROM usuarios WHERE Codigo like '"+txtCodigo.getText()+"'");
         //System.out.println("Eliminado");
         st.close();
}
else if(txtDNI.getText().length()>0)
 {   st.executeUpdate("DELETE FROM usuarios WHERE DNI like '"+txtDNI.getText()+"'");
         //System.out.println("Eliminado");
         st.close();
}
else {
JOptionPane.showMessageDialog(null,"no se ingresaron los datos");}
         }
         conexion.close();
        }
        catch(SQLException ex){
        JOptionPane.showMessageDialog(null,"Error");
        }
         catch(ClassNotFoundException e)
         {
        JOptionPane.showMessageDialog(null,e);
        }

}

}
