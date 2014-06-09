/*     */ package ven.music.data;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.io.PrintStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import vena.JMC;
/*     */ 
/*     */ public class Score
/*     */   implements JMC, Cloneable, Serializable
/*     */ {
/*     */   public static final String DEFAULT_TITLE = "Untitled Score";
/*     */   public static final double DEFAULT_TEMPO = 60.0D;
/*     */   public static final int DEFAULT_VOLUME = 100;
/*     */   public static final int DEFAULT_KEY_SIGNATURE = 0;
/*     */   public static final int DEFAULT_KEY_QUALITY = 0;
/*     */   public static final int DEFAULT_NUMERATOR = 4;
/*     */   public static final int DEFAULT_DENOMINATOR = 4;
/*     */   private String title;
/*     */   private Vector partList;
/*     */   private double tempo;
/*     */   private int volume;
/*     */   private int keySignature;
/*     */   private int keyQuality;
/*     */   private int numerator;
/*     */   private int denominator;
/*     */ 
/*     */   public Score()
/*     */   {
/* 119 */     this("Untitled Score");
/*     */   }
/*     */ 
/*     */   public Score(String paramString)
/*     */   {
/* 127 */     this(paramString, 60.0D);
/*     */   }
/*     */ 
/*     */   public Score(double paramDouble)
/*     */   {
/* 135 */     this("Untitled Score", paramDouble);
/*     */   }
/*     */ 
/*     */   public Score(String paramString, double paramDouble)
/*     */   {
/* 144 */     this.title = paramString;
/* 145 */     this.tempo = paramDouble;
/* 146 */     this.partList = new Vector();
/* 147 */     this.volume = 100;
/* 148 */     this.keySignature = 0;
/* 149 */     this.keyQuality = 0;
/* 150 */     this.numerator = 4;
/* 151 */     this.denominator = 4;
/*     */   }
/*     */ 
/*     */   public Score(Part paramPart)
/*     */   {
/* 161 */     addPart(paramPart);
/*     */   }
/*     */ 
/*     */   public Score(String paramString, double paramDouble, Part paramPart)
/*     */   {
/* 171 */     this(paramString, paramDouble);
/* 172 */     addPart(paramPart);
/*     */   }
/*     */ 
/*     */   public Score(Part[] paramArrayOfPart)
/*     */   {
/* 183 */     addPartList(paramArrayOfPart);
/*     */   }
/*     */ 
/*     */   public Score(Part paramPart, String paramString)
/*     */   {
/* 194 */     this(paramString);
/* 195 */     addPart(paramPart);
/*     */   }
/*     */ 
/*     */   public Score(Part[] paramArrayOfPart, String paramString)
/*     */   {
/* 206 */     this(paramString);
/* 207 */     addPartList(paramArrayOfPart);
/*     */   }
/*     */ 
/*     */   public Score(Part paramPart, String paramString, double paramDouble)
/*     */   {
/* 219 */     this(paramString, paramDouble);
/* 220 */     addPart(paramPart);
/*     */   }
/*     */ 
/*     */   public Score(Part[] paramArrayOfPart, String paramString, double paramDouble)
/*     */   {
/* 232 */     this(paramString, paramDouble);
/* 233 */     addPartList(paramArrayOfPart);
/*     */   }
/*     */ 
/*     */   public void add(Part paramPart)
/*     */   {
/* 244 */     addPart(paramPart);
/*     */   }
/*     */ 
/*     */   public void addPart(Part paramPart)
/*     */   {
/* 251 */     paramPart.setMyScore(this);
/* 252 */     this.partList.addElement(paramPart);
/*     */   }
/*     */ 
/*     */   public void insertPart(Part paramPart, int paramInt)
/*     */     throws ArrayIndexOutOfBoundsException
/*     */   {
/* 267 */     this.partList.insertElementAt(paramPart, paramInt);
/* 268 */     paramPart.setMyScore(this);
/*     */   }
/*     */ 
/*     */   public void addPartList(Part[] paramArrayOfPart)
/*     */   {
/* 276 */     for (int i = 0; i < paramArrayOfPart.length; ++i)
/* 277 */       addPart(paramArrayOfPart[i]);
/*     */   }
/*     */ 
/*     */   public void removePart(int paramInt)
/*     */   {
/* 286 */     Vector localVector = this.partList;
/*     */     try {
/* 288 */       localVector.removeElement(localVector.elementAt(paramInt));
/*     */     } catch (RuntimeException localRuntimeException) {
/* 290 */       System.err.println("The Part index to be deleted must be within the score.");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void removePart(Part paramPart)
/*     */   {
/* 299 */     this.partList.removeElement(paramPart);
/*     */   }
/*     */ 
/*     */   public void removeLastPart()
/*     */   {
/* 306 */     Vector localVector = this.partList;
/* 307 */     localVector.removeElement(localVector.lastElement());
/*     */   }
/*     */ 
/*     */   public void removeAllParts()
/*     */   {
/* 314 */     this.partList.removeAllElements();
/*     */   }
/*     */ 
/*     */   public Vector getPartList()
/*     */   {
/* 321 */     return this.partList;
/*     */   }
/*     */ 
/*     */   public Part[] getPartArray()
/*     */   {
/* 329 */     Vector localVector = (Vector)this.partList.clone();
/* 330 */     Part[] arrayOfPart = new Part[localVector.size()];
/* 331 */     for (int i = 0; i < arrayOfPart.length; ++i) {
/* 332 */       arrayOfPart[i] = ((Part)localVector.elementAt(i));
/*     */     }
/* 334 */     return arrayOfPart;
/*     */   }
/*     */ 
/*     */   public Part getPart(String paramString)
/*     */   {
/* 343 */     Enumeration localEnumeration = this.partList.elements();
/* 344 */     while (localEnumeration.hasMoreElements()) {
/* 345 */       Part localPart = (Part)localEnumeration.nextElement();
/* 346 */       if (localPart.getTitle().equalsIgnoreCase(paramString)) {
/* 347 */         return localPart;
/*     */       }
/*     */     }
/* 350 */     return null;
/*     */   }
/*     */ 
/*     */   public Part getPart(int paramInt)
/*     */   {
/* 359 */     Enumeration localEnumeration = this.partList.elements();
/* 360 */     int i = 0;
/* 361 */     while (localEnumeration.hasMoreElements()) {
/* 362 */       Part localPart = (Part)localEnumeration.nextElement();
/* 363 */       if (i == paramInt) {
/* 364 */         return localPart;
/*     */       }
/* 366 */       ++i;
/*     */     }
/* 368 */     return null;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 381 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String paramString)
/*     */   {
/* 388 */     this.title = paramString;
/*     */   }
/*     */ 
/*     */   public double getTempo()
/*     */   {
/* 396 */     return this.tempo;
/*     */   }
/*     */ 
/*     */   public void setTempo(double paramDouble)
/*     */   {
/* 404 */     this.tempo = paramDouble;
/*     */   }
/*     */ 
/*     */   public int getVolume()
/*     */   {
/* 412 */     return this.volume;
/*     */   }
/*     */ 
/*     */   public void setVolume(int paramInt)
/*     */   {
/* 420 */     this.volume = paramInt;
/*     */   }
/*     */ 
/*     */   public int getKeySignature()
/*     */   {
/* 429 */     return this.keySignature;
/*     */   }
/*     */ 
/*     */   public void setKeySignature(int paramInt)
/*     */   {
/* 438 */     this.keySignature = paramInt;
/*     */   }
/*     */ 
/*     */   public int getKeyQuality()
/*     */   {
/* 447 */     return this.keyQuality;
/*     */   }
/*     */ 
/*     */   public void setKeyQuality(int paramInt)
/*     */   {
/* 456 */     this.keyQuality = paramInt;
/*     */   }
/*     */ 
/*     */   public void setTimeSignature(int paramInt1, int paramInt2)
/*     */   {
/* 465 */     this.numerator = paramInt1;
/* 466 */     this.denominator = paramInt2;
/*     */   }
/*     */ 
/*     */   public Point getTimeSignature()
/*     */   {
/* 478 */     return new Point(this.numerator, this.denominator);
/*     */   }
/*     */ 
/*     */   public void setNumerator(int paramInt)
/*     */   {
/* 486 */     this.numerator = paramInt;
/*     */   }
/*     */ 
/*     */   public void setDenominator(int paramInt)
/*     */   {
/* 494 */     this.denominator = paramInt;
/*     */   }
/*     */ 
/*     */   public int getNumerator()
/*     */   {
/* 504 */     return this.numerator;
/*     */   }
/*     */ 
/*     */   public int getDenominator()
/*     */   {
/* 512 */     return this.denominator;
/*     */   }
/*     */ 
/*     */   public Score copy()
/*     */   {
/* 520 */     Score localScore = new Score(this.title + " copy");
/* 521 */     localScore.setTempo(this.tempo);
/* 522 */     localScore.setVolume(this.volume);
/* 523 */     localScore.setTimeSignature(this.numerator, this.denominator);
/* 524 */     Enumeration localEnumeration = this.partList.elements();
/* 525 */     while (localEnumeration.hasMoreElements()) {
/* 526 */       Part localPart = (Part)localEnumeration.nextElement();
/* 527 */       localScore.addPart(localPart.copy());
/*     */     }
/* 529 */     return localScore;
/*     */   }
/*     */ 
/*     */   public Score copy(double paramDouble1, double paramDouble2) {
/* 533 */     Score localScore = copy();
/* 534 */     localScore.removeAllParts();
/* 535 */     localScore.setTempo(this.tempo);
/* 536 */     localScore.setVolume(this.volume);
/* 537 */     localScore.setTimeSignature(this.numerator, this.denominator);
/* 538 */     int i = size();
/* 539 */     for (int j = 0; j < i; ++j) {
/* 540 */       localScore.addPart(getPart(j).copy(paramDouble1, paramDouble2));
/*     */     }
/* 542 */     return localScore;
/*     */   }
/*     */ 
/*     */   public double getEndTime()
/*     */   {
/* 550 */     double d1 = 0.0D;
/* 551 */     Enumeration localEnumeration = this.partList.elements();
/* 552 */     while (localEnumeration.hasMoreElements()) {
/* 553 */       Part localPart = (Part)localEnumeration.nextElement();
/* 554 */       double d2 = localPart.getEndTime();
/* 555 */       if (d2 > d1) d1 = d2;
/*     */     }
/* 557 */     return d1;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 565 */     String str = new String("***** jMusic SCORE: '" + this.title + "' contains " + size() + " parts. ****" + '\n');
/*     */ 
/* 567 */     str = str + "Score Tempo = " + this.tempo + " bpm" + '\n';
/* 568 */     Enumeration localEnumeration = this.partList.elements();
/* 569 */     while (localEnumeration.hasMoreElements()) {
/* 570 */       Part localPart = (Part)localEnumeration.nextElement();
/* 571 */       str = str + localPart.toString() + '\n';
/*     */     }
/* 573 */     return str;
/*     */   }
/*     */ 
/*     */   public void empty()
/*     */   {
/* 580 */     empty(false);
/*     */   }
/*     */ 
/*     */   public void empty(boolean paramBoolean)
/*     */   {
/* 589 */     if (paramBoolean) {
/* 590 */       Enumeration localEnumeration1 = getPartList().elements();
/* 591 */       while (localEnumeration1.hasMoreElements()) {
/* 592 */         Part localPart = (Part)localEnumeration1.nextElement();
/* 593 */         Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 594 */         while (localEnumeration2.hasMoreElements()) {
/* 595 */           Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 596 */           Enumeration localEnumeration3 = localPart.getPhraseList().elements();
/* 597 */           while (localEnumeration3.hasMoreElements()) {
/* 598 */             Note localNote = (Note)localEnumeration3.nextElement();
/* 599 */             localNote = null;
/*     */           }
/* 601 */           localPhrase = null;
/*     */         }
/* 603 */         localPart = null;
/*     */       }
/*     */     }
/* 606 */     this.partList.removeAllElements();
/*     */   }
/*     */ 
/*     */   public int length()
/*     */   {
/* 614 */     return size();
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 622 */     return this.partList.size();
/*     */   }
/*     */ 
/*     */   public int getSize()
/*     */   {
/* 630 */     return this.partList.size();
/*     */   }
/*     */ 
/*     */   public void clean()
/*     */   {
/* 637 */     Enumeration localEnumeration = getPartList().elements();
/* 638 */     while (localEnumeration.hasMoreElements()) {
/* 639 */       Part localPart = (Part)localEnumeration.nextElement();
/*     */ 
/* 641 */       localPart.clean();
/*     */ 
/* 643 */       if (localPart.getPhraseList().size() == 0)
/* 644 */         removePart(localPart);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getHighestPitch()
/*     */   {
/* 653 */     int i = 0;
/* 654 */     Enumeration localEnumeration = getPartList().elements();
/* 655 */     while (localEnumeration.hasMoreElements()) {
/* 656 */       Part localPart = (Part)localEnumeration.nextElement();
/* 657 */       if (localPart.getHighestPitch() > i) i = localPart.getHighestPitch();
/*     */     }
/* 659 */     return i;
/*     */   }
/*     */ 
/*     */   public int getLowestPitch()
/*     */   {
/* 666 */     int i = 127;
/* 667 */     Enumeration localEnumeration = getPartList().elements();
/* 668 */     while (localEnumeration.hasMoreElements()) {
/* 669 */       Part localPart = (Part)localEnumeration.nextElement();
/* 670 */       if (localPart.getLowestPitch() < i) i = localPart.getLowestPitch();
/*     */     }
/* 672 */     return i;
/*     */   }
/*     */ 
/*     */   public double getLongestRhythmValue()
/*     */   {
/* 679 */     double d = 0.0D;
/* 680 */     Enumeration localEnumeration = getPartList().elements();
/* 681 */     while (localEnumeration.hasMoreElements()) {
/* 682 */       Part localPart = (Part)localEnumeration.nextElement();
/* 683 */       if (localPart.getLongestRhythmValue() > d) d = localPart.getLongestRhythmValue();
/*     */     }
/* 685 */     return d;
/*     */   }
/*     */ 
/*     */   public double getShortestRhythmValue()
/*     */   {
/* 692 */     double d = 1000.0D;
/* 693 */     Enumeration localEnumeration = getPartList().elements();
/* 694 */     while (localEnumeration.hasMoreElements()) {
/* 695 */       Part localPart = (Part)localEnumeration.nextElement();
/* 696 */       if (localPart.getShortestRhythmValue() < d) d = localPart.getShortestRhythmValue();
/*     */     }
/* 698 */     return d;
/*     */   }
/*     */ 
/*     */   public void setPan(double paramDouble)
/*     */   {
/* 706 */     Enumeration localEnumeration = this.partList.elements();
/* 707 */     while (localEnumeration.hasMoreElements()) {
/* 708 */       Part localPart = (Part)localEnumeration.nextElement();
/* 709 */       localPart.setPan(paramDouble);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Part createPart()
/*     */   {
/* 718 */     Part localPart = new Part();
/* 719 */     addPart(localPart);
/* 720 */     return localPart;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Score
 * JD-Core Version:    0.5.3
 */