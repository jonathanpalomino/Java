/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Comb extends AudioObject
/*    */ {
/*    */   private float decay;
/*    */   private int delay;
/*    */   private float[] delayLine;
/*    */   private int delayIndex;
/*    */ 
/*    */   public Comb(AudioObject paramAudioObject, int paramInt)
/*    */   {
/* 57 */     this(paramAudioObject, paramInt, 0.5D);
/*    */   }
/*    */ 
/*    */   public Comb(AudioObject paramAudioObject, int paramInt, double paramDouble)
/*    */   {
/* 65 */     super(paramAudioObject, "[Comb]");
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
/* 80 */       float f = this.delayLine[this.delayIndex];
/* 81 */       paramArrayOfFloat[j] += f * this.decay;
/* 82 */       this.delayLine[(this.delayIndex++)] = paramArrayOfFloat[j];
/* 83 */       if (this.delayIndex >= this.delayLine.length) {
/* 84 */         this.delayIndex = 0;
/*    */       }
/*    */     }
/* 87 */     return j;
/*    */   }
/*    */ 
/*    */   public void build()
/*    */   {
/* 94 */     int i = (int)(this.delay / 1000.0F * this.sampleRate);
/* 95 */     this.delayLine = new float[i * this.channels];
/* 96 */     this.delayIndex = 0;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Comb
 * JD-Core Version:    0.5.3
 */