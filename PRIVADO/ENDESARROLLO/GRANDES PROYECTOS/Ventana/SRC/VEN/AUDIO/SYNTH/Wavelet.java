/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Wavelet extends AudioObject
/*    */ {
/*    */   public Wavelet(AudioObject paramAudioObject)
/*    */   {
/* 47 */     super(paramAudioObject, "[FGT]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 59 */     return paramArrayOfFloat.length;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Wavelet
 * JD-Core Version:    0.5.3
 */