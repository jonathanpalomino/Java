/*      */ package jm.music.tools;
/*      */ 
/*      */ import java.io.PrintStream;
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ import jm.JMC;
/*      */ import jm.music.data.CPhrase;
/*      */ import jm.music.data.Note;
/*      */ import jm.music.data.Part;
/*      */ import jm.music.data.Phrase;
/*      */ import jm.music.data.Score;
/*      */ 
/*      */ public class Mod
/*      */   implements JMC
/*      */ {
/*      */   public static void append(Note paramNote1, Note paramNote2)
/*      */   {
/*      */     try
/*      */     {
/*   63 */       if ((paramNote1 == null) || (paramNote2 == null))
/*   64 */         new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*   65 */       localNullPointerException.printStackTrace();
/*      */     }
/*   67 */     paramNote1.setRhythmValue(paramNote1.getRhythmValue() + paramNote2.getRhythmValue());
/*      */ 
/*   69 */     paramNote1.setDuration(paramNote1.getDuration() + paramNote2.getDuration());
/*      */   }
/*      */ 
/*      */   public static void transpose(Note paramNote, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/*   86 */       if (paramNote == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*   87 */       localNullPointerException.printStackTrace();
/*      */     }
/*   89 */     if ((!(paramNote.getPitchType())) && (paramNote.getPitch() != -2147483648))
/*   90 */       paramNote.setPitch(paramNote.getPitch() + paramInt);
/*   91 */     if (paramNote.getPitchType() == true)
/*   92 */       System.err.println("jMusic Mod transpose: No action taken - notes with frequency values cannot yet be transposed.");
/*      */   }
/*      */ 
/*      */   public static void transpose(Note paramNote, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/*      */     try
/*      */     {
/*  118 */       if (paramNote == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*  119 */       localNullPointerException.printStackTrace();
/*      */     }
/*  121 */     int i = paramNote.getPitch();
/*  122 */     if (i == -2147483648)
/*      */       return;
/*  124 */     int j = i / 12;
/*  125 */     int k = 0;
/*  126 */     Note localNote = paramNote.copy();
/*  127 */     while (!(localNote.isScale(paramArrayOfInt))) {
/*  128 */       localNote.setPitch(localNote.getPitch() - 1);
/*  129 */       ++k;
/*      */     }
/*  131 */     int l = 0;
/*  132 */     for (int i1 = 0; i1 < paramArrayOfInt.length; ++i1) {
/*  133 */       if (i % 12 - k == paramArrayOfInt[i1]) {
/*  134 */         l = i1;
/*  135 */         i1 = paramArrayOfInt.length;
/*      */       }
/*      */     }
/*      */ 
/*  139 */     i1 = l + paramInt1;
/*  140 */     while (i1 >= paramArrayOfInt.length) {
/*  141 */       ++j;
/*  142 */       i1 -= paramArrayOfInt.length;
/*      */     }
/*  144 */     while (i1 < 0) {
/*  145 */       --j;
/*  146 */       i1 += paramArrayOfInt.length;
/*      */     }
/*  148 */     paramNote.setPitch(paramArrayOfInt[i1] + j * 12 + k);
/*      */   }
/*      */ 
/*      */   public static final void crescendo(Phrase paramPhrase, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
/*      */   {
/*  160 */     double d1 = paramInt2 - paramInt1;
/*  161 */     double d2 = paramDouble2 - paramDouble1;
/*  162 */     if (d2 == 0.0D)
/*      */     {
/*  166 */       return;
/*      */     }
/*  168 */     double d3 = 0.0D;
/*  169 */     Vector localVector = paramPhrase.getNoteList();
/*  170 */     for (int i = 0; i < localVector.size(); ++i) {
/*  171 */       Note localNote = (Note)localVector.elementAt(i);
/*  172 */       if (d3 >= paramDouble1) {
/*  173 */         localNote.setDynamic((int)((d3 - paramDouble1) / d2 * d1 + paramInt1));
/*      */       }
/*      */ 
/*  176 */       d3 += localNote.getRhythmValue();
/*  177 */       if (d3 > paramDouble2)
/*      */         return;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static final void diminuendo(Phrase paramPhrase, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
/*      */   {
/*  189 */     crescendo(paramPhrase, paramDouble1, paramDouble2, paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   public static final void decrescendo(Phrase paramPhrase, double paramDouble1, double paramDouble2, int paramInt1, int paramInt2)
/*      */   {
/*  198 */     crescendo(paramPhrase, paramDouble1, paramDouble2, paramInt1, paramInt2);
/*      */   }
/*      */ 
/*      */   public static void transpose(Phrase paramPhrase, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/*  215 */       if (paramPhrase == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*  216 */       localNullPointerException.printStackTrace();
/*      */     }
/*  218 */     Vector localVector = paramPhrase.getNoteList();
/*  219 */     Enumeration localEnumeration = localVector.elements();
/*  220 */     while (localEnumeration.hasMoreElements()) {
/*  221 */       Note localNote = (Note)localEnumeration.nextElement();
/*  222 */       if (localNote.getPitch() != -2147483648) {
/*  223 */         localNote.setPitch(localNote.getPitch() + paramInt);
/*      */       }
/*      */     }
/*  226 */     paramPhrase.setNoteList(localVector);
/*      */   }
/*      */ 
/*      */   public static void transpose(Phrase paramPhrase, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/*      */     try
/*      */     {
/*  251 */       if (paramPhrase == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*  252 */       localNullPointerException.printStackTrace();
/*      */     }
/*      */ 
/*  255 */     int i = paramInt2 % 12;
/*  256 */     Vector localVector = paramPhrase.getNoteList();
/*  257 */     Enumeration localEnumeration = localVector.elements();
/*  258 */     while (localEnumeration.hasMoreElements()) {
/*  259 */       Note localNote = (Note)localEnumeration.nextElement();
/*  260 */       transpose(localNote, paramInt1, paramArrayOfInt, paramInt2);
/*      */     }
/*  262 */     paramPhrase.setNoteList(localVector);
/*      */   }
/*      */ 
/*      */   public static void repeat(Phrase paramPhrase)
/*      */   {
/*  273 */     repeat(paramPhrase, 2);
/*      */   }
/*      */ 
/*      */   public static void repeat(Phrase paramPhrase, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/*  288 */       if (paramPhrase == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*  289 */       localNullPointerException.printStackTrace();
/*      */     }
/*  291 */     int i = paramPhrase.size();
/*  292 */     for (int j = 0; j < paramInt - 1; ++j)
/*  293 */       for (int k = 0; k < i; ++k)
/*  294 */         paramPhrase.addNote(paramPhrase.getNote(k).copy());
/*      */   }
/*      */ 
/*      */   public static void repeat(Phrase paramPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/*  314 */     repeat(paramPhrase, 2, paramDouble1, paramDouble2);
/*      */   }
/*      */ 
/*      */   public static void repeat(Phrase paramPhrase, int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/*  334 */     if (paramPhrase == null) {
/*  335 */       System.err.println("phrase is null");
/*  336 */       return; }
/*  337 */     if (paramDouble1 >= paramDouble2) {
/*  338 */       System.err.println("startlocation is bigger or equal to end location");
/*      */ 
/*  340 */       return; }
/*  341 */     if (paramInt < 2) {
/*  342 */       System.err.println("times is smaller than 2");
/*  343 */       return; }
/*  344 */     if (paramDouble1 < 0.0D) {
/*  345 */       System.err.println("startLoc is smaller than 0");
/*  346 */       return;
/*      */     }
/*      */ 
/*  350 */     Phrase localPhrase1 = paramPhrase.copy(paramDouble1, paramDouble2);
/*      */ 
/*  352 */     Phrase localPhrase2 = new Phrase();
/*  353 */     int i = 0;
/*  354 */     int j = 0;
/*  355 */     int k = 0;
/*  356 */     double d = (paramPhrase.getStartTime() < 0.0D) ? 0.0D : paramPhrase.getStartTime();
/*      */ 
/*  359 */     int l = 0;
/*  360 */     for (; (l < paramPhrase.size()) && (d + paramPhrase.getNote(l).getRhythmValue() <= paramDouble2); ++l)
/*      */     {
/*  362 */       localPhrase2.addNote(paramPhrase.getNote(l));
/*      */ 
/*  364 */       if ((d < paramDouble1) && (d + paramPhrase.getNote(l).getRhythmValue() > paramDouble1)) {
/*  365 */         i = 1;
/*  366 */         j = l;
/*      */       }
/*  368 */       d += paramPhrase.getNote(l).getRhythmValue();
/*      */     }
/*      */ 
/*  372 */     if ((l + 1 < paramPhrase.size()) && 
/*  373 */       (d < paramDouble2) && (d + paramPhrase.getNote(l + 1).getRhythmValue() > paramDouble2)) {
/*  374 */       k = 1;
/*      */ 
/*  376 */       Note localNote = paramPhrase.getNote(l).copy();
/*  377 */       localNote.setDuration(localNote.getDuration() * paramDouble2 - (d / localNote.getRhythmValue()));
/*  378 */       localNote.setRhythmValue(paramDouble2 - d);
/*  379 */       localPhrase2.addNote(localNote);
/*      */     }
/*      */ 
/*  384 */     int i1 = 0;
/*  385 */     for (int i2 = 0; i2 < paramInt - 1; ++i2) {
/*  386 */       for (int i3 = 0; i3 < localPhrase1.size(); ++i3) {
/*  387 */         if (i1 != 0) continue; localPhrase2.addNote(localPhrase1.getNote(i3));
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  392 */     if (k != 0)
/*      */     {
/*  394 */       localPhrase2.removeLastNote();
/*      */     }
/*      */ 
/*  398 */     for (i2 = l; i2 < paramPhrase.size(); ++i2) {
/*  399 */       localPhrase2.addNote(paramPhrase.getNote(i2));
/*      */     }
/*      */ 
/*  403 */     paramPhrase.setNoteList(localPhrase2.getNoteList());
/*      */   }
/*      */ 
/*      */   public static void increaseDynamic(Phrase paramPhrase, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/*  416 */       if (paramPhrase == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/*  417 */       localNullPointerException.toString(); return;
/*      */     }
/*  419 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*  420 */     while (localEnumeration.hasMoreElements()) {
/*  421 */       Note localNote = (Note)localEnumeration.nextElement();
/*  422 */       localNote.setDynamic(localNote.getDynamic() + paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  438 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/*  439 */       return;
/*      */     }
/*  441 */     double d1 = 0.0D;
/*  442 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*  443 */     while (localEnumeration.hasMoreElements()) {
/*  444 */       if (d1 > paramDouble) {
/*      */         return;
/*      */       }
/*  447 */       Note localNote = (Note)localEnumeration.nextElement();
/*  448 */       double d2 = d1 / paramDouble;
/*  449 */       int i = (int)(localNote.getDynamic() * d2);
/*  450 */       if (i == 0)
/*      */       {
/*  452 */         i = 1;
/*      */       }
/*  454 */       localNote.setDynamic(i);
/*  455 */       d1 += localNote.getRhythmValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(Phrase paramPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/*  478 */     if ((paramPhrase == null) || (paramDouble1 <= 0.0D) || (paramDouble2 < 0.0D)) {
/*  479 */       return;
/*      */     }
/*  481 */     double d1 = paramDouble2;
/*  482 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*  483 */     while (localEnumeration.hasMoreElements()) {
/*  484 */       if (d1 >= paramDouble1) {
/*      */         return;
/*      */       }
/*  487 */       Note localNote = (Note)localEnumeration.nextElement();
/*  488 */       double d2 = d1 / paramDouble1;
/*  489 */       int i = (int)(localNote.getDynamic() * d2);
/*  490 */       if (i == 0)
/*      */       {
/*  492 */         i = 1;
/*      */       }
/*  494 */       localNote.setDynamic(i);
/*  495 */       d1 += localNote.getRhythmValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  510 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/*  511 */       return;
/*      */     }
/*  513 */     double d1 = 0.0D;
/*  514 */     int i = paramPhrase.size() - 1;
/*  515 */     for (int j = 0; j <= i; ++j) {
/*  516 */       Note localNote = (Note)paramPhrase.getNoteList().elementAt(i - j);
/*  517 */       if (d1 > paramDouble) {
/*      */         return;
/*      */       }
/*      */ 
/*  521 */       double d2 = d1 / paramDouble;
/*  522 */       int k = (int)(localNote.getDynamic() * d2);
/*  523 */       if (k == 0)
/*  524 */         k = 1;
/*  525 */       localNote.setDynamic(k);
/*  526 */       d1 += localNote.getRhythmValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(Phrase paramPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/*  550 */     if ((paramPhrase == null) || (paramDouble1 <= 0.0D) || (paramDouble2 < 0.0D)) {
/*  551 */       return;
/*      */     }
/*  553 */     double d1 = paramDouble2;
/*  554 */     int i = paramPhrase.size() - 1;
/*  555 */     for (int j = 0; j <= i; ++j) {
/*  556 */       Note localNote = (Note)paramPhrase.getNoteList().elementAt(i - j);
/*  557 */       if (d1 >= paramDouble1) {
/*      */         return;
/*      */       }
/*  560 */       double d2 = d1 / paramDouble1;
/*  561 */       int k = (int)(localNote.getDynamic() * d2);
/*  562 */       if (k == 0)
/*      */       {
/*  565 */         k = 1;
/*      */       }
/*  567 */       localNote.setDynamic(k);
/*  568 */       d1 += localNote.getRhythmValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void compress(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  599 */     if (paramPhrase == null) {
/*  600 */       return;
/*      */     }
/*      */ 
/*  604 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*      */ 
/*  607 */     int i = 0;
/*  608 */     int j = 127;
/*      */     Note localNote;
/*      */     int k;
/*  612 */     while (localEnumeration.hasMoreElements()) {
/*  613 */       localNote = (Note)localEnumeration.nextElement();
/*  614 */       if (localNote.getPitch() != -2147483648) {
/*  615 */         k = localNote.getDynamic();
/*  616 */         if (k > i) {
/*  617 */           i = k;
/*      */         }
/*  619 */         if (k < j) {
/*  620 */           j = k;
/*      */         }
/*      */       }
/*      */     }
/*  624 */     int l = (j + i) / 2;
/*      */ 
/*  627 */     localEnumeration = paramPhrase.getNoteList().elements();
/*  628 */     while (localEnumeration.hasMoreElements()) {
/*  629 */       localNote = (Note)localEnumeration.nextElement();
/*  630 */       k = (int)(l + (localNote.getDynamic() - l) * paramDouble);
/*  631 */       localNote.setDynamic(k);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void append(Phrase paramPhrase1, Phrase paramPhrase2)
/*      */   {
/*  645 */     if ((paramPhrase1 == null) || (paramPhrase2 == null)) {
/*  646 */       return;
/*      */     }
/*  648 */     Enumeration localEnumeration = paramPhrase2.getNoteList().elements();
/*  649 */     while (localEnumeration.hasMoreElements())
/*  650 */       paramPhrase1.addNote(((Note)localEnumeration.nextElement()).copy());
/*      */   }
/*      */ 
/*      */   public static void quantize(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  662 */     quantise(paramPhrase, paramDouble);
/*      */   }
/*      */ 
/*      */   public static void quantise(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  688 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/*  689 */       return;
/*      */     }
/*  691 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*  692 */     while (localEnumeration.hasMoreElements()) {
/*  693 */       Note localNote = (Note)localEnumeration.nextElement();
/*  694 */       double d = localNote.getRhythmValue();
/*      */ 
/*  697 */       localNote.setRhythmValue((int)Math.round(d / paramDouble) * paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void cycle(Phrase paramPhrase, int paramInt)
/*      */   {
/*  718 */     if (paramPhrase == null) {
/*  719 */       return;
/*      */     }
/*      */ 
/*  723 */     int i = paramPhrase.size();
/*  724 */     if (paramInt <= i) {
/*  725 */       return;
/*      */     }
/*      */ 
/*  728 */     Phrase localPhrase = new Phrase();
/*      */ 
/*  731 */     for (int j = 0; j < paramInt; ++j) {
/*  732 */       localPhrase.addNote(paramPhrase.getNote(j % i).copy());
/*      */     }
/*      */ 
/*  736 */     paramPhrase.getNoteList().removeAllElements();
/*  737 */     Enumeration localEnumeration = localPhrase.getNoteList().elements();
/*  738 */     while (localEnumeration.hasMoreElements())
/*  739 */       paramPhrase.getNoteList().addElement(localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void cycle(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  768 */     if ((paramPhrase == null) || (paramDouble <= paramPhrase.getEndTime())) {
/*  769 */       return;
/*      */     }
/*      */ 
/*  772 */     int i = paramPhrase.size();
/*  773 */     Phrase localPhrase = new Phrase();
/*      */ 
/*  776 */     for (int j = 0; localPhrase.getEndTime() < paramDouble; ++j) {
/*  777 */       localPhrase.addNote(paramPhrase.getNote(j % i).copy());
/*      */     }
/*      */ 
/*  781 */     paramPhrase.getNoteList().removeAllElements();
/*  782 */     Enumeration localEnumeration = localPhrase.getNoteList().elements();
/*  783 */     while (localEnumeration.hasMoreElements())
/*  784 */       paramPhrase.getNoteList().addElement(localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void shuffle(Phrase paramPhrase)
/*      */   {
/*  796 */     if (paramPhrase == null) {
/*  797 */       return;
/*      */     }
/*      */ 
/*  800 */     Phrase localPhrase = new Phrase();
/*      */ 
/*  802 */     localPhrase.addNote(paramPhrase.getNote((int)(Math.random() * paramPhrase.size())));
/*      */ 
/*  804 */     for (int i = 0; i < paramPhrase.size() - 1; )
/*      */     {
/*  806 */       Note localNote = paramPhrase.getNote((int)(Math.random() * paramPhrase.size()));
/*      */ 
/*  808 */       int j = 1;
/*  809 */       for (int k = 0; k < localPhrase.size(); ++k) {
/*  810 */         if (localNote == localPhrase.getNote(k)) {
/*  811 */           j = 0;
/*      */         }
/*      */ 
/*      */       }
/*      */ 
/*  816 */       if (j != 0) {
/*  817 */         localPhrase.addNote(localNote);
/*  818 */         ++i;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/*  823 */     paramPhrase.getNoteList().removeAllElements();
/*  824 */     Enumeration localEnumeration = localPhrase.getNoteList().elements();
/*  825 */     while (localEnumeration.hasMoreElements())
/*  826 */       paramPhrase.getNoteList().addElement(localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void palindrome(Phrase paramPhrase)
/*      */   {
/*  839 */     palindrome(paramPhrase, true);
/*      */   }
/*      */ 
/*      */   public static void palindrome(Phrase paramPhrase, boolean paramBoolean)
/*      */   {
/*  852 */     if (paramPhrase == null) {
/*  853 */       return;
/*      */     }
/*      */ 
/*  856 */     int i = (paramBoolean) ? paramPhrase.size() : paramPhrase.size() - 1;
/*      */ 
/*  859 */     for (int j = i - 1; j >= 0; --j)
/*  860 */       paramPhrase.addNote(paramPhrase.getNote(j));
/*      */   }
/*      */ 
/*      */   public static void rotate(Phrase paramPhrase)
/*      */   {
/*  873 */     rotate(paramPhrase, 1);
/*      */   }
/*      */ 
/*      */   public static void rotate(Phrase paramPhrase, int paramInt)
/*      */   {
/*  887 */     if (paramPhrase == null) {
/*  888 */       return;
/*      */     }
/*  890 */     Vector localVector = paramPhrase.getNoteList();
/*  891 */     for (int i = 0; i < paramInt; ++i)
/*      */     {
/*  894 */       localVector.insertElementAt(localVector.lastElement(), 0);
/*  895 */       localVector.removeElementAt(localVector.size() - 1);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void retrograde(Phrase paramPhrase)
/*      */   {
/*  907 */     if (paramPhrase == null) {
/*  908 */       return;
/*      */     }
/*  910 */     Phrase localPhrase = new Phrase();
/*  911 */     for (int i = paramPhrase.size(); i > 0; --i) {
/*  912 */       localPhrase.addNote(paramPhrase.getNote(i - 1));
/*      */     }
/*      */ 
/*  916 */     paramPhrase.getNoteList().removeAllElements();
/*  917 */     Enumeration localEnumeration = localPhrase.getNoteList().elements();
/*  918 */     while (localEnumeration.hasMoreElements())
/*  919 */       paramPhrase.getNoteList().addElement(localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void inversion(Phrase paramPhrase)
/*      */   {
/*  938 */     if (paramPhrase == null) {
/*  939 */       return;
/*      */     }
/*      */ 
/*  942 */     int i = 0;
/*  943 */     int j = -2147483648;
/*      */ 
/*  946 */     while ((i < paramPhrase.size()) && (j == -2147483648)) {
/*  947 */       j = paramPhrase.getNote(i++).getPitch();
/*      */     }
/*      */ 
/*  950 */     for (; i < paramPhrase.size(); ++i)
/*      */     {
/*  953 */       paramPhrase.getNote(i).setPitch(j - (paramPhrase.getNote(i).getPitch() - j));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void changeLength(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  970 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/*  971 */       return;
/*      */     }
/*  973 */     double d = paramPhrase.getEndTime() - paramPhrase.getStartTime();
/*  974 */     elongate(paramPhrase, paramDouble / d);
/*      */   }
/*      */ 
/*      */   public static void elongate(Phrase paramPhrase, double paramDouble)
/*      */   {
/*  987 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/*  988 */       return;
/*      */     }
/*      */ 
/*  991 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/*  992 */     while (localEnumeration.hasMoreElements()) {
/*  993 */       Note localNote = (Note)localEnumeration.nextElement();
/*  994 */       localNote.setRhythmValue(localNote.getRhythmValue() * paramDouble);
/*  995 */       localNote.setDuration(localNote.getDuration() * paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void accents(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1017 */     double[] arrayOfDouble = { 0.0D };
/* 1018 */     accents(paramPhrase, paramDouble, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public static void accents(Phrase paramPhrase, double paramDouble, double[] paramArrayOfDouble)
/*      */   {
/* 1049 */     accents(paramPhrase, paramDouble, paramArrayOfDouble, 20);
/*      */   }
/*      */ 
/*      */   public static void accents(Phrase paramPhrase, double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*      */   {
/* 1087 */     if ((paramPhrase == null) || (paramDouble <= 0.0D)) {
/* 1088 */       return;
/*      */     }
/* 1090 */     for (int i = 0; i < paramArrayOfDouble.length; ++i) {
/* 1091 */       if ((paramArrayOfDouble[i] < 0.0D) || (paramArrayOfDouble[i] >= paramDouble)) {
/* 1092 */         return;
/*      */       }
/*      */     }
/*      */ 
/* 1096 */     double d = (paramPhrase.getStartTime() < 0.0D) ? 0.0D : paramPhrase.getStartTime();
/*      */ 
/* 1099 */     Vector localVector = paramPhrase.getNoteList();
/* 1100 */     for (int j = 0; j < localVector.size(); ++j) {
/* 1101 */       Note localNote = (Note)localVector.elementAt(j);
/*      */ 
/* 1105 */       for (int k = 0; k < paramArrayOfDouble.length; ++k) {
/* 1106 */         if (d % paramDouble == paramArrayOfDouble[k]) {
/* 1107 */           int l = localNote.getDynamic();
/* 1108 */           l += paramInt;
/* 1109 */           localNote.setDynamic(l);
/*      */         }
/*      */       }
/* 1112 */       d += localNote.getRhythmValue();
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void normalise(Phrase paramPhrase)
/*      */   {
/* 1127 */     if (paramPhrase == null) {
/* 1128 */       return;
/*      */     }
/*      */ 
/* 1131 */     int i = 0;
/* 1132 */     Enumeration localEnumeration1 = paramPhrase.getNoteList().elements();
/* 1133 */     while (localEnumeration1.hasMoreElements()) {
/* 1134 */       Note localNote1 = (Note)localEnumeration1.nextElement();
/* 1135 */       if (localNote1.getDynamic() > i) i = localNote1.getDynamic();
/*      */     }
/*      */ 
/* 1138 */     if (i == 127) {
/* 1139 */       return;
/*      */     }
/* 1141 */     int j = 127 - i;
/* 1142 */     Enumeration localEnumeration2 = paramPhrase.getNoteList().elements();
/* 1143 */     while (localEnumeration2.hasMoreElements()) {
/* 1144 */       Note localNote2 = (Note)localEnumeration2.nextElement();
/* 1145 */       localNote2.setDynamic(localNote2.getDynamic() + j);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shake(Phrase paramPhrase)
/*      */   {
/* 1158 */     shake(paramPhrase, 20);
/*      */   }
/*      */ 
/*      */   public static void shake(Phrase paramPhrase, int paramInt)
/*      */   {
/* 1173 */     if (paramPhrase == null) {
/* 1174 */       return;
/*      */     }
/*      */ 
/* 1177 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1178 */     while (localEnumeration.hasMoreElements()) {
/* 1179 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1180 */       int i = localNote.getDynamic();
/*      */       int j;
/*      */       do
/* 1183 */         j = i + (int)(Math.random() * 2.0D * paramInt - paramInt);
/* 1184 */       while ((j < 0) || (j > 127));
/* 1185 */       localNote.setDynamic(j);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void mutate(Phrase paramPhrase)
/*      */   {
/* 1196 */     mutate(paramPhrase, 1, 1, CHROMATIC_SCALE, paramPhrase.getLowestPitch(), paramPhrase.getHighestPitch(), new double[] { 0.25D, 0.5D, 1.0D, 1.5D, 2.0D });
/*      */   }
/*      */ 
/*      */   public static void mutate(Phrase paramPhrase, int paramInt, int[] paramArrayOfInt)
/*      */   {
/* 1217 */     mutate(paramPhrase, 1, 0, paramArrayOfInt, paramPhrase.getLowestPitch(), paramPhrase.getHighestPitch(), new double[0]);
/*      */   }
/*      */ 
/*      */   public static void mutate(Phrase paramPhrase, int paramInt1, int paramInt2, int[] paramArrayOfInt, int paramInt3, int paramInt4, double[] paramArrayOfDouble)
/*      */   {
/*      */     Note localNote;
/* 1240 */     for (int i = 0; i < paramInt1; ++i) {
/* 1241 */       int k = (int)(Math.random() * (paramInt4 - paramInt3) + paramInt3);
/* 1242 */       int l = (int)(Math.random() * paramPhrase.size());
/* 1243 */       localNote = paramPhrase.getNote(l);
/* 1244 */       localNote.setPitch(k);
/* 1245 */       while (!(localNote.isScale(paramArrayOfInt))) {
/* 1246 */         k = (int)(Math.random() * (paramInt4 - paramInt3) + paramInt3);
/* 1247 */         l = (int)(Math.random() * paramPhrase.size());
/* 1248 */         localNote = paramPhrase.getNote(l);
/*      */       }
/*      */     }
/*      */ 
/* 1252 */     for (int j = 0; j < paramInt2; ++j) {
/* 1253 */       double d = paramArrayOfDouble[(int)(Math.random() * paramArrayOfDouble.length)];
/* 1254 */       localNote = paramPhrase.getNote((int)(Math.random() * paramPhrase.size()));
/* 1255 */       localNote.setRhythmValue(d);
/* 1256 */       localNote.setDuration(d * 0.9D);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tiePitches(Phrase paramPhrase)
/*      */   {
/* 1271 */     for (int i = 0; i < paramPhrase.size() - 1; ) {
/* 1272 */       Note localNote1 = paramPhrase.getNote(i);
/* 1273 */       Note localNote2 = paramPhrase.getNote(i + 1);
/* 1274 */       if (localNote1.getPitch() == localNote2.getPitch()) {
/* 1275 */         localNote1.setRhythmValue(localNote1.getRhythmValue() + localNote2.getRhythmValue());
/* 1276 */         localNote1.setDuration(localNote1.getDuration() + localNote2.getDuration());
/* 1277 */         paramPhrase.removeNote(i + 1); } else {
/* 1278 */         ++i;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tieRests(Phrase paramPhrase)
/*      */   {
/* 1291 */     for (int i = 0; i < paramPhrase.size() - 1; ) {
/* 1292 */       Note localNote1 = paramPhrase.getNote(i);
/* 1293 */       Note localNote2 = paramPhrase.getNote(i + 1);
/* 1294 */       if ((localNote1.getPitch() == -2147483648) && (localNote2.getPitch() == -2147483648)) {
/* 1295 */         localNote1.setRhythmValue(localNote1.getRhythmValue() + localNote2.getRhythmValue());
/* 1296 */         localNote1.setDuration(localNote1.getDuration() + localNote2.getDuration());
/* 1297 */         paramPhrase.removeNote(i + 1); } else {
/* 1298 */         ++i;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fillRests(Phrase paramPhrase)
/*      */   {
/* 1312 */     for (int i = 0; i < paramPhrase.size() - 1; ) {
/* 1313 */       Note localNote1 = paramPhrase.getNote(i);
/* 1314 */       Note localNote2 = paramPhrase.getNote(i + 1);
/* 1315 */       if ((localNote1.getPitch() != -2147483648) && (localNote2.getPitch() == -2147483648)) {
/* 1316 */         localNote1.setRhythmValue(localNote1.getRhythmValue() + localNote2.getRhythmValue());
/* 1317 */         localNote1.setDuration(localNote1.getDuration() + localNote2.getDuration());
/* 1318 */         paramPhrase.removeNote(i + 1); } else {
/* 1319 */         ++i;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void spread(Phrase paramPhrase)
/*      */   {
/* 1331 */     if (paramPhrase == null) {
/* 1332 */       return;
/*      */     }
/*      */ 
/* 1335 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1336 */     while (localEnumeration.hasMoreElements()) {
/* 1337 */       Note localNote = (Note)localEnumeration.nextElement();
/*      */ 
/* 1339 */       localNote.setPan(Math.random());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void bounce(Phrase paramPhrase)
/*      */   {
/* 1352 */     if (paramPhrase == null) {
/* 1353 */       return;
/*      */     }
/* 1355 */     int i = 1;
/* 1356 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1357 */     while (localEnumeration.hasMoreElements()) {
/* 1358 */       Note localNote = (Note)localEnumeration.nextElement();
/*      */ 
/* 1360 */       if (i != 0) localNote.setPan(0.0D);
/*      */       else localNote.setPan(1.0D);
/* 1362 */       i = (i == 0) ? 1 : 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void varyLength(Phrase paramPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/* 1375 */     if ((paramPhrase == null) || (paramDouble2 < paramDouble1)) {
/* 1376 */       return;
/*      */     }
/* 1378 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1379 */     while (localEnumeration.hasMoreElements()) {
/* 1380 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1381 */       double d = Math.random() * (paramDouble2 - paramDouble1) + paramDouble1;
/* 1382 */       localNote.setDuration(d);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void randomize(Phrase paramPhrase, int paramInt)
/*      */   {
/* 1395 */     randomize(paramPhrase, paramInt, 0.0D);
/*      */   }
/*      */ 
/*      */   public static void randomize(Phrase paramPhrase, int paramInt, double paramDouble)
/*      */   {
/* 1408 */     randomize(paramPhrase, paramInt, paramDouble, 0);
/*      */   }
/*      */ 
/*      */   public static void randomize(Phrase paramPhrase, int paramInt1, double paramDouble, int paramInt2)
/*      */   {
/* 1422 */     if (paramPhrase == null) {
/* 1423 */       return;
/*      */     }
/* 1425 */     int i = 1;
/* 1426 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1427 */     while (localEnumeration.hasMoreElements()) {
/* 1428 */       Note localNote = (Note)localEnumeration.nextElement();
/*      */ 
/* 1430 */       if (paramInt1 > 0) {
/* 1431 */         localNote.setPitch(localNote.getPitch() + (int)(Math.random() * paramInt1 * 2 - paramInt1));
/*      */       }
/*      */ 
/* 1435 */       if (paramDouble > 0.0D) {
/* 1436 */         double d = Math.random() * paramDouble * 2.0D - paramDouble;
/* 1437 */         localNote.setRhythmValue(localNote.getRhythmValue() + d);
/* 1438 */         localNote.setDuration(localNote.getDuration() + d);
/*      */       }
/*      */ 
/* 1441 */       if (paramInt2 > 0)
/* 1442 */         localNote.setDynamic(localNote.getDynamic() + (int)(Math.random() * paramInt2 * 2 - paramInt2));
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurUp(Phrase paramPhrase)
/*      */   {
/* 1457 */     slurUp(paramPhrase, 2);
/*      */   }
/*      */ 
/*      */   public static void slurDown(Phrase paramPhrase)
/*      */   {
/* 1469 */     slurDown(paramPhrase, 2);
/*      */   }
/*      */ 
/*      */   public static void slurUp(Phrase paramPhrase, int paramInt)
/*      */   {
/* 1482 */     if ((paramPhrase == null) || (paramPhrase.size() < paramInt) || (paramInt < 2)) {
/* 1483 */       System.err.println("jMusic Mod.slurUp error: Arguments not valid.");
/* 1484 */       return;
/*      */     }
/* 1486 */     int i = 0;
/* 1487 */     int j = paramPhrase.size() - paramInt;
/* 1488 */     for (int k = 0; k < j; ) {
/* 1489 */       for (int l = 0; l < paramInt - 1; ++l) {
/* 1490 */         if ((paramPhrase.getNote(k + l).getPitch() >= 0) && (paramPhrase.getNote(k + l).getPitch() < paramPhrase.getNote(k + l + 1).getPitch()))
/*      */         {
/* 1492 */           i = 1;
/*      */         } else {
/* 1494 */           i = 0;
/* 1495 */           break;
/*      */         }
/*      */       }
/* 1498 */       if (i != 0) {
/* 1499 */         for (l = 0; l < paramInt - 1; ++l) {
/* 1500 */           paramPhrase.getNote(k + l).setDuration(paramPhrase.getNote(k + l).getRhythmValue());
/*      */         }
/* 1502 */         k += paramInt - 1; } else {
/* 1503 */         ++k; }
/* 1504 */       i = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurDown(Phrase paramPhrase, int paramInt)
/*      */   {
/* 1519 */     if ((paramPhrase == null) || (paramPhrase.size() < paramInt) || (paramInt < 2)) {
/* 1520 */       System.err.println("jMusic Mod.slurDown error: Arguments not valid.");
/* 1521 */       return;
/*      */     }
/* 1523 */     int i = 0;
/* 1524 */     int j = paramPhrase.size() - paramInt;
/* 1525 */     for (int k = 0; k < j; ) {
/* 1526 */       for (int l = 0; l < paramInt - 1; ++l) {
/* 1527 */         if ((paramPhrase.getNote(k + l).getPitch() >= 0) && (paramPhrase.getNote(k + l).getPitch() > paramPhrase.getNote(k + l + 1).getPitch()))
/*      */         {
/* 1529 */           i = 1;
/*      */         } else {
/* 1531 */           i = 0;
/* 1532 */           break;
/*      */         }
/*      */       }
/* 1535 */       if (i != 0) {
/* 1536 */         for (l = 0; l < paramInt - 1; ++l) {
/* 1537 */           paramPhrase.getNote(k + l).setDuration(paramPhrase.getNote(k + l).getRhythmValue());
/*      */         }
/* 1539 */         k += paramInt - 1; } else {
/* 1540 */         ++k; }
/* 1541 */       i = 0;
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDuration(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1551 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1552 */     while (localEnumeration.hasMoreElements()) {
/* 1553 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1554 */       localNote.setDuration(localNote.getDuration() * paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToDuration(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1565 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1566 */     while (localEnumeration.hasMoreElements()) {
/* 1567 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1568 */       localNote.setDuration(localNote.getDuration() + paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToRhythmValue(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1578 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1579 */     while (localEnumeration.hasMoreElements()) {
/* 1580 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1581 */       localNote.setRhythmValue(localNote.getRhythmValue() + paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToLength(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1591 */     Enumeration localEnumeration = paramPhrase.getNoteList().elements();
/* 1592 */     double d = 0.0D;
/* 1593 */     while (localEnumeration.hasMoreElements()) {
/* 1594 */       Note localNote = (Note)localEnumeration.nextElement();
/* 1595 */       d = localNote.getRhythmValue() / localNote.getDuration();
/* 1596 */       localNote.setRhythmValue(localNote.getRhythmValue() + paramDouble);
/* 1597 */       localNote.setDuration(localNote.getRhythmValue() * d);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void expandIntervals(Phrase paramPhrase, double paramDouble)
/*      */   {
/* 1607 */     int i = paramPhrase.size();
/* 1608 */     if (i < 2) return;
/* 1609 */     Note localNote1 = paramPhrase.getNote(0);
/* 1610 */     for (int j = 1; j < i; ++j) {
/* 1611 */       Note localNote2 = paramPhrase.getNote(j);
/* 1612 */       int k = (int)((localNote2.getPitch() - localNote1.getPitch()) * paramDouble);
/* 1613 */       localNote2.setPitch(localNote2.getPitch() + k);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void transpose(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 1632 */     if (paramCPhrase == null) {
/* 1633 */       return;
/*      */     }
/*      */ 
/* 1636 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1637 */     while (localEnumeration.hasMoreElements())
/* 1638 */       transpose((Phrase)localEnumeration.nextElement(), paramInt);
/*      */   }
/*      */ 
/*      */   public static void transpose(CPhrase paramCPhrase, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/* 1664 */     if (paramCPhrase == null) {
/* 1665 */       return;
/*      */     }
/*      */ 
/* 1668 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1669 */     while (localEnumeration.hasMoreElements())
/* 1670 */       transpose((Phrase)localEnumeration.nextElement(), paramInt1, paramArrayOfInt, paramInt2);
/*      */   }
/*      */ 
/*      */   public static void repeat(CPhrase paramCPhrase)
/*      */   {
/* 1683 */     repeat(paramCPhrase, 2);
/*      */   }
/*      */ 
/*      */   public static void repeat(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 1696 */     if (paramCPhrase == null) {
/* 1697 */       return;
/*      */     }
/*      */ 
/* 1700 */     int i = paramCPhrase.getPhraseList().size();
/* 1701 */     for (int j = 0; j < paramInt - 1; ++j) {
/* 1702 */       double d = paramCPhrase.getEndTime();
/* 1703 */       for (int k = 0; k < i; ++k) {
/* 1704 */         Phrase localPhrase1 = (Phrase)paramCPhrase.getPhraseList().elementAt(k);
/* 1705 */         Phrase localPhrase2 = localPhrase1.copy();
/* 1706 */         localPhrase2.setStartTime(d + localPhrase1.getStartTime());
/* 1707 */         paramCPhrase.addPhrase(localPhrase2);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void repeat(CPhrase paramCPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/* 1724 */     repeat(paramCPhrase, 2, paramDouble1, paramDouble2);
/*      */   }
/*      */ 
/*      */   public static void repeat(CPhrase paramCPhrase, int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/* 1741 */     if ((paramCPhrase == null) || (paramDouble1 >= paramDouble2) || (paramInt < 2)) {
/* 1742 */       return;
/*      */     }
/* 1744 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1745 */     while (localEnumeration.hasMoreElements()) {
/* 1746 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 1747 */       repeat(localPhrase, paramInt, paramDouble1 - paramCPhrase.getStartTime(), paramDouble2 - paramCPhrase.getStartTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 1764 */     if ((paramCPhrase == null) || (paramDouble <= 0.0D)) {
/* 1765 */       return;
/*      */     }
/* 1767 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1768 */     while (localEnumeration.hasMoreElements()) {
/* 1769 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 1771 */       fadeIn(localPhrase, paramDouble, localPhrase.getStartTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(CPhrase paramCPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/* 1791 */     if ((paramCPhrase == null) || (paramDouble1 < 0.0D) || (paramDouble2 < 0.0D) || (paramDouble1 <= paramDouble2))
/*      */     {
/* 1793 */       return;
/*      */     }
/*      */ 
/* 1796 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1797 */     while (localEnumeration.hasMoreElements()) {
/* 1798 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 1801 */       fadeIn(localPhrase, paramDouble1, paramDouble2 + localPhrase.getStartTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 1817 */     if ((paramCPhrase == null) || (paramDouble <= 0.0D)) {
/* 1818 */       return;
/*      */     }
/*      */ 
/* 1821 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1822 */     while (localEnumeration.hasMoreElements()) {
/* 1823 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 1827 */       fadeOut(localPhrase, paramDouble, paramCPhrase.getEndTime() - localPhrase.getEndTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(CPhrase paramCPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/* 1850 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1851 */     while (localEnumeration.hasMoreElements()) {
/* 1852 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 1856 */       fadeOut(localPhrase, paramDouble1, paramDouble2 + paramCPhrase.getEndTime() - localPhrase.getEndTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void compress(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 1874 */     if (paramCPhrase == null) {
/* 1875 */       return;
/*      */     }
/*      */ 
/* 1879 */     double d = 0.0D;
/*      */ 
/* 1881 */     int k = 0;
/*      */ 
/* 1883 */     Enumeration localEnumeration1 = paramCPhrase.getPhraseList().elements();
/*      */     Phrase localPhrase;
/*      */     Enumeration localEnumeration2;
/*      */     Note localNote;
/*      */     int i;
/* 1884 */     while (localEnumeration1.hasMoreElements()) {
/* 1885 */       localPhrase = (Phrase)localEnumeration1.nextElement();
/* 1886 */       if (localPhrase == null) {
/*      */         break;
/*      */       }
/* 1889 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 1890 */       while (localEnumeration2.hasMoreElements()) {
/* 1891 */         localNote = (Note)localEnumeration2.nextElement();
/* 1892 */         if (localNote.getPitch() != -2147483648) {
/* 1893 */           i = localNote.getDynamic();
/* 1894 */           d += i;
/* 1895 */           ++k;
/*      */         }
/*      */       }
/*      */     }
/* 1899 */     int j = (int)(d / k);
/*      */ 
/* 1902 */     localEnumeration1 = paramCPhrase.getPhraseList().elements();
/* 1903 */     while (localEnumeration1.hasMoreElements()) {
/* 1904 */       localPhrase = (Phrase)localEnumeration1.nextElement();
/* 1905 */       if (localPhrase == null) {
/*      */         return;
/*      */       }
/* 1908 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 1909 */       while (localEnumeration2.hasMoreElements()) {
/* 1910 */         localNote = (Note)localEnumeration2.nextElement();
/* 1911 */         System.out.println("note was =" + localNote.getDynamic());
/* 1912 */         i = (int)(j + (localNote.getDynamic() - j) * paramDouble);
/* 1913 */         localNote.setDynamic(i);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void append(CPhrase paramCPhrase1, CPhrase paramCPhrase2)
/*      */   {
/* 1928 */     if ((paramCPhrase1 == null) || (paramCPhrase2 == null)) {
/* 1929 */       return;
/*      */     }
/*      */ 
/* 1934 */     double d = paramCPhrase1.getEndTime();
/* 1935 */     Enumeration localEnumeration = paramCPhrase2.getPhraseList().elements();
/* 1936 */     while (localEnumeration.hasMoreElements()) {
/* 1937 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 1938 */       localPhrase.setStartTime(d + localPhrase.getStartTime());
/* 1939 */       paramCPhrase1.addPhrase(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void merge(CPhrase paramCPhrase1, CPhrase paramCPhrase2)
/*      */   {
/* 1953 */     if ((paramCPhrase1 == null) || (paramCPhrase2 == null)) {
/* 1954 */       return;
/*      */     }
/*      */ 
/* 1959 */     Enumeration localEnumeration = paramCPhrase2.getPhraseList().elements();
/* 1960 */     while (localEnumeration.hasMoreElements())
/* 1961 */       paramCPhrase1.addPhrase((Phrase)localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void quantize(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 1974 */     quantise(paramCPhrase, paramDouble);
/*      */   }
/*      */ 
/*      */   public static void quantise(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 1989 */     if ((paramCPhrase == null) || (paramDouble <= 0.0D)) {
/* 1990 */       return;
/*      */     }
/*      */ 
/* 1993 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 1994 */     while (localEnumeration.hasMoreElements())
/* 1995 */       quantise((Phrase)localEnumeration.nextElement(), paramDouble);
/*      */   }
/*      */ 
/*      */   public static void shuffle(CPhrase paramCPhrase)
/*      */   {
/* 2004 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2005 */     while (localEnumeration.hasMoreElements())
/* 2006 */       shuffle((Phrase)localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void cycle(Part paramPart, double paramDouble)
/*      */   {
/* 2017 */     if ((paramPart == null) || (paramDouble <= 0.0D) || (paramDouble == paramPart.getEndTime())) {
/* 2018 */       return;
/*      */     }
/* 2020 */     double d1 = paramPart.getEndTime();
/*      */ 
/* 2023 */     if (paramDouble < d1) {
/* 2024 */       Part localPart1 = paramPart.copy(0.0D, paramDouble);
/* 2025 */       paramPart.empty();
/* 2026 */       paramPart.addPhraseList(localPart1.getPhraseArray());
/* 2027 */       return;
/*      */     }
/*      */ 
/* 2031 */     int i = 1;
/* 2032 */     double d2 = paramDouble;
/* 2033 */     for (d2 = paramDouble; (int)(d2 / d1) > 1; d2 -= d1) {
/* 2034 */       Phrase[] arrayOfPhrase1 = paramPart.getPhraseArray();
/* 2035 */       for (int j = 0; j < arrayOfPhrase1.length; ++j)
/*      */       {
/* 2037 */         arrayOfPhrase1[j].setStartTime(arrayOfPhrase1[j].getStartTime() + i * d1);
/*      */ 
/* 2039 */         paramPart.addPhrase(arrayOfPhrase1[j]);
/*      */       }
/* 2041 */       ++i;
/*      */     }
/*      */ 
/* 2045 */     double d3 = paramDouble - (i * d1);
/*      */ 
/* 2047 */     if (d3 > 0.0D) {
/* 2048 */       Part localPart2 = paramPart.copy(0.0D, d3, true, true, false);
/* 2049 */       Phrase[] arrayOfPhrase2 = localPart2.getPhraseArray();
/* 2050 */       for (int k = 0; k < arrayOfPhrase2.length; ++k)
/*      */       {
/* 2052 */         arrayOfPhrase2[k].setStartTime(arrayOfPhrase2[k].getStartTime() + i * d1);
/*      */ 
/* 2054 */         paramPart.addPhrase(arrayOfPhrase2[k]);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void elongate(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2072 */     if ((paramCPhrase == null) || (paramDouble <= 0.0D)) {
/* 2073 */       return;
/*      */     }
/* 2075 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2076 */     while (localEnumeration.hasMoreElements()) {
/* 2077 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2078 */       elongate(localPhrase, paramDouble);
/* 2079 */       localPhrase.setStartTime(localPhrase.getStartTime() * paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void accents(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2101 */     double[] arrayOfDouble = { 0.0D };
/* 2102 */     accents(paramCPhrase, paramDouble, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public static void accents(CPhrase paramCPhrase, double paramDouble, double[] paramArrayOfDouble)
/*      */   {
/* 2133 */     accents(paramCPhrase, paramDouble, paramArrayOfDouble, 20);
/*      */   }
/*      */ 
/*      */   public static void accents(CPhrase paramCPhrase, double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*      */   {
/* 2171 */     if ((paramCPhrase == null) || (paramDouble <= 0.0D)) {
/* 2172 */       return;
/*      */     }
/* 2174 */     for (int i = 0; i < paramArrayOfDouble.length; ++i) {
/* 2175 */       if ((paramArrayOfDouble[i] < 0.0D) || (paramArrayOfDouble[i] >= paramDouble)) {
/* 2176 */         return;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 2181 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2182 */     while (localEnumeration.hasMoreElements()) {
/* 2183 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2184 */       accents(localPhrase, paramDouble, paramArrayOfDouble, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void normalise(CPhrase paramCPhrase)
/*      */   {
/* 2197 */     if (paramCPhrase == null) {
/* 2198 */       return;
/*      */     }
/*      */ 
/* 2201 */     int i = 0;
/* 2202 */     Enumeration localEnumeration1 = paramCPhrase.getPhraseList().elements();
/*      */     Object localObject;
/* 2203 */     while (localEnumeration1.hasMoreElements()) {
/* 2204 */       Phrase localPhrase = (Phrase)localEnumeration1.nextElement();
/* 2205 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 2206 */       while (localEnumeration2.hasMoreElements()) {
/* 2207 */         localObject = (Note)localEnumeration2.nextElement();
/* 2208 */         if (((Note)localObject).getDynamic() > i) i = ((Note)localObject).getDynamic();
/*      */       }
/*      */     }
/*      */ 
/* 2212 */     if (i == 127) {
/* 2213 */       return;
/*      */     }
/* 2215 */     int j = 127 - i;
/* 2216 */     Enumeration localEnumeration2 = paramCPhrase.getPhraseList().elements();
/* 2217 */     while (localEnumeration2.hasMoreElements()) {
/* 2218 */       localObject = (Phrase)localEnumeration2.nextElement();
/* 2219 */       Enumeration localEnumeration3 = ((Phrase)localObject).getNoteList().elements();
/* 2220 */       while (localEnumeration3.hasMoreElements()) {
/* 2221 */         Note localNote = (Note)localEnumeration3.nextElement();
/* 2222 */         localNote.setDynamic(localNote.getDynamic() + j);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void spread(CPhrase paramCPhrase)
/*      */   {
/* 2235 */     if (paramCPhrase == null) {
/* 2236 */       return;
/*      */     }
/*      */ 
/* 2239 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2240 */     while (localEnumeration.hasMoreElements()) {
/* 2241 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 2243 */       spread(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void bounce(CPhrase paramCPhrase)
/*      */   {
/* 2255 */     if (paramCPhrase == null) {
/* 2256 */       return;
/*      */     }
/* 2258 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2259 */     while (localEnumeration.hasMoreElements()) {
/* 2260 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2261 */       bounce(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tiePitches(CPhrase paramCPhrase)
/*      */   {
/* 2276 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2277 */     while (localEnumeration.hasMoreElements()) {
/* 2278 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2279 */       tiePitches(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tieRests(CPhrase paramCPhrase)
/*      */   {
/* 2292 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2293 */     while (localEnumeration.hasMoreElements()) {
/* 2294 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2295 */       tieRests(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fillRests(CPhrase paramCPhrase)
/*      */   {
/* 2309 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2310 */     while (localEnumeration.hasMoreElements()) {
/* 2311 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2312 */       fillRests(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void varyLength(CPhrase paramCPhrase, double paramDouble1, double paramDouble2)
/*      */   {
/* 2325 */     if ((paramCPhrase == null) || (paramDouble2 < paramDouble1)) {
/* 2326 */       return;
/*      */     }
/* 2328 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2329 */     while (localEnumeration.hasMoreElements()) {
/* 2330 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2331 */       varyLength(localPhrase, paramDouble1, paramDouble2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void randomize(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 2344 */     randomize(paramCPhrase, paramInt, 0.0D);
/*      */   }
/*      */ 
/*      */   public static void randomize(CPhrase paramCPhrase, int paramInt, double paramDouble)
/*      */   {
/* 2357 */     randomize(paramCPhrase, paramInt, paramDouble, 0);
/*      */   }
/*      */ 
/*      */   public static void randomize(CPhrase paramCPhrase, int paramInt1, double paramDouble, int paramInt2)
/*      */   {
/* 2371 */     if (paramCPhrase == null) {
/* 2372 */       return;
/*      */     }
/* 2374 */     int i = 1;
/* 2375 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2376 */     while (localEnumeration.hasMoreElements()) {
/* 2377 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2378 */       randomize(localPhrase, paramInt1, paramDouble, paramInt2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurUp(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 2392 */     if (paramCPhrase == null) {
/* 2393 */       return;
/*      */     }
/* 2395 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2396 */     while (localEnumeration.hasMoreElements()) {
/* 2397 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2398 */       slurUp(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurDown(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 2412 */     if (paramCPhrase == null) {
/* 2413 */       return;
/*      */     }
/* 2415 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2416 */     while (localEnumeration.hasMoreElements()) {
/* 2417 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2418 */       slurDown(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDuration(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2428 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2429 */     while (localEnumeration.hasMoreElements()) {
/* 2430 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2431 */       increaseDuration(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToDuration(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2442 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2443 */     while (localEnumeration.hasMoreElements()) {
/* 2444 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2445 */       addToDuration(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToRhythmValue(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2455 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2456 */     while (localEnumeration.hasMoreElements()) {
/* 2457 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2458 */       addToRhythmValue(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToLength(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2469 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2470 */     while (localEnumeration.hasMoreElements()) {
/* 2471 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2472 */       addToLength(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void expandIntervals(CPhrase paramCPhrase, double paramDouble)
/*      */   {
/* 2482 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2483 */     while (localEnumeration.hasMoreElements()) {
/* 2484 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2485 */       expandIntervals(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shake(CPhrase paramCPhrase, int paramInt)
/*      */   {
/* 2495 */     Enumeration localEnumeration = paramCPhrase.getPhraseList().elements();
/* 2496 */     while (localEnumeration.hasMoreElements()) {
/* 2497 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2498 */       shake(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void repeat(Part paramPart)
/*      */   {
/* 2511 */     repeat(paramPart, 2);
/*      */   }
/*      */ 
/*      */   public static void repeat(Part paramPart, int paramInt)
/*      */   {
/* 2524 */     if (paramPart == null) {
/* 2525 */       return;
/*      */     }
/*      */ 
/* 2528 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2529 */     while (localEnumeration.hasMoreElements()) {
/* 2530 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2531 */       repeat(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void repeat(Part paramPart, double paramDouble1, double paramDouble2)
/*      */   {
/* 2561 */     repeat(paramPart, 2, paramDouble1, paramDouble2);
/*      */   }
/*      */ 
/*      */   public static void repeat(Part paramPart, int paramInt, double paramDouble1, double paramDouble2)
/*      */   {
/* 2578 */     if ((paramPart == null) || (paramDouble1 >= paramDouble2) || (paramInt < 2)) {
/* 2579 */       return;
/*      */     }
/* 2581 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2582 */     while (localEnumeration.hasMoreElements()) {
/* 2583 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2584 */       repeat(localPhrase, paramInt, paramDouble1, paramDouble2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void transpose(Part paramPart, int paramInt)
/*      */   {
/* 2596 */     if ((paramPart == null) || (paramInt == 0)) return;
/* 2597 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2598 */     while (localEnumeration.hasMoreElements()) {
/* 2599 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2600 */       transpose(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void transpose(Part paramPart, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/* 2626 */     if (paramPart == null) {
/* 2627 */       return;
/*      */     }
/*      */ 
/* 2630 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2631 */     while (localEnumeration.hasMoreElements())
/* 2632 */       transpose((Phrase)localEnumeration.nextElement(), paramInt1, paramArrayOfInt, paramInt2);
/*      */   }
/*      */ 
/*      */   public static void compress(Part paramPart, double paramDouble)
/*      */   {
/* 2651 */     if (paramPart == null) {
/* 2652 */       return;
/*      */     }
/*      */ 
/* 2657 */     double d = 0.0D;
/*      */ 
/* 2659 */     int k = 0;
/*      */ 
/* 2661 */     Enumeration localEnumeration1 = paramPart.getPhraseList().elements();
/*      */     Phrase localPhrase;
/*      */     Enumeration localEnumeration2;
/*      */     Note localNote;
/*      */     int i;
/* 2662 */     while (localEnumeration1.hasMoreElements()) {
/* 2663 */       localPhrase = (Phrase)localEnumeration1.nextElement();
/* 2664 */       if (localPhrase == null) {
/*      */         break;
/*      */       }
/* 2667 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 2668 */       while (localEnumeration2.hasMoreElements()) {
/* 2669 */         localNote = (Note)localEnumeration2.nextElement();
/* 2670 */         if (localNote.getPitch() != -2147483648) {
/* 2671 */           i = localNote.getDynamic();
/* 2672 */           d += i;
/* 2673 */           ++k;
/*      */         }
/*      */       }
/*      */     }
/* 2677 */     int j = (int)(d / k);
/*      */ 
/* 2680 */     localEnumeration1 = paramPart.getPhraseList().elements();
/* 2681 */     while (localEnumeration1.hasMoreElements()) {
/* 2682 */       localPhrase = (Phrase)localEnumeration1.nextElement();
/* 2683 */       if (localPhrase == null) {
/*      */         return;
/*      */       }
/* 2686 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 2687 */       while (localEnumeration2.hasMoreElements()) {
/* 2688 */         localNote = (Note)localEnumeration2.nextElement();
/* 2689 */         System.out.println("note was =" + localNote.getDynamic());
/* 2690 */         i = (int)(j + (localNote.getDynamic() - j) * paramDouble);
/* 2691 */         localNote.setDynamic(i);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void append(Part paramPart1, Part paramPart2)
/*      */   {
/* 2706 */     append(paramPart1, paramPart2, paramPart1.getEndTime());
/*      */   }
/*      */ 
/*      */   public static void append(Part paramPart1, Part paramPart2, double paramDouble)
/*      */   {
/* 2721 */     if ((paramPart1 == null) || (paramPart2 == null)) {
/* 2722 */       return;
/*      */     }
/*      */ 
/* 2727 */     Enumeration localEnumeration = paramPart2.getPhraseList().elements();
/* 2728 */     while (localEnumeration.hasMoreElements()) {
/* 2729 */       Phrase localPhrase = ((Phrase)localEnumeration.nextElement()).copy();
/* 2730 */       localPhrase.setStartTime(paramDouble + localPhrase.getStartTime());
/* 2731 */       if (localPhrase.getInstrument() == paramPart1.getInstrument())
/* 2732 */         localPhrase.setInstrument(-1);
/* 2733 */       paramPart1.addPhrase(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDynamic(Part paramPart, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/* 2746 */       if (paramPart == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/* 2747 */       localNullPointerException.toString(); return;
/*      */     }
/* 2749 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2750 */     while (localEnumeration.hasMoreElements()) {
/* 2751 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2752 */       increaseDynamic(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(Part paramPart, double paramDouble)
/*      */   {
/* 2768 */     if ((paramPart == null) || (paramDouble <= 0.0D)) {
/* 2769 */       return;
/*      */     }
/*      */ 
/* 2772 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2773 */     while (localEnumeration.hasMoreElements()) {
/* 2774 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 2777 */       fadeIn(localPhrase, paramDouble, localPhrase.getStartTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeIn(Part paramPart, double paramDouble1, double paramDouble2)
/*      */   {
/* 2798 */     if ((paramPart == null) || (paramDouble1 <= 0.0D) || (paramDouble2 < 0.0D)) {
/* 2799 */       return;
/*      */     }
/*      */ 
/* 2802 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2803 */     while (localEnumeration.hasMoreElements()) {
/* 2804 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 2806 */       fadeIn(localPhrase, paramDouble1, paramDouble2 + localPhrase.getStartTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(Part paramPart, double paramDouble)
/*      */   {
/* 2821 */     if ((paramPart == null) || (paramDouble <= 0.0D)) {
/* 2822 */       return;
/*      */     }
/*      */ 
/* 2825 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2826 */     while (localEnumeration.hasMoreElements()) {
/* 2827 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 2829 */       fadeOut(localPhrase, paramDouble, paramPart.getEndTime() - localPhrase.getEndTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(Part paramPart, double paramDouble1, double paramDouble2)
/*      */   {
/* 2850 */     if ((paramPart == null) || (paramDouble1 <= 0.0D) || (paramDouble2 < 0.0D)) {
/* 2851 */       return;
/*      */     }
/*      */ 
/* 2854 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2855 */     while (localEnumeration.hasMoreElements()) {
/* 2856 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 2858 */       fadeOut(localPhrase, paramDouble1, paramDouble2 + paramPart.getEndTime() - localPhrase.getEndTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void merge(Part paramPart1, Part paramPart2)
/*      */   {
/* 2874 */     if ((paramPart1 == null) || (paramPart2 == null)) {
/* 2875 */       return;
/*      */     }
/*      */ 
/* 2880 */     Enumeration localEnumeration = paramPart2.getPhraseList().elements();
/* 2881 */     while (localEnumeration.hasMoreElements())
/* 2882 */       paramPart1.addPhrase((Phrase)localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void quantize(Part paramPart, double paramDouble)
/*      */   {
/* 2895 */     quantise(paramPart, paramDouble);
/*      */   }
/*      */ 
/*      */   public static void quantise(Part paramPart, double paramDouble)
/*      */   {
/* 2910 */     if ((paramPart == null) || (paramDouble <= 0.0D)) {
/* 2911 */       return;
/*      */     }
/*      */ 
/* 2914 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2915 */     while (localEnumeration.hasMoreElements()) {
/* 2916 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 2917 */       quantise(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shuffle(Part paramPart)
/*      */   {
/* 2926 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 2927 */     while (localEnumeration.hasMoreElements())
/* 2928 */       shuffle((Phrase)localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void accents(Part paramPart, double paramDouble)
/*      */   {
/* 2949 */     double[] arrayOfDouble = { 0.0D };
/* 2950 */     accents(paramPart, paramDouble, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public static void accents(Part paramPart, double paramDouble, double[] paramArrayOfDouble)
/*      */   {
/* 2981 */     accents(paramPart, paramDouble, paramArrayOfDouble, 20);
/*      */   }
/*      */ 
/*      */   public static void accents(Part paramPart, double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*      */   {
/* 3019 */     if ((paramPart == null) || (paramDouble <= 0.0D)) {
/* 3020 */       return;
/*      */     }
/* 3022 */     for (int i = 0; i < paramArrayOfDouble.length; ++i) {
/* 3023 */       if ((paramArrayOfDouble[i] < 0.0D) || (paramArrayOfDouble[i] >= paramDouble)) {
/* 3024 */         return;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 3029 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3030 */     while (localEnumeration.hasMoreElements()) {
/* 3031 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3032 */       accents(localPhrase, paramDouble, paramArrayOfDouble, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void normalise(Part paramPart)
/*      */   {
/* 3045 */     if (paramPart == null) {
/* 3046 */       return;
/*      */     }
/*      */ 
/* 3049 */     int i = 0;
/* 3050 */     Enumeration localEnumeration1 = paramPart.getPhraseList().elements();
/*      */     Object localObject;
/* 3051 */     while (localEnumeration1.hasMoreElements()) {
/* 3052 */       Phrase localPhrase = (Phrase)localEnumeration1.nextElement();
/* 3053 */       localEnumeration2 = localPhrase.getNoteList().elements();
/* 3054 */       while (localEnumeration2.hasMoreElements()) {
/* 3055 */         localObject = (Note)localEnumeration2.nextElement();
/* 3056 */         if (((Note)localObject).getDynamic() > i) i = ((Note)localObject).getDynamic();
/*      */       }
/*      */     }
/*      */ 
/* 3060 */     if (i == 127) {
/* 3061 */       return;
/*      */     }
/* 3063 */     int j = 127 - i;
/* 3064 */     Enumeration localEnumeration2 = paramPart.getPhraseList().elements();
/* 3065 */     while (localEnumeration2.hasMoreElements()) {
/* 3066 */       localObject = (Phrase)localEnumeration2.nextElement();
/* 3067 */       Enumeration localEnumeration3 = ((Phrase)localObject).getNoteList().elements();
/* 3068 */       while (localEnumeration3.hasMoreElements()) {
/* 3069 */         Note localNote = (Note)localEnumeration3.nextElement();
/* 3070 */         localNote.setDynamic(localNote.getDynamic() + j);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void elongate(Part paramPart, double paramDouble)
/*      */   {
/* 3085 */     if ((paramPart == null) || (paramDouble <= 0.0D)) {
/* 3086 */       return;
/*      */     }
/* 3088 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3089 */     while (localEnumeration.hasMoreElements()) {
/* 3090 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3091 */       elongate(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tiePitches(Part paramPart)
/*      */   {
/* 3106 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3107 */     while (localEnumeration.hasMoreElements()) {
/* 3108 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3109 */       tiePitches(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tieRests(Part paramPart)
/*      */   {
/* 3122 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3123 */     while (localEnumeration.hasMoreElements()) {
/* 3124 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3125 */       tieRests(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fillRests(Part paramPart)
/*      */   {
/* 3139 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3140 */     while (localEnumeration.hasMoreElements()) {
/* 3141 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3142 */       fillRests(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void spread(Part paramPart)
/*      */   {
/* 3154 */     if (paramPart == null) {
/* 3155 */       return;
/*      */     }
/*      */ 
/* 3158 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3159 */     while (localEnumeration.hasMoreElements()) {
/* 3160 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/*      */ 
/* 3162 */       spread(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void bounce(Part paramPart)
/*      */   {
/* 3174 */     if (paramPart == null) {
/* 3175 */       return;
/*      */     }
/* 3177 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3178 */     while (localEnumeration.hasMoreElements()) {
/* 3179 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3180 */       bounce(localPhrase);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void varyLength(Part paramPart, double paramDouble1, double paramDouble2)
/*      */   {
/* 3193 */     if ((paramPart == null) || (paramDouble2 < paramDouble1)) {
/* 3194 */       return;
/*      */     }
/* 3196 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3197 */     while (localEnumeration.hasMoreElements()) {
/* 3198 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3199 */       varyLength(localPhrase, paramDouble1, paramDouble2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void randomize(Part paramPart, int paramInt)
/*      */   {
/* 3212 */     randomize(paramPart, paramInt, 0.0D);
/*      */   }
/*      */ 
/*      */   public static void randomize(Part paramPart, int paramInt, double paramDouble)
/*      */   {
/* 3225 */     randomize(paramPart, paramInt, paramDouble, 0);
/*      */   }
/*      */ 
/*      */   public static void randomize(Part paramPart, int paramInt1, double paramDouble, int paramInt2)
/*      */   {
/* 3239 */     if (paramPart == null) {
/* 3240 */       return;
/*      */     }
/* 3242 */     int i = 1;
/* 3243 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3244 */     while (localEnumeration.hasMoreElements()) {
/* 3245 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3246 */       randomize(localPhrase, paramInt1, paramDouble, paramInt2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurUp(Part paramPart, int paramInt)
/*      */   {
/* 3260 */     if (paramPart == null) {
/* 3261 */       return;
/*      */     }
/* 3263 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3264 */     while (localEnumeration.hasMoreElements()) {
/* 3265 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3266 */       slurUp(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurDown(Part paramPart, int paramInt)
/*      */   {
/* 3280 */     if (paramPart == null) {
/* 3281 */       return;
/*      */     }
/* 3283 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3284 */     while (localEnumeration.hasMoreElements()) {
/* 3285 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3286 */       slurDown(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDuration(Part paramPart, double paramDouble)
/*      */   {
/* 3296 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3297 */     while (localEnumeration.hasMoreElements()) {
/* 3298 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3299 */       increaseDuration(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToDuration(Part paramPart, double paramDouble)
/*      */   {
/* 3309 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3310 */     while (localEnumeration.hasMoreElements()) {
/* 3311 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3312 */       addToDuration(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToRhythmValue(Part paramPart, double paramDouble)
/*      */   {
/* 3322 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3323 */     while (localEnumeration.hasMoreElements()) {
/* 3324 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3325 */       addToRhythmValue(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToLength(Part paramPart, double paramDouble)
/*      */   {
/* 3336 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3337 */     while (localEnumeration.hasMoreElements()) {
/* 3338 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3339 */       addToLength(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void expandIntervals(Part paramPart, double paramDouble)
/*      */   {
/* 3349 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3350 */     while (localEnumeration.hasMoreElements()) {
/* 3351 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3352 */       expandIntervals(localPhrase, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shake(Part paramPart, int paramInt)
/*      */   {
/* 3362 */     Enumeration localEnumeration = paramPart.getPhraseList().elements();
/* 3363 */     while (localEnumeration.hasMoreElements()) {
/* 3364 */       Phrase localPhrase = (Phrase)localEnumeration.nextElement();
/* 3365 */       shake(localPhrase, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void consolidate(Part paramPart)
/*      */   {
/* 3375 */     Phrase[] arrayOfPhrase = paramPart.getPhraseArray();
/* 3376 */     if (arrayOfPhrase.length < 2) {
/* 3377 */       return;
/*      */     }
/* 3379 */     Phrase localPhrase1 = new Phrase(arrayOfPhrase[0].getStartTime());
/*      */ 
/* 3382 */     int i = 0;
/* 3383 */     double d1 = arrayOfPhrase[0].getStartTime();
/* 3384 */     while (i == 0) {
/* 3385 */       double d2 = (1.0D / 0.0D);
/*      */ 
/* 3387 */       Phrase localPhrase2 = null;
/*      */ 
/* 3390 */       for (int j = 0; j < arrayOfPhrase.length; ++j)
/*      */       {
/* 3392 */         if ((arrayOfPhrase[j].getSize() > 0) && (arrayOfPhrase[j].getStartTime() < d2)) {
/* 3393 */           d2 = arrayOfPhrase[j].getStartTime();
/* 3394 */           localPhrase2 = arrayOfPhrase[j];
/*      */         }
/*      */       }
/* 3397 */       if (localPhrase2 == null) { i = 1; break;
/*      */       }
/*      */ 
/* 3401 */       Note localNote = localPhrase2.getNote(0);
/*      */ 
/* 3403 */       if (!(localNote.isRest())) {
/* 3404 */         if (localPhrase1.getSize() > 0)
/* 3405 */           localPhrase1.getNote(localPhrase1.getSize() - 1).setRhythmValue((int)((d2 - d1) * 100000.0D + 0.5D) / 100000.0D);
/*      */         else {
/* 3407 */           localPhrase1.setStartTime(d2);
/*      */         }
/* 3409 */         localPhrase1.addNote(localNote);
/*      */       }
/*      */ 
/* 3412 */       localPhrase2.setStartTime((int)((d2 + localNote.getRhythmValue()) * 100000.0D + 0.5D) / 100000.0D);
/*      */ 
/* 3414 */       localPhrase2.removeNote(0);
/*      */ 
/* 3416 */       d1 = d2;
/*      */     }
/* 3418 */     paramPart.empty();
/* 3419 */     paramPart.addPhrase(localPhrase1);
/*      */   }
/*      */ 
/*      */   public static void transpose(Score paramScore, int paramInt)
/*      */   {
/* 3433 */     if ((paramScore == null) || (paramInt == 0)) return;
/* 3434 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3435 */     while (localEnumeration.hasMoreElements()) {
/* 3436 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3437 */       transpose(localPart, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void transpose(Score paramScore, int paramInt1, int[] paramArrayOfInt, int paramInt2)
/*      */   {
/* 3463 */     if (paramScore == null) {
/* 3464 */       return;
/*      */     }
/*      */ 
/* 3467 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3468 */     while (localEnumeration.hasMoreElements())
/* 3469 */       transpose((Part)localEnumeration.nextElement(), paramInt1, paramArrayOfInt, paramInt2);
/*      */   }
/*      */ 
/*      */   public static void fadeIn(Score paramScore, double paramDouble)
/*      */   {
/* 3486 */     if ((paramScore == null) || (paramDouble <= 0.0D)) {
/* 3487 */       return;
/*      */     }
/*      */ 
/* 3490 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3491 */     while (localEnumeration.hasMoreElements()) {
/* 3492 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3493 */       fadeIn(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDynamic(Score paramScore, int paramInt)
/*      */   {
/*      */     try
/*      */     {
/* 3506 */       if (paramScore == null) new NullPointerException(); 
/*      */     } catch (NullPointerException localNullPointerException) {
/* 3507 */       localNullPointerException.toString(); return;
/*      */     }
/* 3509 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3510 */     while (localEnumeration.hasMoreElements()) {
/* 3511 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3512 */       increaseDynamic(localPart, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fadeOut(Score paramScore, double paramDouble)
/*      */   {
/* 3530 */     if ((paramScore == null) || (paramDouble <= 0.0D)) {
/* 3531 */       return;
/*      */     }
/*      */ 
/* 3534 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3535 */     while (localEnumeration.hasMoreElements()) {
/* 3536 */       Part localPart = (Part)localEnumeration.nextElement();
/*      */ 
/* 3538 */       fadeOut(localPart, paramDouble, paramScore.getEndTime() - localPart.getEndTime());
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void compress(Score paramScore, double paramDouble)
/*      */   {
/* 3554 */     if (paramScore == null) {
/* 3555 */       return;
/*      */     }
/*      */ 
/* 3558 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3559 */     while (localEnumeration.hasMoreElements()) {
/* 3560 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3561 */       compress(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void repeat(Score paramScore)
/*      */   {
/* 3573 */     repeat(paramScore, 2);
/*      */   }
/*      */ 
/*      */   public static void repeat(Score paramScore, int paramInt)
/*      */   {
/* 3586 */     if ((paramScore == null) || (paramInt < 2)) {
/* 3587 */       return;
/*      */     }
/*      */ 
/* 3591 */     double d1 = 0.0D;
/* 3592 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3593 */     while (localEnumeration.hasMoreElements()) {
/* 3594 */       Part localPart1 = (Part)localEnumeration.nextElement();
/* 3595 */       if (d1 < localPart1.getEndTime()) d1 = localPart1.getEndTime();
/*      */     }
/*      */ 
/* 3598 */     for (int i = 0; i < paramScore.getPartList().size(); ++i) {
/* 3599 */       Part localPart2 = (Part)paramScore.getPartList().elementAt(i);
/* 3600 */       int j = localPart2.getPhraseList().size();
/* 3601 */       for (int k = 0; k < paramInt - 1; ++k) {
/* 3602 */         double d2 = d1 * (k + 1);
/* 3603 */         for (int l = 0; l < j; ++l) {
/* 3604 */           Phrase localPhrase1 = (Phrase)localPart2.getPhraseList().elementAt(l);
/* 3605 */           Phrase localPhrase2 = localPhrase1.copy();
/* 3606 */           localPhrase2.setStartTime(d2 + localPhrase1.getStartTime());
/* 3607 */           localPart2.addPhrase(localPhrase2);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void append(Score paramScore1, Score paramScore2)
/*      */   {
/* 3623 */     if ((paramScore1 == null) || (paramScore2 == null)) {
/* 3624 */       return;
/*      */     }
/*      */ 
/* 3627 */     paramScore2.clean();
/* 3628 */     if (paramScore2.size() == 0) return;
/*      */ 
/* 3630 */     double d = paramScore1.getEndTime();
/*      */ 
/* 3632 */     Enumeration localEnumeration1 = paramScore2.getPartList().elements();
/* 3633 */     while (localEnumeration1.hasMoreElements()) {
/* 3634 */       Part localPart = (Part)localEnumeration1.nextElement();
/*      */ 
/* 3636 */       Enumeration localEnumeration2 = localPart.getPhraseList().elements();
/* 3637 */       while (localEnumeration2.hasMoreElements()) {
/* 3638 */         Phrase localPhrase = (Phrase)localEnumeration2.nextElement();
/* 3639 */         localPhrase.setStartTime(localPhrase.getStartTime() + d);
/* 3640 */         if ((localPhrase.getInstrument() != 250) && (localPhrase.getInstrument() != localPart.getInstrument()))
/* 3641 */           localPhrase.setInstrument(localPart.getInstrument());
/* 3642 */         if (localPhrase.getInstrument() == localPart.getInstrument()) {
/* 3643 */           localPhrase.setInstrument(-1);
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 3648 */     merge(paramScore1, paramScore2);
/*      */   }
/*      */ 
/*      */   public static void merge(Score paramScore1, Score paramScore2)
/*      */   {
/* 3663 */     if ((paramScore1 == null) || (paramScore2 == null)) {
/* 3664 */       return;
/*      */     }
/*      */ 
/* 3668 */     int i = 0;
/*      */ 
/* 3672 */     int j = paramScore1.size();
/* 3673 */     int k = paramScore2.size();
/* 3674 */     for (int l = 0; l < k; ++l) {
/* 3675 */       Part localPart2 = paramScore2.getPart(l);
/*      */ 
/* 3677 */       int i1 = localPart2.getChannel();
/*      */ 
/* 3679 */       for (int i2 = 0; i2 < j; ++i2) {
/* 3680 */         Part localPart1 = paramScore1.getPart(i2);
/* 3681 */         if (i1 != localPart1.getChannel())
/*      */           continue;
/* 3683 */         int i3 = localPart2.size();
/* 3684 */         for (int i4 = 0; i4 < i3; ++i4) {
/* 3685 */           localPart1.addPhrase(localPart2.getPhrase(i4));
/*      */         }
/* 3687 */         i = 1;
/* 3688 */         i2 = j;
/*      */       }
/*      */ 
/* 3692 */       if (i == 0) {
/* 3693 */         paramScore1.addPart(localPart2);
/* 3694 */         i = 0;
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void quantize(Score paramScore, double paramDouble)
/*      */   {
/* 3709 */     quantise(paramScore, paramDouble);
/*      */   }
/*      */ 
/*      */   public static void quantise(Score paramScore, double paramDouble)
/*      */   {
/* 3724 */     if ((paramScore == null) || (paramDouble <= 0.0D)) {
/* 3725 */       return;
/*      */     }
/*      */ 
/* 3728 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3729 */     while (localEnumeration.hasMoreElements()) {
/* 3730 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3731 */       quantise(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shuffle(Score paramScore)
/*      */   {
/* 3740 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3741 */     while (localEnumeration.hasMoreElements())
/* 3742 */       shuffle((Part)localEnumeration.nextElement());
/*      */   }
/*      */ 
/*      */   public static void accents(Score paramScore, double paramDouble)
/*      */   {
/* 3763 */     double[] arrayOfDouble = { 0.0D };
/* 3764 */     accents(paramScore, paramDouble, arrayOfDouble);
/*      */   }
/*      */ 
/*      */   public static void accents(Score paramScore, double paramDouble, double[] paramArrayOfDouble)
/*      */   {
/* 3795 */     accents(paramScore, paramDouble, paramArrayOfDouble, 20);
/*      */   }
/*      */ 
/*      */   public static void accents(Score paramScore, double paramDouble, double[] paramArrayOfDouble, int paramInt)
/*      */   {
/* 3833 */     if ((paramScore == null) || (paramDouble <= 0.0D)) {
/* 3834 */       return;
/*      */     }
/* 3836 */     for (int i = 0; i < paramArrayOfDouble.length; ++i) {
/* 3837 */       if ((paramArrayOfDouble[i] < 0.0D) || (paramArrayOfDouble[i] >= paramDouble)) {
/* 3838 */         return;
/*      */       }
/*      */ 
/*      */     }
/*      */ 
/* 3843 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3844 */     while (localEnumeration.hasMoreElements()) {
/* 3845 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3846 */       accents(localPart, paramDouble, paramArrayOfDouble, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void normalise(Score paramScore)
/*      */   {
/* 3859 */     if (paramScore == null) {
/* 3860 */       return;
/*      */     }
/*      */ 
/* 3863 */     int i = 0;
/* 3864 */     Enumeration localEnumeration1 = paramScore.getPartList().elements();
/*      */     Object localObject1;
/*      */     Enumeration localEnumeration3;
/*      */     Object localObject2;
/* 3865 */     while (localEnumeration1.hasMoreElements()) {
/* 3866 */       Part localPart = (Part)localEnumeration1.nextElement();
/* 3867 */       localEnumeration2 = localPart.getPhraseList().elements();
/* 3868 */       while (localEnumeration2.hasMoreElements()) {
/* 3869 */         localObject1 = (Phrase)localEnumeration2.nextElement();
/* 3870 */         localEnumeration3 = ((Phrase)localObject1).getNoteList().elements();
/* 3871 */         while (localEnumeration3.hasMoreElements()) {
/* 3872 */           localObject2 = (Note)localEnumeration3.nextElement();
/* 3873 */           if (((Note)localObject2).getDynamic() > i) i = ((Note)localObject2).getDynamic();
/*      */         }
/*      */       }
/*      */     }
/*      */ 
/* 3878 */     if (i == 127) {
/* 3879 */       return;
/*      */     }
/* 3881 */     int j = 127 - i;
/* 3882 */     Enumeration localEnumeration2 = paramScore.getPartList().elements();
/* 3883 */     while (localEnumeration2.hasMoreElements()) {
/* 3884 */       localObject1 = (Part)localEnumeration2.nextElement();
/* 3885 */       localEnumeration3 = ((Part)localObject1).getPhraseList().elements();
/* 3886 */       while (localEnumeration3.hasMoreElements()) {
/* 3887 */         localObject2 = (Phrase)localEnumeration3.nextElement();
/* 3888 */         Enumeration localEnumeration4 = ((Phrase)localObject2).getNoteList().elements();
/* 3889 */         while (localEnumeration4.hasMoreElements()) {
/* 3890 */           Note localNote = (Note)localEnumeration4.nextElement();
/* 3891 */           localNote.setDynamic(localNote.getDynamic() + j);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void consolidate(Score paramScore)
/*      */   {
/* 3910 */     if (paramScore == null) {
/* 3911 */       return;
/*      */     }
/*      */ 
/* 3914 */     for (int l = 0; l < paramScore.size(); ++l) {
/* 3915 */       Part localPart1 = paramScore.getPart(l);
/* 3916 */       int i = localPart1.getChannel();
/* 3917 */       int k = paramScore.size();
/* 3918 */       for (int i1 = k - 1; i1 > l; --i1)
/*      */       {
/* 3920 */         Part localPart2 = paramScore.getPart(i1);
/* 3921 */         if (localPart2.getChannel() != i)
/*      */           continue;
/* 3923 */         int j = localPart2.size();
/* 3924 */         for (int i2 = 0; i2 < j; ++i2)
/*      */         {
/* 3926 */           Phrase localPhrase = localPart2.getPhrase(i2);
/* 3927 */           localPhrase.setAppend(false);
/* 3928 */           localPart1.addPhrase(localPhrase);
/*      */         }
/*      */ 
/* 3931 */         paramScore.removePart(i1);
/*      */       }
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void elongate(Score paramScore, double paramDouble)
/*      */   {
/* 3947 */     if ((paramScore == null) || (paramDouble <= 0.0D)) {
/* 3948 */       return;
/*      */     }
/* 3950 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3951 */     while (localEnumeration.hasMoreElements()) {
/* 3952 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3953 */       elongate(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tiePitches(Score paramScore)
/*      */   {
/* 3968 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3969 */     while (localEnumeration.hasMoreElements()) {
/* 3970 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3971 */       tiePitches(localPart);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void tieRests(Score paramScore)
/*      */   {
/* 3984 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 3985 */     while (localEnumeration.hasMoreElements()) {
/* 3986 */       Part localPart = (Part)localEnumeration.nextElement();
/* 3987 */       tieRests(localPart);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void fillRests(Score paramScore)
/*      */   {
/* 4001 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4002 */     while (localEnumeration.hasMoreElements()) {
/* 4003 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4004 */       fillRests(localPart);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void spread(Score paramScore)
/*      */   {
/* 4016 */     if (paramScore == null) {
/* 4017 */       return;
/*      */     }
/*      */ 
/* 4020 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4021 */     while (localEnumeration.hasMoreElements()) {
/* 4022 */       Part localPart = (Part)localEnumeration.nextElement();
/*      */ 
/* 4024 */       spread(localPart);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void bounce(Score paramScore)
/*      */   {
/* 4036 */     if (paramScore == null) {
/* 4037 */       return;
/*      */     }
/* 4039 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4040 */     while (localEnumeration.hasMoreElements()) {
/* 4041 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4042 */       bounce(localPart);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void varyLength(Score paramScore, double paramDouble1, double paramDouble2)
/*      */   {
/* 4055 */     if ((paramScore == null) || (paramDouble2 < paramDouble1)) {
/* 4056 */       return;
/*      */     }
/* 4058 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4059 */     while (localEnumeration.hasMoreElements()) {
/* 4060 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4061 */       varyLength(localPart, paramDouble1, paramDouble2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void randomize(Score paramScore, int paramInt)
/*      */   {
/* 4074 */     randomize(paramScore, paramInt, 0.0D);
/*      */   }
/*      */ 
/*      */   public static void randomize(Score paramScore, int paramInt, double paramDouble)
/*      */   {
/* 4087 */     randomize(paramScore, paramInt, paramDouble, 0);
/*      */   }
/*      */ 
/*      */   public static void randomize(Score paramScore, int paramInt1, double paramDouble, int paramInt2)
/*      */   {
/* 4101 */     if (paramScore == null) {
/* 4102 */       return;
/*      */     }
/* 4104 */     int i = 1;
/* 4105 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4106 */     while (localEnumeration.hasMoreElements()) {
/* 4107 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4108 */       randomize(localPart, paramInt1, paramDouble, paramInt2);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurUp(Score paramScore, int paramInt)
/*      */   {
/* 4122 */     if (paramScore == null) {
/* 4123 */       return;
/*      */     }
/* 4125 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4126 */     while (localEnumeration.hasMoreElements()) {
/* 4127 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4128 */       slurUp(localPart, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void slurDown(Score paramScore, int paramInt)
/*      */   {
/* 4142 */     if (paramScore == null) {
/* 4143 */       return;
/*      */     }
/* 4145 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4146 */     while (localEnumeration.hasMoreElements()) {
/* 4147 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4148 */       slurDown(localPart, paramInt);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void increaseDuration(Score paramScore, double paramDouble)
/*      */   {
/* 4158 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4159 */     while (localEnumeration.hasMoreElements()) {
/* 4160 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4161 */       increaseDuration(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToDuration(Score paramScore, double paramDouble)
/*      */   {
/* 4171 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4172 */     while (localEnumeration.hasMoreElements()) {
/* 4173 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4174 */       addToDuration(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToRhythmValue(Score paramScore, double paramDouble)
/*      */   {
/* 4184 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4185 */     while (localEnumeration.hasMoreElements()) {
/* 4186 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4187 */       addToRhythmValue(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void addToLength(Score paramScore, double paramDouble)
/*      */   {
/* 4197 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4198 */     while (localEnumeration.hasMoreElements()) {
/* 4199 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4200 */       addToLength(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void expandIntervals(Score paramScore, double paramDouble)
/*      */   {
/* 4210 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4211 */     while (localEnumeration.hasMoreElements()) {
/* 4212 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4213 */       expandIntervals(localPart, paramDouble);
/*      */     }
/*      */   }
/*      */ 
/*      */   public static void shake(Score paramScore, int paramInt)
/*      */   {
/* 4223 */     Enumeration localEnumeration = paramScore.getPartList().elements();
/* 4224 */     while (localEnumeration.hasMoreElements()) {
/* 4225 */       Part localPart = (Part)localEnumeration.nextElement();
/* 4226 */       shake(localPart, paramInt);
/*      */     }
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.Mod
 * JD-Core Version:    0.5.3
 */