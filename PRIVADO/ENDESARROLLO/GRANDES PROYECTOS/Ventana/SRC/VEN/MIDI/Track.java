/*    */ package jm.midi;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ import java.util.Enumeration;
/*    */ import java.util.Vector;
/*    */ import jm.midi.event.Event;
/*    */ 
/*    */ public class Track
/*    */ {
/*    */   private Vector eventList;
/*    */ 
/*    */   public Track()
/*    */   {
/* 43 */     this.eventList = new Vector();
/*    */   }
/*    */ 
/*    */   public void addEvent(Event paramEvent)
/*    */   {
/* 54 */     this.eventList.addElement(paramEvent);
/*    */   }
/*    */ 
/*    */   public Vector getEvtList()
/*    */   {
/* 62 */     return this.eventList;
/*    */   }
/*    */ 
/*    */   public void print()
/*    */   {
/* 69 */     System.out.println("------------------");
/* 70 */     System.out.println("Track");
/* 71 */     Enumeration localEnumeration = this.eventList.elements();
/* 72 */     while (localEnumeration.hasMoreElements())
/* 73 */       Event localEvent = (Event)localEnumeration.nextElement();
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.Track
 * JD-Core Version:    0.5.3
 */