/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ultimo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.datatransfer.Clipboard;
import java.awt.event.FocusEvent;
import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import paquete.Contenedores.TBarraHerramientas;
import paquete.Contenedores.TPopupMenu;
import paquete.TBarraEstado;
import paquete.TFocusListener;
import paquete.TInputVerifier;
import paquete.utiles.ControlTeclas;
import paquete.utiles.Globales;
import paquete.utiles.Herramientas;
import paquete.utiles.ValidacionBloques;

/**
 *
 * @author JONATHAN
 */
public class JPTextField extends JTextField implements JPRedefinidorTeclas, JPInputComponent {

    public static final Color GRIS_CLARO = new Color(220, 220, 220);
    public static final Color GRIS_ROTO = new Color(192, 192, 192);
    public static final Color COLOR_ERROR = new Color(255, 192, 192);
    public static final Color COLOR_OBLIGA = new Color(255, 255, 192);
    public static final Color COLOR_NORMAL = new Color(192, 255, 255);
    private Component padreValidacion;
    Font miFuente = new Font("Dialog", Font.PLAIN, 12);
    /**
     * Auditor de eventos de foco. Para mas información consultar la
     * documentación de TFocusListener.
     */
    TFocusListener aSymFocus = null;
    /**
     * Panel de validación en bloque
     */
    private javax.swing.JPanel panelGrupo = null;
    /**
     * Ejemplar de la clase ValidacionBloques que se usa para invalidar el
     * bloque donde se halla el campo cuando el usuario introduce cambios en el
     * mismo.
     */
    ValidacionBloques validacionBloques = new ValidacionBloques();
    /**
     * Menú desplegable que se invoca cuando el usuario pulsa el botón derecho
     * del ratón sobre el campo.
     */
    protected static TPopupMenu pop = null;
    /**
     * Ventana (JFrameo o JDialog) en donde se encuentra el campo.
     */
    private java.awt.Component _padre = null;
    /**
     * Valor que devuelve getPadre() cuando no consigue encontrar ningún padre.
     * Este valor se devuelve para evitar errores de NullPointerException.
     */
    private static JPVentana ventanaDefecto = (JPVentana) (new JPFrame());
    /**
     * Cota superior que admite el campo según se ha definido en rangoNumeros.
     */
    private double numMaximo = java.lang.Double.MAX_VALUE;
    /**
     * Cota inferior que admite el campo según se ha definido en rangoNumeros.
     */
    private double numMinimo = 0;
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
     * Guarda la última tecla pulsada en el campo
     */
    private int ultimaTecla = 0;
    private int indicePropAutovalor = 0;
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
     * Nombre del método post lista
     */
    private String _metodoPostLista = "";
    /**
     * Nombre del método pre lista
     */
    private String _metodoPreLista = "";
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
     * Tipo del campo. Puede ser "NUMERO", "TEXTO" o "FECHA"
     *
     * @see #setTipoDato(java.lang.String)
     */
    protected String tipoDato = "TEXTO";
    /**
     * Guarda la longitud visible, minima y maxima
     *
     * @see #setLongitud(java.lang.String)
     */
    protected String datosLongitud = "";
    /**
     * Mascara de la fecha
     *
     * @see #setMascaraFecha(java.lang.String)
     */
    protected String mascaraFecha = "";
    /**
     * Controla si es el primer acceso que se realiza al componente. En caso de
     * ser true al entrar hay que poner el autovalor, si lo tuviera.
     */
    private boolean primerAcceso = true;
    /**
     * true if this component has activity
     */
    private boolean tiene_actividad = false;
    /**
     * Controla si se permite salir o no del campo
     */
    private boolean _permiteSalida = false;
    /**
     * Guarda si actualmente el campo se halla en medio de una validación.
     */
    private boolean estaValidando = false;
    /**
     * Controla si en caso de ser nulo el valor del campo hay que validar o no
     */
    private boolean _validaNulo = true;
    private boolean bHayLongitud = false;
    private boolean bHayTipoDato = false;
    /**
     * Guarda el estado de validación anterior.
     */
    private boolean validatePrevio = false;
    /**
     * Controlar el estado de validación de validación. Cuando el campo está
     * marcado como "no validado" la información se muestra en cursiva.
     */
    private boolean isValidate = false;
    /**
     * Indica que hay que refrescar el mensaje de la barra de estado con el tool
     * tip del campo
     */
    private boolean reponerMensaje = false;
    /**
     * Control del signo. Un valor de false indica que no se mostrará signo el
     * un campo numérico
     */
    private boolean _signo = false;
    /**
     * Guarda la propiedad de Gran Edición. Un valor true indica que al pulsar
     * GRANEDICION se abre un cuadro de diálogo donde con un text area en donde
     * se pueden escribir textos largos. Este cuadro de diálogo es un ejemplar
     * de la clase DialogoEditar. Por defecto false.
     */
    private boolean bGranEdicion = false;
    /**
     * Valor de la propiedad <i>Usar decimales por defecto</i>. Un valor de true
     * indica que el número de decimales se extrae de la clase Globales. Un
     * valor de false indica que el número se extrae de la propiedad <i>Número
     * de decimales por defecto<i>
     */
    private boolean decimalesDef = false;
    /**
     * Control de la puntuación. Un valor de false indica que no se mostrará
     * puntuación de milllares en un campo numérico
     */
    private boolean _puntuacion = false;
    /**
     * Controlo si se esta validando el TTextField
     */
    private boolean validando = false;
    /**
     * Un valor true indica que actualmente se está realizando una validación de
     * bloque.
     */
    private static boolean validacionTemporal = false;
    private boolean bEraOpaco = true;
    /**
     * Indica si hay un mensaje de error asociado al campo. Este valor se pone a
     * true cuando una validación falla y cuando se intenta introducir en el
     * campo texto incorrecto. Por ejemplo al intentar introducir letras en un
     * campo numérico.
     */
    private boolean hayMensajeError = false;
    /**
     * Indica el modo de traza. Un valor de true significa que hay que mostrar
     * información de traza
     */
    private boolean bDebug = false;
    /**
     * Controla si se debe ejecutar la función de oracle
     */
    boolean bEjecutarFuncion = true;
    /**
     * Indica que hay una lista de valores / opciones asociada al campo. Un
     * valor de true indica que hay que dibujar un triángulo negro en la esquina
     * inferior derecha del componente.
     */
    boolean hayLista = false;
    /**
     * Si es true, el texto introducido se pasa automaticamente a mayusculas
     *
     * @see #setMayusculas(boolean)
     */
    protected boolean mayusculas = true;
    /**
     * true si el campo es obligatorio, false de otro modo
     *
     * @see #setObligatorio(boolean)
     */
    protected boolean obligatorio;
    
     /**
     * Funcion de validacion oracle
     *
     * @see #setFuncionValidacion(java.lang.String)
     */
    protected String funcionValidacion = "";

    public JPTextField() {
        this.setFont(miFuente);
        setSize(0, 0);
        if (!java.beans.Beans.isDesignTime()) {
            aSymFocus = TFocusListener.getFocusListener(); //Auditor de eventos de foco
            this.addFocusListener(aSymFocus);

            JPTextField.SymMouse aSymMouse = new JPTextField.SymMouse(); //Auditor de eventos de ratón
            this.addMouseListener(aSymMouse);

            JPTextField.SymKey aSymKey = new JPTextField.SymKey(); //Auditor de eventos de teclado
            this.addKeyListener(aSymKey);

            JPTextField.SymMouseMotion aSymMouseMotion = new JPTextField.SymMouseMotion(); //Aud. de eventos de movimiento de ratón
            this.addMouseMotionListener(aSymMouseMotion);

            JPTextField.SymCaret lSymCaret = new JPTextField.SymCaret(); //Auditor de eventos de cursor
            this.addCaretListener(lSymCaret);

        }
        super.setAutoscrolls(false);
        this.setDisabledTextColor(Color.darkGray);
        if (!java.beans.Beans.isDesignTime()) {
            //{*{REGISTER_LISTENERS
            JPTextField.SymAction lSymAction = new JPTextField.SymAction(); //Auditor de eventos de acción
            this.addActionListener(lSymAction);
            //}*}	
        }
    }


    @Override
    public void focusGained(FocusEvent event) {
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

        if (tipoDato.equals("NUMERICO") && !(super.getText().trim().equals(""))) {
            String numeroFormate = Herramientas.quitaFormatoImporte(super.getText(), Herramientas.PANTALLA);

            if (numeroFormate != null) {
                boolean valido = this.isValidate();
                super.setText(numeroFormate);
                this.setValidate(valido);
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
            } else {
                this.setBackground(COLOR_NORMAL);
            }
        } else {
            setOpaque(bEraOpaco);
            this.setBackground(Color.white);

        }

        valorPrevio = super.getText();
        validatePrevio = this.isValidate();

        if (super.getText().length() != 0) {
            this.selectAll();
        }

        if ((getPadre().getComponenteActual() != this || this.getNextFocusableComponent() == this) //beware 08032001
                && this.isEnabled()) {
            ejecutaMetodo(this.METODO_PRECAMPO, null);
        }
        if (this.isEnabled()) {
            // Añadido el 10052001
            getPadre().setComponenteActual(this); //Solamete cuando este habilitado hay que hacer el setComponenteActual()
        }

        //getPadre().setComponenteActual(this); //En cualquier caso hay que hacer el setComponenteActual()
        if (this.getText().trim().equals("") && this.isPrimerAcceso()) {
            ponAutoValor();
        }
        //Control de la validación en la salida
        validacionBloques.permiteSalirAtras(this.getPanelGrupo(), this);
    }

    /**
     * Se lanza al perder el foco el campo. <br>Uso interno.
     *
     * @param event Evento de perdida de foco
     */
    @Override
    public void focusLost(FocusEvent event) {
        if (event.isTemporary() || !(this.isEnabled()) || validacionTemporal) {
            return;
        }
        //Control del borde
        setOpaque(bEraOpaco);
        this.setBackground(Color.white);

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

        this.setPrimerAcceso(false);
        validacionBloques.setUltimoComponente(this);

        //Si se ejecuta el focusLost es que se ha validado todo correctamente
        //ahora debemos volver a "enmascarar" el dato.
        if (tipoDato.equals("NUMERICO")) {
            String numeroFormate = Herramientas.ponFormatoImporte(
                    getText(), //super.getText()
                    getDecimalesDef() ? Globales.getNumDec() : getNumDecimal(),
                    isPuntuacion());

            if (numeroFormate != null) {
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
     * Lanza las validaciones necesarias antes de perder el foco. <br> Uso
     * interno.
     */
    public boolean ejecutaCampoSig() {
        //Antes de salir del campo comprobamos la validacuón 
        if (this.getInputVerifier() == null || getInputVerifier().verify(this)) {
            InputVerifier inputVerifier;
            if (getInputVerifier() == null) {
                inputVerifier = new TInputVerifier();
            } else {
                inputVerifier = getInputVerifier();
            }


            setInputVerifier(null);

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
                transferFocus();
            }
            setInputVerifier(inputVerifier);
        }

        return true; //Consumimos la tecla de campo siguiente
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
        /*if (isEjecutandoMenu()) {
         return true;
         }*/

        if (!this.isEnabled() || //Si es de salida se comporta como JLabel
                !this.isEditable() || //Si es de salida se comporta como JLabel
                (this.isNull() && //Para el caso de que sea nulo, no obligatorio y no "Valida si nulo"
                !this.isValidaNulo()
                && !this.isObligatorio())) {
            setValidate(true);
            estaValidando = true;
            hayMensajeError = false;
            return true;
        }

        if ((_validaNulo && super.getText().equals(""))
                || !super.getText().equals(valorPrevio) || !isValidate) {
            // Aqui se impide sobrepasar el rango maximo o minimo de un dato numérico
            /*if (this.tipoDato.equals("NUMERICO")) {
             double valor = 0;
             if (!super.getText().equals("")) 
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
             return false;
             }
             if (valor < this.getNumMinimo()) {
             TBarraEstado.setMensajeError(Globales.getCodigoMensaje("trn", "10190015"));//"El valor númerico no puede ser inferior al valor mínimo establecido.");
             setValidate(false);
             hayMensajeError = true;
             return false;
             }
             }*/

            if (!(validaObligatorio() && validaLongitud() && validaTipoDato() && ejecutaMetodo(this.METODO_PREVALIDACION, null))) {
                setValidate(false);
                hayMensajeError = true;
                return false;
            }

            /*if (bValidarContraListaOpciones && this.listaOpc != null) {
             } else if (!validacionMetodo()) //recordar que validacionMetodo llama internamente al post validacion
             {
             setValidate(false);
             hayMensajeError = true;	            
             return false;
             }*/
            setValidate(true);
            estaValidando = true;
            hayMensajeError = false;

        } else {
            setValidate(this.isValidate);
            estaValidando = isValidate;
        }
        return true;
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
     * Indica si un JPTextField obligatorio es válido o no, dependiendo de si ha
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

    public void formateo() {
    }

    public void ponFormato() {
    }

    public void addParametro(Object param, String io, int piNumero) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void addParametro(Object param, String io, int piNumero, int piTotal) {
        throw new UnsupportedOperationException("Not yet implemented");
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

    public void setMetodoOnError(Component _padre, String metodoOnError) {
        throw new UnsupportedOperationException("Not yet implemented");
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

        //TBarraEstado.setBloqueoBarra(!aux);
        this.setValidando(false);

        return aux;
    }
    //11032002

    public boolean lanzaValidacionOracle(Component p) {
        padreValidacion = p;
        return lanzaValidacionOracle();
    }
    /**
     * Realiza la validacion oracle sin tener en cuenta pre-validacion ni
     * post-validacion
     */
    private boolean lanzaValidacionOracleInterno() {
        return true;
    }
    public Object[] devuelveValores() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Object devuelveValor(int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void inicializaParametros() {
        throw new UnsupportedOperationException("Not yet implemented");
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

    public Object getTipoDato() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean ejecutaMenuOpciones() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public boolean ejecutaListaValores() {
        throw new UnsupportedOperationException("Not yet implemented");
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

    /**
     * Método de traza
     */
    private void msg(String s) {
        if (bDebug) {
        }
    }

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
            if (object == JPTextField.this) {
                JPTextField_caretUpdate(event);
            }
            validacionTemporal = false;
        }
    }

    /**
     * Se lanza al marcar sobre el campo
     *
     * @param event Evento de modificacion de la marca
     */
    void JPTextField_caretUpdate(javax.swing.event.CaretEvent event) {
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
            if (object == JPTextField.this) {
                JPTextField_mouseMoved(event);
            }
        }
    }

    /**
     * Se lanza al mover el raton sobre el campo
     *
     * @param event Evento de movimiento de raton
     */
    void JPTextField_mouseMoved(java.awt.event.MouseEvent event) {
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
            if (object == JPTextField.this) {
                JPTextField_mouseClicked(event);
            }
            validacionTemporal = false;
        }

        /**
         * Se ejecuta al presionar un botón del ratón sobre el campo.
         */
        public void mouseReleased(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == JPTextField.this) {
                JPTextField_mouseReleased(event);
            }
        }
    }

    /**
     * Se lanza al presionar con el raton sobre el campo
     *
     * @param event Evento de raton
     */
    void JPTextField_mouseReleased(java.awt.event.MouseEvent event) {

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
     * Se lanza al hacer clik sobre el campo
     *
     * @param event Evento de clik del raton
     */
    void JPTextField_mouseClicked(java.awt.event.MouseEvent event) {
        //pulsadoTab = false;

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
        validacionBloques.validaBloque(this.panelGrupo, this);
        //hayRaton=true;
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

            //Oculta el menu desplegable, si existe
            if (pop != null) {
                if (pop.isVisible()) {
                    pop.setVisible(false);
                }
            }
            if (reponerMensaje) {
                TBarraEstado.setMensaje(JPTextField.this.getToolTipText());
                reponerMensaje = false;
            }


            Object object = event.getSource();
            if (object == JPTextField.this) {
                JPTextField_keyPressed(event);
            }
        }
    }

    /**
     * Auditor de eventos de acción
     */
    class SymAction implements java.awt.event.ActionListener {

        /**
         * Se lanza cuando se pulsa acción sobre el campo. Usualmente al pulsar
         * ENTER
         */
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JPTextField.this) {
                JPTextField_actionPerformed(event);
            }
        }
    }

    /**
     * Se lanza cuando el campo captura un evento de ejecucion de accion, por
     * ejemplo al pulsar enter sobre el mismo.
     *
     * @param event Evento de ejecucion de accion
     */
    void JPTextField_actionPerformed(java.awt.event.ActionEvent event) {
        // to do: code goes here.
        /*if (hayLista || soyDescripcionLista || soyCodigoLista)
         {
         mostrarIconoLista();
         } */
    }

    /**
     * Se lanza al pulsar una tecla sobre el campo. <br> Uso Interno.
     *
     * @param event Evento de tecla
     */
    public void JPTextField_keyPressed(java.awt.event.KeyEvent event) {
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

    public void teclaTipeada(java.awt.event.KeyEvent event) {
        System.out.println("tecla tipeada() " + event);
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
     * Se lanza al escribir sobre el campo
     *
     * @param event Evento de escritura
     */
    void TTextField_keyTyped(java.awt.event.KeyEvent event) {
        if (this.isValidando()) {
            event.consume();
        }
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
        }
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
     *
     * Este método se utiliza para poder manejar los eventos de teclado
     * asociados a la tecla TAB. <br> Uso interno.
     *
     */
    public boolean isManagingFocus() {
        return true;
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
     * Lanza el evento de focus lost sobre el componente. Solo utilizado
     * internamente por los componentes.
     */
    public void lanzaFocusLost() {
        FocusEvent fe = new FocusEvent(this, FocusEvent.FOCUS_LOST, false);
        processEvent(fe);
    }
    /**
     * Ventana en donde está insertado el componente
     */
    JPVentana ventanaPadre = null;

    /**
     * Recupera la ventana en donde está insertado el componente.
     *
     * @return Ventana en donde está insertado el componente
     */
    public JPVentana getPadre() {
        if (ventanaPadre == null) {
            Component p = Globales.getPadre(this);
            if (p != null) {
                ventanaPadre = (JPFrame) p;
            }
        }
        return (ventanaPadre != null ? ventanaPadre : ventanaDefecto);
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
        miFuente = new Font("ARIAL", Font.PLAIN, 12);
        this.setFont(miFuente);

        if (this.tipoDato.trim().equals("FECHA")) {
            if (mascaraFecha == null || mascaraFecha.trim().equals("")) {
                setMascaraFecha("dd-MM-yyyy");
            }
        } else {
            setMascaraFecha("");
        }
        if (this.tipoDato.trim().equals("NUMERICO")) {
            miFuente = new Font("ARIAL", Font.PLAIN, 12);
            this.setFont(miFuente);

        }
        if (bHayLongitud) {
            setLongitud(this.datosLongitud);
        }

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
            tiene_actividad = true;
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
     * Realiza la acción de la pulsación de doble-click. Por defecto no hace
     * nada.
     *
     * @return false
     */
    public boolean ejecutaDobleClick() {
        return false;
    }

    /**
     * Permite pegar el texto seleccionado. Redefinido para tener en cuenta
     * ControlTeclas. Uso interno.
     */
    public void paste() {
    }

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
    @Override
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
    @Override
    public String getMetodoTeclas() {
        if (this._metodoTeclas == null) {
            return "";
        }

        return this._metodoTeclas;
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

}
