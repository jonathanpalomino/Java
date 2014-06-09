/*      */ package jm.util;
/*      */ 
/*      */ class StandardXMLStyle extends XMLStyle
/*      */ {
/*      */   public final char[] getReferenceChars()
/*      */   {
/* 1769 */     return new char[0];
/*      */   }
/*      */ 
/*      */   public final char[][] getEncodingsOfReferenceChars()
/*      */   {
/* 1774 */     return new char[0][];
/*      */   }
/*      */ 
/*      */   public final char[] getValueReferenceChars()
/*      */   {
/* 1779 */     return new char[] { '<', '>', '"', '&' };
/*      */   }
/*      */ 
/*      */   public final char[][] getEncodingsOfValueReferenceChars()
/*      */   {
/* 1784 */     return new char[][] { { '&', 'l', 't', ';' }, { '&', 'g', 't', ';' }, { '&', 'q', 'u', 'o', 't', ';' }, { '&', 'a', 'm', 'p', ';' } };
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.StandardXMLStyle
 * JD-Core Version:    0.5.3
 */