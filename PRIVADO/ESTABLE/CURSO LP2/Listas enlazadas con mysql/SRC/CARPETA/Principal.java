
package carpeta;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Principal extends Frame implements ActionListener
{
String contraseña;
String usuario;
String url="jdbc:mysql://localhost:3306/listas";
Connection conexion=null;
String Orden;
String ReiniciarBD="en_uso";
Productos Productos;
TextField txtCodpro = new TextField();
TextField txtCodVenta = new TextField();
TextField txtDesc = new TextField();
TextField txtPrecio = new TextField();
TextField txtCant = new TextField();
Choice Cat = new Choice();
TextField Calcular = new TextField();
TextField Limpiar = new TextField();
Button btnReiniciar = new Button ("Reiniciar");
Button btnLimpiar = new Button("Limpiar");
Button btnCalcular = new Button("Calcular");
Button btnVolveringreso = new Button("Volver a ingresar");
Button btnLimpiarT = new Button("Limpiar Tabla y obtener de Mysql");
Button btnREiniciarBase = new Button("Reiniciar Base de Datos");
Label lblCategoria = new Label ("Categoria del producto");
Label lblCant = new Label ("Cantidad");
Label lblPrecio = new Label("Precio del producto");
Label lbldescripcion = new Label("Descripcion");
Label lblCodigoVenta = new Label("Codigo de Venta");
Label lblCodigoprod = new Label("Codigo del Producto");
static ListaDobleConOrden ldco =new ListaDobleConOrden();
private PanelTabla	panTabla;
int stockalfa=30,stockbeta=20,stockgamma=10;

    public Principal(String usuario1,String contraseña1)
    {
        
        usuario = usuario1;
        contraseña = contraseña1;
        System.out.println(usuario+"   "+contraseña);
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

    add(btnLimpiarT);
    btnLimpiarT.setBounds(50, 300, 190, 20);
    btnLimpiarT.setVisible(false);

    add(btnREiniciarBase);
    btnREiniciarBase.setBounds(50, 300, 170, 20);

    
    panTabla = new PanelTabla();
	add(panTabla, BorderLayout.CENTER);
    panTabla.setVisible(false);

        MenuBar barra = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu menu1 = new Menu("Eliminar");

        MenuItem check = new MenuItem("Ingresar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Modificar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Ubicar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Eliminar Nodo");
        check.addActionListener(this);
        menu1.add(check);

        check = new MenuItem("Eliminar Registro en BD");
        check.addActionListener(this);
        menu1.add(check);

        check = new MenuItem("Eliminar Registro en BD y Nodo");
        check.addActionListener(this);
        menu1.add(check);

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
        barra.add(menu1);
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
            borrar();
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
            Aparece();

            }
        });

        btnREiniciarBase.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
              Truncar();
            }
        });

        btnLimpiarT.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                inicializaTabla();
                AgregaraTabla();
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
    else if(e.getActionCommand().equals("Eliminar Registro en BD"))
    {
        Aparece();
        Ubicar();
        EliminarBD();
        borrar();
    }
    else if(e.getActionCommand().equals("Eliminar Nodo"))
    {
        Aparece();
        EliminarED();
    }
    else if(e.getActionCommand().equals("Eliminar Registro en BD y Nodo"))
    {
        Aparece();
        Ubicar();
        EliminarBD();
        EliminarED();
        borrar();
    }
    }


    public void Ubicar()
    {
    	String codigo = leerCodigo();// lee codigo
	// busca codigo leido
	NodoDoble auxiliar = ldco.busca(codigo);

	if(auxiliar != null)
        {//Si existe    
            Productos = auxiliar.getPro();
            muestraDatosProducto();
        }
        else if( Busqueda_BD())
            {// si lo encontro
                muestraBD();
            }
        else { // si no encontro
		corrigeCodigo();
                mensaje("El codigo no existe en la lista ni la BD");
             }
    }

    public void Modificar()
    {
        String codigo = leerCodigo();// lee codigo
		// busca codigo leido
	NodoDoble auxiliar = ldco.busca(codigo);

	if(auxiliar != null)
        {//Si existe
            Productos = auxiliar.getPro();
            modificarDatosProducto();
        }
        else if( Busqueda_BD())
            {// si lo encontro
                modificaBD();
            }
        else { // si no encontro
		corrigeCodigo();
            mensaje("El codigo no existe en la lista ni la BD");
             }
    }

    public void ascendente()
    {
Desaparece();
inicializaTabla();
NodoDoble auxiliar;
Orden = "ascendente";
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
			
            if(ReiniciarBD.equals("en_uso"))
            {
                        Enviar_aBD(Pro.getDescripcion(),
			Pro.getCodigo(),
				  		Pro.getCodigopro(),
				  		Pro.getprecio(),
                        Pro.getcantidad(),
                        Pro.getCategoria(),
                        Pro.getprecio()*Pro.getcantidad());

            }
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

	if(auxiliar != null || Busqueda_BD()){// si lo encontro
		mensaje("El codigo YA EXISTE");
		corrigeCodigo();
	} else {// no lo encontro
		ldco.agrega(Productos);
		prepararNuevosIngresos();
                ReiniciarBD ="en_uso";
	}
            }
            catch(Exception exc)
            {
            Calcular.setText("Error en las operaciones");
            }
            }

    public void mensaje(String texto){JOptionPane.showMessageDialog(this,texto);}

    public void Enviar_aBD(String descripcion, String codigo, int codigopro, double precio, int cantidad, String categoria, double d)
    {
        
        // Se mete todo en un try por los posibles errores de MySQL
        try {
            // Se registra el Driver de MySQL
   Class.forName("com.mysql.jdbc.Driver");
      // Se obtiene una conexión con la base de datos. Hay que
      // cambiar el usuario "root" y la clave "la_clave" por las
      // adecuadas a la base de datos que estemos usando.
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
          // Se crea un Statement, para realizar la consulta
           Statement st = conexion.createStatement();
           //Insertar en tabla
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");

           st.executeUpdate("INSERT INTO tabla (Codigo_de_venta,Descripcion,Codigo_de_producto,Precio,Cantidad,Categoria,Total) VALUES ('"+codigo+"','"+descripcion+"','"+codigopro+"','"+precio+"','"+cantidad+"','"+categoria+"','"+d+"')");

           
           System.out.println("Codigo de venta\tDescripcion\tCodigo de producto\tPrecio\tCantidad\tCategoria\tTotal\n");
           // Se realiza la consulta. Los resultados se guardan en el

           // ResultSet resp
           resp  = st.executeQuery("SELECT * FROM tabla");

            while(resp.next())
            {
               System.out.println(resp.getObject("Codigo_de_venta") +"\t\t"+ resp.getObject("Descripcion")+"\t\t"+resp.getObject("Codigo_de_producto")+"\t\t"+resp.getObject("Precio")+"\t\t"+resp.getObject("Cantidad")+"\t\t"+resp.getObject("Categoria")+"\t\t"+resp.getObject("Total"));
            }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
    }

    private void borrar() {
        txtDesc.setText("");
        txtPrecio.setText("");
        txtCant.setText("");
        txtCodVenta.setText("");
        txtCodpro.setText("");
        Calcular.setText("");
        txtCodVenta.requestFocus();
    }

    private void corrigeCodigo() {txtCodVenta.requestFocus();}

    public void descendente() {
Desaparece();
inicializaTabla();
       NodoDoble auxiliar;
Orden = "descendente";
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

            if(ReiniciarBD.equals("en_uso"))
            {
              Enviar_aBD(Pro.getDescripcion(),
			Pro.getCodigo(),
				  		Pro.getCodigopro(),
				  		Pro.getprecio(),
                        Pro.getcantidad(),
                        Pro.getCategoria(),
                        Pro.getprecio()*Pro.getcantidad());

            }
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
        Productos.setCodigopro(leerCodigoPro());
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
    //txtCateg.setText(""+Productos.getCategoria());
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
//............................
public int leerCodigoPro() { return Integer.parseInt(txtCodpro.getText()); }

public void Aparece()
{
            panTabla.setVisible(false);
            txtCant.setVisible(true);
            //txtCateg.setVisible(true);
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
            btnLimpiarT.setVisible(false);
}

public void Desaparece()
{
panTabla.setVisible(true);
txtCant.setVisible(false);
//txtCateg.setVisible(false);
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
btnLimpiarT.setVisible(true);
}

    public boolean Busqueda_BD()
    {
        boolean resul = false;
        try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");

           // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while(resp.next())
            {
                if(resp.getObject("Codigo_de_venta").equals(txtCodVenta.getText()))
                {
                    resul = true;
                }
            }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }

        return resul;
    }

    public void AgregaraTabla()
    {
              try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");
                      if(Orden.equals("ascendente"))
           {
               resp  = st.executeQuery("select Codigo_de_venta,Descripcion,Codigo_de_producto,Precio,Cantidad,Categoria,Total from tabla ORDER BY Codigo_de_venta asc;");
           }
           else
               resp  = st.executeQuery("select Codigo_de_venta,Descripcion,Codigo_de_producto,Precio,Cantidad,Categoria,Total from tabla ORDER BY Codigo_de_venta desc;");
           // Se recorre el ResultSet, mostrando por pantalla los resultados.
            while(resp.next())
            {
            Object[] datos = {resp.getObject("Descripcion"),resp.getObject("Codigo_de_venta"),resp.getObject("Codigo_de_producto"),resp.getObject("Precio"),resp.getObject("Cantidad"),resp.getObject("Categoria"),resp.getObject("Total")};//jalar de BD
            // agrega fila de datos en tabla
            panTabla.getMiModelo().addRow(datos);
            }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
    }

    public void muestraBD()
    {
                      try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");
           // Se recorre el ResultSet, mostrando por pantalla los resultados.
           resp = st.executeQuery("select *from tabla where(Codigo_de_venta='"+txtCodVenta.getText()+"');");
           while(resp.next())
           {
               String a = (String) resp.getObject("Codigo_de_venta");
               String b = (String) resp.getObject("Descripcion");
               Object c = resp.getObject("Codigo_de_Producto");
               Object d = resp.getObject("Precio");
               Object e =  resp.getObject("Cantidad");
               String f = (String) resp.getObject("Categoria");
               Object g =  resp.getObject("Total");
               txtCodVenta.setText(a);
               txtDesc.setText(b);
               txtCodpro.setText(String.valueOf(c));
               txtPrecio.setText(String.valueOf(d));
               txtCant.setText(String.valueOf(e));
               Cat.select(f);
               Calcular.setText(String.valueOf(g));

           }
           
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
    }

    private void modificaBD()
    {
    try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");
           // Se recorre el ResultSet, mostrando por pantalla los resultados.
           resp = st.executeQuery("select *from tabla where(Codigo_de_venta='"+txtCodVenta.getText()+"');");
           
           if(resp.next())
           {
               st.execute("update tabla set Descripcion='"+txtDesc.getText()+"',Codigo_de_producto='"+txtCodpro.getText()+"',Precio='"+txtPrecio.getText()+"',Cantidad='"+txtCant.getText()+"',Categoria='"+Cat.getSelectedItem()+"',Total='"+Calcular.getText()+"' Where Codigo_de_venta='"+txtCodVenta.getText()+"'");
           }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
    }

    public void EliminarBD()
    {
            try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");
           // Se recorre el ResultSet, mostrando por pantalla los resultados.
           resp = st.executeQuery("select *from tabla where(Codigo_de_venta='"+txtCodVenta.getText()+"');");

           if(resp.next())
           {
               st.execute("delete from tabla Where Codigo_de_venta like '"+txtCodVenta.getText()+"'");
           }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
    }

    public void EliminarED()
    {
        String codigo = leerCodigo();// lee codigo
        // busca codigo leido
	NodoDoble auxiliar = ldco.busca(codigo);

	if(auxiliar != null)
        {// si lo encontro
		ldco.elimina(auxiliar);
	} 
        else
        {// no lo encontro
		corrigeCodigo();
                mensaje("No puedo eliminar lo que no existe");
	}
    }
    public void Truncar()
{
        
     ReiniciarBD = "limpio";
                try {
   Class.forName("com.mysql.jdbc.Driver");
   conexion = DriverManager.getConnection(url,usuario,contraseña);
      if (conexion !=null)
      {
           Statement st = conexion.createStatement();
           ResultSet resp  = st.executeQuery("SELECT * FROM tabla");
           
           if(resp.next())
           {
               st.execute("delete from tabla where Codigo_de_venta>0;");
           }
           resp.close();
           st.close();
       }
   // Se cierra la conexión con la base de datos.
    conexion.close();
  }
    catch(SQLException ex){
        System.out.println(ex);
        }
  catch(ClassNotFoundException ex){
        System.out.println(ex);
        }
}
}

