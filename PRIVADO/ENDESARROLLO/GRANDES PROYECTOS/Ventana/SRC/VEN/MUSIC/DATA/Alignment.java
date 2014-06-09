/*     */ package ven.music.data;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public abstract class Alignment
/*     */   implements Serializable
/*     */ {
/*  31 */   public static final Alignment START_TOGETHER = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  37 */       return paramDouble2;
/*     */     }
/*  31 */   };
/*     */ 
/*  41 */   public static final Alignment END_TOGETHER = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  47 */       return (paramDouble3 - paramDouble1);
/*     */     }
/*  41 */   };
/*     */ 
/*  51 */   public static final Alignment AFTER = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  57 */       return paramDouble3;
/*     */     }
/*  51 */   };
/*     */ 
/*  61 */   public static final Alignment BEFORE = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  67 */       return (paramDouble2 - paramDouble1);
/*     */     }
/*  61 */   };
/*     */ 
/*  71 */   public static final Alignment CENTRE_ALIGN = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  77 */       return ((paramDouble3 + paramDouble2 - paramDouble1) / 2.0D);
/*     */     }
/*  71 */   };
/*     */ 
/*  82 */   public static final Alignment CENTER_ALIGN = CENTRE_ALIGN;
/*     */ 
/*  84 */   public static final Alignment START_ON_CENTRE = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/*  90 */       return ((paramDouble2 + paramDouble3) / 2.0D);
/*     */     }
/*  84 */   };
/*     */ 
/*  95 */   public static final Alignment START_ON_CENTER = START_ON_CENTRE;
/*     */ 
/*  97 */   public static final Alignment END_ON_CENTRE = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/* 103 */       return ((paramDouble2 + paramDouble3) / 2.0D - paramDouble1);
/*     */     }
/*  97 */   };
/*     */ 
/* 108 */   public static final Alignment END_ON_CENTER = END_ON_CENTRE;
/*     */ 
/* 110 */   public static final Alignment CENTRE_ON_START = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/* 116 */       return (paramDouble2 - (paramDouble1 / 2.0D));
/*     */     }
/* 110 */   };
/*     */ 
/* 120 */   public static final Alignment CENTER_ON_START = CENTRE_ON_START;
/*     */ 
/* 122 */   public static final Alignment CENTRE_ON_END = new Alignment()
/*     */   {
/*     */     public double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3)
/*     */     {
/* 128 */       return (paramDouble3 - (paramDouble1 / 2.0D));
/*     */     }
/* 122 */   };
/*     */ 
/* 132 */   public static final Alignment CENTER_ON_END = CENTRE_ON_END;
/*     */ 
/*     */   abstract double determineStartTime(double paramDouble1, double paramDouble2, double paramDouble3);
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Alignment
 * JD-Core Version:    0.5.3
 */