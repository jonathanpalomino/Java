/*    */ package jm.gui.graph;
/*    */ 
/*    */ import java.awt.ScrollPane;
/*    */ 
/*    */ public class LineGraph extends ScrollPane
/*    */ {
/*    */   protected GraphCanvas graphCanvas;
/*    */ 
/*    */   public LineGraph()
/*    */   {
/* 36 */     this(new Statistics());
/*    */   }
/*    */ 
/*    */   public LineGraph(Statistics paramStatistics)
/*    */   {
/* 41 */     this.graphCanvas = new LineGraphCanvas(paramStatistics);
/* 42 */     add(this.graphCanvas);
/*    */   }
/*    */ 
/*    */   public LineGraph(Statistics[] paramArrayOfStatistics)
/*    */   {
/* 47 */     this.graphCanvas = new LineGraphCanvas(paramArrayOfStatistics);
/* 48 */     add(this.graphCanvas);
/*    */   }
/*    */ 
/*    */   public LineGraph(StatisticsList paramStatisticsList)
/*    */   {
/* 53 */     this.graphCanvas = new LineGraphCanvas(paramStatisticsList);
/* 54 */     add(this.graphCanvas);
/*    */   }
/*    */ 
/*    */   public void addStatistics(Statistics paramStatistics) {
/* 58 */     this.graphCanvas.addStatistics(paramStatistics);
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.graph.LineGraph
 * JD-Core Version:    0.5.3
 */