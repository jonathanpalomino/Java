/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StreamTokenizer;
/*     */ import vena.JMC;
/*     */ import ven.audio.AOException;
/*     */ import ven.audio.AudioObject;
/*     */ import ven.audio.Instrument;
/*     */ 
/*     */ public final class TextIn extends AudioObject
/*     */   implements JMC
/*     */ {
/*  43 */   private boolean DEBUG_AUIn = false;
/*     */   private String fileName;
/*     */   private FileInputStream fis;
/*     */   private StreamTokenizer st;
/*  51 */   public boolean fin = false;
/*     */ 
/*     */   public TextIn(Instrument paramInstrument, String paramString, int paramInt1, int paramInt2)
/*     */   {
/*  63 */     super(paramInstrument, paramInt1, "[TextIn]");
/*  64 */     this.fileName = paramString;
/*     */     try {
/*  66 */       this.fis = new FileInputStream(this.fileName); } catch (IOException localIOException) {
/*  67 */       System.out.println(localIOException);
/*     */     }
/*     */ 
/*  70 */     this.channels = paramInt2;
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/*  85 */     int i = 0;
/*  86 */     int j = 1;
/*  87 */     float f = 0.0F;
/*  88 */     while (j != 0) {
/*     */       try {
/*  90 */         paramArrayOfFloat[(i++)] = this.fis.read();
/*  91 */         if (i >= paramArrayOfFloat.length) j = 0;
/*     */       } catch (IOException localIOException) {
/*  93 */         localIOException.printStackTrace();
/*  94 */         System.exit(1);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 138 */     return i;
/*     */   }
/*     */ 
/*     */   public void finalize() {
/* 142 */     if (this.fis == null) return;
/*     */     try {
/* 144 */       this.fis.close();
/*     */     } catch (IOException localIOException) {
/* 146 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.io.TextIn
 * JD-Core Version:    0.5.3
 */