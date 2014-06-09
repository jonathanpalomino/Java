/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Label;
/*     */ import java.awt.event.WindowAdapter;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.StreamCorruptedException;
/*     */ import jm.midi.MidiParser;
/*     */ import jm.midi.SMF;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public abstract class AbstractReadButton extends Button
/*     */ {
/*     */   protected Frame owner;
/*     */ 
/*     */   public AbstractReadButton()
/*     */   {
/*  60 */     this.owner = null;
/*     */   }
/*     */ 
/*     */   public Score readFile(String paramString1, String paramString2)
/*     */   {
/*  75 */     if ((paramString1 == null) || (paramString2 == null)) {
/*  76 */       return null;
/*     */     }
/*     */ 
/*  79 */     Score localScore = null;
/*  80 */     String str1 = null;
/*     */     FileInputStream localFileInputStream;
/*     */     try
/*     */     {
/*  84 */       SMF localSMF = new SMF();
/*  85 */       localScore = new Score(paramString2);
/*  86 */       localFileInputStream = new FileInputStream(paramString1 + paramString2);
/*  87 */       localSMF.read(localFileInputStream);
/*  88 */       MidiParser.SMFToScore(localScore, localSMF);
/*     */     } catch (IOException localIOException1) {
/*  90 */       str1 = localIOException1.getMessage();
/*  91 */       if (str1 == null)
/*  92 */         str1 = "Unknown IO Exception";
/*  93 */       else if (str1.equals("Track Started in wrong place!!!!  ABORTING"))
/*     */       {
/*  95 */         str1 = "The MIDI file corrupted.  Track data started in the wrong place.";
/*     */       }
/*  97 */       else if (str1.equals("This is NOT a MIDI file !!!")) {
/*     */         try {
/*  99 */           localFileInputStream = new FileInputStream(paramString1 + paramString2);
/*     */ 
/* 101 */           ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
/* 102 */           localScore = (Score)localObjectInputStream.readObject();
/* 103 */           localObjectInputStream.close();
/* 104 */           localFileInputStream.close();
/*     */         } catch (SecurityException localSecurityException) {
/* 106 */           str1 = "Read access not allowed to " + paramString2;
/*     */         } catch (ClassNotFoundException localClassNotFoundException) {
/* 108 */           str1 = "The file " + paramString2 + " is neither a jm or MIDI file";
/*     */         }
/*     */         catch (ClassCastException localClassCastException) {
/* 111 */           str1 = "The file " + paramString2 + " is neither a jm or MIDI file";
/*     */         }
/*     */         catch (StreamCorruptedException localStreamCorruptedException) {
/* 114 */           str1 = "The file " + paramString2 + " is neither a jm or MIDI file";
/*     */         }
/*     */         catch (IOException localIOException2) {
/* 117 */           str1 = localIOException2.getMessage();
/* 118 */           if (str1 == null) {
/* 119 */             str1 = "Unknown Exception";
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 126 */     if (str1 != null) {
/* 127 */       String str2 = str1;
/* 128 */       new Dialog(this.owner, "Not a valid MIDI or jMusic File", true, str2) { private final String val$finalMessage; } .show();
/*     */     }
/*     */ 
/* 142 */     return localScore;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.AbstractReadButton
 * JD-Core Version:    0.5.3
 */