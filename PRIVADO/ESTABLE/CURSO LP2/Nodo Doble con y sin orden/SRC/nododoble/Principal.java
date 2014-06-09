/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package nododoble;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Principal  extends JFrame
{
    JButton btncalcular= new JButton("Calcula");
    JButton btnmostrar = new JButton("Mostrar des");
    JButton btnmostrarasc = new JButton("Mostrar asc");
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

public Principal()
{
    setSize(400,400);
    setTitle("Dobles");
    txtcodigo.setBounds(100, 100, 100, 20);
    btncalcular.setBounds(150,150, 100, 20);
    btnmostrar.setBounds(200,200,100,20);
    btnmostrarasc.setBounds(270,270,100,20);
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

                btnmostrarasc.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Mostar_Datos_asc();
            }

        });
        add(txtcodigo);
        add(btncalcular);
        add(btnmostrar);
        add(btnmostrarasc);
        add(vacio);
}
    public static void main(String[] args) {
        Principal obj = new Principal();
        obj.show();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void Enviar_A_Nodo()
    {
        NodoDoble nuevo,auxiliar;
        nuevo = new NodoDoble();
        nuevo.codigo = txtcodigo.getText();
        nuevo.siguiente = null;
        nuevo.anterior = null;

        if(inicio==null)
        {
        inicio=nuevo;
        fin=inicio;
        }
        else if(nuevo.codigo.compareTo(inicio.codigo)<0)
        {
            nuevo.siguiente=inicio;
            inicio.anterior = nuevo;
            inicio=nuevo;
        }
        else if(nuevo.codigo.compareTo(fin.codigo)>0 ||nuevo.codigo.compareTo(fin.codigo)==0 )
        {
            nuevo.anterior=fin;
            fin.siguiente = nuevo;
            fin = nuevo;
        }
        else
        {
            auxiliar= inicio;
            while(nuevo.codigo.compareTo(auxiliar.codigo)>0 ||nuevo.codigo.compareTo(auxiliar.codigo)==0 )
            {
                auxiliar= auxiliar.siguiente;
            }
            nuevo.siguiente =auxiliar;
            nuevo.anterior=auxiliar.anterior;
            auxiliar.anterior.siguiente = nuevo;
            auxiliar.anterior = nuevo;
        }
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

    public void Mostar_Datos_asc() {
    NodoDoble otro = inicio;
    while(otro!=null)
    {
        System.out.println("   imprime "+otro.codigo);
        otro = otro.siguiente;
    }
    }
}
