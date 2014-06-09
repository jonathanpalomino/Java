/*     */ package jm.music.tools.ga;
/*     */ 
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Scrollbar;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.io.PrintStream;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.tools.NoteListException;
/*     */ import jm.music.tools.PhraseAnalysis;
/*     */ import jm.music.tools.QuantisationException;
/*     */ 
/*     */ public class NormalDistributionFE extends FitnessEvaluater
/*     */ {
/*  46 */   protected static String label = "Normal Distribution Fitness Evaluater";
/*     */   protected Panel panel;
/*  50 */   private double[] weighting = { 1.0D, 1.0D, 1.0D, 0.1D, 1.0D, 1.0D, 0.5D, 0.5D, 0.5D, 1.0D, 0.5D, 0.5D, 1.0D, 0.5D, 1.0D, 0.1D, 1.0D, 1.0D, 1.0D, 0.5D, 0.1D, 0.1D, 0.1D };
/*     */   protected Label F1Label;
/*     */   protected Label F2Label;
/*     */   protected Label F3Label;
/*     */   protected Label F4Label;
/*     */   protected Label F5Label;
/*     */   protected Label F6Label;
/*     */   protected Label F7Label;
/*     */   protected Label F8Label;
/*     */   protected Label F9Label;
/*     */   protected Label F10Label;
/*     */   protected Label F11Label;
/*     */   protected Label F12Label;
/*     */   protected Label F13Label;
/*     */   protected Label F14Label;
/*     */   protected Label F15Label;
/*     */   protected Label F16Label;
/*     */   protected Label F17Label;
/*     */   protected Label F18Label;
/*     */   protected Label F19Label;
/*     */   protected Label F20Label;
/*     */   protected Label F21Label;
/*     */   protected Label F22Label;
/*     */   protected Label F23Label;
/* 562 */   private double[] mean = { 0.307D, 0.308D, 0.021D, 0.669D, 0.021D, 0.079D, 0.652D, 0.545D, 0.383D, 0.13D, 0.5620000000000001D, 0.411D, 0.495D, 0.601D, 0.013D, 0.252D, 0.066D, 0.183D, 0.112D, 0.538D, 0.439D, 0.523D, 0.346D };
/*     */ 
/* 586 */   private double[] standardDeviation = { 0.115D, 0.129D, 0.038D, 0.318D, 0.044D, 0.137D, 0.148D, 0.166D, 0.211D, 0.13D, 0.21D, 0.139D, 0.059D, 0.218D, 0.047D, 0.399D, 0.105D, 0.146D, 0.125D, 0.227D, 0.246D, 0.261D, 0.275D };
/*     */   public static final double duration = 0.25D;
/*     */   public static final int tonic = 60;
/* 614 */   public static final int[] scale = PhraseAnalysis.MAJOR_SCALE;
/*     */ 
/*     */   public NormalDistributionFE()
/*     */   {
/* 121 */     this.panel = new Panel();
/* 122 */     this.panel.setLayout(new GridLayout(23, 3));
/* 123 */     this.F1Label = new Label(Integer.toString((int)(this.weighting[0] * 100.0D)));
/*     */ 
/* 125 */     this.panel.add(new Label("Note Density", 2));
/* 126 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[0] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 140 */     this.panel.add(this.F1Label);
/*     */ 
/* 142 */     this.F2Label = new Label(Integer.toString((int)(this.weighting[1] * 100.0D)));
/*     */ 
/* 144 */     this.panel.add(new Label("Pitch Variety", 2));
/* 145 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[1] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 159 */     this.panel.add(this.F2Label);
/*     */ 
/* 161 */     this.F3Label = new Label(Integer.toString((int)(this.weighting[1] * 100.0D)));
/*     */ 
/* 163 */     this.panel.add(new Label("Rhythmic Variety", 2));
/* 164 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[2] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 178 */     this.panel.add(this.F3Label);
/*     */ 
/* 180 */     this.F4Label = new Label(Integer.toString((int)(this.weighting[3] * 100.0D)));
/*     */ 
/* 182 */     this.panel.add(new Label("Climax Strength", 2));
/* 183 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[3] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 197 */     this.panel.add(this.F4Label);
/*     */ 
/* 199 */     this.F5Label = new Label(Integer.toString((int)(this.weighting[4] * 100.0D)));
/*     */ 
/* 201 */     this.panel.add(new Label("Rest Density", 2));
/* 202 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[4] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 216 */     this.panel.add(this.F5Label);
/*     */ 
/* 218 */     this.F6Label = new Label(Integer.toString((int)(this.weighting[5] * 100.0D)));
/*     */ 
/* 220 */     this.panel.add(new Label("Tonal Deviation", 2));
/* 221 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[5] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 235 */     this.panel.add(this.F6Label);
/*     */ 
/* 237 */     this.F7Label = new Label(Integer.toString((int)(this.weighting[6] * 100.0D)));
/*     */ 
/* 239 */     this.panel.add(new Label("Key Centeredness", 2));
/* 240 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[6] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 254 */     this.panel.add(this.F7Label);
/*     */ 
/* 256 */     this.F8Label = new Label(Integer.toString((int)(this.weighting[7] * 100.0D)));
/*     */ 
/* 258 */     this.panel.add(new Label("Pitch Range", 2));
/* 259 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[7] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 273 */     this.panel.add(this.F8Label);
/*     */ 
/* 275 */     this.F9Label = new Label(Integer.toString((int)(this.weighting[8] * 100.0D)));
/*     */ 
/* 277 */     this.panel.add(new Label("Rhythm Range", 2));
/* 278 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[8] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 292 */     this.panel.add(this.F9Label);
/*     */ 
/* 294 */     this.F10Label = new Label(Integer.toString((int)(this.weighting[9] * 100.0D)));
/*     */ 
/* 296 */     this.panel.add(new Label("Repeated Pitch Density", 2));
/* 297 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[9] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 311 */     this.panel.add(this.F10Label);
/*     */ 
/* 313 */     this.F11Label = new Label(Integer.toString((int)(this.weighting[10] * 100.0D)));
/*     */ 
/* 315 */     this.panel.add(new Label("Repeated Rhythm Density", 2));
/* 316 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[10] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 330 */     this.panel.add(this.F11Label);
/*     */ 
/* 332 */     this.F12Label = new Label(Integer.toString((int)(this.weighting[11] * 100.0D)));
/*     */ 
/* 334 */     this.panel.add(new Label("Melodic Direction Stability", 2));
/* 335 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[11] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 349 */     this.panel.add(this.F12Label);
/*     */ 
/* 351 */     this.F13Label = new Label(Integer.toString((int)(this.weighting[12] * 100.0D)));
/*     */ 
/* 353 */     this.panel.add(new Label("Overall Pitch Direction", 2));
/* 354 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[12] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 368 */     this.panel.add(this.F13Label);
/*     */ 
/* 370 */     this.F14Label = new Label(Integer.toString((int)(this.weighting[13] * 100.0D)));
/*     */ 
/* 372 */     this.panel.add(new Label("Pitch Movement", 2));
/* 373 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[13] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 387 */     this.panel.add(this.F14Label);
/*     */ 
/* 389 */     this.F15Label = new Label(Integer.toString((int)(this.weighting[14] * 100.0D)));
/*     */ 
/* 391 */     this.panel.add(new Label("Dissonance", 2));
/* 392 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[14] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 406 */     this.panel.add(this.F15Label);
/*     */ 
/* 408 */     this.F16Label = new Label(Integer.toString((int)(this.weighting[15] * 100.0D)));
/*     */ 
/* 410 */     this.panel.add(new Label("Leap Compensation", 2));
/* 411 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[15] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 425 */     this.panel.add(this.F16Label);
/*     */ 
/* 427 */     this.F17Label = new Label(Integer.toString((int)(this.weighting[16] * 100.0D)));
/*     */ 
/* 429 */     this.panel.add(new Label("Syncopation", 2));
/* 430 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[16] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 444 */     this.panel.add(this.F17Label);
/*     */ 
/* 446 */     this.F18Label = new Label(Integer.toString((int)(this.weighting[17] * 100.0D)));
/*     */ 
/* 448 */     this.panel.add(new Label("Repeated Pitch Patterns of 3", 2));
/* 449 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[17] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 463 */     this.panel.add(this.F18Label);
/*     */ 
/* 465 */     this.F19Label = new Label(Integer.toString((int)(this.weighting[18] * 100.0D)));
/*     */ 
/* 467 */     this.panel.add(new Label("Repeated Pitch Patterns of 4", 2));
/* 468 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[18] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 482 */     this.panel.add(this.F19Label);
/*     */ 
/* 484 */     this.F20Label = new Label(Integer.toString((int)(this.weighting[19] * 100.0D)));
/*     */ 
/* 486 */     this.panel.add(new Label("Repeated Rhythm Patterns of 3", 2));
/* 487 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[19] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 501 */     this.panel.add(this.F20Label);
/*     */ 
/* 503 */     this.F21Label = new Label(Integer.toString((int)(this.weighting[20] * 100.0D)));
/*     */ 
/* 505 */     this.panel.add(new Label("Repeated Rhythm Patterns of 4", 2));
/* 506 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[20] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 520 */     this.panel.add(this.F21Label);
/*     */ 
/* 522 */     this.F22Label = new Label(Integer.toString((int)(this.weighting[21] * 100.0D)));
/*     */ 
/* 524 */     this.panel.add(new Label("Climax Position", 2));
/* 525 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[21] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 539 */     this.panel.add(this.F22Label);
/*     */ 
/* 541 */     this.F23Label = new Label(Integer.toString((int)(this.weighting[22] * 100.0D)));
/*     */ 
/* 543 */     this.panel.add(new Label("Climax Tonality", 2));
/* 544 */     this.panel.add(new Scrollbar(0, (int)(this.weighting[22] * 100.0D), 1, 0, 100)
/*     */     {
/*     */     });
/* 558 */     this.panel.add(this.F23Label);
/*     */   }
/*     */ 
/*     */   public double[] evaluate(Phrase[] paramArrayOfPhrase)
/*     */   {
/* 618 */     double[] arrayOfDouble = new double[paramArrayOfPhrase.length];
/* 619 */     for (int i = 0; i < paramArrayOfPhrase.length; ++i) {
/* 620 */       double d = 0.0D;
/* 621 */       for (int j = 0; j < this.mean.length; ++j) {
/* 622 */         d += calculateFitness(getValue2(j, paramArrayOfPhrase[i]), this.mean[j], this.standardDeviation[j], this.weighting[j]);
/*     */       }
/*     */ 
/* 626 */       arrayOfDouble[i] = (1.0D / (d / (this.weighting.length - 1) + 1.0D));
/*     */     }
/* 628 */     return arrayOfDouble;
/*     */   }
/*     */ 
/*     */   private double calculateFitness(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4)
/*     */   {
/* 634 */     return (Math.abs((paramDouble1 - paramDouble2) / paramDouble3) * paramDouble4);
/*     */   }
/*     */ 
/*     */   private double getValue2(int paramInt, Phrase paramPhrase) {
/*     */     try {
/* 639 */       switch (paramInt) {
/*     */       case 0:
/*     */       default:
/* 642 */         return PhraseAnalysis.noteDensity(paramPhrase, 0.25D);
/*     */       case 1:
/* 644 */         return PhraseAnalysis.pitchVariety(paramPhrase);
/*     */       case 2:
/* 646 */         return PhraseAnalysis.rhythmicVariety(paramPhrase);
/*     */       case 3:
/* 648 */         return PhraseAnalysis.climaxStrength(paramPhrase);
/*     */       case 4:
/* 650 */         return PhraseAnalysis.restDensity(paramPhrase, 0.25D);
/*     */       case 5:
/* 652 */         return PhraseAnalysis.tonalDeviation(paramPhrase, 0.25D, 60, scale);
/*     */       case 6:
/* 655 */         return PhraseAnalysis.keyCenteredness(paramPhrase, 0.25D, 60);
/*     */       case 7:
/* 657 */         return PhraseAnalysis.pitchRangePerSpan(paramPhrase);
/*     */       case 8:
/* 659 */         return PhraseAnalysis.rhythmRangePerSpan(paramPhrase);
/*     */       case 9:
/* 661 */         return PhraseAnalysis.repeatedPitchDensity(paramPhrase);
/*     */       case 10:
/* 663 */         return PhraseAnalysis.repeatedRhythmicValueDensity(paramPhrase);
/*     */       case 11:
/* 665 */         return PhraseAnalysis.melodicDirectionStability(paramPhrase);
/*     */       case 12:
/* 667 */         return PhraseAnalysis.overallPitchDirection(paramPhrase);
/*     */       case 13:
/* 669 */         return PhraseAnalysis.movementByStep(paramPhrase, 60, scale);
/*     */       case 14:
/* 671 */         return PhraseAnalysis.dissonance(paramPhrase);
/*     */       case 15:
/* 673 */         return PhraseAnalysis.leapCompensation(paramPhrase);
/*     */       case 16:
/* 675 */         return PhraseAnalysis.syncopation(paramPhrase);
/*     */       case 17:
/* 677 */         return PhraseAnalysis.repeatedPitchPatterns(paramPhrase, 3);
/*     */       case 18:
/* 679 */         return PhraseAnalysis.repeatedPitchPatterns(paramPhrase, 4);
/*     */       case 19:
/* 681 */         return PhraseAnalysis.repeatedRhythmPatterns(paramPhrase, 3);
/*     */       case 20:
/* 683 */         return PhraseAnalysis.repeatedRhythmPatterns(paramPhrase, 4);
/*     */       case 21:
/* 685 */         return PhraseAnalysis.climaxPosition(paramPhrase);
/*     */       case 22:
/*     */       }
/* 687 */       return PhraseAnalysis.climaxTonality(paramPhrase, 60, scale);
/*     */     }
/*     */     catch (NoteListException localNoteListException) {
/* 690 */       localNoteListException.printStackTrace();
/* 691 */       System.err.println(localNoteListException);
/* 692 */       System.exit(-1);
/*     */     } catch (QuantisationException localQuantisationException) {
/* 694 */       localQuantisationException.printStackTrace();
/* 695 */       System.err.println(localQuantisationException);
/* 696 */       System.exit(-1);
/*     */     }
/*     */ 
/* 702 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public Panel getPanel() {
/* 706 */     return this.panel;
/*     */   }
/*     */ 
/*     */   public String getLabel() {
/* 710 */     return label;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.NormalDistributionFE
 * JD-Core Version:    0.5.3
 */