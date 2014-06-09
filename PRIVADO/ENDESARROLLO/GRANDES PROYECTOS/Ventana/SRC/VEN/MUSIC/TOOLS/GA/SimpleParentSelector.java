/*    */ package jm.music.tools.ga;
/*    */ 
/*    */ import jm.music.data.Phrase;
/*    */ 
/*    */ public class SimpleParentSelector extends ParentSelector
/*    */ {
/*    */   public Phrase[] selectParents(Phrase[] paramArrayOfPhrase, double[] paramArrayOfDouble)
/*    */   {
/* 37 */     Phrase[] arrayOfPhrase = new Phrase[paramArrayOfPhrase.length];
/* 38 */     for (int i = 0; i < paramArrayOfPhrase.length; ++i) {
/* 39 */       arrayOfPhrase[i] = paramArrayOfPhrase[i].copy();
/*    */     }
/* 41 */     return arrayOfPhrase;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.SimpleParentSelector
 * JD-Core Version:    0.5.3
 */