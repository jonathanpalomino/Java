/*     */ package jm.gui.helper;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Button;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Scrollbar;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.io.PrintStream;
/*     */ import jm.JMC;
/*     */ import jm.audio.Instrument;
/*     */ import jm.midi.MidiSynth;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Play;
/*     */ import jm.util.Read;
/*     */ import jm.util.View;
/*     */ import jm.util.Write;
/*     */ 
/*     */ public class HelperGUI extends Frame
/*     */   implements JMC, ActionListener, AdjustmentListener
/*     */ {
/*  65 */   protected Score score = new Score();
/*     */   private Button composeBtn;
/*     */   private Button playBtn;
/*     */   private Button stopBtn;
/*     */   private Button showBtn;
/*     */   private Button sketchBtn;
/*     */   private Button histogramBtn;
/*     */   private Button printBtn;
/*     */   private Button saveBtn;
/*     */   private Button renderBtn;
/*     */   private Button notateBtn;
/*     */   private Button readMidiBtn;
/*     */   private Button audioViewBtn;
/*     */   private Button audioPlayBtn;
/*     */   private Button audioStopBtn;
/*     */   private Button xmlOpenBtn;
/*     */   private Button xmlSaveBtn;
/*     */   private Scrollbar sliderA;
/*     */   private Scrollbar sliderB;
/*     */   private Scrollbar sliderC;
/*     */   private Scrollbar sliderD;
/*     */   private Scrollbar sliderE;
/*     */   private Label labelA;
/*     */   private Label labelB;
/*     */   private Label labelC;
/*     */   private Label labelD;
/*     */   private Label labelE;
/*     */   private Label commentLabA;
/*     */   private Label commentLabB;
/*     */   private Label commentLabC;
/*     */   private Label commentLabD;
/*     */   private Label commentLabE;
/*     */   protected Instrument[] insts;
/*     */   protected int variableA;
/*     */   protected int variableB;
/*     */   protected int variableC;
/*     */   protected int variableD;
/*     */   protected int variableE;
/*  75 */   private MidiSynth ms = new MidiSynth();
/*     */ 
/*  77 */   private boolean playing = false;
/*     */   protected String audioFileName;
/*     */   protected Panel sliders;
/*     */ 
/*     */   public HelperGUI()
/*     */   {
/*  91 */     super("jMusic Helper GUI");
/*     */ 
/*  93 */     setLayout(new BorderLayout());
/*  94 */     Panel localPanel1 = new Panel(new BorderLayout());
/*     */ 
/*  96 */     add(localPanel1, "Center");
/*  97 */     this.sliders = new Panel();
/*  98 */     this.sliders.setLayout(new GridLayout(6, 1));
/*     */ 
/* 100 */     Panel localPanel2 = new Panel(new BorderLayout());
/* 101 */     Panel localPanel3 = new Panel();
/* 102 */     Panel localPanel4 = new Panel();
/* 103 */     Panel localPanel5 = new Panel(new BorderLayout());
/* 104 */     Panel localPanel6 = new Panel();
/* 105 */     Panel localPanel7 = new Panel();
/* 106 */     Panel localPanel8 = new Panel(new BorderLayout());
/* 107 */     Panel localPanel9 = new Panel(new BorderLayout());
/* 108 */     Panel localPanel10 = new Panel(new BorderLayout());
/* 109 */     Label localLabel1 = new Label("Create and View");
/* 110 */     localLabel1.setAlignment(1);
/* 111 */     localPanel2.add(localLabel1, "North");
/* 112 */     localPanel1.add(localPanel2, "North");
/*     */ 
/* 115 */     Label localLabel2 = new Label("MIDI Options");
/* 116 */     localLabel2.setAlignment(1);
/* 117 */     localPanel5.add(localLabel2, "North");
/* 118 */     localPanel1.add(localPanel5, "Center");
/*     */ 
/* 120 */     Label localLabel3 = new Label("Audio Options");
/* 121 */     localLabel3.setAlignment(1);
/* 122 */     localPanel8.add(localLabel3, "North");
/*     */ 
/* 124 */     localPanel1.add(localPanel8, "South");
/*     */ 
/* 126 */     Panel localPanel11 = new Panel(new BorderLayout());
/* 127 */     Label localLabel4 = new Label("XML Options");
/* 128 */     localLabel4.setAlignment(1);
/* 129 */     localPanel9.add(localLabel4, "North");
/* 130 */     localPanel11.add(localPanel9, "North");
/*     */ 
/* 132 */     Label localLabel5 = new Label("Compositional parameters");
/* 133 */     localLabel5.setAlignment(1);
/* 134 */     Panel localPanel12 = new Panel();
/* 135 */     localPanel12.add(localLabel5);
/* 136 */     localPanel10.add(localPanel12, "North");
/* 137 */     localPanel11.add(localPanel10, "Center");
/* 138 */     add(localPanel11, "South");
/*     */ 
/* 141 */     Panel localPanel13 = new Panel(new GridLayout(3, 1));
/* 142 */     this.composeBtn = new Button("Compose");
/* 143 */     this.composeBtn.addActionListener(this);
/* 144 */     Panel localPanel14 = new Panel();
/* 145 */     localPanel14.add(this.composeBtn);
/* 146 */     localPanel13.add(localPanel14);
/*     */ 
/* 148 */     this.showBtn = new Button("View.show()");
/* 149 */     this.showBtn.addActionListener(this);
/* 150 */     this.showBtn.setEnabled(false);
/* 151 */     localPanel3.add(this.showBtn);
/*     */ 
/* 153 */     this.notateBtn = new Button("View.notate()");
/* 154 */     this.notateBtn.addActionListener(this);
/* 155 */     this.notateBtn.setEnabled(false);
/* 156 */     localPanel3.add(this.notateBtn);
/*     */ 
/* 158 */     this.printBtn = new Button("View.print()");
/* 159 */     this.printBtn.addActionListener(this);
/* 160 */     this.printBtn.setEnabled(false);
/* 161 */     localPanel3.add(this.printBtn);
/* 162 */     localPanel13.add(localPanel3);
/*     */ 
/* 164 */     this.sketchBtn = new Button("View.sketch()");
/* 165 */     this.sketchBtn.addActionListener(this);
/* 166 */     this.sketchBtn.setEnabled(false);
/* 167 */     localPanel4.add(this.sketchBtn);
/*     */ 
/* 169 */     this.histogramBtn = new Button("View.histogram()");
/* 170 */     this.histogramBtn.addActionListener(this);
/* 171 */     this.histogramBtn.setEnabled(false);
/* 172 */     localPanel4.add(this.histogramBtn);
/* 173 */     localPanel13.add(localPanel4);
/* 174 */     localPanel2.add(localPanel13, "Center");
/*     */ 
/* 177 */     Panel localPanel15 = new Panel(new GridLayout(2, 1));
/* 178 */     this.playBtn = new Button("Play.midi()");
/* 179 */     this.playBtn.addActionListener(this);
/* 180 */     this.playBtn.setEnabled(false);
/* 181 */     localPanel7.add(this.playBtn);
/*     */ 
/* 183 */     this.stopBtn = new Button("Stop MIDI");
/* 184 */     this.stopBtn.addActionListener(this);
/* 185 */     this.stopBtn.setEnabled(false);
/* 186 */     localPanel7.add(this.stopBtn);
/* 187 */     localPanel15.add(localPanel7);
/*     */ 
/* 189 */     this.saveBtn = new Button("Write.midi()");
/* 190 */     this.saveBtn.addActionListener(this);
/* 191 */     this.saveBtn.setEnabled(false);
/* 192 */     localPanel6.add(this.saveBtn);
/*     */ 
/* 194 */     this.readMidiBtn = new Button("Read.midi()");
/* 195 */     this.readMidiBtn.addActionListener(this);
/* 196 */     this.readMidiBtn.setEnabled(true);
/* 197 */     localPanel6.add(this.readMidiBtn);
/* 198 */     localPanel15.add(localPanel6);
/* 199 */     localPanel5.add(localPanel15, "Center");
/*     */ 
/* 202 */     Panel localPanel16 = new Panel();
/* 203 */     this.renderBtn = new Button("Write.au()");
/* 204 */     this.renderBtn.addActionListener(this);
/* 205 */     this.renderBtn.setEnabled(false);
/* 206 */     localPanel16.add(this.renderBtn);
/* 207 */     localPanel8.add(localPanel16, "Center");
/*     */ 
/* 209 */     this.audioViewBtn = new Button("View.au()");
/* 210 */     this.audioViewBtn.addActionListener(this);
/* 211 */     this.audioViewBtn.setEnabled(false);
/* 212 */     localPanel16.add(this.audioViewBtn);
/*     */ 
/* 214 */     this.audioPlayBtn = new Button("Play.au()");
/* 215 */     this.audioPlayBtn.addActionListener(this);
/* 216 */     this.audioPlayBtn.setEnabled(false);
/* 217 */     localPanel16.add(this.audioPlayBtn);
/*     */ 
/* 220 */     Panel localPanel17 = new Panel();
/* 221 */     this.xmlOpenBtn = new Button("Read.xml()");
/* 222 */     this.xmlOpenBtn.addActionListener(this);
/* 223 */     this.xmlOpenBtn.setEnabled(true);
/* 224 */     localPanel17.add(this.xmlOpenBtn);
/*     */ 
/* 226 */     this.xmlSaveBtn = new Button("Write.xml()");
/* 227 */     this.xmlSaveBtn.addActionListener(this);
/* 228 */     this.xmlSaveBtn.setEnabled(false);
/* 229 */     localPanel17.add(this.xmlSaveBtn);
/* 230 */     localPanel9.add(localPanel17, "Center");
/*     */ 
/* 240 */     Panel localPanel18 = new Panel(new GridLayout(1, 3));
/* 241 */     this.labelA = new Label(" variableA = 0");
/* 242 */     localPanel18.add(this.labelA);
/* 243 */     this.sliderA = new Scrollbar(0, 0, 15, 0, 142);
/* 244 */     this.sliderA.addAdjustmentListener(this);
/* 245 */     localPanel18.add(this.sliderA);
/* 246 */     this.commentLabA = new Label(" No Comment ");
/* 247 */     localPanel18.add(this.commentLabA);
/* 248 */     this.sliders.add(localPanel18);
/*     */ 
/* 250 */     Panel localPanel19 = new Panel(new GridLayout(1, 3));
/* 251 */     this.labelB = new Label(" variableB = 0");
/* 252 */     localPanel19.add(this.labelB);
/* 253 */     this.sliderB = new Scrollbar(0, 0, 15, 0, 142);
/* 254 */     this.sliderB.addAdjustmentListener(this);
/* 255 */     localPanel19.add(this.sliderB);
/* 256 */     this.commentLabB = new Label(" No Comment ");
/* 257 */     localPanel19.add(this.commentLabB);
/* 258 */     this.sliders.add(localPanel19);
/*     */ 
/* 260 */     Panel localPanel20 = new Panel(new GridLayout(1, 3));
/* 261 */     this.labelC = new Label(" variableC = 0");
/* 262 */     localPanel20.add(this.labelC);
/* 263 */     this.sliderC = new Scrollbar(0, 0, 15, 0, 142);
/* 264 */     this.sliderC.addAdjustmentListener(this);
/* 265 */     localPanel20.add(this.sliderC);
/* 266 */     this.commentLabC = new Label(" No Comment ");
/* 267 */     localPanel20.add(this.commentLabC);
/* 268 */     this.sliders.add(localPanel20);
/*     */ 
/* 270 */     Panel localPanel21 = new Panel(new GridLayout(1, 3));
/* 271 */     this.labelD = new Label(" variableD = 0");
/* 272 */     localPanel21.add(this.labelD);
/* 273 */     this.sliderD = new Scrollbar(0, 0, 15, 0, 142);
/* 274 */     this.sliderD.addAdjustmentListener(this);
/* 275 */     localPanel21.add(this.sliderD);
/* 276 */     this.commentLabD = new Label(" No Comment ");
/* 277 */     localPanel21.add(this.commentLabD);
/* 278 */     this.sliders.add(localPanel21);
/*     */ 
/* 280 */     Panel localPanel22 = new Panel(new GridLayout(1, 3));
/* 281 */     this.labelE = new Label(" variableE = 0");
/* 282 */     localPanel22.add(this.labelE);
/* 283 */     this.sliderE = new Scrollbar(0, 0, 15, 0, 142);
/* 284 */     this.sliderE.addAdjustmentListener(this);
/* 285 */     localPanel22.add(this.sliderE);
/* 286 */     this.commentLabE = new Label(" No Comment ");
/* 287 */     localPanel22.add(this.commentLabE);
/* 288 */     this.sliders.add(localPanel22);
/*     */ 
/* 291 */     Label localLabel6 = new Label(" ");
/* 292 */     this.sliders.add(localLabel6);
/* 293 */     localPanel10.add(this.sliders, "Center");
/*     */ 
/* 295 */     pack();
/*     */ 
/* 297 */     setSize(new Dimension(350, 510));
/* 298 */     setVisible(true);
/*     */ 
/* 300 */     this.composeBtn.requestFocus();
/*     */   }
/*     */ 
/*     */   public void setVariableA(int paramInt)
/*     */   {
/* 311 */     setVariableA(paramInt, "No Comment");
/*     */   }
/*     */ 
/*     */   public void setVariableA(int paramInt, String paramString)
/*     */   {
/* 318 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 319 */       this.sliderA.setValue(paramInt);
/* 320 */       this.labelA.setText(" variableA = " + paramInt + "  ");
/* 321 */       this.variableA = paramInt;
/*     */     }
/* 323 */     if (paramString.length() > 18)
/* 324 */       this.commentLabA.setText(" " + paramString.substring(0, 16) + "...");
/*     */     else this.commentLabA.setText(" " + paramString + " ");
/*     */   }
/*     */ 
/*     */   public void setVariableB(int paramInt)
/*     */   {
/* 332 */     setVariableB(paramInt, "No Comment");
/*     */   }
/*     */ 
/*     */   public void setVariableB(int paramInt, String paramString)
/*     */   {
/* 339 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 340 */       this.sliderB.setValue(paramInt);
/* 341 */       this.labelB.setText(" variableB = " + paramInt + "  ");
/* 342 */       this.variableB = paramInt;
/*     */     }
/* 344 */     if (paramString.length() > 18)
/* 345 */       this.commentLabB.setText(" " + paramString.substring(0, 16) + "...");
/*     */     else this.commentLabB.setText(" " + paramString + " ");
/*     */   }
/*     */ 
/*     */   public void setVariableC(int paramInt)
/*     */   {
/* 353 */     setVariableC(paramInt, "No Comment");
/*     */   }
/*     */ 
/*     */   public void setVariableC(int paramInt, String paramString)
/*     */   {
/* 361 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 362 */       this.sliderC.setValue(paramInt);
/* 363 */       this.labelC.setText(" variableC = " + paramInt + "  ");
/* 364 */       this.variableC = paramInt;
/*     */     }
/* 366 */     if (paramString.length() > 18)
/* 367 */       this.commentLabC.setText(" " + paramString.substring(0, 16) + "...");
/*     */     else this.commentLabC.setText(" " + paramString + " ");
/*     */   }
/*     */ 
/*     */   public void setVariableD(int paramInt)
/*     */   {
/* 375 */     setVariableD(paramInt, "No Comment");
/*     */   }
/*     */ 
/*     */   public void setVariableD(int paramInt, String paramString)
/*     */   {
/* 382 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 383 */       this.sliderD.setValue(paramInt);
/* 384 */       this.labelD.setText(" variableD = " + paramInt + "  ");
/* 385 */       this.variableD = paramInt;
/*     */     }
/* 387 */     if (paramString.length() > 18)
/* 388 */       this.commentLabD.setText(" " + paramString.substring(0, 16) + "...");
/*     */     else this.commentLabD.setText(" " + paramString + " ");
/*     */   }
/*     */ 
/*     */   public void setVariableE(int paramInt)
/*     */   {
/* 396 */     setVariableE(paramInt, "No Comment");
/*     */   }
/*     */ 
/*     */   public void setVariableE(int paramInt, String paramString)
/*     */   {
/* 403 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 404 */       this.sliderE.setValue(paramInt);
/* 405 */       this.labelE.setText(" variableE = " + paramInt + "  ");
/* 406 */       this.variableE = paramInt;
/*     */     }
/* 408 */     if (paramString.length() > 18)
/* 409 */       this.commentLabE.setText(" " + paramString.substring(0, 16) + "...");
/*     */     else this.commentLabE.setText(" " + paramString + " ");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 417 */     if (paramActionEvent.getSource() == this.composeBtn) composeScore();
/* 418 */     if (paramActionEvent.getSource() == this.playBtn) playScore();
/* 419 */     if (paramActionEvent.getSource() == this.stopBtn) stopScore();
/* 420 */     if (paramActionEvent.getSource() == this.showBtn) showScore();
/* 421 */     if (paramActionEvent.getSource() == this.notateBtn) notateScore();
/* 422 */     if (paramActionEvent.getSource() == this.printBtn) printScore();
/* 423 */     if (paramActionEvent.getSource() == this.sketchBtn) sketchScore();
/* 424 */     if (paramActionEvent.getSource() == this.histogramBtn) histogramScore();
/* 425 */     if (paramActionEvent.getSource() == this.saveBtn) saveScore();
/* 426 */     if (paramActionEvent.getSource() == this.readMidiBtn) openMidi();
/* 427 */     if (paramActionEvent.getSource() == this.renderBtn) renderScore();
/* 428 */     if (paramActionEvent.getSource() == this.audioViewBtn) viewAudio();
/* 429 */     if (paramActionEvent.getSource() == this.audioPlayBtn) playAudio();
/* 430 */     if (paramActionEvent.getSource() == this.audioStopBtn) stopAudio();
/* 431 */     if (paramActionEvent.getSource() == this.xmlOpenBtn) xmlOpen();
/* 432 */     if (paramActionEvent.getSource() != this.xmlSaveBtn) return; xmlSave();
/*     */   }
/*     */ 
/*     */   private void composeScore()
/*     */   {
/* 437 */     this.score = compose();
/* 438 */     makeBtnsVisible();
/*     */   }
/*     */ 
/*     */   private void makeBtnsVisible()
/*     */   {
/* 443 */     this.playBtn.setEnabled(true);
/* 444 */     this.stopBtn.setEnabled(true);
/* 445 */     this.showBtn.setEnabled(true);
/* 446 */     this.notateBtn.setEnabled(true);
/* 447 */     this.printBtn.setEnabled(true);
/* 448 */     this.sketchBtn.setEnabled(true);
/* 449 */     this.histogramBtn.setEnabled(true);
/* 450 */     this.saveBtn.setEnabled(true);
/* 451 */     this.xmlSaveBtn.setEnabled(true);
/*     */ 
/* 453 */     if (this.insts != null)
/* 454 */       this.renderBtn.setEnabled(true);
/*     */   }
/*     */ 
/*     */   protected Score compose()
/*     */   {
/* 465 */     Phrase localPhrase = new Phrase();
/* 466 */     Score localScore = new Score(new Part(localPhrase));
/*     */ 
/* 468 */     Note localNote = new Note(48 + (int)(Math.random() * this.variableA), 0.5D + this.variableB * 0.25D);
/* 469 */     localPhrase.addNote(localNote);
/*     */ 
/* 474 */     return localScore;
/*     */   }
/*     */ 
/*     */   private void playScore()
/*     */   {
/* 479 */     if (this.playing) this.ms.stop();
/*     */     try {
/* 481 */       this.ms.play(this.score);
/* 482 */       this.playing = true;
/*     */     } catch (Exception localException) {
/* 484 */       System.err.println("JavaSound MIDI Playback Error:" + localException);
/* 485 */       return;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void stopScore()
/*     */   {
/* 491 */     if (this.playing) {
/* 492 */       this.ms.stop();
/* 493 */       this.playing = false;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void showScore()
/*     */   {
/* 499 */     View.show(this.score, getSize().width + 15, 0);
/*     */   }
/*     */ 
/*     */   private void notateScore()
/*     */   {
/* 504 */     View.notate(this.score, getSize().width + 15, 0);
/*     */   }
/*     */ 
/*     */   private void printScore()
/*     */   {
/* 510 */     View.print(this.score);
/*     */   }
/*     */ 
/*     */   private void histogramScore()
/*     */   {
/* 515 */     View.histogram(this.score, 0, getSize().width + 15, 0);
/*     */   }
/*     */ 
/*     */   private void sketchScore()
/*     */   {
/* 520 */     View.sketch(this.score, getSize().width + 15, 0);
/*     */   }
/*     */ 
/*     */   public void saveScore()
/*     */   {
/* 527 */     FileDialog localFileDialog = new FileDialog(this, "Save as a MIDI file...", 1);
/* 528 */     localFileDialog.setFile("FileName.mid");
/* 529 */     localFileDialog.show();
/*     */ 
/* 531 */     if (localFileDialog.getFile() != null)
/* 532 */       Write.midi(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   public void openMidi()
/*     */   {
/* 540 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Select a MIDI file to import...", 0);
/*     */ 
/* 543 */     localFileDialog.show();
/* 544 */     String str = localFileDialog.getFile();
/* 545 */     if (str != null) {
/* 546 */       Read.midi(this.score, localFileDialog.getDirectory() + str);
/* 547 */       makeBtnsVisible();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void renderScore()
/*     */   {
/* 554 */     FileDialog localFileDialog = new FileDialog(this, "Save as an audio file...", 1);
/* 555 */     localFileDialog.setFile("FileName.au");
/* 556 */     localFileDialog.show();
/*     */ 
/* 558 */     if (localFileDialog.getFile() != null) {
/* 559 */       this.audioFileName = localFileDialog.getDirectory() + localFileDialog.getFile();
/* 560 */       Write.au(this.score, this.audioFileName, this.insts);
/*     */     }
/*     */ 
/* 563 */     this.audioViewBtn.setEnabled(true);
/* 564 */     this.audioPlayBtn.setEnabled(true);
/*     */   }
/*     */ 
/*     */   private void viewAudio()
/*     */   {
/* 569 */     View.au(this.audioFileName, getSize().width + 5, 0);
/*     */   }
/*     */ 
/*     */   private void playAudio()
/*     */   {
/* 574 */     Play.au(this.audioFileName, false);
/*     */   }
/*     */ 
/*     */   private void stopAudio()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void adjustmentValueChanged(AdjustmentEvent paramAdjustmentEvent)
/*     */   {
/* 583 */     if (paramAdjustmentEvent.getSource() == this.sliderA)
/*     */     {
/* 585 */       this.labelA.setText(" variableA = " + this.sliderA.getValue());
/*     */ 
/* 587 */       this.variableA = new Integer(this.sliderA.getValue()).intValue();
/*     */     }
/* 590 */     else if (paramAdjustmentEvent.getSource() == this.sliderB) {
/* 591 */       this.labelB.setText(" variableB = " + this.sliderB.getValue());
/* 592 */       this.variableB = new Integer(this.sliderB.getValue()).intValue();
/*     */     }
/* 594 */     else if (paramAdjustmentEvent.getSource() == this.sliderC) {
/* 595 */       this.labelC.setText(" variableC = " + this.sliderC.getValue());
/* 596 */       this.variableC = new Integer(this.sliderC.getValue()).intValue();
/*     */     }
/* 598 */     else if (paramAdjustmentEvent.getSource() == this.sliderD) {
/* 599 */       this.labelD.setText(" variableD = " + this.sliderD.getValue());
/* 600 */       this.variableD = new Integer(this.sliderD.getValue()).intValue();
/*     */     }
/* 602 */     else if (paramAdjustmentEvent.getSource() == this.sliderE) {
/* 603 */       this.labelE.setText(" variableE = " + this.sliderE.getValue());
/* 604 */       this.variableE = new Integer(this.sliderE.getValue()).intValue();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void xmlSave()
/*     */   {
/* 613 */     FileDialog localFileDialog = new FileDialog(new Frame(), "Save as a jMusic XML file...", 1);
/*     */ 
/* 616 */     localFileDialog.show();
/* 617 */     if (localFileDialog.getFile() != null)
/* 618 */       Write.xml(this.score, localFileDialog.getDirectory() + localFileDialog.getFile());
/*     */   }
/*     */ 
/*     */   private void xmlOpen()
/*     */   {
/* 626 */     Read.xml(this.score);
/* 627 */     makeBtnsVisible();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.helper.HelperGUI
 * JD-Core Version:    0.5.3
 */