/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.gui.controles;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;

/**
 *
 * @author JONATHAN
 */
public class Mi_Imprimible implements Printable, Pageable {

    PPapel papelA4 = new PPapel(0, PPapel.HORIZONTAL);
    int nPages = 1;
    PrinterJob pj = PrinterJob.getPrinterJob();
    PageFormat format = this.pj.defaultPage();
    private static double ancho_escala;
    private static double alto_escala;

    public int print(Graphics grafico, PageFormat pf, int pi) {
        Graphics2D grafico2D = (Graphics2D) grafico;
        if(getAlto_escala()==0.0){
           setAlto_escala(pf.getImageableHeight() / pf.getHeight() * 0.5D); 
        }
        if(getAncho_escala()==0.0){
            setAncho_escala(pf.getImageableWidth() / pf.getWidth() * 0.5D);
        }
        try {
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            Robot robot = new Robot();
            BufferedImage myBufferedImage = robot.createScreenCapture(new Rectangle(dimension));
            pf.setPaper(this.papelA4);

            grafico2D.setClip(0, 0, dimension.width, dimension.height);
            grafico2D.scale(getAncho_escala(), getAlto_escala());
            grafico2D.translate(pf.getImageableX() * 0.7D, pf.getImageableY() * 0.7D);
            pf.setOrientation(0);
            grafico2D.drawImage(myBufferedImage, 0, 0, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getNumberOfPages() {
        return this.nPages;
    }

    public PageFormat getPageFormat(int pi) {
        return this.format;
    }

    public Printable getPrintable(int pi) {
        return this;
    }

    /**
     * @return the ancho_escala
     */
    public static double getAncho_escala() {
        return ancho_escala;
    }

    /**
     * @param ancho_escala the ancho_escala to set
     */
    public static void setAncho_escala(double ancho_escala1) {
        ancho_escala = ancho_escala1;
    }

    /**
     * @return the alto_escala
     */
    public static double getAlto_escala() {
        return alto_escala;
    }

    /**
     * @param alto_escala the alto_escala to set
     */
    public static void setAlto_escala(double alto_escala1) {
        alto_escala = alto_escala1;
    }
}
