/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ import javax.sound.sampled.AudioFileFormat;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioFormat.Encoding;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.UnsupportedAudioFileException;
/*     */ 
/*     */ public class AudioFileIn
{
    private String fileName;
    private File file;
    private AudioFileFormat fileFormat;
    private AudioFormat format;
    private boolean bigEndian;
    private int channels;
    private int sampleRate;
    private long duration;
    private int sampleSize;
    private AudioInputStream is;
    private float[] sampleData;
    private boolean audioFileSpecified = true;

    public AudioFileIn(String string) {
	fileName = string;
	try {
	    file = new File(string);
	    fileFormat = AudioSystem.getAudioFileFormat(file);
	    format = fileFormat.getFormat();
	    bigEndian = format.isBigEndian();
	    channels = format.getChannels();
	    sampleRate = (int) format.getSampleRate();
	    duration = (long) fileFormat.getFrameLength() * (long) channels;
	    sampleSize = format.getSampleSizeInBits() / 8;
	} catch (UnsupportedAudioFileException unsupportedaudiofileexception) {
	    System.err.println("jMusic AudioFileIn warning: '" + string
			       + "' may not be an audio file.");
	    System.err.println("Reading it in as raw data...");
	    audioFileSpecified = false;
	    channels = 1;
	    sampleSize = 1;
	    sampleRate = 0;
	} catch (IOException ioexception) {
	    System.err.println
		("jMusic AudioFileIn error: Cannot read the specified file: "
		 + string);
	    System.err.println
		("Most likely the file does not exist at this location. Exiting...");
	    System.exit(0);
	}
    }

    private void readFile() {
	if (audioFileSpecified) {
	    try {
		this.is = AudioSystem.getAudioInputStream(file);
		byte[] is = new byte[(int) duration * sampleSize];
		this.is.read(is);
		this.is.close();
		ByteArrayInputStream bytearrayinputstream
		    = new ByteArrayInputStream(is);
		sampleData = new float[(int) duration];
		byte[] is_0_ = new byte[sampleSize];
		for (int i = 0; (long) i < duration; i++) {
		    if (bytearrayinputstream.read(is_0_) == -1)
			System.out.println("Ran out of samples to read");
		    else
			sampleData[i] = getFloat(is_0_);
		}
		bytearrayinputstream.close();
	    } catch (UnsupportedAudioFileException unsupportedaudiofileexception) {
		/* empty */
	    } catch (IOException ioexception) {
		ioexception.printStackTrace();
	    }
	} else {
	    Vector vector = new Vector();
	    try {
		FileInputStream fileinputstream
		    = new FileInputStream(fileName);
		for (int i = fileinputstream.read(); i != -1;
		     i = fileinputstream.read())
		    vector.addElement(new Float((float) i / 255.0F));
		fileinputstream.close();
	    } catch (IOException ioexception) {
		ioexception.printStackTrace();
		System.exit(1);
	    }
	    int i = vector.size();
	    sampleData = new float[i];
	    for (int i_1_ = 0; i_1_ < i; i_1_++)
		sampleData[i_1_]
		    = ((Float) vector.elementAt(i_1_)).floatValue();
	}
    }

    public int getBitResolution() {
	int i = -1;
	switch (sampleSize) {
	case 1:
	    i = 8;
	    break;
	case 2:
	    i = 16;
	    break;
	case 3:
	    i = 24;
	    break;
	case 4:
	    i = 32;
	    break;
	}
	return i;
    }

    private float getFloat(byte[] is) {
	float f = 0.0F;
	int i = 0;
	int i_2_ = is.length;
	int i_3_ = 0;
	while (i_3_ < is.length) {
	    i = i | (is[i_3_] & 0xff) << (bigEndian ? i_2_ : i_3_ + 1) * 8 - 8;
	    i_3_++;
	    i_2_--;
	}
	switch (sampleSize) {
	case 1:
	    if (i > 127) {
		i = (i ^ 0xffffffff) + 1;
		i &= 0x7f;
		i = (i ^ 0xffffffff) + 1;
	    }
	    f = (float) i / 127.0F;
	    break;
	case 2:
	    if (i > 32767) {
		i = (i ^ 0xffffffff) + 1;
		i &= 0x7fff;
		i = (i ^ 0xffffffff) + 1;
	    }
	    f = (float) i / 32767.0F;
	    break;
	case 3:
	    if (i > 8388607) {
		i = (i ^ 0xffffffff) + 1;
		i &= 0x7fffff;
		i = (i ^ 0xffffffff) + 1;
	    }
	    f = (float) i / 8388608.0F;
	    break;
	case 4:
	    f = (float) ((double) i / 2.147483647E9);
	    break;
	default:
	    System.err.println("Format not accepted");
	}
	return f;
    }

    public float[] getSampleData() {
	readFile();
	return sampleData;
    }

    public int getChannels() {
	return channels;
    }

    public String getFileType() {
	if (audioFileSpecified)
	    return fileFormat.toString();
	return new String("Non-audio");
    }

    public int getSampleRate() {
	return sampleRate;
    }

    public int getSampleBitDepth() {
	return sampleSize * 8;
    }

    public String getEncoding() {
	return format.getEncoding().toString();
    }

    public int getDuration() {
	return (int) duration;
    }
}
