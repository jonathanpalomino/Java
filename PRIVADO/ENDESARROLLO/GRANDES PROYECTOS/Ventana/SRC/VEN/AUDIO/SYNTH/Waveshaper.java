/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Waveshaper extends AudioObject
/*     */ {
/*  40 */   private int shapeType = 1;
/*     */ 
/*  42 */   private int stages = 4;
/*     */   private double[] weights;
/*     */   public static final int POLYNOMIAL = 0;
/*     */   public static final int CHEBYSHEV = 1;
/*     */ 
/*     */   public Waveshaper(AudioObject paramAudioObject)
/*     */   {
/*  60 */     super(paramAudioObject, "[Waveshaper]");
/*  61 */     this.shapeType = 1;
/*  62 */     this.stages = 4;
/*  63 */     double[] arrayOfDouble = { 0.3D, 0.8D, 0.6D, 0.4D };
/*  64 */     this.weights = arrayOfDouble;
/*     */   }
/*     */ 
/*     */   public Waveshaper(AudioObject paramAudioObject, int paramInt1, int paramInt2)
/*     */   {
/*  76 */     super(paramAudioObject, "[Waveshaper]");
/*  77 */     this.shapeType = paramInt1;
/*  78 */     this.stages = paramInt2;
/*  79 */     double[] arrayOfDouble = { 0.3D, 0.8D, 0.6D, 0.4D };
/*  80 */     this.weights = arrayOfDouble;
/*     */   }
/*     */ 
/*     */   public Waveshaper(AudioObject paramAudioObject, int paramInt1, int paramInt2, double[] paramArrayOfDouble)
/*     */   {
/*  95 */     super(paramAudioObject, "[Waveshaper]");
/*  96 */     this.shapeType = paramInt1;
/*  97 */     this.stages = paramInt2;
/*  98 */     this.weights = paramArrayOfDouble;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 111 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/*     */     int j;
/*     */     float f1;
/*     */     float f2;
/* 112 */     if (this.shapeType == 0)
/* 113 */       for (j = 0; j < i; ++j) {
/* 114 */         f1 = Math.abs(paramArrayOfFloat[j]);
/* 115 */         f2 = f1;
/* 116 */         for (int k = 1; k < this.stages; ++k) {
/* 117 */           float f3 = f1;
/* 118 */           for (int l = 0; l < k; ++l) {
/* 119 */             f3 *= f1;
/*     */           }
/* 121 */           f2 = (float)(f2 + f3 * this.weights[k]);
/*     */         }
/* 123 */         if (paramArrayOfFloat[j] < 0.0D) f2 *= -1.0F;
/* 124 */         paramArrayOfFloat[j] = f2;
/*     */       }
/* 126 */     else if (this.shapeType == 1) {
/* 127 */       for (j = 0; j < i; ++j) {
/* 128 */         f1 = Math.abs(paramArrayOfFloat[j]);
/* 129 */         f2 = f1;
/* 130 */         if (this.stages > 1) {
/* 131 */           f2 = (float)(f2 + this.weights[0] * (2.0F * f1 * f1 - 1.0F));
/*     */         }
/* 133 */         if (this.stages > 2) {
/* 134 */           f2 = (float)(f2 + this.weights[1] * (4.0F * (float)Math.pow(f1, 3.0D) - (3.0F * f1)));
/*     */         }
/*     */ 
/* 137 */         if (this.stages > 3) {
/* 138 */           f2 = (float)(f2 + this.weights[2] * (8.0F * (float)Math.pow(f1, 4.0D) - (8.0F * (float)Math.pow(f1, 2.0D)) + 1.0F));
/*     */         }
/*     */ 
/* 141 */         if (this.stages > 4) {
/* 142 */           f2 = (float)(f2 + this.weights[3] * (16.0F * (float)Math.pow(f1, 5.0D) - (20.0F * (float)Math.pow(f1, 3.0D)) + 5.0F * f1));
/*     */         }
/*     */ 
/* 146 */         if (this.stages > 5) {
/* 147 */           f2 = (float)(f2 + this.weights[4] * (32.0F * (float)Math.pow(f1, 6.0D) - (48.0F * (float)Math.pow(f1, 4.0D)) + 18.0F * (float)Math.pow(f1, 2.0D) - 1.0F));
/*     */         }
/*     */ 
/* 151 */         if (this.stages > 6) {
/* 152 */           f2 = (float)(f2 + this.weights[5] * (64.0F * (float)Math.pow(f1, 7.0D) - (112.0F * (float)Math.pow(f1, 5.0D)) + 56.0F * (float)Math.pow(f1, 3.0D) - (7.0F * f1)));
/*     */         }
/*     */ 
/* 157 */         if (this.stages > 7) {
/* 158 */           f2 = (float)(f2 + this.weights[6] * (128.0F * (float)Math.pow(f1, 8.0D) - (256.0F * (float)Math.pow(f1, 6.0D)) + 160.0F * (float)Math.pow(f1, 4.0D) - (32.0F * (float)Math.pow(f1, 2.0D)) + 1.0F));
/*     */         }
/*     */ 
/* 163 */         if (this.stages > 8) {
/* 164 */           f2 = (float)(f2 + this.weights[7] * (256.0F * (float)Math.pow(f1, 9.0D) - (576.0F * (float)Math.pow(f1, 7.0D)) + 432.0F * (float)Math.pow(f1, 5.0D) - (120.0F * (float)Math.pow(f1, 3.0D)) + 9.0F * f1));
/*     */         }
/*     */ 
/* 170 */         if (this.stages > 9) {
/* 171 */           f2 = (float)(f2 + this.weights[8] * (512.0F * (float)Math.pow(f1, 10.0D) - (1280.0F * (float)Math.pow(f1, 8.0D)) + 1120.0F * (float)Math.pow(f1, 6.0D) - (400.0F * (float)Math.pow(f1, 4.0D)) + 50.0F * (float)Math.pow(f1, 2.0D) - 1.0F));
/*     */         }
/*     */ 
/* 177 */         if (paramArrayOfFloat[j] < 0.0D) f2 *= -1.0F;
/* 178 */         paramArrayOfFloat[j] = f2;
/*     */       }
/*     */     }
/* 181 */     return i;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Waveshaper
 * JD-Core Version:    0.5.3
 */