/*    */ package jm.midi.event;
/*    */ 
/*    */ import java.io.DataInputStream;
/*    */ import java.io.DataOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import jm.midi.MidiUtil;
/*    */ 
/*    */ public final class EndTrack
/*    */   implements Event
/*    */ {
/*    */   private final short id;
/*    */   private int time;
/*    */ 
/*    */   public EndTrack()
/*    */   {
/* 41 */     this.id = 23;
/* 42 */     this.time = 0;
/*    */   }
/*    */ 
/*    */   public int getTime()
/*    */   {
/* 48 */     return this.time; }
/*    */ 
/*    */   public void setTime(int paramInt) {
/* 51 */     this.time = paramInt;
/*    */   }
/*    */ 
/*    */   public short getID()
/*    */   {
/* 56 */     return this.id;
/*    */   }
/*    */ 
/*    */   public int write(DataOutputStream paramDataOutputStream)
/*    */     throws IOException
/*    */   {
/* 63 */     int i = MidiUtil.writeVarLength(this.time, paramDataOutputStream);
/* 64 */     paramDataOutputStream.writeByte(-1);
/* 65 */     paramDataOutputStream.writeByte(47);
/* 66 */     paramDataOutputStream.writeByte(0);
/*    */ 
/* 68 */     return (i + 2);
/*    */   }
/*    */ 
/*    */   public int read(DataInputStream paramDataInputStream)
/*    */     throws IOException
/*    */   {
/* 74 */     return 0;
/*    */   }
/*    */ 
/*    */   public Event copy() throws CloneNotSupportedException
/*    */   {
/*    */     TempoEvent localTempoEvent;
/*    */     try {
/* 81 */       localTempoEvent = (TempoEvent)super.clone();
/*    */     } catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 83 */       System.out.println(localCloneNotSupportedException);
/* 84 */       localTempoEvent = new TempoEvent();
/*    */     }
/* 86 */     return localTempoEvent;
/*    */   }
/*    */ 
/*    */   public void print()
/*    */   {
/* 91 */     System.out.println("EndTrack(023):             [time = " + this.time + "]");
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.event.EndTrack
 * JD-Core Version:    0.5.3
 */