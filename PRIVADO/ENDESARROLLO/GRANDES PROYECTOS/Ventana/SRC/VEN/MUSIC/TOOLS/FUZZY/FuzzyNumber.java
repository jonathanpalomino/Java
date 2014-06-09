/*     */ package jm.music.tools.fuzzy;
/*     */ 
/*     */ public class FuzzyNumber
/*     */ {
/*     */   private double peak;
/*     */   private double min;
/*     */   private double max;
/*     */   private double diff;
/*     */   private double membership;
/*     */ 
/*     */   public FuzzyNumber(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/*  44 */     this.peak = paramDouble1;
/*  45 */     this.min = paramDouble2;
/*  46 */     this.max = paramDouble3;
/*     */   }
/*     */ 
/*     */   public double getMembership(double paramDouble)
/*     */   {
/*  55 */     if ((paramDouble < this.min) || (paramDouble > this.max)) return 0.0D;
/*  56 */     this.diff = (this.peak - paramDouble);
/*  57 */     if (this.diff >= 0.0D) this.membership = (1.0D - (this.diff / (this.peak - this.min)));
/*     */     else this.membership = (1.0D + this.diff / (this.max - this.peak));
/*  59 */     return this.membership;
/*     */   }
/*     */ 
/*     */   public void setPeak(double paramDouble)
/*     */   {
/*  67 */     this.peak = paramDouble;
/*  68 */     if (this.min > paramDouble) this.min = paramDouble;
/*  69 */     if (this.max >= paramDouble) return; this.max = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getPeak()
/*     */   {
/*  77 */     return this.peak;
/*     */   }
/*     */ 
/*     */   public void setMin(double paramDouble)
/*     */   {
/*  85 */     this.min = paramDouble;
/*  86 */     if (this.peak < paramDouble) this.peak = paramDouble;
/*  87 */     if (this.max >= paramDouble) return; this.max = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getMin()
/*     */   {
/*  95 */     return this.min;
/*     */   }
/*     */ 
/*     */   public void setMax(double paramDouble)
/*     */   {
/* 103 */     this.max = paramDouble;
/* 104 */     if (this.min > paramDouble) this.min = paramDouble;
/* 105 */     if (this.peak <= paramDouble) return; this.peak = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getMax()
/*     */   {
/* 113 */     return this.max;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.fuzzy.FuzzyNumber
 * JD-Core Version:    0.5.3
 */