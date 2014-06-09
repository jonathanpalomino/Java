/*      */ package jm.util;
/*      */ 
/*      */ abstract class XMLStyle
/*      */ {
/*      */   final char[] WHITESPACE_CHARS;
/*      */   final char[] DOUBLE_QUOTE_CHARS;
/*      */   final char[] HASH_CHARS;
/*      */   final char[] AMPERSAND_CHARS;
/*      */   final char[] SLASH_CHARS;
/*      */   final char[] SEMICOLON_CHARS;
/*      */   final char[] QUESTION_MARK_CHARS;
/*      */   final char[] LEFT_ANGLE_CHARS;
/*      */   final char[] RIGHT_ANGLE_CHARS;
/*      */ 
/*      */   XMLStyle()
/*      */   {
/* 1617 */     this.WHITESPACE_CHARS = new char[] { '%', '2', '0' };
/*      */ 
/* 1620 */     this.DOUBLE_QUOTE_CHARS = new char[] { '%', '2', '2' };
/*      */ 
/* 1623 */     this.HASH_CHARS = new char[] { '%', '2', '3' };
/*      */ 
/* 1626 */     this.AMPERSAND_CHARS = new char[] { '%', '2', '6' };
/*      */ 
/* 1629 */     this.SLASH_CHARS = new char[] { '%', '2', 'F' };
/*      */ 
/* 1632 */     this.SEMICOLON_CHARS = new char[] { '%', '3', 'B' };
/*      */ 
/* 1635 */     this.QUESTION_MARK_CHARS = new char[] { '%', '3', 'F' };
/*      */ 
/* 1638 */     this.LEFT_ANGLE_CHARS = new char[] { '%', '3', 'C' };
/*      */ 
/* 1641 */     this.RIGHT_ANGLE_CHARS = new char[] { '%', '3', 'E' };
/*      */   }
/*      */ 
/*      */   public String initialXMLDeclaration()
/*      */   {
/* 1578 */     return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
/*      */   }
/*      */ 
/*      */   public String getSpace() {
/* 1582 */     return " ";
/*      */   }
/*      */ 
/*      */   public String getDoubleQuote() {
/* 1586 */     return "\"";
/*      */   }
/*      */ 
/*      */   public String getDoubleQuoteInString() {
/* 1590 */     return "&quot;";
/*      */   }
/*      */ 
/*      */   public String getHash() {
/* 1594 */     return "#";
/*      */   }
/*      */ 
/*      */   public String getAmpersand() {
/* 1598 */     return "&";
/*      */   }
/*      */ 
/*      */   public String getAmpersandInString() {
/* 1602 */     return "&amp;";
/*      */   }
/*      */ 
/*      */   public String getSlash() {
/* 1606 */     return "/";
/*      */   }
/*      */ 
/*      */   public String getSemicolon() {
/* 1610 */     return ";";
/*      */   }
/*      */ 
/*      */   public String getQuestionMark() {
/* 1614 */     return "?";
/*      */   }
/*      */ 
/*      */   public abstract char[] getReferenceChars();
/*      */ 
/*      */   public abstract char[][] getEncodingsOfReferenceChars();
/*      */ 
/*      */   public abstract char[] getValueReferenceChars();
/*      */ 
/*      */   public abstract char[][] getEncodingsOfValueReferenceChars();
/*      */ 
/*      */   public String getLeftAngleBracket()
/*      */   {
/* 1653 */     return "<";
/*      */   }
/*      */ 
/*      */   public String getLeftAngleBracketInString() {
/* 1657 */     return "&lt;";
/*      */   }
/*      */ 
/*      */   public String getRightAngleBracket() {
/* 1661 */     return ">";
/*      */   }
/*      */ 
/*      */   public String getRightAngleBracketInString() {
/* 1665 */     return "&gt;";
/*      */   }
/*      */ 
/*      */   public String getScoreTagName() {
/* 1669 */     return "Score";
/*      */   }
/*      */ 
/*      */   public String getPartTagName() {
/* 1673 */     return "Part";
/*      */   }
/*      */ 
/*      */   public String getPhraseTagName() {
/* 1677 */     return "Phrase";
/*      */   }
/*      */ 
/*      */   public String getNoteTagName() {
/* 1681 */     return "Note";
/*      */   }
/*      */ 
/*      */   public String getTitleAttributeName() {
/* 1685 */     return "title";
/*      */   }
/*      */ 
/*      */   public String getTempoAttributeName() {
/* 1689 */     return "tempo";
/*      */   }
/*      */ 
/*      */   public String getVolumeAttributeName() {
/* 1693 */     return "volume";
/*      */   }
/*      */ 
/*      */   public String getKeySignatureAttributeName() {
/* 1697 */     return "keySignature";
/*      */   }
/*      */ 
/*      */   public String getKeyQualityAttributeName() {
/* 1701 */     return "keyQuality";
/*      */   }
/*      */ 
/*      */   public String getNumeratorAttributeName() {
/* 1705 */     return "numerator";
/*      */   }
/*      */ 
/*      */   public String getDenominatorAttributeName() {
/* 1709 */     return "denominator";
/*      */   }
/*      */ 
/*      */   public String getChannelAttributeName() {
/* 1713 */     return "channel";
/*      */   }
/*      */ 
/*      */   public String getInstrumentAttributeName() {
/* 1717 */     return "instrument";
/*      */   }
/*      */ 
/*      */   public String getPanAttributeName() {
/* 1721 */     return "pan";
/*      */   }
/*      */ 
/*      */   public String getStartTimeAttributeName() {
/* 1725 */     return "startTime";
/*      */   }
/*      */ 
/*      */   public String getAppendAttributeName() {
/* 1729 */     return "append";
/*      */   }
/*      */ 
/*      */   public String getPitchAttributeName() {
/* 1733 */     return "pitch";
/*      */   }
/*      */ 
/*      */   public String getFrequencyAttributeName() {
/* 1737 */     return "frequency";
/*      */   }
/*      */ 
/*      */   public String getDynamicAttributeName() {
/* 1741 */     return "dynamic";
/*      */   }
/*      */ 
/*      */   public String getRhythmValueAttributeName() {
/* 1745 */     return "rhythmValue";
/*      */   }
/*      */ 
/*      */   public String getDurationAttributeName() {
/* 1749 */     return "duration";
/*      */   }
/*      */ 
/*      */   public String getOffsetAttributeName() {
/* 1753 */     return "offset";
/*      */   }
/*      */ 
/*      */   public String getSampleStartTimeAttributeName() {
/* 1757 */     return "sampleStartTime";
/*      */   }
/*      */ 
/*      */   public boolean limitDecimalPlaces() {
/* 1761 */     return false;
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.XMLStyle
 * JD-Core Version:    0.5.3
 */