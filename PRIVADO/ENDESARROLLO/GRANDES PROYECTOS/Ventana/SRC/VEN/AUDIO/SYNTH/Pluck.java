/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ 
/*     */ public final class Pluck extends AudioObject
/*     */ {
/*     */   private int index;
/*     */   float[] kernel;
/*     */   private boolean primary;
/*     */   private float prevSample;
/*     */   private float feedback;
/*     */   private float decay;
/*     */   private int delay;
/*     */   private float[] delayLine;
/*     */   private int delayIndex;
/*     */ 
/*     */   public Pluck(Instrument paramInstrument, int paramInt1, int paramInt2)
/*     */   {
/*  68 */     this(paramInstrument, paramInt1, paramInt2, 0.49D);
/*     */   }
/*     */ 
/*     */   public Pluck(Instrument paramInstrument, int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/*  79 */     super(paramInstrument, paramInt1, "[Pluck]");
/*     */ 
/*  39 */     this.index = 0;
/*  40 */     this.kernel = null;
/*     */ 
/*  42 */     this.primary = true;
/*  43 */     this.prevSample = 0.0F;
/*     */ 
/*  45 */     this.feedback = 0.49F;
/*     */ 
/*  48 */     this.decay = 0.5F;
/*     */ 
/*  50 */     this.delay = 1;
/*     */ 
/*  80 */     this.channels = paramInt2;
/*  81 */     this.feedback = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public Pluck(AudioObject paramAudioObject)
/*     */   {
/*  91 */     this(paramAudioObject, 0.5D);
/*     */   }
/*     */ 
/*     */   public Pluck(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/* 102 */     super(paramAudioObject, "[Pluck]");
/*     */ 
/*  39 */     this.index = 0;
/*  40 */     this.kernel = null;
/*     */ 
/*  42 */     this.primary = true;
/*  43 */     this.prevSample = 0.0F;
/*     */ 
/*  45 */     this.feedback = 0.49F;
/*     */ 
/*  48 */     this.decay = 0.5F;
/*     */ 
/*  50 */     this.delay = 1;
/*     */ 
/* 103 */     this.primary = false;
/* 104 */     this.feedback = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public void setFeedback(double paramDouble)
/*     */   {
/* 114 */     this.feedback = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 121 */     double d = FRQ[this.currentNote.getPitch()];
/* 122 */     int i = (int)(this.sampleRate / d);
/* 123 */     this.kernel = new float[i];
/* 124 */     for (int j = 0; j < i; ++j) {
/* 125 */       if (this.primary) this.kernel[j] = (float)(Math.random() * 2.0D - 1.0D);
/*     */       else this.kernel[j] = 0.0F;
/*     */     }
/*     */ 
/* 129 */     j = (int)(this.delay / 1000.0F * this.sampleRate);
/* 130 */     this.delayLine = new float[j * this.channels];
/* 131 */     this.delayIndex = 0;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 139 */     int i = 0;
/* 140 */     float f1 = 0.0F;
/*     */     int j;
/* 141 */     if (this.primary) {
/* 142 */       if (this.index >= this.kernel.length) this.index = 0; while (true) {
/* 143 */         if (i >= paramArrayOfFloat.length) break label378;
/* 144 */         f1 = this.kernel[this.index];
/* 145 */         for (j = 0; j < this.channels; ++j) {
/* 146 */           paramArrayOfFloat[i] = this.kernel[this.index];
/*     */           try
/*     */           {
/* 149 */             paramArrayOfFloat[i] += this.delayLine[this.delayIndex] * this.decay;
/*     */           } catch (IndexOutOfBoundsException localIndexOutOfBoundsException) {
/* 151 */             System.err.println("jMusic Pluck audio object error: i = " + i + " delayIndex = " + this.delayIndex);
/*     */           }
/* 153 */           float f2 = paramArrayOfFloat[i] * -this.decay;
/* 154 */           float f3 = this.delayLine[this.delayIndex];
/* 155 */           this.delayLine[this.delayIndex] = paramArrayOfFloat[i];
/* 156 */           paramArrayOfFloat[i] = (f2 + f3);
/* 157 */           if (this.delayIndex >= this.delayLine.length) {
/* 158 */             this.delayIndex = 0;
/*     */           }
/* 160 */           ++i;
/*     */         }
/* 162 */         this.kernel[this.index] = ((this.kernel[this.index] + this.prevSample) * this.feedback);
/* 163 */         this.prevSample = f1;
/* 164 */         this.index += 1;
/* 165 */         if (this.index < this.kernel.length) continue; this.index = 0;
/*     */       }
/*     */     }
/* 168 */     if (this.index >= this.kernel.length) this.index = 0;
/* 169 */     while (i < paramArrayOfFloat.length) {
/* 170 */       f1 = paramArrayOfFloat[i];
/* 171 */       this.kernel[this.index] = ((paramArrayOfFloat[i] + this.prevSample) * this.feedback);
/* 172 */       for (j = 0; j < this.channels; ++j) {
/* 173 */         paramArrayOfFloat[(i++)] = this.kernel[this.index];
/*     */       }
/* 175 */       this.prevSample = f1;
/* 176 */       this.index += 1;
/* 177 */       if (this.index < this.kernel.length) continue; this.index = 0;
/*     */     }
/*     */ 
/* 180 */     label378: return i;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Pluck
 * JD-Core Version:    0.5.3
 */