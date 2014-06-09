/*     */ package jm.music.tools;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public final class Prob
/*     */ {
/*  44 */   private static final Random RNG = new Random();
/*     */ 
/*     */   public static final int gaussianPitch(int paramInt1, int paramInt2)
/*     */   {
/*     */     long l;
/*     */     do {
/*  60 */       l = Math.round(RNG.nextGaussian() * paramInt2 + paramInt1);
/*     */     }
/*     */ 
/*  63 */     while ((l < 0L) || (l > 127L));
/*  64 */     return (int)l;
/*     */   }
/*     */ 
/*     */   public static final double gaussianFrequency(double paramDouble1, double paramDouble2)
/*     */   {
/*     */     double d;
/*     */     do {
/*  80 */       d = RNG.nextGaussian() * paramDouble2 + paramDouble1;
/*     */     }
/*  82 */     while (d < 1.E-017D);
/*  83 */     return d;
/*     */   }
/*     */ 
/*     */   public static final double gaussianRhythmValue(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/*     */     double d;
/*     */     do
/*     */     {
/* 109 */       d = RNG.nextGaussian() * paramDouble2 + paramDouble1;
/* 110 */       d /= paramDouble3;
/* 111 */       d = Math.round(d);
/* 112 */       d *= paramDouble3;
/*     */     }
/* 114 */     while ((d < 0.0D) || (d > 1.7976931348623157E+308D));
/* 115 */     return d;
/*     */   }
/*     */ 
/*     */   public static final int gaussianDynamic(int paramInt1, int paramInt2)
/*     */   {
/*     */     long l;
/*     */     do {
/* 133 */       l = Math.round(RNG.nextGaussian() * paramInt2 + paramInt1);
/*     */     }
/*     */ 
/* 136 */     while ((l < 0L) || (l > 127L));
/* 137 */     return (int)l;
/*     */   }
/*     */ 
/*     */   public static final double gaussianPan(double paramDouble1, double paramDouble2)
/*     */   {
/* 156 */     return gaussianPan(paramDouble1, paramDouble2, 1.0D);
/*     */   }
/*     */ 
/*     */   public static final double gaussianPan(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */   {
/* 176 */     paramDouble3 = (paramDouble3 >= 0.0D) ? paramDouble3 : 0.0D;
/*     */     long l;
/*     */     do {
/* 179 */       l = Math.round(RNG.nextGaussian() * paramDouble2 + paramDouble1);
/*     */     }
/* 181 */     while ((l < 0.0D) || (l > paramDouble3));
/* 182 */     return (int)l;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.Prob
 * JD-Core Version:    0.5.3
 */