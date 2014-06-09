/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class NoteBufferReversed extends AudioObject
/*    */ {
/*    */   private float[] noteBuffer;
/* 44 */   private boolean flag = true;
/*    */   private int noteBufferPosition;
/*    */ 
/*    */   public NoteBufferReversed(AudioObject paramAudioObject)
/*    */   {
/* 51 */     super(paramAudioObject, "[Volume]");
/*    */   }
/*    */ 
/*    */   public void build()
/*    */   {
/* 58 */     this.noteBuffer = new float[this.numOfSamples];
/* 59 */     this.noteBufferPosition = this.numOfSamples;
/* 60 */     this.flag = true;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 67 */     if (this.flag) {
/* 68 */       i = this.previous[0].nextWork(this.noteBuffer);
/* 69 */       this.flag = false;
/*    */     }
/* 71 */     int i = 0;
/* 72 */     int j = (this.noteBufferPosition < paramArrayOfFloat.length) ? this.noteBufferPosition : paramArrayOfFloat.length;
/*    */ 
/* 74 */     for (; i < j; ++i) {
/* 75 */       paramArrayOfFloat[i] = this.noteBuffer[(--this.noteBufferPosition)];
/*    */     }
/* 77 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.NoteBufferReversed
 * JD-Core Version:    0.5.3
 */