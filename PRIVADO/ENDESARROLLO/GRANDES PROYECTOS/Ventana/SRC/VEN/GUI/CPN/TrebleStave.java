/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class TrebleStave extends Stave
/*     */   implements JMC
/*     */ {
/* 321 */   private Style style = new Style.JMusic();
/*     */ 
/* 339 */   private int tonic = 0;
/*     */ 
/* 341 */   protected int[] scale = JMC.MAJOR_SCALE;
/*     */   public static final int MAX_HEIGHT = 500;
/*     */   public static final int MAX_WIDTH = 2000;
/*     */   private double beatCounter;
/* 553 */   private boolean isFirstNoteInTie = true;
/*     */ 
/* 557 */   private boolean firstAccidentalDisplayed = false;
/*     */ 
/* 559 */   private boolean isTied = false;
/*     */ 
/* 563 */   private boolean semitoneShiftUp = false;
/*     */   private boolean extraImagesUsed;
/*     */   private int savedBeatWidth;
/*     */   private int savedBeatWidth2;
/* 575 */   private int lastChordDisplayed = -1;
/*     */ 
/* 577 */   private int lastPosition = 0;
/*     */ 
/* 583 */   private String[] chordStrings = { "I", "II", "III", "IV", "V", "VI", "VII", "." };
/*     */ 
/*     */   public void setAccidentalDisplayStyle(Style paramStyle)
/*     */   {
/* 329 */     if (paramStyle == Style.TRADITIONAL)
/* 330 */       this.style = new Style.Trad();
/* 331 */     else if (paramStyle == Style.JMUSIC)
/* 332 */       this.style = new Style.JMusic();
/*     */     else
/* 334 */       throw new RuntimeException("Unknown Accidental Display Style");
/*     */   }
/*     */ 
/*     */   public TrebleStave()
/*     */   {
/*     */   }
/*     */ 
/*     */   public TrebleStave(Phrase paramPhrase)
/*     */   {
/* 362 */     super(paramPhrase);
/*     */   }
/*     */ 
/*     */   public TrebleStave(Images paramImages)
/*     */   {
/* 373 */     super(paramImages);
/*     */   }
/*     */ 
/*     */   public TrebleStave(Phrase paramPhrase, Images paramImages)
/*     */   {
/* 385 */     super(paramPhrase, paramImages);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 396 */     if (this.image == null) {
/* 397 */       this.image = createImage(2000, 500);
/* 398 */       this.g = this.image.getGraphics();
/*     */     }
/*     */ 
/* 401 */     this.g.setFont(this.font);
/*     */ 
/* 403 */     this.beatCounter = 0.0D;
/*     */ 
/* 405 */     this.notePositions.removeAllElements();
/*     */ 
/* 407 */     if (getDisplayTitle()) this.g.drawString(this.title, this.rightMargin, this.bPos - 10);
/*     */ 
/* 409 */     int i = 0;
/*     */ 
/* 411 */     this.style.initialise(this.keySignature);
/*     */     int j;
/*     */     int l;
/* 414 */     if ((this.keySignature > 0) && (this.keySignature < 8)) {
/* 415 */       for (j = 0; j < this.keySignature; ++j)
/*     */       {
/* 417 */         l = this.notePosOffset[(this.sharps[j] % 12)] + this.bPos - 4 + (5 - (this.sharps[j] / 12)) * 24 + (6 - (this.sharps[j] / 12)) * 4;
/*     */ 
/* 419 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + i, l, this);
/*     */ 
/* 421 */         i += 10;
/*     */ 
/* 423 */         this.keySigWidth = i;
/*     */       }
/*     */     }
/* 426 */     else if ((this.keySignature < 0) && (this.keySignature > -8)) {
/* 427 */       for (j = 0; j < Math.abs(this.keySignature); ++j)
/*     */       {
/* 429 */         l = this.notePosOffset[(this.flats[j] % 12)] + this.bPos - 4 + (5 - (this.flats[j] / 12)) * 24 + (6 - (this.flats[j] / 12)) * 4;
/*     */ 
/* 431 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + i, l, this);
/*     */ 
/* 433 */         i += 10;
/*     */       }
/*     */     }
/*     */ 
/* 437 */     this.keySigWidth = (i + 3);
/*     */ 
/* 440 */     if (this.metre != 0.0D) {
/* 441 */       Image[] arrayOfImage = { this.one, this.two, this.three, this.four, this.five, this.six, this.seven, this.eight, this.nine };
/*     */ 
/* 444 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13, this);
/*     */ 
/* 446 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29, this);
/* 447 */       this.timeSigWidth = 30; } else {
/* 448 */       this.timeSigWidth = 5;
/*     */     }
/* 450 */     this.totalBeatWidth = (this.rightMargin + this.clefWidth + this.keySigWidth + this.timeSigWidth);
/*     */ 
/* 458 */     this.lastChordDisplayed = -1;
/*     */ 
/* 461 */     for (int k = 0; k < this.phrase.size(); ++k) {
/* 462 */       l = this.phrase.getNote(k).getPitch();
/*     */       int i1;
/* 467 */       if ((l == -2147483648) || (this.phrase.getNote(k).getRhythmValue() == 0.0D))
/* 468 */         i1 = this.notePosOffset[11] + this.bPos - 4 + 0 + 4;
/*     */       else {
/* 470 */         i1 = this.notePosOffset[(l % 12)] + this.bPos - 4 + (5 - (l / 12)) * 24 + (6 - (l / 12)) * 4;
/*     */       }
/*     */ 
/* 474 */       this.firstAccidentalDisplayed = false;
/*     */ 
/* 476 */       this.semitoneShiftUp = false;
/* 477 */       this.isTied = false;
/* 478 */       this.isFirstNoteInTie = true;
/* 479 */       this.extraImagesUsed = false;
/* 480 */       this.savedBeatWidth = this.totalBeatWidth;
/* 481 */       this.savedBeatWidth2 = 0;
/* 482 */       double d1 = this.phrase.getNote(k).getRhythmValue();
/* 483 */       double d2 = this.metre - (this.beatCounter % this.metre);
/*     */ 
/* 485 */       while (d2 < d1) {
/* 486 */         this.isTied = true;
/* 487 */         drawNote(l, d2, i1);
/*     */ 
/* 489 */         d1 -= d2;
/* 490 */         d2 = this.metre - (this.beatCounter % this.metre);
/*     */       }
/*     */ 
/* 494 */       drawNote(l, d1, i1);
/*     */     }
/*     */ 
/* 516 */     for (k = 0; k < 5; ++k) {
/* 517 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + k * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + k * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 523 */     this.g.setColor(Color.lightGray);
/* 524 */     for (k = 0; k < 5; ++k) {
/* 525 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + k * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + k * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 536 */     this.g.setColor(Color.black);
/*     */ 
/* 538 */     this.g.drawImage(this.trebleClef, this.rightMargin + 7, this.bPos - 4, this);
/*     */ 
/* 543 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*     */ 
/* 546 */     this.g.setColor(getBackground());
/* 547 */     this.g.fillRect(0, 0, 2000, 500);
/* 548 */     this.g.setColor(getForeground());
/*     */   }
/*     */ 
/*     */   private void drawNote(int paramInt1, double paramDouble, int paramInt2)
/*     */   {
/* 588 */     this.requiresMoreThanOneImage = false;
/* 589 */     this.excessRhythmValue = 0.0D;
/*     */ 
/* 617 */     chooseImage(paramInt1, paramDouble, 71, 0, 71);
/*     */ 
/* 619 */     drawNote2(paramInt1, paramDouble - this.excessRhythmValue, paramInt2);
/*     */ 
/* 621 */     if (this.requiresMoreThanOneImage) {
/* 622 */       drawNote(paramInt1, this.excessRhythmValue, paramInt2);
/*     */ 
/* 624 */       this.extraImagesUsed = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void drawNote2(int paramInt1, double paramDouble, int paramInt2)
/*     */   {
/* 633 */     if ((paramInt1 != -2147483648) && (paramDouble != 0.0D)) {
/* 634 */       Accidental localAccidental = this.style.selectAccidental(paramInt1, paramDouble);
/*     */ 
/* 636 */       if (localAccidental == Accidental.SHARP) {
/* 637 */         if (!(this.firstAccidentalDisplayed)) {
/* 638 */           displayImage(this.g, this.sharp, this.totalBeatWidth - 9, paramInt2);
/*     */         }
/*     */       }
/* 641 */       else if (localAccidental == Accidental.FLAT) {
/* 642 */         paramInt2 -= 4;
/* 643 */         if (!(this.firstAccidentalDisplayed)) {
/* 644 */           displayImage(this.g, this.flat, this.totalBeatWidth - 9, paramInt2);
/*     */         }
/* 646 */         ++paramInt1;
/* 647 */         this.semitoneShiftUp = true;
/* 648 */       } else if ((localAccidental == Accidental.NATURAL) && 
/* 649 */         (!(this.firstAccidentalDisplayed))) {
/* 650 */         displayImage(this.g, this.natural, this.totalBeatWidth - 7, paramInt2);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 655 */     this.firstAccidentalDisplayed = true;
/*     */ 
/* 658 */     displayImage(this.g, this.currImage, this.totalBeatWidth, paramInt2);
/*     */ 
/* 661 */     this.notePositions.addElement(new Integer(this.totalBeatWidth));
/* 662 */     this.notePositions.addElement(new Integer(paramInt2));
/*     */     int i;
/* 664 */     if (this.dottedNote) {
/* 665 */       i = 1;
/* 666 */       for (int j = 0; j < this.lineNotes.length; ++j) {
/* 667 */         if ((this.lineNotes[j] + 12 != paramInt1) && (this.lineNotes[j] + 36 != paramInt1) && (this.lineNotes[j] + 60 != paramInt1) && (this.lineNotes[j] + 84 != paramInt1) && (this.lineNotes[j] + 108 != paramInt1) && (paramInt1 != -2147483648))
/*     */         {
/*     */           continue;
/*     */         }
/*     */ 
/* 673 */         displayImage(this.g, this.dot, this.totalBeatWidth + 1, paramInt2 - 4);
/* 674 */         i = 0;
/* 675 */         j = this.lineNotes.length;
/*     */       }
/*     */ 
/* 678 */       if (i != 0) {
/* 679 */         displayImage(this.g, this.dot, this.totalBeatWidth + 1, paramInt2);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 684 */     if ((paramInt1 <= 61) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 52, this.totalBeatWidth + 12, this.bPos + 52);
/* 685 */     if ((paramInt1 <= 58) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 60, this.totalBeatWidth + 12, this.bPos + 60);
/* 686 */     if ((paramInt1 <= 54) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 68, this.totalBeatWidth + 12, this.bPos + 68);
/* 687 */     if ((paramInt1 <= 51) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 76, this.totalBeatWidth + 12, this.bPos + 76);
/* 688 */     if ((paramInt1 <= 48) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 84, this.totalBeatWidth + 12, this.bPos + 84);
/*     */ 
/* 690 */     if ((paramInt1 >= 81) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 4, this.totalBeatWidth + 12, this.bPos + 4);
/* 691 */     if ((paramInt1 >= 84) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 4, this.totalBeatWidth + 12, this.bPos - 4);
/* 692 */     if ((paramInt1 >= 88) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 12, this.totalBeatWidth + 12, this.bPos - 12);
/* 693 */     if ((paramInt1 >= 91) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 20, this.totalBeatWidth + 12, this.bPos - 20);
/* 694 */     if ((paramInt1 >= 95) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 28, this.totalBeatWidth + 12, this.bPos - 28);
/*     */ 
/* 697 */     this.savedBeatWidth2 = this.totalBeatWidth;
/*     */ 
/* 699 */     if ((((this.isTied) || (this.extraImagesUsed))) && (this.isNote) && (!(this.isFirstNoteInTie)))
/*     */     {
/* 701 */       i = paramInt2 + 19 - ((this.semitoneShiftUp) ? 4 : 0);
/*     */ 
/* 703 */       if (this.isUp) {
/* 704 */         this.g.drawImage(this.tieUnder, this.savedBeatWidth - 3 + 9, i + 17, this.savedBeatWidth2 + 19 - 9, i + 17 + this.tieUnder.getHeight(this), 0, 0, this.tieUnder.getWidth(this), this.tieUnder.getHeight(this), this);
/*     */       }
/*     */       else
/*     */       {
/* 714 */         this.g.drawImage(this.tieOver, this.savedBeatWidth - 3 + 9, i - 20, this.savedBeatWidth2 + 19 - 9, i - 20 + this.tieOver.getHeight(this), 0, 0, this.tieOver.getWidth(this), this.tieOver.getHeight(this), this);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 725 */     if ((this.isFirstNoteInTie = 1) != 0) {
/* 726 */       this.isFirstNoteInTie = false;
/*     */     }
/*     */ 
/* 729 */     this.savedBeatWidth = this.totalBeatWidth;
/*     */ 
/* 732 */     this.totalBeatWidth += this.currBeatWidth;
/* 733 */     this.dottedNote = false;
/*     */ 
/* 736 */     this.beatCounter += (int)(paramDouble / 0.25D) * 0.25D;
/*     */ 
/* 740 */     if ((this.metre == 0.0D) || 
/* 741 */       (this.beatCounter % this.metre != 0.0D)) return;
/* 742 */     this.g.drawLine(this.totalBeatWidth, this.bPos + 12, this.totalBeatWidth, this.bPos + 44);
/* 743 */     this.style.processBarLine();
/*     */ 
/* 745 */     if (this.barNumbers) this.g.drawString("" + (int)(this.beatCounter / this.metre + 1.0D + this.phrase.getStartTime()), this.totalBeatWidth - 4, this.bPos);
/* 746 */     this.totalBeatWidth += 12;
/*     */   }
/*     */ 
/*     */   private void displayImage(Graphics paramGraphics, Image paramImage, int paramInt1, int paramInt2)
/*     */   {
/* 755 */     paramGraphics.drawImage(paramImage, paramInt1, paramInt2, this);
/*     */   }
/*     */ 
/*     */   public static abstract class Style
/*     */   {
/*  77 */     int[] sharpPitches = { 77, 72, 79, 74, 69, 76, 71 };
/*     */ 
/*  79 */     int[] flatPitches = { 71, 76, 69, 74, 67, 72, 65 };
/*     */ 
/*  85 */     public static final Style TRADITIONAL = new Trad();
/*     */ 
/*  95 */     public static final Style JMUSIC = new JMusic();
/*     */     private String name;
/*     */ 
/*     */     Style(String paramString)
/*     */     {
/* 305 */       this.name = paramString;
/*     */     }
/*     */ 
/*     */     public String toString() {
/* 309 */       return this.name + " of displaying accidentals";
/*     */     }
/*     */ 
/*     */     abstract void initialise(int paramInt);
/*     */ 
/*     */     abstract TrebleStave.Accidental selectAccidental(int paramInt, double paramDouble);
/*     */ 
/*     */     abstract void processBarLine();
/*     */ 
/*     */     private static final class JMusic extends TrebleStave.Style
/*     */     {
/* 195 */       private Vector chromaticallyAffectedPitches = new Vector();
/*     */       private int keySignature;
/*     */       private int keyAccidentals;
/*     */ 
/*     */       public JMusic()
/*     */       {
/* 220 */         super("JMusic style (with superfluous sharps and flats)");
/*     */ 
/* 222 */         initialise(0);
/*     */       }
/*     */ 
/*     */       void initialise(int paramInt) {
/* 226 */         this.chromaticallyAffectedPitches = new Vector();
/* 227 */         this.keySignature = paramInt;
/* 228 */         this.keyAccidentals = 0;
/*     */         int k;
/*     */         int l;
/* 229 */         if ((paramInt > 0) && (paramInt < 8)) {
/* 230 */           for (int i = 0; i < paramInt; ++i) {
/* 231 */             k = this.sharpPitches[i] % 12;
/* 232 */             l = 0;
/* 233 */             while (l <= 127)
/*     */             {
/* 235 */               if (l % 12 == k) {
/* 236 */                 this.chromaticallyAffectedPitches.addElement(new Integer(l));
/*     */ 
/* 238 */                 this.keyAccidentals += 1;
/*     */               }
/* 234 */               ++l;
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/* 242 */         else if ((paramInt < 0) && (paramInt > -8))
/* 243 */           for (int j = 0; j > paramInt; --j) {
/* 244 */             k = this.flatPitches[(-j)] % 12;
/* 245 */             l = 0;
/* 246 */             while (l <= 127)
/*     */             {
/* 248 */               if (l % 12 == k) {
/* 249 */                 this.chromaticallyAffectedPitches.addElement(new Integer(l));
/*     */ 
/* 251 */                 this.keyAccidentals += 1;
/*     */               }
/* 247 */               ++l;
/*     */             }
/*     */           }
/*     */       }
/*     */ 
/*     */       TrebleStave.Accidental selectAccidental(int paramInt, double paramDouble)
/*     */       {
/* 261 */         if ((paramInt == -2147483648) || (paramDouble == 0.0D))
/*     */         {
/* 263 */           return TrebleStave.Accidental.NONE;
/*     */         }
/* 265 */         if ((paramInt % 12 == 1) || (paramInt % 12 == 3) || (paramInt % 12 == 6) || (paramInt % 12 == 8) || (paramInt % 12 == 10))
/*     */         {
/* 268 */           if (this.keySignature > -1) {
/* 269 */             this.chromaticallyAffectedPitches.addElement(new Integer(paramInt - 1));
/*     */ 
/* 271 */             return TrebleStave.Accidental.SHARP;
/*     */           }
/* 273 */           this.chromaticallyAffectedPitches.addElement(new Integer(paramInt + 1));
/*     */ 
/* 275 */           return TrebleStave.Accidental.FLAT;
/*     */         }
/*     */ 
/* 278 */         int i = this.chromaticallyAffectedPitches.size();
/*     */ 
/* 280 */         for (int k = 0; k < i; ++k) {
/* 281 */           int j = ((Integer)this.chromaticallyAffectedPitches.elementAt(k)).intValue();
/*     */ 
/* 284 */           if (j == paramInt) {
/* 285 */             if (k > this.keyAccidentals - 1) {
/* 286 */               this.chromaticallyAffectedPitches.removeElementAt(k);
/*     */             }
/*     */ 
/* 289 */             return TrebleStave.Accidental.NATURAL;
/*     */           }
/*     */         }
/*     */ 
/* 293 */         return TrebleStave.Accidental.NONE;
/*     */       }
/*     */ 
/*     */       void processBarLine()
/*     */       {
/*     */       }
/*     */     }
/*     */ 
/*     */     private static final class Trad extends TrebleStave.Style
/*     */     {
/*  99 */       private boolean[] accidentalRequiredByKeySignature = new boolean[12];
/*     */ 
/* 102 */       private static final int[] SHARP_ACCIDENTAL_PAIRS = { 0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5, 6 };
/*     */ 
/* 105 */       private static final int[] FLAT_ACCIDENTAL_PAIRS = { 0, 1, 1, 2, 2, 3, 4, 4, 5, 5, 6, 6 };
/*     */ 
/* 108 */       private int[] degreeToAccidentalPair = SHARP_ACCIDENTAL_PAIRS;
/*     */ 
/* 111 */       private boolean[] accidentalInEffect = new boolean[7];
/*     */ 
/* 114 */       private int keySignature = 0;
/*     */ 
/*     */       public Trad() {
/* 117 */         super("Traditional style");
/* 118 */         initialise(0);
/*     */       }
/*     */ 
/*     */       private void setBooleanArrayToFalse(boolean[] paramArrayOfBoolean) {
/* 122 */         for (int i = 0; i < paramArrayOfBoolean.length; ++i)
/* 123 */           paramArrayOfBoolean[i] = false;
/*     */       }
/*     */ 
/*     */       void initialise(int paramInt)
/*     */       {
/* 128 */         this.keySignature = paramInt;
/* 129 */         if (paramInt < 0)
/* 130 */           this.degreeToAccidentalPair = FLAT_ACCIDENTAL_PAIRS;
/*     */         else {
/* 132 */           this.degreeToAccidentalPair = SHARP_ACCIDENTAL_PAIRS;
/*     */         }
/* 134 */         setBooleanArrayToFalse(this.accidentalRequiredByKeySignature);
/*     */ 
/* 136 */         this.accidentalRequiredByKeySignature[1] = true;
/* 137 */         this.accidentalRequiredByKeySignature[3] = true;
/* 138 */         this.accidentalRequiredByKeySignature[6] = true;
/* 139 */         this.accidentalRequiredByKeySignature[8] = true;
/* 140 */         this.accidentalRequiredByKeySignature[10] = true;
/* 141 */         for (int i = 0; i < Math.abs(paramInt); ++i) {
/* 142 */           if (paramInt < 0) {
/* 143 */             this.accidentalRequiredByKeySignature[(this.flatPitches[i] % 12)] = true;
/*     */ 
/* 145 */             this.accidentalRequiredByKeySignature[((this.flatPitches[i] - 1) % 12)] = false;
/*     */           }
/*     */           else {
/* 148 */             this.accidentalRequiredByKeySignature[(this.sharpPitches[i] % 12)] = true;
/*     */ 
/* 150 */             this.accidentalRequiredByKeySignature[((this.sharpPitches[i] + 1) % 12)] = false;
/*     */           }
/*     */         }
/*     */ 
/* 154 */         setBooleanArrayToFalse(this.accidentalInEffect);
/*     */       }
/*     */ 
/*     */       TrebleStave.Accidental selectAccidental(int paramInt, double paramDouble)
/*     */       {
/* 160 */         if ((paramInt == -2147483648) || (paramDouble == 0.0D))
/*     */         {
/* 162 */           return TrebleStave.Accidental.NONE;
/*     */         }
/*     */ 
/* 165 */         int i = paramInt % 12;
/*     */ 
/* 167 */         int j = this.degreeToAccidentalPair[i];
/* 168 */         if ((this.accidentalRequiredByKeySignature[i] ^ this.accidentalInEffect[j]) != 0)
/*     */         {
/* 171 */           this.accidentalInEffect[j] = ((this.accidentalInEffect[j] == 0) ? 1 : false);
/*     */ 
/* 174 */           if ((i == 1) || (i == 3) || (i == 6) || (i == 8) || (i == 10))
/*     */           {
/* 176 */             if (this.keySignature > -1) {
/* 177 */               return TrebleStave.Accidental.SHARP;
/*     */             }
/* 179 */             return TrebleStave.Accidental.FLAT;
/*     */           }
/*     */ 
/* 182 */           return TrebleStave.Accidental.NATURAL;
/*     */         }
/*     */ 
/* 185 */         return TrebleStave.Accidental.NONE;
/*     */       }
/*     */ 
/*     */       void processBarLine() {
/* 189 */         setBooleanArrayToFalse(this.accidentalInEffect);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class Accidental
/*     */   {
/*  48 */     public static final Accidental NONE = new Accidental("none");
/*     */ 
/*  50 */     public static final Accidental SHARP = new Accidental("sharp");
/*     */ 
/*  52 */     public static final Accidental NATURAL = new Accidental("natural");
/*     */ 
/*  54 */     public static final Accidental FLAT = new Accidental("flat");
/*     */     private String name;
/*     */ 
/*     */     Accidental(String paramString)
/*     */     {
/*  60 */       this.name = paramString;
/*     */     }
/*     */ 
/*     */     public String toString() {
/*  64 */       return this.name;
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.TrebleStave
 * JD-Core Version:    0.5.3
 */