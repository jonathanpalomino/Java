/*     */ package jm.gui.wave;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ 
/*     */ public class WaveRuler extends Panel
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*     */   private WaveScrollPanel scrollPanel;
/*     */   private int markerWidth;
/*     */   private int startX;
/*     */   private double tempRes;
/*  38 */   private Font font = new Font("Helvetica", 0, 9);
/*  39 */   private int startSecond = 0;
/*     */ 
/*     */   public WaveRuler()
/*     */   {
/*  43 */     setBackground(Color.lightGray);
/*  44 */     setSize(new Dimension(600, 20));
/*  45 */     addMouseListener(this);
/*  46 */     addMouseMotionListener(this);
/*     */   }
/*     */ 
/*     */   public void setWaveScrollPanel(WaveScrollPanel paramWaveScrollPanel)
/*     */   {
/*  53 */     this.scrollPanel = paramWaveScrollPanel;
/*     */   }
/*     */ 
/*     */   public void setMarkerWidth(int paramInt) {
/*  57 */     if (paramInt > 0) {
/*  58 */       this.markerWidth = paramInt;
/*  59 */       repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics) {
/*  64 */     paramGraphics.setColor(Color.darkGray);
/*  65 */     paramGraphics.setFont(this.font);
/*  66 */     int i = getSize().width;
/*  67 */     int j = -1 * this.scrollPanel.getWaveView().getStartPos();
/*  68 */     int k = this.markerWidth;
/*  69 */     int l = (int)Math.round(this.markerWidth / 10.0D);
/*  70 */     int i1 = (int)Math.round(this.markerWidth / 100.0D);
/*  71 */     int i2 = (int)Math.round(this.markerWidth / 1000.0D);
/*  72 */     int i3 = this.scrollPanel.getWaveView().getResolution();
/*  73 */     this.startSecond = (this.scrollPanel.getWaveView().getStartPos() / this.scrollPanel.getWaveView().getSampleRate() / this.scrollPanel.getWaveView().getChannels());
/*     */ 
/*  77 */     int i4 = 0;
/*  78 */     paramGraphics.setColor(Color.white);
/*     */     int i6;
/*     */     int i7;
/*     */     int i8;
/*  79 */     if (this.markerWidth > 20000) {
/*  80 */       for (i5 = j / i3; i5 < i; i5 += k) {
/*  81 */         for (i6 = 0; i6 < 10; ++i6) {
/*  82 */           for (i7 = 0; i7 < 10; ++i7) {
/*  83 */             for (i8 = 0; i8 < 10; ++i8) {
/*  84 */               if (i4 % 10 != 0) {
/*  85 */                 int i9 = i5 + i6 * l + i7 * i1 + i8 * i2;
/*  86 */                 paramGraphics.drawLine(i9, getSize().height / 8 * 7, i9, getSize().height);
/*  87 */                 if (this.markerWidth > 40000) paramGraphics.drawString("" + (this.startSecond + i4 / 1000.0D), i9 + 2, getSize().height - 1);
/*     */               }
/*     */ 
/*  90 */               ++i4;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  98 */     i4 = 0;
/*  99 */     paramGraphics.setColor(Color.magenta);
/* 100 */     if (this.markerWidth > 1200) {
/* 101 */       for (i5 = j / i3; i5 < i; i5 += k) {
/* 102 */         for (i6 = 0; i6 < 10; ++i6) {
/* 103 */           for (i7 = 0; i7 < 10; ++i7) {
/* 104 */             if (i4 % 10 != 0) {
/* 105 */               i8 = i5 + i6 * l + i7 * i1;
/* 106 */               paramGraphics.drawLine(i8, getSize().height / 4 * 3, i8, getSize().height);
/* 107 */               if (this.markerWidth > 4800) paramGraphics.drawString("" + (this.startSecond + i4 / 100.0D), i8 + 2, getSize().height - 1);
/*     */             }
/*     */ 
/* 110 */             ++i4;
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 117 */     i4 = 0;
/* 118 */     paramGraphics.setColor(Color.blue);
/* 119 */     if (this.markerWidth > 150) {
/* 120 */       for (i5 = j / i3; i5 < i; i5 += k) {
/* 121 */         for (i6 = 0; i6 < 10; ++i6) {
/* 122 */           if (i4 % 10 != 0) {
/* 123 */             i7 = i5 + i6 * l;
/* 124 */             paramGraphics.drawLine(i7, getSize().height / 2, i7, getSize().height);
/* 125 */             if (this.markerWidth > 300) paramGraphics.drawString("" + (this.startSecond + i4 / 10.0D), i7 + 2, getSize().height - 1);
/*     */           }
/*     */ 
/* 128 */           ++i4;
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 134 */     i4 = 0;
/* 135 */     paramGraphics.setColor(Color.red);
/* 136 */     for (int i5 = j / i3; i5 < i; i5 += k) {
/* 137 */       paramGraphics.drawLine(i5, 1, i5, getSize().height);
/* 138 */       if ((this.markerWidth > 20) && (this.markerWidth <= 300)) paramGraphics.drawString("" + (this.startSecond + i4), i5 + 2, getSize().height - 1);
/* 140 */       else if (this.markerWidth > 300) paramGraphics.drawString("" + (this.startSecond + i4 / 1.0D), i5 + 2, getSize().height - 1);
/*     */ 
/* 142 */       ++i4;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent)
/*     */   {
/* 151 */     setCursor(new Cursor(10));
/* 152 */     this.startX = paramMouseEvent.getX();
/* 153 */     this.tempRes = this.scrollPanel.getWaveView().getResolution(); }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent) { setCursor(new Cursor(13));
/* 162 */     this.scrollPanel.getWaveView().setResolution((int)this.tempRes); }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent) {
/* 168 */     int i = 5;
/* 169 */     if (paramMouseEvent.getX() > this.startX + i) {
/* 170 */       if (this.tempRes < 8.0D) this.tempRes = Math.round(this.tempRes / 2.0D);
/*     */       else this.tempRes = Math.round(this.tempRes / 1.1D);
/* 172 */       if (this.tempRes < 1.0D) this.tempRes = 1.0D;
/* 173 */       if (this.tempRes > 2048.0D) this.tempRes = 2048.0D;
/* 174 */       this.scrollPanel.setResolution((int)Math.round(this.tempRes));
/* 175 */       this.startX = paramMouseEvent.getX();
/* 176 */       repaint();
/*     */     }
/* 178 */     if (paramMouseEvent.getX() < this.startX - i) {
/* 179 */       if (this.tempRes < 8.0D) this.tempRes = Math.round(this.tempRes * 2.0D);
/*     */       else this.tempRes = Math.round(this.tempRes * 1.1D);
/* 181 */       if (this.tempRes < 1.0D) this.tempRes = 1.0D;
/* 182 */       if (this.tempRes > 2048.0D) this.tempRes = 2048.0D;
/* 183 */       this.scrollPanel.setResolution((int)Math.round(this.tempRes));
/* 184 */       this.startX = paramMouseEvent.getX();
/* 185 */       repaint();
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.wave.WaveRuler
 * JD-Core Version:    0.5.3
 */