/*      */ package jm.music.tools;
/*      */ 
/*      */ import java.util.Hashtable;
/*      */ import jm.music.data.Note;
/*      */ import jm.music.data.Phrase;
/*      */ 
/*      */ public final class PhraseAnalysis
/*      */ {
/*  122 */   public static final String[] featureNames = { "01 - Pitch Variety", "02 - Pitch Range", "03 - Key Centeredness", "04 - Tonal Deviation", "05 - Dissonance", "06 - Overall Pitch Direction", "07 - Melodic Direction Stability", "08 - Pitch Movement By Tonal Step", "09 - Leap Compensation", "10 - Climax Strength", "11 - Climax Position", "12 - Climax Tonality", "13 - Note Density", "14 - Rest Density", "15 - Rhythmic Variety", "16 - Rhythmic Range", "17 - Syncopation", "18 - Repeated Pitch Density", "19 - Repeated Rhythmic Value Density", "20 - Repeated Pitch Patterns Of Three", "21 - Repeated Pitch Patterns Of Four", "22 - Repeated Rhythm Patterns Of Three", "23 - Repeated Rhythm Patterns Of Four" };
/*      */   public static final String NOTELIST_EXCEPTION_STRING = "NoteListException";
/*      */   public static final String QUANTISATION_EXCEPTION_STRING = "QuantisationException";
/*      */   public static final double NOTELIST_EXCEPTION_CONSTANT = -1.0D;
/*      */   public static final double QUANTISATION_EXCEPTION_CONSTANT = -2.0D;
/*      */   public static final int FEATURE_COUNT = 23;
/*      */   public static final int MAX_PITCH_RANGE = 24;
/*      */   public static final double MAX_RHYTHM_RANGE = 16.0D;
/*  204 */   public static final int[] MAJOR_SCALE = { 0, 2, 4, 5, 7, 9, 11 };
/*      */ 
/*  217 */   public static final int[] MINOR_SCALE = { 0, 2, 3, 5, 7, 8, 10 };
/*      */ 
/*  228 */   public static final int[] PRIMARY_NOTES = { 0, 7 };
/*      */   public static final int BIG_JUMP_INTERVAL = 8;
/*      */   public static final int MAX_DISTINCT_RHYTHMS = 16;
/*      */   public static final int INTERVAL_WITH_REST = 255;
/*  277 */   private static final int[] GOOD_INTERVALS = { 0, 1, 2, 3, 4, 5, 7, 8, 9, 12 };
/*      */ 
/*  292 */   private static final int[] BAD_INTERVALS = { 6, 11 };
/*      */   private static final int SEMITONES_PER_OCTAVE = 12;
/*      */ 
/*      */   public static String[] getAllStatisticsAsStrings(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  304 */     return getAllStatisticsAsStrings(paramPhrase.getNoteArray(), paramDouble, paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static String[] getAllStatisticsAsStrings(Note[] paramArrayOfNote, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  312 */     String[] arrayOfString = new String[23];
/*      */     try {
/*  314 */       arrayOfString[0] = Double.toString(pitchVariety(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException1) {
/*  316 */       arrayOfString[0] = "NoteListException";
/*      */     }
/*      */     try {
/*  319 */       arrayOfString[1] = Double.toString(pitchRangePerSpan(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException2) {
/*  321 */       arrayOfString[1] = "NoteListException";
/*      */     }
/*      */     try {
/*  324 */       arrayOfString[2] = Double.toString(keyCenteredness(paramArrayOfNote, paramDouble, paramInt));
/*      */     }
/*      */     catch (NoteListException localNoteListException3)
/*      */     {
/*  328 */       arrayOfString[2] = "NoteListException";
/*      */     } catch (QuantisationException localQuantisationException1) {
/*  330 */       arrayOfString[2] = "QuantisationException";
/*      */     }
/*      */     try {
/*  333 */       arrayOfString[3] = Double.toString(tonalDeviation(paramArrayOfNote, paramDouble, paramInt, paramArrayOfInt));
/*      */     }
/*      */     catch (NoteListException localNoteListException4)
/*      */     {
/*  337 */       arrayOfString[3] = "NoteListException";
/*      */     } catch (QuantisationException localQuantisationException2) {
/*  339 */       arrayOfString[3] = "QuantisationException";
/*      */     }
/*  341 */     arrayOfString[4] = Double.toString(dissonance(paramArrayOfNote));
/*  342 */     arrayOfString[5] = Double.toString(overallPitchDirection(paramArrayOfNote));
/*      */     try {
/*  344 */       arrayOfString[6] = Double.toString(melodicDirectionStability(paramArrayOfNote));
/*      */     }
/*      */     catch (NoteListException localNoteListException5) {
/*  347 */       arrayOfString[6] = "NoteListException";
/*      */     }
/*      */     try {
/*  350 */       arrayOfString[7] = Double.toString(movementByStep(paramArrayOfNote, paramInt, paramArrayOfInt));
/*      */     }
/*      */     catch (NoteListException localNoteListException6) {
/*  353 */       arrayOfString[7] = "NoteListException";
/*      */     }
/*  355 */     arrayOfString[8] = Double.toString(leapCompensation(paramArrayOfNote));
/*      */     try {
/*  357 */       arrayOfString[9] = Double.toString(climaxStrength(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException7) {
/*  359 */       arrayOfString[9] = "NoteListException";
/*      */     }
/*      */     try {
/*  362 */       arrayOfString[10] = Double.toString(climaxPosition(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException8) {
/*  364 */       arrayOfString[10] = "NoteListException";
/*      */     }
/*      */     try {
/*  367 */       arrayOfString[11] = Double.toString(climaxTonality(paramArrayOfNote, paramInt, paramArrayOfInt));
/*      */     }
/*      */     catch (NoteListException localNoteListException9) {
/*  370 */       arrayOfString[11] = "NoteListException";
/*      */     }
/*      */     try {
/*  373 */       arrayOfString[12] = Double.toString(noteDensity(paramArrayOfNote, paramDouble));
/*      */     } catch (NoteListException localNoteListException10) {
/*  375 */       arrayOfString[12] = "NoteListException";
/*      */     } catch (QuantisationException localQuantisationException3) {
/*  377 */       arrayOfString[12] = "QuantisationException";
/*      */     }
/*      */     try {
/*  380 */       arrayOfString[13] = Double.toString(noteDensity(paramArrayOfNote, paramDouble));
/*      */     } catch (NoteListException localNoteListException11) {
/*  382 */       arrayOfString[13] = "NoteListException";
/*      */     } catch (QuantisationException localQuantisationException4) {
/*  384 */       arrayOfString[13] = "QuantisationException";
/*      */     }
/*  386 */     arrayOfString[14] = Double.toString(rhythmicVariety(paramArrayOfNote));
/*      */     try {
/*  388 */       arrayOfString[15] = Double.toString(rhythmRangePerSpan(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException12) {
/*  390 */       arrayOfString[15] = "NoteListException";
/*      */     }
/*  392 */     arrayOfString[16] = Double.toString(syncopation(paramArrayOfNote));
/*      */     try {
/*  394 */       arrayOfString[17] = Double.toString(repeatedPitchDensity(paramArrayOfNote));
/*      */     } catch (NoteListException localNoteListException13) {
/*  396 */       arrayOfString[17] = "NoteListException";
/*      */     }
/*      */     try {
/*  399 */       arrayOfString[18] = Double.toString(repeatedRhythmicValueDensity(paramArrayOfNote));
/*      */     }
/*      */     catch (NoteListException localNoteListException14) {
/*  402 */       arrayOfString[18] = "NoteListException";
/*      */     }
/*      */     try {
/*  405 */       arrayOfString[19] = Double.toString(repeatedPitchPatterns(paramArrayOfNote, 3));
/*      */     }
/*      */     catch (NoteListException localNoteListException15) {
/*  408 */       arrayOfString[19] = "NoteListException";
/*      */     }
/*      */     try {
/*  411 */       arrayOfString[20] = Double.toString(repeatedPitchPatterns(paramArrayOfNote, 4));
/*      */     }
/*      */     catch (NoteListException localNoteListException16) {
/*  414 */       arrayOfString[20] = "NoteListException";
/*      */     }
/*      */     try {
/*  417 */       arrayOfString[21] = Double.toString(repeatedRhythmPatterns(paramArrayOfNote, 3));
/*      */     }
/*      */     catch (NoteListException localNoteListException17) {
/*  420 */       arrayOfString[21] = "NoteListException";
/*      */     }
/*      */     try {
/*  423 */       arrayOfString[22] = Double.toString(repeatedRhythmPatterns(paramArrayOfNote, 4));
/*      */     }
/*      */     catch (NoteListException localNoteListException18) {
/*  426 */       arrayOfString[22] = "NoteListException";
/*      */     }
/*  428 */     return arrayOfString;
/*      */   }
/*      */ 
/*      */   public static double[] getAllStatisticsAsDoubles(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  435 */     return getAllStatisticsAsDoubles(paramPhrase.getNoteArray(), paramDouble, paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static double[] getAllStatisticsAsDoubles(Note[] paramArrayOfNote, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  443 */     double[] arrayOfDouble = new double[23];
/*      */     try {
/*  445 */       arrayOfDouble[0] = pitchVariety(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException1) {
/*  447 */       arrayOfDouble[0] = -1.0D;
/*      */     }
/*      */     try {
/*  450 */       arrayOfDouble[1] = pitchRangePerSpan(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException2) {
/*  452 */       arrayOfDouble[1] = -1.0D;
/*      */     }
/*      */     try {
/*  455 */       arrayOfDouble[2] = keyCenteredness(paramArrayOfNote, paramDouble, paramInt);
/*      */     } catch (NoteListException localNoteListException3) {
/*  457 */       arrayOfDouble[2] = -1.0D;
/*      */     } catch (QuantisationException localQuantisationException1) {
/*  459 */       arrayOfDouble[2] = -2.0D;
/*      */     }
/*      */     try {
/*  462 */       arrayOfDouble[3] = tonalDeviation(paramArrayOfNote, paramDouble, paramInt, paramArrayOfInt);
/*      */     } catch (NoteListException localNoteListException4) {
/*  464 */       arrayOfDouble[3] = -1.0D;
/*      */     } catch (QuantisationException localQuantisationException2) {
/*  466 */       arrayOfDouble[3] = -2.0D;
/*      */     }
/*  468 */     arrayOfDouble[4] = dissonance(paramArrayOfNote);
/*  469 */     arrayOfDouble[5] = overallPitchDirection(paramArrayOfNote);
/*      */     try {
/*  471 */       arrayOfDouble[6] = melodicDirectionStability(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException5) {
/*  473 */       arrayOfDouble[6] = -1.0D;
/*      */     }
/*      */     try {
/*  476 */       arrayOfDouble[7] = movementByStep(paramArrayOfNote, paramInt, paramArrayOfInt);
/*      */     } catch (NoteListException localNoteListException6) {
/*  478 */       arrayOfDouble[7] = -1.0D;
/*      */     }
/*  480 */     arrayOfDouble[8] = leapCompensation(paramArrayOfNote);
/*      */     try {
/*  482 */       arrayOfDouble[9] = climaxStrength(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException7) {
/*  484 */       arrayOfDouble[9] = -1.0D;
/*      */     }
/*      */     try {
/*  487 */       arrayOfDouble[10] = climaxPosition(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException8) {
/*  489 */       arrayOfDouble[10] = -1.0D;
/*      */     }
/*      */     try {
/*  492 */       arrayOfDouble[11] = climaxTonality(paramArrayOfNote, paramInt, paramArrayOfInt);
/*      */     } catch (NoteListException localNoteListException9) {
/*  494 */       arrayOfDouble[11] = -1.0D;
/*      */     }
/*      */     try {
/*  497 */       arrayOfDouble[12] = noteDensity(paramArrayOfNote, paramDouble);
/*      */     } catch (NoteListException localNoteListException10) {
/*  499 */       arrayOfDouble[12] = -1.0D;
/*      */     } catch (QuantisationException localQuantisationException3) {
/*  501 */       arrayOfDouble[12] = -2.0D;
/*      */     }
/*      */     try {
/*  504 */       arrayOfDouble[13] = noteDensity(paramArrayOfNote, paramDouble);
/*      */     } catch (NoteListException localNoteListException11) {
/*  506 */       arrayOfDouble[13] = -1.0D;
/*      */     } catch (QuantisationException localQuantisationException4) {
/*  508 */       arrayOfDouble[13] = -2.0D;
/*      */     }
/*  510 */     arrayOfDouble[14] = rhythmicVariety(paramArrayOfNote);
/*      */     try {
/*  512 */       arrayOfDouble[15] = rhythmRangePerSpan(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException12) {
/*  514 */       arrayOfDouble[15] = -1.0D;
/*      */     }
/*  516 */     arrayOfDouble[16] = syncopation(paramArrayOfNote);
/*      */     try {
/*  518 */       arrayOfDouble[17] = repeatedPitchDensity(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException13) {
/*  520 */       arrayOfDouble[17] = -1.0D;
/*      */     }
/*      */     try {
/*  523 */       arrayOfDouble[18] = repeatedRhythmicValueDensity(paramArrayOfNote);
/*      */     } catch (NoteListException localNoteListException14) {
/*  525 */       arrayOfDouble[18] = -1.0D;
/*      */     }
/*      */     try {
/*  528 */       arrayOfDouble[19] = repeatedPitchPatterns(paramArrayOfNote, 3);
/*      */     } catch (NoteListException localNoteListException15) {
/*  530 */       arrayOfDouble[19] = -1.0D;
/*      */     }
/*      */     try {
/*  533 */       arrayOfDouble[20] = repeatedPitchPatterns(paramArrayOfNote, 4);
/*      */     } catch (NoteListException localNoteListException16) {
/*  535 */       arrayOfDouble[20] = -1.0D;
/*      */     }
/*      */     try {
/*  538 */       arrayOfDouble[21] = repeatedRhythmPatterns(paramArrayOfNote, 3);
/*      */     } catch (NoteListException localNoteListException17) {
/*  540 */       arrayOfDouble[21] = -1.0D;
/*      */     }
/*      */     try {
/*  543 */       arrayOfDouble[22] = repeatedRhythmPatterns(paramArrayOfNote, 4);
/*      */     } catch (NoteListException localNoteListException18) {
/*  545 */       arrayOfDouble[22] = -1.0D;
/*      */     }
/*  547 */     return arrayOfDouble;
/*      */   }
/*      */ 
/*      */   public static Hashtable getAllStatistics(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  582 */     return getAllStatistics(paramPhrase.getNoteArray(), paramDouble, paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static Hashtable getAllStatistics(Note[] paramArrayOfNote, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */   {
/*  617 */     String[] arrayOfString = getAllStatisticsAsStrings(paramArrayOfNote, paramDouble, paramInt, paramArrayOfInt);
/*      */ 
/*  619 */     Hashtable localHashtable = new Hashtable();
/*  620 */     for (int i = 0; i < featureNames.length; ++i) {
/*  621 */       localHashtable.put(featureNames[i], arrayOfString[i]);
/*      */     }
/*  623 */     return localHashtable;
/*      */   }
/*      */ 
/*      */   public static double noteDensity(Phrase paramPhrase, double paramDouble)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  646 */     return noteDensity(paramPhrase.getNoteArray(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static double noteDensity(Note[] paramArrayOfNote, double paramDouble)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  669 */     int i = quantumCount(paramArrayOfNote, paramDouble);
/*  670 */     if (i != 0) {
/*  671 */       return (noteCount(paramArrayOfNote) / i);
/*      */     }
/*  673 */     throw new NoteListException("The length of the melody should be greater than 0.");
/*      */   }
/*      */ 
/*      */   public static double pitchVariety(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/*  690 */     return pitchVariety(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double pitchVariety(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/*  707 */     int i = noteCount(paramArrayOfNote);
/*  708 */     if (i != 0) {
/*  709 */       return (distinctPitchCount(paramArrayOfNote) / i);
/*      */     }
/*  711 */     throw new NoteListException("The melody should contain at least one note.");
/*      */   }
/*      */ 
/*      */   public static double rhythmicVariety(Phrase paramPhrase)
/*      */   {
/*  726 */     return rhythmicVariety(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double rhythmicVariety(Note[] paramArrayOfNote)
/*      */   {
/*  739 */     return (distinctRhythmCount(paramArrayOfNote) / 16.0D);
/*      */   }
/*      */ 
/*      */   public static double climaxStrength(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/*  753 */     return climaxStrength(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double climaxStrength(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/*  768 */     int i = 0;
/*  769 */     int j = 0;
/*      */ 
/*  772 */     for (int l = 0; l < paramArrayOfNote.length; ++l) {
/*  773 */       int k = paramArrayOfNote[l].getPitch();
/*  774 */       if (k != -2147483648) {
/*  775 */         if (k > j) {
/*  776 */           j = k;
/*  777 */           i = 1;
/*  778 */         } else if (k == j) {
/*  779 */           ++i;
/*      */         }
/*      */       }
/*      */     }
/*  783 */     if (i != 0) {
/*  784 */       return (1.0D / i);
/*      */     }
/*  786 */     throw new NoteListException("The melody should contain at least one note.");
/*      */   }
/*      */ 
/*      */   public static double restDensity(Phrase paramPhrase, double paramDouble)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  811 */     return restDensity(paramPhrase.getNoteArray(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static double restDensity(Note[] paramArrayOfNote, double paramDouble)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  834 */     int i = quantumCount(paramArrayOfNote, paramDouble);
/*  835 */     if (i != 0) {
/*  836 */       return (restQuantumCount(paramArrayOfNote, paramDouble) / i);
/*      */     }
/*  838 */     throw new NoteListException("The length of the melody should be greater than 0.");
/*      */   }
/*      */ 
/*      */   public static double tonalDeviation(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  873 */     return tonalDeviation(paramPhrase.getNoteArray(), paramDouble, paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static double tonalDeviation(Note[] paramArrayOfNote, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException, QuantisationException
/*      */   {
/*  905 */     int i = quantumCount(paramArrayOfNote, paramDouble);
/*  906 */     if (i != 0) {
/*  907 */       return (nonScaleQuantumCount(paramArrayOfNote, paramDouble, paramInt, paramArrayOfInt) / i);
/*      */     }
/*      */ 
/*  910 */     throw new NoteListException("The length of the melody should be greater than 0.");
/*      */   }
/*      */ 
/*      */   public static double keyCenteredness(Phrase paramPhrase, double paramDouble, int paramInt)
/*      */     throws QuantisationException, NoteListException
/*      */   {
/*  939 */     return keyCenteredness(paramPhrase.getNoteArray(), paramDouble, paramInt);
/*      */   }
/*      */ 
/*      */   public static double keyCenteredness(Note[] paramArrayOfNote, double paramDouble, int paramInt)
/*      */     throws QuantisationException, NoteListException
/*      */   {
/*  966 */     int i = quantumCount(paramArrayOfNote, paramDouble);
/*  967 */     if (i > 0) {
/*  968 */       return (primaryQuantumCount(paramArrayOfNote, paramDouble, paramInt) / i);
/*      */     }
/*      */ 
/*  971 */     throw new NoteListException("The length of the melody should be greater than 0.");
/*      */   }
/*      */ 
/*      */   public static double pitchRangePerSpan(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/*  994 */     return pitchRangePerSpan(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double pitchRangePerSpan(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1016 */     double d = pitchRange(paramArrayOfNote) / 24.0D;
/* 1017 */     return ((d < 1.0D) ? d : 1.0D);
/*      */   }
/*      */ 
/*      */   public static double rhythmRangePerSpan(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 1040 */     return rhythmRangePerSpan(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double rhythmRangePerSpan(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1063 */     double d = rhythmRange(paramArrayOfNote) / 16.0D;
/* 1064 */     return ((d < 1.0D) ? d : 1.0D);
/*      */   }
/*      */ 
/*      */   public static double repeatedPitchDensity(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 1080 */     return repeatedPitchDensity(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double repeatedPitchDensity(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1096 */     int i = intervalCount(paramArrayOfNote);
/* 1097 */     if (i != 0) {
/* 1098 */       return (consecutiveIdenticalPitches(paramArrayOfNote) / i);
/*      */     }
/*      */ 
/* 1101 */     throw new NoteListException("The melody should contain at least two notes.");
/*      */   }
/*      */ 
/*      */   public static double repeatedRhythmicValueDensity(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 1120 */     return repeatedRhythmicValueDensity(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double repeatedRhythmicValueDensity(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1137 */     int i = intervalCount(paramArrayOfNote);
/* 1138 */     if (i != 0) {
/* 1139 */       return (consecutiveIdenticalRhythms(paramArrayOfNote) / i);
/*      */     }
/*      */ 
/* 1142 */     throw new NoteListException("The melody should contain at least two notes.");
/*      */   }
/*      */ 
/*      */   public static double melodicDirectionStability(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 1162 */     return melodicDirectionStability(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double melodicDirectionStability(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1181 */     int i = intervalCount(paramArrayOfNote);
/* 1182 */     if (i - 1 != 0) {
/* 1183 */       return (sameDirectionIntervalCount(paramArrayOfNote) / i);
/*      */     }
/*      */ 
/* 1186 */     throw new NoteListException("The melody should contain at least three notes.");
/*      */   }
/*      */ 
/*      */   public static double overallPitchDirection(Phrase paramPhrase)
/*      */   {
/* 1205 */     return overallPitchDirection(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double overallPitchDirection(Note[] paramArrayOfNote)
/*      */   {
/* 1222 */     double d = intervalSemitoneCount(paramArrayOfNote);
/* 1223 */     if (d != 0.0D) {
/* 1224 */       return (risingSemitoneCount(paramArrayOfNote) / d);
/*      */     }
/* 1226 */     return 0.5D;
/*      */   }
/*      */ 
/*      */   public static double movementByStep(Phrase paramPhrase, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException
/*      */   {
/* 1255 */     return movementByStep(paramPhrase.getNoteArray(), paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static double movementByStep(Note[] paramArrayOfNote, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException
/*      */   {
/* 1283 */     int i = intervalCount(paramArrayOfNote);
/* 1284 */     if (i > 0) {
/* 1285 */       return (stepIntervalCount(paramArrayOfNote, paramInt, paramArrayOfInt) / i);
/*      */     }
/*      */ 
/* 1288 */     throw new NoteListException("The melody should contain at least two notes.");
/*      */   }
/*      */ 
/*      */   public static double dissonance(Phrase paramPhrase)
/*      */   {
/* 1311 */     return dissonance(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double dissonance(Note[] paramArrayOfNote)
/*      */   {
/* 1333 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 1334 */     int i = 0;
/* 1335 */     for (int j = 0; j < arrayOfInt.length; ++j) {
/* 1336 */       if (arrayOfInt[j] > 127) {
/* 1337 */         arrayOfInt[j] -= 255;
/*      */       }
/* 1339 */       arrayOfInt[j] = Math.abs(arrayOfInt[j]);
/* 1340 */       if (arrayOfInt[j] > 12)
/* 1341 */         i += 2;
/*      */       else {
/* 1343 */         i += rateDissonance(arrayOfInt[j]);
/*      */       }
/*      */     }
/* 1346 */     return (i / 2.0D * arrayOfInt.length);
/*      */   }
/*      */ 
/*      */   public static double leapCompensation(Phrase paramPhrase)
/*      */   {
/* 1361 */     return leapCompensation(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double leapCompensation(Note[] paramArrayOfNote)
/*      */   {
/* 1376 */     int i = bigJumpCount(paramArrayOfNote);
/* 1377 */     if (i != 0) {
/* 1378 */       return ((i - bigJumpFollowedByStepBackCount(paramArrayOfNote)) / i);
/*      */     }
/*      */ 
/* 1381 */     return 0.0D;
/*      */   }
/*      */ 
/*      */   public static double syncopation(Phrase paramPhrase)
/*      */   {
/* 1396 */     return syncopation(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double syncopation(Note[] paramArrayOfNote)
/*      */   {
/* 1414 */     int i = 0;
/* 1415 */     double d1 = 0.0D;
/*      */ 
/* 1418 */     for (int j = 0; j < paramArrayOfNote.length; ++j) {
/* 1419 */       double d2 = paramArrayOfNote[j].getRhythmValue();
/* 1420 */       if ((d2 >= 1.0D) && (paramArrayOfNote[j].getPitch() != -2147483648) && (d1 % 1.0D != 0.0D))
/*      */       {
/* 1423 */         ++i;
/*      */       }
/* 1425 */       d1 += d2;
/*      */     }
/* 1427 */     return (i / Math.floor(rhythmValueCount(paramArrayOfNote) - 1.0D));
/*      */   }
/*      */ 
/*      */   public static double repeatedPitchPatterns(Phrase paramPhrase, int paramInt)
/*      */     throws NoteListException
/*      */   {
/* 1444 */     return repeatedPitchPatterns(paramPhrase.getNoteArray(), paramInt);
/*      */   }
/*      */ 
/*      */   public static double repeatedPitchPatterns(Note[] paramArrayOfNote, int paramInt)
/*      */     throws NoteListException
/*      */   {
/* 1461 */     int i = intervalCount(paramArrayOfNote) - paramInt;
/* 1462 */     if (i > 0) {
/* 1463 */       return (pitchPatternCount(paramArrayOfNote, paramInt) / i);
/*      */     }
/*      */ 
/* 1466 */     throw new NoteListException("The melody must contain more intervals than the size of the pattern being searched for.");
/*      */   }
/*      */ 
/*      */   public static double repeatedRhythmPatterns(Phrase paramPhrase, int paramInt)
/*      */     throws NoteListException
/*      */   {
/* 1486 */     return repeatedRhythmPatterns(paramPhrase.getNoteArray(), paramInt);
/*      */   }
/*      */ 
/*      */   public static double repeatedRhythmPatterns(Note[] paramArrayOfNote, int paramInt)
/*      */     throws NoteListException
/*      */   {
/* 1503 */     int i = intervalCount(paramArrayOfNote) - paramInt;
/* 1504 */     if (i > 0) {
/* 1505 */       return (rhythmPatternCount(paramArrayOfNote, paramInt) / i);
/*      */     }
/*      */ 
/* 1508 */     throw new NoteListException("The melody must contain more intervals than the size of the pattern being searched for.");
/*      */   }
/*      */ 
/*      */   public static double climaxPosition(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 1527 */     return climaxPosition(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double climaxPosition(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 1543 */     if (noteCount(paramArrayOfNote) > 0) {
/* 1544 */       double d1 = 0.0D;
/* 1545 */       int i = 0;
/* 1546 */       int j = 0;
/*      */ 
/* 1548 */       for (int k = 0; k < paramArrayOfNote.length; ++k) {
/* 1549 */         d1 += paramArrayOfNote[k].getRhythmValue();
/* 1550 */         int l = paramArrayOfNote[k].getPitch();
/* 1551 */         if ((l == -2147483648) || 
/* 1552 */           (l < i)) continue;
/* 1553 */         i = l;
/* 1554 */         j = k;
/*      */       }
/*      */ 
/* 1558 */       double d2 = 0.0D;
/* 1559 */       for (int i1 = 0; i1 < j - 1; ++i1) {
/* 1560 */         d2 += paramArrayOfNote[i1].getRhythmValue();
/*      */       }
/* 1562 */       return (d2 / d1);
/*      */     }
/* 1564 */     throw new NoteListException("The melody should contain at least one note.");
/*      */   }
/*      */ 
/*      */   public static double climaxTonality(Phrase paramPhrase, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException
/*      */   {
/* 1600 */     return climaxTonality(paramPhrase.getNoteArray(), paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static double climaxTonality(Note[] paramArrayOfNote, int paramInt, int[] paramArrayOfInt)
/*      */     throws NoteListException
/*      */   {
/* 1635 */     if (noteCount(paramArrayOfNote) > 0) {
/* 1636 */       int i = 0;
/*      */ 
/* 1639 */       for (int k = 0; k < paramArrayOfNote.length; ++k) {
/* 1640 */         int j = paramArrayOfNote[k].getPitch();
/* 1641 */         if ((j == -2147483648) || 
/* 1642 */           (j <= i)) continue;
/* 1643 */         i = j;
/*      */       }
/*      */ 
/* 1648 */       i = pitchToDegree(i, paramInt);
/*      */ 
/* 1650 */       if (isElementOf(i, PRIMARY_NOTES))
/* 1651 */         return 0.0D;
/* 1652 */       if (isElementOf(i, paramArrayOfInt)) {
/* 1653 */         return 0.5D;
/*      */       }
/* 1655 */       return 1.0D;
/*      */     }
/*      */ 
/* 1658 */     throw new NoteListException("The melody should contain at least one note.");
/*      */   }
/*      */ 
/*      */   public static int noteCount(Phrase paramPhrase)
/*      */   {
/* 1672 */     return noteCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int noteCount(Note[] paramArrayOfNote)
/*      */   {
/* 1684 */     int i = 0;
/* 1685 */     for (int j = 0; j < paramArrayOfNote.length; ++j) {
/* 1686 */       if (paramArrayOfNote[j].getPitch() != -2147483648) {
/* 1687 */         ++i;
/*      */       }
/*      */     }
/* 1690 */     return i;
/*      */   }
/*      */ 
/*      */   public static int quantumCount(Phrase paramPhrase, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 1709 */     return quantumCount(paramPhrase.getNoteArray(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static int quantumCount(Note[] paramArrayOfNote, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 1729 */     if (isQuantised(paramArrayOfNote, paramDouble)) {
/* 1730 */       int i = 0;
/* 1731 */       for (int j = 0; j < paramArrayOfNote.length; ++j) {
/* 1732 */         i += (int)(paramArrayOfNote[j].getRhythmValue() / paramDouble);
/*      */       }
/* 1734 */       return i;
/*      */     }
/* 1736 */     throw new QuantisationException("Every rhythm value must be a multiple of the quantum duration.");
/*      */   }
/*      */ 
/*      */   public static int distinctPitchCount(Phrase paramPhrase)
/*      */   {
/* 1751 */     return distinctPitchCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int distinctPitchCount(Note[] paramArrayOfNote)
/*      */   {
/* 1763 */     int[] arrayOfInt = new int[paramArrayOfNote.length];
/* 1764 */     int i = 0;
/*      */ 
/* 1767 */     for (int k = 0; k < paramArrayOfNote.length; ++k) {
/* 1768 */       int j = paramArrayOfNote[k].getPitch();
/* 1769 */       if ((j == -2147483648) || 
/* 1770 */         (isElementOf(j, arrayOfInt, i))) continue;
/* 1771 */       arrayOfInt[i] = j;
/* 1772 */       ++i;
/*      */     }
/*      */ 
/* 1776 */     return i;
/*      */   }
/*      */ 
/*      */   public static int distinctRhythmCount(Phrase paramPhrase)
/*      */   {
/* 1787 */     return distinctRhythmCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int distinctRhythmCount(Note[] paramArrayOfNote)
/*      */   {
/* 1799 */     double[] arrayOfDouble = new double[paramArrayOfNote.length];
/* 1800 */     int i = 0;
/*      */ 
/* 1802 */     for (int j = 0; j < paramArrayOfNote.length; ++j) {
/* 1803 */       if (paramArrayOfNote[j].getPitch() <= -2147483648) {
/* 1804 */         double d = paramArrayOfNote[j].getRhythmValue();
/* 1805 */         if (!(isElementOf(d, arrayOfDouble, i))) {
/* 1806 */           arrayOfDouble[i] = d;
/* 1807 */           ++i;
/*      */         }
/*      */       }
/*      */     }
/* 1811 */     return i;
/*      */   }
/*      */ 
/*      */   public static int restQuantumCount(Phrase paramPhrase, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 1832 */     return restQuantumCount(paramPhrase.getNoteArray(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static int restQuantumCount(Note[] paramArrayOfNote, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 1853 */     if (isQuantised(paramArrayOfNote, paramDouble)) {
/* 1854 */       int i = 0;
/* 1855 */       for (int j = 0; j < paramArrayOfNote.length; ++j) {
/* 1856 */         if (paramArrayOfNote[j].getPitch() == -2147483648) {
/* 1857 */           i += (int)(paramArrayOfNote[j].getRhythmValue() / paramDouble);
/*      */         }
/*      */       }
/* 1860 */       return i;
/*      */     }
/* 1862 */     throw new QuantisationException("Every rhythm value must be a multiple of the quantum duration.");
/*      */   }
/*      */ 
/*      */   public static int nonScaleQuantumCount(Phrase paramPhrase, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */     throws QuantisationException
/*      */   {
/* 1894 */     return nonScaleQuantumCount(paramPhrase.getNoteArray(), paramDouble, paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static int nonScaleQuantumCount(Note[] paramArrayOfNote, double paramDouble, int paramInt, int[] paramArrayOfInt)
/*      */     throws QuantisationException
/*      */   {
/* 1924 */     if (isQuantised(paramArrayOfNote, paramDouble)) {
/* 1925 */       int i = 0;
/*      */ 
/* 1927 */       for (int k = 0; k < paramArrayOfNote.length; ++k) {
/* 1928 */         int j = paramArrayOfNote[k].getPitch();
/* 1929 */         if ((j == -2147483648) || (isElementOf(pitchToDegree(j, paramInt), paramArrayOfInt))) {
/*      */           continue;
/*      */         }
/* 1932 */         i += (int)(paramArrayOfNote[k].getRhythmValue() / paramDouble);
/*      */       }
/*      */ 
/* 1936 */       return i;
/*      */     }
/* 1938 */     throw new QuantisationException("Every rhythm value must be a multiple of the quantum duration.");
/*      */   }
/*      */ 
/*      */   public static int primaryQuantumCount(Phrase paramPhrase, double paramDouble, int paramInt)
/*      */     throws QuantisationException
/*      */   {
/* 1971 */     return primaryQuantumCount(paramPhrase.getNoteArray(), paramDouble, paramInt);
/*      */   }
/*      */ 
/*      */   public static int primaryQuantumCount(Note[] paramArrayOfNote, double paramDouble, int paramInt)
/*      */     throws QuantisationException
/*      */   {
/* 2000 */     if (isQuantised(paramArrayOfNote, paramDouble)) {
/* 2001 */       int i = 0;
/*      */ 
/* 2004 */       for (int k = 0; k < paramArrayOfNote.length; ++k) {
/* 2005 */         int j = paramArrayOfNote[k].getPitch();
/* 2006 */         if ((j != -2147483648) && (!(isElementOf(pitchToDegree(j, paramInt), PRIMARY_NOTES)))) {
/*      */           continue;
/*      */         }
/* 2009 */         i += (int)(paramArrayOfNote[k].getRhythmValue() / paramDouble);
/*      */       }
/*      */ 
/* 2013 */       return i;
/*      */     }
/* 2015 */     throw new QuantisationException("Every rhythm value must be a multiple of the quantum duration.");
/*      */   }
/*      */ 
/*      */   public static int pitchRange(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 2033 */     return pitchRange(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int pitchRange(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 2049 */     int i = 0;
/* 2050 */     int j = 127;
/*      */ 
/* 2053 */     for (int l = 0; l < paramArrayOfNote.length; ++l) {
/* 2054 */       int k = paramArrayOfNote[l].getPitch();
/* 2055 */       if (k != -2147483648) {
/* 2056 */         if (k > i)
/* 2057 */           i = k;
/* 2058 */         else if (k < j) {
/* 2059 */           j = k;
/*      */         }
/*      */       }
/*      */     }
/* 2063 */     if ((i != 0) && (j != 127)) {
/* 2064 */       return (i - j);
/*      */     }
/* 2066 */     throw new NoteListException("There are no notes in the melody.");
/*      */   }
/*      */ 
/*      */   public static double rhythmRange(Phrase paramPhrase)
/*      */     throws NoteListException
/*      */   {
/* 2082 */     return rhythmRange(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double rhythmRange(Note[] paramArrayOfNote)
/*      */     throws NoteListException
/*      */   {
/* 2098 */     double d1 = 0.0D;
/* 2099 */     double d2 = 1.7976931348623157E+308D;
/*      */ 
/* 2102 */     for (int i = 0; i < paramArrayOfNote.length; ++i) {
/* 2103 */       double d3 = paramArrayOfNote[i].getRhythmValue();
/* 2104 */       if (paramArrayOfNote[i].getPitch() != -2147483648) {
/* 2105 */         if (d3 > d1)
/* 2106 */           d1 = d3;
/* 2107 */         else if (d3 < d2) {
/* 2108 */           d2 = d3;
/*      */         }
/*      */       }
/*      */     }
/* 2112 */     if ((d1 != 0.0D) && (d2 != 1.7976931348623157E+308D))
/*      */     {
/* 2114 */       return (d1 / d2);
/*      */     }
/* 2116 */     throw new NoteListException("There are no notes in the melody.");
/*      */   }
/*      */ 
/*      */   public static int consecutiveIdenticalPitches(Phrase paramPhrase)
/*      */   {
/* 2133 */     return consecutiveIdenticalPitches(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int consecutiveIdenticalPitches(Note[] paramArrayOfNote)
/*      */   {
/* 2149 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2150 */     int i = 0;
/* 2151 */     for (int j = 0; j < arrayOfInt.length; ++j) {
/* 2152 */       if ((arrayOfInt[j] == 0) || (arrayOfInt[j] == 255)) {
/* 2153 */         ++i;
/*      */       }
/*      */     }
/* 2156 */     return i;
/*      */   }
/*      */ 
/*      */   public static int consecutiveIdenticalRhythms(Phrase paramPhrase)
/*      */   {
/* 2174 */     return consecutiveIdenticalRhythms(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int consecutiveIdenticalRhythms(Note[] paramArrayOfNote)
/*      */   {
/* 2187 */     double[] arrayOfDouble = rhythmIntervals(paramArrayOfNote);
/* 2188 */     int i = 0;
/* 2189 */     for (int j = 0; j < arrayOfDouble.length - 1; ++j) {
/* 2190 */       if ((((arrayOfDouble[j] != 1.0D) || (arrayOfDouble[(j + 1)] <= 0.0D))) && (((arrayOfDouble[j] != -1.0D) || (arrayOfDouble[(j + 1)] >= 0.0D))))
/*      */         continue;
/* 2192 */       ++i;
/*      */     }
/*      */ 
/* 2198 */     if (arrayOfDouble[(arrayOfDouble.length - 1)] == 1.0D) {
/* 2199 */       ++i;
/*      */     }
/* 2201 */     return i;
/*      */   }
/*      */ 
/*      */   public static int sameDirectionIntervalCount(Phrase paramPhrase)
/*      */   {
/* 2215 */     return sameDirectionIntervalCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int sameDirectionIntervalCount(Note[] paramArrayOfNote)
/*      */   {
/* 2229 */     int i = 0;
/* 2230 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2231 */     if (arrayOfInt.length > 0)
/*      */     {
/* 2234 */       if (arrayOfInt[0] > 127) {
/* 2235 */         arrayOfInt[0] -= 255;
/*      */       }
/* 2237 */       for (int j = 1; j < arrayOfInt.length; ++j)
/*      */       {
/* 2240 */         if (arrayOfInt[j] > 127) {
/* 2241 */           arrayOfInt[j] -= 255;
/*      */         }
/*      */ 
/* 2244 */         if ((((arrayOfInt[j] <= 0) || (arrayOfInt[(j - 1)] <= 0))) && (((arrayOfInt[j] != 0) || (arrayOfInt[(j - 1)] != 0))) && (((arrayOfInt[j] >= 0) || (arrayOfInt[(j - 1)] >= 0)))) {
/*      */           continue;
/*      */         }
/* 2247 */         ++i;
/*      */       }
/*      */     }
/*      */ 
/* 2251 */     return i;
/*      */   }
/*      */ 
/*      */   public static int intervalCount(Phrase paramPhrase)
/*      */   {
/* 2263 */     return intervalCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int intervalCount(Note[] paramArrayOfNote)
/*      */   {
/* 2275 */     int i = noteCount(paramArrayOfNote) - 1;
/* 2276 */     return ((i < 1) ? 0 : i);
/*      */   }
/*      */ 
/*      */   public static int[] pitchIntervals(Phrase paramPhrase)
/*      */   {
/* 2302 */     return pitchIntervals(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int[] pitchIntervals(Note[] paramArrayOfNote)
/*      */   {
/* 2328 */     int i = intervalCount(paramArrayOfNote);
/* 2329 */     if (i > 0) {
/* 2330 */       int[] arrayOfInt = new int[i];
/* 2331 */       int j = -1;
/*      */ 
/* 2334 */       while (paramArrayOfNote[(++j)].getPitch() == -2147483648);
/* 2336 */       int k = paramArrayOfNote[j].getPitch();
/*      */ 
/* 2339 */       for (int i1 = 0; i1 < arrayOfInt.length; ++i1) {
/* 2340 */         int l = paramArrayOfNote[(++j)].getPitch();
/*      */ 
/* 2344 */         while (l == -2147483648)
/*      */         {
/* 2347 */           if (paramArrayOfNote[(j - 1)].getPitch() != -2147483648) {
/* 2348 */             arrayOfInt[i1] += 255;
/*      */           }
/*      */ 
/* 2351 */           l = paramArrayOfNote[(++j)].getPitch();
/*      */         }
/*      */ 
/* 2354 */         arrayOfInt[i1] += l - k;
/* 2355 */         k = l;
/*      */       }
/* 2357 */       return arrayOfInt;
/*      */     }
/* 2359 */     return new int[0];
/*      */   }
/*      */ 
/*      */   public static double[] rhythmIntervals(Phrase paramPhrase)
/*      */   {
/* 2377 */     return rhythmIntervals(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double[] rhythmIntervals(Note[] paramArrayOfNote)
/*      */   {
/* 2394 */     int i = paramArrayOfNote.length - 1;
/*      */ 
/* 2397 */     int j = paramArrayOfNote.length - 1;
/* 2398 */     while ((paramArrayOfNote[j].getPitch() == -2147483648) && (j > -1))
/*      */     {
/* 2400 */       --i;
/*      */ 
/* 2399 */       --j;
/*      */     }
/*      */ 
/* 2403 */     if (i > 0) {
/* 2404 */       double[] arrayOfDouble = new double[i];
/* 2405 */       for (int k = 0; k < arrayOfDouble.length; ++k) {
/* 2406 */         arrayOfDouble[k] = (paramArrayOfNote[(k + 1)].getRhythmValue() / paramArrayOfNote[k].getRhythmValue());
/*      */ 
/* 2410 */         if (paramArrayOfNote[k].getPitch() == -2147483648) {
/* 2411 */           arrayOfDouble[k] *= -1.0D;
/*      */         }
/*      */       }
/* 2414 */       return arrayOfDouble;
/*      */     }
/* 2416 */     return new double[0];
/*      */   }
/*      */ 
/*      */   public static int intervalSemitoneCount(Phrase paramPhrase)
/*      */   {
/* 2428 */     return intervalSemitoneCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int intervalSemitoneCount(Note[] paramArrayOfNote)
/*      */   {
/* 2439 */     int i = 0;
/* 2440 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2441 */     for (int j = 0; j < arrayOfInt.length; ++j) {
/* 2442 */       i += Math.abs(removeRestMarker(arrayOfInt[j]));
/*      */     }
/* 2444 */     return i;
/*      */   }
/*      */ 
/*      */   public static int risingSemitoneCount(Phrase paramPhrase)
/*      */   {
/* 2455 */     return risingSemitoneCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int risingSemitoneCount(Note[] paramArrayOfNote)
/*      */   {
/* 2466 */     int i = 0;
/* 2467 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2468 */     for (int j = 0; j < arrayOfInt.length; ++j) {
/* 2469 */       arrayOfInt[j] = removeRestMarker(arrayOfInt[j]);
/* 2470 */       if (arrayOfInt[j] > 0) {
/* 2471 */         i += arrayOfInt[j];
/*      */       }
/*      */     }
/* 2474 */     return i;
/*      */   }
/*      */ 
/*      */   public static int stepIntervalCount(Phrase paramPhrase, int paramInt, int[] paramArrayOfInt)
/*      */   {
/* 2499 */     return stepIntervalCount(paramPhrase.getNoteArray(), paramInt, paramArrayOfInt);
/*      */   }
/*      */ 
/*      */   public static int stepIntervalCount(Note[] paramArrayOfNote, int paramInt, int[] paramArrayOfInt)
/*      */   {
/* 2524 */     int i = intervalCount(paramArrayOfNote);
/* 2525 */     if (i > 0) {
/* 2526 */       int j = -1;
/* 2527 */       int k = 0;
/*      */ 
/* 2530 */       while (paramArrayOfNote[(++j)].getPitch() == -2147483648);
/* 2532 */       int l = paramArrayOfNote[j].getPitch();
/*      */ 
/* 2535 */       for (int i2 = 0; i2 < i; ++i2) {
/* 2536 */         if (paramArrayOfNote[(++j)].getPitch() != -2147483648);
/* 2538 */         int i1 = paramArrayOfNote[j].getPitch();
/*      */ 
/* 2540 */         if ((Math.abs(i1 - l) < 3) && (isElementOf(pitchToDegree(i1, paramInt), paramArrayOfInt)) && (isElementOf(pitchToDegree(l, paramInt), paramArrayOfInt)))
/*      */         {
/* 2545 */           ++k;
/*      */         }
/* 2547 */         l = i1;
/*      */       }
/* 2549 */       return k;
/*      */     }
/* 2551 */     return 0;
/*      */   }
/*      */ 
/*      */   public static int bigJumpFollowedByStepBackCount(Phrase paramPhrase)
/*      */   {
/* 2566 */     return bigJumpFollowedByStepBackCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int bigJumpFollowedByStepBackCount(Note[] paramArrayOfNote)
/*      */   {
/* 2580 */     int i = 0;
/* 2581 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2582 */     if (arrayOfInt.length > 0) {
/* 2583 */       arrayOfInt[0] = removeRestMarker(arrayOfInt[0]);
/* 2584 */       for (int j = 1; j < arrayOfInt.length - 1; ++j) {
/* 2585 */         arrayOfInt[j] = removeRestMarker(arrayOfInt[j]);
/* 2586 */         if ((((arrayOfInt[(j - 1)] < 8) || (arrayOfInt[j] >= 0) || (arrayOfInt[j] < -8))) && (((arrayOfInt[(j - 1)] > -8) || (arrayOfInt[j] <= 0) || (arrayOfInt[j] > 8))))
/*      */         {
/*      */           continue;
/*      */         }
/*      */ 
/* 2592 */         ++i;
/*      */       }
/*      */ 
/* 2595 */       return i;
/*      */     }
/* 2597 */     return 0;
/*      */   }
/*      */ 
/*      */   public static int bigJumpCount(Phrase paramPhrase)
/*      */   {
/* 2609 */     return bigJumpCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static int bigJumpCount(Note[] paramArrayOfNote)
/*      */   {
/* 2621 */     int i = 0;
/* 2622 */     int[] arrayOfInt = pitchIntervals(paramArrayOfNote);
/* 2623 */     if (arrayOfInt.length > 0) {
/* 2624 */       for (int j = 0; j < arrayOfInt.length - 1; ++j) {
/* 2625 */         arrayOfInt[j] = removeRestMarker(arrayOfInt[j]);
/* 2626 */         if (Math.abs(arrayOfInt[j]) >= 8) {
/* 2627 */           ++i;
/*      */         }
/*      */       }
/* 2630 */       return i;
/*      */     }
/* 2632 */     return i;
/*      */   }
/*      */ 
/*      */   public static int pitchPatternCount(Phrase paramPhrase, int paramInt)
/*      */   {
/* 2650 */     return pitchPatternCount(paramPhrase.getNoteArray(), paramInt);
/*      */   }
/*      */ 
/*      */   public static int pitchPatternCount(Note[] paramArrayOfNote, int paramInt)
/*      */   {
/* 2667 */     int i = 0;
/* 2668 */     int[] arrayOfInt1 = pitchIntervals(paramArrayOfNote);
/*      */ 
/* 2670 */     if (arrayOfInt1.length > paramInt) {
/* 2671 */       int[][] arrayOfInt = new int[arrayOfInt1.length - paramInt][paramInt];
/*      */ 
/* 2673 */       int j = 0;
/*      */ 
/* 2675 */       for (int k = 0; k < arrayOfInt1.length - paramInt; ++k) {
/* 2676 */         int[] arrayOfInt2 = new int[paramInt];
/*      */ 
/* 2678 */         for (int l = 0; l < paramInt; ++l) {
/* 2679 */           arrayOfInt2[l] = arrayOfInt1[(k + l)];
/*      */         }
/* 2681 */         if (!(isAlreadyMatched(arrayOfInt, arrayOfInt2, j))) {
/* 2682 */           l = k + 1;
/* 2683 */           while (l < arrayOfInt1.length - paramInt + 1)
/*      */           {
/* 2685 */             if (matchPattern(arrayOfInt1, k, l, paramInt)) {
/* 2686 */               if ((j == 0) || (arrayOfInt[(j - 1)] != arrayOfInt2)) {
/* 2687 */                 arrayOfInt[(j++)] = arrayOfInt2;
/*      */               }
/* 2689 */               ++i;
/*      */             }
/* 2684 */             ++l;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2695 */     return i;
/*      */   }
/*      */ 
/*      */   public static int rhythmPatternCount(Phrase paramPhrase, int paramInt)
/*      */   {
/* 2713 */     return rhythmPatternCount(paramPhrase.getNoteArray(), paramInt);
/*      */   }
/*      */ 
/*      */   public static int rhythmPatternCount(Note[] paramArrayOfNote, int paramInt)
/*      */   {
/* 2732 */     int i = 0;
/* 2733 */     double[] arrayOfDouble1 = rhythmIntervals(paramArrayOfNote);
/*      */ 
/* 2735 */     if (arrayOfDouble1.length > paramInt) {
/* 2736 */       double[][] arrayOfDouble = new double[arrayOfDouble1.length - paramInt][paramInt];
/*      */ 
/* 2738 */       int j = 0;
/*      */ 
/* 2740 */       for (int k = 0; k < arrayOfDouble1.length - paramInt; ++k) {
/* 2741 */         double[] arrayOfDouble2 = new double[paramInt];
/*      */ 
/* 2743 */         for (int l = 0; l < paramInt; ++l) {
/* 2744 */           arrayOfDouble2[l] = arrayOfDouble1[(k + l)];
/*      */         }
/* 2746 */         if (!(isAlreadyMatched(arrayOfDouble, arrayOfDouble2, j))) {
/* 2747 */           l = k + 1;
/* 2748 */           while (l < arrayOfDouble1.length - paramInt + 1)
/*      */           {
/* 2750 */             if (matchPattern(arrayOfDouble1, k, l, paramInt)) {
/* 2751 */               if ((j == 0) || (arrayOfDouble[(j - 1)] != arrayOfDouble2)) {
/* 2752 */                 arrayOfDouble[(j++)] = arrayOfDouble2;
/*      */               }
/* 2754 */               ++i;
/*      */             }
/* 2749 */             ++l;
/*      */           }
/*      */ 
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2760 */     return i;
/*      */   }
/*      */ 
/*      */   public static double rhythmValueCount(Phrase paramPhrase)
/*      */   {
/* 2770 */     return rhythmValueCount(paramPhrase.getNoteArray());
/*      */   }
/*      */ 
/*      */   public static double rhythmValueCount(Note[] paramArrayOfNote)
/*      */   {
/* 2780 */     double d = 0.0D;
/* 2781 */     for (int i = 0; i < paramArrayOfNote.length; ++i) {
/* 2782 */       d += paramArrayOfNote[i].getRhythmValue();
/*      */     }
/* 2784 */     return d;
/*      */   }
/*      */ 
/*      */   public static int removeRestMarker(int paramInt)
/*      */   {
/* 2795 */     return ((paramInt > 127) ? paramInt - 255 : paramInt);
/*      */   }
/*      */ 
/*      */   public static boolean isQuantised(Phrase paramPhrase, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 2819 */     return isQuantised(paramPhrase.getNoteArray(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static boolean isQuantised(Note[] paramArrayOfNote, double paramDouble)
/*      */     throws QuantisationException
/*      */   {
/* 2841 */     if (paramDouble > 0.0D) {
/* 2842 */       for (int i = 0; i < paramArrayOfNote.length; ++i) {
/* 2843 */         if (paramArrayOfNote[i].getRhythmValue() % paramDouble != 0.0D) {
/* 2844 */           return false;
/*      */         }
/*      */       }
/* 2847 */       return true;
/*      */     }
/* 2849 */     throw new QuantisationException("The quantum duration must be greater than zero.");
/*      */   }
/*      */ 
/*      */   private static boolean matchPattern(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2870 */     int i = 1;
/* 2871 */     for (int j = 0; j < paramInt3; ++j) {
/* 2872 */       if (paramArrayOfInt[(paramInt1 + j)] != paramArrayOfInt[(paramInt2 + j)]) {
/* 2873 */         i = 0;
/*      */       }
/*      */     }
/* 2876 */     return i;
/*      */   }
/*      */ 
/*      */   private static boolean matchPattern(double[] paramArrayOfDouble, int paramInt1, int paramInt2, int paramInt3)
/*      */   {
/* 2895 */     int i = 1;
/* 2896 */     for (int j = 0; j < paramInt3; ++j) {
/* 2897 */       if (paramArrayOfDouble[(paramInt1 + j)] != paramArrayOfDouble[(paramInt2 + j)]) {
/* 2898 */         i = 0;
/*      */       }
/*      */     }
/* 2901 */     return i;
/*      */   }
/*      */ 
/*      */   private static boolean isAlreadyMatched(int[][] paramArrayOfInt, int[] paramArrayOfInt1, int paramInt)
/*      */   {
/* 2917 */     for (int i = 0; i < paramInt; ++i) {
/* 2918 */       for (int j = 0; j < paramArrayOfInt1.length; ++j) {
/* 2919 */         if (paramArrayOfInt[i][j] != paramArrayOfInt1[j]) {
/*      */           break label41;
/*      */         }
/*      */       }
/* 2923 */       label41: return true;
/*      */     }
/* 2925 */     return false;
/*      */   }
/*      */ 
/*      */   private static boolean isAlreadyMatched(double[][] paramArrayOfDouble, double[] paramArrayOfDouble1, int paramInt)
/*      */   {
/* 2941 */     for (int i = 0; i < paramInt; ++i) {
/* 2942 */       for (int j = 0; j < paramArrayOfDouble1.length; ++j) {
/* 2943 */         if (paramArrayOfDouble[i][j] != paramArrayOfDouble1[j]) {
/*      */           break label42;
/*      */         }
/*      */       }
/* 2947 */       label42: return true;
/*      */     }
/* 2949 */     return false;
/*      */   }
/*      */ 
/*      */   private static int rateDissonance(int paramInt)
/*      */   {
/* 2964 */     for (int i = 0; i < GOOD_INTERVALS.length; ++i) {
/* 2965 */       if (paramInt == GOOD_INTERVALS[i]) {
/* 2966 */         return 0;
/*      */       }
/*      */     }
/* 2969 */     for (i = 0; i < BAD_INTERVALS.length; ++i) {
/* 2970 */       if (paramInt == BAD_INTERVALS[i]) {
/* 2971 */         return 2;
/*      */       }
/*      */     }
/* 2974 */     return 1;
/*      */   }
/*      */ 
/*      */   private static boolean isElementOf(int paramInt, int[] paramArrayOfInt)
/*      */   {
/* 2985 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/* 2986 */       if (paramArrayOfInt[i] == paramInt) {
/* 2987 */         return true;
/*      */       }
/*      */     }
/* 2990 */     return false;
/*      */   }
/*      */ 
/*      */   private static boolean isElementOf(int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/* 3005 */     for (int i = 0; i < paramInt2; ++i) {
/* 3006 */       if (paramArrayOfInt[i] == paramInt1) {
/* 3007 */         return true;
/*      */       }
/*      */     }
/* 3010 */     return false;
/*      */   }
/*      */ 
/*      */   private static boolean isElementOf(double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*      */   {
/* 3024 */     for (int i = 0; i < paramInt; ++i) {
/* 3025 */       if (paramArrayOfDouble[i] == paramDouble) {
/* 3026 */         return true;
/*      */       }
/*      */     }
/* 3029 */     return false;
/*      */   }
/*      */ 
/*      */   public static int pitchToDegree(int paramInt1, int paramInt2)
/*      */   {
/* 3034 */     paramInt1 -= paramInt2;
/*      */ 
/* 3037 */     if (paramInt1 < 0)
/*      */     {
/* 3041 */       paramInt1 += (-paramInt1 / 12 + 1) * 12;
/*      */     }
/*      */ 
/* 3045 */     return (paramInt1 % 12);
/*      */   }
/*      */ 
/*      */   public static boolean isScale(Note paramNote, int paramInt, int[] paramArrayOfInt) {
/* 3049 */     int i = paramNote.getPitch();
/* 3050 */     if (i == -2147483648) {
/* 3051 */       return true;
/*      */     }
/* 3053 */     return isElementOf(pitchToDegree(i, paramInt), paramArrayOfInt, paramArrayOfInt.length);
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.PhraseAnalysis
 * JD-Core Version:    0.5.3
 */