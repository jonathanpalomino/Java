/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ 
/*     */ public final class TapDelay extends AudioObject
/*     */ {
/*     */   private float decay;
/*     */   private int delay;
/*     */   private float[] delayLine;
/*     */   private int delayIndex;
/*     */   private int taps;
/*     */   private int sampleDelay;
/*     */ 
/*     */   public TapDelay(AudioObject paramAudioObject, int paramInt1, int paramInt2)
/*     */   {
/*  63 */     this(paramAudioObject, paramInt1, paramInt2, 0.5D);
/*     */   }
/*     */ 
/*     */   public TapDelay(AudioObject paramAudioObject, int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/*  71 */     super(paramAudioObject, "[Tap Delay]");
/*  72 */     this.finished = false;
/*  73 */     this.decay = (float)paramDouble;
/*  74 */     this.delay = paramInt1;
/*  75 */     this.taps = paramInt2;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  85 */     int i = paramArrayOfFloat.length;
/*  86 */     if ((!(this.inst.finishedNewData)) && (this.inst.getFinished())) i = this.previous[0].nextWork(paramArrayOfFloat);
/*  87 */     int j = 0;
/*  88 */     float f = 0.0F;
/*  89 */     for (; j < i; ++j) {
/*  90 */       for (int k = 1; k <= this.taps; ++k) {
/*  91 */         int l = this.delayIndex + this.sampleDelay * this.channels * k;
/*  92 */         if (l >= this.delayLine.length) l -= this.delayLine.length;
/*  93 */         this.delayLine[l] += paramArrayOfFloat[j] * this.decay / k;
/*     */       }
/*  95 */       paramArrayOfFloat[j] += this.delayLine[this.delayIndex];
/*  96 */       this.delayLine[this.delayIndex] = 0.0F;
/*  97 */       this.delayIndex += 1;
/*  98 */       if (this.delayIndex >= this.delayLine.length) {
/*  99 */         this.delayIndex = 0;
/*     */       }
/* 101 */       if (f >= paramArrayOfFloat[j]) continue; f = paramArrayOfFloat[j];
/*     */     }
/* 103 */     if (this.inst.iterations <= 0 - this.delayLine.length) {
/* 104 */       this.finished = true;
/*     */     }
/* 106 */     return j;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 113 */     if (this.delayLine == null) {
/* 114 */       this.sampleDelay = (int)(this.delay / 1000.0F * this.sampleRate);
/* 115 */       this.delayLine = new float[this.sampleDelay * this.channels * this.taps];
/* 116 */       this.delayIndex = 0;
/*     */     }
/* 118 */     this.finished = false;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.TapDelay
 * JD-Core Version:    0.5.3
 */