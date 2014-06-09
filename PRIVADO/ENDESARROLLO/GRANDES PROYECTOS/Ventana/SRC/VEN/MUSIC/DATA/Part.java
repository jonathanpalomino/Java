/*      */ package ven.music.data;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import vena.JMC;
/*      */ 
/*      */ public class Part
/*      */   implements Cloneable, Serializable, JMC
/*      */ {
/*      */   public static final String DEFAULT_TITLE = "Untitled Part";
/*      */   public static final int DEFAULT_INSTRUMENT = 0;
/*      */   public static final int DEFAULT_CHANNEL = 0;
/*      */   public static final double DEFAULT_TEMPO = -1.0D;
/*      */   public static final int DEFAULT_VOLUME = 100;
/*      */   public static final int DEFAULT_KEY_SIGNATURE = -2147483648;
/*      */   public static final int DEFAULT_KEY_QUALITY = -2147483648;
/*      */   public static final int DEFAULT_NUMERATOR = -2147483648;
/*      */   public static final int DEFAULT_DENOMINATOR = -2147483648;
/*      */   public static final double DEFAULT_PAN = 0.5D;
/*      */   private Vector phraseList;
/*      */   private String title;
/*      */   private int channel;
/*      */   private int instrument;
/*      */   private double tempo;
/*      */   private int volume;
/*      */   private double[] points;
/*      */   private long[] time;
/*      */   private int timeIndex;
/*      */   private Score myScore;
/*      */   private int keySignature;
/*      */   private int keyQuality;
/*      */   private int numerator;
/*      */   private int denominator;
/*      */   private double pan;
/*      */ 
/*      */   public Part()
/*      */   {
/*  134 */     this("Untitled Part");
/*      */   }
/*      */ 
/*      */   public Part(String paramString)
/*      */   {
/*  142 */     this(paramString, 0);
/*      */   }
/*      */ 
/*      */   public Part(int paramInt)
/*      */   {
/*  150 */     this("", paramInt);
/*      */   }
/*      */ 
/*      */   public Part(String paramString, int paramInt)
/*      */   {
/*  159 */     this(paramString, paramInt, 0);
/*      */   }
/*      */ 
/*      */   public Part(int paramInt1, int paramInt2)
/*      */   {
/*  168 */     this("", paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   public Part(String paramString, int paramInt1, int paramInt2)
/*      */   {
/*   99 */     this.points = null;
/*  100 */     this.time = null;
/*  101 */     this.timeIndex = 0;
/*      */ 
/*  103 */     this.myScore = null;
/*      */ 
/*  125 */     this.pan = 0.5D;
/*      */ 
/*  178 */     this.title = paramString;
/*  179 */     this.phraseList = new Vector();
/*      */ 
/*  181 */     if (this.channel > 16) {
/*  182 */       System.err.println(new Exception("jMusic Warning: A MIDI Channel cannot be greater than 16. There can be any number of Audio channels."));
/*      */ 
/*  186 */       new Exception().printStackTrace();
/*      */     }
/*      */ 
/*  189 */     this.channel = paramInt2;
/*      */ 
/*  191 */     if (paramInt1 < -1) {
/*  192 */       System.err.println(new Exception("jMusic EXCEPTION: instrument value must be greater than 0"));
/*      */ 
/*  195 */       new Exception().printStackTrace();
/*  196 */       System.exit(1);
/*      */     }
/*  198 */     this.instrument = paramInt1;
/*  199 */     this.tempo = -1.0D;
/*  200 */     this.volume = 100;
/*  201 */     this.keySignature = -2147483648;
/*  202 */     this.keyQuality = -2147483648;
/*  203 */     this.numerator = -2147483648;
/*  204 */     this.denominator = -2147483648;
/*      */   }
/*      */ 
/*      */   public Part(Phrase paramPhrase)
/*      */   {
/*  214 */     paramPhrase.setMyPart(this);
/*  215 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(String paramString, int paramInt, Phrase paramPhrase)
/*      */   {
/*  225 */     this(paramString, paramInt, 0, paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(String paramString, int paramInt1, int paramInt2, Phrase paramPhrase)
/*      */   {
/*  236 */     this(paramString, paramInt1, paramInt2);
/*  237 */     paramPhrase.setMyPart(this);
/*  238 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase[] paramArrayOfPhrase)
/*      */   {
/*  248 */     addPhraseList(paramArrayOfPhrase);
/*      */   }
/*      */ 
/*      */   public Part(CPhrase paramCPhrase)
/*      */   {
/*  258 */     addCPhrase(paramCPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase paramPhrase, String paramString)
/*      */   {
/*  269 */     this(paramString);
/*  270 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase[] paramArrayOfPhrase, String paramString)
/*      */   {
/*  281 */     this(paramString);
/*  282 */     addPhraseList(paramArrayOfPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase paramPhrase, String paramString, int paramInt)
/*      */   {
/*  295 */     this(paramString, paramInt);
/*  296 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase[] paramArrayOfPhrase, String paramString, int paramInt)
/*      */   {
/*  309 */     this(paramString, paramInt);
/*  310 */     addPhraseList(paramArrayOfPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase paramPhrase, String paramString, int paramInt1, int paramInt2)
/*      */   {
/*  324 */     this(paramString, paramInt1, paramInt2);
/*  325 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public Part(Phrase[] paramArrayOfPhrase, String paramString, int paramInt1, int paramInt2)
/*      */   {
/*  339 */     this(paramString, paramInt1, paramInt2);
/*  340 */     addPhraseList(paramArrayOfPhrase);
/*      */   }
/*      */ 
/*      */   public Phrase getPhrase(int paramInt)
/*      */   {
/*  349 */     Enumeration localEnumeration = this.phraseList.elements();
/*  350 */     int i = 0;
/*  351 */     while (localEnumeration.hasMoreElements()) {
/*  352 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  353 */       if (i == paramInt) {
/*  354 */         return localPhrase;
/*      */       }
/*  356 */       ++i;
/*      */     }
/*  358 */     return null;
/*      */   }
/*      */ 
/*      */   public void add(Phrase paramPhrase)
/*      */   {
/*  369 */     addPhrase(paramPhrase);
/*      */   }
/*      */ 
/*      */   public void addPhrase(Phrase paramPhrase)
/*      */   {
/*  378 */     paramPhrase.setMyPart(this);
/*  379 */     if (paramPhrase.getAppend()) paramPhrase.setStartTime(getEndTime());
/*  380 */     this.phraseList.addElement(paramPhrase);
/*      */   }
/*      */ 
/*      */   public void appendPhrase(Phrase paramPhrase)
/*      */   {
/*  388 */     Phrase localPhrase = paramPhrase.copy();
/*  389 */     localPhrase.setStartTime(getEndTime());
/*  390 */     addPhrase(localPhrase);
/*      */   }
/*      */ 
/*      */   public void addPhraseList(Phrase[] paramArrayOfPhrase)
/*      */   {
/*  398 */     for (int i = 0; i < paramArrayOfPhrase.length; ++i)
/*  399 */       if (paramArrayOfPhrase[i].getAppend()) {
/*  400 */         Phrase localPhrase = paramArrayOfPhrase[i].copy();
/*  401 */         localPhrase.setStartTime(getEndTime());
/*  402 */         addPhrase(localPhrase); } else {
/*  403 */         addPhrase(paramArrayOfPhrase[i]);
/*      */       }
/*      */   }
/*      */ 
/*      */   public void removePhrase(int paramInt)
/*      */   {
/*  412 */     Vector localVector = this.phraseList;
/*      */     try {
/*  414 */       localVector.removeElement(localVector.elementAt(paramInt));
/*      */     } catch (RuntimeException localRuntimeException) {
/*  416 */       System.err.println("The Phrase index to be deleted must be within the part.");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removePhrase(Phrase paramPhrase)
/*      */   {
/*  425 */     this.phraseList.removeElement(paramPhrase);
/*      */   }
/*      */ 
/*      */   public void removeLastPhrase()
/*      */   {
/*  432 */     Vector localVector = this.phraseList;
/*  433 */     localVector.removeElement(localVector.lastElement());
/*      */   }
/*      */ 
/*      */   public void removeAllPhrases()
/*      */   {
/*  440 */     this.phraseList.removeAllElements();
/*      */   }
/*      */ 
/*      */   public Vector getPhraseList()
/*      */   {
/*  448 */     return this.phraseList;
/*      */   }
/*      */ 
/*      */   public void setPhraseList(Vector paramVector)
/*      */   {
/*  456 */     this.phraseList = paramVector;
/*      */   }
/*      */ 
/*      */   public Phrase[] getPhraseArray()
/*      */   {
/*  464 */     Vector localVector = this.phraseList;
/*  465 */     Phrase[] arrayOfPhrase = new Phrase[localVector.size()];
/*  466 */     for (int i = 0; i < arrayOfPhrase.length; ++i) {
/*  467 */       arrayOfPhrase[i] = ((Phrase)localVector.elementAt(i));
/*      */     }
/*  469 */     return arrayOfPhrase;
/*      */   }
/*      */ 
/*      */   public void addCPhrase(CPhrase paramCPhrase)
/*      */   {
/*  481 */     if (paramCPhrase.getAppend()) paramCPhrase.setStartTime(getEndTime());
/*  482 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/*  483 */     while (localEnumeration.hasMoreElements()) {
/*  484 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/*  486 */       addPhrase(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public String getTitle()
/*      */   {
/*  495 */     return this.title;
/*      */   }
/*      */ 
/*      */   public void setTitle(String paramString)
/*      */   {
/*  503 */     this.title = paramString;
/*      */   }
/*      */ 
/*      */   public int getChannel()
/*      */   {
/*  511 */     return this.channel;
/*      */   }
/*      */ 
/*      */   public void setChannel(int paramInt)
/*      */   {
/*  519 */     this.channel = paramInt;
/*      */   }
/*      */ 
/*      */   public int getInstrument()
/*      */   {
/*  527 */     return this.instrument;
/*      */   }
/*      */ 
/*      */   public void setInstrument(int paramInt)
/*      */   {
/*  535 */     this.instrument = paramInt;
/*      */   }
/*      */ 
/*      */   public void setProgChg(int paramInt)
/*      */   {
/*  544 */     this.instrument = paramInt;
/*      */   }
/*      */ 
/*      */   public double getTempo()
/*      */   {
/*  552 */     return this.tempo;
/*      */   }
/*      */ 
/*      */   public void setTempo(double paramDouble)
/*      */   {
/*  560 */     this.tempo = paramDouble;
/*      */   }
/*      */ 
/*      */   public int getVolume()
/*      */   {
/*  568 */     return this.volume;
/*      */   }
/*      */ 
/*      */   public void setVolume(int paramInt)
/*      */   {
/*  576 */     this.volume = paramInt;
/*      */   }
/*      */ 
/*      */   public int getKeySignature()
/*      */   {
/*  585 */     return this.keySignature;
/*      */   }
/*      */ 
/*      */   public void setKeySignature(int paramInt)
/*      */   {
/*  594 */     this.keySignature = paramInt;
/*      */   }
/*      */ 
/*      */   public int getKeyQuality()
/*      */   {
/*  603 */     return this.keyQuality;
/*      */   }
/*      */ 
/*      */   public void setKeyQuality(int paramInt)
/*      */   {
/*  612 */     this.keyQuality = paramInt;
/*      */   }
/*      */ 
/*      */   public int getNumerator()
/*      */   {
/*  620 */     return this.numerator;
/*      */   }
/*      */ 
/*      */   public void setNumerator(int paramInt)
/*      */   {
/*  628 */     this.numerator = paramInt;
/*      */   }
/*      */ 
/*      */   public int getDenominator()
/*      */   {
/*  636 */     return this.denominator;
/*      */   }
/*      */ 
/*      */   public void setDenominator(int paramInt)
/*      */   {
/*  644 */     this.denominator = paramInt;
/*      */   }
/*      */ 
/*      */   public double getPan()
/*      */   {
/*  657 */     return this.pan;
/*      */   }
/*      */ 
/*      */   public void setPan(double paramDouble)
/*      */   {
/*  665 */     this.pan = paramDouble;
/*  666 */     Enumeration localEnumeration = this.phraseList.elements();
/*  667 */     while (localEnumeration.hasMoreElements()) {
/*  668 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  669 */       localPhrase.setPan(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setMyScore(Score paramScore)
/*      */   {
/*  675 */     this.myScore = paramScore;
/*      */   }
/*      */ 
/*      */   public Score getMyScore()
/*      */   {
/*  680 */     return this.myScore;
/*      */   }
/*      */ 
/*      */   public Part copy()
/*      */   {
/*  686 */     Part localPart = new Part();
/*  687 */     Enumeration localEnumeration = this.phraseList.elements();
/*  688 */     while (localEnumeration.hasMoreElements()) {
/*  689 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  690 */       localPart.addPhrase(localPhrase.copy());
/*      */     }
/*  692 */     copyAttributes(localPart);
/*  693 */     return localPart;
/*      */   }
/*      */ 
/*      */   private void copyAttributes(Part paramPart)
/*      */   {
/*  698 */     paramPart.setInstrument(getInstrument());
/*  699 */     paramPart.setChannel(getChannel());
/*  700 */     paramPart.setTitle(getTitle() + " copy");
/*  701 */     paramPart.setTempo(this.tempo);
/*  702 */     paramPart.setVolume(this.volume);
/*  703 */     paramPart.setPoints(this.points);
/*  704 */     paramPart.setTime(this.time);
/*  705 */     paramPart.setTimeIndex(this.timeIndex);
/*  706 */     paramPart.setMyScore(getMyScore());
/*      */   }
/*      */ 
/*      */   public Part copy(double paramDouble1, double paramDouble2)
/*      */   {
/*  717 */     Vector localVector = new Vector();
/*  718 */     Part localPart = new Part();
/*  719 */     copyAttributes(localPart);
/*  720 */     Enumeration localEnumeration = this.phraseList.elements();
/*  721 */     while (localEnumeration.hasMoreElements()) {
/*  722 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  723 */       if ((localPhrase.getStartTime() < paramDouble2) && (localPhrase.getEndTime() > paramDouble1))
/*      */       {
/*  726 */         localVector.addElement(localPhrase.copy(paramDouble1, paramDouble2));
/*      */       }
/*      */     }
/*  729 */     localPart.setPhraseList(localVector);
/*  730 */     return localPart;
/*      */   }
/*      */ 
/*      */   public Part copy(double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
/*      */   {
/*  750 */     Part localPart = new Part();
/*  751 */     copyAttributes(localPart);
/*  752 */     Vector localVector = new Vector();
/*      */ 
/*  754 */     localPart.setMyScore(getMyScore());
/*  755 */     Enumeration localEnumeration = this.phraseList.elements();
/*      */ 
/*  757 */     while (localEnumeration.hasMoreElements()) {
/*  758 */       Phrase localPhrase1 = (Phrase)localEnumeration.nextElement();
/*  759 */       double d1 = localPhrase1.getStartTime();
/*  760 */       if ((d1 < paramDouble2) && (localPhrase1.getEndTime() > paramDouble1))
/*      */       {
/*  763 */         Phrase localPhrase2 = localPhrase1.copy(paramDouble1, paramDouble2, paramBoolean1, paramBoolean2, false);
/*      */ 
/*  766 */         double d2 = 0.0D;
/*  767 */         if (d2 < 0.0D) d2 = 0.0D;
/*  768 */         if (paramBoolean3) d2 += paramDouble1;
/*  769 */         localPhrase2.setStartTime(d2);
/*      */ 
/*  775 */         localVector.addElement(localPhrase2);
/*      */       }
/*      */     }
/*  778 */     localPart.setPhraseList(localVector);
/*  779 */     return localPart;
/*      */   }
/*      */ 
/*      */   public double getEndTime()
/*      */   {
/*  788 */     double d1 = 0.0D;
/*  789 */     Enumeration localEnumeration = this.phraseList.elements();
/*  790 */     while (localEnumeration.hasMoreElements()) {
/*  791 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  792 */       double d2 = localPhrase.getEndTime();
/*  793 */       if (d2 > d1) d1 = d2;
/*      */     }
/*  795 */     return d1;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/*  802 */     String str = new String("----- jMusic PART: '" + this.title + "' contains " + size() + " phrases.  -----" + '\n');
/*      */ 
/*  804 */     str = str + "Channel = " + this.channel + '\n';
/*  805 */     str = str + "Instrument = " + this.instrument + '\n';
/*  806 */     if (this.tempo > 0.0D) str = str + "Part Tempo = " + this.tempo + '\n';
/*  807 */     Enumeration localEnumeration = this.phraseList.elements();
/*  808 */     while (localEnumeration.hasMoreElements()) {
/*  809 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  810 */       str = str + localPhrase.toString() + '\n';
/*      */     }
/*  812 */     return str;
/*      */   }
/*      */ 
/*      */   public void empty()
/*      */   {
/*  819 */     this.phraseList.removeAllElements();
/*      */   }
/*      */ 
/*      */   public int length()
/*      */   {
/*  827 */     return size();
/*      */   }
/*      */ 
/*      */   public int size()
/*      */   {
/*  835 */     return this.phraseList.size();
/*      */   }
/*      */ 
/*      */   public int getSize()
/*      */   {
/*  843 */     return this.phraseList.size();
/*      */   }
/*      */ 
/*      */   public void clean()
/*      */   {
/*  851 */     Enumeration localEnumeration = getPhraseList().elements();
/*  852 */     while (localEnumeration.hasMoreElements()) {
/*  853 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  854 */       if (localPhrase.getInstrument() == this.instrument)
/*  855 */         localPhrase.setInstrument(-1);
/*  856 */       if (localPhrase.getNoteList().size() == 0)
/*  857 */         removePhrase(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public int getHighestPitch()
/*      */   {
/*  866 */     int i = 0;
/*  867 */     Enumeration localEnumeration = getPhraseList().elements();
/*  868 */     while (localEnumeration.hasMoreElements()) {
/*  869 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  870 */       if (localPhrase.getHighestPitch() > i) i = localPhrase.getHighestPitch();
/*      */     }
/*  872 */     return i;
/*      */   }
/*      */ 
/*      */   public int getLowestPitch()
/*      */   {
/*  879 */     int i = 127;
/*  880 */     Enumeration localEnumeration = getPhraseList().elements();
/*  881 */     while (localEnumeration.hasMoreElements()) {
/*  882 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  883 */       if (localPhrase.getLowestPitch() < i)
/*  884 */         i = localPhrase.getLowestPitch();
/*      */     }
/*  886 */     return i;
/*      */   }
/*      */ 
/*      */   public double getLongestRhythmValue()
/*      */   {
/*  893 */     double d = 0.0D;
/*  894 */     Enumeration localEnumeration = getPhraseList().elements();
/*  895 */     while (localEnumeration.hasMoreElements()) {
/*  896 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  897 */       if (localPhrase.getLongestRhythmValue() > d)
/*  898 */         d = localPhrase.getLongestRhythmValue();
/*      */     }
/*  900 */     return d;
/*      */   }
/*      */ 
/*      */   public double getShortestRhythmValue()
/*      */   {
/*  907 */     double d = 1000.0D;
/*  908 */     Enumeration localEnumeration = getPhraseList().elements();
/*  909 */     while (localEnumeration.hasMoreElements()) {
/*  910 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  911 */       if (localPhrase.getShortestRhythmValue() < d)
/*  912 */         d = localPhrase.getShortestRhythmValue();
/*      */     }
/*  914 */     return d;
/*      */   }
/*      */ 
/*      */   public void setDynamic(int paramInt)
/*      */   {
/*  921 */     Enumeration localEnumeration = getPhraseList().elements();
/*  922 */     while (localEnumeration.hasMoreElements()) {
/*  923 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  924 */       localPhrase.setDynamic(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPitch(int paramInt)
/*      */   {
/*  932 */     Enumeration localEnumeration = getPhraseList().elements();
/*  933 */     while (localEnumeration.hasMoreElements()) {
/*  934 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  935 */       localPhrase.setPitch(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setRhythmValue(double paramDouble)
/*      */   {
/*  943 */     Enumeration localEnumeration = getPhraseList().elements();
/*  944 */     while (localEnumeration.hasMoreElements()) {
/*  945 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  946 */       localPhrase.setRhythmValue(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setDuration(double paramDouble)
/*      */   {
/*  954 */     Enumeration localEnumeration = getPhraseList().elements();
/*  955 */     while (localEnumeration.hasMoreElements()) {
/*  956 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*  957 */       localPhrase.setDuration(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPoints(double[] paramArrayOfDouble)
/*      */   {
/*  964 */     this.points = paramArrayOfDouble;
/*      */   }
/*      */ 
/*      */   public double getPoint() {
/*  968 */     return this.points[this.timeIndex];
/*      */   }
/*      */ 
/*      */   public void setTime(long[] paramArrayOfLong) {
/*  972 */     this.time = paramArrayOfLong;
/*      */   }
/*      */ 
/*      */   public long getTime() {
/*  976 */     return this.time[(this.timeIndex++)];
/*      */   }
/*      */ 
/*      */   public void setTimeIndex(int paramInt) {
/*  980 */     this.timeIndex = paramInt;
/*      */   }
/*      */ 
/*      */   public int getTimeIndex() {
/*  984 */     return this.timeIndex;
/*      */   }
/*      */ 
/*      */   public void addNote(Note paramNote, double paramDouble)
/*      */   {
/*  994 */     Phrase localPhrase = new Phrase("Generated by Part.addNote()", paramDouble);
/*  995 */     localPhrase.addNote(paramNote);
/*  996 */     addPhrase(localPhrase);
/*      */   }
/*      */ 
/*      */   public Phrase createPhrase()
/*      */   {
/* 1004 */     Phrase localPhrase = new Phrase();
/* 1005 */     addPhrase(localPhrase);
/* 1006 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public void setLength(double paramDouble)
/*      */   {
/* 1014 */     setRhythmValue(paramDouble);
/* 1015 */     setDuration(paramDouble * 0.9D);
/*      */   }
/*      */ 
/*      */   public void sort()
/*      */   {
/* 1022 */     Phrase[] arrayOfPhrase = getPhraseArray();
/* 1023 */     quickSort(arrayOfPhrase, 0, arrayOfPhrase.length - 1);
/* 1024 */     this.phraseList.removeAllElements();
/* 1025 */     this.phraseList.ensureCapacity(arrayOfPhrase.length);
/* 1026 */     for (int i = 0; i < arrayOfPhrase.length; ++i)
/* 1027 */       this.phraseList.add(arrayOfPhrase[i]);
/*      */   }
/*      */ 
/*      */   private void quickSort(Phrase[] paramArrayOfPhrase, int paramInt1, int paramInt2)
/*      */   {
/* 1033 */     if (paramInt1 >= paramInt2) return;
/* 1034 */     swap(paramArrayOfPhrase, paramInt1, (int)(Math.random() * (paramInt2 - paramInt1)) + paramInt1);
/*      */ 
/* 1036 */     int j = paramInt1;
/* 1037 */     for (int i = paramInt1 + 1; i <= paramInt2; ++i) {
/* 1038 */       if (paramArrayOfPhrase[i].getStartTime() > paramArrayOfPhrase[paramInt1].getStartTime()) continue; swap(paramArrayOfPhrase, ++j, i);
/*      */     }
/* 1040 */     swap(paramArrayOfPhrase, paramInt1, j);
/* 1041 */     quickSort(paramArrayOfPhrase, paramInt1, j - 1);
/* 1042 */     quickSort(paramArrayOfPhrase, j + 1, paramInt2);
/*      */   }
/*      */ 
/*      */   private static void swap(Phrase[] paramArrayOfPhrase, int paramInt1, int paramInt2) {
/* 1046 */     Phrase localPhrase = paramArrayOfPhrase[paramInt1];
/* 1047 */     paramArrayOfPhrase[paramInt1] = paramArrayOfPhrase[paramInt2];
/* 1048 */     paramArrayOfPhrase[paramInt2] = localPhrase;
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Part
 * JD-Core Version:    0.5.3
 */