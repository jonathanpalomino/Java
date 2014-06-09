/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public final class Volume extends AudioObject
/*     */ {
/*     */   float mainVolume;
/*     */   float volume;
/*     */   double linearVolumeValue;
/*     */   private int returned;
/*     */   private int index;
/*     */   private float[] tmp;
/*     */ 
/*     */   public Volume(AudioObject paramAudioObject)
/*     */   {
/*  68 */     this(paramAudioObject, 1.0F);
/*     */   }
/*     */ 
/*     */   public Volume(AudioObject paramAudioObject, double paramDouble)
/*     */   {
/*  80 */     this(paramAudioObject, (float)paramDouble);
/*     */   }
/*     */ 
/*     */   public Volume(AudioObject paramAudioObject, float paramFloat)
/*     */   {
/*  92 */     super(paramAudioObject, "[Volume]");
/*     */ 
/*  52 */     this.mainVolume = 1.0F;
/*     */ 
/*  54 */     this.volume = 1.0F;
/*  55 */     this.linearVolumeValue = 1.0D;
/*     */ 
/*  93 */     this.mainVolume = paramFloat;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 104 */     this.linearVolumeValue = (this.currentNote.getDynamic() / 127.0D);
/* 105 */     this.volume = ((float)(1.0D - (Math.log(128.0D - this.currentNote.getDynamic()) * 0.2D)) * this.mainVolume);
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 124 */     this.returned = this.previous[0].nextWork(paramArrayOfFloat);
/* 125 */     if (this.inputs == 2) {
/* 126 */       if ((this.tmp == null) || (this.tmp.length != paramArrayOfFloat.length))
/* 127 */         this.tmp = new float[paramArrayOfFloat.length];
/*     */       else for (this.index = 0; this.index < this.tmp.length; this.index += 1) {
/* 129 */           this.tmp[this.index] = 0.0F;
/*     */         }
/* 131 */       if (this.returned != this.previous[1].nextWork(this.tmp)) {
/* 132 */         throw new AOException(this.name, 0);
/*     */       }
/* 134 */       for (this.index = 0; ; this.index += 1) { if (this.index >= this.returned) break label220;
/* 135 */         paramArrayOfFloat[this.index] *= this.tmp[this.index];
/*     */       }
/*     */     }
/* 138 */     for (this.index = 0; this.index < this.returned; this.index += 1) {
/* 139 */       paramArrayOfFloat[this.index] *= this.volume;
/*     */     }
/*     */ 
/* 142 */     label220: return this.returned;
/*     */   }
/*     */ 
/*     */   public void setVolume(double paramDouble)
/*     */   {
/* 150 */     this.linearVolumeValue = paramDouble;
/* 151 */     this.volume = (float)Math.min(1.0D, Math.abs(Math.log(1.0D - paramDouble) * 0.2D));
/*     */   }
/*     */ 
/*     */   public double getVolume()
/*     */   {
/* 159 */     return this.linearVolumeValue;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Volume
 * JD-Core Version:    0.5.3
 */