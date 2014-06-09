/*     */ package jm.gui.histogram;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Menu;
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Read;
/*     */ 
/*     */ public class HistogramFrame extends Frame
/*     */   implements WindowListener, ActionListener, JMC
/*     */ {
/*     */   private Score score;
/*     */   private MenuItem showPitch;
/*     */   private MenuItem showRhythm;
/*     */   private MenuItem showDynamic;
/*     */   private MenuItem showPan;
/*     */   private MenuItem open;
/*     */   private MenuItem openXml;
/*     */   private MenuItem saveAs;
/*     */   private MenuItem quit;
/*     */   private Histogram histo;
/*     */ 
/*     */   public HistogramFrame()
/*     */   {
/*  59 */     this(new Score(), 0);
/*     */   }
/*     */ 
/*     */   public HistogramFrame(Score paramScore)
/*     */   {
/*  66 */     this(paramScore, 0);
/*     */   }
/*     */ 
/*     */   public HistogramFrame(Score paramScore, int paramInt)
/*     */   {
/*  76 */     this(paramScore, paramInt, 0, 0);
/*     */   }
/*     */ 
/*     */   public HistogramFrame(Score paramScore, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  89 */     super(paramScore.getTitle());
/*     */ 
/*  49 */     this.score = new Score();
/*     */ 
/*  90 */     this.score = paramScore;
/*  91 */     setTitle(paramInt1);
/*  92 */     setBackground(Color.white);
/*  93 */     setSize(350, 558);
/*  94 */     this.histo = new Histogram(paramScore, paramInt1, 0, 0, getSize().width);
/*  95 */     setLocation(paramInt2, paramInt3);
/*  96 */     add(this.histo);
/*     */ 
/*  99 */     addWindowListener(this);
/*     */ 
/* 102 */     MenuBar localMenuBar = new MenuBar();
/* 103 */     Menu localMenu = new Menu("Histogram", true);
/*     */ 
/* 105 */     this.showPitch = new MenuItem("Pitch", new MenuShortcut(80));
/* 106 */     this.showPitch.addActionListener(this);
/* 107 */     localMenu.add(this.showPitch);
/*     */ 
/* 109 */     this.showRhythm = new MenuItem("Rhythm", new MenuShortcut(82));
/* 110 */     this.showRhythm.addActionListener(this);
/* 111 */     localMenu.add(this.showRhythm);
/*     */ 
/* 114 */     this.showDynamic = new MenuItem("Dynamic", new MenuShortcut(68));
/* 115 */     this.showDynamic.addActionListener(this);
/* 116 */     localMenu.add(this.showDynamic);
/*     */ 
/* 118 */     this.showPan = new MenuItem("Pan", new MenuShortcut(80, true));
/* 119 */     this.showPan.addActionListener(this);
/* 120 */     localMenu.add(this.showPan);
/*     */ 
/* 122 */     MenuItem localMenuItem1 = new MenuItem("-");
/* 123 */     localMenu.add(localMenuItem1);
/*     */ 
/* 125 */     this.open = new MenuItem("Open MIDI file...", new MenuShortcut(79));
/* 126 */     this.open.addActionListener(this);
/* 127 */     localMenu.add(this.open);
/*     */ 
/* 129 */     this.openXml = new MenuItem("Open jMusic XML file...");
/* 130 */     this.openXml.addActionListener(this);
/* 131 */     localMenu.add(this.openXml);
/*     */ 
/* 133 */     this.saveAs = new MenuItem("Save data as...", new MenuShortcut(83));
/* 134 */     this.saveAs.addActionListener(this);
/* 135 */     localMenu.add(this.saveAs);
/*     */ 
/* 137 */     MenuItem localMenuItem2 = new MenuItem("-");
/* 138 */     localMenu.add(localMenuItem2);
/*     */ 
/* 140 */     this.quit = new MenuItem("Quit", new MenuShortcut(81));
/* 141 */     this.quit.addActionListener(this);
/* 142 */     localMenu.add(this.quit);
/*     */ 
/* 144 */     localMenuBar.add(localMenu);
/* 145 */     setMenuBar(localMenuBar);
/*     */ 
/* 147 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   private void setTitle(int paramInt)
/*     */   {
/* 155 */     if (paramInt == 0) setTitle("jMusic Pitch Histogram: '" + this.score.getTitle() + "'");
/* 156 */     if (paramInt == 1) setTitle("jMusic Rhythm Histogram: '" + this.score.getTitle() + "'");
/* 157 */     if (paramInt == 2) setTitle("jMusic Dynamic Histogram: '" + this.score.getTitle() + "'");
/* 158 */     if (paramInt != 3) return; setTitle("jMusic Pan Histogram: '" + this.score.getTitle() + "'");
/*     */   }
/*     */ 
/*     */   private void changeDataType(int paramInt)
/*     */   {
/* 163 */     setTitle(paramInt);
/* 164 */     this.histo.setType(paramInt);
/* 165 */     repaint();
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent)
/*     */   {
/* 170 */     dispose(); }
/*     */ 
/*     */   public void windowActivated(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void windowClosed(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void windowDeactivated(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void windowIconified(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void windowDeiconified(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent) { }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 185 */     if (paramActionEvent.getSource() == this.showPitch) changeDataType(0);
/* 186 */     if (paramActionEvent.getSource() == this.showRhythm) changeDataType(1);
/* 187 */     if (paramActionEvent.getSource() == this.showDynamic) changeDataType(2);
/* 188 */     if (paramActionEvent.getSource() == this.showPan) changeDataType(3);
/* 189 */     if (paramActionEvent.getSource() == this.open) openMIDIFile();
/* 190 */     if (paramActionEvent.getSource() == this.openXml) openXMLFile();
/* 191 */     if (paramActionEvent.getSource() == this.saveAs) this.histo.saveData();
/* 192 */     if (paramActionEvent.getSource() != this.quit) return; System.exit(0);
/*     */   }
/*     */ 
/*     */   public void openMIDIFile()
/*     */   {
/* 200 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to display.", 0);
/* 201 */     localFileDialog.show();
/* 202 */     String str = localFileDialog.getFile();
/* 203 */     if (str != null) {
/* 204 */       Score localScore = new Score();
/* 205 */       Read.midi(localScore, localFileDialog.getDirectory() + str);
/* 206 */       this.score = localScore;
/* 207 */       this.histo.setScore(localScore);
/* 208 */       changeDataType(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void openXMLFile()
/*     */   {
/* 216 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a jMusic XML file to display.", 0);
/* 217 */     localFileDialog.show();
/* 218 */     String str = localFileDialog.getFile();
/* 219 */     if (str != null) {
/* 220 */       Score localScore = new Score();
/* 221 */       Read.xml(localScore, localFileDialog.getDirectory() + str);
/* 222 */       this.score = localScore;
/* 223 */       this.histo.setScore(localScore);
/* 224 */       changeDataType(0);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.histogram.HistogramFrame
 * JD-Core Version:    0.5.3
 */