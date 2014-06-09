/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.JMC;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public class ADSR extends AudioObject
/*     */   implements JMC
/*     */ {
/*     */   private EnvPoint[] graphPoints;
/*     */   private float[] graphShape;
/*     */   private boolean primary;
/*     */   private int attack;
/*     */   private int decay;
/*     */   private int release;
/*     */   private double sustain;
/*     */   private int totalSamples;
/*  71 */   private int sampleCounter = 0;
/*     */ 
/*  73 */   private int position = 0;
/*     */   private double attackSamps;
/*     */   private double decaySamps;
/*     */   private double releaseSamps;
/*  77 */   private double prevRV = 0.0D;
/*     */   int maxAttackCount;
/*     */   int maxDecayCount;
/*     */ 
/*     */   public ADSR(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble, int paramInt5)
/*     */   {
/*  99 */     super(paramInstrument, paramInt1, "[ADSR]");
/* 100 */     this.channels = paramInt2;
/* 101 */     this.attack = paramInt3;
/* 102 */     this.decay = paramInt4;
/* 103 */     this.sustain = paramDouble;
/* 104 */     this.release = paramInt5;
/* 105 */     this.primary = true;
/* 106 */     this.finished = false;
/* 107 */     calcSamps();
/*     */   }
/*     */ 
/*     */   public ADSR(AudioObject paramAudioObject, int paramInt1, int paramInt2, double paramDouble, int paramInt3)
/*     */   {
/* 124 */     super(paramAudioObject, "[ADSR]");
/*     */ 
/* 126 */     this.attack = paramInt1;
/* 127 */     this.decay = paramInt2;
/* 128 */     this.sustain = paramDouble;
/* 129 */     this.release = paramInt3;
/* 130 */     this.primary = false;
/* 131 */     this.finished = false;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 150 */     if (this.sampleCounter > this.totalSamples)
/* 151 */       this.finished = true;
/*     */     int k;
/* 156 */     if (this.primary) {
/* 157 */       i = paramArrayOfFloat.length;
/*     */ 
/* 159 */       for (j = 0; j < i; j += this.channels) {
/* 160 */         for (k = 0; k < this.channels; ++k) {
/*     */           try {
/* 162 */             paramArrayOfFloat[(j + k)] = this.graphShape[this.position];
/*     */           }
/*     */           catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {
/* 165 */             paramArrayOfFloat[(j + k)] = 0.0F;
/*     */           }
/*     */         }
/* 168 */         this.position += 1;
/*     */       }
/* 170 */       this.sampleCounter += paramArrayOfFloat.length;
/* 171 */       return i;
/*     */     }
/*     */ 
/* 175 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 176 */     for (int j = 0; j < paramArrayOfFloat.length; j += this.channels) {
/* 177 */       for (k = 0; k < this.channels; ++k) {
/*     */         try {
/* 179 */           if (this.sampleCounter < this.maxAttackCount)
/* 180 */             paramArrayOfFloat[(j + k)] *= (float)(this.sampleCounter * 1.0D / this.maxAttackCount);
/* 181 */           else if (this.sampleCounter < this.maxDecayCount + this.maxAttackCount)
/* 182 */             paramArrayOfFloat[(j + k)] *= (float)(1.0D - ((this.sampleCounter - this.maxAttackCount) * (1.0D - this.sustain) / this.maxDecayCount));
/* 183 */           else if (this.sampleCounter < this.numOfSamples)
/* 184 */             paramArrayOfFloat[(j + k)] *= (float)this.sustain;
/* 185 */           else if (this.sampleCounter < this.totalSamples)
/* 186 */             paramArrayOfFloat[(j + k)] *= (float)(this.sustain - ((this.sampleCounter - this.numOfSamples) * this.sustain / this.releaseSamps));
/*     */           else paramArrayOfFloat[(j + k)] = 0.0F;
/*     */         }
/*     */         catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2) {
/* 190 */           paramArrayOfFloat[(j + k)] = 0.0F;
/*     */         }
/*     */       }
/* 193 */       this.sampleCounter += 1;
/* 194 */       this.position += 1;
/*     */     }
/*     */ 
/* 212 */     return i;
/*     */   }
/*     */ 
/*     */   private void calcSamps()
/*     */   {
/* 222 */     this.attackSamps = getSamps(this.attack);
/* 223 */     this.decaySamps = getSamps(this.decay);
/* 224 */     this.releaseSamps = getSamps(this.release);
/*     */   }
/*     */ 
/*     */   private double getSamps(int paramInt) {
/* 228 */     return (paramInt / 1000.0D * this.sampleRate);
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 236 */     this.sampleCounter = 0;
/* 237 */     this.position = 0;
/* 238 */     if (this.numOfSamples == 0) {
/* 239 */       return;
/*     */     }
/*     */ 
/* 242 */     if (this.currentNote.getRhythmValue() == this.prevRV) return;
/*     */ 
/* 244 */     calcSamps();
/*     */ 
/* 247 */     this.totalSamples = (this.numOfSamples + (int)this.releaseSamps);
/*     */ 
/* 249 */     this.graphShape = new float[this.totalSamples];
/*     */ 
/* 251 */     this.maxAttackCount = Math.min((int)this.attackSamps, this.numOfSamples);
/*     */ 
/* 253 */     double d1 = 1.0D / this.maxAttackCount;
/* 254 */     for (int i = 0; i < this.maxAttackCount; ++i) {
/* 255 */       this.graphShape[i] = (float)(d1 * i);
/*     */     }
/*     */ 
/* 259 */     this.maxDecayCount = this.maxAttackCount;
/* 260 */     if (this.sustain < 1.0D) {
/* 261 */       this.maxDecayCount = Math.min((int)this.attackSamps + (int)this.decaySamps, this.numOfSamples);
/*     */ 
/* 263 */       double d2 = (1.0D - this.sustain) / (this.maxDecayCount - this.maxAttackCount);
/* 264 */       for (int k = this.maxAttackCount; k < this.maxDecayCount; ++k) {
/* 265 */         this.graphShape[k] = (float)(1.0D - (d2 * (k - this.maxAttackCount)));
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 271 */     for (int j = this.maxDecayCount; j < this.numOfSamples; ++j) {
/* 272 */       this.graphShape[j] = (float)this.sustain;
/*     */     }
/*     */ 
/* 277 */     double d3 = this.graphShape[(this.numOfSamples - 1)];
/* 278 */     double d4 = d3 / this.releaseSamps;
/* 279 */     for (int l = this.numOfSamples; l < this.totalSamples; ++l) {
/* 280 */       this.graphShape[l] = (float)(d3 - (d4 * (l - this.numOfSamples)));
/*     */     }
/*     */ 
/* 283 */     this.finished = false;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.ADSR
 * JD-Core Version:    0.5.3
 */