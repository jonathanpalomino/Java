
package tabla_Simple;

import java.awt.event.*;
import javax.swing.*;

public class Portada  extends JFrame
{

JLabel lblcodigo = new JLabel("Codigo");
JLabel lblApynom = new JLabel("Apellidos y nombres");
JLabel lblexpar = new JLabel("Examen parcial");
JLabel lblexfin = new JLabel("Examen final");
JLabel lblprompp = new JLabel("Promedio de practicas");
JLabel Promfin = new JLabel("Promedio final");

JTextField txtcodigo = new JTextField();
JTextField txtApynom = new JTextField();
JTextField txtPromfin = new JTextField();

JComboBox combexpar = new JComboBox();
JComboBox combexfinr = new JComboBox();
JComboBox combprompp = new JComboBox();

JButton btnAceptar = new JButton("Aceptar");
JButton btnCancelar = new JButton("Cancelar");
JButton btnMostrar = new JButton("Mostrar");
JLabel vacio = new JLabel("");

private PanelTabla	panTabla;
public Portada()
{
setSize(450,500);
setTitle("Programa");
setLocation(250,150);

lblcodigo.setBounds(50,50, 100, 20);
txtcodigo.setBounds(210,50, 100, 20);
add(lblcodigo);
add(txtcodigo);

lblApynom.setBounds(50,90, 120, 20);
txtApynom.setBounds(210,90, 100, 20);
add(lblApynom);
add(txtApynom);

lblexpar.setBounds(50,130, 100, 20);
combexpar.setBounds(210,130, 100, 20);

combexpar.addItem("1");
combexpar.addItem("2");
combexpar.addItem("3");
combexpar.addItem("4");
combexpar.addItem("5");
combexpar.addItem("6");
combexpar.addItem("7");
combexpar.addItem("8");
combexpar.addItem("9");
combexpar.addItem("10");
combexpar.addItem("11");
combexpar.addItem("12");
combexpar.addItem("13");
combexpar.addItem("14");
combexpar.addItem("15");
combexpar.addItem("16");
combexpar.addItem("17");
combexpar.addItem("18");
combexpar.addItem("19");
combexpar.addItem("20");

add(lblexpar);
add(combexpar);

lblexfin.setBounds(50,170, 100, 20);
combexfinr.setBounds(210,170, 100, 20);
combexfinr.addItem("1");
combexfinr.addItem("2");
combexfinr.addItem("3");
combexfinr.addItem("4");
combexfinr.addItem("5");
combexfinr.addItem("6");
combexfinr.addItem("7");
combexfinr.addItem("8");
combexfinr.addItem("9");
combexfinr.addItem("10");
combexfinr.addItem("11");
combexfinr.addItem("12");
combexfinr.addItem("13");
combexfinr.addItem("14");
combexfinr.addItem("15");
combexfinr.addItem("16");
combexfinr.addItem("17");
combexfinr.addItem("18");
combexfinr.addItem("19");
combexfinr.addItem("20");
add(lblexfin);
add(combexfinr);

lblprompp.setBounds(50,210, 140, 20);
combprompp.setBounds(210,210, 100, 20);
combprompp.addItem("1");
combprompp.addItem("2");
combprompp.addItem("3");
combprompp.addItem("4");
combprompp.addItem("5");
combprompp.addItem("6");
combprompp.addItem("7");
combprompp.addItem("8");
combprompp.addItem("9");
combprompp.addItem("10");
combprompp.addItem("11");
combprompp.addItem("12");
combprompp.addItem("13");
combprompp.addItem("14");
combprompp.addItem("15");
combprompp.addItem("16");
combprompp.addItem("17");
combprompp.addItem("18");
combprompp.addItem("19");
combprompp.addItem("20");

add(lblprompp);
add(combprompp);

Promfin.setBounds(50,250, 100, 20);
txtPromfin.setBounds(210,250, 100, 20);
add(Promfin);
add(txtPromfin);
panTabla = new PanelTabla();

btnAceptar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) 
            
            {
                if(txtcodigo.getText().equals("")||txtApynom.getText().equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Error de Ingreso", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
                }
                else{
            int expar1 =Integer.parseInt(String.valueOf(combexpar.getSelectedItem()));
            int exfin1 =Integer.parseInt(String.valueOf(combexfinr.getSelectedItem()));
            int prompp1 =Integer.parseInt(String.valueOf(combprompp.getSelectedItem()));
            txtPromfin.setText(String.valueOf((exfin1+expar1+prompp1)/3.0));
           Object [] dato ={txtcodigo.getText(),txtApynom.getText(),combexpar.getSelectedItem(),combexfinr.getSelectedItem(),combprompp.getSelectedItem(),txtPromfin.getText()};
           
           panTabla.getMiModelo().addRow(dato);

           //Limpia contenido
           txtcodigo.setText("");
           txtApynom.setText("");
           txtPromfin.setText("");
                }
            }
        });
     btnCancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            dispose();
            }
        });

     btnMostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                panTabla.setVisible(true);
            }
        });

btnAceptar.setBounds(70,310,100,20);
btnCancelar.setBounds(210,310,100,20);
btnMostrar.setBounds(70,340,100,20);
add(btnCancelar);
add(btnAceptar);
add(btnMostrar);
add(vacio);
}

    public static void main(String[] args)
    {
      Portada obj = new Portada();
      obj.show();
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
