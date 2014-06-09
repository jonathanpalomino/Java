/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.io.PrintStream;
/*     */ import jm.JMC;
/*     */ import jm.gui.cpn.Notate;
/*     */ import jm.gui.histogram.HistogramFrame;
/*     */ import jm.gui.show.ShowScore;
/*     */ import jm.gui.sketch.SketchScore;
/*     */ import jm.gui.wave.WaveView;
/*     */ import jm.music.data.CPhrase;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class View
/*     */   implements JMC
/*     */ {
/*     */   public static void show(Score paramScore)
/*     */   {
/*  50 */     show(paramScore, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void show(Score paramScore, int paramInt1, int paramInt2)
/*     */   {
/*  60 */     new ShowScore(paramScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void show(Part paramPart)
/*     */   {
/*  67 */     show(paramPart, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void show(Part paramPart, int paramInt1, int paramInt2)
/*     */   {
/*  76 */     Score localScore = new Score("Part: " + paramPart.getTitle());
/*  77 */     localScore.addPart(paramPart);
/*  78 */     new ShowScore(localScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void show(CPhrase paramCPhrase)
/*     */   {
/*  86 */     show(paramCPhrase, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void show(CPhrase paramCPhrase, int paramInt1, int paramInt2)
/*     */   {
/*  96 */     Score localScore = new Score("Phrase: " + paramCPhrase.getTitle());
/*  97 */     Part localPart = new Part();
/*  98 */     localPart.addCPhrase(paramCPhrase);
/*  99 */     localScore.addPart(localPart);
/* 100 */     new ShowScore(localScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void show(Phrase paramPhrase)
/*     */   {
/* 108 */     show(paramPhrase, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void show(Phrase paramPhrase, int paramInt1, int paramInt2)
/*     */   {
/* 118 */     Score localScore = new Score("Phrase: " + paramPhrase.getTitle());
/* 119 */     Part localPart = new Part();
/* 120 */     localPart.addPhrase(paramPhrase);
/* 121 */     localScore.addPart(localPart);
/* 122 */     new ShowScore(localScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void notate(Phrase paramPhrase)
/*     */   {
/* 132 */     new Notate(paramPhrase, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void notate(Phrase paramPhrase, int paramInt1, int paramInt2)
/*     */   {
/* 141 */     new Notate(paramPhrase, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void notate(Part paramPart)
/*     */   {
/* 150 */     new Notate(paramPart.getPhrase(0), 0, 0);
/*     */   }
/*     */ 
/*     */   public static void notate(Part paramPart, int paramInt1, int paramInt2)
/*     */   {
/* 161 */     new Notate(paramPart.getPhrase(0), paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void notate(Score paramScore)
/*     */   {
/* 170 */     new Notate(paramScore, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void notate(Score paramScore, int paramInt1, int paramInt2)
/*     */   {
/* 181 */     new Notate(paramScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void sketch(Score paramScore)
/*     */   {
/* 191 */     sketch(paramScore, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void sketch(Score paramScore, int paramInt1, int paramInt2)
/*     */   {
/* 202 */     new SketchScore(paramScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void sketch(Part paramPart)
/*     */   {
/* 209 */     sketch(paramPart, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void sketch(Part paramPart, int paramInt1, int paramInt2)
/*     */   {
/* 220 */     Score localScore = new Score("Part: " + paramPart.getTitle());
/* 221 */     localScore.addPart(paramPart);
/* 222 */     new SketchScore(localScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void sketch(Phrase paramPhrase)
/*     */   {
/* 229 */     sketch(paramPhrase, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void sketch(Phrase paramPhrase, int paramInt1, int paramInt2)
/*     */   {
/* 240 */     Score localScore = new Score("Phrase: " + paramPhrase.getTitle());
/* 241 */     Part localPart = new Part();
/* 242 */     localPart.addPhrase(paramPhrase);
/* 243 */     localScore.addPart(localPart);
/* 244 */     new SketchScore(localScore, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public static void print(Note paramNote)
/*     */   {
/* 254 */     System.out.println(paramNote.toString());
/*     */   }
/*     */ 
/*     */   public static void print(Phrase paramPhrase)
/*     */   {
/* 261 */     System.out.println(paramPhrase.toString());
/*     */   }
/*     */ 
/*     */   public static void print(CPhrase paramCPhrase)
/*     */   {
/* 268 */     System.out.println(paramCPhrase.toString());
/*     */   }
/*     */ 
/*     */   public static void print(Part paramPart)
/*     */   {
/* 275 */     System.out.println(paramPart.toString());
/*     */   }
/*     */ 
/*     */   public static void print(Score paramScore)
/*     */   {
/* 282 */     System.out.println(paramScore.toString());
/*     */   }
/*     */ 
/*     */   public static void histogram()
/*     */   {
/* 292 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to display.", 0);
/* 293 */     localFileDialog.show();
/* 294 */     String str = localFileDialog.getFile();
/* 295 */     if (str != null) {
/* 296 */       Score localScore = new Score();
/* 297 */       Read.midi(localScore, localFileDialog.getDirectory() + str);
/* 298 */       HistogramFrame localHistogramFrame = new HistogramFrame(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static void histogram(Score paramScore)
/*     */   {
/* 307 */     histogram(paramScore, 0);
/*     */   }
/*     */ 
/*     */   public static void histogram(Score paramScore, int paramInt)
/*     */   {
/* 316 */     histogram(paramScore, paramInt, 0, 0);
/*     */   }
/*     */ 
/*     */   public static void histogram(Score paramScore, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 327 */     new HistogramFrame(paramScore, paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */ 
/*     */   public static void au(String paramString)
/*     */   {
/* 343 */     new WaveView(paramString);
/*     */   }
/*     */ 
/*     */   public static void au(String paramString, int paramInt1, int paramInt2)
/*     */   {
/* 353 */     new WaveView(paramString, paramInt1, paramInt2);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.View
 * JD-Core Version:    0.5.3
 */