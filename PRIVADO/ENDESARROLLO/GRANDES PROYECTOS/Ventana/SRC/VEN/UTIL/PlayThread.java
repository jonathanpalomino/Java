/*    */ package jm.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import jm.midi.MidiSynth;
/*    */ import jm.music.data.Score;
/*    */ 
/*    */ class PlayThread extends Thread
/*    */ {
/*    */   private Score s;
/*    */   private MidiSynth ms;
/*    */ 
/*    */   public PlayThread(Score paramScore)
/*    */   {
/* 39 */     this.s = paramScore;
/* 40 */     this.ms = new MidiSynth();
/*    */   }
/*    */ 
/*    */   public void run() {
/*    */     try {
/* 45 */       this.ms.play(this.s);
/*    */     }
/*    */     catch (Exception localException) {
/* 48 */       System.err.println("MIDI Playback Error:" + localException);
/* 49 */       return;
/*    */     }
/*    */   }
/*    */ 
/*    */   public void stopPlayThread()
/*    */   {
/* 57 */     this.ms.stop();
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.PlayThread
 * JD-Core Version:    0.5.3
 */