package simMPLS.principal;

import javax.swing.*;

import simMPLS.interfaz.splash.JSplash;
import simMPLS.interfaz.simulador.JSimulador;
import simMPLS.interfaz.utiles.*;

/** Clase principal del simulador. Desde aqu� se crea un nuevo simulador que
 * comenzar� a funcionar de inmediato.
 * @author <B>Manuel Dom�nguez Dorado</B><br><A
 * href="mailto:ingeniero@ManoloDominguez.com">ingeniero@ManoloDominguez.com</A><br><A href="http://www.ManoloDominguez.com" target="_blank">http://www.ManoloDominguez.com</A>
 * @version 1.0
 */

public class openSimMPLS {

    /** Muestra en la consola un comunicado diciendo que el programa se encuentra
     * licenciado bajo GPL v2.0 de la Free Software Foundation, como se indica en la
     * propia licencia GPL que ha de hacerse.
     * @since 1.0
     */
    public static void mostrarGPL() {
		try {
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Open_SimMPLS_1.0_"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Guarantee_of_Service_(GoS)_support_over_MPLS_using_active_techniques."));
	        System.out.println();
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("(C)_Copyright_2004"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Manuel_Dominguez_Dorado"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("manolodd@eresmas.com"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("http://manolodd.virtualave.net"));
        	System.out.println();
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("This_program_is_free_software;_you_can_redistribute_it_and/or_modify"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("it_under_the_terms_of_the_GNU_General_Public_License_as_published_by"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("the_Free_Software_Foundation;_either_version_2_of_the_License,_or"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("(at_your_option)_any_later_version."));
        	System.out.println();
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("This_program_is_distributed_in_the_hope_that_it_will_be_useful,"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("but_WITHOUT_ANY_WARRANTY;_without_even_the_implied_warranty_of"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("MERCHANTABILITY_or_FITNESS_FOR_A_PARTICULAR_PURPOSE.__See_the"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("GNU_General_Public_License_for_more_details."));
        	System.out.println();
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("You_should_have_received_a_copy_of_the_GNU_General_Public_License"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("along_with_this_program;_if_not,_write_to_the_Free_Software"));
        	System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Foundation,_Inc.,_59_Temple_Place,_Suite_330,_Boston,_MA__02111-1307__USA"));
        	System.exit(0);
		} catch (Exception e) {
                    //e.printStackTrace();
			System.out.println(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Error_trying_to_translate."));
		}
    }

    /** Funci�n principal de la aplicaci�n. Comprueba los argumentos pasados por l�nea
     * de comandos e inicia el simulador si es necesario. Si no lo es, muestra la
     * licencia bajo la cual est� liberado el programa.
     * @param args Los argumentos pasados desde la linea de comandos.
     * @since 0.9
     */
    public static void main(String args[]) {
        if (args.length > 0) {
            mostrarGPL();
        }
        try {
            UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        splash = new simMPLS.interfaz.splash.JSplash();
        splash.show();
        splash.ponerTexto(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("Loading_icons..."));
        dispensadorDeImagenes = new TDispensadorDeImagenes();
        splash.ponerTexto(java.util.ResourceBundle.getBundle("simMPLS/lenguajes/lenguajes_es_ES").getString("openSimMPLS.generandoInterfaz"));
        simulador = new JSimulador(dispensadorDeImagenes);
        java.awt.Dimension tamPantalla=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        simulador.setBounds(0,0,tamPantalla.width,tamPantalla.height);
        simulador.show();
        splash.dispose();
    }

    // Variables declaration - do not modify
    /** Este atributo es un objeto que almacena todas las im�genes que se van a
     * necesitar en el programa; de esta forma, aunque ocupan memoria no se cargan m�s
     * que una vez, mejorando la velocidad global de Open SimMPLS.
     * @since 1.0
     */    
    private static TDispensadorDeImagenes dispensadorDeImagenes;
    /** Imagen con el logo y la informaci�n del programa. Se presentar� al inicio de la
     * aplicaci�n y mientras �sta est� haciendo cosas de fondo.
     * @since 1.0
     */
    private static simMPLS.interfaz.splash.JSplash splash;
    /** Instancia del simulador que ver� el usuario en la pantalla y desde donde podr�
     * hacer todo.
     * @since 1.0
     */
    private static simMPLS.interfaz.simulador.JSimulador simulador;
}
