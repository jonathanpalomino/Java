/*     */ package jm.gui.show;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Menu;
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Play;
/*     */ import jm.util.Read;
/*     */ import jm.util.Write;
/*     */ 
/*     */ public class ShowScore extends Frame
/*     */   implements WindowListener, ActionListener
/*     */ {
/*     */   private Panel pan;
/*     */   private Score score;
/*     */   private MenuItem saveMIDI;
/*     */   private MenuItem quit;
/*     */   private MenuItem size7;
/*     */   private MenuItem size2;
/*     */   private MenuItem size3;
/*     */   private MenuItem size4;
/*     */   private MenuItem size5;
/*     */   private MenuItem size6;
/*     */   private MenuItem size8;
/*     */   private MenuItem thin;
/*     */   private MenuItem medium;
/*     */   private MenuItem thick;
/*     */   private MenuItem play;
/*     */   private MenuItem saveXML;
/*     */   private MenuItem openXML;
/*     */   private MenuItem openMIDI;
/*     */   private ShowPanel sp;
/*     */ 
/*     */   public ShowScore(Score paramScore)
/*     */   {
/*  68 */     this(paramScore, 0, 0);
/*     */   }
/*     */ 
/*     */   public ShowScore(Score paramScore, int paramInt1, int paramInt2) {
/*  72 */     super("jMusic Show: '" + paramScore.getTitle() + "'");
/*     */ 
/*  59 */     this.score = new Score();
/*     */ 
/*  73 */     this.score = paramScore;
/*     */ 
/*  75 */     addWindowListener(this);
/*     */ 
/*  78 */     this.sp = new ShowPanel(this, paramScore);
/*  79 */     setSize(650, this.sp.getHeight() + 25);
/*     */ 
/*  81 */     add(this.sp);
/*     */ 
/*  83 */     MenuBar localMenuBar = new MenuBar();
/*  84 */     Menu localMenu = new Menu("Show", true);
/*     */ 
/*  86 */     this.size2 = new MenuItem("Size 2");
/*  87 */     this.size2.addActionListener(this);
/*  88 */     localMenu.add(this.size2);
/*     */ 
/*  90 */     this.size3 = new MenuItem("Size 3");
/*  91 */     this.size3.addActionListener(this);
/*  92 */     localMenu.add(this.size3);
/*     */ 
/*  94 */     this.size4 = new MenuItem("Size 4");
/*  95 */     this.size4.addActionListener(this);
/*  96 */     localMenu.add(this.size4);
/*     */ 
/*  98 */     this.size5 = new MenuItem("Size 5");
/*  99 */     this.size5.addActionListener(this);
/* 100 */     localMenu.add(this.size5);
/*     */ 
/* 102 */     this.size6 = new MenuItem("Size 6");
/* 103 */     this.size6.addActionListener(this);
/* 104 */     localMenu.add(this.size6);
/*     */ 
/* 106 */     this.size7 = new MenuItem("Size 7");
/* 107 */     this.size7.addActionListener(this);
/* 108 */     localMenu.add(this.size7);
/*     */ 
/* 110 */     this.size8 = new MenuItem("Size 8");
/* 111 */     this.size8.addActionListener(this);
/* 112 */     localMenu.add(this.size8);
/*     */ 
/* 114 */     MenuItem localMenuItem1 = new MenuItem("-");
/* 115 */     localMenu.add(localMenuItem1);
/*     */ 
/* 117 */     this.thin = new MenuItem("Thin notes");
/* 118 */     this.thin.addActionListener(this);
/* 119 */     localMenu.add(this.thin);
/*     */ 
/* 121 */     this.medium = new MenuItem("Medium notes");
/* 122 */     this.medium.addActionListener(this);
/* 123 */     localMenu.add(this.medium);
/*     */ 
/* 125 */     this.thick = new MenuItem("Thick notes");
/* 126 */     this.thick.addActionListener(this);
/* 127 */     localMenu.add(this.thick);
/*     */ 
/* 129 */     MenuItem localMenuItem2 = new MenuItem("-");
/* 130 */     localMenu.add(localMenuItem2);
/*     */ 
/* 132 */     this.play = new MenuItem("Play MIDI", new MenuShortcut(80));
/* 133 */     this.play.addActionListener(this);
/* 134 */     localMenu.add(this.play);
/*     */ 
/* 136 */     this.openMIDI = new MenuItem("Open a MIDI file...", new MenuShortcut(79));
/* 137 */     this.openMIDI.addActionListener(this);
/* 138 */     localMenu.add(this.openMIDI);
/*     */ 
/* 140 */     this.openXML = new MenuItem("Open a jMusic XML file...");
/* 141 */     this.openXML.addActionListener(this);
/* 142 */     localMenu.add(this.openXML);
/*     */ 
/* 144 */     this.saveMIDI = new MenuItem("Save as MIDI file...", new MenuShortcut(83));
/* 145 */     this.saveMIDI.addActionListener(this);
/* 146 */     localMenu.add(this.saveMIDI);
/*     */ 
/* 148 */     this.saveXML = new MenuItem("Save as jMusic XML file...");
/* 149 */     this.saveXML.addActionListener(this);
/* 150 */     localMenu.add(this.saveXML);
/*     */ 
/* 152 */     this.quit = new MenuItem("Quit", new MenuShortcut(81));
/* 153 */     this.quit.addActionListener(this);
/* 154 */     localMenu.add(this.quit);
/*     */ 
/* 156 */     localMenuBar.add(localMenu);
/* 157 */     setMenuBar(localMenuBar);
/*     */ 
/* 160 */     pack();
/* 161 */     setLocation(paramInt1, paramInt2);
/* 162 */     show();
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent)
/*     */   {
/* 171 */     dispose(); }
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
/*     */   public void windowOpened(WindowEvent paramWindowEvent) {  }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) { if (paramActionEvent.getSource() == this.play) playBackMidi();
/* 185 */     if (paramActionEvent.getSource() == this.quit) System.exit(0);
/* 186 */     if (paramActionEvent.getSource() == this.saveMIDI) saveMidi();
/* 187 */     if (paramActionEvent.getSource() == this.openMIDI) openMidi();
/* 188 */     if (paramActionEvent.getSource() == this.saveXML) saveXMLFile();
/* 189 */     if (paramActionEvent.getSource() == this.openXML) openXMLFile();
/* 190 */     if (paramActionEvent.getSource() == this.size2) resize(2);
/* 191 */     if (paramActionEvent.getSource() == this.size3) resize(3);
/* 192 */     if (paramActionEvent.getSource() == this.size4) resize(4);
/* 193 */     if (paramActionEvent.getSource() == this.size5) resize(5);
/* 194 */     if (paramActionEvent.getSource() == this.size6) resize(6);
/* 195 */     if (paramActionEvent.getSource() == this.size7) resize(7);
/* 196 */     if (paramActionEvent.getSource() == this.size8) resize(8);
/* 197 */     if (paramActionEvent.getSource() == this.thin) this.sp.getShowArea().setThinNote(2);
/* 198 */     if (paramActionEvent.getSource() == this.medium) this.sp.getShowArea().setThinNote(1);
/* 199 */     if (paramActionEvent.getSource() != this.thick) return; this.sp.getShowArea().setThinNote(0);
/*     */   }
/*     */ 
/*     */   private void resize(int paramInt) {
/* 203 */     this.sp.getShowArea().setNoteHeight(paramInt);
/* 204 */     setSize(getSize().width, this.sp.getHeight() + 25);
/* 205 */     pack();
/*     */   }
/*     */ 
/*     */   public void saveMidi()
/*     */   {
/* 212 */     Write.midi(this.score);
/*     */   }
/*     */ 
/*     */   public void saveXMLFile()
/*     */   {
/* 219 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Save as a jMusic XML file...", 1);
/*     */ 
/* 222 */     localFileDialog.show();
/* 223 */     if (localFileDialog.getFile() != null)
/* 224 */       Write.xml(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void openMidi()
/*     */   {
/* 232 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to display...", 0);
/*     */ 
/* 235 */     localFileDialog.show();
/* 236 */     String str = localFileDialog.getFile();
/* 237 */     if (str != null) {
/* 238 */       Score localScore = new Score();
/* 239 */       Read.midi(localScore, localFileDialog.getDirectory() + str);
/* 240 */       this.score = localScore;
/* 241 */       this.sp.setScore(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void openXMLFile()
/*     */   {
/* 249 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a jMusic XML file to display.", 0);
/*     */ 
/* 252 */     localFileDialog.show();
/* 253 */     String str = localFileDialog.getFile();
/* 254 */     if (str != null) {
/* 255 */       Score localScore = new Score();
/* 256 */       Read.xml(localScore, localFileDialog.getDirectory() + str);
/* 257 */       this.score = localScore;
/* 258 */       this.sp.setScore(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void playBackMidi() {
/* 263 */     Play.midi(this.score, false);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.show.ShowScore
 * JD-Core Version:    0.5.3
 */