/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ 
/*     */ public final class NoteOff
/*     */   implements VoiceEvt, Cloneable
/*     */ {
/*  41 */   private final short id = 4;
/*     */   private short pitch;
/*     */   private short velocity;
/*     */   private short midiChannel;
/*     */   private int time;
/*     */ 
/*     */   public NoteOff()
/*     */   {
/*  50 */     this.pitch = 0;
/*  51 */     this.velocity = 0;
/*  52 */     this.midiChannel = 0;
/*  53 */     this.time = 0;
/*     */   }
/*     */ 
/*     */   public NoteOff(short paramShort1, short paramShort2, short paramShort3, int paramInt)
/*     */   {
/*  58 */     this.pitch = paramShort1;
/*  59 */     this.velocity = paramShort2;
/*  60 */     this.midiChannel = paramShort3;
/*  61 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getPitch()
/*     */   {
/*  67 */     return this.pitch;
/*     */   }
/*     */ 
/*     */   public void setPitch(short paramShort) {
/*  71 */     this.pitch = paramShort;
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
/*  86 */     return this.midiChannel; }
/*     */ 
/*     */   public void setMidiChannel(short paramShort) {
/*  89 */     this.midiChannel = paramShort;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  94 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  97 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/* 102 */     return 4;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     NoteOff localNoteOff;
/*     */     try {
/* 109 */       localNoteOff = (NoteOff)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 111 */       System.out.println(localCloneNotSupportedException);
/* 112 */       localNoteOff = new NoteOff();
/*     */     }
/* 114 */     return localNoteOff;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream) throws IOException
/*     */   {
/* 119 */     return 0;
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 125 */     this.pitch = (short)paramDataInputStream.readUnsignedByte();
/* 126 */     this.velocity = (short)paramDataInputStream.readUnsignedByte();
/* 127 */     return 2;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 133 */     System.out.println("Note Off(004): [time = " + this.time + "][midiChannel = " + this.midiChannel + "][pitch = " + this.pitch + "][velocity = " + this.velocity + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.NoteOff
 * JD-Core Version:    0.5.3
 */