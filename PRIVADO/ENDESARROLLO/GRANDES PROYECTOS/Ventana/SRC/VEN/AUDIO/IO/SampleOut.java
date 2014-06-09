/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.EOFException;
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import vena.JMC;
/*     */ import ven.audio.AOException;
/*     */ import ven.audio.AudioObject;
/*     */ import ven.audio.Instrument;
/*     */ 
/*     */ public final class SampleOut extends AudioObject implements JMC
{
    private boolean sync;
    public static float max = 0.0F;
    public static int numofchan;
    public static int samprate;
    private RandomAccessFile raf;
    private int position = 0;
    private int count;
    private ByteArrayOutputStream bos;
    private DataOutputStream dos;
    private boolean DEBUG_AU;
    private String fileName;
    private int size;

    public SampleOut(AudioObject audioobject) {
	super(audioobject, "[SampleOut]");
	bos = new ByteArrayOutputStream();
	dos = new DataOutputStream(bos);
	DEBUG_AU = false;
	size = 0;
	sync = true;
	fileName = "jmusic.tmp";
	try {
	    raf = new RandomAccessFile(fileName, "rw");
	} catch (IOException ioexception) {
	    System.out.println(ioexception);
	}
    }

    public SampleOut(AudioObject audioobject, String string) {
	super(audioobject, "[SampleOut]");
	bos = new ByteArrayOutputStream();
	dos = new DataOutputStream(bos);
	DEBUG_AU = false;
	size = 0;
	sync = true;
	fileName = string;
	try {
	    raf = new RandomAccessFile(fileName, "rw");
	} catch (IOException ioexception) {
	    System.out.println(ioexception);
	}
    }

    public SampleOut(AudioObject audioobject, String string, boolean bool) {
	super(audioobject, "[SampleOut]");
	bos = new ByteArrayOutputStream();
	dos = new DataOutputStream(bos);
	DEBUG_AU = false;
	size = 0;
	sync = bool;
	fileName = string;
	try {
	    raf = new RandomAccessFile(fileName, "rw");
	} catch (IOException ioexception) {
	    System.out.println(ioexception);
	}
    }

    public SampleOut(AudioObject audioobject, String string, int i,
		     boolean bool) {
	super(audioobject, "[SampleOut]");
	bos = new ByteArrayOutputStream();
	dos = new DataOutputStream(bos);
	DEBUG_AU = false;
	size = 0;
	sync = bool;
	position = i;
	fileName = string;
	try {
	    raf = new RandomAccessFile(fileName, "rw");
	} catch (IOException ioexception) {
	    System.out.println(ioexception);
	}
    }

    public void finalize() {
	/* empty */
    }

    public void build() {
	if (sync) {
	    try {
		raf.getFD().sync();
	    } catch (IOException ioexception) {
		ioexception.printStackTrace();
	    }
	}
	position = ((int) (currentNoteStartTime * (double) sampleRate) * 4
		    * channels);
	if (position < 0)
	    position = 0;
	samprate = sampleRate;
	numofchan = channels;
	finished = false;
    }

    public int work(float[] fs) throws AOException {
	if (inst.iterations < 0)
	    finished = true;
	int i = previous[0].nextWork(fs);
	for (int i_0_ = 0; i_0_ < i; i_0_++) {
	    float f = fs[i_0_];
	    if (max < Math.abs(f))
		max = Math.abs(f);
	    try {
		dos.writeFloat(f);
	    } catch (IOException ioexception) {
		throw new AOException(name, ioexception.toString());
	    }
	}
	write(i);
	return i;
    }

    private void write(int i) {
	int i_1_ = i;
	Object object = null;
	Object object_2_ = null;
	Object object_3_ = null;
	Object object_4_ = null;
	int i_5_ = i_1_ * 4;
	try {
	    raf.seek((long) position);
	    float f = raf.readFloat();
	    raf.seek((long) position);
	    byte[] is = new byte[i_5_];
	    int i_6_ = raf.read(is);
	    ByteArrayInputStream bytearrayinputstream
		= new ByteArrayInputStream(is);
	    DataInputStream datainputstream
		= new DataInputStream(bytearrayinputstream);
	    ByteArrayInputStream bytearrayinputstream_7_
		= new ByteArrayInputStream(bos.toByteArray());
	    DataInputStream datainputstream_8_
		= new DataInputStream(bytearrayinputstream_7_);
	    bos.reset();
	    float[] fs = new float[i_1_];
	    for (int i_9_ = 0; i_9_ < i_1_; i_9_++) {
		fs[i_9_] = (datainputstream.readFloat()
			    + datainputstream_8_.readFloat());
		if (max < Math.abs(fs[i_9_]))
		    max = Math.abs(fs[i_9_]);
		dos.writeFloat(fs[i_9_]);
	    }
	    dos.flush();
	    raf.seek((long) position);
	    raf.write(bos.toByteArray());
	    bos.reset();
	    position += i_5_;
	    datainputstream.close();
	    bytearrayinputstream.close();
	    datainputstream_8_.close();
	    bytearrayinputstream_7_.close();
	} catch (EOFException eofexception) {
	    try {
		raf.seek((long) position);
		raf.write(bos.toByteArray());
		bos.reset();
		position += i_5_;
	    } catch (IOException ioexception) {
		ioexception.printStackTrace();
	    }
	} catch (IOException ioexception) {
	    ioexception.printStackTrace();
	}
    }
}