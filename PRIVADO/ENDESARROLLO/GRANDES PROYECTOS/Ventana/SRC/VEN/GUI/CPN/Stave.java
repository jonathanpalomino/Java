/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.tools.Mod;
/*     */ 
/*     */ public abstract class Stave extends Panel
/*     */   implements JMC, KeyListener
/*     */ {
/*     */   protected boolean requiresMoreThanOneImage;
/*     */   protected double excessRhythmValue;
/*     */   protected boolean isUp;
/*     */   protected boolean isNote;
/*     */   public Image image;
/*     */   protected Graphics g;
/*     */   protected Image trebleClef;
/*     */   protected Image bassClef;
/*     */   protected Image crotchetUp;
/*     */   protected Image crotchetDown;
/*     */   protected Image quaverDown;
/*     */   protected Image quaverUp;
/*     */   protected Image semiquaverDown;
/*     */   protected Image semiquaverUp;
/*     */   protected Image minimDown;
/*     */   protected Image minimUp;
/*     */   protected Image semibreve;
/*     */   protected Image dot;
/*     */   protected Image semiquaverRest;
/*     */   protected Image quaverRest;
/*     */   protected Image crotchetRest;
/*     */   protected Image minimRest;
/*     */   protected Image semibreveRest;
/*     */   protected Image sharp;
/*     */   protected Image flat;
/*     */   protected Image natural;
/*     */   protected Image one;
/*     */   protected Image two;
/*     */   protected Image three;
/*     */   protected Image four;
/*     */   protected Image five;
/*     */   protected Image six;
/*     */   protected Image seven;
/*     */   protected Image eight;
/*     */   protected Image nine;
/*     */   protected Image delete;
/*     */   protected Image tieOver;
/*     */   protected Image tieUnder;
/*     */   public int staveSpaceHeight;
/*     */   public int rightMargin;
/*     */   public int beatWidth;
/*     */   public int staveWidth;
/*     */   public int imageHeightOffset;
/*     */   public int clefWidth;
/*     */   public int timeSigWidth;
/*     */   public int keySigWidth;
/*     */   public int bPos;
/*     */   protected Phrase phrase;
/*     */   protected Image currImage;
/*     */   protected int currBeatWidth;
/*     */   protected int totalBeatWidth;
/*     */   protected boolean dottedNote;
/*     */   protected int[] notePosOffset;
/*     */   protected double metre;
/*     */   protected int keySignature;
/*     */   protected int[] sharps;
/*     */   protected int[] flats;
/*     */   protected Vector previouslyChromatic;
/*     */   protected int[] lineNotes;
/*     */   public Vector notePositions;
/*     */   protected int maxPitch;
/*     */   protected int minPitch;
/*     */   protected String title;
/*     */   protected boolean barNumbers;
/*     */   protected boolean editable;
/*     */   protected boolean qtOn;
/*     */   protected int panelHeight;
/*     */   protected int staveDelta;
/*     */   protected boolean displayTitle;
/*     */   protected Font font;
/*     */   protected int spacingValue;
/*     */ 
/*     */   public Stave()
/*     */   {
/*  98 */     this(new Phrase(), new ToolkitImages());
/*     */   }
/*     */ 
/*     */   public Stave(Phrase paramPhrase)
/*     */   {
/* 108 */     this(paramPhrase, new ToolkitImages());
/*     */   }
/*     */ 
/*     */   public Stave(Images paramImages)
/*     */   {
/* 119 */     this(new Phrase(), paramImages);
/*     */   }
/*     */ 
/*     */   public Stave(Phrase paramPhrase, Images paramImages)
/*     */   {
/*  50 */     this.requiresMoreThanOneImage = false;
/*     */ 
/*  52 */     this.excessRhythmValue = 0.0D;
/*     */ 
/*  54 */     this.isUp = true;
/*     */ 
/*  56 */     this.isNote = false;
/*     */ 
/*  68 */     this.staveSpaceHeight = 8; this.rightMargin = 20; this.beatWidth = 43; this.staveWidth = (this.beatWidth * 15); this.imageHeightOffset = 28; this.clefWidth = 38; this.timeSigWidth = 5; this.keySigWidth = 5;
/*     */ 
/*  70 */     this.bPos = 28;
/*     */ 
/*  74 */     this.dottedNote = false;
/*  75 */     this.notePosOffset = new int[] { 24, 24, 20, 20, 16, 12, 12, 8, 8, 4, 4, 0 };
/*  76 */     this.metre = 4.0D;
/*  77 */     this.keySignature = 0;
/*  78 */     this.sharps = new int[] { 77, 72, 79, 74, 69, 76, 71 };
/*  79 */     this.flats = new int[] { 71, 76, 69, 74, 67, 72, 65 };
/*  80 */     this.previouslyChromatic = new Vector();
/*  81 */     this.lineNotes = new int[] { 0, 1, 4, 7, 8, 11, 14, 15, 17, 18, 21, 22 };
/*  82 */     this.notePositions = new Vector();
/*  83 */     this.maxPitch = 127; this.minPitch = 0;
/*     */ 
/*  85 */     this.barNumbers = false; this.editable = true; this.qtOn = false;
/*  86 */     this.panelHeight = 110; this.staveDelta = 0;
/*  87 */     this.displayTitle = false;
/*  88 */     this.font = new Font("Helvetica", 0, 10);
/*  89 */     this.spacingValue = 70;
/*     */ 
/* 132 */     this.title = paramPhrase.getTitle();
/* 133 */     this.phrase = addRequiredRests(paramPhrase);
/*     */ 
/* 135 */     setBackground(Color.getHSBColor(0.14F, 0.09F, 1.0F));
/*     */ 
/* 137 */     setSize(this.beatWidth * this.spacingValue, this.panelHeight);
/* 138 */     if (getSize().width < (int)(this.phrase.getEndTime() * this.beatWidth * 1.5D)) {
/* 139 */       setSize((int)(this.phrase.getEndTime() * this.beatWidth * 1.5D), this.panelHeight);
/*     */     }
/*     */ 
/* 149 */     StaveActionHandler localStaveActionHandler = new StaveActionHandler(this);
/* 150 */     addMouseListener(localStaveActionHandler);
/* 151 */     addMouseMotionListener(localStaveActionHandler);
/*     */ 
/* 155 */     this.trebleClef = paramImages.getTrebleClef();
/* 156 */     this.bassClef = paramImages.getBassClef();
/* 157 */     this.crotchetDown = paramImages.getCrotchetDown();
/* 158 */     this.crotchetUp = paramImages.getCrotchetUp();
/* 159 */     this.quaverDown = paramImages.getQuaverDown();
/* 160 */     this.quaverUp = paramImages.getQuaverUp();
/* 161 */     this.semiquaverDown = paramImages.getSemiquaverDown();
/* 162 */     this.semiquaverUp = paramImages.getSemiquaverUp();
/* 163 */     this.minimDown = paramImages.getMinimDown();
/* 164 */     this.minimUp = paramImages.getMinimUp();
/* 165 */     this.semibreve = paramImages.getSemibreve();
/* 166 */     this.dot = paramImages.getDot();
/* 167 */     this.semiquaverRest = paramImages.getSemiquaverRest();
/* 168 */     this.quaverRest = paramImages.getQuaverRest();
/* 169 */     this.crotchetRest = paramImages.getCrotchetRest();
/* 170 */     this.minimRest = paramImages.getMinimRest();
/* 171 */     this.semibreveRest = paramImages.getSemibreveRest();
/* 172 */     this.sharp = paramImages.getSharp();
/* 173 */     this.flat = paramImages.getFlat();
/* 174 */     this.natural = paramImages.getNatural();
/* 175 */     this.one = paramImages.getOne();
/* 176 */     this.two = paramImages.getTwo();
/* 177 */     this.three = paramImages.getThree();
/* 178 */     this.four = paramImages.getFour();
/* 179 */     this.five = paramImages.getFive();
/* 180 */     this.six = paramImages.getSix();
/* 181 */     this.seven = paramImages.getSeven();
/* 182 */     this.eight = paramImages.getEight();
/* 183 */     this.nine = paramImages.getNine();
/* 184 */     this.delete = paramImages.getDelete();
/* 185 */     this.tieOver = paramImages.getTieOver();
/* 186 */     this.tieUnder = paramImages.getTieUnder();
/*     */   }
/*     */ 
/*     */   public Phrase addRequiredRests(Phrase paramPhrase)
/*     */   {
/* 195 */     if (paramPhrase.getStartTime() > 0.0D) {
/* 196 */       Phrase localPhrase = new Phrase(0.0D);
/* 197 */       double d = paramPhrase.getStartTime();
/* 198 */       while (d >= 4.0D) {
/* 199 */         localPhrase.addNote(-2147483648, 4.0D);
/* 200 */         d -= 4.0D;
/*     */       }
/* 202 */       while (d >= 1.0D) {
/* 203 */         localPhrase.addNote(-2147483648, 1.0D);
/* 204 */         d -= 1.0D;
/*     */       }
/* 206 */       localPhrase.addNote(-2147483648, d);
/* 207 */       Mod.append(localPhrase, paramPhrase);
/* 208 */       paramPhrase = localPhrase;
/*     */     }
/* 210 */     return paramPhrase;
/*     */   }
/*     */ 
/*     */   public void setPhrase(Phrase paramPhrase)
/*     */   {
/* 218 */     this.phrase = addRequiredRests(paramPhrase);
/* 219 */     this.previouslyChromatic.removeAllElements();
/*     */ 
/* 221 */     repaint();
/*     */   }
/*     */ 
/*     */   public Phrase getPhrase()
/*     */   {
/* 228 */     return this.phrase;
/*     */   }
/*     */ 
/*     */   public void setTitle(String paramString)
/*     */   {
/* 236 */     this.title = paramString;
/* 237 */     if (this.phrase == null) return; this.phrase.setTitle(paramString);
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 245 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void removeTitle()
/*     */   {
/* 251 */     this.title = null;
/*     */   }
/*     */ 
/*     */   public void setDisplayTitle(boolean paramBoolean)
/*     */   {
/* 259 */     this.displayTitle = paramBoolean;
/* 260 */     repaint();
/*     */   }
/*     */ 
/*     */   public boolean getDisplayTitle()
/*     */   {
/* 268 */     return this.displayTitle;
/*     */   }
/*     */ 
/*     */   public int getPanelHeight()
/*     */   {
/* 275 */     return this.panelHeight;
/*     */   }
/*     */ 
/*     */   public void setMetre(double paramDouble)
/*     */   {
/* 291 */     this.metre = paramDouble;
/*     */   }
/*     */ 
/*     */   public double getMetre()
/*     */   {
/* 298 */     return this.metre;
/*     */   }
/*     */ 
/*     */   public int getMajorKey()
/*     */   {
/* 306 */     int[] arrayOfInt = { 11, 6, 1, 8, 3, 10, 5, 0, 7, 2, 9, 4, 11, 6, 1 };
/* 307 */     return arrayOfInt[(this.keySignature + 7)];
/*     */   }
/*     */ 
/*     */   public void setKeySignature(int paramInt)
/*     */   {
/* 316 */     this.keySignature = paramInt;
/*     */   }
/*     */ 
/*     */   public int getKeySignature()
/*     */   {
/* 323 */     return this.keySignature;
/*     */   }
/*     */ 
/*     */   public void setBarNumbers(boolean paramBoolean)
/*     */   {
/* 331 */     this.barNumbers = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void setEditable(boolean paramBoolean)
/*     */   {
/* 339 */     this.editable = paramBoolean;
/*     */   }
/*     */ 
/*     */   public int getMinPitch()
/*     */   {
/* 346 */     return this.minPitch;
/*     */   }
/*     */ 
/*     */   public void setMinPitch(int paramInt)
/*     */   {
/* 353 */     this.minPitch = paramInt;
/*     */   }
/*     */ 
/*     */   public int getMaxPitch()
/*     */   {
/* 360 */     return this.maxPitch;
/*     */   }
/*     */ 
/*     */   public void setMaxPitch(int paramInt)
/*     */   {
/* 367 */     this.maxPitch = paramInt;
/*     */   }
/*     */ 
/*     */   public int getTotalBeatWidth()
/*     */   {
/* 374 */     return this.totalBeatWidth;
/*     */   }
/*     */ 
/*     */   public void setTotalBeatWidth(int paramInt)
/*     */   {
/* 381 */     this.totalBeatWidth = paramInt;
/*     */   }
/*     */ 
/*     */   public boolean getBarNumbers()
/*     */   {
/* 388 */     return this.barNumbers;
/*     */   }
/*     */ 
/*     */   public Dimension getPreferredSize()
/*     */   {
/* 394 */     return new Dimension(getSize().width, getSize().height);
/*     */   }
/*     */ 
/*     */   public boolean getQtOn()
/*     */   {
/* 401 */     return this.qtOn;
/*     */   }
/*     */ 
/*     */   public void setQtOn(boolean paramBoolean)
/*     */   {
/* 408 */     this.qtOn = paramBoolean;
/*     */   }
/*     */ 
/*     */   public void updateChange()
/*     */   {
/*     */   }
/*     */ 
/*     */   public void update(Graphics paramGraphics)
/*     */   {
/* 420 */     paint(paramGraphics);
/*     */   }
/*     */ 
/*     */   public void paint(Graphics paramGraphics)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void deleteLastNote()
/*     */   {
/* 431 */     if (this.phrase.size() > 0) {
/* 432 */       this.phrase.removeNote(this.phrase.size() - 1);
/* 433 */       repaint();
/* 434 */       updateChange();
/*     */     }
/*     */   }
/*     */ 
/*     */   protected void chooseImage(int paramInt1, double paramDouble, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 443 */     if (paramInt1 == -2147483648) {
/* 444 */       this.isNote = false;
/* 445 */       if (paramDouble <= 0.0D) {
/* 446 */         this.currImage = this.delete;
/* 447 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 448 */       } else if (paramDouble <= 0.2501D) {
/* 449 */         this.currImage = this.semiquaverRest;
/* 450 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 451 */       } else if (paramDouble <= 0.501D) {
/* 452 */         this.currImage = this.quaverRest;
/* 453 */         this.currBeatWidth = (int)(this.beatWidth * 0.6666666666666666D);
/* 454 */       } else if (paramDouble <= 0.7501D) {
/* 455 */         this.currImage = this.quaverRest;
/* 456 */         this.currBeatWidth = (int)(this.beatWidth * 0.6666666666666666D);
/* 457 */         this.dottedNote = true;
/* 458 */       } else if (paramDouble <= 1.001D) {
/* 459 */         this.currImage = this.crotchetRest;
/* 460 */         this.currBeatWidth = this.beatWidth;
/* 461 */       } else if (paramDouble <= 1.2501D) {
/* 462 */         this.currImage = this.crotchetRest;
/* 463 */         this.currBeatWidth = this.beatWidth;
/* 464 */         this.requiresMoreThanOneImage = true;
/* 465 */         this.excessRhythmValue = (paramDouble - 1.0D);
/* 466 */       } else if (paramDouble <= 1.501D) {
/* 467 */         this.currImage = this.crotchetRest;
/* 468 */         this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 469 */         this.dottedNote = true;
/* 470 */       } else if (paramDouble <= 1.7501D) {
/* 471 */         this.currImage = this.crotchetRest;
/* 472 */         this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 473 */         this.dottedNote = true;
/* 474 */         this.requiresMoreThanOneImage = true;
/* 475 */         this.excessRhythmValue = (paramDouble - 1.5D);
/* 476 */       } else if (paramDouble <= 2.001D) {
/* 477 */         this.currImage = this.minimRest;
/* 478 */         this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 479 */       } else if (paramDouble <= 2.7501D) {
/* 480 */         this.currImage = this.minimRest;
/* 481 */         this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 482 */         this.requiresMoreThanOneImage = true;
/* 483 */         this.excessRhythmValue = (paramDouble - 2.0D);
/* 484 */       } else if (paramDouble <= 3.001D) {
/* 485 */         this.currImage = this.minimRest;
/* 486 */         this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 487 */         this.dottedNote = true;
/* 488 */       } else if (paramDouble <= 3.7501D) {
/* 489 */         this.currImage = this.minimRest;
/* 490 */         this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 491 */         this.dottedNote = true;
/* 492 */         this.requiresMoreThanOneImage = true;
/* 493 */         this.excessRhythmValue = (paramDouble - 3.0D);
/* 494 */       } else if (paramDouble <= 4.001D) {
/* 495 */         this.currImage = this.semibreveRest;
/* 496 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/*     */       } else {
/* 498 */         this.currImage = this.semibreveRest;
/* 499 */         this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 500 */         this.requiresMoreThanOneImage = true;
/* 501 */         this.excessRhythmValue = (paramDouble - 4.0D);
/*     */       }
/*     */     } else {
/* 504 */       this.isNote = true;
/* 505 */       if (((paramInt1 < paramInt2) && (paramInt1 >= paramInt3)) || (paramInt1 < paramInt4))
/*     */       {
/* 507 */         this.isUp = true;
/* 508 */         if (paramDouble <= 0.001D) {
/* 509 */           this.currImage = this.delete;
/* 510 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 511 */         } else if (paramDouble <= 0.2501D) {
/* 512 */           this.currImage = this.semiquaverUp;
/* 513 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 514 */         } else if (paramDouble <= 0.501D) {
/* 515 */           this.currImage = this.quaverUp;
/* 516 */           this.currBeatWidth = (int)(this.beatWidth * 0.6666666666666666D);
/* 517 */         } else if (paramDouble <= 0.7501D) {
/* 518 */           this.currImage = this.quaverUp;
/* 519 */           this.currBeatWidth = (int)(this.beatWidth * 0.67D);
/* 520 */           this.dottedNote = true;
/* 521 */         } else if (paramDouble <= 1.001D) {
/* 522 */           this.currImage = this.crotchetUp;
/* 523 */           this.currBeatWidth = this.beatWidth;
/* 524 */         } else if (paramDouble <= 1.2501D) {
/* 525 */           this.currImage = this.crotchetUp;
/* 526 */           this.currBeatWidth = this.beatWidth;
/* 527 */           this.requiresMoreThanOneImage = true;
/* 528 */           this.excessRhythmValue = (paramDouble - 1.0D);
/* 529 */         } else if (paramDouble <= 1.501D) {
/* 530 */           this.currImage = this.crotchetUp;
/* 531 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 532 */           this.dottedNote = true;
/* 533 */         } else if (paramDouble <= 1.7501D) {
/* 534 */           this.currImage = this.crotchetUp;
/* 535 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 536 */           this.dottedNote = true;
/* 537 */           this.requiresMoreThanOneImage = true;
/* 538 */           this.excessRhythmValue = (paramDouble - 1.5D);
/* 539 */         } else if (paramDouble <= 2.001D) {
/* 540 */           this.currImage = this.minimUp;
/* 541 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 542 */         } else if (paramDouble <= 2.7501D) {
/* 543 */           this.currImage = this.minimUp;
/* 544 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 545 */           this.requiresMoreThanOneImage = true;
/* 546 */           this.excessRhythmValue = (paramDouble - 2.0D);
/* 547 */         } else if (paramDouble <= 3.001D) {
/* 548 */           this.currImage = this.minimUp;
/* 549 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 550 */           this.dottedNote = true;
/* 551 */         } else if (paramDouble <= 3.7501D) {
/* 552 */           this.currImage = this.minimUp;
/* 553 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 554 */           this.dottedNote = true;
/* 555 */           this.requiresMoreThanOneImage = true;
/* 556 */           this.excessRhythmValue = (paramDouble - 3.0D);
/* 557 */         } else if (paramDouble <= 4.001D) {
/* 558 */           this.currImage = this.semibreve;
/* 559 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/*     */         } else {
/* 561 */           this.currImage = this.semibreve;
/* 562 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/* 563 */           this.requiresMoreThanOneImage = true;
/* 564 */           this.excessRhythmValue = (paramDouble - 4.0D);
/*     */         }
/*     */       } else {
/* 567 */         this.isUp = false;
/* 568 */         if (paramDouble <= 0.001D) {
/* 569 */           this.currImage = this.delete;
/* 570 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 571 */         } else if (paramDouble <= 0.2501D) {
/* 572 */           this.currImage = this.semiquaverDown;
/* 573 */           this.currBeatWidth = (int)(this.beatWidth * 0.5D);
/* 574 */         } else if (paramDouble <= 0.501D) {
/* 575 */           this.currImage = this.quaverDown;
/* 576 */           this.currBeatWidth = (int)(this.beatWidth * 0.6666666666666666D);
/* 577 */         } else if (paramDouble <= 0.7501D) {
/* 578 */           this.currImage = this.quaverDown;
/* 579 */           this.currBeatWidth = (int)(this.beatWidth * 0.6666666666666666D);
/* 580 */           this.dottedNote = true;
/* 581 */         } else if (paramDouble <= 1.001D) {
/* 582 */           this.currImage = this.crotchetDown;
/* 583 */           this.currBeatWidth = this.beatWidth;
/* 584 */         } else if (paramDouble <= 1.2501D) {
/* 585 */           this.currImage = this.crotchetDown;
/* 586 */           this.currBeatWidth = this.beatWidth;
/* 587 */           this.requiresMoreThanOneImage = true;
/* 588 */           this.excessRhythmValue = (paramDouble - 1.0D);
/* 589 */         } else if (paramDouble <= 1.501D) {
/* 590 */           this.currImage = this.crotchetDown;
/* 591 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 592 */           this.dottedNote = true;
/* 593 */         } else if (paramDouble <= 1.7501D) {
/* 594 */           this.currImage = this.crotchetDown;
/* 595 */           this.currBeatWidth = (int)(this.beatWidth * 1.5D);
/* 596 */           this.dottedNote = true;
/* 597 */           this.requiresMoreThanOneImage = true;
/* 598 */           this.excessRhythmValue = (paramDouble - 1.5D);
/* 599 */         } else if (paramDouble <= 2.001D) {
/* 600 */           this.currImage = this.minimDown;
/* 601 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 602 */         } else if (paramDouble <= 2.7501D) {
/* 603 */           this.currImage = this.minimDown;
/* 604 */           this.currBeatWidth = (int)(this.beatWidth * 1.7D);
/* 605 */           this.requiresMoreThanOneImage = true;
/* 606 */           this.excessRhythmValue = (paramDouble - 2.0D);
/* 607 */         } else if (paramDouble <= 3.001D) {
/* 608 */           this.currImage = this.minimDown;
/* 609 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 610 */           this.dottedNote = true;
/* 611 */         } else if (paramDouble <= 3.7501D) {
/* 612 */           this.currImage = this.minimDown;
/* 613 */           this.currBeatWidth = (int)(this.beatWidth * 1.9D);
/* 614 */           this.dottedNote = true;
/* 615 */           this.requiresMoreThanOneImage = true;
/* 616 */           this.excessRhythmValue = (paramDouble - 3.0D);
/* 617 */         } else if (paramDouble <= 4.001D) {
/* 618 */           this.currImage = this.semibreve;
/* 619 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/*     */         } else {
/* 621 */           this.currImage = this.semibreve;
/* 622 */           this.currBeatWidth = (int)(this.beatWidth * 2.25D);
/* 623 */           this.requiresMoreThanOneImage = true;
/* 624 */           this.excessRhythmValue = (paramDouble - 4.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {
/* 637 */     System.out.println(paramKeyEvent.getKeyChar());
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.Stave
 * JD-Core Version:    0.5.3
 */