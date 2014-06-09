/*     */ package ven.music.data;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Note
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   public static final int DEFAULT_PITCH = 60;
/*     */   public static final double DEFAULT_RHYTHM_VALUE = 1.0D;
/*     */   public static final int DEFAULT_DYNAMIC = 85;
/*     */   public static final double DEFAULT_PAN = 0.5D;
/*     */   public static final double DEFAULT_DURATION_MULTIPLIER = 0.9D;
/*     */   public static final double DEFAULT_ARTICULATION = 0.9D;
/*     */   public static final double DEFAULT_DURATION = 0.9D;
/*     */   public static final double DEFAULT_OFFSET = 0.0D;
/*     */   public static final double DEFAULT_SAMPLE_START_TIME = 0.0D;
/*     */   public static final int MIN_PITCH = 0;
/*     */   public static final double MIN_FREQUENCY = 1.E-017D;
/*     */   public static final double MAX_MIDI_PITCH = 127.0D;
/*     */   public static final int MAX_PITCH = 127;
/*     */   public static final double MIN_RHYTHM_VALUE = 0.0D;
/*     */   public static final double MAX_RHYTHM_VALUE = 1.7976931348623157E+308D;
/*     */   public static final int MIN_DYNAMIC = 0;
/*     */   public static final int MAX_DYNAMIC = 127;
/*     */   public static final double MIN_PAN = 0.0D;
/*     */   public static final double MAX_PAN = 1.7976931348623157E+308D;
/*     */   public static final double MIN_DURATION = 0.0D;
/*     */   public static final double MAX_DURATION = 1.7976931348623157E+308D;
/*     */   public static final int REST = -2147483648;
/*     */   public static final int FREQUENCY = 1;
/*     */   public static final int MIDI_PITCH = 0;
/*     */   public static final int AMP_ENV = 0;
/*     */   public static final int PITCH_ENV = 1;
/*     */   public static final int FILTER_ENV = 2;
/*     */   public static final int PAN_ENV = 3;
/*     */   public static final String C = "C";
/*     */   public static final String G = "G";
/*     */   public static final String D = "D";
/*     */   public static final String A = "A";
/*     */   public static final String E = "E";
/*     */   public static final String B = "B";
/*     */   public static final String F_SHARP = "F#";
/*     */   public static final String C_SHARP = "C#";
/*     */   public static final String G_SHARP = "Ab";
/*     */   public static final String D_SHARP = "Eb";
/*     */   public static final String A_SHARP = "Bb";
/*     */   public static final String A_FLAT = "Ab";
/*     */   public static final String E_FLAT = "Eb";
/*     */   public static final String B_FLAT = "Bb";
/*     */   public static final String F = "F";
/*     */   private String noteString;
/*     */   private double pitch;
/*     */   private int dynamic;
/*     */   private double rhythmValue;
/*     */   private double pan;
/*     */   private double duration;
/*     */   private double offset;
/*     */   private double sampleStartTime;
/*     */   private boolean pitchType;
/*     */   private Phrase myPhrase;
/*     */   private double[][] breakPoints;
/*     */ int i;
/*     */   public Note()
/*     */   {
/* 217 */     this(60, 1.0D);
/* 218 */     this.pitch = 60.0D;
/* 219 */     this.pitchType = false;
/* 220 */     this.rhythmValue = 1.0D;
/* 221 */     this.dynamic = 85;
/* 222 */     this.pan = 0.5D;
/* 223 */     this.duration = 0.9D;
/* 224 */     this.offset = 0.0D;
/*     */   }
/*     */ 
/*     */   public Note(int paramInt, double paramDouble)
/*     */   {
/* 236 */     this(paramInt, paramDouble, 85);
/*     */   }
/*     */ 
/*     */   public Note(int paramInt1, double paramDouble, int paramInt2)
/*     */   {
/* 250 */     this(paramInt1, paramDouble, paramInt2, 0.5D);
/*     */   }
/*     */ 
/*     */   public Note(int paramInt1, double paramDouble1, int paramInt2, double paramDouble2)
/*     */   {
/* 180 */     this.noteString = "";
/*     */ 
/* 208 */     this.myPhrase = null;
/*     */ 
/* 211 */     this.breakPoints = new double[64][];
/*     */ 
/* 266 */     if ((paramInt1 < 0) && (paramInt1 > -2147483646))
/*     */     {
/* 268 */       System.err.println("jMusic Note constructor error: Pitch is " + paramInt1 + ", it must be no less than " + 0 + " (REST = " + -2147483648 + ")");
/*     */ 
/* 271 */       System.exit(1);
/*     */     }
/* 273 */     this.pitchType = false;
/* 274 */     this.pitch = paramInt1;
/* 275 */     this.rhythmValue = paramDouble1;
/* 276 */     this.dynamic = ((paramInt2 > 127) ? 127 : (paramInt2 < 0) ? 0 : paramInt2);
/*     */ 
/* 279 */     this.pan = paramDouble2;
/* 280 */     this.duration = (paramDouble1 * 0.9D);
/* 281 */     this.offset = 0.0D;
/*     */   }
/*     */ 
/*     */   public Note(double paramDouble1, double paramDouble2)
/*     */   {
/* 292 */     this(paramDouble1, paramDouble2, 85);
/*     */   }
/*     */ 
/*     */   public Note(double paramDouble1, double paramDouble2, int paramInt)
/*     */   {
/* 304 */     this(paramDouble1, paramDouble2, paramInt, 0.5D);
/*     */   }
/*     */ 
/*     */   public Note(double paramDouble1, double paramDouble2, int paramInt, double paramDouble3)
/*     */   {
/* 180 */     this.noteString = "";
/*     */ 
/* 208 */     this.myPhrase = null;
/*     */ 
/* 211 */     this.breakPoints = new double[64][];
/*     */ 
/* 318 */     if (paramDouble1 > 1.E-017D) {
/* 319 */       this.pitch = paramDouble1;
/*     */     } else {
/* 321 */       System.err.println("jMusic Note constructor error: Frequency is " + paramDouble1 + ", it must be greater than " + 1.E-017D + " hertz.");
/*     */ 
/* 323 */       System.exit(1);
/*     */     }
/* 325 */     this.pitchType = true;
/* 326 */     this.rhythmValue = paramDouble2;
/* 327 */     this.dynamic = ((paramInt > 127) ? 127 : (paramInt < 0) ? 0 : paramInt);
/*     */ 
/* 330 */     this.pan = paramDouble3;
/* 331 */     this.duration = (paramDouble2 * 0.9D);
/* 332 */     this.offset = 0.0D;
/*     */   }
/*     */ 
/*     */   public Note(String paramString)
/*     */   {
/* 180 */     this.noteString = "";
/*     */ 
/* 208 */     this.myPhrase = null;
/*     */ 
/* 211 */     this.breakPoints = new double[64][];
/*     */ 
/* 338 */     this.noteString = paramString;
/* 339 */     setPitch(getPitchValue());
/*     */   }
/*     */ 
/*     */   public boolean getPitchType()
/*     */   {
/* 351 */     return this.pitchType;
/*     */   }
/*     */ 
/*     */   public void setPitchType(boolean paramBoolean)
/*     */   {
/* 360 */     this.pitchType = paramBoolean;
/*     */   }
/*     */ 
/*     */   public double getFrequency()
/*     */   {
/* 370 */     double d = this.pitch;
/* 371 */     if ((!(this.pitchType)) && (this.pitch != -2147483648.0D))
/* 372 */       d = vena.JMC.FRQ[(int)this.pitch];
/* 373 */     if (this.pitch == -2147483648.0D) d = -2147483648.0D;
/* 374 */     return d;
/*     */   }
/*     */ 
/*     */   public void setFrequency(double paramDouble)
/*     */   {
/*     */     try
/*     */     {
/* 385 */       this.pitch = ((this.pitch < 1.E-017D) ? 1.E-017D : paramDouble);
/*     */ 
/* 387 */       this.pitchType = true;
/*     */     } catch (RuntimeException localRuntimeException) {
/* 389 */       System.err.println("Error setting note value: You must enter frequency values above 1.0E-17");
/*     */ 
/* 391 */       System.exit(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getPitch()
/*     */   {
/* 402 */     if ((this.pitchType == true) && (this.pitch != -2147483648.0D)) {
/* 403 */       System.err.println("jMusic error getting Note pitch: Pitch is a frequency - getPitch() can't be used.");
/*     */ 
/* 405 */       System.exit(1);
/*     */     }
/*     */     int i;
/* 409 */     if (this.pitch < -2147483646.0D)
/* 410 */       i = -2147483648;
/*     */     else i = (int)this.pitch;
/* 412 */     return i;
/*     */   }
/*     */ 
/*     */   public void setPitch(int paramInt)
/*     */   {
/* 424 */     if (paramInt == -2147483648)
/* 425 */       this.pitch = -2147483648.0D;
/*     */     else {
/*     */       try {
/* 428 */         this.pitch = paramInt;
/*     */       }
/*     */       catch (RuntimeException localRuntimeException)
/*     */       {
/* 432 */         System.err.println("Error setting pitch value: You must enter pitch values between 0 and 127.0");
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 437 */     this.pitchType = false;
/*     */   }
/*     */ 
/*     */   public double getRhythmValue()
/*     */   {
/* 445 */     return this.rhythmValue;
/*     */   }
/*     */ 
/*     */   public void setRhythmValue(double paramDouble)
/*     */   {
/* 453 */     this.rhythmValue = ((paramDouble > 1.7976931348623157E+308D) ? 1.7976931348623157E+308D : (paramDouble < 0.0D) ? 0.0D : paramDouble);
/*     */   }
/*     */ 
/*     */   public int getDynamic()
/*     */   {
/* 465 */     return this.dynamic;
/*     */   }
/*     */ 
/*     */   public void setDynamic(int paramInt)
/*     */   {
/* 473 */     this.dynamic = ((paramInt > 127) ? 127 : (paramInt < 0) ? 0 : paramInt);
/*     */   }
/*     */ 
/*     */   public double getPan()
/*     */   {
/* 483 */     return this.pan;
/*     */   }
/*     */ 
/*     */   public void setPan(double paramDouble)
/*     */   {
/* 491 */     this.pan = ((paramDouble > 1.7976931348623157E+308D) ? 1.7976931348623157E+308D : (paramDouble < 0.0D) ? 0.0D : paramDouble);
/*     */   }
/*     */ 
/*     */   public double getDuration()
/*     */   {
/* 501 */     return this.duration;
/*     */   }
/*     */ 
/*     */   public void setDuration(double paramDouble)
/*     */   {
/* 509 */     this.duration = ((paramDouble > 1.7976931348623157E+308D) ? 1.7976931348623157E+308D : (paramDouble < 0.0D) ? 0.0D : paramDouble);
/*     */   }
/*     */ 
/*     */   public double getOffset()
/*     */   {
/* 520 */     return this.offset;
/*     */   }
/*     */ 
/*     */   public void setOffset(double paramDouble)
/*     */   {
/* 529 */     this.offset = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getSampleStartTime()
/*     */   {
/* 537 */     return this.sampleStartTime;
/*     */   }
/*     */ 
/*     */   public void setSampleStartTime(double paramDouble)
/*     */   {
/* 545 */     this.sampleStartTime = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setMyPhrase(Phrase paramPhrase)
/*     */   {
/* 550 */     this.myPhrase = paramPhrase;
/*     */   }
/*     */ 
/*     */   public Phrase getMyPhrase()
/*     */   {
/* 555 */     return this.myPhrase;
/*     */   }
/*     */ 
/*     */   public Note copy()
/*     */   {
/*     */     Note localNote;
/* 564 */     if (!(this.pitchType))
/* 565 */       localNote = new Note(getPitch(), this.rhythmValue, this.dynamic);
/*     */     else localNote = new Note(getFrequency(), this.rhythmValue, this.dynamic);
/* 567 */     localNote.setPan(this.pan);
/* 568 */     localNote.setDuration(this.duration);
/* 569 */     localNote.setOffset(this.offset);
/* 570 */     localNote.setSampleStartTime(this.sampleStartTime);
/* 571 */     localNote.setMyPhrase(this.myPhrase);
/* 572 */     for (int i = 0; i < this.breakPoints.length; ++i) {
/* 573 */       if (this.breakPoints[i] == null) continue; localNote.setBreakPoints(i, getBreakPoints(i));
/*     */     }
/* 575 */     return localNote;
/*     */   }
/*     */ 
/*     */   public void setBreakPoints(int paramInt, double[] paramArrayOfDouble)
/*     */   {
/* 586 */     if ((paramInt < 0) || (paramInt > this.breakPoints.length)) {
/* 587 */       System.err.println("jMusic Note error: BreakPoint index " + paramInt + " is out of range when setting.");
/*     */ 
/* 589 */       System.exit(1);
/*     */     }
/* 591 */     this.breakPoints[paramInt] = paramArrayOfDouble;
/*     */   }
/*     */ 
/*     */   public double[] getBreakPoints(int paramInt)
/*     */   {
/* 601 */     if ((paramInt < 0) || (paramInt > this.breakPoints.length)) {
/* 602 */       System.err.println("jMusic Note error: BreakPoint index " + paramInt + "is out of range when getting.");
/*     */ 
/* 604 */       System.exit(1);
/*     */     }
/* 606 */     if (this.breakPoints[paramInt] == null) {
/* 607 */       System.err.println("jMusic Note error: Breakpoint index " + paramInt + " is empty.");
/* 608 */       System.exit(1);
/*     */     }
/* 610 */     return this.breakPoints[paramInt];
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*     */     String str;
/* 621 */     if (!(this.pitchType)) {
/* 622 */       str = new String("jMusic NOTE: [Pitch = " + (int)this.pitch + "][RhythmValue = " + this.rhythmValue + "][Dynamic = " + this.dynamic + "][Pan = " + this.pan + "][Duration = " + this.duration + "]");
/*     */     }
/*     */     else
/*     */     {
/* 629 */       str = new String("Note: [Frequency = " + this.pitch + "][RhythmValue = " + this.rhythmValue + "][Dynamic = " + this.dynamic + "][Pan = " + this.pan + "][Duration = " + this.duration + "]");
/*     */     }
/*     */ 
/* 636 */     return str;
/*     */   }
/*     */ 
/*     */   public boolean isScale(int[] paramArrayOfInt)
/*     */   {
/* 648 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/* 649 */       if (this.pitch % 12.0D == paramArrayOfInt[i]) return true;
/*     */     }
/* 651 */     return false;
/*     */   }
/*     */ 
/*     */   public void setRhythmValue(double paramDouble, boolean paramBoolean)
/*     */   {
/* 662 */     setRhythmValue(paramDouble);
/* 663 */     if (paramBoolean)
/* 664 */       setDuration(paramDouble * 0.9D);
/*     */   }
/*     */ 
/*     */   public static int freqToMidiPitch(double paramDouble)
/*     */   {
/* 677 */     if ((paramDouble < 26.73D) || (paramDouble > 14496.0D)) {
/* 678 */       System.err.println("freqToMidiPitch error: Frequency " + paramDouble + " is not within the MIDI note range.");
/*     */ 
/* 680 */       return -1;
/*     */     }
/*     */ 
/* 684 */     double d1 = Math.pow(2.0D, 0.08333333333333333D);
/*     */ 
/* 687 */     double d2 = Math.pow(2.0D, 0.0008333333333333334D);
/* 688 */     int i = 0;
/* 689 */     int j = 0;
/*     */ 
/* 693 */     double d3 = 440.0D;
/* 694 */     if (paramDouble >= d3) {
/* 695 */       while (paramDouble > d1 * d3) {
/* 696 */         d3 = d1 * d3;
/* 697 */         ++i;
/*     */       }
/* 699 */       while (paramDouble > d2 * d3) {
/* 700 */         d3 = d2 * d3;
/* 701 */         ++j;
/*     */       }
/* 703 */       if (d2 * d3 - paramDouble < paramDouble - d3)
/* 704 */         ++j;
/* 705 */       if (j > 50) {
/* 706 */         ++i;
/* 707 */         j = 100 - j;
/*     */       }
/*     */     }
/*     */     else {
/* 711 */       while (paramDouble < d3 / d1) {
/* 712 */         d3 /= d1;
/* 713 */         --i;
/*     */       }
/* 715 */       while (paramDouble < d3 / d2) {
/* 716 */         d3 /= d2;
/* 717 */         ++j;
/*     */       }
/* 719 */       if (paramDouble - (d3 / d2) < d3 - paramDouble)
/* 720 */         ++j;
/* 721 */       if (j >= 50) {
/* 722 */         --i;
/* 723 */         j = 100 - j;
/*     */       }
/*     */     }
/*     */ 
/* 727 */     return (69 + i);
/*     */   }
/*     */ 
/*     */   public static double midiPitchToFreq(int paramInt)
/*     */   {
/* 739 */     if ((paramInt < 0) || (paramInt > 127)) {
/* 740 */       System.err.println("jMusic Note.midiPitchToFreq error:midiPitch of " + paramInt + " is out side valid range.");
/*     */ 
/* 742 */       return -1.0D;
/*     */     }
/*     */ 
/* 746 */     double d1 = Math.pow(2.0D, 0.08333333333333333D);
/* 747 */     int i = paramInt - 69;
/* 748 */     double d2 = 440.0D;
/* 749 */     if (paramInt > 69) {
/* 750 */       for (int j = 69; j < paramInt; ++j)
/* 751 */         d2 *= d1;
/*     */     }
/*     */     else {
/* 754 */       for (int k = 69; k > paramInt; --k) {
/* 755 */         d2 /= d1;
/*     */       }
/*     */     }
/*     */ 
/* 759 */     d2 = Math.round(d2 * 1000.0D) / 1000.0D;
/*     */ 
/* 761 */     return d2;
/*     */   }
/*     */ 
/*     */   public boolean isRest()
/*     */   {
/* 769 */     return (getPitch() == -2147483648);
/*     */   }
/*     */ 
/*     */   public void setLength(double paramDouble)
/*     */   {
/* 778 */     setRhythmValue(paramDouble);
/* 779 */     setDuration(paramDouble * 0.9D);
/*     */   }
/*     */ 
/*     */   public boolean isSharp()
/*     */   {
/* 787 */     return ((getNote().equals("C#")) || (getNote().equals("F#")));
/*     */   }
/*     */ 
/*     */   public boolean isFlat()
/*     */   {
/* 795 */     return ((getNote().equals("Eb")) || (getNote().equals("Ab")) || (getNote().equals("Bb")));
/*     */   }
/*     */ 
/*     */   public boolean isNatural()
/*     */   {
/* 805 */     return ((!(isSharp())) && (!(isFlat())));
/*     */   }
/*     */ 
/*     */   public boolean samePitch(Note paramNote) {
/* 809 */     return (getPitch() == paramNote.getPitch());
/*     */   }
/*     */ 
/*     */   public boolean sameDuration(Note paramNote) {
/* 813 */     return (getDuration() == paramNote.getDuration());
/*     */   }
/*     */ 
/*     */   public boolean equals(Note paramNote)
/*     */   {
/* 818 */     return ((samePitch(paramNote)) && (sameDuration(paramNote)));
/*     */   }
/*     */ 
/*     */   public Note nextNote(int[] paramArrayOfInt)
/*     */   {
/* 832 */     Note localNote = null;

/* 833 */     for (int i = 0; i < paramArrayOfInt.length; ++i)
/*     */     {
/* 839 */       if (getPitchValue() % 12 == 0) {
/* 840 */         localNote = new Note(getPitch() + paramArrayOfInt[i], 1.0D);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 848 */     i = getPitch() + paramArrayOfInt[1];
/* 849 */     System.out.println("NEXT PITCH " + i + " " + getPitch() + " " + paramArrayOfInt[1]);
/* 850 */     return new Note(i, 1.0D);
/*     */   }
/*     */ 
/*     */   public int getPitchValue()
/*     */   {
/* 855 */     int i = 0;
/* 856 */     if (this.noteString.equals("C"))
/* 857 */       i = 60;
/* 858 */     else if (this.noteString.equals("C#"))
/* 859 */       i = 61;
/* 860 */     else if (this.noteString.equals("D"))
/* 861 */       i = 62;
/* 862 */     else if (this.noteString.equals("Eb"))
/* 863 */       i = 63;
/* 864 */     else if (this.noteString.equals("E"))
/* 865 */       i = 64;
/* 866 */     else if (this.noteString.equals("F"))
/* 867 */       i = 65;
/* 868 */     else if (this.noteString.equals("F#"))
/* 869 */       i = 66;
/* 870 */     else if (this.noteString.equals("G"))
/* 871 */       i = 67;
/* 872 */     else if (this.noteString.equals("Ab"))
/* 873 */       i = 68;
/* 874 */     else if (this.noteString.equals("A"))
/* 875 */       i = 69;
/* 876 */     else if (this.noteString.equals("Bb"))
/* 877 */       i = 70;
/* 878 */     else if (this.noteString.equals("B")) {
/* 879 */       i = 71;
/*     */     }
/* 881 */     return i;
/*     */   }
/*     */ 
/*     */   public String getNote()
/*     */   {
/* 890 */     if (getPitch() % 12 == 0)
/* 891 */       this.noteString = "C";
/* 892 */     else if (getPitch() % 12 == 1)
/* 893 */       this.noteString = "C#";
/* 894 */     else if (getPitch() % 12 == 2)
/* 895 */       this.noteString = "D";
/* 896 */     else if (getPitch() % 12 == 3)
/* 897 */       this.noteString = "Eb";
/* 898 */     else if (getPitch() % 12 == 4)
/* 899 */       this.noteString = "E";
/* 900 */     else if (getPitch() % 12 == 5)
/* 901 */       this.noteString = "F";
/* 902 */     else if (getPitch() % 12 == 6)
/* 903 */       this.noteString = "F#";
/* 904 */     else if (getPitch() % 12 == 7)
/* 905 */       this.noteString = "G";
/* 906 */     else if (getPitch() % 12 == 8)
/* 907 */       this.noteString = "Ab";
/* 908 */     else if (getPitch() % 12 == 9)
/* 909 */       this.noteString = "A";
/* 910 */     else if (getPitch() % 12 == 10)
/* 911 */       this.noteString = "Bb";
/* 912 */     else if (getPitch() % 12 == 11)
/* 913 */       this.noteString = "B";
/*     */     else
/* 915 */       this.noteString = "N/A";
/* 916 */     return this.noteString;
/*     */   }
/*     */ 
/*     */   public static String getNote(int paramInt)
/*     */   {
/* 924 */     String str = "";
/* 925 */     if (paramInt % 12 == 0)
/* 926 */       str = "C";
/* 927 */     else if (paramInt % 12 == 1)
/* 928 */       str = "C#";
/* 929 */     else if (paramInt % 12 == 2)
/* 930 */       str = "D";
/* 931 */     else if (paramInt % 12 == 3)
/* 932 */       str = "Eb";
/* 933 */     else if (paramInt % 12 == 4)
/* 934 */       str = "E";
/* 935 */     else if (paramInt % 12 == 5)
/* 936 */       str = "F";
/* 937 */     else if (paramInt % 12 == 6)
/* 938 */       str = "F#";
/* 939 */     else if (paramInt % 12 == 7)
/* 940 */       str = "G";
/* 941 */     else if (paramInt % 12 == 8)
/* 942 */       str = "Ab";
/* 943 */     else if (paramInt % 12 == 9)
/* 944 */       str = "A";
/* 945 */     else if (paramInt % 12 == 10)
/* 946 */       str = "Bb";
/* 947 */     else if (paramInt % 12 == 11)
/* 948 */       str = "B";
/*     */     else
/* 950 */       str = "N/A";
/* 951 */     return str;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Note
 * JD-Core Version:    0.5.3
 */