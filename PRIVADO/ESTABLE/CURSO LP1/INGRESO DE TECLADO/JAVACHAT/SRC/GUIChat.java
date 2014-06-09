
import javax.swing.text.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
public class GUIChat extends JFrame
{
	//Declaraci�n de componentes de la GUI
	//y variables que se utilizan
	private JMenuBar barraMenu;
	private JMenu menuArchivo,menuAyuda,menuConfigurar,menuVista,menuTemas;
	private JMenuItem itemConectar,itemDesconectar,itemSalir,itemAcerca,itemAyuda,itemSwing,itemBlackW,itemWhiteB,itemBlackR,itemWhiteR,itemWhiteA,itemMoraA,itemNaranjaB,itemRojoB,itemRosaL;
	private JRadioButtonMenuItem radioWindows,radioLinux,radioJava;
	private ButtonGroup grupoVistas;
	private UIManager.LookAndFeelInfo apariencias[];
	private JTextPane areaMensajes;
	private Document doc;
	private JTextField cajaEnviar;
	private JPanel panelEmoticones[];
	private Avatares avatar;
	private Cursor cursor;
	private JList listaUsuarios;
	private JButton botonEnviar,botonColorLetra;
	private JLabel estado;
	private String nombresUsuarios[]={" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "};
	private String smyles[]={":)",";)",":D",";D",">:(",":(",":o","8)","???","::)",":P",":-[",":-X",":-\\",":-*",":'("};
	private String nombreUsuario="";
	private JComboBox comboFuente;
	private Color colorLetra,colorFondo;
	private JCheckBox casillaNegrilla,casillaCursiva;
	private Container cEste,cCentro,cEnviar,cEnvio,cEdicion,cEstilo,cEmoticones,cTotal;
	private ManejadorEventos jefe;
	private ManejadorEstilo estilista;
	private int estilos;
	private Estilo estilo;
	private AdministradorMensajes administradorMensajes;
	private EscuchaMensajes escuchaMensajes;
	//Constructor de la GUI
	public GUIChat(AdministradorMensajes admin)
	{
		//inicializaci�n de objetos que manejan el chat y contenedores
		super("Casidiablo Messenger");
		getContentPane().setLayout(new BorderLayout(4,4));
		apariencias=UIManager.getInstalledLookAndFeels();
		jefe=new ManejadorEventos();
		estilista=new ManejadorEstilo();
		estilo=new Estilo();
		administradorMensajes=admin;
		escuchaMensajes=new MiEscuchaMensajes();
		
		//establecer la barra de Menu
		barraMenu=new JMenuBar();
		menuArchivo=new JMenu("Archivo");
		menuArchivo.setMnemonic('A');
		itemConectar=new JMenuItem("Conectar",new ImageIcon("imagenes/useron.gif"));
		itemConectar.setMnemonic('C');
		itemConectar.addActionListener(jefe);
		itemDesconectar=new JMenuItem("Desconectar",new ImageIcon("imagenes/useroff.gif"));
		itemDesconectar.setMnemonic('D');
		itemDesconectar.setEnabled(false);
		itemDesconectar.addActionListener(jefe);
		itemSalir=new JMenuItem("Salir",new ImageIcon("imagenes/apagar2.jpg"));
		itemSalir.setMnemonic('S');
		itemSalir.addActionListener(jefe);
		menuArchivo.add(itemConectar);
		menuArchivo.add(itemDesconectar);
		menuArchivo.addSeparator();
		menuArchivo.add(itemSalir);
		menuAyuda=new JMenu("Ayuda");
		menuAyuda.setMnemonic('y');
		itemAcerca=new JMenuItem("Acerca de...");
		itemAcerca.setMnemonic('c');
		itemAcerca.addActionListener(jefe);
		itemAyuda=new JMenuItem("Manual de Usuario",new ImageIcon("imagenes/ayuda.gif"));
		itemAyuda.addActionListener(jefe);
		itemAyuda.setMnemonic('M');
		menuAyuda.add(itemAcerca);
		menuAyuda.add(itemAyuda);
		menuConfigurar=new JMenu("Configurar");
		menuConfigurar.setMnemonic('o');
		menuVista=new JMenu("Vista...");
		menuVista.setMnemonic('V');
		grupoVistas=new ButtonGroup();
		radioWindows=new JRadioButtonMenuItem("Windows");
		grupoVistas.add(radioWindows);
		radioWindows.addActionListener(jefe);
		radioLinux=new JRadioButtonMenuItem("UNIX/Linux");
		grupoVistas.add(radioLinux);
		radioLinux.addActionListener(jefe);
		radioJava=new JRadioButtonMenuItem("Swing Java");
		grupoVistas.add(radioJava);
		radioJava.setSelected(true);
		radioJava.addActionListener(jefe);
		menuTemas=new JMenu("Temas...");
		menuTemas.setMnemonic('T');
		itemSwing=new JMenuItem("Java Swing",new ImageIcon("imagenes/eSwing.jpg"));
		itemSwing.addActionListener(jefe);
		itemBlackW=new JMenuItem("Negro-Blanco",new ImageIcon("imagenes/eNB.jpg"));
		itemBlackW.addActionListener(jefe);
		itemWhiteB=new JMenuItem("Blanco-Negro",new ImageIcon("imagenes/eBN.jpg"));
		itemWhiteB.addActionListener(jefe);
		itemBlackR=new JMenuItem("Negro-Rojo",new ImageIcon("imagenes/eNR.jpg"));
		itemBlackR.addActionListener(jefe);
		itemWhiteR=new JMenuItem("Blanco-Rojo",new ImageIcon("imagenes/eBR.jpg"));
		itemWhiteR.addActionListener(jefe);
		itemWhiteA=new JMenuItem("Blanco-Azul",new ImageIcon("imagenes/eBA.jpg"));
		itemWhiteA.addActionListener(jefe);
		itemMoraA=new JMenuItem("Mora-Blanco",new ImageIcon("imagenes/eMB.jpg"));
		itemMoraA.addActionListener(jefe);
		itemNaranjaB=new JMenuItem("Naranja-Negro",new ImageIcon("imagenes/eNN.jpg"));
		itemNaranjaB.addActionListener(jefe);
		itemRojoB=new JMenuItem("Rojo-Negro",new ImageIcon("imagenes/eRN.jpg"));
		itemRojoB.addActionListener(jefe);
		itemRosaL=new JMenuItem("Rosa-Lila",new ImageIcon("imagenes/eRL.jpg"));
		itemRosaL.addActionListener(jefe);
		menuTemas.add(itemSwing);
		menuTemas.add(itemBlackW);
		menuTemas.add(itemWhiteB);
		menuTemas.add(itemBlackR);
		menuTemas.add(itemWhiteR);
		menuTemas.add(itemWhiteA);
		menuTemas.add(itemMoraA);
		menuTemas.add(itemNaranjaB);
		menuTemas.add(itemRojoB);
		menuTemas.add(itemRosaL);
		menuVista.add(radioWindows);
		menuVista.add(radioLinux);
		menuVista.add(radioJava);
		menuConfigurar.add(menuVista);
		menuConfigurar.add(menuTemas);
		barraMenu.add(menuArchivo);
		barraMenu.add(menuConfigurar);
		barraMenu.add(menuAyuda);
		setJMenuBar(barraMenu);
		
		//Inicializar componentes de la GUI
		colorLetra=Color.BLACK;
		cursor=new Cursor(Cursor.HAND_CURSOR);
		colorFondo=Color.WHITE;
		areaMensajes=new JTextPane();
		areaMensajes.setToolTipText("Aqui podra ver los mensajes escritos");
		areaMensajes.setEnabled(false);
		doc=areaMensajes.getDocument();
		panelEmoticones=new Emoticones[16];
		cEmoticones=new Container();
		cEmoticones.setLayout(new GridLayout(2,8));
		for(int i=0;i<16;i++) {
			final int j=i;
			panelEmoticones[i]=new Emoticones(i+1);
			panelEmoticones[i].setToolTipText("Coloca un Smyle en la caja de texto para ser enviado");
			panelEmoticones[i].addMouseListener(
				new MouseListener()
				{
					public void mouseClicked(MouseEvent evento)
					{
						String t=cajaEnviar.getText();
						t+=" "+smyles[j]+" ";
						cajaEnviar.setText(t);
					}
					public void mousePressed(MouseEvent evento){}
					public void mouseReleased(MouseEvent evento){}
					public void mouseEntered(MouseEvent evento){}
					public void mouseExited(MouseEvent evento){}
				}
			);
			cEmoticones.add(panelEmoticones[i]);
		}
		listaUsuarios=new JList(nombresUsuarios);
		listaUsuarios.setToolTipText("Aqui se pueden ver los usuarios actualmente conectados");
		avatar=new Avatares("imagenes/avatares/avatar ("+(int)(Math.random()*12)+").jpg",131,131);
		avatar.setCursor(cursor);
		avatar.setToolTipText("Presione aui para cambiar su avatar");
		avatar.addMouseListener(
			new MouseListener()
			{
				public void mouseClicked(MouseEvent evento)
				{
					new Avatar();
				}
				public void mousePressed(MouseEvent evento){}
				public void mouseReleased(MouseEvent evento){}
				public void mouseEntered(MouseEvent evento){}
				public void mouseExited(MouseEvent evento){}
			}
		);
		botonColorLetra=new JButton("Color de Letra",new ImageIcon("imagenes/face.gif"));
		botonColorLetra.setToolTipText("Cambia el color de fuente");
		botonColorLetra.addActionListener(jefe);
		botonEnviar=new JButton("Enviar",new ImageIcon("imagenes/enviar.gif"));
		botonEnviar.setToolTipText("Envia el mensaje editado al chat");
		botonEnviar.setEnabled(false);
		botonEnviar.addActionListener(jefe);
		cajaEnviar=new JTextField(18);
		cajaEnviar.setToolTipText("Digite aqui el mensaje a enviar");
		cajaEnviar.setEditable(false);
		cajaEnviar.addActionListener(jefe);
		comboFuente=new JComboBox();
		comboFuente.addItem("Serif");
		comboFuente.addItem("SansSerif");
		comboFuente.addItem("Monospaced");
		comboFuente.setSelectedIndex(1);
		comboFuente.setToolTipText("Aqui podr� seleccionar el tipo de la letra");
		comboFuente.addItemListener(
			new ItemListener()
			{
				public void itemStateChanged(ItemEvent evento)
				{
					if(evento.getStateChange()==ItemEvent.SELECTED)
					{
						String f="SansSerif";
						if(comboFuente.getSelectedIndex()==0)
							f="Serif";
						else if(comboFuente.getSelectedIndex()==1)
							f="SansSerif";
						else if(comboFuente.getSelectedIndex()==2)
							f="Monospaced";
						estilo.asignarNombreFuente(f);
						actualizarFuente();
					}
				}
			}
		);
		casillaNegrilla=new JCheckBox("Negrita");
		casillaNegrilla.setToolTipText("Establece la letra en Negrita");
		casillaNegrilla.addItemListener(estilista);
		casillaCursiva=new JCheckBox("Cursiva");
		casillaCursiva.setToolTipText("Establece la letra en Cursiva");
		casillaCursiva.addItemListener(estilista);
		estado=new JLabel("Su estado es: ",new ImageIcon("imagenes/useroff.gif"),SwingConstants.RIGHT);
		estado.setBorder(new BevelBorder(BevelBorder.LOWERED));
		estado.setToolTipText("Muestra el estado de la conexion");
		estado.setHorizontalTextPosition(SwingConstants.LEFT);
		
		//A�adir los componentes a los contenedores correspondientes
		cEstilo=new Container();
		cEstilo.setLayout(new GridLayout(2,2,5,5));
		cEstilo.add(casillaNegrilla);
		cEstilo.add(casillaCursiva);
		cEstilo.add(botonColorLetra);
		cEstilo.add(comboFuente);
		
		//A�adir los componentes a los contenedores correspondientes
		cEdicion=new Container();
		cEdicion.setLayout(new BorderLayout());
		cEmoticones.setCursor(cursor);
		cEdicion.add(cEmoticones);
		cEdicion.add(cEstilo,BorderLayout.EAST);
		
		//A�adir los componentes a los contenedores correspondientes
		cEnvio=new Container();
		cEnvio.setLayout(new BorderLayout());
		cEnvio.add(cajaEnviar);
		cEnvio.add(botonEnviar,BorderLayout.EAST);
		
		//A�adir los componentes a los contenedores correspondientes
		cEnviar=new Container();
		cEnviar.setLayout(new BorderLayout());
		cEnviar.add(cEdicion);
		cEnviar.add(cEnvio,BorderLayout.NORTH);
		
		cCentro=new Container();
		cCentro.setLayout(new BorderLayout());
		cCentro.add(new JScrollPane(areaMensajes));
		cCentro.add(cEnviar,BorderLayout.SOUTH);
		
		//A�adir los componentes a los contenedores correspondientes
		cEste=new Container();
		cEste.setLayout(new GridLayout(2,1));
		cEste.add(new JScrollPane(listaUsuarios));
		cEste.add(avatar);
		
		//A�adir los componentes a los contenedores correspondientes
		cTotal=new Container();
		cTotal.setLayout(new BorderLayout());
		cTotal.add(cCentro);
		cTotal.add(cEste,BorderLayout.EAST);
		
		//A�adir los componentes a los contenedores correspondientes
		getContentPane().add(cTotal);
		getContentPane().add(estado,BorderLayout.SOUTH);
		
		cambiarEstilo(Color.BLACK,Color.RED,Color.LIGHT_GRAY);
		addWindowListener(
			new WindowAdapter()
			{
				public void windowClosing ( WindowEvent evento )
				{
					administradorMensajes.desconectar( escuchaMensajes );
					System.exit( 0 );
				}
			}
		);
		setSize(690,335);
		setResizable(false);
		setVisible(true);
	}
	//Clase interna anonima para manejar eventos
	private class ManejadorEventos implements ActionListener
	{
		public void actionPerformed(ActionEvent evento)
		{
			int indice=0;
			if(evento.getSource()==radioJava)
			{
				indice=0;
				cambiarLaAparienciaVisual(indice);
			}
			if(evento.getSource()==radioLinux)
			{	indice=1;
				cambiarLaAparienciaVisual(indice);
			}
			if(evento.getSource()==radioWindows)
			{
				indice=2;
				cambiarLaAparienciaVisual(indice);
			}
			if(evento.getSource()==botonEnviar||evento.getSource()==cajaEnviar)
			{
				administradorMensajes.enviarMensaje(nombreUsuario,cajaEnviar.getText());
				cajaEnviar.setText("");
			}
			if(evento.getSource()==itemConectar)
			{
				administradorMensajes.conectar( escuchaMensajes );
				nombreUsuario=JOptionPane.showInputDialog(GUIChat.this, "digite su nick de usuario:");
				areaMensajes.setText( "" );
				itemConectar.setEnabled( false );
				itemDesconectar.setEnabled( true );
				botonEnviar.setEnabled( true );
				cajaEnviar.setEditable( true );
				cajaEnviar.requestFocus();
				estado.setText(nombreUsuario+" se encuentra conectado");
				estado.setIcon(new ImageIcon("imagenes/useron.gif"));
				administradorMensajes.enviarMensaje(nombreUsuario,Constantes.NUEVO_USUARIO);
			}
			if(evento.getSource()==itemDesconectar)
			{
				administradorMensajes.enviarMensaje(nombreUsuario,Constantes.CADENA_DESCONEXION);
				administradorMensajes.desconectar( escuchaMensajes );
				botonEnviar.setEnabled( false );
				itemDesconectar.setEnabled( false );
				cajaEnviar.setEditable( false );
				itemConectar.setEnabled( true );
				estado.setIcon(new ImageIcon("imagenes/useroff.gif"));
			}
			if(evento.getSource()==itemAyuda)
			{
				new Manual();
			}
			if(evento.getSource()==itemSalir)
			{
				administradorMensajes.desconectar( escuchaMensajes );
				System.exit( 0 );
			}
			if(evento.getSource()==itemAcerca)
			{
				String s="Copyleft: 'Casidiablo Chat' es softawre libre;";
				s+="\ntu puedes redistribuir y/o modificar este codigo";
				s+="\nbajo los t�rminos de la Licencia Publica General";
				s+="\n(GNU) version 2 o superior como es etipulado por";
				s+="\nla Fundacion de Software Libre.\n";
				s+="\nAutores: Christian Castiblanco... casidiablo";
				s+="\n         Ra�l Andr�s Ascencio... ASpen\n";
				s+="Contactos: castidiablo@gmail.com\n";
				s+="           rulascencio@gmail.com\n";
				s+="           http://foro.elhacker.net";
				s+="\n\n      Masturba tu cerebro y eyacular�n tus ideas";
				JTextArea a=new JTextArea(s);
				a.setBackground(Color.BLACK);
				a.setForeground(Color.RED);
				a.setEditable(false);
				a.setFont(new Font("Monospaced",1,13));
				JOptionPane.showMessageDialog(null,a,"Acerca de Casidiablo Chat",JOptionPane.PLAIN_MESSAGE);
			}
			if(evento.getSource()==itemSalir)
			{
				administradorMensajes.desconectar( escuchaMensajes );
				System.exit( 0 );
			}
			if(evento.getSource()==botonColorLetra)
			{
				colorLetra=JColorChooser.showDialog(GUIChat.this,"Seleccione el color de la letra",colorLetra);
				estilo.asignarColor(colorLetra);
				actualizarFuente();
			}
			if(evento.getSource()==itemBlackW)
			{
				cambiarEstilo(Color.BLACK,Color.WHITE,Color.GRAY);
			}
			if(evento.getSource()==itemWhiteB)
			{
				cambiarEstilo(Color.WHITE,Color.BLACK,Color.WHITE);
			}
			if(evento.getSource()==itemBlackR)
			{
				cambiarEstilo(Color.BLACK,Color.RED,Color.LIGHT_GRAY);
			}
			if(evento.getSource()==itemWhiteR)
			{
				cambiarEstilo(Color.WHITE,Color.RED,Color.WHITE);
			}
			if(evento.getSource()==itemWhiteA)
			{
				cambiarEstilo(Color.WHITE,Color.BLUE,Color.WHITE);
			}
			if(evento.getSource()==itemMoraA)
			{
				cambiarEstilo(new Color(125,27,213),Color.WHITE,new Color(72,10,49));
			}
			if(evento.getSource()==itemNaranjaB)
			{
				cambiarEstilo(new Color(255,166,0),Color.BLACK,new Color(255,192,3));
			}
			if(evento.getSource()==itemRojoB)
			{
				cambiarEstilo(new Color(255,0,0),Color.BLACK,new Color(255,77,0));
			}
			if(evento.getSource()==itemSwing)
			{
				cambiarEstilo(Color.LIGHT_GRAY,Color.BLACK,Color.WHITE);
			}
			if(evento.getSource()==itemRosaL)
			{
				cambiarEstilo(new Color(255,161,161),new Color(152,17,141),new Color(255,161,247));
			}
		}
	}
	//Cambiar apariencia visual de la GUI
	private void cambiarLaAparienciaVisual( int valor ) {
		try {
			UIManager.setLookAndFeel( apariencias[ valor ].getClassName() );
			SwingUtilities.updateComponentTreeUI( this );
		}
		catch ( Exception excepcion ) {
			excepcion.printStackTrace();
		}
	}
	private void cambiarEstilo(Color comp,Color letra,Color fondo)
	{
		getContentPane().setBackground(comp);
		barraMenu.setBackground(comp);
		menuArchivo.setBackground(comp);
		menuAyuda.setBackground(comp);
		menuConfigurar.setBackground(comp);
		menuVista.setBackground(comp);
		menuTemas.setBackground(comp);
		itemConectar.setBackground(comp);
		itemDesconectar.setBackground(comp);
		itemSalir.setBackground(comp);
		itemAcerca.setBackground(comp);
		itemAyuda.setBackground(comp);
		itemBlackW.setBackground(comp);
		itemWhiteB.setBackground(comp);
		itemBlackR.setBackground(comp);
		itemWhiteR.setBackground(comp);
		itemSwing.setBackground(comp);
		itemWhiteA.setBackground(comp);
		itemMoraA.setBackground(comp);
		itemNaranjaB.setBackground(comp);
		itemRojoB.setBackground(comp);
		itemRosaL.setBackground(comp);
		radioWindows.setBackground(comp);
		radioLinux.setBackground(comp);
		radioJava.setBackground(comp);
		areaMensajes.setBackground(fondo);
		cajaEnviar.setBackground(fondo);
		for(int i=0;i<16;i++)
			panelEmoticones[i].setBackground(comp);
		avatar.setBackground(comp);
		listaUsuarios.setBackground(comp);
		botonEnviar.setBackground(comp);
		botonColorLetra.setBackground(comp);
		estado.setBackground(comp);
		comboFuente.setBackground(comp);
		casillaNegrilla.setBackground(comp);
		casillaCursiva.setBackground(comp);
		cEste.setBackground(comp);
		cCentro.setBackground(comp);
		cEnviar.setBackground(comp);
		cEnvio.setBackground(comp);
		cEdicion.setBackground(comp);
		cEstilo.setBackground(comp);
		cEmoticones.setBackground(comp);
		cTotal.setBackground(comp);
		
		barraMenu.setForeground(letra);
		menuArchivo.setForeground(letra);
		menuAyuda.setForeground(letra);
		menuConfigurar.setForeground(letra);
		menuVista.setForeground(letra);
		menuTemas.setForeground(letra);
		itemConectar.setForeground(letra);
		itemDesconectar.setForeground(letra);
		itemSalir.setForeground(letra);
		itemAcerca.setForeground(letra);
		itemAyuda.setForeground(letra);
		itemBlackW.setForeground(letra);
		itemWhiteB.setForeground(letra);
		itemBlackR.setForeground(letra);
		itemWhiteR.setForeground(letra);
		itemSwing.setForeground(letra);
		itemWhiteA.setForeground(letra);
		itemMoraA.setForeground(letra);
		itemNaranjaB.setForeground(letra);
		itemRojoB.setForeground(letra);
		itemRosaL.setForeground(letra);
		radioWindows.setForeground(letra);
		radioLinux.setForeground(letra);
		radioJava.setForeground(letra);
		areaMensajes.setForeground(letra);
		cajaEnviar.setForeground(letra);
		listaUsuarios.setForeground(letra);
		botonEnviar.setForeground(letra);
		botonColorLetra.setForeground(letra);
		estado.setForeground(letra);
		comboFuente.setForeground(letra);
		casillaNegrilla.setForeground(letra);
		casillaCursiva.setForeground(letra);
		cEste.setForeground(letra);
		cCentro.setForeground(letra);
		cEnviar.setForeground(letra);
		cEnvio.setForeground(letra);
		cEdicion.setForeground(letra);
		cEstilo.setForeground(letra);
		cEmoticones.setForeground(letra);
		cTotal.setForeground(letra);
	}
	private class ManejadorEstilo implements ItemListener
	{
		public void itemStateChanged(ItemEvent e)
		{
			estilos=0;
			if(casillaNegrilla.isSelected())
				estilos+=Font.BOLD;
			if(casillaCursiva.isSelected())
				estilos+=Font.ITALIC;
			estilo.asignarTipo(estilos);
			actualizarFuente();
		}
	}
	private void actualizarFuente()
	{
		cajaEnviar.setFont(estilo.obtenerFuente());
		cajaEnviar.setForeground(estilo.obtenerColor());
	}
	private class Avatar extends JFrame
	{
		Avatares arr[];
		manejador m=new manejador();
		public Avatar()
		{
			getContentPane().setLayout(new GridLayout(3,4));
			arr=new Avatares[12];
			for(int i=0;i<12;i++)
			{
				arr[i]=new Avatares("imagenes/avatares/avatar ("+i+").jpg",146,146);
				arr[i].setCursor(cursor);
				getContentPane().add(arr[i]);
				arr[i].addMouseListener(m);
			}
			setSize(595,455);
			setVisible(true);
		}
		private class manejador implements MouseListener
		{
			public void mouseClicked(MouseEvent evento){
				int x=0;
				for(int i=0;i<12;i++)
					if(evento.getSource().equals(arr[i]))
					{
						x=i;
						break;
					}
				avatar.asignar(arr[x].obtenerNombre());
				hide();
				dispose();
			}
			public void mousePressed(MouseEvent evento){}
			public void mouseReleased(MouseEvent evento){}
			public void mouseEntered(MouseEvent evento){}
			public void mouseExited(MouseEvent evento){}
		}
	}
	private class Manual extends JFrame
	{
		private JEditorPane area=new JEditorPane();
		public Manual()
		{
			getContentPane().setLayout(new BorderLayout());
			area.setEditable(false);
			try
			{
				area.setPage("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"recursos/Manual/Inicio.htm");
			}
			catch(IOException exp)
			{
				exp.printStackTrace();
			}
			area.addHyperlinkListener(
				new HyperlinkListener()
				{
					public void hyperlinkUpdate(HyperlinkEvent evento)
					{
						if (evento.getEventType()==HyperlinkEvent.EventType.ACTIVATED)
						{
							try
							{
								area.setPage(evento.getURL().toString());
							}
							catch(IOException exp)
							{
								exp.printStackTrace();
							}
						}
					}
				}
			);
			addWindowListener(
				new WindowAdapter()
				{
					public void windowClosing ( WindowEvent evento )
					{
						Manual.this.setVisible(false);
					}
				}
			);
			getContentPane().add(new JScrollPane(area));
			setSize(800,500);
			setVisible(true);
		}
	}
	private class MiEscuchaMensajes implements EscuchaMensajes
	{
		public void mensajeRecibido(String de, String mensaje)
		{
			if(mensaje.equals(Constantes.NUEVO_USUARIO))
			{
				StringTokenizer partes=new StringTokenizer(de,".....");
				int i=0;
				while(partes.hasMoreTokens())
				{
					nombresUsuarios[i]=partes.nextToken();
					++i;
				}
				SwingUtilities.invokeLater(
					new Runnable()
					{
						public void run()
						{
							listaUsuarios=new JList(nombresUsuarios);
						}
					}
				);
			}
			else
				SwingUtilities.invokeLater(new MostradorMensajes(de,mensaje));
		}
	}
	private class MostradorMensajes implements Runnable
	{
		private String deUsuario;
		private String cuerpoMensaje;
		public MostradorMensajes( String de, String cuerpo )
		{
			deUsuario = de;
			cuerpoMensaje = cuerpo;
		}
		public void run()
		{
			try{
				doc.insertString(doc.getLength(),capturarHora(),null);
			}
			catch (BadLocationException ble)
			{
				JOptionPane.showMessageDialog(null,"Ocurri� un error al intentar acomodar el mesaje, lo siento :-(","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			StringTokenizer tokens=new StringTokenizer(deUsuario+" dice: "+cuerpoMensaje);
			while(tokens.hasMoreTokens())
			{
				String s=tokens.nextToken();
				JLabel t=new JLabel(" "+s+" ");
				for(int i=0;i<16;i++)
				{
					if(s.equals(smyles[i]))
					{
						t.setText("");
						t.setIcon(new ImageIcon("imagenes/"+(i+1)+".gif"));
						break;
					}
				}
				t.setFont(estilo.obtenerFuente());
				t.setForeground(estilo.obtenerColor());
				areaMensajes.insertComponent(t);
			}
			try{
				doc.insertString(doc.getLength(),"\n",null);
			}
			catch (BadLocationException ble)
			{
				JOptionPane.showMessageDialog(null,"Ocurri� un error al intentar acomodar el mesaje, lo siento :-(","ERROR",JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	public String capturarHora()
	{
		Date fechaActual=new Date();
		SimpleDateFormat formato=new SimpleDateFormat ("hh:mm",Locale.getDefault());
		return "["+formato.format(fechaActual)+"]";
	}
}
