/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import vena.JMC;
/*     */ import ven.audio.AOException;
/*     */ import ven.audio.AudioObject;
/*     */ 
public final class PrintOut extends AudioObject implements JMC
{
    private int width;

    public PrintOut(AudioObject audioobject) {
	this(audioobject, 80);
    }

    public PrintOut(AudioObject audioobject, int i) {
	super(audioobject, "[PrintOut]");
	width = i;
	try {
	    RandomAccessFile randomaccessfile
		= new RandomAccessFile("jmusic.tmp", "rw");
	    try {
		randomaccessfile.close();
	    } catch (IOException ioexception) {
		/* empty */
	    }
	} catch (IOException ioexception) {
	    /* empty */
	}
    }

    public void finalize() {
	/* empty */
    }

    public int work(float[] fs) throws AOException {
	int i = previous[0].nextWork(fs);
	for (int i_0_ = 0; i_0_ < i; i_0_++) {
	    float f = fs[i_0_];
	    String string = "";
	    if (i_0_ % ((int) ((double) sampleRate / 8000.0) + 1) == 0) {
		int i_1_ = 0;
		for (int i_2_ = 0;
		     i_2_ < (int) (((double) f + 1.0)
				   * ((double) width * 0.5 - 4.0));
		     i_2_++) {
		    string += " ";
		    i_1_++;
		}
		string += "o";
		for (int i_3_ = i_1_; i_3_ < width - 4; i_3_++)
		    string += " ";
		f *= 1000.0F;
		string += (double) (int) f / 1000.0;
		System.out.println(string);
	    }
	}
	return i;
    }
}