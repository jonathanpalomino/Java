/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impresion;

/**
 *
 * @author synccon
 */
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.print.Book; 
import java.awt.print.PrinterJob; 
import javax.swing.JButton; 
import javax.swing.JFrame; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.JTextField; 

public class Principal { 

    public static void main(String[] args) { 
        final JPanel panel = new JPanel(); 
        JButton boton = new JButton("Aceptar"); 
        boton.addActionListener(new ActionListener() { 

            @Override 
            public void actionPerformed(ActionEvent e) { 
               imprimir(panel); 
            } 
        }); 
        JFrame frame = new JFrame(); 
        JTextField text = new JTextField("Texto escrito"); 
         
        panel.add(boton); 
        frame.setSize(300, 300); 
        panel.add(text); 
         
        frame.add(panel); 
         
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    } 

    public static void imprimir(JPanel panel) { 
        PrinterJob printJob = PrinterJob.getPrinterJob(); 

        Book libro = new Book(); 
        libro.append(new Impresion(panel, 0.9, 0.9), printJob.defaultPage()); 
        printJob.setPageable(libro); 

        if (printJob.printDialog()) { 
            try { 
                printJob.print(); 
            } catch (Exception PrinterException) { 
                JOptionPane.showMessageDialog(null, PrinterException.getMessage()); 
            } 
        } 
    } 
}  

