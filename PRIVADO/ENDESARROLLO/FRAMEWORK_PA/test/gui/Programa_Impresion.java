/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;
import palomino.gui.controles.ControlTeclas;

/**
 *
 * @author JONATHAN
 */
//A CONTINUACION LA CLASE PRINCIPAL
public class Programa_Impresion extends JFrame {

    String cadena;
    JTextField campo;
    JButton imprimir;
    JLabel info;
    JPanel principal = new JPanel(new BorderLayout());
    JPanel etiq = new JPanel(new FlowLayout());
    JPanel dos = new JPanel(new FlowLayout());
//CONSTRUCTOR DE LA CLASE
    Programa_Impresion() {
        super("Muestra Simple de ImpresiÃ³n en JAVA...");
        info = new JLabel("ESCRIBA ALGO EN EL CAMPO Y HAGA CLIC EN IMPRIMIR...");
        cadena = new String();
        campo = new JTextField(30);
        imprimir = new JButton("IMPRIMIR");
        dos.add(campo);
        dos.add(imprimir);
        etiq.add(info);
        campo.setToolTipText("ESCRIBA ALGO AQUÃ...");
        imprimir.setToolTipText("CLIC AQUI PARA IMPRIMIR...");
        principal.add(etiq, BorderLayout.NORTH);
        principal.add(dos, BorderLayout.CENTER);
        getContentPane().add(principal);
//AJUSTO EL TAMAÃ‘O DE LA VENTANA AL MINIMO
        pack();
//NO PERMITO QUE PUEDAN CAMBIAR EL TAMAÃ‘O DE LA VENTANA
        this.setResizable(false);
//AHORA LA CENTRARA EN LA PANTALLA
        Dimension pantalla, cuadro;
        pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        cuadro = this.getSize();
        this.setLocation(((pantalla.width - cuadro.width) / 2),
                (pantalla.height - cuadro.height) / 2);

//LE AGREGAMOS EL EVENTO AL BOTON "imprimir"
        imprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cadena = "";
                cadena = String.valueOf(campo.getText());
                if (!cadena.equals("")) {
                    ControlTeclas.imprime_texto(cadena);
                } else {
                    System.out.println("NO SE IMPRIME NADA EN BLANCO...");
                }
                campo.requestFocus();
                campo.select(0, cadena.length());
            }
        });


    }//FIN DEL CONSTRUCTOR
    public static void main(String jm[]) {
        Programa_Impresion p = new Programa_Impresion();//Instanciamos clase
        p.show();
        p.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }//FIN DEL MAIN
}//FIN DE LA CLASE PRINCIPAL