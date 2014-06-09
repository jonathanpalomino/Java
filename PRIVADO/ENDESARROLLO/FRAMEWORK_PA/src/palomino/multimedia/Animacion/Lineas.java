/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.multimedia.Animacion;

/**
 *
 * @author JONATHAN
 */
import java.awt.*;
import java.applet.Applet;

public class Lineas extends Applet implements Runnable{

    Thread runner = null;
    final static int WIDTH = 600;
    final static int HEIGHT = 500;
    Image image;
    Graphics grafico_inicial;
    int[] x1;
    int[] y1;
    int[] x2;
    int[] y2;
    int dx1 = 2 + (int) (3 * Math.random());
    int dy1 = 2 + (int) (3 * Math.random());
    int dx2 = 2 + (int) (3 * Math.random());
    int dy2 = 2 + (int) (3 * Math.random());
    static int primero = 0;
    final static int LINEAS = 50;

    public void init() {
        x1 = new int[LINEAS];
        y1 = new int[LINEAS];
        x2 = new int[LINEAS];
        y2 = new int[LINEAS];

        x1[0] = (int) (WIDTH * Math.random());
        y1[0] = (int) (HEIGHT * Math.random());
        x2[0] = (int) (WIDTH * Math.random());
        y2[0] = (int) (HEIGHT * Math.random());

        for (int i = 1; i < LINEAS; i++) {

            x1[i] = x1[0];
            y1[i] = y1[0];
            x2[i] = x2[0];
            y2[i] = y2[0];
        }
        image = createImage(WIDTH, HEIGHT);
        grafico_inicial = image.getGraphics();
    }

    public void start() {

        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    public void stop() {
        if (runner != null && runner.isAlive()) {
            runner.stop();
        }
        runner = null;
    }

    public void run() {

        while (runner != null) {

            repaint();
            //Redibuja las 4 lineas de manera aleatoria
            try {
                Thread.sleep(20);
            }
            catch (InterruptedException e) {
            }
        }
    }

    public void paint(Graphics g) {
        update(g);
    }

    public void update(Graphics grafico) {
        grafico_inicial.setColor(Color.white);
        grafico_inicial.fillRect(0, 0, WIDTH, HEIGHT);
        grafico_inicial.setColor(Color.red);

        int line = primero;

        for (int i = 0; i < LINEAS; i++) {

            grafico_inicial.drawLine(x1[line], y1[line],
                    x2[line], y2[line]);
            line++;
            if (line == LINEAS) {
                line = 0;
            }
        }

        line = primero;
        primero--;

        if (primero < 0) {
            primero = LINEAS - 1;
        }

        x1[primero] = x1[line];
        y1[primero] = y1[line];
        x2[primero] = x2[line];
        y2[primero] = y2[line];

        if (x1[primero] + dx2 < WIDTH) {
            x1[primero] += dx1;
        }
        else {
            dx1 = -(2 + (int) (3 * Math.random()));
        }
        if (x1[primero] + dx1 >= 0) {
            x1[primero] += dx1;
        }
        else {
            dx1 = 2 + (int) (3 * Math.random());
        }
        if (y1[primero] + dy1 < HEIGHT) {
            y1[primero] += dy1;
        }
        else {
            dy1 = -(2 + (int) (3 * Math.random()));
        }
        if (y1[primero] + dy1 >= 0) {
            y1[primero] += dy1;
        }
        else {
            dy1 = 2 + (int) (3 * Math.random());
        }
        if (x2[primero] + dx2 < WIDTH) {
            x2[primero] += dx2;
        }
        else {
            dx2 = -(2 + (int) (3 * Math.random()));
        }
        if (x2[primero] + dx2 >= 0) {
            x2[primero] += dx2;
        }
        else {
            dx2 = 2 + (int) (3 * Math.random());
        }
        if (y2[primero] + dy2 < HEIGHT) {
            y2[primero] += dy2;
        }
        else {
            dy2 = -(2 + (int) (3 * Math.random()));
        }
        if (y2[primero] + dy2 >= 0) {
            y2[primero] += dy2;
        }
        else {
            dy2 = 2 + (int) (3 * Math.random());
        }
        grafico.drawImage(image, 0, 0, this);
    }
}
