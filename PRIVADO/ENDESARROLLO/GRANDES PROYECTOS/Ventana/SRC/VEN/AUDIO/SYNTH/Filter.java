/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Filter extends AudioObject
/*     */ {
/*     */   public static final int LOW_PASS = 0;
/*     */   public static final int HIGH_PASS = 1;
/*     */   private int type;
/*     */   private double cutoff_frequency;
/*     */   private double initialCutoff;
/*     */   private double cutoff_frq_percent;
/*     */   private double ripple;
/*     */   private double poles;
/*     */   private double[] a;
/*     */   private double[] ta;
/*     */   private double[] b;
/*     */   private double[] tb;
/*     */   private double[][] xbuf;
/*     */   private double[][] ybuf;
/*     */ 
/*     */   public Filter(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/*  93 */     this(paramAudioObject, paramDouble, 0, 0.5D, 2.0D);
/*     */   }
/*     */ 
/*     */   public Filter(AudioObject paramAudioObject, double paramDouble, int paramInt)
/*     */   {
/* 103 */     this(paramAudioObject, paramDouble, paramInt, 0.5D, 2.0D);
/*     */   }
/*     */ 
/*     */   public Filter(AudioObject paramAudioObject, double paramDouble1, int paramInt, double paramDouble2, double paramDouble3)
/*     */   {
/* 116 */     super(paramAudioObject, "[Filter]");
/*     */ 
/*  50 */     this.type = 0;
/*     */ 
/*  64 */     this.ripple = 0.5D;
/*     */ 
/*  70 */     this.poles = 2.0D;
/*     */ 
/*  72 */     this.a = new double[22];
/*     */ 
/*  74 */     this.ta = new double[22];
/*     */ 
/*  76 */     this.b = new double[22];
/*     */ 
/*  78 */     this.tb = new double[22];
/*     */ 
/* 117 */     this.type = paramInt;
/* 118 */     this.cutoff_frequency = paramDouble1;
/* 119 */     this.ripple = paramDouble2;
/* 120 */     this.poles = paramDouble3;
/* 121 */     if (this.poles > 20.0D) {
/* 122 */       System.err.println("More than 20 poles are not allowed (Sorry)");
/* 123 */       System.exit(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Filter(AudioObject[] paramArrayOfAudioObject, double paramDouble, int paramInt)
/*     */   {
/* 129 */     this(paramArrayOfAudioObject, paramDouble, paramInt, 0.5D, 2.0D);
/*     */   }
/*     */ 
/*     */   public Filter(AudioObject[] paramArrayOfAudioObject, double paramDouble1, int paramInt, double paramDouble2, double paramDouble3)
/*     */   {
/* 135 */     super(paramArrayOfAudioObject, "[Filter]");
/*     */ 
/*  50 */     this.type = 0;
/*     */ 
/*  64 */     this.ripple = 0.5D;
/*     */ 
/*  70 */     this.poles = 2.0D;
/*     */ 
/*  72 */     this.a = new double[22];
/*     */ 
/*  74 */     this.ta = new double[22];
/*     */ 
/*  76 */     this.b = new double[22];
/*     */ 
/*  78 */     this.tb = new double[22];
/*     */ 
/* 136 */     this.type = paramInt;
/* 137 */     this.cutoff_frequency = paramDouble1;
/* 138 */     this.initialCutoff = paramDouble1;
/* 139 */     this.ripple = paramDouble2;
/* 140 */     this.poles = paramDouble3;
/* 141 */     if (this.poles > 20.0D) {
/* 142 */       System.err.println("More than 20 poles are not allowed (Sorry)");
/* 143 */       System.exit(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 154 */     this.ybuf = new double[this.channels][22];
/* 155 */     this.xbuf = new double[this.channels][22];
/* 156 */     setCutOff(this.cutoff_frequency);
/*     */   }
/*     */ 
/*     */   public void printCoefficients()
/*     */   {
/* 163 */     for (int i = 0; i < 22; ++i)
/* 164 */       System.out.println("a[" + i + "] " + this.a[i] + "    b[" + i + "] " + this.b[i]);
/*     */   }
/*     */ 
/*     */   public void setCutOff(double paramDouble)
/*     */   {
/* 173 */     this.cutoff_frequency = paramDouble;
/* 174 */     if (paramDouble <= 0.0D) {
/* 175 */       System.err.println("Filter error: You tried to use a cuttoff frequency of " + paramDouble + " - woops! Frequency must be greater than zero. ");
/*     */ 
/* 178 */       System.err.println("Exiting from Filter");
/* 179 */       System.exit(1);
/*     */     }
/*     */ 
/* 182 */     if (paramDouble > 0.5D * this.sampleRate) {
/* 183 */       System.err.println("Cutoff frequencies above the Nyquist limit are BAD ;) SampleRate = " + this.sampleRate + " Frequency = " + paramDouble);
/*     */ 
/* 186 */       System.err.println("Exiting from Filter");
/* 187 */       System.exit(1);
/*     */     }
/* 189 */     this.cutoff_frq_percent = (1.0D / this.sampleRate * paramDouble);
/* 190 */     coefficientCalc();
/*     */   }
/*     */ 
/*     */   public void setPoles(int paramInt)
/*     */   {
/* 197 */     if (paramInt < 0) paramInt = 0;
/* 198 */     if (paramInt > 20) paramInt = 20;
/* 199 */     this.poles = paramInt;
/* 200 */     setCutOff(this.cutoff_frequency);
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 210 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 211 */     float[] arrayOfFloat = null;
/* 212 */     if (this.previous.length > 1) {
/* 213 */       arrayOfFloat = new float[i];
/* 214 */       this.previous[1].nextWork(arrayOfFloat);
/*     */     }
/* 216 */     int j = 0;
/* 217 */     int k = 0;
/* 218 */     for (; j < i; ++j)
/*     */     {
/* 220 */       if ((j % 100 == 0) && (this.previous.length > 1)) {
/* 221 */         setCutOff(arrayOfFloat[j] + this.initialCutoff);
/*     */       }
/*     */ 
/* 225 */       for (int l = (int)this.poles; l > 0; --l) {
/* 226 */         this.xbuf[k][l] = this.xbuf[k][(l - 1)];
/*     */       }
/* 228 */       this.xbuf[k][0] = paramArrayOfFloat[j];
/*     */ 
/* 230 */       for (l = (int)this.poles; l > 0; --l) {
/* 231 */         this.ybuf[k][l] = this.ybuf[k][(l - 1)];
/*     */       }
/*     */ 
/* 234 */       this.ybuf[k][0] = 0.0D;
/*     */ 
/* 236 */       for (l = 0; l < this.poles + 1.0D; ++l) {
/* 237 */         this.ybuf[k][0] += this.a[l] * this.xbuf[k][l];
/* 238 */         if (l <= 0) continue; this.ybuf[k][0] += this.b[l] * this.ybuf[k][l];
/*     */       }
/*     */ 
/* 241 */       paramArrayOfFloat[j] = (float)(this.ybuf[k][0] * 1.0D);
/*     */ 
/* 243 */       if (this.channels == ++k) {
/* 244 */         k = 0;
/*     */       }
/*     */     }
/* 247 */     return j;
/*     */   }
/*     */ 
/*     */   public void coefficientCalc()
/*     */   {
/* 254 */     for (int i = 0; i < 22; ++i) {
/* 255 */       this.a[i] = 0.0D;
/* 256 */       this.b[i] = 0.0D;
/*     */     }
/* 258 */     this.a[2] = 1.0D;
/* 259 */     this.b[2] = 1.0D;
/*     */ 
/* 261 */     for (i = 1; i <= this.poles * 0.5D; ++i) {
/* 262 */       double[] arrayOfDouble = coefficientCalcSupport(i);
/* 263 */       for (int j = 0; j < 22; ++j) {
/* 264 */         this.ta[j] = this.a[j];
/* 265 */         this.tb[j] = this.b[j];
/*     */       }
/*     */ 
/* 268 */       for (j = 2; j < 22; ++j) {
/* 269 */         this.a[j] = (arrayOfDouble[0] * this.ta[j] + arrayOfDouble[1] * this.ta[(j - 1)] + arrayOfDouble[2] * this.ta[(j - 2)]);
/* 270 */         this.b[j] = (this.tb[j] - (arrayOfDouble[3] * this.tb[(j - 1)]) - (arrayOfDouble[4] * this.tb[(j - 2)]));
/*     */       }
/*     */     }
/* 273 */     this.b[2] = 0.0D;
/* 274 */     for (i = 0; i < 20; ++i) {
/* 275 */       this.a[i] = this.a[(i + 2)];
/* 276 */       this.b[i] = (-this.b[(i + 2)]);
/*     */     }
/* 278 */     double d1 = 0.0D;
/* 279 */     double d2 = 0.0D;
/* 280 */     for (int k = 0; k < 20; ++k) {
/* 281 */       if (this.type == 0) d1 += this.a[k];
/* 282 */       if (this.type == 0) d2 += this.b[k];
/* 283 */       if (this.type == 1) d1 += this.a[k] * Math.pow(-1.0D, k);
/* 284 */       if (this.type != 1) continue; d2 += this.b[k] * Math.pow(-1.0D, k);
/*     */     }
/* 286 */     double d3 = d1 / (1.0D - d2);
/* 287 */     for (int l = 0; l < 20; ++l)
/* 288 */       this.a[l] /= d3;
/*     */   }
/*     */ 
/*     */   private double[] coefficientCalcSupport(int paramInt)
/*     */   {
/* 296 */     double[] arrayOfDouble = new double[5];
/* 297 */     double d1 = -Math.cos(3.141592653589793D / this.poles * 2.0D + (paramInt - 1) * 3.141592653589793D / this.poles);
/* 298 */     double d2 = Math.sin(3.141592653589793D / this.poles * 2.0D + (paramInt - 1) * 3.141592653589793D / this.poles);
/*     */ 
/* 300 */     if (this.ripple != 0.0D) {
/* 301 */       d3 = Math.sqrt(Math.pow(100.0D / (100.0D - this.ripple), 2.0D) - 1.0D);
/* 302 */       d4 = 1.0D / this.poles * Math.log(1.0D / d3 + Math.sqrt(1.0D / d3 * d3 + 1.0D));
/*     */ 
/* 304 */       d5 = 1.0D / this.poles * Math.log(1.0D / d3 + Math.sqrt(1.0D / d3 * d3 - 1.0D));
/*     */ 
/* 306 */       d5 = (Math.exp(d5) + Math.exp(-d5)) * 0.5D;
/* 307 */       d1 = d1 * (Math.exp(d4) - Math.exp(-d4)) * 0.5D / d5;
/* 308 */       d2 = d2 * (Math.exp(d4) + Math.exp(-d4)) * 0.5D / d5;
/*     */     }
/* 310 */     double d3 = 2.0D * Math.tan(0.5D);
/* 311 */     double d4 = 6.283185307179586D * this.cutoff_frq_percent;
/* 312 */     double d5 = d1 * d1 + d2 * d2;
/* 313 */     double d6 = 4.0D - (4.0D * d1 * d3) + d5 * d3 * d3;
/* 314 */     double d7 = d3 * d3 / d6;
/* 315 */     double d8 = 2.0D * d7;
/* 316 */     double d9 = d7;
/* 317 */     double d10 = (8.0D - (2.0D * d5 * d3 * d3)) / d6;
/* 318 */     double d11 = (-4.0D - (4.0D * d1 * d3) - (d5 * d3 * d3)) / d6;
/*     */ 
/* 320 */     double d12 = 0.0D;
/* 321 */     if (this.type == 1) d12 = -Math.cos(d4 * 0.5D + 0.5D) / Math.cos(d4 * 0.5D - 0.5D);
/* 322 */     if (this.type == 0) d12 = Math.sin(0.5D - (d4 * 0.5D)) / Math.sin(0.5D + d4 * 0.5D);
/* 323 */     d6 = 1.0D + d10 * d12 - (d11 * d12 * d12);
/* 324 */     arrayOfDouble[0] = ((d7 - (d8 * d12) + d9 * d12 * d12) / d6);
/* 325 */     arrayOfDouble[1] = ((-2.0D * d7 * d12 + d8 + d8 * d12 * d12 - (2.0D * d9 * d12)) / d6);
/* 326 */     arrayOfDouble[2] = ((d7 * d12 * d12 - (d8 * d12) + d9) / d6);
/* 327 */     arrayOfDouble[3] = ((2.0D * d12 + d10 + d10 * d12 * d12 - (2.0D * d11 * d12)) / d6);
/* 328 */     arrayOfDouble[4] = ((-(d12 * d12) - (d10 * d12) + d11) / d6);
/*     */ 
/* 330 */     if (this.type == 1) arrayOfDouble[1] = (-arrayOfDouble[1]);
/* 331 */     if (this.type == 1) arrayOfDouble[3] = (-arrayOfDouble[3]);
/*     */ 
/* 333 */     return arrayOfDouble;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Filter
 * JD-Core Version:    0.5.3
 */