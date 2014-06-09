/*     */ package jm.gui.wave;
/*     */ 
/*     */ import java.awt.Canvas;
/*     */ import java.awt.Color;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ 
/*     */ public class WaveCanvas extends Canvas
/*     */ {
/*     */   private float[] data;
/*  40 */   private int segmentSize = 0;
/*  41 */   private int resolution = 125;
/*  42 */   private int height = 200;
/*  43 */   private int amplitude = 1;
/*     */ 
/*  45 */   public Image image = null;
/*     */   protected Graphics g;
/*  47 */   private boolean resized = false;
/*  48 */   private Color waveColor = Color.darkGray;
/*  49 */   private Color backgroundColor = Color.white;
/*  50 */   private int sampleStart = 0;
/*     */   private int waveSize;
/*  52 */   private boolean fastDraw = false;
/*     */ 
/*     */   public void setData(float[] paramArrayOfFloat)
/*     */   {
/*  62 */     this.data = paramArrayOfFloat;
/*  63 */     this.segmentSize = paramArrayOfFloat.length;
/*     */   }
/*     */ 
/*     */   public void setResolution(int paramInt)
/*     */   {
/*  71 */     if (paramInt > 0) this.resolution = paramInt;
/*  72 */     setFastDraw(true);
/*  73 */     repaint();
/*  74 */     setFastDraw(false);
/*  75 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setHeight(int paramInt)
/*     */   {
/*  83 */     if (paramInt > 0) this.height = paramInt;
/*  84 */     setSize(new Dimension(600, this.height + 1));
/*  85 */     this.resized = true;
/*  86 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setFastDraw(boolean paramBoolean)
/*     */   {
/*  94 */     this.fastDraw = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setAmplitude(int paramInt)
/*     */   {
/* 103 */     if (paramInt > 0) this.amplitude = paramInt;
/* 104 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setWaveSize(int paramInt)
/*     */   {
/* 111 */     this.waveSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setResized(boolean paramBoolean)
/*     */   {
/* 119 */     this.resized = paramBoolean;
/* 120 */     setFastDraw(true);
/* 121 */     repaint();
/* 122 */     setFastDraw(false);
/* 123 */     repaint();
/*     */   }
/*     */ 
/*     */   public void toggleColor()
/*     */   {
/* 128 */     if (this.waveColor == Color.darkGray) {
/* 129 */       this.waveColor = Color.green;
/* 130 */       this.backgroundColor = Color.darkGray;
/*     */     } else {
/* 132 */       this.waveColor = Color.darkGray;
/* 133 */       this.backgroundColor = Color.white;
/*     */     }
/*     */ 
/* 136 */     repaint();
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/* 141 */     setCursor(new Cursor(3));
/*     */ 
/* 143 */     if ((this.image == null) || (this.resized)) {
/* 144 */       this.image = createImage(getSize().width, getSize().height);
/* 145 */       this.g = this.image.getGraphics();
/* 146 */       this.resized = false;
/*     */     }
/* 148 */     clearImage(this.g);
/* 149 */     int i = this.height / 2 - 1;
/*     */ 
/* 152 */     this.g.setColor(Color.black);
/* 153 */     this.g.drawLine(0, i, getSize().width, i);
/*     */ 
/* 157 */     int j = getSize().width * this.resolution;
/* 158 */     int k = this.data.length - this.resolution;
/* 159 */     this.g.setColor(this.waveColor);
/*     */     int l;
/*     */     float f3;
/* 160 */     if (this.resolution == 1) {
/* 161 */       for (l = 0; (l < j) && (l < k); ++l) {
/* 162 */         f3 = this.data[l];
/* 163 */         this.g.drawLine(l, (int)(i - (f3 * i * this.amplitude)), l, (int)(i - (f3 * i * this.amplitude)));
/*     */       }
/*     */     }
/*     */     else {
/* 167 */       l = 0;
/* 168 */       float f4 = 0.0F;
/* 169 */       float f5 = 0.0F;
/*     */ 
/* 171 */       for (int i1 = 0; (i1 < j) && (i1 < k); i1 += this.resolution) {
/* 172 */         f3 = this.data[i1];
/*     */ 
/* 174 */         if (this.fastDraw) {
/* 175 */           this.g.drawLine(l++, (int)(i - (f3 * i * this.amplitude)), l, (int)(i - (f3 * i * this.amplitude)));
/*     */         }
/*     */         else
/*     */         {
/* 179 */           f4 = 0.0F;
/* 180 */           f5 = 0.0F;
/* 181 */           for (int i2 = 0; i2 < this.resolution; ++i2) {
/* 182 */             if (this.data[(i1 + i2)] > f4) f4 = this.data[(i1 + i2)];
/* 183 */             if (this.data[(i1 + i2)] >= f5) continue; f5 = this.data[(i1 + i2)];
/*     */           }
/*     */ 
/* 186 */           if (this.resolution > 8) {
/* 187 */             float f1 = Math.max(f3, this.data[(i1 + this.resolution)]);
/* 188 */             float f2 = Math.min(f3, this.data[(i1 + this.resolution)]);
/* 189 */             if (f4 > 0.0F) this.g.drawLine(l, (int)(i - (f1 * i * this.amplitude)), l, (int)(i - (f4 * i * this.amplitude)));
/*     */ 
/* 192 */             if (f5 < 0.0F) this.g.drawLine(l, (int)(i - (f2 * i * this.amplitude)), l, (int)(i - (f5 * i * this.amplitude)));
/*     */ 
/*     */           }
/*     */ 
/* 197 */           this.g.drawLine(l++, (int)(i - (f3 * i * this.amplitude)), l, (int)(i - (this.data[(i1 + this.resolution)] * i * this.amplitude)));
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 203 */     this.g.setColor(Color.lightGray);
/*     */ 
/* 205 */     this.g.drawLine(0, this.height, getSize().width, this.height);
/*     */ 
/* 208 */     paramGraphics.drawImage(this.image, 0, 0, null);
/* 209 */     clearImage(this.g);
/*     */ 
/* 211 */     setCursor(new Cursor(0));
/*     */   }
/*     */ 
/*     */   private void clearImage(Graphics paramGraphics)
/*     */   {
/* 216 */     paramGraphics.setColor(this.backgroundColor);
/* 217 */     paramGraphics.fillRect(0, 0, getSize().width, getSize().height);
/* 218 */     paramGraphics.setColor(this.waveColor);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.wave.WaveCanvas
 * JD-Core Version:    0.5.3
 */