
package nododoble;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Principalsin_orden  extends JFrame
{
    JButton btncalcular= new JButton("Calcula");
    JButton btnmostrar = new JButton("Mostrar des");
    JTextField txtcodigo = new JTextField();
    JLabel vacio = new JLabel();
    static class NodoDoble
    {
        String codigo;
        NodoDoble siguiente;
        NodoDoble anterior;
    }
    NodoDoble inicio= null;
    NodoDoble fin= null;

public Principalsin_orden()
{
    setSize(400,400);
    setTitle("Dobles");
    txtcodigo.setBounds(100, 100, 100, 20);
    btncalcular.setBounds(150,150, 100, 20);
    btnmostrar.setBounds(200,200,100,20);
    btncalcular.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Enviar_A_Nodo();
                txtcodigo.setText("");
                txtcodigo.requestFocus();
            }
        });

        btnmostrar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Mostar_Datos();
            }
        });
        add(txtcodigo);
        add(btncalcular);
        add(btnmostrar);
        add(vacio);
}
    public static void main(String[] args) {
        Principalsin_orden obj = new Principalsin_orden();
        obj.show();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
        public void Enviar_A_Nodo()
    {
        NodoDoble nuevo;
        nuevo = new NodoDoble();
        nuevo.codigo = txtcodigo.getText();
        nuevo.siguiente = null;
        nuevo.anterior = fin;

        if(inicio==null)
        {
        inicio=nuevo;
        }
        else
            fin.siguiente=nuevo;

        fin=nuevo;
    }
        public void Mostar_Datos()
    {
    NodoDoble otro = fin;
    while(otro!=null)
    {
        System.out.println("   imprime "+otro.codigo);
        otro = otro.anterior;
    }
    }

}
