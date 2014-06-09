//
//  ControlSonido.java
package controlsonido;

import java.awt.*;
import java.applet.*;

// Este applet presenta tres botones para controla el sonido que se emite.
// Permite que se indique este sonido en el parámetro SONIDO cuando se
// llama desde una página html
//
public class ControlSonido extends Applet implements Runnable {
    int ancho = 260;
    int alto = 40;

    String ficheroSonido;
    AudioClip sonido = null;
    Button reproducir = null;
    Button repeticion = null;
    Button parar = null;
    Checkbox carga = null;
    Event pendiente = null;
    Thread thrd = null;
    boolean cargando = false;

    // Inicializacion del applet
    public synchronized void init() {
        Dimension d = size();
        resize( d.width,d.height );

        // Recogemos el nombre del fichero de sonido que se quiere
        // reproducir y que se pasa como parametro al applet
        // Si no se indica nada, reproduce el de defecto
        ficheroSonido = getParameter( "SONIDO" );
        if( ficheroSonido == null )
            ficheroSonido = "spacemusic.au";

        // Incorporamos al applet los tres botones de control de la
        // reproduccion
        reproducir = new Button( "Reproducir" );
        add( reproducir );
        repeticion = new Button( "Repetir" );
        add( repeticion );
        parar = new Button( "Detener" );
        add( parar );

        // Incorporamos el checkbox de carga, que si ya esta cargando
        // el thread el fichero de sonido, aparece marcada
        carga = new Checkbox( "Cargar" );
        if( cargando )
            carga.setState( true );
        add( carga );
        }


    public boolean mouseEnter( Event evt,int x,int y ) {
        // Cuando el raton entre en los dominios del applet, en la
        // barra de status aparecera en nombre del fichero que se
        // esta reproduciendo
        showStatus( ficheroSonido );

        return( true );
        }


    public boolean mouseExit( Event evt,int x,int y ) {
        // Vaciamos la linea de status cuando sale el raton del applet
        showStatus( null );

        return( true );
        }


    // Cuando pulsemos una tecla, se detiene el sonido y matamos
    // todo
    public synchronized boolean keyDown( Event evt,int key ) {
        // Paramos la reproduccion del sonido
        if( sonido != null )
            sonido.stop();
        // Matamos el thread
        if( thrd != null )
            thrd.stop();
        // Desmarcamos el checkbox
        carga.setState( false );
        // Reinicializamos variables
        sonido = null;
        pendiente = null;
        thrd = null;
        cargando = false;
        cargaSonido( null );

        return( true );
        }


    // Controlamos las acciones de los botones del applet
    public boolean action( Event evt,Object obj ) {
        if( evt.target == null )
            return( true );
        if( evt.target.equals( reproducir ) )
            {
            if( !cargaSonido( evt ) )
                {
                showStatus( "Reproduciendo " + ficheroSonido );
                sonido.play();
                }
            }
        else if( evt.target.equals( repeticion ) )
            {
            if( !cargaSonido( evt ) )
                {
                showStatus( "Repitiendo " + ficheroSonido );
                sonido.loop();
                }
            }
        else if( evt.target.equals( parar ) )
            {
            pendiente = null;
            if( sonido != null )
                {
                showStatus( "Parando " + ficheroSonido );
                sonido.stop();
                }
            }
        else if( evt.target.equals( carga ) )
           cargaSonido( null );

        return( true );
        }


    // Carga el fichero de sonido, creando un thread para que lo haga
    synchronized boolean cargaSonido( Event evt ) {
        carga.setState( true );
        if( !cargando )
            {
            cargando = true;
            if( sonido == null )
                {
                showStatus( "Cargando " + ficheroSonido );
                if( evt != null )
                    pendiente = evt;
                thrd = new Thread( this );
                thrd.start();
                return( true );
                }
            }
        if( sonido == null )
            {
            if( evt != null )
                pendiente = evt;
            showStatus( "Sigue cargando " + ficheroSonido );
            return( true );
            }
        return( false );
        }


    public void run() {
        // Cargamos el ficehro de sonido
        sonido = getAudioClip( getDocumentBase(),ficheroSonido );
        showStatus( "Cargado " + ficheroSonido );
        // Quitamos el checkbox indicador
        remove( carga );
        repaint();
        // Si estabamos pendientes de la carga del fichero de sonido
        // lanzamos un evento indicando que ya esta listo
        if( pendiente != null )
            action( pendiente,this );
        // Ya no esta pendiente la carga del fichero, y el thread
        // encargado de esa carga ya ha cumplido su mision
        pendiente = null;
        thrd = null;
        }


    public synchronized void stop() {
        // Si ya habiamos cargado el fichero de sonido, detenemos
        // la musica
        if( sonido != null )
            sonido.stop();

        // Si esta todavia en activo el thread encargado de la carga del
        // fichero de sonido, detenemos la carga y volvemos el
        // checkbox indicador a su estado vacio
        if( thrd != null )
            {
            thrd.stop();
            carga.setState( false );
            sonido = null;
            pendiente = null;
            thrd = null;
            cargando = false;
            }
        }
    }

//------------------------------------ Final del fichero ControlSonido.java

