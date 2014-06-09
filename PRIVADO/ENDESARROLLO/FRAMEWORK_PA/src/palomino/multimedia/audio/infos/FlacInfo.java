package palomino.multimedia.audio.infos;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * This class gives information (audio format and comments) about Flac file or URL.
 */
public class FlacInfo implements Informacion {

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
	 * @uml.property  name="size"
	 */
    protected long size = 0;
    /**
	 * @uml.property  name="location"
	 */
    protected String location = null;
    /**
	 * @uml.property  name="milisegundos"
	 */
    private long milisegundos = 0;

    /**
     * Constructor.
     */
    public FlacInfo() {
        super();
    }

    /**
     * Load and parse Flac info from File.
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
     * Load and parse Flac info from URL.
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
     * Load and parse Flac info from InputStream.
     *
     * @param input
     * @throws IOException
     * @throws UnsupportedAudioFileException
     */
    public void load(InputStream input) throws IOException, UnsupportedAudioFileException {
        loadInfo(input);
    }

    /**
     * Load Flac info from input stream.
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
     * Load Flac info from file.
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
     * Load Flac info from AudioFileFormat.
     *
     * @param aff
     */
    protected void loadInfo(AudioFileFormat aff) throws UnsupportedAudioFileException {
        String type = aff.getType().toString();
        if (!type.equalsIgnoreCase("flac")) {
            throw new UnsupportedAudioFileException("Not Flac audio format");
        }
        AudioFormat af = aff.getFormat();
        channels = af.getChannels();
        samplerate = (int) af.getSampleRate();
        bitspersample = af.getSampleSizeInBits();
    }

    /**
     * Load Flac info from URL.
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

    public int getCanales() {
        return channels;
    }

    public int getSamplingRate() {
        return samplerate;
    }

    public int getBitsPerSample() {
        return bitspersample;
    }

    public Vector getComment() {
        return null;
    }

    public String getanio() {
        return null;
    }

    public String getGenero() {
        return null;
    }

    public int getTrack() {
        return -1;
    }

    public String getAlbum() {
        return null;
    }

    public String getArtista() {
        return null;
    }

    public String getTitulo() {
        return null;
    }

    public long getPlayTime() {
        return -1;
    }

    public int getBitRate() {
        return -1;
    }

    /**
	 * @return
	 * @uml.property  name="milisegundos"
	 */
    public long getMilisegundos() {
        return milisegundos;
    }

    public double getTiempo_en_segundos() {
        return -1;
    }

}
