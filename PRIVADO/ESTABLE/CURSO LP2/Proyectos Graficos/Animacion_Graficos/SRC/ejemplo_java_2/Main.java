
package ejemplo_java_2;

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Main extends JFrame
{
    
    public Main()
    {
        setTitle("Imagenes");
        setLayout(new FlowLayout());

        JLabel etiqueta1 = new JLabel("Etiqueta con Texto");
        etiqueta1.setToolTipText("Etiqueta"); // Informacion al posicionar el puntero sobre la etiqueta
        add( etiqueta1 );

        ImageIcon silvestre = new ImageIcon("C:/Documents and Settings/David Talla/Escritorio/Graficas_Final/Imagenes/ken.gif");
        JLabel etiqueta2 = new JLabel("Etiqueta con texto e icono",silvestre,SwingConstants.LEFT);
        etiqueta2.setToolTipText("Silvestre"); // Mouse
        add( etiqueta2);

        ImageIcon pio = new ImageIcon("C:/Documents and Settings/David Talla/Escritorio/Graficas_Final/Imagenes/goku.gif");
        JButton pio2 = new JButton("Abrir",pio);
        pio2.setToolTipText("Abrir");
        add(pio2);

        ImageIcon sasori = new ImageIcon("C:/Documents and Settings/David Talla/Escritorio/Graficas_Final/Imagenes/naruto.gif");
        JLabel sasori2 = new JLabel("Naruto",sasori,SwingConstants.LEFT);
        add(sasori2);



    }
    public static void main(String[] args) 
    {
        Main c = new Main();

        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        c.setSize(400,400);
        c.setVisible(true);
       
    }

}

