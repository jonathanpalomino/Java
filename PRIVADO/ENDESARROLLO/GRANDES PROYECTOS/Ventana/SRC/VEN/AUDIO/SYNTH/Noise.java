/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Random;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ 
/*     */ public class Noise extends AudioObject
/*     */ {
/*     */   private int noiseType;
/*     */   private int noiseDensity;
/*     */   private float amp;
/*     */   private static float sum;
/*  57 */   private static float[] rg = new float[16];
/*     */   private static int k;
/*     */   private static int kg;
/*     */   private static int ng;
/*     */   private static int threshold;
/*  59 */   private static int np = 1;
/*  60 */   private static int nbits = 1;
/*  61 */   private static int numbPoints = 48000;
/*  62 */   private static float nr = numbPoints;
/*     */   private static float result;
/*  64 */   private static int counter = 0;
/*     */   private double standardDeviation;
/*     */   private double mean;
/*     */   private float walkLastValue;
/*     */   private float walkStepSize;
/*     */   private float walkMax;
/*     */   private float walkMin;
/*     */   private int walkNoiseDensity;
/*     */   private long walkDensityCounter;
/*     */   private boolean walkVaryDensity;
/*     */   private int walkNoiseDensityMin;
/*     */   private int walkNoiseDensityMax;
/*     */   private int walkNoiseDensityStepSize;
/*     */   private Random RandomGenerator;
/*     */   private int gendynAmpGranularity;
/*     */   private double gendynPrevTime;
/*     */   private int gendynTimeMirror;
/*     */   private int gendynAmpMirror;
/*     */   private int tempAmpMirror;
/*     */   private boolean ampMirrorUpdate;
/*     */   private int gendynPointSize;
/*     */   private boolean pointSizeReset;
/*     */   private int newPointSize;
/*     */   private double[] gendynAmpArray;
/*     */   private double[] gendynTimeArray;
/*     */   private double gendynAmp0;
/*     */   private int[] gendynIntArray;
/*     */   private double gendynIntArrayLength;
/*     */   private int gendynIntArrayCounter;
/*     */   private double gendynTimeStepSize;
/*     */   private double maxGendynTimeStepSize;
/*     */   private double gendynAmpStepSize;
/*     */   private double maxGendynAmpStepSize;
/*     */   private int mirrorMax;
/*     */   private boolean gendynGaussian;
/*     */   private double gendynPrimaryTimeStepSize;
/*     */   private double gendynPrimaryAmpStepSize;
/*     */   private int gendynPrimaryTimeMirror;
/*     */   private int gendynPrimaryAmpMirror;
/*     */   private int gendynInterpolation;
/*     */   private boolean gendynGranularityUpdate;
/*     */   private int tempGendynGranularity;
/*     */   public static final int WHITE_NOISE = 0;
/*     */   public static final int STEP_NOISE = 1;
/*     */   public static final int SMOOTH_NOISE = 2;
/*     */   public static final int BROWN_NOISE = 3;
/*     */   public static final int FRACTAL_NOISE = 4;
/*     */   public static final int GAUSSIAN_NOISE = 5;
/*     */   public static final int WALK_NOISE = 6;
/*     */   public static final int GENDYN_NOISE = 7;
/*     */   private float gnSampleVal;
/*     */   private int gnj;
/*     */   private int mgaCounter;
/*     */   private double mgaInc;
/*     */   private int index;
/*     */   private int jindex;
/*     */   private double rwNewVal;
/*     */ 
/*     */   public Noise(Instrument paramInstrument)
/*     */   {
/* 142 */     this(paramInstrument, 0);
/*     */   }
/*     */ 
/*     */   public Noise(Instrument paramInstrument, int paramInt)
/*     */   {
/* 152 */     this(paramInstrument, paramInt, 44100);
/*     */   }
/*     */ 
/*     */   public Noise(Instrument paramInstrument, int paramInt1, int paramInt2)
/*     */   {
/* 163 */     this(paramInstrument, paramInt1, paramInt2, 1);
/*     */   }
/*     */ 
/*     */   public Noise(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 175 */     super(paramInstrument, paramInt2, "[WaveTable]");
/*     */ 
/*  52 */     this.noiseType = 0;
/*  53 */     this.noiseDensity = 10;
/*  54 */     this.amp = 1.0F;
/*     */ 
/*  66 */     this.standardDeviation = 0.25D;
/*  67 */     this.mean = 0.0D;
/*     */ 
/*  70 */     this.walkLastValue = 0.0F;
/*     */ 
/*  72 */     this.walkStepSize = 0.3F;
/*     */ 
/*  74 */     this.walkMax = 1.0F;
/*     */ 
/*  76 */     this.walkMin = -1.0F;
/*     */ 
/*  78 */     this.walkNoiseDensity = 500;
/*     */ 
/*  80 */     this.walkDensityCounter = 0L;
/*     */ 
/*  82 */     this.walkVaryDensity = true;
/*     */ 
/*  84 */     this.walkNoiseDensityMin = 1;
/*     */ 
/*  86 */     this.walkNoiseDensityMax = 1500;
/*     */ 
/*  88 */     this.walkNoiseDensityStepSize = 100;
/*     */ 
/*  91 */     this.RandomGenerator = new Random();
/*  92 */     this.gendynAmpGranularity = 128;
/*  93 */     this.gendynPrevTime = 50.0D;
/*  94 */     this.gendynTimeMirror = 80;
/*  95 */     this.gendynAmpMirror = 80;
/*     */ 
/*  97 */     this.ampMirrorUpdate = false;
/*     */ 
/*  99 */     this.gendynPointSize = 4;
/* 100 */     this.pointSizeReset = false;
/*     */ 
/* 102 */     this.gendynAmpArray = new double[this.gendynPointSize];
/* 103 */     this.gendynTimeArray = new double[this.gendynPointSize];
/* 104 */     this.gendynAmp0 = 0.0D;
/*     */ 
/* 107 */     this.gendynIntArrayCounter = 0;
/* 108 */     this.gendynTimeStepSize = 10.0D;
/* 109 */     this.maxGendynTimeStepSize = 100.0D;
/* 110 */     this.gendynAmpStepSize = 10.0D;
/* 111 */     this.maxGendynAmpStepSize = 100.0D;
/* 112 */     this.mirrorMax = 100;
/*     */ 
/* 114 */     this.gendynGaussian = false;
/* 115 */     this.gendynPrimaryTimeStepSize = 10.0D;
/* 116 */     this.gendynPrimaryAmpStepSize = 10.0D;
/* 117 */     this.gendynPrimaryTimeMirror = 100;
/* 118 */     this.gendynPrimaryAmpMirror = 100;
/* 119 */     this.gendynInterpolation = 1;
/* 120 */     this.gendynGranularityUpdate = false;
/*     */ 
/* 176 */     this.noiseType = paramInt1;
/* 177 */     this.channels = paramInt3;
/*     */ 
/* 179 */     if (paramInt1 == 4) setUpFractalMath();
/*     */ 
/* 181 */     if (paramInt1 == 7) makeGendynArray();
/* 182 */     for (int i = 0; i < this.gendynPointSize; ++i)
/* 183 */       this.gendynAmpArray[i] = 50.0D;
/*     */   }
/*     */ 
/*     */   public void setAmp(float paramFloat)
/*     */   {
/* 192 */     this.amp = paramFloat;
/*     */   }
/*     */ 
/*     */   public float getAmp()
/*     */   {
/* 199 */     return this.amp;
/*     */   }
/*     */ 
/*     */   private void setUpFractalMath()
/*     */   {
/* 205 */     nr /= 2.0F;
/*     */ 
/* 207 */     while (nr > 1.0F) {
/* 208 */       nbits += 1;
/* 209 */       np = 2 * np;
/* 210 */       nr /= 2.0F;
/*     */     }
/*     */ 
/* 213 */     for (kg = 0; kg < nbits; kg += 1)
/* 214 */       rg[kg] = (float)Math.random();
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 230 */     int i = 0;
/*     */     int l;
/*     */     float f6;
/*     */     int i2;
/*     */     int i3;
/* 233 */     switch (this.noiseType) {
/*     */     case 0:
/*     */       while (true) { if (i >= paramArrayOfFloat.length) break label1157;
/* 236 */         for (int j = 0; j < this.channels; ++j)
/* 237 */           paramArrayOfFloat[(i++)] = ((float)(Math.random() * 2.0D - 1.0D) * this.amp);
/*     */       }
/*     */     case 3:
/* 242 */       float f1 = 0.0F;
/* 243 */       float f2 = 0.0F;
/* 244 */       float f3 = 0.0F;
/*     */       while (true) {
/* 246 */         if (i >= paramArrayOfFloat.length) break label1157;
/* 247 */         for (l = 0; l < this.channels; ++l) {
/* 248 */           float f5 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/* 249 */           float f4 = (f1 + f2 + f3 + f5) / 4.0F;
/* 250 */           paramArrayOfFloat[(i++)] = f4;
/*     */ 
/* 252 */           f1 = f2;
/* 253 */           f2 = f3;
/* 254 */           f3 = f5;
/*     */         }
/*     */       }
/*     */     case 1:
/* 261 */       l = this.noiseDensity;
/* 262 */       f6 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/*     */       while (true) { if (i >= paramArrayOfFloat.length) break label1157;
/* 264 */         for (int i1 = 0; i1 < this.channels; ++i1) {
/* 265 */           if (i % l == 0) f6 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/*     */ 
/* 267 */           paramArrayOfFloat[(i++)] = f6;
/*     */         }
/*     */       }
/*     */     case 2:
/* 274 */       l = this.noiseDensity;
/* 275 */       f6 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/* 276 */       float f7 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/*     */       while (true) { if (i >= paramArrayOfFloat.length) break label1157;
/* 278 */         for (i2 = 0; i2 < this.channels; ++i2) {
/* 279 */           if ((i + 1) % l == 0) {
/* 280 */             paramArrayOfFloat[(i++)] = f7;
/* 281 */             f6 = f7;
/* 282 */             f7 = (float)(Math.random() * 2.0D - 1.0D) * this.amp;
/*     */           } else {
/* 284 */             paramArrayOfFloat[(i++)] = (f6 + (f7 - f6) / l * i % l);
/*     */           }
/*     */         }
/*     */       }
/*     */     case 4:
/*     */       while (true)
/*     */       {
/* 291 */         if (i >= paramArrayOfFloat.length) break label1157;
/* 292 */         for (i2 = 0; i2 < this.channels; ++i2) {
/* 293 */           if (counter % this.noiseDensity == 0) {
/* 294 */             threshold = np;
/* 295 */             ng = nbits;
/* 296 */             while (k % threshold != 0) {
/* 297 */               ng -= 1;
/* 298 */               threshold /= 2;
/*     */             }
/* 300 */             sum = 0.0F;
/* 301 */             for (kg = 0; kg < nbits; kg += 1) {
/* 302 */               if (kg < ng) rg[kg] = (float)Math.random();
/* 303 */               sum += rg[kg];
/*     */             }
/* 305 */             result = (float)((sum / nbits - 0.17D) * 2.85D - 1.0D);
/* 306 */             if (result > 1.0D) result = 1.0F;
/* 307 */             else if (result < -1.0D) result = -1.0F;
/*     */           }
/* 309 */           counter += 1;
/* 310 */           paramArrayOfFloat[(i++)] = (result * this.amp);
/*     */         }
/* 312 */         if (counter <= 67000) continue; counter = 0;
/*     */       }
/*     */     case 5:
/* 316 */       Random localRandom = new Random();
/*     */       while (true) {
/* 318 */         if (i >= paramArrayOfFloat.length) break label1157;
/* 319 */         for (i3 = 0; i3 < this.channels; ++i3) {
/* 320 */           float f8 = (float)(localRandom.nextGaussian() * this.standardDeviation + this.mean);
/*     */ 
/* 322 */           if (f8 < -1.0F) f8 = -1.0F;
/* 323 */           else if (f8 > 1.0F) f8 = 1.0F;
/* 324 */           paramArrayOfFloat[(i++)] = (f8 * this.amp);
/*     */         }
/*     */       }
/*     */     case 6:
/*     */       while (true)
/*     */       {
/* 330 */         if (i >= paramArrayOfFloat.length) break label1157;
/* 331 */         for (i3 = 0; i3 < this.channels; ++i3) {
/* 332 */           paramArrayOfFloat[(i++)] = this.walkLastValue;
/* 333 */           this.walkDensityCounter += 1L;
/* 334 */           if ((int)this.walkDensityCounter % this.walkNoiseDensity != 0)
/*     */             continue;
/* 336 */           this.walkLastValue += (float)Math.random() * this.walkStepSize * 2.0F - this.walkStepSize;
/* 337 */           while ((this.walkLastValue > this.walkMax) || (this.walkLastValue < this.walkMin)) {
/* 338 */             if (this.walkLastValue > this.walkMax) this.walkLastValue -= (this.walkLastValue - this.walkMax) * 2.0F;
/* 339 */             if (this.walkLastValue >= this.walkMin) continue; this.walkLastValue += (this.walkMin - this.walkLastValue) * 2.0F;
/*     */           }
/*     */ 
/* 342 */           if (!(this.walkVaryDensity))
/*     */             continue;
/* 344 */           this.walkNoiseDensity += (int)(Math.random() * this.walkNoiseDensityStepSize * 2.0D - this.walkNoiseDensityStepSize);
/*     */ 
/* 346 */           if (this.walkNoiseDensity < this.walkNoiseDensityMin) { this.walkNoiseDensity = this.walkNoiseDensityMin; } else {
/* 347 */             if (this.walkNoiseDensity <= this.walkNoiseDensityMax) continue; this.walkNoiseDensity = this.walkNoiseDensityMax;
/*     */           }
/*     */         }
/*     */       }
/*     */     case 7:
/* 355 */       this.gnSampleVal = 0.0F;
/*     */       while (true) { if (i >= paramArrayOfFloat.length)
/*     */           break label1157;
/* 358 */         this.gnSampleVal = ((this.gendynIntArray[this.gendynIntArrayCounter] / this.gendynAmpGranularity - 0.5F) * 2.0F);
/*     */ 
/* 361 */         if (this.gnSampleVal > 1.0D) this.gnSampleVal = 1.0F;
/* 362 */         else if (this.gnSampleVal < -1.0D) this.gnSampleVal = -1.0F;
/*     */ 
/* 364 */         for (this.gnj = 0; this.gnj < this.channels; this.gnj += 1)
/*     */         {
/* 366 */           paramArrayOfFloat[(i++)] = this.gnSampleVal;
/*     */         }
/* 368 */         this.gendynIntArrayCounter += 1;
/* 369 */         if (this.gendynIntArrayCounter < (int)this.gendynIntArrayLength) continue; makeGendynArray();
/*     */       }
/*     */     }
/*     */ 
/* 373 */     System.err.println(this.name + "jMusic error: Noise type " + this.noiseType + " not supported yet.");
/*     */ 
/* 375 */     System.exit(1);
/*     */ 
/* 378 */     label1157: return i;
/*     */   }
/*     */ 
/*     */   private void makeGendynArray()
/*     */   {
/* 387 */     this.gendynTimeStepSize = randWalk(this.gendynTimeStepSize, this.gendynPrimaryTimeStepSize, this.gendynPrimaryTimeMirror, true);
/*     */ 
/* 390 */     if (Math.abs(this.gendynTimeStepSize) > this.maxGendynTimeStepSize)
/* 391 */       this.gendynTimeStepSize = this.maxGendynTimeStepSize;
/* 392 */     this.gendynAmpStepSize = randWalk(this.gendynAmpStepSize, this.gendynPrimaryAmpStepSize, this.gendynPrimaryAmpMirror, false);
/*     */ 
/* 395 */     if (Math.abs(this.gendynAmpStepSize) > this.maxGendynAmpStepSize) {
/* 396 */       this.gendynAmpStepSize = this.maxGendynAmpStepSize;
/*     */     }
/*     */ 
/* 400 */     for (this.index = 0; this.index < this.gendynPointSize; this.index += 1) {
/* 401 */       this.gendynTimeArray[this.index] = Math.abs(randWalk(this.gendynTimeArray[this.index], this.gendynTimeStepSize, this.gendynTimeMirror, true));
/*     */ 
/* 405 */       if (this.gendynTimeArray[this.index] < 1.0D) this.gendynTimeArray[this.index] = 1.0D;
/*     */ 
/* 407 */       this.gendynAmpArray[this.index] = randWalk(this.gendynAmpArray[this.index], this.gendynAmpStepSize, this.gendynAmpMirror / 2 + 51, false);
/*     */     }
/*     */ 
/* 417 */     this.gendynIntArrayLength = 0.0D;
/* 418 */     for (this.index = 0; this.index < this.gendynPointSize; this.index += 1) {
/* 419 */       this.gendynIntArrayLength += this.gendynTimeArray[this.index];
/*     */     }
/*     */ 
/* 422 */     this.gendynIntArray = new int[(int)this.gendynIntArrayLength];
/* 423 */     this.mgaCounter = 0;
/*     */ 
/* 425 */     this.mgaInc = ((this.gendynAmpArray[0] - this.gendynAmp0) / this.gendynTimeArray[0]);
/*     */     double d;
/* 427 */     for (this.jindex = 0; this.jindex < (int)this.gendynTimeArray[0]; this.jindex += 1) {
/* 428 */       switch (this.gendynInterpolation)
/*     */       {
/*     */       case 2:
/* 431 */         d = (1.0D - (Math.cos(this.jindex / this.gendynTimeArray[0] * 3.14D) / 2.0D + 0.5D)) * (this.gendynAmpArray[0] - this.gendynAmp0);
/*     */ 
/* 433 */         this.gendynIntArray[(this.mgaCounter++)] = (int)((this.gendynAmp0 + d) / 100.0D * this.gendynAmpGranularity);
/*     */ 
/* 435 */         break;
/*     */       case 1:
/* 438 */         this.gendynIntArray[(this.mgaCounter++)] = (int)((this.gendynAmp0 + this.mgaInc * this.jindex) / 100.0D * this.gendynAmpGranularity);
/*     */ 
/* 440 */         break;
/*     */       case 3:
/* 443 */         this.gendynIntArray[(this.mgaCounter++)] = (int)(this.gendynAmp0 / 100.0D * this.gendynAmpGranularity);
/*     */       }
/*     */     }
/*     */ 
/* 447 */     for (this.index = 1; this.index < this.gendynPointSize - 1; this.index += 1) {
/* 448 */       this.mgaInc = ((this.gendynAmpArray[this.index] - this.gendynAmpArray[(this.index - 1)]) / this.gendynTimeArray[this.index]);
/* 449 */       for (this.jindex = 0; this.jindex < (int)this.gendynTimeArray[this.index]; this.jindex += 1) {
/* 450 */         switch (this.gendynInterpolation)
/*     */         {
/*     */         case 2:
/* 452 */           d = (1.0D - (Math.cos(this.jindex / this.gendynTimeArray[this.index] * 3.14D) / 2.0D + 0.5D)) * (this.gendynAmpArray[this.index] - this.gendynAmpArray[(this.index - 1)]);
/*     */ 
/* 454 */           this.gendynIntArray[(this.mgaCounter++)] = (int)((this.gendynAmpArray[(this.index - 1)] + d) / 100.0D * this.gendynAmpGranularity);
/*     */ 
/* 456 */           break;
/*     */         case 1:
/* 460 */           this.gendynIntArray[(this.mgaCounter++)] = (int)((this.gendynAmpArray[(this.index - 1)] + this.mgaInc * this.jindex) / 100.0D * this.gendynAmpGranularity);
/*     */ 
/* 462 */           break;
/*     */         case 3:
/* 465 */           this.gendynIntArray[(this.mgaCounter++)] = (int)(this.gendynAmpArray[(this.index - 1)] / 100.0D * this.gendynAmpGranularity);
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 471 */     this.gendynAmp0 = this.gendynAmpArray[(this.gendynPointSize - 1)];
/*     */ 
/* 473 */     this.gendynIntArrayCounter = 0;
/*     */ 
/* 475 */     if (this.pointSizeReset) resetPointSize();
/*     */ 
/* 477 */     if (!(this.gendynGranularityUpdate)) return; updateGranularity();
/*     */   }
/*     */ 
/*     */   private double randWalk(double paramDouble1, double paramDouble2, int paramInt, boolean paramBoolean)
/*     */   {
/* 484 */     this.rwNewVal = 0.0D;
/* 485 */     if (this.gendynGaussian)
/* 486 */       this.rwNewVal = (paramDouble1 + this.RandomGenerator.nextGaussian() * paramDouble2);
/*     */     else this.rwNewVal = (paramDouble1 + this.RandomGenerator.nextDouble() * paramDouble2 * 2.0D - paramDouble2);
/*     */ 
/* 489 */     if (paramBoolean) {
/* 490 */       if (paramDouble2 == 0.0D)
/* 491 */         this.rwNewVal = paramDouble1;
/*     */       else {
/* 493 */         while ((this.rwNewVal > paramInt) || (this.rwNewVal < 0.0D)) {
/* 494 */           if (this.rwNewVal > paramInt) this.rwNewVal = (paramInt - (this.rwNewVal - paramInt));
/* 495 */           if (this.rwNewVal >= 0.0D) continue; this.rwNewVal = (this.rwNewVal / 2.0D * -1.0D);
/*     */         }
/*     */       }
/*     */ 
/* 499 */       if (this.rwNewVal < 0.0D) this.rwNewVal = 0.0D;
/*     */     } else {
/* 501 */       int i = this.mirrorMax - paramInt;
/* 502 */       while ((this.rwNewVal > paramInt) || (this.rwNewVal < i))
/*     */       {
/* 504 */         if (this.rwNewVal > paramInt) this.rwNewVal = (paramInt - (this.rwNewVal - paramInt));
/* 505 */         if (this.rwNewVal >= i) continue; this.rwNewVal = (i + i - this.rwNewVal);
/*     */       }
/*     */ 
/* 508 */       if (this.rwNewVal < 0.0D) this.rwNewVal = 0.0D;
/*     */ 
/*     */     }
/*     */ 
/* 512 */     return this.rwNewVal;
/*     */   }
/*     */ 
/*     */   public void setNoiseDensity(int paramInt)
/*     */   {
/* 522 */     this.noiseDensity = paramInt;
/*     */   }
/*     */ 
/*     */   public void setStandardDeviation(double paramDouble)
/*     */   {
/* 530 */     this.standardDeviation = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setMean(double paramDouble)
/*     */   {
/* 538 */     this.mean = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setWalkStepSize(double paramDouble)
/*     */   {
/* 547 */     if (paramDouble > 0.0D) this.walkStepSize = (float)paramDouble;
/*     */     else System.err.println("Walk step size must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkMax(double paramDouble)
/*     */   {
/* 557 */     if (paramDouble > 0.0D) this.walkMax = (float)paramDouble;
/*     */     else System.err.println("Walk maximum value must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkMin(double paramDouble)
/*     */   {
/* 567 */     if (paramDouble < 0.0D) this.walkMin = (float)paramDouble;
/*     */     else System.err.println("Walk minimum value must be less than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkNoiseDensity(int paramInt)
/*     */   {
/* 578 */     if (paramInt > 0) this.walkNoiseDensity = paramInt;
/*     */     else System.err.println("walkNoiseDensity must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkVaryDensity(boolean paramBoolean)
/*     */   {
/* 588 */     this.walkVaryDensity = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setWalkNoiseDensityMin(int paramInt)
/*     */   {
/* 597 */     if (paramInt > 0) this.walkNoiseDensityMin = paramInt;
/*     */     else System.err.println("walkNoiseDensityMin must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkNoiseDensityMax(int paramInt)
/*     */   {
/* 607 */     if (paramInt > 0) this.walkNoiseDensityMax = paramInt;
/*     */     else System.err.println("walkNoiseDensityMax must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setWalkNoiseDensityStepSize(int paramInt)
/*     */   {
/* 617 */     if (paramInt > 0) this.walkNoiseDensityStepSize = paramInt;
/*     */     else System.err.println("walkNoiseDensityMax must be greater than zero.");
/*     */   }
/*     */ 
/*     */   public void setGendynTimeMirror(double paramDouble)
/*     */   {
/* 627 */     if ((paramDouble > 1.0D) && (paramDouble <= 100.0D))
/* 628 */       this.gendynTimeMirror = (int)paramDouble;
/*     */     else
/* 630 */       System.err.println("GendynTimeMirror must be between 3 and 100, not " + paramDouble);
/*     */   }
/*     */ 
/*     */   public void setGendynAmpMirror(double paramDouble)
/*     */   {
/* 638 */     if ((paramDouble > 0.0D) && (paramDouble <= 100.0D))
/* 639 */       this.gendynAmpMirror = (int)paramDouble;
/*     */     else System.err.println("GendynAmpMirror must be between 1 and 100, not " + paramDouble);
/*     */   }
/*     */ 
/*     */   public double getGendynAmp0() {
/* 644 */     return this.gendynAmp0;
/*     */   }
/*     */ 
/*     */   public int getGendynPointSize() {
/* 648 */     return this.gendynPointSize;
/*     */   }
/*     */ 
/*     */   public void setGendynPointSize(int paramInt) {
/* 652 */     this.pointSizeReset = true;
/* 653 */     this.newPointSize = paramInt;
/*     */   }
/*     */ 
/*     */   private void resetPointSize() {
/* 657 */     this.gendynPointSize = this.newPointSize;
/* 658 */     this.gendynAmpArray = new double[this.gendynPointSize];
/* 659 */     this.gendynTimeArray = new double[this.gendynPointSize];
/* 660 */     for (int i = 0; i < this.gendynPointSize; ++i) {
/* 661 */       this.gendynAmpArray[i] = 50.0D;
/* 662 */       this.gendynTimeArray[i] = 30.0D;
/*     */     }
/* 664 */     if (getGendynAmpStepSize() < 3.0D) setGendynAmpStepSize(3);
/* 665 */     this.pointSizeReset = false;
/*     */   }
/*     */ 
/*     */   public double getGendynAmpArray(int paramInt) {
/* 669 */     return this.gendynAmpArray[paramInt];
/*     */   }
/*     */ 
/*     */   public double getGendynTimeArray(int paramInt) {
/* 673 */     return this.gendynTimeArray[paramInt];
/*     */   }
/*     */ 
/*     */   public double getGendynAmpStepSize() {
/* 677 */     return this.gendynAmpStepSize;
/*     */   }
/*     */ 
/*     */   public double getGendynTimeStepSize() {
/* 681 */     return this.gendynTimeStepSize;
/*     */   }
/*     */ 
/*     */   public void setGendynAmpStepSize(int paramInt) {
/* 685 */     if (paramInt < 0) return; this.gendynAmpStepSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setMaxGendynAmpStepSize(int paramInt) {
/* 689 */     if (paramInt < 0) return; this.maxGendynAmpStepSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynTimeStepSize(double paramDouble) {
/* 693 */     if (paramDouble < 0.0D) return; this.gendynTimeStepSize = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setMaxGendynTimeStepSize(int paramInt) {
/* 697 */     if (paramInt < 0) return; this.maxGendynTimeStepSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynPrimaryAmpStepSize(int paramInt) {
/* 701 */     if (paramInt < 0) return; this.gendynPrimaryAmpStepSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynPrimaryTimeStepSize(int paramInt) {
/* 705 */     if (paramInt < 0) return; this.gendynPrimaryTimeStepSize = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynAmpGranularity(int paramInt) {
/* 709 */     this.gendynGranularityUpdate = true;
/* 710 */     this.tempGendynGranularity = paramInt;
/*     */   }
/*     */ 
/*     */   private void updateGranularity() {
/* 714 */     if (this.tempGendynGranularity <= 0) return; this.gendynAmpGranularity = this.tempGendynGranularity;
/*     */   }
/*     */ 
/*     */   public void setGendynPrimaryTimeMirror(int paramInt) {
/* 718 */     if (paramInt < 0) return; this.gendynPrimaryTimeMirror = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynPrimaryAmpMirror(int paramInt) {
/* 722 */     if (paramInt < 0) return; this.gendynPrimaryAmpMirror = paramInt;
/*     */   }
/*     */ 
/*     */   public void setGendynInterpolation(int paramInt) {
/* 726 */     this.gendynInterpolation = paramInt;
/*     */   }
/*     */ 
/*     */   public int getGendynInterpolation()
/*     */   {
/* 731 */     return this.gendynInterpolation;
/*     */   }
/*     */ 
/*     */   public void setGendynGaussian(boolean paramBoolean)
/*     */   {
/* 739 */     this.gendynGaussian = paramBoolean;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Noise
 * JD-Core Version:    0.5.3
 */