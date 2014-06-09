/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class PianoStave extends Stave
/*     */   implements JMC
/*     */ {
/*     */   public PianoStave()
/*     */   {
/*  40 */     this.panelHeight = 160;
/*  41 */     setSize(this.beatWidth * this.spacingValue, this.panelHeight);
/*     */   }
/*     */ 
/*     */   public PianoStave(Phrase paramPhrase) {
/*  45 */     super(paramPhrase);
/*  46 */     this.panelHeight = 160;
/*  47 */     setSize(this.beatWidth * this.spacingValue, this.panelHeight);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/*  52 */     if (this.image == null) {
/*  53 */       this.image = createImage(getSize().width, getSize().height);
/*  54 */       this.g = this.image.getGraphics();
/*     */     }
/*  56 */     this.g.setFont(this.font);
/*     */ 
/*  58 */     double d = 0.0D;
/*     */ 
/*  60 */     this.previouslyChromatic.removeAllElements();
/*     */ 
/*  62 */     this.notePositions.removeAllElements();
/*  63 */     int i = 0;
/*     */ 
/*  65 */     if (getDisplayTitle()) this.g.drawString(this.title, this.rightMargin, this.bPos - 10);
/*     */ 
/*  67 */     int j = 0;
/*     */     int k;
/*     */     int i1;
/*     */     int i2;
/*     */     int i3;
/*  69 */     if ((this.keySignature > 0) && (this.keySignature < 8)) {
/*  70 */       for (k = 0; k < this.keySignature; ++k)
/*     */       {
/*  72 */         i1 = this.notePosOffset[(this.sharps[k] % 12)] + this.bPos - 4 + (5 - (this.sharps[k] / 12)) * 24 + (6 - (this.sharps[k] / 12)) * 4;
/*     */ 
/*  74 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + j, i1, this);
/*     */ 
/*  76 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*     */ 
/*  78 */         j += 10;
/*     */ 
/*  80 */         i2 = this.sharps[k] % 12;
/*  81 */         for (i3 = 0; i3 < 128; ++i3) {
/*  82 */           if (i3 % 12 == i2) {
/*  83 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  84 */             ++i;
/*     */           }
/*     */         }
/*  87 */         this.keySigWidth = j;
/*     */       }
/*     */     }
/*  90 */     else if ((this.keySignature < 0) && (this.keySignature > -8)) {
/*  91 */       for (k = 0; k < Math.abs(this.keySignature); ++k)
/*     */       {
/*  93 */         i1 = this.notePosOffset[(this.flats[k] % 12)] + this.bPos - 4 + (5 - (this.flats[k] / 12)) * 24 + (6 - (this.flats[k] / 12)) * 4;
/*     */ 
/*  95 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + j, i1, this);
/*     */ 
/*  97 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*     */ 
/*  99 */         j += 10;
/*     */ 
/* 101 */         i2 = this.flats[k] % 12;
/* 102 */         for (i3 = 0; i3 < 128; ++i3) {
/* 103 */           if (i3 % 12 == i2) {
/* 104 */             this.previouslyChromatic.addElement(new Integer(i3));
/* 105 */             ++i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 111 */     this.keySigWidth = (j + 3);
/*     */ 
/* 114 */     if (this.metre != 0.0D) {
/* 115 */       Image[] arrayOfImage = { this.one, this.two, this.three, this.four, this.five, this.six, this.seven, this.eight, this.nine };
/*     */ 
/* 118 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13, this);
/* 119 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13 + this.staveSpaceHeight * 6, this);
/*     */ 
/* 121 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29, this);
/* 122 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29 + this.staveSpaceHeight * 6, this);
/* 123 */       this.timeSigWidth = 30; } else {
/* 124 */       this.timeSigWidth = 5;
/*     */     }
/* 126 */     this.totalBeatWidth = (this.rightMargin + this.clefWidth + this.keySigWidth + this.timeSigWidth);
/*     */ 
/* 129 */     for (int l = 0; l < this.phrase.size(); ++l) {
/* 130 */       i1 = this.phrase.getNote(l).getPitch();
/*     */ 
/* 132 */       chooseImage(i1, this.phrase.getNote(l).getRhythmValue(), 71, 60, 50);
/*     */ 
/* 137 */       if ((i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/* 138 */         i2 = this.notePosOffset[11] + this.bPos - 4 + 0 + 4;
/*     */       else
/* 140 */         i2 = this.notePosOffset[(i1 % 12)] + this.bPos - 4 + (5 - (i1 / 12)) * 24 + (6 - (i1 / 12)) * 4;
/*     */       int i4;
/* 145 */       if ((((i1 % 12 == 1) || (i1 % 12 == 3) || (i1 % 12 == 6) || (i1 % 12 == 8) || (i1 % 12 == 10))) && (i1 != -2147483648) && (this.phrase.getNote(l).getRhythmValue() != 0.0D))
/*     */       {
/* 148 */         if (this.keySignature > -1) {
/* 149 */           this.g.drawImage(this.sharp, this.totalBeatWidth - 9, i2, this);
/*     */ 
/* 151 */           this.previouslyChromatic.addElement(new Integer(i1 - 1));
/*     */         } else {
/* 153 */           i2 -= 4;
/* 154 */           this.g.drawImage(this.flat, this.totalBeatWidth - 9, i2, this);
/* 155 */           this.previouslyChromatic.addElement(new Integer(i1 + 1));
/* 156 */           ++i1;
/*     */         }
/*     */       }
/*     */       else {
/* 160 */         i3 = this.previouslyChromatic.size();
/* 161 */         for (i4 = 0; i4 < i3; ++i4) {
/* 162 */           Integer localInteger = (Integer)this.previouslyChromatic.elementAt(i4);
/* 163 */           if ((localInteger.intValue() != i1) || (i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D)) {
/*     */             continue;
/*     */           }
/* 166 */           this.g.drawImage(this.natural, this.totalBeatWidth - 7, i2, this);
/*     */ 
/* 168 */           if (i4 > i - 1) this.previouslyChromatic.removeElementAt(i4);
/* 169 */           i4 = i3;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 175 */       this.g.drawImage(this.currImage, this.totalBeatWidth, i2, this);
/*     */ 
/* 177 */       this.notePositions.addElement(new Integer(this.totalBeatWidth));
/* 178 */       this.notePositions.addElement(new Integer(i2));
/*     */ 
/* 181 */       if (this.dottedNote) {
/* 182 */         i3 = 1;
/* 183 */         for (i4 = 0; i4 < this.lineNotes.length; ++i4) {
/* 184 */           if ((this.lineNotes[i4] + 12 != i1) && (this.lineNotes[i4] + 36 != i1) && (this.lineNotes[i4] + 60 != i1) && (this.lineNotes[i4] + 84 != i1) && (this.lineNotes[i4] + 108 != i1) && (i1 != -2147483648)) {
/*     */             continue;
/*     */           }
/* 187 */           this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2 - 4, this);
/* 188 */           i3 = 0;
/* 189 */           i4 = this.lineNotes.length;
/*     */         }
/*     */ 
/* 192 */         if (i3 != 0) this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2, this);
/*     */       }
/*     */ 
/* 195 */       if ((i1 == 60) || ((i1 == 61) && (this.phrase.getNote(l).getRhythmValue() != 0.0D))) {
/* 196 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 52, this.totalBeatWidth + 12, this.bPos + 52);
/*     */       }
/*     */ 
/* 200 */       if ((i1 <= 40) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 201 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 100, this.totalBeatWidth + 12, this.bPos + 100);
/*     */       }
/* 203 */       if ((i1 <= 37) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 204 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 108, this.totalBeatWidth + 12, this.bPos + 108);
/*     */       }
/* 206 */       if ((i1 <= 34) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 207 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 116, this.totalBeatWidth + 12, this.bPos + 116);
/*     */       }
/* 209 */       if ((i1 <= 30) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 210 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 124, this.totalBeatWidth + 12, this.bPos + 124);
/*     */       }
/* 212 */       if ((i1 <= 27) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 213 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 132, this.totalBeatWidth + 12, this.bPos + 132);
/*     */       }
/*     */ 
/* 216 */       if ((i1 >= 81) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 217 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 4, this.totalBeatWidth + 12, this.bPos + 4);
/*     */       }
/* 219 */       if ((i1 >= 84) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 220 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 4, this.totalBeatWidth + 12, this.bPos - 4);
/*     */       }
/* 222 */       if ((i1 >= 88) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 223 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 12, this.totalBeatWidth + 12, this.bPos - 12);
/*     */       }
/* 225 */       if ((i1 >= 91) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 226 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 20, this.totalBeatWidth + 12, this.bPos - 20);
/*     */       }
/* 228 */       if ((i1 >= 95) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 229 */         this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 28, this.totalBeatWidth + 12, this.bPos - 28);
/*     */       }
/*     */ 
/* 233 */       this.totalBeatWidth += this.currBeatWidth;
/* 234 */       this.dottedNote = false;
/*     */ 
/* 237 */       d += (int)(this.phrase.getNote(l).getRhythmValue() / 0.25D) * 0.25D;
/*     */ 
/* 240 */       if ((this.metre == 0.0D) || 
/* 241 */         (d % this.metre != 0.0D)) continue;
/* 242 */       this.g.drawLine(this.totalBeatWidth, this.bPos + 12, this.totalBeatWidth, this.bPos + 44 + this.staveSpaceHeight * 6);
/*     */ 
/* 244 */       if (this.barNumbers) this.g.drawString("" + (int)(d / this.metre + 1.0D + this.phrase.getStartTime()), this.totalBeatWidth - 4, this.bPos);
/*     */ 
/* 246 */       this.totalBeatWidth += 12;
/*     */     }
/*     */ 
/* 252 */     for (l = 0; l < 5; ++l) {
/* 253 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 258 */     for (l = 6; l < 11; ++l) {
/* 259 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 264 */     this.g.setColor(Color.lightGray);
/* 265 */     for (l = 0; l < 5; ++l) {
/* 266 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 271 */     for (l = 6; l < 11; ++l) {
/* 272 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 277 */     this.g.setColor(Color.black);
/*     */ 
/* 279 */     this.g.drawImage(this.trebleClef, this.rightMargin + 7, this.bPos - 4, this);
/* 280 */     this.g.drawImage(this.bassClef, this.rightMargin + 7, this.bPos + this.staveSpaceHeight * 6, this);
/*     */ 
/* 284 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*     */ 
/* 287 */     this.g.setColor(getBackground());
/* 288 */     this.g.fillRect(0, 0, getSize().width, getSize().height);
/* 289 */     this.g.setColor(getForeground());
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.PianoStave
 * JD-Core Version:    0.5.3
 */