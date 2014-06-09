/*     */ package jm.gui.sketch;
/*     */ 
/*     */ import java.awt.Adjustable;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.Menu;
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
/*     */ import java.awt.Panel;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.midi.MidiSynth;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Read;
/*     */ import jm.util.Write;
/*     */ 
/*     */ public class SketchScore extends Frame
/*     */   implements WindowListener, ActionListener
/*     */ {
/*     */   private static int maxWidth;
/*     */   private static int maxParts;
/*     */   protected static Score score;
/*     */   protected double beatWidth;
/*     */   private Panel pan;
/*     */   private SketchScoreArea sketchScoreArea;
/*     */   private SketchRuler ruler;
/*     */   private MenuItem play;
/*     */   private MenuItem speedUp;
/*     */   private MenuItem slowDown;
/*     */   private MenuItem clear;
/*     */   private MenuItem saveMIDI;
/*     */   private MenuItem quit;
/*     */   private MenuItem openMIDI;
/*     */   private MenuItem openXML;
/*     */   private MenuItem saveXML;
/*     */ 
/*     */   public SketchScore(Score paramScore)
/*     */   {
/*  64 */     this(paramScore, 0, 0);
/*     */   }
/*     */ 
/*     */   public SketchScore(Score paramScore, int paramInt1, int paramInt2) {
/*  68 */     super("jMusic Sketch: '" + paramScore.getTitle() + "'");
/*     */ 
/*  54 */     this.beatWidth = 10.0D;
/*     */ 
/*  69 */     score = paramScore;
/*  70 */     getWidthAndParts();
/*     */ 
/*  73 */     addWindowListener(this);
/*     */ 
/*  75 */     this.pan = new Panel();
/*  76 */     this.pan.setLayout(new BorderLayout());
/*  77 */     this.sketchScoreArea = new SketchScoreArea(paramScore, maxWidth, this.beatWidth);
/*  78 */     this.sketchScoreArea.setSketchScore(this);
/*  79 */     this.pan.add("Center", this.sketchScoreArea);
/*     */ 
/*  82 */     this.ruler = new SketchRuler(this);
/*  83 */     this.pan.add("South", this.ruler);
/*     */ 
/*  86 */     ScrollPane localScrollPane = new ScrollPane(1);
/*  87 */     localScrollPane.getHAdjustable().setUnitIncrement(20);
/*  88 */     localScrollPane.add(this.pan);
/*  89 */     add(localScrollPane);
/*     */ 
/*  92 */     MenuBar localMenuBar = new MenuBar();
/*  93 */     Menu localMenu = new Menu("Sketch", true);
/*     */ 
/*  95 */     this.play = new MenuItem("Play @ " + paramScore.getTempo() + " bpm", new MenuShortcut(80));
/*  96 */     this.play.addActionListener(this);
/*  97 */     localMenu.add(this.play);
/*     */ 
/*  99 */     this.speedUp = new MenuItem("Speed Up");
/* 100 */     this.speedUp.addActionListener(this);
/* 101 */     localMenu.add(this.speedUp);
/*     */ 
/* 103 */     this.slowDown = new MenuItem("Slow Down");
/* 104 */     this.slowDown.addActionListener(this);
/* 105 */     localMenu.add(this.slowDown);
/*     */ 
/* 107 */     this.clear = new MenuItem("Clear notes");
/* 108 */     this.clear.addActionListener(this);
/* 109 */     localMenu.add(this.clear);
/*     */ 
/* 111 */     MenuItem localMenuItem = new MenuItem("-");
/* 112 */     localMenu.add(localMenuItem);
/*     */ 
/* 114 */     this.openMIDI = new MenuItem("Open a MIDI file...", new MenuShortcut(79));
/* 115 */     this.openMIDI.addActionListener(this);
/* 116 */     localMenu.add(this.openMIDI);
/*     */ 
/* 118 */     this.openXML = new MenuItem("Open a jMusic XML file...");
/* 119 */     this.openXML.addActionListener(this);
/* 120 */     localMenu.add(this.openXML);
/*     */ 
/* 122 */     this.saveMIDI = new MenuItem("Save as MIDI file", new MenuShortcut(83));
/* 123 */     this.saveMIDI.addActionListener(this);
/* 124 */     localMenu.add(this.saveMIDI);
/*     */ 
/* 126 */     this.saveXML = new MenuItem("Save as a jMusic XML file");
/* 127 */     this.saveXML.addActionListener(this);
/* 128 */     localMenu.add(this.saveXML);
/*     */ 
/* 130 */     this.quit = new MenuItem("Quit", new MenuShortcut(81));
/* 131 */     this.quit.addActionListener(this);
/* 132 */     localMenu.add(this.quit);
/*     */ 
/* 134 */     localMenuBar.add(localMenu);
/* 135 */     setMenuBar(localMenuBar);
/*     */ 
/* 139 */     setSize(650, this.sketchScoreArea.getHeight() + this.ruler.getHeight());
/* 140 */     setLocation(paramInt1, paramInt2);
/* 141 */     show();
/*     */   }
/*     */ 
/*     */   public SketchScoreArea getSketchScoreArea()
/*     */   {
/* 150 */     return this.sketchScoreArea;
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent)
/*     */   {
/* 155 */     dispose(); }
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
/*     */   public void update() {
/* 170 */     this.sketchScoreArea.setScore(score);
/* 171 */     this.pan.repaint();
/* 172 */     this.sketchScoreArea.setSize((int)Math.round(score.getEndTime() * this.beatWidth), this.sketchScoreArea.getHeight());
/* 173 */     this.sketchScoreArea.setBeatWidth(this.beatWidth);
/* 174 */     this.sketchScoreArea.repaint();
/* 175 */     this.ruler.repaint();
/*     */ 
/* 177 */     setSize(getSize().width, this.sketchScoreArea.getHeight() + this.ruler.getHeight());
/* 178 */     pack();
/*     */   }
/*     */ 
/*     */   private void getWidthAndParts() {
/* 182 */     Enumeration localEnumeration1 = score.getPartList().elements();
/* 183 */     while (localEnumeration1.hasMoreElements()) {
/* 184 */       Part localPart = (Part)localEnumeration1.nextElement();
/* 185 */       maxParts += 1;
/* 186 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 187 */       while (localEnumeration2.hasMoreElements()) {
/* 188 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 189 */         Enumeration localEnumeration3 = localPhrase.getNoteList().elements();
/* 190 */         maxWidth = (int)(localPhrase.getStartTime() * this.beatWidth);
/* 191 */         while (localEnumeration3.hasMoreElements()) {
/* 192 */           Note localNote = (Note)localEnumeration3.nextElement();
/* 193 */           maxWidth += (int)(localNote.getRhythmValue() * this.beatWidth);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 201 */     if (paramActionEvent.getSource() == this.play) playScore();
/* 202 */     if (paramActionEvent.getSource() == this.speedUp) speedItUp();
/* 203 */     if (paramActionEvent.getSource() == this.slowDown) slowItDown();
/* 204 */     if (paramActionEvent.getSource() == this.clear) clearNotes();
/* 205 */     if (paramActionEvent.getSource() == this.quit) System.exit(0);
/* 206 */     if (paramActionEvent.getSource() == this.saveMIDI) saveMidi();
/* 207 */     if (paramActionEvent.getSource() == this.openMIDI) openMidi();
/* 208 */     if (paramActionEvent.getSource() == this.saveXML) saveXMLFile();
/* 209 */     if (paramActionEvent.getSource() != this.openXML) return; openXMLFile();
/*     */   }
/*     */ 
/*     */   private void playScore() {
/* 213 */     MidiSynth localMidiSynth = new MidiSynth();
/*     */     try {
/* 215 */       localMidiSynth.play(score);
/*     */     } catch (Exception localException) {
/* 217 */       System.err.println("MIDI Playback Error:" + localException);
/* 218 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void speedItUp() {
/* 223 */     double d = score.getTempo() + 10.0D;
/* 224 */     if (d > 250.0D) d = 250.0D;
/* 225 */     score.setTempo(d);
/* 226 */     this.play.setLabel("Play @ " + d + " bpm");
/*     */   }
/*     */ 
/*     */   private void slowItDown() {
/* 230 */     double d = score.getTempo() - 10.0D;
/* 231 */     if (d < 20.0D) d = 20.0D;
/* 232 */     score.setTempo(d);
/* 233 */     this.play.setLabel("Play @ " + d + " bpm");
/*     */   }
/*     */ 
/*     */   private void clearNotes() {
/* 237 */     score.removeAllParts();
/* 238 */     this.sketchScoreArea.repaint();
/*     */   }
/*     */ 
/*     */   public void saveMidi()
/*     */   {
/* 245 */     FileDialog localFileDialog = new FileDialog(this, "Save score as a MIDI file ...", 1);
/* 246 */     localFileDialog.setFile("FileName.mid");
/* 247 */     localFileDialog.show();
/*     */ 
/* 249 */     if (localFileDialog.getFile() != null)
/* 250 */       Write.midi(score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void saveXMLFile()
/*     */   {
/* 258 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Save as a jMusic XML file...", 1);
/*     */ 
/* 261 */     localFileDialog.setFile("FileName.xml");
/* 262 */     localFileDialog.show();
/* 263 */     if (localFileDialog.getFile() != null)
/* 264 */       Write.xml(score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void openMidi()
/*     */   {
/* 272 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to display.", 0);
/*     */ 
/* 275 */     localFileDialog.show();
/* 276 */     String str = localFileDialog.getFile();
/* 277 */     if (str != null) {
/* 278 */       Score localScore = new Score();
/* 279 */       Read.midi(localScore, localFileDialog.getDirectory() + str);
/* 280 */       score = localScore;
/* 281 */       update();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void openXMLFile()
/*     */   {
/* 289 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a jMusic XML file to display.", 0);
/*     */ 
/* 292 */     localFileDialog.show();
/* 293 */     String str = localFileDialog.getFile();
/* 294 */     if (str != null) {
/* 295 */       Score localScore = new Score();
/* 296 */       Read.xml(localScore, localFileDialog.getDirectory() + str);
/* 297 */       score = localScore;
/* 298 */       update();
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.sketch.SketchScore
 * JD-Core Version:    0.5.3
 */