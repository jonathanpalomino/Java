/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ 
/*     */ public final class Delay extends AudioObject
/*     */ {
/*  50 */   Vector storedSamples = new Vector();
/*     */ 
/*  55 */   int sampleDelay = 0;
/*     */ 
/*  59 */   int sampleCounter = 0;
/*     */ 
/*     */   public Delay(AudioObject paramAudioObject, int paramInt)
/*     */   {
/*  72 */     super(paramAudioObject, "[Delay]");
/*  73 */     this.sampleDelay = paramInt;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  90 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/*     */     int j;
/*  91 */     if (this.sampleCounter >= this.sampleDelay) {
/*  92 */       for (j = 0; j < i; ++j)
/*     */       {
/*  94 */         this.storedSamples.addElement(new Float(paramArrayOfFloat[j]));
/*     */ 
/*  96 */         Float localFloat = (Float)this.storedSamples.elementAt(this.storedSamples.size() - 1);
/*  97 */         paramArrayOfFloat[j] = localFloat.floatValue();
/*     */ 
/*  99 */         this.storedSamples.removeElementAt(this.storedSamples.size() - 1);
/*     */       }
/*     */     }
/*     */     else {
/* 103 */       for (j = 0; j < i; ++j)
/*     */       {
/* 105 */         this.storedSamples.addElement(new Float(paramArrayOfFloat[j]));
/*     */ 
/* 107 */         paramArrayOfFloat[j] = 0.0F;
/*     */       }
/*     */     }
/*     */ 
/* 111 */     this.sampleCounter += 1;
/* 112 */     return i;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Delay
 * JD-Core Version:    0.5.3
 */