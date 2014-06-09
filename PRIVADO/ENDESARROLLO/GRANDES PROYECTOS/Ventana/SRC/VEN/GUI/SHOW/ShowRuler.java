/*     */ package jm.gui.show;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ShowRuler extends Canvas
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   private int startX;
/*  46 */   private int height = 15;
/*  47 */   private int timeSig = 2;
/*     */   private ShowPanel sp;
/*  49 */   private Font font = new Font("Helvetica", 0, 10);
/*     */ 
/*     */   public ShowRuler(ShowPanel paramShowPanel)
/*     */   {
/*  53 */     this.sp = paramShowPanel;
/*  54 */     setSize((int)(paramShowPanel.score.getEndTime() * paramShowPanel.beatWidth), this.height);
/*  55 */     setBackground(Color.lightGray);
/*  56 */     addMouseListener(this);
/*  57 */     addMouseMotionListener(this);
/*  58 */     setCursor(new Cursor(13));
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/*  65 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics) {
/*  69 */     double d = this.sp.beatWidth;
/*  70 */     paramGraphics.setFont(this.font);
/*  71 */     for (int i = 0; i < this.sp.score.getEndTime(); ++i) {
/*  72 */       int j = (int)Math.round(i * d);
/*  73 */       if (i % this.timeSig == 0) {
/*  74 */         paramGraphics.drawLine(j, 0, j, this.height);
/*  75 */         if (d <= 15.0D) continue; paramGraphics.drawString("" + i, j + 2, this.height - 2);
/*     */       } else {
/*  77 */         paramGraphics.drawLine(j, this.height / 2, j, this.height);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent)
/*     */   {
/*  85 */     setCursor(new Cursor(10));
/*  86 */     this.startX = paramMouseEvent.getX(); }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) { setCursor(new Cursor(13));
/*  95 */     this.sp.update();
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent) {
/* 102 */     double d = this.sp.beatWidth;
/* 103 */     d += (paramMouseEvent.getX() - this.startX) / 5.0D;
/* 104 */     if (d < 1.0D) d = 1.0D;
/* 105 */     if (d > 256.0D) d = 256.0D;
/*     */ 
/* 107 */     this.sp.beatWidth = d;
/* 108 */     this.startX = paramMouseEvent.getX();
/*     */ 
/* 110 */     repaint();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.show.ShowRuler
 * JD-Core Version:    0.5.3
 */