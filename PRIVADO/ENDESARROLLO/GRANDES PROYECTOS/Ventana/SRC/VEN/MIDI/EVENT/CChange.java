/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class CChange
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  40 */   private final short id = 3;
/*     */   private short controllerNum;
/*     */   private short value;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public CChange()
/*     */   {
/*  48 */     this.controllerNum = 0;
/*  49 */     this.value = 0;
/*  50 */     this.midiChannel = 0;
/*  51 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public CChange(short paramShort1, short paramShort2, short paramShort3, int paramInt)
/*     */   {
/*  56 */     this.controllerNum = paramShort1;
/*  57 */     this.value = paramShort2;
/*  58 */     this.midiChannel = paramShort3;
/*  59 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getControllerNum()
/*     */   {
/*  65 */     return this.controllerNum;
/*     */   }
/*     */ 
/*     */   public void setControllerNum(short paramShort) {
/*  69 */     this.controllerNum = paramShort;
/*     */   }
/*     */ 
/*     */   public short getValue()
/*     */   {
/*  75 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(short paramShort) {
/*  79 */     this.value = paramShort;
/*     */   }
/*     */ 
/*     */   public short getMidiChannel()
/*     */   {
/*  84 */     return this.midiChannel; }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  87 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  92 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  95 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/* 100 */     return 3;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/* 107 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/* 108 */     paramDataOutputStream.writeByte((byte)(176 + this.midiChannel));
/* 109 */     paramDataOutputStream.writeByte((byte)this.controllerNum);
/* 110 */     paramDataOutputStream.writeByte((byte)this.value);
/* 111 */     return (i + 3);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 117 */     this.controllerNum = (short)paramDataInputStream.readUnsignedByte();
/* 118 */     this.value = (short)paramDataInputStream.readUnsignedByte();
/* 119 */     return 2;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     CChange localCChange;
/*     */     try
/*     */     {
/* 127 */       localCChange = (CChange)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 129 */       System.out.println(localCloneNotSupportedException);
/* 130 */       localCChange = new CChange();
/*     */     }
/* 132 */     return localCChange;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 137 */     System.out.println("Contol Change(003):\t\t\t [time = " + this.time + "][midiChannel = " + this.midiChannel + "][contoller_num = " + this.controllerNum + "][value = " + this.value + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.CChange
 * JD-Core Version:    0.5.3
 */