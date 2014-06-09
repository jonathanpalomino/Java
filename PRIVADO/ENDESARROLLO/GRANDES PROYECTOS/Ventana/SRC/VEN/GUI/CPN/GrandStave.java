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
/*     */ public class GrandStave extends Stave
/*     */   implements JMC
/*     */ {
/*     */   public GrandStave()
/*     */   {
/*  40 */     this.bPos = 110;
/*  41 */     this.panelHeight = 310;
/*  42 */     setSize(this.beatWidth * this.spacingValue, this.panelHeight);
/*     */   }
/*     */ 
/*     */   public GrandStave(Phrase paramPhrase) {
/*  46 */     super(paramPhrase);
/*  47 */     this.bPos = 110;
/*  48 */     this.panelHeight = 310;
/*  49 */     setSize(this.beatWidth * this.spacingValue, this.panelHeight);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/*  54 */     if (this.image == null) {
/*  55 */       this.image = createImage(getSize().width, getSize().height);
/*  56 */       this.g = this.image.getGraphics();
/*     */     }
/*  58 */     this.g.setFont(this.font);
/*     */ 
/*  60 */     double d = 0.0D;
/*     */ 
/*  62 */     this.previouslyChromatic.removeAllElements();
/*     */ 
/*  64 */     this.notePositions.removeAllElements();
/*  65 */     int i = 0;
/*     */ 
/*  67 */     if (getDisplayTitle()) this.g.drawString(this.title, this.rightMargin, 60);
/*     */ 
/*  69 */     int j = 0;
/*     */     int k;
/*     */     int i1;
/*     */     int i2;
/*     */     int i3;
/*  71 */     if ((this.keySignature > 0) && (this.keySignature < 8)) {
/*  72 */       for (k = 0; k < this.keySignature; ++k)
/*     */       {
/*  74 */         i1 = this.notePosOffset[(this.sharps[k] % 12)] + this.bPos - 4 + (5 - (this.sharps[k] / 12)) * 24 + (6 - (this.sharps[k] / 12)) * 4;
/*     */ 
/*  76 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + j, i1, this);
/*     */ 
/*  78 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*     */ 
/*  80 */         j += 10;
/*     */ 
/*  82 */         i2 = this.sharps[k] % 12;
/*  83 */         for (i3 = 0; i3 < 128; ++i3) {
/*  84 */           if (i3 % 12 == i2) {
/*  85 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  86 */             ++i;
/*     */           }
/*     */         }
/*  89 */         this.keySigWidth = j;
/*     */       }
/*     */     }
/*  92 */     else if ((this.keySignature < 0) && (this.keySignature > -8)) {
/*  93 */       for (k = 0; k < Math.abs(this.keySignature); ++k)
/*     */       {
/*  95 */         i1 = this.notePosOffset[(this.flats[k] % 12)] + this.bPos - 4 + (5 - (this.flats[k] / 12)) * 24 + (6 - (this.flats[k] / 12)) * 4;
/*     */ 
/*  97 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + j, i1, this);
/*     */ 
/*  99 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*     */ 
/* 101 */         j += 10;
/*     */ 
/* 103 */         i2 = this.flats[k] % 12;
/* 104 */         for (i3 = 0; i3 < 128; ++i3) {
/* 105 */           if (i3 % 12 == i2) {
/* 106 */             this.previouslyChromatic.addElement(new Integer(i3));
/* 107 */             ++i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 113 */     this.keySigWidth = (j + 3);
/*     */ 
/* 116 */     if (this.metre != 0.0D) {
/* 117 */       Image[] arrayOfImage = { this.one, this.two, this.three, this.four, this.five, this.six, this.seven, this.eight, this.nine };
/*     */ 
/* 120 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13, this);
/* 121 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13 + this.staveSpaceHeight * 6, this);
/*     */ 
/* 123 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29, this);
/* 124 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29 + this.staveSpaceHeight * 6, this);
/* 125 */       this.timeSigWidth = 30; } else {
/* 126 */       this.timeSigWidth = 5;
/*     */     }
/* 128 */     this.totalBeatWidth = (this.rightMargin + this.clefWidth + this.keySigWidth + this.timeSigWidth);
/*     */ 
/* 131 */     for (int l = 0; l < this.phrase.size(); ++l) {
/* 132 */       i1 = this.phrase.getNote(l).getPitch();
/*     */ 
/* 134 */       chooseImage(i1, this.phrase.getNote(l).getRhythmValue(), 71, 60, 50);
/*     */ 
/* 139 */       if ((i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/* 140 */         i2 = this.notePosOffset[11] + this.bPos - 4 + 0 + 4;
/*     */       else
/* 142 */         i2 = this.notePosOffset[(i1 % 12)] + this.bPos - 4 + (5 - (i1 / 12)) * 24 + (6 - (i1 / 12)) * 4;
/*     */       int i4;
/* 146 */       if ((((i1 % 12 == 1) || (i1 % 12 == 3) || (i1 % 12 == 6) || (i1 % 12 == 8) || (i1 % 12 == 10))) && (i1 != -2147483648) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 147 */         if (this.keySignature > -1) {
/* 148 */           this.g.drawImage(this.sharp, this.totalBeatWidth - 9, i2, this);
/* 149 */           this.previouslyChromatic.addElement(new Integer(i1 - 1));
/*     */         } else {
/* 151 */           i2 -= 4;
/* 152 */           this.g.drawImage(this.flat, this.totalBeatWidth - 9, i2, this);
/* 153 */           this.previouslyChromatic.addElement(new Integer(i1 + 1));
/* 154 */           ++i1;
/*     */         }
/*     */       }
/*     */       else {
/* 158 */         i3 = this.previouslyChromatic.size();
/* 159 */         for (i4 = 0; i4 < i3; ++i4) {
/* 160 */           Integer localInteger = (Integer)this.previouslyChromatic.elementAt(i4);
/* 161 */           if ((localInteger.intValue() != i1) || (i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/*     */             continue;
/* 163 */           this.g.drawImage(this.natural, this.totalBeatWidth - 7, i2, this);
/*     */ 
/* 165 */           if (i4 > i - 1) this.previouslyChromatic.removeElementAt(i4);
/* 166 */           i4 = i3;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 172 */       this.g.drawImage(this.currImage, this.totalBeatWidth, i2, this);
/*     */ 
/* 174 */       this.notePositions.addElement(new Integer(this.totalBeatWidth));
/* 175 */       this.notePositions.addElement(new Integer(i2));
/*     */ 
/* 177 */       if (this.dottedNote) {
/* 178 */         i3 = 1;
/* 179 */         for (i4 = 0; i4 < this.lineNotes.length; ++i4) {
/* 180 */           if ((this.lineNotes[i4] + 12 == i1) || (this.lineNotes[i4] + 36 == i1) || (this.lineNotes[i4] + 60 == i1) || (this.lineNotes[i4] + 84 == i1) || (this.lineNotes[i4] + 108 == i1) || (i1 == -2147483648)) {
/* 181 */             this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2 - 4, this);
/* 182 */             i3 = 0;
/* 183 */             i4 = this.lineNotes.length;
/*     */           }
/*     */         }
/* 186 */         if (i3 != 0) this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2, this);
/*     */       }
/*     */ 
/* 189 */       if ((i1 == 60) || ((i1 == 61) && (this.phrase.getNote(l).getRhythmValue() != 0.0D))) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 52, this.totalBeatWidth + 12, this.bPos + 52);
/*     */ 
/* 192 */       if ((i1 <= 40) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 100, this.totalBeatWidth + 12, this.bPos + 100);
/* 193 */       if ((i1 <= 37) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 108, this.totalBeatWidth + 12, this.bPos + 108);
/*     */ 
/* 195 */       if ((i1 <= 16) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 156, this.totalBeatWidth + 12, this.bPos + 156);
/* 196 */       if ((i1 <= 13) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 164, this.totalBeatWidth + 12, this.bPos + 164);
/* 197 */       if ((i1 <= 10) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 172, this.totalBeatWidth + 12, this.bPos + 172);
/* 198 */       if ((i1 <= 6) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 180, this.totalBeatWidth + 12, this.bPos + 180);
/* 199 */       if ((i1 <= 3) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 188, this.totalBeatWidth + 12, this.bPos + 188);
/*     */ 
/* 201 */       if ((i1 >= 81) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 4, this.totalBeatWidth + 12, this.bPos + 4);
/* 202 */       if ((i1 >= 84) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 4, this.totalBeatWidth + 12, this.bPos - 4);
/*     */ 
/* 204 */       if ((i1 >= 105) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 52, this.totalBeatWidth + 12, this.bPos - 52);
/* 205 */       if ((i1 >= 108) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 60, this.totalBeatWidth + 12, this.bPos - 60);
/* 206 */       if ((i1 >= 112) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 68, this.totalBeatWidth + 12, this.bPos - 68);
/* 207 */       if ((i1 >= 115) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 76, this.totalBeatWidth + 12, this.bPos - 76);
/* 208 */       if ((i1 >= 119) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 84, this.totalBeatWidth + 12, this.bPos - 84);
/* 209 */       if ((i1 >= 122) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 92, this.totalBeatWidth + 12, this.bPos - 92);
/* 210 */       if ((i1 >= 125) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 100, this.totalBeatWidth + 12, this.bPos - 100);
/*     */ 
/* 213 */       this.totalBeatWidth += this.currBeatWidth;
/* 214 */       this.dottedNote = false;
/*     */ 
/* 216 */       d += (int)(this.phrase.getNote(l).getRhythmValue() / 0.25D) * 0.25D;
/*     */ 
/* 219 */       if ((this.metre == 0.0D) || 
/* 220 */         (d % this.metre != 0.0D)) continue;
/* 221 */       this.g.drawLine(this.totalBeatWidth, this.bPos + 12 - (this.staveSpaceHeight * 7), this.totalBeatWidth, this.bPos + 44 + this.staveSpaceHeight * 13);
/*     */ 
/* 223 */       if (this.barNumbers) this.g.drawString("" + (int)(d / this.metre + 1.0D + this.phrase.getStartTime()), this.totalBeatWidth - 4, this.bPos - 50);
/* 224 */       this.totalBeatWidth += 12;
/*     */     }
/*     */ 
/* 230 */     for (l = 0; l < 5; ++l) {
/* 231 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 235 */     for (l = 6; l < 11; ++l) {
/* 236 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 239 */     this.g.setColor(Color.darkGray);
/*     */ 
/* 241 */     for (l = -7; l < -2; ++l) {
/* 242 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 246 */     for (l = 13; l < 18; ++l) {
/* 247 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 251 */     this.g.setColor(Color.lightGray);
/* 252 */     for (l = 0; l < 5; ++l) {
/* 253 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 258 */     for (l = 6; l < 11; ++l) {
/* 259 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 264 */     this.g.setColor(Color.black);
/*     */ 
/* 266 */     this.g.drawImage(this.trebleClef, this.rightMargin + 7, this.bPos - 4, this);
/* 267 */     this.g.drawImage(this.bassClef, this.rightMargin + 7, this.bPos + this.staveSpaceHeight * 6, this);
/*     */ 
/* 270 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*     */ 
/* 273 */     this.g.setColor(getBackground());
/* 274 */     this.g.fillRect(0, 0, getSize().width, getSize().height);
/* 275 */     this.g.setColor(getForeground());
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.GrandStave
 * JD-Core Version:    0.5.3
 */