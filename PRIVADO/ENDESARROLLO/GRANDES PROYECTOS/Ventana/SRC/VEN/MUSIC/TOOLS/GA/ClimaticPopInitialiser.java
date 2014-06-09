/*     */ package jm.music.tools.ga;
/*     */ 
/*     */ import java.awt.FlowLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Scrollbar;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.tools.PhraseAnalysis;
/*     */ 
/*     */ public class ClimaticPopInitialiser extends PopulationInitialiser
/*     */ {
/*  43 */   protected static String label = "Climatic Population Initialiser";
/*     */   public final int TONIC = 60;
/*     */   public static final int MIN_POPULATION_SIZE = 2;
/*     */   public static final int MAX_POPULATION_SIZE = 100;
/*     */   public static final int DEFAULT_POPULATION_SIZE = 50;
/*     */   public static final double CLIMAX_AVERAGE = 0.523D;
/*     */   public static final double CLIMAX_ST_DEV = 0.261D;
/*     */   protected Panel panel;
/*     */   protected int populationSize;
/*     */   protected Label populationLabel;
/*     */   protected boolean modifyAll;
/*     */ 
/*     */   public ClimaticPopInitialiser()
/*     */   {
/*  66 */     this(50);
/*     */   }
/*     */ 
/*     */   public ClimaticPopInitialiser(int paramInt)
/*     */   {
/*  45 */     this.TONIC = 60;
/*     */ 
/*  63 */     this.modifyAll = false;
/*     */ 
/*  70 */     this.populationSize = paramInt;
/*  71 */     this.panel = new Panel();
/*  72 */     this.panel.setLayout(new FlowLayout(0, 0, 0));
/*  73 */     this.populationLabel = new Label(Integer.toString(this.populationSize));
/*  74 */     this.panel.add(new Label("Population Size", 2));
/*  75 */     this.panel.add(new Scrollbar(0, this.populationSize, 1, 2, 100)
/*     */     {
/*     */     });
/*  89 */     this.panel.add(this.populationLabel);
/*     */   }
/*     */ 
/*     */   public Phrase[] initPopulation(Phrase paramPhrase, int paramInt) {
/*  93 */     Phrase localPhrase = completeFinalBeat(paramPhrase, paramInt);
/*     */     int i;
/*  95 */     if (this.modifyAll)
/*  96 */       i = 0;
/*     */     else i = localPhrase.size();
/*  98 */     double[][] arrayOfDouble = generateBeatRhythmArray(localPhrase, paramInt);
/*  99 */     int[] arrayOfInt = generateIntervalArray(localPhrase);
/*     */ 
/* 102 */     Phrase[] arrayOfPhrase = new Phrase[this.populationSize];
/* 103 */     for (int j = 0; j < this.populationSize; ++j) {
/* 104 */       arrayOfPhrase[j] = localPhrase.copy();
/*     */ 
/* 108 */       int l = 0;
/*     */       Note localNote;
/*     */       int k;
/*     */       int i3;
/* 112 */       if (isClimaxAccepted(localPhrase, paramInt)) {
/* 113 */         l = findClimax(localPhrase);
/* 114 */         localNote = new Note(60, paramInt);
/* 115 */         k = 7 * paramInt;
/*     */       } else {
/* 117 */         i1 = 127;
/* 118 */         for (i2 = 0; i2 < localPhrase.size(); ++i2) {
/* 119 */           i3 = localPhrase.getNote(i2).getPitch();
/* 120 */           if ((i3 != -2147483648) && (i3 < i1)) {
/* 121 */             i1 = i3;
/*     */           }
/*     */         }
/* 124 */         localNote = generateClimax(i1);
/* 125 */         l = localNote.getPitch();
/* 126 */         k = 4 * paramInt;
/*     */       }
/*     */ 
/* 133 */       int i1 = -1;
/* 134 */       for (int i2 = 0; i2 < localPhrase.size(); ++i2) {
/* 135 */         i3 = localPhrase.getNote(i2).getPitch();
/* 136 */         if (i3 != -2147483648) {
/* 137 */           i1 = i3 - 12;
/* 138 */           break;
/*     */         }
/*     */       }
/* 141 */       if (i1 < 53) {
/* 142 */         i1 = 53;
/*     */       }
/*     */ 
/* 147 */       extend(arrayOfPhrase[j], localNote, k, arrayOfDouble, arrayOfInt, l, paramInt, i1);
/*     */ 
/* 149 */       addAppropriateTarget(arrayOfPhrase[j], localNote);
/*     */ 
/* 154 */       if (arrayOfPhrase[j].getEndTime() != 8 * paramInt) {
/* 155 */         localNote = new Note(60, paramInt);
/* 156 */         k = 7 * paramInt;
/* 157 */         extend(arrayOfPhrase[j], localNote, k, arrayOfDouble, arrayOfInt, l, paramInt, i1);
/*     */ 
/* 162 */         i2 = arrayOfPhrase[j].size() - 1;
/* 163 */         i3 = arrayOfPhrase[j].getNote(i2).getPitch();
/* 164 */         while (i3 == -2147483648) {
/* 165 */           i3 = arrayOfPhrase[j].getNote(--i2).getPitch();
/*     */         }
/*     */ 
/* 169 */         int i4 = localNote.getPitch();
/* 170 */         if (i3 < i4) {
/* 171 */           if (i4 - i3 > 6)
/* 172 */             localNote.setPitch(i4 - 12);
/*     */         }
/* 174 */         else if ((i3 > i4) && 
/* 175 */           (i3 - i4 > 6)) {
/* 176 */           localNote.setPitch(i4 + 12);
/*     */         }
/*     */ 
/* 179 */         arrayOfPhrase[j].addNote(localNote);
/*     */       }
/*     */ 
/* 183 */       cleanMelody(arrayOfPhrase[j], i);
/*     */     }
/*     */ 
/* 190 */     return arrayOfPhrase;
/*     */   }
/*     */ 
/*     */   private Phrase completeFinalBeat(Phrase paramPhrase, int paramInt) {
/* 194 */     Phrase localPhrase = paramPhrase.copy();
/*     */ 
/* 196 */     double d1 = localPhrase.getEndTime();
/*     */ 
/* 199 */     double d2 = Math.ceil(d1) - d1;
/*     */ 
/* 202 */     if (d2 > 0.0D)
/*     */     {
/* 204 */       int[] arrayOfInt = generateIntervalArray(paramPhrase);
/* 205 */       int i = localPhrase.size() - 1;
/* 206 */       int j = -2147483648;
/* 207 */       while (j == -2147483648) {
/* 208 */         j = localPhrase.getNote(i--).getPitch();
/*     */       }
/*     */ 
/* 211 */       j += arrayOfInt[(int)(Math.random() * arrayOfInt.length)];
/* 212 */       if (!(isScale(j))) {
/* 213 */         if (Math.random() < 0.5D)
/* 214 */           ++j;
/*     */         else {
/* 216 */           --j;
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 221 */       localPhrase.addNote(new Note(j, d2));
/*     */     }
/*     */ 
/* 224 */     return localPhrase;
/*     */   }
/*     */ 
/*     */   private double[][] generateBeatRhythmArray(Phrase paramPhrase, int paramInt) {
/* 228 */     double[][] arrayOfDouble1 = new double[(int)paramPhrase.getEndTime() * paramInt][];
/*     */ 
/* 230 */     int i = 0;
/* 231 */     int j = 0;
/* 232 */     double d1 = 0.0D;
/*     */ 
/* 235 */     while (j < paramPhrase.size()) {
/* 236 */       int k = j;
/* 237 */       double d2 = d1;
/*     */ 
/* 239 */       int l = j;
/* 240 */       double d3 = d1;
/*     */ 
/* 242 */       double[] arrayOfDouble3 = new double[paramPhrase.size()];
/* 243 */       int i1 = 0;
/* 244 */       Note localNote = paramPhrase.getNote(l++);
/* 245 */       double d4 = localNote.getRhythmValue();
/* 246 */       arrayOfDouble3[(i1++)] = d4;
/* 247 */       if (localNote.getPitch() == -2147483648) {
/* 248 */         arrayOfDouble3[(i1 - 1)] *= -1.0D;
/*     */       }
/*     */ 
/* 252 */       d3 += d4;
/* 253 */       while (d3 != Math.ceil(d3)) {
/* 254 */         localNote = paramPhrase.getNote(l++);
/* 255 */         d4 = localNote.getRhythmValue();
/* 256 */         arrayOfDouble3[(i1++)] = d4;
/* 257 */         if (localNote.getPitch() == -2147483648) {
/* 258 */           arrayOfDouble3[(i1 - 1)] *= -1.0D;
/*     */         }
/*     */ 
/* 261 */         d3 += d4;
/*     */       }
/* 263 */       double[] arrayOfDouble4 = new double[i1];
/* 264 */       System.arraycopy(arrayOfDouble3, 0, arrayOfDouble4, 0, i1);
/* 265 */       arrayOfDouble1[(i++)] = arrayOfDouble4;
/*     */ 
/* 267 */       j = l;
/* 268 */       d1 = d3;
/*     */ 
/* 271 */       while ((d3 < d2 + paramInt) && (l < paramPhrase.size())) {
/* 272 */         localNote = paramPhrase.getNote(l++);
/* 273 */         d4 = localNote.getRhythmValue();
/* 274 */         arrayOfDouble3[(i1++)] = d4;
/* 275 */         if (localNote.getPitch() == -2147483648) {
/* 276 */           arrayOfDouble3[(i1 - 1)] *= -1.0D;
/*     */         }
/*     */ 
/* 279 */         d3 += d4;
/* 280 */         while (d3 != Math.ceil(d3)) {
/* 281 */           localNote = paramPhrase.getNote(l++);
/* 282 */           d4 = localNote.getRhythmValue();
/* 283 */           arrayOfDouble3[(i1++)] = d4;
/* 284 */           if (localNote.getPitch() == -2147483648) {
/* 285 */             arrayOfDouble3[(i1 - 1)] *= -1.0D;
/*     */           }
/*     */ 
/* 288 */           d3 += d4;
/*     */         }
/* 290 */         if (d3 <= d2 + paramInt);
/* 291 */         arrayOfDouble4 = new double[i1];
/* 292 */         System.arraycopy(arrayOfDouble3, 0, arrayOfDouble4, 0, i1);
/* 293 */         arrayOfDouble1[(i++)] = arrayOfDouble4;
/*     */       }
/*     */     }
/*     */ 
/* 297 */     double[][] arrayOfDouble2 = new double[i][];
/* 298 */     System.arraycopy(arrayOfDouble1, 0, arrayOfDouble2, 0, i);
/* 299 */     return arrayOfDouble2;
/*     */   }
/*     */ 
/*     */   private int[] generateIntervalArray(Phrase paramPhrase) {
/* 303 */     int[] arrayOfInt1 = new int[0];
/*     */     try {
/* 305 */       arrayOfInt1 = PhraseAnalysis.pitchIntervals(paramPhrase);
/*     */     } catch (ArrayStoreException localArrayStoreException) {
/* 307 */       System.exit(0);
/*     */     }
/*     */ 
/* 311 */     int[] arrayOfInt2 = new int[arrayOfInt1.length * 2];
/* 312 */     System.arraycopy(arrayOfInt1, 0, arrayOfInt2, 0, arrayOfInt1.length);
/* 313 */     for (int i = 0; i < arrayOfInt1.length; ++i) {
/* 314 */       if (arrayOfInt2[i] > 127) {
/* 315 */         arrayOfInt2[i] -= 255;
/*     */       }
/* 317 */       arrayOfInt2[(arrayOfInt1.length + i)] = (0 - arrayOfInt2[i]);
/*     */     }
/*     */ 
/* 320 */     return arrayOfInt2;
/*     */   }
/*     */ 
/*     */   private boolean isClimaxAccepted(Phrase paramPhrase, int paramInt) {
/* 324 */     int i = 0;
/* 325 */     int j = 0;
/* 326 */     double d1 = 0.0D;
/* 327 */     double d2 = 0.0D;
/* 328 */     for (int k = 0; k < paramPhrase.size(); ++k) {
/* 329 */       int l = paramPhrase.getNote(k).getPitch();
/* 330 */       if (l != -2147483648) {
/* 331 */         if (l > i) {
/* 332 */           i = l;
/* 333 */           d2 = d1;
/* 334 */           j = 0;
/* 335 */         } else if (l == i) {
/* 336 */           ++j;
/* 337 */           d2 = d1;
/*     */         }
/*     */       }
/* 340 */       d1 += paramPhrase.getNote(k).getRhythmValue();
/*     */     }
/* 342 */     if ((d2 < 8 * paramInt * 0.262D) || (d2 > 8 * paramInt * 0.784D))
/*     */     {
/* 344 */       return false;
/*     */     }
/*     */ 
/* 348 */     return ((i > paramPhrase.getNote(0).getPitch() + 12) && (j <= 1));
/*     */   }
/*     */ 
/*     */   private int findClimax(Phrase paramPhrase)
/*     */   {
/* 354 */     int i = 0;
/* 355 */     for (int j = 0; j < paramPhrase.size(); ++j) {
/* 356 */       int k = paramPhrase.getNote(j).getPitch();
/* 357 */       if ((k == -2147483648) || 
/* 358 */         (k <= i)) continue;
/* 359 */       i = k;
/*     */     }
/*     */ 
/* 363 */     return i;
/*     */   }
/*     */ 
/*     */   private Note generateClimax(int paramInt) {
/* 367 */     int i = 0;
/* 368 */     int j = paramInt + 13;
/* 369 */     while (i == 0) {
/* 370 */       if ((j % 12 == 0) || (j % 12 == 7))
/*     */       {
/* 372 */         i = j;
/*     */       }
/* 374 */       ++j;
/*     */     }
/* 376 */     while (i > 88) {
/* 377 */       if ((j % 12 == 0) || (j % 12 == 7))
/*     */       {
/* 379 */         i = j;
/*     */       }
/* 381 */       --j;
/*     */     }
/*     */ 
/* 384 */     return new Note(i, 1.0D);
/*     */   }
/*     */ 
/*     */   private void extend(Phrase paramPhrase, Note paramNote, int paramInt1, double[][] paramArrayOfDouble, int[] paramArrayOfInt, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 390 */     int i = (int)paramPhrase.getEndTime();
/* 391 */     while (i < paramInt1)
/*     */     {
/*     */       int j;
/*     */       int k;
/* 392 */       if (i == 2 * paramInt3) {
/* 393 */         j = paramPhrase.size() - 1;
/* 394 */         k = paramPhrase.getNote(j).getPitch();
/* 395 */         while (k == -2147483648) {
/* 396 */           k = paramPhrase.getNote(--j).getPitch();
/*     */         }
/*     */ 
/* 399 */         if ((k % 12 != 0) && (k % 12 != 7))
/*     */         {
/* 401 */           int l = k + 1;
/* 402 */           while ((l % 12 != 0) && (l % 12 != 7))
/*     */           {
/* 404 */             ++l;
/*     */           }
/* 406 */           int i2 = k - 1;
/* 407 */           while ((i2 % 12 != 0) && (i2 % 12 != 7))
/*     */           {
/* 409 */             --i2;
/*     */           }
/* 411 */           if (l > paramInt2)
/* 412 */             k = i2;
/* 413 */           else if (i2 < paramInt4)
/* 414 */             k = l;
/* 415 */           else if (l - k > k - i2)
/*     */           {
/* 417 */             k = i2;
/* 418 */           } else if (k - i2 > l - k)
/*     */           {
/* 420 */             k = l;
/*     */           }
/*     */           else k = l;
/*     */         }
/*     */ 
/* 425 */         paramPhrase.addNote(new Note(k, 2.0D));
/*     */       }
/*     */       else
/*     */       {
/* 431 */         j = paramInt1;
/* 432 */         k = 0;
/*     */ 
/* 434 */         int i1 = 0;
/*     */ 
/* 440 */         while ((i1 < 30) && (i + j > (i / paramInt3 + 1) * paramInt3)) {
/* 441 */           k = (int)(Math.random() * paramArrayOfDouble.length);
/* 442 */           double d = 0.0D;
/* 443 */           for (int i4 = 0; i4 < paramArrayOfDouble[k].length; ++i4)
/*     */           {
/* 445 */             d += ((paramArrayOfDouble[k][i4] < 0.0D) ? 0.0D - paramArrayOfDouble[k][i4] : paramArrayOfDouble[k][i4]);
/*     */           }
/*     */ 
/* 450 */           j = (int)d;
/* 451 */           ++i1;
/*     */         }
/*     */ 
/* 455 */         if (i1 != 30)
/*     */         {
/* 457 */           for (int i3 = 0; i3 < paramArrayOfDouble[k].length; ++i3) {
/* 458 */             addNote(paramPhrase, paramNote, paramInt1, paramArrayOfDouble[k][i3], paramArrayOfInt, paramInt2, paramInt4);
/*     */           }
/*     */         }
/*     */         else {
/* 462 */           addNote(paramPhrase, paramNote, paramInt1, (i / paramInt3 + 1) * paramInt3 - i, paramArrayOfInt, paramInt2, paramInt4);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 467 */       i = (int)paramPhrase.getEndTime();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void addAppropriateTarget(Phrase paramPhrase, Note paramNote)
/*     */   {
/* 476 */     Note localNote = paramNote.copy();
/* 477 */     int i = paramPhrase.size();
/*     */     int j;
/*     */     do
/* 480 */       j = paramPhrase.getNote(--i).getPitch();
/* 481 */     while (j == -2147483648);
/* 482 */     int k = paramNote.getPitch();
/* 483 */     if (j + 7 < k) {
/*     */       do
/* 485 */         k -= 12;
/* 486 */       while (k - 12 > j);
/* 487 */       localNote.setPitch(k);
/*     */     }
/* 489 */     paramPhrase.addNote(localNote);
/*     */   }
/*     */ 
/*     */   private void addNote(Phrase paramPhrase, Note paramNote, int paramInt1, double paramDouble, int[] paramArrayOfInt, int paramInt2, int paramInt3)
/*     */   {
/* 495 */     if (paramDouble < 0.0D) {
/* 496 */       paramPhrase.addNote(new Note(-2147483648, 0.0D - paramDouble));
/*     */     }
/*     */     else {
/* 499 */       int i = paramPhrase.size() - 1;
/* 500 */       int j = paramPhrase.getNote(i).getPitch();
/* 501 */       while (j == -2147483648) {
/* 502 */         j = paramPhrase.getNote(--i).getPitch();
/*     */       }
/*     */ 
/* 505 */       double d1 = (paramNote.getPitch() - j) / (paramInt1 - paramPhrase.getEndTime());
/*     */ 
/* 507 */       int k = paramArrayOfInt[(int)(Math.random() * paramArrayOfInt.length)];
/*     */ 
/* 509 */       int l = paramNote.getPitch() - j;
/* 510 */       double d2 = k / l;
/* 511 */       if ((d2 < 0.0D) && 
/* 512 */         (Math.random() < 2.5D / (paramInt1 - paramPhrase.getEndTime()))) {
/* 513 */         k = 0 - k;
/*     */       }
/*     */ 
/* 516 */       double d3 = l / (paramInt1 - paramPhrase.getEndTime());
/*     */ 
/* 518 */       double d4 = d3 / d1;
/* 519 */       if ((d4 >= 2.0D) || (d4 <= 0.5D)) {
/* 520 */         k /= 2;
/*     */       }
/*     */ 
/* 523 */       int i1 = j + k;
/* 524 */       if ((i1 >= paramInt2) || (i1 < paramInt3)) {
/* 525 */         i1 = j - k;
/*     */       }
/* 527 */       if ((i1 >= paramInt2) || (i1 < paramInt3)) {
/* 528 */         i1 = j - (k / 2);
/*     */       }
/* 530 */       if ((i1 >= paramInt2) || (i1 < paramInt3)) {
/* 531 */         i1 = j - (k / 4);
/*     */       }
/*     */ 
/* 534 */       paramPhrase.addNote(new Note(i1, paramDouble));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void cleanMelody(Phrase paramPhrase, int paramInt) {
/* 539 */     for (int i = paramInt; i < paramPhrase.size(); ++i) {
/* 540 */       int j = paramPhrase.getNote(i).getPitch();
/* 541 */       if ((j == -2147483648) || 
/* 542 */         (isScale(j))) continue;
/* 543 */       if (Math.random() < 0.5D)
/* 544 */         paramPhrase.getNote(i).setPitch(j + 1);
/*     */       else
/* 546 */         paramPhrase.getNote(i).setPitch(j - 1);
/*     */     }
/*     */   }
/*     */ 
/*     */   private boolean isScale(int paramInt)
/*     */   {
/* 554 */     for (int i = 0; i < PhraseAnalysis.MAJOR_SCALE.length; ++i) {
/* 555 */       if (paramInt % 12 == PhraseAnalysis.MAJOR_SCALE[i]) {
/* 556 */         return true;
/*     */       }
/*     */     }
/* 559 */     return false;
/*     */   }
/*     */ 
/*     */   public Panel getPanel() {
/* 563 */     return this.panel;
/*     */   }
/*     */ 
/*     */   public String getLabel() {
/* 567 */     return label;
/*     */   }
/*     */ 
/*     */   public void setModifyAll(boolean paramBoolean) {
/* 571 */     this.modifyAll = paramBoolean;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.ClimaticPopInitialiser
 * JD-Core Version:    0.5.3
 */