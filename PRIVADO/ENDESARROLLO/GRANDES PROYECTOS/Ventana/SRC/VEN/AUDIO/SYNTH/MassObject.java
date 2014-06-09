/*    */ package jm.audio.synth;
/*    */ 
/*    */ public class MassObject
/*    */ {
/*    */   private double massSize;
/*    */   private double friction;
/*    */   private double inertia;
/*    */   private double deltaTime;
/*    */   private double yPosition;
/*    */ 
/*    */   public MassObject()
/*    */   {
/* 48 */     this(1.0D);
/*    */   }
/*    */ 
/*    */   public MassObject(double paramDouble) {
/* 52 */     this(paramDouble, 1.0D);
/*    */   }
/*    */ 
/*    */   public MassObject(double paramDouble1, double paramDouble2)
/*    */   {
/* 34 */     this.massSize = 1.0D;
/*    */ 
/* 36 */     this.friction = 3.E-006D;
/*    */ 
/* 38 */     this.inertia = 0.0D;
/*    */ 
/* 40 */     this.deltaTime = 1.0D;
/*    */ 
/* 56 */     this.massSize = paramDouble2;
/* 57 */     this.friction = paramDouble1;
/*    */   }
/*    */ 
/*    */   public void setYPosition(double paramDouble)
/*    */   {
/* 62 */     this.yPosition = paramDouble;
/*    */   }
/*    */ 
/*    */   public double getYPosition()
/*    */   {
/* 67 */     return this.yPosition;
/*    */   }
/*    */ 
/*    */   public double getDisplacement(double paramDouble) {
/* 71 */     paramDouble += this.inertia;
/* 72 */     if (((this.inertia < 0.0D) && (this.friction > 0.0D)) || ((this.inertia > 0.0D) && (this.friction < 0.0D))) {
/* 73 */       this.friction *= -1.0D;
/*    */     }
/* 75 */     if (Math.abs(this.friction) > Math.abs(paramDouble))
/* 76 */       this.inertia = 0.0D;
/*    */     else this.inertia = (paramDouble - this.friction);
/* 78 */     double d1 = paramDouble / this.massSize;
/* 79 */     double d2 = d1 / this.deltaTime * this.deltaTime;
/*    */ 
/* 81 */     return d2;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.MassObject
 * JD-Core Version:    0.5.3
 */