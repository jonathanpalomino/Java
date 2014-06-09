/*
 * REALIZAR EL PROGRAMA QUE INGRESE A UNA PILA DINAMICA POR MEDIO DE UN FORMULARIO
 * LOS DATOS DE N TRIANGULOS : CODIGO ,BASE,ALTURA Y TIPO DE TRIANGULO SE PIDE CALCULAR E
 * IMPRIMIR EL AREA DE CADA TRIANGULO ASI COMO EL PROMEDIO DEL AREA POR CADA TIPO
 * DE TRIANGULO. APLICAR INTERFACE
 *
 */

package problema1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame
{
    JLabel lblcodigo = new JLabel("Codigo");
    JLabel lblbase = new JLabel("Base");
    JLabel lblAltura = new JLabel("Altura");
    JLabel vacio = new JLabel();
    JComboBox Tipo = new JComboBox();
    JButton Calcular = new JButton("Calcular");
    JButton Limpiar = new JButton("Limpiar");
    JButton Mostrar = new JButton("Mostrar");
    JTextArea txtSalida = new JTextArea();
    JTextField txtCodigo = new JTextField();
    JTextField txtBase = new JTextField();
    JTextField txtAltura = new JTextField();
    String acumulador ="";

    static class Pila
   {
      String Tipo,codigo;
      double altura,base,area;
      Pila anterior;
   }
    Pila ultimo = null;
    Pila nuevo = null;
    public Main()
    {
    setTitle("Triangulo");
    setSize(500,500);
    Tipo.addItem("Rectangulo");
    Tipo.addItem("Isosceles");
    Tipo.addItem("Obstuso");

    lblcodigo.setBounds(50,50, 100, 20);
    lblAltura.setBounds(50, 90,100,20);
    lblbase.setBounds(50, 130,100,20);
    txtAltura.setBounds(160, 90,100,20);
    txtBase.setBounds(160, 130, 100, 20);
    txtCodigo.setBounds(160,50,100,20);
    txtSalida.setBounds(50, 250,400,200);
    Calcular.setBounds(50, 180,100,20);
    Limpiar.setBounds(290, 180, 100,20);
    Mostrar.setBounds(290, 220, 100,20);
    Tipo.setBounds(290,130, 100,20);
    add(lblcodigo);
    add(lblAltura);
    add(lblbase);
    add(txtAltura);
    add(txtBase);
    add(txtCodigo);
    add(txtSalida);
    add(Tipo);
    add(Calcular);
    add(Limpiar);
    add(Mostrar);
    add(vacio);

    Calcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                try
                {
            nuevo = new Pila();
            nuevo.Tipo = (String) Tipo.getSelectedItem();
            nuevo.altura = Double.parseDouble(txtAltura.getText());
            nuevo.base = Double.parseDouble(txtBase.getText());
            nuevo.codigo = txtCodigo.getText();
            nuevo.area = Double.parseDouble(txtBase.getText())*Double.parseDouble(txtAltura.getText());
            nuevo.anterior=ultimo;
            ultimo=nuevo;
            txtAltura.setText("");
            txtBase.setText("");
            txtCodigo.setText("");
                }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Error de Ingreso");
                Limpiar();
            }
            }

        });

    Limpiar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            Limpiar();
            }
        });

    Mostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                Limpiar();
                while(ultimo!=null)
                {
                    if(ultimo.codigo!=null)
                    {
                    acumulador = acumulador+(ultimo.codigo+"\t"+ultimo.altura+"\t"+ultimo.base+"\t"+ultimo.Tipo+"\t"+ultimo.area+"\n");
                    }
                    ultimo = ultimo.anterior;
                    
                }
                txtSalida.setText(acumulador);
            }
        });
    }

    public static void main(String[] args)
    {
        Main obj = new Main();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.show();
    }
            public void Limpiar()
            {
            txtAltura.setText("");
            txtBase.setText("");
            txtCodigo.setText("");
            txtSalida.setText("");
            }

}
