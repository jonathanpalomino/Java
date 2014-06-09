/*    */ package jm.audio.synth;
/*    */ 
/*    */ public class SpringPipe
/*    */ {
/* 10 */   int totalLength = 500;
/*    */ 
/* 12 */   double width = 1.0D;
/*    */ 
/* 14 */   double pluckAmt = 1.0D;
/*    */   private SpringObject[] springObjectArray;
/*    */   private MassObject[] massObjectArray;
/*    */ 
/*    */   public SpringPipe(int paramInt, double paramDouble1, double paramDouble2, double paramDouble3)
/*    */   {
/* 22 */     SpringObject[] arrayOfSpringObject = new SpringObject[paramInt + 1];
/* 23 */     MassObject[] arrayOfMassObject = new MassObject[paramInt];
/* 24 */     int i = (int)(this.totalLength - (paramInt * this.width)) / (paramInt + 1);
/* 25 */     for (int j = 0; j < paramInt; ++j) {
/* 26 */       arrayOfSpringObject[j] = new SpringObject(paramDouble1);
/* 27 */       arrayOfSpringObject[j].setRestingLength(i);
/* 28 */       arrayOfMassObject[j] = new MassObject(paramDouble2, 1.0D + Math.random() * paramDouble3 - (paramDouble3 / 2.0D));
/* 29 */       arrayOfMassObject[j].setYPosition(i * (j + 1.0D) + this.width * j);
/*    */     }
/* 31 */     arrayOfSpringObject[paramInt] = new SpringObject();
/* 32 */     arrayOfSpringObject[paramInt].setRestingLength(i);
/*    */ 
/* 34 */     arrayOfMassObject[0].setYPosition(arrayOfMassObject[0].getYPosition() - (arrayOfMassObject[0].getYPosition() * this.pluckAmt));
/*    */ 
/* 36 */     this.springObjectArray = arrayOfSpringObject;
/* 37 */     this.massObjectArray = arrayOfMassObject;
/*    */   }
/*    */ 
/*    */   private void updateSpringMassNetwork()
/*    */   {
/* 43 */     double[] arrayOfDouble = new double[this.springObjectArray.length];
/* 44 */     arrayOfDouble[0] = this.springObjectArray[0].getCurrentForce(0.0D, this.massObjectArray[0].getYPosition());
/* 45 */     for (int i = 1; i < this.massObjectArray.length; ++i) {
/* 46 */       arrayOfDouble[i] = this.springObjectArray[i].getCurrentForce(this.massObjectArray[(i - 1)].getYPosition() + this.width, this.massObjectArray[i].getYPosition());
/*    */     }
/*    */ 
/* 49 */     arrayOfDouble[(arrayOfDouble.length - 1)] = this.springObjectArray[(arrayOfDouble.length - 1)].getCurrentForce(this.massObjectArray[(this.massObjectArray.length - 1)].getYPosition() + this.width, this.totalLength);
/*    */ 
/* 53 */     for (i = 0; i < this.massObjectArray.length; ++i)
/* 54 */       this.massObjectArray[i].setYPosition(this.massObjectArray[i].getYPosition() + this.massObjectArray[i].getDisplacement(arrayOfDouble[i] - arrayOfDouble[(i + 1)]));
/*    */   }
/*    */ 
/*    */   public double getNextNodePosition(int paramInt)
/*    */   {
/* 61 */     updateSpringMassNetwork();
/* 62 */     return this.massObjectArray[paramInt].getYPosition();
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.SpringPipe
 * JD-Core Version:    0.5.3
 */