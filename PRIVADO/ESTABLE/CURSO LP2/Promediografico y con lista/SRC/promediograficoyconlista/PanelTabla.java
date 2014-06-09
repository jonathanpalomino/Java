package promediograficoyconlista;
/**
 *
 * @author JONATHAN1
 */
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends javax.swing.JPanel {
	private String[] cabecera= {"Descripcion", "Codigo de venta","Codigo de producto","Precio","Cantidad","Categoria","Total"};
	private String[][] datos = {};
	
	private DefaultTableModel Modelo;

    /** Creates new form PanelTabla */
    public PanelTabla() {
        initComponents();
     	Modelo = new DefaultTableModel(datos,cabecera);
	Tabla.setModel(Modelo);
    }

    public JTable getMiTabla(){ return Tabla;}
    public DefaultTableModel getMiModelo(){ return Modelo;}
    
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

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

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable Tabla;

}
