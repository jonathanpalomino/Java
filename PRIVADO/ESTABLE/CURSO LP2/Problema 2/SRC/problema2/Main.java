/*
 * REALIZAR EL PROGRAMA QUE MUESTRE UN MENU QUE TENGA 2 SUBOPCIONES : LA PRIMERA
 * OPCION DEBERA MOSTRAR UN FORMULARIO QUE PERMITA INGRESAR LAS EDADES DE N PERSONAS
 * Y LA SEGUNDA OPCION DEBERA MOSTRAR UN GRAFICO(CIRCULO) CON EFECTOS DE TAMAÑO
 */

package problema2;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Main extends JFrame implements ActionListener,ItemListener
{
    JPanel panelGraficos = new JPanel();
    int x=100;
public Main()
{
    setSize(500,500);
    MenuBar barra =new MenuBar();
    Menu menu = new Menu("Menu");
    MenuItem men = new MenuItem("Formulario");
    men.addActionListener(this);
    menu.add(men);

    men = new MenuItem("Figura");
    men.addActionListener(this);
    menu.add(men);

    barra.add(menu);
    setMenuBar(barra);
    add(panelGraficos,"Center");

}

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Formulario"))
        {
        Formulario uno = new Formulario();
        uno.show(true);
        }
        else if(e.getActionCommand().equals("Figura"))
        {
        Circulo();
        }
    }

    public void itemStateChanged(ItemEvent e) {
        //
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.show();
    }

    public void Circulo() {
        Timer timer = new Timer (100, new ActionListener ()
{
    public void actionPerformed(ActionEvent e)
    {
        Graphics g = panelGraficos.getGraphics();
        g.setColor(Color.BLUE);
        g.drawArc(220, 150, x, x, 0, 360);
        x=x+3;
     }
});
timer.start();
    }
}
