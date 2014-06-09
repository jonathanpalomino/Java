/*     */ package ven.music.data;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import vena.JMC;
/*     */ 
/*     */ public class CPhrase
/*     */   implements JMC, Cloneable, Serializable
/*     */ {
/*     */   private Vector phraseList;
/*     */   private double currentTime;
/*     */   private double startTime;
/*     */   private String title;
/*     */   private int instrument;
/*     */   private boolean append;
/*     */   private Phrase linkedPhrase;
/*     */   private double pan;
/*     */   private double tempo;
/*     */   private int volume;
/*     */   int i;
/*     */   public CPhrase()
/*     */   {
/*  79 */     this("Untitled CPhrase", 0.0D, -1, true);
/*     */   }
/*     */ 
/*     */   public CPhrase(double paramDouble)
/*     */   {
/*  85 */     this("Untitled CPhrase", paramDouble, -1, false);
/*     */   }
/*     */ 
/*     */   public CPhrase(String paramString)
/*     */   {
/*  94 */     this(paramString, 0.0D, -1, true);
/*     */   }
/*     */ 
/*     */   public CPhrase(String paramString, double paramDouble)
/*     */   {
/* 101 */     this(paramString, paramDouble, -1, false);
/*     */   }
/*     */ 
/*     */   public CPhrase(double paramDouble, int paramInt)
/*     */   {
/* 108 */     this("Untitled CPhrase", paramDouble, paramInt, false);
/*     */   }
/*     */ 
/*     */   public CPhrase(String paramString, double paramDouble, int paramInt)
/*     */   {
/* 115 */     this(paramString, paramDouble, paramInt, false);
/*     */   }
/*     */ 
/*     */   public CPhrase(String paramString, double paramDouble, int paramInt, boolean paramBoolean)
/*     */   {
/*  56 */     this.instrument = -1;
/*     */ 
/*  60 */     this.append = false;
/*     */ 
/*  64 */     this.pan = 0.5D;
/*     */ 
/*  66 */     this.tempo = -1.0D;
/*     */ 
/*  68 */     this.volume = 100;
/*     */ 
/* 122 */     this.title = paramString;
/* 123 */     this.startTime = paramDouble;
/* 124 */     this.currentTime = paramDouble;
/* 125 */     this.instrument = paramInt;
/* 126 */     this.append = paramBoolean;
/* 127 */     this.phraseList = new Vector();
/*     */   }
/*     */ 
/*     */   public Vector getPhraseList()
/*     */   {
/* 138 */     return this.phraseList;
/*     */   }
/*     */ 
/*     */   public void setPhraseList(Vector paramVector)
/*     */   {
/* 146 */     this.phraseList = paramVector;
/*     */   }
/*     */ 
/*     */   public void addPhrase(Phrase paramPhrase)
/*     */   {
/* 158 */     if (paramPhrase.getAppend()) paramPhrase.setStartTime(this.startTime);
/*     */ 
/* 161 */     if (paramPhrase.getStartTime() >= this.startTime)
/* 162 */       this.phraseList.addElement(paramPhrase);
/*     */     else System.err.println("Phrase to added to CPhrase: Phrases added to a CPhrase must have a start time at ot after the CPhrase start time.");
/*     */   }
/*     */ 
/*     */   public void removePhrase(Phrase paramPhrase)
/*     */   {
/* 172 */     this.phraseList.removeElement(paramPhrase);
/*     */   }
/*     */ 
/*     */   public void addChord(int[] paramArrayOfInt, double paramDouble)
/*     */   {
/* 181 */     addChord(paramArrayOfInt, paramDouble, 70);
/*     */   }
/*     */ 
/*     */   public void addChord(int[] paramArrayOfInt, double paramDouble, int paramInt)
/*     */   {
/* 194 */     if (this.phraseList.size() < paramArrayOfInt.length) {
/* 195 */       i = paramArrayOfInt.length - this.phraseList.size();
/* 196 */       for (int j = 0; j < i; ++j) {
/* 197 */         Phrase localPhrase = new Phrase(getEndTime(), this.instrument);
/*     */ 
/* 199 */         this.phraseList.addElement(localPhrase);
/*     */       }
/*     */     }
/* 202 */     int i = 0;
/*     */     Note localNote;
/* 204 */     for (; i < paramArrayOfInt.length; ++i) {
/* 205 */       localNote = new Note(paramArrayOfInt[i], paramDouble, paramInt);
/* 206 */       ((Phrase)this.phraseList.elementAt(i)).addNote(localNote);
/*     */     }
/*     */ 
/* 209 */     for (; i < this.phraseList.size(); ++i) {
/* 210 */       localNote = new Note(-2147483648, paramDouble);
/* 211 */       ((Phrase)this.phraseList.elementAt(i)).addNote(localNote);
/*     */     }
/*     */   }
/*     */ 
/*     */   public boolean hasNote(Note paramNote)
/*     */   {
/* 222 */     for (int i = 0; i < this.phraseList.size(); ++i) {
/* 223 */       Phrase localPhrase = (Phrase)this.phraseList.get(i);
/* 224 */       Note[] arrayOfNote = localPhrase.getNoteArray();
/* 225 */       for (int j = 0; j < arrayOfNote.length; ++j) {
/* 226 */         Note localNote = arrayOfNote[j];
/* 227 */         if (paramNote.getNote().equals(localNote.getNote()))
/* 228 */           return true;
/*     */       }
/*     */     }
/* 231 */     return false;
/*     */   }
/*     */ 
/*     */   public void addChord(Note[] paramArrayOfNote)
/*     */   {
/* 241 */     this.currentTime = getEndTime();
/* 242 */     double d = paramArrayOfNote[0].getRhythmValue();
/*     */ 
/* 245 */     if (this.phraseList.size() < paramArrayOfNote.length) {
/* 246 */       i = paramArrayOfNote.length - this.phraseList.size();
/* 247 */       for (int j = 0; j < i; ++j) {
/* 248 */         Phrase localPhrase = new Phrase(getEndTime(), this.instrument);
/*     */ 
/* 250 */         this.phraseList.addElement(localPhrase);
/*     */       }
/*     */     }
/* 253 */     int i = 0;
/* 254 */     for (; i < paramArrayOfNote.length; ++i)
/*     */     {
/* 256 */       paramArrayOfNote[i].setRhythmValue(d);
/*     */ 
/* 258 */       ((Phrase)this.phraseList.elementAt(i)).addNote(paramArrayOfNote[i]);
/*     */     }
/*     */ 
/* 261 */     for (; i < this.phraseList.size(); ++i) {
/* 262 */       Note localNote = new Note(-2147483648, d);
/* 263 */       ((Phrase)this.phraseList.elementAt(i)).addNote(localNote);
/*     */     }
/* 265 */     this.currentTime += d;
/*     */   }
/*     */ 
/*     */   public double getStartTime()
/*     */   {
/* 273 */     return this.startTime;
/*     */   }
/*     */ 
/*     */   public void setStartTime(double paramDouble)
/*     */   {
/* 281 */     double d = paramDouble - this.startTime;
/* 282 */     Enumeration localEnumeration = this.phraseList.elements();
/* 283 */     while (localEnumeration.hasMoreElements())
/*     */     {
/* 285 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 286 */       localPhrase.setStartTime(localPhrase.getStartTime() + d);
/*     */     }
/* 288 */     this.startTime = paramDouble;
/* 289 */     this.append = false;
/*     */   }
/*     */ 
/*     */   public double getEndTime()
/*     */   {
/* 297 */     double d1 = getStartTime();
/* 298 */     Enumeration localEnumeration = this.phraseList.elements();
/* 299 */     while (localEnumeration.hasMoreElements()) {
/* 300 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 301 */       double d2 = localPhrase.getEndTime();
/* 302 */       if (d2 > d1) d1 = d2;
/*     */     }
/* 304 */     return d1;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 312 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String paramString)
/*     */   {
/* 320 */     this.title = paramString;
/*     */   }
/*     */ 
/*     */   public int getInstrument()
/*     */   {
/* 328 */     return this.instrument;
/*     */   }
/*     */ 
/*     */   public void setTitle(int paramInt)
/*     */   {
/* 336 */     if (paramInt >= -1) return; this.instrument = paramInt;
/*     */   }
/*     */ 
/*     */   public boolean getAppend()
/*     */   {
/* 344 */     return this.append;
/*     */   }
/*     */ 
/*     */   public void setAppend(boolean paramBoolean)
/*     */   {
/* 352 */     this.append = paramBoolean;
/*     */   }
/*     */ 
/*     */   public Phrase getLinkedPhrase()
/*     */   {
/* 360 */     return this.linkedPhrase;
/*     */   }
/*     */ 
/*     */   public void setLinkedPhrase(Phrase paramPhrase)
/*     */   {
/* 368 */     this.linkedPhrase = paramPhrase;
/*     */   }
/*     */ 
/*     */   public double getPan()
/*     */   {
/* 376 */     return this.pan;
/*     */   }
/*     */ 
/*     */   public void setPan(double paramDouble)
/*     */   {
/* 384 */     this.pan = paramDouble;
/* 385 */     Enumeration localEnumeration = this.phraseList.elements();
/* 386 */     while (localEnumeration.hasMoreElements()) {
/* 387 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 388 */       localPhrase.setPan(paramDouble);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getHighestPitch()
/*     */   {
/* 396 */     int i = 0;
/* 397 */     Enumeration localEnumeration = getPhraseList().elements();
/* 398 */     while (localEnumeration.hasMoreElements()) {
/* 399 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 400 */       if (localPhrase.getHighestPitch() > i) i = localPhrase.getHighestPitch();
/*     */     }
/* 402 */     return i;
/*     */   }
/*     */ 
/*     */   public int getLowestPitch()
/*     */   {
/* 409 */     int i = 127;
/* 410 */     Enumeration localEnumeration = getPhraseList().elements();
/* 411 */     while (localEnumeration.hasMoreElements()) {
/* 412 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 413 */       if (localPhrase.getLowestPitch() < i) i = localPhrase.getLowestPitch();
/*     */     }
/* 415 */     return i;
/*     */   }
/*     */ 
/*     */   public double getLongestRhythmValue()
/*     */   {
/* 422 */     double d = 0.0D;
/* 423 */     Enumeration localEnumeration = getPhraseList().elements();
/* 424 */     while (localEnumeration.hasMoreElements()) {
/* 425 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 426 */       if (localPhrase.getLongestRhythmValue() > d) d = localPhrase.getLongestRhythmValue();
/*     */     }
/* 428 */     return d;
/*     */   }
/*     */ 
/*     */   public double getShortestRhythmValue()
/*     */   {
/* 435 */     double d = 1000.0D;
/* 436 */     Enumeration localEnumeration = getPhraseList().elements();
/* 437 */     while (localEnumeration.hasMoreElements()) {
/* 438 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 439 */       if (localPhrase.getShortestRhythmValue() < d) d = localPhrase.getShortestRhythmValue();
/*     */     }
/* 441 */     return d;
/*     */   }
/*     */ 
/*     */   public CPhrase copy()
/*     */   {
/* 456 */     Vector localVector = new Vector();
/* 457 */     CPhrase localCPhrase = new CPhrase(this.title + " copy", this.startTime, this.instrument);
/* 458 */     Enumeration localEnumeration = this.phraseList.elements();
/* 459 */     while (localEnumeration.hasMoreElements()) {
/* 460 */       Phrase localPhrase = ((Phrase)localEnumeration.nextElement()).copy();
/* 461 */       localVector.addElement(localPhrase);
/*     */     }
/* 463 */     localCPhrase.setPhraseList(localVector);
/* 464 */     localCPhrase.setAppend(this.append);
/* 465 */     localCPhrase.setLinkedPhrase(this.linkedPhrase);
/* 466 */     return localCPhrase;
/*     */   }
/*     */ 
/*     */   public CPhrase copy(double paramDouble1, double paramDouble2)
/*     */   {
/* 477 */     Vector localVector = new Vector();
/* 478 */     CPhrase localCPhrase = new CPhrase(this.title + " copy", paramDouble1, this.instrument);
/* 479 */     Enumeration localEnumeration = this.phraseList.elements();
/* 480 */     while (localEnumeration.hasMoreElements()) {
/* 481 */       Phrase localPhrase = ((Phrase)localEnumeration.nextElement()).copy(paramDouble1, paramDouble2);
/* 482 */       localPhrase.setStartTime(0.0D);
/* 483 */       localVector.addElement(localPhrase);
/*     */     }
/* 485 */     localCPhrase.setPhraseList(localVector);
/* 486 */     localCPhrase.setAppend(this.append);
/* 487 */     localCPhrase.setLinkedPhrase(this.linkedPhrase);
/* 488 */     return localCPhrase;
/*     */   }
/*     */ 
/*     */   public void empty()
/*     */   {
/* 495 */     this.phraseList.removeAllElements();
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 502 */     String str = new String("---- jMusic CPHRASE: '" + this.title + "' Start time: " + this.startTime + " ----" + '\n');
/*     */ 
/* 504 */     Enumeration localEnumeration = this.phraseList.elements();
/* 505 */     while (localEnumeration.hasMoreElements()) {
/* 506 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 507 */       str = str + localPhrase.toString() + '\n';
/*     */     }
/* 509 */     return str;
/*     */   }
/*     */ 
/*     */   public void setDynamic(int paramInt)
/*     */   {
/* 516 */     Enumeration localEnumeration = getPhraseList().elements();
/* 517 */     while (localEnumeration.hasMoreElements()) {
/* 518 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 519 */       localPhrase.setDynamic(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setPitch(int paramInt)
/*     */   {
/* 527 */     Enumeration localEnumeration = getPhraseList().elements();
/* 528 */     while (localEnumeration.hasMoreElements()) {
/* 529 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 530 */       localPhrase.setPitch(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setRhythmValue(int paramInt)
/*     */   {
/* 538 */     Enumeration localEnumeration = getPhraseList().elements();
/* 539 */     while (localEnumeration.hasMoreElements()) {
/* 540 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 541 */       localPhrase.setRhythmValue(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setDuration(double paramDouble)
/*     */   {
/* 549 */     Enumeration localEnumeration = getPhraseList().elements();
/* 550 */     while (localEnumeration.hasMoreElements()) {
/* 551 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 552 */       localPhrase.setDuration(paramDouble);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void flam()
/*     */   {
/* 561 */     flam(0.05D);
/*     */   }
/*     */ 
/*     */   public void flam(double paramDouble)
/*     */   {
/* 570 */     int i = 0;
/* 571 */     Enumeration localEnumeration1 = this.phraseList.elements();
/* 572 */     while (localEnumeration1.hasMoreElements()) {
/* 573 */       double d = paramDouble * i;
/* 574 */       Phrase localPhrase = (Phrase)localEnumeration1.nextElement();
/* 575 */       Enumeration localEnumeration2 = localPhrase.getNoteList().elements();
/* 576 */       while (localEnumeration2.hasMoreElements()) {
/* 577 */         Note localNote = (Note)localEnumeration2.nextElement();
/* 578 */         localNote.setOffset(d);
/*     */       }
/* 580 */       ++i;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setTempo(double paramDouble)
/*     */   {
/* 589 */     if (paramDouble > 0.0D) {
/* 590 */       this.tempo = paramDouble;
/* 591 */       Enumeration localEnumeration = this.phraseList.elements();
/* 592 */       while (localEnumeration.hasMoreElements()) {
/* 593 */         Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 594 */         localPhrase.setTempo(paramDouble);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public double getTempo()
/*     */   {
/* 603 */     return this.tempo;
/*     */   }
/*     */ 
/*     */   public void setVolume(int paramInt)
/*     */   {
/* 611 */     if (paramInt > 0.0D) {
/* 612 */       this.volume = paramInt;
/* 613 */       Enumeration localEnumeration = this.phraseList.elements();
/* 614 */       while (localEnumeration.hasMoreElements()) {
/* 615 */         Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 616 */         localPhrase.setVolume(paramInt);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public double getVolume()
/*     */   {
/* 625 */     return this.volume;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.CPhrase
 * JD-Core Version:    0.5.3
 */