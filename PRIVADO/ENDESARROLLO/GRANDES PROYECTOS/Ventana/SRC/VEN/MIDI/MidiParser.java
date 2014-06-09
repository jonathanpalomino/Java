/*     */ package jm.midi;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Arrays;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.midi.event.CChange;
/*     */ import jm.midi.event.EndTrack;
/*     */ import jm.midi.event.Event;
/*     */ import jm.midi.event.KeySig;
/*     */ import jm.midi.event.NoteOn;
/*     */ import jm.midi.event.PChange;
/*     */ import jm.midi.event.TempoEvent;
/*     */ import jm.midi.event.TimeSig;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public final class MidiParser
/*     */   implements JMC
/*     */ {
/* 392 */   private static double tickRemainder = 0.0D;
/*     */ 
/*     */   public static void SMFToScore(Score paramScore, SMF paramSMF)
/*     */   {
/*  55 */     System.out.println("Convert SMF to JM");
/*  56 */     Enumeration localEnumeration = paramSMF.getTrackList().elements();
/*     */ 
/*  58 */     while (localEnumeration.hasMoreElements()) {
/*  59 */       Part localPart = new Part();
/*  60 */       Track localTrack = (Track)localEnumeration.nextElement();
/*  61 */       Vector localVector1 = localTrack.getEvtList();
/*  62 */       Vector localVector2 = new Vector();
/*  63 */       sortEvents(paramScore, localVector1, localVector2, paramSMF, localPart);
/*  64 */       for (int i = 0; i < localVector2.size(); ++i) {
/*  65 */         localPart.addPhrase((Phrase)localVector2.elementAt(i));
/*     */       }
/*  67 */       paramScore.addPart(localPart);
/*  68 */       paramScore.clean();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void sortEvents(Score paramScore, Vector paramVector1, Vector paramVector2, SMF paramSMF, Part paramPart)
/*     */   {
/*  74 */     double d1 = 0.0D;
/*  75 */     double[] arrayOfDouble = new double[100];
/*  76 */     Note[] arrayOfNote = new Note[100];
/*  77 */     int i = 0;
/*  78 */     double d2 = 0.0D;
/*  79 */     int j = 0;
/*     */ 
/*  81 */     for (int k = 0; k < paramVector1.size(); ++k) {
/*  82 */       Event localEvent = (Event)paramVector1.elementAt(k);
/*  83 */       d1 += localEvent.getTime() / paramSMF.getPPQN();
/*     */       Object localObject;
/*  84 */       if (localEvent.getID() == 7) {
/*  85 */         localObject = (PChange)localEvent;
/*  86 */         paramPart.setInstrument(((PChange)localObject).getValue());
/*     */       }
/*  88 */       else if (localEvent.getID() == 16) {
/*  89 */         localObject = (TempoEvent)localEvent;
/*  90 */         paramScore.setTempo(((TempoEvent)localObject).getTempo());
/*  91 */       } else if (localEvent.getID() == 5) {
/*  92 */         localObject = (NoteOn)localEvent;
/*  93 */         paramPart.setChannel(((NoteOn)localObject).getMidiChannel());
/*  94 */         short s1 = ((NoteOn)localObject).getPitch();
/*  95 */         int l = ((NoteOn)localObject).getVelocity();
/*  96 */         short s2 = ((NoteOn)localObject).getMidiChannel();
/*     */ 
/*  98 */         if (l > 0) {
/*  99 */           noteOn(j, arrayOfNote, paramSMF, k, arrayOfDouble, d1, paramVector2, s2, s1, l, paramVector1);
/*     */         }
/*     */ 
/*     */       }
/* 105 */       else if (localEvent instanceof TimeSig)
/*     */       {
/* 107 */         localObject = (TimeSig)localEvent;
/* 108 */         paramScore.setNumerator(((TimeSig)localObject).getNumerator());
/* 109 */         paramScore.setDenominator(((TimeSig)localObject).getDenominator());
/*     */       } else {
/* 111 */         if (!(localEvent instanceof KeySig))
/*     */           continue;
/* 113 */         localObject = (KeySig)localEvent;
/* 114 */         paramScore.setKeySignature(((KeySig)localObject).getKeySig());
/* 115 */         paramScore.setKeyQuality(((KeySig)localObject).getKeyQuality());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void noteOn(int paramInt1, Note[] paramArrayOfNote, SMF paramSMF, int paramInt2, double[] paramArrayOfDouble, double paramDouble, Vector paramVector1, short paramShort1, short paramShort2, int paramInt3, Vector paramVector2)
/*     */   {
/* 144 */     paramInt1 = -1;
/*     */ 
/* 146 */     for (int i = 0; i < paramVector1.size(); ++i)
/*     */     {
/* 148 */       if (paramArrayOfDouble[i] <= paramDouble + 0.08D) {
/* 149 */         paramInt1 = i;
/* 150 */         break;
/*     */       }
/*     */     }
/*     */ 
/* 154 */     if (paramInt1 == -1) {
/* 155 */       paramInt1 = paramVector1.size();
/* 156 */       paramVector1.addElement(new Phrase(paramDouble));
/* 157 */       paramArrayOfDouble[paramInt1] = paramDouble;
/*     */     }
/*     */ 
/* 160 */     if ((paramDouble > paramArrayOfDouble[paramInt1]) && (paramArrayOfNote[paramInt1] != null))
/*     */     {
/* 162 */       d1 = paramDouble - paramArrayOfDouble[paramInt1];
/*     */ 
/* 164 */       if (d1 < 0.25D) {
/* 165 */         double d2 = paramArrayOfNote[paramInt1].getRhythmValue();
/*     */ 
/* 167 */         paramArrayOfNote[paramInt1].setRhythmValue(d2 + d1);
/*     */       }
/*     */       else {
/* 170 */         localNote = new Note(-2147483648, d1, 0);
/* 171 */         localNote.setPan(paramShort1);
/* 172 */         localNote.setDuration(d1);
/* 173 */         localNote.setOffset(0.0D);
/* 174 */         ((Phrase)paramVector1.elementAt(paramInt1)).addNote(localNote);
/*     */       }
/*     */ 
/* 177 */       paramArrayOfDouble[paramInt1] += d1;
/*     */     }
/*     */ 
/* 180 */     double d1 = MidiUtil.getEndEvt(paramShort2, paramVector2, paramInt2) / paramSMF.getPPQN();
/*     */ 
/* 183 */     Note localNote = new Note(paramShort2, d1, paramInt3);
/* 184 */     localNote.setDuration(d1);
/* 185 */     paramArrayOfNote[paramInt1] = localNote;
/* 186 */     ((Phrase)paramVector1.elementAt(paramInt1)).addNote(paramArrayOfNote[paramInt1]);
/* 187 */     paramArrayOfDouble[paramInt1] += paramArrayOfNote[paramInt1].getRhythmValue();
/*     */   }
/*     */ 
/*     */   public static void scoreToSMF(Score paramScore, SMF paramSMF)
/*     */   {
/* 202 */     System.out.println("Converting to SMF data structure...");
/*     */ 
/* 204 */     double d1 = paramScore.getTempo();
/* 205 */     double d2 = 1.0D;
/*     */ 
/* 211 */     Track localTrack1 = new Track();
/* 212 */     localTrack1.addEvent(new TempoEvent(0, paramScore.getTempo()));
/* 213 */     localTrack1.addEvent(new TimeSig(0, paramScore.getNumerator(), paramScore.getDenominator()));
/* 214 */     localTrack1.addEvent(new KeySig(0, paramScore.getKeySignature()));
/* 215 */     localTrack1.addEvent(new EndTrack());
/* 216 */     paramSMF.getTrackList().addElement(localTrack1);
/*     */ 
/* 218 */     int j = 0;
/* 219 */     Enumeration localEnumeration1 = paramScore.getPartList().elements();
/* 220 */     while (localEnumeration1.hasMoreElements()) {
/* 221 */       Track localTrack2 = new Track();
/* 222 */       Part localPart = (Part)localEnumeration1.nextElement();
/* 223 */       System.out.print("    Part " + j + " '" + localPart.getTitle() + "' to SMF Track on Ch. " + localPart.getChannel() + ": ");
/*     */ 
/* 225 */       ++j;
/*     */ 
/* 228 */       if (localPart.getTempo() != -1.0D) d2 = d1 / localPart.getTempo();
/*     */       else {
/* 230 */         d2 = 1.0D;
/*     */       }
/*     */ 
/* 234 */       int i = localPart.getPhraseList().size();
/* 235 */       for (int k = 0; k < i; ++k) {
/* 236 */         Phrase localPhrase1 = (Phrase)localPart.getPhraseList().elementAt(k);
/* 237 */         for (int l = 0; l < i; ++l) {
/* 238 */           Phrase localPhrase2 = (Phrase)localPart.getPhraseList().elementAt(l);
/* 239 */           if (localPhrase2.getStartTime() > localPhrase1.getStartTime()) {
/* 240 */             localPart.getPhraseList().setElementAt(localPhrase2, k);
/* 241 */             localPart.getPhraseList().setElementAt(localPhrase1, l);
/* 242 */             break;
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 248 */       HashMap localHashMap = new HashMap();
/*     */ 
/* 250 */       if (localPart.getTempo() != -1.0D)
/*     */       {
/* 252 */         localHashMap.put(new Double(0.0D), new TempoEvent(localPart.getTempo()));
/*     */       }
/*     */ 
/* 255 */       if (localPart.getInstrument() != -1)
/*     */       {
/* 257 */         localHashMap.put(new Double(0.0D), new PChange((short)localPart.getInstrument(), (short)localPart.getChannel(), 0));
/*     */       }
/*     */ 
/* 260 */       if (localPart.getNumerator() != -2147483648) {
/* 261 */         localHashMap.put(new Double(0.0D), new TimeSig(localPart.getNumerator(), localPart.getDenominator()));
/*     */       }
/*     */ 
/* 264 */       if (localPart.getKeySignature() != -2147483648) {
/* 265 */         localHashMap.put(new Double(0.0D), new KeySig(localPart.getKeySignature(), localPart.getKeyQuality()));
/*     */       }
/*     */ 
/* 268 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 269 */       double d3 = 0.0D;
/* 270 */       double d4 = 0.0D;
/* 271 */       double d5 = 0.0D;
/* 272 */       int i1 = 0;
/* 273 */       double d6 = 1.0D;
/*     */       double d8;
/* 274 */       while (localEnumeration2.hasMoreElements()) {
/* 275 */         localObject = (Phrase)localEnumeration2.nextElement();
/* 276 */         Enumeration localEnumeration3 = ((Phrase)localObject).getNoteList().elements();
/* 277 */         d4 = ((Phrase)localObject).getStartTime() * d2;
/* 278 */         if (((Phrase)localObject).getInstrument() != -1) {
/* 279 */           localHashMap.put(new Double(d4), new PChange((short)((Phrase)localObject).getInstrument(), (short)localPart.getChannel(), 0));
/*     */         }
/* 281 */         if (((Phrase)localObject).getTempo() != -1.0D)
/* 282 */           d6 = d1 * d2 / ((Phrase)localObject).getTempo();
/*     */         else {
/* 284 */           d6 = d2;
/*     */         }
/*     */ 
/* 288 */         int i2 = 0;
/*     */ 
/* 290 */         System.out.print(" Phrase " + (i1++) + ":");
/*     */ 
/* 292 */         d8 = -1.0D;
/* 293 */         resetTicker();
/* 294 */         while (localEnumeration3.hasMoreElements())
/*     */         {
/* 296 */           Note localNote = (Note)localEnumeration3.nextElement();
/* 297 */           d5 = localNote.getOffset();
/*     */ 
/* 299 */           if (localNote.getPan() != d8) {
/* 300 */             d8 = localNote.getPan();
/* 301 */             localHashMap.put(new Double(d4 + d5), new CChange(10, (short)(int)(d8 * 127.0D), (short)localPart.getChannel(), 0));
/*     */           }
/*     */ 
/* 304 */           i4 = 0;
/* 305 */           if (localNote.getPitchType() == true) {
/* 306 */             System.err.println("jMusic warning: converting note frequency to the closest MIDI pitch for SMF.");
/*     */ 
/* 308 */             i4 = Note.freqToMidiPitch(localNote.getFrequency()); } else {
/* 309 */             i4 = localNote.getPitch(); }
/* 310 */           if (i4 != -2147483648) {
/* 311 */             localHashMap.put(new Double(d4 + d5), new NoteOn((short)i4, (short)localNote.getDynamic(), (short)localPart.getChannel(), 0));
/*     */ 
/* 315 */             double d9 = d4 + localNote.getDuration() * d6;
/*     */ 
/* 317 */             localHashMap.put(new Double(d9 + d5), new NoteOn((short)i4, 0, (short)localPart.getChannel(), 0));
/*     */           }
/*     */ 
/* 320 */           d4 += tickRounder(localNote.getRhythmValue() * d6);
/* 321 */           System.out.print(".");
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 359 */       Object localObject = localHashMap.keySet().toArray();
/* 360 */       Arrays.sort(localObject);
/*     */ 
/* 362 */       double d7 = 0.0D;
/*     */ 
/* 365 */       resetTicker();
/* 366 */       for (int i4 = 0; i4 < localObject.length; ++i4) {
/* 367 */         Event localEvent = (Event)localHashMap.get(localObject[i4]);
/* 368 */         d8 = ((Double)localObject[i4]).doubleValue();
/* 369 */         int i3 = (int)((d8 - d7) * paramSMF.getPPQN() * d2 + 0.5D);
/* 370 */         d7 = d8;
/* 371 */         localEvent.setTime(i3);
/* 372 */         localTrack2.addEvent(localEvent);
/*     */       }
/* 374 */       localTrack2.addEvent(new EndTrack());
/*     */ 
/* 376 */       paramSMF.getTrackList().addElement(localTrack2);
/* 377 */       System.out.println();
/*     */     }
/*     */   }
/*     */ 
/*     */   private static boolean zeroVelEventQ(Event paramEvent)
/*     */   {
/* 386 */     return ((paramEvent.getID() == 5) && 
/* 386 */       (((NoteOn)paramEvent).getVelocity() == 0));
/*     */   }
/*     */ 
/*     */   private static void resetTicker()
/*     */   {
/* 395 */     tickRemainder = 0.0D;
/*     */   }
/*     */ 
/*     */   private static double tickRounder(double paramDouble)
/*     */   {
/* 407 */     int i = (int)(paramDouble * 480.0D);
/* 408 */     double d = i * 0.002083333333333333D;
/* 409 */     tickRemainder += paramDouble - d;
/* 410 */     if (tickRemainder > 0.001041666666666667D)
/*     */     {
/* 412 */       d += 0.002083333333333333D;
/* 413 */       tickRemainder -= 0.002083333333333333D;
/*     */     }
/* 415 */     return d;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.MidiParser
 * JD-Core Version:    0.5.3
 */