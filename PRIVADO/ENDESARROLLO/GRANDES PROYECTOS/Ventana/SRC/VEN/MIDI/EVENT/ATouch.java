/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public final class ATouch
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  40 */   private final short id = 1;
/*     */   private short pitch;
/*     */   private short pressure;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public ATouch()
/*     */   {
/*  48 */     this.pitch = 0;
/*  49 */     this.pressure = 0;
/*  50 */     this.midiChannel = 0;
/*  51 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public ATouch(short paramShort1, short paramShort2, short paramShort3, int paramInt)
/*     */   {
/*  56 */     this.pitch = paramShort1;
/*  57 */     this.pressure = paramShort2;
/*  58 */     this.midiChannel = paramShort3;
/*  59 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getPitch()
/*     */   {
/*  65 */     return this.pitch;
/*     */   }
/*     */ 
/*     */   public void setPitch(short paramShort) {
/*  69 */     this.pitch = paramShort;
/*     */   }
/*     */ 
/*     */   public short getPressure()
/*     */   {
/*  75 */     return this.pressure;
/*     */   }
/*     */ 
/*     */   public void setPressure(short paramShort) {
/*  79 */     this.pressure = paramShort;
/*     */   }
/*     */ 
/*     */   public short getMidiChannel()
/*     */   {
/*  85 */     return this.midiChannel;
/*     */   }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  89 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  95 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  99 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/* 105 */     return 1;
/*     */   }
/*     */ 
/*     */   public Event copy()
/*     */     throws CloneNotSupportedException
/*     */   {
/*     */     ATouch localATouch;
/*     */     try
/*     */     {
/* 114 */       localATouch = (ATouch)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 116 */       System.out.println(localCloneNotSupportedException);
/* 117 */       localATouch = new ATouch();
/*     */     }
/* 119 */     return localATouch;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/* 125 */     return 0;
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 131 */     this.pitch = (short)paramDataInputStream.readUnsignedByte();
/* 132 */     this.pressure = (short)paramDataInputStream.readUnsignedByte();
/* 133 */     return 2;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 139 */     System.out.println("ATouch(001):    \t\t\t\t   [time = " + this.time + "][midiChannel = " + this.midiChannel + "][pitch = " + this.pitch + "][pressure = " + this.pressure + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.ATouch
 * JD-Core Version:    0.5.3
 */