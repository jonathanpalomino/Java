
package practicando_graficos_2;

//Clases necesarias para el proyecto
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JColorChooser; //Permite cambiar de color
import javax.swing.JPanel;  

       
public class Main extends JFrame
{
    JButton cambiarColorJButton = new JButton("Cambiar color");
    Color color = Color.LIGHT_GRAY;
    JPanel coloresJPanel = new JPanel();
    
    
    public Main()
    {
        setTitle("Uso de JColorChooser"); //Título del Frame
        
        coloresJPanel.setBackground(color); //Color de fondo
        
                
        cambiarColorJButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                color = JColorChooser.showDialog(Main.this,"Selección de color", color); // Permite seleccionar el color

                if(color == null) //Si no se selecciona ningun color
                {
                    color = color.LIGHT_GRAY;  //El color por defecto será gris

                }

                coloresJPanel.setBackground(color); //El Panel toma el color
                                                    //seleccionado
            }

        });

        add(coloresJPanel,BorderLayout.CENTER);
        add(cambiarColorJButton,BorderLayout.SOUTH);
        
        setSize(500,130);
        setVisible(true);
        
        
    }        
       

    public static void main(String[] args) 
    {
        Main c = new Main();
        
        c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
    }

}
