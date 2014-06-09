/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generador;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author synccon
 */
public class Test {
    
    public final static String URL = "http://www.webservicex.net/globalweather.asmx?wsdl";
    public static final String NAMESPACE = "http://www.webserviceX.NET";
    public static final String SOAP_ACTION_PREFIX = "/";
    private static final String METHOD = "GetWeather";
    private static String resp;
    
    public static void main(String[] args) {
        try {
           // Modelo el request
            SoapObject request = new SoapObject(NAMESPACE, METHOD);
            request.addProperty("CityName", "LIMA");// Paso parametros al WS
            request.addProperty("CountryName", "PERU");
            
            // SoapEnvelop.VER11 is SOAP Version 1.1 constant
            // Modelo el Sobre
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            //bodyOut is the body object to be sent out with this envelope
            //envelope.bodyOut = request;
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;//era necesario esto

            // Modelo el transporte
            HttpTransportSE transport = new HttpTransportSE(URL);
            
            try {
                // Llamada
                String llamado = NAMESPACE + SOAP_ACTION_PREFIX + METHOD;
                transport.call(llamado, envelope);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            //bodyIn is the body object received with this envelope
            if (envelope.bodyIn != null) {
                //getProperty() Returns a specific property at a certain index.
                // Resultado
                SoapPrimitive resultSOAP = (SoapPrimitive) ((SoapObject) envelope.bodyIn).getProperty(0);
                resp = resultSOAP.toString();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            resp = e.getMessage();
        }
        System.out.println("Respuesta\n" + resp);
    }
}
