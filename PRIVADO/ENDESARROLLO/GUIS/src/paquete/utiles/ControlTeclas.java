/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete.utiles;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.beans.Beans;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import javax.swing.FocusManager;
import javax.swing.JDialog;
import paquete.Contenedores.TDialog;
import paquete.Contenedores.TPanel;
import paquete.Contenedores.TVentana;
import paquete.PRedefinidorTeclas;
import paquete.TTextField;
import ultimo.JPTextField;

/**
 *
 * @author JONATHAN
 */
public final class ControlTeclas {

    static Component compoActivo = null;
    private static ArrayList teclasSuspendidas = new ArrayList();
    private static ArrayList teclasSinUso = new ArrayList();
    private static final double ESCALA_IMPRESION = 0.8D;
    public static final int SIN_MODIFICADOR = 0;
    public static final int SHIFT = 1;
    public static final int CTRL = 2;
    public static final int ALT = 4;
    static Object objeto = null;
    public static final int ENVIAR = setTecla(85,
            2);
    public static final int CONTROLP = setTecla(80,
            2);
    public static final int JSPOOLP = setTecla(115,
            2);
    public static final int CONTROLM = setTecla(77,
            2);
    public static final int REGSIGSHIFT = setTecla(40,
            1);
    public static final int REGANTSHIFT = setTecla(38,
            1);
    public static final int TECLAMAS = setTecla(107,
            0);
    public static final int TECLAMENOS = setTecla(109,
            0);
    public static final int AYUDA = setTecla(112,
            0);
    /**
     * @deprecated
     */
    public static final int RECONECTAR = setTecla(82,
            2);
    public static final int SALIR = setTecla(114,
            0);
    public static final int LISTA = setTecla(115,
            0);
    public static final int AUTOVALOR = setTecla(116,
            0);
    public static final int MENUOPCIONES = setTecla(115,
            1);
    public static final int EDITARCAMPO = setTecla(117,
            0);
    public static final int CREARREGISTRO = setTecla(118,
            0);
    public static final int FILTRARREG = setTecla(119,
            0);
    public static final int IMPRIMIRHC = setTecla(120,
            0);
    public static final int IMPRIMIRHCV = setTecla(120,
            1);
    public static final int MMENSAJES = setTecla(121,
            1);
    public static final int RESTAURAR = setTecla(122,
            0);
    public static final int ACEPTAR = setTecla(123,
            0);
    public static final int BOTONDEFECTO = setTecla(10,
            0);
    public static final int BORRARREG = setTecla(116,
            1);
    public static final int SELECCIONAR = setTecla(113,
            0);
    public static final int MODIFICARREG = SELECCIONAR;
    public static final int SELECCIONAR2 = setTecla(113,
            1);
    public static final int PAGREGANT = setTecla(33,
            0);
    public static final int PAGREGSIG = setTecla(34,
            0);
    public static final int REGANT = setTecla(38,
            0);
    public static final int REGSIG = setTecla(40,
            0);
    public static final int CAMPOSIGCURSOR = setTecla(39,
            0);
    public static final int CAMPOANTCURSOR = setTecla(37,
            0);
    public static final int REGPRIMERO = setTecla(36,
            2);
    public static final int REGULTIMO = setTecla(35,
            2);
    public static final int CAMPOSIG = setTecla(9,
            0);
    public static final int CAMPOANT = setTecla(9,
            1);
    public static final int BLOQUESIG = setTecla(34,
            2);
    public static final int BLOQUEANT = setTecla(33,
            2);
    public static final int COPIAR = setTecla(67,
            2);
    public static final int CORTAR = setTecla(88,
            2);
    public static final int PEGAR = setTecla(86,
            2);
    public static final int SALIRMODREG = setTecla(27,
            0);
    public static final int DESCARTAR = setTecla(90,
            2);
    public static final int DUPLICARREG = setTecla(82,
            2);
    public static final int DUPLICARCAMPO = setTecla(84,
            2);
    public static final int DOBLE_CLICK = setTecla(113,
            0);
    public static final int IR_A = setTecla(73,
            2);
    public static final int CAMBIA_PERSISTENCIA = setTecla(17,
            0);

    public static int setTecla(KeyEvent e) {
        return setTecla(e.getKeyCode(),
                codificaModificador(e));
    }

    public static int codificaModificador(KeyEvent e) {
        int modificadores = (e.getModifiers() & 0x1) == 0 ? 0 : 1;
        modificadores += ((e.getModifiers() & 0x2) == 0 ? 0 : 2);
        modificadores += ((e.getModifiers() & 0x8) == 0 ? 0 : 4);

        return modificadores;
    }

    public static final int setTecla(int pCodigoSin, int pModificador) {
        return pCodigoSin * 8 + pModificador;
    }

    public static boolean esHabilitadaTecla(int piTecla) {
        return !teclasSuspendidas.contains(new Integer(piTecla));
    }

    public static boolean esHabilitadaTeclaLocal(int piTecla, String prgTecla) {
        Integer tecla = new Integer(piTecla);

        return !teclasSinUso.contains(tecla + prgTecla);
    }

    private static Boolean ejecuta(String metodoTeclas, Object padre, Class[] params, Object[] argumentos) {
        Boolean salidaFuncion = null;
        Class clase = padre.getClass();
        try {
            Method funcion = clase.getMethod(metodoTeclas, params);
            salidaFuncion = (Boolean) funcion.invoke(padre, argumentos);
        } catch (InvocationTargetException localInvocationTargetException) {
        } catch (NoSuchMethodException localNoSuchMethodException) {
        } catch (IllegalAccessException localIllegalAccessException) {
        }
        return salidaFuncion;
    }

    private static boolean ejecutarMetodoTecla(int piTecla, Component componente) {
        Component redefinidor = componente;

        while ((redefinidor.getParent() != null)
                && (!(redefinidor instanceof JDialog)) && (!isRedefinidor(redefinidor))) {
            try {
                redefinidor = redefinidor.getParent();
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

        }

        if (!isRedefinidor(redefinidor)) {
            return false;
        }

        PRedefinidorTeclas r = (PRedefinidorTeclas) redefinidor;
        String metodoTeclas = r.getMetodoTeclas();

        Object padreMetodoTeclas = r.getPadreMetodoTeclas();

        Class[] params = {Integer.TYPE};
        Object[] argumentos = {new Integer(piTecla)};

        Boolean salidaFuncion = null;

        salidaFuncion = ejecuta(metodoTeclas, padreMetodoTeclas, params, argumentos);
        if (salidaFuncion == null) {
            return false;
        }
        if (salidaFuncion.booleanValue()) {
            return true;
        }

        if ((redefinidor.getParent() != null) && (!(redefinidor instanceof TDialog))) {
            return ejecutarMetodoTecla(piTecla, redefinidor.getParent());
        }
        return false;
    }

    public static boolean controlarTecla(int piTecla, Component componente) {
        System.out.println("tecla pulsada : "+piTecla);
        if (Beans.isDesignTime()) {
            return true;
        }
        
        if ((componente == null) || (Globales.getPadre(componente) == null)) {
            return true;
        }
        Object obj = Globales.getPadre(componente);
        if ((ejecutarMetodoTecla(piTecla, componente))
                || (!esHabilitadaTecla(piTecla)) || (!esHabilitadaTeclaLocal(piTecla, obj.getClass().getName()))) {
            return true;
        }
        /*
         if ((componente instanceof TColumna))
         {
         componente = ((TColumna)componente).getParent();
         }
         */
        boolean aux = false;
        boolean teclaFuncion = true;

        if (piTecla == setTecla(9, 2)) {
            KeyEvent key = new KeyEvent(
                    componente,
                    401,
                    0L,
                    2,
                    9);
            TFocusManager fm = (TFocusManager) FocusManager.getCurrentManager();
            fm.processKeyEvent(componente, key, false);
            aux = true;
        } else if (piTecla == setTecla(9, 3)) {
            KeyEvent key = new KeyEvent(
                    componente,
                    401,
                    0L,
                    3,
                    9);
            TFocusManager fm = (TFocusManager) FocusManager.getCurrentManager();
            fm.processKeyEvent(componente, key, false);
            aux = true;
        } else if (piTecla == ACEPTAR) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaAceptar();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaAceptar();
             }
             else
             {
             aux = ejecutaAceptar(componente);
             }*/
        } else if (piTecla == AUTOVALOR) {
            /*if ((componente instanceof JPTextField))
             {
             aux = ((JPTextField)componente).ejecutaAutoValor();
             }
             if ((componente instanceof TCheckBox))
             {
             aux = ((TCheckBox)componente).ejecutaAutoValor();
             }
             if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaAutoValor();
             }*/
        } else if (piTecla == AYUDA) {
            /*if ((componente instanceof JPTextField))
             {
             aux = teclaAyuda(((JPTextField)componente).getNombreCampo());
             }
             else
             {
             aux = teclaAyuda();
             }*/
        } else if (piTecla == BLOQUEANT) {
            /*if (((componente instanceof TTablaEdicion)) && (((TTablaEdicion)componente).getObjetoAnterior() != null))
             {
             aux = darFocoBloque((Container)((TTablaEdicion)componente).getObjetoAnterior(), BLOQUEANT);
             }
             else
             {
             Container target = getBloqueTarget(componente, piTecla);

             aux = darFocoBloque(target, piTecla);
             }*/

        } else if (piTecla == BLOQUESIG) {
            /*if (((componente instanceof TTablaEdicion)) && (((TTablaEdicion)componente).getObjetoPosterior() != null))
             {
             aux = darFocoBloque((Container)((TTablaEdicion)componente).getObjetoPosterior(), BLOQUESIG);
             }
             else
             {
             Container target = getBloqueTarget(componente, piTecla);
             aux = darFocoBloque(target, piTecla);
             }*/
        } else if (piTecla == BORRARREG) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaBorrarRegistro();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaBorrarRegistro();
             }
             else
             {
             teclaFuncion = false;
             }*/
        } else if (piTecla == BOTONDEFECTO) {
            if ((componente.getParent() instanceof TPanel)) {
                aux = ((TPanel) componente.getParent()).ejecutaBotonDefecto();
            }
        } else if (piTecla == CAMBIA_PERSISTENCIA) {
            /*if ((componente instanceof TTablaEdicion))
             {
             TTablaEdicion tbl = (TTablaEdicion)componente;
             if (tbl.getModoActual().equals("CONSULTA"))
             {
             if (((TTablaEdicion)componente).isMultiregistro())
             {
             tbl.setSticky(!tbl.isSticky());
             if (!tbl.isSticky()) {
             tbl.addSelectionInterval(new int[] { tbl.getNumFilaActiva() }, false);
             }
             }
             }
             aux = true;
             }*/
        } else if (piTecla == CAMPOANT) {
            /*if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).moverTabuladorLeft();
             }
             else if (!System.getProperty("java.version").startsWith("1.3")) {
             FocusManager.getCurrentManager().focusPreviousComponent(componente);
             }*/
        } else if (piTecla == CAMPOANTCURSOR) {
            /*if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).moverLeft();
             }
             else if ((componente instanceof TButton))
             {
             aux = ((TButton)componente).moverLeft();
             }
             else if ((componente instanceof TRadioButton))
             {
             FocusManager.getCurrentManager().focusPreviousComponent(componente);
             aux = true;
             }
             else
             {
             teclaFuncion = false;
             }*/
        } else if (piTecla == CAMPOSIGCURSOR) {
            /*if ((componente instanceof TButton))
             {
             aux = controlarTecla(CAMPOSIG, componente);
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).moverRight();
             }
             else if ((componente instanceof TRadioButton))
             {
             componente.transferFocus();
             aux = true;
             }
             else
             {
             teclaFuncion = false;
             }*/
        } else if (piTecla == CAMPOSIG) {
            //JOptionPane.showMessageDialog(null,"CAMPO SIGUIENTE");
            if ((componente instanceof TTextField))
             {
             aux = ((TTextField)componente).ejecutaCampoSig();
             }
            if ((componente instanceof JPTextField))
             {
             aux = ((JPTextField)componente).ejecutaCampoSig();
             }
            /*if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).moverTabuladorRight();
             }
             else if ((componente instanceof JPTextField))
             {
             aux = ((JPTextField)componente).ejecutaCampoSig();
             }
             else if ((componente instanceof TButton))
             {
             aux = ((TButton)componente).ejecutaCampoSig();
             }
             else if ((componente instanceof TCheckBox))
             {
             aux = ((TCheckBox)componente).ejecutaCampoSig();
             }*/
        } else if (piTecla == CREARREGISTRO) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaCrearRegistro();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaCrearRegistro();
             }*/
        } else if (piTecla == COPIAR) {
            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaCopiar();
            }
            /*else if ((componente instanceof TTextArea))
             {
             ((TTextArea)componente).copy();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             ((TTablaEdicion)componente).ejecutaCopiar();
             }
             else if ((componente instanceof TEdicionTexto))
             {
             ((TEdicionTexto)componente).copy();
             aux = true;
             }*/
        } else if (piTecla == CORTAR) {
            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaCortar();
            }
            /*else if ((componente instanceof TTextArea))
             {
             ((TTextArea)componente).cut();
             aux = true;
             }
             else if ((componente instanceof TEdicionTexto))
             {
             ((TEdicionTexto)componente).cut();
             aux = true;
             }*/
        } else if (piTecla == DESCARTAR) {
            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaDescartar();
            }
            /*else if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaDescartar();
             aux = true;
             }*/
        } else if (piTecla == SALIRMODREG) {
            /*if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaModoConsulta();
             }*/
        } else if (piTecla == DOBLE_CLICK) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaModificarRegistro();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaModoEdicion();
             }
             else*/ if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaDobleClick();
                teclaFuncion = false;
            }

        } else if (piTecla == EDITARCAMPO) {
            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaGranEdicion();
            }
            /*if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaGranEdicion();
             }*/
        } else if (piTecla == FILTRARREG) {
            /* if ((componente instanceof TTextArea))
             {
             aux = ejecutaFiltroTexto((TTextArea)componente, false);
             }*/
        } else if (piTecla == IMPRIMIRHC) {
            // aux = imprimePantalla();
        } else if (piTecla == IMPRIMIRHCV) {
            /*if ((componente instanceof TTablaEdicion))
             {
             TTablaEdicion t = (TTablaEdicion)componente;
             ppTable = t.getJTable();
             codigoPackage = t.getCodigoPackage();
             if (t.isCargarTodos())
             {
             aux = imprimeTabla();
             }
             else if (Herramientas.mostrarMensaje(Globales.getCodigoMensaje("trn", 10045), 2, t) == 0)
             {
             aux = imprimeTabla();
             }

             }
             else
             {
             aux = imprime(compoActivo);
             }*/
        } else if (piTecla == LISTA) {
            System.out.println("tecla lista");
            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaListaValores();
            }
            /*else if ((componente instanceof TTabla))
             {
             aux = ((TTabla)componente).ejecutaListaValores();
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaListaValores();
             }*/

        } else if (piTecla == MENUOPCIONES) {
            /*if ((componente instanceof TTabbedPane))
             {
             aux = ((TTabbedPane)componente).ejecutaMenuOpciones();
             }
             else */            if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaMenuOpciones();
            } /*else if ((componente instanceof TTextArea))
             {
             aux = ((TTextArea)componente).ejecutaMenuOpciones();
             }
             else if ((componente instanceof TCheckBox))
             {
             aux = ((TCheckBox)componente).ejecutaMenuOpciones();
             }*/ /*else if ((componente instanceof TButton))
             {
             aux = ((TButton)componente).ejecutaMenuOpciones();
             }
             else if ((componente instanceof TTabla))
             {
             aux = ((TTabla)componente).ejecutaMenuOpciones();
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaMenuOpciones();
             }*/ else if ((componente instanceof TPanel)) {
                aux = ((TPanel) componente).ejecutaMenuOpciones();
            } else if ((componente instanceof TVentana)) {
                aux = ((TVentana) componente).ejecutaMenuOpciones();
            }
        } else if (piTecla == MMENSAJES) {
            //teclaShowErrors();
            aux = true;
        } else if (piTecla == REGSIGSHIFT) {
            /*if ((componente instanceof TTablaEdicion))
             {
             ((TTablaEdicion)componente).ejecutaRegistroSiguiente();
             TTablaEdicion tabla = (TTablaEdicion)componente;
             ((TTablaEdicion)componente).anadeSeleccion(tabla.getJTable().getSelectedRow());
             aux = true;
             }*/
        } else if (piTecla == REGANTSHIFT) {
            /*if ((componente instanceof TTablaEdicion))
             {
             ((TTablaEdicion)componente).ejecutaRegistroAnterior();
             TTablaEdicion tabla = (TTablaEdicion)componente;
             ((TTablaEdicion)componente).anadeSeleccion(tabla.getJTable().getSelectedRow());
             aux = true;
             }*/
        } else if (piTecla == PAGREGANT) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaBloqueAnterior();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaPaginaAnterior();
             }*/
        } else if (piTecla == PAGREGSIG) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaBloqueSiguiente();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaPaginaSiguiente();
             }*/
        } else if (piTecla == PEGAR) {
            if ((componente instanceof JPTextField)) {
                ((JPTextField) componente).paste();
                aux = true;
            }
            /*else if ((componente instanceof TTextArea))
             {
             ((TTextArea)componente).paste();
             aux = true;
             }
             else if ((componente instanceof TEdicionTexto))
             {
             ((TEdicionTexto)componente).paste();
             aux = true;
             }*/
        } else if (piTecla == REGANT) {
            System.out.println("registro anterior");
            /*if ((componente instanceof TTabla))
             {
             aux = ((TTabla)componente).ejecutaRegistroAnterior();
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaRegistroAnterior();
             }
             else*/ if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaRegistroAnterior();
            }
        } else if (piTecla == REGPRIMERO) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaPrimerRegistro();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaPrimerRegistro();
             }*/
        } else if (piTecla == REGSIG) {
            /* if ((componente instanceof TTabla))
             {
             aux = ((TTabla)componente).ejecutaRegistroSiguiente();
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaRegistroSiguiente();
             }
             else*/ if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaRegistroSiguiente();
            }
             if ((componente instanceof JPTextField)) {
                aux = ((JPTextField) componente).ejecutaRegistroSiguiente();
            }

        } else if (piTecla == REGULTIMO) {
            /*if ((componente instanceof TTabla))
             {
             ((TTabla)componente).ejecutaUltimoRegistro();
             aux = true;
             }
             else if ((componente instanceof TTablaEdicion))
             {
             aux = ((TTablaEdicion)componente).ejecutaUltimoRegistro();
             }*/
        } else if (piTecla == RESTAURAR) {
            aux = true;
        } else if (piTecla == SALIR) {
            /*if ((Globales.getPadre(componente) instanceof TMessageDialog))
             {
             if (((TMessageDialog)Globales.getPadre(componente)).isSalidaNormal()) {
             teclaSalir(componente);
             }
             }
             else {
             teclaSalir(componente);
             }

             aux = true;*/
        } else if (piTecla == RECONECTAR) {
            /*reconectar();
             aux = true;*/
        } else if (piTecla == IR_A) {
            /*if ((componente instanceof TTextArea))
             {
             ((TTextArea)componente).ejecutaIrA();
             aux = true;
             }*/
        } else if (piTecla == TECLAMAS) {
            /* if ((componente instanceof TTree))
             {
             ((TTree)componente).expandeRama();
             aux = true;
             }*/
        } else if (piTecla == TECLAMENOS) {
            /*if ((componente instanceof TTree))
             {
             ((TTree)componente).cierraRama();
             aux = true;
             }*/
        } else if (piTecla == CONTROLP) {
            /*compoActivo = Globales.getPadre(Globales.getComponenteActual());
             aux = ejecutaMenuImpresion();*/
        } else if (piTecla == CONTROLM) {
            /*compoActivo = Globales.getPadre(Globales.getComponenteActual());
             aux = ejecutaMenuMensajesIns();*/
        } else if (piTecla == ENVIAR) {
            /*ejecutaProgramaCorreo();
             aux = true;*/
        } else if (piTecla == JSPOOLP) {
            /* aux = ejecutaJSPOOLP();*/
        }

        if (teclaFuncion);
        return aux;
    }

    private static boolean isRedefinidor(Object o) {
        PRedefinidorTeclas r = null;
        try {
            r = (PRedefinidorTeclas) o;
            if ((r.getMetodoTeclas() == null)
                    || (r.getMetodoTeclas().equals(""))
                    || (r.getMetodoTeclas().equals("null"))) {
                return false;
            }
            return true;
        } catch (Exception localException) {
        }
        return false;
    }

  public static int getTecla(int pCodigoCon)
  {
    return pCodigoCon / 8;
  }

 public static int getModificador(int pCodigoCon)
  {
    return pCodigoCon % 8;
  }
}
