package encriptameintosimple;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
/**
 *
 * @author  JONATHAN
 */
public class Principal extends JPanel {
    
    static Object objetos [][] = {
        {"Encriptacion", Encriptar.class},
    };
    
    static JPanel creaPanel(Class clase){
        String titulo = clase.getName();
        titulo= titulo.substring(titulo.lastIndexOf('.')+1);
        JPanel panel = null;
        try{
            panel= (JPanel)clase.newInstance();
            panel.setBorder(new TitledBorder(titulo));
        }catch(Exception e){
            System.out.println(e);
        }
        
        return(panel);
    }
    
    public Principal(){
       // initComponents();
        setLayout(new BorderLayout());
        JTabbedPane pestana= new JTabbedPane();
        for(int i=0; i<objetos.length; i++){
            pestana.addTab((String)objetos[i][0], creaPanel((Class)objetos[i][1]));
        }
        add(pestana,BorderLayout.CENTER);
        pestana.setSelectedIndex(objetos.length/2);
    }
    
    public static void main(String args[]){
        JFrame frame= new JFrame("SOFTWARE DE REDES");
        frame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent evt){
                System.exit(0);
            }
        });
        
        frame.getContentPane().add(new Principal(),BorderLayout.CENTER);
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }
    
}
