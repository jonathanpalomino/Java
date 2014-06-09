/*     */ package ven.audio;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import vena.JMC;
/*     */ import ven.music.data.Note;
/*     */ import ven.music.rt.RTLine;
/*     */ 
/*     */ public abstract class Instrument extends Thread
/*     */   implements Runnable, JMC
/*     */ {
/*     */   public int iterations;
/*     */   protected Vector primaryAO;
/*  49 */   protected AudioObject finalAO = null;
/*     */ 
/*  51 */   protected int numOfSamples = 0;
/*     */ 
/*  53 */   protected int numOfChannels = 0;
/*     */ 
/*  55 */   protected int bufsize = 4096;
/*     */ 
/*  57 */   protected volatile Vector listeners = new Vector();
/*     */ 
/*  59 */   protected long samplesProcessed = 0L;
/*     */ 
/*  61 */   private boolean restNote = false;
/*     */ 
/*  67 */   private float[] rtBuffer = new float[this.bufsize];
/*     */ 
/*  72 */   private boolean finished = true;
/*     */ 
/*  74 */   private boolean clear = false;
/*     */ 
/*  76 */   private boolean block = true;
/*     */   private RTLine rtline;
/*  80 */   private int index = 0;
/*  81 */   public boolean finishedNewData = false;
/*     */ 
/*  83 */   private boolean initialised = false;
/*     */ 
/*  85 */   private boolean okToRun = true;
/*     */   public static final int RENDER = 0;
/*     */   public static final int REALTIME = 1;
/*  94 */   protected int output = 0;
/*     */   private int returned;
/*     */   private float[] buffer;
/*     */ 
/*     */   protected Instrument()
/*     */   {
/* 108 */     this.primaryAO = new Vector();
/*     */   }
/*     */ 
/*     */   public void renderNote(Note paramNote, double paramDouble)
/*     */   {
/* 124 */     this.finalAO = null;
/* 125 */     Enumeration localEnumeration = this.primaryAO.elements();
/*     */ 
/* 127 */     AudioObject localAudioObject1 = (AudioObject)this.primaryAO.elementAt(0);
/*     */ 
/* 130 */     this.numOfSamples = (int)(localAudioObject1.getSampleRate() * (float)paramNote.getDuration());
/* 131 */     this.numOfChannels = localAudioObject1.channels;
/*     */ 
/* 136 */     if (paramNote.getFrequency() == -2147483648.0D) {
/* 137 */       this.restNote = true;
/*     */     }
/*     */     else {
/* 140 */       double d = 0.0D;
/* 141 */       if ((!(paramNote.getPitchType())) && (paramNote.getPitch() != -2147483648))
/* 142 */         d = JMC.FRQ[paramNote.getPitch()];
/*     */       else {
/* 144 */         d = paramNote.getFrequency();
/*     */       }
/* 146 */       if (localAudioObject1.getSampleRate() * 0.5D < d) {
/* 147 */         System.out.println("jMusic Instrument error: Sorry, can't render a note above the Nyquist frequency.");
/*     */ 
/* 149 */         System.out.println("Sample rate = " + localAudioObject1.getSampleRate() + " Pitch = " + paramNote.getFrequency());
/*     */ 
/* 151 */         System.exit(1);
/*     */       }
/*     */ 
/* 155 */       this.restNote = false;
/* 156 */       while (localEnumeration.hasMoreElements()) {
/* 157 */         AudioObject localAudioObject2 = (AudioObject)localEnumeration.nextElement();
/* 158 */         localAudioObject2.newNote(paramNote, paramDouble - paramNote.getOffset(), this.numOfSamples);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void addPrimaryAO(AudioObject paramAudioObject)
/*     */   {
/* 168 */     this.primaryAO.addElement(paramAudioObject);
/*     */   }
/*     */ 
/*     */   public void setFinalAO(AudioObject paramAudioObject)
/*     */     throws AOException
/*     */   {
/* 175 */     if ((this.finalAO == null) || (this.finalAO == paramAudioObject))
/* 176 */       this.finalAO = paramAudioObject;
/*     */     else
/* 178 */       throw new AOException("jMusic Instrument error: " + paramAudioObject.name, this.finalAO.name + " is already set as finalAO.\n" + "  There can only be one finalAO.");
/*     */   }
/*     */ 
/*     */   public void setFinished(boolean paramBoolean)
/*     */   {
/* 189 */     if (!(this.finished)) return;
/* 190 */     this.finished = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getFinished()
/*     */   {
/* 195 */     return this.finished;
/*     */   }
/*     */ 
/*     */   public void setBufSize(int paramInt)
/*     */   {
/* 205 */     this.bufsize = paramInt;
/* 206 */     this.rtBuffer = new float[paramInt];
/*     */   }
/*     */ 
/*     */   public int getBufSize()
/*     */   {
/* 214 */     return this.bufsize;
/*     */   }
/*     */ 
/*     */   public int getChannels()
/*     */   {
/*     */     try
/*     */     {
/* 223 */       if (!(getInitialised())) {
/* 224 */         createChain();
/* 225 */         setInitialised(true);
/*     */       }
/*     */     } catch (AOException localAOException) {
/* 228 */       localAOException.printStackTrace();
/*     */     }
/* 230 */     return ((AudioObject)this.primaryAO.firstElement()).getChannels();
/*     */   }
/*     */ 
/*     */   public int getSampleRate()
/*     */   {
/*     */     try
/*     */     {
/* 239 */       if (!(getInitialised())) {
/* 240 */         createChain();
/* 241 */         setInitialised(true);
/*     */       }
/*     */     } catch (AOException localAOException) {
/* 244 */       localAOException.printStackTrace();
/*     */     }
/* 246 */     return ((AudioObject)this.primaryAO.firstElement()).getSampleRate();
/*     */   }
/*     */ 
/*     */   public void setInitialised(boolean paramBoolean)
/*     */   {
/* 254 */     this.initialised = paramBoolean;
/*     */   }
/*     */ 
/*     */   public boolean getInitialised()
/*     */   {
/* 262 */     return this.initialised;
/*     */   }
/*     */ 
/*     */   public void addRTLine(RTLine paramRTLine)
/*     */   {
/* 269 */     this.rtline = paramRTLine;
/*     */   }
/*     */ 
/*     */   public Enumeration getListeners()
/*     */   {
/* 277 */     return this.listeners.elements();
/*     */   }
/*     */ 
/*     */   public void addAudioChainListener(AudioChainListener paramAudioChainListener)
/*     */   {
/* 285 */     this.listeners.addElement(paramAudioChainListener);
/*     */   }
/*     */ 
/*     */   public void setController(double[] paramArrayOfDouble)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     while (true)
/*     */     {
/* 302 */       if (this.okToRun)
/*     */       {
/* 305 */         this.finished = false;
/* 306 */         this.rtline.instNote(this, this.samplesProcessed);
/*     */ 
/* 309 */         iterateChain();
/*     */       }
/*     */       try {
/* 312 */         Thread.sleep(10L);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pause()
/*     */   {
/* 323 */     this.okToRun = false;
/*     */   }
/*     */ 
/*     */   public void unPause()
/*     */   {
/* 331 */     this.okToRun = true;
/*     */   }
/*     */ 
/*     */   public void setBlock(boolean paramBoolean)
/*     */   {
/* 340 */     this.block = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setClear(boolean paramBoolean)
/*     */   {
/* 348 */     this.clear = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setOutput(int paramInt)
/*     */   {
/* 358 */     this.output = paramInt;
/*     */   }
/*     */ 
/*     */   public int getOutput()
/*     */   {
/* 367 */     return this.output;
/*     */   }
/*     */ 
/*     */   public synchronized void release()
/*     */   {
/* 377 */     super.notify();
/* 378 */     this.clear = true;
/*     */   }
/*     */ 
/*     */   public synchronized void block()
/*     */   {
/* 388 */     if ((!(this.clear)) && (this.block))
/*     */       try {
/* 390 */         super.wait();
/*     */       }
/*     */       catch (InterruptedException localInterruptedException)
/*     */       {
/*     */       }
/* 395 */     this.clear = false;
/*     */   }
/*     */ 
/*     */   public abstract void createChain()
/*     */     throws AOException;
/*     */ 
/*     */   public void iterateChain()
/*     */   {
/* 414 */     this.iterations = 0;
/* 415 */     if (this.numOfSamples > 0)
/* 416 */       this.iterations = (this.numOfSamples * this.numOfChannels);
/* 417 */     this.returned = 0;
/*     */ 
/* 419 */     if (!(this.finished)) {
/* 420 */       this.finished = true;
/*     */ 
/* 422 */       this.buffer = null;
/* 423 */       if ((this.iterations > this.bufsize) || (this.iterations <= 0))
/*     */       {
/* 425 */         this.buffer = new float[this.bufsize];
/*     */       }
/*     */       else
/*     */       {
/* 434 */         this.buffer = new float[this.iterations];
/*     */       }
/*     */ 
/*     */       try
/*     */       {
/* 443 */         if (this.restNote)
/* 444 */           this.returned = this.buffer.length;
/*     */         else
/* 446 */           this.returned = this.finalAO.nextWork(this.buffer);
/*     */       }
/*     */       catch (AOException localAOException) {
/* 449 */         System.out.println(localAOException);
/* 450 */         System.exit(1);
/*     */       }
/* 452 */       this.iterations -= this.returned;
/* 453 */       if (this.iterations > 0) {
/* 454 */         this.finished = false;
/*     */       }
/* 456 */       this.samplesProcessed += this.returned;
/*     */ 
/* 459 */       for (int i = 0; ; ++i) { if (i < this.returned);
/* 460 */         this.rtBuffer[(this.index++)] = this.buffer[i];
/* 461 */         if (this.index == this.bufsize) {
/* 462 */           this.index = 0;
/* 463 */           Enumeration localEnumeration = this.listeners.elements();
/* 464 */           while (localEnumeration.hasMoreElements()) {
/* 465 */             AudioChainListener localAudioChainListener = (AudioChainListener)localEnumeration.nextElement();
/* 466 */             localAudioChainListener.controlChange(this.rtBuffer, this.returned, this.finished);
/*     */           }
/* 468 */           block();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.Instrument
 * JD-Core Version:    0.5.3
 */