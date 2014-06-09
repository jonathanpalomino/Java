/*    */ package jm.util;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FilenameFilter;
/*    */ 
/*    */ class ReadFilenameFilter
/*    */   implements FilenameFilter
/*    */ {
/*    */   public boolean accept(File paramFile, String paramString)
/*    */   {
/* 50 */     return ((!(paramString.startsWith("."))) && (((paramString.endsWith(".mid")) || (paramString.endsWith(".midi")) || (paramString.endsWith(".jm")))));
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadFilenameFilter
 * JD-Core Version:    0.5.3
 */