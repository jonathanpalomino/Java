/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class ReSample extends AudioObject
/*     */ {
/*  31 */   private double baseFreq = 0.0D;
/*  32 */   private double newFreq = 0.0D;
/*  33 */   private boolean noteFreq = true;
/*     */ 
/*     */   public ReSample(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/*  39 */     super(paramAudioObject, "[ReSample]");
/*  40 */     this.baseFreq = paramDouble;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/*  49 */     if (this.noteFreq) this.newFreq = FRQ[this.currentNote.getPitch()];
/*  50 */     this.finished = true;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  66 */     double d1 = this.newFreq / this.baseFreq;
/*  67 */     double d2 = -1.0D / (1.0D - d1) / d1;
/*  68 */     double d3 = 0.0D;
/*  69 */     int i = 0;
/*  70 */     if (d2 < 0.0D) { d2 = -1.0D / d2; i = 1; }
/*  71 */     if (d2 == 0.0D) { i = 2; d2 = 1.0D; }
/*  72 */     float[] arrayOfFloat = new float[(int)(paramArrayOfFloat.length * d1 + 0.5D)];
/*  73 */     int j = this.previous[0].nextWork(arrayOfFloat);
/*  74 */     float f1 = 0.0F;
/*  75 */     int k = 0; int l = 0;
/*  76 */     for (int i1 = 0; ; ++i1) {
/*  77 */       if (i == 0) {
/*  78 */         if (++l >= (int)(d2 + d3)) {
/*  79 */           d3 = (d2 + d3) % 1.0D;
/*  80 */           l = 0;
/*  81 */           continue;
/*     */         }
/*  83 */         if (k >= paramArrayOfFloat.length) break;
/*  84 */         paramArrayOfFloat[(k++)] = arrayOfFloat[i1];
/*  85 */       } else if (i == 1) {
/*  86 */         if (d2 + d3 >= 1.0D) {
/*  87 */           float f2 = (arrayOfFloat[i1] - f1) / (float)(d2 + d3);
/*  88 */           for (int i2 = 0; i2 < (int)(d2 + d3); ++i2) {
/*  89 */             paramArrayOfFloat[(k++)] = (f1 + f2 * i2);
/*  90 */             if (k == paramArrayOfFloat.length) break;
/*     */           }
/*     */         }
/*  93 */         if (k == paramArrayOfFloat.length) break;
/*  94 */         paramArrayOfFloat[(k++)] = arrayOfFloat[i1];
/*  95 */         f1 = arrayOfFloat[i1];
/*  96 */         d3 = (d2 + d3) % 1.0D;
/*     */       } else {
/*  98 */         paramArrayOfFloat[(k++)] = arrayOfFloat[i1];
/*     */       }
/* 100 */       if (k == paramArrayOfFloat.length) break;
/*     */     }
/* 102 */     return paramArrayOfFloat.length;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.ReSample
 * JD-Core Version:    0.5.3
 */