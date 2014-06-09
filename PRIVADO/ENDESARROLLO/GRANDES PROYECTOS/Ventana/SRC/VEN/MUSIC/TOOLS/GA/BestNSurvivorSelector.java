/*    */ package jm.music.tools.ga;
/*    */ 
/*    */ import jm.music.data.Phrase;
/*    */ 
/*    */ public class BestNSurvivorSelector extends SurvivorSelector
/*    */ {
/*    */   public Phrase[] selectSurvivors(Phrase[] paramArrayOfPhrase1, double[] paramArrayOfDouble1, Phrase[] paramArrayOfPhrase2, double[] paramArrayOfDouble2)
/*    */   {
/* 38 */     Phrase[] arrayOfPhrase = new Phrase[paramArrayOfPhrase1.length];
/* 39 */     double[] arrayOfDouble = new double[paramArrayOfDouble1.length + paramArrayOfDouble2.length];
/* 40 */     for (int i = 0; i < paramArrayOfDouble1.length; ++i) {
/* 41 */       arrayOfDouble[i] = paramArrayOfDouble1[i];
/*    */     }
/* 43 */     for (i = paramArrayOfDouble1.length; i < arrayOfDouble.length; ++i) {
/* 44 */       arrayOfDouble[i] = paramArrayOfDouble2[(i - paramArrayOfDouble1.length)];
/*    */     }
/* 46 */     int[] arrayOfInt = new int[arrayOfPhrase.length];
/*    */ 
/* 49 */     boolean[] arrayOfBoolean = new boolean[arrayOfDouble.length];
/* 50 */     for (int l = 0; l < arrayOfInt.length; ++l) {
/* 51 */       int j = arrayOfDouble.length - 1;
/* 52 */       for (int i1 = arrayOfDouble.length - 1; i1 >= 0; --i1) {
/* 53 */         if (arrayOfBoolean[i1] == 0) {
/* 54 */           j = i1;
/*    */         }
/*    */       }
/*    */ 
/* 58 */       for (i1 = 0; i1 < arrayOfDouble.length; ++i1) {
/* 59 */         int k = 1;
/* 60 */         if (arrayOfDouble[i1] > arrayOfDouble[j]) {
/* 61 */           for (int i2 = 0; i2 < l; ++i2) {
/* 62 */             if (i1 == arrayOfInt[i2]) {
/* 63 */               k = 0;
/*    */             }
/*    */           }
/* 66 */           if (k == 1) {
/* 67 */             j = i1;
/*    */           }
/*    */         }
/*    */       }
/* 71 */       arrayOfInt[l] = j;
/* 72 */       arrayOfBoolean[j] = true;
/*    */     }
/*    */ 
/* 75 */     for (l = 0; l < arrayOfInt.length; ++l) {
/* 76 */       if (arrayOfInt[l] < paramArrayOfPhrase1.length)
/* 77 */         arrayOfPhrase[l] = paramArrayOfPhrase1[arrayOfInt[l]];
/*    */       else {
/* 79 */         arrayOfPhrase[l] = paramArrayOfPhrase2[(arrayOfInt[l] - paramArrayOfPhrase1.length)];
/*    */       }
/*    */     }
/* 82 */     return arrayOfPhrase;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.BestNSurvivorSelector
 * JD-Core Version:    0.5.3
 */