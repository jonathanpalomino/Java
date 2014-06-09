/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public class Oscillator extends AudioObject
/*     */ {
/*     */   public static final int SINE_WAVE = 0;
/*     */   public static final int COSINE_WAVE = 1;
/*     */   public static final int TRIANGLE_WAVE = 2;
/*     */   public static final int SQUARE_WAVE = 3;
/*     */   public static final int SAWTOOTH_WAVE = 4;
/*     */   public static final int SAWDOWN_WAVE = 5;
/*     */   public static final int SABERSAW_WAVE = 6;
/*     */   public static final int PULSE_WAVE = 7;
/*     */   public static final int AMPLITUDE = 0;
/*     */   public static final int FREQUENCY = 1;
/*     */   private float si;
/*     */   private float phase;
/*     */   private int choice;
/*     */   private float amp;
/*     */   private float frq;
/*     */   private float frqRatio;
/*     */   private int waveType;
/*     */   private double pulseWidth;
/*     */ 
/*     */   public Oscillator(AudioObject[] paramArrayOfAudioObject)
/*     */     throws AOException
/*     */   {
/*  98 */     super(paramArrayOfAudioObject, "[Oscillator]");
/*     */ 
/*  75 */     this.amp = 1.0F;
/*     */ 
/*  77 */     this.frq = -1.0F;
/*     */ 
/*  80 */     this.frqRatio = 1.0F;
/*     */ 
/*  82 */     this.waveType = 0;
/*     */ 
/*  84 */     this.pulseWidth = 0.15D;
/*     */ 
/*  99 */     if (paramArrayOfAudioObject.length <= 2) return; throw new AOException(this.name, 1);
/*     */   }
/*     */ 
/*     */   public Oscillator(AudioObject paramAudioObject, int paramInt1, int paramInt2)
/*     */   {
/* 112 */     super(paramAudioObject, "[Oscillator]");
/*     */ 
/*  75 */     this.amp = 1.0F;
/*     */ 
/*  77 */     this.frq = -1.0F;
/*     */ 
/*  80 */     this.frqRatio = 1.0F;
/*     */ 
/*  82 */     this.waveType = 0;
/*     */ 
/*  84 */     this.pulseWidth = 0.15D;
/*     */ 
/* 113 */     this.waveType = paramInt1;
/* 114 */     this.choice = paramInt2;
/*     */   }
/*     */ 
/*     */   public Oscillator(AudioObject paramAudioObject, int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 130 */     super(paramAudioObject, "[Oscillator]");
/*     */ 
/*  75 */     this.amp = 1.0F;
/*     */ 
/*  77 */     this.frq = -1.0F;
/*     */ 
/*  80 */     this.frqRatio = 1.0F;
/*     */ 
/*  82 */     this.waveType = 0;
/*     */ 
/*  84 */     this.pulseWidth = 0.15D;
/*     */ 
/* 131 */     this.waveType = paramInt1;
/* 132 */     this.choice = paramInt2;
/* 133 */     if (paramInt2 == 1)
/* 134 */       this.frq = (float)paramDouble;
/*     */     else
/* 136 */       this.amp = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public Oscillator(Instrument paramInstrument)
/*     */   {
/* 146 */     this(paramInstrument, 0);
/*     */   }
/*     */ 
/*     */   public Oscillator(Instrument paramInstrument, int paramInt)
/*     */   {
/* 156 */     this(paramInstrument, paramInt, 44100);
/*     */   }
/*     */ 
/*     */   public Oscillator(Instrument paramInstrument, int paramInt1, int paramInt2)
/*     */   {
/* 167 */     this(paramInstrument, paramInt1, paramInt2, 1);
/*     */   }
/*     */ 
/*     */   public Oscillator(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 179 */     super(paramInstrument, paramInt2, "[Oscillator]");
/*     */ 
/*  75 */     this.amp = 1.0F;
/*     */ 
/*  77 */     this.frq = -1.0F;
/*     */ 
/*  80 */     this.frqRatio = 1.0F;
/*     */ 
/*  82 */     this.waveType = 0;
/*     */ 
/*  84 */     this.pulseWidth = 0.15D;
/*     */ 
/* 180 */     this.waveType = paramInt1;
/* 181 */     this.channels = paramInt3;
/*     */   }
/*     */ 
/*     */   public Oscillator(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double paramDouble)
/*     */   {
/* 196 */     super(paramInstrument, paramInt2, "[Oscillator]");
/*     */ 
/*  75 */     this.amp = 1.0F;
/*     */ 
/*  77 */     this.frq = -1.0F;
/*     */ 
/*  80 */     this.frqRatio = 1.0F;
/*     */ 
/*  82 */     this.waveType = 0;
/*     */ 
/*  84 */     this.pulseWidth = 0.15D;
/*     */ 
/* 197 */     this.waveType = paramInt1;
/* 198 */     this.channels = paramInt3;
/* 199 */     this.choice = paramInt4;
/* 200 */     if (this.choice == 1)
/* 201 */       this.frq = (float)paramDouble;
/*     */     else
/* 203 */       this.amp = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 223 */     int i = paramArrayOfFloat.length / this.channels;
/* 224 */     int j = 0;
/*     */     float[] arrayOfFloat1;
/*     */     int k;
/* 225 */     if (this.inputs == 2) {
/* 226 */       arrayOfFloat1 = new float[i];
/* 227 */       k = this.previous[0].nextWork(arrayOfFloat1);
/* 228 */       float[] arrayOfFloat2 = new float[k];
/* 229 */       if (k != this.previous[1].work(arrayOfFloat2)) {
/* 230 */         throw new AOException(this.name, 0);
/*     */       }
/* 232 */       for (int i1 = 0; j < paramArrayOfFloat.length; ++i1) {
/* 233 */         setSI(arrayOfFloat2[i1] * this.frqRatio);
/* 234 */         float f3 = getWaveSample() * this.amp * arrayOfFloat1[i1];
/* 235 */         for (int i3 = 0; i3 < this.channels; ++i3)
/* 236 */           paramArrayOfFloat[(j++)] = f3;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int l;
/*     */       float f2;
/*     */       int i2;
/* 239 */       if ((this.inputs == 1) && (this.choice == 0)) {
/* 240 */         arrayOfFloat1 = new float[i];
/* 241 */         k = this.previous[0].nextWork(arrayOfFloat1);
/* 242 */         for (l = 0; j < paramArrayOfFloat.length; ++l) {
/* 243 */           f2 = getWaveSample() * this.amp * arrayOfFloat1[l];
/* 244 */           for (i2 = 0; i2 < this.channels; ++i2)
/* 245 */             paramArrayOfFloat[(j++)] = f2;
/*     */         }
/*     */       }
/* 248 */       else if ((this.inputs == 1) && (this.choice == 1))
/*     */       {
/* 250 */         arrayOfFloat1 = new float[i];
/* 251 */         k = this.previous[0].work(arrayOfFloat1);
/* 252 */         for (l = 0; l < i; ++l) {
/* 253 */           setSI(arrayOfFloat1[l] * this.frqRatio);
/* 254 */           f2 = getWaveSample() * this.amp;
/* 255 */           for (i2 = 0; i2 < this.channels; ++i2)
/* 256 */             paramArrayOfFloat[(j++)] = f2;
/*     */         }
/*     */       }
/*     */       else
/*     */       {
/* 261 */         while (j < paramArrayOfFloat.length) {
/* 262 */           if (this.choice == 1) setSI(this.frq);
/* 263 */           float f1 = getWaveSample() * this.amp;
/* 264 */           for (k = 0; k < this.channels; ++k)
/*     */             try {
/* 266 */               paramArrayOfFloat[(j++)] = f1;
/*     */             }
/*     */             catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
/*     */             {
/* 274 */               --j;
/*     */             }
/*     */         }
/*     */       }
/*     */     }
/* 279 */     return j;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 287 */     if (this.frq < 0.0F)
/*     */     {
/* 289 */       float f = (float)this.currentNote.getFrequency();
/*     */ 
/* 291 */       f *= this.frqRatio;
/* 292 */       setSI(f);
/*     */     }
/*     */     else {
/* 295 */       this.frq *= this.frqRatio;
/* 296 */       setSI(this.frq);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setChoice(int paramInt)
/*     */   {
/* 305 */     this.choice = paramInt;
/*     */   }
/*     */ 
/*     */   public void setAmp(float paramFloat)
/*     */   {
/* 313 */     this.amp = paramFloat;
/* 314 */     this.choice = 0;
/*     */   }
/*     */ 
/*     */   public float getAmp()
/*     */   {
/* 321 */     return this.amp;
/*     */   }
/*     */ 
/*     */   public void setFrq(float paramFloat)
/*     */   {
/* 329 */     this.frq = paramFloat;
/* 330 */     this.choice = 1;
/*     */   }
/*     */ 
/*     */   public void setFrqRatio(double paramDouble)
/*     */   {
/* 338 */     this.frqRatio = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   protected void setSI(double paramDouble)
/*     */   {
/* 356 */     this.si = (6.283186F / this.sampleRate / (float)paramDouble);
/*     */   }
/*     */ 
/*     */   protected float getWaveSample()
/*     */   {
/*     */     float f1;
/*     */     float f2;
/* 363 */     switch (this.waveType)
/*     */     {
/*     */     case 0:
/* 365 */       if (this.phase < 0.0F) {
/* 366 */         this.phase += 6.283186F;
/*     */       }
/* 368 */       f1 = (float)Math.sin(this.phase + 6.283186F);
/* 369 */       this.phase += this.si;
/* 370 */       if (this.phase >= 6.283186F) {
/* 371 */         this.phase -= 6.283186F;
/*     */       }
/* 373 */       return f1;
/*     */     case 1:
/* 375 */       if (this.phase < 0.0F) {
/* 376 */         this.phase += 6.283186F;
/*     */       }
/* 378 */       f1 = (float)Math.cos(this.phase + 6.283186F);
/* 379 */       this.phase += this.si;
/* 380 */       if (this.phase >= 6.283186F) {
/* 381 */         this.phase -= 6.283186F;
/*     */       }
/* 383 */       return f1;
/*     */     case 2:
/* 386 */       f1 = 0.0F;
/* 387 */       if (this.phase < 0.0F) {
/* 388 */         this.phase += 6.283186F;
/*     */       }
/* 390 */       f2 = 0.1591549F * this.phase;
/* 391 */       if (f2 <= 0.25F) {
/* 392 */         f1 = (float)(f2 * 4.0D);
/*     */       }
/* 394 */       if ((f2 > 0.25F) && (f2 <= 0.75F)) {
/* 395 */         f1 = (float)(4.0D * (0.5D - f2));
/*     */       }
/* 397 */       if (f2 > 0.75F) {
/* 398 */         f1 = (float)((f2 - 1.0D) * 4.0D);
/*     */       }
/* 400 */       this.phase += this.si;
/* 401 */       if (this.phase >= 6.283186F) {
/* 402 */         this.phase -= 6.283186F;
/*     */       }
/* 404 */       return f1;
/*     */     case 3:
/* 406 */       f1 = 0.0F;
/* 407 */       if (this.phase < 0.0F) {
/* 408 */         this.phase += 6.283186F;
/*     */       }
/* 410 */       f2 = 0.1591549F * this.phase;
/* 411 */       if (f2 < 0.5F)
/* 412 */         f1 = 1.0F;
/*     */       else f1 = -1.0F;
/* 414 */       this.phase += this.si;
/* 415 */       if (this.phase >= 6.283186F) {
/* 416 */         this.phase -= 6.283186F;
/*     */       }
/* 418 */       return f1;
/*     */     case 4:
/* 420 */       if (this.phase < 0.0F) {
/* 421 */         this.phase += 6.283186F;
/*     */       }
/* 423 */       f2 = 0.3183099F * this.phase;
/* 424 */       f1 = (float)(f2 - 1.0D);
/* 425 */       this.phase += this.si;
/* 426 */       if (this.phase >= 6.283186F) {
/* 427 */         this.phase -= 6.283186F;
/*     */       }
/* 429 */       return f1;
/*     */     case 5:
/* 431 */       if (this.phase < 0.0F) {
/* 432 */         this.phase += 6.283186F;
/*     */       }
/* 434 */       f2 = 0.3183099F * this.phase;
/* 435 */       f1 = (float)(1.0D - f2);
/* 436 */       this.phase += this.si;
/* 437 */       if (this.phase >= 6.283186F) {
/* 438 */         this.phase -= 6.283186F;
/*     */       }
/* 440 */       return f1;
/*     */     case 6:
/* 442 */       if (this.phase < 0.0F) {
/* 443 */         this.phase += 6.283186F;
/*     */       }
/* 445 */       f2 = 0.1591549F * this.phase;
/* 446 */       f1 = (float)Math.exp(f2) - 2.0F;
/*     */ 
/* 448 */       this.phase += this.si;
/* 449 */       if (this.phase >= 6.283186F) {
/* 450 */         this.phase -= 6.283186F;
/*     */       }
/* 452 */       return f1;
/*     */     case 7:
/* 454 */       f1 = 0.0F;
/* 455 */       if (this.phase < 0.0F) {
/* 456 */         this.phase += 6.283186F;
/*     */       }
/* 458 */       f2 = 0.1591549F * this.phase;
/* 459 */       if (f2 < (float)this.pulseWidth)
/* 460 */         f1 = 1.0F;
/*     */       else f1 = -1.0F;
/* 462 */       this.phase += this.si;
/* 463 */       if (this.phase >= 6.283186F) {
/* 464 */         this.phase -= 6.283186F;
/*     */       }
/* 466 */       return f1;
/*     */     }
/* 468 */     System.err.println("Incorrect oscillator type selected.");
/* 469 */     System.exit(1);
/* 470 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   public void setPulseWidth(double paramDouble)
/*     */   {
/* 479 */     if ((paramDouble >= 0.0D) && (paramDouble <= 1.0D))
/* 480 */       this.pulseWidth = paramDouble;
/*     */     else System.err.println("Pulse wide must be between 0.0 and 1.0");
/*     */   }
/*     */ 
/*     */   public void setPhase(double paramDouble)
/*     */   {
/* 489 */     this.phase = (float)paramDouble;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Oscillator
 * JD-Core Version:    0.5.3
 */