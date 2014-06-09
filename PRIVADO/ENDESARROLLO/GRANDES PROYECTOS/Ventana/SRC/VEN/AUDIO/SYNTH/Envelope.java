/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.JMC;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public class Envelope extends AudioObject
/*     */   implements JMC
/*     */ {
/*     */   private EnvPoint[] graphPoints;
/*     */   private float[] graphShape;
/*  64 */   private int position = 1;
/*     */   private boolean primary;
/*  68 */   private boolean useNotePoints = false;
/*     */   private int notePointIndex;
/*     */ 
/*     */   public Envelope(Instrument paramInstrument, int paramInt1, int paramInt2, EnvPoint[] paramArrayOfEnvPoint)
/*     */   {
/*  84 */     super(paramInstrument, paramInt1, "[Envelope]");
/*  85 */     this.channels = paramInt2;
/*  86 */     this.graphPoints = paramArrayOfEnvPoint;
/*  87 */     this.primary = true;
/*     */   }
/*     */ 
/*     */   public Envelope(Instrument paramInstrument, int paramInt1, int paramInt2, double[] paramArrayOfDouble)
/*     */   {
/*  97 */     super(paramInstrument, paramInt1, "[Envelope]");
/*  98 */     this.channels = paramInt2;
/*  99 */     breakPointsToGraphPoints(paramArrayOfDouble);
/* 100 */     this.primary = true;
/*     */   }
/*     */ 
/*     */   public Envelope(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 110 */     super(paramInstrument, paramInt1, "[Envelope]");
/* 111 */     this.channels = paramInt2;
/* 112 */     this.useNotePoints = true;
/* 113 */     this.notePointIndex = paramInt3;
/* 114 */     this.primary = true;
/*     */   }
/*     */ 
/*     */   public Envelope(AudioObject paramAudioObject)
/*     */   {
/* 126 */     super(paramAudioObject, "[Envelope]");
/* 127 */     this.useNotePoints = true;
/* 128 */     this.notePointIndex = 0;
/* 129 */     this.primary = false;
/*     */   }
/*     */ 
/*     */   public Envelope(AudioObject paramAudioObject, int paramInt)
/*     */   {
/* 141 */     super(paramAudioObject, "[Envelope]");
/* 142 */     this.useNotePoints = true;
/* 143 */     this.notePointIndex = paramInt;
/* 144 */     this.primary = false;
/*     */   }
/*     */ 
/*     */   public Envelope(AudioObject paramAudioObject, EnvPoint[] paramArrayOfEnvPoint)
/*     */   {
/* 156 */     super(paramAudioObject, "[Envelope]");
/* 157 */     this.graphPoints = paramArrayOfEnvPoint;
/* 158 */     this.primary = false;
/*     */   }
/*     */ 
/*     */   public Envelope(AudioObject paramAudioObject, double[] paramArrayOfDouble)
/*     */   {
/* 170 */     super(paramAudioObject, "[Envelope]");
/* 171 */     breakPointsToGraphPoints(paramArrayOfDouble);
/* 172 */     this.primary = false;
/*     */   }
/*     */ 
/*     */   public void setBreakPoints(double[] paramArrayOfDouble)
/*     */   {
/* 180 */     breakPointsToGraphPoints(paramArrayOfDouble);
/*     */   }
/*     */ 
/*     */   private void breakPointsToGraphPoints(double[] paramArrayOfDouble)
/*     */   {
/* 191 */     this.graphPoints = new EnvPoint[paramArrayOfDouble.length / 2];
/* 192 */     int i = 0;
/* 193 */     for (int j = 0; j < paramArrayOfDouble.length / 2; ++j) {
/* 194 */       this.graphPoints[j] = new EnvPoint((float)paramArrayOfDouble[i], (float)paramArrayOfDouble[(i + 1)]);
/*     */ 
/* 196 */       i += 2;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 211 */     if ((this.finished == true) && (this.inst.iterations <= 0)) return paramArrayOfFloat.length;
/*     */ 
/* 213 */     if (this.primary)
/*     */     {
/* 215 */       i = paramArrayOfFloat.length;
/* 216 */       j = 1;
/* 217 */       for (k = 0; k < i; ++k) {
/*     */         try {
/* 219 */           paramArrayOfFloat[k] = this.graphShape[this.position];
/*     */         }
/*     */         catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException1) {
/* 222 */           paramArrayOfFloat[k] = 0.0F;
/*     */         }
/* 224 */         if (j == this.channels) {
/* 225 */           j = 1;
/* 226 */           this.position += 1;
/*     */         } else {
/* 228 */           ++j; }
/*     */       }
/* 230 */       return i;
/*     */     }
/*     */ 
/* 233 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/* 234 */     int j = 1;
/* 235 */     for (int k = 0; k < i; ++k) {
/*     */       try {
/* 237 */         paramArrayOfFloat[k] *= this.graphShape[this.position];
/*     */       }
/*     */       catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException2)
/*     */       {
/* 241 */         paramArrayOfFloat[k] = 0.0F;
/*     */       }
/* 243 */       if (j == this.channels) {
/* 244 */         j = 1;
/* 245 */         this.position += 1; } else {
/* 246 */         ++j; }
/*     */     }
/* 248 */     return i;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 259 */     if (this.useNotePoints) {
/* 260 */       breakPointsToGraphPoints(this.currentNote.getBreakPoints(this.notePointIndex));
/*     */     }
/* 262 */     this.graphShape = new float[this.numOfSamples];
/*     */ 
/* 264 */     if (this.numOfSamples <= this.graphPoints.length * 4) {
/* 265 */       for (i = 0; i < this.graphShape.length; ++i) {
/* 266 */         this.graphShape[i] = 1.0F;
/*     */       }
/* 268 */       return;
/*     */     }
/*     */ 
/* 272 */     if (this.graphPoints[0].x != -1.0D) {
/* 273 */       for (i = 0; i < this.graphPoints.length; ++i) {
/* 274 */         this.graphPoints[i].X = (int)(this.numOfSamples * this.graphPoints[i].x);
/*     */ 
/* 277 */         this.graphPoints[i].X -= 1;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 284 */     int i = 0;
/* 285 */     for (int j = 0; j < this.graphPoints.length - 1; ++j) {
/* 286 */       float f1 = (this.graphPoints[j].y - this.graphPoints[(j + 1)].y) / (this.graphPoints[j].X - this.graphPoints[(j + 1)].X);
/*     */ 
/* 289 */       float f2 = this.graphPoints[(j + 1)].y - (f1 * this.graphPoints[(j + 1)].X);
/*     */       while (true)
/*     */       {
/* 296 */         this.graphShape[i] = (f1 * i + f2);
/*     */ 
/* 298 */         if (i >= this.graphPoints[(j + 1)].X) break;
/* 299 */         ++i;
/*     */       }
/*     */     }
/* 302 */     this.position = 0;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Envelope
 * JD-Core Version:    0.5.3
 */