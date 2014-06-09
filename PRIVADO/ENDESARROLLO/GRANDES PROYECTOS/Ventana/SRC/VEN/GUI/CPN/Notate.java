/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Adjustable;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Menu;
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
/*     */ import java.awt.Panel;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Play;
/*     */ import jm.util.Read;
/*     */ import jm.util.Write;
/*     */ 
/*     */ public class Notate extends Frame
/*     */   implements ActionListener, WindowListener, JMC
/*     */ {
/*     */   private Score score;
/*     */   private Phrase[] phraseArray;
/*     */   private Stave[] staveArray;
/*     */   private int scrollHeight;
/*     */   private int locationX;
/*     */   private int locationY;
/*     */   private Dialog keyDialog;
/*     */   private Dialog timeDialog;
/*     */   private MenuItem keySig;
/*     */   private MenuItem open;
/*     */   private MenuItem openJmXml;
/*     */   private MenuItem openjm;
/*     */   private MenuItem play;
/*     */   private MenuItem stop;
/*     */   private MenuItem delete;
/*     */   private MenuItem clear;
/*     */   private MenuItem newStave;
/*     */   private MenuItem close;
/*     */   private MenuItem timeSig;
/*     */   private MenuItem saveJmXml;
/*     */   private MenuItem saveJM;
/*     */   private MenuItem saveMidi;
/*     */   private MenuItem quit;
/*     */   private MenuItem trebleStave;
/*     */   private MenuItem bassStave;
/*     */   private MenuItem pianoStave;
/*     */   private MenuItem grandStave;
/*     */   private MenuItem automaticStave;
/*     */   private MenuItem appendMidiFile;
/*     */   private MenuItem insertMidiFile;
/*     */   private MenuItem setParameters;
/*     */   private MenuItem playAll;
/*     */   private MenuItem playMeasure;
/*     */   private MenuItem repeatAll;
/*     */   private MenuItem repeatMeasure;
/*     */   private MenuItem stopPlay;
/*     */   private MenuItem earTrain;
/*     */   private MenuItem addNotes;
/*     */   private MenuItem adjustTiming;
/*     */   private MenuItem viewDetails;
/*     */   private MenuItem viewTitle;
/*     */   private MenuItem viewZoom;
/*     */   private MenuItem barNumbers;
/*     */   public boolean timeToStop;
/*     */   private Panel scoreBG;
/*     */   private GridBagConstraints constraints;
/*     */   private GridBagLayout layout;
/*     */   private ScrollPane scroll;
/*     */   private String lastFileName;
/*     */   private String lastDirectory;
/*     */   private String fileNameFilter;
/*     */   private boolean zoomed;
/*     */   private Phrase beforeZoom;
/*     */   private Phrase afterZoom;
/*     */   private int height;
/*     */   private int width;
/*     */ 
/*     */   public Notate()
/*     */   {
/*  99 */     this(new Phrase(), 0, 0);
/* 100 */     clearZoom();
/*     */   }
/*     */ 
/*     */   public Notate(int paramInt1, int paramInt2) {
/* 104 */     this(new Phrase(), paramInt1, paramInt2);
/* 105 */     clearZoom();
/*     */   }
/*     */ 
/*     */   public Notate(Phrase paramPhrase) {
/* 109 */     this(paramPhrase, 0, 0);
/* 110 */     clearZoom();
/*     */   }
/*     */ 
/*     */   private void clearZoom() {
/* 114 */     this.zoomed = false;
/*     */   }
/*     */ 
/*     */   public Notate(Phrase paramPhrase, int paramInt1, int paramInt2) {
/* 118 */     super("CPN: " + paramPhrase.getTitle());
/*     */ 
/*  58 */     this.scrollHeight = 130; this.locationX = 0; this.locationY = 0;
/*     */ 
/*  87 */     this.lastFileName = "*.mid";
/*  88 */     this.lastDirectory = "";
/*  89 */     this.fileNameFilter = "*.mid";
/*     */ 
/*  92 */     this.beforeZoom = new Phrase();
/*  93 */     this.afterZoom = new Phrase();
/*     */ 
/*  95 */     this.height = 0;
/*  96 */     this.width = 700;
/*     */ 
/* 119 */     clearZoom();
/* 120 */     this.score = new Score(new Part(paramPhrase));
/* 121 */     this.locationX = paramInt1;
/* 122 */     this.locationY = paramInt2;
/* 123 */     this.score = new Score(new Part(paramPhrase));
/* 124 */     init();
/*     */   }
/*     */ 
/*     */   public Notate(Score paramScore, int paramInt1, int paramInt2) {
/* 128 */     super("CPN: " + paramScore.getTitle());
/*     */ 
/*  58 */     this.scrollHeight = 130; this.locationX = 0; this.locationY = 0;
/*     */ 
/*  87 */     this.lastFileName = "*.mid";
/*  88 */     this.lastDirectory = "";
/*  89 */     this.fileNameFilter = "*.mid";
/*     */ 
/*  92 */     this.beforeZoom = new Phrase();
/*  93 */     this.afterZoom = new Phrase();
/*     */ 
/*  95 */     this.height = 0;
/*  96 */     this.width = 700;
/*     */ 
/* 129 */     clearZoom();
/* 130 */     this.score = paramScore;
/* 131 */     this.locationX = paramInt1;
/* 132 */     this.locationY = paramInt2;
/* 133 */     init();
/*     */   }
/*     */ 
/*     */   public void init() {
/* 137 */     addWindowListener(this);
/*     */ 
/* 139 */     MenuBar localMenuBar = new MenuBar();
/* 140 */     Menu localMenu1 = new Menu("File", true);
/* 141 */     Menu localMenu2 = new Menu("Tools", true);
/* 142 */     Menu localMenu3 = new Menu("Play", true);
/* 143 */     Menu localMenu4 = new Menu("View", true);
/*     */ 
/* 145 */     this.newStave = new MenuItem("New", new MenuShortcut(78));
/* 146 */     this.newStave.addActionListener(this);
/* 147 */     localMenu1.add(this.newStave);
/*     */ 
/* 150 */     this.open = new MenuItem("Open MIDI file...", new MenuShortcut(79));
/* 151 */     this.open.addActionListener(this);
/* 152 */     localMenu1.add(this.open);
/*     */ 
/* 154 */     this.openJmXml = new MenuItem("Open jMusic XML file...");
/* 155 */     this.openJmXml.addActionListener(this);
/* 156 */     localMenu1.add(this.openJmXml);
/*     */ 
/* 158 */     this.openjm = new MenuItem("Open jm file..");
/* 159 */     this.openjm.addActionListener(this);
/* 160 */     localMenu1.add(this.openjm);
/*     */ 
/* 162 */     this.close = new MenuItem("Close", new MenuShortcut(87));
/* 163 */     this.close.addActionListener(this);
/* 164 */     localMenu1.add(this.close);
/*     */ 
/* 166 */     localMenu1.add("-");
/* 167 */     this.delete = new MenuItem("Delete last note", new MenuShortcut(68));
/* 168 */     this.delete.addActionListener(this);
/* 169 */     localMenu1.add(this.delete);
/*     */ 
/* 171 */     this.clear = new MenuItem("Clear all notes", new MenuShortcut(67));
/* 172 */     this.clear.addActionListener(this);
/* 173 */     localMenu1.add(this.clear);
/*     */ 
/* 175 */     localMenu1.add("-");
/*     */ 
/* 177 */     this.keySig = new MenuItem("Key Signature", new MenuShortcut(75));
/* 178 */     this.keySig.addActionListener(this);
/* 179 */     localMenu1.add(this.keySig);
/*     */ 
/* 181 */     this.timeSig = new MenuItem("Time Signature", new MenuShortcut(84));
/* 182 */     this.timeSig.addActionListener(this);
/* 183 */     localMenu1.add(this.timeSig);
/*     */ 
/* 185 */     localMenu1.add("-");
/*     */ 
/* 187 */     this.saveMidi = new MenuItem("Save as a MIDI file...", new MenuShortcut(83));
/* 188 */     this.saveMidi.addActionListener(this);
/* 189 */     localMenu1.add(this.saveMidi);
/*     */ 
/* 191 */     this.saveJmXml = new MenuItem("Save as a jMusic XML file...", new MenuShortcut(83, true));
/* 192 */     this.saveJmXml.addActionListener(this);
/* 193 */     localMenu1.add(this.saveJmXml);
/*     */ 
/* 195 */     this.saveJM = new MenuItem("Save as a jm file...");
/* 196 */     this.saveJM.addActionListener(this);
/* 197 */     localMenu1.add(this.saveJM);
/*     */ 
/* 200 */     localMenu1.add("-");
/*     */ 
/* 213 */     this.setParameters = new MenuItem("Set Parameters...");
/* 214 */     this.setParameters.addActionListener(this);
/* 215 */     localMenu2.add(this.setParameters);
/*     */ 
/* 219 */     this.addNotes = new MenuItem("Add Notes by Letter");
/* 220 */     this.addNotes.addActionListener(this);
/* 221 */     localMenu2.add(this.addNotes);
/*     */ 
/* 224 */     this.adjustTiming = new MenuItem("Quantize Timing");
/* 225 */     this.adjustTiming.addActionListener(this);
/* 226 */     localMenu2.add(this.adjustTiming);
/*     */ 
/* 229 */     this.playAll = new MenuItem("Play All", new MenuShortcut(80));
/* 230 */     this.playAll.addActionListener(this);
/* 231 */     localMenu3.add(this.playAll);
/* 232 */     this.repeatAll = new MenuItem("Repeat All");
/* 233 */     this.repeatAll.addActionListener(this);
/* 234 */     localMenu3.add(this.repeatAll);
/*     */ 
/* 237 */     this.playMeasure = new MenuItem("Play Last Measure");
/* 238 */     this.playMeasure.addActionListener(this);
/* 239 */     localMenu3.add(this.playMeasure);
/*     */ 
/* 241 */     this.repeatMeasure = new MenuItem("Repeat Last Measure");
/* 242 */     this.repeatMeasure.addActionListener(this);
/* 243 */     localMenu3.add(this.repeatMeasure);
/*     */ 
/* 245 */     this.stopPlay = new MenuItem("Stop Playback", new MenuShortcut(80, true));
/* 246 */     this.stopPlay.addActionListener(this);
/* 247 */     localMenu3.add(this.stopPlay);
/*     */ 
/* 253 */     Menu localMenu5 = new Menu("Stave");
/* 254 */     localMenu1.add(localMenu5);
/* 255 */     this.trebleStave = new MenuItem("Treble");
/* 256 */     this.trebleStave.addActionListener(this);
/* 257 */     localMenu5.add(this.trebleStave);
/* 258 */     this.bassStave = new MenuItem("Bass");
/* 259 */     this.bassStave.addActionListener(this);
/* 260 */     localMenu5.add(this.bassStave);
/* 261 */     this.pianoStave = new MenuItem("Piano");
/* 262 */     this.pianoStave.addActionListener(this);
/* 263 */     localMenu5.add(this.pianoStave);
/* 264 */     this.grandStave = new MenuItem("Grand");
/* 265 */     this.grandStave.addActionListener(this);
/* 266 */     localMenu5.add(this.grandStave);
/* 267 */     this.automaticStave = new MenuItem("Automatic");
/* 268 */     this.automaticStave.addActionListener(this);
/* 269 */     localMenu5.add(this.automaticStave);
/*     */ 
/* 272 */     localMenu1.add("-");
/*     */ 
/* 275 */     this.quit = new MenuItem("Quit", new MenuShortcut(81));
/* 276 */     this.quit.addActionListener(this);
/* 277 */     localMenu1.add(this.quit);
/*     */ 
/* 280 */     this.viewDetails = new MenuItem("Note data as text");
/* 281 */     this.viewDetails.addActionListener(this);
/* 282 */     localMenu4.add(this.viewDetails);
/*     */ 
/* 284 */     this.viewZoom = new MenuItem("View phrase section", new MenuShortcut(86));
/* 285 */     this.viewZoom.addActionListener(this);
/* 286 */     localMenu4.add(this.viewZoom);
/*     */ 
/* 288 */     this.barNumbers = new MenuItem("Bar Numbers", new MenuShortcut(66));
/* 289 */     this.barNumbers.addActionListener(this);
/* 290 */     localMenu4.add(this.barNumbers);
/*     */ 
/* 292 */     this.viewTitle = new MenuItem("Stave Title");
/* 293 */     this.viewTitle.addActionListener(this);
/* 294 */     localMenu4.add(this.viewTitle);
/*     */ 
/* 297 */     localMenuBar.add(localMenu1);
/* 298 */     localMenuBar.add(localMenu2);
/* 299 */     localMenuBar.add(localMenu3);
/* 300 */     localMenuBar.add(localMenu4);
/* 301 */     setMenuBar(localMenuBar);
/*     */ 
/* 304 */     this.scroll = new ScrollPane(1);
/*     */ 
/* 306 */     this.scroll.getHAdjustable().setUnitIncrement(10);
/* 307 */     this.scroll.getVAdjustable().setUnitIncrement(10);
/*     */ 
/* 309 */     this.scoreBG = new Panel();
/* 310 */     this.layout = new GridBagLayout();
/* 311 */     this.scoreBG.setLayout(this.layout);
/* 312 */     this.constraints = new GridBagConstraints();
/* 313 */     setupConstraints();
/*     */ 
/* 315 */     this.scroll.add(this.scoreBG);
/* 316 */     add(this.scroll);
/*     */ 
/* 318 */     setupArrays();
/* 319 */     makeAppropriateStaves();
/*     */ 
/* 321 */     pack();
/* 322 */     setLocation(this.locationX, this.locationY);
/*     */ 
/* 334 */     show();
/*     */   }
/*     */ 
/*     */   private void setupArrays()
/*     */   {
/* 339 */     this.phraseArray = new Phrase[this.score.size()];
/* 340 */     this.staveArray = new Stave[this.score.size()];
/*     */ 
/* 342 */     for (int i = 0; i < this.staveArray.length; ++i) {
/* 343 */       this.phraseArray[i] = this.score.getPart(i).getPhrase(0);
/* 344 */       this.staveArray[i] = new PianoStave();
/* 345 */       this.staveArray[i].setKeySignature(this.score.getKeySignature());
/* 346 */       this.staveArray[i].setMetre(this.score.getNumerator());
/* 347 */       this.staveArray[i].setBarNumbers(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setupConstraints() {
/* 352 */     this.constraints.weightx = 100.0D;
/* 353 */     this.constraints.weighty = 0.0D;
/* 354 */     this.constraints.gridx = 0;
/* 355 */     this.constraints.gridy = 0;
/* 356 */     this.constraints.gridwidth = 1;
/* 357 */     this.constraints.gridheight = 1;
/*     */ 
/* 359 */     this.constraints.fill = 1;
/*     */   }
/*     */ 
/*     */   private void calcHeight()
/*     */   {
/* 364 */     this.height = 0;
/* 365 */     for (int i = 0; i < this.staveArray.length; ++i)
/* 366 */       this.height += this.staveArray[i].getSize().height;
/*     */   }
/*     */ 
/*     */   private void makeAppropriateStaves()
/*     */   {
/* 372 */     Stave[] arrayOfStave = new Stave[this.staveArray.length];
/* 373 */     for (int i = 0; i < this.score.size(); ++i) {
/* 374 */       Phrase localPhrase = this.score.getPart(i).getPhrase(0);
/* 375 */       arrayOfStave[i] = new PianoStave();
/* 376 */       if ((localPhrase.getHighestPitch() < 93) && (localPhrase.getLowestPitch() > 54))
/* 377 */         arrayOfStave[i] = new TrebleStave();
/* 378 */       else if ((localPhrase.getHighestPitch() < 65) && (localPhrase.getLowestPitch() > 35))
/* 379 */         arrayOfStave[i] = new BassStave();
/* 380 */       else if ((localPhrase.getHighestPitch() > 93) || (localPhrase.getLowestPitch() < 35))
/* 381 */         arrayOfStave[i] = new GrandStave();
/*     */     }
/* 383 */     updateAllStaves(arrayOfStave);
/*     */   }
/*     */ 
/*     */   private void makeTrebleStave()
/*     */   {
/* 388 */     Stave[] arrayOfStave = new Stave[this.score.size()];
/* 389 */     for (int i = 0; i < this.staveArray.length; ++i) {
/* 390 */       arrayOfStave[i] = new TrebleStave();
/*     */     }
/* 392 */     updateAllStaves(arrayOfStave);
/*     */   }
/*     */ 
/*     */   private void updateAllStaves(Stave[] paramArrayOfStave) {
/* 396 */     int i = 0;
/* 397 */     int j = 0;
/* 398 */     int k = 0;
/* 399 */     this.scoreBG.removeAll();
/* 400 */     for (int l = 0; l < this.staveArray.length; ++l)
/*     */     {
/* 402 */       paramArrayOfStave[l].setKeySignature(this.staveArray[l].getKeySignature());
/* 403 */       paramArrayOfStave[l].setMetre(this.staveArray[l].getMetre());
/* 404 */       paramArrayOfStave[l].setBarNumbers(this.staveArray[l].getBarNumbers());
/* 405 */       paramArrayOfStave[l].setPhrase(this.phraseArray[l]);
/*     */ 
/* 407 */       this.staveArray[l] = paramArrayOfStave[l];
/* 408 */       paramArrayOfStave[l] = null;
/*     */ 
/* 410 */       this.constraints.gridy = i;
/* 411 */       if ((this.staveArray[l].getClass().isInstance(new TrebleStave())) || (this.staveArray[l].getClass().isInstance(new BassStave())))
/*     */       {
/* 413 */         j = 1;
/* 414 */       } else if (this.staveArray[l].getClass().isInstance(new PianoStave()))
/* 415 */         j = 2;
/*     */       else {
/* 417 */         j = 3;
/*     */       }
/* 419 */       this.constraints.gridheight = j;
/*     */ 
/* 421 */       this.scoreBG.add(this.staveArray[l], this.constraints);
/* 422 */       i += j;
/* 423 */       k += this.staveArray[l].getPanelHeight();
/*     */     }
/*     */ 
/* 426 */     this.scroll.setSize(new Dimension(this.width, k));
/*     */ 
/* 428 */     Toolkit localToolkit = Toolkit.getDefaultToolkit();
/* 429 */     Dimension localDimension = localToolkit.getScreenSize();
/* 430 */     setSize(new Dimension(this.width, Math.min(localDimension.height - 40, k + 40)));
/*     */ 
/* 432 */     pack();
/*     */   }
/*     */ 
/*     */   private void makeBassStave() {
/* 436 */     Stave[] arrayOfStave = new Stave[this.score.size()];
/* 437 */     for (int i = 0; i < this.staveArray.length; ++i) {
/* 438 */       arrayOfStave[i] = new BassStave();
/*     */     }
/* 440 */     updateAllStaves(arrayOfStave);
/*     */   }
/*     */ 
/*     */   private void makePianoStave()
/*     */   {
/* 463 */     Stave[] arrayOfStave = new Stave[this.score.size()];
/* 464 */     for (int i = 0; i < arrayOfStave.length; ++i) {
/* 465 */       arrayOfStave[i] = new PianoStave();
/*     */     }
/* 467 */     updateAllStaves(arrayOfStave);
/*     */   }
/*     */ 
/*     */   private void makeGrandStave()
/*     */   {
/* 491 */     Stave[] arrayOfStave = new Stave[this.score.size()];
/* 492 */     for (int i = 0; i < arrayOfStave.length; ++i) {
/* 493 */       arrayOfStave[i] = new GrandStave();
/*     */     }
/* 495 */     updateAllStaves(arrayOfStave);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 540 */     if (paramActionEvent.getSource() == this.close) { dispose();
/*     */     }
/* 542 */     else if (paramActionEvent.getSource() == this.newStave) { new Notate();
/*     */     }
/* 544 */     else if (paramActionEvent.getSource() == this.open) { openMidi();
/*     */     }
/* 546 */     else if (paramActionEvent.getSource() == this.openjm) { openJM();
/*     */     }
/* 548 */     else if (paramActionEvent.getSource() == this.openJmXml) { openJMXML();
/*     */     }
/*     */     else
/*     */     {
/*     */       int i;
/* 550 */       if (paramActionEvent.getSource() == this.keySig) {
/* 551 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 552 */           if (this.staveArray[i].getKeySignature() == 0) {
/* 553 */             this.staveArray[i].setKeySignature(2);
/* 554 */             this.staveArray[i].repaint();
/*     */           } else {
/* 556 */             this.staveArray[i].setKeySignature(0);
/* 557 */             this.staveArray[i].repaint();
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 562 */       else if (paramActionEvent.getSource() == this.timeSig) {
/* 563 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 564 */           if (this.staveArray[i].getMetre() == 0.0D) {
/* 565 */             this.staveArray[i].setMetre(4.0D);
/* 566 */             this.staveArray[i].repaint();
/*     */           } else {
/* 568 */             this.staveArray[i].setMetre(0.0D);
/* 569 */             this.staveArray[i].repaint();
/*     */           }
/*     */         }
/*     */ 
/*     */       }
/* 574 */       else if (paramActionEvent.getSource() == this.saveJM) { saveJM();
/*     */       }
/* 576 */       else if (paramActionEvent.getSource() == this.saveJmXml) { saveJMXML();
/*     */       }
/* 578 */       else if (paramActionEvent.getSource() == this.saveMidi) { saveMidi();
/*     */       }
/* 580 */       else if (paramActionEvent.getSource() == this.quit) { System.exit(0);
/*     */       }
/* 582 */       else if (paramActionEvent.getSource() == this.delete) {
/* 583 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 584 */           this.staveArray[i].deleteLastNote();
/*     */         }
/*     */       }
/* 587 */       else if (paramActionEvent.getSource() == this.clear) {
/* 588 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 589 */           this.staveArray[i].getPhrase().empty();
/* 590 */           this.staveArray[i].repaint();
/*     */         }
/*     */ 
/*     */       }
/* 594 */       else if (paramActionEvent.getSource() == this.trebleStave) {
/* 595 */         setCursor(new Cursor(3));
/* 596 */         makeTrebleStave();
/* 597 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 598 */           this.staveArray[i].repaint();
/*     */         }
/* 600 */         setCursor(new Cursor(0));
/*     */       }
/* 603 */       else if (paramActionEvent.getSource() == this.bassStave) {
/* 604 */         setCursor(new Cursor(3));
/* 605 */         makeBassStave();
/* 606 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 607 */           this.staveArray[i].repaint();
/*     */         }
/* 609 */         setCursor(new Cursor(0));
/*     */       }
/* 612 */       else if (paramActionEvent.getSource() == this.pianoStave) {
/* 613 */         setCursor(new Cursor(3));
/* 614 */         makePianoStave();
/* 615 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 616 */           this.staveArray[i].repaint();
/*     */         }
/* 618 */         setCursor(new Cursor(0));
/*     */       }
/* 621 */       else if (paramActionEvent.getSource() == this.grandStave) {
/* 622 */         setCursor(new Cursor(3));
/* 623 */         makeGrandStave();
/* 624 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 625 */           this.staveArray[i].repaint();
/*     */         }
/* 627 */         setCursor(new Cursor(0));
/*     */       }
/* 630 */       else if (paramActionEvent.getSource() == this.automaticStave) {
/* 631 */         setCursor(new Cursor(3));
/* 632 */         makeAppropriateStaves();
/* 633 */         for (i = 0; i < this.staveArray.length; ++i) {
/* 634 */           this.staveArray[i].repaint();
/*     */         }
/* 636 */         setCursor(new Cursor(0));
/*     */       }
/*     */       else
/*     */       {
/*     */         Object localObject;
/* 669 */         if (paramActionEvent.getSource() == this.setParameters) {
/* 670 */           localObject = new ParmScreen(this);
/*     */ 
/* 672 */           double d = this.staveArray[0].getPhrase().getTempo();
/* 673 */           ((ParmScreen)localObject).getParms(this.staveArray[0].getPhrase(), 15, 15);
/*     */ 
/* 675 */           repaint();
/*     */         }
/* 677 */         else if (paramActionEvent.getSource() == this.playAll) {
/* 678 */           Play.midi(this.score, false);
/*     */         }
/* 680 */         else if (paramActionEvent.getSource() == this.repeatAll) { Play.midiCycle(this.score);
/* 681 */         } else if (paramActionEvent.getSource() == this.stopPlay)
/*     */         {
/* 683 */           Play.stopMidi();
/* 684 */           Play.stopCycle();
/*     */         }
/* 686 */         else if (paramActionEvent.getSource() == this.repeatMeasure) {
/* 687 */           Play.midiCycle(getLastMeasure());
/*     */         }
/* 689 */         else if (paramActionEvent.getSource() == this.playMeasure) {
/* 690 */           Play.midi(getLastMeasure(), false);
/*     */         }
/* 693 */         else if (paramActionEvent.getSource() == this.addNotes) {
/* 694 */           localObject = new LetterNotesEditor(this);
/*     */ 
/* 696 */           ((LetterNotesEditor)localObject).getNotes(this.staveArray[0]);
/* 697 */           this.staveArray[0].repaint();
/*     */         }
/* 699 */         else if (paramActionEvent.getSource() == this.adjustTiming) {
/* 700 */           adjustTimeValues(this.staveArray[0].getPhrase());
/* 701 */           this.staveArray[0].repaint();
/*     */         }
/* 703 */         else if (paramActionEvent.getSource() == this.viewDetails) {
/* 704 */           localObject = new PhraseViewer(this);
/*     */ 
/* 706 */           ((PhraseViewer)localObject).showPhrase(this.staveArray[0], this.staveArray[0].getPhrase(), 15, 15);
/*     */         }
/* 711 */         else if (paramActionEvent.getSource() == this.viewZoom) {
/* 712 */           if (!(this.zoomed)) {
/* 713 */             localObject = new CpnZoomScreen(this);
/*     */ 
/* 715 */             this.beforeZoom = this.staveArray[0].getPhrase().copy();
/* 716 */             this.afterZoom = this.staveArray[0].getPhrase().copy();
/* 717 */             this.beforeZoom.empty();
/* 718 */             this.afterZoom.empty();
/* 719 */             ((CpnZoomScreen)localObject).zoomIn(this.beforeZoom, this.staveArray[0].getPhrase(), this.afterZoom);
/*     */ 
/* 723 */             if (this.beforeZoom.size() + this.afterZoom.size() > 0)
/*     */             {
/* 725 */               this.zoomed = true;
/* 726 */               this.viewZoom.setLabel("View complete phrase");
/* 727 */               repaint();
/*     */             }
/*     */           }
/*     */           else {
/* 731 */             CpnZoomScreen.zoomOut(this.beforeZoom, this.staveArray[0].getPhrase(), this.afterZoom);
/*     */ 
/* 735 */             this.zoomed = false;
/* 736 */             this.viewZoom.setLabel("View phrase section");
/* 737 */             repaint();
/*     */           }
/*     */         }
/* 740 */         else if (paramActionEvent.getSource() == this.barNumbers) {
/* 741 */           for (int j = 0; j < this.staveArray.length; ++j) {
/* 742 */             this.staveArray[j].setBarNumbers(!(this.staveArray[j].getBarNumbers()));
/* 743 */             this.staveArray[j].repaint();
/*     */           }
/*     */         } else {
/* 746 */           if (paramActionEvent.getSource() != this.viewTitle) return; toggleDisplayTitle();
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void openMidi()
/*     */   {
/* 757 */     Score localScore = new Score();
/* 758 */     FileDialog localFileDialog = new FileDialog(this, "Select a MIDI file.", 0);
/* 759 */     localFileDialog.setDirectory(this.lastDirectory);
/* 760 */     localFileDialog.setFile(this.lastFileName);
/* 761 */     localFileDialog.show();
/* 762 */     String str = localFileDialog.getFile();
/* 763 */     if (str != null) {
/* 764 */       this.lastFileName = str;
/* 765 */       this.lastDirectory = localFileDialog.getDirectory();
/* 766 */       Read.midi(localScore, this.lastDirectory + str);
/* 767 */       setNewScore(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setNewScore(Score paramScore)
/*     */   {
/* 773 */     this.score = paramScore;
/*     */ 
/* 775 */     setupArrays();
/* 776 */     makeAppropriateStaves();
/*     */   }
/*     */ 
/*     */   public void openJM()
/*     */   {
/* 784 */     FileDialog localFileDialog = new FileDialog(this, "Select a jm score file.", 0);
/* 785 */     localFileDialog.setDirectory(this.lastDirectory);
/* 786 */     localFileDialog.show();
/* 787 */     String str = localFileDialog.getFile();
/* 788 */     if (str != null) {
/* 789 */       Score localScore = new Score();
/* 790 */       this.lastDirectory = localFileDialog.getDirectory();
/* 791 */       Read.jm(localScore, this.lastDirectory + str);
/* 792 */       setNewScore(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void openJMXML()
/*     */   {
/* 801 */     FileDialog localFileDialog = new FileDialog(this, "Select a jMusic XML score file.", 0);
/* 802 */     localFileDialog.setDirectory(this.lastDirectory);
/* 803 */     localFileDialog.show();
/* 804 */     String str = localFileDialog.getFile();
/* 805 */     if (str != null) {
/* 806 */       Score localScore = new Score();
/* 807 */       this.lastDirectory = localFileDialog.getDirectory();
/* 808 */       Read.xml(localScore, this.lastDirectory + str);
/* 809 */       setNewScore(localScore);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void saveMidi()
/*     */   {
/* 819 */     FileDialog localFileDialog = new FileDialog(this, "Save as a MIDI file...", 1);
/* 820 */     localFileDialog.show();
/*     */ 
/* 823 */     if (localFileDialog.getFile() != null)
/* 824 */       Write.midi(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void saveJM()
/*     */   {
/* 851 */     FileDialog localFileDialog = new FileDialog(this, "Save as a jm file...", 1);
/* 852 */     localFileDialog.show();
/*     */ 
/* 855 */     if (localFileDialog.getFile() != null)
/* 856 */       Write.jm(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void saveJMXML()
/*     */   {
/* 864 */     FileDialog localFileDialog = new FileDialog(this, "Save as a jMusic XML file...", 1);
/* 865 */     localFileDialog.show();
/*     */ 
/* 868 */     if (localFileDialog.getFile() != null)
/* 869 */       Write.xml(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public Phrase readMidiPhrase()
/*     */   {
/* 878 */     FileDialog localFileDialog = new FileDialog(this, "Select a MIDI file.", 0);
/* 879 */     localFileDialog.show();
/* 880 */     String str = localFileDialog.getFile();
/* 881 */     Phrase localPhrase = new Phrase(0.0D);
/* 882 */     Score localScore = new Score();
/* 883 */     if (str != null) {
/* 884 */       Read.midi(localScore, localFileDialog.getDirectory() + str);
/*     */     }
/* 886 */     localScore.clean();
/* 887 */     if ((localScore.size() > 0) && (localScore.getPart(0).size() > 0)) localPhrase = localScore.getPart(0).getPhrase(0);
/*     */ 
/* 889 */     return localPhrase;
/*     */   }
/*     */ 
/*     */   private Score getLastMeasure() {
/* 893 */     double d1 = this.phraseArray[0].getNumerator();
/* 894 */     double d2 = this.score.getEndTime();
/* 895 */     int i = (int)(d2 / d1);
/* 896 */     double d3 = d1 * i;
/* 897 */     if (d3 == d2) d3 -= d1;
/* 898 */     Score localScore = this.score.copy(d3, d2);
/*     */ 
/* 900 */     for (int j = 0; j < localScore.size(); ++j) {
/* 901 */       localScore.getPart(j).getPhrase(0).setStartTime(0.0D);
/*     */     }
/* 903 */     return localScore;
/*     */   }
/*     */ 
/*     */   private static double getRhythmAdjustment(double paramDouble1, double paramDouble2)
/*     */   {
/* 910 */     double d1 = paramDouble1 / paramDouble2;
/*     */ 
/* 912 */     double d2 = 1.E-005D;
/*     */ 
/* 914 */     double d3 = 0.0D;
/*     */ 
/* 916 */     double d4 = Math.floor(d1);
/*     */ 
/* 918 */     while ((Math.floor(d1 + d2) > d4) && (d2 > 1.0E-014D)) {
/* 919 */       d3 = d2;
/* 920 */       d2 /= 2.0D;
/*     */     }
/* 922 */     return (d3 * paramDouble2);
/*     */   }
/*     */ 
/*     */   private static void adjustTimeValues(Phrase paramPhrase)
/*     */   {
/*     */     double d1;
/*     */     double d2;
/* 928 */     for (int i = 0; i < paramPhrase.size(); ++i) {
/* 929 */       d1 = paramPhrase.getNote(i).getRhythmValue();
/* 930 */       d2 = getRhythmAdjustment(d1, 0.00390625D);
/* 931 */       paramPhrase.getNote(i).setRhythmValue(d1 + d2);
/*     */     }
/*     */ 
/* 934 */     double d3 = 0.0D;
/* 935 */     for (i = 0; i < paramPhrase.size(); ++i) {
/* 936 */       d1 = paramPhrase.getNote(i).getRhythmValue();
/* 937 */       d3 += d1;
/* 938 */       d2 = getRhythmAdjustment(d3, 1.0D);
/* 939 */       paramPhrase.getNote(i).setRhythmValue(d1 + d2);
/* 940 */       d3 += d2;
/*     */     }
/*     */   }
/*     */ 
/*     */   public void toggleDisplayTitle()
/*     */   {
/* 948 */     for (int i = 0; i < this.staveArray.length; ++i)
/* 949 */       this.staveArray[i].setDisplayTitle(!(this.staveArray[i].getDisplayTitle()));
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent)
/*     */   {
/* 964 */     if (paramWindowEvent.getSource() == this) dispose();
/* 965 */     if (paramWindowEvent.getSource() == this.keyDialog) this.keyDialog.dispose();
/* 966 */     if (paramWindowEvent.getSource() != this.timeDialog) return; this.timeDialog.dispose();
/*     */   }
/*     */ 
/*     */   public void windowClosed(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowIconified(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowDeiconified(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowActivated(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowDeactivated(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   class PlayRepeater extends Thread
/*     */   {
/*     */     JmMidiPlayer midiPlayer;
/*     */     Notate n;
/*     */ 
/*     */     public PlayRepeater(String paramString, Notate paramNotate, JmMidiPlayer paramJmMidiPlayer)
/*     */     {
/* 527 */       super(paramString);
/* 528 */       this.n = paramNotate;
/* 529 */       this.midiPlayer = paramJmMidiPlayer;
/*     */     }
/*     */ 
/*     */     public void run() {
/*     */       do
/* 534 */         this.midiPlayer.play();
/* 535 */       while (!(this.n.timeToStop));
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.Notate
 * JD-Core Version:    0.5.3
 */