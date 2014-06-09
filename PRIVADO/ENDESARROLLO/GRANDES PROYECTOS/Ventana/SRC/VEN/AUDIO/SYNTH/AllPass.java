/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class AllPass extends AudioObject
/*    */ {
/*    */   private float decay;
/*    */   private int delay;
/*    */   private float[] delayLine;
/*    */   private int delayIndex;
/*    */ 
/*    */   public AllPass(AudioObject paramAudioObject, int paramInt)
/*    */   {
/* 57 */     this(paramAudioObject, paramInt, 0.5D);
/*    */   }
/*    */ 
/*    */   public AllPass(AudioObject paramAudioObject, int paramInt, double paramDouble)
/*    */   {
/* 65 */     super(paramAudioObject, "[AllPass]");
/* 66 */     this.decay = (float)paramDouble;
/* 67 */     this.delay = paramInt;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 77 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 78 */     int j = 0;
/* 79 */     for (; j < i; ++j) {
/* 80 */       paramArrayOfFloat[j] += this.delayLine[this.delayIndex] * this.decay;
/* 81 */       float f1 = paramArrayOfFloat[j] * -this.decay;
/* 82 */       float f2 = this.delayLine[this.delayIndex];
/* 83 */       this.delayLine[this.delayIndex] = paramArrayOfFloat[j];
/* 84 */       paramArrayOfFloat[j] = (f1 + f2);
/* 85 */       if (this.delayIndex >= this.delayLine.length) {
/* 86 */         this.delayIndex = 0;
/*    */       }
/*    */     }
/* 89 */     return j;
/*    */   }
/*    */ 
/*    */   public void build()
/*    */   {
/* 96 */     int i = (int)(this.delay / 1000.0F * this.sampleRate);
/* 97 */     this.delayLine = new float[i * this.channels];
/* 98 */     this.delayIndex = 0;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.AllPass
 * JD-Core Version:    0.5.3
 */