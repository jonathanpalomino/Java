/*     */ package jm.gui.show;
/*     */ 
/*     */ import java.awt.Adjustable;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Panel;
/*     */ import java.awt.ScrollPane;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ShowPanel extends ScrollPane
/*     */ {
/*     */   public Score score;
/*     */   protected double beatWidth;
/*     */   private ShowArea sa;
/*     */   private ShowRuler ruler;
/*     */   private Panel pan;
/*     */   private Frame frame;
/*     */   private int panelHeight;
/*     */ 
/*     */   public ShowPanel(Frame paramFrame, Score paramScore)
/*     */   {
/*  58 */     super(1);
/*     */ 
/*  60 */     this.beatWidth = (650.0D / paramScore.getEndTime());
/*  61 */     if (this.beatWidth < 1.0D) this.beatWidth = 1.0D;
/*  62 */     if (this.beatWidth > 256.0D) this.beatWidth = 256.0D;
/*  63 */     this.frame = paramFrame;
/*  64 */     this.score = paramScore;
/*     */ 
/*  68 */     this.pan = new Panel();
/*  69 */     this.pan.setLayout(new BorderLayout());
/*     */ 
/*  71 */     this.sa = new ShowArea(this);
/*  72 */     this.pan.add("Center", this.sa);
/*     */ 
/*  74 */     this.ruler = new ShowRuler(this);
/*  75 */     this.pan.add("South", this.ruler);
/*  76 */     setSize(new Dimension(650, 400));
/*  77 */     updatePanelHeight();
/*  78 */     add(this.pan);
/*     */ 
/*  80 */     getHAdjustable().setUnitIncrement(50);
/*  81 */     getHAdjustable().setBlockIncrement(50);
/*     */ 
/*  83 */     setScrollPosition(0, 0);
/*     */   }
/*     */ 
/*     */   public void setScore(Score paramScore)
/*     */   {
/*  88 */     this.score = paramScore;
/*  89 */     this.beatWidth = (getSize().width / paramScore.getEndTime());
/*  90 */     if (this.beatWidth < 1.0D) this.beatWidth = 1.0D;
/*  91 */     if (this.beatWidth > 256.0D) this.beatWidth = 256.0D;
/*  92 */     update();
/*     */   }
/*     */ 
/*     */   public void updatePanelHeight()
/*     */   {
/*  99 */     this.panelHeight = (this.sa.getHeight() + this.ruler.getHeight() + 25);
/* 100 */     setSize(new Dimension(getSize().width, this.panelHeight));
/*     */   }
/*     */ 
/*     */   public int getHeight()
/*     */   {
/* 107 */     return this.panelHeight;
/*     */   }
/*     */ 
/*     */   public ShowArea getShowArea()
/*     */   {
/* 114 */     return this.sa;
/*     */   }
/*     */ 
/*     */   public void update() {
/* 118 */     this.pan.repaint();
/* 119 */     this.sa.setSize((int)Math.round(this.score.getEndTime() * this.beatWidth), this.sa.getHeight());
/* 120 */     this.sa.repaint();
/* 121 */     this.ruler.repaint();
/* 122 */     repaint();
/* 123 */     this.frame.pack();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.show.ShowPanel
 * JD-Core Version:    0.5.3
 */