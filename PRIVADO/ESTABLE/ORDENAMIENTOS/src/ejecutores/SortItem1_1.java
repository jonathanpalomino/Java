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

import java.awt.*;


/*
 * SortItem1_1.java
 *
 * Creado en 03/08/2011, 11:49:42 PM
 *
 * Aplicacion creada por jonathan-palomino.blogspot.com.
 *
 */

/**
 *
 * @author Jonathan palomino <jhonelfenix@gmail.com>
 */
public class SortItem1_1 extends java.applet.Applet implements Runnable {
    /**
     * The thread that is sorting (or null).
     */
    private Thread kicker;

    /**
     * The array that is being sorted.
     */
    int arr[];

    /**
     * The high water mark.
     */
    int h1 = -1;

    /**
     * The low water mark.
     */
    int h2 = -1;

    /**
     * The name of the algorithm.
     */
    String algName;

    /**
     * The sorting algorithm (or null).
     */
    SortAlgorithm algorithm;

    /**
     * Fill the array with random numbers from 0..n-1.
     */
    void scramble() {
	int a[] = new int[size().height / 2];
	double f = size().width / (double) a.length;
	for (int i = a.length; --i >= 0;) {
            a[i] = (int)(a.length * f * Math.random());
/*jh: fill the array with numbers from 0..n-1, not a scrambled
  set of 0..n-1 unique numbers.  So duplicates will occur in
  in most cases */
/*	    a[i] = (int)(i * f);*/
	}

/*jh: we don't shuffle the array anymore*/
/*
	for (int i = a.length; --i >= 0;) {
	    int j = (int)(i * Math.random());
	    int t = a[i];
	    a[i] = a[j];
	    a[j] = t;
	}
        */
	arr = a;
    }

    /**
     * Pause a while.
     * @see SortAlgorithm
     */
    void pause() {
	pause(-1, -1);
    }

    /**
     * Pause a while, and draw the high water mark.
     * @see SortAlgorithm
     */
    void pause(int H1) {
	pause(H1, -1);
    }

    /**
     * Pause a while, and draw the low&high water marks.
     * @see SortAlgorithm
     */
    void pause(int H1, int H2) {
	h1 = H1;
	h2 = H2;
	if (kicker != null) {
	    repaint();
	}
	try {Thread.sleep(20);} catch (InterruptedException e){}
    }

    /**
     * Initialize the applet.
     */
    public void init() {
	//aca se setea
        String at = getParameter("alg");
	
        if (at == null) {
	    at = "BubbleSort";
	}

	algName = at + "Algorithm";
	scramble();

	resize(100, 100);
    }

    /**
     * Paint the array of numbers as a list
     * of horizontal lines of varying lenghts.
     */
    public void paint(Graphics g) {
	int a[] = arr;
	int y = size().height - 1;

	// Erase old lines
	g.setColor(Color.lightGray);
	for (int i = a.length; --i >= 0; y -= 2) {
	    g.drawLine(arr[i], y, size().width, y);
	}

	// Draw new lines
	g.setColor(Color.black);
	y = size().height - 1;
	for (int i = a.length; --i >= 0; y -= 2) {
	    g.drawLine(0, y, arr[i], y);
	}

	if (h1 >= 0) {
	    g.setColor(Color.red);
	    y = h1 * 2 + 1;
	    g.drawLine(0, y, size().width, y);
	}
	if (h2 >= 0) {
	    g.setColor(Color.blue);
	    y = h2 * 2 + 1;
	    g.drawLine(0, y, size().width, y);
	}
    }

    /**
     * Update without erasing the background.
     */
    public void update(Graphics g) {
	paint(g);
    }

    /**
     * Run the sorting algorithm. This method is
     * called by class Thread once the sorting algorithm
     * is started.
     * @see java.lang.Thread#run
     * @see SortItem#mouseUp
     */
    public void run() {
	try {
	    if (algorithm == null) {
		algorithm = (SortAlgorithm)Class.forName(algName).newInstance();
		algorithm.setParent(null);
	    }
	    algorithm.init();
	    algorithm.sort(arr);
	} catch(Exception e) {
	}
    }

    /**
     * Stop the applet. Kill any sorting algorithm that
     * is still sorting.
     */
    public synchronized void stop() {
	if (kicker != null) {
            try {
		kicker.stop();
            } catch (IllegalThreadStateException e) {
                // ignore this exception
            }
	    kicker = null;
	}
	if (algorithm != null){
            try {
		algorithm.stop();
            } catch (IllegalThreadStateException e) {
                // ignore this exception
            }
	}
    }


    /**
     * For a Thread to actually do the sorting. This routine makes
     * sure we do not simultaneously start several sorts if the user
     * repeatedly clicks on the sort item.  It needs to be
     * synchronoized with the stop() method because they both
     * manipulate the common kicker variable.
     */
    private synchronized void startSort() {
	if (kicker == null || !kicker.isAlive()) {
	    scramble();
	    repaint();
	    kicker = new Thread(this);
	    kicker.start();
	}
    }


    /**
     * The user clicked in the applet. Start the clock!
     */
    public boolean mouseUp(java.awt.Event evt, int x, int y) {
	startSort();
	return true;
    }
}
