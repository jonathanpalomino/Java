/*     */ package jm.gui.sketch;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.music.tools.Mod;
/*     */ 
/*     */ public class SketchScoreArea extends Canvas
/*     */   implements JMC, KeyListener, MouseListener, MouseMotionListener
/*     */ {
/*     */   private Score score;
/*     */   private int scoreChannels;
/*     */   private int currentChannel;
/*  39 */   private int oldY = 0;
/*  40 */   private Color[] theColors = new Color[10];
/*     */   private int maxWidth;
/*     */   private double beatWidth;
/*     */   private int x;
/*     */   private int y;
/*  45 */   private int newWidth = 650;
/*  46 */   private Vector drawPoints = new Vector();
/*  47 */   private int myHeight = 127;
/*     */   private SketchScore sc;
/*     */ 
/*     */   public SketchScoreArea(Score paramScore, int paramInt, double paramDouble)
/*     */   {
/*  52 */     this.score = paramScore;
/*  53 */     this.scoreChannels = paramScore.size();
/*  54 */     this.currentChannel = this.scoreChannels;
/*  55 */     this.maxWidth = paramInt;
/*  56 */     this.beatWidth = paramDouble;
/*     */ 
/*  59 */     setSize(paramInt, this.myHeight);
/*  60 */     this.score = paramScore;
/*  61 */     this.maxWidth = paramInt;
/*     */ 
/*  63 */     addKeyListener(this);
/*  64 */     addMouseListener(this);
/*  65 */     addMouseMotionListener(this);
/*     */ 
/*  67 */     setBackground(new Color(250, 250, 250));
/*     */ 
/*  70 */     for (int i = 0; i < 10; ++i) {
/*  71 */       Color localColor = new Color((int)(Math.random() * 256.0D), (int)(Math.random() * 256.0D), (int)(Math.random() * 256.0D));
/*     */ 
/*  74 */       this.theColors[i] = localColor;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setBeatWidth(double paramDouble)
/*     */   {
/*  81 */     this.beatWidth = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setScore(Score paramScore)
/*     */   {
/*  87 */     this.score = paramScore;
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  94 */     return this.myHeight;
/*     */   }
/*     */ 
/*     */   public void setSketchScore(SketchScore paramSketchScore)
/*     */   {
/* 101 */     this.sc = paramSketchScore;
/*     */   }
/*     */ 
/*     */   public int getNewWidth()
/*     */   {
/* 108 */     return this.newWidth;
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 115 */     this.score.clean();
/*     */ 
/* 117 */     Enumeration localEnumeration1 = this.score.getPartList().elements();
/* 118 */     int i = 0;
/*     */ 
/* 125 */     while (localEnumeration1.hasMoreElements()) {
/* 126 */       localObject = (Part)localEnumeration1.nextElement();
/*     */ 
/* 128 */       paramGraphics.setColor(this.theColors[(i % 10)]);
/* 129 */       ++i;
/*     */ 
/* 131 */       Enumeration localEnumeration2 = ((Part)localObject).getPhraseList().elements();
/* 132 */       while (localEnumeration2.hasMoreElements()) {
/* 133 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 134 */         Enumeration localEnumeration3 = localPhrase.getNoteList().elements();
/* 135 */         double d = localPhrase.getStartTime();
/*     */ 
/* 137 */         while (localEnumeration3.hasMoreElements()) {
/* 138 */           Note localNote = (Note)localEnumeration3.nextElement();
/*     */ 
/* 140 */           int i2 = -1;
/* 141 */           if (!(localNote.getPitchType())) i2 = localNote.getPitch();
/*     */           else i2 = Note.freqToMidiPitch(localNote.getFrequency());
/* 143 */           if (i2 != -2147483648) {
/* 144 */             int i3 = 127 - i2;
/* 145 */             int i4 = (int)Math.round(localNote.getDuration() * this.beatWidth);
/* 146 */             int i5 = (int)Math.round(d * this.beatWidth);
/* 147 */             paramGraphics.drawLine(i5, i3, i5 + i4, i3);
/*     */           }
/* 149 */           d += localNote.getRhythmValue();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 154 */     paramGraphics.setColor(Color.black);
/* 155 */     Object localObject = this.drawPoints.elements();
/* 156 */     while (((Enumeration)localObject).hasMoreElements()) {
/* 157 */       int j = ((Integer)(Integer)((Enumeration)localObject).nextElement()).intValue();
/* 158 */       int k = ((Integer)(Integer)((Enumeration)localObject).nextElement()).intValue();
/* 159 */       int l = ((Integer)(Integer)((Enumeration)localObject).nextElement()).intValue();
/* 160 */       int i1 = ((Integer)(Integer)((Enumeration)localObject).nextElement()).intValue();
/*     */ 
/* 162 */       paramGraphics.drawLine(j, k, l, i1); } }
/*     */ 
/*     */   public void keyPressed(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {
/* 171 */     if (paramKeyEvent.getKeyChar() == '\b') {
/* 172 */       if (this.score.getPart(0).size() < 1) return;
/* 173 */       Vector localVector = this.score.getPartList();
/* 174 */       localVector.removeElementAt(localVector.size() - 1);
/* 175 */       if (this.sc == null)
/* 176 */         repaint();
/*     */       else this.sc.update();
/* 178 */       this.newWidth = 50; } }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent) {
/* 190 */     this.x = paramMouseEvent.getX();
/* 191 */     this.y = paramMouseEvent.getY();
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent)
/*     */   {
/* 196 */     this.drawPoints.addElement(new Integer(this.x));
/* 197 */     this.drawPoints.addElement(new Integer(this.y));
/*     */ 
/* 199 */     this.drawPoints.addElement(new Integer(paramMouseEvent.getX()));
/* 200 */     this.drawPoints.addElement(new Integer(paramMouseEvent.getY()));
/*     */ 
/* 202 */     this.x = paramMouseEvent.getX();
/* 203 */     this.y = paramMouseEvent.getY();
/*     */ 
/* 205 */     if ((paramMouseEvent.getX() > (int)(this.score.getEndTime() * this.beatWidth)) && (paramMouseEvent.getX() > this.newWidth)) this.newWidth = paramMouseEvent.getX();
/*     */ 
/* 207 */     repaint();
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent)
/*     */   {
/* 214 */     if (this.drawPoints.size() > 0) {
/* 215 */       if (paramMouseEvent.getModifiers() == 24) convertLineToPhrase(true);
/*     */       else convertLineToPhrase(false);
/*     */     }
/* 218 */     this.drawPoints.removeAllElements();
/* 219 */     this.newWidth = 50;
/* 220 */     repaint();
/*     */   }
/*     */ 
/*     */   private void convertLineToPhrase(boolean paramBoolean) {
/* 224 */     Phrase localPhrase = null;
/* 225 */     double[][] arrayOfDouble = new double[this.drawPoints.size() / 4][3];
/* 226 */     Enumeration localEnumeration = this.drawPoints.elements();
/* 227 */     int i = 0;
/* 228 */     while (localEnumeration.hasMoreElements()) {
/* 229 */       j = ((Integer)(Integer)localEnumeration.nextElement()).intValue();
/* 230 */       int k = ((Integer)(Integer)localEnumeration.nextElement()).intValue();
/* 231 */       int l = ((Integer)(Integer)localEnumeration.nextElement()).intValue();
/* 232 */       int i1 = ((Integer)(Integer)localEnumeration.nextElement()).intValue();
/*     */ 
/* 234 */       if (paramBoolean)
/*     */       {
/* 236 */         for (int i2 = 0; i2 < Math.abs(k - i1); ++i2) {
/* 237 */           arrayOfDouble[i][0] = (((Integer)this.drawPoints.elementAt(i * 4)).intValue() / this.beatWidth + (Math.abs(l - j) / this.beatWidth / Math.abs(k - i1) + 1.0D) / (Math.abs(k - i1) + 1));
/*     */ 
/* 241 */           if (((Integer)this.drawPoints.elementAt(i * 4)).intValue() > ((Integer)this.drawPoints.elementAt(i * 4 + 2)).intValue())
/*     */           {
/* 244 */             arrayOfDouble[i][0] = (((Integer)this.drawPoints.elementAt(i * 4 + 2)).intValue() / this.beatWidth / Math.abs(k - i1) + 1.0D);
/*     */           }
/*     */ 
/* 248 */           arrayOfDouble[i][1] = (127 - k + i2);
/*     */ 
/* 250 */           arrayOfDouble[i][2] = (Math.abs(l - j) / this.beatWidth / Math.abs(k - i1) + 1.0D);
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 256 */         arrayOfDouble[i][0] = (((Integer)this.drawPoints.elementAt(i * 4)).intValue() / this.beatWidth);
/*     */ 
/* 258 */         if (((Integer)this.drawPoints.elementAt(i * 4)).intValue() > ((Integer)this.drawPoints.elementAt(i * 4 + 2)).intValue())
/*     */         {
/* 261 */           arrayOfDouble[i][0] = (((Integer)this.drawPoints.elementAt(i * 4 + 2)).intValue() / this.beatWidth);
/*     */         }
/*     */ 
/* 264 */         arrayOfDouble[i][1] = (127 - k);
/* 265 */         arrayOfDouble[i][2] = (Math.abs(l - j) / this.beatWidth);
/*     */       }
/* 267 */       ++i;
/*     */     }
/*     */ 
/* 270 */     quickSort(arrayOfDouble, 0, arrayOfDouble.length - 1);
/*     */ 
/* 273 */     if (this.drawPoints.size() > 0) localPhrase = new Phrase(arrayOfDouble[0][0]);
/*     */     Object localObject;
/* 274 */     for (int j = 0; j < arrayOfDouble.length - 1; ++j)
/*     */     {
/* 276 */       if (arrayOfDouble[j][1] < 0.0D) arrayOfDouble[j][1] = 0.0D;
/* 277 */       if (arrayOfDouble[j][1] > 127.0D) arrayOfDouble[j][1] = 127.0D;
/* 278 */       localObject = new Note((int)arrayOfDouble[j][1], arrayOfDouble[(j + 1)][0] - arrayOfDouble[j][0]);
/*     */ 
/* 280 */       if ((j > 0) && (!(((Note)localObject).getPitchType())) && (!(((Note)(Note)localPhrase.getNoteList().lastElement()).getPitchType())))
/*     */       {
/* 282 */         if ((localPhrase.size() > 0) && (((Note)localObject).getPitch() == ((Note)(Note)localPhrase.getNoteList().lastElement()).getPitch()))
/*     */         {
/* 284 */           Mod.append((Note)(Note)localPhrase.getNoteList().lastElement(), (Note)localObject);
/*     */         }
/*     */         else localPhrase.addNote((Note)localObject);
/*     */       }
/*     */       else localPhrase.addNote((Note)localObject);
/*     */     }
/*     */ 
/* 291 */     if (arrayOfDouble[(arrayOfDouble.length - 1)][1] < 0.0D) arrayOfDouble[(arrayOfDouble.length - 1)][1] = 0.0D;
/* 292 */     if (arrayOfDouble[(arrayOfDouble.length - 1)][1] > 127.0D) arrayOfDouble[(arrayOfDouble.length - 1)][1] = 127.0D;
/* 293 */     Note localNote = new Note((int)arrayOfDouble[(arrayOfDouble.length - 1)][1], arrayOfDouble[(arrayOfDouble.length - 1)][2]);
/* 294 */     localNote.setDuration(arrayOfDouble[(arrayOfDouble.length - 1)][2]);
/* 295 */     localPhrase.addNote(localNote);
/*     */ 
/* 298 */     if (localPhrase != null) {
/* 299 */       this.currentChannel += 1;
/* 300 */       if (this.currentChannel > 15) this.currentChannel = this.scoreChannels;
/* 301 */       localObject = new Part("Sketch Part", 0, this.currentChannel);
/* 302 */       ((Part)localObject).addPhrase(localPhrase);
/* 303 */       this.score.addPart((Part)localObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void quickSort(double[][] paramArrayOfDouble, int paramInt1, int paramInt2)
/*     */   {
/* 309 */     if (paramInt1 >= paramInt2) return;
/* 310 */     swap(paramArrayOfDouble, paramInt1, (int)(Math.random() * (paramInt2 - paramInt1)) + paramInt1);
/* 311 */     int j = paramInt1;
/* 312 */     for (int i = paramInt1 + 1; i <= paramInt2; ++i) {
/* 313 */       if (paramArrayOfDouble[i][0] > paramArrayOfDouble[paramInt1][0]) continue; swap(paramArrayOfDouble, ++j, i);
/*     */     }
/* 315 */     swap(paramArrayOfDouble, paramInt1, j);
/* 316 */     quickSort(paramArrayOfDouble, paramInt1, j - 1);
/* 317 */     quickSort(paramArrayOfDouble, j + 1, paramInt2);
/*     */   }
/*     */ 
/*     */   static void swap(double[][] paramArrayOfDouble, int paramInt1, int paramInt2)
/*     */   {
/* 322 */     for (int i = 0; i < 3; ++i) {
/* 323 */       double d = paramArrayOfDouble[paramInt1][i];
/* 324 */       paramArrayOfDouble[paramInt1][i] = paramArrayOfDouble[paramInt2][i];
/* 325 */       paramArrayOfDouble[paramInt2][i] = d;
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.sketch.SketchScoreArea
 * JD-Core Version:    0.5.3
 */