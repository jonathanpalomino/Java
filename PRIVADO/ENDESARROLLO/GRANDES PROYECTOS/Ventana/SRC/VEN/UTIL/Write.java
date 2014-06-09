/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.audio.Audio;
/*     */ import jm.audio.Instrument;
/*     */ import jm.audio.io.AudioFileOut;
/*     */ import jm.midi.MidiParser;
/*     */ import jm.midi.SMF;
/*     */ import jm.music.data.CPhrase;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class Write
/*     */   implements JMC
/*     */ {
/*     */   public static void midi(Score paramScore)
/*     */   {
/*  65 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Save as a MIDI file ...", 1);
/*     */ 
/*  68 */     localFileDialog.setFile("jMusic_composition.mid");
/*  69 */     localFileDialog.show();
/*  70 */     if (localFileDialog.getFile() != null)
/*  71 */       midi(paramScore, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public static void midi(Score paramScore, OutputStream paramOutputStream)
/*     */   {
/*  83 */     SMF localSMF = new SMF();
/*     */     try {
/*  85 */       localSMF.clearTracks();
/*  86 */       MidiParser.scoreToSMF(paramScore, localSMF);
/*  87 */       localSMF.write(paramOutputStream);
/*     */     } catch (IOException localIOException) {
/*  89 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void midi(Score paramScore, String paramString)
/*     */   {
/* 100 */     SMF localSMF = new SMF();
/*     */     try {
/* 102 */       double d1 = System.currentTimeMillis();
/* 103 */       System.out.println("----------------------------- Writing MIDI File ------------------------------");
/* 104 */       localSMF.clearTracks();
/* 105 */       MidiParser.scoreToSMF(paramScore, localSMF);
/* 106 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 107 */       localSMF.write(localFileOutputStream);
/* 108 */       double d2 = System.currentTimeMillis();
/* 109 */       System.out.println("MIDI file '" + paramString + "' written from score '" + paramScore.getTitle() + "' in " + ((d2 - d1) / 1000.0D) + " seconds.");
/*     */ 
/* 111 */       System.out.println("------------------------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 113 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart)
/*     */   {
/* 123 */     midi(new Score(paramPart));
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart, String paramString)
/*     */   {
/* 132 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 133 */     localScore.addPart(paramPart);
/* 134 */     midi(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase)
/*     */   {
/* 143 */     midi(new Score(new Part(paramPhrase)));
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase, String paramString)
/*     */   {
/* 152 */     Part localPart = new Part();
/* 153 */     localPart.addPhrase(paramPhrase);
/* 154 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 155 */     localScore.addPart(localPart);
/* 156 */     midi(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void midi(CPhrase paramCPhrase)
/*     */   {
/* 165 */     Part localPart = new Part();
/* 166 */     localPart.addCPhrase(paramCPhrase);
/* 167 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 168 */     localScore.addPart(localPart);
/* 169 */     midi(localScore, paramCPhrase.getTitle() + ".mid");
/*     */   }
/*     */ 
/*     */   public static void midi(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 178 */     Part localPart = new Part();
/* 179 */     localPart.addCPhrase(paramCPhrase);
/* 180 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 181 */     localScore.addPart(localPart);
/* 182 */     midi(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void midi(Note paramNote)
/*     */   {
/* 191 */     midi(paramNote, "SingleNote.mid");
/*     */   }
/*     */ 
/*     */   public static void midi(Note paramNote, String paramString)
/*     */   {
/* 200 */     Score localScore = new Score("Score of a single note");
/* 201 */     Part localPart = new Part(new Phrase(paramNote));
/* 202 */     localScore.addPart(localPart);
/* 203 */     midi(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void jm(Score paramScore)
/*     */   {
/* 216 */     jm(paramScore, paramScore.getTitle() + ".jm");
/*     */   }
/*     */ 
/*     */   public static void jm(Score paramScore, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 226 */       System.out.println("--------------------- Writing JM File -----------------------");
/* 227 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
/* 228 */       ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
/* 229 */       localObjectOutputStream.writeObject(paramScore);
/* 230 */       localObjectOutputStream.flush();
/* 231 */       localObjectOutputStream.close();
/* 232 */       System.out.println("JM file '" + paramString + "' written from score '" + paramScore.getTitle() + "'");
/* 233 */       System.out.println("-------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 235 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void jm(Part paramPart)
/*     */   {
/* 245 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 246 */     localScore.addPart(paramPart);
/* 247 */     jm(localScore, paramPart.getTitle() + ".jm");
/*     */   }
/*     */ 
/*     */   public static void jm(Part paramPart, String paramString)
/*     */   {
/* 256 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 257 */     localScore.addPart(paramPart);
/* 258 */     jm(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void jm(Phrase paramPhrase)
/*     */   {
/* 267 */     Part localPart = new Part();
/* 268 */     localPart.addPhrase(paramPhrase);
/* 269 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 270 */     localScore.addPart(localPart);
/* 271 */     jm(localScore, paramPhrase.getTitle() + ".jm");
/*     */   }
/*     */ 
/*     */   public static void jm(Phrase paramPhrase, String paramString)
/*     */   {
/* 280 */     Part localPart = new Part();
/* 281 */     localPart.addPhrase(paramPhrase);
/* 282 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 283 */     localScore.addPart(localPart);
/* 284 */     jm(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void jm(CPhrase paramCPhrase)
/*     */   {
/* 293 */     Part localPart = new Part();
/* 294 */     localPart.addCPhrase(paramCPhrase);
/* 295 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 296 */     localScore.addPart(localPart);
/* 297 */     jm(localScore, paramCPhrase.getTitle() + ".jm");
/*     */   }
/*     */ 
/*     */   public static void jm(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 306 */     Part localPart = new Part();
/* 307 */     localPart.addCPhrase(paramCPhrase);
/* 308 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 309 */     localScore.addPart(localPart);
/* 310 */     jm(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void au(Score paramScore, Instrument paramInstrument)
/*     */   {
/* 322 */     Instrument[] arrayOfInstrument = { paramInstrument };
/* 323 */     au(paramScore, paramScore.getTitle() + ".au", arrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Score paramScore, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 332 */     au(paramScore, paramScore.getTitle() + ".au", paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Score paramScore, String paramString, Instrument paramInstrument)
/*     */   {
/* 342 */     Instrument[] arrayOfInstrument = { paramInstrument };
/* 343 */     au(paramScore, paramString, arrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Score paramScore, String paramString, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 353 */     double d1 = System.currentTimeMillis();
/* 354 */     System.out.println("------------------------------ Writing AU File --------------------------------");
/* 355 */     String str1 = paramString + ".jpf";
/* 356 */     String str2 = "jmusic.tmp";
/* 357 */     File localFile = new File(str2);
/* 358 */     if (localFile.exists()) localFile.delete();
/* 359 */     Audio.processScore(paramScore, paramArrayOfInstrument, str1);
/*     */ 
/* 361 */     Audio.combine(str1, str2, paramString, true, true);
/* 362 */     double d2 = System.currentTimeMillis();
/* 363 */     System.out.println("AU file '" + paramString + "' written from score '" + paramScore.getTitle() + "' in " + ((d2 - d1) / 1000.0D) + " seconds.");
/* 364 */     System.out.println("-------------------------------------------------------------------------------");
/*     */   }
/*     */ 
/*     */   public static void au(Part paramPart, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 373 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 374 */     localScore.addPart(paramPart);
/* 375 */     au(localScore, paramPart.getTitle() + ".au", paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Part paramPart, String paramString, Instrument paramInstrument)
/*     */   {
/* 385 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 386 */     localScore.addPart(paramPart);
/* 387 */     Instrument[] arrayOfInstrument = { paramInstrument };
/* 388 */     au(localScore, paramString, arrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Part paramPart, String paramString, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 398 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 399 */     localScore.addPart(paramPart);
/* 400 */     au(localScore, paramString, paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Phrase paramPhrase, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 409 */     Part localPart = new Part();
/* 410 */     localPart.addPhrase(paramPhrase);
/* 411 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 412 */     localScore.addPart(localPart);
/* 413 */     au(localScore, paramPhrase.getTitle() + ".au", paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Phrase paramPhrase, String paramString, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 423 */     Part localPart = new Part();
/* 424 */     localPart.addPhrase(paramPhrase);
/* 425 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 426 */     localScore.addPart(localPart);
/* 427 */     au(localScore, paramString, paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(Phrase paramPhrase, String paramString, Instrument paramInstrument)
/*     */   {
/* 437 */     Part localPart = new Part();
/* 438 */     localPart.addPhrase(paramPhrase);
/* 439 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 440 */     localScore.addPart(localPart);
/* 441 */     Instrument[] arrayOfInstrument = { paramInstrument };
/* 442 */     au(localScore, paramString, arrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(CPhrase paramCPhrase, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 451 */     Part localPart = new Part();
/* 452 */     localPart.addCPhrase(paramCPhrase);
/* 453 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 454 */     localScore.addPart(localPart);
/* 455 */     au(localScore, paramCPhrase.getTitle() + ".au", paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void au(CPhrase paramCPhrase, String paramString, Instrument[] paramArrayOfInstrument)
/*     */   {
/* 465 */     Part localPart = new Part();
/* 466 */     localPart.addCPhrase(paramCPhrase);
/* 467 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 468 */     localScore.addPart(localPart);
/* 469 */     au(localScore, paramString, paramArrayOfInstrument);
/*     */   }
/*     */ 
/*     */   public static void audio(float[] paramArrayOfFloat, String paramString)
/*     */   {
/* 484 */     audio(paramArrayOfFloat, paramString, 1, 44100, 16);
/*     */   }
/*     */ 
/*     */   public static void audio(float[] paramArrayOfFloat, String paramString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 498 */     double d1 = System.currentTimeMillis();
/* 499 */     System.out.println("---------------------------- Writing Audio File -------------------------------");
/* 500 */     AudioFileOut localAudioFileOut = new AudioFileOut(paramArrayOfFloat, paramString, paramInt1, paramInt2, paramInt3);
/*     */ 
/* 502 */     double d2 = System.currentTimeMillis();
/* 503 */     System.out.println("Audio file '" + paramString + "' written in " + ((d2 - d1) / 1000.0D) + " seconds.");
/* 504 */     System.out.println("Channels = " + paramInt1 + " Sample rate = " + paramInt2 + " Bit depth = " + paramInt3);
/*     */ 
/* 507 */     System.out.println("-------------------------------------------------------------------------------");
/*     */   }
/*     */ 
/*     */   public static void xml(Score paramScore)
/*     */   {
/* 519 */     xml(paramScore, paramScore.getTitle() + ".xml");
/*     */   }
/*     */ 
/*     */   public static void xml(Score paramScore, String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 529 */       PrintWriter localPrintWriter = new PrintWriter(new FileWriter(paramString));
/* 530 */       System.out.println("--------------------- Writing XML File -----------------------");
/* 531 */       String str = XMLParser.scoreToXMLString(paramScore);
/*     */ 
/* 533 */       localPrintWriter.print(str);
/* 534 */       localPrintWriter.close();
/* 535 */       System.out.println("XML file '" + paramString + "' written from score '" + paramScore.getTitle() + "'");
/* 536 */       System.out.println("-------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 538 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void xml(Part paramPart)
/*     */   {
/* 549 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 550 */     localScore.addPart(paramPart);
/* 551 */     xml(localScore, paramPart.getTitle() + ".xml");
/*     */   }
/*     */ 
/*     */   public static void xml(Part paramPart, String paramString)
/*     */   {
/* 560 */     Score localScore = new Score("Score of " + paramPart.getTitle());
/* 561 */     localScore.addPart(paramPart);
/* 562 */     xml(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void xml(Phrase paramPhrase)
/*     */   {
/* 571 */     Part localPart = new Part();
/* 572 */     localPart.addPhrase(paramPhrase);
/* 573 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 574 */     localScore.addPart(localPart);
/* 575 */     xml(localScore, paramPhrase.getTitle() + ".xml");
/*     */   }
/*     */ 
/*     */   public static void xml(Phrase paramPhrase, String paramString)
/*     */   {
/* 584 */     Part localPart = new Part();
/* 585 */     localPart.addPhrase(paramPhrase);
/* 586 */     Score localScore = new Score("Score of " + paramPhrase.getTitle());
/* 587 */     localScore.addPart(localPart);
/* 588 */     xml(localScore, paramString);
/*     */   }
/*     */ 
/*     */   public static void xml(CPhrase paramCPhrase)
/*     */   {
/* 597 */     Part localPart = new Part();
/* 598 */     localPart.addCPhrase(paramCPhrase);
/* 599 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 600 */     localScore.addPart(localPart);
/* 601 */     xml(localScore, paramCPhrase.getTitle() + ".xml");
/*     */   }
/*     */ 
/*     */   public static void xml(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 610 */     Part localPart = new Part();
/* 611 */     localPart.addCPhrase(paramCPhrase);
/* 612 */     Score localScore = new Score("Score of " + paramCPhrase.getTitle());
/* 613 */     localScore.addPart(localPart);
/* 614 */     xml(localScore, paramString);
/*     */   }
/*     */ 
/*     */   private static Score adjustTempo(Score paramScore)
/*     */   {
/* 622 */     Enumeration localEnumeration1 = paramScore.getPartList().elements();
/* 623 */     double d1 = 60.0D / paramScore.getTempo();
/* 624 */     while (localEnumeration1.hasMoreElements()) {
/* 625 */       Part localPart = (Part)localEnumeration1.nextElement();
/* 626 */       double d2 = d1;
/* 627 */       if (localPart.getTempo() != 0.0D) d2 = 60.0D / localPart.getTempo();
/* 628 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 629 */       while (localEnumeration2.hasMoreElements()) {
/* 630 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 631 */         Enumeration localEnumeration3 = localPhrase.getNoteList().elements();
/* 632 */         while (localEnumeration3.hasMoreElements()) {
/* 633 */           Note localNote = (Note)localEnumeration3.nextElement();
/* 634 */           localNote.setRhythmValue(localNote.getRhythmValue() * d2);
/* 635 */           localNote.setDuration(localNote.getDuration() * d2);
/*     */         }
/*     */       }
/*     */     }
/* 639 */     return paramScore;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.Write
 * JD-Core Version:    0.5.3
 */