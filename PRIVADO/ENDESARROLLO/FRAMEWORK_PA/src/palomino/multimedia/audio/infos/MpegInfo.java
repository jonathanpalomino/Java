/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package palomino.multimedia.audio.infos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * @author  JONATHAN
 */
public class MpegInfo implements Informacion {

    /**
	 * @uml.property  name="canales"
	 */
    private int canales = -1;
    /**
	 * @uml.property  name="channelsMode"
	 */
    private String channelsMode = null;
    /**
	 * @uml.property  name="version"
	 */
    private String version = null;
    /**
	 * @uml.property  name="rate"
	 */
    protected int rate = 0;
    /**
	 * @uml.property  name="layer"
	 */
    private String layer = null;
    /**
	 * @uml.property  name="emphasis"
	 */
    private String emphasis = null;
    /**
	 * @uml.property  name="nominalbitrate"
	 */
    private int nominalbitrate = 0;
    /**
	 * @uml.property  name="segundos"
	 */
    private double segundos = 0.0;
    /**
	 * @uml.property  name="milisegundos"
	 */
    private long milisegundos=0;
    /**
	 * @uml.property  name="location"
	 */
    private String location = null;
    /**
	 * @uml.property  name="tamano"
	 */
    private long tamano = 0;
    /**
	 * @uml.property  name="copyright"
	 */
    protected boolean copyright = false;
    /**
	 * @uml.property  name="crc"
	 */
    protected boolean crc = false;
    /**
	 * @uml.property  name="original"
	 */
    protected boolean original = false;
    /**
	 * @uml.property  name="priv"
	 */
    protected boolean priv = false;
    /**
	 * @uml.property  name="vbr"
	 */
    protected boolean vbr = false;
    /**
	 * @uml.property  name="track"
	 */
    private int track = -1;
    /**
	 * @uml.property  name="anio"
	 */
    private String anio = null;
    /**
	 * @uml.property  name="genero"
	 */
    private String genero = null;
    /**
	 * @uml.property  name="titulo"
	 */
    private String titulo = null;
    /**
	 * @uml.property  name="artista"
	 */
    private String artista = null;
    /**
	 * @uml.property  name="album"
	 */
    private String album = null;
    public MpegInfo() {
        super();
    }
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException {
        loadInfo(input);
    }

    public void load(URL input) throws IOException, UnsupportedAudioFileException {
        location = input.toString();
        loadInfo(input);
    }

    public void load(File input) throws IOException, UnsupportedAudioFileException {
        tamano = input.length();
        location = input.getPath();
        loadInfo(input);
    }

    public int getSamplingRate() {
        return rate;
    }

    public int getBitRate() {
        return nominalbitrate;
    }

    /**
	 * @return
	 * @uml.property  name="canales"
	 */
    public int getCanales() {
        return canales;
    }

    public double getTiempo_en_segundos() {
        return segundos;
    }

    /**
	 * @return
	 * @uml.property  name="album"
	 */
    public String getAlbum() {
        return album;
    }

    /**
	 * @return
	 * @uml.property  name="track"
	 */
    public int getTrack() {
        return track;
    }

    /**
	 * @return
	 * @uml.property  name="titulo"
	 */
    public String getTitulo() {
        return titulo;
    }

    /**
	 * @return
	 * @uml.property  name="artista"
	 */
    public String getArtista() {
        return artista;
    }

    /**
	 * @return
	 * @uml.property  name="genero"
	 */
    public String getGenero() {
        return genero;
    }

    public String getanio() {
        return anio;
    }

    public boolean getVBR() {
        return vbr;
    }

    /**
	 * @return
	 * @uml.property  name="version"
	 */
    public String getVersion() {
        return version;
    }

    /**
	 * @return
	 * @uml.property  name="emphasis"
	 */
    public String getEmphasis() {
        return emphasis;
    }

    /**
	 * @return
	 * @uml.property  name="copyright"
	 */
    public boolean getCopyright() {
        return copyright;
    }

    public boolean getCRC() {
        return crc;
    }

    /**
	 * @return
	 * @uml.property  name="original"
	 */
    public boolean getOriginal() {
        return original;
    }

    /**
	 * @return
	 * @uml.property  name="layer"
	 */
    public String getLayer() {
        return layer;
    }

    /**
	 * @return
	 * @uml.property  name="tamano"
	 */
    public long getTamano() {
        return tamano;
    }

    public String getDireccion() {
        return location;
    }

    private void loadInfo(File input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
    }

    private void loadInfo(InputStream input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
    }

    private void loadInfo(URL input) throws UnsupportedAudioFileException, IOException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
        loadShoutastInfo(aff);
    }

    private void loadInfo(AudioFileFormat aff) throws UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("mp3")) {
            throw new UnsupportedAudioFileException("Not MP3 audio format");
        }
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            if (props.containsKey("mp3.channels")) {
                canales = ((Integer) props.get("mp3.channels")).intValue();
            }
            if (props.containsKey("mp3.frequency.hz")) {
                rate = ((Integer) props.get("mp3.frequency.hz")).intValue();
            }
            if (props.containsKey("mp3.bitrate.nominal.bps")) {
                nominalbitrate = ((Integer) props.get("mp3.bitrate.nominal.bps")).intValue();
            }
            if (props.containsKey("mp3.version.layer")) {
                layer = "Layer " + props.get("mp3.version.layer");
            }
            if (props.containsKey("mp3.version.mpeg")) {
                version = (String) props.get("mp3.version.mpeg");
                if (version.equals("1")) {
                    version = "MPEG1";
                } else if (version.equals("2")) {
                    version = "MPEG2-LSF";
                } else if (version.equals("2.5")) {
                    version = "MPEG2.5-LSF";
                }
            }
            if (props.containsKey("mp3.mode")) {
                int mode = ((Integer) props.get("mp3.mode")).intValue();
                if (mode == 0) {
                    channelsMode = "Stereo";
                } else if (mode == 1) {
                    channelsMode = "Joint Stereo";
                } else if (mode == 2) {
                    channelsMode = "Dual Channel";
                } else if (mode == 3) {
                    channelsMode = "Single Channel";
                }
            }
            if (props.containsKey("mp3.crc")) {
                crc = ((Boolean) props.get("mp3.crc")).booleanValue();
            }
            if (props.containsKey("mp3.vbr")) {
                vbr = ((Boolean) props.get("mp3.vbr")).booleanValue();
            }
            if (props.containsKey("mp3.copyright")) {
                copyright = ((Boolean) props.get("mp3.copyright")).booleanValue();
            }
            if (props.containsKey("mp3.original")) {
                original = ((Boolean) props.get("mp3.original")).booleanValue();
            }
            emphasis = "none";
            if (props.containsKey("title")) {
                titulo = (String) props.get("title");
            }
            if (props.containsKey("author")) {
                artista = (String) props.get("author");
            }
            if (props.containsKey("album")) {
                album = (String) props.get("album");
            }
            if (props.containsKey("date")) {
                anio = (String) props.get("date");
            }
            if (props.containsKey("duration")) {
                milisegundos=(Long) props.get("duration");
                segundos=(((int) ((Long) props.get("duration") / 10000)) / 100.0);
            }
            if (props.containsKey("mp3.id3tag.genre")) {
                genero = (String) props.get("mp3.id3tag.genre");
            }
            if (props.containsKey("mp3.id3tag.track")) {
                try {
                    track = Integer.parseInt((String) props.get("mp3.id3tag.track"));
                } catch (NumberFormatException e1) {
                    // Not a number
                }
            }
        }
    }

    protected void loadShoutastInfo(AudioFileFormat aff) throws IOException, UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("mp3")) throw new UnsupportedAudioFileException("Not MP3 audio format");
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            Iterator it = props.keySet().iterator();
            while (it.hasNext()) {
                String key = (String) it.next();
                if (key.startsWith("mp3.shoutcast.metadata.")) {
                    String value = (String) props.get(key);
                    key = key.substring(23, key.length());
                    if (key.equalsIgnoreCase("icy-name")) {
                        titulo = value;
                    } else if (key.equalsIgnoreCase("icy-genre")) {
                        genero = value;
                    }
                }
            }
        }
    }

    /**
	 * @return  the milisegundos
	 * @uml.property  name="milisegundos"
	 */
    public long getMilisegundos() {
        return milisegundos;
    }

    /**
	 * @return  the channelsMode
	 * @uml.property  name="channelsMode"
	 */
    public String getChannelsMode() {
        return channelsMode;
    }
}
