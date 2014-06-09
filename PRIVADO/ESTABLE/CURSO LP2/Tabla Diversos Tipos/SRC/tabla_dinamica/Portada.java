
package tabla_dinamica;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Portada  extends JFrame implements ActionListener
{
String contrase√±a;
String usuario;
String url="jdbc:mysql://localhost:3306/listas";
JLabel lblcodigo = new JLabel("Codigo");
JLabel lblApynom = new JLabel("Apellidos y nombres");
JLabel lblexpar = new JLabel("Examen parcial");
JLabel lblexfin = new JLabel("Examen final");
JLabel lblprompp = new JLabel("EPromedio de practicas");
JLabel Promfin = new JLabel("Promedio final");

JTextField txtcodigo = new JTextField();
JTextField txtApynom = new JTextField();
JTextField txtPromfin = new JTextField();

JComboBox combexpar = new JComboBox();
JComboBox combexfinr = new JComboBox();
JComboBox combprompp = new JComboBox();

JButton btnAceptar = new JButton("Aceptar");
JButton btnCancelar = new JButton("Cancelar");
JButton btnReinicar = new JButton("Reiniciar");
JLabel vacio = new JLabel("");
static ListaDobleConOrden ldco = new ListaDobleConOrden();
Productos Productos;
private PanelTabla	panTabla;
public Portada()
{
setSize(450,500);
setTitle("Programa");
setLocation(250,150);

lblcodigo.setBounds(50,50, 100, 20);
txtcodigo.setBounds(180,50, 100, 20);
add(lblcodigo);
add(txtcodigo);

lblApynom.setBounds(50,90, 100, 20);
txtApynom.setBounds(180,90, 100, 20);
add(lblApynom);
add(txtApynom);

lblexpar.setBounds(50,130, 100, 20);
combexpar.setBounds(180,130, 100, 20);

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
combexfinr.setBounds(180,170, 100, 20);
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

lblprompp.setBounds(50,210, 100, 20);
combprompp.setBounds(180,210, 100, 20);
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
txtPromfin.setBounds(180,250, 100, 20);
add(Promfin);
add(txtPromfin);
panTabla = new PanelTabla();
        MenuBar barra = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu menu1 = new Menu("Eliminar");

        MenuItem check = new MenuItem("Modificar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Ubicar");
        check.addActionListener(this);
        menu.add(check);

        check = new MenuItem("Eliminar Nodo");
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

btnAceptar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) 
            
            {
                try
                {
            int expar1 =Integer.parseInt(String.valueOf(combexpar.getSelectedItem()));
            int exfin1 =Integer.parseInt(String.valueOf(combexfinr.getSelectedItem()));
            int prompp1 =Integer.parseInt(String.valueOf(combprompp.getSelectedItem()));
            txtPromfin.setText(String.valueOf((exfin1+expar1+prompp1)/3.0));

ingresaDatosProducto();

        NodoDoble auxiliar = ldco.busca(Productos.getCodigo());
	if(auxiliar != null){// si lo encontro
		mensaje("El codigo YA EXISTE");
		corrigeCodigo();
	} else {// no lo encontro
		ldco.agrega(Productos);
		borrar();
	}
                }
                catch(Exception p)
                {
                txtPromfin.setText("Error en las operaciones");
                }
           
            }
        });
     btnCancelar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            borrar();
            }
        });

       btnReinicar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                ldco.setInicio(null);
            }

        });

btnAceptar.setBounds(70,310,100,20);
btnCancelar.setBounds(210,310,100,20);
btnReinicar.setBounds(70,340,100,20);
add(btnCancelar);
add(btnAceptar);
add(btnReinicar);
add(vacio);
}

    public static void main(String[] args)
    {
      Portada obj = new Portada();
      obj.show();
      obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

     if(e.getActionCommand().equals("Modificar"))
    {
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

    else if(e.getActionCommand().equals("Eliminar Nodo"))
    {
        EliminarED();
    }

    }


    public void EliminarED()
    {
        String codigo = leerCodigo();
	NodoDoble auxiliar = Portada.ldco.busca(codigo);

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

    public void ascendente()
    {
        panTabla.setVisible(true);
inicializaTabla();
NodoDoble auxiliar;

	if(Portada.ldco.getInicio() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");
	else {
		// primero de la lista
		auxiliar = Portada.ldco.getInicio();
		// recorrido secuencial hacia la derecha
		while(auxiliar != null)	{
			// obtiene un empleado
			Productos Pro = auxiliar.getPro();
                        Object[] datos = {	Pro.getCodigo(),
				  		Pro.getApellidos(),
				  		Pro.getExamenPar(),
				  		Pro.getExamenFin(),
                        Pro.getPrompp(),
                        Pro.getPromFin(),
				  	   };

                        // agrega fila de datos en tabla
			panTabla.getMiModelo().addRow(datos);

			//avanza siguiente nodo
 			auxiliar= auxiliar.getApuntSgte();
		}// fin del while
	}// fin del else
    }

    public void descendente() {
        panTabla.setVisible(true);
inicializaTabla();
       NodoDoble auxiliar;

	if(Portada.ldco.getInicio() == null)
		mensaje("NO HAY INFORMACION REGISTRADA");
	else {
		// ultimo de la lista
		auxiliar = Portada.ldco.getFin();
		// recorrido secuencial hacia la izquierda
		while(auxiliar != null)	{
			// obtiene un empleado
			Productos Pro = auxiliar.getPro();
                        Object[] datos = {	Pro.getCodigo(),
				  		Pro.getApellidos(),
				  		Pro.getExamenPar(),
				  		Pro.getExamenFin(),
                        Pro.getProm   ˚˚ˇ      ê     @  Arial   Q                   ??   -    .        	      2
f % 	   HUARINGA              .     -        ˚˚ˇ      ê     @  Arial   Q                   ??   -    .        	      2
f B    HUARINGA           .     -        ˚˚ˇ      ê     @  Arial   Q                   ??   -    .        	      2
f _    ROGERN        .     -        ˚˚ˇ      ê     @  Arial   E                   ??   -    .        	   %   2
m +    PEREZ BALDOCEDA ABEL                       .     -                                                                                                                                                                                                                                                                                                                                                                                                    itiva                                                           ˆ      _¿ë„v   Ù C MANUEL   M A N U E L                                                                                                                                                                                                                                                                                                                                                                                                               C u r r e n t   U s e r                                           ˇˇˇˇˇˇˇˇˇˇˇˇ                                    	   2                                                                           ˇˇˇˇˇˇˇˇˇˇˇˇ                                                                                                                    ˇˇˇˇˇˇˇˇˇˇˇˇ                                                                                                                    ˇˇˇˇˇˇˇˇˇˇˇˇ                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   