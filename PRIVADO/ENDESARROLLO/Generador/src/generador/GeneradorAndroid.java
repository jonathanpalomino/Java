/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package generador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;

public class GeneradorAndroid {

    private static final String URL = "http://190.81.33.132:9080/soap_capa_presentacion/servicios/presentacion/SoatConsultaPlacaImplWS?wsdl";

    private static void Copiar_URL_to_File(String URL, File archivo_tmp) {
        try {
            URL fi = new URL(URL);
            byte[] ba1 = new byte[1024];
            int baLength;
            FileOutputStream fos1 = new FileOutputStream(archivo_tmp);
            InputStream is1 = fi.openStream();
            while ((baLength = is1.read(ba1)) != -1) {
                fos1.write(ba1, 0, baLength);
            }
            fos1.flush();
            fos1.close();
            is1.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    ArrayList<String> metodos_almacenados = new ArrayList<String>();

    public static void main(String[] args) {
        try {
            File file = new File("wsdl.xml");
            Copiar_URL_to_File(URL, file);
            SAXBuilder builder = new SAXBuilder();

            try {
                Document document = (Document) builder.build(file);
                Element rootNode = document.getRootElement();
                List list = rootNode.getChildren();
                for (int i = 0; i < list.size(); i++) {
                    Element rootNode2;
                    rootNode2 = (Element) list.get(i);
                    for(Attribute atributos :rootNode2.getAttributes()){
                        //definir cada tipo
                        System.out.println("-"+atributos.getAttributeType());
                       // System.out.println("*"+atributos.getValue());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}