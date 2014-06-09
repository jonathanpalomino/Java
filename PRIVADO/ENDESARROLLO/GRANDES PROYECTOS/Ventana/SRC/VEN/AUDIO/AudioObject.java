/*     */ package ven.audio;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import vena.JMC;
/*     */ import ven.music.data.Note;
/*     */ 
/*     */ public abstract class AudioObject
/*     */   implements JMC
/*     */ {
/*     */   protected AudioObject[] previous;
/*     */   protected AudioObject[] next;
/*     */   protected String name;
/*     */   protected int sampleRate;
/*     */   protected int channels;
/*  74 */   protected int inputs = 0;
/*     */ 
/*  76 */   protected Note currentNote = null;
/*     */   protected double currentNoteStartTime;
/*  82 */   protected int numOfSamples = 0;
/*     */ 
/*  84 */   protected Instrument inst = null;
/*     */ 
/*  86 */   protected boolean finished = true;
/*     */   private int returned;
/*     */ 
/*     */   protected AudioObject(AudioObject paramAudioObject, String paramString)
/*     */   {
/* 100 */     AudioObject[] arrayOfAudioObject = { paramAudioObject };
/* 101 */     this.name = paramString;
/* 102 */     this.previous = arrayOfAudioObject;
/* 103 */     this.previous[0].setNext(this);
/* 104 */     this.inputs = 1;
/*     */   }
/*     */ 
/*     */   protected AudioObject(AudioObject[] paramArrayOfAudioObject, String paramString)
/*     */   {
/* 114 */     this.name = paramString;
/* 115 */     this.previous = paramArrayOfAudioObject;
/* 116 */     for (int i = 0; i < paramArrayOfAudioObject.length; ++i) {
/* 117 */       paramArrayOfAudioObject[i].setNext(this);
/*     */     }
/* 119 */     this.inputs = paramArrayOfAudioObject.length;
/*     */   }
/*     */ 
/*     */   protected AudioObject(Instrument paramInstrument, int paramInt, String paramString)
/*     */   {
/* 134 */     this.inst = paramInstrument;
/* 135 */     this.name = paramString;
/* 136 */     this.sampleRate = paramInt;
/* 137 */     this.inst.addPrimaryAO(this);
/*     */   }
/*     */ 
/*     */   public abstract int work(float[] paramArrayOfFloat)
/*     */     throws AOException;
/*     */ 
/*     */   private void setNext(AudioObject paramAudioObject)
/*     */   {
/* 168 */     if (this.next == null) {
/* 169 */       this.next = new AudioObject[1];
/* 170 */       this.next[0] = paramAudioObject;
/*     */     } else {
/* 172 */       AudioObject[] arrayOfAudioObject = new AudioObject[this.next.length + 1];
/* 173 */       for (int i = 0; i < this.next.length; ++i) arrayOfAudioObject[i] = this.next[i];
/* 174 */       arrayOfAudioObject[this.next.length] = paramAudioObject;
/* 175 */       this.next = arrayOfAudioObject;
/*     */     }
/*     */   }
/*     */ 
/*     */   public int nextWork(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 186 */     this.returned = 0;
/* 187 */     this.returned = work(paramArrayOfFloat);
/*     */ 
/* 189 */     this.inst.setFinished(this.finished);
/* 190 */     return this.returned;
/*     */   }
/*     */ 
/*     */   protected void buildNext(Note paramNote, double paramDouble, int paramInt)
/*     */   {
/* 210 */     if (this.next != null)
/* 211 */       for (int i = 0; i < this.next.length; ++i) {
/* 212 */         this.next[i].numOfSamples = paramInt;
/* 213 */         this.next[i].inst = this.inst;
/* 214 */         this.next[i].channels = this.channels;
/* 215 */         this.next[i].sampleRate = this.sampleRate;
/* 216 */         this.next[i].newNote(paramNote, paramDouble, paramInt);
/*     */       }
/*     */     else
/*     */       try {
/* 220 */         this.inst.setFinalAO(this);
/*     */       } catch (AOException localAOException) {
/* 222 */         System.out.println(localAOException);
/* 223 */         System.exit(1);
/*     */       }
/*     */   }
/*     */ 
/*     */   protected void build()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void newNote(Note paramNote, double paramDouble, int paramInt)
/*     */   {
/* 253 */     this.currentNote = paramNote;
/* 254 */     this.currentNoteStartTime = paramDouble;
/* 255 */     this.numOfSamples = paramInt;
/* 256 */     build();
/* 257 */     buildNext(this.currentNote, this.currentNoteStartTime, this.numOfSamples);
/*     */   }
/*     */ 
/*     */   public int getSampleRate()
/*     */   {
/* 266 */     return this.sampleRate;
/*     */   }
/*     */ 
/*     */   public int getChannels()
/*     */   {
/* 274 */     return this.channels;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.AudioObject
 * JD-Core Version:    0.5.3
 */