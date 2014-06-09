/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

/**
 *
 * @author JONATHAN
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Stack;

public class Trazar
{
  static final int ERROR = 0;
  static final int MENSAJE = 1;
  static final int DEPURAR = 2;
  static final int TRAZAR = 3;
  static String[] sSalidas = { "ERR", "OUT", "OUT", "ERR" };
  static String sFormatoFecha = "dd-MM-yyyy HH:mm";
  static final String[] sColor1 = { "<font color=\"#990000\">", "<font color=\"#000099\">", "<font color=\"#999900\">", "<font color=\"#9900D0\">" };
  static final String[] sColor2 = { "<font color=\"#800000\">", "<font color=\"#000080\">", "<font color=\"#707000\">", "<font color=\"#800070\">" };
  static final String[] sColorF = { "FFDFDF", "EEEEFF", "FFFFEE", "FFEEFF" };

  static String sCabeceraA = "<html>\r\n<head>\r\n<title>\r\n";
  static String sCabeceraB1 = "</title>\r\n</head>\r\n<body bgcolor=\"#";
  static String sCabeceraB2 = "\">\r\n<p><a name=\"Final de Página\" href=\"#Actualizar\">Final de Página</a><br>\r\n";
  static String sCabeceraC = "<a href=\"javascript:history.go(0)\" name=\"Actualizar\">Actualizar</a> <a href=\"#Final de Página\">Inicio de Página</a><br>";

  static Properties properties = null;
  static boolean bGuardarDepurar = false;
  static boolean bGuardarStack = false;
  static boolean bGuardarError = false;
  static boolean bGuardarMensaje = false;

  static String sTamanoDepurar = "1M";
  static String sTamanoStack = "1M";
  static String sTamanoMensaje = "1M";
  static String sTamanoError = "1M";

  static boolean bTrazarDepurar = false;
  static boolean bTrazarStack = false;
  static boolean bTrazarError = true;
  static boolean bTrazarMensaje = true;

  static int[] iTamano = { 1048576, 1048576, 1048576, 1048576 };

  static String sSalto = "<br>";
  static String sTab = "----";

  static boolean bEsperar = false;
  static Stack[] stkMensajes = { new Stack(), new Stack(), new Stack(), new Stack() };

  static String sMarcoI = "<html>\r\n<head>\r\n<title>Mensajes TRONWEB</title>\r\n</head>\r\n<frameset cols=\"160,7*\" framespacing=\"1\">\r\n<frame name=\"Indice\" scrolling=\"no\" noresize target=\"TGIndice\" src=\"TWIndice.htm\">\n\r<frameset rows=\"*,*,*,*\">\n\r";
  static String sMarcoF = "</frameset>\r\n<noframes>\r\n<body>\r\n</body>\r\n</noframes>\r\n</frameset>\r\n</html>";

  static String sIndiceI = "<html>\r\n<head>\r\n<title>Indice de Trazas TRONWEB</title>\r\n</head><body topmargin=\"0\" leftmargin=\"0\" bgcolor=\"#F5FFF5\">\r\n<div align=\"center\"><center>\r\n<table border=\"6\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" height=\"100%\" bordercolorlight=\"#C0C0C0\" bordercolordark=\"#808080\" bordercolor=\"#000000\">";

  static String sIndiceF = "</table>\r\n</center>\r\n</div>\r\n</body>\r\n</html>";

  public static void cargarTrazas()
  {
    if (properties != null) return;
    properties = new Properties();
    try
    {
      String lsRuta = System.getProperty("user.dir") + System.getProperty("file.separator") + "Trazar.prop";
      InputStream isProyecto = new FileInputStream(lsRuta);
      properties.load(isProyecto);
    } catch (Exception e) {
      e.printStackTrace();
    }
    bGuardarDepurar = new Boolean(properties.getProperty("GUARDAR.DEPURAR", "false")).booleanValue();
    bGuardarStack = new Boolean(properties.getProperty("GUARDAR.STACK", "false")).booleanValue();
    bGuardarError = new Boolean(properties.getProperty("GUARDAR.ERROR", "true")).booleanValue();
    bGuardarMensaje = new Boolean(properties.getProperty("GUARDAR.MENSAJE", "true")).booleanValue();

    bTrazarDepurar = new Boolean(properties.getProperty("TRAZAR.DEPURAR", "false")).booleanValue();
    bTrazarStack = new Boolean(properties.getProperty("TRAZAR.STACK", "false")).booleanValue();
    bTrazarMensaje = new Boolean(properties.getProperty("TRAZAR.MENSAJE", "true")).booleanValue();
    bTrazarError = new Boolean(properties.getProperty("TRAZAR.ERROR", "true")).booleanValue();

    sSalidas[1] = properties.getProperty("MENSAJE.SALIDA", "TWMensaje.htm");
    sSalidas[0] = properties.getProperty("ERROR.SALIDA", "TWError.htm");
    sSalidas[2] = properties.getProperty("DEPURAR.SALIDA", "TWDepurar.htm");
    sSalidas[3] = properties.getProperty("STACK.SALIDA", "TWStack.htm");

    sTamanoDepurar = properties.getProperty("DEPURAR.TAMAÑO", "1M");
    sTamanoStack = properties.getProperty("STACK.TAMAÑO", "1M");
    sTamanoMensaje = properties.getProperty("MENSAJE.TAMAÑO", "1M");
    sTamanoError = properties.getProperty("ERROR.TAMAÑO", "1M");

    sFormatoFecha = properties.getProperty("FORMATO.FECHA", "dd-MM-yyyy HH:mm");

    iTamano[2] = convierteKM(sTamanoDepurar);
    iTamano[3] = convierteKM(sTamanoStack);
    iTamano[0] = convierteKM(sTamanoError);
    iTamano[1] = convierteKM(sTamanoMensaje);

    crearMarcos();
    crearIndice();
    borrarAntiguos();
  }

  public static String[] getEstadoTrazas()
  {
    /*String[] lasRes = { bGuardarDepurar, bGuardarMensaje, bGuardarError, bGuardarStack, 
      bGuardarMensaje, bTrazarDepurar, bTrazarMensaje, bTrazarError, bTrazarStack, sFormatoFecha, 
      sTamanoDepurar, sTamanoStack, sTamanoMensaje, sTamanoError };*/
    
    String[] lasRes={
        String.valueOf(bGuardarDepurar),String.valueOf(bGuardarMensaje),String.valueOf(bGuardarError),String.valueOf(bGuardarStack),
        String.valueOf(bGuardarMensaje),String.valueOf(bTrazarDepurar),String.valueOf(bTrazarMensaje),String.valueOf(bTrazarError),String.valueOf(bTrazarStack),String.valueOf(sFormatoFecha),
       sTamanoDepurar, sTamanoStack, sTamanoMensaje, sTamanoError 
    };
    return lasRes;
  }

  public static int convierteKM(String pDato)
  {
    if (pDato.endsWith("M"))
    {
      return new Integer(pDato.replace('M', ' ').trim()).intValue() * 1048576;
    }
    if (pDato.endsWith("K"))
    {
      return new Integer(pDato.replace('K', ' ').trim()).intValue() * 1024;
    }
    return new Integer(pDato.trim()).intValue();
  }

  public static void borrarAntiguos()
  {
    for (int x = 0; x < 4; x++)
    {
      File lFic = new File(sSalidas[x]);
      if (lFic.exists())
      {
        lFic.delete();
      }
      guardaError(sColor1[x] + sSalidas[x] + "<br>", x, sColorF[x]);
    }
  }

  public static boolean isDepurar()
  {
    return bGuardarDepurar;
  }

  public static boolean isTrazar(String psSesion)
  {
    return true;
  }

  public static synchronized void depurar(String psTexto)
  {
    depurar(psTexto, "");
  }

  public static synchronized void depurar(String psTexto, String psSesion)
  {
    cargarTrazas();
    if ((isDepurar()) || (isTrazar(psSesion)))
    {
      analizar(psTexto, "depurar", psSesion);
    }
  }

  public static synchronized void mensaje(String psTexto) {
    mensaje(psTexto, "");
  }

  public static synchronized void mensaje(String psTexto, String psSesion)
  {
    cargarTrazas();
    analizar(psTexto, "mensaje", psSesion);
  }

  public static synchronized void error(String psTexto) {
    error(psTexto, "");
  }

  public static synchronized void error(String psTexto, String psSesion)
  {
    cargarTrazas();
    analizar(psTexto, "error", psSesion);
  }

  public static synchronized void stack(Exception e)
  {
    stack(e, "");
  }

  public static synchronized void stack(Exception e, String psSesion)
  {
    boolean lbCab = false;
    cargarTrazas();

    if (bTrazarStack)
    {
      System.err.println("[?>]" + getFecha());
      e.printStackTrace();
      System.err.println("[<?]");
    }

    if (bGuardarStack)
    {
      try
      {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream miPS = new PrintStream(out, true);
        e.printStackTrace(miPS);

        guardaError(sColor1[3] + "[x>] " + getFecha() + " - " + psSesion + ":</font>" + sSalto + sColor2[3] + out.toString() + sSalto + sColor1[3] + "[&lt;x]<br></font>", 3, sColorF[3]);
      }
      catch (Exception f)
      {
        f.printStackTrace();
      }
    }
  }

  public static synchronized void analizar(String psDato, String psCual, String psSesion)
  {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream miPS = new PrintStream(out, true);
    try
    {
      fuerzaTraza();
    }
    catch (Exception e)
    {
      e.printStackTrace(miPS);
      String texto = out.toString();
      int liDesde = texto.indexOf(psCual);
      if (liDesde > -1)
      {
        liDesde = texto.indexOf("at ", liDesde) + 3;
        int liHasta = texto.indexOf("\n", liDesde + 1);
        String lsTexto = new String(texto.substring(liDesde, liHasta - 1));

        if (psCual.equals("error"))
        {
          if (bTrazarError)
            System.err.println("[?>] " + getFecha() + " - " + psSesion + ":\n" + lsTexto + "\n\t" + psDato + "\n[<?]");
          if (bGuardarError) {
            guardaError(sColor1[0] + "[?>] " + getFecha() + " - " + psSesion + ":</font>" + sSalto + sTab + sColor2[0] + lsTexto + sSalto + sTab + psDato + sSalto + sColor1[0] + "[&lt;?]</font>" + sSalto, 0, sColorF[0]);
          }
        }
        else if (psCual.equals("depurar"))
        {
          if (bTrazarDepurar)
            System.out.println("[!>] " + getFecha() + " - " + psSesion + ":\n" + sTab + lsTexto + sSalto + sTab + psDato + "\n[<!]");
          if (bGuardarDepurar) {
            guardaError(sColor1[2] + "[*>] " + getFecha() + " - " + psSesion + ":</font>" + sSalto + sTab + sColor2[2] + lsTexto + sSalto + sTab + psDato + sSalto + sColor1[2] + "[&lt;*]</font>" + sSalto, 2, sColorF[2]);
          }
        }
        else if (psCual.equals("mensaje"))
        {
          if (bTrazarMensaje)
            System.out.println("[!>] " + getFecha() + " - " + psSesion + ":\n" + sTab + lsTexto + sSalto + sTab + psDato + "\n[<!]");
          if (bGuardarMensaje)
            guardaError(sColor1[1] + "[!>] " + getFecha() + " - " + psSesion + ":</font>" + sSalto + sTab + sColor2[1] + lsTexto + sSalto + sTab + psDato + sSalto + sColor1[1] + "[&lt;!]</font>" + sSalto, 1, sColorF[1]);
        }
      }
      try
      {
        out.close();
      } catch (Exception localException1) {
      }
      miPS.flush();
      miPS.close();
    }
  }

  static String getFecha()
  {
    return new SimpleDateFormat(sFormatoFecha).format(new Date());
  }

  private static void guardaError(String psMensaje, int piCual, String psColor)
  {
    stkMensajes[piCual].push(new String[] { psMensaje, psColor });
    if (bEsperar) return;
    bEsperar = true;
    try
    {
      guardaError();
    } catch (Exception localException) {
    }
    bEsperar = false;
  }

  private static synchronized void guardaError()
  {
    try
    {
      for (int x = 0; x < 4; x++)
      {
        File lFic = new File(sSalidas[x]);
        if (lFic.length() > iTamano[x])
        {
          lFic.delete();
        }
        if (!stkMensajes[x].empty()) {
          boolean lbCab = false;
          if (!lFic.exists()) lbCab = true;
          RandomAccessFile miRFic = new RandomAccessFile(lFic, "rw");

          if (!lbCab)
          {
            int liLon = (int)miRFic.length() - sCabeceraC.length();
            if (liLon > -1)
              miRFic.seek(liLon);
            else {
              miRFic.seek(0L);
            }
          }

          while (!stkMensajes[x].empty())
          {
            String[] lsDatosMng = (String[])stkMensajes[x].pop();
            if (lbCab)
            {
              miRFic.write(new String(sCabeceraA + sSalidas[x] + sCabeceraB1).getBytes());
              miRFic.write(lsDatosMng[1].getBytes());
              miRFic.write(sCabeceraB2.getBytes());
              lbCab = false;
            }
            miRFic.write(lsDatosMng[0].getBytes());
          }
          miRFic.write(sCabeceraC.getBytes());
          miRFic.close();
        }
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static synchronized void fuerzaTraza()
  {
    throw new NullPointerException();
  }

  private static byte[] getFrame(String psNombre)
  {
    String lsNombre = psNombre.substring(0, psNombre.indexOf("."));
    return new String("<frame name=\"" + lsNombre + "\"  src=\"" + psNombre + "\">\r\n").getBytes();
  }

  private static byte[] tr() {
    return new String("<tr>\r\n").getBytes();
  }

  private static void crearMarcos()
  {
    try
    {
      FileOutputStream miOut = new FileOutputStream("TWTrazas.htm");
      miOut.write(sMarcoI.getBytes());
      miOut.flush();
      miOut.write(getFrame(sSalidas[0]));
      miOut.flush();
      miOut.write(getFrame(sSalidas[1]));
      miOut.flush();
      miOut.write(getFrame(sSalidas[2]));
      miOut.flush();
      miOut.write(getFrame(sSalidas[3]));
      miOut.flush();
      miOut.write(sMarcoF.getBytes());
      miOut.flush();
      miOut.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static void crearIndice()
  {
    try
    {
      FileOutputStream miOut = new FileOutputStream("TWIndice.htm");
      miOut.write(sIndiceI.getBytes());
      miOut.write(getaReferencia(sSalidas[0], "Errores", "800000"));
      miOut.write(getaReferencia(sSalidas[1], "Mensajes", "000080"));
      miOut.write(getaReferencia(sSalidas[2], "Depuración", "008000"));
      miOut.write(getaReferencia(sSalidas[3], "Stack", "800080"));
      miOut.write(sIndiceF.getBytes());
      miOut.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  private static byte[] getaReferencia(String psTipo, String psEtiqueta, String psColor)
  {
    String lsNombre = psTipo.substring(0, psTipo.indexOf("."));
    StringBuffer lsRes = new StringBuffer("<tr><td>\r\n<p align=\"center\" style=\"word-spacing: 0; line-height: 100%; text-indent: 0; margin: 0\"><font color=\"#" + psColor + "\" size=\"5\"><b>" + psEtiqueta + "</b></font></a></p><br>\r\n");
    lsRes.append("<p align=\"center\" style=\"word-spacing: 0; line-height: 100%; text-indent: 0; margin: 0\"><a href=\"http:" + psTipo + "\" target=\"" + lsNombre + "\"><font color=\"#" + psColor + "\">Actualizar</font></a></p>\r\n");
    lsRes.append("<p align=\"center\" style=\"word-spacing: 0; line-height: 100%; text-indent: 0; margin: 0\"><a href=\"http:" + psTipo + "#Final de Página\" target=\"" + lsNombre + "\"><font color=\"#" + psColor + "\">Inicio</font></a></p>\r\n");
    lsRes.append("<p align=\"center\" style=\"word-spacing: 0; line-height: 100%; text-indent: 0; margin: 0\"><a href=\"http:" + psTipo + "#Actualizar\" target=\"" + lsNombre + "\"><font color=\"#" + psColor + "\">Final</font></a></p>\r\n");
    lsRes.append("<p align=\"center\" style=\"word-spacing: 0; line-height: 100%; text-indent: 0; margin: 0\"><a href=\"http:" + psTipo + "\" target=\"_blank\"><font color=\"#" + psColor + "\">Nueva</a></font></p>\r\n</td></tr>\r\n");
    return lsRes.toString().getBytes();
  }
}
