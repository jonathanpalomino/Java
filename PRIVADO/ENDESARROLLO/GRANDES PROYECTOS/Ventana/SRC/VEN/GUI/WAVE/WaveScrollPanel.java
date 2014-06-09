/*     */ package jm.gui.wave;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Button;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Scrollbar;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ 
/*     */ public class WaveScrollPanel extends Panel
/*     */   implements ActionListener, AdjustmentListener
/*     */ {
/*     */   private Label name;
/*     */   private Label bitSize;
/*     */   private Label sampleRate;
/*     */   private Label channels;
/*     */   private Label resLable;
/*     */   private Button minus;
/*     */   private Button plus;
/*     */   private Button play;
/*     */   private Button stop;
/*     */   private WaveView viewer;
/*  38 */   private WaveRuler ruler = new WaveRuler();
/*     */   private Panel resizePanel;
/*  40 */   private Scrollbar scroll = new Scrollbar(0);
/*  41 */   private Font font = new Font("Helvetica", 0, 10);
/*     */ 
/*     */   public WaveScrollPanel() {
/*  44 */     setBackground(Color.lightGray);
/*  45 */     setLayout(new BorderLayout());
/*  46 */     this.resizePanel = new Panel();
/*  47 */     this.resLable = new Label("Display Resolution: 1:0");
/*  48 */     this.resLable.setFont(this.font);
/*  49 */     this.resizePanel.add(this.resLable);
/*  50 */     this.minus = new Button("-");
/*  51 */     this.minus.addActionListener(this);
/*  52 */     this.resizePanel.add(this.minus);
/*  53 */     this.plus = new Button("+");
/*  54 */     this.plus.addActionListener(this);
/*  55 */     this.resizePanel.add(this.plus);
/*  56 */     add(this.resizePanel, "East");
/*     */ 
/*  58 */     Panel localPanel = new Panel();
/*  59 */     this.play = new Button("Play");
/*  60 */     this.play.addActionListener(this);
/*  61 */     localPanel.add(this.play);
/*  62 */     this.stop = new Button("Stop");
/*  63 */     this.stop.setEnabled(false);
/*  64 */     this.stop.addActionListener(this);
/*  65 */     localPanel.add(this.stop);
/*  66 */     this.name = new Label();
/*  67 */     this.name.setFont(this.font);
/*  68 */     localPanel.add(this.name);
/*  69 */     this.bitSize = new Label();
/*  70 */     this.bitSize.setFont(this.font);
/*  71 */     localPanel.add(this.bitSize);
/*  72 */     this.sampleRate = new Label();
/*  73 */     this.sampleRate.setFont(this.font);
/*  74 */     localPanel.add(this.sampleRate);
/*  75 */     this.channels = new Label();
/*  76 */     this.channels.setFont(this.font);
/*  77 */     localPanel.add(this.channels);
/*  78 */     add(localPanel, "West");
/*     */ 
/*  80 */     this.scroll.addAdjustmentListener(this);
/*  81 */     add(this.scroll, "South");
/*     */ 
/*  83 */     this.ruler.setWaveScrollPanel(this);
/*  84 */     add(this.ruler, "North");
/*     */   }
/*     */ 
/*     */   public void setViewer(WaveView paramWaveView)
/*     */   {
/*  93 */     this.viewer = paramWaveView;
/*  94 */     setResolution(paramWaveView.getResolution());
/*     */   }
/*     */ 
/*     */   public void setScrollbarAttributes(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 104 */     this.scroll.setUnitIncrement(1000);
/* 105 */     this.scroll.setBlockIncrement(paramInt2 * paramInt3 / 2);
/* 106 */     this.scroll.setMinimum(0);
/* 107 */     this.scroll.setMaximum(paramInt1 * 2);
/* 108 */     this.scroll.setVisibleAmount(paramInt2 * paramInt3);
/*     */   }
/*     */ 
/*     */   public void setScrollbarResolution(int paramInt)
/*     */   {
/* 115 */     if (this.viewer != null) {
/* 116 */       this.scroll.setVisibleAmount(this.viewer.getWidth() * paramInt);
/* 117 */       this.scroll.setBlockIncrement(this.viewer.getWidth() * paramInt / 2);
/*     */     } else {
/* 119 */       this.scroll.setVisibleAmount(204800);
/* 120 */       this.scroll.setBlockIncrement(102400);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setScrollbarValue(int paramInt)
/*     */   {
/* 129 */     this.scroll.setValue(paramInt);
/*     */   }
/*     */ 
/*     */   public void setResolution(int paramInt)
/*     */   {
/* 136 */     String str = new String("Display Resolution = 1:" + paramInt);
/* 137 */     if (paramInt < 1000) str = str + "  ";
/* 138 */     this.resLable.setText(str);
/* 139 */     this.ruler.setMarkerWidth(this.viewer.getSampleRate() / paramInt);
/* 140 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setFileName(String paramString)
/*     */   {
/* 147 */     this.name.setText("File = " + paramString + ". ");
/* 148 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setBitSize(int paramInt)
/*     */   {
/* 155 */     this.bitSize.setText("Bit Depth = " + paramInt + ". ");
/* 156 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setSampleRate(int paramInt)
/*     */   {
/* 163 */     this.sampleRate.setText("Sample Rate = " + paramInt + ". ");
/* 164 */     repaint();
/*     */   }
/*     */ 
/*     */   public void setChannels(int paramInt)
/*     */   {
/* 172 */     this.channels.setText("Channels = " + paramInt + ". ");
/* 173 */     repaint();
/*     */   }
/*     */ 
/*     */   public WaveRuler getWaveRuler()
/*     */   {
/* 180 */     return this.ruler;
/*     */   }
/*     */ 
/*     */   public WaveView getWaveView()
/*     */   {
/* 187 */     return this.viewer;
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*     */     int i;
/* 194 */     if (paramActionEvent.getSource() == this.minus) {
/* 195 */       i = this.viewer.getResolution();
/* 196 */       if ((i > 0) && (i <= 1024)) {
/* 197 */         i *= 2;
/* 198 */         setResolution(i);
/*     */ 
/* 200 */         this.plus.setEnabled(true);
/* 201 */         if (i > 1024) this.minus.setEnabled(false);
/* 202 */         this.viewer.setResolution(i);
/*     */       }
/*     */     }
/* 205 */     if (paramActionEvent.getSource() == this.plus) {
/* 206 */       i = this.viewer.getResolution();
/* 207 */       if (i > 1) {
/* 208 */         i /= 2;
/* 209 */         setResolution(i);
/*     */ 
/* 211 */         this.minus.setEnabled(true);
/* 212 */         if (i < 2) this.plus.setEnabled(false);
/* 213 */         this.viewer.setResolution(i);
/*     */       }
/*     */     }
/* 216 */     if (paramActionEvent.getSource() == this.play) {
/* 217 */       this.stop.setEnabled(true);
/* 218 */       this.viewer.playFile();
/*     */     }
/* 220 */     if (paramActionEvent.getSource() == this.stop) {
/* 221 */       this.viewer.pauseFile();
/* 222 */       this.stop.setEnabled(false);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void adjustmentValueChanged(AdjustmentEvent paramAdjustmentEvent)
/*     */   {
/* 230 */     this.viewer.setStartPos(this.scroll.getValue());
/* 231 */     this.ruler.repaint();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.wave.WaveScrollPanel
 * JD-Core Version:    0.5.3
 */