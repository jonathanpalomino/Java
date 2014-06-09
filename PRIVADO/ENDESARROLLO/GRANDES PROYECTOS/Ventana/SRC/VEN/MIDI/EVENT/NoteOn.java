/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class NoteOn
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  40 */   private final short id = 5;
/*     */   private short pitch;
/*     */   private short velocity;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public NoteOn()
/*     */   {
/*  48 */     this.pitch = 0;
/*  49 */     this.velocity = 0;
/*  50 */     this.midiChannel = 0;
/*  51 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public NoteOn(short paramShort1, short paramShort2, short paramShort3, int paramInt)
/*     */   {
/*  56 */     this.pitch = paramShort1;
/*  57 */     this.velocity = paramShort2;
/*  58 */     this.midiChannel = paramShort3;
/*  59 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getPitch()
/*     */   {
/*  66 */     return this.pitch;
/*     */   }
/*     */ 
/*     */   public void setPitch(short paramShort) {
/*  70 */     this.pitch = paramShort;
/*     */   }
/*     */ 
/*     */   public short getVelocity()
/*     */   {
/*  77 */     return this.velocity;
/*     */   }
/*     */ 
/*     */   public void setVelocity(short paramShort) {
/*  81 */     this.velocity = paramShort;
/*     */   }
/*     */ 
/*     */   public short getMidiChannel()
/*     */   {
/*  87 */     return this.midiChannel; }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  90 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  96 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  99 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/* 105 */     return 5;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     NoteOn localNoteOn;
/*     */     try
/*     */     {
/* 113 */       localNoteOn = (NoteOn)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 115 */       System.out.println(localCloneNotSupportedException);
/* 116 */       localNoteOn = new NoteOn();
/*     */     }
/* 118 */     return localNoteOn;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/* 124 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/* 125 */     paramDataOutputStream.writeByte((byte)(144 + this.midiChannel));
/* 126 */     paramDataOutputStream.writeByte((byte)this.pitch);
/* 127 */     paramDataOutputStream.writeByte((byte)this.velocity);
/* 128 */     return (i + 3);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 134 */     this.pitch = (short)paramDataInputStream.readUnsignedByte();
/* 135 */     this.velocity = (short)paramDataInputStream.readUnsignedByte();
/* 136 */     return 2;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 143 */     System.out.println("Note On(005): [time = " + this.time + "][midiChannel = " + this.midiChannel + "][pitch = " + this.pitch + "][velocity = " + this.velocity + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.NoteOn
 * JD-Core Version:    0.5.3
 */