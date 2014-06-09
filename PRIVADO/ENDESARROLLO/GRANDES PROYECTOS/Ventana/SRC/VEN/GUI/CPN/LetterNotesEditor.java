/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.List;
/*     */ import java.awt.TextArea;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ import jm.util.Write;
/*     */ 
/*     */ public class LetterNotesEditor extends Dialog
/*     */   implements ActionListener, WindowListener
/*     */ {
/*     */   private Button okButton;
/*     */   private Button playButton;
/*     */   private Button cancelButton;
/*     */   private Button copyButton;
/*     */   private Label inputLabel;
/*     */   private TextArea notesInput;
/*     */   private Phrase phrase;
/*     */   private Stave stave;
/*     */   private Note currentNote;
/* 107 */   private char currentNoteLetter = 'A';
/* 108 */   private int currentPitch = 69;
/*     */ 
/* 110 */   private static List inputList = new List(8);
/*     */ 
/*     */   public LetterNotesEditor(Frame paramFrame) {
/* 113 */     super(paramFrame, "Set Music Parameters", true);
/*     */ 
/* 117 */     initializeData();
/* 118 */     initializeButtons();
/* 119 */     initializeLabels();
/* 120 */     setSize(500, 300);
/* 121 */     placeControls();
/* 122 */     addWindowListener(this);
/* 123 */     setVisible(false);
/* 124 */     pack();
/*     */   }
/*     */ 
/*     */   private void initializeData()
/*     */   {
/* 132 */     this.notesInput = new TextArea("", 10, 100, 0);
/*     */   }
/*     */ 
/*     */   private void initializeButtons()
/*     */   {
/* 138 */     this.okButton = new Button("Add Notes");
/* 139 */     this.playButton = new Button("Play Notes");
/* 140 */     this.cancelButton = new Button("Cancel");
/* 141 */     this.copyButton = new Button("Copy");
/*     */   }
/*     */ 
/*     */   private void initializeLabels() {
/* 145 */     this.inputLabel = new Label("Enter Note Names, R for Rest");
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 151 */     if (paramActionEvent.getSource() == this.okButton) {
/* 152 */       addNotes();
/* 153 */       dispose();
/*     */     }
/* 155 */     else if (paramActionEvent.getSource() == this.playButton) {
/* 156 */       playNotes();
/*     */     }
/* 158 */     else if (paramActionEvent.getSource() == this.cancelButton) {
/* 159 */       dispose();
/*     */     } else {
/* 161 */       if ((paramActionEvent.getSource() != this.copyButton) || 
/* 162 */         (inputList.getSelectedItem().length() <= 0)) return;
/* 163 */       this.notesInput.setText(inputList.getSelectedItem());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addNotes()
/*     */   {
/* 170 */     initializeUpdate();
/* 171 */     String str = this.notesInput.getText();
/* 172 */     for (int i = 0; i < str.length(); ++i) {
/* 173 */       processChar(Character.toUpperCase(str.charAt(i)));
/*     */     }
/* 175 */     inputList.add(crunchLine(str), 0);
/* 176 */     while (inputList.getItemCount() > 100)
/* 177 */       inputList.remove(100);
/*     */   }
/*     */ 
/*     */   private void playNotes()
/*     */   {
/* 184 */     Phrase localPhrase = this.phrase.copy();
/* 185 */     initializeUpdate();
/* 186 */     String str = this.notesInput.getText();
/* 187 */     for (int i = 0; i < str.length(); ++i) {
/* 188 */       processChar(Character.toUpperCase(str.charAt(i)));
/*     */     }
/* 190 */     for (i = 0; i < localPhrase.size(); ++i)
/* 191 */       this.phrase.removeNote(0);
/*     */     try
/*     */     {
/* 194 */       JmMidiPlayer localJmMidiPlayer = new JmMidiPlayer();
/* 195 */       Score localScore = new Score();
/* 196 */       Part localPart = new Part();
/* 197 */       localScore.addPart(localPart);
/* 198 */       localPart.addPhrase(this.phrase);
/* 199 */       Write.midi(localScore, localJmMidiPlayer);
/* 200 */       localJmMidiPlayer.play();
/* 201 */       localJmMidiPlayer.close();
/*     */     }
/*     */     catch (MidiUnavailableException localMidiUnavailableException) {
/* 204 */       System.out.println("Midi Not Available");
/*     */     }
/*     */ 
/* 208 */     this.phrase.empty();
/* 209 */     for (int j = 0; j < localPhrase.size(); ++j)
/* 210 */       this.phrase.addNote(localPhrase.getNote(j));
/*     */   }
/*     */ 
/*     */   private void initializeUpdate()
/*     */   {
/* 216 */     if (this.phrase.size() > 0) {
/* 217 */       this.currentNote = this.phrase.getNote(this.phrase.size() - 1);
/* 218 */       if (this.currentNote.getPitch() >= 0) {
/* 219 */         this.currentPitch = this.currentNote.getPitch();
/* 220 */         this.currentNoteLetter = getNoteLetter(this.currentPitch, this.stave.getKeySignature());
/*     */       }
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 227 */       this.currentNote = null;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static char getNoteLetter(int paramInt1, int paramInt2)
/*     */   {
/* 236 */     int i = paramInt1 % 12;
/* 237 */     switch (i) { case 0:
/* 238 */       return 'C';
/*     */     case 2:
/* 239 */       return 'D';
/*     */     case 4:
/* 240 */       return 'E';
/*     */     case 5:
/* 241 */       return 'F';
/*     */     case 7:
/* 242 */       return 'G';
/*     */     case 9:
/* 243 */       return 'A';
/*     */     case 11:
/* 244 */       return 'B';
/*     */     case 1:
/*     */     case 3:
/*     */     case 6:
/*     */     case 8:
/*     */     case 10: } if (paramInt2 >= 0) {
/* 246 */       return getNoteLetter(paramInt1 - 1, paramInt2);
/*     */     }
/*     */ 
/* 252 */     return getNoteLetter(paramInt1 + 1, paramInt2);
/*     */   }
/*     */ 
/*     */   private void processChar(char paramChar)
/*     */   {
/* 263 */     switch (paramChar) { case 'A':
/* 264 */       addNote(69, paramChar);
/* 265 */       break;
/*     */     case 'B':
/* 266 */       addNote(71, paramChar);
/* 267 */       break;
/*     */     case 'C':
/* 268 */       addNote(60, paramChar);
/* 269 */       break;
/*     */     case 'D':
/* 270 */       addNote(62, paramChar);
/* 271 */       break;
/*     */     case 'E':
/* 272 */       addNote(64, paramChar);
/* 273 */       break;
/*     */     case 'F':
/* 274 */       addNote(65, paramChar);
/* 275 */       break;
/*     */     case 'G':
/* 276 */       addNote(67, paramChar);
/* 277 */       break;
/*     */     case 'R':
/* 278 */       addNote(-2147483648, paramChar);
/* 279 */       break;
/*     */     case '#':
/*     */     case '+':
/* 281 */       sharpenNote();
/* 282 */       break;
/*     */     case '-':
/* 283 */       flattenNote();
/* 284 */       break;
/*     */     case '>':
/* 285 */       addOctave();
/* 286 */       break;
/*     */     case '<':
/* 287 */       subtractOctave();
/* 288 */       break;
/*     */     case '1':
/* 290 */       moveToInterval(23, 36);
/* 291 */       break;
/*     */     case '2':
/* 292 */       moveToInterval(35, 48);
/* 293 */       break;
/*     */     case '3':
/* 294 */       moveToInterval(47, 60);
/* 295 */       break;
/*     */     case '4':
/* 296 */       moveToInterval(59, 72);
/* 297 */       break;
/*     */     case '5':
/* 298 */       moveToInterval(71, 84);
/* 299 */       break;
/*     */     case '6':
/* 300 */       moveToInterval(83, 96);
/* 301 */       break;
/*     */     case '7':
/* 302 */       moveToInterval(95, 108);
/* 303 */       break;
/*     */     case '8':
/* 304 */       moveToInterval(107, 120);
/* 305 */       break;
/*     */     case '9':
/* 306 */       moveToInterval(119, 127);
/* 307 */       break;
/*     */     case '.':
/* 309 */       dotNote();
/* 310 */       break;
/*     */     case 'H':
/* 311 */       makeHalfNote();
/* 312 */       break;
/*     */     case 'W':
/* 313 */       makeWholeNote();
/* 314 */       break;
/*     */     case 'Q':
/* 315 */       makeQuarterNote();
/* 316 */       break;
/*     */     case 'N':
/* 317 */       makeEighthNote();
/* 318 */       break;
/*     */     case 'T':
/* 319 */       makeTriplet();
/* 320 */       break;
/*     */     case 'X':
/* 321 */       makeSixteenthNote();
/* 322 */       break;
/*     */     case '&':
/* 323 */       tieNotes();
/*     */     case '$':
/*     */     case '%':
/*     */     case '\'':
/*     */     case '(':
/*     */     case ')':
/*     */     case '*':
/*     */     case ',':
/*     */     case '/':
/*     */     case '0':
/*     */     case ':':
/*     */     case ';':
/*     */     case '=':
/*     */     case '?':
/*     */     case '@':
/*     */     case 'I':
/*     */     case 'J':
/*     */     case 'K':
/*     */     case 'L':
/*     */     case 'M':
/*     */     case 'O':
/*     */     case 'P':
/*     */     case 'S':
/*     */     case 'U':
/*     */     case 'V': }  } 
/*     */   static boolean pitchIsHigh(int paramInt1, int paramInt2, char paramChar1, char paramChar2) { String str = "ABCDEFGABCDEFG";
/* 339 */     if (paramInt1 > paramInt2 + 8) {
/* 340 */       return true;
/*     */     }
/* 342 */     if (paramInt1 < paramInt2 + 3) {
/* 343 */       return false;
/*     */     }
/*     */ 
/* 346 */     int i = str.indexOf(paramChar2);
/* 347 */     int j = str.indexOf(paramChar1, i);
/* 348 */     return (j > i + 3);
/*     */   }
/*     */ 
/*     */   static boolean pitchIsLow(int paramInt1, int paramInt2, char paramChar1, char paramChar2)
/*     */   {
/* 359 */     String str = "ABCDEFGABCDEFG";
/* 360 */     if (paramInt1 < paramInt2 - 8) {
/* 361 */       return true;
/*     */     }
/* 363 */     if (paramInt1 > paramInt2 - 3) {
/* 364 */       return false;
/*     */     }
/*     */ 
/* 367 */     int i = str.indexOf(paramChar1);
/* 368 */     int j = str.indexOf(paramChar2, i);
/* 369 */     return (j > i + 3);
/*     */   }
/*     */ 
/*     */   private void addNote(int paramInt, char paramChar)
/*     */   {
/* 376 */     int i = paramInt;
/*     */     Note localNote;
/* 377 */     if (this.currentNote != null) {
/* 378 */       localNote = this.currentNote.copy();
/*     */     }
/*     */     else {
/* 381 */       localNote = new Note();
/*     */     }
/* 383 */     if (paramChar != 'R')
/*     */     {
/* 385 */       if (i > this.currentPitch) while (true) {
/* 386 */           if (!(pitchIsHigh(i, this.currentPitch, paramChar, this.currentNoteLetter)))
/*     */           {
/*     */             break label90;
/*     */           }
/*     */ 
/* 391 */           i -= 12;
/*     */         }
/*     */ 
/*     */ 
/* 395 */       while (pitchIsLow(i, this.currentPitch, paramChar, this.currentNoteLetter))
/*     */       {
/* 400 */         i += 12;
/*     */       }
/*     */     }
/*     */ 
/* 404 */     if (noteIsSharp(paramChar)) {
/* 405 */       label90: ++i;
/*     */     }
/* 407 */     if (noteIsFlat(paramChar)) {
/* 408 */       --i;
/*     */     }
/* 410 */     localNote.setPitch(i);
/* 411 */     this.phrase.add(localNote);
/* 412 */     this.currentNote = localNote;
/* 413 */     this.currentNoteLetter = paramChar;
/* 414 */     if (this.currentNoteLetter != 'R')
/* 415 */       this.currentPitch = i;
/*     */   }
/*     */ 
/*     */   private void sharpenNote()
/*     */   {
/* 421 */     this.currentNote.setPitch(this.currentNote.getPitch() + 1);
/* 422 */     this.currentPitch = this.currentNote.getPitch();
/*     */   }
/*     */ 
/*     */   private void flattenNote()
/*     */   {
/* 427 */     this.currentNote.setPitch(this.currentNote.getPitch() - 1);
/* 428 */     this.currentPitch = this.currentNote.getPitch();
/*     */   }
/*     */ 
/*     */   private void addOctave()
/*     */   {
/* 433 */     this.currentNote.setPitch(this.currentNote.getPitch() + 12);
/* 434 */     this.currentPitch = this.currentNote.getPitch();
/*     */   }
/*     */ 
/*     */   private void subtractOctave()
/*     */   {
/* 439 */     this.currentNote.setPitch(this.currentNote.getPitch() - 12);
/* 440 */     this.currentPitch = this.currentNote.getPitch();
/*     */   }
/*     */ 
/*     */   private void moveToInterval(int paramInt1, int paramInt2)
/*     */   {
/* 445 */     while (this.currentNote.getPitch() > paramInt2) {
/* 446 */       this.currentNote.setPitch(this.currentNote.getPitch() - 12);
/*     */     }
/* 448 */     while (this.currentNote.getPitch() < paramInt1) {
/* 449 */       this.currentNote.setPitch(this.currentNote.getPitch() + 12);
/*     */     }
/* 451 */     if ((this.currentNote.getPitch() == paramInt1) && 
/* 452 */       (this.currentNoteLetter == 'B')) {
/* 453 */       this.currentNote.setPitch(this.currentNote.getPitch() + 12);
/*     */     }
/*     */ 
/* 456 */     if ((this.currentNote.getPitch() == paramInt2) && 
/* 457 */       (this.currentNoteLetter == 'C')) {
/* 458 */       this.currentNote.setPitch(this.currentNote.getPitch() - 12);
/*     */     }
/*     */ 
/* 461 */     this.currentPitch = this.currentNote.getPitch();
/*     */   }
/*     */ 
/*     */   private void dotNote()
/*     */   {
/* 466 */     adjustNoteByFactor(1.5D);
/*     */   }
/*     */ 
/*     */   private void makeHalfNote()
/*     */   {
/* 471 */     adjustNoteByFactor(2.0D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void makeWholeNote()
/*     */   {
/* 477 */     adjustNoteByFactor(4.0D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void makeQuarterNote()
/*     */   {
/* 483 */     adjustNoteByFactor(1.0D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void makeEighthNote()
/*     */   {
/* 489 */     adjustNoteByFactor(0.5D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void makeTriplet()
/*     */   {
/* 495 */     adjustNoteByFactor(0.3333333333333333D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void makeSixteenthNote()
/*     */   {
/* 502 */     adjustNoteByFactor(0.25D / this.currentNote.getRhythmValue());
/*     */   }
/*     */ 
/*     */   private void tieNotes()
/*     */   {
/* 509 */     if (this.phrase.size() > 1) {
/* 510 */       this.phrase.removeLastNote();
/*     */ 
/* 512 */       Note localNote = this.phrase.getNote(this.phrase.size() - 1);
/* 513 */       localNote.setDuration(localNote.getDuration() + this.currentNote.getDuration());
/*     */ 
/* 516 */       localNote.setRhythmValue(localNote.getRhythmValue() + this.currentNote.getRhythmValue());
/*     */ 
/* 519 */       this.currentNote = localNote;
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean noteIsSharp(char paramChar)
/*     */   {
/* 526 */     String str = "FCGDAEB";
/* 527 */     int j = this.stave.getKeySignature();
/*     */     int i;
/* 528 */     if (paramChar == 'R') {
/* 529 */       i = 0;
/*     */     }
/* 531 */     else if (j > 0) {
/* 532 */       i = (str.indexOf(paramChar) < j) ? 1 : 0;
/*     */     }
/*     */     else
/*     */     {
/* 536 */       i = 0;
/*     */     }
/* 538 */     return i;
/*     */   }
/*     */ 
/*     */   private boolean noteIsFlat(char paramChar)
/*     */   {
/* 544 */     String str = "BEADGCF";
/* 545 */     int j = -this.stave.getKeySignature();
/*     */     int i;
/* 546 */     if (paramChar == 'R') {
/* 547 */       i = 0;
/*     */     }
/* 549 */     else if (j > 0) {
/* 550 */       i = (str.indexOf(paramChar) < j) ? 1 : 0;
/*     */     }
/*     */     else
/*     */     {
/* 554 */       i = 0;
/*     */     }
/* 556 */     return i;
/*     */   }
/*     */ 
/*     */   private void adjustNoteByFactor(double paramDouble) {
/* 560 */     this.currentNote.setRhythmValue(paramDouble * this.currentNote.getRhythmValue());
/*     */ 
/* 562 */     this.currentNote.setDuration(paramDouble * this.currentNote.getDuration());
/*     */   }
/*     */ 
/*     */   private void placeControls()
/*     */   {
/* 567 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/* 568 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 569 */     setLayout(localGridBagLayout);
/*     */ 
/* 571 */     localGridBagConstraints.fill = 1;
/* 572 */     localGridBagConstraints.weightx = 0.5D;
/* 573 */     localGridBagConstraints.gridwidth = 5;
/* 574 */     localGridBagConstraints.gridheight = 1;
/*     */ 
/* 576 */     localGridBagConstraints.gridx = 0;
/* 577 */     localGridBagConstraints.gridy = 0;
/* 578 */     localGridBagLayout.setConstraints(this.inputLabel, localGridBagConstraints);
/* 579 */     add(this.inputLabel);
/*     */ 
/* 581 */     localGridBagConstraints.gridx = 0;
/* 582 */     localGridBagConstraints.gridy = 1;
/* 583 */     localGridBagConstraints.gridheight = 10;
/* 584 */     localGridBagLayout.setConstraints(this.notesInput, localGridBagConstraints);
/* 585 */     add(this.notesInput);
/*     */ 
/* 587 */     localGridBagConstraints.gridx = 2;
/* 588 */     localGridBagConstraints.gridy = 12;
/* 589 */     localGridBagConstraints.gridheight = 1;
/* 590 */     localGridBagConstraints.gridwidth = 1;
/* 591 */     localGridBagLayout.setConstraints(this.okButton, localGridBagConstraints);
/* 592 */     add(this.okButton);
/* 593 */     this.okButton.addActionListener(this);
/*     */ 
/* 595 */     localGridBagConstraints.gridx = 3;
/* 596 */     localGridBagConstraints.gridy = 12;
/* 597 */     localGridBagConstraints.gridwidth = 1;
/* 598 */     localGridBagLayout.setConstraints(this.playButton, localGridBagConstraints);
/* 599 */     add(this.playButton);
/* 600 */     this.playButton.addActionListener(this);
/*     */ 
/* 602 */     localGridBagConstraints.gridx = 4;
/* 603 */     localGridBagConstraints.gridy = 12;
/* 604 */     localGridBagConstraints.gridwidth = 1;
/* 605 */     localGridBagLayout.setConstraints(this.cancelButton, localGridBagConstraints);
/* 606 */     add(this.cancelButton);
/* 607 */     this.cancelButton.addActionListener(this);
/*     */ 
/* 609 */     localGridBagConstraints.gridx = 1;
/* 610 */     localGridBagConstraints.gridy = 15;
/* 611 */     localGridBagConstraints.gridwidth = 5;
/* 612 */     localGridBagConstraints.gridheight = 5;
/* 613 */     localGridBagLayout.setConstraints(inputList, localGridBagConstraints);
/* 614 */     add(inputList);
/*     */ 
/* 616 */     localGridBagConstraints.gridx = 1;
/* 617 */     localGridBagConstraints.gridy = 20;
/* 618 */     localGridBagConstraints.gridwidth = 5;
/* 619 */     localGridBagConstraints.gridheight = 1;
/* 620 */     localGridBagLayout.setConstraints(this.copyButton, localGridBagConstraints);
/* 621 */     add(this.copyButton);
/* 622 */     this.copyButton.addActionListener(this);
/*     */   }
/*     */ 
/*     */   public void getNotes(Stave paramStave)
/*     */   {
/* 628 */     this.phrase = paramStave.getPhrase();
/* 629 */     this.stave = paramStave;
/* 630 */     setLocation(200, 50);
/* 631 */     show();
/*     */   }
/*     */ 
/*     */   private static String crunchLine(String paramString) {
/* 635 */     StringBuffer localStringBuffer = new StringBuffer(paramString);
/* 636 */     for (int i = 0; i < localStringBuffer.length(); ++i) {
/* 637 */       if (localStringBuffer.charAt(i) < ' ') {
/* 638 */         localStringBuffer.setCharAt(i, ' ');
/*     */       }
/*     */     }
/* 641 */     for (i = 0; i < localStringBuffer.length() - 1; ) {
/* 642 */       if (localStringBuffer.charAt(i) == ' ') {
/* 643 */         if (localStringBuffer.charAt(i + 1) == ' ') {
/* 644 */           localStringBuffer.deleteCharAt(i);
/*     */         }
/*     */ 
/* 647 */         i += 2;
/*     */       }
/*     */ 
/* 651 */       ++i;
/*     */     }
/*     */ 
/* 654 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent) {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent) {
/* 661 */     if (paramWindowEvent.getSource() != this) return; dispose();
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
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.LetterNotesEditor
 * JD-Core Version:    0.5.3
 */