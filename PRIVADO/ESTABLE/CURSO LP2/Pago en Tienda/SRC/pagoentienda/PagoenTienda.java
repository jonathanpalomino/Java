/*
*
*Realizar el programa en java en formnulario en el que ingrese los datos de un articulo:
* codigo,descripcion precio unitartio cantidad tipo de articulo(alfa,berta ,gamma)
* Stock minimo del articulo, el importe se debe calcular en el formulario
* el el formulario se debe calcular el importe total del articulo sabiendo ademas que para
* los articulos tipo alfa hay un stock minimo de 30 unidades , para beta un stock minimo
* de 20 unidades y el gama 10.
* El programa debe imprimir si el articulo necesita realizar un nuevo pedido
*
*/

/*
* PROGRAMA REALIZADO POR JONATHAN MOISES PALOMINO VILCA
* CODIGO 2008009684
* B5-3 TURNO MAÑANA
* ARMANDO HUAPAYA SOTERO
*/
package pagoentienda;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
public class PagoenTienda extends Frame
{
TextField txtCod = new TextField();
TextField txtDesc = new TextField();
TextField txtPrecio = new TextField();
TextField txtCant = new TextField();
TextField txtCateg = new TextField();
Choice Cat = new Choice();
TextField Calcular = new TextField();
TextField Limpiar = new TextField();
public PagoenTienda()
{
setSize(500,450);
Label lblCodigoProd = new Label("Codigo del producto");
add(lblCodigoProd);
lblCodigoProd.setBounds(50, 70, 130, 20);
txtCod.setBounds(200, 70, 130, 20);
add(txtCod);

Label lbldescripcion = new Label("Descripcion");//
add(lbldescripcion);
lbldescripcion.setBounds(50, 40, 130, 20);
txtDesc.setBounds(200, 40, 130, 20);
add(txtDesc);

Label lblPrecio = new Label("Precio del producto");
add(lblPrecio);
add(txtPrecio);
lblPrecio.setBounds(50, 100, 130, 20);
txtPrecio.setBounds(200, 100, 130, 20);

Label lblCant = new Label ("Cantidad");
add(lblCant);
add(txtCant);
lblCant.setBounds(50, 130, 130, 20);
txtCant.setBounds(200, 130, 130, 20);

Label lblCategoria = new Label ("Categoria del producto");
add(lblCategoria);
add(Cat);
Cat.addItem("alfa");
Cat.addItem("beta");
Cat.addItem("gamma");

lblCategoria.setBounds(50, 160, 130, 20);
Cat.setBounds(200, 160, 130, 20);

Button btnCalcular = new Button("Calcular");
add(btnCalcular);
add(Calcular);

btnCalcular.setBounds(50, 240, 130, 20);
Calcular.setBounds(200, 240, 130, 20);

Button btnLimpiar = new Button("Limpiar");
add(btnLimpiar);
add(Limpiar);

btnLimpiar.setBounds(350, 240, 130, 20);
Limpiar.setBounds(400, 240, 130, 20);

btnCalcular.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
int stockalfa=30,stockbeta=20,stockgamma=10;

try
{
Double precio,cantidad;
precio = Double.parseDouble(txtPrecio.getText());
cantidad = Double.parseDouble(txtCant.getText());
if(((String)Cat.getSelectedItem()).equals("alfa"))
{
if(cantidad>stockalfa)
{
JOptionPane.showMessageDialog(null,"No hay suficiente Stock", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
}
else{
Calcular.setText("Total es "+(precio*cantidad));
}
}
else if(((String)Cat.getSelectedItem()).equals("beta"))
{
if(cantidad>stockbeta)
{
JOptionPane.showMessageDialog(null,"No hay suficiente Stock", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
}
else{
Calcular.setText("Total es "+(precio*cantidad));
}
}
else if(((String)Cat.getSelectedItem()).equals("gamma"))
{
if(cantidad>stockgamma)
{
JOptionPane.showMessageDialog(null,"No hay suficiente Stock", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
}
else{
Calcular.setText("Total es "+(precio*cantidad));
}
}
}
catch(Exception exc)
{
Calcular.setText("Error en las operaciones");
}
}
});

btnLimpiar.addActionListener(new ActionListener()//Esto limpiara
{
public void actionPerformed(ActionEvent e)
{
///Limpiar
txtDesc.setText("");
txtCod.setText("");
txtPrecio.setText("");
txtCant.setText("");
Calcular.setText("");
}
});
this.addWindowListener(new WindowAdapter()
{
public void windowClosing(WindowEvent e)
{
System.exit(0);
}
}) ;
}
public static void main(String[] args)
{
PagoenTienda c= new PagoenTienda();//Creo el contenedor
c.show(); //Llamo el contenedor Frame
}

}