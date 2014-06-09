/*    */ package jm.gui.show;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class NoteGraphic extends Component
/*    */   implements MouseListener
/*    */ {
/*    */   NoteGraphic()
/*    */   {
/*  9 */     addMouseListener(this);
/*    */   }
/*    */ 
/*    */   public void mousePressed(MouseEvent paramMouseEvent) {
/* 13 */     System.out.println("X is: " + paramMouseEvent.getX());
/*    */   }
/*    */ 
/*    */   public void mouseClicked(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseEntered(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseExited(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ 
/*    */   public void mouseReleased(MouseEvent paramMouseEvent)
/*    */   {
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.show.NoteGraphic
 * JD-Core Version:    0.5.3
 */