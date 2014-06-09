/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class PChange
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  41 */   private final short id = 7;
/*     */   private short value;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public PChange()
/*     */   {
/*  48 */     this.value = 0;
/*  49 */     this.midiChannel = 0;
/*  50 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public PChange(short paramShort1, short paramShort2, int paramInt) {
/*  54 */     this.value = paramShort1;
/*  55 */     this.midiChannel = paramShort2;
/*  56 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getValue()
/*     */   {
/*  62 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(short paramShort) {
/*  66 */     this.value = paramShort;
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
/*  91 */     return 7;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  99 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/* 100 */     paramDataOutputStream.writeByte((byte)(192 + this.midiChannel));
/* 101 */     paramDataOutputStream.writeByte((byte)this.value);
/* 102 */     return (i + 2);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 108 */     this.value = (short)paramDataInputStream.readUnsignedByte();
/* 109 */     return 1;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     PChange localPChange;
/*     */     try
/*     */     {
/* 117 */       localPChange = (PChange)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 119 */       System.out.println(localCloneNotSupportedException);
/* 120 */       localPChange = new PChange();
/*     */     }
/* 122 */     return localPChange;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 127 */     System.out.println(toString());
/*     */   }
/*     */ 
/*     */   public String toString() {
/* 131 */     return new String("Program Change(007): [time = " + this.time + "][midiChannel = " + this.midiChannel + "][value = " + this.value + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.PChange
 * JD-Core Version:    0.5.3
 */