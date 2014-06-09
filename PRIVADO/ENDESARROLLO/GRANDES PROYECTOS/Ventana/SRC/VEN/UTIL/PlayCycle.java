/*    */ package jm.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import jm.midi.MidiSynth;
/*    */ import jm.music.data.Score;
/*    */ 
/*    */ public class PlayCycle extends Thread
/*    */ {
/*    */   private Score s;
/*    */   private MidiSynth ms;
/*    */   private PlayThread pt;
/*    */ 
/*    */   public PlayCycle(Score paramScore)
/*    */   {
/* 49 */     this.s = paramScore;
/*    */   }
/*    */ 
/*    */   public void run()
/*    */   {
/* 58 */     System.out.println("Cycle-playing " + this.s.getTitle());
/* 59 */     while (Play.cycleIsPlaying())
/*    */     {
/* 61 */       Score localScore = this.s.copy();
/* 62 */       this.pt = new PlayThread(localScore);
/* 63 */       new Thread(this.pt).start();
/*    */ 
/* 65 */       Play.waitCycle(localScore);
/*    */     }
/* 67 */     System.out.println("Stopping");
/* 68 */     Play.stopCycle();
/*    */   }
/*    */ 
/*    */   public void stopPlayCycle() {
/* 72 */     this.pt.stopPlayThread();
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.PlayCycle
 * JD-Core Version:    0.5.3
 */