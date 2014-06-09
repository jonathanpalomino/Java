/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public final class StereoPan extends AudioObject
/*     */ {
/*     */   private float pan;
/*  48 */   private int channel = 1;
/*     */ 
/*  50 */   boolean panSet = false;
/*     */ 
/*     */   public StereoPan(AudioObject paramAudioObject)
/*     */   {
/*  62 */     super(paramAudioObject, "[StereoPan]");
/*  63 */     this.pan = 0.5F;
/*     */   }
/*     */ 
/*     */   public StereoPan(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/*  74 */     super(paramAudioObject, "[StereoPan]");
/*  75 */     this.panSet = true;
/*  76 */     if (paramDouble < 0.0D)
/*  77 */       this.pan = 0.0F;
/*  78 */     else if (paramDouble > 1.0D)
/*  79 */       this.pan = 1.0F;
/*     */     else
/*  81 */       this.pan = (float)paramDouble;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/*  95 */     float f = (float)this.currentNote.getPan();
/*  96 */     if (!(this.panSet)) {
/*  97 */       if (f < 0.0F)
/*  98 */         this.pan = 0.0F;
/*  99 */       else if (f > 1.0F)
/* 100 */         this.pan = 1.0F;
/*     */       else {
/* 102 */         this.pan = f;
/*     */       }
/*     */     }
/* 105 */     this.channel = 1;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 118 */     int i = this.previous[0].work(paramArrayOfFloat);
/*     */ 
/* 120 */     if (this.channels == 1) return i;
/*     */ 
/* 122 */     for (int j = 0; j < i; ++j) {
/* 123 */       if (this.channel == 1) {
/* 124 */         if (this.pan > 0.5D) {
/* 125 */           paramArrayOfFloat[j] *= (1.0F - ((this.pan - 0.5F) * 2.0F));
/*     */         }
/* 127 */         this.channel = 2;
/*     */       } else {
/* 129 */         if (this.pan < 0.5D) {
/* 130 */           paramArrayOfFloat[j] = (paramArrayOfFloat[j] * this.pan * 2.0F);
/*     */         }
/* 132 */         this.channel = 1;
/*     */       }
/*     */     }
/*     */ 
/* 136 */     return i;
/*     */   }
/*     */ 
/*     */   public void setPan(double paramDouble)
/*     */   {
/* 144 */     if ((paramDouble >= 0.0D) && (paramDouble <= 1.0D))
/* 145 */       this.pan = (float)paramDouble;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.StereoPan
 * JD-Core Version:    0.5.3
 */