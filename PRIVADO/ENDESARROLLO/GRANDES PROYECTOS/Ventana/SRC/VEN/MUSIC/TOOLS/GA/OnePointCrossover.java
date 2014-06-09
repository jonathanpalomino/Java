/*     */ package jm.music.tools.ga;
/*     */ 
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class OnePointCrossover extends Recombiner
/*     */ {
/*     */   private static final int ROUNDS = 10;
/*     */   private static final int ELITISM_CONSTANT = 2;
/*     */ 
/*     */   public Phrase[] recombine(Phrase[] paramArrayOfPhrase, double[] paramArrayOfDouble, double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/*  46 */     Phrase[] arrayOfPhrase = (paramArrayOfPhrase.length - 2 >= 0) ? new Phrase[paramArrayOfPhrase.length - 2] : new Phrase[0];
/*     */     int i;
/*     */     int j;
/*     */     int k;
/*     */     int l;
/*     */     int i1;
/*     */     int i2;
/*  49 */     if (arrayOfPhrase.length > 1) {
/*  50 */       for (i = 1; i < arrayOfPhrase.length; i += 2) {
/*  51 */         j = selectTournamentVictor(paramArrayOfDouble, -1);
/*  52 */         k = selectTournamentVictor(paramArrayOfDouble, j);
/*  53 */         l = (int)paramArrayOfPhrase[j].getEndTime() / paramInt2;
/*     */ 
/*  55 */         i1 = (int)paramArrayOfPhrase[k].getEndTime() / paramInt2;
/*     */ 
/*  57 */         i2 = (l > i1) ? i1 : l;
/*     */ 
/*  60 */         int i3 = 0;
/*  61 */         while (i3 < (int)(paramDouble / paramInt2) + 1) {
/*  62 */           i3 = (int)(Math.random() * i2);
/*     */         }
/*  64 */         arrayOfPhrase[(i - 1)] = crossover(i3, paramArrayOfPhrase[j], paramArrayOfPhrase[k], true, paramInt2);
/*     */ 
/*  67 */         arrayOfPhrase[i] = crossover(i3, paramArrayOfPhrase[j], paramArrayOfPhrase[k], false, paramInt2);
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  72 */     if ((int)(arrayOfPhrase.length / 2.0D) != Math.round(arrayOfPhrase.length / 2.0D))
/*     */     {
/*  74 */       i = selectTournamentVictor(paramArrayOfDouble, -1);
/*  75 */       j = selectTournamentVictor(paramArrayOfDouble, i);
/*  76 */       k = (int)paramArrayOfPhrase[i].getEndTime() / paramInt2;
/*     */ 
/*  78 */       l = (int)paramArrayOfPhrase[j].getEndTime() / paramInt2;
/*     */ 
/*  80 */       i1 = (k > l) ? l : k;
/*     */ 
/*  83 */       i2 = (int)(Math.random() * i1);
/*  84 */       arrayOfPhrase[(arrayOfPhrase.length - 1)] = crossover(i2, paramArrayOfPhrase[i], paramArrayOfPhrase[j], true, paramInt2);
/*     */     }
/*     */ 
/*  89 */     return arrayOfPhrase;
/*     */   }
/*     */ 
/*     */   private int selectTournamentVictor(double[] paramArrayOfDouble, int paramInt) {
/*  93 */     int i = paramInt;
/*  94 */     while (i == paramInt) {
/*  95 */       i = (int)(Math.random() * paramArrayOfDouble.length);
/*     */     }
/*  97 */     for (int j = 0; j < 10; ++j) {
/*  98 */       int k = i;
/*  99 */       while ((k == i) || (k == paramInt)) {
/* 100 */         k = (int)(Math.random() * paramArrayOfDouble.length);
/*     */       }
/* 102 */       if (paramArrayOfDouble[k] > paramArrayOfDouble[i]) {
/* 103 */         return k;
/*     */       }
/*     */     }
/* 106 */     return i;
/*     */   }
/*     */ 
/*     */   private Phrase crossover(int paramInt1, Phrase paramPhrase1, Phrase paramPhrase2, boolean paramBoolean, int paramInt2)
/*     */   {
/* 111 */     Phrase localPhrase1 = new Phrase();
/*     */ 
/* 113 */     Phrase localPhrase2 = (paramBoolean) ? paramPhrase2 : paramPhrase1;
/* 114 */     int i = 0;
/*     */ 
/* 118 */     while (localPhrase1.getEndTime() + localPhrase2.getNote(i).getRhythmValue() < paramInt1 * paramInt2) {
/* 119 */       localPhrase1.addNote(localPhrase2.getNote(i++).copy());
/*     */     }
/* 121 */     double d1 = paramInt1 * paramInt2 - localPhrase1.getEndTime();
/*     */ 
/* 123 */     localPhrase1.addNote(new Note(localPhrase2.getNote(i).getPitch(), paramInt1 * paramInt2 - localPhrase1.getEndTime()));
/*     */ 
/* 126 */     i = -1;
/* 127 */     localPhrase2 = (paramBoolean) ? paramPhrase1 : paramPhrase2;
/* 128 */     double d2 = 0.0D;
/* 129 */     while (d2 <= paramInt1 * paramInt2) {
/* 130 */       ++i;
/* 131 */       d2 += localPhrase2.getNote(i).getRhythmValue();
/*     */     }
/*     */ 
/* 134 */     localPhrase1.addNote(new Note(localPhrase2.getNote(i++).getPitch(), d2 - (paramInt1 * paramInt2)));
/*     */ 
/* 137 */     while (i < localPhrase2.size()) {
/* 138 */       localPhrase1.addNote(localPhrase2.getNote(i++));
/*     */     }
/* 140 */     return localPhrase1;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.ga.OnePointCrossover
 * JD-Core Version:    0.5.3
 */