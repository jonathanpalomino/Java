/*      */ package ven.music.data;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import vena.JMC;
/*      */ 
/*      */ public class Phrase
/*      */   implements JMC, Cloneable, Serializable
/*      */ {
/*      */   public static final double MIN_START_TIME = 0.0D;
/*      */   public static final String DEFAULT_TITLE = "Untitled Phrase";
/*      */   public static final double DEFAULT_START_TIME = 0.0D;
/*      */   public static final int DEFAULT_INSTRUMENT = -1;
/*      */   public static final int DEFAULT_APPEND = 0;
/*      */   public static final double DEFAULT_TEMPO = -1.0D;
/*      */   public static final double DEFAULT_PAN = 0.5D;
/*      */   public static final int DEFAULT_NUMERATOR = 4;
/*      */   public static final int DEFAULT_DENOMINATOR = 4;
/*      */   public static final int DEFAULT_VOLUME = 100;
/*      */   private Vector noteList;
/*      */   private String title;
/*      */   private Position position;
/*      */   private int instrument;
/*      */   private double tempo;
/*      */   private boolean append;
/*      */   private Phrase linkedPhrase;
/*      */   private double pan;
/*      */   private int numerator;
/*      */   private int denominator;
/*      */   private Part myPart;
/*      */   private int volume;
/*      */   private boolean mute;
/*      */ 
/*      */   public Phrase()
/*      */   {
/*  132 */     this(0.0D);
/*  133 */     this.append = true;
/*      */   }
/*      */ 
/*      */   public Phrase(double paramDouble)
/*      */   {
/*  141 */     this(paramDouble, -1);
/*      */   }
/*      */ 
/*      */   public Phrase(double paramDouble, int paramInt)
/*      */   {
/*  150 */     this("Untitled Phrase", paramDouble, paramInt);
/*      */   }
/*      */ 
/*      */   public Phrase(String paramString)
/*      */   {
/*  158 */     this(paramString, 0.0D);
/*  159 */     this.append = true;
/*      */   }
/*      */ 
/*      */   public Phrase(String paramString, double paramDouble)
/*      */   {
/*  168 */     this(paramString, paramDouble, -1);
/*      */   }
/*      */ 
/*      */   public Phrase(String paramString, double paramDouble, int paramInt)
/*      */   {
/*  178 */     this(paramString, paramDouble, paramInt, false);
/*      */   }
/*      */ 
/*      */   public Phrase(String paramString, double paramDouble, int paramInt, boolean paramBoolean)
/*      */   {
/*  107 */     this.append = false;
/*      */ 
/*  112 */     this.pan = 0.5D;
/*      */ 
/*  118 */     this.myPart = null;
/*      */ 
/*  122 */     this.mute = false;
/*      */ 
/*  190 */     this.title = paramString;
/*      */ 
/*  192 */     this.position = new Position(paramDouble, this, null);
/*  193 */     this.append = paramBoolean;
/*  194 */     if (paramInt < -1) {
/*  195 */       System.err.println(new Exception("jMusic EXCEPTION: instrument value must be greater than 0"));
/*      */ 
/*  197 */       new Exception().printStackTrace();
/*  198 */       System.exit(1);
/*      */     }
/*  200 */     this.instrument = paramInt;
/*  201 */     this.noteList = new Vector();
/*  202 */     this.numerator = 4;
/*  203 */     this.denominator = 4;
/*  204 */     this.tempo = -1.0D;
/*  205 */     this.volume = 100;
/*      */   }
/*      */ 
/*      */   public Phrase(Note paramNote)
/*      */   {
/*  215 */     addNote(paramNote);
/*      */   }
/*      */ 
/*      */   public Phrase(Note[] paramArrayOfNote)
/*      */   {
/*  225 */     addNoteList(paramArrayOfNote);
/*      */   }
/*      */ 
/*      */   public Phrase(Note paramNote, String paramString)
/*      */   {
/*  236 */     this(paramString);
/*  237 */     addNote(paramNote);
/*      */   }
/*      */ 
/*      */   public Phrase(Note[] paramArrayOfNote, String paramString)
/*      */   {
/*  248 */     this(paramString);
/*  249 */     addNoteList(paramArrayOfNote);
/*      */   }
/*      */ 
/*      */   public Phrase(Note paramNote, double paramDouble)
/*      */   {
/*  260 */     this(paramNote);
/*  261 */     setStartTime(paramDouble);
/*      */   }
/*      */ 
/*      */   public int getInstrument()
/*      */   {
/*  273 */     return this.instrument;
/*      */   }
/*      */ 
/*      */   public void setInstrument(int paramInt)
/*      */   {
/*  281 */     this.instrument = paramInt;
/*      */   }
/*      */ 
/*      */   public void addNote(Note paramNote)
/*      */   {
/*  289 */     paramNote.setMyPhrase(this);
/*  290 */     this.noteList.addElement(paramNote);
/*      */   }
/*      */ 
/*      */   public void addNote(int paramInt, double paramDouble)
/*      */   {
/*  300 */     Note localNote = new Note(paramInt, paramDouble);
/*  301 */     addNote(localNote);
/*      */   }
/*      */ 
/*      */   public void add(Note paramNote)
/*      */   {
/*  309 */     addNote(paramNote);
/*      */   }
/*      */ 
/*      */   public void addRest(Rest paramRest)
/*      */   {
/*  317 */     paramRest.setMyPhrase(this);
/*  318 */     this.noteList.addElement(paramRest);
/*      */   }
/*      */ 
/*      */   public void addNoteList(Note[] paramArrayOfNote)
/*      */   {
/*  328 */     for (int i = 0; i < paramArrayOfNote.length; ++i)
/*  329 */       addNote(paramArrayOfNote[i]);
/*      */   }
/*      */ 
/*      */   public void addNoteList(Vector paramVector, boolean paramBoolean)
/*      */   {
/*  341 */     Enumeration localEnumeration = paramVector.elements();
/*  342 */     if (!(paramBoolean)) this.noteList.removeAllElements();
/*  343 */     while (localEnumeration.hasMoreElements())
/*      */       try {
/*  345 */         Note localNote = (Note)localEnumeration.nextElement();
/*  346 */         addNote(localNote);
/*      */       }
/*      */       catch (RuntimeException localRuntimeException) {
/*  349 */         System.err.println("The vector passed to this method must contain Notes only!");
/*      */       }
/*      */   }
/*      */ 
/*      */   public void addNoteList(Note[] paramArrayOfNote, boolean paramBoolean)
/*      */   {
/*  361 */     if (!(paramBoolean)) this.noteList.removeAllElements();
/*  362 */     for (int i = 0; i < paramArrayOfNote.length; ++i)
/*  363 */       addNote(paramArrayOfNote[i]);
/*      */   }
/*      */ 
/*      */   public void addNoteList(int[] paramArrayOfInt, double paramDouble)
/*      */   {
/*  373 */     double[] arrayOfDouble = new double[paramArrayOfInt.length];
/*  374 */     for (int i = 0; i < arrayOfDouble.length; ++i) {
/*  375 */       arrayOfDouble[i] = paramDouble;
/*      */     }
/*  377 */     addNoteList(paramArrayOfInt, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public void addNoteList(int[] paramArrayOfInt, double paramDouble, int paramInt)
/*      */   {
/*  387 */     double[] arrayOfDouble = new double[paramArrayOfInt.length];
/*  388 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/*  389 */     for (int i = 0; i < arrayOfDouble.length; ++i) {
/*  390 */       arrayOfDouble[i] = paramDouble;
/*  391 */       arrayOfInt[i] = paramInt;
/*      */     }
/*  393 */     addNoteList(paramArrayOfInt, arrayOfDouble, arrayOfInt);
/*      */   }
/*      */ 
/*      */   public void addNoteList(double[] paramArrayOfDouble, double paramDouble)
/*      */   {
/*  402 */     double[] arrayOfDouble = new double[paramArrayOfDouble.length];
/*  403 */     for (int i = 0; i < arrayOfDouble.length; ++i) {
/*  404 */       arrayOfDouble[i] = paramDouble;
/*      */     }
/*  406 */     addNoteList(paramArrayOfDouble, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public void addNoteList(int[] paramArrayOfInt, double[] paramArrayOfDouble)
/*      */   {
/*  417 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/*  418 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/*  419 */       arrayOfInt[i] = 85;
/*      */     }
/*  421 */     addNoteList(paramArrayOfInt, paramArrayOfDouble, arrayOfInt);
/*      */   }
/*      */ 
/*      */   public void addNoteList(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
/*      */   {
/*  430 */     int[] arrayOfInt = new int[paramArrayOfDouble1.length];
/*  431 */     for (int i = 0; i < paramArrayOfDouble1.length; ++i) {
/*  432 */       arrayOfInt[i] = 85;
/*      */     }
/*  434 */     addNoteList(paramArrayOfDouble1, paramArrayOfDouble2, arrayOfInt);
/*      */   }
/*      */ 
/*      */   public void addNoteList(int[] paramArrayOfInt1, double[] paramArrayOfDouble, int[] paramArrayOfInt2)
/*      */   {
/*  445 */     addNoteList(paramArrayOfInt1, paramArrayOfDouble, paramArrayOfInt2, true);
/*      */   }
/*      */ 
/*      */   public void addNoteList(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int[] paramArrayOfInt)
/*      */   {
/*  456 */     addNoteList(paramArrayOfDouble1, paramArrayOfDouble2, paramArrayOfInt, true);
/*      */   }
/*      */ 
/*      */   public void addNoteList(int[] paramArrayOfInt1, double[] paramArrayOfDouble, int[] paramArrayOfInt2, boolean paramBoolean)
/*      */   {
/*  470 */     if (!(paramBoolean)) this.noteList.removeAllElements();
/*  471 */     for (int i = 0; i < paramArrayOfInt1.length; ++i)
/*      */       try {
/*  473 */         Note localNote = new Note(paramArrayOfInt1[i], paramArrayOfDouble[i], paramArrayOfInt2[i]);
/*  474 */         addNote(localNote);
/*      */       } catch (RuntimeException localRuntimeException) {
/*  476 */         System.err.println("You must enter arrays of even length");
/*      */       }
/*      */   }
/*      */ 
/*      */   public void addNoteList(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int[] paramArrayOfInt, boolean paramBoolean)
/*      */   {
/*  492 */     if (!(paramBoolean)) this.noteList.removeAllElements();
/*  493 */     for (int i = 0; i < paramArrayOfDouble1.length; ++i)
/*      */       try {
/*  495 */         Note localNote = new Note(paramArrayOfDouble1[i], paramArrayOfDouble2[i], paramArrayOfInt[i]);
/*  496 */         addNote(localNote);
/*      */       } catch (RuntimeException localRuntimeException) {
/*  498 */         System.err.println("jMusic Phrase error: You must enter arrays of even length");
/*      */       }
/*      */   }
/*      */ 
/*      */   public void addNoteList(double[] paramArrayOfDouble)
/*      */   {
/*  508 */     for (int i = 0; i < paramArrayOfDouble.length; i += 2)
/*      */       try {
/*  510 */         Note localNote = new Note((int)paramArrayOfDouble[i], paramArrayOfDouble[(i + 1)]);
/*  511 */         addNote(localNote);
/*      */       } catch (RuntimeException localRuntimeException) {
/*  513 */         System.err.println("Error adding note list: Possibly the wrong number of values in the pitch and rhythm array.");
/*      */       }
/*      */   }
/*      */ 
/*      */   public void addNoteList(int paramInt, double[] paramArrayOfDouble)
/*      */   {
/*  524 */     for (int i = 0; i < paramArrayOfDouble.length; ++i)
/*  525 */       addNote(new Note(paramInt, paramArrayOfDouble[i]));
/*      */   }
/*      */ 
/*      */   public void addNoteList(double paramDouble, double[] paramArrayOfDouble)
/*      */   {
/*  535 */     for (int i = 0; i < paramArrayOfDouble.length; ++i)
/*  536 */       addNote(new Note(paramDouble, paramArrayOfDouble[i]));
/*      */   }
/*      */ 
/*      */   public void addChord(int[] paramArrayOfInt, double paramDouble)
/*      */   {
/*  548 */     for (int i = 0; i < paramArrayOfInt.length - 1; ++i) {
/*  549 */       Note localNote = new Note(paramArrayOfInt[i], 0.0D);
/*  550 */       localNote.setDuration(paramDouble * 0.9D);
/*  551 */       addNote(localNote);
/*      */     }
/*  553 */     addNote(paramArrayOfInt[(paramArrayOfInt.length - 1)], paramDouble);
/*      */   }
/*      */ 
/*      */   public int[] getPitchArray()
/*      */   {
/*  559 */     Note[] arrayOfNote = getNoteArray();
/*  560 */     int[] arrayOfInt = new int[arrayOfNote.length];
/*  561 */     for (int i = 0; i < arrayOfNote.length; ++i) {
/*  562 */       arrayOfInt[i] = arrayOfNote[i].getPitch();
/*      */     }
/*  564 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   public double[] getRhythmArray() {
/*  568 */     Note[] arrayOfNote = getNoteArray();
/*  569 */     double[] arrayOfDouble = new double[arrayOfNote.length];
/*  570 */     for (int i = 0; i < arrayOfNote.length; ++i) {
/*  571 */       arrayOfDouble[i] = arrayOfNote[i].getRhythmValue();
/*      */     }
/*  573 */     return arrayOfDouble;
/*      */   }
/*      */ 
/*      */   public int[] getDynamicArray() {
/*  577 */     Note[] arrayOfNote = getNoteArray();
/*  578 */     int[] arrayOfInt = new int[arrayOfNote.length];
/*  579 */     for (int i = 0; i < arrayOfNote.length; ++i) {
/*  580 */       arrayOfInt[i] = arrayOfNote[i].getPitch();
/*      */     }
/*  582 */     return arrayOfInt;
/*      */   }
/*      */ 
/*      */   public void removeNote(int paramInt)
/*      */   {
/*  590 */     Vector localVector = this.noteList;
/*      */     try {
/*  592 */       localVector.removeElement(localVector.elementAt(paramInt));
/*      */     } catch (RuntimeException localRuntimeException) {
/*  594 */       System.err.println("Note index to be deleted must be within the phrase.");
/*      */     }
/*      */   }
/*      */ 
/*      */   public void removeNote(Note paramNote)
/*      */   {
/*  603 */     this.noteList.removeElement(paramNote);
/*      */   }
/*      */ 
/*      */   public void removeLastNote()
/*      */   {
/*  610 */     Vector localVector = this.noteList;
/*  611 */     localVector.removeElementAt(localVector.size() - 1);
/*      */   }
/*      */ 
/*      */   public Vector getNoteList()
/*      */   {
/*  619 */     return this.noteList;
/*      */   }
/*      */ 
/*      */   public void setNoteList(Vector paramVector)
/*      */   {
/*  627 */     this.noteList = paramVector;
/*      */   }
/*      */ 
/*      */   public Note[] getNoteArray()
/*      */   {
/*  635 */     Vector localVector = this.noteList;
/*  636 */     Note[] arrayOfNote = new Note[localVector.size()];
/*  637 */     for (int i = 0; i < arrayOfNote.length; ++i) {
/*  638 */       arrayOfNote[i] = ((Note)localVector.elementAt(i));
/*      */     }
/*  640 */     return arrayOfNote;
/*      */   }
/*      */ 
/*      */   public double getStartTime()
/*      */   {
/*  648 */     return this.position.getStartTime();
/*      */   }
/*      */ 
/*      */   public void setStartTime(double paramDouble)
/*      */   {
/*  665 */     if (paramDouble >= 0.0D) {
/*  666 */       this.position.setStartTime(paramDouble);
/*      */ 
/*  668 */       setAppend(false);
/*      */     } else {
/*  670 */       System.err.println("Error setting phrase start time value: You must enter values greater than 0.0");
/*      */     }
/*      */   }
/*      */ 
/*      */   public boolean attemptAnchoringTo(Phrase paramPhrase, Alignment paramAlignment, double paramDouble)
/*      */   {
/*  694 */     Position localPosition = new Position(paramPhrase.position, paramAlignment, paramDouble, this, null);
/*      */ 
/*  696 */     if (localPosition.getStartTime() < 0.0D) {
/*  697 */       return false;
/*      */     }
/*  699 */     this.position = localPosition;
/*  700 */     return true;
/*      */   }
/*      */ 
/*      */   public Anchoring getAnchoring()
/*      */   {
/*  712 */     return this.position.getAnchoring();
/*      */   }
/*      */ 
/*      */   public double getEndTime()
/*      */   {
/*  720 */     double d1 = (getStartTime() < 0.0D) ? 0.0D : getStartTime();
/*  721 */     double d2 = d1;
/*  722 */     Enumeration localEnumeration = this.noteList.elements();
/*  723 */     while (localEnumeration.hasMoreElements()) {
/*  724 */       Note localNote = (Note)localEnumeration.nextElement();
/*  725 */       d2 += localNote.getRhythmValue();
/*      */     }
/*  727 */     return d2;
/*      */   }
/*      */ 
/*      */   final double getTotalDuration()
/*      */   {
/*  735 */     double d = 0.0D;
/*  736 */     Enumeration localEnumeration = this.noteList.elements();
/*  737 */     while (localEnumeration.hasMoreElements()) {
/*  738 */       Note localNote = (Note)localEnumeration.nextElement();
/*  739 */       d += localNote.getRhythmValue();
/*      */     }
/*  741 */     return d;
/*      */   }
/*      */ 
/*      */   public String getTitle()
/*      */   {
/*  749 */     return this.title;
/*      */   }
/*      */ 
/*      */   public void setTitle(String paramString)
/*      */   {
/*  757 */     this.title = paramString;
/*      */   }
/*      */ 
/*      */   public boolean getAppend()
/*      */   {
/*  765 */     return this.append;
/*      */   }
/*      */ 
/*      */   public void setAppend(boolean paramBoolean)
/*      */   {
/*  773 */     this.append = paramBoolean;
/*      */   }
/*      */ 
/*      */   public Phrase getLinkedPhrase()
/*      */   {
/*  781 */     return this.linkedPhrase;
/*      */   }
/*      */ 
/*      */   public void setLinkedPhrase(Phrase paramPhrase)
/*      */   {
/*  789 */     this.linkedPhrase = paramPhrase;
/*      */   }
/*      */ 
/*      */   public double getPan()
/*      */   {
/*  797 */     return this.pan;
/*      */   }
/*      */ 
/*      */   public void setPan(double paramDouble)
/*      */   {
/*  805 */     this.pan = paramDouble;
/*  806 */     Enumeration localEnumeration = this.noteList.elements();
/*  807 */     while (localEnumeration.hasMoreElements()) {
/*  808 */       Note localNote = (Note)localEnumeration.nextElement();
/*  809 */       localNote.setPan(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public double getTempo()
/*      */   {
/*  818 */     return this.tempo;
/*      */   }
/*      */ 
/*      */   public void setTempo(double paramDouble)
/*      */   {
/*  826 */     this.tempo = paramDouble;
/*      */   }
/*      */ 
/*      */   public Note getNote(int paramInt)
/*      */   {
/*  835 */     Enumeration localEnumeration = this.noteList.elements();
/*  836 */     int i = 0;
/*  837 */     while (localEnumeration.hasMoreElements()) {
/*  838 */       Note localNote = (Note)localEnumeration.nextElement();
/*  839 */       if (i == paramInt) {
/*  840 */         return localNote;
/*      */       }
/*  842 */       ++i;
/*      */     }
/*  844 */     return null;
/*      */   }
/*      */ 
/*      */   public int length()
/*      */   {
/*  853 */     return size();
/*      */   }
/*      */ 
/*      */   public int size()
/*      */   {
/*  861 */     return this.noteList.size();
/*      */   }
/*      */ 
/*      */   public int getSize()
/*      */   {
/*  869 */     return this.noteList.size();
/*      */   }
/*      */ 
/*      */   public int getNumerator()
/*      */   {
/*  878 */     return this.numerator;
/*      */   }
/*      */ 
/*      */   public void setNumerator(int paramInt)
/*      */   {
/*  886 */     this.numerator = paramInt;
/*      */   }
/*      */ 
/*      */   public int getDenominator()
/*      */   {
/*  894 */     return this.denominator;
/*      */   }
/*      */ 
/*      */   public void setDenominator(int paramInt)
/*      */   {
/*  902 */     this.denominator = paramInt;
/*      */   }
/*      */ 
/*      */   public void setMyPart(Part paramPart)
/*      */   {
/*  907 */     this.myPart = paramPart;
/*      */   }
/*      */ 
/*      */   public Part getMyPart()
/*      */   {
/*  912 */     return this.myPart;
/*      */   }
/*      */ 
/*      */   public Phrase copy()
/*      */   {
/*  920 */     Phrase localPhrase = new Phrase();
/*  921 */     copyAttributes(localPhrase);
/*  922 */     Enumeration localEnumeration = this.noteList.elements();
/*  923 */     while (localEnumeration.hasMoreElements()) {
/*  924 */       localPhrase.addNote(((Note)localEnumeration.nextElement()).copy());
/*      */     }
/*  926 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   private void copyAttributes(Phrase paramPhrase)
/*      */   {
/*  931 */     paramPhrase.position = this.position.copy(paramPhrase);
/*  932 */     paramPhrase.setTitle(this.title + " copy");
/*  933 */     paramPhrase.setInstrument(this.instrument);
/*  934 */     paramPhrase.setAppend(this.append);
/*  935 */     paramPhrase.setPan(this.pan);
/*  936 */     paramPhrase.setLinkedPhrase(this.linkedPhrase);
/*  937 */     paramPhrase.setMyPart(getMyPart());
/*  938 */     paramPhrase.setTempo(this.tempo);
/*  939 */     paramPhrase.setNumerator(this.numerator);
/*  940 */     paramPhrase.setDenominator(this.denominator);
/*  941 */     paramPhrase.setVolume(this.volume);
/*      */   }
/*      */ 
/*      */   public Phrase copy(double paramDouble1, double paramDouble2)
/*      */   {
/*  953 */     Phrase localPhrase = copy(paramDouble1, paramDouble2, true);
/*  954 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public Phrase copy(double paramDouble1, double paramDouble2, boolean paramBoolean)
/*      */   {
/*  972 */     if ((paramDouble1 >= paramDouble2) || (paramDouble2 < getStartTime())) {
/*  973 */       return null;
/*      */     }
/*  975 */     Phrase localPhrase = new Phrase(0.0D);
/*  976 */     copyAttributes(localPhrase);
/*  977 */     double d = getStartTime();
/*  978 */     if (d < 0.0D) d = 0.0D;
/*      */ 
/*  980 */     if (paramDouble1 < d) {
/*  981 */       Note localNote1 = new Note(-2147483648, d - paramDouble1);
/*  982 */       localPhrase.addNote(localNote1);
/*  983 */       paramDouble2 += d - paramDouble1;
/*      */     }
/*      */ 
/*  987 */     for (int i = 0; i < size(); ++i)
/*      */     {
/*      */       Note localNote3;
/*  989 */       if (d < paramDouble1) {
/*  990 */         if ((d + getNote(i).getRhythmValue() > paramDouble1) && (d + getNote(i).getRhythmValue() <= paramDouble2))
/*      */         {
/*  992 */           if (paramBoolean) {
/*  993 */             localNote3 = new Note(-2147483648, d + getNote(i).getRhythmValue() - paramDouble1);
/*      */ 
/*  995 */             localPhrase.addNote(localNote3);
/*      */           }
/*  997 */           else if (!(getNote(i).getPitchType())) {
/*  998 */             localNote3 = new Note(getNote(i).getPitch(), d + getNote(i).getRhythmValue() - paramDouble1, getNote(i).getDynamic());
/*      */ 
/* 1001 */             localPhrase.addNote(localNote3);
/*      */           } else {
/* 1003 */             localNote3 = new Note(getNote(i).getFrequency(), d + getNote(i).getRhythmValue() - paramDouble1, getNote(i).getDynamic());
/*      */ 
/* 1006 */             localPhrase.addNote(localNote3);
/*      */           }
/*      */         }
/*      */ 
/* 1010 */         if (d + getNote(i).getRhythmValue() > paramDouble2) {
/* 1011 */           if (paramBoolean) {
/* 1012 */             localNote3 = new Note(-2147483648, d + getNote(i).getRhythmValue() - paramDouble1, getNote(i).getDynamic());
/*      */ 
/* 1014 */             localPhrase.addNote(localNote3);
/*      */           }
/* 1016 */           else if (!(getNote(i).getPitchType())) {
/* 1017 */             localNote3 = new Note(getNote(i).getPitch(), d + paramDouble2 - paramDouble1, getNote(i).getDynamic());
/*      */ 
/* 1019 */             localPhrase.addNote(localNote3);
/*      */           } else {
/* 1021 */             localNote3 = new Note(getNote(i).getPitch(), d + paramDouble2 - paramDouble1, getNote(i).getDynamic());
/*      */ 
/* 1023 */             localPhrase.addNote(localNote3);
/*      */           }
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/* 1029 */       if ((d >= paramDouble1) && (d < paramDouble2)) {
/* 1030 */         if (d + getNote(i).getRhythmValue() <= paramDouble2) {
/* 1031 */           localPhrase.addNote(getNote(i));
/*      */         } else {
/* 1033 */           localNote3 = new Note(getNote(i).getPitch(), paramDouble2 - d, getNote(i).getDynamic());
/* 1034 */           localPhrase.addNote(localNote3);
/*      */         }
/*      */       }
/* 1037 */       d += getNote(i).getRhythmValue();
/*      */     }
/*      */ 
/* 1040 */     if (d < paramDouble2) {
/* 1041 */       Note localNote2 = new Note(-2147483648, paramDouble2 - d);
/* 1042 */       localPhrase.addNote(localNote2);
/*      */     }
/*      */ 
/* 1045 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public Phrase copy(double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
/*      */   {
/* 1064 */     if ((paramDouble1 >= paramDouble2) || (paramDouble2 < getStartTime())) {
/* 1065 */       System.out.println("invalid arguments in Phrase.copy");
/* 1066 */       return null;
/*      */     }
/* 1068 */     Phrase localPhrase = new Phrase("", paramDouble1, this.instrument);
/*      */ 
/* 1070 */     localPhrase.setAppend(this.append);
/* 1071 */     localPhrase.setPan(this.pan);
/* 1072 */     localPhrase.setLinkedPhrase(this.linkedPhrase);
/* 1073 */     localPhrase.setMyPart(getMyPart());
/* 1074 */     double d1 = getStartTime();
/* 1075 */     if (d1 < 0.0D) d1 = 0.0D;
/*      */ 
/* 1079 */     Enumeration localEnumeration = getNoteList().elements();
/*      */     Note localNote1;
/* 1080 */     while ((paramDouble1 > d1) && (localEnumeration.hasMoreElements())) {
/* 1081 */       localNote1 = (Note)localEnumeration.nextElement();
/* 1082 */       d1 += localNote1.getRhythmValue();
/*      */     }
/*      */ 
/* 1087 */     if (paramDouble1 < d1) {
/* 1088 */       if (d1 < paramDouble2) {
/* 1089 */         if (paramBoolean3) {
/* 1090 */           localPhrase.setStartTime(d1 + getStartTime());
/*      */         } else {
/* 1092 */           localNote1 = new Note(-2147483648, d1 - paramDouble1);
/* 1093 */           localPhrase.addNote(localNote1);
/*      */         }
/*      */       } else {
/* 1096 */         localNote1 = new Note(-2147483648, paramDouble2 - paramDouble1);
/* 1097 */         localPhrase.addNote(localNote1);
/* 1098 */         return localPhrase;
/*      */       }
/*      */     }
/* 1101 */     double d2 = 0.0D;
/*      */     Note localNote2;
/* 1105 */     while ((localEnumeration.hasMoreElements()) && (d1 < paramDouble2)) {
/* 1106 */       localNote2 = ((Note)localEnumeration.nextElement()).copy();
/*      */ 
/* 1108 */       if ((localNote2.getRhythmValue() + d1 > paramDouble2) && (paramBoolean1))
/*      */       {
/* 1110 */         localNote2.setRhythmValue(paramDouble2 - d1, paramBoolean2);
/*      */       }
/* 1112 */       localPhrase.addNote(localNote2);
/* 1113 */       d2 += localNote2.getRhythmValue();
/* 1114 */       d1 += localNote2.getRhythmValue();
/*      */     }
/*      */ 
/* 1118 */     if (d1 < paramDouble2) {
/* 1119 */       localNote2 = new Note(-2147483648, paramDouble2 - d1);
/* 1120 */       localPhrase.addNote(localNote2);
/* 1121 */     } else if (d2 == 0.0D) {
/* 1122 */       localNote2 = new Note(-2147483648, paramDouble2 - paramDouble1);
/* 1123 */       localPhrase.addNote(localNote2);
/*      */     }
/*      */ 
/* 1126 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public Phrase copy(int paramInt1, int paramInt2)
/*      */   {
/* 1137 */     if (paramInt2 >= paramInt1) {
/* 1138 */       System.err.println("jMusic Phrase copy error: lowset pitch is not lower than highest pitch");
/*      */ 
/* 1140 */       System.exit(0);
/*      */     }
/* 1142 */     Phrase localPhrase = new Phrase(this.title + " copy");
/*      */ 
/* 1144 */     localPhrase.position = this.position.copy(localPhrase);
/* 1145 */     localPhrase.setInstrument(this.instrument);
/* 1146 */     localPhrase.setAppend(this.append);
/* 1147 */     localPhrase.setPan(this.pan);
/* 1148 */     localPhrase.setLinkedPhrase(this.linkedPhrase);
/* 1149 */     localPhrase.setMyPart(getMyPart());
/* 1150 */     Enumeration localEnumeration = this.noteList.elements();
/* 1151 */     while (localEnumeration.hasMoreElements()) {
/* 1152 */       Note localNote = ((Note)localEnumeration.nextElement()).copy();
/* 1153 */       if ((localNote.getPitch() > paramInt1) && (localNote.getPitch() < paramInt2)) localNote.setPitch(-2147483648);
/* 1154 */       localPhrase.addNote(localNote);
/*      */     }
/* 1156 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1164 */     String str = new String("-------- jMusic PHRASE: '" + this.title + "' contains " + size() + " notes.  Start time: " + getStartTime() + " --------" + '\n');
/*      */ 
/* 1167 */     if (this.tempo > 0.0D) str = str + "Phrase Tempo = " + this.tempo + '\n';
/* 1168 */     Enumeration localEnumeration = getNoteList().elements();
/* 1169 */     int i = 0;
/* 1170 */     while (localEnumeration.hasMoreElements()) {
/* 1171 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1172 */       str = str + localNote.toString() + '\n';
/*      */     }
/* 1174 */     return str;
/*      */   }
/*      */ 
/*      */   public void empty()
/*      */   {
/* 1181 */     this.noteList.removeAllElements();
/*      */   }
/*      */ 
/*      */   public Phrase alias()
/*      */   {
/* 1192 */     Phrase localPhrase = new Phrase(this.title + " alias", getStartTime(), this.instrument);
/* 1193 */     localPhrase.setTempo(this.tempo);
/* 1194 */     localPhrase.setAppend(this.append);
/* 1195 */     localPhrase.noteList = this.noteList;
/* 1196 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   public int getHighestPitch()
/*      */   {
/* 1203 */     int i = -1;
/* 1204 */     Enumeration localEnumeration = getNoteList().elements();
/* 1205 */     while (localEnumeration.hasMoreElements()) {
/* 1206 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1207 */       if ((!(localNote.getPitchType())) && 
/* 1208 */         (localNote.getPitch() > i)) i = localNote.getPitch();
/*      */     }
/* 1210 */     return i;
/*      */   }
/*      */ 
/*      */   public int getLowestPitch()
/*      */   {
/* 1217 */     int i = 128;
/* 1218 */     Enumeration localEnumeration = getNoteList().elements();
/* 1219 */     while (localEnumeration.hasMoreElements()) {
/* 1220 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1221 */       if ((!(localNote.getPitchType())) && 
/* 1222 */         (localNote.getPitch() < i) && (localNote.getPitch() >= 0)) i = localNote.getPitch();
/*      */     }
/* 1224 */     return i;
/*      */   }
/*      */ 
/*      */   public double getLongestRhythmValue()
/*      */   {
/* 1231 */     double d = 0.0D;
/* 1232 */     Enumeration localEnumeration = getNoteList().elements();
/* 1233 */     while (localEnumeration.hasMoreElements()) {
/* 1234 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1235 */       if (localNote.getRhythmValue() > d) d = localNote.getRhythmValue();
/*      */     }
/* 1237 */     return d;
/*      */   }
/*      */ 
/*      */   public double getShortestRhythmValue()
/*      */   {
/* 1244 */     double d = 1000.0D;
/* 1245 */     Enumeration localEnumeration = getNoteList().elements();
/* 1246 */     while (localEnumeration.hasMoreElements()) {
/* 1247 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1248 */       if (localNote.getRhythmValue() < d) d = localNote.getRhythmValue();
/*      */     }
/* 1250 */     return d;
/*      */   }
/*      */ 
/*      */   public void setDynamic(int paramInt)
/*      */   {
/* 1257 */     Enumeration localEnumeration = getNoteList().elements();
/* 1258 */     while (localEnumeration.hasMoreElements()) {
/* 1259 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1260 */       localNote.setDynamic(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setPitch(int paramInt)
/*      */   {
/* 1268 */     Enumeration localEnumeration = getNoteList().elements();
/* 1269 */     while (localEnumeration.hasMoreElements()) {
/* 1270 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1271 */       localNote.setPitch(paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setRhythmValue(double paramDouble)
/*      */   {
/* 1279 */     Enumeration localEnumeration = getNoteList().elements();
/* 1280 */     while (localEnumeration.hasMoreElements()) {
/* 1281 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1282 */       localNote.setRhythmValue(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public void setDuration(double paramDouble)
/*      */   {
/* 1290 */     Enumeration localEnumeration = getNoteList().elements();
/* 1291 */     while (localEnumeration.hasMoreElements()) {
/* 1292 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1293 */       localNote.setDuration(paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public double getBeatLength()
/*      */   {
/* 1301 */     return getEndTime();
/*      */   }
/*      */ 
/*      */   public Note createNote()
/*      */   {
/* 1375 */     Note localNote = new Note();
/* 1376 */     addNote(localNote);
/* 1377 */     return localNote;
/*      */   }
/*      */ 
/*      */   public void setVolume(int paramInt)
/*      */   {
/* 1384 */     this.volume = paramInt;
/*      */   }
/*      */ 
/*      */   public int getVolume()
/*      */   {
/* 1391 */     return this.volume;
/*      */   }
/*      */ 
/*      */   public void setNote(Note paramNote, int paramInt)
/*      */   {
/* 1400 */     if (paramInt >= getSize()) {
/* 1401 */       System.out.println("jMusic error: Phrase setNote index is too large.");
/* 1402 */       return;
/*      */     }
/* 1404 */     this.noteList.removeElementAt(paramInt);
/* 1405 */     this.noteList.insertElementAt(paramNote, paramInt);
/*      */   }
/*      */ 
/*      */   public void setMute(boolean paramBoolean)
/*      */   {
/* 1413 */     this.mute = paramBoolean;
/*      */   }
/*      */ 
/*      */   public boolean getMute()
/*      */   {
/* 1421 */     return this.mute;
/*      */   }
/*      */ 
/*      */   public void setLength(double paramDouble)
/*      */   {
/* 1429 */     setRhythmValue(paramDouble);
/* 1430 */     setDuration(paramDouble * 0.9D);
/*      */   }
/*      */ 
/*      */   public double getNoteStartTime(int paramInt)
/*      */   {
/* 1440 */     if (paramInt >= size()) return -1.0D;
/* 1441 */     double d = getStartTime();
/* 1442 */     for (int i = 0; i < paramInt; ++i) {
/* 1443 */       d += getNote(i).getRhythmValue();
/*      */     }
/* 1445 */     return d;
/*      */   }
/*      */ 
/*      */   private final class Position
/*      */     implements Serializable
/*      */   {
/*      */     private double startTime;
/*      */     private final Phrase phrase;
/*      */     private boolean isAbsolute;
/*      */     private Position anchor;
/*      */     private Alignment alignment;
/*      */     private double offset;
/*      */     private final Phrase this$0;
/*      */ 
/*      */     private Position(double paramDouble, Phrase paramPhrase)
/*      */     {
/* 1317 */       this.this$0 = paramPhrase;
/*      */ 
/* 1305 */       this.startTime = 0.0D;
/*      */ 
/* 1309 */       this.isAbsolute = false;
/*      */ 
/* 1313 */       this.alignment = Alignment.AFTER;
/*      */ 
/* 1318 */       this.isAbsolute = true;
/* 1319 */       this.startTime = paramDouble;
/* 1320 */       this.phrase = paramPhrase;
/*      */     }
/*      */     private Position(Phrase this$0, Position paramPosition, Alignment paramAlignment, double paramDouble, Phrase paramPhrase)
            {
                 this.this$0 = paramPhrase;
/*      */
/* 1305 */       this.startTime = 0.0D;
/*      */
/* 1309 */       this.isAbsolute = false;
/*      */
/* 1313 */       this.alignment = Alignment.AFTER;
/*      */
/* 1327 */       this.isAbsolute = false;
/* 1328 */       this.anchor = paramPosition;
/* 1329 */       this.alignment = paramAlignment;
/* 1330 */       this.offset = paramDouble;
/* 1331 */       this.phrase = paramPhrase;
             }
/*      */     private final Anchoring getAnchoring() {
/* 1335 */       if (this.isAbsolute) {
/* 1336 */         return null;
/*      */       }
/* 1338 */       return new Anchoring(this.anchor.phrase, this.alignment, this.offset);
/*      */     }
/*      */ 
/*      */     private final void setStartTime(double paramDouble) {
/* 1342 */       this.isAbsolute = true;
/* 1343 */       this.startTime = paramDouble;
/*      */     }
/*      */ 
/*      */     private final double getStartTime() {
/* 1347 */       if (this.isAbsolute) {
/* 1348 */         return this.startTime;
/*      */       }
/* 1350 */       return (this.alignment.determineStartTime(this.phrase.getTotalDuration(), this.anchor.getStartTime(), this.anchor.getEndTime()) + this.offset);
/*      */     }
/*      */ 
/*      */     private final double getEndTime()
/*      */     {
/* 1359 */       return this.phrase.getEndTime();
/*      */     }
/*      */ 
/*      */     private final Position copy(Phrase paramPhrase) {
/* 1363 */       return new Position(this.this$0, this.anchor, this.alignment, this.offset, paramPhrase);
/*      */     }
/*      */ 
/*      */     Position(double paramDouble, Phrase paramPhrase, Phrase.1 param1)
/*      */     {
/* 1304 */       this(this.this$0, paramDouble, paramPhrase);
                }
/*      */     Position(Position paramPosition, Alignment paramAlignment, double paramDouble, Phrase paramPhrase, Phrase param1) { this(this$1, paramPosition, paramAlignment, paramDouble, paramPhrase);
/*      */     }
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Phrase
 * JD-Core Version:    0.5.3
 */