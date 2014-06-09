/*    */ package jm.util;
/*    */ 
/*    */ import jm.audio.Instrument;
/*    */ import jm.music.data.Note;
/*    */ import jm.music.rt.RTLine;
/*    */ 
/*    */ public class AudioRTLine extends RTLine
/*    */ {
/* 30 */   private boolean firstTime = true;
/*    */ 
/*    */   public AudioRTLine(String paramString) {
/* 33 */     super(new Instrument[] { new AudioSampleInst(paramString) });
/*    */   }
/*    */ 
/*    */   public synchronized Note getNote()
/*    */   {
/*    */     Note localNote;
/* 39 */     if (this.firstTime) {
/* 40 */       localNote = new Note(67, 1.0D);
/* 41 */       this.firstTime = false; } else {
/* 42 */       localNote = new Note(-2147483648, 1.0D); }
/* 43 */     return localNote;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.AudioRTLine
 * JD-Core Version:    0.5.3
 */