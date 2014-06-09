/*     */ package jm.gui.show;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ShowArea extends Canvas
/*     */ {
/*     */   private int oldX;
/*  45 */   private int maxColours = 8;
/*  46 */   private float[][] theColours = new float[this.maxColours][3];
/*  47 */   private int noteHeight = 5;
/*  48 */   private int w = 2 * this.noteHeight;
/*  49 */   private int ePos = 5 * this.noteHeight;
/*  50 */   private int e = this.ePos + this.noteHeight * 33;
/*  51 */   private int areaHeight = 77 * this.noteHeight;
/*  52 */   private int[] noteOffset = { 0, 0, this.noteHeight, this.noteHeight, this.noteHeight * 2, this.noteHeight * 3, this.noteHeight * 3, this.noteHeight * 4, this.noteHeight * 4, this.noteHeight * 5, this.noteHeight * 5, this.noteHeight * 6 };
/*     */ 
/*  55 */   private Font font = new Font("Helvetica", 0, 10);
/*     */   private ShowPanel sp;
/*     */   private double beatWidth;
/*  58 */   private int thinNote = 2;
/*     */ 
/*     */   public ShowArea(ShowPanel paramShowPanel)
/*     */   {
/*  62 */     this.sp = paramShowPanel;
/*     */ 
/*  64 */     setSize((int)(paramShowPanel.score.getEndTime() * paramShowPanel.beatWidth), this.areaHeight);
/*     */ 
/*  66 */     for (int i = 0; i < this.maxColours; ++i) {
/*  67 */       this.theColours[i][0] = ((float)(Math.random() / this.maxColours / 2.0D) + (float)(1.0D / this.maxColours * i));
/*  68 */       this.theColours[i][1] = ((float)(Math.random() / this.maxColours) + (float)(1.0D / this.maxColours * i));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void reInit() {
/*  73 */     this.w = (2 * this.noteHeight);
/*  74 */     this.ePos = (5 * this.noteHeight);
/*  75 */     this.e = (this.ePos + this.noteHeight * 33);
/*  76 */     this.areaHeight = (77 * this.noteHeight);
/*  77 */     this.noteOffset = new int[] { 0, 0, this.noteHeight, this.noteHeight, this.noteHeight * 2, this.noteHeight * 3, this.noteHeight * 3, this.noteHeight * 4, this.noteHeight * 4, this.noteHeight * 5, this.noteHeight * 5, this.noteHeight * 6 };
/*     */ 
/*  80 */     setSize(new Dimension(getSize().width, this.areaHeight));
/*  81 */     this.sp.updatePanelHeight();
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  88 */     return this.areaHeight;
/*     */   }
/*     */ 
/*     */   public void setNoteHeight(int paramInt)
/*     */   {
/*  96 */     this.noteHeight = paramInt;
/*  97 */     reInit();
/*  98 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setThinNote(int paramInt)
/*     */   {
/* 106 */     if (paramInt >= 0) this.thinNote = paramInt;
/* 107 */     repaint();
/*     */   }
/*     */ 
/*     */   public int getThinNote()
/*     */   {
/* 114 */     return this.thinNote;
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 127 */     paramGraphics.setColor(Color.white);
/* 128 */     paramGraphics.fillRect(0, 0, getSize().width, getSize().height);
/*     */ 
/* 130 */     paramGraphics.setFont(this.font);
/*     */ 
/* 132 */     paramGraphics.setColor(Color.black);
/*     */ 
/* 135 */     this.beatWidth = this.sp.beatWidth;
/* 136 */     int i1 = (int)Math.round(this.sp.score.getEndTime() * this.beatWidth);
/* 137 */     paramGraphics.drawLine(0, this.e, i1, this.e);
/* 138 */     paramGraphics.drawLine(0, this.e - this.w, i1, this.e - this.w);
/* 139 */     paramGraphics.drawLine(0, this.e - (this.w * 2), i1, this.e - (this.w * 2));
/* 140 */     paramGraphics.drawLine(0, this.e - (this.w * 3), i1, this.e - (this.w * 3));
/* 141 */     paramGraphics.drawLine(0, this.e - (this.w * 4), i1, this.e - (this.w * 4));
/*     */ 
/* 143 */     paramGraphics.drawLine(0, this.e + this.w * 2, i1, this.e + this.w * 2);
/* 144 */     paramGraphics.drawLine(0, this.e + this.w * 3, i1, this.e + this.w * 3);
/* 145 */     paramGraphics.drawLine(0, this.e + this.w * 4, i1, this.e + this.w * 4);
/* 146 */     paramGraphics.drawLine(0, this.e + this.w * 5, i1, this.e + this.w * 5);
/* 147 */     paramGraphics.drawLine(0, this.e + this.w * 6, i1, this.e + this.w * 6);
/*     */ 
/* 149 */     paramGraphics.setColor(Color.lightGray);
/* 150 */     paramGraphics.drawLine(0, this.e - (this.w * 7), i1, this.e - (this.w * 7));
/* 151 */     paramGraphics.drawLine(0, this.e - (this.w * 8), i1, this.e - (this.w * 8));
/* 152 */     paramGraphics.drawLine(0, this.e - (this.w * 9), i1, this.e - (this.w * 9));
/* 153 */     paramGraphics.drawLine(0, this.e - (this.w * 10), i1, this.e - (this.w * 10));
/* 154 */     paramGraphics.drawLine(0, this.e - (this.w * 11), i1, this.e - (this.w * 11));
/*     */ 
/* 156 */     paramGraphics.drawLine(0, this.e + this.w * 9, i1, this.e + this.w * 9);
/* 157 */     paramGraphics.drawLine(0, this.e + this.w * 10, i1, this.e + this.w * 10);
/* 158 */     paramGraphics.drawLine(0, this.e + this.w * 11, i1, this.e + this.w * 11);
/* 159 */     paramGraphics.drawLine(0, this.e + this.w * 12, i1, this.e + this.w * 12);
/* 160 */     paramGraphics.drawLine(0, this.e + this.w * 13, i1, this.e + this.w * 13);
/*     */ 
/* 162 */     for (int i2 = 0; i2 < i1; i2 += 10) {
/* 163 */       paramGraphics.drawLine(i2, this.e + this.w, i2 + 1, this.e + this.w);
/*     */ 
/* 165 */       paramGraphics.drawLine(i2, this.e - (this.w * 5), i2 + 1, this.e - (this.w * 5));
/* 166 */       paramGraphics.drawLine(i2, this.e - (this.w * 6), i2 + 1, this.e - (this.w * 6));
/*     */ 
/* 168 */       paramGraphics.drawLine(i2, this.e - (this.w * 12), i2 + 1, this.e - (this.w * 12));
/* 169 */       paramGraphics.drawLine(i2, this.e - (this.w * 13), i2 + 1, this.e - (this.w * 13));
/* 170 */       paramGraphics.drawLine(i2, this.e - (this.w * 14), i2 + 1, this.e - (this.w * 14));
/* 171 */       paramGraphics.drawLine(i2, this.e - (this.w * 15), i2 + 1, this.e - (this.w * 15));
/* 172 */       paramGraphics.drawLine(i2, this.e - (this.w * 16), i2 + 1, this.e - (this.w * 16));
/* 173 */       paramGraphics.drawLine(i2, this.e - (this.w * 17), i2 + 1, this.e - (this.w * 17));
/* 174 */       paramGraphics.drawLine(i2, this.e - (this.w * 18), i2 + 1, this.e - (this.w * 18));
/*     */ 
/* 176 */       paramGraphics.drawLine(i2, this.e + this.w * 7, i2 + 1, this.e + this.w * 7);
/* 177 */       paramGraphics.drawLine(i2, this.e + this.w * 8, i2 + 1, this.e + this.w * 8);
/*     */ 
/* 179 */       paramGraphics.drawLine(i2, this.e + this.w * 14, i2 + 1, this.e + this.w * 14);
/* 180 */       paramGraphics.drawLine(i2, this.e + this.w * 15, i2 + 1, this.e + this.w * 15);
/* 181 */       paramGraphics.drawLine(i2, this.e + this.w * 16, i2 + 1, this.e + this.w * 16);
/* 182 */       paramGraphics.drawLine(i2, this.e + this.w * 17, i2 + 1, this.e + this.w * 17);
/* 183 */       paramGraphics.drawLine(i2, this.e + this.w * 18, i2 + 1, this.e + this.w * 18);
/*     */     }
/*     */ 
/* 186 */     Enumeration localEnumeration1 = this.sp.score.getPartList().elements();
/* 187 */     int i3 = 0;
/* 188 */     while (localEnumeration1.hasMoreElements()) {
/* 189 */       Part localPart = (Part)localEnumeration1.nextElement();
/*     */ 
/* 191 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 192 */       while (localEnumeration2.hasMoreElements()) {
/* 193 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/*     */ 
/* 195 */         Enumeration localEnumeration3 = localPhrase.getNoteList().elements();
/* 196 */         double d = localPhrase.getStartTime();
/* 197 */         this.oldX = (int)Math.round(d * this.beatWidth);
/*     */ 
/* 199 */         int i = this.oldX;
/* 200 */         int j = 100000;
/* 201 */         int k = this.oldX;
/* 202 */         int l = 0;
/*     */ 
/* 204 */         while (localEnumeration3.hasMoreElements()) {
/* 205 */           Note localNote = (Note)localEnumeration3.nextElement();
/* 206 */           int i4 = -1;
/* 207 */           if (!(localNote.getPitchType())) i4 = localNote.getPitch();
/*     */           else i4 = Note.freqToMidiPitch(localNote.getFrequency());
/* 209 */           if ((i4 <= 127) && (i4 >= 0))
/*     */           {
/* 213 */             int i5 = this.noteHeight * 7;
/* 214 */             int i6 = (10 - (i4 / 12)) * i5 + this.ePos - this.noteOffset[(i4 % 12)];
/*     */ 
/* 216 */             int i7 = (int)Math.round(localNote.getDuration() * this.beatWidth);
/* 217 */             int i8 = (int)Math.round(localNote.getRhythmValue() * this.beatWidth);
/*     */ 
/* 220 */             if (i7 < 1) i7 = 1;
/* 221 */             if (i6 < j) j = i6;
/* 222 */             if (i6 > l) l = i6;
/*     */ 
/* 224 */             paramGraphics.setColor(Color.getHSBColor(this.theColours[(i3 % this.maxColours)][0], this.theColours[(i3 % this.maxColours)][1], (float)(0.7D - (localNote.getDynamic() * 0.004D))));
/*     */ 
/* 228 */             if (!(localNote.getPitchType())) {
/* 229 */               paramGraphics.fillRect(this.oldX, i6 - this.noteHeight + this.thinNote, i7, this.noteHeight * 2 - (2 * this.thinNote));
/*     */             }
/*     */             else {
/* 232 */               int i9 = 7;
/* 233 */               for (int i10 = this.oldX; i10 < this.oldX + i7 - 4; i10 += 4) {
/* 234 */                 paramGraphics.drawLine(i10, i6 - this.noteHeight + i9, i10 + 2, i6 - this.noteHeight + i9 - 3);
/*     */ 
/* 236 */                 paramGraphics.drawLine(i10 + 2, i6 - this.noteHeight + i9 - 3, i10 + 4, i6 - this.noteHeight + i9);
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 242 */             paramGraphics.setColor(Color.getHSBColor(this.theColours[(i3 % this.maxColours)][0], this.theColours[(i3 % this.maxColours)][1], 0.4F));
/*     */ 
/* 244 */             paramGraphics.drawRect(this.oldX, i6 - this.noteHeight + this.thinNote, i8, this.noteHeight * 2 - (2 * this.thinNote));
/*     */ 
/* 247 */             if ((i4 % 12 == 1) || (i4 % 12 == 3) || (i4 % 12 == 6) || (i4 % 12 == 8) || (i4 % 12 == 10))
/*     */             {
/* 250 */               paramGraphics.setColor(Color.getHSBColor(this.theColours[(i3 % this.maxColours)][0], this.theColours[(i3 % this.maxColours)][1], 0.3F));
/*     */ 
/* 253 */               paramGraphics.drawString("#", this.oldX - 7, i6 + 5);
/*     */             }
/*     */           }
/* 256 */           d += localNote.getRhythmValue();
/* 257 */           this.oldX = (int)Math.round(d * this.beatWidth);
/* 258 */           k = this.oldX - i;
/*     */         }
/*     */ 
/* 262 */         paramGraphics.setColor(Color.getHSBColor(this.theColours[(i3 % this.maxColours)][0], this.theColours[(i3 % this.maxColours)][1], 0.9F));
/*     */ 
/* 264 */         paramGraphics.drawRect(i - 1, j - this.noteHeight - 1, k + 1, l - j + this.noteHeight * 2 + 2);
/*     */       }
/*     */ 
/* 267 */       ++i3;
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.show.ShowArea
 * JD-Core Version:    0.5.3
 */