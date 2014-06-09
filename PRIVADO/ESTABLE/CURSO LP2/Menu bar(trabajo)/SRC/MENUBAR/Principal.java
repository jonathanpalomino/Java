package menubar;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
/**
 *
 * @author JONATHAN1
 */
public class Principal extends Frame implements ActionListener, ItemListener
{
Productos Productos;
TextField txtCodpro = new TextField();
TextField txtCodVenta = new TextField();
TextField txtDesc = new TextField();
TextField txtPrecio = new TextField();
TextField txtCant = new TextField();
TextField txtCateg = new TextField();
Choice Cat = new Choice();
TextField Calcular = new TextField();
TextField Limpiar = new TextField();
Button btnReiniciar = new Button ("Reiniciar");
Button btnLimpiar = new Button("Limpiar");
Button btnCalcular = new Button("Calcular");
Button btnVolveringreso = new Button("Volver a ingresar");
Label lblCategoria = new Label ("Categoria del producto");
Label lblCant = new Label ("Cantidad");
Label lblPrecio = new Label("Precio del producto");
Label lbldescripcion = new Label("Descripcion");
Label lblCodigoVenta = new Label("Codigo de Venta");
Label lblCodigoprod = new Label("Codigo del Producto");
static ListaDobleConOrden ldco =new ListaDobleConOrden();
private PanelTabla	panTabla;
int stockalfa=30,stockbeta=20,stockgamma=10;
    public Principal()
    {
        setSize(600,600);
        setTitle("Menu de opciones");

            add(lbldescripcion);
    lbldescripcion.setBounds(50, 70, 130, 20);
    txtDesc.setBounds(200, 70, 130, 20);
    add(txtDesc);

    add(lblCodigoVenta);
    lblCodigoVenta.setBounds(50, 100, 130, 20);
    txtCodVenta.setBounds(200, 100, 130, 20);
    add(txtCodVenta);

    add(lblCodigoprod);
    lblCodigoprod.setBounds(50, 130, 130, 20);
    txtCodpro.setBounds(200, 130, 130, 20);
    add(txtCodpro);

    add(lblPrecio);
    add(txtPrecio);
    lblPrecio.setBounds(50, 160, 130, 20);
    txtPrecio.setBounds(200, 160, 130, 20);

    add(lblCant);
    add(txtCant);
    lblCant.setBounds(50, 190, 130, 20);
    txtCant.setBounds(200, 190, 130, 20);

    add(lblCategoria);
    add(Cat);
    Cat.addItem("alfa");
    Cat.addItem("beta");
    Cat.addItem("gamma");
    lblCategoria.setBounds(50, 220, 130, 20);
    Cat.setBounds(200, 220, 130, 20);

    add(btnCalcular);
    add(Calcular);
    btnCalcular.setBounds(50, 270, 130, 20);
    Calcular.setBounds(200, 270, 130, 20);

    add(btnLimpiar);
    btnLimpiar.setBounds(350, 270, 130, 20);

    add(btnReiniciar);
    btnReiniciar.setBounds(350, 300, 130, 20);

    add(btnVolveringreso);
    btnVolveringreso.setBounds(50, 270, 130, 20);
    btnVolveringreso.setVisible(false);

    panTabla = new PanelTabla();
	add(panTabla, BorderLayout.CENTER);
    panTabla.setVisible(false);

        MenuBar barra = new MenuBar();
        Menu menu = new Menu("Menu");

        MenuItem check = new MenuItem("Ingresar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Modificar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Eliminar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Ubicar");
        check.addActionListener(this);
        menu.add(check);
        
        Menu menuorden = new Menu("Ordenar");
        check.addActionListener(this);
        menu.add(menuorden);

        MenuItem submenu= new MenuItem("descendente");
        submenu.addActionListener(this);
        menuorden.add(submenu);

        submenu = new MenuItem("ascendente");
        submenu.addActionListener(this);
        menuorden.add(submenu);
        
        barra.add(menu);

        setMenuBar(barra);

    btnCalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                IngresoCola();
            }
        });

    btnLimpiar.addActionListener(new ActionListener()//Esto limpiara
{
        public void actionPerformed(ActionEvent e)
               {
///Limpiar
            txtDesc.setText("");
            txtCodVenta.setText("");
            txtCodpro.setText("");
            txtPrecio.setText("");
            txtCant.setText("");
            Calcular.setText("");
            }
        });

            btnReiniciar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                ldco.setInicio(null);
                mensaje("Informacion Reiniciada");
            }
        });

            btnVolveringreso.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
Desaparece();
            }
        });

    this.addWindowListener(new WindowAdapter()
{
    public void windowClosing(WindowEvent e)
    {System.exit(0);
    }
});
    }

    public void actionPerformed(ActionEvent e)
    {
    if(e.getActionCommand().equals("Ingresar"))
    {
Aparece();
    }
    else if(e.getActionCommand().equals("Modificar"))
    {
        Aparece();   
        Modificar();
    }
    else if(e.getActionCommand().equals("Eliminar"))
    {
            Eliminar();
    }
    else if(e.getActionCommand().equals("Ubicar"))
    {
            Ubicar();
    }

    else if(e.getActionCommand().equals("descendente"))
    {
            descendente();
    }
    else if(e.getActionCommand().equals("ascendente"))
    {
            ascendente();
    }
    }

    public void itemStateChanged(ItemEvent e){  }//nada por ahora

    public void Eliminar()
    {
        
    }

    public void Ingresar()
    {
    System.out.println("hola");
    }

    public void Ubicar()
    {
    	String codigo = leerCodigo();// lee codigo

	NodoDoble auxiliar = Principal.ldco.busca(codigo);
	if(auxiliar == null){ // no lo encontró
		mensaje("El código no existe en lista doble con orden");
		borrar();
	} else { // si lo encontró
		Productos = auxiliar.getPro();
		muestraDatosProducto();
	}
    }

    public void Modificar()
    {
        String codigo = leerCodigo();// lee codigo

	NodoDoble auxiliar = Principal.ldco.busca(codigo);
	if(auxiliar == null){ // no lo encontró
		mensaje("El código no existe en lista doble con orden");
		borrar();
	} else { // si lo encontró
		Productos = auxiliar.getPro();
		modificarDatosProducto();
	}
    }

    public void ascendente()
    {
Aparece();
inicializaTabla();
NodoDoble auxiliar;

	if(Principal.ldco.getInicio() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");
	else {
		// primero de la lista
		auxiliar = Principal.ldco.getInicio();
		// recorrido secuencial hacia la derecha
		while(auxiliar != null)	{
			// obtiene un empleado
			Productos Pro = auxiliar.getPro();
                        Object[] datos = {	Pro.getDescripcion(),
				  		Pro.getCodigo(),
				  		Pro.getCodigopro(),
				  		Pro.getprecio(),
                        Pro.getcantidad(),
                        Pro.getCategoria(),
                        Pro.getprecio()*Pro.getcantidad()
				  	   };

			// agrega fila de datos en tabla
			panTabla.getMiModelo().addRow(datos);

			//avanza siguiente nodo
 			auxiliar= auxiliar.getApuntSgte();
		}// fin del while
	}// fin del else
    }

    public void IngresoCola() {
                try
            {
            Double precio;
            int cantidad;
            precio = Double.parseDouble(txtPrecio.getText());
            cantidad = Integer.parseInt(txtCant.getText());
                if(((String)Cat.getSelectedItem()).equals("alfa"))
                {
                if(cantidad>stockalfa)
                    {
                    JOptionPane.showMessageDialog(null,"No hay suficiente Stock", "Tenemos problemas", JOptionPane.ERROR_MESSAGE);
                    }
                else{
                    Calcular.setText("Total es "+(precio*cantidad));
                    stockalfa = stockalfa-cantidad;
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
                    stockbeta = stockbeta-cantidad;
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
                    stockgamma = stockgamma-cantidad;
                    }
                }
//espacio
ingresaDatosProducto();
	// busca codigo leido
	NodoDoble auxiliar = ldco.busca(Productos.getCodigo());

	if(auxiliar != null){// si lo encontro
		mensaje("El código YA EXISTE en cola");
		corrigeCodigo();
	} else {// no lo encontro
		ldco.agrega(Productos);
		prepararNuevosIngresos();
	}
            }
            catch(Exception exc)
            {
            Calcular.setText("Error en las operaciones");
            }
            }

    public void mensaje(String texto)
    {
        JOptionPane.showMessageDialog(this,texto);
    }

    private void borrar() {
        txtDesc.setText("");
        txtPrecio.setText("");
        txtCant.setText("");
        txtCateg.setText("");
        txtCodVenta.setText("");
        txtCodpro.setText("");
        txtCodVenta.requestFocus();
    }

    private void corrigeCodigo() {
        txtCodVenta.requestFocus();
    }

    public void descendente() {
Desaparece();
inicializaTabla();
       NodoDoble auxiliar;

	if(Principal.ldco.getInicio() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");
	else {
		// ultimo de la lista
		auxiliar = Principal.ldco.getFin();
		// recorrido secuencial hacia la izquierda
		while(auxiliar != null)	{
			// obtiene un empleado
			Productos Pro = auxiliar.getPro();
                        Object[] datos = {	Pro.getDescripcion(),
				  		Pro.getCodigo(),
				  		Pro.getCodigopro(),
				  		Pro.getprecio(),
                        Pro.getcantidad(),
                        Pro.getCategoria(),
                        Pro.getprecio()*Pro.getcantidad()
				  	   };
			// agrega fila de datos en tabla
			panTabla.getMiModelo().addRow(datos);

			//retrocede al nodo anterior
 			auxiliar= auxiliar.getApuntAnt();
		}// fin del while
	}// fin del else
    }

    private void ingresaDatosProducto() {
                // crea espacio en memoria para un empleado
	Productos =new Productos();

	// ingresa datos de un empleado
	Productos.setCodigo( leerCodigo() );
	Productos.setDescripcion( leerDescripcion() );
	Productos.setprecio( leerprecio() );
	Productos.setcantidad( leercantidad() );
	Productos.setCategoria( leerCategoria() );
    }

    public void inicializaTabla()
    {
        // obtiene numero de filas de la tabla
	int filas = panTabla.getMiTabla().getRowCount();

	// remueve todas las filas de la tabla
	for (int fila=0; fila<filas; fila++) {
            panTabla.getMiModelo().removeRow(0);
        }
    }

    public void muestraDatosProducto() {
    txtDesc.setText(Productos.getDescripcion());
    txtPrecio.setText(""+Productos.getprecio());
    txtCant.setText(""+Productos.getcantidad());
    txtCateg.setText(""+Productos.getCategoria());
    txtCodVenta.setText(""+Productos.getCodigo());
    txtCodpro.setText(""+Productos.getCodigopro());
    }

    public void modificarDatosProducto() {
    Productos.setCategoria(Cat.getSelectedItem());
    Productos.setCodigopro(Integer.parseInt(txtCodpro.getText()));
    Productos.setDescripcion(txtDesc.getText());
    Productos.setcantidad(Integer.parseInt(txtCant.getText()));
    Productos.setprecio(Double.parseDouble(txtPrecio.getText()));
    Productos.setCodigo(txtCodVenta.getText());

    }

    private void prepararNuevosIngresos() {
            txtDesc.setText("");
            txtCodVenta.setText("");
            txtCodpro.setText("");
            txtPrecio.setText("");
            txtCant.setText("");
            Calcular.setText("");
            txtCodVenta.requestFocus();
    }
public String leerCodigo() {	return txtCodVenta.getText();}
//............................
public String leerDescripcion() {return txtDesc.getText();}
//............................
public double leerprecio(){	return Double.parseDouble(txtPrecio.getText());}
//............................
public int leercantidad() {	return Integer.parseInt(txtCant.getText());}
//............................
public String leerCategoria(){return ((String)Cat.getSelectedItem());}

public void Aparece()
{
            panTabla.setVisible(false);
            txtCant.setVisible(true);
            txtCateg.setVisible(true);
            txtCodVenta.setVisible(true);
            txtCodpro.setVisible(true);
            txtDesc.setVisible(true);
            txtPrecio.setVisible(true);
            Cat.setVisible(true);
            lblCant.setVisible(true);
            lblCategoria.setVisible(true);
            lblCodigoVenta.setVisible(true);
            lblCodigoprod.setVisible(true);
            lblPrecio.setVisible(true);
            Calcular.setVisible(true);
            lbldescripcion.setVisible(true);
            btnCalcular.setVisible(true);
            btnLimpiar.setVisible(true);
            btnVolveringreso.setVisible(false);
}

public void Desaparece()
{
panTabla.setVisible(true);
txtCant.setVisible(false);
txtCateg.setVisible(false);
txtCodVenta.setVisible(false);
txtDesc.setVisible(false);
txtPrecio.setVisible(false);
txtCodpro.setVisible(false);
Cat.setVisible(false);
lblCant.setVisible(false);
lblCategoria.setVisible(false);
lblCodigoVenta.setVisible(false);
lblCodigoprod.setVisible(false);
lblPrecio.setVisible(false);
Calcular.setVisible(false);
lbldescripcion.setVisible(false);
btnCalcular.setVisible(false);
btnLimpiar.setVisible(false);
btnVolveringreso.setVisible(true);
}
    public static void main(String[] args)
    {
     Principal nuevo = new Principal();
     nuevo.show();
    }
}

