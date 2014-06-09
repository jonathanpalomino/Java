/*     */ package jm.gui.wave;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.sampled.AudioFileFormat;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.UnsupportedAudioFileException;
/*     */ import jm.JMC;
/*     */ 
/*     */ public class WaveFileReader
/*     */   implements JMC
/*     */ {
/*     */   private File file;
/*     */   private AudioFileFormat fileFormat;
/*     */   private AudioFormat format;
/*     */   private int bits;
/*     */   private String fileType;
/*     */   private boolean cache;
/*     */   private long duration;
/*     */   private InputStream is;
/*  55 */   private boolean wholeFile = false;
/*     */   private boolean bigEndian;
/*     */   private int channels;
/*     */   private int sampleRate;
/*     */ 
/*     */   public WaveFileReader(String paramString)
/*     */   {
/*     */     try
/*     */     {
/*  73 */       this.file = new File(paramString);
/*  74 */       this.fileFormat = AudioSystem.getAudioFileFormat(this.file);
/*  75 */       this.format = this.fileFormat.getFormat();
/*  76 */       this.bigEndian = this.format.isBigEndian();
/*  77 */       this.channels = this.format.getChannels();
/*  78 */       this.sampleRate = (int)this.format.getSampleRate();
/*  79 */       this.duration = (this.fileFormat.getFrameLength() * this.channels);
/*  80 */       this.bits = (this.format.getSampleSizeInBits() / 8);
/*  81 */       this.fileType = this.fileFormat.toString();
/*  82 */       this.is = AudioSystem.getAudioInputStream(this.file);
/*     */     } catch (UnsupportedAudioFileException localUnsupportedAudioFileException) {
/*     */     }
/*     */     catch (IOException localIOException) {
/*  86 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void finalize() {
/*     */     try {
/*  92 */       this.is.close();
/*     */     } catch (IOException localIOException) {
/*  94 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public float[] getSamples(int paramInt1, int paramInt2)
/*     */   {
/* 106 */     float[] arrayOfFloat = new float[paramInt1];
/*     */     try
/*     */     {
/* 109 */       this.is = AudioSystem.getAudioInputStream(this.file);
/*     */ 
/* 111 */       this.is.read(new byte[paramInt2 * this.bits]);
/*     */ 
/* 113 */       byte[] arrayOfByte1 = new byte[this.bits * paramInt1];
/* 114 */       this.is.read(arrayOfByte1);
/*     */ 
/* 116 */       int i = 0; for (int j = 0; i < paramInt1; ++i) {
/* 117 */         byte[] arrayOfByte2 = new byte[this.bits];
/* 118 */         for (int k = 0; k < this.bits; ++k) {
/* 119 */           arrayOfByte2[k] = arrayOfByte1[(j++)];
/*     */         }
/* 121 */         arrayOfFloat[i] = getFloat(arrayOfByte2);
/*     */       }
/*     */     } catch (UnsupportedAudioFileException localUnsupportedAudioFileException) {
/* 124 */       System.out.println("jMusic WaveFileReader error: This file format is not supported.");
/* 125 */       System.exit(0);
/*     */     } catch (IOException localIOException) {
/* 127 */       localIOException.printStackTrace();
/*     */     }
/* 129 */     return arrayOfFloat;
/*     */   }
/*     */ 
/*     */   public int getWaveSize()
/*     */   {
/* 138 */     return (int)(this.duration / this.channels);
/*     */   }
/*     */ 
/*     */   public int getNumOfBytes()
/*     */   {
/* 145 */     return (int)(this.duration * this.bits);
/*     */   }
/*     */ 
/*     */   public int getBits()
/*     */   {
/* 153 */     return this.bits;
/*     */   }
/*     */ 
/*     */   public int getChannels()
/*     */   {
/* 160 */     return this.channels;
/*     */   }
/*     */ 
/*     */   public int getSampleRate()
/*     */   {
/* 167 */     return this.sampleRate;
/*     */   }
/*     */ 
/*     */   public int getBitResolution()
/*     */   {
/* 175 */     int i = -1;
/* 176 */     switch (this.bits)
/*     */     {
/*     */     case 1:
/* 178 */       i = 8;
/* 179 */       break;
/*     */     case 2:
/* 181 */       i = 16;
/* 182 */       break;
/*     */     case 3:
/* 184 */       i = 24;
/* 185 */       break;
/*     */     case 4:
/* 187 */       i = 32;
/*     */     }
/*     */ 
/* 190 */     return i;
/*     */   }
/*     */ 
/*     */   private float getFloat(byte[] paramArrayOfByte)
/*     */   {
/* 197 */     float f = 0.0F;
/* 198 */     int i = 0;
/* 199 */     int j = paramArrayOfByte.length;
/* 200 */     for (int k = 0; k < paramArrayOfByte.length; --j) {
/* 201 */       i |= (paramArrayOfByte[k] & 0xFF) << ((this.bigEndian) ? j : k + 1) * 8 - 8;
/*     */ 
/* 200 */       ++k;
/*     */     }
/*     */ 
/* 203 */     switch (this.bits)
/*     */     {
/*     */     case 1:
/* 205 */       if (i > 127) {
/* 206 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 207 */         i &= 127;
/* 208 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 210 */       f = i / 127.0F;
/* 211 */       break;
/*     */     case 2:
/* 213 */       if (i > 32767) {
/* 214 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 215 */         i &= 32767;
/* 216 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 218 */       f = i / 32767.0F;
/* 219 */       break;
/*     */     case 3:
/* 221 */       if (i > 8388607) {
/* 222 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 223 */         i &= 8388607;
/* 224 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 226 */       f = i / 8388608.0F;
/* 227 */       break;
/*     */     case 4:
/* 229 */       f = i / 2.147484E+009F;
/* 230 */       break;
/*     */     default:
/* 232 */       System.err.println("Format not accepted");
/*     */     }
/* 234 */     return f;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.wave.WaveFileReader
 * JD-Core Version:    0.5.3
 */