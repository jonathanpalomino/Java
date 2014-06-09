/*     */ package jm.music.tools;
/*     */ 
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public final class ChordAnalysis
/*     */ {
/*  40 */   public static final int[] RATINGS = { 1, 4, 4, 3, 2, 5, 7 };
/*     */ 
/*     */   public static Possible[] getChords(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*     */   {
/*  79 */     int[][] arrayOfInt = new int[paramArrayOfInt.length][3];
/*  80 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/*  81 */       arrayOfInt[i][0] = paramArrayOfInt[i];
/*  82 */       arrayOfInt[i][1] = paramArrayOfInt[((i + 2) % paramArrayOfInt.length)];
/*  83 */       arrayOfInt[i][2] = paramArrayOfInt[((i + 4) % paramArrayOfInt.length)];
/*     */     }
/*     */ 
/*  86 */     double d1 = paramPhrase.getStartTime();
/*  87 */     if (d1 < 0.0D) {
/*  88 */       d1 = 0.0D;
/*     */     }
/*  90 */     double d2 = 0.0D;
/*  91 */     int j = 0;
/*  92 */     double d3 = paramPhrase.getEndTime();
/*  93 */     if (d3 == 0.0D) {
/*  94 */       return new Possible[0];
/*     */     }
/*  96 */     Note localNote1 = new Note();
/*  97 */     Note localNote2 = new Note();
/*     */ 
/*  99 */     int k = paramPhrase.size();
/* 100 */     Possible[] arrayOfPossible = new Possible[(int)Math.ceil(d3 / paramDouble)];
/*     */ 
/* 103 */     int l = 0;
/* 104 */     for (l = 0; l < arrayOfPossible.length; ++l) {
/* 105 */       if (d2 == l * paramDouble)
/* 106 */         localNote1 = paramPhrase.getNote(j);
/*     */       else
/* 108 */         localNote1 = null;
/*     */       do
/*     */       {
/* 111 */         if (d2 >= (l + 0.5D) * paramDouble) break label236;
/* 112 */         d2 += paramPhrase.getNote(j).getRhythmValue();
/* 113 */         ++j; }
/* 114 */       while (j < k);
/* 115 */       localNote2 = null;
/* 116 */       break;
/*     */ 
/* 120 */       if (d2 == (l + 0.5D) * paramDouble)
/* 121 */         label236: localNote2 = paramPhrase.getNote(j);
/*     */       else
/* 123 */         localNote2 = null;
/*     */       do
/*     */       {
/* 126 */         if (d2 >= (l + 1) * paramDouble) break label305;
/* 127 */         d2 += paramPhrase.getNote(j).getRhythmValue();
/* 128 */         ++j; }
/* 129 */       while (j < k);
/* 130 */       break;
/*     */ 
/* 134 */       label305: arrayOfPossible[l] = firstPass(localNote1, localNote2, paramInt, paramArrayOfInt, arrayOfInt);
/*     */     }
/* 136 */     arrayOfPossible[l] = firstPass(localNote1, localNote2, paramInt, paramArrayOfInt, arrayOfInt);
/*     */ 
/* 138 */     return arrayOfPossible;
/*     */   }
/*     */ 
/*     */   public static int[] getFirstPassChords(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 145 */     Possible[] arrayOfPossible = getChords(paramPhrase, paramDouble, paramInt, paramArrayOfInt);
/* 146 */     int[] arrayOfInt = new int[arrayOfPossible.length];
/*     */ 
/* 148 */     for (int i = 0; i < arrayOfPossible.length; ++i) {
/* 149 */       if (arrayOfPossible[i] != null)
/* 150 */         arrayOfInt[i] = arrayOfPossible[i].getBestChord();
/*     */       else {
/* 152 */         arrayOfInt[i] = 7;
/*     */       }
/*     */     }
/* 155 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   public static int[] getSecondPassChords(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 162 */     Possible[] arrayOfPossible = getChords(paramPhrase, paramDouble, paramInt, paramArrayOfInt);
/* 163 */     int[] arrayOfInt = new int[arrayOfPossible.length];
/* 164 */     int i = arrayOfPossible.length - 1;
/* 165 */     if (i < 0) {
/* 166 */       return new int[0];
/*     */     }
/* 168 */     while (arrayOfPossible[i] == null) {
/* 169 */       arrayOfInt[i] = 7;
/* 170 */       --i;
/* 171 */       if (i < 0) {
/* 172 */         return arrayOfInt;
/*     */       }
/*     */     }
/* 175 */     arrayOfInt[i] = arrayOfPossible[i].getBestChord();
/* 176 */     int j = arrayOfInt[i];
/* 177 */     --i;
/* 178 */     while (i > 0) {
/*     */       do { if (arrayOfPossible[i] != null) break label114;
/* 180 */         arrayOfInt[i] = 7;
/* 181 */         --i; }
/* 182 */       while (i >= 1);
/* 183 */       break;
/*     */ 
/* 186 */       label114: int k = (j + 4) % paramArrayOfInt.length;
/* 187 */       if (acceptableChange(arrayOfPossible[i].chords, k, arrayOfPossible[i].getBestChord()))
/*     */       {
/* 189 */         arrayOfInt[i] = k;
/*     */       }
/*     */       else arrayOfInt[i] = arrayOfPossible[i].getBestChord();
/*     */ 
/* 193 */       j = arrayOfInt[i];
/* 194 */       --i;
/*     */     }
/* 196 */     if (arrayOfPossible[0] == null)
/* 197 */       arrayOfInt[0] = 7;
/*     */     else {
/* 199 */       arrayOfInt[0] = arrayOfPossible[0].getBestChord();
/*     */     }
/* 201 */     return arrayOfInt;
/*     */   }
/*     */ 
/*     */   private static boolean acceptableChange(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 207 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/* 208 */       if ((paramArrayOfInt[i] == paramInt1) && (RATINGS[paramArrayOfInt[i]] <= 2 + RATINGS[paramInt2]))
/*     */       {
/* 210 */         return true;
/*     */       }
/*     */     }
/* 213 */     return false;
/*     */   }
/*     */ 
/*     */   private static Possible firstPass(Note paramNote1, Note paramNote2, int paramInt, int[] paramArrayOfInt, int[][] paramArrayOfInt1)
/*     */   {
/* 220 */     if (isBad(paramNote1, paramInt, paramArrayOfInt)) {
/* 221 */       if (isBad(paramNote2, paramInt, paramArrayOfInt)) {
/* 222 */         return null;
/*     */       }
/* 224 */       return firstPassChords(paramNote2, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */     }
/*     */ 
/* 227 */     if (isBad(paramNote2, paramInt, paramArrayOfInt)) {
/* 228 */       return firstPassChords(paramNote1, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */     }
/* 230 */     if (PhraseAnalysis.pitchToDegree(paramNote1.getPitch(), paramInt) == PhraseAnalysis.pitchToDegree(paramNote2.getPitch(), paramInt))
/*     */     {
/* 234 */       return firstPassChords(paramNote1, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */     }
/* 236 */     return firstPassChords(paramNote1, paramNote2, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */   }
/*     */ 
/*     */   private static boolean isBad(Note paramNote, int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 245 */     if (paramNote == null) {
/* 246 */       return true;
/*     */     }
/*     */ 
/* 249 */     if (paramNote.getPitch() == -2147483648) {
/* 250 */       return true;
/*     */     }
/*     */ 
/* 254 */     return (!(PhraseAnalysis.isScale(paramNote, paramInt, paramArrayOfInt)));
/*     */   }
/*     */ 
/*     */   private static Possible firstPassChords(Note paramNote, int paramInt, int[] paramArrayOfInt, int[][] paramArrayOfInt1)
/*     */   {
/* 264 */     Possible localPossible = new Possible(new int[3]);
/* 265 */     int i = 0;
/* 266 */     int j = PhraseAnalysis.pitchToDegree(paramNote.getPitch(), paramInt);
/* 267 */     for (int k = 0; k < paramArrayOfInt1.length; ++k) {
/* 268 */       if (isInTriad(j, paramArrayOfInt1[k])) {
/* 269 */         localPossible.chords[(i++)] = k;
/*     */       }
/*     */     }
/* 272 */     return localPossible;
/*     */   }
/*     */ 
/*     */   private static Possible firstPassChords(Note paramNote1, Note paramNote2, int paramInt, int[] paramArrayOfInt, int[][] paramArrayOfInt1)
/*     */   {
/* 280 */     Possible localPossible1 = firstPassChords(paramNote1, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */ 
/* 282 */     Possible localPossible2 = firstPassChords(paramNote2, paramInt, paramArrayOfInt, paramArrayOfInt1);
/*     */ 
/* 284 */     Possible localPossible3 = findCommonChords(localPossible1.chords, localPossible2.chords);
/*     */ 
/* 286 */     return ((localPossible3 == null) ? localPossible1 : localPossible3);
/*     */   }
/*     */ 
/*     */   private static boolean isInTriad(int paramInt, int[] paramArrayOfInt) {
/* 290 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/* 291 */       if (paramArrayOfInt[i] == paramInt) {
/* 292 */         return true;
/*     */       }
/*     */     }
/* 295 */     return false;
/*     */   }
/*     */ 
/*     */   private static Possible findCommonChords(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 300 */     Possible localPossible = new Possible(new int[2]);
/* 301 */     int i = 0;
/* 302 */     for (int j = 0; j < paramArrayOfInt1.length; ++j) {
/* 303 */       for (int k = 0; k < paramArrayOfInt2.length; ++k) {
/* 304 */         if (paramArrayOfInt1[j] == paramArrayOfInt2[k]) {
/* 305 */           localPossible.chords[(i++)] = paramArrayOfInt1[j];
/*     */         }
/*     */       }
/*     */     }
/* 309 */     if (i == 0)
/* 310 */       return null;
/* 311 */     if (i == 2)
/* 312 */       return localPossible;
/* 313 */     if (i == 1) {
/* 314 */       int[] arrayOfInt = new int[1];
/* 315 */       arrayOfInt[0] = localPossible.chords[0];
/* 316 */       return new Possible(arrayOfInt);
/*     */     }
/* 318 */     throw new Error("Unexpected value for index");
/*     */   }
/*     */ 
/*     */   private static class Possible
/*     */   {
/*  44 */     int[] chords = null;
/*     */ 
/*     */     Possible() {
/*     */     }
/*     */ 
/*     */     Possible(int[] paramArrayOfInt) {
/*  50 */       this.chords = paramArrayOfInt;
/*     */     }
/*     */ 
/*     */     int getBestChord() {
/*  54 */       if (this.chords == null) {
/*  55 */         return -1;
/*     */       }
/*     */ 
/*  58 */       int i = 6;
/*  59 */       for (int j = 0; j < this.chords.length; ++j) {
/*  60 */         if (ChordAnalysis.RATINGS[this.chords[j]] < ChordAnalysis.RATINGS[i]) {
/*  61 */           i = this.chords[j];
/*     */         }
/*     */       }
/*  64 */       return i;
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ChordAnalysis
 * JD-Core Version:    0.5.3
 */