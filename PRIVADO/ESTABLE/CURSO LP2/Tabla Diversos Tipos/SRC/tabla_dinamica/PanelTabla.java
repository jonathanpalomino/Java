
package tabla_dinamica;
/**
 *
 * @author JONATHAN
 */
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PanelTabla extends JFrame
{
    private String[] cabecera= {"Codigo","Nombre y Apellidos","Examen parcial","Examen final","Promedio de Practicas","Promedio final"};
    private String[][] data = {};
    private DefaultTableModel miModelo;
    private JScrollPane jScrollPane1;
    private JTable miTabla;
    public PanelTabla() 
    {
        setSize(700,400);
        setLocation(250,200);
        IniciarComponentes();
     	miModelo = new DefaultTableModel(data,cabecera);
	miTabla.setModel(miModelo);
    }

    public JTable getMiTabla(){ return miTabla;}
    public DefaultTableModel getMiModelo(){ return miModelo;}


    private void IniciarComponentes() {

        jScrollPane1 = new JScrollPane();
        miTabla = new JTable();

        setLayout(new BorderLayout());

        miTabla.setModel(new DefaultTableModel(
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

        add(jScrollPane1, BorderLayout.CENTER);
    }

}

