/*     */ package jm.midi;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Stack;
/*     */ import java.util.Vector;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MetaEventListener;
/*     */ import javax.sound.midi.MetaMessage;
/*     */ import javax.sound.midi.MidiEvent;
/*     */ import javax.sound.midi.MidiMessage;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Sequence;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.Synthesizer;
/*     */ import javax.sound.midi.Track;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class MidiSynth
/*     */   implements JMC, MetaEventListener
/*     */ {
/*     */   private short m_ppqn;
/*     */   private Synthesizer m_synth;
/*     */   private Sequencer m_sequencer;
/*     */   private float m_currentTempo;
/*     */   private float m_masterTempo;
/*     */   private Stack m_tempoHistory;
/*     */   private double trackTempoRatio;
/*     */   private double elementTempoRatio;
/*     */   private String scoreTitle;
/*     */   private static final int StopType = 47;
/*     */ 
/*     */   public MidiSynth()
/*     */   {
/*  88 */     this(480);
/*     */   }
/*     */ 
/*     */   public MidiSynth(short paramShort)
/*     */   {
/*  77 */     this.trackTempoRatio = 1.0D;
/*     */ 
/*  79 */     this.elementTempoRatio = 1.0D;
/*     */ 
/*  92 */     this.m_ppqn = paramShort;
/*  93 */     this.m_synth = null;
/*  94 */     this.m_tempoHistory = new Stack();
/*     */   }
/*     */ 
/*     */   public void play(Score paramScore)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 103 */     if ((null == this.m_sequencer) && 
/* 104 */       (!(initSynthesizer()))) {
/* 105 */       return;
/*     */     }
/*     */ 
/* 108 */     this.scoreTitle = paramScore.getTitle();
/* 109 */     this.m_masterTempo = (float)paramScore.getTempo();
/* 110 */     Sequence localSequence = scoreToSeq(paramScore);
/* 111 */     if (null == localSequence) return;
/*     */     try {
/* 113 */       this.m_sequencer.open();
/*     */     }
/*     */     catch (MidiUnavailableException localMidiUnavailableException) {
/* 116 */       System.err.println("MIDI System Unavailable:" + localMidiUnavailableException);
/* 117 */       return;
/*     */     }
/* 119 */     this.m_sequencer.setSequence(localSequence);
/* 120 */     this.m_sequencer.addMetaEventListener(this);
/* 121 */     this.m_sequencer.setTempoInBPM(this.m_masterTempo);
/*     */ 
/* 123 */     printSeqInfo(localSequence);
/*     */ 
/* 125 */     this.m_sequencer.start();
/*     */   }
/*     */ 
/*     */   protected void printSeqInfo(Sequence paramSequence)
/*     */   {
/* 134 */     float f = paramSequence.getDivisionType();
/*     */   }
/*     */ 
/*     */   public void meta(MetaMessage paramMetaMessage)
/*     */   {
/* 173 */     if (paramMetaMessage.getType() == 47)
/* 174 */       stop();
/*     */   }
/*     */ 
/*     */   public void stop()
/*     */   {
/* 183 */     if (!((this.m_sequencer != null & this.m_sequencer.isOpen())))
/*     */       return;
/* 185 */     this.m_sequencer.close();
/* 186 */     this.m_synth.close();
/*     */   }
/*     */ 
/*     */   protected static MidiEvent createNoteOnEvent(int paramInt1, int paramInt2, int paramInt3, long paramLong)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 202 */     ShortMessage localShortMessage = new ShortMessage();
/* 203 */     localShortMessage.setMessage(144 + paramInt1, paramInt2, paramInt3);
/* 204 */     MidiEvent localMidiEvent = new MidiEvent(localShortMessage, paramLong);
/*     */ 
/* 206 */     return localMidiEvent;
/*     */   }
/*     */ 
/*     */   protected static MidiEvent createNoteOffEvent(int paramInt1, int paramInt2, int paramInt3, long paramLong)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 221 */     ShortMessage localShortMessage = new ShortMessage();
/* 222 */     localShortMessage.setMessage(128 + paramInt1, paramInt2, paramInt3);
/* 223 */     MidiEvent localMidiEvent = new MidiEvent(localShortMessage, paramLong);
/*     */ 
/* 225 */     return localMidiEvent;
/*     */   }
/*     */ 
/*     */   protected static MidiEvent createProgramChangeEvent(int paramInt1, int paramInt2, long paramLong)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 239 */     ShortMessage localShortMessage = new ShortMessage();
/* 240 */     localShortMessage.setMessage(192 + paramInt1, paramInt2, 0);
/* 241 */     MidiEvent localMidiEvent = new MidiEvent(localShortMessage, paramLong);
/*     */ 
/* 243 */     return localMidiEvent;
/*     */   }
/*     */ 
/*     */   protected static MidiEvent createCChangeEvent(int paramInt1, int paramInt2, int paramInt3, long paramLong)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 258 */     ShortMessage localShortMessage = new ShortMessage();
/* 259 */     localShortMessage.setMessage(176 + paramInt1, paramInt2, paramInt3);
/* 260 */     MidiEvent localMidiEvent = new MidiEvent(localShortMessage, paramLong);
/* 261 */     return localMidiEvent;
/*     */   }
/*     */ 
/*     */   protected Sequence scoreToSeq(Score paramScore)
/*     */     throws InvalidMidiDataException
/*     */   {
/* 274 */     Sequence localSequence = new Sequence(0.0F, this.m_ppqn);
/* 275 */     if (null == localSequence) {
/* 276 */       return null;
/*     */     }
/*     */ 
/* 279 */     this.m_masterTempo = (this.m_currentTempo = new Float(paramScore.getTempo()).floatValue());
/*     */ 
/* 283 */     Object localObject1 = null;
/* 284 */     double d1 = 0.0D;
/* 285 */     double d2 = 1.0D;
/*     */ 
/* 287 */     Enumeration localEnumeration1 = paramScore.getPartList().elements();
/*     */     Object localObject2;
/* 288 */     while (localEnumeration1.hasMoreElements()) {
/* 289 */       localObject2 = (Part)localEnumeration1.nextElement();
/*     */ 
/* 291 */       int i = ((Part)localObject2).getChannel();
/* 292 */       if (i > 16) {
/* 293 */         throw new InvalidMidiDataException(((Part)localObject2).getTitle() + " - Invalid Channel Number: " + i);
/*     */       }
/*     */ 
/* 299 */       this.m_tempoHistory.push(new Float(this.m_currentTempo));
/*     */ 
/* 301 */       float f = new Float(((Part)localObject2).getTempo()).floatValue();
/*     */ 
/* 303 */       if (f != -1.0D)
/* 304 */         this.m_currentTempo = f;
/* 305 */       else if (f < -1.0D) {
/* 306 */         System.out.println("jMusic MidiSynth error: Part TempoEvent (BPM) too low = " + f);
/*     */       }
/* 308 */       this.trackTempoRatio = (this.m_masterTempo / this.m_currentTempo);
/*     */ 
/* 310 */       int j = ((Part)localObject2).getInstrument();
/* 311 */       if (j == -1) j = 0;
/*     */ 
/* 313 */       Enumeration localEnumeration2 = ((Part)localObject2).getPhraseList().elements();
/* 314 */       double d3 = 0.0D;
/* 315 */       double d4 = 0.0D;
/*     */ 
/* 320 */       Track localTrack = localSequence.createTrack();
/* 321 */       while (localEnumeration2.hasMoreElements())
/*     */       {
/* 327 */         localObject3 = (Phrase)localEnumeration2.nextElement();
/*     */ 
/* 331 */         d4 = ((Phrase)localObject3).getStartTime();
/* 332 */         long l1 = ()(d4 * this.m_ppqn * this.trackTempoRatio);
/*     */ 
/* 335 */         if (((Phrase)localObject3).getInstrument() != -1) j = ((Phrase)localObject3).getInstrument();
/* 336 */         MidiEvent localMidiEvent2 = createProgramChangeEvent(i, j, l1);
/* 337 */         localTrack.add(localMidiEvent2);
/*     */ 
/* 339 */         this.m_tempoHistory.push(new Float(this.m_currentTempo));
/*     */ 
/* 341 */         f = new Float(((Phrase)localObject3).getTempo()).floatValue();
/* 342 */         if (f != -1.0D) {
/* 343 */           this.m_currentTempo = f;
/*     */         }
/*     */ 
/* 347 */         this.elementTempoRatio = (this.m_masterTempo / this.m_currentTempo);
/*     */ 
/* 349 */         double d5 = -1.0D;
/* 350 */         int k = 0;
/*     */ 
/* 352 */         Enumeration localEnumeration3 = ((Phrase)localObject3).getNoteList().elements();
/* 353 */         while (localEnumeration3.hasMoreElements()) {
/* 354 */           localObject4 = (Note)localEnumeration3.nextElement();
/*     */ 
/* 356 */           k = (int)(((Note)localObject4).getOffset() * this.m_ppqn * this.elementTempoRatio);
/*     */ 
/* 359 */           int l = -1;
/* 360 */           if (!(((Note)localObject4).getPitchType()))
/* 361 */             l = ((Note)localObject4).getPitch();
/*     */           else {
/* 363 */             l = Note.freqToMidiPitch(((Note)localObject4).getFrequency());
/*     */           }
/*     */ 
/* 366 */           int i1 = ((Note)localObject4).getDynamic();
/*     */ 
/* 368 */           if (l == -2147483648) {
/* 369 */             l1 = ()(l1 + ((Note)localObject4).getRhythmValue() * this.m_ppqn * this.elementTempoRatio);
/*     */           }
/*     */ 
/* 373 */           long l2 = l1;
/*     */ 
/* 375 */           if (((Note)localObject4).getPan() != d5) {
/* 376 */             localMidiEvent2 = createCChangeEvent(i, 10, (int)(((Note)localObject4).getPan() * 127.0D), l2);
/* 377 */             localTrack.add(localMidiEvent2);
/* 378 */             d5 = ((Note)localObject4).getPan();
/*     */           }
/*     */ 
/* 381 */           localMidiEvent2 = createNoteOnEvent(i, l, i1, l2 + k);
/* 382 */           localTrack.add(localMidiEvent2);
/*     */ 
/* 384 */           long l3 = ()(l1 + ((Note)localObject4).getDuration() * this.m_ppqn * this.elementTempoRatio);
/*     */ 
/* 386 */           localMidiEvent2 = createNoteOffEvent(i, l, i1, l3 + k);
/* 387 */           localTrack.add(localMidiEvent2);
/*     */ 
/* 389 */           l1 = ()(l1 + ((Note)localObject4).getRhythmValue() * this.m_ppqn * this.elementTempoRatio);
/*     */ 
/* 396 */           if (l3 > d1) {
/* 397 */             d1 = l3;
/* 398 */             localObject1 = localTrack;
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 403 */         Object localObject4 = (Float)this.m_tempoHistory.pop();
/* 404 */         this.m_currentTempo = ((Float)localObject4).floatValue();
/*     */       }
/*     */ 
/* 408 */       Object localObject3 = (Float)this.m_tempoHistory.pop();
/* 409 */       this.m_currentTempo = ((Float)localObject3).floatValue();
/*     */     }
/*     */ 
/* 414 */     if ((d1 > 0.0D) && (localObject1 != null)) {
/* 415 */       localObject2 = new MetaMessage();
/* 416 */       byte[] arrayOfByte = new byte[0];
/* 417 */       ((MetaMessage)localObject2).setMessage(47, arrayOfByte, 0);
/* 418 */       MidiEvent localMidiEvent1 = new MidiEvent((MidiMessage)localObject2, ()d1 + 100L);
/* 419 */       localObject1.add(localMidiEvent1);
/*     */     }
/*     */ 
/* 422 */     return ((Sequence)(Sequence)(Sequence)localSequence);
/*     */   }
/*     */ 
/*     */   private boolean initSynthesizer() {
/* 426 */     if (null == this.m_synth) {
/*     */       try {
/* 428 */         if (MidiSystem.getSequencer() == null) {
/* 429 */           System.err.println("MidiSystem Sequencer Unavailable");
/* 430 */           return false;
/*     */         }
/*     */ 
/* 433 */         this.m_synth = MidiSystem.getSynthesizer();
/* 434 */         this.m_synth.open();
/* 435 */         this.m_sequencer = MidiSystem.getSequencer();
/*     */       }
/*     */       catch (MidiUnavailableException localMidiUnavailableException)
/*     */       {
/* 439 */         System.err.println("Midi System Unavailable:" + localMidiUnavailableException);
/* 440 */         return false;
/*     */       }
/*     */     }
/* 443 */     return true;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.MidiSynth
 * JD-Core Version:    0.5.3
 */