/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class NoteBuffer extends AudioObject
/*    */ {
/*    */   private float[] noteBuffer;
/* 43 */   private boolean flag = true;
/*    */   private int noteBufferPosition;
/*    */ 
/*    */   public NoteBuffer(AudioObject paramAudioObject)
/*    */   {
/* 50 */     super(paramAudioObject, "[Volume]");
/*    */   }
/*    */ 
/*    */   public void build()
/*    */   {
/* 57 */     this.noteBuffer = new float[this.numOfSamples];
/* 58 */     this.noteBufferPosition = 0;
/* 59 */     this.flag = true;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 66 */     if (this.flag) {
/* 67 */       i = this.previous[0].nextWork(this.noteBuffer);
/* 68 */       this.flag = false;
/*    */     }
/* 70 */     int i = 0;
/* 71 */     int j = (this.noteBufferPosition + paramArrayOfFloat.length < this.numOfSamples) ? this.numOfSamples - this.noteBufferPosition : paramArrayOfFloat.length;
/*    */ 
/* 73 */     for (; i < j; ++i) {
/* 74 */       paramArrayOfFloat[i] = this.noteBuffer[(this.noteBufferPosition++)];
/*    */     }
/* 76 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.NoteBuffer
 * JD-Core Version:    0.5.3
 */