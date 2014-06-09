/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impresion;

/**
 *
 * @author synccon
 */
import java.awt.Graphics; 
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import javax.swing.JPanel; 

public class Impresion implements Printable { 

    private JPanel componente; //Cualquier Componente 
    private double escalaX, escalaY; 

    public Impresion(JPanel panelUno, double escalax, double escalay) { 
        componente = panelUno; 
        escalaX = escalax; 
        escalaY = escalay; 
    } 

    public int print(Graphics graficar, PageFormat pageFormat, int pageIndex) { 
        if (pageIndex > 0) { 
            return NO_SUCH_PAGE; 
        } 

        Graphics2D graficar2D = (Graphics2D) graficar; 

        graficar2D.translate(pageFormat.getImageableX(), pageFormat.getImageableY()); 
        graficar2D.scale(escalaX, escalaY); 

        componente.printAll(graficar2D); 

        return PAGE_EXISTS; 
    } 
} 
