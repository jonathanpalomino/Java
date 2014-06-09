/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StreamCorruptedException;
/*     */ import javax.swing.JOptionPane;
/*     */ import jm.JMC;
/*     */ import jm.audio.io.AudioFileIn;
/*     */ import jm.midi.MidiParser;
/*     */ import jm.midi.SMF;
/*     */ import jm.music.data.CPhrase;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class Read
/*     */   implements JMC
/*     */ {
/*     */   public static void midi(Score paramScore)
/*     */   {
/*  77 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to open.", 0);
/*     */ 
/*  80 */     localFileDialog.show();
/*  81 */     if (localFileDialog.getFile() != null)
/*  82 */       midi(paramScore, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public static void midi(Score paramScore, String paramString)
/*     */   {
/*  92 */     if (paramScore == null) {
/*  93 */       System.err.println("jMusic Read.midi error: The score is not initialised! I'm doing it for you.");
/*  94 */       paramScore = new Score();
/*     */     }
/*  96 */     paramScore.empty();
/*  97 */     SMF localSMF = new SMF();
/*  98 */     localSMF.setVerbose(true);
/*     */     try {
/* 100 */       System.out.println("--------------------- Reading MIDI File ---------------------");
/* 101 */       FileInputStream localFileInputStream = new FileInputStream(paramString);
/* 102 */       localSMF.read(localFileInputStream);
/* 103 */       MidiParser.SMFToScore(paramScore, localSMF);
/* 104 */       System.out.println("MIDI file '" + paramString + "' read into score '" + paramScore.getTitle() + "' Tempo = " + paramScore.getTempo());
/* 105 */       System.out.println("-------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 107 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart)
/*     */   {
/* 117 */     Score localScore = new Score();
/* 118 */     midi(localScore);
/* 119 */     paramPart = localScore.getPart(0);
/*     */   }
/*     */ 
/*     */   public static void midi(Part paramPart, String paramString)
/*     */   {
/* 130 */     Score localScore = new Score();
/* 131 */     midi(localScore, paramString);
/* 132 */     paramPart = localScore.getPart(0);
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase)
/*     */   {
/* 141 */     Score localScore = new Score();
/* 142 */     midi(localScore);
/* 143 */     paramPhrase = localScore.getPart(0).getPhrase(0);
/*     */   }
/*     */ 
/*     */   public static void midi(Phrase paramPhrase, String paramString)
/*     */   {
/* 153 */     Part localPart = new Part();
/* 154 */     midi(localPart, paramString);
/* 155 */     paramPhrase = localPart.getPhrase(0);
/*     */   }
/*     */ 
/*     */   public static void midi(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 164 */     Score localScore = new Score();
/* 165 */     midi(localScore, paramString);
/* 166 */     Part localPart = new Part();
/* 167 */     localPart = localScore.getPart(0);
/* 168 */     for (int i = 0; i < localPart.size(); ++i)
/* 169 */       paramCPhrase.addPhrase(localPart.getPhrase(i));
/*     */   }
/*     */ 
/*     */   public static void jm(Score paramScore)
/*     */   {
/* 182 */     jm(paramScore, paramScore.getTitle() + ".jm");
/*     */   }
/*     */ 
/*     */   public static void jm(Score paramScore, String paramString)
/*     */   {
/* 191 */     if (paramScore == null) {
/* 192 */       System.err.println("jMusic Read.jm error: The score is not initialised! I'm doing it for you.");
/* 193 */       paramScore = new Score();
/*     */     }
/* 195 */     paramScore.empty();
/*     */     try {
/* 197 */       System.out.println("--------------------- Reading .jm File ---------------------");
/* 198 */       FileInputStream localFileInputStream = new FileInputStream(paramString);
/* 199 */       ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
/*     */       try {
/* 201 */         paramScore.addPartList(((Score)localObjectInputStream.readObject()).getPartArray());
/* 202 */         System.out.println("reading"); } catch (ClassNotFoundException localClassNotFoundException) {
/* 203 */         System.err.println(localClassNotFoundException); }
/* 204 */       System.out.println("jm file '" + paramString + "' read into score '" + paramScore.getTitle() + "'");
/* 205 */       System.out.println("-------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 207 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void jm(Part paramPart, String paramString)
/*     */   {
/* 217 */     if (paramPart == null) {
/* 218 */       System.err.println("jMusic Read.jm error: The part is not initialised! I'm doing it for you.");
/* 219 */       paramPart = new Part();
/*     */     }
/* 221 */     paramPart.empty();
/* 222 */     Score localScore = new Score();
/* 223 */     jm(localScore, paramString);
/* 224 */     paramPart.addPhraseList(localScore.getPart(0).getPhraseArray());
/*     */   }
/*     */ 
/*     */   public static void jm(Phrase paramPhrase, String paramString)
/*     */   {
/* 234 */     if (paramPhrase == null) {
/* 235 */       System.err.println("jMusic Read.jm error: The phrase is not initialised! I'm doing it for you.");
/* 236 */       paramPhrase = new Phrase();
/*     */     }
/* 238 */     paramPhrase.empty();
/* 239 */     Part localPart = new Part();
/* 240 */     jm(localPart, paramString);
/* 241 */     paramPhrase.addNoteList(localPart.getPhrase(0).getNoteArray());
/*     */   }
/*     */ 
/*     */   public static void jm(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 250 */     if (paramCPhrase == null) {
/* 251 */       System.err.println("jMusic Read.jm error: The CPhrase is not initialised! I'm doing it for you.");
/* 252 */       paramCPhrase = new CPhrase();
/*     */     }
/* 254 */     paramCPhrase.empty();
/* 255 */     Score localScore = new Score();
/* 256 */     jm(localScore, paramString);
/* 257 */     Part localPart = new Part();
/* 258 */     localPart = localScore.getPart(0);
/* 259 */     for (int i = 0; i < localPart.size(); ++i)
/* 260 */       paramCPhrase.addPhrase(localPart.getPhrase(i));
/*     */   }
/*     */ 
/*     */   public static void xml(Score paramScore)
/*     */   {
/* 274 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a jMusic XML file to open.", 0);
/*     */ 
/* 277 */     localFileDialog.show();
/* 278 */     if (localFileDialog.getFile() != null)
/* 279 */       xml(paramScore, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public static void xml(Score paramScore, String paramString)
/*     */   {
/* 289 */     if (paramScore == null) {
/* 290 */       System.err.println("jMusic Read.xml error: The score is not initialised! I'm doing it for you.");
/* 291 */       paramScore = new Score();
/*     */     }
/* 293 */     paramScore.empty();
/*     */     try {
/* 295 */       System.out.println("--------------------- Reading .xml File ---------------------");
/* 296 */       BufferedReader localBufferedReader = new BufferedReader(new FileReader(paramString));
/*     */       try {
/* 298 */         paramScore.addPartList(XMLParser.xmlStringToScore(localBufferedReader.readLine()).getPartArray());
/* 299 */         System.out.println("reading"); } catch (ConversionException localConversionException) {
/* 300 */         System.err.println(localConversionException); }
/* 301 */       System.out.println("xml file '" + paramString + "' read into score '" + paramScore.getTitle() + "'");
/* 302 */       System.out.println("-------------------------------------------------------------");
/*     */     } catch (IOException localIOException) {
/* 304 */       System.err.println(localIOException);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void xml(Part paramPart, String paramString)
/*     */   {
/* 315 */     if (paramPart == null) {
/* 316 */       System.err.println("jMusic Read.xml error: The part is not initialised! I'm doing it for you.");
/* 317 */       paramPart = new Part();
/*     */     }
/* 319 */     paramPart.empty();
/* 320 */     Score localScore = new Score();
/* 321 */     xml(localScore, paramString);
/* 322 */     paramPart.addPhraseList(localScore.getPart(0).getPhraseArray());
/*     */   }
/*     */ 
/*     */   public static void xml(Phrase paramPhrase, String paramString)
/*     */   {
/* 332 */     if (paramPhrase == null) {
/* 333 */       System.err.println("jMusic Read.xml error: The phrase is not initialised! I'm doing it for you.");
/* 334 */       paramPhrase = new Phrase();
/*     */     }
/* 336 */     paramPhrase.empty();
/* 337 */     Part localPart = new Part();
/* 338 */     xml(localPart, paramString);
/* 339 */     paramPhrase.addNoteList(localPart.getPhrase(0).getNoteArray());
/*     */   }
/*     */ 
/*     */   public static void xml(CPhrase paramCPhrase, String paramString)
/*     */   {
/* 348 */     if (paramCPhrase == null) {
/* 349 */       System.err.println("jMusic Read.xml error: The CPhrase is not initialised! I'm doing it for you.");
/* 350 */       paramCPhrase = new CPhrase();
/*     */     }
/* 352 */     paramCPhrase.empty();
/* 353 */     Score localScore = new Score();
/* 354 */     xml(localScore, paramString);
/* 355 */     Part localPart = new Part();
/* 356 */     localPart = localScore.getPart(0);
/* 357 */     for (int i = 0; i < localPart.size(); ++i)
/* 358 */       paramCPhrase.addPhrase(localPart.getPhrase(i));
/*     */   }
/*     */ 
/*     */   public static float[] audio(String paramString)
/*     */   {
/* 367 */     System.out.println("-------------------- Reading Audio File ---------------------");
/* 368 */     AudioFileIn localAudioFileIn = new AudioFileIn(paramString);
/* 369 */     float[] arrayOfFloat = localAudioFileIn.getSampleData();
/* 370 */     System.out.println("File '" + paramString + "' read in. Details:");
/* 371 */     System.out.println("Channels = " + localAudioFileIn.getChannels() + " Samples per channel = " + (localAudioFileIn.getDuration() / localAudioFileIn.getChannels()) + " Sample rate = " + localAudioFileIn.getSampleRate() + " Bit depth = " + localAudioFileIn.getSampleBitDepth());
/*     */ 
/* 375 */     System.out.println("-------------------------------------------------------------");
/* 376 */     return arrayOfFloat;
/*     */   }
/*     */ 
/*     */   public static void audio(float[] paramArrayOfFloat, String paramString) {
/* 380 */     System.out.println("-------------------- Reading Audio File ---------------------");
/* 381 */     AudioFileIn localAudioFileIn = new AudioFileIn(paramString);
/* 382 */     paramArrayOfFloat = localAudioFileIn.getSampleData();
/* 383 */     System.out.println("Audio file '" + paramString + "' read in. Details:");
/* 384 */     System.out.println("Channels = " + localAudioFileIn.getChannels() + " Samples per channel = " + (localAudioFileIn.getDuration() / localAudioFileIn.getChannels()) + " Sample rate = " + localAudioFileIn.getSampleRate() + " Bit depth = " + localAudioFileIn.getSampleBitDepth());
/*     */ 
/* 388 */     System.out.println("-------------------------------------------------------------");
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithNoMessaging(File paramFile)
/*     */   {
/* 544 */     return new JmMidiProcessor(paramFile).getScore();
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithNoMessaging(String paramString1, String paramString2)
/*     */   {
/* 565 */     return new JmMidiProcessor(paramString1, paramString2).getScore();
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithAWTMessaging(File paramFile, Frame paramFrame)
/*     */   {
/* 581 */     JmMidiProcessor localJmMidiProcessor = new JmMidiProcessor(paramFile);
/* 582 */     displayErrorDialog(paramFrame, localJmMidiProcessor.getMessage());
/* 583 */     return localJmMidiProcessor.getScore();
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithAWTMessaging(String paramString1, String paramString2, Frame paramFrame)
/*     */   {
/* 607 */     JmMidiProcessor localJmMidiProcessor = new JmMidiProcessor(paramString1, paramString2);
/* 608 */     displayErrorDialog(paramFrame, localJmMidiProcessor.getMessage());
/* 609 */     return localJmMidiProcessor.getScore();
/*     */   }
/*     */ 
/*     */   private static void displayErrorDialog(Frame paramFrame, String paramString)
/*     */   {
/* 630 */     if (paramString == null) {
/* 631 */       return;
/*     */     }
/* 633 */     Dialog localDialog = new Dialog(paramFrame, "Not a valid MIDI or jMusic File", true);
/*     */ 
/* 635 */     completeErrorDialog(localDialog, paramString);
/*     */   }
/*     */ 
/*     */   private static void completeErrorDialog(Dialog paramDialog, String paramString)
/*     */   {
/* 648 */     paramDialog.add(new Label(paramString), "Center");
/*     */ 
/* 650 */     Button localButton = new Button("OK");
/* 651 */     localButton.addActionListener(new ActionListener(paramDialog) { private final Dialog val$dialog;
/*     */ 
/*     */       public void actionPerformed(ActionEvent paramActionEvent) { this.val$dialog.dispose();
/*     */       }
/*     */     });
/* 657 */     Panel localPanel = new Panel();
/* 658 */     localPanel.add(localButton);
/* 659 */     paramDialog.add(localPanel, "South");
/*     */ 
/* 661 */     paramDialog.addWindowListener(new WindowAdapter(paramDialog) { private final Dialog val$dialog;
/*     */ 
/*     */       public void windowClosing(WindowEvent paramWindowEvent) { this.val$dialog.dispose();
/*     */       }
/*     */     });
/* 667 */     paramDialog.pack();
/* 668 */     paramDialog.show();
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithSwingMessaging(File paramFile, Component paramComponent)
/*     */   {
/* 686 */     JmMidiProcessor localJmMidiProcessor = new JmMidiProcessor(paramFile);
/* 687 */     displayErrorJDialog(paramComponent, localJmMidiProcessor.getMessage());
/* 688 */     return localJmMidiProcessor.getScore();
/*     */   }
/*     */ 
/*     */   public static Score midiOrJmWithSwingMessaging(String paramString1, String paramString2, Component paramComponent)
/*     */   {
/* 712 */     JmMidiProcessor localJmMidiProcessor = new JmMidiProcessor(paramString1, paramString2);
/* 713 */     displayErrorJDialog(paramComponent, localJmMidiProcessor.getMessage());
/* 714 */     return localJmMidiProcessor.getScore();
/*     */   }
/*     */ 
/*     */   private static void displayErrorJDialog(Component paramComponent, String paramString)
/*     */   {
/* 731 */     if (paramString == null) {
/* 732 */       return;
/*     */     }
/* 734 */     JOptionPane.showMessageDialog(paramComponent, paramString, "Not a valid MIDI or jMusic File", 0);
/*     */   }
/*     */ 
/*     */   protected static class JmMidiProcessor
/*     */   {
/* 401 */     private String message = null;
/*     */ 
/* 406 */     private Score score = new Score();
/*     */ 
/*     */     public JmMidiProcessor(File paramFile)
/*     */     {
/* 415 */       if (paramFile == null) {
/* 416 */         this.message = "The selected file is null.  No JM/MIDI information could be imported.";
/*     */ 
/* 418 */         this.score = null;
/* 419 */       } else if (paramFile.isDirectory()) {
/* 420 */         this.message = "The selected file is a directory.  No JM/MIDI information could be imported.";
/*     */ 
/* 422 */         this.score = null;
/*     */       } else {
/* 424 */         JmMidiProcessor localJmMidiProcessor = new JmMidiProcessor(paramFile.getParent() + File.separator, paramFile.getName());
/*     */ 
/* 426 */         this.message = localJmMidiProcessor.getMessage();
/* 427 */         this.score = localJmMidiProcessor.getScore();
/*     */       }
/*     */     }
/*     */ 
/*     */     public JmMidiProcessor(String paramString1, String paramString2)
/*     */     {
/* 440 */       if (paramString2 == null) {
/* 441 */         this.message = "The filename String is null.  No JM/MIDI information could be imported.";
/*     */ 
/* 443 */         this.score = null;
/* 444 */         return;
/*     */       }
/*     */       FileInputStream localFileInputStream;
/*     */       try
/*     */       {
/* 449 */         this.score.setTitle(paramString2);
/* 450 */         SMF localSMF = new SMF();
/* 451 */         if (paramString1 == null) {
/* 452 */           localFileInputStream = new FileInputStream(paramString2);
/* 453 */           localSMF.read(localFileInputStream);
/* 454 */           MidiParser.SMFToScore(this.score, localSMF);
/*     */         } else {
/* 456 */           localFileInputStream = new FileInputStream(paramString1 + paramString2);
/* 457 */           localSMF.read(localFileInputStream);
/* 458 */           MidiParser.SMFToScore(this.score, localSMF);
/*     */         }
/*     */       } catch (IOException localIOException1) {
/* 461 */         this.message = localIOException1.getMessage();
/* 462 */         if (this.message == null) {
/* 463 */           this.message = "Unknown IO Exception";
/* 464 */           this.score = null;
/* 465 */           return; }
/* 466 */         if (this.message.equals("Track Started in wrong place!!!!  ABORTING"))
/*     */         {
/* 468 */           this.message = "The MIDI file corrupted.  Track data started in the wrong place.";
/*     */ 
/* 470 */           this.score = null;
/* 471 */           return; }
/* 472 */         if (this.message.equals("This is NOT a MIDI file !!!")) {
/*     */           try {
/* 474 */             localFileInputStream = new FileInputStream(paramString1 + paramString2);
/*     */ 
/* 476 */             ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
/* 477 */             this.score = ((Score)localObjectInputStream.readObject());
/* 478 */             localObjectInputStream.close();
/* 479 */             localFileInputStream.close();
/*     */           } catch (SecurityException localSecurityException) {
/* 481 */             this.message = "Read access not allowed to " + paramString2;
/* 482 */             this.score = null;
/* 483 */             return;
/*     */           } catch (ClassNotFoundException localClassNotFoundException) {
/* 485 */             this.message = "The file " + paramString2 + " is neither a jm nor a MIDI file";
/*     */ 
/* 487 */             this.score = null;
/* 488 */             return;
/*     */           } catch (ClassCastException localClassCastException) {
/* 490 */             this.message = "The file " + paramString2 + " is neither a jm nor a MIDI file";
/*     */ 
/* 492 */             this.score = null;
/* 493 */             return;
/*     */           } catch (StreamCorruptedException localStreamCorruptedException) {
/* 495 */             this.message = "The file " + paramString2 + " is neither a jm nor a MIDI file";
/*     */ 
/* 497 */             this.score = null;
/* 498 */             return;
/*     */           } catch (IOException localIOException2) {
/* 500 */             this.message = localIOException2.getMessage();
/* 501 */             if (this.message == null) {
/* 502 */               this.message = "Unknown Exception.  No musical information could be imported.";
/*     */             }
/*     */ 
/* 505 */             this.score = null;
/* 506 */             return;
/*     */           }
/*     */         } else {
/* 509 */           this.score = null;
/* 510 */           return;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     public Score getScore()
/*     */     {
/* 521 */       return this.score;
/*     */     }
/*     */ 
/*     */     public String getMessage()
/*     */     {
/* 530 */       return this.message;
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.Read
 * JD-Core Version:    0.5.3
 */