/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.List;
/*     */ import java.awt.TextField;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.StringTokenizer;
/*     */ import jm.music.data.Note;
/*     */ 
/*     */ public class NoteEditor extends Dialog
/*     */   implements ActionListener, WindowListener
/*     */ {
/*  46 */   private Button okButton = new Button("Apply"); private Button cancelButton = new Button("Cancel");
/*     */   private Note note;
/*     */   private List noteList;
/*     */   private List octaveList;
/*  55 */   private TextField durationEdit = new TextField(15); private TextField dynamicEdit = new TextField(15); private TextField rhythmEdit = new TextField(15); private TextField panEdit = new TextField(15); private TextField offsetEdit = new TextField(15);
/*     */ 
/*  62 */   private Label noteLabel = new Label("Note"); private Label dynamicLabel = new Label("Volume (1-127)"); private Label rhythmLabel = new Label("Rhythm Value"); private Label durationLabel = new Label("Duration Factor"); private Label panLabel = new Label("Pan"); private Label offsetLabel = new Label("Offset"); private Label octaveLabel = new Label("Octave");
/*     */ 
/*  71 */   private static DecimalFormat decimalFormat = new DecimalFormat("###.###########");
/*     */ 
/*     */   public NoteEditor(Frame paramFrame)
/*     */   {
/*  75 */     super(paramFrame, "Edit Note", true);
/*  76 */     initializeLists();
/*  77 */     placeControls();
/*  78 */     this.okButton.addActionListener(this);
/*  79 */     this.cancelButton.addActionListener(this);
/*  80 */     addWindowListener(this);
/*  81 */     setVisible(false);
/*  82 */     pack();
/*     */   }
/*     */ 
/*     */   private static String getOctaveStringValue(int paramInt)
/*     */   {
/*  87 */     int i = -1;
/*  88 */     int j = paramInt;
/*  89 */     while (j > 11) {
/*  90 */       ++i;
/*  91 */       j -= 12;
/*     */     }
/*  93 */     return new Integer(i).toString();
/*     */   }
/*     */ 
/*     */   private static String getPitchStringValue(int paramInt)
/*     */   {
/*  98 */     if (paramInt == -2147483648) {
/*  99 */       return "Rest";
/*     */     }
/*     */ 
/* 103 */     int i = paramInt;
/* 104 */     while (i >= 12) {
/* 105 */       i -= 12;
/*     */     }
/* 107 */     switch (i)
/*     */     {
/*     */     case 0:
/* 109 */       return "C";
/*     */     case 1:
/* 111 */       return "C#";
/*     */     case 2:
/* 113 */       return "D";
/*     */     case 3:
/* 115 */       return "D#";
/*     */     case 4:
/* 117 */       return "E";
/*     */     case 5:
/* 119 */       return "F";
/*     */     case 6:
/* 121 */       return "F#";
/*     */     case 7:
/* 123 */       return "G";
/*     */     case 8:
/* 125 */       return "G#";
/*     */     case 9:
/* 127 */       return "A";
/*     */     case 10:
/* 129 */       return "A#";
/*     */     case 11:
/* 131 */       return "B";
/*     */     }
/* 133 */     return "Rest";
/*     */   }
/*     */ 
/*     */   private static void setListToMatch(List paramList, String paramString)
/*     */   {
/* 142 */     for (int i = paramList.getItemCount() - 1; i >= 0; --i)
/* 143 */       if (paramList.getItem(i).equals(paramString))
/* 144 */         paramList.select(i);
/*     */   }
/*     */ 
/*     */   private void initializeNoteListValue(int paramInt)
/*     */   {
/* 151 */     setListToMatch(this.noteList, getPitchStringValue(paramInt));
/*     */   }
/*     */ 
/*     */   private void initializeOctaveListValue(int paramInt)
/*     */   {
/* 158 */     setListToMatch(this.octaveList, getOctaveStringValue(paramInt));
/*     */   }
/*     */ 
/*     */   private static void initializeDoubleEdit(TextField paramTextField, double paramDouble)
/*     */   {
/* 167 */     paramTextField.setText(decimalFormat.format(paramDouble));
/*     */   }
/*     */ 
/*     */   private static void initializeIntEdit(TextField paramTextField, int paramInt)
/*     */   {
/* 173 */     paramTextField.setText(new Integer(paramInt).toString());
/*     */   }
/*     */ 
/*     */   private void initializeData()
/*     */   {
/* 179 */     initializeNoteListValue(this.note.getPitch());
/*     */ 
/* 181 */     initializeOctaveListValue(this.note.getPitch());
/*     */ 
/* 183 */     initializeDoubleEdit(this.durationEdit, this.note.getDuration() / this.note.getRhythmValue());
/*     */ 
/* 188 */     initializeDoubleEdit(this.rhythmEdit, this.note.getRhythmValue());
/*     */ 
/* 192 */     initializeDoubleEdit(this.offsetEdit, this.note.getOffset());
/*     */ 
/* 196 */     initializeDoubleEdit(this.panEdit, this.note.getPan());
/*     */ 
/* 200 */     initializeIntEdit(this.dynamicEdit, this.note.getDynamic());
/*     */   }
/*     */ 
/*     */   public void editNote(Note paramNote, int paramInt1, int paramInt2)
/*     */   {
/* 209 */     this.note = paramNote;
/* 210 */     setLocation(paramInt1, paramInt2);
/* 211 */     initializeData();
/* 212 */     show();
/*     */   }
/*     */ 
/*     */   private void initializeLists() {
/* 216 */     this.noteList = new List(6);
/* 217 */     this.noteList.add("Rest");
/* 218 */     this.noteList.add("A");
/* 219 */     this.noteList.add("A#");
/* 220 */     this.noteList.add("B");
/* 221 */     this.noteList.add("C");
/* 222 */     this.noteList.add("C#");
/* 223 */     this.noteList.add("D");
/* 224 */     this.noteList.add("D#");
/* 225 */     this.noteList.add("E");
/* 226 */     this.noteList.add("F");
/* 227 */     this.noteList.add("F#");
/* 228 */     this.noteList.add("G");
/* 229 */     this.noteList.add("G#");
/*     */ 
/* 231 */     this.octaveList = new List(6);
/* 232 */     this.octaveList.add("-1");
/* 233 */     this.octaveList.add("0");
/* 234 */     this.octaveList.add("1");
/* 235 */     this.octaveList.add("2");
/* 236 */     this.octaveList.add("3");
/* 237 */     this.octaveList.add("4");
/* 238 */     this.octaveList.add("5");
/* 239 */     this.octaveList.add("6");
/* 240 */     this.octaveList.add("7");
/* 241 */     this.octaveList.add("8");
/* 242 */     this.octaveList.add("9");
/*     */   }
/*     */ 
/*     */   private static boolean validateFloatEdit(TextField paramTextField, double paramDouble1, double paramDouble2)
/*     */   {
/* 249 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 251 */     if (!(localStringTokenizer.hasMoreElements())) {
/* 252 */       paramTextField.setText("Error--No Data");
/* 253 */       return false;
/*     */     }
/*     */ 
/* 256 */     String str = localStringTokenizer.nextToken();
/*     */     try
/*     */     {
/* 259 */       double d = new Double(str).doubleValue();
/*     */ 
/* 262 */       if (d < paramDouble1) {
/* 263 */         paramTextField.setText("Value Too Low");
/* 264 */         return false;
/*     */       }
/* 266 */       if (d < paramDouble1) {
/* 267 */         paramTextField.setText("Value Too High");
/* 268 */         return false;
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable) {
/* 272 */       paramTextField.setText("Data Error");
/* 273 */       return false;
/*     */     }
/*     */ 
/* 276 */     if (localStringTokenizer.hasMoreElements()) {
/* 277 */       paramTextField.setText("Data Error");
/* 278 */       return false;
/*     */     }
/*     */ 
/* 281 */     return true;
/*     */   }
/*     */ 
/*     */   private static double getFieldDouble(TextField paramTextField)
/*     */   {
/* 289 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 291 */     String str = localStringTokenizer.nextToken();
/*     */ 
/* 293 */     return new Double(str).doubleValue();
/*     */   }
/*     */ 
/*     */   private static boolean validateIntegerEdit(TextField paramTextField, int paramInt1, int paramInt2)
/*     */   {
/* 301 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 303 */     if (!(localStringTokenizer.hasMoreElements())) {
/* 304 */       paramTextField.setText("Error--No Data");
/* 305 */       return false;
/*     */     }
/*     */ 
/* 308 */     String str = localStringTokenizer.nextToken();
/*     */     try
/*     */     {
/* 311 */       int i = new Integer(str).intValue();
/*     */ 
/* 314 */       if (i < paramInt1) {
/* 315 */         paramTextField.setText("Value Too Low");
/* 316 */         return false;
/*     */       }
/* 318 */       if (i > paramInt2) {
/* 319 */         paramTextField.setText("Value Too High");
/* 320 */         return false;
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable) {
/* 324 */       paramTextField.setText("Data Error");
/* 325 */       return false;
/*     */     }
/*     */ 
/* 328 */     if (localStringTokenizer.hasMoreElements()) {
/* 329 */       paramTextField.setText("Data Error");
/* 330 */       return false;
/*     */     }
/*     */ 
/* 333 */     return true;
/*     */   }
/*     */ 
/*     */   private static int getFieldInt(TextField paramTextField)
/*     */   {
/* 341 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 343 */     String str = localStringTokenizer.nextToken();
/*     */ 
/* 345 */     return new Integer(str).intValue();
/*     */   }
/*     */ 
/*     */   private boolean inputIsValid() {
/* 349 */     return ((validateFloatEdit(this.durationEdit, 0.0D, 1.0D)) && (validateIntegerEdit(this.dynamicEdit, 0, 127)) && (validateFloatEdit(this.rhythmEdit, 1.E-005D, 64.0D)) && (validateFloatEdit(this.panEdit, 0.0D, 1.0D)) && (validateFloatEdit(this.offsetEdit, -999.99900000000002D, 999.99900000000002D)));
/*     */   }
/*     */ 
/*     */   private void placeControls()
/*     */   {
/* 373 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/* 374 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 375 */     setLayout(localGridBagLayout);
/*     */ 
/* 377 */     localGridBagConstraints.fill = 1;
/* 378 */     localGridBagConstraints.weightx = 0.5D;
/*     */ 
/* 380 */     localGridBagConstraints.gridwidth = 2;
/* 381 */     localGridBagConstraints.gridheight = 1;
/*     */ 
/* 383 */     localGridBagConstraints.gridx = 0;
/* 384 */     localGridBagConstraints.gridy = 0;
/* 385 */     localGridBagConstraints.gridheight = 1;
/* 386 */     localGridBagLayout.setConstraints(this.noteLabel, localGridBagConstraints);
/* 387 */     add(this.noteLabel);
/*     */ 
/* 389 */     localGridBagConstraints.gridx = 0;
/* 390 */     localGridBagConstraints.gridy = 2;
/* 391 */     localGridBagConstraints.gridheight = 4;
/* 392 */     localGridBagLayout.setConstraints(this.noteList, localGridBagConstraints);
/* 393 */     add(this.noteList);
/*     */ 
/* 395 */     localGridBagConstraints.gridx = 0;
/* 396 */     localGridBagConstraints.gridy = 7;
/* 397 */     localGridBagConstraints.gridheight = 1;
/* 398 */     localGridBagLayout.setConstraints(this.octaveLabel, localGridBagConstraints);
/* 399 */     add(this.octaveLabel);
/*     */ 
/* 401 */     localGridBagConstraints.gridx = 0;
/* 402 */     localGridBagConstraints.gridy = 8;
/* 403 */     localGridBagConstraints.gridheight = 4;
/* 404 */     localGridBagLayout.setConstraints(this.octaveList, localGridBagConstraints);
/* 405 */     add(this.octaveList);
/*     */ 
/* 407 */     localGridBagConstraints.gridx = 0;
/* 408 */     localGridBagConstraints.gridy = 15;
/* 409 */     localGridBagConstraints.gridheight = 1;
/* 410 */     localGridBagConstraints.gridwidth = 1;
/* 411 */     localGridBagLayout.setConstraints(this.rhythmLabel, localGridBagConstraints);
/* 412 */     add(this.rhythmLabel);
/* 413 */     localGridBagConstraints.gridx = 1;
/* 414 */     localGridBagLayout.setConstraints(this.rhythmEdit, localGridBagConstraints);
/* 415 */     add(this.rhythmEdit);
/*     */ 
/* 417 */     localGridBagConstraints.gridx = 0;
/* 418 */     localGridBagConstraints.gridy = 17;
/* 419 */     localGridBagConstraints.gridheight = 1;
/* 420 */     localGridBagConstraints.gridwidth = 1;
/* 421 */     localGridBagLayout.setConstraints(this.dynamicLabel, localGridBagConstraints);
/* 422 */     add(this.dynamicLabel);
/* 423 */     localGridBagConstraints.gridx = 1;
/* 424 */     localGridBagLayout.setConstraints(this.dynamicEdit, localGridBagConstraints);
/* 425 */     add(this.dynamicEdit);
/*     */ 
/* 427 */     localGridBagConstraints.gridx = 0;
/* 428 */     localGridBagConstraints.gridy = 19;
/* 429 */     localGridBagConstraints.gridheight = 1;
/* 430 */     localGridBagConstraints.gridwidth = 1;
/* 431 */     localGridBagLayout.setConstraints(this.durationLabel, localGridBagConstraints);
/* 432 */     add(this.durationLabel);
/* 433 */     localGridBagConstraints.gridx = 1;
/* 434 */     localGridBagLayout.setConstraints(this.durationEdit, localGridBagConstraints);
/* 435 */     add(this.durationEdit);
/*     */ 
/* 437 */     localGridBagConstraints.gridx = 0;
/* 438 */     localGridBagConstraints.gridy = 21;
/* 439 */     localGridBagConstraints.gridheight = 1;
/* 440 */     localGridBagConstraints.gridwidth = 1;
/* 441 */     localGridBagLayout.setConstraints(this.offsetLabel, localGridBagConstraints);
/* 442 */     add(this.offsetLabel);
/* 443 */     localGridBagConstraints.gridx = 1;
/* 444 */     localGridBagLayout.setConstraints(this.offsetEdit, localGridBagConstraints);
/* 445 */     add(this.offsetEdit);
/*     */ 
/* 447 */     localGridBagConstraints.gridx = 0;
/* 448 */     localGridBagConstraints.gridy = 23;
/* 449 */     localGridBagConstraints.gridheight = 1;
/* 450 */     localGridBagConstraints.gridwidth = 1;
/* 451 */     localGridBagLayout.setConstraints(this.panLabel, localGridBagConstraints);
/* 452 */     add(this.panLabel);
/* 453 */     localGridBagConstraints.gridx = 1;
/* 454 */     localGridBagLayout.setConstraints(this.panEdit, localGridBagConstraints);
/* 455 */     add(this.panEdit);
/*     */ 
/* 457 */     localGridBagConstraints.gridx = 0;
/* 458 */     localGridBagConstraints.gridy = 25;
/* 459 */     localGridBagConstraints.gridheight = 1;
/* 460 */     localGridBagConstraints.gridwidth = 1;
/* 461 */     localGridBagLayout.setConstraints(this.okButton, localGridBagConstraints);
/* 462 */     add(this.okButton);
/* 463 */     localGridBagConstraints.gridx = 1;
/* 464 */     localGridBagLayout.setConstraints(this.cancelButton, localGridBagConstraints);
/* 465 */     add(this.cancelButton);
/*     */   }
/*     */ 
/*     */   private int getSelectedPitch()
/*     */   {
/* 470 */     String str = this.noteList.getSelectedItem();
/* 471 */     if (str.equals("Rest"))
/* 472 */       return -2147483648;
/*     */     int i;
/* 476 */     if (str.equals("C")) {
/* 477 */       i = 0;
/*     */     }
/* 479 */     else if (str.equals("C#")) {
/* 480 */       i = 1;
/*     */     }
/* 482 */     else if (str.equals("D")) {
/* 483 */       i = 2;
/*     */     }
/* 485 */     else if (str.equals("D#")) {
/* 486 */       i = 3;
/*     */     }
/* 488 */     else if (str.equals("E")) {
/* 489 */       i = 4;
/*     */     }
/* 491 */     else if (str.equals("F")) {
/* 492 */       i = 5;
/*     */     }
/* 494 */     else if (str.equals("F#")) {
/* 495 */       i = 6;
/*     */     }
/* 497 */     else if (str.equals("G")) {
/* 498 */       i = 7;
/*     */     }
/* 500 */     else if (str.equals("G#")) {
/* 501 */       i = 8;
/*     */     }
/* 503 */     else if (str.equals("A")) {
/* 504 */       i = 9;
/*     */     }
/* 506 */     else if (str.equals("A#")) {
/* 507 */       i = 10;
/*     */     }
/* 509 */     else if (str.equals("A")) {
/* 510 */       i = 11;
/*     */     }
/*     */     else {
/* 513 */       i = 0;
/*     */     }
/* 515 */     int j = new Integer(this.octaveList.getSelectedItem()).intValue();
/*     */ 
/* 519 */     while (j > -1) {
/* 520 */       i += 12;
/* 521 */       --j;
/*     */     }
/* 523 */     return i;
/*     */   }
/*     */ 
/*     */   private void updateTheNote()
/*     */   {
/* 528 */     this.note.setPitch(getSelectedPitch());
/* 529 */     this.note.setRhythmValue(getFieldDouble(this.rhythmEdit));
/* 530 */     this.note.setDuration(this.note.getRhythmValue() * getFieldDouble(this.durationEdit));
/*     */ 
/* 532 */     this.note.setDynamic(getFieldInt(this.dynamicEdit));
/* 533 */     this.note.setPan(getFieldDouble(this.panEdit));
/* 534 */     this.note.setOffset(getFieldDouble(this.offsetEdit));
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 538 */     if (paramActionEvent.getSource() == this.okButton) {
/* 539 */       if (inputIsValid()) {
/* 540 */         updateTheNote();
/* 541 */         dispose();
/*     */       }
/*     */     }
/* 544 */     else if (paramActionEvent.getSource() == this.cancelButton)
/* 545 */       dispose();
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent) {
/* 553 */     if (paramWindowEvent.getSource() != this) return; dispose();
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
 * Qualified Name:     jm.gui.cpn.NoteEditor
 * JD-Core Version:    0.5.3
 */