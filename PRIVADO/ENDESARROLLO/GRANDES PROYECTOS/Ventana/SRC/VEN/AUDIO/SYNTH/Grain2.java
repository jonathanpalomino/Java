/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Grain2 extends AudioObject
/*     */ {
/*  41 */   private int grainSampSize = 1000;
/*  42 */   private int spaceSamp = 1000;
/*  43 */   private int grainCount = 0;
/*  44 */   private int spaceCount = 0;
/*  45 */   private int offset = 0;
/*  46 */   private boolean grainOn = true;
/*     */ 
/*     */   public Grain2(AudioObject paramAudioObject, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4)
/*     */   {
/*  52 */     super(paramAudioObject, "[Grain]");
/*  53 */     this.grainSampSize = (paramInt1 * paramInt3);
/*  54 */     this.spaceSamp = (paramInt2 * paramInt3);
/*  55 */     this.grainOn = paramBoolean;
/*  56 */     this.offset = paramInt4;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  65 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/*  66 */     int j = this.offset;
/*  67 */     if (this.offset > 0) {
/*  68 */       for (k = 0; k < this.offset; ++k) {
/*  69 */         paramArrayOfFloat[k] = 0.0F;
/*     */       }
/*  71 */       this.offset = 0;
/*     */     }
/*     */ 
/*  74 */     for (int k = j; k < i; ++k) {
/*  75 */       if (this.grainOn) paramArrayOfFloat[k] *= (float)Math.sin(3.141592653589793D * this.grainCount / this.grainSampSize);
/*     */ 
/*  77 */       if ((this.grainOn) && (this.grainCount < this.grainSampSize)) { this.grainCount += 1;
/*  78 */       } else if (this.grainOn) {
/*  79 */         this.grainOn = false;
/*  80 */         this.grainCount = 0;
/*     */       }
/*     */ 
/*  83 */       if (!(this.grainOn)) paramArrayOfFloat[k] = 0.0F;
/*  84 */       if ((!(this.grainOn)) && (this.spaceCount < this.spaceSamp)) { this.spaceCount += 1;
/*  85 */       } else if (!(this.grainOn)) {
/*  86 */         this.grainOn = true;
/*  87 */         this.spaceCount = 0;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  92 */     return i;
/*     */   }
/*     */ 
/*     */   public void setGrainDur(int paramInt)
/*     */   {
/*  99 */     this.grainSampSize = paramInt;
/* 100 */     System.out.println("Space4: " + this.grainSampSize);
/*     */   }
/*     */ 
/*     */   public void setSpaceDur(int paramInt)
/*     */   {
/* 105 */     this.spaceSamp = paramInt;
/* 106 */     System.out.println("Space4: " + this.spaceSamp);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Grain2
 * JD-Core Version:    0.5.3
 */