/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Consualta.java
 *
 * Created on 15/01/2011, 10:15:45 PM
 */
package registrador;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

/**
 *
 * @author jonathan
 */
public class Consulta extends javax.swing.JFrame{

    Connection enlace;
    Statement st = null;
    ResultSet resp = null;
    String url_consulta = "";
    DefaultListModel modelo = new DefaultListModel();
    int indice_planeta = 0;
    String direccion_planeta = "";

    /** Creates new form Consualta */
    Consulta( Libreria obje) {
        enlace = obje.getEnlace();
        initComponents();
        listDirecciones.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    public void Cambio_de_Elemento() {
        try {
            int indice = PanelPrincipal.getSelectedIndex();
            //System.out.println(PanelPrincipal.getSelectedIndex()); //Retorna el indice de pestaña
            LIMPIAR_PANELES();
            if (enlace != null) {
                try {
                    if (indice == 0) {
                        //Panel tropas
                        url_consulta = "SELECT nomb_tropas, cantidad_trop"
                                + " FROM usuario_numero_tropas"
                                + " WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "') "
                                + "AND (numero_Direccion = '" + direccion_planeta + "')";
                        st = enlace.createStatement();
                        resp = st.executeQuery(url_consulta);
                        ArrayList lista = new ArrayList();
                        ArrayList numero = new ArrayList();
                        while (resp.next()) {
                            //System.out.println(resp.getObject(1) + " " + resp.getObject(2));
                            lista.add(resp.getObject(1));
                            numero.add(resp.getObject(2));
                        }
                        int espaciado = (int) (Panel_tropas.getHeight() / lista.size()) + 1;
                        Crear_Objetos(espaciado, Panel_tropas, lista, numero);
                    }
                    else if (indice == 1) {
                        //Panel naves
                        url_consulta = "SELECT Nomb_Naves, cantidad_nav"
                                + " FROM usuario_numero_naves"
                                + " WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "') "
                                + "AND (numero_Direccion = '" + direccion_planeta + "')";
                        st = enlace.createStatement();
                        resp = st.executeQuery(url_consulta);
                        ArrayList lista = new ArrayList();
                        ArrayList numero = new ArrayList();
                        while (resp.next()) {
                            // System.out.println(resp.getObject(1) + " " + resp.getObject(2));
                            lista.add(resp.getObject(1));
                            numero.add(resp.getObject(2));
                        }
                        int espaciado = (int) (Panel_naves.getHeight() / lista.size()) + 1;
                        Crear_Objetos(espaciado, Panel_naves, lista, numero);
                    }
                    else {
                        //Panel defensas
                        url_consulta = "SELECT nomb_Defensas, cantidad_def FROM usuario_numero_Defensas "
                                + "WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "') "
                                + "AND (numero_Direccion = '" + direccion_planeta + "')";
                        st = enlace.createStatement();
                        resp = st.executeQuery(url_consulta);
                        ArrayList lista = new ArrayList();
                        ArrayList numero = new ArrayList();
                        while (resp.next()) {
                            //System.out.println(resp.getObject(1) + " " + resp.getObject(2));
                            lista.add(resp.getObject(1));
                            numero.add(resp.getObject(2));
                        }
                        int espaciado = (int) (Panel_defensas.getHeight() / lista.size()) + 1;
                        Crear_Objetos(espaciado, Panel_defensas, lista, numero);
                    }
                }
                catch (SQLException ex) {
                }
            }
        }
        catch (Exception e) {
            //Evita que autoejecute el cambio teniendo valores nulos inicialmente
        }
    }

    public void Ejecucion() {
        boolean encontrado = false;
        if (enlace != null) {
            try {
                url_consulta = "SELECT usuarios.nomb_usuarios, Razas.nomb_raza FROM usuarios "
                        + "INNER JOIN Razas ON usuarios.codi_raza = Razas.codi_raza where"
                        + " usuarios.nomb_usuarios = '" + txtUser.getText().trim() + "'";
                st = enlace.createStatement();
                resp = st.executeQuery(url_consulta);
                while (resp.next()) {
                    // El metodo getObject llama a las columnas
                    //ya sea por su nombre o su indice de columna en este caso comensando en 1
                    txtRaza.setText(resp.getObject(2).toString());
                    if (resp.getObject(1).toString().equals(txtUser.getText().trim())) {
                        encontrado = true;
                    }
                }
                if (encontrado) {
                    url_consulta = "SELECT Numero_direccion, Galaxia,nomb_usuarios"
                            + " FROM Direcciones where nomb_usuarios = '" + txtUser.getText().trim() + "'";
                    resp = st.executeQuery(url_consulta);
                    while (resp.next()) {
                        Agregar_Lista(resp.getObject(1).toString());
                    }
                    listDirecciones.setModel(modelo);
                    Menu_Eliminar.setEnabled(true); //Existe el elemento entonces se puede eliminar
                }
                else {
                    JOptionPane.showMessageDialog(null, "No se hallo la consulta", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            catch (SQLException ex) {
            }
        }

    }

    public void Limpieza() {
        txtUser.setText("");
        txtRaza.setText("");
        modelo.clear();
        listDirecciones.setModel(modelo);
        listDirecciones.clearSelection();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtUser = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listDirecciones = new javax.swing.JList();
        txtRaza = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        PanelPrincipal = new javax.swing.JTabbedPane();
        Panel_tropas = new javax.swing.JPanel();
        Panel_naves = new javax.swing.JPanel();
        Panel_defensas = new javax.swing.JPanel();
        btnConsulta = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Menu_Eliminar = new javax.swing.JMenuItem();
        Menu_SAlir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultar usuarios");

        jLabel1.setText("Usuario");

        jLabel3.setText("Direcciones");

        jLabel2.setText("Raza");

        listDirecciones.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listDireccionesValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(listDirecciones);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        PanelPrincipal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                PanelPrincipalStateChanged(evt);
            }
        });

        Panel_tropas.setPreferredSize(new java.awt.Dimension(445, 378));

        javax.swing.GroupLayout Panel_tropasLayout = new javax.swing.GroupLayout(Panel_tropas);
        Panel_tropas.setLayout(Panel_tropasLayout);
        Panel_tropasLayout.setHorizontalGroup(
            Panel_tropasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        Panel_tropasLayout.setVerticalGroup(
            Panel_tropasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        PanelPrincipal.addTab("Tropas", Panel_tropas);

        javax.swing.GroupLayout Panel_navesLayout = new javax.swing.GroupLayout(Panel_naves);
        Panel_naves.setLayout(Panel_navesLayout);
        Panel_navesLayout.setHorizontalGroup(
            Panel_navesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        Panel_navesLayout.setVerticalGroup(
            Panel_navesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        PanelPrincipal.addTab("Naves", Panel_naves);

        javax.swing.GroupLayout Panel_defensasLayout = new javax.swing.GroupLayout(Panel_defensas);
        Panel_defensas.setLayout(Panel_defensasLayout);
        Panel_defensasLayout.setHorizontalGroup(
            Panel_defensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        Panel_defensasLayout.setVerticalGroup(
            Panel_defensasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );

        PanelPrincipal.addTab("Defensas", Panel_defensas);

        btnConsulta.setText("Consultar");
        btnConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaActionPerformed(evt);
            }
        });

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        jMenu1.setText("Otras opciones");

        Menu_Eliminar.setText("Eliminar usuario y sus datos");
        Menu_Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_EliminarActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_Eliminar);

        Menu_SAlir.setText("Salir");
        Menu_SAlir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Menu_SAlirActionPerformed(evt);
            }
        });
        jMenu1.add(Menu_SAlir);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(PanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(btnAceptar))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnConsulta)
                                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(72, 72, 72))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAceptar)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnConsulta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiar)))
                .addGap(18, 18, 18)
                .addComponent(PanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaActionPerformed
        //LIMPIAR_PANELES();
        //Limpieza();
        modelo.clear();
        listDirecciones.setModel(modelo);
        listDirecciones.clearSelection();
        Ejecucion();
    }//GEN-LAST:event_btnConsultaActionPerformed

    private void PanelPrincipalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_PanelPrincipalStateChanged
        Cambio_de_Elemento();

    }//GEN-LAST:event_PanelPrincipalStateChanged

    private void listDireccionesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listDireccionesValueChanged
        indice_planeta = listDirecciones.getSelectedIndex();
        //System.out.println(indice_planeta);
        //System.out.println("Planeta seleccionado es " + modelo.getElementAt(indice_planeta));
        try {
            direccion_planeta = modelo.getElementAt(indice_planeta).toString();
            Cambio_de_Elemento();
        }
        catch (Exception e) //evita exepcion al dar al boton limpiar
        {
        }
    }//GEN-LAST:event_listDireccionesValueChanged

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Limpieza();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void Menu_EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_EliminarActionPerformed
        Eliminar();
        Limpieza();
    }//GEN-LAST:event_Menu_EliminarActionPerformed

    private void Menu_SAlirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Menu_SAlirActionPerformed
        dispose();
    }//GEN-LAST:event_Menu_SAlirActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Menu_Eliminar;
    private javax.swing.JMenuItem Menu_SAlir;
    private javax.swing.JTabbedPane PanelPrincipal;
    private javax.swing.JPanel Panel_defensas;
    private javax.swing.JPanel Panel_naves;
    private javax.swing.JPanel Panel_tropas;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnConsulta;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listDirecciones;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables

    private void Agregar_Lista(String direccion) {
        //System.out.println(direccion);
        modelo.addElement(direccion);
    }

    private void Crear_Objetos(int espaciado, JPanel panel_padre, ArrayList lista, ArrayList numero) {
        JLabel[] etiketa = new JLabel[lista.size()];
        JTextField[] objetos_texto = new JTextField[espaciado];
        for (int i = 0; i < lista.size(); i++) {
            etiketa[i] = new JLabel(lista.get(i).toString());
            panel_padre.add(etiketa[i]);
            objetos_texto[i] = new JTextField("");
            panel_padre.add(objetos_texto[i]);
            objetos_texto[i].setText(numero.get(i).toString());
            objetos_texto[i].setBounds(300, 5 + espaciado * i, 50, 20);
            etiketa[i].setBounds(20, 5 + espaciado * i, 150, 20);
        }
    }

    private void LIMPIAR_PANELES() {
        Panel_tropas.removeAll();
        Panel_tropas.repaint();
        Panel_naves.removeAll();
        Panel_naves.repaint();
        Panel_defensas.removeAll();
        Panel_defensas.repaint();
    }

    private void Eliminar() {
        if (enlace != null) {
            try {
                url_consulta = "DELETE FROM usuario_numero_naves WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "')";
                st = enlace.createStatement();
                st.execute(url_consulta);

                url_consulta = "DELETE FROM usuario_numero_tropas WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "')";
                st.execute(url_consulta);

                url_consulta = "DELETE FROM usuario_numero_Defensas WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "')";
                st.execute(url_consulta);

                url_consulta = "DELETE FROM Direcciones WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "')";
                st.execute(url_consulta);

                url_consulta = "DELETE FROM usuarios WHERE (nomb_usuarios = '" + txtUser.getText().trim() + "')";
                st.execute(url_consulta);
            }
            catch (SQLException ex) {
            }
        }
    }
}
