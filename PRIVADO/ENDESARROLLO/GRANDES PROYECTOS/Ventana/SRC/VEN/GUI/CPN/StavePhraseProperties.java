/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Properties;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class StavePhraseProperties extends Properties
/*     */ {
/*  33 */   private static String KEY_SIGNATURE = "STAVE_KEY";
/*     */ 
/*  35 */   private static String STAVE_TYPE = "STAVE_TYPE";
/*     */ 
/*  37 */   private static String STAVE_TITLE = "STAVE_TITLE";
/*     */ 
/*  39 */   private static String STAVE_METRE = "STAVE_METRE";
/*     */ 
/*  41 */   private static String PHRASE_NUMERATOR = "PHRASE_NUM";
/*     */ 
/*  43 */   private static String PHRASE_DENOMINATOR = "PHRASE_DEN";
/*     */ 
/*  45 */   private static String PHRASE_TEMPO = "PHRASE_TEMPO";
/*     */ 
/*  47 */   private static String PHRASE_TITLE = "PHRASE_TITLE";
/*  48 */   private static String PHRASE_INSTRUMENT = "PHRASE_INSTRUMENT";
/*     */ 
/*  50 */   private static String LAST_NOTE_RHYTHM = "LAST_NOTE_RHYTHM";
/*  51 */   private static String LAST_NOTE_DUR = "LAST_NOTE_DUR";
/*     */ 
/*  53 */   private static String FINAL_REST_RHYTHM = "FINAL_REST_RHYTHM";
/*  54 */   private static String FINAL_REST_DUR = "FINAL_REST_DUR";
/*     */ 
/*  56 */   private static String OTHER_NOTES_TOTAL_RHYTHM = "OTHER_NOTES_TOTAL_RHYTHM";
/*     */ 
/*  58 */   private static String OTHER_NOTES_TOTAL_DUR = "OTHER_NOTES_TOTAL_DUR";
/*     */ 
/*  62 */   private static String GRAND_STAVE = "GRAND_STAVE";
/*  63 */   private static String TREBLE_STAVE = "TREBLE_STAVE";
/*  64 */   private static String BASS_STAVE = "BASS_STAVE";
/*  65 */   private static String PIANO_STAVE = "PIANO_STAVE";
/*     */ 
/*  70 */   private static String FILE_NAME_SUFFIX = "pj";
/*     */ 
/*     */   public StavePhraseProperties(String paramString)
/*     */     throws FileNotFoundException, IOException
/*     */   {
/*  75 */     FileInputStream localFileInputStream = new FileInputStream(paramString + FILE_NAME_SUFFIX);
/*     */ 
/*  77 */     load(localFileInputStream);
/*     */   }
/*     */ 
/*     */   public StavePhraseProperties(Stave paramStave, Phrase paramPhrase)
/*     */   {
/*  83 */     System.out.println("1");
/*  84 */     setSavedProperty(KEY_SIGNATURE, paramStave.getKeySignature());
/*     */ 
/*  86 */     System.out.println("2");
/*  87 */     setSavedProperty(STAVE_TYPE, getStaveType(paramStave));
/*  88 */     System.out.println("3");
/*  89 */     setSavedProperty(STAVE_TITLE, paramStave.getTitle());
/*     */ 
/*  91 */     System.out.println("4");
/*  92 */     setSavedProperty(STAVE_METRE, paramStave.getMetre());
/*     */ 
/*  94 */     System.out.println("5");
/*  95 */     setSavedProperty(PHRASE_NUMERATOR, paramPhrase.getNumerator());
/*     */ 
/*  97 */     System.out.println("6");
/*  98 */     setSavedProperty(PHRASE_DENOMINATOR, paramPhrase.getDenominator());
/*     */ 
/* 100 */     System.out.println("7");
/* 101 */     setSavedProperty(PHRASE_INSTRUMENT, paramPhrase.getInstrument());
/*     */ 
/* 103 */     System.out.println("8");
/* 104 */     setSavedProperty(PHRASE_TEMPO, paramPhrase.getTempo());
/*     */ 
/* 106 */     System.out.println("9");
/* 107 */     setSavedProperty(PHRASE_TITLE, paramPhrase.getTitle());
/*     */ 
/* 111 */     int i = findLastNonRest(paramPhrase);
/* 112 */     System.out.println("10");
/* 113 */     if (i >= 0) {
/* 114 */       setSavedProperty(LAST_NOTE_RHYTHM, paramPhrase.getNote(i).getRhythmValue());
/*     */ 
/* 118 */       setSavedProperty(LAST_NOTE_DUR, paramPhrase.getNote(i).getDuration());
/*     */     }
/*     */     else
/*     */     {
/* 124 */       setSavedProperty(LAST_NOTE_RHYTHM, 0.0D);
/* 125 */       setSavedProperty(LAST_NOTE_DUR, 0.0D);
/*     */     }
/*     */ 
/* 128 */     setSavedProperty(FINAL_REST_RHYTHM, getFinalRestRhythm(paramPhrase));
/*     */ 
/* 133 */     setSavedProperty(FINAL_REST_DUR, getFinalRestDuration(paramPhrase));
/*     */ 
/* 138 */     setSavedProperty(OTHER_NOTES_TOTAL_RHYTHM, getOtherNotesTotalRhythm(paramPhrase));
/*     */ 
/* 143 */     setSavedProperty(OTHER_NOTES_TOTAL_DUR, getOtherNotesTotalDuration(paramPhrase));
/*     */   }
/*     */ 
/*     */   private void setSavedProperty(String paramString1, String paramString2)
/*     */   {
/* 151 */     if (paramString1 == null) paramString1 = "";
/* 152 */     if (paramString2 == null) paramString2 = "";
/* 153 */     setProperty(paramString1, paramString2);
/*     */   }
/*     */ 
/*     */   private void setSavedProperty(String paramString, int paramInt) {
/* 157 */     setSavedProperty(paramString, Integer.toString(paramInt));
/*     */   }
/*     */ 
/*     */   private void setSavedProperty(String paramString, double paramDouble) {
/* 161 */     setSavedProperty(paramString, Double.toString(paramDouble));
/*     */   }
/*     */ 
/*     */   private String getStaveType(Stave paramStave) {
/* 165 */     if (paramStave instanceof TrebleStave) {
/* 166 */       return TREBLE_STAVE;
/*     */     }
/* 168 */     if (paramStave instanceof GrandStave) {
/* 169 */       return GRAND_STAVE;
/*     */     }
/* 171 */     if (paramStave instanceof BassStave) {
/* 172 */       return BASS_STAVE;
/*     */     }
/* 174 */     if (paramStave instanceof PianoStave) {
/* 175 */       return PIANO_STAVE;
/*     */     }
/* 177 */     return GRAND_STAVE;
/*     */   }
/*     */ 
/*     */   public void writeToFile(String paramString) throws FileNotFoundException
/*     */   {
/*     */     try {
/* 183 */       FileOutputStream localFileOutputStream = new FileOutputStream(paramString + FILE_NAME_SUFFIX);
/*     */ 
/* 185 */       store(localFileOutputStream, "Stave and Phrase Properties for " + paramString);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/* 190 */       System.out.println("Error Writing MIDI Properties File " + paramString + " " + localIOException.getMessage());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void updateStave(Stave paramStave)
/*     */   {
/* 198 */     paramStave.setKeySignature(new Integer(getProperty(KEY_SIGNATURE)).intValue());
/*     */ 
/* 202 */     paramStave.setTitle(getProperty(STAVE_TITLE));
/*     */ 
/* 204 */     paramStave.setMetre(new Double(getProperty(STAVE_METRE)).doubleValue());
/*     */   }
/*     */ 
/*     */   public void updatePhrase(Phrase paramPhrase)
/*     */   {
/* 210 */     paramPhrase.setNumerator(new Integer(getProperty(PHRASE_NUMERATOR)).intValue());
/*     */ 
/* 214 */     paramPhrase.setDenominator(new Integer(getProperty(PHRASE_DENOMINATOR)).intValue());
/*     */ 
/* 218 */     paramPhrase.setTitle(getProperty(PHRASE_TITLE));
/*     */ 
/* 220 */     paramPhrase.setTempo(new Double(getProperty(PHRASE_TEMPO)).doubleValue());
/*     */     try
/*     */     {
/* 225 */       paramPhrase.setInstrument(new Integer(getProperty(PHRASE_INSTRUMENT)).intValue());
/*     */     }
/*     */     catch (Throwable localThrowable)
/*     */     {
/* 230 */       paramPhrase.setInstrument(0);
/*     */     }
/*     */ 
/* 233 */     int i = findLastNonRest(paramPhrase);
/* 234 */     if (i >= 0) {
/* 235 */       paramPhrase.getNote(i).setRhythmValue(new Double(getProperty(LAST_NOTE_RHYTHM)).doubleValue());
/*     */ 
/* 239 */       paramPhrase.getNote(i).setDuration(new Double(getProperty(LAST_NOTE_DUR)).doubleValue());
/*     */     }
/*     */ 
/* 245 */     if (new Double(getProperty(FINAL_REST_RHYTHM)).doubleValue() > 1.E-005D)
/*     */     {
/* 247 */       adjustFinalRestRhythm(paramPhrase, new Double(getProperty(FINAL_REST_RHYTHM)).doubleValue(), new Double(getProperty(FINAL_REST_DUR)).doubleValue());
/*     */     }
/*     */ 
/* 256 */     adjustOtherNotesTotalRhythm(paramPhrase, new Double(getProperty(OTHER_NOTES_TOTAL_RHYTHM)).doubleValue());
/*     */ 
/* 262 */     adjustOtherNotesTotalDuration(paramPhrase, new Double(getProperty(OTHER_NOTES_TOTAL_DUR)).doubleValue());
/*     */ 
/* 268 */     Score localScore = new Score();
/* 269 */     Part localPart = new Part();
/* 270 */     localScore.addPart(localPart);
/* 271 */     localPart.addPhrase(paramPhrase);
/*     */   }
/*     */ 
/*     */   public boolean isTrebleStave() {
/* 275 */     return getProperty(STAVE_TYPE).equals(TREBLE_STAVE);
/*     */   }
/*     */ 
/*     */   public boolean isBassStave() {
/* 279 */     return getProperty(STAVE_TYPE).equals(BASS_STAVE);
/*     */   }
/*     */ 
/*     */   public boolean isGrandStave() {
/* 283 */     return getProperty(STAVE_TYPE).equals(GRAND_STAVE);
/*     */   }
/*     */ 
/*     */   public boolean isPianoStave() {
/* 287 */     return getProperty(STAVE_TYPE).equals(PIANO_STAVE);
/*     */   }
/*     */ 
/*     */   private static int findLastNonRest(Phrase paramPhrase)
/*     */   {
/* 292 */     int i = paramPhrase.size() - 1;
/* 293 */     while ((i >= 0) && (paramPhrase.getNote(i).getPitch() == -2147483648))
/*     */     {
/* 296 */       --i;
/*     */     }
/* 298 */     return i;
/*     */   }
/*     */ 
/*     */   private static void adjustFinalRestRhythm(Phrase paramPhrase, double paramDouble1, double paramDouble2)
/*     */   {
/* 307 */     double d = getFinalRestRhythm(paramPhrase);
/*     */ 
/* 309 */     if (paramDouble1 - d > 0.001D) {
/* 310 */       Note localNote = new Note();
/* 311 */       localNote.setFrequency(-2147483648.0D);
/* 312 */       localNote.setRhythmValue(paramDouble1 - d);
/*     */ 
/* 314 */       localNote.setDuration((paramDouble1 - d) * paramDouble2 / paramDouble1);
/*     */ 
/* 317 */       paramPhrase.addNote(localNote);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static void adjustOtherNotesTotalRhythm(Phrase paramPhrase, double paramDouble)
/*     */   {
/* 326 */     double d1 = getOtherNotesTotalRhythm(paramPhrase);
/*     */ 
/* 328 */     if (d1 <= 0.0D)
/*     */       return;
/* 330 */     double d2 = paramDouble / d1;
/*     */ 
/* 334 */     int i = findLastNonRest(paramPhrase);
/* 335 */     for (int j = 0; j < i; ++j)
/* 336 */       paramPhrase.getNote(j).setRhythmValue(paramPhrase.getNote(j).getRhythmValue() * d2);
/*     */   }
/*     */ 
/*     */   private static void adjustOtherNotesTotalDuration(Phrase paramPhrase, double paramDouble)
/*     */   {
/* 350 */     double d1 = getOtherNotesTotalDuration(paramPhrase);
/*     */ 
/* 352 */     if (d1 <= 0.0D)
/*     */       return;
/* 354 */     double d2 = paramDouble / d1;
/*     */ 
/* 358 */     int i = findLastNonRest(paramPhrase);
/* 359 */     for (int j = 0; j < i; ++j)
/* 360 */       if (paramPhrase.getNote(j).getPitch() != -2147483648)
/*     */       {
/* 362 */         paramPhrase.getNote(j).setDuration(paramPhrase.getNote(j).getDuration() * d2);
/*     */       }
/*     */       else
/*     */       {
/* 367 */         paramPhrase.getNote(j).setDuration(paramPhrase.getNote(j).getRhythmValue());
/*     */       }
/*     */   }
/*     */ 
/*     */   private static double getFinalRestRhythm(Phrase paramPhrase)
/*     */   {
/* 380 */     double d = 0.0D;
/* 381 */     int j = paramPhrase.size();
/* 382 */     int i = findLastNonRest(paramPhrase) + 1;
/* 383 */     while (i < j) {
/* 384 */       d += paramPhrase.getNote(i).getRhythmValue();
/*     */ 
/* 386 */       ++i;
/*     */     }
/* 388 */     return d;
/*     */   }
/*     */ 
/*     */   private static double getFinalRestDuration(Phrase paramPhrase)
/*     */   {
/* 396 */     double d = 0.0D;
/* 397 */     int j = paramPhrase.size();
/* 398 */     int i = findLastNonRest(paramPhrase) + 1;
/* 399 */     while (i < j) {
/* 400 */       d += paramPhrase.getNote(i).getDuration();
/*     */ 
/* 402 */       ++i;
/*     */     }
/* 404 */     return d;
/*     */   }
/*     */ 
/*     */   private static double getOtherNotesTotalRhythm(Phrase paramPhrase)
/*     */   {
/* 411 */     double d = 0.0D;
/* 412 */     int i = 0;
/* 413 */     int j = findLastNonRest(paramPhrase);
/* 414 */     while (i < j) {
/* 415 */       d += paramPhrase.getNote(i).getRhythmValue();
/*     */ 
/* 417 */       ++i;
/*     */     }
/* 419 */     return d;
/*     */   }
/*     */ 
/*     */   private static double getOtherNotesTotalDuration(Phrase paramPhrase)
/*     */   {
/* 427 */     double d = 0.0D;
/* 428 */     int i = 0;
/* 429 */     int j = findLastNonRest(paramPhrase);
/* 430 */     while (i < j) {
/* 431 */       if (paramPhrase.getNote(i).getPitch() != -2147483648)
/*     */       {
/* 433 */         d += paramPhrase.getNote(i).getDuration();
/*     */       }
/*     */ 
/* 436 */       ++i;
/*     */     }
/* 438 */     return d;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.StavePhraseProperties
 * JD-Core Version:    0.5.3
 */