/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.gui.controles;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.PrintJob;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.awt.print.Book;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author JONATHAN
 */
public class ControlTeclas {

    public ControlTeclas() {
    }

    /**
     * ******************************************************************
     * A continuacion el metodo "imprimir(String)", el encargado de * colocar en
     * el objeto grafico la cadena que se le pasa como * parÃ¡metro y se
     * imprime. *
     * ******************************************************************
     */
    public static void imprime_texto(String Cadena) {
        Font fuente = new Font("Dialog", Font.PLAIN, 10);
        PrintJob trabajo_impresion = Toolkit.getDefaultToolkit().getPrintJob(new Frame(), "Desde Java", null);
        Graphics pagina;
        //LO COLOCO EN UN try/catch PORQUE PUEDEN CANCELAR LA IMPRESION
        try {
            pagina = trabajo_impresion.getGraphics();
            pagina.setFont(fuente);
            pagina.setColor(Color.black);
            pagina.drawString(Cadena, 60, 60);
            pagina.dispose();
            trabajo_impresion.end();
        } catch (Exception e) {
            System.out.println("LA IMPRESION HA SIDO CANCELADA...");
        }
    }

    public static void imprime(Container panel_contenedor) {
        System.out.println("imprimiendo pantalla");
        Toolkit tk = panel_contenedor.getToolkit();
        Frame f = getFrame(panel_contenedor);
        if (f == null) {
            f = new JFrame();
        }

        PrintJob mi_trabajo = tk.getPrintJob(getFrame(panel_contenedor), "JONATHAN", new Properties());
        if (mi_trabajo != null) {
            Graphics grafico = mi_trabajo.getGraphics();
            if (grafico != null) {
                panel_contenedor.printAll(grafico);
            }
            mi_trabajo.end();
        }

    }

    public static void imprime(Container panel_contenedor, double X, double Y) {
        PrinterJob printJob = PrinterJob.getPrinterJob();

        Book libro = new Book();
        libro.append(new Impresion(panel_contenedor, X, Y), printJob.defaultPage());
        printJob.setPageable(libro);

        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (Exception PrinterException) {
                JOptionPane.showMessageDialog(null, PrinterException.getMessage());
            }
        }
    }

    private static Frame getFrame(Component c) {
        Frame frm = null;
        Component padre = c;
        while ((padre != null) && (!(padre instanceof Frame))) {
            padre = padre.getParent();
        }
        if ((padre instanceof Frame)) {
            frm = (Frame) padre;
        }
        return frm;
    }

    public static void imprimepantalla() {
        PrinterJob printJob = PrinterJob.getPrinterJob();

        printJob.setPageable(new Mi_Imprimible());

        if (printJob.printDialog()) {
            try {
                printJob.print();
            } catch (PrinterException e) {
                System.err.println("Exception: " + e);
            }
        }
    }

    public static void imprimepantalla_setAnchoEscala(double ancho) {
        Mi_Imprimible.setAncho_escala(ancho);
    }

    public static void imprimepantalla_setAltoEscala(double alto) {
        Mi_Imprimible.setAlto_escala(alto);
    }

    public static double imprimepantalla_getAnchoEscala() {
        return Mi_Imprimible.getAncho_escala();
    }

    public static double imprimepantalla_getAltoEscala() {
        return Mi_Imprimible.getAlto_escala();
    }
    ////////////////////////////////////

    private static BufferedImage Captura_Pantalla() throws AWTException, HeadlessException {
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        return image;
    }

    public static void guarda_captura_pantalla(String disco, String imagenpng) {
        try {
            BufferedImage image = Captura_Pantalla();
            String fileName = disco + "\\" + imagenpng;
            String extension = imagenpng.replace(imagenpng.substring(0, imagenpng.lastIndexOf(".")) + ".", "");
            ImageIO.write(image, extension, new File(fileName));
        } catch (IOException ex) {
            Logger.getLogger(ControlTeclas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(ControlTeclas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void guarda_captura_pantalla(String ruta) {
        try {
            File abc = new File(ruta);
            BufferedImage image = Captura_Pantalla();
            String extension = abc.getName().replace(abc.getName().substring(0, abc.getName().lastIndexOf(".")) + ".", "");
            ImageIO.write(image, extension, new File(ruta));
        } catch (IOException ex) {
            Logger.getLogger(ControlTeclas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (AWTException ex) {
            Logger.getLogger(ControlTeclas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeadlessException ex) {
            Logger.getLogger(ControlTeclas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void guarda_captura(String ruta, Container panel_contenedor) {
        System.out.println("imprimiendo pantalla");
        Toolkit tk = panel_contenedor.getToolkit();
        Frame f = getFrame(panel_contenedor);
        if (f == null) {
            f = new JFrame();
        }

    }

    public static class Impresion implements Printable {

        private Container componente; //Cualquier Componente 
        private double escalaX, escalaY;

        private Impresion(Container panel_contenedor, double escalax, double escalay) {
            componente = panel_contenedor;
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

    public static void Copiar_portapapeles(String txtResultado) {
        StringSelection ss = new StringSelection(txtResultado);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }
}