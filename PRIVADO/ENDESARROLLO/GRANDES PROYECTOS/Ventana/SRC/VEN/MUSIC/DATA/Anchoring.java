/*    */ package ven.music.data;
/*    */ 
/*    */ public class Anchoring
/*    */ {
/*    */   final Phrase anchor;
/*    */   final Alignment alignment;
/*    */   final double offset;
/*    */ 
/*    */   Anchoring(Phrase paramPhrase, Alignment paramAlignment, double paramDouble)
/*    */   {
/* 37 */     this.anchor = paramPhrase;
/* 38 */     this.alignment = paramAlignment;
/* 39 */     this.offset = paramDouble;
/*    */   }
/*    */ 
/*    */   public final Phrase getAnchor() {
/* 43 */     return this.anchor;
/*    */   }
/*    */ 
/*    */   public final Alignment getAlignment() {
/* 47 */     return this.alignment;
/*    */   }
/*    */ 
/*    */   public final double getOffset() {
/* 51 */     return this.offset;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Anchoring
 * JD-Core Version:    0.5.3
 */