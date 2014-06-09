/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package problema1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main1 extends JFrame
{
    Triangulo triangulo;
    static Pila pila = new Pila();
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

public Main1()
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

        Calcular.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
                {
            ingresoPila();

                }


        });
        Limpiar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            Limpiar();
            }
        });

        Mostrar.addActionListener(new ActionListener()
{
        public void actionPerformed(ActionEvent e)
               {
                mostrarListaTipoCola();
               }
        });
}
    public static void main(String[] args)
    {
        Main1 obj = new Main1();
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
public void ingresoPila()
{
try{
     String codigo;
     double altura;
     double base;
     double area;
            String tipo = String.valueOf(Tipo.getSelectedItem());
            altura = Double.parseDouble(txtAltura.getText());
            base = Double.parseDouble(txtBase.getText());
            codigo = txtCodigo.getText();
            area = Double.parseDouble(txtBase.getText())*Double.parseDouble(txtAltura.getText());
	
        ingresaDatosTriangulo();
	pila.agrega(triangulo);
        Limpiar();
}

catch(Exception e)
{
    JOptionPane.showMessageDialog(null, "Error");
}

}

    public void ingresaDatosTriangulo() {
        triangulo = new Triangulo();
        triangulo.setAltura(leeraltura());
        triangulo.setArea(leerarea());
        triangulo.setBase(leerBase());
        triangulo.setCodigo(leerCodigo());
        triangulo.setTipo(leerTipo());
    }

public void mensaje(String texto)
{
	JOptionPane.showMessageDialog(this,texto);
}

    public double leeraltura() {
        return Double.parseDouble(txtAltura.getText());
    }

    private double leerarea() {
        return Double.parseDouble(txtBase.getText())*Double.parseDouble(txtAltura.getText());
    }

    private double leerBase() {
        return Double.parseDouble(txtBase.getText());
    }
    public String leerCodigo(){
        return txtCodigo.getText();
    }
    public String leerTipo(){return String.valueOf(Tipo.getSelectedItem());}


    public void mostrarListaTipoCola()
    {
     NodoSimple auxiliar;
     if(Main1.pila.getUltimo() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");

     auxiliar = Main1.pila.getUltimo();
     while(auxiliar!=null)
     {  
         Triangulo tri = auxiliar.getTriangulos();
         if(auxiliar.getTriangulos().getCodigo()!=null);
         {
         acumulador = acumulador+(tri.getCodigo()+"\t"+tri.getAltura()+"\t"+tri.getBase()+"\t"+tri.getTipo()+"\t"+tri.getArea()+"\n");
         }
         auxiliar = auxiliar.getAnterior();
     }
     txtSalida.setText(acumulador);
    }
}