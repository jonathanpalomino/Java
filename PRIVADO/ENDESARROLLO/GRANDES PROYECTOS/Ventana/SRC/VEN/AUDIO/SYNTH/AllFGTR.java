/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class AllFGTR extends AudioObject
/*     */ {
/*  43 */   private float[][] FGTArray = new float[200][7];
/*     */   private float bandwidthTop;
/*     */   private float bandwidthBottom;
/*     */   private float frequency;
/*     */   private float spatial;
/*  45 */   private float highestAmp = 0.0F;
/*     */   private int grainsPerSecond;
/*     */   private int interOnset;
/*     */   private int grainDuration;
/*  48 */   private int bCounter = 0; private int gCounter = 0; private int dCounter = 0;
/*  49 */   private int grainsPerBuffer = 0; private int sampleRate = 44100; private int channels = 2;
/*     */ 
/*     */   public AllFGTR(AudioObject paramAudioObject, int paramInt1, float paramFloat1, float paramFloat2, int paramInt2)
/*     */   {
/*  57 */     super(paramAudioObject, "[AllFGTR]");
/*  58 */     this.grainDuration = paramInt1;
/*  59 */     this.bandwidthTop = paramFloat1;
/*  60 */     this.bandwidthBottom = paramFloat2;
/*  61 */     this.grainsPerSecond = paramInt2;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  75 */     System.out.println("Point 1");
/*     */ 
/*  82 */     this.highestAmp = 0.0F;
/*  83 */     this.bCounter = 0;
/*  84 */     this.gCounter = 0;
/*  85 */     this.dCounter = 0;
/*  86 */     this.grainsPerBuffer = (this.grainsPerSecond * paramArrayOfFloat.length / this.sampleRate * this.channels);
/*     */ 
/*  88 */     this.interOnset = (paramArrayOfFloat.length / this.grainsPerBuffer);
/*  89 */     for (this.gCounter = 0; this.gCounter < this.grainsPerBuffer; this.gCounter += 1) {
/*  90 */       System.out.println("Point 1.1");
/*  91 */       this.bCounter = (this.gCounter * this.interOnset);
/*  92 */       this.highestAmp = 0.0F;
/*  93 */       System.out.println("gCounter: " + this.gCounter);
/*  94 */       System.out.println("grainDuration1: " + this.grainDuration);
/*  95 */       System.out.println("dCounter: " + this.dCounter);
/*  96 */       for (this.dCounter = 0; this.dCounter < this.grainDuration; this.dCounter += 1)
/*     */       {
/*  99 */         float f1 = paramArrayOfFloat[this.bCounter] * (float)Math.sin(3.141592653589793D * this.dCounter / this.grainDuration);
/*     */ 
/* 107 */         float f2 = f1;
/* 108 */         if (f1 < 0.0F) f1 *= -1.0F;
/* 109 */         if (this.highestAmp < f1) this.highestAmp = f1;
/*     */ 
/* 111 */         this.bCounter += 1;
/*     */       }
/* 113 */       System.out.println("Point 2");
/* 114 */       this.FGTArray[this.gCounter][0] = (this.gCounter * this.interOnset);
/* 115 */       this.FGTArray[this.gCounter][1] = this.grainDuration;
/* 116 */       this.FGTArray[this.gCounter][2] = this.bandwidthTop;
/* 117 */       this.FGTArray[this.gCounter][3] = this.bandwidthBottom;
/* 118 */       this.FGTArray[this.gCounter][4] = 0.5F;
/* 119 */       this.FGTArray[this.gCounter][5] = this.highestAmp;
/* 120 */       this.FGTArray[this.gCounter][6] = this.grainsPerBuffer;
/*     */ 
/* 130 */       if ((this.gCounter + 1) * this.interOnset + this.grainDuration <= paramArrayOfFloat.length) continue; this.gCounter = this.grainsPerBuffer;
/*     */     }
/*     */ 
/* 138 */     System.out.println("Point 5");
/* 139 */     this.grainDuration = 0;
/* 140 */     this.bandwidthTop = 0.0F;
/* 141 */     this.bandwidthBottom = 0.0F;
/* 142 */     this.grainsPerBuffer = 0;
/* 143 */     this.interOnset = 0;
/* 144 */     this.highestAmp = 0.0F;
/* 145 */     this.bCounter = 0;
/* 146 */     this.gCounter = 0;
/* 147 */     this.dCounter = 0;
/* 148 */     for (int i = 0; i < paramArrayOfFloat.length; ++i) {
/* 149 */       paramArrayOfFloat[i] = 0.0F;
/*     */     }
/* 151 */     System.out.println("Point 6");
/*     */ 
/* 153 */     this.grainDuration = 1936;
/* 154 */     this.grainsPerBuffer = (int)this.FGTArray[this.gCounter][6];
/* 155 */     System.out.println("grainsPerBuffer6: " + this.grainsPerBuffer);
/* 156 */     System.out.println("grainDuration7: " + this.grainDuration);
/* 157 */     for (this.gCounter = 0; this.gCounter < this.grainsPerBuffer; this.gCounter += 1) {
/* 158 */       this.bCounter = (int)this.FGTArray[this.gCounter][0];
/* 159 */       this.grainDuration = 1936;
/* 160 */       this.bandwidthTop = this.FGTArray[this.gCounter][2];
/* 161 */       this.bandwidthBottom = this.FGTArray[this.gCounter][3];
/* 162 */       this.spatial = this.FGTArray[this.gCounter][4];
/* 163 */       this.highestAmp = this.FGTArray[this.gCounter][5];
/* 164 */       this.frequency = ((this.bandwidthTop + this.bandwidthBottom) * 0.5F);
/*     */ 
/* 174 */       for (this.dCounter = 0; this.dCounter < this.grainDuration; this.dCounter += 1)
/*     */       {
/* 176 */         paramArrayOfFloat[this.bCounter] += (float)(Math.sin(6.283185307179586D * this.dCounter * this.frequency * paramArrayOfFloat.length / this.sampleRate * this.channels) * Math.sin(3.141592653589793D * this.dCounter / this.grainDuration) * this.highestAmp);
/*     */ 
/* 184 */         this.bCounter += 1;
/*     */       }
/*     */ 
/* 187 */       System.out.println("Point 9");
/*     */     }
/* 189 */     System.out.println("Point 10");
/* 190 */     return paramArrayOfFloat.length;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.AllFGTR
 * JD-Core Version:    0.5.3
 */