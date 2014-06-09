/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ import jm.audio.math.RealFloatFFT_Radix2;
/*    */ 
/*    */ public final class InverseFFT extends AudioObject
/*    */ {
/*    */   public InverseFFT(AudioObject paramAudioObject)
/*    */   {
/* 46 */     super(paramAudioObject, "[InverseFFT]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 56 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 57 */     RealFloatFFT_Radix2 localRealFloatFFT_Radix2 = new RealFloatFFT_Radix2(i);
/* 58 */     localRealFloatFFT_Radix2.inverse(paramArrayOfFloat);
/* 59 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.InverseFFT
 * JD-Core Version:    0.5.3
 */