/*     */ package jm.midi.event;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public final class SysEx
/*     */   implements SysComEvt
/*     */ {
/*     */   private Vector message;
/*     */   private int time;
/*  39 */   private final short id = 8;
/*     */ 
/*     */   public SysEx()
/*     */   {
/*  44 */     this.message = new Vector();
/*     */   }
/*     */ 
/*     */   public void addToList(byte paramByte)
/*     */   {
/*     */   }
/*     */ 
/*     */   public Vector getList()
/*     */   {
/*  55 */     return this.message;
/*     */   }
/*     */ 
/*     */   public int getTime()
/*     */   {
/*  60 */     return this.time; }
/*     */ 
/*     */   public void setTime(int paramInt) {
/*  63 */     this.time = paramInt;
/*     */   }
/*     */ 
/*     */   public short getID()
/*     */   {
/*  68 */     return 8;
/*     */   }
/*     */ 
/*     */   public int write(DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  75 */     return 0;
/*     */   }
/*     */ 
/*     */   public int read(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/*  81 */     return 0;
/*     */   }
/*     */ 
/*     */   public Event copy()
/*     */     throws CloneNotSupportedException
/*     */   {
/*  87 */     SysEx localSysEx = null;
/*     */     try {
/*  89 */       localSysEx = (SysEx)super.clone();
/*     */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/*  91 */       System.out.println(localCloneNotSupportedException);
/*  92 */       localSysEx = new SysEx();
/*     */     }
/*  94 */     return localSysEx;
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 100 */     System.out.println("System Exclusive(010): [time =" + this.time + "]");
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.SysEx
 * JD-Core Version:    0.5.3
 */