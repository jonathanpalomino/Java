
package palomino.multimedia.audio.infos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.Vector;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * This class gives information (audio format and comments) about Ogg Vorbis file or URL.
 */
public class OggVorbisInfo implements Informacion {

    /**
	 * @uml.property  name="serial"
	 */
    protected int serial = 0;
    /**
	 * @uml.property  name="channels"
	 */
    protected int channels = 0;
    /**
	 * @uml.property  name="version"
	 */
    protected int version = 0;
    /**
	 * @uml.property  name="rate"
	 */
    protected int rate = 0;
    /**
	 * @uml.property  name="minbitrate"
	 */
    protected int minbitrate = 0;
    /**
	 * @uml.property  name="maxbitrate"
	 */
    protected int maxbitrate = 0;
    /**
	 * @uml.property  name="averagebitrate"
	 */
    protected int averagebitrate = 0;
    /**
	 * @uml.property  name="nominalbitrate"
	 */
    protected int nominalbitrate = 0;
    /**
	 * @uml.property  name="totalms"
	 */
    protected long totalms = 0;
    /**
	 * @uml.property  name="milisegundos"
	 */
    private long milisegundos = 0;
    /**
	 * @uml.property  name="segundos"
	 */
    private double segundos = 0.0;
    /**
	 * @uml.property  name="vendor"
	 */
    protected String vendor = "";
    /**
	 * @uml.property  name="location"
	 */
    protected String location = null;
    /**
	 * @uml.property  name="size"
	 */
    protected long size = 0;
    /**
	 * @uml.property  name="track"
	 */
    protected int track = -1;
    /**
	 * @uml.property  name="year"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.util.Vector"
	 */
    protected String year = null;
    /**
	 * @uml.property  name="genre"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.util.Vector"
	 */
    protected String genre = null;
    /**
	 * @uml.property  name="title"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.util.Vector"
	 */
    protected String title = null;
    /**
	 * @uml.property  name="artist"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.util.Vector"
	 */
    protected String artist = null;
    /**
	 * @uml.property  name="album"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="java.util.Vector"
	 */
    protected String album = null;
    /**
	 * @uml.property  name="comments"
	 */
    protected Vector comments = new Vector();

    /**
     * Constructor.
     */
    public OggVorbisInfo() {
        super();
    }

    /**
     * Load and parse Ogg Vorbis info from File.
     *
     * @param input
     * @throws IOException
     */
    public void load(File input) throws IOException, UnsupportedAudioFileException {
        size = input.length();
        location = input.getPath();
        loadInfo(input);
    }

    /**
     * Load and parse Ogg Vorbis info from URL.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void load(URL input) throws IOException, UnsupportedAudioFileException {
        location = input.toString();
        loadInfo(input);
    }

    /**
     * Load and parse Ogg Vorbis info from InputStream.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException {
        loadInfo(input);
    }

    /**
     * Load info from input stream.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void loadInfo(InputStream input) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
    }

    /**
     * Load Ogg Vorbis info from file.
     *
     * @param file
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void loadInfo(File file) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(file);
        loadInfo(aff);
    }

    /**
     * Load Ogg Vorbis info from URL.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void loadInfo(URL input) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
        loadExtendedInfo(aff);
    }

    /**
     * Load info from AudioFileFormat.
     *
     * @param aff
     * @throws UnsupportedAudioFileException
     */
    protected void loadInfo(AudioFileFormat aff) throws UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("ogg")) {
            throw new UnsupportedAudioFileException("Not Ogg Vorbis audio format");
        }
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            if (props.containsKey("ogg.channels")) {
                channels = ((Integer) props.get("ogg.channels")).intValue();
            }
            if (props.containsKey("ogg.frequency.hz")) {
                rate = ((Integer) props.get("ogg.frequency.hz")).intValue();
            }
            if (props.containsKey("ogg.bitrate.nominal.bps")) {
                nominalbitrate = ((Integer) props.get("ogg.bitrate.nominal.bps")).intValue();
            }
            averagebitrate = nominalbitrate;
            if (props.containsKey("ogg.bitrate.max.bps")) {
                maxbitrate = ((Integer) props.get("ogg.bitrate.max.bps")).intValue();
            }
            if (props.containsKey("ogg.bitrate.min.bps")) {
                minbitrate = ((Integer) props.get("ogg.bitrate.min.bps")).intValue();
            }
            if (props.containsKey("ogg.version")) {
                version = ((Integer) props.get("ogg.version")).intValue();
            }
            if (props.containsKey("ogg.serial")) {
                serial = ((Integer) props.get("ogg.serial")).intValue();
            }
            if (props.containsKey("ogg.comment.encodedby")) {
                vendor = (String) props.get("ogg.comment.encodedby");
            }
            if (props.containsKey("copyright")) {
                comments.add((String) props.get("copyright"));
            }
            if (props.containsKey("title")) {
                title = (String) props.get("title");
            }
            if (props.containsKey("author")) {
                artist = (String) props.get("author");
            }
            if (props.containsKey("album")) {
                album = (String) props.get("album");
            }
            if (props.containsKey("date")) {
                year = (String) props.get("date");
            }
            if (props.containsKey("comment")) {
                comments.add((String) props.get("comment"));
            }
            if (props.containsKey("duration")) {

                milisegundos = (Long) props.get("duration");
                segundos = (((int) ((Long) props.get("duration") / 10000)) / 100.0);
            }
            if (props.containsKey("ogg.comment.genre")) {
                genre = (String) props.get("ogg.comment.genre");
            }
            if (props.containsKey("ogg.comment.track")) {
                try {
                    track = Integer.parseInt((String) props.get("ogg.comment.track"));
                } catch (NumberFormatException e1) {
                    // Not a number
                }
            }
            if (props.containsKey("ogg.comment.ext.1")) {
                comments.add((String) props.get("ogg.comment.ext.1"));
            }
            if (props.containsKey("ogg.comment.ext.2")) {
                comments.add((String) props.get("ogg.comment.ext.2"));
            }
            if (props.containsKey("ogg.comment.ext.3")) {
                comments.add((String) props.get("ogg.comment.ext.3"));
            }
        }
    }

    /**
     * Load extended info from AudioFileFormat.
     *
     * @param aff
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void loadExtendedInfo(AudioFileFormat aff) throws IOException, UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("ogg")) {
            throw new UnsupportedAudioFileException("Not Ogg Vorbis audio format");
        }
        if (aff instanceof TAudioFileFormat) {
            //Map props = ((TAudioFileFormat) aff).properties();
            // How to load icecast meta data (if any) ??
        }
    }

    /**
	 * @return
	 * @uml.property  name="serial"
	 */
    public int getSerial() {
        return serial;
    }

    /**
	 * @return
	 * @uml.property  name="version"
	 */
    public int getVersion() {
        return version;
    }

    public int getMinBitrate() {
        return minbitrate;
    }

    public int getMaxBitrate() {
        return maxbitrate;
    }

    public int getAverageBitrate() {
        return averagebitrate;
    }

    /**
	 * @return
	 * @uml.property  name="size"
	 */
    public long getSize() {
        return size;
    }

    /**
	 * @return
	 * @uml.property  name="vendor"
	 */
    public String getVendor() {
        return vendor;
    }

    /**
	 * @return
	 * @uml.property  name="location"
	 */
    public String getLocation() {
        return location;
    }

    /*-- TagInfo Implementation --*/
    public int getSamplingRate() {
        return rate;
    }

    public int getBitRate() {
        return nominalbitrate;
    }

    public long getPlayTime() {
        return totalms;
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

    public Vector getComment() {
        return comments;
    }

    /**
	 * @return  the milisegundos
	 * @uml.property  name="milisegundos"
	 */
    public long getMilisegundos() {
        return milisegundos;
    }

    public int getCanales() {
        return channels;
    }

    public double getTiempo_en_segundos() {
        return segundos;
    }

    public String getTitulo() {
        return title;
    }

    public String getArtista() {
        return artist;
    }

    public String getGenero() {
        return genre;
    }

    public String getanio() {
        return year;
    }
}
