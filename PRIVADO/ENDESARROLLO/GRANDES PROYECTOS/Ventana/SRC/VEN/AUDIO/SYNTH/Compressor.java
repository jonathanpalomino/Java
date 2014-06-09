/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Compressor extends AudioObject
/*     */ {
/*     */   private float threshold;
/*     */   private double ratio;
/*     */   private float gainReduction;
/*     */   private float gain;
/*     */ 
/*     */   public Compressor(AudioObject paramAudioObject)
/*     */   {
/*  72 */     this(paramAudioObject, 0.5D);
/*     */   }
/*     */ 
/*     */   public Compressor(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/*  83 */     this(paramAudioObject, paramDouble, 2.0D);
/*     */   }
/*     */ 
/*     */   public Compressor(AudioObject paramAudioObject, double paramDouble1, double paramDouble2)
/*     */   {
/*  94 */     this(paramAudioObject, paramDouble1, paramDouble2, 1.5D);
/*     */   }
/*     */ 
/*     */   public Compressor(AudioObject paramAudioObject, double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 106 */     super(paramAudioObject, "[Compressor]");
/*     */ 
/*  50 */     this.threshold = 1.0F;
/*     */ 
/*  55 */     this.ratio = 1.0D;
/*     */ 
/*  58 */     this.gainReduction = 1.0F;
/*     */ 
/*  60 */     this.gain = 1.0F;
/*     */ 
/* 107 */     this.threshold = (float)paramDouble1;
/* 108 */     this.ratio = paramDouble2;
/* 109 */     this.gain = (float)paramDouble3;
/* 110 */     calcGainReduction();
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void calcGainReduction()
/*     */   {
/* 129 */     if (this.ratio == 1.0D) {
/* 130 */       this.gainReduction = 1.0F;
/*     */     }
/* 132 */     else if (this.ratio > 1.0D) {
/* 133 */       this.gainReduction = (float)Math.min(1.0D, Math.abs(Math.log(1.0D - (1.0D / this.ratio)) * 0.2D));
/*     */     }
/* 135 */     else if (this.ratio > 0.0D) {
/* 136 */       this.gainReduction = (float)(1.0D / this.ratio);
/*     */     }
/*     */     else {
/* 139 */       System.out.println("jMusic error: Compressor ratio values cannot be less than 0.0");
/* 140 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 153 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 154 */     for (int j = 0; j < i; ++j)
/*     */     {
/* 156 */       if (paramArrayOfFloat[j] > this.threshold) {
/* 157 */         paramArrayOfFloat[j] = (this.threshold + (paramArrayOfFloat[j] - this.threshold) * this.gainReduction);
/*     */       }
/*     */ 
/* 160 */       if (paramArrayOfFloat[j] < -this.threshold) {
/* 161 */         paramArrayOfFloat[j] = (-this.threshold + (paramArrayOfFloat[j] + this.threshold) * this.gainReduction);
/*     */       }
/* 163 */       paramArrayOfFloat[j] *= this.gain;
/*     */     }
/* 165 */     return i;
/*     */   }
/*     */ 
/*     */   public void setThreshold(double paramDouble)
/*     */   {
/* 173 */     this.threshold = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public void setRatio(double paramDouble)
/*     */   {
/* 181 */     this.ratio = paramDouble;
/* 182 */     calcGainReduction();
/*     */   }
/*     */ 
/*     */   public void setGain(double paramDouble)
/*     */   {
/* 191 */     this.gain = (float)paramDouble;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Compressor
 * JD-Core Version:    0.5.3
 */