/*      */ package jm.gui.cpn;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Image;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.image.FilteredImageSource;
/*      */ import java.awt.image.RGBImageFilter;
/*      */ import java.io.PrintStream;
/*      */ import java.util.Vector;
/*      */ import javax.swing.JPanel;
/*      */ import jm.JMC;
/*      */ import jm.music.data.Note;
/*      */ import jm.music.data.Phrase;
/*      */ import jm.music.tools.ChordAnalysis;
/*      */ import jm.music.tools.PhraseAnalysis;
/*      */ 
/*      */ public class JGrandStave extends JPanel
/*      */   implements JMC
/*      */ {
/*      */   private int tonic;
/*      */   RGBImageFilter filter;
/*   87 */   public static final int[] keys = { 11, 6, 1, 8, 3, 10, 5, 0, 7, 2, 9, 4, 11, 6, 1 };
/*      */   protected int[] scale;
/*      */   private boolean isNormalColor;
/*      */   protected KeyChangeListener keyChangeListener;
/*      */   public Image image;
/*      */   protected Graphics g;
/*      */   protected RedFilter crotchetUp;
/*      */   protected RedFilter crotchetDown;
/*      */   protected RedFilter quaverDown;
/*      */   protected RedFilter quaverUp;
/*      */   protected RedFilter semiquaverDown;
/*      */   protected RedFilter semiquaverUp;
/*      */   protected RedFilter minimDown;
/*      */   protected RedFilter minimUp;
/*      */   protected RedFilter semibreve;
/*      */   protected RedFilter dot;
/*      */   protected RedFilter semiquaverRest;
/*      */   protected RedFilter quaverRest;
/*      */   protected RedFilter crotchetRest;
/*      */   protected RedFilter minimRest;
/*      */   protected RedFilter semibreveRest;
/*      */   protected RedFilter sharp;
/*      */   protected RedFilter flat;
/*      */   protected RedFilter natural;
/*      */   protected RedFilter delete;
/*      */   protected RedFilter tie;
/*      */   protected Image trebleClef;
/*      */   protected Image bassClef;
/*      */   protected Image one;
/*      */   protected Image two;
/*      */   protected Image three;
/*      */   protected Image four;
/*      */   protected Image five;
/*      */   protected Image six;
/*      */   protected Image seven;
/*      */   protected Image eight;
/*      */   protected Image nine;
/*      */   public int staveSpaceHeight;
/*      */   public int rightMargin;
/*      */   public int beatWidth;
/*      */   public int staveWidth;
/*      */   public int imageHeightOffset;
/*      */   public int clefWidth;
/*      */   public int timeSigWidth;
/*      */   public int keySigWidth;
/*      */   public int bPos;
/*      */   protected Phrase phrase;
/*      */   protected RedFilter currImage;
/*      */   protected int currBeatWidth;
/*      */   protected int totalBeatWidth;
/*      */   protected boolean dottedNote;
/*      */   protected int[] notePosOffset;
/*      */   protected double metre;
/*      */   protected int keySignature;
/*      */   protected int[] sharps;
/*      */   protected int[] flats;
/*      */   protected Vector previouslyChromatic;
/*      */   protected int[] lineNotes;
/*      */   public Vector notePositions;
/*      */   protected int maxPitch;
/*      */   protected int minPitch;
/*      */   protected String title;
/*      */   protected boolean barNumbers;
/*      */   protected boolean editable;
/*      */   protected boolean qtOn;
/*      */   protected int panelHeight;
/*      */   protected int staveDelta;
/*      */   public static final int MAX_HEIGHT = 500;
/*      */   public static final int MAX_WIDTH = 2000;
/*      */   private double beatCounter;
/*      */   private boolean isFirstNoteInTie;
/*      */   private boolean isNote;
/*      */   private boolean firstAccidentalDisplayed;
/*      */   private boolean isTied;
/*      */   private boolean isUp;
/*      */   private boolean semitoneShiftUp;
/*      */   private boolean extraImagesUsed;
/*      */   private boolean requiresMoreThanOneImage;
/*      */   private double excessRhythmValue;
/*      */   private int savedBeatWidth;
/*      */   private int savedBeatWidth2;
/*      */   private int lastChordDisplayed;
/*      */   private int lastPosition;
/*      */   private int[] firstChords;
/*      */   private int[] secondChords;
/*      */   private String[] chordStrings;
/*      */ 
/*      */   public JGrandStave()
/*      */   {
/*  153 */     this(new Phrase());
/*  154 */     this.bPos = 110;
/*  155 */     this.panelHeight = 310;
/*  156 */     setSize(this.beatWidth * 40, this.panelHeight);
/*      */   }
/*      */ 
/*      */   public JGrandStave(Phrase paramPhrase)
/*      */   {
/*   61 */     this.tonic = 0;
/*      */ 
/*   63 */     this.filter = new RGBImageFilter()
/*      */     {
/*      */       public int filterRGB(int paramInt1, int paramInt2, int paramInt3)
/*      */       {
/*   69 */         return (paramInt3 | 0xFFFF0000);
/*      */       }
/*      */     };
/*   90 */     this.scale = JMC.MAJOR_SCALE;
/*      */ 
/*   92 */     this.isNormalColor = true;
/*      */ 
/*   94 */     this.keyChangeListener = null;
/*      */ 
/*  100 */     this.crotchetUp = new RedFilter();
/*  101 */     this.crotchetDown = new RedFilter();
/*  102 */     this.quaverDown = new RedFilter();
/*  103 */     this.quaverUp = new RedFilter();
/*  104 */     this.semiquaverDown = new RedFilter();
/*  105 */     this.semiquaverUp = new RedFilter();
/*  106 */     this.minimDown = new RedFilter();
/*  107 */     this.minimUp = new RedFilter();
/*  108 */     this.semibreve = new RedFilter();
/*  109 */     this.dot = new RedFilter();
/*  110 */     this.semiquaverRest = new RedFilter();
/*  111 */     this.quaverRest = new RedFilter();
/*  112 */     this.crotchetRest = new RedFilter();
/*  113 */     this.minimRest = new RedFilter();
/*  114 */     this.semibreveRest = new RedFilter();
/*  115 */     this.sharp = new RedFilter();
/*  116 */     this.flat = new RedFilter();
/*  117 */     this.natural = new RedFilter();
/*  118 */     this.delete = new RedFilter();
/*  119 */     this.tie = new RedFilter(Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/tie.gif")));
/*      */ 
/*  125 */     this.staveSpaceHeight = 8; this.rightMargin = 20; this.beatWidth = 43; this.staveWidth = (this.beatWidth * 15); this.imageHeightOffset = 28; this.clefWidth = 38; this.timeSigWidth = 5; this.keySigWidth = 5;
/*      */ 
/*  127 */     this.bPos = 28;
/*      */ 
/*  131 */     this.dottedNote = false;
/*  132 */     this.notePosOffset = new int[] { 24, 24, 20, 20, 16, 12, 12, 8, 8, 4, 4, 0 };
/*  133 */     this.metre = 4.0D;
/*  134 */     this.keySignature = 0;
/*  135 */     this.sharps = new int[] { 77, 72, 79, 74, 69, 76, 71 };
/*  136 */     this.flats = new int[] { 71, 76, 69, 74, 67, 72, 65 };
/*  137 */     this.previouslyChromatic = new Vector();
/*  138 */     this.lineNotes = new int[] { 0, 1, 4, 7, 8, 11, 14, 15, 17, 18, 21, 22 };
/*  139 */     this.notePositions = new Vector();
/*  140 */     this.maxPitch = 127; this.minPitch = 0;
/*      */ 
/*  142 */     this.barNumbers = false; this.editable = true; this.qtOn = false;
/*  143 */     this.panelHeight = 110; this.staveDelta = 0;
/*      */ 
/*  587 */     this.isFirstNoteInTie = true;
/*      */ 
/*  589 */     this.isNote = false;
/*      */ 
/*  591 */     this.firstAccidentalDisplayed = false;
/*      */ 
/*  593 */     this.isTied = false;
/*      */ 
/*  595 */     this.isUp = true;
/*      */ 
/*  597 */     this.semitoneShiftUp = false;
/*      */ 
/*  609 */     this.lastChordDisplayed = -1;
/*      */ 
/*  611 */     this.lastPosition = 0;
/*      */ 
/*  613 */     this.firstChords = new int[0];
/*      */ 
/*  615 */     this.secondChords = new int[0];
/*      */ 
/*  617 */     this.chordStrings = new String[] { "I", "II", "III", "IV", "V", "VI", "VII", "." };
/*      */ 
/*  161 */     this.phrase = paramPhrase;
/*  162 */     this.title = paramPhrase.getTitle();
/*      */ 
/*  164 */     setBackground(Color.getHSBColor(0.14F, 0.09F, 1.0F));
/*      */ 
/*  166 */     setSize(this.beatWidth * 40, this.panelHeight);
/*  167 */     if (getSize().width < (int)(paramPhrase.getEndTime() * this.beatWidth * 1.5D)) setSize((int)(paramPhrase.getEndTime() * this.beatWidth * 1.5D), this.panelHeight);
/*      */ 
/*  175 */     JStaveActionHandler localJStaveActionHandler = new JStaveActionHandler(this);
/*  176 */     addMouseListener(localJStaveActionHandler);
/*  177 */     addMouseMotionListener(localJStaveActionHandler);
/*      */     try
/*      */     {
/*  191 */       this.trebleClef = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/trebleClef.gif"));
/*  192 */       this.bassClef = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/bassClef.gif"));
/*  193 */       this.crotchetDown.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/crotchetDown.gif"));
/*  194 */       this.crotchetUp.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/crotchetUp.gif"));
/*  195 */       this.quaverDown.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/quaverDown.gif"));
/*  196 */       this.quaverUp.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/quaverUp.gif"));
/*  197 */       this.semiquaverDown.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/semiquaverDown.gif"));
/*  198 */       this.semiquaverUp.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/semiquaverUp.gif"));
/*  199 */       this.minimDown.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/minimDown.gif"));
/*  200 */       this.minimUp.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/minimUp.gif"));
/*  201 */       this.semibreve.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/semibreve.gif"));
/*  202 */       this.dot.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/dot.gif"));
/*  203 */       this.semiquaverRest.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/semiquaverRest.gif"));
/*  204 */       this.quaverRest.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/quaverRest.gif"));
/*  205 */       this.crotchetRest.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/crotchetRest.gif"));
/*  206 */       this.minimRest.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/minimRest.gif"));
/*  207 */       this.semibreveRest.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/semibreveRest.gif"));
/*  208 */       this.sharp.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/sharp.gif"));
/*  209 */       this.flat.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/flat.gif"));
/*  210 */       this.natural.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/natural.gif"));
/*  211 */       this.one = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/one.gif"));
/*  212 */       this.two = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/two.gif"));
/*  213 */       this.three = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/three.gif"));
/*  214 */       this.four = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/four.gif"));
/*  215 */       this.five = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/five.gif"));
/*  216 */       this.six = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/six.gif"));
/*  217 */       this.seven = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/seven.gif"));
/*  218 */       this.eight = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/eight.gif"));
/*  219 */       this.nine = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/nine.gif"));
/*  220 */       this.delete.image = Toolkit.getDefaultToolkit().getImage(Stave.class.getResource("graphics/delete.gif"));
/*      */     }
/*      */     catch (Exception localException)
/*      */     {
/*  228 */       System.out.println("Error while loading pictures...");
/*  229 */       localException.printStackTrace();
/*      */     }
/*      */ 
/*  232 */     2 local2 = new RGBImageFilter()
/*      */     {
/*      */       public int filterRGB(int paramInt1, int paramInt2, int paramInt3)
/*      */       {
/*  238 */         return (paramInt3 | 0xFFFF0000);
/*      */       }
/*      */     };
/*  244 */     this.crotchetDown.redImage = createImage(new FilteredImageSource(this.crotchetDown.image.getSource(), local2));
/*      */ 
/*  247 */     this.crotchetUp.redImage = createImage(new FilteredImageSource(this.crotchetUp.image.getSource(), local2));
/*      */ 
/*  250 */     this.quaverDown.redImage = createImage(new FilteredImageSource(this.quaverDown.image.getSource(), local2));
/*      */ 
/*  253 */     this.quaverUp.redImage = createImage(new FilteredImageSource(this.quaverUp.image.getSource(), local2));
/*      */ 
/*  256 */     this.semiquaverDown.redImage = createImage(new FilteredImageSource(this.semiquaverDown.image.getSource(), local2));
/*      */ 
/*  259 */     this.semiquaverUp.redImage = createImage(new FilteredImageSource(this.semiquaverUp.image.getSource(), local2));
/*      */ 
/*  262 */     this.minimDown.redImage = createImage(new FilteredImageSource(this.minimDown.image.getSource(), local2));
/*      */ 
/*  265 */     this.minimUp.redImage = createImage(new FilteredImageSource(this.minimUp.image.getSource(), local2));
/*      */ 
/*  268 */     this.semibreve.redImage = createImage(new FilteredImageSource(this.semibreve.image.getSource(), local2));
/*      */ 
/*  271 */     this.dot.redImage = createImage(new FilteredImageSource(this.dot.image.getSource(), local2));
/*      */ 
/*  274 */     this.semiquaverRest.redImage = createImage(new FilteredImageSource(this.semiquaverRest.image.getSource(), local2));
/*      */ 
/*  277 */     this.quaverRest.redImage = createImage(new FilteredImageSource(this.quaverRest.image.getSource(), local2));
/*      */ 
/*  280 */     this.crotchetRest.redImage = createImage(new FilteredImageSource(this.crotchetRest.image.getSource(), local2));
/*      */ 
/*  283 */     this.minimRest.redImage = createImage(new FilteredImageSource(this.minimRest.image.getSource(), local2));
/*      */ 
/*  286 */     this.sharp.redImage = createImage(new FilteredImageSource(this.sharp.image.getSource(), local2));
/*      */ 
/*  289 */     this.flat.redImage = createImage(new FilteredImageSource(this.flat.image.getSource(), local2));
/*      */ 
/*  292 */     this.natural.redImage = createImage(new FilteredImageSource(this.natural.image.getSource(), local2));
/*      */ 
/*  295 */     this.delete.redImage = createImage(new FilteredImageSource(this.delete.image.getSource(), local2));
/*      */ 
/*  376 */     this.bPos = 110;
/*  377 */     this.panelHeight = 310;
/*  378 */     setSize(this.beatWidth * 40, this.panelHeight);
/*      */   }
/*      */ 
/*      */   public void paintComponent(Graphics paramGraphics)
/*      */   {
/*  384 */     if (this.phrase == null) {
/*  385 */       return;
/*      */     }
/*      */ 
/*  389 */     if (this.image == null) {
/*  390 */       this.image = createImage(2000, 500);
/*  391 */       this.g = this.image.getGraphics();
/*      */     }
/*      */ 
/*  394 */     this.beatCounter = 0.0D;
/*      */ 
/*  396 */     this.previouslyChromatic.removeAllElements();
/*      */ 
/*  398 */     this.notePositions.removeAllElements();
/*  399 */     int i = 0;
/*      */ 
/*  401 */     if (this.title != null) this.g.drawString(this.title, this.rightMargin, this.bPos - 50);
/*      */ 
/*  403 */     int j = 0;
/*      */     int k;
/*      */     int i1;
/*      */     int i2;
/*      */     int i3;
/*  405 */     if ((this.keySignature > 0) && (this.keySignature < 8)) {
/*  406 */       for (k = 0; k < this.keySignature; ++k)
/*      */       {
/*  408 */         i1 = this.notePosOffset[(this.sharps[k] % 12)] + this.bPos - 4 + (5 - (this.sharps[k] / 12)) * 24 + (6 - (this.sharps[k] / 12)) * 4;
/*      */ 
/*  410 */         this.g.drawImage(this.sharp.image, this.rightMargin + this.clefWidth + j, i1, this);
/*      */ 
/*  412 */         this.g.drawImage(this.sharp.image, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*      */ 
/*  414 */         j += 10;
/*      */ 
/*  416 */         i2 = this.sharps[k] % 12;
/*  417 */         for (i3 = 0; i3 < 128; ++i3) {
/*  418 */           if (i3 % 12 == i2) {
/*  419 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  420 */             ++i;
/*      */           }
/*      */         }
/*  423 */         this.keySigWidth = j;
/*      */       }
/*      */     }
/*  426 */     else if ((this.keySignature < 0) && (this.keySignature > -8)) {
/*  427 */       for (k = 0; k < Math.abs(this.keySignature); ++k)
/*      */       {
/*  429 */         i1 = this.notePosOffset[(this.flats[k] % 12)] + this.bPos - 4 + (5 - (this.flats[k] / 12)) * 24 + (6 - (this.flats[k] / 12)) * 4;
/*      */ 
/*  431 */         this.g.drawImage(this.flat.image, this.rightMargin + this.clefWidth + j, i1, this);
/*      */ 
/*  433 */         this.g.drawImage(this.flat.image, this.rightMargin + this.clefWidth + j, i1 + this.staveSpaceHeight * 7, this);
/*      */ 
/*  435 */         j += 10;
/*      */ 
/*  437 */         i2 = this.flats[k] % 12;
/*  438 */         for (i3 = 0; i3 < 128; ++i3) {
/*  439 */           if (i3 % 12 == i2) {
/*  440 */             this.previouslyChromatic.addElement(new Integer(i3));
/*  441 */             ++i;
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/*  447 */     this.keySigWidth = (j + 3);
/*      */ 
/*  450 */     if (this.metre != 0.0D) {
/*  451 */       Image[] arrayOfImage = { this.one, this.two, this.three, this.four, this.five, this.six, this.seven, this.eight, this.nine };
/*      */ 
/*  454 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13, this);
/*  455 */       this.g.drawImage(arrayOfImage[((int)this.metre - 1)], this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 13 + this.staveSpaceHeight * 6, this);
/*      */ 
/*  457 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29, this);
/*  458 */       this.g.drawImage(this.four, this.rightMargin + this.clefWidth + this.keySigWidth, this.bPos + 29 + this.staveSpaceHeight * 6, this);
/*  459 */       this.timeSigWidth = 30; } else {
/*  460 */       this.timeSigWidth = 5;
/*      */     }
/*  462 */     this.totalBeatWidth = (this.rightMargin + this.clefWidth + this.keySigWidth + this.timeSigWidth);
/*      */ 
/*  464 */     this.firstChords = ChordAnalysis.getFirstPassChords(this.phrase, 1.0D, this.tonic, this.scale);
/*      */ 
/*  467 */     this.secondChords = ChordAnalysis.getSecondPassChords(this.phrase, 1.0D, this.tonic, this.scale);
/*      */ 
/*  470 */     this.lastChordDisplayed = -1;
/*      */ 
/*  473 */     for (int l = 0; l < this.phrase.size(); ++l) {
/*  474 */       i1 = this.phrase.getNote(l).getPitch();
/*      */ 
/*  479 */       if ((i1 == -2147483648) || (this.phrase.getNote(l).getRhythmValue() == 0.0D))
/*  480 */         i2 = this.notePosOffset[11] + this.bPos - 4 + 0 + 4;
/*      */       else {
/*  482 */         i2 = this.notePosOffset[(i1 % 12)] + this.bPos - 4 + (5 - (i1 / 12)) * 24 + (6 - (i1 / 12)) * 4;
/*      */       }
/*      */ 
/*  485 */       if ((i1 == -2147483648) || (PhraseAnalysis.isScale(this.phrase.getNote(l), this.tonic, this.scale)))
/*      */       {
/*  489 */         this.isNormalColor = true;
/*      */       }
/*      */       else this.isNormalColor = false;
/*      */ 
/*  494 */       this.firstAccidentalDisplayed = false;
/*      */ 
/*  496 */       this.semitoneShiftUp = false;
/*  497 */       this.isTied = false;
/*  498 */       this.isFirstNoteInTie = true;
/*  499 */       this.extraImagesUsed = false;
/*  500 */       this.savedBeatWidth = this.totalBeatWidth;
/*  501 */       this.savedBeatWidth2 = 0;
/*  502 */       double d1 = this.phrase.getNote(l).getRhythmValue();
/*  503 */       double d2 = this.metre - (this.beatCounter % this.metre);
/*      */ 
/*  505 */       while (d2 < d1) {
/*  506 */         this.isTied = true;
/*  507 */         drawNote(i1, d2, i2, i);
/*      */ 
/*  509 */         d1 -= d2;
/*  510 */         d2 = this.metre - (this.beatCounter % this.metre);
/*      */       }
/*      */ 
/*  514 */       drawNote(i1, d1, i2, i);
/*      */     }
/*      */ 
/*  536 */     for (l = 0; l < 5; ++l) {
/*  537 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  541 */     for (l = 6; l < 11; ++l) {
/*  542 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  545 */     this.g.setColor(Color.darkGray);
/*      */ 
/*  547 */     for (l = -7; l < -2; ++l) {
/*  548 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  552 */     for (l = 13; l < 18; ++l) {
/*  553 */       this.g.drawLine(this.rightMargin, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  557 */     this.g.setColor(Color.lightGray);
/*  558 */     for (l = 0; l < 5; ++l) {
/*  559 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  564 */     for (l = 6; l < 11; ++l) {
/*  565 */       this.g.drawLine(this.totalBeatWidth, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight, this.totalBeatWidth + 50, this.bPos + this.imageHeightOffset - (2 * this.staveSpaceHeight) + l * this.staveSpaceHeight);
/*      */     }
/*      */ 
/*  570 */     this.g.setColor(Color.black);
/*      */ 
/*  572 */     this.g.drawImage(this.trebleClef, this.rightMargin + 7, this.bPos - 4, this);
/*  573 */     this.g.drawImage(this.bassClef, this.rightMargin + 7, this.bPos + this.staveSpaceHeight * 6, this);
/*      */ 
/*  577 */     paramGraphics.drawImage(this.image, 0, 0, null);
/*      */ 
/*  580 */     this.g.setColor(getBackground());
/*  581 */     this.g.fillRect(0, 0, 2000, 500);
/*  582 */     this.g.setColor(getForeground());
/*      */   }
/*      */ 
/*      */   private void drawNote(int paramInt1, double paramDouble, int paramInt2, int paramInt3)
/*      */   {
/*  622 */     this.requiresMoreThanOneImage = false;
/*  623 */     this.excessRhythmValue = 0.0D;
/*      */ 
/*  625 */     if (this.beatCounter % 1.0D == 0.0D) {
/*  626 */       int i = (int)(this.beatCounter / 1.0D);
/*  627 */       int j = i - this.lastChordDisplayed;
/*  628 */       int k = j;
/*  629 */       while (this.lastChordDisplayed < i) {
/*  630 */         this.lastChordDisplayed += 1;
/*      */ 
/*  632 */         --k;
/*  633 */         this.g.drawString(this.chordStrings[this.firstChords[this.lastChordDisplayed]], (int)(this.totalBeatWidth - ((this.totalBeatWidth - this.lastPosition) * k / j)), 20);
/*      */ 
/*  638 */         int l = this.secondChords[this.lastChordDisplayed];
/*  639 */         String str = this.chordStrings[l];
/*      */ 
/*  641 */         this.g.drawString(str, (int)(this.totalBeatWidth - ((this.totalBeatWidth - this.lastPosition) * k / j)), 40);
/*      */       }
/*      */ 
/*  647 */       this.lastPosition = this.totalBeatWidth;
/*      */     }
/*      */ 
/*  651 */     chooseImage(paramInt1, paramDouble, 71, 60, 50);
/*      */ 
/*  653 */     drawNote2(paramInt1, paramDouble - this.excessRhythmValue, paramInt2, paramInt3);
/*      */ 
/*  655 */     if (this.requiresMoreThanOneImage) {
/*  656 */       drawNote(paramInt1, this.excessRhythmValue, paramInt2, paramInt3);
/*      */ 
/*  658 */       this.extraImagesUsed = true;
/*      */     }
/*      */   }
/*      */ 
/*      */   private void drawNote2(int paramInt1, double paramDouble, int paramInt2, int paramInt3)
/*      */   {
/*      */     int i;
/*      */     int j;
/*  666 */     if ((((paramInt1 % 12 == 1) || (paramInt1 % 12 == 3) || (paramInt1 % 12 == 6) || (paramInt1 % 12 == 8) || (paramInt1 % 12 == 10))) && (paramInt1 != -2147483648) && (paramDouble != 0.0D)) {
/*  667 */       if (this.keySignature > -1) {
/*  668 */         if (!(this.firstAccidentalDisplayed)) {
/*  669 */           displayImage(this.g, this.sharp, this.totalBeatWidth - 9, paramInt2);
/*      */         }
/*      */ 
/*  672 */         this.previouslyChromatic.addElement(new Integer(paramInt1 - 1));
/*      */       } else {
/*  674 */         paramInt2 -= 4;
/*  675 */         if (!(this.firstAccidentalDisplayed)) {
/*  676 */           displayImage(this.g, this.flat, this.totalBeatWidth - 9, paramInt2);
/*      */         }
/*      */ 
/*  679 */         this.previouslyChromatic.addElement(new Integer(paramInt1 + 1));
/*  680 */         ++paramInt1;
/*  681 */         this.semitoneShiftUp = true;
/*      */       }
/*      */     }
/*      */     else {
/*  685 */       i = this.previouslyChromatic.size();
/*  686 */       for (j = 0; j < i; ++j) {
/*  687 */         Integer localInteger = (Integer)this.previouslyChromatic.elementAt(j);
/*  688 */         if ((localInteger.intValue() != paramInt1) || (paramInt1 == -2147483648) || (paramDouble == 0.0D))
/*      */           continue;
/*  690 */         if (!(this.firstAccidentalDisplayed)) {
/*  691 */           displayImage(this.g, this.natural, this.totalBeatWidth - 7, paramInt2);
/*      */         }
/*      */ 
/*  695 */         if (j > paramInt3 - 1) this.previouslyChromatic.removeElementAt(j);
/*  696 */         j = i;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  701 */     this.firstAccidentalDisplayed = true;
/*      */ 
/*  704 */     displayImage(this.g, this.currImage, this.totalBeatWidth, paramInt2);
/*      */ 
/*  707 */     this.notePositions.addElement(new Integer(this.totalBeatWidth));
/*  708 */     this.notePositions.addElement(new Integer(paramInt2));
/*      */ 
/*  711 */     if (this.dottedNote) {
/*  712 */       i = 1;
/*  713 */       for (j = 0; j < this.lineNotes.length; ++j) {
/*  714 */         if ((this.lineNotes[j] + 12 == paramInt1) || (this.lineNotes[j] + 36 == paramInt1) || (this.lineNotes[j] + 60 == paramInt1) || (this.lineNotes[j] + 84 == paramInt1) || (this.lineNotes[j] + 108 == paramInt1) || (paramInt1 == -2147483648)) {
/*  715 */           displayImage(this.g, this.dot, this.totalBeatWidth + 1, paramInt2 - 4);
/*      */ 
/*  717 */           i = 0;
/*  718 */           j = this.lineNotes.length;
/*      */         }
/*      */       }
/*  721 */       if (i != 0) {
/*  722 */         displayImage(this.g, this.dot, this.totalBeatWidth + 1, paramInt2);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  729 */     if ((paramInt1 == 60) || ((paramInt1 == 61) && (paramDouble != 0.0D))) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 52, this.totalBeatWidth + 12, this.bPos + 52);
/*      */ 
/*  732 */     if ((paramInt1 <= 40) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 100, this.totalBeatWidth + 12, this.bPos + 100);
/*  733 */     if ((paramInt1 <= 37) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 108, this.totalBeatWidth + 12, this.bPos + 108);
/*      */ 
/*  735 */     if ((paramInt1 <= 16) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 156, this.totalBeatWidth + 12, this.bPos + 156);
/*  736 */     if ((paramInt1 <= 13) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 164, this.totalBeatWidth + 12, this.bPos + 164);
/*  737 */     if ((paramInt1 <= 10) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 172, this.totalBeatWidth + 12, this.bPos + 172);
/*  738 */     if ((paramInt1 <= 6) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 180, this.totalBeatWidth + 12, this.bPos + 180);
/*  739 */     if ((paramInt1 <= 3) && (paramInt1 > -1) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 188, this.totalBeatWidth + 12, this.bPos + 188);
/*      */ 
/*  741 */     if ((paramInt1 >= 81) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos + 4, this.totalBeatWidth + 12, this.bPos + 4);
/*  742 */     if ((paramInt1 >= 84) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 4, this.totalBeatWidth + 12, this.bPos - 4);
/*      */ 
/*  744 */     if ((paramInt1 >= 105) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 52, this.totalBeatWidth + 12, this.bPos - 52);
/*  745 */     if ((paramInt1 >= 108) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 60, this.totalBeatWidth + 12, this.bPos - 60);
/*  746 */     if ((paramInt1 >= 112) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 68, this.totalBeatWidth + 12, this.bPos - 68);
/*  747 */     if ((paramInt1 >= 115) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 76, this.totalBeatWidth + 12, this.bPos - 76);
/*  748 */     if ((paramInt1 >= 119) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 84, this.totalBeatWidth + 12, this.bPos - 84);
/*  749 */     if ((paramInt1 >= 122) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 92, this.totalBeatWidth + 12, this.bPos - 92);
/*  750 */     if ((paramInt1 >= 125) && (paramInt1 < 128) && (paramDouble != 0.0D)) this.g.drawLine(this.totalBeatWidth - 3, this.bPos - 100, this.totalBeatWidth + 12, this.bPos - 100);
/*      */ 
/*  753 */     this.savedBeatWidth2 = this.totalBeatWidth;
/*      */ 
/*  755 */     if ((((this.isTied) || (this.extraImagesUsed))) && (this.isNote) && (!(this.isFirstNoteInTie))) {
/*  756 */       Image localImage = this.tie.image;
/*  757 */       if (!(this.isNormalColor)) {
/*  758 */         localImage = this.tie.redImage;
/*      */       }
/*      */ 
/*  761 */       j = paramInt2 + 19 - ((this.semitoneShiftUp) ? 4 : 0);
/*      */ 
/*  763 */       if (this.isUp) {
/*  764 */         this.g.drawImage(localImage, this.savedBeatWidth - 3 + 9, j + 17 + localImage.getHeight(this), this.savedBeatWidth2 + 19 - 9, j + 17, 0, 0, localImage.getWidth(this), localImage.getHeight(this), this);
/*      */       }
/*      */       else
/*      */       {
/*  774 */         this.g.drawImage(localImage, this.savedBeatWidth - 3 + 9, j - 20, this.savedBeatWidth2 + 19 - 9, j - 20 + localImage.getHeight(this), 0, 0, localImage.getWidth(this), localImage.getHeight(this), this);
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  785 */     if ((this.isFirstNoteInTie = 1) != 0) {
/*  786 */       this.isFirstNoteInTie = false;
/*      */     }
/*      */ 
/*  789 */     this.savedBeatWidth = this.totalBeatWidth;
/*      */ 
/*  792 */     this.totalBeatWidth += this.currBeatWidth;
/*  793 */     this.dottedNote = false;
/*      */ 
/*  796 */     this.beatCounter += (int)(paramDouble / 0.25D) * 0.25D;
/*      */ 
/*  799 */     if ((this.metre == 0.0D) || 
/*  800 */       (this.beatCounter % this.metre != 0.0D)) return;
/*  801 */     this.g.drawLine(this.totalBeatWidth, this.bPos + 12 - (this.staveSpaceHeight * 7), this.totalBeatWidth, this.bPos + 44 + this.staveSpaceHeight * 13);
/*      */ 
/*  803 */     if (this.barNumbers) this.g.drawString("" + (int)(this.beatCounter / this.metre + 1.0D), this.totalBeatWidth - 4, this.bPos - 50);
/*  804 */     this.totalBeatWidth += 12;
/*      */   }
/*      */ 
/*      */   public void displayImage(Graphics paramGraphics, RedFilter paramRedFilter, int paramInt1, int paramInt2)
/*      */   {
/*  813 */     if (this.isNormalColor)
/*  814 */       paramGraphics.drawImage(paramRedFilter.image, paramInt1, paramInt2, this);
/*      */     else
/*  816 */       paramGraphics.drawImage(paramRedFilter.redImage, paramInt1, paramInt2, this);
/*      */   }
/*      */ 
/*      */   public Dimension getPreferredSize()
/*      */   {
/*  875 */     return new Dimension(2000, 500);
/*      */   }
/*      */ 
/*      */   public void setPhrase(Phrase paramPhrase)
/*      */   {
/*  884 */     this.phrase = paramPhrase;
/*  885 */     this.previouslyChromatic.removeAllElements();
/*  886 */     setTitle(paramPhrase.getTitle());
/*  887 */     repaint();
/*      */   }
/*      */ 
/*      */   public Phrase getPhrase()
/*      */   {
/*  894 */     return this.phrase;
/*      */   }
/*      */ 
/*      */   public void setTitle(String paramString)
/*      */   {
/*  902 */     this.title = paramString;
/*      */   }
/*      */ 
/*      */   public String getTitle()
/*      */   {
/*  910 */     return this.title;
/*      */   }
/*      */ 
/*      */   public void removeTitle()
/*      */   {
/*  916 */     this.title = null;
/*      */   }
/*      */ 
/*      */   public void setMetre(double paramDouble)
/*      */   {
/*  925 */     this.metre = paramDouble;
/*      */   }
/*      */ 
/*      */   public double getMetre()
/*      */   {
/*  932 */     return this.metre;
/*      */   }
/*      */ 
/*      */   public void setScale(int[] paramArrayOfInt) {
/*  936 */     this.scale = paramArrayOfInt;
/*  937 */     setTonic(this.tonic);
/*      */   }
/*      */ 
/*      */   public int getTonic()
/*      */   {
/*  945 */     return this.tonic;
/*      */   }
/*      */ 
/*      */   public void setKey(int paramInt, int[] paramArrayOfInt) {
/*  949 */     this.scale = paramArrayOfInt;
/*  950 */     setTonic(paramInt);
/*      */   }
/*      */ 
/*      */   public void setTonic(int paramInt) {
/*  954 */     if ((paramInt < 0) || (paramInt > 11)) {
/*  955 */       return;
/*      */     }
/*      */ 
/*  958 */     if (this.scale == JMC.MAJOR_SCALE) {
/*  959 */       this.tonic = paramInt;
/*  960 */     } else if (this.scale == JMC.NATURAL_MINOR_SCALE) {
/*  961 */       this.tonic = paramInt;
/*  962 */       paramInt = (paramInt + 3) % 12;
/*      */     } else {
/*  964 */       return;
/*      */     }
/*      */ 
/*  967 */     for (int i = 0; i < keys.length; ++i) {
/*  968 */       if (keys[i] == paramInt) {
/*  969 */         this.keySignature = (i - 7);
/*      */       }
/*      */     }
/*      */ 
/*  973 */     repaint();
/*  974 */     if (this.keyChangeListener == null) {
/*  975 */       return;
/*      */     }
/*  977 */     this.keyChangeListener.keyChanged();
/*      */   }
/*      */ 
/*      */   public void setKeyChangeListener(KeyChangeListener paramKeyChangeListener) {
/*  981 */     this.keyChangeListener = paramKeyChangeListener;
/*      */   }
/*      */ 
/*      */   public void setKeySignature(int paramInt)
/*      */   {
/*  991 */     this.keySignature = ((paramInt < -7) ? -7 : (paramInt > 7) ? 7 : paramInt);
/*      */ 
/*  994 */     if (this.keyChangeListener == null) {
/*  995 */       return;
/*      */     }
/*  997 */     this.keyChangeListener.keyChanged();
/*      */   }
/*      */ 
/*      */   public int getKeySignature()
/*      */   {
/* 1005 */     return this.keySignature;
/*      */   }
/*      */ 
/*      */   public void setBarNumbers(boolean paramBoolean)
/*      */   {
/* 1013 */     this.barNumbers = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void setEditable(boolean paramBoolean)
/*      */   {
/* 1021 */     this.editable = paramBoolean;
/*      */   }
/*      */ 
/*      */   public int getMinPitch()
/*      */   {
/* 1028 */     return this.minPitch;
/*      */   }
/*      */ 
/*      */   public void setMinPitch(int paramInt)
/*      */   {
/* 1035 */     this.minPitch = paramInt;
/*      */   }
/*      */ 
/*      */   public int getMaxPitch()
/*      */   {
/* 1042 */     return this.maxPitch;
/*      */   }
/*      */ 
/*      */   public void setMaxPitch(int paramInt)
/*      */   {
/* 1049 */     this.maxPitch = paramInt;
/*      */   }
/*      */ 
/*      */   public int getTotalBeatWidth()
/*      */   {
/* 1056 */     return this.totalBeatWidth;
/*      */   }
/*      */ 
/*      */   public void setTotalBeatWidth(int paramInt)
/*      */   {
/* 1063 */     this.totalBeatWidth = paramInt;
/*      */   }
/*      */ 
/*      */   public boolean getBarNumbers()
/*      */   {
/* 1070 */     return this.barNumbers;
/*      */   }
/*      */ 
/*      */   public boolean getQtOn()
/*      */   {
/* 1077 */     return this.qtOn;
/*      */   }
/*      */ 
/*      */   public void setQtOn(boolean paramBoolean)
/*      */   {
/* 1084 */     this.qtOn = paramBoolean;
/*      */   }
/*      */ 
/*      */   public void updateChange()
/*      */   {
/*      */   }
/*      */ 
/*      */   public void deleteLastNote()
/*      */   {
/* 1104 */     if (this.phrase.size() > 0) {
/* 1105 */       this.phrase.removeNote(this.phrase.size() - 1);
/* 1106 */       repaint();
/* 1107 */       updateChange();
/*      */     }
/*      */   }
/*      */ 
/*      */   protected void chooseImage(int paramInt1, double paramDouble, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/* 1113 */     if (paramInt1 == -2147483648) {
/* 1114 */       this.isNote = false;
/* 1115 */       if (paramDouble <= 0.0D) {
/* 1116 */         this.currImage = this.delete;
/* 1117 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/*      */       }
/* 1119 */       if ((paramDouble > 0.0D) && (paramDouble <= 0.25D)) {
/* 1120 */         this.currImage = this.semiquaverRest;
/* 1121 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/*      */       }
/* 1123 */       if ((paramDouble > 0.25D) && (paramDouble <= 0.5D)) {
/* 1124 */         this.currImage = this.quaverRest;
/* 1125 */         this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/*      */       }
/* 1127 */       if ((paramDouble > 0.5D) && (paramDouble <= 0.75D)) {
/* 1128 */         this.currImage = this.quaverRest;
/* 1129 */         this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 1130 */         this.dottedNote = true;
/*      */       }
/* 1132 */       if ((paramDouble > 0.75D) && (paramDouble <= 1.0D)) {
/* 1133 */         this.currImage = this.crotchetRest;
/* 1134 */         this.currBeatWidth = this.beatWidth;
/*      */       }
/* 1136 */       if ((paramDouble > 1.0D) && (paramDouble <= 1.25D)) {
/* 1137 */         this.currImage = this.crotchetRest;
/* 1138 */         this.currBeatWidth = this.beatWidth;
/* 1139 */         this.requiresMoreThanOneImage = true;
/* 1140 */         this.excessRhythmValue = (paramDouble - 1.0D);
/*      */       }
/* 1142 */       if ((paramDouble > 1.25D) && (paramDouble <= 1.5D)) {
/* 1143 */         this.currImage = this.crotchetRest;
/* 1144 */         this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1145 */         this.dottedNote = true;
/*      */       }
/* 1147 */       if ((paramDouble > 1.5D) && (paramDouble <= 1.75D)) {
/* 1148 */         this.currImage = this.crotchetRest;
/* 1149 */         this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1150 */         this.dottedNote = true;
/* 1151 */         this.requiresMoreThanOneImage = true;
/* 1152 */         this.excessRhythmValue = (paramDouble - 1.5D);
/*      */       }
/*      */ 
/* 1155 */       if ((paramDouble > 1.75D) && (paramDouble <= 2.0D)) {
/* 1156 */         this.currImage = this.minimRest;
/* 1157 */         this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/*      */       }
/* 1159 */       if ((paramDouble > 2.0D) && (paramDouble <= 2.75D)) {
/* 1160 */         this.currImage = this.minimRest;
/* 1161 */         this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 1162 */         this.requiresMoreThanOneImage = true;
/* 1163 */         this.excessRhythmValue = (paramDouble - 2.0D);
/*      */       }
/* 1165 */       if ((paramDouble > 2.75D) && (paramDouble <= 3.0D)) {
/* 1166 */         this.currImage = this.minimRest;
/* 1167 */         this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1168 */         this.dottedNote = true;
/*      */       }
/* 1170 */       if ((paramDouble > 3.0D) && (paramDouble <= 3.75D)) {
/* 1171 */         this.currImage = this.minimRest;
/* 1172 */         this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1173 */         this.dottedNote = true;
/* 1174 */         this.requiresMoreThanOneImage = true;
/* 1175 */         this.excessRhythmValue = (paramDouble - 3.0D);
/*      */       }
/*      */ 
/* 1178 */       if ((paramDouble > 3.75D) && (paramDouble <= 4.0D)) {
/* 1179 */         this.currImage = this.semibreveRest;
/* 1180 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/*      */       }
/*      */     } else {
/* 1183 */       this.isNote = true;
/* 1184 */       if (((paramInt1 < paramInt2) && (paramInt1 >= paramInt3)) || (paramInt1 < paramInt4)) {
/* 1185 */         if (paramDouble <= 0.0D) {
/* 1186 */           this.currImage = this.delete;
/* 1187 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 1188 */           this.isUp = true;
/*      */         }
/* 1190 */         if ((paramDouble > 0.0D) && (paramDouble <= 0.25D)) {
/* 1191 */           this.currImage = this.semiquaverUp;
/* 1192 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 1193 */           this.isUp = true;
/*      */         }
/* 1195 */         if ((paramDouble > 0.25D) && (paramDouble <= 0.5D)) {
/* 1196 */           this.currImage = this.quaverUp;
/* 1197 */           this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 1198 */           this.isUp = true;
/*      */         }
/* 1200 */         if ((paramDouble > 0.5D) && (paramDouble <= 0.75D)) {
/* 1201 */           this.currImage = this.quaverUp;
/* 1202 */           this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 1203 */           this.dottedNote = true;
/* 1204 */           this.isUp = true;
/*      */         }
/* 1206 */         if ((paramDouble > 0.75D) && (paramDouble <= 1.0D)) {
/* 1207 */           this.currImage = this.crotchetUp;
/* 1208 */           this.currBeatWidth = this.beatWidth;
/* 1209 */           this.isUp = true;
/*      */         }
/* 1211 */         if ((paramDouble > 1.0D) && (paramDouble <= 1.25D)) {
/* 1212 */           this.currImage = this.crotchetUp;
/* 1213 */           this.currBeatWidth = this.beatWidth;
/* 1214 */           this.requiresMoreThanOneImage = true;
/* 1215 */           this.excessRhythmValue = (paramDouble - 1.0D);
/* 1216 */           this.isUp = true;
/*      */         }
/*      */ 
/* 1219 */         if ((paramDouble > 1.25D) && (paramDouble <= 1.5D)) {
/* 1220 */           this.currImage = this.crotchetUp;
/* 1221 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1222 */           this.dottedNote = true;
/* 1223 */           this.isUp = true;
/*      */         }
/* 1225 */         if ((paramDouble > 1.5D) && (paramDouble <= 1.75D)) {
/* 1226 */           this.currImage = this.crotchetUp;
/* 1227 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1228 */           this.dottedNote = true;
/* 1229 */           this.requiresMoreThanOneImage = true;
/* 1230 */           this.excessRhythmValue = (paramDouble - 1.5D);
/* 1231 */           this.isUp = true;
/*      */         }
/* 1233 */         if ((paramDouble > 1.75D) && (paramDouble <= 2.0D)) {
/* 1234 */           this.currImage = this.minimUp;
/* 1235 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 1236 */           this.isUp = true;
/*      */         }
/* 1238 */         if ((paramDouble > 2.0D) && (paramDouble <= 2.75D)) {
/* 1239 */           this.currImage = this.minimUp;
/* 1240 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 1241 */           this.requiresMoreThanOneImage = true;
/* 1242 */           this.excessRhythmValue = (paramDouble - 2.0D);
/* 1243 */           this.isUp = true;
/*      */         }
/* 1245 */         if ((paramDouble > 2.75D) && (paramDouble <= 3.0D)) {
/* 1246 */           this.currImage = this.minimUp;
/* 1247 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1248 */           this.dottedNote = true;
/* 1249 */           this.isUp = true;
/*      */         }
/* 1251 */         if ((paramDouble > 3.0D) && (paramDouble <= 3.75D)) {
/* 1252 */           this.currImage = this.minimUp;
/* 1253 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1254 */           this.dottedNote = true;
/* 1255 */           this.requiresMoreThanOneImage = true;
/* 1256 */           this.excessRhythmValue = (paramDouble - 3.0D);
/* 1257 */           this.isUp = true;
/*      */         }
/*      */ 
/* 1260 */         if ((paramDouble > 3.75D) && (paramDouble <= 4.0D)) {
/* 1261 */           this.currImage = this.semibreve;
/* 1262 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/* 1263 */           this.isUp = true;
/*      */         }
/*      */       } else {
/* 1266 */         if (paramDouble <= 0.0D) {
/* 1267 */           this.currImage = this.delete;
/* 1268 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 1269 */           this.isUp = false;
/*      */         }
/* 1271 */         if ((paramDouble > 0.0D) && (paramDouble <= 0.25D)) {
/* 1272 */           this.currImage = this.semiquaverDown;
/* 1273 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 1274 */           this.isUp = false;
/*      */         }
/* 1276 */         if ((paramDouble > 0.25D) && (paramDouble <= 0.5D)) {
/* 1277 */           this.currImage = this.quaverDown;
/* 1278 */           this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 1279 */           this.isUp = false;
/*      */         }
/* 1281 */         if ((paramDouble > 0.5D) && (paramDouble <= 0.75D)) {
/* 1282 */           this.currImage = this.quaverDown;
/* 1283 */           this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 1284 */           this.dottedNote = true;
/* 1285 */           this.isUp = false;
/*      */         }
/* 1287 */         if ((paramDouble > 0.75D) && (paramDouble <= 1.0D)) {
/* 1288 */           this.currImage = this.crotchetDown;
/* 1289 */           this.currBeatWidth = this.beatWidth;
/* 1290 */           this.isUp = false;
/*      */         }
/* 1292 */         if ((paramDouble > 1.0D) && (paramDouble <= 1.25D)) {
/* 1293 */           this.currImage = this.crotchetDown;
/* 1294 */           this.currBeatWidth = this.beatWidth;
/* 1295 */           this.requiresMoreThanOneImage = true;
/* 1296 */           this.excessRhythmValue = (paramDouble - 1.0D);
/* 1297 */           this.isUp = false;
/*      */         }
/*      */ 
/* 1300 */         if ((paramDouble > 1.25D) && (paramDouble <= 1.5D)) {
/* 1301 */           this.currImage = this.crotchetDown;
/* 1302 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1303 */           this.dottedNote = true;
/* 1304 */           this.isUp = false;
/*      */         }
/* 1306 */         if ((paramDouble > 1.5D) && (paramDouble <= 1.75D)) {
/* 1307 */           this.currImage = this.crotchetDown;
/* 1308 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 1309 */           this.dottedNote = true;
/* 1310 */           this.requiresMoreThanOneImage = true;
/* 1311 */           this.excessRhythmValue = (paramDouble - 1.5D);
/* 1312 */           this.isUp = false;
/*      */         }
/* 1314 */         if ((paramDouble > 1.75D) && (paramDouble <= 2.0D)) {
/* 1315 */           this.currImage = this.minimDown;
/* 1316 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 1317 */           this.isUp = false;
/*      */         }
/* 1319 */         if ((paramDouble > 2.0D) && (paramDouble <= 2.75D)) {
/* 1320 */           this.currImage = this.minimDown;
/* 1321 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 1322 */           this.requiresMoreThanOneImage = true;
/* 1323 */           this.excessRhythmValue = (paramDouble - 2.0D);
/* 1324 */           this.isUp = false;
/*      */         }
/*      */ 
/* 1327 */         if ((paramDouble > 2.75D) && (paramDouble <= 3.0D)) {
/* 1328 */           this.currImage = this.minimDown;
/* 1329 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1330 */           this.dottedNote = true;
/* 1331 */           this.isUp = false;
/*      */         }
/* 1333 */         if ((paramDouble > 3.0D) && (paramDouble <= 3.75D)) {
/* 1334 */           this.currImage = this.minimDown;
/* 1335 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 1336 */           this.dottedNote = true;
/* 1337 */           this.requiresMoreThanOneImage = true;
/* 1338 */           this.excessRhythmValue = (paramDouble - 3.0D);
/* 1339 */           this.isUp = false;
/*      */         }
/*      */ 
/* 1343 */         if ((paramDouble > 3.75D) && (paramDouble <= 4.0D)) {
/* 1344 */           this.currImage = this.semibreve;
/* 1345 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/* 1346 */           this.isUp = false;
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   class RedFilter
/*      */   {
/*      */     public Image image;
/*      */     public Image redImage;
/*      */ 
/*      */     RedFilter()
/*      */     {
/*      */     }
/*      */ 
/*      */     RedFilter(Image paramImage)
/*      */     {
/*   81 */       this.image = paramImage;
/*   82 */       this.redImage = JGrandStave.this.createImage(new FilteredImageSource(paramImage.getSource(), JGrandStave.this.filter));
/*      */     }
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.JGrandStave
 * JD-Core Version:    0.5.3
 */