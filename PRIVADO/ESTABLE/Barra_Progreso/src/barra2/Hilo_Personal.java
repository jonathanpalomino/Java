/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package barra2;

import javax.swing.JProgressBar;

/**
 *
 * @author synccon
 */
class Hilo_Personal  implements Runnable{
private JProgressBar jProgressBar;
 private int value = 50;//retardo en milisegundos

    Hilo_Personal(JProgressBar jProgressBar, int valor) {
        this.jProgressBar = jProgressBar;
        this.value=valor;
    }

    @Override
    public void run() {
        for(int i=1;i<=100;i++){
            jProgressBar.setValue(i);
            jProgressBar.repaint();  
            //retardo en milisegundos
            try{Thread.sleep( (this.value*10) );}            
            catch (InterruptedException e){ System.err.println( e.getMessage() ); }     
        }
    }
    
}
