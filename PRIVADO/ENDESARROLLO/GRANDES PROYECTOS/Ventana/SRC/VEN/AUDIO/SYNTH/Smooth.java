/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Smooth extends AudioObject
/*    */ {
/*    */   private float[] prevSampleValues;
/*    */ 
/*    */   public Smooth(AudioObject paramAudioObject)
/*    */   {
/* 51 */     super(paramAudioObject, "[Smooth]");
/*    */   }
/*    */ 
/*    */   public void build() {
/* 55 */     this.prevSampleValues = new float[this.channels];
/* 56 */     for (int i = 0; i < this.prevSampleValues.length; ++i)
/* 57 */       this.prevSampleValues[i] = 0.0F;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 68 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 69 */     for (int j = 0; j < i; j += this.channels) {
/* 70 */       for (int k = 0; k < this.channels; ++k) {
/* 71 */         paramArrayOfFloat[(j + k)] = (paramArrayOfFloat[(j + k)] * 0.5F + this.prevSampleValues[k] * 0.5F);
/* 72 */         this.prevSampleValues[k] = paramArrayOfFloat[(j + k)];
/*    */       }
/*    */     }
/* 75 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Smooth
 * JD-Core Version:    0.5.3
 */