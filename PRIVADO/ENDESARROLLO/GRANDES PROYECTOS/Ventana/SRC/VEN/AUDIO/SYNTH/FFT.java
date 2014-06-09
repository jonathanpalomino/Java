/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ import jm.audio.Instrument;
/*    */ import jm.audio.math.RealFloatFFT_Radix2;
/*    */ 
/*    */ public final class FFT extends AudioObject
/*    */ {
/*    */   public FFT(AudioObject paramAudioObject)
/*    */   {
/* 46 */     super(paramAudioObject, "[FFT]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 56 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 57 */     RealFloatFFT_Radix2 localRealFloatFFT_Radix2 = null;
/* 58 */     localRealFloatFFT_Radix2 = new RealFloatFFT_Radix2(this.inst.getBufSize());
/* 59 */     localRealFloatFFT_Radix2.transform(paramArrayOfFloat);
/* 60 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.FFT
 * JD-Core Version:    0.5.3
 */