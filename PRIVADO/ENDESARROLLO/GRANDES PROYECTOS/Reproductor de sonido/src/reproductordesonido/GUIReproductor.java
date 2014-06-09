package reproductordesonido;

import elementos_de_control.ListaDobleConOrden;
import elementos_de_control.Archivo_Jalar_Pegar;
import elementos_de_control.NodoDoble;
import elementos_de_control.Direcciones;
import java.awt.*;
import java.awt.event.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.net.URI;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javazoom.jlgui.basicplayer.*;

/**
 *
 * @author JONATHAN
 */
public class GUIReproductor extends JFrame implements BasicPlayerListener {

    Desktop elemento;
    private Map audioInfo = null;
    private UIManager.LookAndFeelInfo apariencias[];
    static ListaDobleConOrden ldco = new ListaDobleConOrden();
    static Direcciones Direccion;
    Equalizador Equaliza;
    JFileChooser archivo;
    File[] Elementos;
    File Directorio, tamañoarchivo;
    private BasicPlayer basicPlayer;
    private BasicController player;
    private static DefaultTableModel miModelo;
    public String[][] data = {};
    boolean estado = false, repetir = false, aleatorio = false, estado1 = false;
    static boolean duplicado = false;
    String nombre1;
    int Contador_de_celda = 0, Pista = 0, control1 = 0, veces = 0, tipo;
    private double bytesLength;
    Object rutaTabla;
    Long duration = null;
    int horas = 0, minutofinal = 0, minuto, segundos, segundofinal;
    private long secondsAmount = 0;

    JTable getMiTabla() {
        return Tabla;
    }

    public static DefaultTableModel getMiModelo() {
        return miModelo;
    }

    public GUIReproductor() {
        elemento = Desktop.getDesktop();
        /*Verifica que el ambiente del SO soporte los procedimientos*/
        if (elemento.isDesktopSupported() == false) {
            JOptionPane.showMessageDialog(this, "El sistema no soporta los procedimientos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        setVisible(true);
        basicPlayer = new BasicPlayer();
        basicPlayer.addBasicPlayerListener(this);
        initComponents();
        setLocation(400, 200);
        Equaliza = new Equalizador(this);
        miModelo = new DefaultTableModel(data, new String[]{"Lista de Reproduccion", "Ruta"}) {

            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Tabla.setModel(miModelo);
        jScrollPane1.setViewportView(Tabla);
        apariencias = UIManager.getInstalledLookAndFeels();
        ImageIcon icono = new ImageIcon(GUIReproductor.class.getResource("/reproductordesonido/iconos/Reproductor.png"));
        setIconImage(icono.getImage());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo = new javax.swing.ButtonGroup();
        btnAbrir = new javax.swing.JButton();
        btnPlay = new javax.swing.JButton();
        btnStop = new javax.swing.JButton();
        btnPausa = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnSiguiente = new javax.swing.JButton();
        Volumen = new javax.swing.JSlider();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        Progreso1 = new javax.swing.JSlider();
        txtInformacion = new javax.swing.JTextField();
        Posicion = new javax.swing.JTextField();
        SPEAKER = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        MenuPArchivo = new javax.swing.JMenu();
        MenuArchivo = new javax.swing.JMenuItem();
        MenuCarpeta = new javax.swing.JMenuItem();
        MenuOcultar = new javax.swing.JMenuItem();
        menuSalir = new javax.swing.JMenuItem();
        MenuLista = new javax.swing.JMenu();
        OpcionLista = new javax.swing.JMenu();
        PermitirDuplicado = new javax.swing.JRadioButtonMenuItem();
        EliminarDuplicadoAuto = new javax.swing.JRadioButtonMenuItem();
        EliminarDuplicadoDespues = new javax.swing.JRadioButtonMenuItem();
        Buscar = new javax.swing.JMenuItem();
        EliminarElegido = new javax.swing.JMenuItem();
        EliminarTodo = new javax.swing.JMenuItem();
        CargarLista = new javax.swing.JMenuItem();
        GuardarLista = new javax.swing.JMenuItem();
        MenuOpciones = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        RepetirLista = new javax.swing.JRadioButtonMenuItem();
        NoRepetir = new javax.swing.JRadioButtonMenuItem();
        RepetirAleatorio = new javax.swing.JRadioButtonMenuItem();
        MenuAyuda = new javax.swing.JMenu();
        AcercaDE = new javax.swing.JMenuItem();
        Web = new javax.swing.JMenuItem();
        Contactar = new javax.swing.JMenuItem();
        MenuTema = new javax.swing.JMenu();
        TipoWindows = new javax.swing.JCheckBoxMenuItem();
        TipoRedondo = new javax.swing.JCheckBoxMenuItem();
        TipoJava = new javax.swing.JCheckBoxMenuItem();

        setTitle("Reproductor Java");

        btnAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/1.JPG"))); // NOI18N
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/3.JPG"))); // NOI18N
        btnPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlayActionPerformed(evt);
            }
        });

        btnStop.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/2.JPG"))); // NOI18N
        btnStop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStopActionPerformed(evt);
            }
        });

        btnPausa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/4.JPG"))); // NOI18N
        btnPausa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPausaActionPerformed(evt);
            }
        });

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/5.JPG"))); // NOI18N
        btnAnterior.setToolTipText("atraz");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });

        btnSiguiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/6.JPG"))); // NOI18N
        btnSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSiguienteActionPerformed(evt);
            }
        });

        Volumen.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                VolumenMouseDragged(evt);
            }
        });

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Tabla.setDragEnabled(true);
        Tabla.setDropMode(javax.swing.DropMode.ON_OR_INSERT_ROWS);
        Tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                TablaMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(Tabla);

        Progreso1.setValue(0);
        Progreso1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                Progreso1MouseDragged(evt);
            }
        });

        txtInformacion.setEditable(false);
        txtInformacion.setFont(new java.awt.Font("Comic Sans MS", 0, 11));
        txtInformacion.setToolTipText("Informacion de titulo");

        Posicion.setEditable(false);
        Posicion.setToolTipText("Posicion de pista");

        SPEAKER.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker1.png"))); // NOI18N
        SPEAKER.setMaximumSize(new java.awt.Dimension(49, 25));
        SPEAKER.setMinimumSize(new java.awt.Dimension(49, 25));
        SPEAKER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SPEAKERActionPerformed(evt);
            }
        });

        MenuPArchivo.setText("Archivo");

        MenuArchivo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        MenuArchivo.setText("Añadir Archivo");
        MenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuArchivoActionPerformed(evt);
            }
        });
        MenuPArchivo.add(MenuArchivo);

        MenuCarpeta.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        MenuCarpeta.setText("Añadir Carpeta");
        MenuCarpeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCarpetaActionPerformed(evt);
            }
        });
        MenuPArchivo.add(MenuCarpeta);

        MenuOcultar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, 0));
        MenuOcultar.setText("Ocultar Reproductor");
        MenuOcultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuOcultarActionPerformed(evt);
            }
        });
        MenuPArchivo.add(MenuOcultar);

        menuSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        menuSalir.setText("Salir");
        menuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSalirActionPerformed(evt);
            }
        });
        MenuPArchivo.add(menuSalir);

        jMenuBar1.add(MenuPArchivo);

        MenuLista.setText("Lista");

        OpcionLista.setText("Opciones de Lista");

        PermitirDuplicado.setSelected(true);
        PermitirDuplicado.setText("Permitir Entradas duplicadas");
        PermitirDuplicado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PermitirDuplicadoActionPerformed(evt);
            }
        });
        OpcionLista.add(PermitirDuplicado);

        EliminarDuplicadoAuto.setText("Eliminar Duplicados Automaticamente");
        EliminarDuplicadoAuto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDuplicadoAutoActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarDuplicadoAuto);

        EliminarDuplicadoDespues.setText("Eliminar Duplicados despues");
        EliminarDuplicadoDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarDuplicadoDespuesActionPerformed(evt);
            }
        });
        OpcionLista.add(EliminarDuplicadoDespues);

        Buscar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, 0));
        Buscar.setText("Saltar a Musica");
        Buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarActionPerformed(evt);
            }
        });
        OpcionLista.add(Buscar);

        MenuLista.add(OpcionLista);

        EliminarElegido.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        EliminarElegido.setText("Eliminar Seleccionado");
        EliminarElegido.setEnabled(false);
        EliminarElegido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarElegidoActionPerformed(evt);
            }
        });
        MenuLista.add(EliminarElegido);

        EliminarTodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        EliminarTodo.setText("Eliminar todo");
        EliminarTodo.setEnabled(false);
        EliminarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTodoActionPerformed(evt);
            }
        });
        MenuLista.add(EliminarTodo);

        CargarLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        CargarLista.setText("Cargar Lista");
        CargarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CargarListaActionPerformed(evt);
            }
        });
        MenuLista.add(CargarLista);

        GuardarLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        GuardarLista.setText("Guardar Lista");
        GuardarLista.setEnabled(false);
        GuardarLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarListaActionPerformed(evt);
            }
        });
        MenuLista.add(GuardarLista);

        jMenuBar1.add(MenuLista);

        MenuOpciones.setText("Opciones");
        grupo.add(MenuOpciones);

        jMenuItem1.setText("Mostrar Equalizador");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuOpciones.add(jMenuItem1);

        RepetirLista.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, 0));
        RepetirLista.setText("Repetir Lista");
        RepetirLista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepetirListaActionPerformed(evt);
            }
        });
        MenuOpciones.add(RepetirLista);

        NoRepetir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, 0));
        NoRepetir.setSelected(true);
        NoRepetir.setText("No Repetir Lista");
        NoRepetir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoRepetirActionPerformed(evt);
            }
        });
        MenuOpciones.add(NoRepetir);

        RepetirAleatorio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, 0));
        RepetirAleatorio.setText("Repetir Aleatorio");
        RepetirAleatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RepetirAleatorioActionPerformed(evt);
            }
        });
        MenuOpciones.add(RepetirAleatorio);

        jMenuBar1.add(MenuOpciones);

        MenuAyuda.setText("Ayuda");

        AcercaDE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        AcercaDE.setText("Acerca de:");
        AcercaDE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AcercaDEActionPerformed(evt);
            }
        });
        MenuAyuda.add(AcercaDE);

        Web.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        Web.setText("Web");
        Web.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WebActionPerformed(evt);
            }
        });
        MenuAyuda.add(Web);

        Contactar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, 0));
        Contactar.setText("Contactar");
        Contactar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ContactarActionPerformed(evt);
            }
        });
        MenuAyuda.add(Contactar);

        MenuTema.setText("Tema Visual");

        TipoWindows.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, 0));
        TipoWindows.setText("Tipo Windows");
        TipoWindows.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoWindowsActionPerformed(evt);
            }
        });
        MenuTema.add(TipoWindows);

        TipoRedondo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, 0));
        TipoRedondo.setText("Tipo Redondeado suave");
        TipoRedondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoRedondoActionPerformed(evt);
            }
        });
        MenuTema.add(TipoRedondo);

        TipoJava.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_J, 0));
        TipoJava.setSelected(true);
        TipoJava.setText("Tipo Java");
        TipoJava.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoJavaActionPerformed(evt);
            }
        });
        MenuTema.add(TipoJava);

        MenuAyuda.add(MenuTema);

        jMenuBar1.add(MenuAyuda);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAbrir, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPlay, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPausa, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(SPEAKER, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Volumen, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Progreso1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(txtInformacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnPlay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnStop, javax.swing.GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
                            .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPausa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(SPEAKER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Volumen, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(txtInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(Posicion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Progreso1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlayActionPerformed
        SeleccionarArchivo();
    }//GEN-LAST:event_btnPlayActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        ABRIR("Archivos");
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void AcercaDEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AcercaDEActionPerformed
        AcercaDE abc = new AcercaDE();
        abc.show();
    }//GEN-LAST:event_AcercaDEActionPerformed

    private void MenuCarpetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCarpetaActionPerformed
        ABRIR("Carpeta");
    }//GEN-LAST:event_MenuCarpetaActionPerformed

    private void MenuOcultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuOcultarActionPerformed
        MiminizarAlReloj();
    }//GEN-LAST:event_MenuOcultarActionPerformed

    private void menuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_menuSalirActionPerformed

    private void EliminarElegidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarElegidoActionPerformed
        NodoDoble auxiliar = ldco.busca((File) rutaTabla);
        if (auxiliar != null) {
            ldco.elimina(auxiliar);
            ActualizaTabla();

        }
    }//GEN-LAST:event_EliminarElegidoActionPerformed

    private void EliminarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTodoActionPerformed
        ldco.setInicio(null);
        inicializaTabla();
        Habilitar(false);
        stop();
        Progreso1.setValue(0);
        Posicion.setText("");
        txtInformacion.setText("");
    }//GEN-LAST:event_EliminarTodoActionPerformed

    private void CargarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CargarListaActionPerformed
        CargarLista();
        Habilitar(true);
    }//GEN-LAST:event_CargarListaActionPerformed

    private void GuardarListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarListaActionPerformed
        GuardarLista();
    }//GEN-LAST:event_GuardarListaActionPerformed

    private void WebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_WebActionPerformed
        Aparecer();
    }//GEN-LAST:event_WebActionPerformed

    private void btnStopActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStopActionPerformed
        stop();
        SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker1.png")));
    }//GEN-LAST:event_btnStopActionPerformed

    private void btnPausaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPausaActionPerformed

        estado = !estado;
        if (estado) {
            pause();
        } else {
            resume();
        }
    }//GEN-LAST:event_btnPausaActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        try {
            SiguienteAnterior(0, control1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
        ABRIR("Archivos");

    }//GEN-LAST:event_MenuArchivoActionPerformed

    private void btnSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSiguienteActionPerformed
        try {
            SiguienteAnterior(1, control1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnSiguienteActionPerformed

    private void TablaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaMousePressed
        Contador_de_celda = getMiTabla().getSelectedRow();
        //System.out.println("orden " + Contador_de_celda);//Indica el lugar de la fila seleccionada el Contador_de_celda
        rutaTabla = getMiTabla().getValueAt(Contador_de_celda, 1);
        //System.out.println("Ruta " + rutaTabla);//Nombre de la celda
        //String uno=getMiTabla().getColumnName(Contador_de_celda);//Entrega nombre d columna
        //System.out.println("aparecio "+uno);
        if (evt.getClickCount() == 2) {
            int filas = getMiTabla().getRowCount();
            for (int h = 0; h < filas; h++) {
                if (rutaTabla.toString() == getMiTabla().getValueAt(h, 1).toString()) {
                    Reproducir(h);
                    getMiTabla().changeSelection(h, 1, false, false);
                }
            }
        }
    }//GEN-LAST:event_TablaMousePressed

    private void Progreso1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Progreso1MouseDragged
        //System.out.println("progreso "+Progreso1.getValue());// TODO add your handling code here:
        try {
            int cal = (int) (tamañoarchivo.length() * Progreso1.getValue() / 100);
            //System.out.println("asumido "+cal);
            basicPlayer.seek(cal);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "No lo movere xq no estoy reproduciendo", "Error", JOptionPane.ERROR_MESSAGE);
            Progreso1.setValue(0);
        }
    }//GEN-LAST:event_Progreso1MouseDragged

    private void VolumenMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_VolumenMouseDragged
        //System.out.println("volumen "+Volumen.getValue());
        ModificarVolumen();
    }//GEN-LAST:event_VolumenMouseDragged

    private void NoRepetirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoRepetirActionPerformed
        RepetirAleatorio.setSelected(false);
        RepetirLista.setSelected(false);
        repetir = false;
        aleatorio = false;
        control1 = 0;
    }//GEN-LAST:event_NoRepetirActionPerformed

    private void RepetirAleatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepetirAleatorioActionPerformed
        NoRepetir.setSelected(false);
        RepetirLista.setSelected(false);
        aleatorio = true;
        repetir = false;
        control1 = 1;
    }//GEN-LAST:event_RepetirAleatorioActionPerformed

    private void RepetirListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RepetirListaActionPerformed
        NoRepetir.setSelected(false);
        RepetirAleatorio.setSelected(false);
        repetir = true;
        aleatorio = false;
        control1 = 0;
    }//GEN-LAST:event_RepetirListaActionPerformed

    private void TipoJavaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoJavaActionPerformed
        TipoRedondo.setSelected(false);
        TipoWindows.setSelected(false);
        tipo = 0;
        CambiarApariencia(tipo);
    }//GEN-LAST:event_TipoJavaActionPerformed

    private void TipoWindowsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoWindowsActionPerformed
        TipoRedondo.setSelected(false);
        TipoJava.setSelected(false);
        tipo = 3;
        CambiarApariencia(tipo);
    }//GEN-LAST:event_TipoWindowsActionPerformed

    private void TipoRedondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoRedondoActionPerformed
        TipoWindows.setSelected(false);
        TipoJava.setSelected(false);
        tipo = 1;
        CambiarApariencia(tipo);
    }//GEN-LAST:event_TipoRedondoActionPerformed

    private void PermitirDuplicadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PermitirDuplicadoActionPerformed
        duplicado = false;
        EliminarDuplicadoAuto.setSelected(false);
        EliminarDuplicadoDespues.setSelected(false);
    }//GEN-LAST:event_PermitirDuplicadoActionPerformed

    private void EliminarDuplicadoAutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDuplicadoAutoActionPerformed
        duplicado = true;
        PermitirDuplicado.setSelected(false);
        EliminarDuplicadoDespues.setSelected(false);
    }//GEN-LAST:event_EliminarDuplicadoAutoActionPerformed

    private void BuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarActionPerformed
        Busqueda bcd = new Busqueda(ldco, this);
        bcd.show();
    }//GEN-LAST:event_BuscarActionPerformed

    private void EliminarDuplicadoDespuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarDuplicadoDespuesActionPerformed
        PermitirDuplicado.setSelected(false);
        EliminarDuplicadoAuto.setSelected(false);
        BuscarDuplicaddos();
        //Hace q regrese a la primera opcion
        PermitirDuplicado.setSelected(true);
        EliminarDuplicadoDespues.setSelected(false);
    }//GEN-LAST:event_EliminarDuplicadoDespuesActionPerformed

    private void ContactarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ContactarActionPerformed
        Contactar();
    }//GEN-LAST:event_ContactarActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

        Equaliza.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void SPEAKERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SPEAKERActionPerformed
        estado1 = !estado1;
        if (estado1) {
            try {
                SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker3.png")));
                basicPlayer.setGain(0);
            } catch (BasicPlayerException ex) {
            }
        } else {
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            ModificarVolumen();
        }

    }//GEN-LAST:event_SPEAKERActionPerformed

    public static void main(String args[]) {
        new Archivo_Jalar_Pegar(new GUIReproductor(), new Archivo_Jalar_Pegar.Listener() {

            public void filesDropped(File[] files) {
                for (int i = 0; i < files.length; i++) {
                    try {
                        if (files[i].isFile())//Verificar que es un archivo y no una carpeta
                        {
                            if (files[i].getName().contains(".mp3") || files[i].getName().contains(".ogg") || files[i].getName().contains(".wav")) {
                                String nom = files[i].getName();
                                File dat = new File(files[i].getPath());
                                Enviar(nom, dat);
                            } else if (files[i].getName().contains(".rep")) {
                                Traer_Lista(files[i]);
                            }
                        }

                    } // end try
                    catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }   // end for: through each dropped file
            }   // end filesDropped
        }); // end FileDrop.Listener

    }

    private static void Traer_Lista(File file) {
        FileReader LeerArchivo = null;
        BufferedReader Temporal_memoria = null;
        try {
            LeerArchivo = new FileReader(file);
            Temporal_memoria = new BufferedReader(LeerArchivo);

            // Lectura del fichero
            String linea;
            File actual;
            while ((linea = Temporal_memoria.readLine()) != null) {
                if (linea.contains("#EXT") == false)//Evita leer metadata de winamp
                {
                    actual = new File(linea);
                    String name = actual.getName();
                    Enviar(name, actual);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error Cargando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void Enviar(String nom, File dat) {
        IngresaDatos(nom, dat);
        if (duplicado) {
            NodoDoble aux = ldco.busca(dat);//Si encuentra algo es diferente de nulo
            if (aux == null) {
                ldco.agrega(Direccion);
                Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
                getMiModelo().addRow(datos);
                Habilitar(true);
            }
        } else {
            ldco.agrega(Direccion);
            Object[] datos = {Direccion.getNombre(), Direccion.getDireccion()};
            getMiModelo().addRow(datos);
            Habilitar(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem AcercaDE;
    private javax.swing.JMenuItem Buscar;
    private static javax.swing.JMenuItem CargarLista;
    private javax.swing.JMenuItem Contactar;
    private javax.swing.JRadioButtonMenuItem EliminarDuplicadoAuto;
    private javax.swing.JRadioButtonMenuItem EliminarDuplicadoDespues;
    private static javax.swing.JMenuItem EliminarElegido;
    private static javax.swing.JMenuItem EliminarTodo;
    private static javax.swing.JMenuItem GuardarLista;
    private javax.swing.JMenuItem MenuArchivo;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenuItem MenuCarpeta;
    private javax.swing.JMenu MenuLista;
    private javax.swing.JMenuItem MenuOcultar;
    private javax.swing.JMenu MenuOpciones;
    private javax.swing.JMenu MenuPArchivo;
    private javax.swing.JMenu MenuTema;
    private javax.swing.JRadioButtonMenuItem NoRepetir;
    private javax.swing.JMenu OpcionLista;
    private javax.swing.JRadioButtonMenuItem PermitirDuplicado;
    private javax.swing.JTextField Posicion;
    private javax.swing.JSlider Progreso1;
    private javax.swing.JRadioButtonMenuItem RepetirAleatorio;
    private javax.swing.JRadioButtonMenuItem RepetirLista;
    private javax.swing.JButton SPEAKER;
    private javax.swing.JTable Tabla;
    private javax.swing.JCheckBoxMenuItem TipoJava;
    private javax.swing.JCheckBoxMenuItem TipoRedondo;
    private javax.swing.JCheckBoxMenuItem TipoWindows;
    private javax.swing.JSlider Volumen;
    private javax.swing.JMenuItem Web;
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnPausa;
    private javax.swing.JButton btnPlay;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnStop;
    private javax.swing.ButtonGroup grupo;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menuSalir;
    private javax.swing.JTextField txtInformacion;
    // End of variables declaration//GEN-END:variables

    public void LlenarTabla(File[] Elementos) {
        EliminarElegido.setEnabled(true);
        EliminarTodo.setEnabled(true);
        GuardarLista.setEnabled(true);
        int tamaño = Elementos.length;
        for (int t = 0; t < tamaño; t++) {
            if (Elementos[t].isFile())//Verificar que es un archivo y no una carpeta
            {
                if (Elementos[t].getName().contains(".mp3") || Elementos[t].getName().contains(".ogg") || Elementos[t].getName().contains(".wav")) {//Filtrando archivos a agregar
                    String nombre = Elementos[t].getName();
                    Enviar(nombre, Elementos[t]);
                }
            } else {
                llamar(Elementos[t]);
                //System.out.println(Elementos[t].getName());
            }

        }
    }

    public void SiguienteAnterior(int valor, int control) {
        //control ==1 es para aleatorio
        //JOptionPane.showMessageDialog(null, Pista);
        if (valor == 0 && control == 1) /// 0 es anterior
        {
            if (Pista == 0) {
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            } else {
                Pista = (int) (Math.random() * (getMiTabla().getRowCount() - 1));
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            }
        }
        if (valor == 0 && control == 0) /// 0 es anterior
        {
            if (Pista == 0) {
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            } else {
                Pista = Pista - 1;
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            }
        }
        if (valor == 1 && control == 0) /// 1 es posterior
        {
            try {
                Pista = Pista + 1;
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            } catch (Exception e) {
                Reproducir(Pista - 1);
                getMiTabla().changeSelection((Pista - 1), 1, false, false);
                Pista = Pista - 1;
            }
        }
        if (valor == 1 && control == 1) /// 1 es posterior
        {
            try {
                Pista = (int) (Math.random() * (getMiTabla().getRowCount() - 1));
                Reproducir(Pista);
                getMiTabla().changeSelection(Pista, 1, false, false);
            } catch (Exception e) {
                Reproducir(Pista - 1);
                getMiTabla().changeSelection((Pista - 1), 1, false, false);
                Pista = Pista - 1;
            }
        }
    }

    public void SeleccionarArchivo() {
        if (getMiTabla().getRowCount() != 0) {
            Pista = Contador_de_celda;
            Reproducir(Contador_de_celda);//Manda el numero de celda para que reprodusca
        }
    }

    public void Reproducir(int Pista) {
        Object direccion = getMiTabla().getValueAt(Pista, 1);
        // System.out.println("Pista "+direccion);//Nombre de la celda
        String file = direccion.toString();
        nombre1 = getMiTabla().getValueAt(Pista, 0).toString();
        try {
            loadFile(file);
            play();
            ModificarVolumen();
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/reproductordesonido/iconos/speaker2.png")));
            //Reproduciendo = 1;
            MOSTRARPOPPUP(nombre1);

        } catch (BasicPlayerException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error de Archivo! \nElija otro ", "Error Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Necesario por implementar BasicPlayerListener. Es ejecutado una vez se
     * carga un fichero. En este caso, obtiene el tamaño en bytes del fichero.
     */
    public void opened(Object arg0, Map properties) {
        if (properties.containsKey("audio.length.bytes")) {
            bytesLength = Double.parseDouble(properties.get("audio.length.bytes").toString());
        }
        audioInfo = properties;
    }

    /**
     * Necesario por implementar BasicPlayerListener. Según la documentación,
     * este método es llamado varias veces por segundo para informar del
     * progreso en la reproducción.
     */
    public void progress(int bytesread, long microseconds, byte[] pcmdata,
            Map properties) {
        processProgress(bytesread, microseconds, pcmdata, properties);

    }

    public void setController(BasicController controller) {
        this.player = controller;

    }

    public BasicController getController() {
        return basicPlayer;
    }

    public void stateUpdated(BasicPlayerEvent arg0) {
        System.out.println("evemto " + arg0);
    }

    public void play() {
        try {
            basicPlayer.play();
        } catch (BasicPlayerException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            basicPlayer.stop();
        } catch (BasicPlayerException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void pause() {
        try {
            basicPlayer.pause();
        } catch (BasicPlayerException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void resume() {
        try {
            basicPlayer.resume();
        } catch (BasicPlayerException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void loadFile(String ruta) throws BasicPlayerException {
        tamañoarchivo = new File(ruta);
        basicPlayer.open(new File(ruta));
    }

    public void ABRIR(String tipo) {
        archivo = new JFileChooser("C:\\Documents and Settings\\JONATHAN\\Escritorio\\");
        archivo.setMultiSelectionEnabled(true);
        //archivo.setDragEnabled(true);
        archivo.getDragEnabled();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Audio", "mp3", "wav", "ogg");
        archivo.setFileFilter(filter);
        if (tipo.equals("Archivos")) {
            archivo.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int resultado = archivo.showOpenDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            } else {
                try {
                    Elementos = archivo.getSelectedFiles();
                    LlenarTabla(Elementos);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error Cargando Archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        if (tipo.equals("Carpeta")) {
            archivo.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int resultado = archivo.showOpenDialog(this);
            if (resultado == JFileChooser.CANCEL_OPTION) {
                return;
            }
            try {
                Directorio = archivo.getSelectedFile();
                llamar(Directorio);

            } catch (ArrayIndexOutOfBoundsException e) {
                //System.out.println(e);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error Cargando Carpeta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    File[] llamar(File Dir) {
        File[] lista_Archivos = Dir.listFiles();
        LlenarTabla(lista_Archivos);
        return lista_Archivos;
    }

    public void ProgresarBarra(File tamañoarchivo, int progreso) {
        int tamano = (int) tamañoarchivo.length();
        try {
            int valor1 = progreso * 100 / tamano;
            Progreso1.setValue(valor1);
        } catch (Exception w) {
        }
        if (Progreso1.getValue() >= 99) {
            //System.out.println(" tamaño de tabla "+(getMiTabla().getRowCount()-1));
            //System.out.println("Tamano pista "+Pista);
            if (repetir && Pista == (getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            } else {
                SiguienteAnterior(1, control1);
            }

            Progreso1.setValue(0);
        }
    }

    public void inicializaTabla() {
        // obtiene numero de filas de la tabla
        int filas = getMiTabla().getRowCount();

        // remueve todas las filas de la tabla
        for (int fila = 0; fila < filas; fila++) {
            getMiModelo().removeRow(0);
        }
    }

    public static void IngresaDatos(String nombre, File file) {
        Direccion = new Direcciones();
        Direccion.setDireccion(file);
        Direccion.setNombre(nombre);
    }

    public void ActualizaTabla() {
        inicializaTabla();
        NodoDoble auxiliar;
        auxiliar = ldco.getInicio();
        while (auxiliar != null) {
            Direcciones dir = auxiliar.getNodo();
            Object[] datos = {dir.getNombre(), dir.getDireccion()};
            getMiModelo().addRow(datos);
            //retrocede al nodo anterior
            auxiliar = auxiliar.getApuntSgte();
        }

    }

    public void CargarLista() {
        JFileChooser FileGuardar = new JFileChooser("C:\\");
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("Archivos Soportados", "m3u", "rep");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);
        FileGuardar.setFileFilter(filtro2);
        int resultado = FileGuardar.showOpenDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        Traer_Lista(FileGuardar.getSelectedFile());

    }

    public void GuardarLista() {
        String Filtroingresado = "";
        JFileChooser FileGuardar = new JFileChooser("C:\\");
        FileGuardar.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos REP", "rep");
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("Archivos M3U", "m3u");
        FileGuardar.setFileFilter(filtro);
        FileGuardar.setFileFilter(filtro1);

        int resultado = FileGuardar.showSaveDialog(this);

        if (resultado == JFileChooser.CANCEL_OPTION) {
            return;
        }
        NodoDoble auxiliar = ldco.getInicio();

        if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos M3U") == 0) {
            Filtroingresado = ".m3u";
        } else if (FileGuardar.getFileFilter().getDescription().compareTo("Archivos REP") == 0) {
            Filtroingresado = ".rep";
        }
        try {
            FileOutputStream salida = null;

            if (FileGuardar.getSelectedFile().toString().indexOf(Filtroingresado) != -1) {
                salida = new FileOutputStream(FileGuardar.getSelectedFile());
            } else {
                salida = new FileOutputStream(FileGuardar.getSelectedFile() + Filtroingresado);
            }


            BufferedOutputStream memoria = new BufferedOutputStream(salida);
            while (auxiliar != null) {
                Direcciones dir = auxiliar.getNodo();
                Object datos = dir.getDireccion();
                memoria.write((datos.toString() + System.getProperty("line.separator")).getBytes());

                //avanza al nodo siguiente
                auxiliar = auxiliar.getApuntSgte();
            }
            memoria.flush();
            salida.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error Guardando Archivo!", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void CambiarApariencia(int i) {
        try {
            UIManager.setLookAndFeel(apariencias[i].getClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Imposible modificar el tema visual", "Lookandfeel inválido.",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void Habilitar(boolean b) {
        EliminarElegido.setEnabled(b);
        EliminarTodo.setEnabled(b);
        GuardarLista.setEnabled(b);
    }

    public void MiminizarAlReloj() {
        setVisible(false);
        veces += 1;
        //dispose();
        //se declara el objeto tipo icono
        final TrayIcon iconoSystemTray;
        //se verifica que el SystemTray sea soportado
        if (SystemTray.isSupported()) {
            //se obtiene una instancia estática de la clase SystemTray
            SystemTray tray = SystemTray.getSystemTray();
            //esta es la imagen de icono
            ImageIcon icono = new ImageIcon(GUIReproductor.class.getResource("/reproductordesonido/iconos/Reproductor.png"));
            iconoSystemTray = new TrayIcon(icono.getImage(), "Reproductor Java", null);

            iconoSystemTray.setImageAutoSize(true);
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            //este listener se asociara con un item del menu contextual
            //que aparece al hacer click derecho sobre el icono
            ActionListener escuchadorSalir = new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    //System.out.println("Saliendo...");
                    System.exit(0);
                }
            };
            //menu que aparece al hacer click derecho
            final JPopupMenu popup = new JPopupMenu();
            JMenuItem mostrar = new JMenuItem("Mostrar Reproductor", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/icono.png")));
            JMenuItem subirVolumen = new JMenuItem("subirVolumen +");
            JMenuItem bajarVolumen = new JMenuItem("bajarVolumen -");
            JMenuItem PistaAnterior = new JMenuItem("PistaAnterior", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/5.JPG")));
            JMenuItem PistaSiguiente = new JMenuItem("PistaSiguiente", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/6.JPG")));
            JMenuItem Pausar = new JMenuItem("Pausar", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/4.JPG")));
            JMenuItem Detener = new JMenuItem("Detener", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/2.JPG")));
            JMenuItem Reproducir = new JMenuItem("Reproducir", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/3.JPG")));
            JMenuItem salir = new JMenuItem("Salir", new ImageIcon(getClass().getResource("/reproductordesonido/iconos/salir.png")));
            salir.addActionListener(escuchadorSalir);
            //aniadir un menu con icono (swing) 
            popup.add(Reproducir);
            popup.add(Pausar);
            popup.add(Detener);
            popup.addSeparator();
            popup.add(PistaAnterior);
            popup.add(PistaSiguiente);
            popup.addSeparator();
            popup.add(subirVolumen);
            popup.add(bajarVolumen);
            popup.addSeparator();
            popup.add(mostrar);
            popup.add(salir);

            Reproducir.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    SeleccionarArchivo();
                }
            });
            Pausar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    estado = !estado;
                    if (estado) {
                        pause();
                    } else {
                        resume();
                    }
                }
            });
            Detener.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    stop();
                    secondsAmount = 0;
                }
            });
            PistaAnterior.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        SiguienteAnterior(0, control1);
                    } catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            PistaSiguiente.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    try {
                        SiguienteAnterior(1, control1);
                    } catch (Exception et) {
                        JOptionPane.showMessageDialog(null, "No encuentro pista", "Error de usuario", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
            subirVolumen.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() + 10);
                    ModificarVolumen();
                }
            });
            bajarVolumen.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    Volumen.setValue(Volumen.getValue() - 10);
                    ModificarVolumen();
                }
            });
            mostrar.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    setVisible(true);
                }
            });
            iconoSystemTray.addMouseListener(new MouseAdapter() {

                public void mouseReleased(MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        popup.setLocation(e.getX(), e.getY() - 50);
                        popup.setInvoker(popup);
                        popup.setVisible(true);
                    }
                }
            });
            //se debe capturar una excepción en caso que falle la adicion de un icono
            try {
                if (veces <= 1)//Verificar q solo se agrega una ves un tray icon
                {
                    tray.add(iconoSystemTray);
                }

            } catch (AWTException e) {
                System.err.println("No es posible agregar el icono al System Tray");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Tu sistema no soporta el System Tray :(", "Error Inesperado", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void ModificarVolumen() {
        Volumen.setToolTipText(String.valueOf(Volumen.getValue()));
        try {
            int gainValue = Volumen.getValue();
            int maxGain = Volumen.getMaximum();
            if (gainValue == 0) {
                basicPlayer.setGain(0);
            } else {
                basicPlayer.setGain(((double) gainValue / (double) maxGain));
                //config.setVolume(gainValue);
            }
        } catch (BasicPlayerException ex) {
            //System.out.println("Error");
        }
    }

    public void MOSTRARPOPPUP(String file) {
        try {
            AudioFileFormat baseFileFormat = AudioSystem.getAudioFileFormat(tamañoarchivo);
            Map properties = baseFileFormat.properties();
            String key_duration = "duration";
            duration = (Long) properties.get(key_duration);

        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        }

        double ensegundos = ((int) (duration / 10000)) / 100.0; //tiene 2 decimales
        double enminutos = (int) (ensegundos / 60 * 100) / 100.0;
        minuto = (int) enminutos;
        if (minuto >= 60) {
            horas = minuto / 60;
            minutofinal = minuto - (horas * 60);
        } else {
            minutofinal = minuto;
        }
        segundos = (int) Math.round((enminutos - minuto) * 60);
        segundofinal = segundos;
        final double constante = 6.01;
        int situacion = Pista + 1;
        String cancion = file;
        int total1 = getMiTabla().getRowCount();
        String Formato = String.valueOf(horas) + ":" + String.valueOf(minutofinal) + ":" + String.valueOf(segundofinal);
        String Cancion_duracion = cancion + "  " + "(" + Formato + ")";
        txtInformacion.setText(Cancion_duracion);
        int tam = Cancion_duracion.length();
        int longitud_ventana = (int) (constante * tam);
        txtInformacion.setSize(longitud_ventana, 20);
        Posicion.setText(String.valueOf(situacion) + " de " + String.valueOf(total1));
    }

    public void Aparecer() {
        try {
            elemento.browse(new URI("www.google.com"));

        } catch (URISyntaxException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GUIReproductor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void BuscarDuplicaddos() {
        for (int i = 0; i < getMiTabla().getRowCount(); i++) {
            for (int j = 0; j < i; j++) {
                if (getMiTabla().getValueAt(i, 1).toString().compareTo(getMiTabla().getValueAt(j, 1).toString()) == 0) {
                    //Eliminar
                    //System.out.println("Eliminar "+getMiTabla().getValueAt(i, 1).toString());
                    NodoDoble auxiliar = ldco.busca(new File(getMiTabla().getValueAt(i, 1).toString()));
                    if (auxiliar != null) {
                        ldco.elimina(auxiliar);
                    }
                }
            }
        }
        ActualizaTabla();
    }

    private void Contactar() {
        String email = JOptionPane.showInputDialog("Ingrese su emal");
        if (email.contains("@")) {
            String mail = "mailto:" + email;
            try {
                elemento.mail(new URI(mail));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error de usuario  Ingrese el @", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void processProgress(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        float progressUpdate = (float) (bytesread * 1.0f / bytesLength * 1.0f);
        int progressNow = (int) (bytesLength * progressUpdate);
        ProgresarBarra(tamañoarchivo, progressNow);
        int byteslength = -1;
        long total = -1;
        if (total <= 0) {
            total = (long) Math.round(duration / 1000);
        }
        // If it fails again then it might be stream => Total = -1
        if (total <= 0) {
            total = -1;
        }
        if (audioInfo.containsKey("basicplayer.sourcedataline")) {
            // Spectrum/time analyzer
            //if (ui.getAcAnalyzer() != null) ui.getAcAnalyzer().writeDSP(pcmdata);
        }
        if (audioInfo.containsKey("audio.length.bytes")) {
            byteslength = ((Integer) audioInfo.get("audio.length.bytes")).intValue();
        }
        float progress = -1.0f;
        if ((bytesread > 0) && ((byteslength > 0))) {
            progress = bytesread * 1.0f / byteslength * 1.0f;
        }
        if (audioInfo.containsKey("audio.type")) {
            String audioformat = (String) audioInfo.get("audio.type");
            if (audioformat.equalsIgnoreCase("mp3")) {
                // EqualizerUI
                if (properties.containsKey("mp3.equalizer")) {
                    Equaliza.setBands((float[]) properties.get("mp3.equalizer"));

                }
                if (total > 0) {
                    secondsAmount = (long) (total * progress);
                } else {
                    secondsAmount = -1;
                }
            } else if (audioformat.equalsIgnoreCase("wave")) {
                secondsAmount = (long) (total * progress);
            } else {
                secondsAmount = (long) Math.round(microseconds / 1000000);
                Equaliza.setBands(null);
            }
        } else {
            secondsAmount = (long) Math.round(microseconds / 1000000);
            Equaliza.setBands(null);
        }
    }
}
