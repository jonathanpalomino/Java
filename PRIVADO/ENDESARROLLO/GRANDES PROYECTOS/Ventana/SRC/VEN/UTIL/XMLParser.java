/*      */ package jm.util;
/*      */ 
/*      */ import java.util.Vector;
/*      */ import jm.music.data.Note;
/*      */ import jm.music.data.Part;
/*      */ import jm.music.data.Phrase;
/*      */ import jm.music.data.Score;
/*      */ 
/*      */ class XMLParser
/*      */ {
/*   44 */   private static final XMLStyle DEFAULT_XML_STYLE = new StandardXMLStyle();
/*      */ 
/*      */   public static String scoreToXMLString(Score paramScore)
/*      */   {
/*   48 */     return DEFAULT_XML_STYLE.initialXMLDeclaration() + scoreToXMLString(paramScore, DEFAULT_XML_STYLE);
/*      */   }
/*      */ 
/*      */   private static String scoreToXMLString(Score paramScore, XMLStyle paramXMLStyle)
/*      */   {
/*   55 */     StringBuffer localStringBuffer = new StringBuffer();
/*   56 */     localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getScoreTagName());
/*      */     int j;
/*   58 */     if (!(paramScore.getTitle().equals("Untitled Score"))) {
/*   59 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTitleAttributeName() + "=" + paramXMLStyle.getDoubleQuote());
/*      */ 
/*   64 */       String str = paramScore.getTitle();
/*   65 */       for (j = 0; j < str.length(); ++j) {
/*   66 */         char c = str.charAt(j);
/*   67 */         if (c == ' ')
/*   68 */           localStringBuffer.append(paramXMLStyle.getSpace());
/*   69 */         else if (c == '/')
/*   70 */           localStringBuffer.append(paramXMLStyle.getSlash());
/*   71 */         else if (c == '&')
/*   72 */           localStringBuffer.append(paramXMLStyle.getAmpersandInString());
/*   73 */         else if (c == '<')
/*   74 */           localStringBuffer.append(paramXMLStyle.getLeftAngleBracketInString());
/*   75 */         else if (c == '>')
/*   76 */           localStringBuffer.append(paramXMLStyle.getRightAngleBracketInString());
/*   77 */         else if (c == '"')
/*   78 */           localStringBuffer.append(paramXMLStyle.getDoubleQuoteInString());
/*   79 */         else if (c == '#')
/*   80 */           localStringBuffer.append(paramXMLStyle.getHash());
/*   81 */         else if (c == '/')
/*   82 */           localStringBuffer.append(paramXMLStyle.getSlash());
/*   83 */         else if (c == '?')
/*   84 */           localStringBuffer.append(paramXMLStyle.getQuestionMark());
/*   85 */         else if (c == ';')
/*   86 */           localStringBuffer.append(paramXMLStyle.getSemicolon());
/*      */         else {
/*   88 */           localStringBuffer.append(c);
/*      */         }
/*      */       }
/*   91 */       localStringBuffer.append(paramXMLStyle.getDoubleQuote());
/*      */     }
/*   93 */     if (paramScore.getTempo() != 60.0D) {
/*   94 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTempoAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramScore.getTempo(), 2) : Double.toString(paramScore.getTempo())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  104 */     if (paramScore.getVolume() != 100) {
/*  105 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getVolumeAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramScore.getVolume()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  113 */     if (paramScore.getKeySignature() != 0) {
/*  114 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getKeySignatureAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramScore.getKeySignature()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  122 */     if (paramScore.getKeyQuality() != 0) {
/*  123 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getKeyQualityAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramScore.getKeyQuality()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  131 */     if (paramScore.getNumerator() != 4) {
/*  132 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getNumeratorAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramScore.getNumerator()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  140 */     if (paramScore.getDenominator() != 4) {
/*  141 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getDenominatorAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramScore.getDenominator()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  149 */     int i = paramScore.size();
/*  150 */     if (i == 0) {
/*  151 */       localStringBuffer.append(paramXMLStyle.getSlash() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */     else {
/*  154 */       localStringBuffer.append(paramXMLStyle.getRightAngleBracket());
/*  155 */       for (j = 0; j < paramScore.size(); ++j) {
/*  156 */         localStringBuffer.append(partToXMLString(paramScore.getPart(j), paramXMLStyle));
/*      */       }
/*  158 */       localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getSlash() + paramXMLStyle.getScoreTagName() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */ 
/*  164 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public static String partToXMLString(Part paramPart) {
/*  168 */     return DEFAULT_XML_STYLE.initialXMLDeclaration() + partToXMLString(paramPart, DEFAULT_XML_STYLE);
/*      */   }
/*      */ 
/*      */   private static String partToXMLString(Part paramPart, XMLStyle paramXMLStyle)
/*      */   {
/*  175 */     StringBuffer localStringBuffer = new StringBuffer();
/*  176 */     localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getPartTagName());
/*      */     int j;
/*  178 */     if (!(paramPart.getTitle().equals("Untitled Part"))) {
/*  179 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTitleAttributeName() + "=" + paramXMLStyle.getDoubleQuote());
/*      */ 
/*  184 */       String str = paramPart.getTitle();
/*  185 */       for (j = 0; j < str.length(); ++j) {
/*  186 */         char c = str.charAt(j);
/*  187 */         if (c == ' ')
/*  188 */           localStringBuffer.append(paramXMLStyle.getSpace());
/*  189 */         else if (c == '/')
/*  190 */           localStringBuffer.append(paramXMLStyle.getSlash());
/*  191 */         else if (c == '&')
/*  192 */           localStringBuffer.append(paramXMLStyle.getAmpersandInString());
/*  193 */         else if (c == '<')
/*  194 */           localStringBuffer.append(paramXMLStyle.getLeftAngleBracketInString());
/*  195 */         else if (c == '>')
/*  196 */           localStringBuffer.append(paramXMLStyle.getRightAngleBracketInString());
/*  197 */         else if (c == '"')
/*  198 */           localStringBuffer.append(paramXMLStyle.getDoubleQuoteInString());
/*  199 */         else if (c == '#')
/*  200 */           localStringBuffer.append(paramXMLStyle.getHash());
/*  201 */         else if (c == '?')
/*  202 */           localStringBuffer.append(paramXMLStyle.getQuestionMark());
/*  203 */         else if (c == ';')
/*  204 */           localStringBuffer.append(paramXMLStyle.getSemicolon());
/*      */         else {
/*  206 */           localStringBuffer.append(c);
/*      */         }
/*      */       }
/*  209 */       localStringBuffer.append(paramXMLStyle.getDoubleQuote());
/*      */     }
/*  211 */     if (paramPart.getChannel() != 0) {
/*  212 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getChannelAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getChannel()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  220 */     if (paramPart.getInstrument() != 0) {
/*  221 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getInstrumentAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getInstrument()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  229 */     if (paramPart.getTempo() != -1.0D) {
/*  230 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTempoAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramPart.getTempo(), 2) : Double.toString(paramPart.getTempo())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  240 */     if (paramPart.getVolume() != 100) {
/*  241 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getVolumeAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getVolume()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  249 */     if (paramPart.getKeySignature() != -2147483648) {
/*  250 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getKeySignatureAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getKeySignature()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  258 */     if (paramPart.getKeyQuality() != -2147483648) {
/*  259 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getKeyQualityAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getKeyQuality()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  267 */     if (paramPart.getNumerator() != -2147483648) {
/*  268 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getNumeratorAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getNumerator()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  276 */     if (paramPart.getDenominator() != -2147483648) {
/*  277 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getDenominatorAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPart.getDenominator()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  285 */     if (paramPart.getPan() != 0.5D) {
/*  286 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getPanAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramPart.getPan(), 2) : Double.toString(paramPart.getPan())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  296 */     int i = paramPart.size();
/*  297 */     if (i == 0) {
/*  298 */       localStringBuffer.append(paramXMLStyle.getSlash() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */     else {
/*  301 */       localStringBuffer.append(paramXMLStyle.getRightAngleBracket());
/*  302 */       for (j = 0; j < paramPart.size(); ++j) {
/*  303 */         localStringBuffer.append(phraseToXMLString(paramPart.getPhrase(j), paramXMLStyle));
/*      */       }
/*  305 */       localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getSlash() + paramXMLStyle.getPartTagName() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */ 
/*  310 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public static String phraseToXMLString(Phrase paramPhrase) {
/*  314 */     return DEFAULT_XML_STYLE.initialXMLDeclaration() + phraseToXMLString(paramPhrase, DEFAULT_XML_STYLE);
/*      */   }
/*      */ 
/*      */   private static String phraseToXMLString(Phrase paramPhrase, XMLStyle paramXMLStyle)
/*      */   {
/*  321 */     StringBuffer localStringBuffer = new StringBuffer();
/*  322 */     localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getPhraseTagName());
/*      */     int j;
/*  324 */     if (!(paramPhrase.getTitle().equals("Untitled Phrase"))) {
/*  325 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTitleAttributeName() + "=" + paramXMLStyle.getDoubleQuote());
/*      */ 
/*  330 */       String str = paramPhrase.getTitle();
/*  331 */       for (j = 0; j < str.length(); ++j) {
/*  332 */         char c = str.charAt(j);
/*  333 */         if (c == ' ')
/*  334 */           localStringBuffer.append(paramXMLStyle.getSpace());
/*  335 */         else if (c == '/')
/*  336 */           localStringBuffer.append(paramXMLStyle.getSlash());
/*  337 */         else if (c == '&')
/*  338 */           localStringBuffer.append(paramXMLStyle.getAmpersandInString());
/*  339 */         else if (c == '<')
/*  340 */           localStringBuffer.append(paramXMLStyle.getLeftAngleBracketInString());
/*  341 */         else if (c == '>')
/*  342 */           localStringBuffer.append(paramXMLStyle.getRightAngleBracketInString());
/*  343 */         else if (c == '"')
/*  344 */           localStringBuffer.append(paramXMLStyle.getDoubleQuoteInString());
/*  345 */         else if (c == '#')
/*  346 */           localStringBuffer.append(paramXMLStyle.getHash());
/*  347 */         else if (c == '?')
/*  348 */           localStringBuffer.append(paramXMLStyle.getQuestionMark());
/*  349 */         else if (c == ';')
/*  350 */           localStringBuffer.append(paramXMLStyle.getSemicolon());
/*      */         else {
/*  352 */           localStringBuffer.append(c);
/*      */         }
/*      */       }
/*  355 */       localStringBuffer.append(paramXMLStyle.getDoubleQuote());
/*      */     }
/*  357 */     if (paramPhrase.getStartTime() != 0.0D) {
/*  358 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getStartTimeAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramPhrase.getStartTime(), 2) : Double.toString(paramPhrase.getStartTime())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  368 */     if (paramPhrase.getInstrument() != -1) {
/*  369 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getInstrumentAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramPhrase.getInstrument()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  377 */     if (paramPhrase.getPan() != -1.0D) {
/*  378 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getTempoAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramPhrase.getTempo(), 2) : Double.toString(paramPhrase.getTempo())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  388 */     if (paramPhrase.getAppend()) {
/*  389 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getAppendAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramPhrase.getAppend()) ? Boolean.TRUE.toString() : Boolean.FALSE.toString()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  399 */     if (paramPhrase.getPan() != 0.5D) {
/*  400 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getPanAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramPhrase.getPan(), 2) : Double.toString(paramPhrase.getPan())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  410 */     int i = paramPhrase.size();
/*  411 */     if (i == 0) {
/*  412 */       localStringBuffer.append(paramXMLStyle.getSlash() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */     else {
/*  415 */       localStringBuffer.append(paramXMLStyle.getRightAngleBracket());
/*  416 */       for (j = 0; j < i; ++j) {
/*  417 */         localStringBuffer.append(noteToXMLString(paramPhrase.getNote(j), paramXMLStyle));
/*      */       }
/*  419 */       localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getSlash() + paramXMLStyle.getPhraseTagName() + paramXMLStyle.getRightAngleBracket());
/*      */     }
/*      */ 
/*  425 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   public static String noteToXMLString(Note paramNote) {
/*  429 */     return DEFAULT_XML_STYLE.initialXMLDeclaration() + noteToXMLString(paramNote, DEFAULT_XML_STYLE);
/*      */   }
/*      */ 
/*      */   private static String noteToXMLString(Note paramNote, XMLStyle paramXMLStyle)
/*      */   {
/*  436 */     StringBuffer localStringBuffer = new StringBuffer();
/*  437 */     localStringBuffer.append(paramXMLStyle.getLeftAngleBracket() + paramXMLStyle.getNoteTagName());
/*      */ 
/*  439 */     if (!(paramNote.getPitchType())) {
/*  440 */       if (paramNote.getPitch() != 60) {
/*  441 */         localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getPitchAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramNote.getPitch()) + paramXMLStyle.getDoubleQuote());
/*      */       }
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  450 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getFrequencyAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Double.toString(paramNote.getFrequency()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  458 */     if (paramNote.getDynamic() != 85) {
/*  459 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getDynamicAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + Integer.toString(paramNote.getDynamic()) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  467 */     if (paramNote.getRhythmValue() != 1.0D) {
/*  468 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getRhythmValueAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramNote.getRhythmValue(), 2) : Double.toString(paramNote.getRhythmValue())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  478 */     if (paramNote.getPan() != 0.5D) {
/*  479 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getPanAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramNote.getPan(), 2) : Double.toString(paramNote.getPan())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  489 */     if (paramNote.getDuration() != 0.9D) {
/*  490 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getDurationAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramNote.getDuration(), 2) : Double.toString(paramNote.getDuration())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  500 */     if (paramNote.getOffset() != 0.0D) {
/*  501 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getOffsetAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramNote.getOffset(), 2) : Double.toString(paramNote.getOffset())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  511 */     if (paramNote.getSampleStartTime() != 0.0D) {
/*  512 */       localStringBuffer.append(paramXMLStyle.getSpace() + paramXMLStyle.getSampleStartTimeAttributeName() + "=" + paramXMLStyle.getDoubleQuote() + ((paramXMLStyle.limitDecimalPlaces()) ? limitDecimalPlaces(paramNote.getSampleStartTime(), 2) : Double.toString(paramNote.getSampleStartTime())) + paramXMLStyle.getDoubleQuote());
/*      */     }
/*      */ 
/*  523 */     localStringBuffer.append(paramXMLStyle.getSlash() + paramXMLStyle.getRightAngleBracket());
/*  524 */     return localStringBuffer.toString();
/*      */   }
/*      */ 
/*      */   private static String limitDecimalPlaces(double paramDouble, int paramInt)
/*      */   {
/*  529 */     String str = Double.toString(paramDouble);
/*  530 */     int i = str.lastIndexOf(".") + paramInt + 1;
/*  531 */     if (i > str.length()) {
/*  532 */       i = str.length();
/*      */     }
/*  534 */     return str.substring(0, i);
/*      */   }
/*      */ 
/*      */   public static Score xmlStringToScore(String paramString) throws ConversionException
/*      */   {
/*  539 */     String str = preprocessString(paramString);
/*  540 */     Element[] arrayOfElement = xmlStringToElements(str);
/*  541 */     if (arrayOfElement.length != 1) {
/*  542 */       throw new ConversionException("There can be only one root element.  This string invalidly has " + arrayOfElement.length + " root elements.");
/*      */     }
/*      */ 
/*  546 */     Element localElement = arrayOfElement[0];
/*  547 */     if (XMLStyles.isValidScoreTag(arrayOfElement[0].getName()))
/*  548 */       return elementToScore(arrayOfElement[0]);
/*  549 */     if (XMLStyles.isValidPartTag(arrayOfElement[0].getName()))
/*  550 */       return new Score(elementToPart(arrayOfElement[0]));
/*  551 */     if (XMLStyles.isValidPhraseTag(arrayOfElement[0].getName()))
/*  552 */       return new Score(new Part(elementToPhrase(arrayOfElement[0])));
/*  553 */     if (XMLStyles.isValidNoteTag(arrayOfElement[0].getName())) {
/*  554 */       return new Score(new Part(new Phrase(elementToNote(arrayOfElement[0]))));
/*      */     }
/*      */ 
/*  557 */     throw new ConversionException("Unrecognised root element: " + arrayOfElement[0].getName());
/*      */   }
/*      */ 
/*      */   public static Part xmlStringToPart(String paramString)
/*      */     throws ConversionException
/*      */   {
/*  563 */     String str = preprocessString(paramString);
/*  564 */     Element[] arrayOfElement = xmlStringToElements(str);
/*  565 */     if (arrayOfElement.length != 1) {
/*  566 */       throw new ConversionException("There can be only one root element.  This string invalidly has " + arrayOfElement.length + " root elements.");
/*      */     }
/*      */ 
/*  570 */     Element localElement = arrayOfElement[0];
/*  571 */     if (XMLStyles.isValidScoreTag(arrayOfElement[0].getName())) {
/*  572 */       throw new ConversionException("This XML string represents a Score, use the xmlStringToScore(String) method instead.");
/*      */     }
/*      */ 
/*  575 */     if (XMLStyles.isValidPartTag(arrayOfElement[0].getName()))
/*  576 */       return elementToPart(arrayOfElement[0]);
/*  577 */     if (XMLStyles.isValidPhraseTag(arrayOfElement[0].getName()))
/*  578 */       return new Part(elementToPhrase(arrayOfElement[0]));
/*  579 */     if (XMLStyles.isValidNoteTag(arrayOfElement[0].getName())) {
/*  580 */       return new Part(new Phrase(elementToNote(arrayOfElement[0])));
/*      */     }
/*  582 */     throw new ConversionException("Unrecognised root element: " + arrayOfElement[0].getName());
/*      */   }
/*      */ 
/*      */   public static Phrase xmlStringToPhrase(String paramString)
/*      */     throws ConversionException
/*      */   {
/*  588 */     String str = preprocessString(paramString);
/*  589 */     Element[] arrayOfElement = xmlStringToElements(str);
/*  590 */     if (arrayOfElement.length != 1) {
/*  591 */       throw new ConversionException("There can be only one root element.  This string invalidly has " + arrayOfElement.length + " root elements.");
/*      */     }
/*      */ 
/*  595 */     Element localElement = arrayOfElement[0];
/*  596 */     if (XMLStyles.isValidScoreTag(arrayOfElement[0].getName())) {
/*  597 */       throw new ConversionException("This XML string represents a Score, use the xmlStringToScore(String) method instead.");
/*      */     }
/*      */ 
/*  600 */     if (XMLStyles.isValidPartTag(arrayOfElement[0].getName())) {
/*  601 */       throw new ConversionException("This XML string represents a Part, use the xmlStringToPart(String) method instead.");
/*      */     }
/*      */ 
/*  604 */     if (XMLStyles.isValidPhraseTag(arrayOfElement[0].getName()))
/*  605 */       return elementToPhrase(arrayOfElement[0]);
/*  606 */     if (XMLStyles.isValidNoteTag(arrayOfElement[0].getName())) {
/*  607 */       return new Phrase(elementToNote(arrayOfElement[0]));
/*      */     }
/*  609 */     throw new ConversionException("Unrecognised root element: " + arrayOfElement[0].getName());
/*      */   }
/*      */ 
/*      */   public static Note xmlStringToNote(String paramString)
/*      */     throws ConversionException
/*      */   {
/*  615 */     String str = preprocessString(paramString);
/*  616 */     Element[] arrayOfElement = xmlStringToElements(str);
/*  617 */     if (arrayOfElement.length != 1) {
/*  618 */       throw new ConversionException("There can be only one root element.  This string invalidly has " + arrayOfElement.length + " root elements.");
/*      */     }
/*      */ 
/*  622 */     Element localElement = arrayOfElement[0];
/*  623 */     if (XMLStyles.isValidScoreTag(arrayOfElement[0].getName())) {
/*  624 */       throw new ConversionException("This XML string represents a Score, use the xmlStringToScore(String) method instead.");
/*      */     }
/*      */ 
/*  627 */     if (XMLStyles.isValidPartTag(arrayOfElement[0].getName())) {
/*  628 */       throw new ConversionException("This XML string represents a Part, use the xmlStringToPart(String) method instead.");
/*      */     }
/*      */ 
/*  631 */     if (XMLStyles.isValidPhraseTag(arrayOfElement[0].getName())) {
/*  632 */       throw new ConversionException("This XML string represents a Phrase, use the xmlStringToPhrase(String) method instead.");
/*      */     }
/*      */ 
/*  635 */     if (XMLStyles.isValidNoteTag(arrayOfElement[0].getName())) {
/*  636 */       return elementToNote(arrayOfElement[0]);
/*      */     }
/*  638 */     throw new ConversionException("Unrecognised root element: " + arrayOfElement[0].getName());
/*      */   }
/*      */ 
/*      */   private static String preprocessString(String paramString)
/*      */     throws ConversionException
/*      */   {
/*  644 */     String str1 = paramString;
/*  645 */     for (int i = 0; i < XMLStyles.styles.length; ++i) {
/*  646 */       localObject = XMLStyles.styles[i].initialXMLDeclaration();
/*  647 */       if (paramString.startsWith((String)localObject)) {
/*  648 */         str1 = str1.substring(((String)localObject).length());
/*  649 */         break;
/*      */       }
/*      */     }
/*  652 */     char[] arrayOfChar1 = str1.toCharArray();
/*  653 */     Object localObject = null;
/*      */ 
/*  655 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/*  656 */     char[][] arrayOfChar = localStandardXMLStyle.getEncodingsOfReferenceChars();
/*  657 */     char[] arrayOfChar2 = localStandardXMLStyle.getReferenceChars();
/*      */ 
/*  659 */     for (int j = 0; j < arrayOfChar.length; ++j) {
/*  660 */       localObject = new StringBuffer();
/*  661 */       String str2 = new String(arrayOfChar[j]);
/*  662 */       int k = 0;
/*      */ 
/*  664 */       int l = str1.indexOf(str2);
/*  665 */       while (l != -1) {
/*  666 */         while (k < l) {
/*  667 */           ((StringBuffer)localObject).append(arrayOfChar1[k]);
/*  668 */           ++k;
/*      */         }
/*  670 */         ((StringBuffer)localObject).append(arrayOfChar2[j]);
/*  671 */         k += 3;
/*      */ 
/*  673 */         l = str1.indexOf(str2, k);
/*      */       }
/*      */ 
/*  676 */       l = str1.length();
/*  677 */       while (k < l) {
/*  678 */         ((StringBuffer)localObject).append(arrayOfChar1[k]);
/*  679 */         ++k;
/*      */       }
/*      */ 
/*  682 */       str1 = ((StringBuffer)localObject).toString();
/*  683 */       arrayOfChar1 = str1.toCharArray();
/*      */     }
/*  685 */     return ((String)str1);
/*      */   }
/*      */ 
/*      */   private static Score elementToScore(Element paramElement)
/*      */     throws ConversionException
/*      */   {
/*  691 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/*  692 */     if (!(XMLStyles.isValidScoreTag(paramElement.getName()))) {
/*  693 */       throw new ConversionException("The root element must have the name '" + localStandardXMLStyle.getScoreTagName() + "'.  The invalid name used " + "was '" + paramElement.getName() + "'.");
/*      */     }
/*      */ 
/*  698 */     Score localScore = new Score();
/*      */ 
/*  701 */     String str = XMLStyles.getTitleAttributeValue(paramElement);
/*  702 */     if (!(str.equals(""))) {
/*  703 */       localScore.setTitle(str);
/*      */     }
/*  705 */     str = XMLStyles.getTempoAttributeValue(paramElement);
/*  706 */     if (!(str.equals(""))) {
/*      */       try {
/*  708 */         localScore.setTempo(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException1) {
/*  711 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getTempoAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  717 */     str = XMLStyles.getVolumeAttributeValue(paramElement);
/*  718 */     if (!(str.equals(""))) {
/*      */       try {
/*  720 */         localScore.setVolume(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException2) {
/*  722 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getVolumeAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  728 */     str = XMLStyles.getKeySignatureAttributeValue(paramElement);
/*  729 */     if (!(str.equals(""))) {
/*      */       try {
/*  731 */         localScore.setKeySignature(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException3) {
/*  733 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getKeySignatureAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  739 */     str = XMLStyles.getKeyQualityAttributeValue(paramElement);
/*  740 */     if (!(str.equals(""))) {
/*      */       try {
/*  742 */         localScore.setKeyQuality(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException4) {
/*  744 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getKeyQualityAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  750 */     str = XMLStyles.getNumeratorAttributeValue(paramElement);
/*  751 */     if (!(str.equals(""))) {
/*      */       try {
/*  753 */         localScore.setNumerator(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException5) {
/*  755 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getNumeratorAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  761 */     str = XMLStyles.getDenominatorAttributeValue(paramElement);
/*  762 */     if (!(str.equals(""))) {
/*      */       try {
/*  764 */         localScore.setDenominator(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException6) {
/*  766 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getDenominatorAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  772 */     Element[] arrayOfElement = paramElement.getChildren();
/*  773 */     for (int i = 0; i < arrayOfElement.length; ++i) {
/*  774 */       if (XMLStyles.isValidPartTag(arrayOfElement[i].getName())) {
/*  775 */         localScore.addPart(elementToPart(arrayOfElement[i]));
/*      */       }
/*      */     }
/*  778 */     return localScore;
/*      */   }
/*      */ 
/*      */   private static Part elementToPart(Element paramElement)
/*      */     throws ConversionException
/*      */   {
/*  784 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/*  785 */     if (!(XMLStyles.isValidPartTag(paramElement.getName()))) {
/*  786 */       throw new ConversionException("Invalid element: " + paramElement.getName() + ".  The only " + "accepted tag name is '" + localStandardXMLStyle.getPartTagName() + "'.");
/*      */     }
/*      */ 
/*  790 */     Part localPart = new Part();
/*      */ 
/*  793 */     String str = XMLStyles.getTitleAttributeValue(paramElement);
/*  794 */     if (!(str.equals(""))) {
/*  795 */       localPart.setTitle(str);
/*      */     }
/*  797 */     str = XMLStyles.getChannelAttributeValue(paramElement);
/*  798 */     if (!(str.equals(""))) {
/*      */       try {
/*  800 */         localPart.setChannel(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException1) {
/*  802 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getChannelAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  808 */     str = XMLStyles.getInstrumentAttributeValue(paramElement);
/*  809 */     if (!(str.equals(""))) {
/*      */       try {
/*  811 */         localPart.setInstrument(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException2) {
/*  813 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getInstrumentAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  819 */     str = XMLStyles.getTempoAttributeValue(paramElement);
/*  820 */     if (!(str.equals(""))) {
/*      */       try {
/*  822 */         localPart.setTempo(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException3) {
/*  825 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getTempoAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  831 */     str = XMLStyles.getVolumeAttributeValue(paramElement);
/*  832 */     if (!(str.equals(""))) {
/*      */       try {
/*  834 */         localPart.setVolume(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException4) {
/*  836 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getVolumeAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  842 */     str = XMLStyles.getKeySignatureAttributeValue(paramElement);
/*  843 */     if (!(str.equals(""))) {
/*      */       try {
/*  845 */         localPart.setKeySignature(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException5) {
/*  847 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getKeySignatureAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  853 */     str = XMLStyles.getKeyQualityAttributeValue(paramElement);
/*  854 */     if (!(str.equals(""))) {
/*      */       try {
/*  856 */         localPart.setKeyQuality(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException6) {
/*  858 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getKeyQualityAttributeName() + "' of element '" + localStandardXMLStyle.getScoreTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  864 */     str = XMLStyles.getNumeratorAttributeValue(paramElement);
/*  865 */     if (!(str.equals(""))) {
/*      */       try {
/*  867 */         localPart.setNumerator(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException7) {
/*  869 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getNumeratorAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  875 */     str = XMLStyles.getDenominatorAttributeValue(paramElement);
/*  876 */     if (!(str.equals(""))) {
/*      */       try {
/*  878 */         localPart.setDenominator(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException8) {
/*  880 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getDenominatorAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  886 */     str = XMLStyles.getPanAttributeValue(paramElement);
/*  887 */     if (!(str.equals(""))) {
/*      */       try {
/*  889 */         localPart.setPan(Double.valueOf(str).doubleValue());
/*      */       } catch (NumberFormatException localNumberFormatException9) {
/*  891 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getPanAttributeName() + "' of element '" + localStandardXMLStyle.getPartTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  897 */     Element[] arrayOfElement = paramElement.getChildren();
/*  898 */     for (int i = 0; i < arrayOfElement.length; ++i) {
/*  899 */       if (XMLStyles.isValidPhraseTag(arrayOfElement[i].getName())) {
/*  900 */         localPart.addPhrase(elementToPhrase(arrayOfElement[i]));
/*      */       }
/*      */     }
/*  903 */     return localPart;
/*      */   }
/*      */ 
/*      */   private static Phrase elementToPhrase(Element paramElement)
/*      */     throws ConversionException
/*      */   {
/*  909 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/*  910 */     if (!(XMLStyles.isValidPhraseTag(paramElement.getName()))) {
/*  911 */       throw new ConversionException("Invalid element: " + paramElement.getName() + ".  The only " + "accepted tag name is '" + localStandardXMLStyle.getPhraseTagName() + "'.");
/*      */     }
/*      */ 
/*  915 */     Phrase localPhrase = new Phrase();
/*      */ 
/*  918 */     String str = XMLStyles.getTitleAttributeValue(paramElement);
/*  919 */     if (!(str.equals(""))) {
/*      */       try {
/*  921 */         localPhrase.setTitle(str);
/*      */       } catch (NumberFormatException localNumberFormatException1) {
/*  923 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getTitleAttributeName() + "' of element '" + localStandardXMLStyle.getPhraseTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  929 */     str = XMLStyles.getStartTimeAttributeValue(paramElement);
/*  930 */     if (!(str.equals(""))) {
/*      */       try {
/*  932 */         localPhrase.setStartTime(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException2) {
/*  935 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getStartTimeAttributeName() + "' of element '" + localStandardXMLStyle.getPhraseTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  941 */     str = XMLStyles.getInstrumentAttributeValue(paramElement);
/*  942 */     if (!(str.equals(""))) {
/*      */       try {
/*  944 */         localPhrase.setInstrument(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException3) {
/*  946 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getInstrumentAttributeName() + "' of element '" + localStandardXMLStyle.getPhraseTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  952 */     str = XMLStyles.getTempoAttributeValue(paramElement);
/*  953 */     if (!(str.equals(""))) {
/*      */       try {
/*  955 */         localPhrase.setTempo(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException4) {
/*  958 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getTempoAttributeName() + "' of element '" + localStandardXMLStyle.getPhraseTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  964 */     str = XMLStyles.getAppendAttributeValue(paramElement);
/*  965 */     if (!(str.equals(""))) {
/*  966 */       localPhrase.setAppend(new Boolean(str).booleanValue());
/*      */     }
/*  968 */     str = XMLStyles.getPanAttributeValue(paramElement);
/*  969 */     if (!(str.equals(""))) {
/*      */       try {
/*  971 */         localPhrase.setPan(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException5) {
/*  974 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getPanAttributeName() + "' of element '" + localStandardXMLStyle.getPhraseTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  980 */     Element[] arrayOfElement = paramElement.getChildren();
/*  981 */     for (int i = 0; i < arrayOfElement.length; ++i) {
/*  982 */       if (XMLStyles.isValidNoteTag(arrayOfElement[i].getName())) {
/*  983 */         localPhrase.addNote(elementToNote(arrayOfElement[i]));
/*      */       }
/*      */     }
/*  986 */     return localPhrase;
/*      */   }
/*      */ 
/*      */   private static Note elementToNote(Element paramElement)
/*      */     throws ConversionException
/*      */   {
/*  992 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/*  993 */     if (!(XMLStyles.isValidNoteTag(paramElement.getName()))) {
/*  994 */       throw new ConversionException("Invalid element: " + paramElement.getName() + ".  The only " + "accepted tag name is '" + localStandardXMLStyle.getNoteTagName() + "'.");
/*      */     }
/*      */ 
/*  998 */     Note localNote = new Note();
/*      */ 
/* 1001 */     String str = XMLStyles.getPitchAttributeValue(paramElement);
/* 1002 */     if (!(str.equals(""))) {
/*      */       try {
/* 1004 */         localNote.setPitch(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException1) {
/* 1006 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getPitchAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1012 */     str = XMLStyles.getFrequencyAttributeValue(paramElement);
/* 1013 */     if (!(str.equals(""))) {
/*      */       try {
/* 1015 */         double d = Double.valueOf(str).doubleValue();
/* 1016 */         localNote.setFrequency(d);
/*      */       } catch (NumberFormatException localNumberFormatException2) {
/* 1018 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getFrequencyAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1024 */     str = XMLStyles.getDynamicAttributeValue(paramElement);
/* 1025 */     if (!(str.equals(""))) {
/*      */       try {
/* 1027 */         localNote.setDynamic(Integer.parseInt(str));
/*      */       } catch (NumberFormatException localNumberFormatException3) {
/* 1029 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getDynamicAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java integer.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1035 */     str = XMLStyles.getRhythmValueAttributeValue(paramElement);
/* 1036 */     if (!(str.equals(""))) {
/*      */       try {
/* 1038 */         localNote.setRhythmValue(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException4) {
/* 1041 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getRhythmValueAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1047 */     str = XMLStyles.getPanAttributeValue(paramElement);
/* 1048 */     if (!(str.equals(""))) {
/*      */       try {
/* 1050 */         localNote.setPan(Double.valueOf(str).doubleValue());
/*      */       } catch (NumberFormatException localNumberFormatException5) {
/* 1052 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getPanAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1058 */     str = XMLStyles.getDurationAttributeValue(paramElement);
/* 1059 */     if (!(str.equals(""))) {
/*      */       try {
/* 1061 */         localNote.setDuration(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException6) {
/* 1064 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getDurationAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1070 */     str = XMLStyles.getOffsetAttributeValue(paramElement);
/* 1071 */     if (!(str.equals(""))) {
/*      */       try {
/* 1073 */         localNote.setOffset(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException7) {
/* 1076 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getOffsetAttributeName() + "' of element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1082 */     str = XMLStyles.getSampleStartTimeAttributeValue(paramElement);
/* 1083 */     if (!(str.equals(""))) {
/*      */       try {
/* 1085 */         localNote.setSampleStartTime(Double.valueOf(str).doubleValue());
/*      */       }
/*      */       catch (NumberFormatException localNumberFormatException8) {
/* 1088 */         throw new ConversionException("Invalid attribute value: " + str + ".  The " + "attribute '" + localStandardXMLStyle.getSampleStartTimeAttributeName() + "' of " + "element '" + localStandardXMLStyle.getNoteTagName() + "' must represent a " + "Java double.");
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 1095 */     return localNote;
/*      */   }
/*      */ 
/*      */   private static Element[] xmlStringToElements(String paramString)
/*      */     throws ConversionException
/*      */   {
/* 1102 */     Vector localVector = new Vector();
/* 1103 */     StandardXMLStyle localStandardXMLStyle = new StandardXMLStyle();
/* 1104 */     char[][] arrayOfChar = localStandardXMLStyle.getEncodingsOfValueReferenceChars();
/* 1105 */     char[] arrayOfChar1 = localStandardXMLStyle.getValueReferenceChars();
/*      */     try {
/* 1107 */       int i = 0;
/* 1108 */       if (paramString.charAt(i++) != '<') {
/* 1109 */         throw new ConversionException("XML String does not begin with '<'");
/*      */       }
/*      */ 
/* 1112 */       StringBuffer localStringBuffer1 = new StringBuffer();
/* 1113 */       char c = paramString.charAt(i++);
/* 1114 */       while ((c != ' ') && (c != '/') && (c != '>')) {
/* 1115 */         localStringBuffer1.append(c);
/* 1116 */         c = paramString.charAt(i++);
/*      */       }
/* 1118 */       Element localElement = new Element(localStringBuffer1.toString());
/* 1119 */       while (c == ' ') {
/* 1120 */         StringBuffer localStringBuffer2 = new StringBuffer();
/* 1121 */         c = paramString.charAt(i++);
/* 1122 */         while (c != '=') {
/* 1123 */           if (c == '/') {
/* 1124 */             throw new ConversionException("Illegal character '/' in attribute name of the '" + localElement.getName() + "' element.");
/*      */           }
/*      */ 
/* 1128 */           if (c == '>') {
/* 1129 */             throw new ConversionException("Illegal character '>' in attribute name of the '" + localElement.getName() + "' element.");
/*      */           }
/*      */ 
/* 1133 */           localStringBuffer2.append(c);
/* 1134 */           c = paramString.charAt(i++);
/*      */         }
/* 1136 */         Attribute localAttribute = new Attribute(localStringBuffer2.toString());
/* 1137 */         c = paramString.charAt(i++);
/* 1138 */         if (c != '"') {
/* 1139 */           throw new ConversionException("The value of the '" + localAttribute.getName() + "' attribute in the '" + localElement.getName() + "' element does not begin with a double-quote " + "(\").");
/*      */         }
/*      */ 
/* 1145 */         StringBuffer localStringBuffer3 = new StringBuffer();
/* 1146 */         c = paramString.charAt(i++);
/* 1147 */         while (c != '"')
/*      */         {
/* 1166 */           label519: for (int l = 0; l < arrayOfChar.length; ++l) {
/* 1167 */             for (int i1 = 0; i1 < arrayOfChar[l].length; ++i1) {
/*      */               try {
/* 1169 */                 if (arrayOfChar[l][i1] != paramString.charAt(i + i1 - 1))
/*      */                 {
/* 1172 */                   if (l == arrayOfChar.length - 1) {
/* 1173 */                     localStringBuffer3.append(c);
/*      */                   }
/* 1175 */                   break label519:
/*      */                 }
/*      */ 
/* 1178 */                 if (i1 == arrayOfChar[l].length - 1) {
/* 1179 */                   i += arrayOfChar[l].length - 1;
/* 1180 */                   localStringBuffer3.append(arrayOfChar1[l]);
/* 1181 */                   break label525:
/*      */                 }
/*      */               }
/*      */               catch (IndexOutOfBoundsException localIndexOutOfBoundsException2) {
/* 1185 */                 if (l == arrayOfChar.length - 1) {
/* 1186 */                   localStringBuffer3.append(c);
/*      */                 }
/* 1188 */                 break label519:
/*      */               }
/*      */             }
/*      */           }
/* 1192 */           label525: c = paramString.charAt(i++);
/*      */         }
/* 1194 */         localAttribute.setValue(localStringBuffer3.toString());
/* 1195 */         localElement.addAttribute(localAttribute);
/* 1196 */         c = paramString.charAt(i++);
/*      */       }
/* 1198 */       if (c == '>') {
/* 1199 */         int j = paramString.indexOf("</" + localElement.getName() + ">");
/* 1200 */         if (j == -1) {
/* 1201 */           throw new ConversionException("No closing tag found: </" + localElement.getName() + ">");
/*      */         }
/*      */ 
/* 1204 */         localElement.appendChildren(xmlStringToElements(paramString.substring(i, j)));
/*      */ 
/* 1206 */         i = j + localElement.getName().length() + 3;
/* 1207 */       } else if (c == '/') {
/* 1208 */         c = paramString.charAt(i++);
/* 1209 */         if (c != '>') {
/* 1210 */           throw new ConversionException("Character '>' is expected to terminate the '" + localElement.getName() + "' element but was not " + "found.");
/*      */         }
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 1216 */         throw new ConversionException("Either '>' or '/>' is expected to terminate the '" + localElement.getName() + "' element but neither was " + "found.");
/*      */       }
/*      */ 
/* 1221 */       localVector.addElement(localElement);
/* 1222 */       if (i < paramString.length()) {
/* 1223 */         arrayOfElement = xmlStringToElements(paramString.substring(i));
/*      */ 
/* 1225 */         for (int k = 0; k < arrayOfElement.length; ++k) {
/* 1226 */           localVector.addElement(arrayOfElement[k]);
/*      */         }
/*      */       }
/* 1229 */       Element[] arrayOfElement = new Element[localVector.size()];
/*      */ 
/* 1231 */       localVector.copyInto(arrayOfElement);
/* 1232 */       return arrayOfElement;
/*      */     } catch (IndexOutOfBoundsException localIndexOutOfBoundsException1) {
/* 1234 */       throw new ConversionException("Xml string ended prematurely.  Further characters were excepted.");
/*      */     }
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.XMLParser
 * JD-Core Version:    0.5.3
 */