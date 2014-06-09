/*     */ package jm.util;
/*     */ 
/*     */ import java.lang.reflect.Field;
/*     */ import jm.constants.Pitches;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Part;
/*     */ import jm.music.data.Phrase;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class Convert
/*     */ {
/*     */   public static final String DEFAULT_SEPARATOR = ",";
/*     */   public static final String LEFT_BRACKET = "[";
/*     */   public static final String RIGHT_BRACKET = "]";
/*     */ 
/*     */   public static Phrase pitchAndRhythmStringToPhrase(String paramString)
/*     */   {
/* 112 */     StringProcessor localStringProcessor = new StringProcessor(paramString);
/* 113 */     Phrase localPhrase = new Phrase();
/*     */     while (true) {
/*     */       try {
/* 116 */         localPhrase.addNote(new Note((int)localStringProcessor.getNextRhythm(), localStringProcessor.getNextRhythm()));
/*     */       }
/*     */       catch (EOSException localEOSException)
/*     */       {
/*     */       }
/*     */     }
/* 122 */     return localPhrase;
/*     */   }
/*     */ 
/*     */   public static String phraseToPitchAndRhythmString(Phrase paramPhrase)
/*     */   {
/* 133 */     Note[] arrayOfNote = paramPhrase.getNoteArray();
/*     */ 
/* 139 */     StringBuffer localStringBuffer = new StringBuffer(arrayOfNote.length * 10);
/*     */ 
/* 142 */     for (int i = 0; i < arrayOfNote.length - 1; ++i)
/*     */     {
/* 144 */       localStringBuffer.append(arrayOfNote[i].getPitch());
/* 145 */       localStringBuffer.append(",");
/* 146 */       localStringBuffer.append(limitDecimalPlaces(arrayOfNote[i].getRhythmValue(), 3));
/*     */ 
/* 149 */       localStringBuffer.append(",");
/*     */     }
/*     */ 
/* 152 */     if (arrayOfNote.length > 0)
/*     */     {
/* 155 */       localStringBuffer.append(arrayOfNote[(arrayOfNote.length - 1)].getPitch());
/* 156 */       localStringBuffer.append(",");
/* 157 */       localStringBuffer.append(limitDecimalPlaces(arrayOfNote[(arrayOfNote.length - 1)].getRhythmValue(), 3));
/*     */     }
/*     */ 
/* 164 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   public static Phrase pitchRhythmAndDynamicStringToPhrase(String paramString)
/*     */   {
/* 175 */     StringProcessor localStringProcessor = new StringProcessor(paramString);
/* 176 */     Phrase localPhrase = new Phrase();
/*     */     while (true) {
/*     */       try {
/* 179 */         localPhrase.addNote(new Note((int)localStringProcessor.getNextRhythm(), localStringProcessor.getNextRhythm(), (int)localStringProcessor.getNextRhythm()));
/*     */       }
/*     */       catch (EOSException localEOSException)
/*     */       {
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 187 */     return localPhrase;
/*     */   }
/*     */ 
/*     */   public static String phraseToPitchRhythmAndDynamicString(Phrase paramPhrase)
/*     */   {
/* 200 */     Note[] arrayOfNote = paramPhrase.getNoteArray();
/*     */ 
/* 206 */     StringBuffer localStringBuffer = new StringBuffer(arrayOfNote.length * 12);
/*     */ 
/* 209 */     for (int i = 0; i < arrayOfNote.length - 1; ++i) {
/* 210 */       localStringBuffer.append("[");
/* 211 */       localStringBuffer.append(arrayOfNote[i].getPitch());
/* 212 */       localStringBuffer.append(",");
/* 213 */       localStringBuffer.append(limitDecimalPlaces(arrayOfNote[i].getRhythmValue(), 3));
/*     */ 
/* 215 */       localStringBuffer.append(",");
/* 216 */       localStringBuffer.append(arrayOfNote[i].getDynamic());
/* 217 */       localStringBuffer.append("]");
/* 218 */       localStringBuffer.append(",");
/*     */     }
/*     */ 
/* 221 */     if (arrayOfNote.length > 0)
/*     */     {
/* 223 */       localStringBuffer.append("[");
/* 224 */       localStringBuffer.append(arrayOfNote[(arrayOfNote.length - 1)].getPitch());
/* 225 */       localStringBuffer.append(",");
/* 226 */       localStringBuffer.append(limitDecimalPlaces(arrayOfNote[(arrayOfNote.length - 1)].getRhythmValue(), 3));
/*     */ 
/* 230 */       localStringBuffer.append(",");
/* 231 */       localStringBuffer.append(arrayOfNote[(arrayOfNote.length - 1)].getDynamic());
/* 232 */       localStringBuffer.append("]");
/*     */     }
/*     */ 
/* 235 */     return localStringBuffer.toString();
/*     */   }
/*     */ 
/*     */   static String limitDecimalPlaces(double paramDouble, int paramInt)
/*     */   {
/* 261 */     String str = Double.toString(paramDouble);
/* 262 */     int i = str.lastIndexOf(".") + paramInt + 1;
/* 263 */     if (i > str.length()) {
/* 264 */       i = str.length();
/*     */     }
/* 266 */     return str.substring(0, i);
/*     */   }
/*     */ 
/*     */   public static String scoreToXMLString(Score paramScore)
/*     */   {
/* 341 */     return XMLParser.scoreToXMLString(paramScore);
/*     */   }
/*     */ 
/*     */   public static String partToXMLString(Part paramPart) {
/* 345 */     return XMLParser.partToXMLString(paramPart);
/*     */   }
/*     */ 
/*     */   public static String phraseToXMLString(Phrase paramPhrase) {
/* 349 */     return XMLParser.phraseToXMLString(paramPhrase);
/*     */   }
/*     */ 
/*     */   public static String noteToXMLString(Note paramNote) {
/* 353 */     return XMLParser.noteToXMLString(paramNote);
/*     */   }
/*     */ 
/*     */   public static Score xmlStringToScore(String paramString)
/*     */     throws ConversionException
/*     */   {
/* 360 */     return XMLParser.xmlStringToScore(paramString);
/*     */   }
/*     */ 
/*     */   public static Part xmlStringToPart(String paramString)
/*     */     throws ConversionException
/*     */   {
/* 366 */     return XMLParser.xmlStringToPart(paramString);
/*     */   }
/*     */ 
/*     */   public static Phrase xmlStringToPhrase(String paramString)
/*     */     throws ConversionException
/*     */   {
/* 372 */     return XMLParser.xmlStringToPhrase(paramString);
/*     */   }
/*     */ 
/*     */   public static Note xmlStringToNote(String paramString)
/*     */     throws ConversionException
/*     */   {
/* 378 */     return XMLParser.xmlStringToNote(paramString);
/*     */   }
/*     */ 
/*     */   public static final float getFrequencyByMidiPitch(int paramInt)
/*     */   {
/* 392 */     float f = -1.0F;
/* 393 */     if ((paramInt >= 0) && (paramInt <= 127)) {
/* 394 */       f = (float)(6.875D * Math.pow(2.0D, (3 + paramInt) / 12.0D));
/*     */     }
/* 396 */     return f;
/*     */   }
/*     */ 
/*     */   public static final int getMidiPitchByFrequency(float paramFloat)
/*     */   {
/* 416 */     float f = (float)Math.pow(2.0D, 0.0833333358168602D);
/* 417 */     if ((paramFloat < jm.constants.Frequencies.FRQ[0] / f) || (paramFloat > jm.constants.Frequencies.FRQ[127] * f))
/*     */     {
/* 419 */       return -1;
/*     */     }
/*     */ 
/* 422 */     int i = Math.round((float)(12.0D * Math.log(paramFloat / 6.875D) / Math.log(2.0D) - 3.0D));
/* 423 */     return i;
/*     */   }
/*     */ 
/*     */   public static final String getNameOfMidiPitch(int paramInt)
/*     */   {
/* 436 */     Field[] arrayOfField = Pitches.class.getFields();
/* 437 */     if (arrayOfField != null) {
/* 438 */       for (int i = 0; i < arrayOfField.length; ++i) {
/* 439 */         Field localField = arrayOfField[i];
/*     */         try
/*     */         {
/* 442 */           if (localField.getInt(null) == paramInt)
/* 443 */             return localField.getName();
/*     */         } catch (IllegalArgumentException localIllegalArgumentException) {
/* 445 */           return "";
/*     */         } catch (IllegalAccessException localIllegalAccessException) {
/* 447 */           return "";
/*     */         }
/*     */       }
/*     */     }
/* 451 */     return "";
/*     */   }
/*     */ 
/*     */   private static class StringProcessor
/*     */   {
/* 277 */     private int i = 0;
/*     */     private String string;
/*     */ 
/*     */     StringProcessor(String paramString)
/*     */     {
/* 282 */       this.string = paramString;
/*     */     }
/*     */ 
/*     */     private int getNextPitch()
/*     */       throws ConversionException, Convert.EOSException
/*     */     {
/*     */       // Byte code:
/*     */       //   0: new 5	java/lang/StringBuffer
/*     */       //   3: dup
/*     */       //   4: invokespecial 6	java/lang/StringBuffer:<init>	()V
/*     */       //   7: astore_1
/*     */       //   8: aload_0
/*     */       //   9: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   12: aload_0
/*     */       //   13: dup
/*     */       //   14: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   17: dup_x1
/*     */       //   18: iconst_1
/*     */       //   19: iadd
/*     */       //   20: putfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   23: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   26: invokestatic 8	java/lang/Character:isDigit	(C)Z
/*     */       //   29: ifne +6 -> 35
/*     */       //   32: goto -24 -> 8
/*     */       //   35: aload_1
/*     */       //   36: aload_0
/*     */       //   37: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   40: aload_0
/*     */       //   41: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   44: iconst_1
/*     */       //   45: isub
/*     */       //   46: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   49: invokevirtual 9	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
/*     */       //   52: pop
/*     */       //   53: aload_0
/*     */       //   54: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   57: aload_0
/*     */       //   58: dup
/*     */       //   59: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   62: dup_x1
/*     */       //   63: iconst_1
/*     */       //   64: iadd
/*     */       //   65: putfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   68: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   71: invokestatic 8	java/lang/Character:isDigit	(C)Z
/*     */       //   74: ifeq +24 -> 98
/*     */       //   77: aload_1
/*     */       //   78: aload_0
/*     */       //   79: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   82: aload_0
/*     */       //   83: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   86: iconst_1
/*     */       //   87: isub
/*     */       //   88: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   91: invokevirtual 9	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
/*     */       //   94: pop
/*     */       //   95: goto -42 -> 53
/*     */       //   98: aload_0
/*     */       //   99: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   102: aload_0
/*     */       //   103: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   106: iconst_1
/*     */       //   107: isub
/*     */       //   108: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   111: bipush 46
/*     */       //   113: if_icmpne +13 -> 126
/*     */       //   116: new 10	ConversionException
/*     */       //   119: dup
/*     */       //   120: ldc 11
/*     */       //   122: invokespecial 12	ConversionException:<init>	(Ljava/lang/String;)V
/*     */       //   125: athrow
/*     */       //   126: aload_1
/*     */       //   127: invokevirtual 13	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */       //   130: invokestatic 14	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */       //   133: ireturn
/*     */       //   134: astore_2
/*     */       //   135: aload_1
/*     */       //   136: invokevirtual 16	java/lang/StringBuffer:length	()I
/*     */       //   139: ifle +11 -> 150
/*     */       //   142: aload_1
/*     */       //   143: invokevirtual 13	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */       //   146: invokestatic 14	java/lang/Integer:parseInt	(Ljava/lang/String;)I
/*     */       //   149: ireturn
/*     */       //   150: new 17	Convert$EOSException
/*     */       //   153: dup
/*     */       //   154: aconst_null
/*     */       //   155: invokespecial 18	Convert$EOSException:<init>	(Ljm/util/Convert$1;)V
/*     */       //   158: athrow
/*     */       //
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   8	133	134	java/lang/IndexOutOfBoundsException
/*     */     }
/*     */ 
/*     */     private double getNextRhythm()
/*     */       throws Convert.EOSException
/*     */     {
/*     */       // Byte code:
/*     */       //   0: new 5	java/lang/StringBuffer
/*     */       //   3: dup
/*     */       //   4: invokespecial 6	java/lang/StringBuffer:<init>	()V
/*     */       //   7: astore_1
/*     */       //   8: aload_0
/*     */       //   9: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   12: aload_0
/*     */       //   13: dup
/*     */       //   14: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   17: dup_x1
/*     */       //   18: iconst_1
/*     */       //   19: iadd
/*     */       //   20: putfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   23: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   26: invokestatic 8	java/lang/Character:isDigit	(C)Z
/*     */       //   29: ifne +22 -> 51
/*     */       //   32: aload_0
/*     */       //   33: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   36: aload_0
/*     */       //   37: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   40: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   43: bipush 46
/*     */       //   45: if_icmpeq +6 -> 51
/*     */       //   48: goto -40 -> 8
/*     */       //   51: aload_1
/*     */       //   52: aload_0
/*     */       //   53: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   56: aload_0
/*     */       //   57: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   60: iconst_1
/*     */       //   61: isub
/*     */       //   62: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   65: invokevirtual 9	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
/*     */       //   68: pop
/*     */       //   69: aload_0
/*     */       //   70: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   73: aload_0
/*     */       //   74: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   77: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   80: invokestatic 8	java/lang/Character:isDigit	(C)Z
/*     */       //   83: ifne +19 -> 102
/*     */       //   86: aload_0
/*     */       //   87: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   90: aload_0
/*     */       //   91: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   94: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   97: bipush 46
/*     */       //   99: if_icmpne +32 -> 131
/*     */       //   102: aload_1
/*     */       //   103: aload_0
/*     */       //   104: getfield 4	jm/util/Convert$StringProcessor:string	Ljava/lang/String;
/*     */       //   107: aload_0
/*     */       //   108: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   111: invokevirtual 7	java/lang/String:charAt	(I)C
/*     */       //   114: invokevirtual 9	java/lang/StringBuffer:append	(C)Ljava/lang/StringBuffer;
/*     */       //   117: pop
/*     */       //   118: aload_0
/*     */       //   119: dup
/*     */       //   120: getfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   123: iconst_1
/*     */       //   124: iadd
/*     */       //   125: putfield 3	jm/util/Convert$StringProcessor:i	I
/*     */       //   128: goto -59 -> 69
/*     */       //   131: aload_1
/*     */       //   132: invokevirtual 13	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */       //   135: invokestatic 19	java/lang/Double:valueOf	(Ljava/lang/String;)Ljava/lang/Double;
/*     */       //   138: invokevirtual 20	java/lang/Double:doubleValue	()D
/*     */       //   141: dreturn
/*     */       //   142: astore_2
/*     */       //   143: aload_1
/*     */       //   144: invokevirtual 16	java/lang/StringBuffer:length	()I
/*     */       //   147: ifle +14 -> 161
/*     */       //   150: aload_1
/*     */       //   151: invokevirtual 13	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */       //   154: invokestatic 19	java/lang/Double:valueOf	(Ljava/lang/String;)Ljava/lang/Double;
/*     */       //   157: invokevirtual 20	java/lang/Double:doubleValue	()D
/*     */       //   160: dreturn
/*     */       //   161: new 17	Convert$EOSException
/*     */       //   164: dup
/*     */       //   165: aconst_null
/*     */       //   166: invokespecial 18	Convert$EOSException:<init>	(Ljm/util/Convert$1;)V
/*     */       //   169: athrow
/*     */       //
/*     */       // Exception table:
/*     */       //   from	to	target	type
/*     */       //   8	141	142	java/lang/IndexOutOfBoundsException
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class EOSException extends Exception
/*     */   {
/*     */     private EOSException()
/*     */     {
/*     */     }
/*     */ 
/*     */     EOSException(Convert.1 param1)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.Convert
 * JD-Core Version:    0.5.3
 */