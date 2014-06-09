/*    */ package jm.music.tools.ga;
/*    */ 
/*    */ import jm.music.data.Phrase;
/*    */ 
/*    */ public class NoTerminationCriteria extends TerminationCriteria
/*    */ {
/*    */   public boolean isFinished(Phrase[] paramArrayOfPhrase)
/*    */   {
/* 34 */     return false;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.NoTerminationCriteria
 * JD-Core Version:    0.5.3
 */