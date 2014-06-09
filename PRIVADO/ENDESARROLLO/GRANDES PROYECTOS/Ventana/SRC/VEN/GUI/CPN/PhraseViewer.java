/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.TextArea;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.text.DecimalFormat;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class PhraseViewer extends Dialog
/*     */   implements WindowListener
/*     */ {
/*  42 */   private ScrollPane scrollPane = new ScrollPane();
/*  43 */   private TextArea textArea = new TextArea(20, 120);
/*     */   private Phrase phrase;
/*     */   private Stave stave;
/*  47 */   private DecimalFormat decimalFormat = new DecimalFormat("#####.######");
/*     */ 
/*     */   public PhraseViewer(Frame paramFrame)
/*     */   {
/*  52 */     super(paramFrame, "Phrase Detail Display", true);
/*     */ 
/*  56 */     setSize(500, 400);
/*  57 */     placeControls();
/*  58 */     addWindowListener(this);
/*  59 */     setVisible(false);
/*  60 */     pack();
/*     */   }
/*     */ 
/*     */   private void placeControls()
/*     */   {
/*  65 */     this.scrollPane.add(this.textArea);
/*  66 */     setLayout(new BorderLayout());
/*  67 */     add("Center", this.scrollPane);
/*     */   }
/*     */ 
/*     */   public void showPhrase(Stave paramStave, Phrase paramPhrase, int paramInt1, int paramInt2)
/*     */   {
/*  75 */     this.stave = paramStave;
/*  76 */     this.phrase = paramPhrase;
/*  77 */     getPhraseText();
/*  78 */     setLocation(paramInt1, paramInt2);
/*  79 */     show();
/*     */   }
/*     */ 
/*     */   private void getPhraseText() {
/*  83 */     getStaveText();
/*  84 */     this.textArea.append("Phrase has " + this.phrase.size() + " notes.\n");
/*     */ 
/*  87 */     this.textArea.append("Tempo " + this.decimalFormat.format(this.phrase.getTempo()));
/*     */ 
/*  90 */     this.textArea.append("    Numerator " + this.phrase.getNumerator());
/*     */ 
/*  92 */     this.textArea.append("    Denominator " + this.phrase.getDenominator());
/*     */ 
/*  94 */     this.textArea.append("\n");
/*     */ 
/*  96 */     for (int i = 0; i < this.phrase.size(); ++i)
/*  97 */       getNoteText(this.phrase.getNote(i));
/*     */   }
/*     */ 
/*     */   private void getStaveText()
/*     */   {
/* 102 */     this.textArea.append("Stave " + this.stave.getTitle() + "   Metre " + this.decimalFormat.format(this.stave.getMetre()) + "\n");
/*     */   }
/*     */ 
/*     */   private void getNoteText(Note paramNote)
/*     */   {
/* 110 */     this.textArea.append("Pitch " + paramNote.getPitch());
/*     */ 
/* 112 */     this.textArea.append("   Start " + this.decimalFormat.format(paramNote.getSampleStartTime()));
/*     */ 
/* 115 */     this.textArea.append("   Rhythm " + this.decimalFormat.format(paramNote.getRhythmValue()));
/*     */ 
/* 118 */     this.textArea.append("   Dur " + this.decimalFormat.format(paramNote.getDuration()));
/*     */ 
/* 121 */     this.textArea.append("   Offset " + this.decimalFormat.format(paramNote.getOffset()));
/*     */ 
/* 124 */     this.textArea.append("   Vol " + paramNote.getDynamic());
/*     */ 
/* 126 */     this.textArea.append("\n");
/*     */   }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent)
/*     */   {
/* 137 */     if (paramWindowEvent.getSource() != this) return; dispose();
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
 * Qualified Name:     jm.gui.cpn.PhraseViewer
 * JD-Core Version:    0.5.3
 */