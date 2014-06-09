/*     */ package jm.audio.synth;
/*     */ 
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ 
/*     */ public class Spring extends AudioObject
/*     */ {
/*     */   private SpringPipe springNetwork;
/*     */   private int numberOfNodes;
/*     */   private double springConstant;
/*     */   private double massFriction;
/*     */   private double jitter;
/*     */ 
/*     */   public Spring(Instrument paramInstrument, int paramInt)
/*     */   {
/*  55 */     this(paramInstrument, paramInt, 44100.0D, 1.0D);
/*     */   }
/*     */ 
/*     */   public Spring(Instrument paramInstrument, int paramInt, double paramDouble1, double paramDouble2)
/*     */   {
/*  67 */     this(paramInstrument, paramInt, paramDouble1, paramDouble2, 0.0D, 44100, 1);
/*     */   }
/*     */ 
/*     */   public Spring(Instrument paramInstrument, int paramInt1, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt2, int paramInt3)
/*     */   {
/*  83 */     super(paramInstrument, paramInt2, "[WaveTable]");
/*     */ 
/*  40 */     this.numberOfNodes = 8;
/*     */ 
/*  84 */     this.numberOfNodes = paramInt1;
/*  85 */     this.springConstant = paramDouble1;
/*  86 */     this.jitter = paramDouble3;
/*  87 */     this.massFriction = paramDouble2;
/*  88 */     this.channels = paramInt3;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/*  95 */     this.springNetwork = new SpringPipe(this.numberOfNodes, this.springConstant, this.massFriction, this.jitter);
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 103 */     int i = 0;
/*     */ 
/* 105 */     while (i < paramArrayOfFloat.length) {
/* 106 */       for (int j = 0; j < this.channels; ++j)
/*     */       {
/* 108 */         paramArrayOfFloat[(i++)] = (float)(this.springNetwork.getNextNodePosition(0) / 40 / this.numberOfNodes - 3.0D);
/*     */       }
/*     */     }
/* 111 */     return i;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Spring
 * JD-Core Version:    0.5.3
 */