package promediograficoyconlista;
/**
 *
 * @author JONATHAN1
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class Main extends Frame {
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
TextField Mostrar = new TextField();
Button btnReiniciar = new Button ("Reiniciar");
Button btneliminar = new Button("Eliminar");
Button btnMostrar = new Button("Mostrar Datos");
Button btnLimpiar = new Button("Limpiar");
Button btnCalcular = new Button("Calcular");
Button btnVolveringreso = new Button("Volver a ingresar");
Label lblCategoria = new Label ("Categoria del producto");
Label lblCant = new Label ("Cantidad");
Label lblPrecio = new Label("Precio del producto");
Label lbldescripcion = new Label("Descripcion");
Label lblCodigoVenta = new Label("Codigo de Venta");
Label lblCodigoprod = new Label("Codigo del Producto");
static Cola cola=new Cola();
private PanelTabla	panTabla;
int stockalfa=30,stockbeta=20,stockgamma=10;
public Main()
{
    setSize(490,300);
    setTitle("Estructuras Dinamicas de Tipo Cola");

    add(lbldescripcion);
    lbldescripcion.setBounds(50, 40, 130, 20);
    txtDesc.setBounds(200, 40, 130, 20);
    add(txtDesc);

    add(lblCodigoVenta);
    lblCodigoVenta.setBounds(50, 70, 130, 20);
    txtCodVenta.setBounds(200, 70, 130, 20);
    add(txtCodVenta);

    add(lblCodigoprod);
    lblCodigoprod.setBounds(50, 100, 130, 20);
    txtCodpro.setBounds(200, 100, 130, 20);
    add(txtCodpro);

    add(lblPrecio);
    add(txtPrecio);
    lblPrecio.setBounds(50, 130, 130, 20);
    txtPrecio.setBounds(200, 130, 130, 20);

    add(lblCant);
    add(txtCant);
    lblCant.setBounds(50, 160, 130, 20);
    txtCant.setBounds(200, 160, 130, 20);

    add(lblCategoria);
    add(Cat);
    Cat.addItem("alfa");
    Cat.addItem("beta");
    Cat.addItem("gamma");
    lblCategoria.setBounds(50, 190, 130, 20);
    Cat.setBounds(200, 190, 130, 20);

    add(btneliminar);
    btneliminar.setBounds(350, 210, 130,20);
    
    add(btnCalcular);
    add(Calcular);
    btnCalcular.setBounds(50, 240, 130, 20);
    Calcular.setBounds(200, 240, 130, 20);

    add(btnLimpiar);
    btnLimpiar.setBounds(350, 240, 130, 20);
  
    add(btnMostrar);
    add(Mostrar);
    btnMostrar.setBounds(50, 270, 130, 20);
    
    add(btnReiniciar);
    btnReiniciar.setBounds(350, 270, 130, 20);

    add(btnVolveringreso);
    btnVolveringreso.setBounds(50, 240, 130, 20);
    btnVolveringreso.setVisible(false);
    
    panTabla = new PanelTabla();
	add(panTabla, BorderLayout.CENTER);
    panTabla.setVisible(false);
    btnCalcular.addActionListener(new ActionListener()
        {
        public void actionPerformed(ActionEvent e)
                {
            ingresoCola();
                }
        });

    btnReiniciar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                cola.setInicio(null);
                mensaje("Informacion Reiniciada");
            }
        });
    btnMostrar.addActionListener(new ActionListener()
{
        public void actionPerformed(ActionEvent e)
               {
                mostrarListaTipoCola();
               }

        });
    btnVolveringreso.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
Aparece();
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
btneliminar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Elimina();
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

void ingresoCola()
{

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
	NodoSimple auxiliar = cola.busca(Productos.getCodigo());

	if(auxiliar != null){// si lo encontro
		mensaje("El código YA EXISTE en cola");
		corrigeCodigo();
	} else {// no lo encontro
		cola.agrega(Productos);
		prepararNuevosIngresos();
	}
            }
            catch(Exception exc)
            {
            Calcular.setText("Error en las operaciones");
            }


}
public static void main(String[] args)
     {
         Main panelInicial= new Main();
         panelInicial.show();
     }

void prepararNuevosIngresos()
    {
            txtDesc.setText("");
            txtCodVenta.setText("");
            txtCodpro.setText("");
            txtPrecio.setText("");
            txtCant.setText("");
            Calcular.setText("");
            txtCodVenta.requestFocus();
    }

public void mensaje(String texto) 
{
	JOptionPane.showMessageDialog(this,texto);
}

public void corrigeCodigo()
{
    txtCodVenta.requestFocus();
}

public void ingresaDatosProducto()
    {
        // crea espacio en memoria para un empleado
	Productos =new Productos();

	// ingresa datos de un empleado
	Productos.setCodigo( leerCodigo() );
	Productos.setDescripcion( leerDescripcion() );
	Productos.setprecio( leerprecio() );
	Productos.setcantidad( leercantidad() );
	Productos.setCategoria( leerCategoria() );
    }
public void mostrarListaTipoCola()
{
Desaparece();
inicializaTabla();
NodoSimple auxiliar;
	if(Main.cola.getInicio() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");
	else	{
		// primero de la lista
		auxiliar = Main.cola.getInicio();
		// mientras no sea nulo
		while(auxiliar != null) {
			// obtiene un empleado
			Productos pro = auxiliar.Prod();
                        Object[] datos = {	pro.getDescripcion(),
				  		pro.getCodigo(),
                        pro.getCodigopro(),
				  		pro.getprecio(),
				  		pro.getcantidad(),
                        pro.getCategoria(),
                        pro.getprecio()*pro.getcantidad()
				  	   };

			// agrega fila de datos en tabla
            
			panTabla.getMiModelo().addRow(datos);
			// avanza al siguiente de la lista
			auxiliar = auxiliar.getApuntSgte();
		}// fin del while
	}// fin del else
}
public int leerCodigo() {	return Integer.parseInt(txtCodVenta.getText());}
//............................
public String leerDescripcion() {return txtDesc.getText();}
//............................
public double leerprecio(){	return Double.parseDouble(txtPrecio.getText());}
//............................
public int leercantidad() {	return Integer.parseInt(txtCant.getText());}
//............................
public String leerCategoria(){return ((String)Cat.getSelectedItem());}

public void inicializaTabla()
    {
        // obtiene numero de filas de la tabla
	int filas = panTabla.getMiTabla().getRowCount();

	// remueve todas las filas de la tabla
	for (int fila=0; fila<filas; fila++) {
            panTabla.getMiModelo().removeRow(0);
        }
    }
//............................

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
            btnMostrar.setVisible(true);
            btnReiniciar.setVisible(true);
            btneliminar.setVisible(true);
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
btnMostrar.setVisible(false);
btneliminar.setVisible(false);
}

public void Elimina()
{
    int codigo = leerCodigo();
    NodoSimple auxiliar = cola.busca(codigo);
    if(auxiliar!=null)
    {
        cola.elimina(auxiliar);
    }
     else
    {// no lo encontro
            corrigeCodigo();
            mensaje("No puedo eliminar lo que no existe");
    }
}
}
