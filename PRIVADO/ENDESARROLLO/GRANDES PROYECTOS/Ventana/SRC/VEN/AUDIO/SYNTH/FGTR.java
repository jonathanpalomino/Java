/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class FGTR extends AudioObject
/*    */ {
/*    */   private float[][] FGTArray;
/*    */   private int bCounter;
/*    */   private int gCounter;
/*    */   private int gDuration;
/*    */   private int grainsPerBuffer;
/*    */   private float bandwidthTop;
/*    */   private float bandwidthBottom;
/*    */   private float spatial;
/*    */   private float highestAmp;
/*    */   private float frequency;
/*    */   private float grainDuration;
/*    */ 
/*    */   public FGTR(AudioObject paramAudioObject)
/*    */   {
/* 52 */     super(paramAudioObject, "[FGTR]");
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 66 */     for (this.gCounter = 0; this.gCounter < this.grainsPerBuffer; this.gCounter += 1) {
/* 67 */       this.bCounter = (int)this.FGTArray[this.gCounter][0];
/* 68 */       this.gDuration = (int)this.FGTArray[this.gCounter][1];
/* 69 */       this.bandwidthTop = this.FGTArray[this.gCounter][2];
/* 70 */       this.bandwidthBottom = this.FGTArray[this.gCounter][3];
/* 71 */       this.spatial = this.FGTArray[this.gCounter][4];
/* 72 */       this.highestAmp = this.FGTArray[this.gCounter][5];
/* 73 */       this.frequency = (float)((this.bandwidthTop + this.bandwidthBottom) * 0.5D);
/*    */ 
/* 75 */       for (int i = 0; i < this.grainDuration; ++i)
/*    */       {
/* 77 */         paramArrayOfFloat[this.bCounter] += (float)(Math.sin(6.283185307179586D * i * this.frequency * paramArrayOfFloat.length / this.sampleRate * this.channels) * Math.sin(3.141592653589793D * i / this.grainDuration) * this.highestAmp);
/*    */ 
/* 86 */         this.bCounter += 1;
/*    */       }
/*    */     }
/* 89 */     return 0;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.FGTR
 * JD-Core Version:    0.5.3
 */