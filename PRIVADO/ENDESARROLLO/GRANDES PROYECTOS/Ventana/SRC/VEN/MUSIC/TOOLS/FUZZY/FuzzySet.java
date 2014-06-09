/*    */ package jm.music.tools.fuzzy;
/*    */ 
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class FuzzySet
/*    */ {
/*    */   private Vector numberList;
/*    */   private double productSum;
/*    */   private double memberSum;
/*    */ 
/*    */   public FuzzySet()
/*    */   {
/* 41 */     this.numberList = new Vector();
/*    */   }
/*    */ 
/*    */   public void add(FuzzyNumber paramFuzzyNumber)
/*    */   {
/* 49 */     this.numberList.addElement(paramFuzzyNumber);
/*    */   }
/*    */ 
/*    */   public void remove(FuzzyNumber paramFuzzyNumber)
/*    */   {
/* 57 */     this.numberList.removeElement(paramFuzzyNumber);
/*    */   }
/*    */ 
/*    */   public double getOutput(double paramDouble)
/*    */   {
/* 66 */     this.productSum = 0.0D;
/* 67 */     this.memberSum = 0.0D;
/*    */ 
/* 69 */     for (Enumeration localEnumeration = this.numberList.elements(); localEnumeration.hasMoreElements(); ) {
/* 70 */       FuzzyNumber localFuzzyNumber = (FuzzyNumber)localEnumeration.nextElement();
/* 71 */       this.productSum += localFuzzyNumber.getPeak() * localFuzzyNumber.getMembership(paramDouble);
/* 72 */       this.memberSum += localFuzzyNumber.getMembership(paramDouble);
/*    */     }
/*    */ 
/* 75 */     return (this.productSum / this.memberSum);
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.fuzzy.FuzzySet
 * JD-Core Version:    0.5.3
 */