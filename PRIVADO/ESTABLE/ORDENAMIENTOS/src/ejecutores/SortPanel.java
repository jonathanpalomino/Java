/*
 * Copyright (C) 2011 Jonathan palomino <jhonelfenix@gmail.com>
 *
 * Este programa es software libre: usted puede redistribuirlo y / o modificarlo
 * bajo los términos de la Licencia Pública General de GNU según lo publicado por
 * la Foundacion de Software Libre, ya sea la versión 3 de la Licencia, o
 * (a su elección) cualquier versión posterior.
 *
 * Este programa se distribuye con la esperanza de que sea útil,
 * pero SIN NINGUNA GARANTÍA, incluso sin la garantía implícita de
 * o IDONEIDAD PARA UN PROPÓSITO PARTICULAR. ver el
 * GNU General Public License para más detalles.
 *
 * Usted debería haber recibido una copia de la Licencia Pública General de GNU
 * junto con este programa. Si no es así, consulte <http://www.gnu.org/licenses/>.
 */
package ejecutores;


/*
 * SortPanel.java
 *
 * Creado en 04/08/2011, 12:05:02 AM
 *
 * Aplicacion creada por jonathan-palomino.blogspot.com.
 *
 */
/**
 *
 * @author Jonathan palomino <jhonelfenix@gmail.com>
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Panel;

public class SortPanel extends Panel
        implements Runnable {

    private Thread kicker;
    int[] arr;
    int h1;
    int h2;
    int c1;
    int c2;
    String algName;
    SortAlgorithm algorithm;

    SortPanel() {
        Bloquear();
        setPreferredSize(preferredSize());
        setSize(preferredSize());
        setAlgorithm("BubbleSort");
    }

    public Dimension preferredSize() {
        return new Dimension(125, 100);
    }

    public Dimension minimumSize() {
        return new Dimension(125, 100);
    }

    void setAlgorithm(String paramString) {
        if (kicker != null) {
            kicker.stop();
            kicker = null;
        }
        algName = (paramString + "Algorithm");
        scramble();
        repaint();
    }

    void scramble() {
        int[] arrayOfInt = new int[size().height / 2];
        double d = 100.0D / arrayOfInt.length;
        System.out.println("tamañoG " + d);
        int i = arrayOfInt.length;
        System.out.println("tamaño " + i);
        
        for(int j=i;j>=0;j--){
            System.out.println("valor j " + j);
            arrayOfInt[j-1] = (int) (i * d);
        }
        /*while (i >= 0) {
            arrayOfInt[i - 1] = (int) (i * d);

            i--;
        } */

        i = arrayOfInt.length;
        do {
            int j = (int) (i * Math.random());
            int k = arrayOfInt[i];
            arrayOfInt[i] = arrayOfInt[j];
            arrayOfInt[j] = k;

            i--;
        } while (i >= 0);

        arr = arrayOfInt;
    }

    void pause() {
        pause(-1, -1);
    }

    void pause(int paramInt) {
        pause(paramInt, -1);
    }

    void pause(int paramInt1, int paramInt2) {
        h1 = paramInt1;
        h2 = paramInt2;
        if (kicker != null) {
            repaint();
        }
        try {
            Thread.sleep(20L);
            return;
        } catch (InterruptedException localInterruptedException) {
        }
    }

    void compex(int paramInt1, int paramInt2) {
        c1 = paramInt1;
        c2 = paramInt2;
        pause(h1, h2);
    }

    public void paint(Graphics paramGraphics) {
        int[] arrayOfInt = arr;
        int i = size().height - 1;
        int j = 20;

        paramGraphics.setColor(getBackground());
        int k = arrayOfInt.length;
        do {
            paramGraphics.drawLine(j + arr[k], i, size().width, i);
            paramGraphics.drawLine(0, i, 19, i);

            i -= 2;
            k--;
        } while (k >= 0);

        paramGraphics.drawLine(4, 0, 4, size().height);

        paramGraphics.setColor(Color.black);
        i = size().height - 1;
        k = arrayOfInt.length;
        do {
            paramGraphics.drawLine(j, i, j + arr[k], i);
            if ((k == c1) || (k == c2)) {
                paramGraphics.setColor(Color.red);
                paramGraphics.drawLine(4, i, 17, i);
                paramGraphics.setColor(Color.black);
            }
            i -= 2;
            k--;
        } while (k >= 0);

        if ((c1 >= 0) && (c2 >= 0)) {
            paramGraphics.setColor(Color.red);
            k = c1 * 2 + 1;
            int m = c2 * 2 + 1;
            paramGraphics.drawLine(4, k, 4, m);
        }
    }

    public void update(Graphics paramGraphics) {
        paint(paramGraphics);
    }

    public void run() {
        try {
            algorithm = ((SortAlgorithm) Class.forName(algName).newInstance());
            algorithm.setParent(this);
            algorithm.init();
            algorithm.sort(arr);
            return;
        } catch (Exception localException) {
        }
    }

    public synchronized void stop() {
        if (kicker != null) {
            try {
                kicker.stop();
            } catch (IllegalThreadStateException localIllegalThreadStateException1) {
            }
            kicker = null;
        }
        if (algorithm != null) {
            try {
                algorithm.stop();
                return;
            } catch (IllegalThreadStateException localIllegalThreadStateException2) {
            }
        }
    }

    private synchronized void startSort() {
        if ((kicker == null) || (!kicker.isAlive())) {
            scramble();
            repaint();
            kicker = new Thread(this);
            kicker.start();
        }
    }

    public boolean mouseUp(Event paramEvent, int paramInt1, int paramInt2) {
        startSort();
        return true;
    }

    private void Bloquear() {
        h1 = -1;

        h2 = -1;

        c1 = -1;

        c2 = -1;
    }
}