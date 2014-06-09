/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generador;

import java.io.IOException;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpResponseException;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

/**
 *
 * @author synccon
 */
public class ServiceCall {

    private static final String NAMESPACE = "http://www.webserviceX.NET";
    private static final String URL = "http://www.webservicex.com/globalweather.asmx";
    private static final String METHOD = "GetCitiesByCountry";
    private static final String SOAP_ACTION_PREFIX = "/";

    public ServiceCall() {
        String sd = prova("PERU");
        System.out.println(sd);
    }

    public String prova(String citta) {
        SoapObject request = new SoapObject(NAMESPACE, "GetCitiesByCountry");
        String soapAction = NAMESPACE+SOAP_ACTION_PREFIX+METHOD;

        request.addProperty("CountryName", citta);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut = request;
        envelope.dotNet = true;

        HttpTransportSE ht = new HttpTransportSE(URL);
        ht.debug = true;
//System.err.println( ht.requestDump );
        try {
            ht.call(soapAction, envelope);
            System.out.println("####################: " + envelope.getResponse());
        } catch (HttpResponseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (XmlPullParserException ex) {
            ex.printStackTrace();
        }

//SoapObject result = (SoapObject)envelope.getResponse();
        return null;
    }

    public static void main(String[] args) {
        new ServiceCall();
    }
}
