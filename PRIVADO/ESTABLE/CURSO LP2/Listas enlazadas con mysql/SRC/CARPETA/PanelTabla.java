
package carpeta;
/**
 *
 * @author JONATHAN1
 */
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JPanel {
private String[] cabecera= {"Descripcion", "Codigo de venta","Codigo de producto","Precio","Cantidad","Categoria","Total"};
private String[][] data = {};
private DefaultTableModel miModelo;
private JScrollPane Panel;
private JTable miTabla;

    public PanelTabla() {
        Panel = new JScrollPane();
        miTabla = new JTable();
        setLayout(new BorderLayout());
        Panel.setViewportView(miTabla);
        add(Panel, BorderLayout.CENTER);
     	miModelo = new DefaultTableModel(data,cabecera);
	miTabla.setModel(miModelo);
    }

    public JTable getMiTabla(){ return miTabla;}
    public DefaultTableModel getMiModelo(){ return miModelo;}

}

