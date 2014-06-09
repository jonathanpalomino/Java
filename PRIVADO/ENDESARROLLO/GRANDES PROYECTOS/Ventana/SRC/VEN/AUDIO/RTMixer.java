/*     */ package ven.audio;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioFormat.Encoding;
/*     */ import javax.sound.sampled.AudioSystem;
          import javax.sound.sampled.DataLine;
/*     */ import javax.sound.sampled.DataLine.Info;
/*     */ import javax.sound.sampled.SourceDataLine;
/*     */ import ven.music.rt.RTLine;
/*     */ 
public class RTMixer implements AudioChainListener
{
    private int totLines = 0;
    private int count = 0;
    private float[] sampleArray;
    private ByteArrayOutputStream bos;
    private DataOutputStream dos;
    private SourceDataLine dline;
    protected int sampleRate;
    protected int channels;
    public long currentTime = 0L;
    protected double controlRate;
    private double scorePosition = 0.0;
    private RTLine[] rtlines;
    private int bufferSize;
    /*synthetic*/ static Class class$javax$sound$sampled$SourceDataLine;

    public RTMixer(RTLine[] rtlines) {
	this(rtlines, 0.1);
    }

    public RTMixer(RTLine[] rtlines, double d) {
	this.rtlines = rtlines;
	sampleRate = rtlines[0].getSampleRate();
	channels = rtlines[0].getChannels();
	controlRate = d;
	for (bufferSize
		 = (int) ((double) (sampleRate * channels) * controlRate);
	     bufferSize % 4 != 0;
	     bufferSize
		 = (int) ((double) (sampleRate * channels) * controlRate))
	    controlRate += 0.0010;
	for (int i = 0; i < rtlines.length; i++) {
	    totLines += this.rtlines[i].getNumLines();
	    this.rtlines[i].setBufferSize(bufferSize);
	    if (rtlines[i].getSampleRate() != sampleRate) {
		System.err.println
		    ("jMusic RTMixer error: All instruments must have the same sample rate.");
		System.exit(0);
	    }
	    if (rtlines[i].getChannels() != channels) {
		System.err.println
		    ("jMusic RTMixer error: All instruments must have the same number of channels.");
		System.exit(0);
	    }
	}
	initJMFSound(bufferSize);
	bos = new ByteArrayOutputStream();
	dos = new DataOutputStream(bos);
    }

    public synchronized void controlChange(float[] fs, int i, boolean bool) {
	for (int i_0_ = 0; i_0_ < i; i_0_++)
	    sampleArray[i_0_] += fs[i_0_];
	if (++count == totLines) {
	    scorePosition += controlRate;
	    for (int i_1_ = 0; i_1_ < rtlines.length; i_1_++) {
		Instrument[] instruments = rtlines[i_1_].getInstrument();
		for (int i_2_ = 0; i_2_ < instruments.length; i_2_++)
		    instruments[i_2_].release();
	    }
	    count = 0;
	    writeOutAudio(sampleArray.length);
	}
    }

    public void begin() {
	sampleArray = new float[bufferSize];
	for (int i = 0; i < rtlines.length; i++)
	    rtlines[i].start(scorePosition, this);
    }

    public void pause() {
	for (int i = 0; i < rtlines.length; i++)
	    rtlines[i].pause();
    }

    public void unPause() {
	for (int i = 0; i < rtlines.length; i++)
	    rtlines[i].unPause();
    }

    public void actionLines(Object object, int i) {
	for (int i_3_ = 0; i_3_ < rtlines.length; i_3_++)
	    rtlines[i_3_].externalAction(object, i);
    }

    private void writeOutAudio(int i) {
	bos.reset();
	for (int i_4_ = 0; i_4_ < i; i_4_++) {
	    if (totLines > 1)
		sampleArray[i_4_]
		    = sampleArray[i_4_] / ((float) totLines * 0.75F);
	    try {
		dos.writeShort((short) (int) (sampleArray[i_4_] * 32767.0F));
	    } catch (IOException ioexception) {
		ioexception.printStackTrace();
	    }
	    sampleArray[i_4_] = 0.0F;
	}
	int i_5_ = dline.write(bos.toByteArray(), 0, bos.size());
	currentTime += (long) i;
    }

    private void initJMFSound(int i) {
	AudioFormat audioformat
	    = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
			      (float) sampleRate, 16, channels, channels * 2,
			      (float) sampleRate, true);
	DataLine.Info info
	    = (new DataLine.Info
	       ((class$javax$sound$sampled$SourceDataLine == null
		 ? (class$javax$sound$sampled$SourceDataLine
		    = class$("javax.sound.sampled.SourceDataLine"))
		 : class$javax$sound$sampled$SourceDataLine),
		audioformat));
	if (!AudioSystem.isLineSupported(info)) {
	    System.out.println(info);
	    System.err.println
		("jMusic RTMixer error: JMF Line not supported. Real time audio must be 16 bit stereo ... exiting .. so there : (");
	    System.exit(1);
	}
	try {
	    dline = (SourceDataLine) AudioSystem.getLine(info);
	    dline.open(audioformat, i * 8);
	    dline.start();
	} catch (Exception exception) {
	    exception.printStackTrace();
	}
    }

    public void finalize() {
	try {
	    dos.close();
	    bos.close();
	} catch (IOException ioexception) {
	    /* empty */
	}
    }

    /*synthetic*/ static Class class$(String string) {
	Class var_class;
	try {
	    var_class = Class.forName(string);
	} catch (ClassNotFoundException classnotfoundexception) {
	    throw new NoClassDefFoundError(classnotfoundexception
					       .getMessage());
	}
	return var_class;
    }
}
