/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public class WaveTable extends AudioObject
/*     */ {
/*     */   private float[] waveTable;
/*     */   private float si;
/*     */   private float phase;
/*     */   private int aoDestination;
/* 135 */   private float amp = 1.0F;
/*     */ 
/* 139 */   private float frq = -1.0F;
/*     */ 
/* 145 */   private float frqRatio = 1.0F;
/*     */   public static final int AMPLITUDE = 0;
/*     */   public static final int FREQUENCY = 1;
/*     */   public static final int MONO = 1;
/*     */   public static final int STEREO = 2;
/*     */ 
/*     */   public WaveTable(AudioObject[] paramArrayOfAudioObject, float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 189 */     super(paramArrayOfAudioObject, "[WaveTable]");
/*     */ 
/* 191 */     if (paramArrayOfAudioObject.length > 2) throw new AOException(this.name, 1);
/*     */ 
/* 193 */     this.waveTable = paramArrayOfFloat;
/*     */   }
/*     */ 
/*     */   public WaveTable(AudioObject paramAudioObject, float[] paramArrayOfFloat, int paramInt)
/*     */   {
/* 219 */     super(paramAudioObject, "[WaveTable]");
/*     */ 
/* 221 */     this.waveTable = paramArrayOfFloat;
/*     */ 
/* 223 */     this.aoDestination = paramInt;
/*     */   }
/*     */ 
/*     */   public WaveTable(Instrument paramInstrument, int paramInt1, float[] paramArrayOfFloat, int paramInt2, int paramInt3, float paramFloat)
/*     */   {
/* 255 */     super(paramInstrument, paramInt1, "[WaveTable]");
/*     */ 
/* 257 */     this.waveTable = paramArrayOfFloat;
/*     */ 
/* 259 */     this.channels = paramInt2;
/*     */ 
/* 261 */     this.aoDestination = paramInt3;
/*     */ 
/* 263 */     if (paramInt3 == 1)
/*     */     {
/* 265 */       this.frq = paramFloat;
/*     */     }
/*     */     else
/*     */     {
/* 269 */       this.amp = paramFloat;
/*     */     }
/*     */   }
/*     */ 
/*     */   public WaveTable(Instrument paramInstrument, int paramInt1, float[] paramArrayOfFloat, int paramInt2)
/*     */   {
/* 305 */     super(paramInstrument, paramInt1, "[WaveTable]");
/*     */ 
/* 307 */     this.waveTable = paramArrayOfFloat;
/*     */ 
/* 309 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 347 */     int i = paramArrayOfFloat.length / this.channels;
/*     */ 
/* 349 */     int j = 0;
/*     */     float[] arrayOfFloat1;
/*     */     int k;
/* 353 */     if (this.inputs == 2)
/*     */     {
/* 355 */       arrayOfFloat1 = new float[i];
/*     */ 
/* 357 */       k = this.previous[0].nextWork(arrayOfFloat1);
/*     */ 
/* 359 */       float[] arrayOfFloat2 = new float[k];
/*     */ 
/* 361 */       if (k != this.previous[1].work(arrayOfFloat2))
/*     */       {
/* 363 */         throw new AOException(this.name, 0);
/*     */       }
/*     */ 
/* 367 */       for (int i1 = 0; j < paramArrayOfFloat.length; ++i1)
/*     */       {
/* 369 */         setSI((int)arrayOfFloat2[i1]);
/*     */ 
/* 371 */         if (this.phase < 0.0F)
/*     */         {
/* 373 */           this.phase = (this.waveTable.length + this.phase);
/*     */         }
/*     */ 
/* 377 */         float f3 = this.waveTable[(int)this.phase] * this.amp * arrayOfFloat1[i1];
/*     */ 
/* 379 */         this.phase += this.si;
/*     */ 
/* 381 */         if (this.phase >= this.waveTable.length)
/*     */         {
/* 383 */           this.phase -= this.waveTable.length;
/*     */         }
/*     */ 
/* 387 */         for (int i3 = 0; i3 < this.channels; ++i3)
/*     */         {
/* 389 */           paramArrayOfFloat[(j++)] = f3;
/*     */         }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/*     */       int l;
/*     */       float f2;
/*     */       int i2;
/* 395 */       if ((this.inputs == 1) && (this.aoDestination == 0))
/*     */       {
/* 397 */         arrayOfFloat1 = new float[i];
/*     */ 
/* 399 */         k = this.previous[0].nextWork(arrayOfFloat1);
/*     */ 
/* 401 */         for (l = 0; j < paramArrayOfFloat.length; ++l)
/*     */         {
/* 403 */           f2 = this.waveTable[(int)this.phase] * this.amp * arrayOfFloat1[l];
/*     */ 
/* 405 */           this.phase += this.si;
/*     */ 
/* 407 */           if (this.phase >= this.waveTable.length)
/*     */           {
/* 409 */             this.phase -= this.waveTable.length;
/*     */           }
/*     */ 
/* 413 */           for (i2 = 0; i2 < this.channels; ++i2)
/*     */           {
/* 415 */             paramArrayOfFloat[(j++)] = f2;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/* 421 */       else if ((this.inputs == 1) && (this.aoDestination == 1))
/*     */       {
/* 423 */         arrayOfFloat1 = new float[i];
/*     */ 
/* 425 */         k = this.previous[0].work(arrayOfFloat1);
/*     */ 
/* 427 */         for (l = 0; l < i; ++l)
/*     */         {
/* 429 */           setSI((int)arrayOfFloat1[l]);
/*     */ 
/* 431 */           if (this.phase < 0.0F)
/*     */           {
/* 433 */             this.phase = (this.waveTable.length + this.phase);
/*     */           }
/*     */ 
/* 437 */           f2 = this.waveTable[(int)this.phase] * this.amp;
/*     */ 
/* 439 */           this.phase += this.si;
/*     */ 
/* 441 */           if (this.phase >= this.waveTable.length)
/*     */           {
/* 443 */             this.phase -= this.waveTable.length;
/*     */           }
/*     */ 
/* 447 */           for (i2 = 0; i2 < this.channels; ++i2)
/*     */           {
/* 449 */             paramArrayOfFloat[(j++)] = f2;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 457 */         while (j < paramArrayOfFloat.length)
/*     */         {
/* 459 */           float f1 = this.waveTable[(int)this.phase] * this.amp;
/*     */ 
/* 461 */           this.phase += this.si;
/*     */ 
/* 463 */           if (this.phase >= this.waveTable.length)
/*     */           {
/* 465 */             this.phase -= this.waveTable.length;
/*     */           }
/*     */ 
/* 469 */           for (k = 0; k < this.channels; ++k)
/*     */           {
/*     */             try
/*     */             {
/* 473 */               paramArrayOfFloat[(j++)] = f1;
/*     */             }
/*     */             catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
/*     */             {
/* 489 */               --j;
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 499 */     return j;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 511 */     float f = (float)this.currentNote.getFrequency() * this.frqRatio;
/*     */ 
/* 513 */     if (this.frq < 0.0F)
/*     */     {
/* 515 */       setSI(f);
/*     */     }
/*     */     else
/*     */     {
/* 519 */       setSI(this.frq);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setAmp(float paramFloat)
/*     */   {
/* 537 */     this.amp = paramFloat;
/*     */   }
/*     */ 
/*     */   public void setFrq(float paramFloat)
/*     */   {
/* 553 */     this.frq = paramFloat;
/*     */   }
/*     */ 
/*     */   public void setFrqRatio(float paramFloat)
/*     */   {
/* 569 */     this.frqRatio = paramFloat;
/*     */   }
/*     */ 
/*     */   protected void setSI(float paramFloat)
/*     */   {
/* 595 */     this.si = (paramFloat / this.sampleRate * this.waveTable.length);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.WaveTable
 * JD-Core Version:    0.5.3
 */