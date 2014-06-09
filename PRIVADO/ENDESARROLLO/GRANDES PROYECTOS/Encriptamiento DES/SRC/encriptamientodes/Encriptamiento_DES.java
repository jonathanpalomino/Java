package encriptamientodes;

/**
 *
 * @author JONATHAN
 */
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Encriptamiento_DES extends JFrame {

	private JLabel EInputTextLabel, DInputTextLabel;

	private JTextArea EInputText,EOutputText, DInputText, DOutputText;
	private JButton btnEncriptar, btnDesencriptar;                          /* GUI components used */
	private JPasswordField EPasswordField,DPasswordField;


	private JPopupMenu popupMenu1, popupMenu2, popupMenu3,
	                   popupMenu4;                             /* four popup menus used,
	                                                              one for each JTextField */

	private JMenuItem pop1a, pop1b, pop1c, pop1d, pop1e,
	                  pop2a, pop2b, pop2c, pop2d, pop2e,
	                  pop3a, pop3b, pop3c, pop3d, pop3e,
	                  pop4a, pop4b, pop4c, pop4d, pop4e;      /* menu items of each of
	                                                             the popup menus */

	public String currentPassword;
	private UIManager.LookAndFeelInfo looks[] = UIManager.getInstalledLookAndFeels();
	private JTabbedPane tabbed;

	private char llave[] = new char[10];
	private char subkey1[] = new char[8];
	private char subkey2[] = new char[8];


	public Encriptamiento_DES()
	{
		super("Encriptamiento usando algoritmo DES");


		EInputTextLabel = new JLabel("Ingrese texto a Encriptar: ");
		EInputText = new JTextArea(10,30);
		EInputText.setLineWrap(true);
		EInputText.setWrapStyleWord(true);
		EInputText.addMouseListener(
			new MouseAdapter() {
				public void mousePressed( MouseEvent e )
				{
					checkForTriggerEvent(e);
				}
				public void mouseReleased( MouseEvent e)
				{
					checkForTriggerEvent(e);
				}

				private void checkForTriggerEvent( MouseEvent e )
				{
					if(e.isPopupTrigger())
					popupMenu1.show( e.getComponent(), e.getX(), e.getY());
				}
			}
		);





		EOutputText = new JTextArea(10,30);
		EOutputText.setLineWrap(true);
		EOutputText.setWrapStyleWord(true);
		EOutputText.addMouseListener(
			new MouseAdapter() {
				public void mousePressed( MouseEvent e )
				{
					checkForTriggerEvent(e);
				}
				public void mouseReleased( MouseEvent e)
				{
					checkForTriggerEvent(e);
				}

				private void checkForTriggerEvent( MouseEvent e )
				{
					if(e.isPopupTrigger())
					popupMenu2.show( e.getComponent(), e.getX(), e.getY());
				}
			}
		);



		btnEncriptar = new JButton("Encriptar...");
		ButtonHandler handler = new ButtonHandler();
		btnEncriptar.addActionListener(handler);

		Box EPanel = Box.createVerticalBox();  

		DInputTextLabel = new JLabel("Ingrese texto para Desencriptar: ");
		DInputText = new JTextArea(10,30);
		DInputText.setLineWrap(true);
		DInputText.setWrapStyleWord(true);
		DInputText.addMouseListener(
			new MouseAdapter() {
				public void mousePressed( MouseEvent e )
				{
					checkForTriggerEvent(e);
				}
				public void mouseReleased( MouseEvent e)
				{
					checkForTriggerEvent(e);
				}

				private void checkForTriggerEvent( MouseEvent e )
				{
					if(e.isPopupTrigger())
					popupMenu3.show( e.getComponent(), e.getX(), e.getY());
				}
			}
		);


		DOutputText = new JTextArea(10,30);
		DOutputText.setLineWrap(true);
		DOutputText.setWrapStyleWord(true);
		DOutputText.addMouseListener(
			new MouseAdapter() {
				public void mousePressed( MouseEvent e )
				{
					checkForTriggerEvent(e);
				}
				public void mouseReleased( MouseEvent e)
				{
					checkForTriggerEvent(e);
				}

				private void checkForTriggerEvent( MouseEvent e )
				{
					if(e.isPopupTrigger())
					popupMenu4.show( e.getComponent(), e.getX(), e.getY());
				}
			}
		);

		btnDesencriptar = new JButton("Desencriptar...");
		btnDesencriptar.addActionListener(handler);

		Box DPanel = Box.createVerticalBox();  

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu Menu_Archivo = new JMenu("Archivo");
		Menu_Archivo.setMnemonic('F');

		JMenuItem Item_Abrir = new JMenuItem("Abrir archivo de texto...");
		Item_Abrir.setMnemonic('O');
		Item_Abrir.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent e )
				{
					if (tabbed.getSelectedIndex() == 0 )
					Abrir_Archivo( EInputText);
					else
					Abrir_Archivo (DInputText);
				}
			}
		);
		Menu_Archivo.add(Item_Abrir);

		JMenuItem Item_Guardar = new JMenuItem("Guardar en Archivo...");
		Item_Guardar.setMnemonic('S');
		Item_Guardar.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent e )
				{
					if (tabbed.getSelectedIndex() == 0 ) {
						EOutputText.selectAll();
						Guardar_En_Archivo(EOutputText);
					}


					else if (tabbed.getSelectedIndex() == 1 ) {
						DOutputText.selectAll();
						Guardar_En_Archivo(DOutputText);
					}

				}
			}
		);
		Menu_Archivo.add(Item_Guardar);

		Menu_Archivo.addSeparator();

		JMenuItem Item_Salida = new JMenuItem("Salir");
		Item_Salida.setMnemonic('X');
		Item_Salida.addActionListener(
                new ActionListener() {
                        public void actionPerformed( ActionEvent e )
                        {
                        JOptionPane.showMessageDialog(null,"Gracias por usar esta aplicacion");
                        System.exit(0);
                        }
                }
		);

		Menu_Archivo.add(Item_Salida);

		menuBar.add(Menu_Archivo);


		Box EInputPanel = Box.createVerticalBox();
		EInputPanel.add(EInputTextLabel);
		EInputPanel.add(EInputText);
		EInputPanel.add( new JScrollPane( EInputText));



		Box EOutputPanel = Box.createVerticalBox();
		EOutputPanel.add( new JLabel("Texto Encriptado:" ));
		EOutputPanel.add(EOutputText);
		EOutputPanel.add( new JScrollPane( EOutputText));

		Box EPanel1 = Box.createHorizontalBox();
		EPanel1.add( Box.createHorizontalStrut(5));
		EPanel1.add(EInputPanel);
		EPanel1.add( Box.createHorizontalStrut(25));
		EPanel1.add(EOutputPanel);
		EPanel1.add( Box.createHorizontalStrut(5));



		EPasswordField = new JPasswordField(15);
		EPasswordField.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent e )
				{
					Encriptar();
				}
			}
		);

		Box EButtomPanel = Box.createHorizontalBox();
		EButtomPanel.add( Box.createHorizontalStrut(5));
		EButtomPanel.add( new JLabel("Ingrese Contraseña: "));
		EButtomPanel.add( Box.createHorizontalStrut(5));
		EButtomPanel.add(EPasswordField);
		EButtomPanel.add( Box.createHorizontalStrut(15));
		EButtomPanel.add(btnEncriptar);


		JPanel EPanel3 = new JPanel();
		EPanel3.setLayout( new BorderLayout());
		EPanel3.add(EButtomPanel, BorderLayout.WEST);


		EPanel.add( EPanel1);
		EPanel.add( Box.createVerticalStrut(10) );

		EPanel.add(EPanel3);
		EPanel.add( Box.createVerticalStrut(5) );

		Box DInputPanel = Box.createVerticalBox();
		DInputPanel.add(DInputTextLabel);
		DInputPanel.add(DInputText);
		DInputPanel.add( new JScrollPane( DInputText));

		Box DOutputPanel = Box.createVerticalBox();
		DOutputPanel.add( new JLabel("Texto Desencriptado:" ) );
		DOutputPanel.add(DOutputText);
		DOutputPanel.add( new JScrollPane( DOutputText));

		Box DPanel1 = Box.createHorizontalBox();
		DPanel1.add( Box.createHorizontalStrut(5));
		DPanel1.add(DInputPanel);
		DPanel1.add( Box.createHorizontalStrut(25));
		DPanel1.add(DOutputPanel);
		DPanel1.add( Box.createHorizontalStrut(5));

		DPasswordField = new JPasswordField(15);
		DPasswordField.addActionListener(
			new ActionListener() {
				public void actionPerformed( ActionEvent e )
				{
					Desncriptar();
				}
			}
		);

		Box DButtomPanel = Box.createHorizontalBox();
		DButtomPanel.add( Box.createHorizontalStrut(5));
		DButtomPanel.add( new JLabel("Ingrese Contraseña: "));
		DButtomPanel.add( Box.createHorizontalStrut(5));
		DButtomPanel.add(DPasswordField);
		DButtomPanel.add( Box.createHorizontalStrut(15));
		DButtomPanel.add(btnDesencriptar);

		JPanel DPanel3 = new JPanel();
		DPanel3.setLayout( new BorderLayout());
		DPanel3.add(DButtomPanel,BorderLayout.WEST);



		DPanel.add( DPanel1);
		DPanel.add( Box.createVerticalStrut(10) );

		DPanel.add(DPanel3);
		DPanel.add( Box.createVerticalStrut(5) );

		tabbed = new JTabbedPane();
		tabbed.addTab("Encriptar",null,EPanel,"Esta seccion es para encriptar texto!");
		tabbed.addTab("Desencriptar",null,DPanel, "Esta seccion es para desencriptar texto!");
		tabbed.addChangeListener(
			new ChangeListener() {
				public void stateChanged( ChangeEvent e )
				{
					if ( tabbed.getSelectedIndex() == 0 ) {
						EInputText.grabFocus();
					}
					else if ( tabbed.getSelectedIndex() == 1 ) {
						DInputText.grabFocus();
					}
				}
			}
		);



		Container c = getContentPane();
		c.setLayout( new FlowLayout());
		c.add(tabbed);

		// setting up the four popup menus

		popupMenu1 = new JPopupMenu();
		PopupMenu_1_Handler popup1 = new PopupMenu_1_Handler();

		pop1a = new JMenuItem("Cortar");
		pop1a.addActionListener( popup1 );
		pop1b = new JMenuItem("Copiar");
		pop1b.addActionListener( popup1 );
		pop1c = new JMenuItem("Pegar");
		pop1c.addActionListener( popup1 );
		pop1d = new JMenuItem("Borrar");
		pop1d.addActionListener( popup1 );
		pop1e = new JMenuItem("Seleccionar Todo");
		pop1e.addActionListener( popup1 );

		popupMenu1.add(pop1a);
		popupMenu1.add(pop1b);
		popupMenu1.add(pop1c);
		popupMenu1.add(pop1d);
		popupMenu1.addSeparator();
		popupMenu1.add(pop1e);

		popupMenu2 = new JPopupMenu();
		PopupMenu_2_Handler popup2 = new PopupMenu_2_Handler();

		pop2a = new JMenuItem("Cortar");
		pop2a.addActionListener( popup2 );
		pop2b = new JMenuItem("Copiar");
		pop2b.addActionListener( popup2 );
		pop2c = new JMenuItem("Pegar");
		pop2c.addActionListener( popup2 );
		pop2d = new JMenuItem("Borrar");
		pop2d.addActionListener( popup2 );
		pop2e = new JMenuItem("Seleccionar Todo");
		pop2e.addActionListener( popup2 );

		popupMenu2.add(pop2a);
		popupMenu2.add(pop2b);
		popupMenu2.add(pop2c);
		popupMenu2.add(pop2d);
		popupMenu2.addSeparator();
		popupMenu2.add(pop2e);

		popupMenu3 = new JPopupMenu();
		PopupMenu_3_Handler popup3 = new PopupMenu_3_Handler();

		pop3a = new JMenuItem("Cortar");
		pop3a.addActionListener( popup3 );
		pop3b = new JMenuItem("Copiar");
		pop3b.addActionListener( popup3 );
		pop3c = new JMenuItem("Pegar");
		pop3c.addActionListener( popup3 );
		pop3d = new JMenuItem("Borrar");
		pop3d.addActionListener( popup3 );
		pop3e = new JMenuItem("Seleccionar todo");
		pop3e.addActionListener( popup3 );

		popupMenu3.add(pop3a);
		popupMenu3.add(pop3b);
		popupMenu3.add(pop3c);
		popupMenu3.add(pop3d);
		popupMenu3.addSeparator();
		popupMenu3.add(pop3e);

		popupMenu4 = new JPopupMenu();
		PopupMenu_4_Handler popup4 = new PopupMenu_4_Handler();

		pop4a = new JMenuItem("Cortar");
		pop4a.addActionListener( popup4 );
		pop4b = new JMenuItem("Copiar");
		pop4b.addActionListener( popup4 );
		pop4c = new JMenuItem("Pegar");
		pop4c.addActionListener( popup4 );
		pop4d = new JMenuItem("Borrar");
		pop4d.addActionListener( popup4 );
		pop4e = new JMenuItem("Seleccionar todo");
		pop4e.addActionListener( popup4 );

		popupMenu4.add(pop4a);
		popupMenu4.add(pop4b);
		popupMenu4.add(pop4c);
		popupMenu4.add(pop4d);
		popupMenu4.addSeparator();
		popupMenu4.add(pop4e);

		try {
			UIManager.setLookAndFeel(looks[0].getClassName());
			SwingUtilities.updateComponentTreeUI(this);
		}

		catch ( Exception e ) {
			JOptionPane.showMessageDialog(this,"Error while loading the Look-And-Feel",
			                              "Error",JOptionPane.ERROR_MESSAGE);
	    }

		setSize(720,315);       
		setLocation(100,100);
		setResizable(false);

	    show();

	}


	private class ButtonHandler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			if ( e.getSource() == btnEncriptar ) 
			{
				Encriptar();
			}

			else if ( e.getSource() == btnDesencriptar ) 
			{
				Desncriptar();
			}
		}
	}

	private class PopupMenu_1_Handler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			if ( e.getSource() == pop1a )
			EInputText.cut();
			else if ( e.getSource() == pop1b )
			EInputText.copy();
			else if ( e.getSource() == pop1c )
			EInputText.paste();
			else if (e.getSource() == pop1d )
			Borrar(EInputText);
			else if ( e.getSource() == pop1e )
		    EInputText.selectAll();
		}
	}

	private class PopupMenu_2_Handler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			if ( e.getSource() == pop2a )
			EOutputText.cut();
			else if ( e.getSource() == pop2b )
			EOutputText.copy();
			else if ( e.getSource() == pop2c )
			EOutputText.paste();
			else if (e.getSource() == pop2d )
			Borrar(EOutputText);
			else if ( e.getSource() == pop2e )
			EOutputText.selectAll();
		}
	}
	private class PopupMenu_3_Handler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			if ( e.getSource() == pop3a )
			DInputText.cut();
			else if ( e.getSource() == pop3b )
			DInputText.copy();
			else if ( e.getSource() == pop3c )
			DInputText.paste();
			else if (e.getSource() == pop3d )
			Borrar(DInputText);
			else if ( e.getSource() == pop3e )
			DInputText.selectAll();
		}


	}

	private class PopupMenu_4_Handler implements ActionListener {
		public void actionPerformed( ActionEvent e )
		{
			if ( e.getSource() == pop4a )
			DOutputText.cut();
			else if ( e.getSource() == pop4b )
			DOutputText.copy();
			else if ( e.getSource() == pop4c )
			DOutputText.paste();
			else if (e.getSource() == pop4d )
			Borrar(DOutputText);
			else if ( e.getSource() == pop4e )
			DOutputText.selectAll();
		}
	}

	public void Borrar(JTextArea area)
	{
		area.replaceRange( "", area.getSelectionStart(),
				                  area.getSelectionEnd() );
	}

	public void Abrir_Archivo( JTextArea area)
	{
		JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode( JFileChooser.FILES_ONLY );
        String txtmsg="";

        int result = fch.showOpenDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
        try{
        	FileInputStream fis = new FileInputStream( fch.getSelectedFile() );
            BufferedInputStream bis = new BufferedInputStream( fis );

            int c = bis.read();
            while ( c != -1 )
            {
            	txtmsg = txtmsg + (char) c;
                c = bis.read();
            }

            area.setText( txtmsg );
            bis.close();
        }

        catch( Exception e )
        {
        	JOptionPane.showMessageDialog( this, "Error Cargando Archivo!", "Error",
        	                              JOptionPane.ERROR_MESSAGE );
		}
	}

	public void Guardar_En_Archivo( JTextArea area )
    {
    	JFileChooser fch = new JFileChooser("C:\\");
        fch.setFileSelectionMode( JFileChooser.FILES_ONLY );

        int result = fch.showSaveDialog( this );

        if ( result == JFileChooser.CANCEL_OPTION )
        return;

        else
    	try {
    		FileOutputStream fos = new FileOutputStream( fch.getSelectedFile() );
    		BufferedOutputStream bos = new BufferedOutputStream( fos );
            bos.write( area.getText().getBytes() );
            bos.flush();
        	fos.close();
        }

		catch( Exception e )
		{
			JOptionPane.showMessageDialog(this, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE );
		}
	}

	public void Encriptar()
	{
		JTextField passField = (JTextField) EPasswordField;
		currentPassword = passField.getText();

		if (currentPassword.length() < 10) {
			JOptionPane.showMessageDialog(this, "Su contraseña no tiene\nlos 10 caracteres...",
			                              "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}

		Generar_Llaves();

		String texto_a_Encriptar = EInputText.getText();
		String texto_Encriptado = new String("");

		char charsToManipulate[] = new char[8];

		for ( int i = 0; i < texto_a_Encriptar.length(); i++ ) {

			char theChar = texto_a_Encriptar.charAt(i);
			int value = (int) theChar;
			String valueString = Integer.toBinaryString(value);

			while ( valueString.length() < 8 )
			valueString = "0" + valueString;

			for ( int j = 0; j < 8; j++ )
			charsToManipulate[j] = valueString.charAt(j);

			// chars to manipulate are now ready

			Algoritmo_DES s = new Algoritmo_DES();

			char array1[] = new char[8];
			array1 = s.IP(charsToManipulate);
			char array2[] = new char[8];
			array2 = s.FK(array1,subkey1);
			char array3[] = new char[8];
			array3 = s.SW(array2);
			char array4[] = new char[8];
			array4 = s.FK(array3,subkey2);
			char array5[] = new char[8];
			array5 = s.IP_inverse(array4);
			String tmp = new String("");
			for ( int k = 0; k < array5.length; k++ ) {
				tmp += String.valueOf( array5[k] );
			}

			texto_Encriptado += (char)(Integer.parseInt(tmp,2));

		}

		EOutputText.setText(texto_Encriptado);

	}

	public void Desncriptar()
	{
		JTextField passField = (JTextField) DPasswordField;
		currentPassword = passField.getText();

		if (currentPassword.length() < 10) {
			JOptionPane.showMessageDialog(this, "Su contraseña no tiene\nlos 10 caracteres...",
			                              "Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		Generar_Llaves();

		String textToDecrypt = DInputText.getText();
		String texto_desencriptado = new String("");

		char charsToManipulate[] = new char[8];

		for ( int i = 0; i < textToDecrypt.length(); i++ ) {

			char theChar = textToDecrypt.charAt(i);
			int value = (int) theChar;
			String valueString = Integer.toBinaryString(value);

			while ( valueString.length() < 8 )
			valueString = "0" + valueString;

			for ( int j = 0; j < 8; j++ )
			charsToManipulate[j] = valueString.charAt(j);
			// chars to manipulate are now ready

			Algoritmo_DES s = new Algoritmo_DES();

			char array1[] = new char[8];
			array1 = s.IP(charsToManipulate);
			char array2[] = new char[8];
			array2 = s.FK(array1,subkey2);
			char array3[] = new char[8];
			array3 = s.SW(array2);
			char array4[] = new char[8];
			array4 = s.FK(array3,subkey1);
			char array5[] = new char[8];
			array5 = s.IP_inverse(array4);


			String tmp = new String("");
			for ( int k = 0; k < array5.length; k++ ) {
				tmp += String.valueOf( array5[k] );
			}

			texto_desencriptado += (char)(Integer.parseInt(tmp,2));

		}

		DOutputText.setText(texto_desencriptado);
	}

	private void Generar_Llaves()
	{

		llave = (new Llave(currentPassword)).Llaves_Generadas();

		// P10
		char p10[] = new char[10];
		p10[0] = llave[2];
		p10[1] = llave[4];
		p10[2] = llave[1];
		p10[3] = llave[6];
		p10[4] = llave[3];
		p10[5] = llave[9];
		p10[6] = llave[0];
		p10[7] = llave[8];
		p10[8] = llave[7];
		p10[9] = llave[5];

		Seteo_de_bits LeftFiveBits = new Seteo_de_bits(5);
		Seteo_de_bits RightFiveBits = new Seteo_de_bits(5);

		for ( int i = 0; i < p10.length; i++ ) {

			if ( i < 5 ) {
				if ( p10[i] == '1' )
				LeftFiveBits.set(i);
				else if ( p10[i] == '0' )
				LeftFiveBits.clear(i);
			}
			else {
				if ( p10[i] == '1' )
				RightFiveBits.set(i);
				else if ( p10[i] == '0' )
				RightFiveBits.clear(i);
			}
		}

		// Apply LS_1 on each one

		Seteo_de_bits LS_1LeftBits = LeftFiveBits.LS_1(5);
		Seteo_de_bits LS_1RightBits = RightFiveBits.LS_1(5);

		// Apply P8 to produce the first subkey

		char La_llave_derecha_LS_1[] = new char[10];

		char left1[] = LS_1LeftBits.Bit_en_arregloChar(5);
		char right1[] = LS_1RightBits.Bit_en_arregloChar(5);

		for ( int i = 0; i < 10; i++) {
			if ( i < 5 )
			La_llave_derecha_LS_1[i] = left1[i];
			else
			La_llave_derecha_LS_1[i] = right1[i - 5];
		}

		subkey1[0] = La_llave_derecha_LS_1[5];
		subkey1[1] = La_llave_derecha_LS_1[2];
		subkey1[2] = La_llave_derecha_LS_1[6];
		subkey1[3] = La_llave_derecha_LS_1[3];
		subkey1[4] = La_llave_derecha_LS_1[7];
		subkey1[5] = La_llave_derecha_LS_1[4];
		subkey1[6] = La_llave_derecha_LS_1[9];
		subkey1[7] = La_llave_derecha_LS_1[8];

		// Apply LS_2

		Seteo_de_bits LS_2LeftBits = LS_1LeftBits.LS_2(5);
		Seteo_de_bits LS_2RightBits = LS_1RightBits.LS_2(5);

		// Apply P8

		char La_llave_Izquierda_LS_2[] = new char[10];

		char left2[] = LS_2LeftBits.Bit_en_arregloChar(5);
		char right2[] = LS_2RightBits.Bit_en_arregloChar(5);

		for ( int i = 0; i < 10; i++) {
			if ( i < 5 )
			La_llave_Izquierda_LS_2[i] = left2[i];
			else
			La_llave_Izquierda_LS_2[i] = right2[i - 5];
		}

		subkey2[0] = La_llave_Izquierda_LS_2[5];
		subkey2[1] = La_llave_Izquierda_LS_2[2];
		subkey2[2] = La_llave_Izquierda_LS_2[6];
		subkey2[3] = La_llave_Izquierda_LS_2[3];
		subkey2[4] = La_llave_Izquierda_LS_2[7];
		subkey2[5] = La_llave_Izquierda_LS_2[4];
		subkey2[6] = La_llave_Izquierda_LS_2[9];
		subkey2[7] = La_llave_Izquierda_LS_2[8];



	}

	public static void main( String args[] )
	{
		Encriptamiento_DES app = new Encriptamiento_DES();
		app.addWindowListener(
			new WindowAdapter() {
				public void windowClosing( WindowEvent e )
				{
                                        JOptionPane.showMessageDialog(null,"Gracias por usar esta aplicacion");
					System.exit(0);
				}
			}
		);
	}
}	
