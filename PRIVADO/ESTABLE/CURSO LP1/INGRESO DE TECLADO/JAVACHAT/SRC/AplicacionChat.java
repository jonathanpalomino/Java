
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
public class AplicacionChat extends JFrame
{
	private JEditorPane area=new JEditorPane();
	private JButton aceptar=new JButton("Aceptar");
	private JButton salir=new JButton("Salir");
	private JTextField caja=new JTextField("127.0.0.1");
	AdministradorMensajes administradorMensajes;
	Manejador jefe=new Manejador();
	public AplicacionChat()
	{
		super("Configuraci�n de la conexi�n...");
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setLayout(new BorderLayout(5,5));
		Container abajo=new Container();
		abajo.setLayout(new GridLayout(2,1,5,5));
		Container botones=new Container();
		botones.setLayout(new GridLayout(1,2,5,5));
		botones.add(aceptar);
		botones.add(salir);
		abajo.add(caja);
		abajo.add(botones);
		area.setEditable(false);
		try
		{
			area.setPage("file:"+System.getProperty("user.dir")+System.getProperty("file.separator")+"recursos/Inicio.htm");
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
		getContentPane().add(new JScrollPane(area));
		getContentPane().add(abajo,BorderLayout.SOUTH);
		
		aceptar.setForeground(Color.RED);
		aceptar.setOpaque(false);
		salir.setForeground(Color.RED);
		salir.setOpaque(false);
		
		caja.addActionListener(jefe);
		aceptar.addActionListener(jefe);
		salir.addActionListener(jefe);
		
		setSize(430,460);
		setVisible(true);
	}
	private class Manejador implements ActionListener
	{
		public void actionPerformed(ActionEvent evento)
		{
			if(evento.getSource()==caja||evento.getSource()==aceptar)
			{
				if(caja.getText().trim().equals(""))
					JOptionPane.showMessageDialog(null,"Debes indicar una direccion IP o nombre del PC","Error",JOptionPane.ERROR_MESSAGE);
				else
				{
					administradorMensajes=new AdministradorSocketsMensajes(caja.getText());
					GUIChat guiCliente=new GUIChat(administradorMensajes);
					AplicacionChat.this.setVisible(false);
				}
			}
			if(evento.getSource()==salir)
			{
				System.exit(0);
			}
		}
	}
	public static void main( String args[] ) 
	{
		AplicacionChat ventana=new AplicacionChat();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
