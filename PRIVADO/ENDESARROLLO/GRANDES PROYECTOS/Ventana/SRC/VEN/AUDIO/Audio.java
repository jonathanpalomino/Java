package ven.audio;
import java.io.RandomAccessFile;
import java.util.Enumeration;
import java.util.Stack;

import vena.JMC;
import ven.music.data.Note;
import ven.music.data.Part;
import ven.music.data.Phrase;
import ven.music.data.Score;

public final class Audio implements JMC
{
    private static boolean JPF = false;
    private static int channels;
    private static int sampleRate;

    public static void processScore(Score score, Instrument[] instruments,
				    String string) {
	Stack stack = new Stack();
	stack.push(instruments[0]);
	for (int i = 0; i < instruments.length; i++) {
	    if (instruments[i] != null && !instruments[i].getInitialised()) {
		try {
		    if (!instruments[i].getInitialised()) {
			instruments[i].createChain();
			instruments[i].setInitialised(true);
		    }
		} catch (AOException aoexception) {
		    aoexception.printStackTrace();
		}
	    }
	}
	Enumeration enumeration = score.getPartList().elements();
	double d = 60.0 / score.getTempo();
	int i = 0;
	while (enumeration.hasMoreElements()) {
	    Part part = (Part) enumeration.nextElement();
	    double d_0_ = d;
	    if (part.getTempo() > 0.0)
		d_0_ = 60.0 / part.getTempo();
	    if (part.getInstrument() != -1) {
		try {
		    stack.push(instruments[part.getInstrument()]);
		} catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
		    System.out.println
			("jMusic Audio warning: Can't find the instrument number "
			 + part.getInstrument()
			 + " that you have specified for " + "the part named "
			 + part.getTitle() + ".");
		}
	    }
	    System.out.println("Part " + i++ + " '" + part.getTitle() + "'. ");
	    Enumeration enumeration_1_ = part.getPhraseList().elements();
	    int i_2_ = 0;
	    while (enumeration_1_.hasMoreElements()) {
		Phrase phrase = (Phrase) enumeration_1_.nextElement();
		double d_3_ = d_0_;
		if (phrase.getTempo() > 0.0) {
		    System.out.println("A: " + d_3_);
		    d_3_ = 60.0 / phrase.getTempo();
		    System.out.println("B: " + d_3_);
		}
		if (phrase.getInstrument() != -1) {
		    try {
			stack.push(instruments[phrase.getInstrument()]);
		    } catch (ArrayIndexOutOfBoundsException arrayindexoutofboundsexception) {
			System.out.println
			    ("jMusic Audio warning: Can't find the instrument number "
			     + phrase.getInstrument()
			     + " that you have specified for"
			     + " the phrase named " + phrase.getTitle() + ".");
		    }
		}
		double d_4_ = d_0_ * phrase.getStartTime();
		double d_5_ = 0.0;
		Enumeration enumeration_6_ = phrase.getNoteList().elements();
		System.out.print("    Phrase " + i_2_++ + " '"
				 + phrase.getTitle() + "'"
				 + " starting at beat " + phrase.getStartTime()
				 + ": ");
		int i_7_ = 0;
		while (enumeration_6_.hasMoreElements()) {
		    Note note = (Note) enumeration_6_.nextElement();
		    if (note.getFrequency() == -2.147483648E9)
			d_5_ += d_3_ * note.getRhythmValue();
		    else {
			if (++i_7_ % 10 == 0)
			    System.out.print(i_7_);
			else
			    System.out.print(".");
			Note note_8_ = note.copy();
			note_8_.setDuration(d_3_ * note.getDuration());
			note_8_.setRhythmValue(d_3_ * note.getRhythmValue());
			Instrument instrument = (Instrument) stack.peek();
			instrument.setBlock(false);
			instrument.setFinished(true);
			instrument.renderNote(note_8_, d_4_ + d_5_);
			instrument.setFinished(false);
			instrument.iterateChain();
			d_5_ += d_3_ * note.getRhythmValue();
		    }
		}
		System.out.println();
		if (phrase.getInstrument() != -1)
		    stack.pop();
	    }
	}
    }

    public static void combine(String string, String string_9_,
			       String string_10_, boolean bool,
			       boolean bool_11_) {
	ioexception = ioexception_18_;
	break while_7_;
    }

    private static float getAudio(String string, long l, int i, float f,
				  RandomAccessFile randomaccessfile) {
	ioexception = ioexception_30_;
	break;
    }

    public static void addEmUp(String string, String string_35_, float f) {
	ioexception = ioexception_44_;
	break;
    }
}
