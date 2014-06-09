
package figuras_graficos;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame
{
    JPanel panelGraficos = new JPanel();
    JButton btnMostrar = new JButton("Mostrar");
    JButton btnLimpiar = new JButton("Limpiar");
  int x,y;
    public Main()
    {
        setSize(600,500);

        add(btnMostrar,"North");
        add(panelGraficos,"Center");
        add(btnLimpiar,"South");

        btnMostrar.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {

        Graphics g = panelGraficos.getGraphics(); //Captura los gráficos en el Panel

        // Dibuja rectangulo 3D color gris

             int m=10, n=30, p=45;
             for(int i=1; i<100;i++)
              {
                g.setColor(Color.getHSBColor(m,n,p));
                g.draw3DRect(x, y, 100, 70,true); //(int x, int y, ancho, alto, boolean)

                g.drawOval(x+100, y+100, i, i);
                x=x+1;
                y=y+1;
                m=m+10;
                n=n+10;
                p=p+10;
              }
		

		
            }

            });

        btnLimpiar.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                panelGraficos.updateUI();

            }
            }
        );

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
