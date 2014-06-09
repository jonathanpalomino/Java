package examenfinallp2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Problema_1 extends JApplet
{
String user="root";
String pass ="123456789";
String url="jdbc:mysql://localhost:3306/equipos";
Connection coneccion=null;
double totalAlfa=0;
double totalBeta=0;
double totalGamma=0;

JButton btnprocesar  = new JButton("Procesar");
JButton btnmostrar = new JButton("Mostrar datos");
JLabel lblfacu= new JLabel("Facultad");
JLabel lblcodigo = new JLabel("Codigo");
JLabel lblescuela= new JLabel("Escuela");
JLabel lblSO= new JLabel("Sistema Operativo");
JLabel lblTecno= new JLabel("Tecnologia");
JLabel lblDistro= new JLabel("Distribuidor");
JLabel lblmarca= new JLabel("Marca");
JLabel lblcantidad= new JLabel("Cantidad");
JLabel lblprecio= new JLabel("Precio");
JLabel vacio= new JLabel("");

JTextField txtfacu= new JTextField();
JTextField txtcodigo = new JTextField();
JTextField txtescuela= new JTextField();
JTextField txtSO= new JTextField();
JTextField txtTecno= new JTextField();
JTextField txtDistro= new JTextField();
JTextField txtcantidad= new JTextField();
JTextField txtprecio= new JTextField();
JComboBox marca= new JComboBox();

public void init()
{
    setSize(600, 600);
    IniciarComponentes();
btnprocesar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                
Enviar_a_Base();
Limpiar();
            }
        });

btnmostrar.addActionListener(new ActionListener() {

    public void actionPerformed(ActionEvent e)
    {
        totalAlfa=0;totalBeta=0;totalGamma=0;
        LlamarDatos();
    }
        });

}
    private void LlamarDatos()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            coneccion = DriverManager.getConnection(url, user, pass);
            if(coneccion!=null)
            {
            Statement st = coneccion.createStatement();
            ResultSet resp = st.executeQuery("select *from costos");
            while(resp.next())
            {
            if(resp.getObject("marca").equals("Alfa"))
            {
            double precio = Double.parseDouble(String.valueOf(resp.getObject("precio")));
            int cantidad = Integer.parseInt(String.valueOf(resp.getObject("cantidad")));
            totalAlfa = totalAlfa+precio*cantidad;
            }
            else if(resp.getObject("marca").equals("Beta"))
            {
            double precio = Double.parseDouble(String.valueOf(resp.getObject("precio")));
            int cantidad = Integer.parseInt(String.valueOf(resp.getObject("cantidad")));
            totalBeta = totalBeta+precio*cantidad;
            }
            else if(resp.getObject("marca").equals("Gamma"))
            {
            double precio = Double.parseDouble(String.valueOf(resp.getObject("precio")));
            int cantidad = Integer.parseInt(String.valueOf(resp.getObject("cantidad")));
            totalGamma = totalGamma+precio*cantidad;
            }
            }
            }
        }

        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }

        System.out.println(" El total para alfa es"+totalAlfa);
        System.out.println(" El total para beta es"+totalBeta);
        System.out.println(" El total para gamma es"+totalGamma);
    }
    void Limpiar()
    {
        txtDistro.setText("");
        txtSO.setText("");
        txtTecno.setText("");
        txtcantidad.setText("");
        txtcodigo.setText("");
        txtescuela.setText("");
        txtfacu.setText("");
        txtprecio.setText("");
    }
    private void Enviar_a_Base()
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            coneccion = DriverManager.getConnection(url, user, pass);
            if(coneccion!=null)
            {
            Statement st = coneccion.createStatement();
            st.executeUpdate("insert into costos(codigo,marca,cantidad,precio) values ('"+txtcodigo.getText()+"','"+marca.getSelectedItem()+"','"+txtcantidad.getText()+"','"+txtprecio.getText()+"');");
            st.executeUpdate("insert into caracteristicas(codigo,SO,Tecnologia,Distribuidor) values ('"+txtcodigo.getText()+"','"+txtSO.getText()+"','"+txtTecno.getText()+"','"+txtDistro.getText()+"')");
            st.executeUpdate("insert into universidad (codigo,facultad,escuela) values ('"+txtcodigo.getText()+"','"+txtfacu.getText()+"','"+txtescuela.getText()+"')");
            }
            coneccion.close();
        }
        catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        catch(ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void IniciarComponentes() {
    lblfacu.setBounds(50,100,100,20);
    lblescuela.setBounds(50,140,100,20);
    lblSO.setBounds(50,180,100,20);
    lblTecno.setBounds(50,220,100,20);
    lblDistro.setBounds(350,220,100,20);
    lblmarca.setBounds(50,260,100,20);
    lblcantidad.setBounds(350,260,100,20);
    lblprecio.setBounds(50,300,100,20);
    lblcodigo.setBounds(350,100,100,20);

    txtfacu.setBounds(150,100,100,20);
    txtcodigo.setBounds(450, 100, 100, 20);
    txtescuela.setBounds(150,140,100,20);
    txtSO.setBounds(150,180,100,20);
    txtTecno.setBounds(150,220,100,20);
    txtDistro.setBounds(450,220,100,20);
    txtcantidad.setBounds(450,260,100,20);
    txtprecio.setBounds(150,300,100,20);

    btnmostrar.setBounds(100, 330, 150, 20);
    btnprocesar.setBounds(300, 330, 150, 20);
    marca.setBounds(150,260,100,20);

    marca.addItem("Alfa");
    marca.addItem("Beta");
    marca.addItem("Gamma");
      add(lblfacu);
      add(lblcodigo);
      add(lblDistro);
      add(lblSO);
      add(lblTecno);
      add(lblcantidad);
      add(lblescuela);
      add(lblmarca);
      add(lblprecio);
      add(txtDistro);
      add(txtcodigo);
      add(txtSO);
      add(txtTecno);
      add(txtcantidad);
      add(txtescuela);
      add(txtfacu);
      add(txtprecio);
      add(marca);
      add(btnmostrar);
      add(btnprocesar);
      add(vacio);
    }
}



