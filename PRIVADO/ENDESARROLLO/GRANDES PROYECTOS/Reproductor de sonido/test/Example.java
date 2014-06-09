/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.*;
import javax.swing.*;

/**
 *
 * @author JONATHAN
 */
public class Example {


    /** Runs a sample program that shows dropped files */
    @SuppressWarnings("static-access")
    public static void main( String[] args )
    {
        JFrame frame = new JFrame( "Jalando" );

        final JTextArea text = new JTextArea();
        frame.getContentPane().add(
            new JScrollPane( text ),
            BorderLayout.CENTER );

        new FileDrop( System.out, text, /*dragBorder,*/ new FileDrop.Listener()
        {   public void filesDropped( java.io.File[] files )
            {   for( int i = 0; i < files.length; i++ )
                {   try
                    {   text.append( files[i].getCanonicalPath() + "\n" );
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

        frame.setBounds( 100, 100, 300, 400 );
        frame.setDefaultCloseOperation( frame.EXIT_ON_CLOSE );
        frame.setVisible(true);
    }   // end main

}