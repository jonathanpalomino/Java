/*    */ package jm.audio.synth;
/*    */ 
/*    */ public final class EnvPoint
/*    */ {
/*    */   public float y;
/* 35 */   public float x = -1.0F;
/*    */ 
/* 37 */   public int X = -1;
/*    */ 
/*    */   public EnvPoint(float paramFloat1, float paramFloat2) {
/* 40 */     this.y = paramFloat2;
/* 41 */     this.x = paramFloat1;
/*    */   }
/*    */ 
/*    */   public EnvPoint(int paramInt, float paramFloat) {
/* 45 */     this.y = paramFloat;
/* 46 */     this.X = paramInt;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.EnvPoint
 * JD-Core Version:    0.5.3
 */