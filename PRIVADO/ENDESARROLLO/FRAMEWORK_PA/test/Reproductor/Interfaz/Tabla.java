package Reproductor.Interfaz;

import Reproductor.Librerias.Cargar_audio;
import Reproductor.Librerias.Libreria_Tabla;
import java.io.File;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import palomino.herramientas.variables_entorno.Archivo_Jalar_Pegar;
import palomino.herramientas.variables_entorno.Archivo_Jalar_Pegar.Ejecutador;

/**
 *
 * @author JONATHAN
 */
public class Tabla extends javax.swing.JFrame{

    private static DefaultTableModel miModelo;
    public String[][] data = {};
    public int Contador_de_celda = 0;
    Object rutaTabla;
    private Libreria_Tabla Libreria_tab;

    /** Creates new form Tabla */
    public Tabla() {
        initComponents();
        setLocation(400, 400);
        setSize(445, 180);
        miModelo = new DefaultTableModel(data, new String[]{"Lista de Reproduccion", "Ruta"}){

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        Libreria_tab=Libreria_Tabla.getClaseMaestra().getLib_tabla();
        Libreria_tab.setTabla_Datos(Tabla,miModelo);
        Libreria_tab.setExtension(Libreria_Tabla.getClaseMaestra().getPropiedades_conf().extencion_archivo);
        //Crear_Submenus();
        new Archivo_Jalar_Pegar(this, new Ejecutador(){

            public void filesDropped(File[] files) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        if (files[i].isFile())//Verificar que es un archivo y no una carpeta
                        {
                            String[] extencion_lista = {".rep", ".m3u"};
                            if (getLibreria_tab().Validaciones(files[i], getLibreria_tab().getExtension())) {
                                getLibreria_tab().Enviar(files[i].getName(), new File(files[i].getPath()));
                            }
                            else if (getLibreria_tab().Validaciones(files[i], extencion_lista)) {
                                getLibreria_tab().Traer_Lista(files[i]);
                            }
                        }

                    } // end try
                    catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Menu_Opciones = new javax.swing.JPopupMenu();
        Repro = new javax.swing.JMenuItem();
        Informacion = new javax.swing.JMenuItem();
        Eliminar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        //Repro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/3.JPG"))); // NOI18N
        Repro.setText("Reproducir");
        Repro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReproActionPerformed(evt);
            }
        });
        Menu_Opciones.add(Repro);

        Informacion.setText("Informacion");
        Informacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InformacionActionPerformed(evt);
            }
        });
        Menu_Opciones.add(Informacion);

        Eliminar.setText("Eliminar");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });
        Menu_Opciones.add(Eliminar);

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
        Tabla.setComponentPopupMenu(Menu_Opciones);
        Tabla.setDragEnabled(true);
        Tabla.setDropMode(javax.swing.DropMode.ON_OR_INSERT_ROWS);
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMousePressed
        try {
            Contador_de_celda = Libreria_Tabla.getMiTabla().getSelectedRow();
            //System.out.println("orden " + Contador_de_celda);//Indica el lugar de la fila seleccionada el Contador_de_celda
            rutaTabla = Libreria_Tabla.getMiTabla().getValueAt(Contador_de_celda, 1);
            //System.out.println("Ruta " + rutaTabla);//Nombre de la celda
            //String uno=getMiTabla().getColumnName(Contador_de_celda);//Entrega nombre d columna
            //System.out.println("aparecio "+uno);
            if (evt.getClickCount() == 2) {
                Cargar_audio.Pista = Contador_de_celda;
                getLibreria_tab().Reproduce(rutaTabla);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(this, "Elemento no seleccionado");
        }
}//GEN-LAST:event_TablaMousePressed

    private void ReproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReproActionPerformed
        getLibreria_tab().Reproduce(rutaTabla);
    }//GEN-LAST:event_ReproActionPerformed

    private void InformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InformacionActionPerformed
       Libreria_Tabla.getClaseMaestra().Mostrar_info();
    }//GEN-LAST:event_InformacionActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        Libreria_Tabla.getClaseMaestra().Eliminar_Item();
    }//GEN-LAST:event_EliminarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Eliminar;
    private javax.swing.JMenuItem Informacion;
    private javax.swing.JPopupMenu Menu_Opciones;
    private javax.swing.JMenuItem Repro;
    public static javax.swing.JTable Tabla;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the Libreria_tab
     */
    public Libreria_Tabla getLibreria_tab() {
        return Libreria_tab;
    }

    /**
     * @param Libreria_tab the Libreria_tab to set
     */
    public void setLibreria_tab(Libreria_Tabla Libreria_tab) {
        this.Libreria_tab = Libreria_tab;
    }
}