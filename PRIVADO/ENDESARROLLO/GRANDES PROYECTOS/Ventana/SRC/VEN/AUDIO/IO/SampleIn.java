/*     */ package ven.audio.io;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.sampled.AudioFileFormat;
/*     */ import javax.sound.sampled.AudioFormat;
/*     */ import javax.sound.sampled.AudioSystem;
/*     */ import javax.sound.sampled.UnsupportedAudioFileException;
/*     */ import vena.JMC;
/*     */ import ven.audio.AOException;
/*     */ import ven.audio.AudioObject;
/*     */ import ven.audio.Instrument;
/*     */ import ven.music.data.Note;
/*     */ 
/*     */ public class SampleIn extends AudioObject
/*     */   implements JMC
/*     */ {
/*     */   private File file;
/*     */   private AudioFileFormat fileFormat;
/*     */   private AudioFormat format;
/*     */   private int sampleSize;
/*     */   private String fileType;
/*     */   private boolean cache;
/*     */   private long duration;
/*     */   private InputStream is;
/*     */   private boolean wholeFile;
/*     */   private int loop;
/*     */   private int loopCount;
/*     */   private int loopStart;
/*     */   private int loopEnd;
/*     */   private int streamPosition;
/*     */   private boolean bigEndian;
/*     */ 
/*     */   public SampleIn(Instrument paramInstrument, String paramString)
/*     */   {
/*  76 */     this(paramInstrument, paramString, false);
/*     */   }
/*     */ 
/*     */   public SampleIn(Instrument paramInstrument, String paramString, boolean paramBoolean)
/*     */   {
/*  87 */     this(paramInstrument, paramString, paramBoolean, false);
/*     */   }
/*     */ 
/*     */   public SampleIn(Instrument paramInstrument, String paramString, boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 100 */     this(paramInstrument, paramString, paramBoolean1, paramBoolean2, 0);
/*     */   }
/*     */ 
/*     */   public SampleIn(Instrument paramInstrument, String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
/*     */   {
/* 116 */     this(paramInstrument, paramString, paramBoolean1, paramBoolean2, 0, 0, 0);
/*     */   }
/*     */ 
/*     */   public SampleIn(Instrument paramInstrument, String paramString, boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 134 */     super(paramInstrument, 0, "[SampleIn]");
/*     */ 
/*  57 */     this.wholeFile = false;
/*     */     try
/*     */     {
/* 136 */       this.file = new File(paramString);
/* 137 */       this.cache = paramBoolean1;
/* 138 */       this.wholeFile = paramBoolean2;
/* 139 */       this.loop = paramInt1;
/* 140 */       this.loopStart = paramInt2;
/* 141 */       this.loopEnd = paramInt3;
/* 142 */       if (this.loop == -1) this.loop = 2147483647;
/* 143 */       this.fileFormat = AudioSystem.getAudioFileFormat(this.file);
/* 144 */       this.format = this.fileFormat.getFormat();
/* 145 */       this.bigEndian = this.format.isBigEndian();
/* 146 */       this.channels = this.format.getChannels();
/* 147 */       this.sampleRate = (int)this.format.getSampleRate();
/* 148 */       this.duration = (this.fileFormat.getFrameLength() * this.channels);
/*     */ 
/* 151 */       this.sampleSize = (this.format.getSampleSizeInBits() / 8);
/* 152 */       this.fileType = this.fileFormat.toString();
/* 153 */       this.is = AudioSystem.getAudioInputStream(this.file);
/* 154 */       if (this.cache) {
/* 155 */         byte[] arrayOfByte = new byte[(int)this.duration * this.sampleSize];
/* 156 */         this.is.read(arrayOfByte);
/* 157 */         this.is.close();
/* 158 */         this.is = new ByteArrayInputStream(arrayOfByte);
/*     */       }
/*     */     } catch (UnsupportedAudioFileException localUnsupportedAudioFileException) {
/*     */     }
/*     */     catch (IOException localIOException) {
/* 163 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void finalize() {
/*     */     try {
/* 169 */       this.is.close();
/*     */     } catch (IOException localIOException) {
/* 171 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
    public void build() {
	if (!wholeFile)
	    duration = (long) (currentNote.getDuration() * (double) sampleRate
			       * (double) channels);
	loopCount = loop;
	reset(0);
    }
/*     */ 
/*     */   public void reset(int paramInt)
/*     */   {
/* 192 */     this.streamPosition = 0;
/*     */     try {
/* 194 */       if (this.cache) {
/* 195 */         this.is.reset();
/*     */       } else {
/* 197 */         this.is = AudioSystem.getAudioInputStream(this.file);
/* 198 */         if (paramInt > 0) this.is.read(new byte[paramInt * this.sampleSize * this.channels]);
/*     */       }
/*     */     } catch (UnsupportedAudioFileException localUnsupportedAudioFileException) {
/* 201 */       System.out.println("jMusic SampleIn error: This file format is not supported.");
/* 202 */       System.exit(0);
/*     */     } catch (IOException localIOException) {
/* 204 */       localIOException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int work(float[] paramArrayOfFloat)
/*     */     throws AOException
/*     */   {
/* 216 */     this.finished = false;
/* 217 */     byte[] arrayOfByte1 = new byte[this.sampleSize * this.channels];
/* 218 */     byte[] arrayOfByte2 = new byte[this.sampleSize];
/* 219 */     for (int i = 0; i < paramArrayOfFloat.length - this.channels; i += this.channels) {
/*     */       try
/*     */       {
/* 222 */         if (this.is.read(arrayOfByte1) == -1) {
/* 223 */           this.finished = true;
/*     */         }
/*     */         else
/* 226 */           for (int j = 0; j < this.channels; ++j)
/*     */           {
/* 228 */             for (int k = 0; k < this.sampleSize; ++k) {
/* 229 */               arrayOfByte2[k] = arrayOfByte1[(k + j * this.sampleSize)];
/*     */             }
/*     */ 
/* 232 */             paramArrayOfFloat[(i + j)] = getFloat(arrayOfByte2);
/*     */ 
/* 235 */             if ((++this.streamPosition == this.loopStart) && (this.loop > 0))
/* 236 */               this.is.mark(this.loopStart);
/* 237 */             else if ((this.streamPosition == this.loopEnd) && (this.loop > 0)) {
/* 238 */               if (--this.loopCount >= 1) {
/* 239 */                 reset(this.loopStart);
/* 240 */                 this.streamPosition = this.loopStart;
/*     */               }
/*     */             }
/* 243 */             if (this.streamPosition >= this.duration)
/* 244 */               this.finished = true;
/*     */           }
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/* 249 */         localIOException.printStackTrace();
/*     */       }
/*     */     }
/* 252 */     return paramArrayOfFloat.length;
/*     */   }
/*     */ 
/*     */   public void setWholeFile(boolean paramBoolean)
/*     */   {
/* 263 */     this.wholeFile = paramBoolean;
/*     */   }
/*     */ 
/*     */   public int getWaveSize()
/*     */   {
/* 271 */     return (int)(this.duration / this.channels);
/*     */   }
/*     */ 
/*     */   public int getNumOfBytes()
/*     */   {
/* 278 */     return (int)(this.duration * this.sampleSize);
/*     */   }
/*     */ 
/*     */   public int getBits()
/*     */   {
/* 287 */     return this.sampleSize;
/*     */   }
/*     */ 
/*     */   public int getSampleSize()
/*     */   {
/* 295 */     return this.sampleSize;
/*     */   }
/*     */ 
/*     */   public int getBitResolution()
/*     */   {
/* 304 */     int i = -1;
/* 305 */     switch (this.sampleSize)
/*     */     {
/*     */     case 1:
/* 307 */       i = 8;
/* 308 */       break;
/*     */     case 2:
/* 310 */       i = 16;
/* 311 */       break;
/*     */     case 3:
/* 313 */       i = 24;
/* 314 */       break;
/*     */     case 4:
/* 316 */       i = 32;
/*     */     }
/*     */ 
/* 319 */     return i;
/*     */   }
/*     */ 
/*     */   private float getFloat(byte[] paramArrayOfByte)
/*     */   {
/* 326 */     float f = 0.0F;
/* 327 */     int i = 0;
/* 328 */     int j = paramArrayOfByte.length;
/* 329 */     for (int k = 0; k < paramArrayOfByte.length; --j) {
/* 330 */       i |= (paramArrayOfByte[k] & 0xFF) << ((this.bigEndian) ? j : k + 1) * 8 - 8;
/*     */ 
/* 329 */       ++k;
/*     */     }
/*     */ 
/* 332 */     switch (this.sampleSize)
/*     */     {
/*     */     case 1:
/* 334 */       if (i > 127) {
/* 335 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 336 */         i &= 127;
/* 337 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 339 */       f = i / 127.0F;
/* 340 */       break;
/*     */     case 2:
/* 342 */       if (i > 32767) {
/* 343 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 344 */         i &= 32767;
/* 345 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 347 */       f = i / 32767.0F;
/* 348 */       break;
/*     */     case 3:
/* 350 */       if (i > 8388607) {
/* 351 */         i = (i ^ 0xFFFFFFFF) + 1;
/* 352 */         i &= 8388607;
/* 353 */         i = (i ^ 0xFFFFFFFF) + 1;
/*     */       }
/* 355 */       f = i / 8388608.0F;
/* 356 */       break;
/*     */     case 4:
/* 358 */       f = (float)(i / 2147483647.0D);
/* 359 */       break;
/*     */     default:
/* 361 */       System.err.println("Format not accepted");
/*     */     }
/* 363 */     return f;
/*     */   }
/*     */ 
/*     */   public void setLoopStart(int paramInt)
/*     */   {
/* 373 */     this.loopStart = paramInt;
/*     */   }
/*     */ 
/*     */   public void setLoopEnd(int paramInt)
/*     */   {
/* 383 */     this.loopEnd = paramInt;
/*     */   }
/*     */ 
/*     */   public void setLoop(int paramInt)
/*     */   {
/* 394 */     this.loop = paramInt;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.io.SampleIn
 * JD-Core Version:    0.5.3
 */