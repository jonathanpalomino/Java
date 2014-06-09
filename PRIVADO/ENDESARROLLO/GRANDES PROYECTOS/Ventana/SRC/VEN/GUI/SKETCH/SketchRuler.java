/*     */ package jm.gui.sketch;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class SketchRuler extends Canvas
/*     */   implements MouseListener, MouseMotionListener, KeyListener
/*     */ {
/*     */   private int startX;
/*  53 */   private int height = 15;
/*  54 */   private int barNumbCount = 2;
/*     */   private SketchScore sketchScore;
/*  56 */   private Font font = new Font("Helvetica", 0, 10);
/*     */ 
/*     */   public SketchRuler(SketchScore paramSketchScore)
/*     */   {
/*  60 */     this.sketchScore = paramSketchScore;
/*  61 */     setSize((int)(SketchScore.score.getEndTime() * paramSketchScore.beatWidth), this.height);
/*  62 */     setBackground(Color.lightGray);
/*  63 */     addMouseListener(this);
/*  64 */     addMouseMotionListener(this);
/*  65 */     addKeyListener(this);
/*  66 */     setCursor(new Cursor(13));
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  73 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/*  78 */     double d = this.sketchScore.beatWidth;
/*  79 */     paramGraphics.setFont(this.font);
/*  80 */     for (int i = 0; i < this.sketchScore.getSketchScoreArea().getNewWidth(); ++i) {
/*  81 */       int j = (int)Math.round(i * d);
/*  82 */       if (i % this.barNumbCount == 0) {
/*  83 */         paramGraphics.drawLine(j, 0, j, this.height);
/*  84 */         if (d <= 15.0D) continue; paramGraphics.drawString("" + i, j + 2, this.height - 2);
/*     */       } else {
/*  86 */         paramGraphics.drawLine(j, this.height / 2, j, this.height);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent)
/*     */   {
/*  94 */     setCursor(new Cursor(10));
/*  95 */     this.startX = paramMouseEvent.getX(); }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) { setCursor(new Cursor(13));
/* 104 */     repaint();
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent) {
/* 111 */     double d = this.sketchScore.beatWidth;
/* 112 */     d += (paramMouseEvent.getX() - this.startX) / 5.0D;
/* 113 */     if (d < 1.0D) d = 1.0D;
/* 114 */     if (d > 256.0D) d = 256.0D;
/*     */ 
/* 116 */     this.sketchScore.beatWidth = d;
/* 117 */     this.startX = paramMouseEvent.getX();
/* 118 */     this.sketchScore.update(); }
/*     */ 
/*     */   public void keyPressed(KeyEvent paramKeyEvent) { }
/*     */ 
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {
/* 126 */     if (paramKeyEvent.getKeyChar() == '\b')
/* 127 */       repaint();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.sketch.SketchRuler
 * JD-Core Version:    0.5.3
 */