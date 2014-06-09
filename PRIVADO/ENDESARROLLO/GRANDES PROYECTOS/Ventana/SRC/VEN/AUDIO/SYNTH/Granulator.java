/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Granulator extends AudioObject
/*     */ {
/*  42 */   private int grainDuration = 1323;
/*  43 */   private int envelopeType = 1;
/*     */   private int nog;
/*     */   private float cfm;
/*     */   private int cgd;
/*     */   private float[] grain;
/*     */   private float[] newbuf;
/*  49 */   private int grainCnt = 0;
/*  50 */   private int grainsPerSecond = 10;
/*     */   private float[] tailBuf;
/*  52 */   private float freqMod = 1.0F;
/*  53 */   private float[] inBuffer = null;
/*  54 */   private boolean inBufActive = false;
/*  55 */   private boolean ri = false;
/*  56 */   private boolean rgd = false;
/*  57 */   private int rdist = 0;
/*  58 */   private int rdisttemp = 0;
/*  59 */   private int gdb = 1000;
/*  60 */   private int gdt = 1000;
/*  61 */   private boolean rf = false;
/*  62 */   private float rfb = 0.99F;
/*  63 */   private float rft = 1.01F;
/*     */   private float[] durationArray;
/*     */   private float[] gpsArray;
/*     */   private float[] freqArray;
/*  68 */   private boolean premapped = false;
/*     */ 
/*     */   public Granulator(AudioObject paramAudioObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*  76 */     super(paramAudioObject, "[Granulator]");
/*  77 */     this.grainDuration = paramInt3;
/*  78 */     this.grainsPerSecond = paramInt4;
/*  79 */     this.cgd = 0;
/*  80 */     this.grain = new float[this.grainDuration];
/*  81 */     this.tailBuf = new float[0];
/*  82 */     this.sampleRate = paramInt1;
/*  83 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public Granulator(AudioObject paramAudioObject, int paramInt1, int paramInt2, float[] paramArrayOfFloat1, float[] paramArrayOfFloat2, float[] paramArrayOfFloat3, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3)
/*     */   {
/*  88 */     super(paramAudioObject, "[Granulator]");
/*  89 */     this.durationArray = paramArrayOfFloat1;
/*  90 */     this.gpsArray = paramArrayOfFloat2;
/*  91 */     this.freqArray = paramArrayOfFloat3;
/*  92 */     this.grain = new float[(int)this.durationArray[0]];
/*  93 */     this.ri = paramBoolean1;
/*  94 */     this.rgd = paramBoolean2;
/*  95 */     this.rf = paramBoolean3;
/*  96 */     this.tailBuf = new float[0];
/*  97 */     this.rdist = paramInt3;
/*  98 */     this.premapped = true;
/*  99 */     this.sampleRate = paramInt1;
/* 100 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 109 */     if (this.inBuffer == null) {
/* 110 */       this.newbuf = new float[paramArrayOfFloat.length];
/* 111 */       this.previous[0].nextWork(this.newbuf);
/*     */     } else {
/* 113 */       this.newbuf = new float[paramArrayOfFloat.length];
/* 114 */       for (i = 0; (i < this.inBuffer.length) && (i < this.newbuf.length); ++i) {
/* 115 */         this.newbuf[i] = this.inBuffer[i];
/*     */       }
/* 117 */       this.inBuffer = null;
/*     */     }
/*     */ 
/* 120 */     if (this.grainsPerSecond <= 0) this.grainsPerSecond = 1;
/*     */ 
/* 125 */     this.nog = (int)(this.newbuf.length / this.sampleRate * this.channels / this.grainsPerSecond);
/*     */ 
/* 128 */     if (this.nog <= 0) this.nog = 1;
/*     */ 
/* 130 */     int i = this.newbuf.length / this.nog;
/*     */ 
/* 132 */     for (int j = 0; (j < paramArrayOfFloat.length) && (j < this.tailBuf.length); ++j) {
/* 133 */       paramArrayOfFloat[j] += this.tailBuf[j];
/*     */     }
/* 135 */     this.tailBuf = new float[this.newbuf.length];
/* 136 */     this.inBufActive = true;
/*     */ 
/* 138 */     for (j = 0; j < this.nog; ++j) {
/* 139 */       if (this.rdist > 0) this.rdisttemp = (int)(Math.random() * this.rdist);
/*     */       else this.rdisttemp = 0;
/* 141 */       int k = j * i + this.rdisttemp;
/* 142 */       setGrain(k - this.rdisttemp);
/* 143 */       for (int l = 0; l < this.grain.length; ++l) {
/* 144 */         if (k >= paramArrayOfFloat.length)
/* 145 */           this.tailBuf[(k - paramArrayOfFloat.length)] += this.grain[l];
/*     */         else {
/* 147 */           paramArrayOfFloat[k] += this.grain[l];
/*     */         }
/* 149 */         ++k;
/* 150 */         this.grainCnt += 1;
/*     */       }
/*     */     }
/* 153 */     this.inBufActive = false;
/* 154 */     return paramArrayOfFloat.length;
/*     */   }
/*     */ 
/*     */   public void setFreqMod(float paramFloat)
/*     */   {
/* 161 */     this.freqMod = paramFloat;
/*     */   }
/*     */ 
/*     */   public void setGrainDuration(int paramInt)
/*     */   {
/* 168 */     this.grainDuration = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGrainsPerSecond(int paramInt)
/*     */   {
/* 175 */     this.grainsPerSecond = paramInt;
/*     */   }
/*     */ 
/*     */   public void setEnvelopeType(int paramInt)
/*     */   {
/* 182 */     this.envelopeType = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRandomGrainDuration(boolean paramBoolean)
/*     */   {
/* 190 */     this.rgd = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setRandomGrainBottom(int paramInt)
/*     */   {
/* 197 */     this.gdb = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRandomGrainTop(int paramInt)
/*     */   {
/* 204 */     this.gdt = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRandomIndex(boolean paramBoolean)
/*     */   {
/* 211 */     this.ri = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setRandomFreq(boolean paramBoolean)
/*     */   {
/* 218 */     this.rf = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setRandomDist(int paramInt)
/*     */   {
/* 225 */     this.rdist = paramInt;
/*     */   }
/*     */ 
/*     */   public void setRandomFreqBottom(float paramFloat)
/*     */   {
/* 232 */     this.rfb = paramFloat;
/*     */   }
/*     */ 
/*     */   public void setRandomFreqTop(float paramFloat)
/*     */   {
/* 239 */     this.rft = paramFloat;
/*     */   }
/*     */ 
/*     */   private void setGrain(int paramInt)
/*     */     throws AOException
/*     */   {
/* 249 */     if (this.ri) paramInt = (int)(Math.random() * this.newbuf.length);
/* 250 */     float[] arrayOfFloat = this.newbuf;
/* 251 */     if (this.rgd) this.cgd = (this.gdb + (int)(Math.random() * this.gdt));
/*     */     else
/*     */     {
/* 255 */       this.cgd = this.grainDuration;
/*     */     }
/*     */ 
/* 261 */     this.cfm = this.freqMod;
/*     */ 
/* 264 */     if (this.rf) this.cfm = (float)(this.rfb + Math.random() * (this.rft - this.rfb));
/* 265 */     if (this.inBufActive) {
/* 266 */       this.inBuffer = new float[this.newbuf.length];
/* 267 */       i = this.previous[0].nextWork(this.inBuffer);
/* 268 */       this.inBufActive = false;
/*     */     }
/* 270 */     this.grain = new float[this.cgd];
/* 271 */     int i = 0;
/* 272 */     float f1 = 0.0F;
/*     */ 
/* 275 */     double d1 = -1.0D / (1.0F - this.cfm) / this.cfm;
/* 276 */     double d2 = 0.0D;
/* 277 */     int j = 0;
/* 278 */     if (d1 < 0.0D) { d1 = -1.0D / d1; j = 1; }
/* 279 */     if (d1 == 0.0D) j = 2;
/* 280 */     int k = 0;
/*     */ 
/* 282 */     for (int l = paramInt; ; ++l) {
/* 283 */       if (l == arrayOfFloat.length) { l = 0; arrayOfFloat = this.inBuffer; }
/* 284 */       if (j == 0)
/*     */       {
/* 286 */         if (++k >= (int)(d1 + d2)) {
/* 287 */           d2 = (d1 + d2) % 1.0D;
/* 288 */           k = 0;
/* 289 */           continue;
/*     */         }
/* 291 */         if (i >= this.cgd) break;
/* 292 */         this.grain[(i++)] = arrayOfFloat[l];
/* 293 */       } else if (j == 1) {
/* 294 */         if (d1 + d2 >= 1.0D) {
/* 295 */           float f2 = (f1 - arrayOfFloat[l]) / ((int)d1 + 1);
/* 296 */           for (int i1 = 0; i1 < (int)(d1 + d2); ++i1) {
/* 297 */             this.grain[(i++)] = (f2 * i1 + arrayOfFloat[l]);
/* 298 */             if (i == this.cgd) break;
/*     */           }
/*     */         }
/* 301 */         if (i == this.cgd) break;
/* 302 */         this.grain[(i++)] = arrayOfFloat[l];
/* 303 */         f1 = arrayOfFloat[l];
/* 304 */         d2 = (d1 + d2) % 1.0D;
/*     */       } else {
/* 306 */         this.grain[(i++)] = arrayOfFloat[l];
/*     */       }
/* 308 */       if (i == this.cgd) {
/*     */         break;
/*     */       }
/*     */     }
/* 312 */     if (this.envelopeType <= 1) {
/* 313 */       for (l = 0; l < this.cgd; ++l) {
/* 314 */         this.grain[l] *= (float)(0.5D - (0.5D * Math.cos(6.283185307179586D * l / this.cgd)));
/*     */       }
/*     */ 
/*     */     }
/* 324 */     else if (this.envelopeType == 3) {
/* 325 */       for (l = 0; l < this.cgd / 2; ++l) {
/* 326 */         this.grain[l] *= 2.0F;
/*     */       }
/* 328 */       for (l = this.cgd / 2; l < this.cgd; ++l)
/* 329 */         this.grain[l] = (this.grain[l] * -2.0F + 2.0F);
/*     */     }
/*     */     else
/*     */     {
/* 333 */       for (l = 0; l < this.cgd; ++l)
/* 334 */         this.grain[l] *= (float)Math.sin(3.141592653589793D * l / this.cgd);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Granulator
 * JD-Core Version:    0.5.3
 */