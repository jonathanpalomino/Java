/*     */ package jm.midi;
/*     */ 
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.midi.event.ATouch;
/*     */ import jm.midi.event.CChange;
/*     */ import jm.midi.event.CPres;
/*     */ import jm.midi.event.EndTrack;
/*     */ import jm.midi.event.Event;
/*     */ import jm.midi.event.KeySig;
/*     */ import jm.midi.event.NoteOff;
/*     */ import jm.midi.event.NoteOn;
/*     */ import jm.midi.event.PChange;
/*     */ import jm.midi.event.PWheel;
/*     */ import jm.midi.event.TempoEvent;
/*     */ import jm.midi.event.TimeSig;
/*     */ 
/*     */ public final class MidiUtil
/*     */   implements JMC
/*     */ {
/*     */   private static final boolean VERBOSE = 0;
/*     */ 
/*     */   public static int readVarLength(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/*  49 */     int i = (short)paramDataInputStream.readUnsignedByte();
/*  50 */     int j = i;
/*  51 */     if ((i & 0x80) != 0) {
/*  52 */       j &= 127;
/*     */       do {
/*  54 */         i = (short)paramDataInputStream.readUnsignedByte();
/*  55 */         j = (j << 7) + (i & 0x7F); }
/*  56 */       while ((i & 0x80) != 0);
/*     */     }
/*  58 */     return j;
/*     */   }
/*     */ 
/*     */   public static int writeVarLength(int paramInt, DataOutputStream paramDataOutputStream)
/*     */     throws IOException
/*     */   {
/*  70 */     int i = 0;
/*  71 */     long l = paramInt & 0x7F;
/*  72 */     while (paramInt >>= 7 > 0) {
/*  73 */       l <<= 8;
/*  74 */       l |= paramInt & 0x7F | 0x80;
/*     */     }
/*     */     while (true) {
/*  77 */       paramDataOutputStream.writeByte((byte)(int)l);
/*  78 */       ++i;
/*  79 */       if ((l & 0x80) == 0L) break;
/*  80 */       l >>= 8;
/*     */     }
/*     */ 
/*  85 */     return i;
/*     */   }
/*     */ 
/*     */   public static int varLengthBytes(int paramInt)
/*     */   {
/*  94 */     int i = 0;
/*  95 */     long l = paramInt & 0x7F;
/*  96 */     while (paramInt >>= 7 > 0) {
/*  97 */       l <<= 8;
/*  98 */       l |= paramInt & 0x7F | 0x80;
/*     */     }
/*     */     while (true) {
/* 101 */       ++i;
/* 102 */       if ((l & 0x80) == 0L) break;
/* 103 */       l >>= 8;
/*     */     }
/*     */ 
/* 108 */     return i;
/*     */   }
/*     */ 
/*     */   public static double getEndEvt(short paramShort, Vector paramVector, int paramInt)
/*     */   {
/* 117 */     double d = 0.0D;
/*     */ 
/* 119 */     ++paramInt;
/* 120 */     for (; paramInt < paramVector.size(); ++paramInt) {
/* 121 */       Event localEvent = (Event)paramVector.elementAt(paramInt);
/* 122 */       d += localEvent.getTime();
/* 123 */       switch (localEvent.getID())
/*     */       {
/*     */       case 5:
/* 125 */         NoteOn localNoteOn = (NoteOn)localEvent;
/* 126 */         if ((localNoteOn.getPitch() != paramShort) || (localNoteOn.getVelocity() != 0) || 
/* 129 */           (d <= 0.0D))
/*     */           continue;
/* 131 */         localNoteOn.setPitch(255);
/* 132 */         return d;
/*     */       case 4:
/* 137 */         NoteOff localNoteOff = (NoteOff)localEvent;
/* 138 */         if ((localNoteOff.getPitch() != paramShort) || 
/* 140 */           (d <= 0.0D))
/*     */           continue;
/* 142 */         localNoteOff.setPitch(255);
/* 143 */         return d;
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 149 */     System.out.println("Error reading file - sorry!");
/* 150 */     System.out.println("Try to continue reading anyway");
/*     */ 
/* 152 */     return 0.0D;
/*     */   }
/*     */ 
/*     */   public static Event createVoiceEvent(int paramInt)
/*     */     throws IOException
/*     */   {
/* 159 */     switch (paramInt)
/*     */     {
/*     */     case 8:
/* 162 */       return new NoteOff();
/*     */     case 9:
/* 165 */       return new NoteOn();
/*     */     case 10:
/* 168 */       return new ATouch();
/*     */     case 11:
/* 171 */       return new CChange();
/*     */     case 12:
/* 174 */       return new PChange();
/*     */     case 13:
/* 177 */       return new CPres();
/*     */     case 14:
/* 180 */       return new PWheel();
/*     */     }
/* 182 */     return null;
/*     */   }
/*     */ 
/*     */   public static Event createMetaEvent(int paramInt)
/*     */     throws IOException
/*     */   {
/* 194 */     switch (paramInt)
/*     */     {
/*     */     case 81:
/* 197 */       return new TempoEvent();
/*     */     case 47:
/* 200 */       return new EndTrack();
/*     */     case 88:
/* 203 */       return new TimeSig();
/*     */     case 89:
/* 206 */       return new KeySig();
/*     */     }
/*     */ 
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */   public static Event createSysExEvent(int paramInt)
/*     */     throws IOException
/*     */   {
/* 217 */     switch (paramInt) {
/*     */     }
/* 219 */     return null;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.MidiUtil
 * JD-Core Version:    0.5.3
 */