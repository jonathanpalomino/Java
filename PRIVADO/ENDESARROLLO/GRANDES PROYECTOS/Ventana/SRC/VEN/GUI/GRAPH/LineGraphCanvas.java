/*    */ package jm.gui.graph;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ 
/*    */ public class LineGraphCanvas extends GraphCanvas
/*    */ {
/* 37 */   protected boolean hasMusicChanged = true;
/*    */ 
/* 39 */   protected Dimension preferredSize = new Dimension(800, 800);
/*    */ 
/*    */   public LineGraphCanvas()
/*    */   {
/*    */   }
/*    */ 
/*    */   public LineGraphCanvas(Statistics paramStatistics) {
/* 46 */     super(paramStatistics);
/*    */   }
/*    */ 
/*    */   public LineGraphCanvas(Statistics[] paramArrayOfStatistics) {
/* 50 */     super(paramArrayOfStatistics);
/*    */   }
/*    */ 
/*    */   public LineGraphCanvas(StatisticsList paramStatisticsList) {
/* 54 */     super(paramStatisticsList);
/*    */   }
/*    */ 
/*    */   public Dimension getPreferredSize() {
/* 58 */     return this.preferredSize;
/*    */   }
/*    */ 
/*    */   public void paintBuffer() {
/* 62 */     int i = 1000;
/* 63 */     double d = 1000.0D;
/* 64 */     for (int j = 0; j < this.statsList.size(); ++j) {
/* 65 */       if (this.statsList.get(j).size() > i) {
/* 66 */         i = this.statsList.get(j).size();
/*    */       }
/* 68 */       if ((this.statsList.get(j).largestValue() == (1.0D / 0.0D)) || (this.statsList.get(j).largestValue() <= d))
/*    */         continue;
/* 70 */       d = this.statsList.get(j).largestValue();
/*    */     }
/*    */ 
/* 73 */     this.image = createImage(i, (int)d);
/* 74 */     this.graphics = this.image.getGraphics();
/*    */ 
/* 76 */     for (j = 0; j < this.statsList.size(); ++j) {
/* 77 */       this.graphics.setColor(new Color((float)Math.random(), (float)Math.random(), (float)Math.random()));
/*    */ 
/* 80 */       for (int k = 1; k < this.statsList.get(j).size(); ++k) {
/* 81 */         if (this.statsList.get(j).largestValue() == (1.0D / 0.0D))
/*    */           continue;
/* 83 */         if (j == 2) {
/* 84 */           this.graphics.drawLine((int)((k - 1) * 0.5D), (int)(this.statsList.get(j).get(k - 1) * 10000.0D), (int)(k * 0.5D), (int)(this.statsList.get(j).get(k) * 10000.0D));
/*    */         }
/*    */         else
/* 87 */           this.graphics.drawLine((int)((k - 1) * 0.5D), (int)(this.statsList.get(j).get(k - 1) * 300.0D), (int)(k * 0.5D), (int)(this.statsList.get(j).get(k) * 300.0D));
/*    */       }
/*    */     }
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.graph.LineGraphCanvas
 * JD-Core Version:    0.5.3
 */