/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.TextField;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.util.StringTokenizer;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class CpnZoomScreen extends Dialog
/*     */   implements ActionListener, WindowListener
/*     */ {
/*     */   private Phrase phrase;
/*     */   private Phrase beforeZoom;
/*     */   private Phrase afterZoom;
/*  52 */   private static TextField startMeasureEdit = new TextField(8);
/*  53 */   private static TextField measureCountEdit = new TextField(8);
/*     */ 
/*  55 */   private static Label startMeasureLabel = new Label("Start at Measure");
/*     */ 
/*  58 */   private static Label measureCountLabel = new Label("Number of Measures");
/*     */ 
/*  61 */   private Button okButton = new Button("Update View:"); private Button cancelButton = new Button("Cancel");
/*     */ 
/*     */   public CpnZoomScreen(Frame paramFrame)
/*     */   {
/*  69 */     super(paramFrame, "Select the Measures to Show", true);
/*     */ 
/*  73 */     setSize(500, 400);
/*  74 */     placeControls();
/*  75 */     addWindowListener(this);
/*  76 */     setVisible(false);
/*  77 */     pack();
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent) {
/*  85 */     if (paramWindowEvent.getSource() != this) return; dispose();
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
/*     */   public void zoomIn(Phrase paramPhrase1, Phrase paramPhrase2, Phrase paramPhrase3)
/*     */   {
/* 123 */     this.phrase = paramPhrase2;
/* 124 */     this.beforeZoom = paramPhrase1;
/* 125 */     this.afterZoom = paramPhrase3;
/* 126 */     this.beforeZoom.empty();
/* 127 */     this.afterZoom.empty();
/* 128 */     setLocation(20, 20);
/* 129 */     show();
/*     */   }
/*     */ 
/*     */   public static void zoomOut(Phrase paramPhrase1, Phrase paramPhrase2, Phrase paramPhrase3)
/*     */   {
/* 138 */     for (int i = 0; i < paramPhrase2.size(); ++i) {
/* 139 */       paramPhrase1.addNote(paramPhrase2.getNote(i));
/*     */     }
/*     */ 
/* 142 */     for (i = 0; i < paramPhrase3.size(); ++i) {
/* 143 */       paramPhrase1.addNote(paramPhrase3.getNote(i));
/*     */     }
/*     */ 
/* 146 */     paramPhrase2.empty();
/* 147 */     for (i = 0; i < paramPhrase1.size(); ++i) {
/* 148 */       paramPhrase2.addNote(paramPhrase1.getNote(i));
/*     */     }
/*     */ 
/* 151 */     paramPhrase1.empty();
/* 152 */     paramPhrase3.empty();
/*     */   }
/*     */ 
/*     */   private void placeControls()
/*     */   {
/* 157 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/* 158 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 159 */     setLayout(localGridBagLayout);
/*     */ 
/* 161 */     localGridBagConstraints.fill = 1;
/* 162 */     localGridBagConstraints.weightx = 0.5D;
/* 163 */     localGridBagConstraints.gridwidth = 1;
/* 164 */     localGridBagConstraints.gridheight = 1;
/*     */ 
/* 166 */     localGridBagConstraints.gridx = 0;
/* 167 */     localGridBagConstraints.gridy = 0;
/* 168 */     localGridBagConstraints.gridheight = 1;
/* 169 */     localGridBagLayout.setConstraints(startMeasureLabel, localGridBagConstraints);
/* 170 */     add(startMeasureLabel);
/*     */ 
/* 172 */     localGridBagConstraints.gridx = 1;
/* 173 */     localGridBagConstraints.gridy = 0;
/* 174 */     localGridBagConstraints.gridheight = 1;
/* 175 */     localGridBagLayout.setConstraints(startMeasureEdit, localGridBagConstraints);
/* 176 */     add(startMeasureEdit);
/*     */ 
/* 178 */     localGridBagConstraints.gridx = 0;
/* 179 */     localGridBagConstraints.gridy = 1;
/* 180 */     localGridBagConstraints.gridheight = 1;
/* 181 */     localGridBagLayout.setConstraints(measureCountLabel, localGridBagConstraints);
/* 182 */     add(measureCountLabel);
/*     */ 
/* 184 */     localGridBagConstraints.gridx = 1;
/* 185 */     localGridBagConstraints.gridy = 1;
/* 186 */     localGridBagConstraints.gridheight = 1;
/* 187 */     localGridBagLayout.setConstraints(measureCountEdit, localGridBagConstraints);
/* 188 */     add(measureCountEdit);
/*     */ 
/* 190 */     localGridBagConstraints.gridx = 0;
/* 191 */     localGridBagConstraints.gridy = 3;
/* 192 */     localGridBagConstraints.gridheight = 1;
/* 193 */     localGridBagLayout.setConstraints(this.okButton, localGridBagConstraints);
/* 194 */     add(this.okButton);
/* 195 */     this.okButton.addActionListener(this);
/*     */ 
/* 197 */     localGridBagConstraints.gridx = 1;
/* 198 */     localGridBagConstraints.gridy = 3;
/* 199 */     localGridBagConstraints.gridheight = 1;
/* 200 */     add(this.cancelButton);
/* 201 */     localGridBagLayout.setConstraints(this.cancelButton, localGridBagConstraints);
/*     */ 
/* 203 */     this.cancelButton.addActionListener(this);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 207 */     if (paramActionEvent.getSource() == this.okButton)
/*     */     {
/* 209 */       if (startFieldError()) {
/* 210 */         startMeasureEdit.setText("Error");
/*     */       }
/* 212 */       else if (countFieldError()) {
/* 213 */         measureCountEdit.setText("Error");
/*     */       }
/*     */       else {
/* 216 */         zoom();
/* 217 */         dispose();
/*     */       }
/*     */     }
/* 220 */     if (paramActionEvent.getSource() == this.cancelButton)
/* 221 */       dispose();
/*     */   }
/*     */ 
/*     */   private double countMeasures(Phrase paramPhrase)
/*     */   {
/* 228 */     double d = 0.0D;
/* 229 */     for (int i = 0; i < paramPhrase.size(); ++i) {
/* 230 */       d += paramPhrase.getNote(i).getRhythmValue();
/*     */     }
/*     */ 
/* 233 */     return (d / paramPhrase.getNumerator());
/*     */   }
/*     */ 
/*     */   private static boolean intFieldError(TextField paramTextField, double paramDouble1, double paramDouble2)
/*     */   {
/* 241 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 243 */     if (!(localStringTokenizer.hasMoreElements())) {
/* 244 */       paramTextField.setText("Error");
/* 245 */       return true;
/*     */     }
/*     */ 
/* 248 */     String str = localStringTokenizer.nextToken();
/*     */     try
/*     */     {
/* 251 */       int i = new Integer(str).intValue();
/*     */ 
/* 254 */       if (i < paramDouble1) {
/* 255 */         paramTextField.setText("Error");
/* 256 */         return true;
/*     */       }
/* 258 */       if (i > paramDouble2) {
/* 259 */         paramTextField.setText("Error");
/* 260 */         return true;
/*     */       }
/*     */     }
/*     */     catch (Throwable localThrowable) {
/* 264 */       paramTextField.setText("Error");
/* 265 */       return true;
/*     */     }
/*     */ 
/* 268 */     if (localStringTokenizer.hasMoreElements()) {
/* 269 */       paramTextField.setText("Error");
/* 270 */       return true;
/*     */     }
/*     */ 
/* 273 */     return false;
/*     */   }
/*     */ 
/*     */   private static int getIntegerValue(TextField paramTextField)
/*     */   {
/* 280 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramTextField.getText());
/*     */ 
/* 282 */     String str = localStringTokenizer.nextToken();
/*     */ 
/* 284 */     return new Integer(str).intValue();
/*     */   }
/*     */ 
/*     */   private boolean startFieldError()
/*     */   {
/* 290 */     return intFieldError(startMeasureEdit, 1.0D, countMeasures(this.phrase) + 0.99D);
/*     */   }
/*     */ 
/*     */   private boolean countFieldError()
/*     */   {
/* 299 */     return intFieldError(measureCountEdit, 1.0D, 99999.990000000005D);
/*     */   }
/*     */ 
/*     */   private void moveMeasures(Phrase paramPhrase1, Phrase paramPhrase2, double paramDouble)
/*     */   {
/* 310 */     double d1 = paramDouble * paramPhrase1.getNumerator();
/*     */ 
/* 315 */     while ((d1 > 0.005D) && (paramPhrase1.size() > 0))
/*     */     {
/* 317 */       Note localNote = paramPhrase1.getNote(0);
/* 318 */       double d2 = localNote.getRhythmValue();
/* 319 */       paramPhrase2.addNote(localNote);
/* 320 */       paramPhrase1.removeNote(0);
/* 321 */       d1 -= d2;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void moveAll(Phrase paramPhrase1, Phrase paramPhrase2)
/*     */   {
/* 331 */     while (paramPhrase1.size() > 0) {
/* 332 */       Note localNote = paramPhrase1.getNote(0);
/* 333 */       paramPhrase2.addNote(localNote);
/* 334 */       paramPhrase1.removeNote(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void zoom()
/*     */   {
/* 341 */     int i = getIntegerValue(startMeasureEdit) - 1;
/*     */ 
/* 343 */     int j = getIntegerValue(measureCountEdit);
/*     */ 
/* 345 */     this.beforeZoom.empty();
/* 346 */     this.afterZoom.empty();
/*     */ 
/* 348 */     moveMeasures(this.phrase, this.beforeZoom, i);
/* 349 */     moveAll(this.phrase, this.afterZoom);
/* 350 */     moveMeasures(this.afterZoom, this.phrase, i + j - countMeasures(this.beforeZoom));
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.CpnZoomScreen
 * JD-Core Version:    0.5.3
 */