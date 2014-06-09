/*     */ package ven.music.rt;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import ven.audio.AOException;
/*     */ import ven.audio.AudioChainListener;
/*     */ import ven.audio.Instrument;
/*     */ import ven.audio.RTMixer;
/*     */ import ven.music.data.Note;
/*     */ 
/*     */ public abstract class RTLine
/*     */   implements AudioChainListener
/*     */ {
/*     */   protected Instrument[] inst;
/*  47 */   protected boolean clear = false;
/*     */ 
/*  49 */   private double localCounter = 0.0D;
/*     */ 
/*  51 */   private boolean newNote = true;
/*     */ 
/*  53 */   private double tempo = 60.0D;
/*     */   private double testpos;
/*     */   private double size;
/*     */ int i;
/*     */   public RTLine(Instrument[] paramArrayOfInstrument)
/*     */   {
/*  67 */     this.inst = paramArrayOfInstrument;
/*  68 */     for (int i = 0; i < paramArrayOfInstrument.length; ++i) {
/*  69 */       paramArrayOfInstrument[i].addRTLine(this);
/*     */     }
/*     */ 
/*  72 */     i = paramArrayOfInstrument[0].getSampleRate();
/*  73 */     int j = paramArrayOfInstrument[0].getChannels();
/*  74 */     for (int k = 0; k < paramArrayOfInstrument.length; ++k) {
/*  75 */       if (paramArrayOfInstrument[k].getSampleRate() != i) {
/*  76 */         System.err.println("jMusic RTLine error: All instruments must have the same sample rate.");
/*     */ 
/*  78 */         System.exit(0);
/*     */       }
/*  80 */       if (paramArrayOfInstrument[k].getChannels() != j) {
/*  81 */         System.err.println("jMusic RTLine error: All instruments must have the same number of channels.");
/*     */ 
/*  83 */         System.exit(0);
/*     */       }
/*     */     }
/*  86 */     this.size = (i * j);
/*     */   }
/*     */ 
/*     */   public Instrument[] getInstrument()
/*     */   {
/*  97 */     return this.inst;
/*     */   }
/*     */ 
/*     */   public int getNumLines()
/*     */   {
/* 102 */     return this.inst.length;
/*     */   }
/*     */ 
/*     */   public void setTempo(double paramDouble)
/*     */   {
/* 107 */     this.tempo = paramDouble;
/*     */   }
/*     */ 
/*     */   public void setBufferSize(int paramInt)
/*     */   {
/* 113 */     for (int i = 0; i < this.inst.length; ++i)
/* 114 */       this.inst[i].setBufSize(paramInt);
/*     */   }
/*     */ 
/*     */   public int getSampleRate()
/*     */   {
/* 123 */     return this.inst[0].getSampleRate();
/*     */   }
/*     */ 
/*     */   public int getChannels()
/*     */   {
/* 131 */     return this.inst[0].getChannels();
/*     */   }
/*     */ 
/*     */   public void externalAction(Object paramObject, int paramInt)
/*     */   {
/*     */   }
/*     */ 
/*     */   public synchronized void controlChange(float[] paramArrayOfFloat, int paramInt, boolean paramBoolean)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void instNote(Instrument paramInstrument, long paramLong)
/*     */   {
/* 169 */     Note localNote = null;
/* 170 */     double d1 = paramLong / this.size;
/* 171 */     double d2 = 60.0D / this.tempo;
/* 172 */     if ((d1 > this.testpos - 0.001D) && (d1 < this.testpos + 0.001D)) {
/* 173 */       localNote = getNote().copy();
/* 174 */       localNote.setRhythmValue(localNote.getRhythmValue() * d2);
/* 175 */       localNote.setDuration(localNote.getDuration() * d2);
/* 176 */       this.testpos += localNote.getRhythmValue();
/*     */     }
/*     */     else {
/* 179 */       localNote = new Note(-2147483648, this.testpos - d1);
/* 180 */       localNote.setRhythmValue(localNote.getRhythmValue() * d2);
/* 181 */       localNote.setDuration(localNote.getRhythmValue());
/*     */     }
/*     */ 
/* 184 */     paramInstrument.renderNote(localNote, d1);
/*     */   }
/*     */ 
/*     */   public void start(double paramDouble, RTMixer paramRTMixer)
/*     */   {
/* 191 */     for (int i = 0; i < this.inst.length; ++i) {
/*     */       try {
/* 193 */         if (!(this.inst[i].getInitialised())) {
/* 194 */           this.inst[i].createChain();
/* 195 */           this.inst[i].setInitialised(true);
/*     */         }
/*     */ 
/* 198 */         this.inst[i].addAudioChainListener(paramRTMixer);
/*     */       } catch (AOException localAOException) {
/* 200 */         localAOException.printStackTrace();
/*     */       }
/*     */     }
/* 203 */     for (i = 0; i < this.inst.length; ++i)
/* 204 */       this.inst[i].start();
/*     */   }
/*     */ 
/*     */   public void pause()
/*     */   {
/* 212 */     for (int i = 0; i < this.inst.length; ++i)
/* 213 */       this.inst[i].pause();
/*     */   }
/*     */ 
/*     */   public void unPause()
/*     */   {
/* 221 */     for (int i = 0; i < this.inst.length; ++i)
/* 222 */       this.inst[i].unPause();
/*     */   }
/*     */ 
/*     */   public abstract Note getNote();
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.rt.RTLine
 * JD-Core Version:    0.5.3
 */