
package fotos_graficos;

import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame
{ 
    JPanel PanelCentro = new JPanel();
    JButton btnAplicar = new JButton("Buscar Imagen");
    JTextArea txtRuta = new JTextArea();
    JButton btnFoto = new JButton("Imagen");

    public Main()
    {
       setSize(700,600);
       
       PanelCentro.add(btnFoto,"Center");
       add(btnAplicar,"North");
       add(PanelCentro,"Center");
       add(txtRuta,"South");

       //Para que el Frame aparezca al centro de la pantalla
        setLocation((getToolkit().getScreenSize().width - 700) / 2,
                    (getToolkit().getScreenSize().height - 600) / 2);


        btnAplicar.addActionListener(new ActionListener()
    {
         public void actionPerformed(ActionEvent e)
      {
            PanelGraficos obj = new PanelGraficos();
            String FILE = obj.Archivo();

            // muestra nombre de archivo
            txtRuta.setText("Archivo: " + FILE);

            // carga imagen en el boton central
            btnFoto.setIcon(new ImageIcon(FILE));
      }
    });

         this.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

    }
    public static void main(String[] args)
    {
       Main c = new Main();
         c.show();
    }
    

}
