/*    */ package ven.audio.io;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import javax.sound.sampled.AudioFormat;
/*    */ import javax.sound.sampled.AudioFormat.Encoding;
/*    */ import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
/*    */ import javax.sound.sampled.DataLine.Info;
/*    */ import javax.sound.sampled.TargetDataLine;
/*    */ import ven.audio.AOException;
/*    */ import ven.audio.AudioObject;
/*    */ import ven.audio.Instrument;
/*    */ 
/*    */ public final class RTIn extends AudioObject
/*    */ {
/* 42 */   public boolean finished = false;
/*    */   private int bufsize;
/*    */   private TargetDataLine dline;
/* 48 */   private boolean started = false;
/*    */ 
/*    */   public RTIn(Instrument paramInstrument, int paramInt1, int paramInt2, int paramInt3)
/*    */   {
/* 56 */     super(paramInstrument, paramInt1, "[RTIn]");
/* 57 */     this.sampleRate = paramInt1;
/* 58 */     this.channels = paramInt2;
/* 59 */     this.bufsize = paramInt3;
/* 60 */     init();
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 70 */     if (!(this.started)) { this.dline.start(); this.started = true; }
/* 71 */     int i = 0;
/* 72 */     int j = 0;
/* 73 */     int k = paramArrayOfFloat.length * 2;
/* 74 */     byte[] arrayOfByte = new byte[k];
/* 75 */     this.dline.read(arrayOfByte, 0, k);
/* 76 */     for (; i < paramArrayOfFloat.length; ++i) {
/* 77 */       int l = (short)((arrayOfByte[(j++)] << 8) + arrayOfByte[(j++)]);
/* 78 */       paramArrayOfFloat[i] = (l / 32767.0F);
/*    */     }
/*    */ 
/* 81 */     return i;
/*    */   }
/*    */ 
/*    */   public void init() {
/* 85 */     AudioFormat localAudioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, this.sampleRate, 16, this.channels, this.channels * 2, this.sampleRate, true);
/*    */ 
/* 87 */     DataLine.Info localInfo = new DataLine.Info(TargetDataLine.class, localAudioFormat);
/* 88 */     System.out.println("Setting for audio line: " + localInfo);
/* 89 */     if (!(AudioSystem.isLineSupported(localInfo))) {
/* 90 */       System.out.println(localInfo);
/* 91 */       System.err.println("JMF Line not supported ... exiting .. sothere");
/* 92 */       System.exit(1);
/*    */     }
/*    */     try {
/* 95 */       this.dline = ((TargetDataLine)AudioSystem.getLine(localInfo));
/*    */ 
/* 97 */       this.dline.open(localAudioFormat, this.bufsize * 2); } catch (Exception localException) {
/* 98 */       localException.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.io.RTIn
 * JD-Core Version:    0.5.3
 */