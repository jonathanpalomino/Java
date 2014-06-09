
package palomino.multimedia.audio.infos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

/**
 * This class gives information (audio format and comments) about APE file or URL.
 */
public class APEInfo implements Informacion {

    /**
	 * @uml.property  name="channels"
	 */
    protected int channels = -1;
    /**
	 * @uml.property  name="bitspersample"
	 */
    protected int bitspersample = -1;
    /**
	 * @uml.property  name="samplerate"
	 */
    protected int samplerate = -1;
    /**
	 * @uml.property  name="bitrate"
	 */
    protected int bitrate = -1;
    /**
	 * @uml.property  name="version"
	 */
    protected int version = -1;
    /**
	 * @uml.property  name="compressionlevel"
	 */
    protected String compressionlevel = null;
    /**
	 * @uml.property  name="totalframes"
	 */
    protected int totalframes = -1;
    /**
	 * @uml.property  name="blocksperframe"
	 */
    protected int blocksperframe = -1;
    /**
	 * @uml.property  name="finalframeblocks"
	 */
    protected int finalframeblocks = -1;
    /**
	 * @uml.property  name="totalblocks"
	 */
    protected int totalblocks = -1;
    /**
	 * @uml.property  name="peaklevel"
	 */
    protected int peaklevel = -1;
    /**
	 * @uml.property  name="segundos"
	 */
    private double segundos = 0.0;
    /**
	 * @uml.property  name="milisegundos"
	 */
    private long milisegundos = 0;
    /**
	 * @uml.property  name="author"
	 */
    protected String author = null;
    /**
	 * @uml.property  name="title"
	 */
    protected String title = null;
    /**
	 * @uml.property  name="copyright"
	 */
    protected String copyright = null;
    /**
	 * @uml.property  name="date"
	 */
    protected Date date = null;
    /**
	 * @uml.property  name="comment"
	 */
    protected String comment = null;
    /**
	 * @uml.property  name="track"
	 */
    protected String track = null;
    /**
	 * @uml.property  name="genre"
	 */
    protected String genre = null;
    /**
	 * @uml.property  name="album"
	 */
    protected String album = null;
    /**
	 * @uml.property  name="size"
	 */
    protected long size = 0;
    /**
	 * @uml.property  name="location"
	 */
    protected String location = null;

    /**
     * Constructor.
     */
    public APEInfo() {
        super();
    }

    /**
     * Load and parse APE info from File.
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
     * Load and parse APE info from URL.
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
     * Load and parse APE info from InputStream.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException {
        loadInfo(input);
    }

    /**
     * Load APE info from input stream.
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
     * Load APE info from file.
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
     * Load APE info from AudioFileFormat.
     *
     * @param aff
     */
    protected void loadInfo(AudioFileFormat aff) throws UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("Monkey's Audio (ape)") && !type.equalsIgnoreCase("Monkey's Audio (mac)")) {
            throw new UnsupportedAudioFileException("Not APE audio format");
        }
        if (aff instanceof TAudioFileFormat) {
            Map props = ((TAudioFileFormat) aff).properties();
            if (props.containsKey("duration")) {
                milisegundos = (Long) props.get("duration");
                segundos = (((int) ((Long) props.get("duration") / 10000)) / 100.0);
            }
            if (props.containsKey("author")) {
                author = (String) props.get("author");
            }
            if (props.containsKey("title")) {
                title = (String) props.get("title");
            }
            if (props.containsKey("copyright")) {
                copyright = (String) props.get("copyright");
            }
            if (props.containsKey("date")) {
                date = (Date) props.get("date");
            }
            if (props.containsKey("comment")) {
                comment = (String) props.get("comment");
            }
            if (props.containsKey("album")) {
                album = (String) props.get("album");
            }
            if (props.containsKey("track")) {
                track = (String) props.get("track");
            }
            if (props.containsKey("genre")) {
                genre = (String) props.get("genre");
            }
            AudioFormat af = aff.getFormat();
            channels = af.getChannels();
            samplerate = (int) af.getSampleRate();
            bitspersample = af.getSampleSizeInBits();
            if (af instanceof TAudioFormat) {
                props = ((TAudioFormat) af).properties();
                if (props.containsKey("bitrate")) {
                    bitrate = ((Integer) props.get("bitrate")).intValue();
                }
                if (props.containsKey("ape.version")) {
                    version = ((Integer) props.get("ape.version")).intValue();
                }
                if (props.containsKey("ape.compressionlevel")) {
                    int cl = ((Integer) props.get("ape.compressionlevel")).intValue();
                    switch (cl) {
                        case 1000:
                            compressionlevel = "Fast";
                            break;
                        case 2000:
                            compressionlevel = "Normal";
                            break;
                        case 3000:
                            compressionlevel = "High";
                            break;
                        case 4000:
                            compressionlevel = "Extra High";
                            break;
                        case 5000:
                            compressionlevel = "Insane";
                            break;
                    }
                }
                if (props.containsKey("ape.totalframes")) {
                    totalframes = ((Integer) props.get("ape.totalframes")).intValue();
                }
                if (props.containsKey("ape.blocksperframe")) {
                    totalframes = ((Integer) props.get("ape.blocksperframe")).intValue();
                }
                if (props.containsKey("ape.finalframeblocks")) {
                    finalframeblocks = ((Integer) props.get("ape.finalframeblocks")).intValue();
                }
                if (props.containsKey("ape.totalblocks")) {
                    totalblocks = ((Integer) props.get("ape.totalblocks")).intValue();
                }
                if (props.containsKey("ape.peaklevel")) {
                    peaklevel = ((Integer) props.get("ape.peaklevel")).intValue();
                }
            }
        }
    }

    /**
     * Load APE info from URL.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    protected void loadInfo(URL input) throws IOException, UnsupportedAudioFileException {
        AudioFileFormat aff = AudioSystem.getAudioFileFormat(input);
        loadInfo(aff);
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
	 * @uml.property  name="location"
	 */
    public String getLocation() {
        return location;
    }

    /**
	 * @return
	 * @uml.property  name="version"
	 */
    public int getVersion() {
        return version;
    }

    /**
	 * @return
	 * @uml.property  name="compressionlevel"
	 */
    public String getCompressionlevel() {
        return compressionlevel;
    }

    /**
	 * @return
	 * @uml.property  name="totalframes"
	 */
    public int getTotalframes() {
        return totalframes;
    }

    /**
	 * @return
	 * @uml.property  name="blocksperframe"
	 */
    public int getBlocksperframe() {
        return blocksperframe;
    }

    /**
	 * @return
	 * @uml.property  name="finalframeblocks"
	 */
    public int getFinalframeblocks() {
        return finalframeblocks;
    }

    public int getSamplingRate() {
        return samplerate;
    }

    public int getBitsPerSample() {
        return bitspersample;
    }

    /**
	 * @return
	 * @uml.property  name="totalblocks"
	 */
    public int getTotalblocks() {
        return totalblocks;
    }

    public int getBitRate() {
        return bitrate * 1000;
    }

    /**
	 * @return
	 * @uml.property  name="peaklevel"
	 */
    public int getPeaklevel() {
        return peaklevel;
    }

    /**
	 * @return
	 * @uml.property  name="track"
	 */
    public int getTrack() {
        int t;
        try {
            t = Integer.parseInt(track);
        } catch (Exception e) {
            t = -1;
        }
        return t;
    }

    public String getanio() {
        if (date != null) {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            return String.valueOf(c.get(Calendar.YEAR));
        }
        return null;
    }

    public String getGenero() {
        return genre;
    }

    public String getTitulo() {
        return title;
    }

    public String getArtista() {
        return author;
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
	 * @uml.property  name="comment"
	 */
    public Vector getComment() {
        if (comment != null) {
            Vector c = new Vector();
            c.add(comment);
            return c;
        }
        return null;
    }

    /**
	 * @return  the milisegundos
	 * @uml.property  name="milisegundos"
	 */
    public long getMilisegundos() {
        return milisegundos;
    }

    /**
	 * @return
	 * @uml.property  name="copyright"
	 */
    public String getCopyright() {
        return copyright;
    }

    public int getCanales() {
        return channels;
    }

    public double getTiempo_en_segundos() {
        return segundos;
    }
}
