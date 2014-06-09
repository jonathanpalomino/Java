/*
 * Funcion Seno Con graficas
 */

package figurasgraficasfuncionseno;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Main  extends JFrame{
JButton btnMostrar = new JButton("Mostrar");
JButton btnLimpiar = new JButton("Limpiar");
JPanel panelGraficos = new JPanel();
public Main()
{
        setSize(600,500);

        add(btnMostrar,"North");
        add(panelGraficos,"Center");
        add(btnLimpiar,"South");

        btnMostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
            Graphics obj = panelGraficos.getGraphics();
            obj.setColor(Color.red);

            obj.drawArc(80,80, 90, 90, 0, 180);//Primer arco

            obj.drawArc(170, 80, 90, 90, 180, 180);//Segundo arco

            obj.drawArc(260, 80, 90, 90, 0,180);//Tercer arco

            obj.drawArc(350, 80, 90,90, 180,180);//Cuarto arco

            obj.drawArc(440, 80,90,90,0,180);//Quinto arco

            obj.setColor(Color.BLUE);
            obj.drawLine(80,12, 80, 200);//Linea Vertical
            obj.drawLine(40,122,600, 122);//Linea Horizontal
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
                panelGraficos.updateUI();
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
    public static void main(String[] args) {
       Main obj = new Main();
       obj.show();
    }

}