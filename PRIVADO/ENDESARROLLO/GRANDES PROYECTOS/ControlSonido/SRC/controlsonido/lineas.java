/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controlsonido;

import java.awt.*;
import java.applet.Applet;

public class lineas extends Applet {
    int LINEAS = 25;
    int gSup = 3;
    int gInf = 3;
    int gIzq = 2;
    int gDch = 6;
    int apAncho,apAlto;
    int gLineas[][] = new int[LINEAS][4];

    public void init() {
        Dimension d = size();
        apAncho = d.width;
        apAlto = d.height;
        }

    public void start() {
        gLineas[0][0] = aleatorio( apAncho );
        gLineas[0][1] = aleatorio( apAlto );
        gLineas[0][2] = aleatorio( apAncho );
        gLineas[0][3] = aleatorio( apAlto );

        for( int i=1; i < LINEAS; i++ )
            {
            CopiaLinea( i,i-1 );
            RecalcLinea( i );
            }
        repaint();
        }

    public void paint( Graphics g ) {
        while( true )
            {
            for( int i=LINEAS-1; i > 0; i-- )
                CopiaLinea( i,i-1 );
            RecalcLinea( 0 );
            g.setColor( Color.black );
            g.drawLine(
                gLineas[0][0],gLineas[0][1],
                gLineas[0][2],gLineas[0][3] );
            g.setColor( getBackground() );
            g.drawLine( gLineas[LINEAS-1][0],gLineas[LINEAS-1][1],
                gLineas[LINEAS-1][2],gLineas[LINEAS-1][3] );
            }
        }

    private void CopiaLinea( int desde,int hasta ) {
        for( int i=0; i < 4; i++ )
            gLineas[desde][i] =  gLineas[hasta][i];
        }

    public int aleatorio( int rango ) {
        double retornoMath;

        retornoMath = Math.random();
        return( (int)(retornoMath * rango) );
        }

    private void RecalcLinea( int i ) {
        gLineas[i][1] += gSup;
        if( (gLineas[i][1] < 0 )  ||  (gLineas[i][1] > apAlto) )
            {
            gSup *= -1;
            gLineas[i][1] += 2*gSup;
            }

        gLineas[i][3] += gInf;
        if( (gLineas[i][3] < 0 )  ||  (gLineas[i][3] > apAlto) )
            {
            gInf *= -1;
            gLineas[i][3] += 2*gInf;
            }

        gLineas[i][0] += gIzq;
        if( (gLineas[i][0] < 0 )  ||  (gLineas[i][0] > apAncho) )
            {
            gIzq *= -1;
            gLineas[i][0] += 2*gIzq;
            }

        gLineas[i][2] += gDch;
        if( (gLineas[i][2] < 0 )  ||  (gLineas[i][2] > apAncho) )
            {
            gDch *= -1;
            gLineas[i][2] += 2*gDch;
            }
        }

    }

