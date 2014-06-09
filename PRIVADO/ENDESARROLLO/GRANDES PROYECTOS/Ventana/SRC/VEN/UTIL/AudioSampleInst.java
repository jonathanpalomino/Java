/*    */ package jm.util;
/*    */ 
/*    */ import jm.audio.Instrument;
/*    */ import jm.audio.io.SampleIn;
/*    */ 
/*    */ public final class AudioSampleInst extends Instrument
/*    */ {
/*    */   private String fileName;
/*    */ 
/*    */   public AudioSampleInst(String paramString)
/*    */   {
/* 38 */     this.fileName = paramString;
/*    */   }
/*    */ 
/*    */   public void createChain() {
/* 42 */     SampleIn localSampleIn = new SampleIn(this, this.fileName, true, true);
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.AudioSampleInst
 * JD-Core Version:    0.5.3
 */