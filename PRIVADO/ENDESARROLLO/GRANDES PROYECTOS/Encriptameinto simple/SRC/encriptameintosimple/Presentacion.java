package encriptameintosimple;


import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Graphics;
/**
 *
 * @author JONATHAN
 */
public class Presentacion extends JApplet {
    
    JButton entrar; 

    public Presentacion() {
       
    }
    
    public void init(){
        entrar = new  JButton("Entrar");
      setLayout(new FlowLayout());
      
      ActionListener al= new ActionListener(){
            public void actionPerformed(ActionEvent e){
                           
               JFrame frame= new JFrame("ENCRIPTAMIENTO");
        
                frame.getContentPane().add(new Principal(),BorderLayout.CENTER);
                frame.setSize(500, 550);
                frame.setVisible(true);
               
            }
        };
        entrar.addActionListener(al);
    }
    
    public void paint(Graphics g){
        g.drawString("Haga clic en el boton:  ",25,25);
        
    }
    
    public void start(){
        add(entrar);
    }
    
   
}
    
