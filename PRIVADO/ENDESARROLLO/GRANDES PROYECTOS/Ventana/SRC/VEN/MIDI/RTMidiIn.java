/*     */ package jm.midi;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import javax.sound.midi.MidiDevice.Info;
/*     */ import javax.sound.midi.MidiMessage;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.Transmitter;
/*     */ import jm.midi.event.Event;
/*     */ import jm.midi.event.VoiceEvt;
/*     */ 
/*     */ public class RTMidiIn
/*     */   implements Receiver
/*     */ {
/*     */   private int oldStatus;
/*     */   private Vector listeners;
/*  50 */   private Transmitter trans = null;
/*     */ 
/*     */   public RTMidiIn()
/*     */   {
/*  56 */     this.listeners = new Vector();
/*  57 */     init();
/*     */   }
/*     */ 
/*     */   public void addMidiInputListener(MidiInputListener paramMidiInputListener)
/*     */   {
/*  64 */     this.listeners.add(paramMidiInputListener);
/*     */   }
/*     */ 
/*     */   public void notifyListeners(Event paramEvent)
/*     */   {
/*  71 */     Enumeration localEnumeration = this.listeners.elements();
/*  72 */     while (localEnumeration.hasMoreElements())
/*  73 */       ((MidiInputListener)localEnumeration.nextElement()).newEvent(paramEvent);
/*     */   }
/*     */ 
/*     */   public void send(MidiMessage paramMidiMessage, long paramLong)
/*     */   {
/*  81 */     System.out.println("New MIDI message");
/*  82 */     Object localObject = null;
/*  83 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramMidiMessage.getMessage());
/*  84 */     DataInputStream localDataInputStream = new DataInputStream(localByteArrayInputStream);
/*     */     try {
/*  86 */       localDataInputStream.mark(2);
/*  87 */       int i = localDataInputStream.readUnsignedByte();
/*  88 */       int j = 0;
/*     */ 
/*  90 */       if (i < 128) {
/*  91 */         i = this.oldStatus;
/*  92 */         localDataInputStream.reset();
/*     */       }
/*     */       int k;
/*  94 */       if (i >= 255) {
/*  95 */         k = localDataInputStream.readUnsignedByte();
/*  96 */         j = MidiUtil.readVarLength(localDataInputStream);
/*  97 */         localObject = MidiUtil.createMetaEvent(k);
/*  98 */       } else if (i >= 240) {
/*  99 */         System.out.println("SysEX---");
/* 100 */         j = MidiUtil.readVarLength(localDataInputStream);
/* 101 */       } else if (i >= 128) {
/* 102 */         k = (short)(i / 16);
/* 103 */         short s = (short)(i - (k * 16));
/* 104 */         VoiceEvt localVoiceEvt = (VoiceEvt)MidiUtil.createVoiceEvent(k);
/* 105 */         localVoiceEvt.setMidiChannel(s);
/* 106 */         localObject = localVoiceEvt;
/* 107 */         if (localObject == null) {
/* 108 */           throw new IOException("Read Error");
/*     */         }
/*     */       }
/* 111 */       if (localObject != null) {
/* 112 */         ((Event)localObject).setTime((int)paramLong);
/* 113 */         ((Event)localObject).read(localDataInputStream);
/*     */       }
/* 115 */       this.oldStatus = i;
/*     */     } catch (Exception localException) {
/* 117 */       localException.printStackTrace();
/* 118 */       System.exit(1);
/*     */     }
/* 120 */     notifyListeners((Event)localObject);
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/* 127 */     this.trans.close();
/*     */   }
/*     */ 
/*     */   private boolean init()
/*     */   {
/* 134 */     if (this.trans == null) {
/*     */       try {
/* 136 */         if (MidiSystem.getReceiver() == null) {
/* 137 */           System.err.println("MidiSystem Receiver Unavailable");
/* 138 */           return false;
/*     */         }
/* 140 */         MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/* 141 */         for (int i = 0; i < arrayOfInfo.length; ++i) {
/* 142 */           System.out.println(arrayOfInfo[i]);
/*     */         }
/* 144 */         this.trans = MidiSystem.getTransmitter();
/* 145 */         this.trans.setReceiver(this);
/*     */       } catch (MidiUnavailableException localMidiUnavailableException) {
/* 147 */         System.err.println("Midi System Unavailable:" + localMidiUnavailableException);
/* 148 */         return false;
/*     */       }
/*     */     }
/* 151 */     return true;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.RTMidiIn
 * JD-Core Version:    0.5.3
 */