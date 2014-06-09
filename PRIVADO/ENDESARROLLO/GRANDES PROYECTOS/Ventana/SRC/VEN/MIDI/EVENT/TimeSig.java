/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class TimeSig
/*     */   implements Event
/*     */ {
/*     */   public static final short ID = 17;
/*     */   private int time;
/*     */   private int numerator;
/*     */   private int denominator;
/*     */   private int metronomePulse;
/*     */   private int thirtySecondNotesPerBeat;
/*     */ 
/*     */   public TimeSig()
/*     */   {
/*  56 */     this(0, 4, 4);
/*     */   }
/*     */ 
/*     */   public TimeSig(int paramInt1, int paramInt2)
/*     */   {
/*  61 */     this(0, paramInt1, paramInt2);
/*     */   }
/*     */ 
/*     */   public TimeSig(int paramInt1, int paramInt2, int paramInt3) {
/*  65 */     this.time = paramInt1;
/*  66 */     this.numerator = paramInt2;
/*  67 */     this.denominator = paramInt3;
/*  68 */     this.metronomePulse = 24;
/*  69 */     this.thirtySecondNotesPerBeat = 32;
/*     */   }
/*     */ 
/*     */   public int getDenominator() {
/*  73 */     return this.denominator; }
/*     */ 
/*     */   public void setDenominator(int paramInt) {
/*  76 */     this.denominator = paramInt; }
/*     */ 
/*     */   public int getNumerator() {
/*  79 */     return this.numerator; }
/*     */ 
/*     */   public void setNumerator(int paramInt) {
/*  82 */     this.numerator = paramInt;
/*     */   }
/*     */ 
/*     */   public int getMetronomePulse()
/*     */   {
/*  92 */     return this.metronomePulse;
/*     */   }
/*     */ 
/*     */   public void setMetronomePulse(int paramInt)
/*     */   {
/* 106 */     this.metronomePulse = paramInt;
/*     */   }
/*     */ 
/*     */   public int getThirtySecondNotesPerBeat()
/*     */   {
/* 111 */     return this.thirtySecondNotesPerBeat;
/*     */   }
/*     */ 
/*     */   public void setThirtySecondNotesPerBeat(int paramInt)
/*     */   {
/* 116 */     this.thirtySecondNotesPerBeat = paramInt;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/* 122 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/* 125 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/* 130 */     return 17;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/* 137 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/* 138 */     paramDataOutputStream.writeByte(255);
/* 139 */     paramDataOutputStream.writeByte(88);
/* 140 */     i += MidiUtil.writeVarLength(4, paramDataOutputStream);
/* 141 */     paramDataOutputStream.writeByte((byte)this.numerator);
/* 142 */     int j = this.denominator;
/* 143 */     int k = 0;
/* 144 */     for (; j % 2 == 0; ++k) j /= 2;
/* 145 */     paramDataOutputStream.writeByte((byte)k);
/* 146 */     paramDataOutputStream.writeByte(24);
/* 147 */     paramDataOutputStream.writeByte(8);
/* 148 */     return (i + 6);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 160 */     this.numerator = (short)paramDataInputStream.readUnsignedByte();
/* 161 */     int i = (short)paramDataInputStream.readUnsignedByte();
/* 162 */     this.denominator = (1 << i);
/* 163 */     this.metronomePulse = paramDataInputStream.readUnsignedByte();
/* 164 */     this.thirtySecondNotesPerBeat = paramDataInputStream.readUnsignedByte();
/* 165 */     return 4;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     TimeSig localTimeSig;
/*     */     try
/*     */     {
/* 173 */       localTimeSig = (TimeSig)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 175 */       System.out.println(localCloneNotSupportedException);
/* 176 */       localTimeSig = new TimeSig();
/*     */     }
/* 178 */     return localTimeSig;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 183 */     System.out.println("TimeSig(021):             [time = " + this.time + "][numerator = " + this.numerator + "][denominator = " + this.denominator + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.TimeSig
 * JD-Core Version:    0.5.3
 */