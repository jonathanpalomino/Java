/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.*;
import  java.io.*;
/**
 *
 * @author JONATHAN
 */
public class Prueba extends JFrame
{

    public Prueba()
    {
        setVisible(true);
        setBounds( 100, 100, 300, 400 );
    }

    @SuppressWarnings("static-access")
    public static void main( String[] args )
    {
        Prueba obj = new Prueba();
        obj.show();
      new  FileDrop( new Prueba(), new FileDrop.Listener()
      {   public void  filesDropped( File[] files )
          {
              for( int i = 0; i < files.length; i++ )
                {   try
                    {
                        System.out.println(files[i].getCanonicalPath());
                    }   // end try
                    catch( java.io.IOException e ) {}
                }   // end for: through each dropped file
          }   // end filesDropped
      }); // end FileDrop.Listener

}
}