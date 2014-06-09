
package problema2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Formulario extends JFrame
{
    JLabel lblEdad = new JLabel("Edad");
    JLabel lblvacio = new JLabel("");
    JTextField txtEdad = new JTextField();
    JButton btnCalcular = new JButton("Calcular");
    JButton btnMostrar = new JButton("Mostrar");
    JTextArea txtArea = new JTextArea();
       static class Nodo
   {
      String Edad;
      Nodo siguiente;
   }
      Nodo comienzo = null;
      Nodo nuevo=null;
      Nodo q=null;


    public Formulario()
    {
        setSize(400,380);
        setTitle("Formulario");
        lblEdad.setBounds(50, 50, 100, 20);
        txtEdad.setBounds(160, 50, 100, 20);
        btnMostrar.setBounds(60, 100, 100, 20);
        btnCalcular.setBounds(200, 100, 100, 20);
        txtArea.setBounds(70,150,150,150);
        txtArea.setVisible( false );
        add(txtEdad);
        add(lblEdad);
        add(btnMostrar);
        add(btnCalcular);
        add(txtArea);
        add(lblvacio);

        btnCalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
    try
    {
    nuevo = new Nodo();
    nuevo.Edad = txtEdad.getText();
    txtEdad.setText("");

            if(comienzo==null)
        {
      	   comienzo=nuevo;
        }
        else
        {
      	   q= comienzo;
      	   while(q.siguiente!=null)
      	   {
      		 q=q.siguiente;
           }
           q.siguiente=nuevo;
        }
        nuevo.siguiente=null;
    }
    catch(Exception ex)
    {
        JOptionPane.showMessageDialog(null,"Campo en blanco");
    }

            }
        });


        btnMostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e)
            {
            Calcular();
            }
        });
    }


    public void Calcular()
    {
        if(comienzo==null)
        {
            JOptionPane.showMessageDialog(null,"No hay datos");
        }
        txtArea.setVisible(true);
        String acu="";
        while (comienzo != null)
             {
                if(comienzo.Edad!=null)
                {
                    acu=acu+(comienzo.Edad+"\n");
                }
                  comienzo = comienzo.siguiente;
             }
        txtArea.setText(acu);
    }

}
