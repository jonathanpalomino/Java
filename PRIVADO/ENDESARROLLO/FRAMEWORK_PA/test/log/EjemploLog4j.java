/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package log;

/**
 *
 * @author JONATHAN
 */
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class EjemploLog4j {

	private final static Logger log = Logger.getLogger(EjemploLog4j.class);

	public static void main(String[] args) {
		PropertyConfigurator.configure("src/palomino/configuracion/LogStores.properties");
                //BasicConfigurator.configure();
		log.warn("un warning");
		log.error("un error");
                log.info("traza");
                log.fatal("fatal");
	}

}
