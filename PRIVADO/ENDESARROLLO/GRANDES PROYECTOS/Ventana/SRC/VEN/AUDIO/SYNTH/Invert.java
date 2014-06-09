/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Invert extends AudioObject
/*    */ {
/*    */   public Invert(AudioObject paramAudioObject)
/*    */   {
/* 49 */     super(paramAudioObject, "[Invert]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 60 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 61 */     for (int j = 0; j < i; ++j) {
/* 62 */       paramArrayOfFloat[j] *= -1.0F;
/*    */     }
/* 64 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Invert
 * JD-Core Version:    0.5.3
 */