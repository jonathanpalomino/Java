/*     */ package jm.gui.histogram;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Graphics;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class Histogram extends Component
/*     */   implements JMC
/*     */ {
/*     */   private Score score;
/*     */   private int maxPitchValue;
/*     */   private int maxRhythmValue;
/*     */   private int maxDynamicValue;
/*     */   private int maxPanValue;
/*     */   private int[] pitchValues;
/*     */   private int[] rhythmValues;
/*     */   private int[] dynamicValues;
/*     */   private int[] panValues;
/*     */   private Font f;
/*     */   private int barWidth;
/*     */   private int lableSpace;
/*     */   private int type;
/*     */   private String title;
/*     */   private int xPos;
/*     */   private int yPos;
/*     */ 
/*     */   public Histogram(Score paramScore)
/*     */   {
/*  72 */     this(paramScore, 0);
/*     */   }
/*     */ 
/*     */   public Histogram(Score paramScore, int paramInt)
/*     */   {
/*  82 */     this(paramScore, paramInt, 0, 0);
/*     */   }
/*     */ 
/*     */   public Histogram(Score paramScore, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  93 */     this(paramScore, paramInt1, paramInt2, paramInt3, 200);
/*     */   }
/*     */ 
/*     */   public Histogram(Score paramScore, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  53 */     this.f = new Font("Helvetica", 0, 9);
/*     */ 
/*  55 */     this.barWidth = 4;
/*     */ 
/*  57 */     this.lableSpace = 24;
/*     */ 
/*  59 */     this.type = 0;
/*     */ 
/*  61 */     this.title = "Pitch Histogram";
/*     */ 
/*  63 */     this.xPos = 0;
/*     */ 
/*  65 */     this.yPos = 0;
/*     */ 
/* 104 */     this.score = paramScore;
/* 105 */     this.type = paramInt1;
/* 106 */     this.xPos = paramInt2;
/* 107 */     this.yPos = paramInt3;
/* 108 */     if (paramInt1 == 1) this.title = "Rhythm Histogram";
/* 109 */     if (paramInt1 == 2) this.title = "Dynamic Histogram";
/* 110 */     if (paramInt1 == 3) this.title = "Pan Histogram";
/* 111 */     setSize(paramInt4, this.barWidth * 127 + this.lableSpace);
/*     */ 
/* 113 */     analysis();
/*     */   }
/*     */ 
/*     */   private void analysis()
/*     */   {
/* 118 */     this.pitchValues = new int[128];
/* 119 */     this.rhythmValues = new int[66];
/* 120 */     this.dynamicValues = new int[127];
/* 121 */     this.panValues = new int[100];
/* 122 */     this.maxPitchValue = 0;
/* 123 */     this.maxRhythmValue = 0;
/* 124 */     this.maxDynamicValue = 0;
/* 125 */     this.maxPanValue = 0;
/*     */ 
/* 127 */     Enumeration localEnumeration1 = this.score.getPartList().elements();
/* 128 */     while (localEnumeration1.hasMoreElements()) {
/* 129 */       Part localPart = (Part)localEnumeration1.nextElement();
/* 130 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 131 */       while (localEnumeration2.hasMoreElements()) {
/* 132 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 133 */         Enumeration localEnumeration3 = localPhrase.getNoteList().elements();
/* 134 */         while (localEnumeration3.hasMoreElements()) {
/* 135 */           Note localNote = (Note)localEnumeration3.nextElement();
/*     */ 
/* 137 */           if ((!(localNote.getPitchType())) && 
/* 138 */             (localNote.getPitch() != -2147483648))
/*     */           {
/* 140 */             this.pitchValues[localNote.getPitch()] += 1;
/* 141 */             if (this.pitchValues[localNote.getPitch()] > this.maxPitchValue) this.maxPitchValue = this.pitchValues[localNote.getPitch()];
/*     */ 
/* 144 */             int i = (int)(localNote.getRhythmValue() / 0.125D);
/* 145 */             if (i >= this.rhythmValues.length) i = this.rhythmValues.length - 1;
/* 146 */             this.rhythmValues[i] += 1;
/* 147 */             if (this.rhythmValues[i] > this.maxRhythmValue) {
/* 148 */               this.maxRhythmValue = this.rhythmValues[i];
/*     */             }
/* 150 */             this.dynamicValues[localNote.getDynamic()] += 1;
/* 151 */             if (this.dynamicValues[localNote.getDynamic()] > this.maxDynamicValue) this.maxDynamicValue = this.dynamicValues[localNote.getDynamic()];
/*     */ 
/* 154 */             this.panValues[(int)(localNote.getPan() * 100.0D)] += 1;
/* 155 */             if (this.panValues[(int)(localNote.getPan() * 100.0D)] > this.maxPanValue) this.maxPanValue = this.panValues[(int)(localNote.getPan() * 100.0D)];
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 166 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setType(int paramInt)
/*     */   {
/* 173 */     this.type = paramInt;
/* 174 */     repaint();
/*     */   }
/*     */ 
/*     */   public int getXPos()
/*     */   {
/* 179 */     return this.xPos;
/*     */   }
/*     */ 
/*     */   public int getYPos()
/*     */   {
/* 184 */     return this.yPos;
/*     */   }
/*     */ 
/*     */   public void setScore(Score paramScore)
/*     */   {
/* 189 */     this.score = paramScore;
/* 190 */     analysis();
/* 191 */     repaint();
/*     */   }
/*     */ 
/*     */   public void saveData()
/*     */   {
/* 199 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Save histogram data as...", 1);
/*     */ 
/* 201 */     localFileDialog.show();
/* 202 */     String str = localFileDialog.getFile();
/* 203 */     if (str != null)
/* 204 */       saveDataAs(localFileDialog.getDirectory() + str);
/*     */   }
/*     */ 
/*     */   public void saveDataAs(String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 214 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 215 */       String str1 = "Pitch value" + String.valueOf("\t") + "Pitch data" + String.valueOf("\t") + "Rhythm value" + String.valueOf("\t") + "Rhythm data" + String.valueOf("\t") + "Dynamic value" + String.valueOf("\t") + "Dynamic data" + String.valueOf("\t") + "Pan value" + String.valueOf("\t") + "Pan data" + String.valueOf("\n");
/*     */ 
/* 220 */       localFileOutputStream.write(str1.getBytes());
/* 221 */       for (int i = 0; i < this.pitchValues.length; ++i) {
/* 222 */         String str2 = String.valueOf(i) + String.valueOf("\t") + String.valueOf(this.pitchValues[i]);
/* 223 */         if (i < this.rhythmValues.length) str2 = str2 + String.valueOf("\t") + String.valueOf(i * 0.125D) + String.valueOf("\t") + String.valueOf(this.rhythmValues[i]);
/*     */ 
/* 225 */         if (i < this.dynamicValues.length) str2 = str2 + String.valueOf("\t") + String.valueOf(i) + String.valueOf("\t") + String.valueOf(this.dynamicValues[i]);
/*     */ 
/* 227 */         if (i < this.panValues.length) str2 = str2 + String.valueOf("\t") + String.valueOf(i / 100.0D) + String.valueOf("\t") + String.valueOf(this.panValues[i]);
/*     */ 
/* 229 */         str2 = str2 + String.valueOf("\n");
/* 230 */         localFileOutputStream.write(str2.getBytes());
/*     */       }
/* 232 */       localFileOutputStream.close(); } catch (IOException localIOException) {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics) {
/* 237 */     paramGraphics.setFont(this.f);
/* 238 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics(this.f);
/* 239 */     int i = localFontMetrics.getAscent() / 2;
/*     */ 
/* 241 */     int j = 0;
/* 242 */     if (this.type == 0) j = this.maxPitchValue;
/* 243 */     else if (this.type == 1) j = this.maxRhythmValue;
/* 244 */     else if (this.type == 2) j = this.maxDynamicValue;
/* 245 */     else if (this.type == 3) j = this.maxPanValue;
/*     */ 
/* 247 */     for (int k = 0; k < 5; ++k) {
/* 248 */       paramGraphics.setColor(Color.green);
/* 249 */       String str = "" + (j / 4 * k);
/* 250 */       paramGraphics.drawString(str, getSize().width / 5 * k + this.lableSpace - (localFontMetrics.stringWidth(str) / 2), this.lableSpace - (localFontMetrics.getAscent() / 2));
/*     */ 
/* 252 */       paramGraphics.setColor(Color.gray);
/* 253 */       paramGraphics.drawLine(getSize().width / 5 * k + this.lableSpace, this.lableSpace, getSize().width / 5 * k + this.lableSpace, getSize().height);
/*     */     }
/*     */ 
/* 258 */     switch (this.type)
/*     */     {
/*     */     case 0:
/* 260 */       paintPitches(paramGraphics);
/* 261 */       break;
/*     */     case 1:
/* 264 */       paintRhythms(paramGraphics);
/* 265 */       break;
/*     */     case 2:
/* 268 */       paintDynamics(paramGraphics);
/* 269 */       break;
/*     */     case 3:
/* 272 */       paintPans(paramGraphics);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void paintPitches(Graphics paramGraphics)
/*     */   {
/* 280 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics(this.f);
/* 281 */     int i = localFontMetrics.getAscent() / 2;
/*     */ 
/* 283 */     for (int j = 0; j < 127; ++j) {
/* 284 */       if (j % 12 == 0) paramGraphics.setColor(Color.red);
/* 285 */       else if (j % 12 == 4) paramGraphics.setColor(Color.orange);
/* 286 */       else if (j % 12 == 7) paramGraphics.setColor(Color.blue);
/*     */       else paramGraphics.setColor(Color.black);
/* 288 */       paramGraphics.fillRect(this.lableSpace, j * this.barWidth + this.lableSpace, (int)(this.pitchValues[j] / this.maxPitchValue * (getSize().width - this.lableSpace)), this.barWidth - 1);
/*     */ 
/* 291 */       if (j % 12 == 0) {
/* 292 */         paramGraphics.setColor(Color.red);
/* 293 */         paramGraphics.drawString("C" + (j / 12 - 1), 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/* 295 */       if (j % 12 == 4) {
/* 296 */         paramGraphics.setColor(Color.orange);
/* 297 */         paramGraphics.drawString("E" + (j / 12 - 1), 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/* 299 */       if (j % 12 == 7) {
/* 300 */         paramGraphics.setColor(Color.blue);
/* 301 */         paramGraphics.drawString("G" + (j / 12 - 1), 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void paintRhythms(Graphics paramGraphics)
/*     */   {
/* 308 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics(this.f);
/* 309 */     int i = localFontMetrics.getAscent();
/*     */ 
/* 311 */     for (int j = 1; j < 65; ++j) {
/* 312 */       if (j % 8 == 0) paramGraphics.setColor(Color.red);
/* 313 */       else if (j % 8 == 4) paramGraphics.setColor(Color.orange);
/* 314 */       else if ((j % 8 == 2) || (j % 8 == 6)) paramGraphics.setColor(Color.blue);
/*     */       else paramGraphics.setColor(Color.black);
/* 316 */       paramGraphics.fillRect(this.lableSpace, j * this.barWidth * 2 + this.lableSpace, (int)(this.rhythmValues[j] / this.maxRhythmValue * (getSize().width - this.lableSpace)), this.barWidth * 2 - 1);
/*     */ 
/* 319 */       if (j % 8 == 0) {
/* 320 */         paramGraphics.setColor(Color.red);
/* 321 */         paramGraphics.drawString("" + (j / 8.0D), 2, j * this.barWidth * 2 + i + this.lableSpace);
/*     */       }
/* 323 */       if (j % 8 == 4) {
/* 324 */         paramGraphics.setColor(Color.orange);
/* 325 */         paramGraphics.drawString("" + (j / 8.0D), 2, j * this.barWidth * 2 + i + this.lableSpace);
/*     */       }
/* 327 */       if ((j % 8 == 2) || (j % 8 == 6)) {
/* 328 */         paramGraphics.setColor(Color.blue);
/* 329 */         paramGraphics.drawString("" + (j / 8.0D), 2, j * this.barWidth * 2 + i + this.lableSpace);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void paintDynamics(Graphics paramGraphics)
/*     */   {
/* 336 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics(this.f);
/* 337 */     int i = localFontMetrics.getAscent() / 2;
/*     */ 
/* 339 */     for (int j = 1; j < 127; ++j) {
/* 340 */       if (j % 10 == 0) paramGraphics.setColor(Color.red);
/* 341 */       else if (j % 10 == 5) paramGraphics.setColor(Color.orange);
/*     */       else paramGraphics.setColor(Color.black);
/* 343 */       paramGraphics.fillRect(this.lableSpace, j * this.barWidth + this.lableSpace, (int)(this.dynamicValues[j] / this.maxDynamicValue * (getSize().width - this.lableSpace)), this.barWidth - 1);
/*     */ 
/* 346 */       if (j % 10 == 0) {
/* 347 */         paramGraphics.setColor(Color.red);
/* 348 */         paramGraphics.drawString("" + j, 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/* 350 */       if (j % 10 == 5) {
/* 351 */         paramGraphics.setColor(Color.orange);
/* 352 */         paramGraphics.drawString("" + j, 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void paintPans(Graphics paramGraphics)
/*     */   {
/* 359 */     FontMetrics localFontMetrics = paramGraphics.getFontMetrics(this.f);
/* 360 */     int i = localFontMetrics.getAscent() / 2;
/*     */ 
/* 362 */     for (int j = 1; j < 100; ++j) {
/* 363 */       if ((j % 10 == 0) && (j != 50)) paramGraphics.setColor(Color.red);
/* 364 */       else if ((j % 10 == 5) && (j != 50)) paramGraphics.setColor(Color.orange);
/* 365 */       else if (j == 50) paramGraphics.setColor(Color.blue);
/*     */       else paramGraphics.setColor(Color.black);
/* 367 */       paramGraphics.fillRect(this.lableSpace, j * this.barWidth + this.lableSpace, (int)(this.panValues[j] / this.maxPanValue * (getSize().width - this.lableSpace)), this.barWidth - 1);
/*     */ 
/* 370 */       if ((j % 10 == 0) && (j != 50)) {
/* 371 */         paramGraphics.setColor(Color.red);
/* 372 */         paramGraphics.drawString("" + (j / 100.0D), 2, j * this.barWidth + i + this.lableSpace);
/* 373 */       } else if ((j % 10 == 5) && (j != 50)) {
/* 374 */         paramGraphics.setColor(Color.orange);
/* 375 */         paramGraphics.drawString("" + (j / 100.0D), 2, j * this.barWidth + i + this.lableSpace);
/* 376 */       } else if (j == 50) {
/* 377 */         paramGraphics.setColor(Color.blue);
/* 378 */         paramGraphics.drawString("" + (j / 100.0D), 2, j * this.barWidth + i + this.lableSpace);
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.histogram.Histogram
 * JD-Core Version:    0.5.3
 */