/*    */ package jm.music.tools.ga;
/*    */ 
/*    */ import java.awt.Panel;
/*    */ 
/*    */ public abstract class GAComponent
/*    */ {
/*    */   protected Panel panel;
/* 35 */   protected static String label = "Genetic Algorithm Component";
/*    */ 
/*    */   public GAComponent()
/*    */   {
/* 33 */     this.panel = new Panel();
/*    */   }
/*    */ 
/*    */   public Panel getPanel()
/*    */   {
/* 38 */     return this.panel;
/*    */   }
/*    */ 
/*    */   public String getLabel() {
/* 42 */     return label;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.GAComponent
 * JD-Core Version:    0.5.3
 */