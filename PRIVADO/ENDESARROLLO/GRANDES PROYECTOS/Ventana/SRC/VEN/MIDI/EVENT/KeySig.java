/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import jm.midi.MidiUtil;
/*     */ 
/*     */ public final class KeySig
/*     */   implements Event
/*     */ {
/*     */   private static final short ID = 18;
/*     */   private int time;
/*     */   private int keySig;
/*     */   private int keyQual;
/*     */ 
/*     */   public KeySig()
/*     */   {
/*  45 */     this.time = 0;
/*  46 */     this.keySig = 0;
/*  47 */     this.keyQual = 0; }
/*     */ 
/*     */   public KeySig(int paramInt1, int paramInt2) {
/*  50 */     this.time = 0;
/*  51 */     this.keySig = paramInt1;
/*  52 */     this.keyQual = paramInt2; }
/*     */ 
/*     */   public KeySig(int paramInt1, int paramInt2, int paramInt3) {
/*  55 */     this.time = paramInt1;
/*  56 */     this.keySig = paramInt2;
/*  57 */     this.keyQual = paramInt3;
/*     */   }
/*     */ 
/*     */   public int getKeyQuality() {
/*  61 */     return this.keyQual; }
/*     */ 
/*     */   public void setKeyQuality(int paramInt) {
/*  64 */     this.keyQual = paramInt; }
/*     */ 
/*     */   public int getKeySig() {
/*  67 */     return this.keySig; }
/*     */ 
/*     */   public void setKeySig(int paramInt) {
/*  70 */     this.keySig = paramInt;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  76 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  79 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/*  84 */     return 18;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  91 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/*  92 */     paramDataOutputStream.writeByte(255);
/*  93 */     paramDataOutputStream.writeByte(89);
/*  94 */     i += MidiUtil.writeVarLength(2, paramDataOutputStream);
/*  95 */     paramDataOutputStream.writeByte((byte)this.keySig);
/*  96 */     paramDataOutputStream.writeByte((byte)this.keyQual);
/*  97 */     return (i + 4);
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 109 */     this.keySig = paramDataInputStream.readByte();
/* 110 */     this.keyQual = paramDataInputStream.readUnsignedByte();
/* 111 */     return 2;
/*     */   }
/*     */ 
/*     */   public Event copy() throws CloneNotSupportedException
/*     */   {
/*     */     KeySig localKeySig;
/*     */     try
/*     */     {
/* 119 */       localKeySig = (KeySig)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 121 */       System.out.println(localCloneNotSupportedException);
/* 122 */       localKeySig = new KeySig();
/*     */     }
/* 124 */     return localKeySig;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 129 */     System.out.println("KeySig(022):             [time = " + this.time + "][keySig = " + this.keySig + "][keyQual = " + this.keyQual + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.KeySig
 * JD-Core Version:    0.5.3
 */