/*     */ package jm.music.tools;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public final class PhraseMatrix
/*     */   implements JMC
/*     */ {
/*     */   private AdaptiveMatrix pitchAM;
/*     */   private AdaptiveMatrix rhythmAM;
/*     */   private AdaptiveMatrix dynamicAM;
/*     */   private int pitchDepth;
/*     */   private int rhythmDepth;
/*     */   private int dynamicDepth;
/*     */   private Note[] notes;
/*     */   private final double[] rhythmMap;
/*     */ 
/*     */   public PhraseMatrix(Phrase paramPhrase, int paramInt)
/*     */   {
/*  94 */     this(paramPhrase, paramInt, paramInt, paramInt);
/*     */   }
/*     */ 
/*     */   public PhraseMatrix(Phrase paramPhrase, int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  83 */     this.rhythmMap = new double[] { 4.0D, 3.0D, 2.0D, 1.0D, 0.6666666666666666D, 1.5D, 0.5D, 0.75D, 0.3333333333333333D, 0.25D, 0.125D };
/*     */ 
/* 103 */     this.pitchDepth = paramInt1;
/* 104 */     this.rhythmDepth = paramInt2;
/* 105 */     this.dynamicDepth = paramInt3;
/* 106 */     this.notes = paramPhrase.getNoteArray();
/* 107 */     calcPitch();
/* 108 */     calcRhythm();
/* 109 */     calcDynamic();
/*     */   }
/*     */ 
/*     */   public void calcPitch()
/*     */   {
/* 120 */     int[] arrayOfInt = new int[this.notes.length];
/* 121 */     for (int i = 0; i < this.notes.length; ++i) {
/* 122 */       arrayOfInt[i] = this.notes[i].getPitch();
/*     */     }
/* 124 */     this.pitchAM = new AdaptiveMatrix(arrayOfInt, this.pitchDepth, 127);
/*     */   }
/*     */ 
/*     */   public void calcRhythm()
/*     */   {
/* 132 */     int[] arrayOfInt = new int[this.notes.length];
/* 133 */     for (int i = 0; i < this.notes.length; ++i) {
/* 134 */       int j = 0;
/* 135 */       for (int k = 0; k < this.rhythmMap.length; ++k) {
/* 136 */         if (this.notes[i].getRhythmValue() == this.rhythmMap[k]) {
/* 137 */           j = 1;
/* 138 */           arrayOfInt[i] = k;
/* 139 */           break;
/*     */         }
/*     */       }
/* 142 */       if (j == 0) {
/* 143 */         System.err.print("[WARNING] PhraseMatrix only supports ");
/* 144 */         System.err.println("rhythm values supported in the JMC file");
/*     */       }
/*     */     }
/* 147 */     this.rhythmAM = new AdaptiveMatrix(arrayOfInt, this.rhythmDepth, this.rhythmMap.length);
/*     */   }
/*     */ 
/*     */   public void calcDynamic()
/*     */   {
/* 156 */     int[] arrayOfInt = new int[this.notes.length];
/* 157 */     for (int i = 0; i < this.notes.length; ++i) {
/* 158 */       arrayOfInt[i] = this.notes[i].getDynamic();
/*     */     }
/* 160 */     this.dynamicAM = new AdaptiveMatrix(arrayOfInt, this.dynamicDepth, 127);
/*     */   }
/*     */ 
/*     */   public Phrase generate(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
/*     */   {
/* 175 */     return generate(paramBoolean1, paramBoolean2, paramBoolean3, this.notes.length);
/*     */   }
/*     */ 
/*     */   public Phrase generate(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt)
/*     */   {
/* 188 */     int[] arrayOfInt1 = new int[this.pitchDepth];
/* 189 */     int[] arrayOfInt2 = new int[this.rhythmDepth];
/* 190 */     int[] arrayOfInt3 = new int[this.dynamicDepth];
/*     */ 
/* 192 */     Note[] arrayOfNote = new Note[paramInt];
/* 193 */     for (int i = 0; i < paramInt; ++i) {
/* 194 */       arrayOfNote[i] = new Note();
/*     */     }
/*     */ 
/* 209 */     for (int j = 0; j < this.pitchDepth; ++j) {
/* 210 */       arrayOfInt1[j] = this.notes[j].getPitch();
/*     */     }
/*     */ 
/* 214 */     for (j = 0; j < this.rhythmDepth; ++j) {
/* 215 */       for (int k = 0; k < this.rhythmMap.length; ++k) {
/* 216 */         if (this.notes[j].getRhythmValue() == this.rhythmMap[k]) {
/* 217 */           arrayOfInt2[j] = k;
/* 218 */           break;
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 223 */     for (j = 0; j < this.dynamicDepth; ++j) {
/* 224 */       arrayOfInt3[j] = this.notes[j].getDynamic();
/*     */     }
/*     */ 
/* 227 */     int[] arrayOfInt4 = this.pitchAM.generate(paramInt, arrayOfInt1);
/* 228 */     int[] arrayOfInt5 = this.dynamicAM.generate(paramInt, arrayOfInt3);
/* 229 */     int[] arrayOfInt6 = this.rhythmAM.generate(paramInt, arrayOfInt2);
/*     */ 
/* 231 */     if (paramBoolean1) {
/* 232 */       for (int l = 0; l < paramInt; ++l) {
/* 233 */         arrayOfNote[l].setPitch(arrayOfInt4[l]);
/*     */       }
/*     */     }
/* 236 */     if (paramBoolean2) {
/* 237 */       for (int i1 = 0; i1 < paramInt; ++i1) {
/* 238 */         arrayOfNote[i1].setRhythmValue(this.rhythmMap[arrayOfInt6[i1]]);
/* 239 */         arrayOfNote[i1].setDuration(this.rhythmMap[arrayOfInt6[i1]] * 0.9D);
/*     */       }
/*     */     }
/* 242 */     if (paramBoolean3) {
/* 243 */       for (int i2 = 0; i2 < paramInt; ++i2) {
/* 244 */         arrayOfNote[i2].setDynamic(arrayOfInt5[i2]);
/*     */       }
/*     */     }
/*     */ 
/* 248 */     Phrase localPhrase = new Phrase();
/* 249 */     localPhrase.addNoteList(arrayOfNote);
/* 250 */     return localPhrase;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.PhraseMatrix
 * JD-Core Version:    0.5.3
 */