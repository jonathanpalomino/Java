/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Splitter extends AudioObject
/*    */ {
/* 43 */   float[] buf = null;
/*    */ 
/* 45 */   int count = 0;
/*    */ 
/* 47 */   int outputs = 0;
/*    */ 
/*    */   public Splitter(AudioObject paramAudioObject)
/*    */   {
/* 58 */     super(paramAudioObject, "[Volume]");
/*    */   }
/*    */ 
/*    */   public void build()
/*    */   {
/* 68 */     this.outputs = this.next.length;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 77 */     if (this.count == 0) {
/* 78 */       this.buf = new float[paramArrayOfFloat.length];
/* 79 */       this.previous[0].nextWork(this.buf);
/*    */     }
/* 81 */     if (++this.count == this.outputs) this.count = 0;
/* 82 */     for (int i = 0; i < this.buf.length; ++i) {
/* 83 */       paramArrayOfFloat[i] = this.buf[i];
/*    */     }
/* 85 */     return this.buf.length;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Splitter
 * JD-Core Version:    0.5.3
 */