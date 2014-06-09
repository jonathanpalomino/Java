/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class TempoEvent
/*     */   implements Event
/*     */ {
/*     */   private short id;
/*     */   private int time;
/*     */   private double tempo;
/*     */ 
/*     */   public TempoEvent()
/*     */   {
/*  45 */     this(0, 0.0D);
/*     */   }
/*     */ 
/*     */   public TempoEvent(double paramDouble) {
/*  49 */     this(0, paramDouble);
/*     */   }
/*     */ 
/*     */   public TempoEvent(int paramInt, double paramDouble)
/*     */   {
/*  37 */     this.id = 16;
/*  38 */     this.time = 0;
/*  39 */     this.tempo = 60.0D;
/*     */ 
/*  53 */     this.time = paramInt;
/*  54 */     this.tempo = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getTempo()
/*     */   {
/*  60 */     return this.tempo;
/*     */   }
/*     */ 
/*     */   public void setTempo(double paramDouble)
/*     */   {
/*  65 */     this.tempo = paramDouble;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  71 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(int paramInt)
/*     */   {
/*  76 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/*  82 */     return this.id;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  89 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/*  90 */     paramDataOutputStream.writeByte(-1);
/*  91 */     paramDataOutputStream.writeByte(81);
/*  92 */     i += MidiUtil.writeVarLength(3, paramDataOutputStream);
/*  93 */     int j = (int)(60.0F / (float)this.tempo * 1000000.0F);
/*  94 */     paramDataOutputStream.writeByte((byte)(j >> 16 & 0xFF));
/*  95 */     paramDataOutputStream.writeByte((byte)(j >> 8 & 0xFF));
/*  96 */     paramDataOutputStream.writeByte((byte)(j & 0xFF));
/*  97 */     return (i + 5);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 103 */     int i = paramDataInputStream.readUnsignedByte();
/* 104 */     int j = paramDataInputStream.readUnsignedByte();
/* 105 */     int k = paramDataInputStream.readUnsignedByte();
/* 106 */     int l = (i << 16) + (j << 8) + k;
/* 107 */     this.tempo = (1000000.0F / l * 60.0F);
/* 108 */     return 3;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     TempoEvent localTempoEvent;
/*     */     try {
/* 115 */       localTempoEvent = (TempoEvent)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 117 */       System.out.println(localCloneNotSupportedException);
/* 118 */       localTempoEvent = new TempoEvent();
/*     */     }
/* 120 */     return localTempoEvent;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 125 */     System.out.println("TempoEvent(020):             [time = " + this.time + "][tempo = " + this.tempo + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.TempoEvent
 * JD-Core Version:    0.5.3
 */