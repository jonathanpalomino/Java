/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public final class CPres
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  39 */   private final short id = 2;
/*     */   private short pressure;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public CPres()
/*     */   {
/*  47 */     this.pressure = 0;
/*  48 */     this.midiChannel = 0;
/*  49 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public CPres(short paramShort1, short paramShort2, int paramInt)
/*     */   {
/*  54 */     this.pressure = paramShort1;
/*  55 */     this.midiChannel = paramShort2;
/*  56 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getPressure()
/*     */   {
/*  62 */     return this.pressure;
/*     */   }
/*     */ 
/*     */   public void setPressure(short paramShort) {
/*  66 */     this.pressure = paramShort;
/*     */   }
/*     */ 
/*     */   public short getMidiChannel()
/*     */   {
/*  71 */     return this.midiChannel; }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  74 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  79 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  82 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/*  87 */     return 2;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  94 */     return 0;
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 100 */     this.pressure = (short)paramDataInputStream.readUnsignedByte();
/* 101 */     return 1;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     CPres localCPres;
/*     */     try
/*     */     {
/* 109 */       localCPres = (CPres)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 111 */       System.out.println(localCloneNotSupportedException);
/* 112 */       localCPres = new CPres();
/*     */     }
/* 114 */     return localCPres;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 119 */     System.out.println("Channel Pressure(002):\t[time = " + this.time + "][midiChannel = " + this.midiChannel + "][pressure = " + this.pressure + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.CPres
 * JD-Core Version:    0.5.3
 */