/*     */ package jm.music.tools.ga;
/*     */ 
/*     */ import java.awt.Choice;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.Panel;
/*     */ import java.awt.Scrollbar;
/*     */ import java.awt.event.AdjustmentEvent;
/*     */ import java.awt.event.AdjustmentListener;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.util.Vector;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.tools.PhraseAnalysis;
/*     */ 
/*     */ public class ComplexMutater extends Mutater
/*     */ {
/*  48 */   private int[] MUTATE_PERCENTAGE = { 0, 40, 1, 40, 60 };
/*     */   private static final int SEMITONES_PER_OCTAVE = 12;
/*     */   private static final int TONIC = 60;
/*  54 */   protected static String label = "Mutater";
/*     */   protected Panel panel;
/*     */   protected Choice choice;
/*     */   protected Scrollbar scrollbar;
/*     */   protected Label mutateLabel;
/*  64 */   protected boolean modifyAll = false;
/*     */ 
/*     */   public ComplexMutater() {
/*  67 */     this.panel = new Panel();
/*  68 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/*  69 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/*  70 */     this.panel.setLayout(localGridBagLayout);
/*  71 */     this.mutateLabel = new Label(Integer.toString(this.MUTATE_PERCENTAGE[0]));
/*  72 */     this.scrollbar = new Scrollbar(0, this.MUTATE_PERCENTAGE[0], 1, 0, 100);
/*     */ 
/*  74 */     this.choice = new Choice();
/*  75 */     this.choice.add("Random pitch change");
/*  76 */     this.choice.add("Bar sequence mutations");
/*  77 */     this.choice.add("Split and merge");
/*  78 */     this.choice.add("Step interpolation");
/*  79 */     this.choice.add("Tonal Pauses");
/*  80 */     this.choice.addItemListener(new ItemListener() {
/*     */       public void itemStateChanged(ItemEvent paramItemEvent) {
/*  82 */         ComplexMutater.this.mutateLabel.setText(Integer.toString(ComplexMutater.this.MUTATE_PERCENTAGE[ComplexMutater.this.choice.getSelectedIndex()]));
/*     */ 
/*  84 */         ComplexMutater.this.scrollbar.setValue(ComplexMutater.this.MUTATE_PERCENTAGE[ComplexMutater.this.choice.getSelectedIndex()]);
/*     */       }
/*     */     });
/*  88 */     this.scrollbar.addAdjustmentListener(new AdjustmentListener() {
/*     */       public void adjustmentValueChanged(AdjustmentEvent paramAdjustmentEvent) {
/*  90 */         ComplexMutater.this.MUTATE_PERCENTAGE[ComplexMutater.this.choice.getSelectedIndex()] = ComplexMutater.this.scrollbar.getValue();
/*     */ 
/*  92 */         ComplexMutater.this.mutateLabel.setText(Integer.toString(ComplexMutater.this.scrollbar.getValue()));
/*  93 */         ComplexMutater.this.mutateLabel.repaint();
/*     */       }
/*     */     });
/*  97 */     localGridBagConstraints.gridy = -1;
/*  98 */     localGridBagConstraints.gridwidth = 2;
/*  99 */     localGridBagLayout.setConstraints(this.choice, localGridBagConstraints);
/* 100 */     this.panel.add(this.choice);
/*     */ 
/* 102 */     localGridBagConstraints.gridwidth = 1;
/* 103 */     localGridBagConstraints.gridx = 0;
/* 104 */     localGridBagConstraints.gridy = 1;
/* 105 */     localGridBagConstraints.weightx = 1.0D;
/* 106 */     localGridBagConstraints.fill = 2;
/* 107 */     localGridBagLayout.setConstraints(this.scrollbar, localGridBagConstraints);
/* 108 */     this.panel.add(this.scrollbar);
/*     */ 
/* 110 */     localGridBagConstraints.gridx = -1;
/* 111 */     localGridBagConstraints.fill = 0;
/* 112 */     localGridBagConstraints.weightx = 0.0D;
/* 113 */     localGridBagLayout.setConstraints(this.mutateLabel, localGridBagConstraints);
/* 114 */     this.panel.add(this.mutateLabel);
/*     */   }
/*     */ 
/*     */   public Phrase[] mutate(Phrase[] paramArrayOfPhrase, double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 119 */     double[] arrayOfDouble1 = new double[paramArrayOfPhrase.length];
/* 120 */     for (int i = 0; i < paramArrayOfPhrase.length; ++i) {
/* 121 */       arrayOfDouble1[i] = paramArrayOfPhrase[i].getEndTime();
/*     */     }
/* 123 */     for (i = 0; i < paramArrayOfPhrase.length; ++i) {
/* 124 */       Phrase localPhrase = paramArrayOfPhrase[i];
/*     */ 
/* 127 */       if (this.modifyAll) {
/* 128 */         paramInt1 = 0;
/* 129 */         paramDouble = 0.0D;
/*     */       }
/*     */ 
/* 134 */       int j = localPhrase.size() - paramInt1;
/* 135 */       double d1 = j * this.MUTATE_PERCENTAGE[0] / 100.0D;
/* 136 */       int k = 0;
/* 137 */       if (d1 < 1.0D) {
/* 138 */         if (Math.random() < d1)
/* 139 */           k = 1;
/*     */         else
/* 141 */           k = 0;
/*     */       }
/*     */       else {
/* 144 */         k = (int)Math.floor(d1);
/*     */       }
/* 146 */       for (int l = 0; l < k; ++l) {
/* 147 */         int i1 = (int)(Math.random() * j);
/* 148 */         mutate(localPhrase.getNote(paramInt1 + i1));
/*     */       }
/*     */       int i5;
/*     */       Object localObject2;
/*     */       int i11;
/* 153 */       if (Math.random() < this.MUTATE_PERCENTAGE[1] / 100.0D) {
/* 154 */         l = 0;
/* 155 */         d2 = 0.0D;
/* 156 */         for (int i2 = 0; i2 < localPhrase.size(); ++i2) {
/* 157 */           d2 += localPhrase.getNote(i2).getRhythmValue();
/*     */         }
/* 159 */         int[] arrayOfInt1 = new int[(int)d2];
/* 160 */         localObject1 = new int[(int)d2];
/* 161 */         i4 = 0;
/* 162 */         d2 = 0.0D;
/* 163 */         for (i5 = 0; i5 < localPhrase.size(); ++i5) {
/* 164 */           if (d2 / paramInt2 == Math.floor(d2 / paramInt2))
/*     */           {
/* 166 */             arrayOfInt1[i4] = i5;
/* 167 */             localObject1[(i4++)] = (int)(d2 * paramInt2);
/*     */           }
/* 169 */           d2 += localPhrase.getNote(i5).getRhythmValue();
/*     */         }
/* 171 */         i5 = 0;
/* 172 */         localObject2 = new int[i4];
/* 173 */         if (i4 > 0) {
/* 174 */           for (i6 = 1; i6 < i4; ++i6) {
/* 175 */             if (localObject1[i6] == localObject1[(i6 - 1)] + 1) {
/* 176 */               localObject2[(i5++)] = arrayOfInt1[(i6 - 1)];
/*     */             }
/*     */           }
/*     */         }
/*     */ 
/* 181 */         if (i5 > 0) {
/* 182 */           i6 = 0;
/* 183 */           while (i6 < (int)(paramDouble / paramInt2) - 1) {
/* 184 */             i6 = (int)(Math.random() * i5);
/*     */           }
/* 186 */           int i7 = (int)(Math.random() * 2.0D + 1.0D);
/* 187 */           switch (i7)
/*     */           {
/*     */           case 1:
/* 189 */             int i8 = 0;
/* 190 */             if (Math.random() < 0.5D)
/* 191 */               i8 = 2;
/*     */             else {
/* 193 */               i8 = -2;
/*     */             }
/* 195 */             d2 = 0.0D;
/* 196 */             i4 = localObject2[i6];
/*     */             while (true) { if (d2 >= paramInt2) break label720;
/* 198 */               shiftPitch(localPhrase.getNote(i4), i8);
/* 199 */               d2 += localPhrase.getNote(i4++).getRhythmValue();
/*     */             }
/*     */           case 2:
/*     */           }
/*     */ 
/* 204 */           i4 = localObject2[i6];
/* 205 */           d2 = 0.0D;
/* 206 */           while (d2 < paramInt2) {
/* 207 */             d2 += localPhrase.getNote(i4++).getRhythmValue();
/*     */           }
/* 209 */           int i9 = i4 - localObject2[i6];
/* 210 */           i4 = localObject2[i6];
/* 211 */           if (i9 > 0) {
/* 212 */             arrayOfInt3 = new int[i9];
/* 213 */             double[] arrayOfDouble2 = new double[i9];
/* 214 */             for (i11 = 0; i11 < i9; ++i11) {
/* 215 */               arrayOfInt3[i11] = localPhrase.getNote(i11 + i4).getPitch();
/* 216 */               arrayOfDouble2[i11] = localPhrase.getNote(i11 + i4).getRhythmValue();
/*     */             }
/* 218 */             for (i11 = 0; i11 < i9; ++i11) {
/* 219 */               localPhrase.getNote(i11 + i4).setPitch(arrayOfInt3[(i9 - i11 - 1)]);
/* 220 */               localPhrase.getNote(i11 + i4).setRhythmValue(arrayOfDouble2[(i9 - i11 - 1)]);
/*     */             }
/*     */ 
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 229 */       label720: l = localPhrase.size() - paramInt1;
/* 230 */       double d2 = l * this.MUTATE_PERCENTAGE[2] / 100.0D;
/* 231 */       int i3 = 0;
/* 232 */       if (d2 < 1.0D) {
/* 233 */         if (Math.random() < d2)
/* 234 */           i3 = 1;
/*     */         else
/* 236 */           i3 = 0;
/*     */       }
/*     */       else {
/* 239 */         i3 = (int)Math.floor(d2);
/*     */       }
/* 241 */       Object localObject1 = (Vector)localPhrase.getNoteList().clone();
/* 242 */       for (int i4 = 0; i4 < i3; ++i4) {
/* 243 */         i5 = (int)(Math.random() * (l - 1));
/* 244 */         localObject2 = (Note)((Vector)localObject1).elementAt(paramInt1 + i5);
/* 245 */         i6 = ((Note)localObject2).getPitch();
/* 246 */         d4 = ((Note)localObject2).getRhythmValue();
/* 247 */         if ((d4 >= 1.0D) && (d4 % 1.0D == 0.0D) && (d4 * 2.0D == Math.ceil(d4 * 2.0D)))
/*     */         {
/* 249 */           ((Vector)localObject1).removeElementAt(paramInt1 + i5);
/* 250 */           ((Vector)localObject1).insertElementAt(new Note(i6, d4 / 2.0D), paramInt1 + i5);
/*     */ 
/* 253 */           ((Vector)localObject1).insertElementAt(new Note(i6, d4 / 2.0D), paramInt1 + i5);
/*     */ 
/* 256 */           ++l;
/*     */         } else {
/* 258 */           double d5 = d4 + ((Note)((Vector)localObject1).elementAt(paramInt1 + i5 + 1)).getRhythmValue();
/*     */ 
/* 261 */           if (d5 <= 2.0D) {
/* 262 */             ((Vector)localObject1).removeElementAt(paramInt1 + i5);
/* 263 */             ((Vector)localObject1).removeElementAt(paramInt1 + i5);
/* 264 */             ((Vector)localObject1).insertElementAt(new Note(i6, d5), paramInt1 + i5);
/*     */ 
/* 267 */             --l;
/*     */           }
/*     */         }
/*     */       }
/* 271 */       localPhrase.addNoteList((Vector)localObject1, false);
/*     */ 
/* 275 */       localObject1 = (Vector)localPhrase.getNoteList().clone();
/*     */ 
/* 278 */       int i6 = -2147483648;
/* 279 */       double d4 = 0.0D;
/* 280 */       int[] arrayOfInt2 = paramInt1;
/* 281 */       while ((arrayOfInt2 < ((Vector)localObject1).size()) && (i6 == -2147483648)) {
/* 282 */         i6 = ((Note)((Vector)localObject1).elementAt(arrayOfInt2)).getPitch();
/* 283 */         d4 = ((Note)((Vector)localObject1).elementAt(arrayOfInt2)).getRhythmValue();
/* 284 */         ++arrayOfInt2;
/*     */       }
/* 286 */       int[] arrayOfInt3 = arrayOfInt2;
/* 287 */       while (arrayOfInt3 < ((Vector)localObject1).size()) {
/* 288 */         i4 = ((Note)((Vector)localObject1).elementAt(arrayOfInt3)).getPitch();
/* 289 */         double d3 = ((Note)((Vector)localObject1).elementAt(arrayOfInt3)).getRhythmValue();
/* 290 */         if (i4 != -2147483648) {
/* 291 */           int i10 = i4 - i6;
/* 292 */           if ((((Math.abs(i10) == 4) || (Math.abs(i10) == 3))) && (Math.random() < this.MUTATE_PERCENTAGE[3] / 100.0D))
/*     */           {
/* 294 */             i11 = 0;
/* 295 */             if (i10 > 0) {
/* 296 */               i11 = i4 - 1;
/* 297 */               if (!(isScale(i11)))
/* 298 */                 --i11;
/*     */             }
/*     */             else {
/* 301 */               i11 = i4 + 1;
/* 302 */               if (!(isScale(i11))) {
/* 303 */                 ++i11;
/*     */               }
/*     */             }
/* 306 */             if (d3 > d4) {
/* 307 */               if ((d3 >= 0.5D) && ((int)Math.ceil(d3 * 2.0D) == (int)(d3 * 2.0D)))
/*     */               {
/* 310 */                 ((Vector)localObject1).removeElementAt(arrayOfInt3);
/* 311 */                 ((Vector)localObject1).insertElementAt(new Note(i4, d3 / 2.0D), arrayOfInt3);
/*     */ 
/* 313 */                 ((Vector)localObject1).insertElementAt(new Note(i11, d3 / 2.0D), arrayOfInt3);
/*     */ 
/* 315 */                 ++arrayOfInt3;
/*     */               }
/*     */             }
/* 318 */             else if ((d4 >= 0.5D) && ((int)Math.ceil(d4 * 2.0D) == (int)(d4 * 2.0D)))
/*     */             {
/* 321 */               ((Vector)localObject1).removeElementAt(arrayOfInt3 - 1);
/* 322 */               ((Vector)localObject1).insertElementAt(new Note(i11, d4 / 2.0D), arrayOfInt3 - 1);
/*     */ 
/* 324 */               ((Vector)localObject1).insertElementAt(new Note(i6, d4 / 2.0D), arrayOfInt3 - 1);
/*     */ 
/* 326 */               ++arrayOfInt3;
/*     */             }
/*     */           }
/*     */ 
/* 330 */           i6 = i4;
/* 331 */           d4 = d3;
/*     */         }
/* 333 */         ++arrayOfInt3;
/*     */       }
/*     */ 
/* 336 */       localPhrase.addNoteList((Vector)localObject1, false);
/*     */ 
/* 341 */       localPhrase.addNoteList(applyTonalPausesMutation(localPhrase, paramDouble, paramInt1, paramInt2), false);
/*     */ 
/* 349 */       double d6 = 0.0D;
/* 350 */       for (int i12 = paramInt1; i12 < localPhrase.size(); ++i12) {
/* 351 */         int i13 = localPhrase.getNote(i12).getPitch();
/* 352 */         double d7 = localPhrase.getNote(i12).getRhythmValue();
/* 353 */         if ((i13 != -2147483648) && 
/* 354 */           (!(isScale(i13)))) {
/* 355 */           if ((int)Math.ceil(d6 / 2.0D) == (int)(d6 / 2.0D))
/*     */           {
/* 357 */             if (Math.random() < d7) {
/* 358 */               if (Math.random() < 0.5D)
/* 359 */                 localPhrase.getNote(i12).setPitch(i13 + 1);
/*     */               else {
/* 361 */                 localPhrase.getNote(i12).setPitch(i13 - 1);
/*     */               }
/*     */             }
/*     */           }
/* 365 */           else if (Math.random() < d7 / 2.0D) {
/* 366 */             if (Math.random() < 0.5D)
/* 367 */               localPhrase.getNote(i12).setPitch(i13 + 1);
/*     */             else {
/* 369 */               localPhrase.getNote(i12).setPitch(i13 - 1);
/*     */             }
/*     */           }
/*     */ 
/*     */         }
/*     */ 
/* 375 */         d6 += d7;
/*     */       }
/*     */     }
/* 378 */     return ((Phrase)(Phrase)paramArrayOfPhrase);
/*     */   }
/*     */ 
/*     */   private void mutate(Note paramNote)
/*     */   {
/* 383 */     int i = (int)(10.0D / (Math.random() * 6.0D + 2.0D));
/* 384 */     if (Math.random() < 0.5D)
/* 385 */       shiftPitch(paramNote, i);
/*     */     else
/* 387 */       shiftPitch(paramNote, 0 - i);
/*     */   }
/*     */ 
/*     */   private Vector applyTonalPausesMutation(Phrase paramPhrase, double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 394 */     Vector localVector = (Vector)paramPhrase.getNoteList().clone();
/* 395 */     double d1 = paramDouble;
/* 396 */     int i = 0;
/* 397 */     for (int j = paramInt1; j < paramPhrase.size() - 1; ++j) {
/* 398 */       int k = paramPhrase.getNote(j).getPitch();
/* 399 */       int l = pitchToDegree(k, 60);
/* 400 */       double d2 = paramPhrase.getNote(j).getRhythmValue() + paramPhrase.getNote(j + 1).getRhythmValue();
/*     */ 
/* 402 */       if ((d1 / paramInt2 == Math.ceil(d1 / paramInt2)) && (((l == 0) || (l == 7))) && (Math.random() < 2.0D / d2 * this.MUTATE_PERCENTAGE[4] / 100.0D))
/*     */       {
/* 407 */         localVector.removeElementAt(j - i);
/* 408 */         localVector.removeElementAt(j - i);
/* 409 */         localVector.insertElementAt(new Note(k, d2), j - i);
/*     */ 
/* 411 */         d1 += paramPhrase.getNote(j).getRhythmValue();
/* 412 */         ++j;
/* 413 */         ++i;
/*     */       }
/* 415 */       d1 += paramPhrase.getNote(j).getRhythmValue();
/*     */     }
/*     */ 
/* 418 */     return localVector;
/*     */   }
/*     */ 
/*     */   private void shiftPitch(Note paramNote, int paramInt) {
/* 422 */     paramNote.setPitch(paramNote.getPitch() + paramInt);
/*     */   }
/*     */ 
/*     */   private boolean isScale(int paramInt) {
/* 426 */     for (int i = 0; i < PhraseAnalysis.MAJOR_SCALE.length; ++i) {
/* 427 */       if (paramInt % 12 == PhraseAnalysis.MAJOR_SCALE[i]) {
/* 428 */         return true;
/*     */       }
/*     */     }
/* 431 */     return false;
/*     */   }
/*     */ 
/*     */   private static int pitchToDegree(int paramInt1, int paramInt2)
/*     */   {
/* 436 */     paramInt1 -= paramInt2;
/*     */ 
/* 439 */     if (paramInt1 < 0)
/*     */     {
/* 443 */       paramInt1 += (-paramInt1 / 12 + 1) * 12;
/*     */     }
/*     */ 
/* 447 */     return (paramInt1 % 12);
/*     */   }
/*     */ 
/*     */   public Panel getPanel()
/*     */   {
/* 456 */     return this.panel;
/*     */   }
/*     */ 
/*     */   public String getLabel() {
/* 460 */     return label;
/*     */   }
/*     */ 
/*     */   public void setModifyAll(boolean paramBoolean) {
/* 464 */     this.modifyAll = paramBoolean;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.ComplexMutater
 * JD-Core Version:    0.5.3
 */