/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Reproductor.Librerias;

import Reproductor.Equalizador.Equalizador;
import Reproductor.Interfaz.Gui_play;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javazoom.jlgui.player.amp.visual.ui.SpectrumTimeAnalyzer;
import palomino.herramientas.variables_entorno.Formatos;
import palomino.multimedia.audio.codec.Controlador;
import palomino.multimedia.audio.codec.Reproductor;
import palomino.multimedia.audio.codec.ReproductorEvento;
import palomino.multimedia.audio.codec.ReproductorExcepcion;
import palomino.multimedia.audio.codec.ReproductorLanzador;
import palomino.multimedia.audio.infos.*;

/**
 *
 * @author JONATHAN
 */
public class Cargar_audio implements ReproductorLanzador {

    public MpegInfo mpeg;
    public OggVorbisInfo ogg;
    public FlacInfo fla;
    public APEInfo ape;
    public WavInfo wav;
    public static int Pista = 0;
    public Map audioInfo = null;
    static File tamañoarchivo;
    private static Reproductor ReproductorBasico;
    public long secondsAmount = 0;
    private Equalizador equalizar;
    private Propiedades propiedades_conf;
    private Gui_play ClaseMaestra;
    public int Contador_de_celda = 0, control1 = 0, veces = 0;
    Long duration = null;

    public Cargar_audio() {
        equalizar = new Equalizador();
        ReproductorBasico = new Reproductor();
        ReproductorBasico.addBasicPlayerListener(this);
        mpeg = new MpegInfo();
        ogg = new OggVorbisInfo();
        fla = new FlacInfo();
        ape = new APEInfo();
        wav = new WavInfo();
    }

    public static void loadFile(String ruta) throws ReproductorExcepcion {
        tamañoarchivo = new File(ruta);
        ReproductorBasico.abrir(new File(ruta));
    }

    public static void loadFile(URL direccion) {
        try {
            tamañoarchivo = new File(direccion.toString());
            ReproductorBasico.abrir(direccion);
        } catch (ReproductorExcepcion ex) {
            JOptionPane.showMessageDialog(null, "URL nula");
        }
    }

    @Override
    public void abierto(Map Infoaudio) {
        audioInfo = Infoaudio;
        SpectrumTimeAnalyzer analizer = (SpectrumTimeAnalyzer) getClaseMaestra().Panel_espectro;
        analizer.stopDSP();
        analizer.closeDSP();
        analizer.setupDSP((SourceDataLine) audioInfo.get("basicplayer.sourcedataline"));
        analizer.startDSP((SourceDataLine) audioInfo.get("basicplayer.sourcedataline"));
    }

    @Override
    public void progreso(int bytesread, long microseconds, byte[] pcmdata, Map properties) {
        Procesar_Progreso(bytesread, microseconds, pcmdata, properties);
    }

    @Override
    public void estadoActualizado(ReproductorEvento event) {
        System.out.println("evento " + event);
    }

    @Override
    public void setControlador(Controlador controller) {
        ReproductorBasico = (Reproductor) controller;
    }

    public void reproducir() {
        try {
            ReproductorBasico.reproducir();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void parar() {
        try {
            ReproductorBasico.parar();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void pausar() {
        try {
            ReproductorBasico.pausar();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    public void resumir() {
        try {
            ReproductorBasico.resumir();
        } catch (ReproductorExcepcion e) {
// TODO Auto-generated catch block
        }
    }

    private void Procesar_Progreso(int bytes_leidos, long micro_segundos, byte[] pcmdata, Map propiedades) {
        SpectrumTimeAnalyzer analyzer = (SpectrumTimeAnalyzer) getClaseMaestra().Panel_espectro;
        analyzer.writeDSP(pcmdata);
        int byteslength = -1;
        long total = -1;
        // If it fails then try again with JavaSound SPI.
        if (total <= 0) {
            total = (long) Math.round(getLongitud_tiempo_estimado(audioInfo) / 1000);
        }
        // If it fails again then it might be stream => Total = -1
        if (total <= 0) {
            total = -1;
        }
        if (audioInfo.containsKey("audio.length.bytes")) {
            byteslength = ((Integer) audioInfo.get("audio.length.bytes")).intValue();
        }
        float progress = -1.0f;
        if ((bytes_leidos > 0) && ((byteslength > 0))) {
            progress = bytes_leidos * 1.0f / byteslength * 1.0f;
        }
        if (audioInfo.containsKey("audio.type")) {
            String audioformat = (String) audioInfo.get("audio.type");
            if (audioformat.equalsIgnoreCase("mp3")) {
                // Equalizer
                if (propiedades.containsKey("mp3.equalizer")) {
                    getEqualizar().getControl().setBands((float[]) propiedades.get("mp3.equalizer"));
                }
                if (total > 0) {
                    secondsAmount = (long) (total * progress);
                } else {
                    secondsAmount = -1;
                }
            } else if (audioformat.equalsIgnoreCase("wave")) {
                secondsAmount = (long) (total * progress);
            } else {
                secondsAmount = (long) Math.round(micro_segundos / 1000000);
                getEqualizar().getControl().setBands(null);
            }
        } else {
            secondsAmount = (long) Math.round(micro_segundos / 1000000);
            getEqualizar().getControl().setBands(null);
        }
        if (total != 0) {
            PROGRESARBARRA(secondsAmount, total);
        }
    }

    private void PROGRESARBARRA(long secondsAmount, long total) {
        getClaseMaestra().Progreso_percent.setText(getClaseMaestra().Progreso1.getValue() + "%");
        int posValue = ((int) Math.round(secondsAmount * getClaseMaestra().Progreso1.getMaximum() / total));
        getClaseMaestra().Progreso1.setValue(posValue);

        if (getClaseMaestra().Progreso1.getValue() >= 99 && getClaseMaestra().Modo_Presentacion.isSelected() == false) {
            if (getClaseMaestra().repetir && Pista == (Libreria_Tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            } else {
                SiguienteAnterior(1, control1);
            }
            getClaseMaestra().Progreso1.setValue(0);
        } else if (getClaseMaestra().Progreso1.getValue() >= 10 && getClaseMaestra().Modo_Presentacion.isSelected()) {
            if (getClaseMaestra().repetir && Pista == (Libreria_Tabla.getMiTabla().getRowCount() - 1)) {
                Reproducir(0);
            } else {
                SiguienteAnterior(1, control1);
            }
            getClaseMaestra().Progreso1.setValue(0);
        }
    }

    private boolean IsSiguiente(int valor) {
        boolean sig = false;
        if (valor == 0) {
            sig = true;
        }
        return sig;
    }

    private boolean IsAleatorio(int control) {
        boolean alea = false;
        if (control == 0) {
            alea = true;
        }
        return alea;
    }

    public void REP_pista(int Pista) {
        Reproducir(Pista);
        Libreria_Tabla.getMiTabla().changeSelection(Pista, 1, false, false);
    }

    public void SiguienteAnterior(int valor, int control) {
        //control ==1 es para aleatorio
        if (getClaseMaestra().repetir_Cancion) {
            REP_pista(Pista);
        } else {
            if (IsSiguiente(valor)) {
                if (IsAleatorio(control)) {
                    if (Pista == 0) {
                        REP_pista(Pista);
                    } else {
                        Pista = (int) (Math.random() * (Libreria_Tabla.getMiTabla().getRowCount() - 1));
                        REP_pista(Pista);
                    }
                } else {
                    if (Pista == 0) {
                        REP_pista(Pista);
                    } else {
                        Pista = Pista - 1;
                        REP_pista(Pista);
                    }
                }
            } else {
                if (IsAleatorio(control)) {
                    if ((Pista + 1) == Libreria_Tabla.getMiTabla().getRowCount()) {
                        //System.out.println("al inicio");
                        //manda el contador al inicio paran emular un regreso
                        Pista = 0;
                        REP_pista(Pista);
                    } else {
                        try {
                            Pista = Pista + 1;
                            REP_pista(Pista);
                        } catch (Exception e) {
                            Reproducir(Pista - 1);
                            Libreria_Tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                            Pista = Pista - 1;
                        }
                    }
                } else {
                    try {
                        Pista = (int) (Math.random() * (Libreria_Tabla.getMiTabla().getRowCount() - 1));
                        REP_pista(Pista);
                    } catch (Exception e) {
                        Reproducir(Pista - 1);
                        Libreria_Tabla.getMiTabla().changeSelection((Pista - 1), 1, false, false);
                        Pista = Pista - 1;
                    }
                }
            }
        }
    }

    public void ModificarVolumen(JSlider Volumen) {
        Volumen.setToolTipText(String.valueOf(Volumen.getValue()));
        try {
            int Valor_ganancia = Volumen.getValue();
            int Maxima_ganancia = Volumen.getMaximum();
            if (Valor_ganancia == 0) {
                ReproductorBasico.setGain(0);
            } else {
                ReproductorBasico.setGain(((double) Valor_ganancia / (double) Maxima_ganancia));
            }
            try {
                getPropiedades_conf().GUARDAR_VOLUMEN(Volumen.getValue());
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Gui_play.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ReproductorExcepcion ex) {
            System.out.println("Error   ModificarVolumen");
        }
    }
    public void BOTON_MUTE_N(boolean estado, JButton SPEAKER, JSlider Volumen) {
        if (estado) {
            try {
                SPEAKER.setIcon(new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/mute.png")));
                ReproductorBasico.setGain(0);
            }
            catch (ReproductorExcepcion ex) {
            }
            try {
                getPropiedades_conf().GUARDAR_ULTIMO_MUTE(estado);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Gui_play.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else {
            SPEAKER.setIcon(new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/speaker_alto.png")));
            ModificarVolumen(Volumen);
            try {
                getPropiedades_conf().GUARDAR_ULTIMO_MUTE(estado);
            }
            catch (FileNotFoundException ex) {
                Logger.getLogger(Gui_play.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void Reproducir(int Pista) {
        Object direccion = Libreria_Tabla.getMiTabla().getValueAt(Pista, 1);
        // System.out.println("Pista "+direccion);//Nombre de la celda
        String file = direccion.toString();
        String nombre1 = Libreria_Tabla.getMiTabla().getValueAt(Pista, 0).toString();
        try {
            loadFile(file);
            reproducir();
            ModificarVolumen(getClaseMaestra().Volumen);
            getClaseMaestra().SPEAKER.setIcon(new ImageIcon(getClass().getResource("/palomino/multimedia/audio/iconos/speaker_alto.png")));
            MOSTRARINFO(nombre1, file, getClaseMaestra().txtInformacion, getClaseMaestra().Posicion, getClaseMaestra().txtBit);
        } catch (ReproductorExcepcion e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error Archivo invalido! \nElija otro ", "Error Fatal", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void MOSTRARINFO(String nombre, String direccion, JTextField txtInformacion, JTextField Posicion, JTextField txtBit) {
        final double constante = 6.03;
        try {
            if (direccion.contains(".mp3")) {
                mpeg.load(new File(direccion));
                TRANSFORMAR(mpeg, txtInformacion, Posicion, txtBit, nombre, constante);
            } else if (direccion.contains(".ogg")) {
                ogg.load(new File(direccion));
                TRANSFORMAR(ogg, txtInformacion, Posicion, txtBit, nombre, constante);
            } else if (direccion.contains(".flac")) {
                fla.load(new File(direccion));
                TRANSFORMAR(fla, txtInformacion, Posicion, txtBit, nombre, constante);
            } else if (direccion.contains(".ape")) {
                ape.load(new File(direccion));
                TRANSFORMAR(ape, txtInformacion, Posicion, txtBit, nombre, constante);
            } else if (direccion.contains(".wav")) {
                wav.load(new File(direccion));
                TRANSFORMAR(wav, txtInformacion, Posicion, txtBit, nombre, constante);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de IO");
        } catch (UnsupportedAudioFileException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error archivo no soportado");
        }
    }

    private void TRANSFORMAR(MpegInfo mpeg, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = mpeg.getMilisegundos();
        String formatohora = Formatos.FormatoHoras(mpeg.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(mpeg.getBitRate() / 1000 + " Kbps  " + mpeg.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(OggVorbisInfo ogg, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = ogg.getMilisegundos();
        System.out.println("mili " + ogg.getMilisegundos() + " seg " + ogg.getTiempo_en_segundos());
        String formatohora = Formatos.FormatoHoras(ogg.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(ogg.getBitRate() / 1000 + " Kbps  " + ogg.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(FlacInfo fla, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = fla.getMilisegundos();
        String formatohora = Formatos.FormatoHoras(fla.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(fla.getBitRate() / 1000 + " Kbps  " + fla.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(APEInfo ape, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, Double constante) {
        duration = ape.getMilisegundos();
        String formatohora = Formatos.FormatoHoras(ape.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(ape.getBitRate() / 1000 + " Kbps  " + ape.getSamplingRate() / 1000 + " KHZ");
    }

    private void TRANSFORMAR(WavInfo wav, JTextField txtInformacion, JTextField Posicion, JTextField txtBit, String nombre, double constante) {
        duration = wav.getMilisegundos();
        String formatohora = Formatos.FormatoHoras(wav.getTiempo_en_segundos());
        txtInformacion.setText(nombre + "  " + "(" + formatohora + ")");
        txtInformacion.setSize((int) (constante * txtInformacion.getText().length()), 20);//Longitud de ventana
        Posicion.setText((Pista + 1) + " de " + Libreria_Tabla.getMiTabla().getRowCount());
        txtBit.setText(wav.getBitRate() / 1000 + " Kbps  " + wav.getSamplingRate() / 1000 + " KHZ");
    }

    public long getLongitud_tiempo_estimado(Map properties) {
        long milliseconds = -1;
        int byteslength = -1;
        if (properties != null) {
            if (properties.containsKey("audio.length.bytes")) {
                byteslength = ((Integer) properties.get("audio.length.bytes")).intValue();
            }
            if (properties.containsKey("duration")) {
                milliseconds = (int) (((Long) properties.get("duration")).longValue()) / 1000;
            } else {
                // Try to compute duration
                int bitspersample = -1;
                int channels = -1;
                float samplerate = -1.0f;
                int framesize = -1;
                if (properties.containsKey("audio.samplesize.bits")) {
                    bitspersample = ((Integer) properties.get("audio.samplesize.bits")).intValue();
                }
                if (properties.containsKey("audio.channels")) {
                    channels = ((Integer) properties.get("audio.channels")).intValue();
                }
                if (properties.containsKey("audio.samplerate.hz")) {
                    samplerate = ((Float) properties.get("audio.samplerate.hz")).floatValue();
                }
                if (properties.containsKey("audio.framesize.bytes")) {
                    framesize = ((Integer) properties.get("audio.framesize.bytes")).intValue();
                }
                if (bitspersample > 0) {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * channels * (bitspersample / 8)));
                } else {
                    milliseconds = (int) (1000.0f * byteslength / (samplerate * framesize));
                }
            }
        }
        return milliseconds;
    }
        public void MOVIDA_MOUSE(JButton SPEAKER, JSlider Progreso1, JSlider Volumen) throws HeadlessException {
        try {
            int cal = (int) (tamañoarchivo.length() * Progreso1.getValue() / 100);
            ReproductorBasico.buscar_saltar(cal);
            if (SPEAKER.isSelected()) {
                ReproductorBasico.setGain(0);
            }
            else {
                ModificarVolumen(Volumen);
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "No lo movere xq no estoy reproduciendo", "Error", JOptionPane.ERROR_MESSAGE);
            Progreso1.setValue(0);
        }
    }
    public void SeleccionarArchivo() {
        int Pista = 0 ;
        if (Libreria_Tabla.getMiTabla().getRowCount() != 0) {
           //Pista = Libreria_Tabla.getVariable_tabla().Contador_de_celda;
            Pista = Libreria_Tabla.getClaseMaestra().getAudio().Contador_de_celda;
            Reproducir(Pista);//Manda el numero de celda para que reprodusca
        }
    }
    public void setClaseMaestra(Gui_play ClaseMaestra) {
        this.ClaseMaestra=ClaseMaestra;
    }

    /**
     * @return the ClaseMaestra
     */
    public Gui_play getClaseMaestra() {
        return ClaseMaestra;
    }

    /**
     * @return the propiedades_conf
     */
    public Propiedades getPropiedades_conf() {
        return propiedades_conf;
    }

    /**
     * @param propiedades_conf the propiedades_conf to set
     */
    public void setPropiedades_conf(Propiedades propiedades_conf) {
        this.propiedades_conf = propiedades_conf;
    }

    /**
     * @return the equalizar
     */
    public Equalizador getEqualizar() {
        return equalizar;
    }

    /**
     * @param equalizar the equalizar to set
     */
    public void setEqualizar(Equalizador equalizar) {
        this.equalizar = equalizar;
    }
}
