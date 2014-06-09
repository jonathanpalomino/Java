/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Multiply extends AudioObject
/*    */ {
/*    */   public Multiply(AudioObject[] paramArrayOfAudioObject)
/*    */   {
/* 46 */     super(paramArrayOfAudioObject, "[Multiply]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 59 */     float[][] arrayOfFloat = new float[this.inputs][];
/* 60 */     arrayOfFloat[0] = new float[paramArrayOfFloat.length];
/* 61 */     int i = this.previous[0].nextWork(arrayOfFloat[0]);
/* 62 */     for (int j = 1; j < this.inputs; ++j) {
/* 63 */       arrayOfFloat[j] = new float[i];
/* 64 */       if (i != this.previous[j].nextWork(arrayOfFloat[j])) {
/* 65 */         throw new AOException(this.name, 0);
/*    */       }
/*    */     }
/* 68 */     j = 0;
/* 69 */     for (; j < i; ++j) {
/* 70 */       paramArrayOfFloat[j] = arrayOfFloat[0][j];
/* 71 */       for (int k = 1; k < this.inputs; ++k) {
/* 72 */         paramArrayOfFloat[j] *= arrayOfFloat[k][j];
/*    */       }
/*    */     }
/* 75 */     return j;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Multiply
 * JD-Core Version:    0.5.3
 */