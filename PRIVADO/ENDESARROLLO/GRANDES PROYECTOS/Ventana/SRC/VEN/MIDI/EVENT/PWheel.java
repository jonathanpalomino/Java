/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public final class PWheel
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  40 */   private final short id = 6;
/*     */   private int value;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public PWheel()
/*     */   {
/*  47 */     this.value = 0;
/*  48 */     this.midiChannel = 0;
/*  49 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public PWheel(int paramInt1, short paramShort, int paramInt2)
/*     */   {
/*  54 */     this.value = paramInt1;
/*  55 */     this.midiChannel = paramShort;
/*  56 */     this.time = paramInt2;
/*     */   }
/*     */ 
/*     */   public int getValue()
/*     */   {
/*  62 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(int paramInt) {
/*  66 */     this.value = paramInt;
/*     */   }
/*     */ 
/*     */   public short getMidiChannel()
/*     */   {
/*  72 */     return this.midiChannel;
/*     */   }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  76 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  82 */     return this.time;
/*     */   }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  86 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/*  91 */     return 6;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  98 */     return 0;
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 106 */     this.value = paramDataInputStream.readUnsignedByte();
/* 107 */     this.value += paramDataInputStream.readUnsignedByte() * 128;
/* 108 */     return 1;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     PWheel localPWheel;
/*     */     try
/*     */     {
/* 116 */       localPWheel = (PWheel)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 118 */       System.out.println(localCloneNotSupportedException);
/* 119 */       localPWheel = new PWheel();
/*     */     }
/* 121 */     return localPWheel;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 126 */     System.out.println("Pitch Wheel(006):\t\t\t\t  [time = " + this.time + "][midiChannel = " + this.midiChannel + "][value = " + this.value + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.PWheel
 * JD-Core Version:    0.5.3
 */