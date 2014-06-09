package vena;

import jm.constants.Alignments;
import jm.constants.DrumMap;
import jm.constants.Dynamics;
import jm.constants.Frequencies;
import jm.constants.Noises;
import jm.constants.Panning;
import jm.constants.Pitches;
import jm.constants.ProgramChanges;
import jm.constants.RhythmValues;
import jm.constants.Scales;
import jm.constants.Tunings;
import jm.constants.Waveforms;

public abstract interface JMC extends RhythmValues, Pitches, Frequencies, Tunings, Dynamics, Panning, ProgramChanges, DrumMap, Scales, Waveforms, Noises, Alignments
{
  public static final int DEBUG = 0;
  public static final int VERBOSE = 1;
  public static final int EIGHT_BIT = 127;
  public static final int SIXTEEN_BIT = 32767;
  public static final int THIRTY_TWO_BIT = 214748647;
  public static final int PROG_EVT = 748394;
  public static final int TEMP_EVT = 748395;
  public static final int KEY_SIG_EVT = 748396;
  public static final int TIME_SIG_EVT = 748397;
  public static final int NO_KEY_SIGNATURE = -2147483648;
  public static final int NO_KEY_QUALITY = -2147483648;
  public static final int NO_NUMERATOR = -2147483648;
  public static final int NO_DENOMINATOR = -2147483648;
  public static final int NO_INSTRUMENT = -1;
  public static final int AMPLITUDE = 0;
  public static final int FREQUENCY = 1;
  public static final int MONO = 1;
  public static final int STEREO = 2;
  public static final int QUADRAPHONIC = 4;
  public static final int OCTAPHONIC = 8;
  public static final int PITCH = 0;
  public static final int RHYTHM = 1;
  public static final int DYNAMIC = 2;
  public static final int PAN = 3;
}

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.JMC
 * JD-Core Version:    0.5.3
 */