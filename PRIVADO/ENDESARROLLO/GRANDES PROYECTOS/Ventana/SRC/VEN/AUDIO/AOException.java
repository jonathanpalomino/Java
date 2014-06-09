/*    */ package ven.audio;
/*    */ 
/*    */ public final class AOException extends Exception
/*    */ {
/* 33 */   private static String[] MESSAGES = new String[2];
/*    */ 
/*    */   public AOException(String paramString1, String paramString2)
/*    */   {
/* 46 */     super(paramString1 + paramString2);
/*    */   }
/*    */ 
/*    */   public AOException(String paramString, int paramInt)
/*    */   {
/* 56 */     super(paramString + MESSAGES[paramInt]);
/*    */   }
/*    */ 
/*    */   static
/*    */   {
/* 35 */     MESSAGES[0] = "Unbalanced number of returned samples from multiple inputs.";
/*    */ 
/* 37 */     MESSAGES[1] = "Wrong number of inputs for this AudioObject.";
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.AOException
 * JD-Core Version:    0.5.3
 */