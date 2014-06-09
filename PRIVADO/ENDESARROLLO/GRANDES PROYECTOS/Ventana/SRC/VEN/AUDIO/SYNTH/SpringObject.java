/*    */ package jm.audio.synth;
/*    */ 
/*    */ public class SpringObject
/*    */ {
/*    */   private int restingLength;
/* 35 */   private double k = 0.002D;
/*    */ 
/*    */   public SpringObject() {
/*    */   }
/*    */ 
/*    */   public SpringObject(double paramDouble) {
/* 41 */     this.k = paramDouble;
/*    */   }
/*    */ 
/*    */   public double getCurrentForce(double paramDouble1, double paramDouble2) {
/* 45 */     double d1 = -1.0D * (paramDouble2 - paramDouble1 - this.restingLength);
/* 46 */     double d2 = this.k * d1;
/*    */ 
/* 48 */     return d2;
/*    */   }
/*    */ 
/*    */   public void setRestingLength(int paramInt) {
/* 52 */     this.restingLength = paramInt;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.SpringObject
 * JD-Core Version:    0.5.3
 */