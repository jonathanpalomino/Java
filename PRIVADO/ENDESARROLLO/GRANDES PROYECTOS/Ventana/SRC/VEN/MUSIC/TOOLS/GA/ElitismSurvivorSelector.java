/*    */ package jm.music.tools.ga;
/*    */ 
/*    */ import jm.music.data.Phrase;
/*    */ 
/*    */ public class ElitismSurvivorSelector extends SurvivorSelector
/*    */ {
/*    */   private static final int ELITISM_CONSTANT = 2;
/*    */ 
/*    */   public Phrase[] selectSurvivors(Phrase[] paramArrayOfPhrase1, double[] paramArrayOfDouble1, Phrase[] paramArrayOfPhrase2, double[] paramArrayOfDouble2)
/*    */   {
/* 41 */     Phrase[] arrayOfPhrase = new Phrase[paramArrayOfPhrase1.length];
/*    */ 
/* 43 */     int[] arrayOfInt = new int[2];
/*    */ 
/* 45 */     int i = -1;
/*    */ 
/* 47 */     boolean[] arrayOfBoolean = new boolean[paramArrayOfDouble1.length];
/* 48 */     for (int k = 0; k < arrayOfInt.length; ++k) {
/* 49 */       i = paramArrayOfDouble1.length - 1;
/* 50 */       for (int l = paramArrayOfDouble1.length - 1; l >= 0; --l) {
/* 51 */         if (arrayOfBoolean[l] == 0) {
/* 52 */           i = l;
/*    */         }
/*    */       }
/* 55 */       for (l = 0; l < paramArrayOfDouble1.length - 1; ++l) {
/* 56 */         int j = 1;
/* 57 */         if (paramArrayOfDouble1[l] > paramArrayOfDouble1[i]) {
/* 58 */           for (int i1 = 0; i1 < k; ++i1) {
/* 59 */             if (l == arrayOfInt[i1]) {
/* 60 */               j = 0;
/*    */             }
/*    */           }
/* 63 */           if (j == 1) {
/* 64 */             i = l;
/*    */           }
/*    */         }
/* 67 */         arrayOfInt[k] = i;
/* 68 */         arrayOfBoolean[i] = true;
/*    */       }
/*    */ 
/*    */     }
/*    */ 
/* 73 */     for (k = 0; k < arrayOfInt.length; ++k) {
/* 74 */       arrayOfPhrase[k] = paramArrayOfPhrase1[arrayOfInt[k]];
/*    */     }
/*    */ 
/* 78 */     for (k = 0; k < arrayOfPhrase.length - 2; ++k) {
/* 79 */       arrayOfPhrase[(k + 2)] = paramArrayOfPhrase2[k];
/*    */     }
/*    */ 
/* 83 */     return arrayOfPhrase;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.ElitismSurvivorSelector
 * JD-Core Version:    0.5.3
 */