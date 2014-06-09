/*     */ package jm.audio.synth;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.AOException;
/*     */ import jm.audio.AudioObject;
/*     */ import jm.audio.Instrument;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public final class Value extends AudioObject
/*     */ {
/*     */   private float theValue;
/*  55 */   private double changeRatio = 1.0D;
/*     */ 
/*  65 */   int noteAttribute = 0;
/*     */   public static final int FIXED = 0;
/*     */   public static final int NOTE_PITCH = 1;
/*     */   public static final int NOTE_DYNAMIC = 2;
/*     */   public static final int NOTE_DURATION = 3;
/*     */   public static final int NOTE_RHYTHM_VALUE = 4;
/*     */ 
/*     */   public Value(Instrument paramInstrument, int paramInt1, int paramInt2, float paramFloat)
/*     */   {
/*  84 */     super(paramInstrument, paramInt1, "[Value]");
/*  85 */     this.theValue = paramFloat;
/*  86 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public Value(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 102 */     super(paramInstrument, paramInt1, "[Value]");
/* 103 */     this.noteAttribute = paramInt3;
/* 104 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public double getChangeRation()
/*     */   {
/* 109 */     return this.changeRatio;
/*     */   }
/*     */ 
/*     */   public void setChangeRation(double paramDouble)
/*     */   {
/* 116 */     this.changeRatio = paramDouble;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 126 */     int i = 0;
/* 127 */     for (; i < paramArrayOfFloat.length; ++i) {
/* 128 */       paramArrayOfFloat[i] = this.theValue;
/*     */     }
/* 130 */     return i;
/*     */   }
/*     */ 
/*     */   public void build()
/*     */   {
/* 140 */     switch (this.noteAttribute)
/*     */     {
/*     */     case 0:
/* 142 */       break;
/*     */     case 1:
/* 144 */       this.theValue = (float)(this.currentNote.getFrequency() * this.changeRatio);
/* 145 */       break;
/*     */     case 2:
/* 147 */       this.theValue = (127.0F / (float)(this.currentNote.getDynamic() * this.changeRatio));
/* 148 */       break;
/*     */     case 3:
/* 150 */       this.theValue = (float)(this.currentNote.getDuration() * this.changeRatio);
/* 151 */       break;
/*     */     case 4:
/* 153 */       this.theValue = (float)(this.currentNote.getRhythmValue() * this.changeRatio);
/* 154 */       break;
/*     */     default:
/* 156 */       System.err.println(this.name + " A value setting of " + this.theValue + " is not supported yet");
/* 157 */       System.exit(1);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Value
 * JD-Core Version:    0.5.3
 */