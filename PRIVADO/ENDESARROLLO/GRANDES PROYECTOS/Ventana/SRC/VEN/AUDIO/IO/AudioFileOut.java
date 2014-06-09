/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
          import javax.sound.sampled.AudioFileFormat;
/*     */ import javax.sound.sampled.AudioFileFormat.Type;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioInputStream;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ 
/*     */ public class AudioFileOut
/*     */ {
/*     */   private String fileName;
/*     */   private File file;
/*     */   private AudioFileFormat.Type fileType;
/*     */   private AudioFormat format;
/*     */   private boolean bigEndian;
/*     */   private int channels;
/*     */   private int sampleRate;
/*     */   private long duration;
/*     */   private int sampleSize;
/*     */   private AudioInputStream ais;
/*     */   private float[] sampleData;
/*     */ 
/*     */   public AudioFileOut(float[] paramArrayOfFloat, String paramString)
/*     */   {
/*  66 */     this(paramArrayOfFloat, paramString, 1, 44100, 16);
/*     */   }
/*     */ 
/*     */   public AudioFileOut(float[] paramArrayOfFloat, String paramString, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  46 */     this.fileType = AudioFileFormat.Type.AU;
/*     */ 
/*  71 */     this.sampleData = paramArrayOfFloat;
/*  72 */     this.duration = paramArrayOfFloat.length;
/*  73 */     this.fileName = paramString;
/*  74 */     this.channels = paramInt1;
/*  75 */     this.sampleRate = paramInt2;
/*  76 */     this.sampleSize = (paramInt3 / 8);
/*     */ 
/*  78 */     if (paramString.endsWith(".au")) {
/*  79 */       this.fileType = AudioFileFormat.Type.AU;
/*  80 */       this.bigEndian = true;
/*  81 */     } else if (paramString.endsWith(".wav")) {
/*  82 */       this.fileType = AudioFileFormat.Type.WAVE;
/*  83 */       this.bigEndian = false;
/*  84 */     } else if ((paramString.endsWith(".aif")) || (paramString.endsWith(".aiff"))) {
/*  85 */       this.fileType = AudioFileFormat.Type.AIFF;
/*  86 */       this.bigEndian = true;
/*     */     } else {
/*  88 */       paramString = paramString + ".au";
/*  89 */       this.bigEndian = true;
/*     */     }
/*     */ 
/*  92 */     this.file = new File(this.fileName);
/*     */ 
/*  94 */     byte[] arrayOfByte = new byte[paramArrayOfFloat.length * this.sampleSize];
/*  95 */     for (int i = 0; i < paramArrayOfFloat.length; ++i) {
/*  96 */       int j = -1;
/*  97 */       switch (this.sampleSize)
/*     */       {
/*     */       case 1:
/*  99 */         arrayOfByte[i] = new Float(paramArrayOfFloat[i] * 127.0F).byteValue();
/* 100 */         break;
/*     */       case 2:
/* 102 */         int k = new Float(paramArrayOfFloat[i] * 32767.0F).shortValue();
/* 103 */         if (this.bigEndian) {
/* 104 */           arrayOfByte[(i * 2)] = (byte)((k & 0xFF00) >> 8);
/* 105 */           arrayOfByte[(i * 2 + 1)] = (byte)(k & 0xFF);
/*     */         } else {
/* 107 */           arrayOfByte[(i * 2)] = (byte)(k & 0xFF);
/* 108 */           arrayOfByte[(i * 2 + 1)] = (byte)((k & 0xFF00) >> 8);
/*     */         }
/* 110 */         break;
/*     */       case 3:
/* 112 */         j = new Float(paramArrayOfFloat[i] * 8388608.0F).intValue();
/* 113 */         if (this.bigEndian) {
/* 114 */           arrayOfByte[(i * 3)] = (byte)((j & 0xFF0000) >> 16);
/* 115 */           arrayOfByte[(i * 3 + 1)] = (byte)((j & 0xFF00) >> 8);
/* 116 */           arrayOfByte[(i * 3 + 2)] = (byte)(j & 0xFF);
/*     */         } else {
/* 118 */           arrayOfByte[(i * 3)] = (byte)(j & 0xFF);
/* 119 */           arrayOfByte[(i * 3 + 1)] = (byte)((j & 0xFF00) >> 8);
/* 120 */           arrayOfByte[(i * 3 + 2)] = (byte)((j & 0xFF0000) >> 16);
/*     */         }
/* 122 */         break;
/*     */       case 4:
/* 124 */         j = new Float(paramArrayOfFloat[i] * 2.147484E+009F).intValue();
/* 125 */         if (this.bigEndian) {
/* 126 */           arrayOfByte[(i * 4)] = (byte)((j & 0xFF000000) >> 24);
/* 127 */           arrayOfByte[(i * 4 + 1)] = (byte)((j & 0xFF0000) >> 16);
/* 128 */           arrayOfByte[(i * 4 + 2)] = (byte)((j & 0xFF00) >> 8);
/* 129 */           arrayOfByte[(i * 4 + 3)] = (byte)(j & 0xFF);
/*     */         } else {
/* 131 */           arrayOfByte[(i * 4)] = (byte)(j & 0xFF);
/* 132 */           arrayOfByte[(i * 4 + 1)] = (byte)((j & 0xFF00) >> 8);
/* 133 */           arrayOfByte[(i * 4 + 2)] = (byte)((j & 0xFF0000) >> 16);
/* 134 */           arrayOfByte[(i * 4 + 3)] = (byte)((j & 0xFF000000) >> 24);
/*     */         }
/* 136 */         break;
/*     */       default:
/* 138 */         System.err.println("jMusic AudioFileOut error: " + paramInt3 + " bit audio output file format not supported, sorry :(");
/*     */ 
/* 141 */         System.exit(0);
/*     */       }
/*     */     }
/* 144 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/*     */ 
/* 146 */     this.format = new AudioFormat(this.sampleRate, paramInt3, this.channels, true, this.bigEndian);
/* 147 */     AudioInputStream localAudioInputStream = new AudioInputStream(localByteArrayInputStream, this.format, this.duration / this.channels);
/*     */     try
/*     */     {
/* 150 */       AudioSystem.write(localAudioInputStream, this.fileType, this.file);
/*     */     } catch (IOException localIOException) {
/* 152 */       System.out.println("error writing audio file.");
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.io.AudioFileOut
 * JD-Core Version:    0.5.3
 */