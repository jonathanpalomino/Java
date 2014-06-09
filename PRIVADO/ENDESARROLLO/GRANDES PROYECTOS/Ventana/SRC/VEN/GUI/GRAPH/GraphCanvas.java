/*    */ package jm.gui.graph;
/*    */ 
/*    */ import java.awt.Canvas;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ 
/*    */ public abstract class GraphCanvas extends Canvas
/*    */ {
/*    */   protected StatisticsList statsList;
/*    */   protected Image image;
/*    */   protected Graphics graphics;
/*    */   protected Dimension preferredSize;
/*    */   protected Dimension minimumSize;
/*    */ 
/*    */   public GraphCanvas()
/*    */   {
/* 47 */     this(new Statistics());
/*    */   }
/*    */ 
/*    */   public GraphCanvas(Statistics paramStatistics)
/*    */   {
/* 42 */     this.preferredSize = new Dimension(1, 1);
/*    */ 
/* 44 */     this.minimumSize = new Dimension(1, 1);
/*    */ 
/* 51 */     this.statsList = new StatisticsList();
/* 52 */     this.statsList.add(paramStatistics);
/*    */   }
/*    */ 
/*    */   public GraphCanvas(Statistics[] paramArrayOfStatistics)
/*    */   {
/* 42 */     this.preferredSize = new Dimension(1, 1);
/*    */ 
/* 44 */     this.minimumSize = new Dimension(1, 1);
/*    */ 
/* 56 */     this.statsList = new StatisticsList(paramArrayOfStatistics.length * 110 / 100);
/* 57 */     for (int i = 0; i < paramArrayOfStatistics.length; ++i)
/* 58 */       this.statsList.add(paramArrayOfStatistics[i]);
/*    */   }
/*    */ 
/*    */   public GraphCanvas(StatisticsList paramStatisticsList)
/*    */   {
/* 42 */     this.preferredSize = new Dimension(1, 1);
/*    */ 
/* 44 */     this.minimumSize = new Dimension(1, 1);
/*    */ 
/* 63 */     this.statsList = paramStatisticsList;
/*    */   }
/*    */ 
/*    */   public Dimension getMinimumSize() {
/* 67 */     return this.minimumSize;
/*    */   }
/*    */ 
/*    */   public Dimension getPreferredSize() {
/* 71 */     return this.preferredSize;
/*    */   }
/*    */ 
/*    */   public void addStatistics(Statistics paramStatistics) {
/* 75 */     this.statsList.add(paramStatistics);
/*    */   }
/*    */ 
/*    */   protected abstract void paintBuffer();
/*    */ 
/*    */   public void update(Graphics paramGraphics) {
/* 81 */     paint(paramGraphics);
/*    */   }
/*    */ 
/*    */   public void paint(Graphics paramGraphics) {
/* 85 */     if (this.image == null) {
/* 86 */       this.image = createImage(1, 1);
/* 87 */       this.graphics = this.image.getGraphics();
/*    */     }
/*    */ 
/* 90 */     paintBuffer();
/*    */ 
/* 92 */     paramGraphics.drawImage(this.image, 0, 0, this);
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.graph.GraphCanvas
 * JD-Core Version:    0.5.3
 */