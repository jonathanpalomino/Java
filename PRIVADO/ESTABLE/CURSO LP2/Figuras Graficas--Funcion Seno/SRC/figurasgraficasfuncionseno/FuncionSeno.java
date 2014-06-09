/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package figurasgraficasfuncionseno;

import java.applet.Applet;
import java.awt.*;

/**
 *
 * @author JONATHAN
 */
public class FuncionSeno extends Applet
{
int x0,xN,y0,yN;
    double xmin,xmax,ymin,ymax;
    int apAncho,apAlto;
    public void init() {
        Dimension d = size();
        apAncho = d.width;
        apAlto = d.height;

        x0 = y0 = 0;
        xN = apAncho-1;
        yN = apAlto-1;
        xmin = -10.0;
        xmax = 10.0;
        ymin = -1.0;
        ymax = 1.0;
        }
    public void paint( Graphics g ) {
        double x1,y1,x2,y2;
        int j1,j2;

        j1 = ValorY( 0 );
        for( int i=0; i < apAncho; i++ )
            {
            j2 = ValorY( i+1 );
            g.drawLine( i,j1,i+1,j2 );
            j1 = j2;
            }
        }

    private int ValorY( int valor ) {
        double x,y;
        int retorno;

        // Cartesianas equivalentes al punto de la pantalla
        x = (valor * (xmax-xmin) / (apAncho-1)) + xmin;
        // Calculamos el seno de ese punto
        y = Math.sin( x );
        // Escalamos la coordenada y dentro de los limites de la ventana
        retorno = (int)( (y-ymin) * (apAlto-1) / (ymax-ymin) );
        // Reconvertinos el valor cartesiano a punto de pantalla
        retorno = apAlto - retorno;

        return( retorno );
        }
}
