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
/*     */ public class BassStave extends Stave
/*     */   implements JMC
/*     */ {
/*     */   public BassStave()
/*     */   {
/*  40 */     this.staveDelta = (this.staveSpaceHeight * 11 / 2);
/*     */   }
/*     */ 
/*     */   public BassStave(Phrase paramPhrase) {
/*  44 */     super(paramPhrase);
/*  45 */     this.staveDelta = (this.staveSpaceHeight * 11 / 2);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/*  50 */     if (this.image == null) {
/*  51 */       this.image = createImage(getSize().width, getSize().height);
/*  52 */       this.g = this.image.getGraphics();
/*     */     }
/*  54 */     this.g.setFont(this.font);
/*     */ 
/*  56 */     double d = 0.0D;
/*     */ 
/*  58 */     this.previouslyChromatic.removeAllElements();
/*     */ 
/*  60 */     this.notePositions.removeAllElements();
/*  61 */     int i = 0;
/*     */ 
/*  63 */     if (getDisplayTitle()) this.g.drawString(this.title, this.rightMargin, this.bPos - 10);
/*     */ 
/*  65 */     int j = 0;
/*     */     int k;
/*     */     int i1;
/*     */     int i2;
/*     */     int i3;
/*  67 */     if ((this.keySignature > 0) && (this.keySignature < 8)) {
/*  68 */       for (k = 0; k < this.keySignature; ++k)
/*     */       {
/*  70 */         i1 = this.notePosOffset[(this.sharps[k] % 12)] + this.bPos - 4 + (5 - (this.sharps[k] / 12)) * 24 + (6 - (this.sharps[k] / 12)) * 4;
/*     */ 
/*  72 */         this.g.drawImage(this.sharp, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight, this);
/*     */ 
/*  74 */         j += 10;
/*     */ 
/*  76 */         i2 = this.sharps[k] % 12;
/*  77 */         for (i3 = 0; i3 < 128; ++i3) {
/*  78 */           if (i3 % 12 == i2) {
/*  79 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  80 */             ++i;
/*     */           }
/*     */         }
/*  83 */         this.keySigWidth = j;
/*     */       }
/*     */     }
/*  86 */     else if ((this.keySignature < 0) && (this.keySignature > -8)) {
/*  87 */       for (k = 0; k < Math.abs(this.keySignature); ++k)
/*     */       {
/*  89 */         i1 = this.notePosOffset[(this.flats[k] % 12)] + this.bPos - 4 + (5 - (this.flats[k] / 12)) * 24 + (6 - (this.flats[k] / 12)) * 4;
/*     */ 
/*  91 */         this.g.drawImage(this.flat, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight, this);
/*     */ 
/*  93 */         j += 10;
/*     */ 
/*  95 */         i2 = this.flats[k] % 12;
/*  96 */         for (i3 = 0; i3 < 128; ++i3) {
/*  97 */           if (i3 % 12 == i2) {
/*  98 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  99 */             ++i;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 105 */     this.keySigWidth = (j + 3);
/*     */ 
/* 108 */     if (this.metre != 0.0D) {
/* 109 */       Image[] arrayOfImage = { this.one, this.two, this.three, this.four, this.five, this.six, this.seven, this.eight, this.nine };
/*     */ 
/* 112 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13, this);
/*     */ 
/* 114 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29, this);
/* 115 */       this.timeSigWidth = 30; } else {
/* 116 */       this.timeSigWidth = 5;
/*     */     }
/* 118 */     this.totalBeatWidth = (this.rightMargin + this.clefWidth + this.keySigWidth + this.timeSigWidth);
/*     */ 
/* 121 */     for (int l = 0; l < this.phrase.size(); ++l) {
/* 122 */       i1 = this.phrase.getNote(l).getPitch();
/*     */ 
/* 124 */       chooseImage(i1, this.phrase.getNote(l).getRhythmValue(), 50, 0, 50);
/*     */ 
/* 129 */       if ((i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/* 130 */         i2 = this.notePosOffset[11] + this.bPos - 4 + 0 + 4;
/*     */       else
/* 132 */         i2 = this.notePosOffset[(i1 % 12)] + this.bPos - 4 + (5 - (i1 / 12)) * 24 + (6 - (i1 / 12)) * 4 - (this.staveSpaceHeight * 6);
/*     */       int i4;
/* 136 */       if ((((i1 % 12 == 1) || (i1 % 12 == 3) || (i1 % 12 == 6) || (i1 % 12 == 8) || (i1 % 12 == 10))) && (i1 != -2147483648) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) {
/* 137 */         if (this.keySignature > -1) {
/* 138 */           this.g.drawImage(this.sharp, this.totalBeatWidth - 9, i2, this);
/* 139 */           this.previouslyChromatic.addElement(new Integer(i1 - 1));
/*     */         } else {
/* 141 */           i2 -= 4;
/* 142 */           this.g.drawImage(this.flat, this.totalBeatWidth - 9, i2, this);
/* 143 */           this.previouslyChromatic.addElement(new Integer(i1 + 1));
/* 144 */           ++i1;
/*     */         }
/*     */       }
/*     */       else {
/* 148 */         i3 = this.previouslyChromatic.size();
/* 149 */         for (i4 = 0; i4 < i3; ++i4) {
/* 150 */           Integer localInteger = (Integer)this.previouslyChromatic.elementAt(i4);
/* 151 */           if ((localInteger.intValue() != i1) || (i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/*     */             continue;
/* 153 */           this.g.drawImage(this.natural, this.totalBeatWidth - 7, i2, this);
/*     */ 
/* 155 */           if (i4 > i - 1) this.previouslyChromatic.removeElementAt(i4);
/* 156 */           i4 = i3;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 162 */       this.g.drawImage(this.currImage, this.totalBeatWidth, i2, this);
/*     */ 
/* 164 */       this.notePositions.addElement(new Integer(this.totalBeatWidth));
/* 165 */       this.notePositions.addElement(new Integer(i2 + this.staveDelta));
/*     */ 
/* 168 */       if (this.dottedNote) {
/* 169 */         i3 = 1;
/* 170 */         for (i4 = 0; i4 < this.lineNotes.length; ++i4) {
/* 171 */           if ((this.lineNotes[i4] + 12 == i1) || (this.lineNotes[i4] + 36 == i1) || (this.lineNotes[i4] + 60 == i1) || (this.lineNotes[i4] + 84 == i1) || (this.lineNotes[i4] + 108 == i1) || (i1 == -2147483648)) {
/* 172 */             this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2 - 4, this);
/* 173 */             i3 = 0;
/* 174 */             i4 = this.lineNotes.length;
/*     */           }
/*     */         }
/* 177 */         if (i3 != 0) this.g.drawImage(this.dot, this.totalBeatWidth + 1, i2, this);
/*     */       }
/*     */ 
/* 180 */       if ((i1 <= 40) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 52, this.totalBeatWidth + 12, this.bPos + 52);
/* 181 */       if ((i1 <= 37) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 60, this.totalBeatWidth + 12, this.bPos + 60);
/* 182 */       if ((i1 <= 34) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 68, this.totalBeatWidth + 12, this.bPos + 68);
/* 183 */       if ((i1 <= 30) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 76, this.totalBeatWidth + 12, this.bPos + 76);
/* 184 */       if ((i1 <= 26) && (i1 > -1) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 84, this.totalBeatWidth + 12, this.bPos + 84);
/*     */ 
/* 186 */       if ((i1 >= 60) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 4, this.totalBeatWidth + 12, this.bPos + 4);
/* 187 */       if ((i1 >= 64) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 4, this.totalBeatWidth + 12, this.bPos - 4);
/* 188 */       if ((i1 >= 67) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 12, this.totalBeatWidth + 12, this.bPos - 12);
/* 189 */       if ((i1 >= 71) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 20, this.totalBeatWidth + 12, this.bPos - 20);
/* 190 */       if ((i1 >= 74) && (i1 < 128) && (this.phrase.getNote(l).getRhythmValue() != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 28, this.totalBeatWidth + 12, this.bPos - 28);
/*     */ 
/* 193 */       this.totalBeatWidth += this.currBeatWidth;
/* 194 */       this.dottedNote = false;
/*     */ 
/* 197 */       d += (int)(this.phrase.getNote(l).getRhythmValue() / 0.25D) * 0.25D;
/*     */ 
/* 200 */       if ((this.metre == 0.0D) || 
/* 201 */         (d % this.metre != 0.0D)) continue;
/* 202 */       this.g.drawLine(this.totalBeatWidth, this.bPos + 12, this.totalBeatWidth, this.bPos + 44);
/*     */ 
/* 204 */       if (this.barNumbers) this.g.drawString("" + (int)(d / this.metre + 1.0D + this.phrase.getStartTime()), this.totalBeatWidth - 4, this.bPos);
/* 205 */       this.totalBeatWidth += 12;
/*     */     }
/*     */ 
/* 211 */     for (l = 0; l < 5; ++l) {
/* 212 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 216 */     this.g.setColor(Color.lightGray);
/* 217 */     for (l = 0; l < 5; ++l) {
/* 218 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*     */     }
/*     */ 
/* 223 */     this.g.setColor(Color.black);
/*     */ 
/* 225 */     this.g.drawImage(this.bassClef, this.rightMargin + 7, this.bPos, this);
/*     */ 
/* 229 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*     */ 
/* 232 */     this.g.setColor(getBackground());
/* 233 */     this.g.fillRect(0, 0, getSize().width, getSize().height);
/* 234 */     this.g.setColor(getForeground());
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.BassStave
 * JD-Core Version:    0.5.3
 */