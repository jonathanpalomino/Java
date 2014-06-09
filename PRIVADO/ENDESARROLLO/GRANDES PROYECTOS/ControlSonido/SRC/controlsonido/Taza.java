
package controlsonido;
import java.awt.*;
import java.applet.Applet;

public class Taza extends Applet {
    Image imagenes[];
    MediaTracker tracker;
    int index = 0;
    int maxAncho,maxAlto;

    // Componentes off-screen para el doble buffering
    Image offScrImage;
    Graphics offScrCG;
    boolean cargado = false;

    // Inicializa el applet. Establece el tamaño y carga las imágenes
    public void init() {
        // Establece el supervisor de imágenes y dimensiones
        tracker = new MediaTracker( this );
        maxAncho = 228;
        maxAlto = 228;
        imagenes = new Image[34];

        // Establece el doble buffer y cambia el tamaño del applet
        try {
            offScrImage = createImage( maxAncho,maxAlto );
            offScrCG = offScrImage.getGraphics();
            offScrCG.setColor( Color.lightGray );
            offScrCG.fillRect( 0,0,maxAncho,maxAlto );
            resize( maxAncho,maxAlto );
            }
        catch( Exception e ) {
            e.printStackTrace();
            }

        // Carga las imágenes en un array
        for( int i=0; i < 33; i++ )
            {
            String imageFich = 
                new String( "taza"+String.valueOf(i+1)+".gif" );
            imagenes[i] = getImage( getDocumentBase(),imageFich );
            // Pasamos esta imagen al tracker
            tracker.addImage( imagenes[i],i );
            }

        try {
            // Utilizamos el tracker para asegurar que se 
            // cargaran todas las imágenes
            tracker.waitForAll();
            }
        catch( InterruptedException e ) {
            }
        cargado = true;
        }
        
    // Pinta el fotograma actual
    public void paint( Graphics g ) {
        if( cargado )
            {
            // Copia del doble buffer a la pantalla
            g.drawImage( offScrImage,0,0,this );
            // Hacemos una pausa y cogemos la siguiente imagen
            timerloop();
            }
        }

    // Establecemos la primera imagen
    public void start() {
        index = 0;

        if( tracker.checkID( index ) )
            // Pintamos en el doble buffer
            offScrCG.drawImage( imagenes[index],0,0,this );
        }

    // Actualiza los fotogramas para que avance la animación
    @SuppressWarnings("empty-statement")
    public void timerloop() {
        // Se asegura que la imagen esté presente y la mete en el buffer
        if( tracker.checkID( index ) ) 
            {
            // Borra el fondo y obtiene la siguiente imagen
            offScrCG.fillRect( 0,0,100,100 );
            offScrCG.drawImage( imagenes[index],0,0,this );
            index++;

            // Vuelve al principio de la animación
            if( index <= imagenes.length )
                index = 0;
            }
        // Bucle de retardo
        for( int retardo=0; retardo < 200000; retardo++ );
        // Dibujamos el siguiente fotograma
        repaint();
        }
    }


