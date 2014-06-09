/*     */ package jm.util;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.applet.AudioClip;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.audio.Instrument;
/*     */ import jm.audio.RTMixer;
/*     */ import jm.gui.wave.WaveFileReader;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.music.rt.RTLine;
/*     */ import jm.music.rt.RTPhrase;
/*     */ 
/*     */ public class Play
/*     */   implements JMC
/*     */ {
/*     */   private static PlayThread pt;
/*     */   private static PlayCycle pc;
/*  44 */   private static boolean cyclePlaying = false;
/*     */ 
/*  48 */   private static boolean midiPlaying = false;
/*     */   private static Thread pauseThread;
/*     */ 
/*     */   public static void stopCycle()
/*     */   {
/*  62 */     cyclePlaying = false;
/*  63 */     if (pc == null) return; pc.stopPlayCycle();
/*     */   }
/*     */ 
/*     */   public static boolean cycleIsPlaying()
/*     */   {
/*  71 */     return cyclePlaying;
/*     */   }
/*     */ 
/*     */   public static void waitCycle(Score paramScore)
/*     */   {
/*     */     try
/*     */     {
/*  89 */       Thread.sleep((int)(60000.0D / paramScore.getTempo() * paramScore.getEndTime() + 2000.0D)); } catch (Exception localException) {
/*  90 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void mid(String paramString)
/*     */   {
/*  97 */     Score localScore = new Score();
/*  98 */     Read.midi(localScore, paramString);
/*  99 */     midi(localScore);
/*     */   }
/*     */ 
/*     */   public static void midi(Note paramNote)
/*     */   {
/* 110 */     midi(paramNote, true);
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase)
/*     */   {
/* 118 */     midi(paramPhrase, true);
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart)
/*     */   {
/* 126 */     midi(paramPart, true);
/*     */   }
/*     */ 
/*     */   public static void midi(Score paramScore)
/*     */   {
/* 136 */     midi(paramScore, true);
/*     */   }
/*     */ 
/*     */   public static void midi(Note paramNote, boolean paramBoolean)
/*     */   {
/* 146 */     Score localScore = new Score("One note score", 60.0D);
/* 147 */     localScore.addPart(new Part(new Phrase(paramNote)));
/* 148 */     midi(localScore, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase, boolean paramBoolean)
/*     */   {
/* 157 */     double d = 60.0D;
/* 158 */     if (paramPhrase.getTempo() != -1.0D) d = paramPhrase.getTempo();
/* 159 */     Score localScore = new Score(paramPhrase.getTitle() + " score", d);
/* 160 */     if (paramPhrase.getTempo() != -1.0D) localScore.setTempo(paramPhrase.getTempo());
/* 161 */     localScore.addPart(new Part(paramPhrase));
/* 162 */     midi(localScore, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart, boolean paramBoolean)
/*     */   {
/* 171 */     double d = 60.0D;
/* 172 */     if (paramPart.getTempo() != -1.0D) d = paramPart.getTempo();
/* 173 */     Score localScore = new Score(paramPart.getTitle() + " score", d);
/* 174 */     if (paramPart.getTempo() != -1.0D) localScore.setTempo(paramPart.getTempo());
/* 175 */     localScore.addPart(paramPart);
/* 176 */     midi(localScore, paramBoolean);
/*     */   }
/*     */ 
/*     */   public static void midi(Score paramScore, boolean paramBoolean)
/*     */   {
/* 187 */     if (midiPlaying) stopMidi();
/* 188 */     midiPlaying = true;
/* 189 */     Score localScore = paramScore.copy();
/*     */ 
/* 191 */     System.out.print("-- Constructing MIDI file from'" + paramScore.getTitle() + "'...");
/* 192 */     pt = new PlayThread(localScore);
/* 193 */     new Thread(pt).start();
/* 194 */     System.out.print(" Playing with JavaSound ...");
/* 195 */     midiWait(paramScore, paramBoolean);
/*     */   }
/*     */ 
/*     */   private static void midiWait(Score paramScore, boolean paramBoolean)
/*     */   {
/* 209 */     pauseThread = new Thread(new Runnable(paramScore, paramBoolean) { private final Score val$score;
/*     */       private final boolean val$exit;
/*     */ 
/*     */       public void run() { try { Thread.sleep((int)(this.val$score.getEndTime() * 60.0D / this.val$score.getTempo() * 1000.0D) + 3000); } catch (Exception localException) {
/* 213 */           System.out.println("jMusic Play error in pause thread"); }
/* 214 */         System.out.println(" Completed MIDI playback --------");
/* 215 */         if (!(this.val$exit)) return; System.exit(0);
/*     */       }
/*     */     });
/* 217 */     pauseThread.start();
/*     */   }
/*     */ 
/*     */   public static void stopMidi() {
/* 221 */     if (pt != null) {
/* 222 */       pt.stopPlayThread();
/* 223 */       midiPlaying = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void midiCycle(Note paramNote)
/*     */   {
/* 232 */     Score localScore = new Score("One note score");
/* 233 */     localScore.addPart(new Part(new Phrase(paramNote)));
/* 234 */     midiCycle(localScore);
/*     */   }
/*     */ 
/*     */   public static void midiCycle(Phrase paramPhrase)
/*     */   {
/* 242 */     Score localScore = new Score(paramPhrase.getTitle() + " score");
/* 243 */     localScore.addPart(new Part(paramPhrase));
/* 244 */     midiCycle(localScore);
/*     */   }
/*     */ 
/*     */   public static void midiCycle(Part paramPart)
/*     */   {
/* 252 */     Score localScore = new Score(paramPart.getTitle() + " score");
/* 253 */     localScore.addPart(paramPart);
/* 254 */     midiCycle(localScore);
/*     */   }
/*     */ 
/*     */   public static void midiCycle(Score paramScore)
/*     */   {
/* 271 */     if (cyclePlaying == true) stopCycle();
/* 272 */     cyclePlaying = true;
/* 273 */     pc = new PlayCycle(paramScore);
/* 274 */     new Thread(pc).start();
/*     */   }
/*     */ 
/*     */   public static void au(String paramString)
/*     */   {
/* 283 */     au(paramString, true);
/*     */   }
/*     */ 
/*     */   public static void au(String paramString, boolean paramBoolean)
/*     */   {
/* 296 */     WaveFileReader localWaveFileReader = new WaveFileReader(paramString);
/* 297 */     RTLine[] arrayOfRTLine = { new AudioRTLine(paramString) };
/* 298 */     RTMixer localRTMixer = new RTMixer(arrayOfRTLine);
/* 299 */     localRTMixer.begin();
/* 300 */     System.out.println("---------- Playing '" + paramString + "'... Sample rate = " + localWaveFileReader.getSampleRate() + " Channels = " + localWaveFileReader.getChannels() + " ----------");
/*     */ 
/* 302 */     if (paramBoolean) {
/* 303 */       File localFile = new File(paramString);
/*     */       try {
/* 305 */         int i = localWaveFileReader.getBits() - 1;
/*     */ 
/* 307 */         Thread.sleep((int)(localFile.length() / i / localWaveFileReader.getSampleRate() / localWaveFileReader.getChannels() * 1000.0D) + 1000);
/*     */       }
/*     */       catch (InterruptedException localInterruptedException) {
/* 310 */         System.err.println("jMusic play.au error: Thread sleeping interupted");
/*     */       }
/* 312 */       System.out.println("-------------------- Completed Audio Playback ----------------------");
/* 313 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void audio(Phrase paramPhrase, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 323 */     audio(new Score(new Part(paramPhrase)), paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void audio(Phrase paramPhrase, Instrument paramInstrument)
/*     */   {
/* 332 */     Part localPart = new Part(paramPhrase);
/* 333 */     if (paramPhrase.getTempo() != -1.0D) localPart.setTempo(paramPhrase.getTempo());
/* 334 */     audio(localPart, new Instrument[] { paramInstrument });
/*     */   }
/*     */ 
/*     */   public static void audio(Part paramPart, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 343 */     Score localScore = new Score(paramPart);
/* 344 */     if (paramPart.getTempo() != -1.0D) localScore.setTempo(paramPart.getTempo());
/* 345 */     audio(localScore, paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void audio(Part paramPart, Instrument paramInstrument)
/*     */   {
/* 354 */     audio(new Score(paramPart), new Instrument[] { paramInstrument });
/*     */   }
/*     */ 
/*     */   public static void audio(Score paramScore, Instrument paramInstrument)
/*     */   {
/* 363 */     audio(paramScore, new Instrument[] { paramInstrument });
/*     */   }
/*     */ 
/*     */   public static void audio(Score paramScore, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 371 */     System.out.print("-------- Playing Score as Audio ...");
/*     */ 
/* 373 */     for (int i = 0; i < paramArrayOfInstrument.length; ++i) {
/* 374 */       paramArrayOfInstrument[i].setOutput(1);
/*     */     }
/*     */ 
/* 377 */     Vector localVector = new Vector();
/* 378 */     for (int j = 0; j < paramScore.size(); ++j) {
/* 379 */       Part localPart = paramScore.getPart(j);
/* 380 */       for (int l = 0; l < localPart.size(); ++l) {
/* 381 */         Phrase localPhrase2 = localPart.getPhrase(l);
/* 382 */         if (localPhrase2.getInstrument() == -1)
/* 383 */           localPhrase2.setInstrument(localPart.getInstrument());
/* 384 */         if (localPhrase2.getTempo() == -1.0D)
/* 385 */           localPhrase2.setTempo(localPart.getTempo());
/* 386 */         localVector.addElement(localPhrase2);
/*     */       }
/*     */     }
/*     */ 
/* 390 */     RTLine[] arrayOfRTLine = new RTLine[localVector.size()];
/* 391 */     for (int k = 0; k < localVector.size(); ++k) {
/* 392 */       Phrase localPhrase1 = (Phrase)(Phrase)localVector.elementAt(k);
/* 393 */       arrayOfRTLine[k] = new RTPhrase(localPhrase1, paramArrayOfInstrument[localPhrase1.getInstrument()]);
/*     */     }
/*     */ 
/* 396 */     RTMixer localRTMixer = new RTMixer(arrayOfRTLine);
/* 397 */     localRTMixer.begin();
/* 398 */     audioWait(paramScore, localRTMixer);
/*     */   }
/*     */ 
/*     */   private static void audioWait(Score paramScore, RTMixer paramRTMixer) {
/* 402 */     pauseThread = new Thread(new Runnable(paramScore, paramRTMixer) { private final Score val$score;
/*     */       private final RTMixer val$mixer;
/*     */ 
/*     */       public void run() { try { Thread.sleep((int)(this.val$score.getEndTime() * 60.0D / this.val$score.getTempo() * 1000.0D)); } catch (Exception localException) {
/* 406 */           System.out.println("jMusic Play error in pause thread"); }
/* 407 */         System.out.println(" Completed audio playback --------");
/* 408 */         this.val$mixer.pause();
/*     */       }
/*     */     });
/* 410 */     pauseThread.start();
/*     */   }
/*     */ 
/*     */   public static void audioClip(String paramString)
/*     */   {
/* 420 */     System.out.println("-------- Playing an audio file ----------");
/* 421 */     System.out.println("Loading sound into memory, please wait...");
/* 422 */     File localFile = new File(paramString);
/*     */     try {
/* 424 */       URI localURI = localFile.toURI();
/* 425 */       URL localURL = localURI.toURL();
/* 426 */       AudioClip localAudioClip = Applet.newAudioClip(localURL);
/* 427 */       System.out.println("Playing '" + paramString + "' ...");
/* 428 */       localAudioClip.play();
/*     */     } catch (MalformedURLException localMalformedURLException) {
/* 430 */       System.err.println("jMusic play.au error: malformed URL or filename");
/*     */     }
/*     */     try
/*     */     {
/* 434 */       Thread.sleep((int)(localFile.length() / 2.0D / 44100.0D / 2.0D * 1000.0D) + 1000);
/*     */     } catch (InterruptedException localInterruptedException) {
/* 436 */       System.err.println("jMusic play.au error: Thread sleeping interupted");
/*     */     }
/* 438 */     System.out.println("-------------------- Completed Playback ----------------------");
/* 439 */     System.exit(0);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.Play
 * JD-Core Version:    0.5.3
 */