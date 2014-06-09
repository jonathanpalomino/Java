
package menubar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Formulario extends Frame
{
MenuBar barra = new MenuBar();
Menu men = new Menu("j");
MenuItem m = new MenuItem("uno");

Button btnCalcular = new Button("Calcular");
Button btnLimpiar = new Button("Limpiar");
Choice cat = new Choice();
TextField txtCodigo = new TextField();
TextField txtCodigoVen = new TextField();
TextField txtCantidad = new TextField();
TextField txtPrecioU = new TextField();
TextField txtDescripcion = new TextField();
TextField txtTotal = new TextField();

Label lblCodigo = new Label("Codigo Producto");
Label lblCodigoVen = new Label("Codigo Venta");
Label lblCantidad = new Label("Cantidad");
Label lblPrecioU = new Label("Precio Unitario");
Label lblDescripcion = new Label("Descripcion");
Label lblTotal = new Label("Total");
Label lblTipo = new Label("Tipo");

Productos pro;
static ListaDobleConOrden ldco = new ListaDobleConOrden();
private PanelTabla panTabla;
    public Formulario()
    {
        setSize(400,500);
        setTitle("Estructura Dinamica");

        lblDescripcion.setBounds(50, 50, 100, 20);
        txtDescripcion.setBounds(180, 50, 100, 20);
        add(lblDescripcion);
        add(txtDescripcion);
        
        lblCodigo.setBounds(50, 80, 100, 20);
        txtCodigo.setBounds(180, 80, 100, 20);
        add(txtCodigo);
        add(lblCodigo);

        lblCodigoVen.setBounds(50, 110, 100, 20);
        txtCodigoVen.setBounds(180,110, 100, 20);
        add(lblCodigoVen);
        add(txtCodigoVen);
        
        lblCantidad.setBounds(50, 140, 100, 20);
        txtCantidad.setBounds(180, 140, 100, 20);
        add(lblCantidad);
        add(txtCantidad);

        lblPrecioU.setBounds(50, 170, 100, 20);
        txtPrecioU.setBounds(180, 170, 100, 20);
        add(lblPrecioU);
        add(txtPrecioU);

        lblTotal.setBounds(50, 200, 100, 20);
        txtTotal.setBounds(180, 200, 100, 20);
        add(txtTotal);
        add(lblTotal);

        lblTipo.setBounds(290, 110, 100, 20);
        cat.setBounds(290, 140, 100, 20);
        cat.add("Alfa");
        cat.add("Beta");
        cat.add("Gamma");
        add(cat);
        add(lblTipo);

        btnCalcular.setBounds(50, 250, 100, 20);
        btnLimpiar.setBounds(280, 250, 100, 20);
        add(btnCalcular);
        add(btnLimpiar);
        barra.add(men);
        setMenuBar(barra);

        btnCalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                IngresosNodo();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                prepararNuevosIngresos();
            }
        });
this.addWindowListener(new WindowAdapter(){
public void windowClosing(WindowEvent e)
{
        System.exit(0);
}
});
panTabla = new PanelTabla();
	add(panTabla, BorderLayout.CENTER);
        panTabla.setVisible(false);
    }
    public static void main(String[] args) {
        Formulario f = new Formulario();
        f.show();
    }
    public void IngresosNodo() {
         ingresaDatosProducto();
	// busca codigo leido
	NodoDoble auxiliar = ldco.busca(pro.getCodigo());
	if(auxiliar != null){// si lo encontro
		mensaje("El código YA EXISTE en lista doble con orden");
		corrigeCodigo();
	} else {// no lo encontro
		ldco.agrega(pro);
		prepararNuevosIngresos();
	}
            }

    public void mensaje(String texto) {
    JOptionPane.showMessageDialog(this,texto);
    }

    public void corrigeCodigo() {txtCodigo.requestFocus();}

    public void prepararNuevosIngresos() {
        txtCantidad.setText("");
        txtCodigo.setText("");
        txtCodigoVen.setText("");
        txtDescripcion.setText("");
        txtPrecioU.setText("");
        txtCodigoVen.requestFocus();
    }

    public void ingresaDatosProducto() {
        pro = new Productos();
        pro.setCantidad(leerCantidad());
        pro.setCodigo( leerCodigo() );
        pro.setCodigoventa(leerCodVenta());
        pro.setDescripcion(leerDescripcion());
        pro.setPrecioU(leerPrecioU());
    }

    public int leerCantidad() {return Integer.parseInt(txtCantidad.getText());}
    public double leerPrecioU() {return Double.parseDouble(txtPrecioU.getText());}
    public String leerDescripcion(){return txtDescripcion.getText();}
    public String leerCodigo(){return txtCodigo.getText();}
    public String leerCodVenta(){return txtCodigoVen.getText();}

}
