package menubar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends javax.swing.JPanel {
	private String[] cabecera= {"Codigo", "Apellidos","Nombres","Cargo","Sueldo"};
	private String[][] data = {};

	private DefaultTableModel miModelo;

    /** Creates new form PanelTabla */
    public PanelTabla() {
        initComponents();
     	miModelo = new DefaultTableModel(data,cabecera);
	miTabla.setModel(miModelo);
    }

    public JTable getMiTabla(){ return miTabla;}
    public DefaultTableModel getMiModelo(){ return miModelo;}

    @SuppressWarnings("unchecked")
   
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        miTabla = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        miTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(miTabla);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable miTabla;

}
