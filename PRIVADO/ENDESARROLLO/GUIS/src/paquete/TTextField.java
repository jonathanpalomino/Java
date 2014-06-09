package paquete;
/*
 Formateo de fecha antes y después de un campo.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.datatransfer.Clipboard;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import paquete.Contenedores.TBarraHerramientas;
import paquete.Contenedores.TDialog;
import paquete.Contenedores.TPopupMenu;
import paquete.Contenedores.TVentana;
import paquete.utiles.ControlTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import paquete.utiles.Trazar;
import paquete.utiles.ValidacionBloques;

//DOCUMENTACION TECNICA
//Las validaciones del TTextField se llevan a cabo mediante un
//ejeplar de la clase TInputVerifier. Para mas información ver
//documentación técnica de esta clase.
public class TTextField extends javax.swing.JTextField implements PRedefinidorTeclas, TInputComponent {

    private Component padreValidacion;
    private int maxOffset = 0;
    private boolean bHayLongitud = false;
    private boolean bHayTipoDato = false;
    private int indicePropAutovalor = 0;
    /* Variable que nos comunica el estadode ejecucion del meun de opciones */
    private int resulMenu = 1;
    /* Variable que nos dice si se ha informado del programa en el menu de opciones */
    private boolean conProgramaMenu;
//    HashTable erroresSinPadre = new Hashtable();
    /**
     * Controla si se debe ejecutar la función de oracle
     */
    boolean bEjecutarFuncion = true;
    /**
     * Controla si es el primer acceso que se realiza al componente. En caso de
     * ser true al entrar hay que poner el autovalor, si lo tuviera.
     */
    private boolean primerAcceso = true;
    /**
     * Controlar el estado de validación de validación. Cuando el campo está
     * marcado como "no validado" la información se muestra en cursiva.
     */
    private boolean isValidate = false;
    /**
     * Guarda el estado de validación anterior.
     */
    private boolean validatePrevio = false;
    /**
     * Guarda la propiedad de Gran Edición. Un valor true indica que al pulsar
     * GRANEDICION se abre un cuadro de diálogo donde con un text area en donde
     * se pueden escribir textos largos. Este cuadro de diálogo es un ejemplar
     * de la clase DialogoEditar. Por defecto false.
     */
    private boolean bGranEdicion = false;
    /**
     * Guarda la propiedad de si en Gran Edicion acepta la tecla <Enter>.Cambio
     * de linea El valor true indica que en gran Edicion al pulsar <Enter> se
     * producira un cambio de linea. A false la tecla <Enter> no tendra nigún
     * efecto.
     */
    private boolean bEnterGranEdicion = false;
    /**
     * Guarda si actualmente el campo se halla en medio de una validación.
     */
    private boolean estaValidando = false;
    /**
     * Controla que no se lance dos veces simultaneamente la lista de valores
     */
    private boolean bEjecutandoLista = false;
    /**
     * Un valor true indica que actualmente se está realizando una validación de
     * bloque.
     */
    private static boolean validacionTemporal = false;
    /**
     * Borde hundido del text field. Es el borde que se muestra cuando el campo
     * no tiene el foco.
     */
    // Border esteBorde=this.getBorder();
    /**
     * Nombre del Campo como Etiqueta
     */
    private String sNombreReal = "";
    /**
     * Identificador del menú de opciones
     */
    private String menuOpciones = "";
    /**
     * Nombre del programa asociado al menú de opciones. Normalmente será el
     * nombre de pantalla en donde está incluido el text field
     */
    private String programaMenu = "";
    /**
     * Número de menú de opciones.
     */
    private String numMenuOpcion = "";
    /**
     * Valor por defecto del menú de opciones.
     */
    private String defectoMenu = "";
    /**
     * Indica si hay un menú de opciones asociado al campo
     */
    private boolean hayMenu = false;
    /**
     * Indica si hay un mensaje de error asociado al campo. Este valor se pone a
     * true cuando una validación falla y cuando se intenta introducir en el
     * campo texto incorrecto. Por ejemplo al intentar introducir letras en un
     * campo numérico.
     */
    private boolean hayMensajeError = false;
    /**
     * Indica que hay que refrescar el mensaje de la barra de estado con el tool
     * tip del campo
     */
    private boolean reponerMensaje = false;
    /**
     * Componente modelo para la anchura. Un valor distinto de null indica que
     * el campo debe medir en anchura lo mismo que el componente modelo
     */
    private Component componenteModelo = null;
    /**
     * Borde Típico del TTextField
     */
    public static final javax.swing.border.Border bordeNormal = (new javax.swing.JTextField()).getBorder();
    /**
     * Indica que hay una lista de valores / opciones asociada al campo. Un
     * valor de true indica que hay que dibujar un triángulo negro en la esquina
     * inferior derecha del componente.
     */
    boolean hayLista = false;
    /**
     * Valor anterior del componente. Si el valor actual es igual al anterior
     * entonces la validación actual es igual a la anterior.
     */
    private String valorPrevio = null;
    /**
     * Valor inicial del campo. Se usa cuando el usuario invoca DESCARTAR sobre
     * el campo.
     */
    private String valorInicial = null;
    /**
     * Rango permitido de números para el campo. Es una cadena de caracteres que
     * tiene dos números separados por una coma. Se garantiza que el campo no
     * puede tener un valor inferior al primer número ni superior al segundo.
     * Este atributo solo es usado cuando el tipo de dato del campo es numérico.
     */
    private String rangoNumeros = null;
    /**
     * Cota superior que admite el campo según se ha definido en rangoNumeros.
     */
    private double numMaximo = java.lang.Double.MAX_VALUE;
    /**
     * Cota inferior que admite el campo según se ha definido en rangoNumeros.
     */
    private double numMinimo = 0;
    /**
     * Nombre del campo asociado a la lista de opciones.
     */
    private String sCampoListaOpciones = "";
    /**
     * Nombre del ramo asociado a la lista de opciones.
     */
    private String sRamoListaOpciones = "";
    /**
     * Componente asociado a la lista de opciones. Cuando el usuario selecciona
     * un valor de la lista de opciones este campo se rellenará automáticamente
     * según la opción seleccionada.
     */
    private TTextField ttextFieldAsociadoListaOpciones = null;
    /**
     * Un valor de true indica que antes de salir del campo se valida que el
     * valor introducido en el mismo coincide con algun valor de la lista de
     * opciones asociada.
     */
    private boolean bValidarContraListaOpciones = false;
    /**
     * Indica que este campo es el código de una lista de opciones.
     */
    boolean soyCodigoLista = false;
    /**
     * Indica que este campo es la descripción de una opción de la lista de
     * opciones.
     */
    boolean soyDescripcionLista = false;
    /**
     * Objeto de lista de opciones que controla la apertura de la ventana de
     * lista de opciones.
     */
    ListaOpciones listaOpc = null;
    /**
     * Valor que devuelve getPadre() cuando no consigue encontrar ningún padre.
     * Este valor se devuelve para evitar errores de NullPointerException.
     */
    private static TVentana ventanaDefecto = (TVentana) (new TDialog());
    /**
     * Consante que indica que la anchura del campo será la estandar.
     */
    public static final String ANCHURA_ESTANDAR = "ESTANDAR";
    /**
     * Constante que indica que la anchura del campo será igual que la de las
     * columnas de la TTablaEdicion
     */
    public static final String ANCHURA_NUMEROS = "NUMEROS";
    public static final String ANCHURA_TABLA = "TABLA";
    /**
     * Constante que indica que la anchura del campo será igual a la de otro
     * componente.
     */
    public static final String ANCHURA_MODELO = "MODELO";
    /**
     * Guarda el tipo de anchura que puede ser: <ul> <li>ANCHURA_ESTANDAR
     * <li>ANCHURA_TABLA <li>ANCHURA_NUMEROS <li>ANCHURA_MODELO </ul>
     */
    protected String tipoAnchura = ANCHURA_TABLA;
    /**
     * Constante que representa al metodo previo al campo <br>Uso interno
     */
    public static final int METODO_PRECAMPO = 0;
    /**
     * Constante que representa al metodo previo a la validacion oracle <br>Uso
     * interno
     */
    public static final int METODO_PREVALIDACION = 1;
    /**
     * Constante que representa al metodo posterior al campo <br>Uso interno
     */
    public static final int METODO_POSTCAMPO = 2;
    /**
     * Constante que representa al metodo de teclas de funcion <br>Uso interno
     */
    public static final int METODO_TECLAS = 3;
    /**
     * Constante que representa al metodo previo a la lista <br>Uso interno
     */
    public static final int METODO_PRELISTA = 4;
    /**
     * Constante que representa al metodo posterior a la lista <br>Uso interno
     */
    public static final int METODO_POSTLISTA = 5;
    /**
     * Constante que representa al metodo posterior a la validacion <br>Uso
     * interno
     */
    public static final int METODO_POSTVALIDACION = 6;
    /**
     * Constante que representa al metodo posterior al menu de opciones <br>Uso
     * interno
     */
    public static final int METODO_POSTMENU = 7;
    /**
     * Constante que representa al metodo previo al menu de opciones <br>Uso
     * interno
     */
    public static final int METODO_PREMENU = 8;
    /**
     * Constante que representa al metodo "on error" que se lanza al haber un
     * error de validacion <br>Uso interno
     */
    public static final int METODO_ONERROR = 9;
    private boolean bEraOpaco = true;
    /**
     * Menú desplegable que se invoca cuando el usuario pulsa el botón derecho
     * del ratón sobre el campo.
     */
    protected static TPopupMenu pop = null;
    /**
     * Ejemplar de la clase ValidacionBloques que se usa para invalidar el
     * bloque donde se halla el campo cuando el usuario introduce cambios en el
     * mismo.
     */
    ValidacionBloques validacionBloques = new ValidacionBloques();
    /**
     * Guarda la última tecla pulsada en el campo
     */
    private int ultimaTecla = 0;
    /**
     * Auditor de eventos de foco. Para mas información consultar la
     * documentación de TFocusListener.
     */
    TFocusListener aSymFocus = null;
    /**
     * Construye un ejemplar por defecto de TTextField vacio y con cero columas.
     */
    Font miFuente = new Font("Dialog", Font.PLAIN, 12);
    /**
     * Controlo si se esta validando el TTextField
     */
    private boolean validando = false;

    public TTextField() {
        //Establecemos la fuente
        this.setFont(miFuente);
        //this(""         , 0              ); 

        //{*{INIT_CONTROLS
        //Tamaño por defecto = 0
        setSize(0, 0);

        //$$ bordeActual.move(0,0);
        //$$ bordeObligado.move(0,0);
        //$$ bordeError.move(0,0);
        //}*}
        if (!java.beans.Beans.isDesignTime()) {

            //Auditories de eventos

            aSymFocus = TFocusListener.getFocusListener(); //Auditor de eventos de foco
            this.addFocusListener(aSymFocus);
            SymMouse aSymMouse = new SymMouse(); //Auditor de eventos de ratón
            this.addMouseListener(aSymMouse);
            SymKey aSymKey = new SymKey(); //Auditor de eventos de teclado
            this.addKeyListener(aSymKey);
            SymMouseMotion aSymMouseMotion = new SymMouseMotion(); //Aud. de eventos de movimiento de ratón
            this.addMouseMotionListener(aSymMouseMotion);
            SymCaret lSymCaret = new SymCaret(); //Auditor de eventos de cursor
            this.addCaretListener(lSymCaret);
        }
        super.setAutoscrolls(false);
        //Inicializar de bordes
        /*
         ((TEtchedBorder)bordeActual).setEtchType(javax.swing.border.EtchedBorder.RAISED);
         ((TEtchedBorder)bordeActual).setShadowColor(java.awt.Color.darkGray);
         ((TEtchedBorder)bordeActual).setHighlightColor(java.awt.Color.blue);
         ((TEtchedBorder)bordeObligado).setEtchType(javax.swing.border.EtchedBorder.RAISED);
         ((TEtchedBorder)bordeObligado).setShadowColor(java.awt.Color.darkGray);
         ((TEtchedBorder)bordeObligado).setHighlightColor(java.awt.Color.yellow);
         ((TEtchedBorder)bordeError).setShadowColor(java.awt.Color.darkGray);
         ((TEtchedBorder)bordeError).setHighlightColor(java.awt.Color.red);
         */
        this.setDisabledTextColor(Color.darkGray);


        if (!java.beans.Beans.isDesignTime()) {

            //{*{REGISTER_LISTENERS
            SymAction lSymAction = new SymAction(); //Auditor de eventos de acción
            this.addActionListener(lSymAction);
            //}*}	
        }
    }

    /**
     * Establece la propiedad <i>Validar con lista de opciones</i>. Un valor de
     * true indica que antes de salir del campo el sistema verificará que la
     * información introducida se adecúa a alguna opción de la lista de
     * opciones.
     */
    public void setValidarConOpciones(boolean pbValidarConOpciones) {
        bValidarContraListaOpciones = pbValidarConOpciones;
    }

    /**
     * Recupera el valor de la propiedad <i>Validar con lista de opciones</i>
     * establecida previamente mediante setValidarConOpciones(boolean)
     */
    public boolean getValidarConOpciones() {
        return bValidarContraListaOpciones;
    }

    /**
     * Recupera el campo asociado a la lista de opciones actual.
     */
    public Component getCampoAsociado() {
        return ttextFieldAsociadoListaOpciones;
    }

    /**
     * Recupera el nombre de campo y ramo de la lista de opciones
     */
    public String getListaOpciones() {
        if (sCampoListaOpciones != null && !sCampoListaOpciones.trim().equals("")) {
            return sCampoListaOpciones + "," + sRamoListaOpciones;
        } else {
            return "";
        }
    }

    /**
     * Recupera la propiedad <i>Tipo de Anchura</i> establecida mediante
     * setTipoAnchura(java.lang.String)
     */
    public String getTipoAnchura() {
        return tipoAnchura;
    }

    /**
     * Establece el tipo de anchura que tendrá el componente. Esta puede ser
     * <ul> <li>ESTANDAR - anchura normal del componente <li>TABLA - mide igual
     * que las columnas de una TTablaEdicion <li>MODELO - mide igual que otro
     * componente que se le pasa como parámetro mediate
     * setComponenteModelo(java.awt.Component)
     */
    public void setTipoAnchura(String s) {
        tipoAnchura = s;
    }

    /**
     * Recupera el <i>Componente modelo para anchura</i>
     */
    public Component getComponenteModelo() {
        return componenteModelo;
    }

    /**
     * Estable el <i>Componente modelo para anchura</i>. Esta propiedad solo
     * tiene sentido si el tipo de anchura es MODELO.
     */
    public void setComponenteModelo(Component c) {
        componenteModelo = c;
    }

    /**
     * Cambia el valor de rangoNumeros, numMaximo y numMinimo. <br> Establece
     * los valores mínimo y/o máximo de los valores númericos introducidos.
     * <br>Solo tiene efecto si el TTextField es de tipo numérico.
     *
     * @param pRangoNumeros valor mínimo y máximo permitidos para un dato
     * numérico con el formato "mínimo,máximo".
     * @see #getRango()
     * @see #setTipoDato(String)
     * @see #setNumMinimo(double)
     * @see #setNumMaximo(double)
     */
    public void setRango(String pRangoNumeros) {
        this.rangoNumeros = pRangoNumeros;
        StringTokenizer tok = new StringTokenizer(rangoNumeros, ",");
        try {
            this.setNumMinimo(new Integer(tok.nextToken()).intValue());
            this.setNumMaximo(new Integer(tok.nextToken()).intValue());
        } catch (java.util.NoSuchElementException f) {
        } catch (java.lang.NumberFormatException e) {
        }
    }

    /**
     * @deprecated Ver setRango(String)
     * @see #setRango(String)
     */
    public void setRangoNumeros(String pRangoNumeros) {
        setRango(pRangoNumeros);
    }

    /**
     *
     * Este método se utiliza para poder manejar los eventos de teclado
     * asociados a la tecla TAB. <br> Uso interno.
     *
     */
    public boolean isManagingFocus() {
        return true;
    }

    /**
     * Devuelve el valor del rango numerico.
     *
     * @deprecated Ver getRango()
     * @return rango String de la forma: "numMinimo,numMaximo"
     * @see #getRango()
     */
    public String getRangoNumeros() {
        return getRango();
    }

    /**
     * Devuelve el valor del rango numerico.
     *
     * @return rango String de la forma: "numMinimo,numMaximo"
     * @see #setRango(String)
     */
    public String getRango() {
        return this.rangoNumeros;
    }

    /**
     * Establece el valor máximo del valor numérico introducido. <br> Solo
     * válido si el tipo es numérico.
     *
     * @param pNumMaximo valor máximo permitido para un dato numérico
     * @see #setTipoDato(String)
     * @see #setRango(String)
     * @see #setNumMinimo(double)
     */
    public void setNumMaximo(double pNumMaximo) {
        this.numMaximo = pNumMaximo;
    }

    /**
     * Método accedente. Devuelve el valor máximo permitido para un dato
     * numérico
     *
     * @return el valor máximo permitido para un dato numérico establecido
     * previamente con setNumMaximo()
     * @see #setNumMaximo(double)
     */
    public double getNumMaximo() {
        return this.numMaximo;
    }

    /**
     * Establece el valor mínimo del valor numérico introducido. <br>Si el
     * número no tiene signo fija el valor mínimo a 0. Solo válido si el tipo es
     * numérico.
     *
     * @param pNumMinimo valor mínimo permitido para un dato numérico
     * @see #setTipoDato(String)
     * @see #setRango(String)
     * @see #setNumMaximo(double)
     */
    public void setNumMinimo(double pNumMinimo) {
        if (this.isSigno() || pNumMinimo >= 0) {
            this.numMinimo = pNumMinimo;
        } else {
            this.numMinimo = 0;
        }

        //msg("El numero minimo es: "+numMinimo);
    }

    /**
     * Devuelve el valor mínimo permitido para un dato numérico.
     *
     * @return el valor mínimo permitido para un dato numérico establecido
     * previamente con setNumMinimo()
     * @see #setNumMinimo(double)
     */
    public double getNumMinimo() {
        return this.numMinimo;
    }

    /**
     * Método que habilita/inhabilita la ejecucion de el siguiente método o
     * función de validación. <p>Afecta a la ejecucion de: Funcion Oracle de
     * inicio, metodos previo o posterior al campo, a la validación, al menú de
     * opciones y a la Lista,de la siguiente manera : <p>- Si se situa en
     * <i>Método Previo a Validación</i> : afecta a <i>Función Validación
     * Oracle</i> y por tanto también a el <i>Método Posterior a Validación</i>
     * . <br>- Si se situa en <i>Método Previo al Campo</i> : afecta a <i>Método
     * Posterior al Campo</i>. <br>- Si se situa en <i>Método Previo al Menú de
     * Opciones</i> : afecta a <i>Método Posterior al Menú de Opciones</i>.
     * <br>- Si se situa en <i>Método Previo a la Lista</i> : afecta a <i>Método
     * Posterior a la Lista</i>.
     *
     * @param pbValor false si se quiere impedir la ejecución del siguiente
     * método o funcion
     * @see #setMetodoPreValidacion(java.awt.Component,java.lang.String)
     * @see #setMetodoPreCampo(java.awt.Component,java.lang.String)
     * @see #setMetodoPreLista(java.awt.Component,java.lang.String)
     */
    public void setEjecutarFuncion(boolean pbValor) {
        this.bEjecutarFuncion = pbValor;
    }

    /**
     * En caso de que haya paneles solapados esta propiedad permite establecer
     * el grupo de tarjetas que los manejo. <br> Uso interno.
     *
     * @param pGrupoTarjetas Grupo de tarjetas
     * @see #getGrupoTarjetas() public void setGrupoTarjetas(GrupoTarjetas
     * pGrupoTarjetas) { this.oGrupoTarjetas = pGrupoTarjetas; }
     */
    /**
     * Devuelve el grupo de tarjetas. <br> Uso interno.
     *
     * @return El grupo de tarjetas establecido previamente con
     * setGrupoTarjetas()
     * @see #setGrupoTarjetas(mapfre.com.c.GUI.GrupoTarjetas) public
     * GrupoTarjetas getGrupoTarjetas() { return this.oGrupoTarjetas; }
     */
    /**
     * Devuelve el tamaño preferido para el campo.
     *
     * @return Tamaño preferido para el campo
     */
    public Dimension getPreferredSize() {
        java.awt.Dimension miDim = super.getPreferredSize();
        if (!System.getProperty("java.version").startsWith("1.3")) {
            miDim.width -= 6;
        }
        if (bGranEdicion) {
            miDim.height = 21;
        }

        return miDim;
    }
    /**
     * Controla el estado de activación del focus-listener. Un valor de false
     * indica que el componente no reacciona ante eventos de cambio de foco-
     */
    boolean activadoFocusListener = true;

    /**
     * Activa / desactiva las validaciones del componente. Sólo utilizado
     * internamente por los componentes.
     */
    public void activarFocusListener(boolean b) {
        //Si no hay cambios no hacer nada
        if (!java.beans.Beans.isDesignTime()) {

            if (b != activadoFocusListener) {
                activadoFocusListener = b;
                if (b) {

                    this.addFocusListener(aSymFocus);
                } else {
                    this.removeFocusListener(aSymFocus);
                }
            }
        }
    }

    /**
     * Devuelve el tamaño máximo establecido para el objeto.
     *
     * @return Tamaño máximo del TTextfield.
     */
    public Dimension getMaximumSize() {
        java.awt.Dimension miDim = super.getPreferredSize();
        if (bGranEdicion) {
            miDim.height = 21;
        }

        return miDim;
    }

    /**
     * Permite que el componente valide su contenido cuando el valor sea vacío.
     * <br>Por defecto, la propiedad <i>Valida Si Nulo</i> está a true.
     * <p>Cuando está a false un contenido nulo del campo no lanza la validación
     * al abandonar el mismo.
     *
     * @param pbValidaNulo true si se desea validar, false en otro caso
     * @see #setFuncionValidacion(String)
     */
    public void setValidaNulo(boolean pbValidaNulo) {
        this._validaNulo = pbValidaNulo;
    }

    /**
     * Devuelve true si se permite gran edicion (con F6).
     *
     * @return true si se permite gran edicion
     * @see #setGranEdicion(boolean)
     */
    public boolean isGranEdicion() {
        return this.bGranEdicion;
    }

    /**
     * Establece si se permite gran edicion. Si el valor es true entonces al
     * pulsar F6 sobre el campo se abre un cuadro de dialogo con posiblidades
     * avanzadas de edicion de texto (copiar, cortar, pegar, ...)
     *
     * @param pGranEdicion true si se permite la gran edicion
     * @see #isGranEdicion()
     */
    public void setGranEdicion(boolean pGranEdicion) {
        this.bGranEdicion = pGranEdicion;
    }

    /**
     * Devuelve true si se permite <Enter> en gran edicion (con F6).
     *
     * @return true si se permite <Enter> en gran edicion
     * @see #setEnterGranEdicion(boolean)
     */
    public boolean isEnterGranEdicion() {
        return this.bEnterGranEdicion;
    }

    /**
     * Establece si se permite la tecla <Enter> en gran edicion. Si el valor es
     * true entonces al pulsar <Enter> sobre el campo cambiamos de linea
     *
     * @param pEnterGranEdicion true si se permite <Enter> en la gran edicion
     * @see #isEnterGranEdicion()
     */
    public void setEnterGranEdicion(boolean pEnterGranEdicion) {
        this.bEnterGranEdicion = pEnterGranEdicion;
    }

    /**
     * Devuelve true si se validan los valores nulos para este campo.
     *
     * @return true si se valida los valores nulos
     * @see #setValidaNulo(boolean)
     */
    public boolean isValidaNulo() {
        return this._validaNulo;
    }

    /**
     * Devuelve true si el contenido del campo es vacio.
     *
     * @return true si el contenido del campo es vacio
     */
    public boolean isNull() {
        if (super.getText() == null || super.getText().trim().equals("")) {
            return true;
        }
        return false;
    }

    /**
     * Construye un ejemplar de TTextField con el número de columnas
     * especificados.
     *
     * @param numberOfColumns Tamaño del campo
     */
    public TTextField(int numberOfColumns) {
        this("", numberOfColumns);
    }

    /**
     * Construye un ejemplar de TTextField con un texto inicial y 256 columnas.
     *
     * @param initialText Texto inicial
     */
    public TTextField(String initialText) {
        this(initialText, 256);
    }

    /**
     * En datos numéricos, permite introducir el signo '-' (negativo).
     *
     * @param pbSigno true si queremos permitir el signo negativo, falso de otro
     * modo
     * @see #setTipoDato(String)
     * @see #isSigno()
     */
    public void setSigno(boolean pbSigno) {
        this._signo = pbSigno;
        if (pbSigno) {
            this.setNumMinimo(-java.lang.Double.MAX_VALUE);
        } else {
            this.setNumMinimo(0);
        }
    }

    /**
     * Devuelve true si se permite poner signo negativo al campo numerico.
     *
     * @return true si se permite poner signo negativo
     * @see #setSigno(boolean)
     */
    public boolean isSigno() {
        return this._signo;
    }

    /**
     * Esta opción, que es la de defecto, mostrará el campo con la puntuación
     * (según el idioma del usuario) de decimales y millares. Si el campo es un
     * código y no un número, dejar a false. <br> La puntuación no debe ser
     * introducida por el usuario, sino que se pone automáticamente al salir del
     * campo. <br> Ejemplo:
     *
     * <pre>
     * 1.123.220,50 (Español)
     * 1,123,220.50 (Inglés)
     * </pre>
     *
     * @param pbPuntuacion true si se desea utilizar puntuacion de millares,
     * false de otro modo
     * @see #isPuntuacion()
     */
    public void setPuntuacion(boolean pbPuntuacion) {
        this._puntuacion = pbPuntuacion;
        if (bHayLongitud) {
            setLongitud(datosLongitud);
        }
    }

    /**
     * Consulta si se ha establecido la puntuacion de millares para un campo
     * numerico.
     *
     * @return true si se ha establecido la puntuacion de millares
     * @see #setPuntuacion(boolean)
     */
    public boolean isPuntuacion() {
        return this._puntuacion;
    }

    /**
     * Devuelve si se accede por primera vez al TTextField.
     *
     * @return true si es la primera vez que el TTextField gana el foco y false
     * en caso contrario
     * @see #setPrimerAcceso(boolean)
     */
    public boolean isPrimerAcceso() {
        return this.primerAcceso;
    }

    /**
     * Establece el valor del atributo primerAcceso.
     *
     * @param pPrimerAcceso boolean que indica si el la primera vez que se
     * accede al campo o no
     * @see #isPrimerAcceso()
     */
    public void setPrimerAcceso(boolean pPrimerAcceso) {
        this.primerAcceso = pPrimerAcceso;
    }

    /**
     * Construye un ejemplar de TTextField con un texto inicial y un numero de
     * columnas dado.
     *
     * @param initialText Texto inicial
     * @param numberOfColumns Tamaño del campo
     */
    public TTextField(String initialText, int numberOfColumns) {
        this(null, initialText, numberOfColumns);
    }
    /**
     * Pinta el componente por pantalla
     *
     * @see #paint(java.awt.Graphics)
     *
     * public void paint() { paint(this.getGraphics());
    }
     */
    public static final Color GRIS_CLARO = new Color(220, 220, 220);
    public static final Color GRIS_ROTO = new Color(192, 192, 192);
    public static final Color COLOR_ERROR = new Color(255, 192, 192);
    public static final Color COLOR_OBLIGA = new Color(255, 255, 192);
    public static final Color COLOR_NORMAL = new Color(192, 255, 255);

    /**
     * Pinta el componente por pantalla. En caso de que tenga lista de opciones
     * o de valores dibuja un triángulo negro en la esquina inferior derecha.
     * <br>Uso interno.
     *
     * @param g Entorno grafico
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (!hasFocus()) {
            if (this.isEnabled()) {
                if (this.getBackground() != Color.white) {
                    this.setBackground(Color.white);
                }
            } else if (!this.isEnabled()) {
                if (this.getBackground() != GRIS_CLARO) {
                    bEraOpaco = isOpaque();
                    setOpaque(true);
                    this.setBackground(GRIS_CLARO);
                }
            }
        }

        // Juan.28112001
        // Tratamiento del contenido del campo y movimiento de scroll
        // para pintar el campo roto.

        Dimension tm = this.getPreferredSize();
        double ancho = (double) tm.width;
        double txt = ancho / (double) this.ANCHO_FUENTE;
        Math visibleReal = null;

        double longitud;

        if (tipoDato.equals("TEXTO")) {
            longitud = visibleReal.ceil(txt) + 1;
        } else {
            longitud = this.getLongitudVisible();
        }

        /*
         Herramientas.println("Longitud "+this.getColumnWidth());
         Herramientas.println("ANCHO_FUENTE "+Herramientas.dameLongitudMediaFuente(this));
         Herramientas.println("Size "+ancho);
         */
        if (this.getText().length() > longitud) //this.getLongitudVisible())
        {
            if (this.hasFocus()) {
                if (maxOffset > 0 && (!this.getText().equals(""))) {
                    if (this.getCaretPosition() < this.getLongitudReal()) {
                        mostrarCampoRotoR(g);
                    }
                }
                if (this.getScrollOffset() > 1) {
                    if (this.getText().length() < this.getLongitudReal()) {
                        maxOffset = getScrollOffset();
                        mostrarCampoRotoR(g);
                    }
                    mostrarCampoRotoL(g);
                }
            } else {
                mostrarCampoRotoR(g);
            }

        } else {
            maxOffset = 0;
        }
        // Juan 28112001

        if ((hayLista || soyDescripcionLista || soyCodigoLista) && isEnabled()) {
            mostrarIconoLista(g);
        }

    }

    /**
     * Construye un ejemplar de TTextField a partir de un documento
     *
     * @param doc Documento de partida
     * @param initialText Texto inicial
     * @param numberOfColumns Tamaño del campo
     * @see javax.swing.text.Document
     */
    public TTextField(Document doc, String initialText, int numberOfColumns) {
        super(doc, initialText, numberOfColumns);
    }

    /**
     * Devuelve el texto introducido en el campo en forma de String. Notese en
     * el caso de un núemro el texto devuelto elimina las posibles mascaras. Por
     * ejemplo si el contenido del campo numerico es 1.000.000 (un millón) este
     * metodo devolveria "1000000"
     *
     * @return Contenido del TextField una vez se ha eliminado la mascara
     * @see #setText(String)
     */
    public String getText() {
        String valorDevuelto = super.getText();
        /*if (tipoDato.equals("FECHA"))
         {	        
         return Herramientas.quitaMascaraFecha(super.getText());
         }*/

        return valorDevuelto;
    }

    public String toString() {
        return getText();
    }

    /**
     * Establece el contenido del campo. Cuando se utiliza este metodo el campo
     * queda invalidado (en cursiva) mientras que el usuario no pase por encima
     * y lo valide. <br> Si el campo es numérico lo formatea según lo indicado
     * en <i>Numero Decimales</i> y <i>Mostrar puntuacion miles</i> .
     *
     * @param t Texto de entrada al campo
     * @see #getText()
     * @see #setFuncionValidacion(String)
     * @see #setNumDecimal(int)
     * @see #setDecimalesDef(boolean)
     */
    public synchronized void setText(String t) {
        if (t != null && !(t.equals(super.getText()))) {
            this.setValidate(false);
        }
        //Control de tipo de dato NUMERICO: pone máscara de número
        //al valor introducido
        if (tipoDato.equals("NUMERICO")) {
            //System.err.println("setText()");
            String numeroFormate = Herramientas.ponFormatoImporte(
                    t,
                    getDecimalesDef() ? Globales.getNumDec() : getNumDecimal(),
                    isPuntuacion());
            if (!isSigno() && numeroFormate.length() > 0 && numeroFormate.charAt(0) == '-') {
                numeroFormate = numeroFormate.substring(1);
            }
            if (numeroFormate != null) {
                super.setText(numeroFormate);
            }
        } //Control de tipo de dato FECHA: pone máscara de fecha al valor
        //introducido
        else if (tipoDato.equals("FECHA")) {
            try {

                //06102003.Juan. Para que quite las mascara cuando el TTextField tiene el foco, pues 
                //  
                if (this.hasFocus()) {
                    String fechaSin = Herramientas.quitaMascaraFecha(t);
                    if (fechaSin != null) {
                        super.setText(fechaSin);
                    }
                } else {
                    String textoFecha = Herramientas.ponMascaraFecha(this.getMascaraFecha(), t);
                    super.setText(textoFecha);
                }
            } catch (Exception e) {
                super.setText(t);
            }
        } else {
            super.setText(t);
        }
        this.select(0, 0);

    }

    /**
     * Habilita / deshabilita este campo
     *
     * @param pbHabilitado true para habilitar, false para deshabilitarlo
     * @see #isEnabled()
     */
    public void setEnabled(boolean pbHabilitado) {
        /*if (pbHabilitado==this.isEnabled())
         {
         return;
         }*/

        //msg("TTextField - setEnabled("+pbHabilitado+")");
        boolean habilitadoAntes = super.isEnabled();
        super.setEnabled(pbHabilitado);
        if (pbHabilitado == false) {
            setOpaque(bEraOpaco);
            this.setBackground(Color.white);
            this.setValidate(true);

        } else if (!habilitadoAntes) // jbachil 01-02-2001 Para que no invalide el campo 
        {                          // al habilitar un campo que ya estaba habilitado 
            this.setValidate(false);
        }
        repaint();

    }

    /**
     * Metodo generado internamente por VisualCafe. No modificar
     */
    public synchronized void removeNotify() {
        if (_focusListener != null) {
            removeFocusListener(_focusListener);
            _focusListener = null;
        }
        super.removeNotify();
    }

    /**
     * Permite cortar el texto seleccionado del campo actual y colocarlo en el
     * clipboard <br> Uso interno.
     *
     * @param clipboard Entorno donde queda guardado el texto cortado a la
     * espera de ser "pegado" en algun otro sitio
     * @return true si es posible cortar, false en otro caso
     */
    public synchronized void cut(Clipboard clipboard) {
        //msg ("Cortando");
        //boolean ejecucionCorrecta = false;
        if (puedeCortar()) {
            _activity = true;
            //ejecucionCorrecta = true;            
        }
        //return ejecucionCorrecta;
    }

    /**
     * Corta el texto seleccionado.
     *
     * @return true si permite cortar
     * @see #ejecutaCopiar()
     * @see #ejecutaPegar()
     */
    public boolean ejecutaCortar() {
        super.cut();
        return puedeCortar();
    }

    /**
     * Corta el texto seleccionado.
     *
     * @return true si permite cortar
     * @see #ejecutaCortar()
     * @see #ejecutaPegar()
     */
    public boolean ejecutaCopiar() {
        copy();
        return puedeCopiar();
    }

    /**
     * Permite pegar el texto seleccionado. Redefinido para tener en cuenta
     * ControlTeclas. Uso interno.
     */
    public void paste() {
    }

    /**
     * Establece el documento del que depende el textfield
     *
     * @param d Documento del que depende
     */
    public void setDocument(Document d) {

        if (_docListener == null) {
            _docListener = new DocumentListener() {
                public void changedUpdate(DocumentEvent e) {
                    docChange(e);
                }

                public void removeUpdate(DocumentEvent e) {
                    docRemove(e);
                }

                public void insertUpdate(DocumentEvent e) {
                    docInsert(e);
                }
            };
        }

        if (_document != null) {
            _document.removeDocumentListener(_docListener);
        }

        _document = d;
        if (!java.beans.Beans.isDesignTime()) {

            _document.addDocumentListener(_docListener);
        }

        if (_myDoc == null) {
            _myDoc = new PlainDocument();
        }
        super.setDocument(_myDoc);
    }

    /**
     * Se ejecuta cuando salta un evento de cambio de documento
     *
     * @param e Evento de cambio de documento
     */
    // These shouldn't get called
    protected void docChange(DocumentEvent e) {
        if (_docListenerDisabled) {
            return;
        }
    }

    /**
     * Se ejecuta cuando se elimina el documento
     *
     * @param e Evento de eliminacion de documento
     */
    protected void docRemove(DocumentEvent e) {
        if (_docListenerDisabled) {
            return;
        }
        super.setText("");
    }

    /**
     * Se ejecuta cuando se inserta el documento
     *
     * @param e Evento de insercion de documento
     */
    protected void docInsert(DocumentEvent e) {
        if (_docListenerDisabled) {
            return;
        }
        int len = e.getLength();
        String data = "";

        try {
            data = _document.getText(0, len);
        } catch (javax.swing.text.BadLocationException ex) {
        }
    }

    /**
     * Establece la función de validación Oracle. <br> Existe la posibilidad de
     * inhabilitar la ejecución de este método desde el método previo a la
     * validación mediante la llamada a setEjecutarFuncion(false). <br> Notese
     * que si la función de validación no se ejecuta correctamente no se
     * ejecutará el método posterior a la validación
     *
     * @param funcionValidacion Nobre de la funcion de validacion oracle
     * asociada a este campo
     * @see #getFuncionValidacion()
     * @see #setMetodoPreValidacion(java.awt.Component,String)
     * @see #setMetodoPostValidacion(java.awt.Component,String)
     * @see #setEjecutarFuncion(boolean)
     */
    public void setFuncionValidacion(String funcionValidacion) {
        this.funcionValidacion = funcionValidacion;
    }

    /**
     * Devuelve la funcion de validacion oracle asociada a este campo
     *
     * @return Funcion de validacion oracle asociada a este campo
     * @see #setFuncionValidacion(String)
     */
    public String getFuncionValidacion() {
        return funcionValidacion;
    }

    //static int iVersion = (System.getProperty("java.version").startsWith("1.4")?0:1);
    /**
     * Permite expresar diferentes dimensiones en la longitud de un campo: el
     * valor visible en pantalla, la longitud que realmente podemos introducir y
     * la longitud mínima aceptada. <br>Los valores Máximo y Mínimo son
     * opcionales. El Máximo tiene como valor por defecto el visible, y el
     * mínimo, 0. El valor mínimo mayor que cero no implica que el campo sea
     * obligatorio. <p>Nótese que en caso de que el campo de texto sea de tipo
     * NUMERO los caracteres de separación de millares y de punto decimal sí que
     * intervienen en el cómputo del número de caracteres. Así el número 1000
     * realmente tiene 5 caracteres ya que el sistema lo interpreta como 1.000
     *
     * @param psLongitud Longitud visible, minima y maxima separadas por comas
     * @see #getLongitud()
     * @see #getLongitudReal()
     */
    public void setLongitud(String psLongitud) {
        this.datosLongitud = psLongitud;
        bHayLongitud = true;

        StringTokenizer tok = new StringTokenizer(psLongitud, ",");
        try {
            longitudVisible = new Integer(tok.nextToken()).intValue();
            longitudReal = new Integer(tok.nextToken()).intValue();
            longitudMinima = new Integer(tok.nextToken()).intValue();
        } catch (java.util.NoSuchElementException f) {
        } catch (java.lang.NumberFormatException e) {
        }
        if (longitudVisible == 1) {
            longitudVisible = 2;
            this.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        }
        if (longitudReal == 0) {
            longitudReal = longitudVisible;
        }
        if (longitudMinima > longitudReal) {
            longitudMinima = longitudReal;
        }
        int cols = longitudVisible;
        if (tipoDato.equals("NUMERICO"))//&&getTipoAnchura().equals(ANCHURA_ESTANDAR))
        {
            cols++;//=iVersion;
            if (this.getNumDecimal() > 0 || (this.getDecimalesDef() && Globales.getNumDec() > 0)) {
                longitudVisible++; // Para el separador decimal
            }

            if (this.isPuntuacion()) {
                if (longitudVisible / 3 > 0) //Para el separador de miles
                {
                    // cols+=(((longitudVisible-this.getNumDecimal())-1)/3);
                }
            }
        }

        if (cols > 0) {
            super.setColumns(cols);
        }
    }

    /**
     * Devuelve el String que representa la longitud. La longitud viene en el
     * formato "visible[,maxima[,minima]]". Si no se incluye el tamaño maximo
     * entonces se considera igual que el visible por defecto. Si no se
     * establece el minimo entonces se toma por defecto el 0. Un valor "minima"
     * mayor de 0 no implica que el campo sea obligatorio
     *
     * @return Longitud del campo con el formato "visible,maxima,minima"
     * @see #setLongitud(java.lang.String)
     */
    public String getLongitud() {
        return this.datosLongitud;
    }

    /**
     * Devuelve el tamaño máximo del campo introducido con setLongitud(String).
     *
     * @return Tamaño real del campo
     * @see #setLongitud(String)
     */
    public int getLongitudReal() {
        return this.longitudReal;
    }

    /**
     * Deviuelve el tamaño visible del campo introducido con setLongitud(String)
     */
    public int getLongitudVisible() {
        return this.longitudVisible;
    }

    /**
     * Indica que el campo debe tener contenido. No permite avanzar si no se
     * cumple. La única forma de salir de un campo de tipo obligatorio o con
     * errores de validación una vez que se ha entrado, es utilizando la tecla
     * &lt;retroceso&gt; o &lt;backespace&gt; previo borrado del contenido del
     * campo
     *
     * @param obligatorio true si se quiere que sea obligatorio, false de otro
     * modo
     * @see #isObligatorio()
     */
    public void setObligatorio(boolean obligatorio) {
        this.obligatorio = obligatorio;
    }

    /**
     * Devuelve true si el campo actual es obligatorio
     *
     * @see #setObligatorio(boolean)
     */
    public boolean isObligatorio() {
        return this.obligatorio;
    }

    /**
     * Establece la máscara deseada de todas las permitidas para esta
     * aplicación. Si el tipo de dato seleccionado es FECHA, en este lugar se
     * formatea la fecha introducida con esa máscara.
     *
     * @param pMascara Mascara de la fecha donde "dd" es el dia "MM" es el mes y
     * "yyyy" es el año
     * @see #getMascaraFecha()
     * @see #setTipoDato(String)
     */
    public void setMascaraFecha(String pMascara) {
        if (pMascara == null || pMascara.trim().equals("") || pMascara.trim().equals("null")) {
            if (this.tipoDato.equals("FECHA")) {
                if (Globales.getFormatoFecha().toUpperCase().equals("MM-DD-YYYY")) {
                    this.mascaraFecha = "MM-dd-yyyy";
                } else {
                    this.mascaraFecha = "dd-MM-yyyy";
                }
            } else {
                return;
            }
        } else {
            if (Globales.getFormatoFecha().toUpperCase().equals("MM-DD-YYYY")) {
                if (pMascara.equals("dd-MM-yyyy")) {
                    pMascara = "MM-dd-yyyy";
                }
            }
            this.mascaraFecha = pMascara;
        }
        int lon = Herramientas.quitaMascaraFecha(pMascara).length();
        if (this.tipoDato.equals("FECHA")) {
            this.setLongitud("" + pMascara.length() + "," + lon + "," + lon);
            this.setSigno(false);
        }
    }

    /**
     * Devuelve la mascara de fecha establecida.
     *
     * @return Mascara de fecha
     * @see #setMascaraFecha(String)
     */
    public String getMascaraFecha() {
        return mascaraFecha;
    }

    /**
     * Se indica aquí el tipo de dato: NUMERICO, FECHA o TEXTO. El numérico se
     * complementa con la opción de <i>Numero Decimales</i>. El de fecha, con
     * <i>Máscara de Fecha</i> y el de Texto con la opción <i>Mayúsculas</i>.
     * Todos Texto y Números obedecen también a la propiedad de <i>Longitud</i>
     *
     * @param pTipoDato Tipo de dato que admitira el campo
     * @see #getTipoDato()
     * @see #setNumDecimal(int)
     * @see #setMascaraFecha(String)
     * @see #setMayusculas(boolean)
     * @see #setLongitud(String)
     */
    public void setTipoDato(String pTipoDato) {
        bHayTipoDato = true;
        this.tipoDato = pTipoDato;
        miFuente = new Font("dialog", Font.PLAIN, 12);
        this.setFont(miFuente);

        if (this.tipoDato.trim().equals("FECHA")) {
            if (mascaraFecha == null || mascaraFecha.trim().equals("")) {
                setMascaraFecha("dd-MM-yyyy");
            }
        } else {
            setMascaraFecha("");
        }
        if (this.tipoDato.trim().equals("NUMERICO")) {
            miFuente = new Font("TronWeb", Font.PLAIN, 12);
            this.setFont(miFuente);

        }
        if (bHayLongitud) {
            setLongitud(this.datosLongitud);
        }

    }

    /**
     * Devuelve el tipo de dato que admitira el campo actual. El tipo de dato
     * solo puede ser TEXTO, NUMERO o FECHA.
     *
     * @return TEXTO si es un texto, NUMERO si es numerico o FECHA si es una
     * fecha
     * @see #setTipoDato(java.lang.String)
     */
    public String getTipoDato() {
        return tipoDato;
    }

    /**
     * En proceso de construccion
     */
    /////Para el literal asociado/////
    public java.lang.String getLiteral() {
        return this.parametros;
    }

    /**
     * En proceso de construccion
     *
     */
    public void setLiteral(java.lang.String parametros) {
    }

    /**
     * En proceso de construccion
     *
     */
    public void setMayusculasLiteral(boolean mayusculas) {
    }

    /**
     * En proceso de construccion
     */
    public boolean isMayusculasLiteral() {
        return this.mayusculas;
    }

    /**
     * Si el campo tiene activada la opción de caché, en esta propiedad se
     * indicará la tabla de donde procede la información que contiene el citado
     * campo. La finalidad es la del mantenimiento de la información de los
     * datos de caché. En efecto, si se modifica o borra algún dato de la tabla
     * "nodriza", entonces el sistema debe inhabilitar la información contenida
     * en el caché del usuario sobre dicha tabla.
     *
     * @param cache Cache que se desea usar
     * @see #getCache()
     */
    public void setCache(String cache) {
        this.cache = cache;
    }

    /**
     * Devuelve una cadena que representa la tabla cache que se usara para el
     * campo actual
     *
     * @return cache usada
     * @see #setCache(java.lang.String)
     */
    public String getCache() {
        return this.cache;
    }

    /**
     * En proceso de construccion
     */
    public void setRellenarLiteral(boolean rellenar) {
        this.rellenar = rellenar;

    }

    /**
     * En proceso de construccion
     */
    public boolean isRellenarLiteral() {

        return this.rellenar;
    }

    /**
     * Pregunta si el campo actual esta validado. Un campo no esta validado en
     * los siguiente casos: <ul> <li>Cuando falla la funcion de validacion
     * oracle asociada al campo</li> <li>Cuando no se respeta el tipo de dato
     * (longitud minima, fecha incorrecta, ...)</li> <li>Cuando se establece el
     * valor del campo con un setText(String)</li> <li>Cuando se establece el
     * valor de validación con un setValidate(false)</li> </ul> Un campo no
     * validado se muestra al usuario en cursiva
     *
     * @return true si el campo esta validado, false en caso contrario
     * @see #setValidate(boolean)
     */
    public boolean isValidate() {
        return this.isValidate;
    }

    public void setNombreReal(String psNombreReal) {
        sNombreReal = psNombreReal;
        super.setName(psNombreReal);
    }

    public String getNombreReal() {
        return sNombreReal;
    }

    /**
     * @deprecated Ver isValidate()
     * @see #isValidate()
     */
    public boolean estaValidado() {
        return this.isValidate;
    }

    /**
     * Establece el valor de validacion actual del campo. <br><b>NOTA :</b>Si se
     * establece el TTextField como validado con esta función, no se lanzará la
     * validación Oracle.
     *
     * @param pbIsValidate true si se quiere dar como valida al campo, false en
     * otro caso
     * @see #isValidate()
     * @see #setFuncionValidacion(String)
     */
    public void setValidate(boolean pbIsValidate) {
        this.isValidate = pbIsValidate;
        if (!this.isValidate) {
            if (this.isEnabled()) {
                this.setFont(new Font(this.getFont().getName(), Font.ITALIC, this.getFont().getSize()));
            }
            valorPrevio = null;
        } else {
            this.setFont(new Font(this.getFont().getName(), Font.PLAIN, this.getFont().getSize()));
        }
    }

    /**
     * Para permitir preparar las listas de valores, existe la posibilidad de
     * ejecutar este método antes de que la lista sea utlizada. Este tipo de
     * métodos no lleva parámetros ni devuelve valores. <p><B>NOTA: </b>Este
     * método no tendrá efecto si se pasa como segundo argumento la cadena vacía
     * o
     * <code>null</code>.
     *
     * @param padre componente propietario del metodo que se quiere ejecutar
     * antes de desplegar la lista
     * @param pmMetodo Nombre del metodo que se va a ejecutar antes de la lista
     * @see #getMetodoPreLista()
     * @see #setListaVal(java.awt.Component,String)
     * @see #setMetodoPostLista(java.awt.Component,String)
     */
    public void setMetodoPreLista(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }

        this._padre = padre;
        this._metodoPreLista = pmMetodo;
    }

    /**
     *
     * @see #setMetodoPreLista(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreLista(String pmMetodo) {
        this._metodoPreLista = pmMetodo;

    }

    /**
     * Devuelve el metodo previo a la lista establecido con setMetodoPreLista La
     * lista es un dialogo que muestra una lista de valores en la que el usuario
     * puede seleccionar uno para rellenar el campo actual
     *
     * @return Nombre del metodo previo a la lista
     * @see #setMetodoPreLista(java.awt.Component, java.lang.String)
     */
    public String getMetodoPreLista() {
        if (this._metodoPreLista == null) {
            return "";
        }
        return this._metodoPreLista;
    }

    /**
     * @deprecated Usar setMetodoPostLista(java.awt.Component, java.lang.String)
     * @see #setMetodoPostLista(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostLista() {
    }

    /**
     * Se ejecuta después de la utilización de una lista de valores, y se
     * utiliza por ejemplo para asignar el resultado de la selección en la lista
     * a un campo determinado <p><B>NOTA: </b>Este método no tendrá efecto si se
     * pasa como segundo argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre componente propietario del metodo que se quiere ejecutar
     * antes de desplegar la lista
     * @param pmMetodo Nombre del metodo que se va a ejecutar antes de la lista
     * @see #setListaVal(java.awt.Component,String)
     * @see #setMetodoPreLista(java.awt.Component,String)
     * @see #getValoresLista()
     */
    public void setMetodoPostLista(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }

        this._padre = padre;
        this._metodoPostLista = pmMetodo;
    }

    /**
     * setMetodoPostLista(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPostLista(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostLista(String pmMetodo) {
        this._metodoPostLista = pmMetodo;
    }

    /**
     * Devuelve el metodo posterior a la lista establecido con
     * setMetodoPostLista.
     *
     * @return Metodo previo a la lista
     * @see #setMetodoPostLista(java.awt.Component, java.lang.String)
     */
    public String getMetodoPostLista() {
        if (this._metodoPostLista == null) {
            return "";
        }
        return this._metodoPostLista;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPostMenu(java.awt.Component,
     * java.lang.String)"> setMetodoPostMenu(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPostMenu(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostMenu() {
    }

    /**
     * Se ejecuta despues de la visualizacion y cerrado de un menu de opciones.
     * <br> En este método normalmente se obtendrá el valor seleccionado del
     * menú, con las funciones getValorMenuOpciones() y getOpcionMenu.
     * <br><p><B>NOTA: </b>Este método no tendrá efecto si se pasa como segundo
     * argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre componente propietario del metodo que se quiere ejecutar
     * antes de desplegar la lista
     * @param pmMetodo metodo a ejecutar despues del menu de opciones
     * @see #getMetodoPostMenu()
     * @see #getValorMenuOpciones()
     * @see #getOpcionMenu()
     * @see #setMenuOpciones(String)
     * @see #setMetodoPreMenu(java.awt.Component,String)
     */
    public void setMetodoPostMenu(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }

        this._padre = padre;
        this._metodoPostMenu = pmMetodo;
    }

    /**
     * setMetodoPostMenu(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPostMenu(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostMenu(String pmMetodo) {
        this._metodoPostMenu = pmMetodo;

    }

    /**
     * Devuelve el metodo posterior al menu establecido con setMetodoPostMenu
     *
     * @return Nombre del metodo posterior al menu
     * @see #setMetodoPostMenu(java.awt.Component,String)
     */
    public String getMetodoPostMenu() {
        if (this._metodoPostMenu == null) {
            return "";
        }
        return this._metodoPostMenu;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPreMenu(java.awt.Component,
     * java.lang.String)"> setMetodoPreMenu(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPreMenu(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreMenu() {
    }

    /**
     * Se ejecuta el metodo anterior al menu de opciones. <p><B>NOTA: </b>Este
     * método no tendrá efecto si se pasa como segundo argumento la cadena vacía
     * o
     * <code>null</code>.
     *
     * @param padre componente propietario del metodo que se quiere ejecutar
     * antes de desplegar la lista
     * @param pmMetodo metodo a ejecutar
     * @see #setMenuOpciones(String)
     * @see #setMetodoPostMenu(java.awt.Component,String)
     */
    public void setMetodoPreMenu(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }

        this._padre = padre;
        this._metodoPreMenu = pmMetodo;
    }

    /**
     * setMetodoPreMenu(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPreMenu(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreMenu(String pmMetodo) {
        this._metodoPreMenu = pmMetodo;

    }

    /**
     * Devuelve el metodo anterior al menu establecido con setMetodoPostMenu
     *
     * @return Nombre del metodo anterior al menu
     * @see #setMetodoPreMenu(java.awt.Component, java.lang.String)
     */
    public String getMetodoPreMenu() {
        if (this._metodoPreMenu == null) {
            return "";
        }
        return this._metodoPreMenu;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPreLista(java.awt.Component,
     * java.lang.String)"> setMetodoPreLista(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPreLista(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreLista() {
    }

    /**
     * @deprecated Usar <a href="#setMetodoPostCampo(java.awt.Component,
     * java.lang.String)"> setMetodoPostCampo(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPostCampo(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostCampo() {
    }

    /**
     * Establece el metodo a ejecutar tras abandonar el campo actual. Este
     * metodo no se ejecuta si se intenta abandonar el campo pero el sistema lo
     * impide por ser un campo invalido o si se sale del campo sin haber
     * realizado ningun cambio. Notese que el metodo post-campo no debera tener
     * parametros de entrada y, ademas, debera devolver un objeto de clase
     * Boolean. Por ejemplo:
     *
     * <pre>
     * public Boolean salgoDeCampo()
     * {
     *   //Acciones a ejecutar al salir del campo
     *   //...
     *   return new Boolean(true)
     * }
     * </pre> <p><B>NOTA: </b>Este método no tendrá efecto si se pasa como
     * segundo argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre Objeto propietario del metodo a ejecutar tras abandonar el
     * campo
     * @param pmMetodo Nombre del metodo a ejecutar al salir del campo
     * @see #getMetodoPostCampo()
     * @see #setMetodoPreCampo(java.awt.Component,String)
     */
    public void setMetodoPostCampo(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            this._metodoPostCampo = "";
            return;
        }

        this._padre = padre;
        this._metodoPostCampo = pmMetodo;
    }

    /**
     * setMetodoPostCampo(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPostCampo(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostCampo(String pmMetodo) {
        this._metodoPostCampo = pmMetodo;

    }

    /**
     * Devuelve el metodo que se ejecutara al salir del campo.
     *
     * @return Nombre del metodo que se ejecutara
     * @see #setMetodoPostCampo(java.awt.Component, java.lang.String)
     */
    public String getMetodoPostCampo() {
        if (this._metodoPostCampo == null) {
            return "";
        }
        return this._metodoPostCampo;
    }

    /**
     * Este método solamente se ejecutará si existe una función de validación
     * Oracle igualmente declarada. Se dispara inmediatamente antes de la
     * función Oracle, y se utilizará fundamentalmente para preparar los datos
     * que la sentencia de validación necesita. Un caso típico será el de la
     * asignación de parámetros a la sentencia Oracle:
     *
     * <pre>
     * public Boolean preValidacion()
     * {
     *
     *     String lsResultado="S";
     *
     *     .............
     *
     *     txtNombre.addParametro(this.txtNombre,"IN",0,4);
     *
     *     txtNombre.addParametro(lsResultado,"IN OUT",2);
     *
     *     .........
     *
     *     return new Boolean(true);
     *
     * }
     * </pre>
     *
     * <br> Con la primera función addParametro se indica que el parámetro 0 (de
     * un total de 4) del procedimiento Oracle dc_p_valida_campo es el contenido
     * del propio campo txtNombre y que es de tipo IN (es decir, envía un dato
     * con contenido). Con la segunda función addParametro hace que el parámetro
     * 2 del mismo procedimiento es de tipo IN OUT (envia información y espera
     * recibir otra nueva) y se guarde en la variable local de tipo String
     * "lsResultado". Si no interesa más información (resto de parámetros,
     * entendiendo que son OUT o IN no obligatorios) no será necesario indicar
     * nada más. <p><B>NOTA: </b>Este método no tendrá efecto si se pasa como
     * segundo argumento la cadena vacía o
     * <code>null</code>. <br>En este punto será posible impedir la ejecución de
     * la función de Validación Oracle, mediante
     * <code>setEjecutarFuncion(false)</code> .
     *
     * @param padre Objeto propietario del metodo
     * @param pmMetodo Nombre del metodo que se ejecutara en "padre"
     * @see #getMetodoPreValidacion()
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPostValidacion(java.awt.Component,String)
     * @see #addParametro(java.lang.Object,String,int,int)
     * @see #addParametro(java.lang.Object,String,int)
     * @see #setEjecutarFuncion(boolean)
     */
    public void setMetodoPreValidacion(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }
        this._padre = padre;
        this._metodoPreValidacion = pmMetodo;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPreValidacion(java.awt.Component,
     * java.lang.String)"> setMetodoPreValidacion(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPreValidacion(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreValidacion() {
    }

    /**
     * setMetodoPreValidacion(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPreValidacion(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreValidacion(String pmMetodo) {
        this._metodoPreValidacion = pmMetodo;
    }

    /**
     * Devuelve el nombre del metodo previo a la validacion oracle.
     *
     * @return El nombre del metodo previo a la validacion oracle
     * @see #setMetodoPreValidacion(java.awt.Component, java.lang.String)
     */
    public String getMetodoPreValidacion() {
        if (this._metodoPreValidacion == null) {
            return "";
        }
        return this._metodoPreValidacion;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPreCampo(java.awt.Component,
     * java.lang.String)"> setMetodoPreCampo(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPreCampo(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreCampo() {
    }

    /**
     * Establece el método previo al campo. <br>El método establecido como
     * previo al campo, se ejecutará cada vez que entremos en él, bien a través
     * del teclado bien del ratón. Todos los métodos pre y post asociados al
     * componente son de la clase Boolean , y no llevan parámetros. En el
     * ejemplo, definiremos dentro de nuestra clase el método:
     *
     * <pre>
     * public Boolean entroEnCampo()
     * {
     *             //..... acciones que se ejecutan al entrar en el campo actual .....
     *
     *             return new Boolean(true);
     * }
     * </pre>
     *
     * @param padre Objeto propietario del metodo precampo
     * @param pmMetodo Nombre del metodo. <b>NOTA:</B >Si es nulo o vacío este
     * método no tiene ningún efecto.
     */
    public void setMetodoPreCampo(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            this._metodoPreCampo = "";
            return;
        }
        this._padre = padre;
        this._metodoPreCampo = pmMetodo;
    }

    /**
     * setMetodoPreCampo(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPreCampo(java.awt.Component, java.lang.String)
     */
    public void setMetodoPreCampo(String pmMetodo) {
        this._metodoPreCampo = pmMetodo;

    }

    /**
     * Devuelve el nombre del metodo previo al campo
     *
     * @return El nombre del metodo previo al campo
     * @see #setMetodoPreCampo(java.awt.Component,String)
     */
    public String getMetodoPreCampo() {
        if (this._metodoPreCampo == null) {
            return "";
        }
        return this._metodoPreCampo;
    }

    /**
     * @deprecated Usar <a href="#setMetodoTeclas(java.awt.Component,
     * java.lang.String)"> setMetodoTeclas(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoTeclas(java.awt.Component, java.lang.String)
     */
    public void setMetodoTeclas() {
    }

    /**
     * Establece el método de teclas asociado al componente. El método de teclas
     * permite redefinir el comportamiento de teclas de función asociado al
     * componente.<br> Las teclas de función realizan determinadas acciones por
     * defecto. Si se quiere modificar el comportamiento por defecto de las
     * teclas de función será necesario crear un método según se indica en la
     * documentación de la clase ControlTeclas. <p><B>NOTA: </b>Este método no
     * tendrá efecto si se pasa como segundo argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre Objeto propietario del metodo
     * @param pmMetodo Nombre del metodo que se ejecutara en "padre"
     * @see #getMetodoTeclas()
     * @see #getPadreMetodoTeclas()
     * @see ControlTeclas
     */
    public void setMetodoTeclas(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("null")) {
            return;
        }
        this._padre = padre;
        this._metodoTeclas = pmMetodo;
    }

    /**
     * Recupera el propietario del metodo de redefinicion de teclas. Usualmente
     * será el panel, diálogo o frame donde esté contenido el componente actual.
     *
     * @return Objeto propietario del método de teclas
     * @see #setMetodoTeclas(java.awt.Component,String)
     */
    public Object getPadreMetodoTeclas() {
        return this._padre;
    }

    /**
     * setMetodoTeclas(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoTeclas(java.awt.Component, java.lang.String)
     */
    public void setMetodoTeclas(String pmMetodo) {
        this._metodoTeclas = pmMetodo;

    }

    /**
     * Devuelve el nombre del metodo de teclas asociado al TTextField
     *
     * @return Nombre del metodo de teclas asociado al TTextField
     * @see #setMetodoTeclas(java.awt.Component, java.lang.String)
     */
    public String getMetodoTeclas() {
        if (this._metodoTeclas == null) {
            return "";
        }

        return this._metodoTeclas;
    }

    /**
     * Ejecuta el método indicado. <br>Uso interno.
     *
     * @param piCual Indica la constante de el método que se desea ejecutar
     * @param piParametro Parametro a la función indicada
     * @return True si se ejecuta satisfactoriamente, false en otro caso
     */
    public boolean ejecutaMetodo(int piCual, Integer piParametro) {

        boolean retorno = false;
        Herramientas.setCursorEspera();
        try {
            retorno = ejecutaMetodo(piCual, piParametro, true);
        } catch (NullPointerException e) {
            bEraOpaco = isOpaque();
            setOpaque(true);
            this.setBackground(COLOR_ERROR);
            //this.setBorder(bordeError);
            TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190003") + " (" + nombreMetodo(piCual) + ")");//"El método "+nombreMetodo(piCual)+" debe devolver tipo Boolean");
            setValidate(false);
            hayMensajeError = true;
        }
        Herramientas.setCursorNormal();
        return retorno;
    }

    /**
     * Devuelve en nombre del método que se pasa como parámetro.
     *
     * @param int Constante que representa el tipo de método
     * @return Nombre del método especificado
     * @see #METODO_PRECAMPO
     * @see #METODO_PREVALIDACION
     * @see #METODO_POSTCAMPO
     * @see #METODO_TECLAS
     * @see #METODO_PRELISTA
     * @see #METODO_POSTLISTA
     * @see #METODO_POSTVALIDACION
     * @see #METODO_PREMENU
     * @see #METODO_POSTMENU
     * @see #METODO_ONERROR
     */
    private String nombreMetodo(int piCual) {
        if (piCual == this.METODO_PRECAMPO) {
            return _metodoPreCampo;
        }
        if (piCual == this.METODO_PREVALIDACION) {
            return _metodoPreValidacion;
        }
        if (piCual == this.METODO_POSTCAMPO) {
            return _metodoPostCampo;
        }
        if (piCual == this.METODO_TECLAS) {
            return _metodoTeclas;
        }
        if (piCual == this.METODO_PRELISTA) {
            return _metodoPreLista;
        }
        if (piCual == this.METODO_POSTLISTA) {
            return _metodoPostLista;
        }
        if (piCual == this.METODO_POSTVALIDACION) {
            return _metodoPostValidacion;
        }
        if (piCual == this.METODO_PREMENU) {
            return _metodoPreMenu;
        }
        if (piCual == this.METODO_POSTMENU) {
            return _metodoPostMenu;
        }
        if (piCual == this.METODO_ONERROR) {
            return _metodoOnError;
        }
        return "NULL";
    }

    /**
     * Ejecuta el método (PRECAMPO, PREVALIDACION, TECLAS...) que se le pasa
     * como argumento a través de piCual
     */
    private boolean ejecutaMetodo(int piCual, Integer piParametro, boolean trucho) {
        if (bEjecutarFuncion == false) {
            bEjecutarFuncion = true;
            return true;
        }

        Boolean result = new Boolean(true);
        Method miMetodo = null;
        String lsNombreMetodo = "";
        Object[] parametros = null;

        if (_padre == null) {
            if (piCual == this.METODO_TECLAS) {
                return false;
            }

            return true;
        }

        //ponCursor(miCursorWait);


        Class miClase = _padre.getClass();
        try {


            if (piCual == this.METODO_PRECAMPO)// metodoPreCampo
            {
                lsNombreMetodo = _metodoPreCampo;
                if (Herramientas.nvl(_metodoPreCampo).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPreCampo, null);
            } else if (piCual == this.METODO_PREVALIDACION)// metodoPreValidacion
            {
                lsNombreMetodo = _metodoPreValidacion;
                if (Herramientas.nvl(_metodoPreValidacion).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPreValidacion, null);
            } else if (piCual == this.METODO_POSTVALIDACION)// metodoPostValidacion
            {
                lsNombreMetodo = _metodoPostValidacion;
                if (Herramientas.nvl(_metodoPostValidacion).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPostValidacion, null);
            } else if (piCual == this.METODO_ONERROR)// metodoPostValidacion
            {
                lsNombreMetodo = _metodoOnError;
                if (Herramientas.nvl(_metodoOnError).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoOnError, null);
            } else // metodoPostCampo
            if (piCual == this.METODO_POSTCAMPO) {
                lsNombreMetodo = _metodoPostCampo;
                if (Herramientas.nvl(_metodoPostCampo).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPostCampo, null);
            } else // metodoPreLista
            if (piCual == this.METODO_PRELISTA) {
                lsNombreMetodo = _metodoPreLista;
                if (Herramientas.nvl(_metodoPreLista).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPreLista, null);
            } else // metodoPostLista
            if (piCual == this.METODO_POSTLISTA) {
                lsNombreMetodo = _metodoPostLista;
                if (Herramientas.nvl(_metodoPostLista).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPostLista, null);
            } else // metodoPreMenu
            if (piCual == this.METODO_PREMENU) {
                lsNombreMetodo = _metodoPreMenu;
                if (Herramientas.nvl(_metodoPreMenu).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPreMenu, null);
            } else // metodoPostMenu
            if (piCual == this.METODO_POSTMENU) {
                lsNombreMetodo = _metodoPostMenu;
                if (Herramientas.nvl(_metodoPostMenu).equals("")) {
                    return true;
                }
                miMetodo = miClase.getMethod(_metodoPostMenu, null);
            } else if (piCual == this.METODO_TECLAS) {
                lsNombreMetodo = _metodoTeclas;
                if (Herramientas.nvl(_metodoTeclas).equals("")) {
                    return false;
                }
                Class[] parameterTypes = new Class[]{Integer.class};
                miMetodo = miClase.getMethod(_metodoTeclas, parameterTypes);
                parametros = new Object[1];
                parametros[0] = piParametro;
            } else {
                return false;
            }
            result = (Boolean) miMetodo.invoke(_padre, parametros);
        } catch (Exception e) {
            bEraOpaco = isOpaque();
            this.setOpaque(true);
            this.setBackground(COLOR_ERROR);

            //this.setBorder(bordeError);
            TBarraEstado.setMensajeError("Error: " + lsNombreMetodo);
            setValidate(false);
            hayMensajeError = true;
            return false;
        }
        return result.booleanValue();
    }

    /**
     * Indica si un TTextField obligatorio es válido o no, dependiendo de si ha
     * sido rellenado o no.
     *
     * @return True si contiene texto, false en otro caso.
     */
    //public boolean validaObligatorio()
    private boolean validaObligatorio() {
        if (obligatorio && super.getText().trim().equalsIgnoreCase("")) {
            //LLamada al metodo de ventana de aviso
            bEraOpaco = isOpaque();
            this.setOpaque(true);
            this.setBackground(COLOR_ERROR);
            TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20003"));
            setValidate(false);
            hayMensajeError = true;
            return false;
        }
        return true;
    }

    /**
     * Devuelve el panel de grupo al que pertenece el TTextField.
     *
     * @return Panel indicado en el <i>Panel de Validación en Bloques</i>
     * @see #setPanelGrupo(javax.swing.JPanel)
     */
    public javax.swing.JPanel getPanelGrupo() {
        return this.panelGrupo;
    }

    /**
     * Para facilitar la navegación y la integridad de la información, existe un
     * sistema interno de validación de campos dentro de unidades amplias de
     * información (Bloques en forms). En esta propiedad se indica el bloque al
     * que pertenece un campo o <i>Panel de Validación en Bloques</i> .
     * <br><B>Atencion:</b> todos los componentes deben tener definido su panel
     * de validacion en grupo. <br> Para saber más sobre la validación de
     * bloques ver ValidacionBloques.
     *
     * @param pPanelGrupo Panel de validacion en bloque
     * @see #getPanelGrupo()
     * @see ValidacionBloques
     */
    public void setPanelGrupo(javax.swing.JPanel pPanelGrupo) {
        this.panelGrupo = pPanelGrupo;
        try {
            this.setInputVerifier(new TInputVerifier());
        } catch (NoSuchMethodError e) {
            //msg("La versión actual de Java no soporta InputVerifier");
        }

    }

    /**
     * Método para verificar la longitud del campo. <br> Se comprueba que el
     * contenido del TTextField tenga una longitud conforme con la definida en
     * la propiedad <>Longitud(visible[,maxima[,minima]])</i> .
     *
     * @return Devuelve true si se ha validado correctamente
     * @see #setLongitud(String)
     */
    //public boolean validaLongitud()
    private boolean validaLongitud() {
        if (this.tipoDato.equals("NUMERICO") && this.getText().length() > 0) {
            char coma = this.getText().charAt(this.getText().length() - 1);
            if (coma == ',') {
                this.setText(this.getText().substring(0, this.getText().length() - 1));
            }
        }
        if (longitudMinima > 0 && super.getText().length() < longitudMinima && !(super.getText().trim().equals(""))) {
            String adendum = " (" + longitudMinima;
            if (longitudReal > 0) {
                adendum += "~" + longitudReal;
            }
            adendum += ")";
            //msg("Validacion de longitud falla");
            TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20024") + adendum);
            bEraOpaco = isOpaque();
            this.setOpaque(true);
            this.setBackground(COLOR_ERROR);

            //this.setBorder(bordeError);
            setValidate(false);
            hayMensajeError = true;
            return false;
        }

        return true;
    }

    /**
     * Metodo para validar el tipo de dato
     *
     * @return Devuelve true si la validación ha sido correcta
     */
    //public boolean validaTipoDato()
    private boolean validaTipoDato() {
        //msg("validaTipoDato "+this.getNombreCampo());
        if (tipoDato.equals("NUMERICO")) {
            String numeroFormate = Herramientas.ponFormatoImporte(
                    super.getText(),
                    getDecimalesDef() ? Globales.getNumDec() : getNumDecimal(),
                    isPuntuacion());
            if (numeroFormate != null) {
                super.setText(numeroFormate);
            }
            return true;
        }

        if (tipoDato.equals("FECHA") && !(super.getText().trim().equals(""))) {
            try {
                String tengo = Herramientas.quitaMascaraFecha(super.getText());
                //msg("la fecha era: ["+tengo+"]");
                String textoFecha = Herramientas.ponMascaraFecha(this.getMascaraFecha(), getText());
                //msg("la fecha es: ["+textoFecha+"]");            
                //msg("Al quitar la máscara queda ["+Herramientas.quitaMascaraFecha(textoFecha)+"]");
                if (!tengo.equals(Herramientas.quitaMascaraFecha(textoFecha))) {
                    TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20078") + " (" + Herramientas.quitaMascaraFecha(mascaraFecha) + ")");
                    return false;
                }
                super.setText(textoFecha);
            } catch (Exception e) {
                e.printStackTrace();
                //LLamada al metodo de mostrar mensaje de fecha incorrecta
                TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20078") + " (" + Herramientas.quitaMascaraFecha(mascaraFecha) + ") " + e.toString());
                bEraOpaco = isOpaque();
                this.setOpaque(true);
                this.setBackground(COLOR_ERROR);

                //this.setBorder(bordeError);
                setValidate(false);
                hayMensajeError = true;
                return false;
            }
        }
        return true;
    }

    /**
     * Fuerza a que se lance la validacion del TTextField. Ello incluye tanto la
     * validación de obligatoriedad, como la de tipo de dato como la validación
     * oracle.
     *
     * @param remoto true si se desea validacion remota
     * @return true si el campo queda validado, false en otro caso
     * @see #setFuncionValidacion(String)
     */
    public boolean lanzaValidacion(boolean remoto) {
        //Juan. Adaptacion JDK1.4
        if (isEjecutandoMenu()) {
            return true;
        }
        //
        if (!this.isEnabled() || //Si es de salida se comporta como JLabel
                !this.isEditable() || //Si es de salida se comporta como JLabel
                (this.isNull() && //Para el caso de que sea nulo, no obligatorio y no "Valida si nulo"
                !this.isValidaNulo()
                && !this.isObligatorio())) {
            setValidate(true);
            estaValidando = true;
            hayMensajeError = false;
            //msg("*****  Se sale 1 "+getNombreCampo());
            return true;
        }
        /*GDP   
         if (!remoto&&this.isValidaNulo()&&this.isNull())
         {
         validacionBloques.setUltimoComponente(this);
         setValidate(false);
         hayMensajeError=false;
         //msg("*****  Se sale 2");
         //return true;
         }
         GDP*/
        if ((_validaNulo && super.getText().equals(""))
                || !super.getText().equals(valorPrevio) || !isValidate) {
            // Aqui se impide sobrepasar el rango maximo o minimo de un dato numérico
            if (this.tipoDato.equals("NUMERICO")) {
                //msg("super.getText()="+super.getText()+event.getKeyChar());
                //msg("el valor es "+getText()+" / "+super.getText());
                double valor = 0;
                if (!super.getText().equals("")) //Añadido lvalero 27092000
                {
                    try {
                        valor = (new Double(getText())).doubleValue();
                    } catch (NumberFormatException e) {
                        TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190007"));
                        return false;
                    }

                }
                if (valor > this.getNumMaximo()) {
                    setValidate(false);
                    hayMensajeError = true;
                    TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190014"));//"El valor númerico no puede sobrepasar el valor máximo establecido.");

                    //msg("***** Se sale por maximo "+getNombreCampo());                
                    //this.requestFocus(); !!!!!!!!
                    return false;
                }
                if (valor < this.getNumMinimo()) {
                    TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190015"));//"El valor númerico no puede ser inferior al valor mínimo establecido.");
                    setValidate(false);
                    hayMensajeError = true;

                    //msg("***** Se sale por minimo "+getNombreCampo());                                
                    //this.requestFocus(); !!!!!!!!!!!!!!!
                    return false;
                }
            }

            if (!(validaObligatorio() && validaLongitud() && validaTipoDato() && ejecutaMetodo(this.METODO_PREVALIDACION, null))) {
                //msg("No va a realizar la validacion de metodo");
                setValidate(false);
                hayMensajeError = true;
                //msg("***** Se sale 2 "+getNombreCampo());
                //this.requestFocus();	            //!!!!!!!!!!!
                return false;
            }
            //msg("***** Ha realizado el preMetodo y ejecuta validación oracle");
            if (bValidarContraListaOpciones && this.listaOpc != null) {
            } else if (!validacionMetodo()) //recordar que validacionMetodo llama internamente al post validacion
            {
                //msg("Fallo en la validacion de metodo oracle");
                setValidate(false);
                hayMensajeError = true;
                //msg("***** Se sale 3 "+getNombreCampo());                
                //this.requestFocus();	          //!!!!!!!!!!  	            
                return false;
            }

            setValidate(true);
            estaValidando = true;
            hayMensajeError = false;

        } else {
            setValidate(this.isValidate);
            estaValidando = isValidate;
        }
        //msg("*****  Se sale al final "+getNombreCampo());
        return true;
    }

    /**
     * Establece el puntero de ratón
     *
     * @deprecated Usar Herramientas.setCurorNormal() y
     * Herramientas.setCursorEspera()
     */
    private void ponCursor(Cursor cursor) {
        if (_padre != null) {
            _padre.setCursor(cursor);
        } else {
            super.setCursor(cursor);
        }
    }

    /**
     * Se lanza al perder el foco el campo. <br>Uso interno.
     *
     * @param event Evento de perdida de foco
     */
    public void focusLost(java.awt.event.FocusEvent event) {
        //Control del evento temporal

//      System.err.println("TTextField.focusLost "+this.getNombreReal());

        if (event.isTemporary() || !(this.isEnabled()) || validacionTemporal) {
            return;
        }

        //Control del borde
        setOpaque(bEraOpaco);
        this.setBackground(Color.white);

        //    this.setBorder(esteBorde);

        //Aquí invalido los campo siguientes del bloque de validación si ha habido cambios
        if (!super.getText().equals(valorPrevio)) {
            if (this.panelGrupo != null) {
                boolean b = this.isValidate();
                validacionBloques.invalidaCampos(this.panelGrupo, this);
                this.setValidate(b);
            }
        }
        //Para garantizar la cursiva a la salida
        if (!this.isValidate) {
            if (this.isEnabled()) {
                this.setFont(new Font(this.getFont().getName(), Font.ITALIC, this.getFont().getSize()));
            }
        } else {
            this.setFont(new Font(this.getFont().getName(), Font.PLAIN, this.getFont().getSize()));
        }


        //Globales.setComponenteActual(null);	     //eliminado lvalero 05102000
        this.setPrimerAcceso(false);
        validacionBloques.setUltimoComponente(this);

        //Añadido lvalero 30102000
        //Si se ejecuta el focusLost es que se ha validado todo correctamente
        //ahora debemos volver a "enmascarar" el dato.
        if (tipoDato.equals("NUMERICO")) {
            String numeroFormate = Herramientas.ponFormatoImporte(
                    getText(), //super.getText()
                    getDecimalesDef() ? Globales.getNumDec() : getNumDecimal(),
                    isPuntuacion());

            if (numeroFormate != null) {
                // 07102003.Juan.Incidencia de decimales por la derecha. Deben decidir como para
                // descomentar este bloque de codigo
                /*
                 if ( numeroFormate.endsWith("0") )
                 {
                 char[] c = numeroFormate.toCharArray();
                 while ( numeroFormate.endsWith("0") )
                 {
                 numeroFormate = numeroFormate.substring(0,numeroFormate.length() -1 ); 
                 }
                 }
                
                 if ( numeroFormate.endsWith(""+Globales.getSepDecimal()) )
                 {
                 numeroFormate = numeroFormate.substring(0,numeroFormate.length() -1 ); 
                 }
                 */
                // 
                boolean valido = isValidate();
                super.setText(numeroFormate);
                setValidate(valido);
            }
        } else if (tipoDato.equals("FECHA") && !(super.getText().trim().equals(""))) {
            try {
                boolean valido = isValidate();
                String textoFecha = Herramientas.ponMascaraFecha(this.getMascaraFecha(), getText());
                super.setText(textoFecha);
                setValidate(valido);
            } catch (Exception e) {
            }
        }
    }

    /**
     * Auditor de eventos de ratón
     */
    class SymMouse extends java.awt.event.MouseAdapter {

        /**
         * Se ejecuta siempre que se hace click con el ratón sobre el campo. Un
         * click consiste en presionar y despresionar un botón del ratón sobre
         * el campo.
         */
        public void mouseClicked(java.awt.event.MouseEvent event) {
            validacionTemporal = true;
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_mouseClicked(event);
            }
            validacionTemporal = false;
        }

        /**
         * Se ejecuta al presionar un botón del ratón sobre el campo.
         */
        public void mouseReleased(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_mouseReleased(event);
            }
        }
    }

    /**
     * Se lanza al presionar con el raton sobre el campo
     *
     * @param event Evento de raton
     */
    void TTextField_mouseReleased(java.awt.event.MouseEvent event) {

        if (event.getModifiers() == (event.BUTTON3_MASK)) {
            if (!this.hasFocus()) {
                this.requestFocus();
            } else {
                if (pop == null) {
                    pop = new TPopupMenu();
                }

                pop.setInvoker(this);
                pop.setVisible(true);
                pop.setLocation(new Point(this.getLocationOnScreen().x + event.getX(), this.getLocationOnScreen().y + event.getY()));

            }
        }
    }
    /**
     * Controla que el campo está en medio de una validación.
     */
    private boolean estoyEnValidacion = false;

    /**
     * Se lanza cuando el campo gana el foco
     *
     * @param event Evento de foco
     */
    public void focusGained(java.awt.event.FocusEvent event) {

        //System.err.println("TTextField.focusGained "+this.getNombreReal());

        if (event.isTemporary() || java.beans.Beans.isDesignTime()) {
            return;
        }

        JComponent actual = this;
        _permiteSalida = false;
        JComponent siguiente = null;

        if (!this.isEnabled()) {
            setOpaque(bEraOpaco);
            this.setBackground(Color.white);
            this.transferFocus();
            return;
        }

        valorInicial = this.getText();
        Component ultimo = validacionBloques.getUltimoComponente();
        _permiteSalida = false;

        //Es preferible que tod el control de fuentes lo haga setValidate
        this.setFont(new Font(this.getFont().getName(), Font.PLAIN, this.getFont().getSize()));

        if (tipoDato.equals("NUMERICO") && /*isPuntuacion() && */ !(super.getText().trim().equals(""))) {
            String numeroFormate = Herramientas.quitaFormatoImporte(super.getText(), Herramientas.PANTALLA);

            if (numeroFormate != null) {
                boolean valido = this.isValidate(); //new 03042001
                super.setText(numeroFormate);
                this.setValidate(valido); //new 03042001
            }

        } else if (tipoDato.equals("FECHA") && !(super.getText().trim().equals(""))) {
            String fechaSin = Herramientas.quitaMascaraFecha(super.getText());
            if (fechaSin != null) {
                super.setText(fechaSin);
            }
        }
        TBarraEstado.setMensaje(this.getToolTipText());


        if (this.hasFocus()) {
            bEraOpaco = isOpaque();
            this.setOpaque(true);
            if (hayMensajeError) {
                this.setBackground(COLOR_ERROR);
            } else if (this.isObligatorio()) {
                this.setBackground(COLOR_OBLIGA);

                //this.setBorder(this.bordeObligado);
            } else {
                this.setBackground(COLOR_NORMAL);
                //this.setBorder(bordeActual);
            }
        } else {
            setOpaque(bEraOpaco);
            this.setBackground(Color.white);

        }

        valorPrevio = super.getText();
        validatePrevio = this.isValidate();

        //System.err.println("TTextField.focusGained "+this.getNombreReal()+" DOS");
        if (super.getText().length() != 0) {
            this.selectAll();
        }

        if ((getPadre().getComponenteActual() != this || this.getNextFocusableComponent() == this) //beware 08032001
                && this.isEnabled()) {
            ejecutaMetodo(this.METODO_PRECAMPO, null);
        }
        //System.err.println("TTextField.focusGained "+this.getNombreReal()+" TRES");
        //17112003.Juan. Añado a la condicion de "enabled" el que el TTextField tenga realmente el foco (hasFocus())para establecerlo
        //               como componenteactual. Esto lo hago poruq al hacerse visible un TVentana el focusGained del primer TTExtField
        //               se dispara, aunque el foco esta en otro campo. Lo quito, pues una lista de valores en un precampo se "embucla"
        //System.err.println("Foco "+this.getNombreReal()+" "+this.hasFocus());
        if (this.isEnabled()) {
            // Añadido el 10052001
            getPadre().setComponenteActual(this); //Solamete cuando este habilitado hay que hacer el setComponenteActual()
        }

        //getPadre().setComponenteActual(this); //En cualquier caso hay que hacer el setComponenteActual()
        if (!(this.nombreCampo.trim().equals("")) && this.getText().trim().equals("") && this.isPrimerAcceso()) {

            ponAutoValor();
        }
        //msg("---FINAL Txt focusGained "+this.getNombreCampo());
        //Control de la validación en la salida
        validacionBloques.permiteSalirAtras(this.getPanelGrupo(), this);
    }

    /**
     * Información que aparecerá sobre el contenido del campo. <br>Dicha
     * información aparecerá en la Barra de Estado y sobre una etiqueta flotante
     * en la posición del puntero del rato´n al situarse eate sobre el
     * TTextField. <br>La información se toma de la tabla de Etiquetas
     * correspondiente al Idioma del Usuario, Módulo de la Aplicación y Código
     * de mensaje.
     *
     * @param psCodToolTip Codigo de la etiqueta asociada al tooltip con el
     * formato "Modulo,Codigo"
     * @see #getToolTip()
     * @see #setNombreCampo(String)
     */
    public void setToolTip(String psCodToolTip) {
        this.codToolTip = psCodToolTip;
        if (!psCodToolTip.equals("") && psCodToolTip.indexOf(',') >= 0) {
            StringTokenizer cadenas = new StringTokenizer(this.codToolTip, ",");
            String lsCodModulo = cadenas.nextToken();
            String lsCodTexto = cadenas.nextToken();
            this.setToolTipText(Globales.leeCodigoSugerencia(lsCodModulo, new Integer(lsCodTexto).intValue()));
        }
    }

    /**
     * Recupera el tool tip establecido.
     *
     * @return El tool tip asociado al TTextField con el formato "modulo,codigo"
     * @see #setToolTip(String)
     */
    public String getToolTip() {
        return this.codToolTip;
    }

    /**
     * Añade un parametro de entrada, salida o entrada/salida a la funcion de
     * validacion oracle establecida mediante
     * <code>setFuncionValidacion</code>.<br> Normalmente se utiliza dentro de
     * la funcion previa a la validacion. <br>Si se añade por primera vez un
     * parametro a la función es necesario indicarle el numero total de
     * parámetros que requiere mediante el metodo
     * addParametro(Object,String,int,int)
     *
     * @param param Parametro que se desea añadir, puede ser un String o un
     * TTextField
     * @param io Tipo de parametro: entrada "IN", salida "OUT" o entrada/salida
     * "INOUT"
     * @param piNumero Numero de parametro dentro de la funcion oracle siendo
     * "0" el primer parametro
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPreValidacion(java.awt.Component,String)
     * @see #addParametro(Object,String,int,int)
     */
    public void addParametro(Object param, String io, int piNumero) {
        if (piNumero == 0) {

            TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190004")); //"TTextField - addParametro - Error de inicialización. Debe usar la otra opción de addParametro e indicar el total de los mismos");
            setValidate(false);
            hayMensajeError = true;
            return;
        }
        addParametro(param, io, piNumero, _iTotalPara);
    }

    /**
     * Añade un parametro de entrada, salida o entrada/salida a la funcion de
     * validacion oracle establecida mediante setFuncionValidacion.<br>
     * Normalmente se utiliza dentro la funcion previa a la validacion <br>Este
     * método es obligatorio si se añade por primera vez un parametro a la
     * función, el resto de parámetros pueden ser añadidos si se desea mediante
     * addParametro(Object,String,int)
     *
     * @param param Parametro que se desea añadir, puede ser un String o un
     * TTextField
     * @param io Tipo de parametro: entrada "IN", salida "OUT" o entrada/salida
     * "INOUT"
     * @param piNumero Numero de parametro dentro de la funcion oracle siendo
     * "0" el primer parametro
     * @param piTotal Numero total de parametros de la funcion oracle
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPreValidacion(java.awt.Component,String)
     * @see #addParametro(Object,String,int)
     */
    public void addParametro(Object param, String io, int piNumero, int piTotal) {
        //Juan 09012002. Si me envian un nulo (null), le asigno un blanco. Esto supuestamante iba bien
        // Ahora tengo que hacer esto. No se.
        if (param == null) {
            param = " ";
        }
        //    
        if (piNumero == 0) {
            vParametros.clear();
            _iTotalPara = piTotal;
        }
        Object[] datos2 = new Object[2];
        datos2[0] = param;
        datos2[1] = io;

        vParametros.put("" + piNumero, datos2);
    }

    /**
     * Vacia los parámetros asignados a un Campo
     *
     *
     */
    public void inicializaParametros() {
        vParametros.clear();
        _iTotalPara = 0;
    }

    /**
     * Establece los parámetros necesarios para la funcion de validacion oracle
     * establecida. <p>Normalmente no necesitará usar esta función. <p> Use en
     * su lugar
     * <code>addParametro</code>
     *
     * @param pv Hashtable donde la clave de cada parámetro añadido, es su
     * posición en la función de validación, empezando el primer parámetro por
     * 0.
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPreValidacion(java.awt.Component,String)
     * @see #addParametro(java.lang.Object, java.lang.String, int, int)
     * @see #addParametro(java.lang.Object, java.lang.String, int)
     */
    public void setParametros(Hashtable pv) {
        vParametros = pv;
    }

    /**
     * Realiza la validación oracle del componente junto a sus métodos pre y
     * post validacion. Nótese que no se realizan las validacion de tipo del
     * componente (obligatoriedad, númerico, fecha, ...).
     *
     * @true si la validacion es correcta, false si es incorrecta. Se considera
     * que la validacion es incorrecta en los siguientes casos <ul> <li>El
     * <i>Método pre validacion</i> devuelve false <li>La <i>Funcion oracle de
     * validación<i> da una excepción <li>El <i>Método post validacion</i>
     * devuelve false </ul>
     */
    public boolean lanzaValidacionOracle() {
        this.setValidando(true);
        boolean aux = ejecutaMetodo(this.METODO_PREVALIDACION, null)
                && lanzaValidacionOracleInterno()
                && ejecutaMetodo(this.METODO_POSTVALIDACION, null);

        TBarraEstado.setBloqueoBarra(!aux);
        this.setValidando(false);

        return aux;
    }
    //11032002

    public boolean lanzaValidacionOracle(Component p) {
        padreValidacion = p;
        return lanzaValidacionOracle();
    }
    //

    /**
     * Realiza la validacion oracle sin tener en cuenta pre-validacion ni
     * post-validacion
     */
    private boolean lanzaValidacionOracleInterno() {

        if (funcionValidacion.trim().equals("")) {
            return true;
        }
        StringTokenizer token = null;
        SimpleDateFormat form = null;

        String cCadena = "";

        String cSentencia = "";
        Object[] aValores = null;
        String[] aFormatos = null;
        String[] aTipos = null;


        Vector Valores = new Vector();
        Vector Formatos = new Vector();
        Vector Tipos = new Vector();
        Object[] para = null;

        try {
            cSentencia = funcionValidacion;
            for (int liNumCampo = 0; liNumCampo < _iTotalPara; liNumCampo++) {
                para = (Object[]) vParametros.get("" + liNumCampo);
                if (para == null) //En caso de que falta parámetro i-esimo se considera de salida
                {
                    para = new Object[2];
                    para[0] = new String();
                    para[1] = new String("OUT");
                }

                if (para[0] == null) //Para evitar NullPointerException
                {
                    continue; //por aquí no debería pasar nunca
                } //TextField
                else if (para[0] instanceof TTextField) {
                    TTextField txtVal = (TTextField) para[0];
                    if (txtVal.getTipoDato().equals("FECHA")) {
                        Valores.addElement(txtVal.getText());
                        Formatos.addElement((String) para[1]);
                        Tipos.addElement(txtVal.getMascaraFecha());
                    } else {
                        Valores.addElement(txtVal.getText());
                        Formatos.addElement((String) para[1]);
                        Tipos.addElement(null);
                    }
                } //Campo de página 0
                else if (para[0] instanceof String) {
                    if (((String) para[0]).equals("")) {
                        Valores.addElement(cCadena);
                    } else {
                        Valores.addElement((String) para[0]);
                    }
                    Tipos.addElement(null);
                    Formatos.addElement((String) para[1]);
                }
            } // Fin del for

            aValores = new String[Valores.size()];
            aFormatos = new String[Valores.size()];
            aTipos = new String[Valores.size()];


            for (int i = 0; i < Valores.size(); i++) {
                aTipos[i] = (String) Tipos.elementAt(i);


                String temporal = "";

                if (Valores.elementAt(i) != null) {
                    temporal = (String) Valores.elementAt(i);
                }

                aValores[i] = temporal;
                //Herramientas.println("VAlores "+aValores[i]);
                aFormatos[i] = (String) Formatos.elementAt(i);
            }

        } catch (Exception f) {
            //
            //f.printStackTrace();
            setValidate(false);
            hayMensajeError = true;
            ejecutaMetodo(this.METODO_ONERROR, null);
            Herramientas.setCursorNormal();
            return false;
        }

        //Recuperación de datos en la salida de la función
        for (int i = 0; i < _iTotalPara; i++) {
            para = (Object[]) vParametros.get("" + i);
            if (para == null) {
                para = new Object[2];
                para[0] = new String();
                para[1] = new String("OUT");
            }
            if (((String) para[1]).indexOf("OUT") < 0) {
                continue;
            }

            if (para[0] instanceof TTextField) {
                try {
                    TTextField txtVal = (TTextField) para[0];

                    /* Modificacion: 01072992.Juan
                     * El fallo de asignacion de texto no logramos determinar cuando y porque se produce, lo que
                     *                si sabemos cual es exactamente: NullPointeException, el vector aVal se vacia y falla el if que pregunta por su contenido.
                     *                Preguntando previamente por el vector y si esta a nulo volvemos a ejecutar la sentencia, creo resolvemos el problema.
                     */
                    if (aVal == null) {
                        //aVal = SapCon.getConexion().ejecutaSentencia(cSentencia,aFormatos,aValores,aTipos,true);                        
                    }
                    /* Fin modificacion 01072002  */

                    if (aVal[i] != null && !((String) aVal[i]).trim().equals("")) {
                        /*if (txtVal.getTipoDato().equals("FECHA"))
                         {
                         //La máscara que usa la base de datos
                         //es distinta de la que usamos nosotros
                         SimpleDateFormat formato = new SimpleDateFormat("dd-MMM-yyyy", Locale.US); //formato de la base de datos
                         Date fechaReal = formato.parse((String)aVal[i]); //Fecha en formato Date
                         formato = new SimpleDateFormat(txtVal.getMascaraFecha()); //Formato real del text field
                         txtVal.setText(formato.format(fechaReal)); //Fecha en formato String
                         }
                         else*/
                        //{
                        txtVal.setText((String) aVal[i]);
                        //}

                    } else {
                        txtVal.setText("");
                    }
                } catch (Exception e) {
                    TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190005"));//"TTextField - validacionMetodo - Fallo en asignación de texto");
                    Herramientas.trazar("Error en asignación de texto", true);
                    e.printStackTrace();
                    setValidate(false);
                    hayMensajeError = true;
                    ejecutaMetodo(this.METODO_ONERROR, null);
                    return false;
                }
            } else if (para[0] instanceof String) {
                para[0] = aVal[i];
            }
        }

        return true;
    }

    /**
     * Usado internamente del sistema. No use este método.
     *
     * @return true si la validacion es correcta, false en otro caso
     */
    public boolean validacionMetodo() {
        //msg("Va a ejecutar la funcion:" + bEjecutarFuncion);
        if (bEjecutarFuncion == false) {

            bEjecutarFuncion = true;
            return true;
        }
        boolean aux = lanzaValidacionOracleInterno()
                ? ejecutaMetodo(this.METODO_POSTVALIDACION, null)
                : false;
        TBarraEstado.setBloqueoBarra(!aux);
        return aux;
    }

    /**
     * Devuelve un array de objetos que representa los parametros devueltos por
     * la funcion de validacion oracle. <br> Suele utilizarse en el método
     * posterior a la validación para recoger información.
     *
     * @return Parametros devueltos por la funcion de validación oracle.
     * @see #devuelveValor(int)
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPostValidacion(java.awt.Component,String)
     */
    public Object[] devuelveValores() {
        return aVal;
    }

    /**
     * Recupera el parametro i-esimo que devuelve la funcion de validacion
     * oracle.
     *
     * @return Parametro i-esimo generado por la funcion oracle, siendo el 0 el
     * primero
     * @see #devuelveValores()
     * @see #setFuncionValidacion(String)
     * @see #setMetodoPostValidacion(java.awt.Component,String)
     */
    public Object devuelveValor(int i) {
        return aVal[i];
    }

    /**
     * Recupera el valor del programa correspondiente a la seleccion del menu de
     * opciones. Si no seleccionó nada entonces devuelve la cadena vacía "".
     * <br>Suele ser llamado desde el <i>Método posterior al menú de
     * opciones</i> .
     *
     * @see #setMenuOpciones(java.lang.String)
     * @see #setMetodoPostMenu(java.awt.Component,String)
     * @see #getOpcionMenu()
     */
    public String getValorMenuOpciones() {
        String devuelta = Globales.getVariable("devueltaPrograma");
        return devuelta;
    }

    /**
     * Devuelve la opción de menú seleccionada. Si no seleccionó nada entonces
     * devuelve la cadena vacía "". <br>Suele ser llamado desde el <i>Método
     * posterior al menú de opciones</i> .
     *
     * @see #setMenuOpciones(java.lang.String)
     * @see #setMetodoPostMenu(java.awt.Component,String)
     * @see #getValorMenuOpciones()
     */
    public String getOpcionMenu() {
        return Globales.getVariable("devueltaOpcion");
    }

    /**
     * Devuelve el nombre de la opción de menú seleccionada(Campo
     * VAL_MENU_opcion.Tabla G1010131). Si no seleccionó nada entonces devuelve
     * la cadena vacía "". <br>Suele ser llamado desde el <i>Método posterior al
     * menú de opciones</i> .
     *
     * @see #setMenuOpciones(java.lang.String)
     * @see #setMetodoPostMenu(java.awt.Component,String)
     * @see #getValorMenuOpciones()
     */
    public String getNombreOpcionMenu() {
        return Globales.getVariable("devueltaNombreOpcion");
    }

    /**
     * Recupera la lista de valores asociada al campo.
     *
     * @return nombre de la lista de valores con el formato "tabla,version"
     * @see #setListaVal(String)
     */
    public String getListaVal() {
        return listaVal;
    }

    /**
     * Se indica la tabla y versión para la lista de valores del campo.
     * Recuérdese que a las listas de valores se pueden asociar disparadores pre
     * y post lista.
     *
     * @param lista Tabla y version de la lista de valores
     * @see #getListaVal()
     * @see #getValoresLista()
     * @see #setMetodoPreLista(java.awt.Component,String)
     * @see #setMetodoPostLista(java.awt.Component,String)
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public void setListaVal(String lista) {

        listaVal = lista.toUpperCase();
        if (!(listaVal.trim().equals(""))) {
            StringTokenizer tok = new StringTokenizer(listaVal, ",");
            try {
                tablaLista = (String) tok.nextElement();
                codVerLista = (String) tok.nextElement();
            } catch (java.util.NoSuchElementException e) {
                if (!tablaLista.toUpperCase().equals("VACIA")) {
                    Trazar.error("TTXF(3784) Una lista no tiene Versión", "");
                }
            }
        }
        hayLista = true;
        //mostrarIconoLista();
    }

    /**
     * Recupera el menú de opciones establecido para el campo actual.
     *
     * @return Nombre del menú de opciones
     * @see #setMenuOpciones(String)
     */
    public String getMenuOpciones() {
        return menuOpciones;
    }

    /**
     * Vincula al campo actual con un cierto menú de opciones. <br><b>NOTA :</b>
     * No se debe indicar el programa asociado al menú de opciones, ya que este
     * lo coge automáticamente del nombre de la clase.
     *
     * @param Nombre del menu de opciones con el formato "numero de menu,menu
     * defecto"
     * @see #getMenuOpciones()
     * @see #setMetodoPreMenu(java.awt.Component,String)
     * @see #setMetodoPostMenu(java.awt.Component,String)
     * @see #getValorMenuOpciones()
     * @see #getOpcionMenu()
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public void setMenuOpciones(String menu) {

        menuOpciones = menu.toUpperCase();

        if (!(menuOpciones.trim().equals(""))) {
            StringTokenizer tok = new StringTokenizer(menuOpciones, ",");
            String dato1 = tok.hasMoreTokens() ? tok.nextToken() : "";
            String dato2 = tok.hasMoreTokens() ? tok.nextToken() : null;

            if (dato2 == null) //Solo hay número de versión 
            {
                programaMenu = null;
                numMenuOpcion = dato1;
                defectoMenu = "";
            } else {
                programaMenu = dato1;
                numMenuOpcion = dato2;
                defectoMenu = "";
            }
            /*    if(tok.hasMoreTokens())
             {
             programaMenu=(String)tok.nextElement();	            
             }
             if(tok.hasMoreTokens())
             {
             numMenuOpcion=(String)tok.nextElement();	            
             }
             if(tok.hasMoreTokens())
             {
             defectoMenu=(String)tok.nextElement();	            
             }*/
        }
        hayMenu = true;
        //mostrarIconoLista();
    }
    /**
     * Posición X del menú
     */
    private int iXMenu = -1;

    /**
     * Establece la posición X del menú
     *
     * @param piX posicion X
     * @see #getXMenuOpciones()
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public void setXMenuOpciones(int piX) {
        iXMenu = piX;
    }

    /**
     * Recupera la posición X del menú
     *
     * @return posición X del menú
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public int getXMenuOpciones() {
        return iXMenu;
    }
    /**
     * Posición Y del menú
     */
    private int iYMenu = -1;

    /**
     * Establece la posición Y del menú
     *
     * @param piY posicion Y
     * @see #getYMenuOpciones()
     * @see #setXMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public void setYMenuOpciones(int piY) {
        iYMenu = piY;
    }

    /**
     * Recupera la posición Y del menú
     *
     * @return posición Y del menú
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public int getYMenuOpciones() {
        return iYMenu;
    }
    /**
     * Ancho del menú
     */
    private int iAnchoMenu = -1;

    /**
     * Establece ancho del menú
     *
     * @param piAncho ancho del menú
     * @see #getAnchoMenuOpciones()
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public void setAnchoMenuOpciones(int piAncho) {
        iAnchoMenu = piAncho;
    }

    /**
     * Recupera ancho del menú
     *
     * @return ancho del menú
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public int getAnchoMenuOpciones() {
        return iAnchoMenu;
    }
    /**
     * Alto del menú
     */
    private int iAltoMenu = -1;

    /**
     * Establece alto del menú
     *
     * @param piAlto alto del menú
     * @see #getAltoMenuOpciones()
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAnchoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     */
    public void setAltoMenuOpciones(int piAlto) {
        iAltoMenu = piAlto;
    }

    /**
     * Recupera alto del menú
     *
     * @return alto del menú
     * @see #setXMenuOpciones(int)
     * @see #setYMenuOpciones(int)
     * @see #setAltoMenuOpciones(int)
     * @see #setPosicionMenu(String)
     * @see #setAnchoMenuOpciones(int)
     */
    public int getAltoMenuOpciones() {
        return iAltoMenu;
    }
    /**
     * Posición X de la lista.
     */
    private int iXLista = -1;

    /**
     * Establece la posición X de la lista
     *
     * @param piX posicion X
     * @see #getXLista()
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public void setXLista(int piX) {
        iXLista = piX;
    }

    /**
     * Recupera la posición X de la lista
     *
     * @return posición X del menú
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public int getXLista() {
        return iXLista;
    }
    /**
     * Posición Y de la lista.
     */
    private int iYLista = -1;

    /**
     * Establece la posición Y de la lista
     *
     * @param piY posicion Y
     * @see #getYLista()
     * @see #setXLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public void setYLista(int piY) {
        iYLista = piY;
    }

    /**
     * Recupera la posición Y de la lista
     *
     * @return posición Y de la lista
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public int getYLista() {
        return iYLista;
    }
    /**
     * Ancho de la lista.
     */
    private int iAnchoLista = -1;

    /**
     * Establece el ancho de la lista
     *
     * @param piY posicion Y
     * @see #getYLista()
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     */
    public void setAnchoLista(int piAncho) {
        iAnchoLista = piAncho;
    }

    /**
     * Recupera el ancho de la lista
     *
     * @return ancho de la lista
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public int getAnchoLista() {
        return iAnchoLista;
    }
    /**
     * Alto de la lista.
     */
    private int iAltoLista = -1;

    /**
     * Establece el alto de la lista
     *
     * @param piAlto alto de la lista
     * @see #getAltoLista()
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAnchoLista(int)
     */
    public void setAltoLista(int piAlto) {
        iAltoLista = piAlto;
    }

    /**
     * Recupera el alto de la lista
     *
     * @return alto de la lista
     * @see #setXLista(int)
     * @see #setYLista(int)
     * @see #setAltoLista(int)
     * @see #setAnchoLista(int)
     */
    public int getAltoLista() {
        return iAltoLista;
    }

    /**
     * Establece el valor seleccionado de la lista de valores. <br> Uso interno.
     *
     * @param valores valor seleccionado de la lista de valores.
     * @see #getValoresLista()
     */
    public void setValoresLista(String[] valores) {
        valoresLista = valores;
    }

    public void teclaTipeada(java.awt.event.KeyEvent event) {

        //Dejamos que el control del TAB vaya por ControlTeclas
        //01102002.Juan. Incluyo a la tecla ENTER que dejo igualmente su control a COntrolTeclas
        if (event.getKeyChar() == java.awt.event.KeyEvent.VK_TAB
                || event.getKeyChar() == java.awt.event.KeyEvent.VK_ENTER) {
            event.consume();
            return;
        }
        //Control de las mayúsculas
        if (tipoDato.equals("TEXTO") && mayusculas) {
            String Sal = "" + event.getKeyChar();
            event.setKeyChar(Sal.toUpperCase().charAt(0));
        }

        //Control del tipo NUMERICO
        if (tipoDato.equals("NUMERICO")) {

            //Control del signo
            if (!isSigno() && event.getKeyChar() == '-') {
                event.setKeyChar((char) 0);
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190006"));//"El campo numérico se ha definido sin signo");

                return;
            }

            //Control del separador decimal
            if (event.getKeyChar() == Globales.getSepMillar()) {
                event.setKeyChar(Globales.getSepDecimal());
            }
            if (!Character.isDigit(event.getKeyChar())
                    && event.getKeyChar() != '-'
                    && event.getKeyChar() != Globales.getSepDecimal()
                    && (int) event.getKeyChar() != event.VK_TAB
                    && (int) event.getKeyChar() != event.VK_BACK_SPACE
                    && (int) event.getKeyChar() != event.VK_ESCAPE
                    && event.getModifiers() == 0
                    && ultimaTecla != event.VK_ENTER) {
                event.setKeyChar((char) 0);
                //Herramientas.println("Estoy en TYPED "+this.getText());
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190007"));//"En un campo numerico solo se pueden introducir números.");                        
                return;
            }
        }

        TTextField_keyTyped(event);

    }

    /**
     * Auditor de eventos de pulsación de tecla
     */
    class SymKey extends java.awt.event.KeyAdapter {

        public void keyTyped(java.awt.event.KeyEvent event) {
            teclaTipeada(event);
        }

        public void keyPressed(java.awt.event.KeyEvent event) {
            ultimaTecla = event.getKeyCode();
            /*if(event.getKeyChar()==event.VK_ENTER)
             {
             event.consume();
             return;
             }	*/

            //Oculta el menu desplegable, si existe
            if (pop != null) {
                if (pop.isVisible()) {
                    pop.setVisible(false);
                }
            }
            if (reponerMensaje) {
                TBarraEstado.setMensaje(TTextField.this.getToolTipText());
                reponerMensaje = false;
            }


            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_keyPressed(event);
            }
        }
    }

    /**
     * Se lanza al pulsar una tecla sobre el campo. <br> Uso Interno.
     *
     * @param event Evento de tecla
     */
    public void TTextField_keyPressed(java.awt.event.KeyEvent event) {
        //Por desgracia el DELETE no pasa por key typed ni realiza
        //caret update cuando estás en la posición 0.		
        if (event.getKeyChar() == event.VK_DELETE && //pulsa delete
                this.getCaret().getDot() == 0 && //está en la posición 0
                !super.getText().equals("")) //hay algo que borrar
        {
            if (!super.getText().substring(1).equals(valorPrevio)) //Nótese substring(1)
            {
                this.setValidate(false);
            } else {
                this.setValidate(validatePrevio);
            }

        }
        int tecla = ControlTeclas.setTecla(event);

        // 	Trazar.mensaje("Tecla: "+tecla+" es: "+ControlTeclas.CAMPOANT,"GUILLE");
        if (ControlTeclas.controlarTecla(tecla, this)) {
            // 11102004.Juan. Habia problemas con el CAMPOANT en el Generador de Productos.
            //if (tecla!=ControlTeclas.CAMPOANT)       
            event.consume();
        }
    }

    /**
     * Auditor de eventos de movimiento de ratón. Se usa para cambiar el dibujo
     * del puntero del ratón por una mano cuando pasa por encima del triángulo
     * negro de una lista de opciones / valores.
     */
    class SymMouseMotion extends java.awt.event.MouseMotionAdapter {

        /**
         * Se lanza al mover el ratón.
         */
        public void mouseMoved(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_mouseMoved(event);
            }
        }
    }

    /**
     * Se lanza al mover el raton sobre el campo
     *
     * @param event Evento de movimiento de raton
     */
    void TTextField_mouseMoved(java.awt.event.MouseEvent event) {
        /*if (hayLista || soyDescripcionLista || soyCodigoLista)
         {
         mostrarIconoLista();
         } */

        if (reponerMensaje) {
            TBarraEstado.setMensaje(this.getToolTipText());
            reponerMensaje = false;
        }
        if (pulsaListaValores(event)) {
            Cursor miCursor = new Cursor(Cursor.HAND_CURSOR);
            this.setCursor(miCursor);
        } else {
            Cursor miCursor = new Cursor(Cursor.TEXT_CURSOR);
            this.setCursor(miCursor);
        }
    }

    /**
     * Indica los decimales que acepta el campo si este es NUMERICO.
     * <br>Establece la propiedad opcional <i>Numero Decimales</i>, esta
     * propiedad solo es valida si se ha especificado <i>Usar decimales por
     * defecto</i> a false. En caso contrario tomará de Globales el numero de
     * decimales por defecto que tenga el usuario.
     *
     * @param dec Numero de decimales permitidos
     * @see #getNumDecimal()
     * @see #setTipoDato(String)
     * @see #setDecimalesDef(boolean)
     * @see Globales#getNumDec()
     */
    public void setNumDecimal(int dec) {
        if (numDec != dec && !this.getText().equals("")) {
            numDec = dec;
            this.setText(this.getText());
        }
        numDec = dec;
        if (bHayLongitud) {
            setLongitud(datosLongitud);
        }

    }

    /**
     * Recupero el numero de decimales permitidos para el campo.
     *
     * @return Numero de decimales permitidos para el campo
     * @see #setNumDecimal(int)
     */
    public int getNumDecimal() {
        return numDec;
    }

    /**
     * Convierte el contenido del campo a Mayúsculas, en caso de tipo TEXTO. El
     * hecho de que el valor sea false no implica que se convierta a minúsculas,
     * sino que se deja el texto tal cual aparece.
     *
     * @param mayusculas un boolean que indica mayusculas si/no
     * @see #isMayusculas()
     */
    public void setMayusculas(boolean pbMayusculas) {
        this.mayusculas = pbMayusculas;
    }

    /**
     * Metodo que indica si esta en mayusculas si/no.
     *
     * @return Si esta en mayusculas o no
     * @see #setMayusculas(boolean)
     */
    public boolean isMayusculas() {
        return this.mayusculas;
    }
    /**
     * Valores recuperados por la lista de valores
     */
    String[] valoresLista = null;
    /**
     * true si el campo es obligatorio, false de otro modo
     *
     * @see #setObligatorio(boolean)
     */
    protected boolean obligatorio;
    /**
     * Tabla de dependencia para cache
     *
     * @see #setCache(java.lang.String)
     */
    protected String cache = "";
    /**
     * Tabla y version de lista de valores
     *
     * @see #setListaVal(java.lang.String)
     */
    protected String listaVal = "";
    /**
     * Nombre de la tabla de la lista de valores
     */
    String tablaLista = "";
    /**
     * Nombre de la versión de la lista de valores
     */
    String codVerLista = "";
    /**
     * Guarda la longitud visible, minima y maxima
     *
     * @see #setLongitud(java.lang.String)
     */
    protected String datosLongitud = "";
    /**
     * Longitud visible del campo
     */
    protected int longitudVisible = 0;
    /**
     * Longitud real del campo
     *
     * @see #setLongitud(java.lang.String)
     */
    protected int longitudReal = 0;
    /**
     * Longitud minima del campo. Si se introduce un valor en el campo no
     * permite abandonar el mismo mientras que la longitud introducida no sea
     * mayor o igual que la minima
     *
     * @see #setLongitud(java.lang.String)
     */
    protected int longitudMinima = 0;
    /**
     * Numero de decimales permitidos
     *
     * @see #setNumDecimal(int)
     */
    protected int numDec;
    /**
     * Valor de la propiedad <i>Usar decimales por defecto</i>. Un valor de true
     * indica que el número de decimales se extrae de la clase Globales. Un
     * valor de false indica que el número se extrae de la propiedad <i>Número
     * de decimales por defecto<i>
     */
    private boolean decimalesDef = false;
    /**
     * Funcion de validacion oracle
     *
     * @see #setFuncionValidacion(java.lang.String)
     */
    protected String funcionValidacion = "";
    /**
     * Codigo del tool tip
     *
     * @see #setToolTip(java.lang.String)
     */
    protected String codToolTip = "";
    /**
     * Mascara de la fecha
     *
     * @see #setMascaraFecha(java.lang.String)
     */
    protected String mascaraFecha = "";
    /**
     * Tipo del campo. Puede ser "NUMERO", "TEXTO" o "FECHA"
     *
     * @see #setTipoDato(java.lang.String)
     */
    protected String tipoDato = "TEXTO";
    /**
     * Si es true, el texto introducido se pasa automaticamente a mayusculas
     *
     * @see #setMayusculas(boolean)
     */
    protected boolean mayusculas = true;
    /**
     * Sin uso
     */
    protected String parametros = "";
    /**
     * Sin uso
     */
    protected boolean rellenar = false;
    /**
     * Nombre del campo para consultar la ayuda
     *
     * @see #setNombreCampo(java.lang.String)
     */
    protected String nombreCampo = "";
    /**
     * Documento a partir del cual se crea el campo
     */
    private PlainDocument _myDoc = null;
    /**
     * Auditor de eventos de documento
     */
    private DocumentListener _docListener = null;
    /**
     * Nombre del documento
     */
    private Document _document = null;
    /**
     * Indica si el auditor de eventos está o no activado
     */
    private boolean _docListenerDisabled = false;
    /**
     * Control del signo. Un valor de false indica que no se mostrará signo el
     * un campo numérico
     */
    private boolean _signo = false;
    /**
     * Control de la puntuación. Un valor de false indica que no se mostrará
     * puntuación de milllares en un campo numérico
     */
    private boolean _puntuacion = false;
    /**
     * Ventana (TFrameo o TDialog) en donde se encuentra el campo.
     */
    private java.awt.Component _padre = null;
    /**
     * Nombre del método post campo
     */
    private String _metodoPostCampo = "";
    /**
     * Nombre del método pre campo
     */
    private String _metodoPreCampo = "";
    /**
     * Nombre del método de control de teclas
     */
    private String _metodoTeclas = "";
//    private GrupoTarjetas     oGrupoTarjetas=null;
    /**
     * Posición del menú de opciones
     */
    protected String posicionMenu = "";
    /**
     * Nombre del método pre validación
     */
    private String _metodoPreValidacion = "";
    /**
     * Nombre del método post validación
     */
    private String _metodoPostValidacion = "";
    /**
     * Nombre del método on eror
     */
    private String _metodoOnError = "";
    /**
     * Nombre del método pre menú
     */
    private String _metodoPreMenu = "";
    /**
     * Nombre del método post menú
     */
    private String _metodoPostMenu = "";
    /**
     * Controla si se permite salir o no del campo
     */
    private boolean _permiteSalida = false;
    /**
     * Controla si en caso de ser nulo el valor del campo hay que validar o no
     */
    private boolean _validaNulo = true;
    /**
     * Nombre del método post lista
     */
    private String _metodoPostLista = "";
    /**
     * Nombre del método pre lista
     */
    private String _metodoPreLista = "";
    /**
     * true iff no mandatory filters are empty
     */
    private boolean _dataComplete = true;
    /**
     * true if this component has input focus
     */
    private boolean _haveFocus = false;
    /**
     * true if a key is down
     */
    private boolean _keyPressed = false;
    /**
     * true if this component has activity
     */
    private boolean _activity = false;
    /**
     * position of first filter
     */
    private int _firstInputPos = 0;
    /**
     * Auditor de eventos de foco
     */
    private FocusAdapter _focusListener = null;
    /**
     * Guarda los parámetros de la función de validación
     */
    private Hashtable vParametros = new Hashtable();
    /**
     * Número total de parámetros
     */
    private int _iTotalPara = 0;
    /**
     * Controla si hay que mostrar información de debug del campo
     */
    private boolean debug = false;
    /**
     * Panel de validación en bloque
     */
    private javax.swing.JPanel panelGrupo = null;
    /**
     * Valores recuperados de la lista de valores
     */
    Object[] aVal = null;
    //private boolean paso=false;

    /**
     * Auditor de eventos de cursor de escritura
     */
    class SymCaret implements javax.swing.event.CaretListener {

        /**
         * Se lanza cuando cambia el cursor de escritura. Por ejemplo cuando
         * cambia su posición
         */
        public void caretUpdate(javax.swing.event.CaretEvent event) {
            validacionTemporal = true;
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_caretUpdate(event);
            }
            validacionTemporal = false;
        }
    }

    /**
     * Se lanza al marcar sobre el campo
     *
     * @param event Evento de modificacion de la marca
     */
    void TTextField_caretUpdate(javax.swing.event.CaretEvent event) {
        //if (getText().equals(""))
        //{
        //    validacionBloques.validaBloque(this.panelGrupo,this);
        //}	
        Vector lBarra = TBarraHerramientas.getBarrasHerramientas((Container) getPadre());
        for (int x = 0; x < lBarra.size(); x++) {
            ((TBarraHerramientas) lBarra.elementAt(x)).refrescarBarra(this);
        }
        // Aquí comprobar cambio de campo para setValidate()
        if (!super.getText().equals(valorPrevio)) {
            msg("ha cambiado [" + super.getText() + "] != [" + valorPrevio + "]");
            this.setValidate(false);
        } else {
            msg("ha recuperado");
            this.setValidate(validatePrevio);
        }
    }

    /**
     * Se lanza al escribir sobre el campo
     *
     * @param event Evento de escritura
     */
    void TTextField_keyTyped(java.awt.event.KeyEvent event) {
        if (this.isValidando()) {
            event.consume();
        }
        /*
         int tecla =ControlTeclas.setTecla(event);
         if (ControlTeclas.controlarTecla(tecla,this))
         {
         event.consume();
         return;
         }
         */

        //Aquí controla la pulsación de borrar para que no 
        //interfiera con las comprobaciones de fecha y número
        if (event.getKeyChar() != event.VK_BACK_SPACE && event.getKeyChar() != event.VK_ENTER) {
            // En tipos de dato fecha solo permitimos letras o numeros.
            if (this.tipoDato.equals("FECHA")) {
                if (!Character.isDigit(event.getKeyChar())
                        || Character.isLetter(event.getKeyChar())) {
                    event.setKeyChar((char) 0);
                    TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190008"));//"En tipos de dato fecha solo permitimos numeros o el separador '-'.");

                    return;
                }
            }

            // Aqui se compueba que no se supera la longitud real del campo.
            //Primero se mide el tamaño del texto seleccionado, si lo hay, para restarlo.
            int liSel = 0;
            try {
                liSel = this.getSelectedText().length();
            } catch (NullPointerException e) {
            }

            if ((super.getText().length() - liSel) >= longitudReal) {
                event.setKeyChar((char) 0);
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190009"));//"Superada la longitud real del campo.");

                return;
            }
            // Aqui se impide introducir cualquier valor en los campos numéricos antes del
            // signo negativo.

            if (this.tipoDato.equals("NUMERICO")
                    && super.getText().indexOf('-') > (-1)
                    && this.getSelectionStart() == 0) {
                event.setKeyChar((char) 0);
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190010"));//"No se puede introducir un valor en los campos numéricos antes del signo negativo.");

                return;
            }

            // Aqui se impide poner el signo negativo en cualquier posición 
            // que no sea la primera.
            if (this.tipoDato.equals("NUMERICO")
                    && event.getKeyChar() == '-'
                    && this.getSelectionStart() != 0) {
                event.setKeyChar((char) 0);
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190011"));//"No se puede poner el signo negativo en cualquier posición que no sea la primera.");

                return;
            }

            // Aqui se impiide introducir más numeros decimales de los permitidos.
            if (this.tipoDato.equals("NUMERICO")) {
                //int coma=super.getText().indexOf(','); 
                int coma = super.getText().indexOf(Globales.getSepDecimal());
                //Aqui se impide meter más enteros que el total menos los decimales
                if (getNumDecimal() > 0 && coma < 0 && Character.isDigit(event.getKeyChar())) {
                    if ((super.getText().length() - liSel) >= (longitudReal - getNumDecimal()) - 1)//-1 por la coma 
                    {
                        event.setKeyChar((char) 0);
                        TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190009"));//"Superada la longitud real del campo.");

                        return;
                    }
                }


                int maxDec = getDecimalesDef() ? Globales.getNumDec() : getNumDecimal();
                if (coma > (-1)
                        && this.getCaret().getDot() > coma
                        && this.getCaret().getDot() == this.getCaret().getMark()
                        && (super.getText().length() - coma) > maxDec) {
                    event.setKeyChar((char) 0);
                    TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190012"));//"No se puede introducir más numeros decimales de los permitidos.");

                    return;
                }
            }

            // Aqui se impide volver a poner una coma decimal si ya hay otra.
            if (this.tipoDato.equals("NUMERICO")
                    && event.getKeyChar() == Globales.getSepDecimal()
                    && super.getText().indexOf(Globales.getSepDecimal()) > (-1)) {
                event.setKeyChar((char) 0);
                TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190013"));//"Ya hay una coma decimal. No se puede escribir otra.");

                return;
            }
            /* //Tranferido a la validación del campo durante la salida
             // Aqui se impide sobrepasar el rango maximo o minimo de un dato numérico
             if (this.tipoDato.equals("NUMERICO") && Character.isDigit(event.getKeyChar()))
             {
             //msg("super.getText()="+super.getText()+event.getKeyChar());
             double valor=(new Double(super.getText()+event.getKeyChar())).doubleValue();
             if (valor>this.getNumMaximo())
             {
             event.setKeyChar((char) 0);
             TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn","10190014"));//"El valor númerico no puede sobrepasar el valor máximo establecido.");
                    
             return;
             }
             if (valor<this.getNumMinimo())
             {
             event.setKeyChar((char) 0);
             TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn","10190015"));//"El valor númerico no puede ser inferior al valor mínimo establecido.");
                    
             return;
             }            
             }
             */
        }

        // Aqui, ejecutar Trigger de Teclas siempre que sea una de función.
        //(NADA)
    }

    /**
     * Auditor de eventos de acción
     */
    class SymAction implements java.awt.event.ActionListener {

        /**
         * Se lanza cuando se pulsa acción sobre el campo. Usualmente al pulsar ENTER
         */
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == TTextField.this) {
                TTextField_actionPerformed(event);
            }
        }
    }

    /**
     * Se lanza cuando el campo captura un evento de ejecucion de accion, por
     * ejemplo al pulsar enter sobre el mismo.
     *
     * @param event Evento de ejecucion de accion
     */
    void TTextField_actionPerformed(java.awt.event.ActionEvent event) {
        // to do: code goes here.
        /*if (hayLista || soyDescripcionLista || soyCodigoLista)
         {
         mostrarIconoLista();
         } */
    }

    /**
     * Se lanza al hacer clik sobre el campo
     *
     * @param event Evento de clik del raton
     */
    void TTextField_mouseClicked(java.awt.event.MouseEvent event) {
        //pulsadoTab = false;

        //msg("foco en "+((TTextField)(this.getPadre().getComponenteActual())).getNombreCampo());
        //msg("estoy en "+this.getNombreCampo());

        //Juan.29102003. He modificado este if. Ahora si el campo es Enabled (habilitado) si se 
        //               ejecutara el dobleclick al igual que lo hace la tecla.
        if (this.isEnabled() == false || this.isEditable() == false) {
            if (this.isEnabled() == true && this.isEditable() == false) {
                //20563-COMPONENTE INHABILITADO
                TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20563"));
                if (event.getClickCount() > 1) {
                    ControlTeclas.controlarTecla(ControlTeclas.DOBLE_CLICK, this);
                    event.consume();
                }
            } else {
                TBarraEstado.setMensajeError(Globales.leeCodigoMensaje("trn", "20563"));
            }
            return;
        } //Fin 29102003
        else if (event.getClickCount() > 1) {
            ControlTeclas.controlarTecla(ControlTeclas.DOBLE_CLICK, this);
            event.consume();
        } else if (pulsaListaValores(event)) {
            if (getPadre().getComponenteActual() == this) {
                ControlTeclas.controlarTecla(ControlTeclas.LISTA, this);
            }
            event.consume();
        }
        //msg("TTextField - mouseClicked");
        validacionBloques.validaBloque(this.panelGrupo, this);
        //hayRaton=true;
    }

    /**
     * A partir de la posición del ratón nos dice si se puede pulsar el ratón
     * para mostrar una lista de valores / opciones
     */
    private boolean pulsaListaValores(java.awt.event.MouseEvent e) {
        return hayLista
                && e.getX() >= this.getWidth() - 10
                && e.getX() <= this.getWidth()
                && e.getY() >= this.getHeight() - 10
                && e.getY() <= this.getHeight();
    }

    public int getHeight() {
        //La altura de los campos de texto es fija a 20.
        return 20;//super.getHeight();
    }

    /**
     * Recupera el nombre de campo establecido con setNombreCampo()
     *
     * @return Nombre del campo
     * @see #setNombreCampo(java.lang.String)
     */
    public String getNombreCampo() {
        return nombreCampo;
    }

    /**
     * Esta propiedad, de tipo opcional, se utiliza para facilitar la
     * localización de la ayuda sobre el campo en que se utiliza o la asignación
     * de preferencias a modo de valores por defecto. Se debe indicar el
     * programa (o módulo, o contexto, etc.) al que se quiere referenciar. Hay
     * que tener en cuenta que siempre aparecerá la misma información de ayuda o
     * idéntico contenido como preferencia para los campos con el mismo valor en
     * esta propiedad.
     *
     * @param nomC Nombre del campo
     * @see #getNombreCampo()
     */
    public void setNombreCampo(String nomC) {
        nombreCampo = nomC;
    }

    /**
     * Deshace campos introducidos en el campo desde la ultima entrada
     *
     * public void undo() { this.setText(valorInicial); }
     */
    /**
     * Deshace cambios introducidos en el campo desde la ultima entrada.
     *
     * @return true
     */
    public boolean ejecutaDescartar() {
        this.setText(valorInicial);
        return true;
    }

    /**
     * Esta propiedad se utiliza para recuperar información obtenida a traves
     * del método de validación. <br> Para recuperar información de la función
     * de validación se hará una llamada a devuelveValores() o
     * devuelveValor(int) <p><B>NOTA: </b>Este método no tendrá efecto si se
     * pasa como segundo argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre Objeto propietario del metodo
     * @param pmMetodo Nombre del metodo que se ejecutara en "padre"
     * @see #setMetodoPreValidacion(java.awt.Component,String)
     * @see #setFuncionValidacion(String)
     * @see #devuelveValores()
     * @see #devuelveValor(int)
     */
    public void setMetodoPostValidacion(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }
        this._padre = padre;
        this._metodoPostValidacion = pmMetodo;
    }

    /**
     * @deprecated Usar <a href="#setMetodoPostValidacion(java.awt.Component,
     * java.lang.String)"> setMetodoPostValidacion(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoPostValidacion(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostValidacion() {
    }

    /**
     * setMetodoPostValidacion(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoPostValidacion(java.awt.Component, java.lang.String)
     */
    public void setMetodoPostValidacion(String pmMetodo) {
        this._metodoPostValidacion = pmMetodo;
    }

    /**
     * Devuelve el nombre del metodo previo a la validacion oracle
     *
     * @return Nombre del metodo
     */
    public String getMetodoPostValidacion() {
        if (this._metodoPostValidacion == null) {
            return "";
        }
        return this._metodoPostValidacion;
    }

    /**
     * Establece el TTextField como código de la lista de una opciones. <br>Uso
     * interno.
     *
     * @param lis Lista de opciones asociada al TTextField
     * @see #setDescripcionListaOpciones(ListaOpciones)
     * @see mapfre.com.c.GUI.ListaOpciones
     */
    public void setCodigoListaOpciones(ListaOpciones lis) {
        listaOpc = lis;
        soyCodigoLista = true;
    }

    /**
     * Establece el TTextField como descripcion de la lista de una opciones.
     * <br>Uso interno.
     *
     * @param lis Lista opciones asociada al TTextField
     * @see #setCodigoListaOpciones(ListaOpciones)
     * @see mapfre.com.c.GUI.ListaOpciones
     */
    public void setDescripcionListaOpciones(ListaOpciones lis) {
        listaOpc = lis;
        soyDescripcionLista = true;
    }

    /**
     * Muestra el icono de la lista de opciones / valores. El icono consiste en
     * una pestaña en la esquina inferior derecha que puede ser pulsado por el
     * usuario. Al pulsarlo se activa el método de control de la tecla LISTA.
     * <br> Uso interno
     *
     * @see ControlTeclas#LISTA
     */
    //public void mostrarIconoLista(Graphics g2) 
    private void mostrarIconoLista(Graphics g2) {
        Polygon polygon = new Polygon();


        polygon.addPoint(this.getWidth(), this.getHeight() - 10);
        polygon.addPoint(this.getWidth() - 10, this.getHeight());
        polygon.addPoint(this.getWidth(), this.getHeight());

        g2.fillPolygon(polygon);

        hayLista = true; //Para que sea pulsable        
    }

    /**
     * Busca recursivamente un menú de opciones en los contenedores superiores
     * hasta llegar al TFrame / TDialog inicial
     */
    private void miraPadresMenu() {
        Object papa = this;
        boolean hayMenuPapa = false;
        while (true) {
            if (papa == null) {
                break;
            } else {
                if (papa instanceof Component) {
                    papa = ((Component) papa).getParent();
                }
            }


        }//fin while        
    }

    /**
     * Establece la posición actual del menú de opciones. <br> Situa el menú de
     * opciones al Norte, Sur, Este u Oeste del TTextField.
     *
     * @param pos Opcion elegida de la propiedad <i>Posición Menú Opciones</i>
     * @see #getPosicionMenu()
     * @see #setMenuOpciones(String)
     */
    public void setPosicionMenu(String pos) {
        posicionMenu = pos;
    }

    /**
     * Recupera la posición actual del menú de opciones respecto el TTextField.
     *
     * @return La posicion actual de el menú de opciones
     * @see #setPosicionMenu(String)
     */
    public String getPosicionMenu() {
        return posicionMenu;
    }

    /**
     * Metodo que se dispara cuando se produce un error en el componente, por
     * ejemplo en una validacion oracle. <p><B>NOTA: </b>Este método no tendrá
     * efecto si se pasa como segundo argumento la cadena vacía o
     * <code>null</code>.
     *
     * @param padre Objeto propietario del metodo
     * @param pmMetodo Nombre del metodo de error que se ejecutara en "padre"
     * @see #getMetodoOnError()
     */
    public void setMetodoOnError(java.awt.Component padre, String pmMetodo) {
        if (pmMetodo == null || pmMetodo.trim().equals("") || pmMetodo.trim().equals("null")) {
            return;
        }
        this._padre = padre;
        this._metodoOnError = pmMetodo;
    }

    /**
     * @deprecated Usar <a href="#setMetodoOnError(java.awt.Component,
     * java.lang.String)"> setMetodoOnError(java.awt.Component,
     * java.lang.String)</a>
     * @see #setMetodoOnError(java.awt.Component, java.lang.String)
     */
    public void setMetodoOnError() {
    }

    /**
     * setMetodoOnError(java.awt.Component, java.lang.String)</a>
     *
     * @see #setMetodoOnError(java.awt.Component, java.lang.String)
     */
    public void setMetodoOnError(String pmMetodo) {
        this._metodoOnError = pmMetodo;
    }

    /**
     * Devuelve el nombre del metodo previo a la validacion oracle
     *
     * @return Nombre metodo de Error
     * @see #setMetodoOnError(java.awt.Component, java.lang.String)
     */
    public String getMetodoOnError() {
        if (this._metodoOnError == null) {
            return "";
        }
        return this._metodoOnError;
    }

    /**
     * Simula la pulsacion de una tecla. <br>Uso interno
     *
     * @param event evento de pulsacion de tecla
     */
    public void procesarTecla(java.awt.event.KeyEvent event) {
        //msg("antes procesarTecla");
        TTextField_keyPressed(event);
        //msg("despues procesarTecla");
    }

    /**
     * Procesa la pulsacion de una tecla
     *
     * @deprecated Usar ControlTeclas.controlarTecla
     */
    public void ejecutarTecla(java.awt.event.KeyEvent event) {
        //msg("ejcurtarTecla");
        /*!!--
         if (pop!=null)
         {
         if (pop.isVisible())
         {
         pop.setVisible(false);
         }
         }
		    
         //Lo primero, verificar si es tecla de funcion
		    
         if (ejecutaMetodo(TTextField.METODO_TECLAS,new Integer(event.getKeyCode())))
         {
         return;
         }

         int modificadores  =  (event.getModifiers() & event.SHIFT_MASK)== 0 ? 0 : 1;
         modificadores +=  (event.getModifiers() & event.CTRL_MASK) == 0 ? 0 : 2;
         modificadores +=  (event.getModifiers() & event.ALT_MASK)  == 0 ? 0 : 4;
		        
         int tecla = ControlTeclas.setTecla(event.getKeyCode(),modificadores);

         if (tecla == ControlTeclas.AYUDA)
         {
         ControlTeclas.teclaAyuda(nombreCampo);
         }
         else
         if (tecla == ControlTeclas.SALIR)
         {
         msg("ejcutarTecla F3");
         ControlTeclas.teclaSalir();
         }
         else
         if (tecla == ControlTeclas.MMENSAJES)
         {
         ControlTeclas.teclaShowErrors();
         }
	        
		    
         Object object = event.getSource();
         if (object == TTextField.this)
         TTextField_keyPressed(event);
         */
    }
    /**
     * Indica que actualmente se está ejecutando un menú de opciones
     */
    boolean ejecutandoMenu = false;

    /**
     * Ejecuta el menu de opciones asociado al campo de texto. Es llamado por
     * ControlTeclas cuando se pulsa la tecla asociada al Menu de Opciones
     *
     * @return true al ejecutarse el metodo
     * @see #setMenuOpciones(String)
     */
    public boolean ejecutaMenuOpciones() {

        if (ejecutandoMenu) {
            return false;
        }
        ejecutandoMenu = true;

        //msg("EL PROGRAMA-MENU ES "+programaMenu);
        //if (Globales.getComponenteActual()!=this)
        if (getPadre().getComponenteActual() != this) {
            ejecutandoMenu = false;
            return false;
        }
        if (!(menuOpciones.equals(""))) {

            if (programaMenu == null) {
                String clase = getPadre().getClass().getName().toUpperCase();
                programaMenu = clase.substring(clase.lastIndexOf(".") + 1, clase.length());
                conProgramaMenu = false;
            } else {
                conProgramaMenu = true;
            }

            ejecutaMetodo(this.METODO_PREMENU, null);
            //
            ejecutaMetodo(this.METODO_POSTMENU, null);
        } else {
            miraPadresMenu();
            //ControlTeclas.controlarTecla(ControlTeclas.MENUOP,this.getParent());
        }
        ejecutandoMenu = false;
        return true;
    }

    /**
     * Abre ventana de gran edición al pulsar F6. Llamado por ControlTeclas
     *
     *
     * @return true si se seleccionó gran edición
     * @see #setGranEdicion(boolean)
     */
    public boolean ejecutaGranEdicion() {
        if (bGranEdicion) {
        }
        return bGranEdicion;
    }

    /**
     * Guarda el contenido del text-field como autovalor. <br> Solo se
     * almacenará el autovalor si se ha definido la propiedad <i>Nombre del
     * Campo</i> .
     *
     * @return true si se ejecuto correctamente
     * @see #setNombreCampo(String)
     * @see #ponAutoValor()
     */
    public boolean ejecutaAutoValor() {

        return true;
    }

    /**
     * Fuerza a que muestre el autovalor, si lo tiene. Este método se ejecuta
     * automáticamente la primera vez que el campo recoge el foco. <br> Solo se
     * almacenará el autovalor si se ha definido la propiedad <i>Nombre del
     * Campo</i> .
     *
     * @see #setNombreCampo(String)
     * @see #ejecutaAutoValor()
     */
    public void ponAutoValor() {
        String autovalor = "";
        if (autovalor != null) {
            this.setText(autovalor);
            this.selectAll();
        }
    }

    public void ponAutoValor(int i) {
        String autovalor = "" + i;
        if (autovalor != null) {
            this.setText(autovalor);
            this.selectAll();
        }
    }

    /**
     * Muestra la lista de valores asociada al campo.
     *
     * @return true si se ejecuto correctamente
     * @see #setListaVal(String)
     */
    public boolean ejecutaListaValores() {
        //if (Globales.getComponenteActual()!=this)
        if (bEjecutandoLista) {
            return false;
        }
        bEjecutandoLista = true;

        if (listaOpc != null) {
            return true;
        } else {
            if (Herramientas.nvl(listaVal) != "") {
                ejecutaMetodo(this.METODO_PRELISTA, null);
                if (listaVal.equals("VACIA")) {
                    bEjecutandoLista = false;
                    return true;
                }


                /* Si el ttextfield tiene metodopostlista se ejecutará en caso 
                 de que no tenga, por defecto traemos el codigo de la lista
                 sobre el que estemos posicionados.
                 */
                if (this._metodoPostLista == null
                        || this._metodoPostLista == "") {
                    String dato = Globales.getVariable("TListaValores.VALOR_LISTA");
                    this.setText(dato);
                } else {
                    ejecutaMetodo(this.METODO_POSTLISTA, null);
                }
                bEjecutandoLista = false;
                return true;
            }
            bEjecutandoLista = false;
        }
        return false;

    }

    /**
     * Realiza la acción de la pulsación de doble-click. Por defecto no hace
     * nada.
     *
     * @return false
     */
    public boolean ejecutaDobleClick() {
        return false;
        /*        return (getPadre().getComponenteActual()==this)?
         ejecutaListaValores():
         false;*/
    }

    /**
     * Recupera el valor de la propiedad <i>Usar decimales por defecto</i>
     *
     * @return valor de la propiedad <i>Usar decimales por defecto</i>
     * @see #setDecimalesDef(boolean)
     */
    public boolean getDecimalesDef() {
        return decimalesDef;
    }

    /**
     * Establece el valor de la propiedad <i>Usar decimales por defecto</i>.
     * <p>Esta propiedad permite extraer de la clase Globales el número de
     * decimales que serán mostrados.
     *
     * @see Globales#getNumDec()
     */
    public void setDecimalesDef(boolean pb) {
        if (decimalesDef != pb && !this.getText().equals("")) {
            decimalesDef = pb;
            this.setText(this.getText());
        }
        decimalesDef = pb;
        if (bHayLongitud) {
            setLongitud(datosLongitud);
        }
        //this.setNumDecimal(0);  //Ver setNumDecimal para mas informacion
    }

    /**
     * Lanza el evento de focus lost sobre el componente. Solo utilizado
     * internamente por los componentes.
     */
    public void lanzaFocusLost() {
        FocusEvent fe = new FocusEvent(this, FocusEvent.FOCUS_LOST, false);
        processEvent(fe);
    }

    /**
     * Lanza las validaciones necesarias antes de perder el foco. <br> Uso
     * interno.
     */
    public boolean ejecutaCampoSig() {
        //Antes de salir del campo comprobamos la validacuón 
        System.out.println(getInputVerifier());
        //System.out.println(getInputVerifier().verify(this));
        if (this.getInputVerifier() == null || this.getInputVerifier().verify(this)) {
            InputVerifier inputVerifier = this.getInputVerifier();

            this.setInputVerifier(null);
            if (getNextFocusableComponent() == this) {
                //Si el salto es circular forzamos el focus lost y el focus gained
                FocusEvent fe = new FocusEvent(this, FocusEvent.FOCUS_LOST, false);
                processEvent(fe);
                fe = new FocusEvent(this, FocusEvent.FOCUS_GAINED, false);
                processEvent(fe);
            }
            if (getNextFocusableComponent() != null) //¿navegación del foco no-estandar?
            {
                //antes de hacer un salto no-estandar validamos el bloque intermedio		        
                if (this.panelGrupo == null || this.validacionBloques.validaBloque(
                        this.panelGrupo,
                        getNextFocusableComponent())) {

                    getNextFocusableComponent().requestFocus();
                }
            } else //El manejo del foco será el estandar
            {
                this.transferFocus();
            }
            this.setInputVerifier(inputVerifier);
        }

        return true; //Consumimos la tecla de campo siguiente
    }

    /**
     * Marcha al campo anterior, SIN lanzar validaciones. <br>Uso interno.
     *
     *
     */
    public boolean moverLeft() {
        javax.swing.FocusManager.getCurrentManager().focusPreviousComponent(this);
        return true; //Consumimos la tecla!
    }
    /**
     * GDIEZ 28092000
     *
     * Realiza la accion BKSPACE que salta de campo si el campo actual es vacio
     *
     * @return false para comportamiento por defecto, true para cambiar de campo
     *
     * public boolean ejecutaCampoAnt_vacio() { if
     * (!(super.getText().trim().equals(""))) { saltaAtras=false; return false;
     * } if (panelGrupo!=null) { saltaAtras=true; Component
     * res=validacionBloques.sacaAnterior((TPanel)this.panelGrupo, this); if
     * (res!=null) { _permiteSalida=true; res.requestFocus(); } } return true;
     *
     * }
     *
     */
    /**
     * Ventana en donde está insertado el componente
     */
    TVentana ventanaPadre = null;

    /**
     * Recupera la ventana en donde está insertado el componente.
     *
     * @return Ventana en donde está insertado el componente
     */
    public TVentana getPadre() {
        if (ventanaPadre == null) {
            Component p = Globales.getPadre(this);
            if (p instanceof TVentana) {
                if (p != null) {
                    ventanaPadre = (TVentana) p;
                }
            }

        }

        return ventanaPadre != null ? ventanaPadre : ventanaDefecto;
    }
    //=================================================================

    /**
     * Recupera si el campo permite copiar su contenido en el portapapeles.
     *
     * @return true si se permite copiar el contenido al portapapeles, false en
     * otro caso
     * @see #puedeCortar()
     * @see #puedePegar()
     * @see #puedeDescartar()
     */
    public boolean puedeCopiar() {
        return getCaret().getMark() != getCaret().getDot();
    }

    /**
     * Recupera si el campo permite cortar su contenido al portapapeles.
     *
     * @return true si se permite cortar el contenido al portapapeles, false en
     * otro caso
     * @see #puedeCopiar()
     * @see #puedeDescartar()
     * @see #puedePegar()
     */
    public boolean puedeCortar() {
        return isEditable() && puedeCopiar();
    }

    /**
     * Recupera si el campo permite pegar su contenido al portapapeles.
     *
     * @return true si se permite pegar el contenido al portapapeles, false en
     * otro caso
     * @see #puedeCortar()
     * @see #puedeCopiar()
     * @see #puedeDescartar()
     */
    public boolean puedePegar() {
        Clipboard clip = java.awt.Toolkit.getDefaultToolkit().getSystemClipboard();
        return clip.getContents(this) != null;
    }

    /**
     * Recupera si el campo permite descartar cambios
     *
     * @return true si ha habido cambios desde que se entró, false en otro caso
     * @see #puedeCortar()
     * @see #puedeCopiar()
     * @see #puedePegar()
     */
    public boolean puedeDescartar() {
        return !super.getText().equals(this.valorInicial);
    }

    /**
     * Recupera si el campo es un TTextField.
     *
     * @return true
     */
    public boolean esTTextField() {
        return true;
    }

    /**
     * Indica si el campo tiene algún autovalor asociado.
     *
     * @return True si se ha definido la propiedad <i>Nombre del Campo</i>,
     * false en otro caso
     * @see #ejecutaAutoValor()
     * @see #setNombreCampo(String)
     */
    public boolean tieneAutoValor() {
        return nombreCampo != null && !nombreCampo.trim().equals("");
    }

    /**
     * Indica si el campo tiene alguna Lista de Valores asociada.
     *
     * @return True si se ha definido la propiedad <i>Tabla y Versión Lista de
     * Valores</i>, false en otro caso
     * @see #ejecutaListaValores()
     * @see #setListaVal(String)
     */
    public boolean tieneLista() {
        return hayLista || listaOpc != null;
    }

    /**
     * Indica si el campo tiene algún Menú de Opciones asociado.
     *
     * @return True si se ha definido la propiedad <i>Tabla y Versión Lista de
     * Valores</i>, false en otro caso
     * @see #ejecutaMenuOpciones()
     * @see #setMenuOpciones(String)
     */
    public boolean tieneMenuOpciones() {
        Component papa = this;

        boolean salir = !menuOpciones.equals("");
        boolean hayMenuPapa = salir;

        while (!salir) {
            //Ir al padre si se puede
            if (papa == null) {
                salir = true; //Raiz: salir del bucle
            } else {
                papa = papa.getParent();
            }


        }
        return hayMenuPapa;

    }

    /**
     * Recupera si el campo tiene la propiedad de <i>Gran edición</i>
     * habilitada.
     */
    public boolean tieneGranEdicion() {
        return bGranEdicion;
    }
    /**
     * Indica el modo de traza. Un valor de true significa que hay que mostrar
     * información de traza
     */
    private boolean bDebug = false;

    /**
     * Activa y desactiva el modo de traza. Cuando está en modo de traza el
     * componente muestra un trazado de su ejecución a través de la salida
     * estandar. <p>Por defecto se considera que no está en modo de traza
     *
     * @param b true para activar el modo de traza, false para desactivar
     */
    public void setDebug(boolean b) {
        bDebug = b;
    }

    /**
     * Método de traza
     */
    private void msg(String s) {
        if (bDebug) {
            
        }
    }
    /**
     * Ancho medio de la fuente
     */
    private final int ANCHO_FUENTE = 10 + 1;

    /**
     * Sobreescrito para tener en cuenta los posibles tipos de anchura del componente
     */
    protected int getColumnWidth() {
        int salida = 8;
        if (getTipoAnchura().equals(ANCHURA_ESTANDAR)) {
            salida = super.getColumnWidth();
        } else if (getTipoAnchura().equals(ANCHURA_NUMEROS)) {
            salida = 7;
        } else if (getTipoAnchura().equals(ANCHURA_MODELO)) {
        }
        return salida;
    }

    //private static final Insets myInsets = new Insets(2,0,2,0);
    /**
     * Establece que hay un mensaje de error asociado al campo
     */
    protected void setHayMensajeError(boolean b) {
        hayMensajeError = b;
    }
    /* Juan 28112001
     * Metodo privado para pintar los dientes del campo roto
     *
     */

    private void mostrarCampoRotoR(Graphics g2) {
        // Creo un poligono vacio
        Polygon polygon = new Polygon();

        // Establezco los puntos del poligono 
        polygon.addPoint(this.getWidth(), 0);
        polygon.addPoint(this.getWidth() - 5, (int) (this.getHeight() / 4));
        //nuevo
        polygon.addPoint(this.getWidth() - 5, (int) (this.getHeight() / 4));
        //
        polygon.addPoint(this.getWidth(), (int) (this.getHeight() / 2));
        polygon.addPoint(this.getWidth() - 5, (int) (this.getHeight() / 1.3f));
        polygon.addPoint(this.getWidth(), this.getHeight());

        // Establezco color y lo relleno
        g2.setColor(GRIS_ROTO);
        g2.fillPolygon(polygon);

        // Selecciono color negro 
        g2.setColor(Color.black);

        // Pinto dos lineas para dar la sensacion de profundidad al poligono
        g2.drawLine(this.getWidth() - 5, (int) ((this.getHeight() / 4)), this.getWidth(), (int) (this.getHeight() / 2));
        g2.drawLine(this.getWidth() - 5, (int) (this.getHeight() / 1.3f), this.getWidth(), this.getHeight());
        g2.setPaintMode();
    }

    private void mostrarCampoRotoL(Graphics g2) {
        // Creo un poligono vacio
        Polygon polygon = new Polygon();

        // Establezco los puntos del poligono 
        polygon.addPoint(0, 0);
        //polygon.addPoint(5,(int)(this.getHeight()/4));
        polygon.addPoint(5, (int) (this.getHeight() / 4) - 1);
        polygon.addPoint(5, (int) (this.getHeight() / 4) + 1);
        polygon.addPoint(0, (int) (this.getHeight() / 2));
        polygon.addPoint(5, (int) (this.getHeight() / 1.3f));
        polygon.addPoint(0, this.getHeight());

        // Establezco color y lo relleno
        g2.setColor(GRIS_ROTO);
        g2.fillPolygon(polygon);

        // Selecciono color negro 
        g2.setColor(Color.black);

        // Pinto dos lineas para dar la sensacion de profundidad al poligono
        g2.drawLine(5, (int) ((this.getHeight() / 4) + 1), 0, (int) (this.getHeight() / 2));
        g2.drawLine(5, (int) (this.getHeight() / 1.3f), 0, this.getHeight());
        g2.setPaintMode();
    }

    /**
     * Lanza el evento de focus gained sobre el componente.
     */
    public void lanzaFocusGained() {
        FocusEvent fe = new FocusEvent(this, FocusEvent.FOCUS_GAINED, false);
        processEvent(fe);
    }

    /*
     * Este metodo da formato al TTextField. Es usado por la clase ValidacionBloques 
     */
    public void formateo() {
        if (tipoDato.equals("NUMERICO")
                && /*isPuntuacion() && */ !(super.getText().trim().equals(""))) {
            String numeroFormate = Herramientas.quitaFormatoImporte(super.getText(), Herramientas.PANTALLA);
            if (numeroFormate != null) {
                super.setText(numeroFormate);
            }
        }
    }

    public void ponFormato() {
        if (tipoDato.equals("NUMERICO")
                && !(super.getText().trim().equals(""))) {
            setText(getText());
        }

    }

    /**
     * Metodo que recupera el autovalor anterior al mostrado segun el historico
     * de autovalores. Usado por ControlTeclas
     *
     * @return true
     */
    public boolean ejecutaRegistroAnterior() {
        if (indicePropAutovalor >= 0) {
            ponAutoValor(indicePropAutovalor - 1);
        }

        if (indicePropAutovalor > 0) {
            indicePropAutovalor--;
        }

        return true;
    }

    /**
     * Metodo que recupera el autovalor posterior al mostrado segun el historico
     * de autovalores. Usado por ControlTeclas
     *
     * @return true
     */
    public boolean ejecutaRegistroSiguiente() {
        return true;
    }

    /**
     * Metodo que informa si el menu de opciones se esta ejecutando
     *
     * @return true, o false, si el menu de opsiones se esta ejecutando o no
     * @see ejecutaMenuOpciones()
     */
    public boolean isEjecutandoMenu() {
        return ejecutandoMenu;
    }

    private void setValidando(boolean bValidando) {
        validando = bValidando;
    }

    /*
     * Nos dice si nos han pulsado salir
     */
    protected boolean isValidando() {
        return validando;
    }
}
