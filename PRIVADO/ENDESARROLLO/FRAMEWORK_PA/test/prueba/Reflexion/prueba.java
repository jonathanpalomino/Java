/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba.Reflexion;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JONATHAN
 */
public class prueba {

   /* public prueba() {
                try {
            Class userClass = Class.forName("prueba.Reflexion.User");
            Field[] userFields = userClass.getFields();
            for(int i=0;i<userFields.length;i++){
                System.out.println(userFields[i]);//variables declaradas
            }
            
            Field[] userFields2 = userClass.getDeclaredFields();
            for(int i=0;i<userFields2.length;i++){
                System.out.println(userFields2[i]);//variables declaradas
            }
            //System.out.println(userClass.getName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

                        try {
            Class userClass = Class.forName("prueba.Reflexion.User");
            Field[] userFields = userClass.getFields();
            for(int i=0;i<userFields.length;i++){
                System.out.println("publicas "+userFields[i]);//variables declaradas publicas
            }
            
            Field[] userFields2 = userClass.getDeclaredFields();
            for(int i=0;i<userFields2.length;i++){
                System.out.println("ambas "+i+" - "+userFields2[i]);//variables declaradas publicas y privadas
                
            }
            Object fieldValue = userClass.getField("address").getShort(null);
            System.out.println(fieldValue);
            
            //System.out.println(userClass.getName());
        } catch (IllegalAccessException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchFieldException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
