/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Add extends AudioObject
/*    */ {
/*    */   public Add(AudioObject[] paramArrayOfAudioObject)
/*    */   {
/* 47 */     super(paramArrayOfAudioObject, "[Add]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 60 */     float[][] arrayOfFloat = new float[this.inputs][];
/* 61 */     arrayOfFloat[0] = new float[paramArrayOfFloat.length];
/* 62 */     int i = this.previous[0].nextWork(arrayOfFloat[0]);
/* 63 */     for (int j = 1; j < this.inputs; ++j) {
/* 64 */       arrayOfFloat[j] = new float[i];
/* 65 */       if (i != this.previous[j].nextWork(arrayOfFloat[j])) {
/* 66 */         throw new AOException(this.name, 0);
/*    */       }
/*    */     }
/* 69 */     j = 0;
/* 70 */     for (; j < i; ++j) {
/* 71 */       for (int k = 0; k < this.inputs; ++k) {
/* 72 */         paramArrayOfFloat[j] += arrayOfFloat[k][j];
/*    */       }
/*    */     }
/* 75 */     return j;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Add
 * JD-Core Version:    0.5.3
 */