/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.midi.MidiDevice;
/*     */ import javax.sound.midi.MidiDevice.Info;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.MidiUnavailableException;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.Sequencer;
/*     */ import javax.sound.midi.Transmitter;
/*     */ 
/*     */ public class JmMidiPlayer extends OutputStream
/*     */ {
/*     */   private Sequencer sequencer;
/*     */   private Transmitter transmitter;
/*     */   private Receiver receiver;
/*     */   private MidiDevice synthesizer;
/*     */   ByteArrayOutputStream os;
/*     */ 
/*     */   private static MidiDevice getSynthesizer()
/*     */     throws MidiUnavailableException
/*     */   {
/*  48 */     MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/*     */ 
/*  52 */     MidiDevice localMidiDevice = null;
/*     */ 
/*  54 */     int i = 0;
/*  55 */     for (int j = 0; j < arrayOfInfo.length; ++j) {
/*  56 */       if (i != 0) continue;
/*     */       try {
/*  58 */         MidiDevice.Info localInfo = arrayOfInfo[(j - 1)];
/*  59 */         System.out.print(localInfo.toString());
/*  60 */         System.out.print(" Getting Info ");
/*  61 */         localMidiDevice = MidiSystem.getMidiDevice(localInfo);
/*  62 */         if (!(localMidiDevice instanceof Sequencer)) {
/*  63 */           System.out.print(" Opening ");
/*  64 */           i = 1;
/*  65 */           System.out.println(" Opened");
/*     */         }
/*     */         else {
/*  68 */           System.out.println(" Not a Sequencer");
/*     */         }
/*     */       }
/*     */       catch (Throwable localThrowable)
/*     */       {
/*  73 */         System.out.println(" Exception " + localThrowable.getMessage());
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/*  78 */     if (i != 0) {
/*  79 */       return localMidiDevice;
/*     */     }
/*     */ 
/*  82 */     System.out.println("No Synthesizer Device Found");
/*     */ 
/*  84 */     throw new MidiUnavailableException("No Synthesizer Device Found");
/*     */   }
/*     */ 
/*     */   public JmMidiPlayer()
/*     */     throws MidiUnavailableException
/*     */   {
/*  93 */     this.sequencer = MidiSystem.getSequencer();
/*     */ 
/*  95 */     this.synthesizer = getSynthesizer();
/*     */ 
/*  97 */     this.transmitter = this.sequencer.getTransmitter();
/*     */ 
/*  99 */     this.receiver = this.synthesizer.getReceiver();
/*     */ 
/* 101 */     this.transmitter.setReceiver(this.receiver);
/* 102 */     this.sequencer.open();
/* 103 */     if (this.sequencer.isOpen());
/* 109 */     this.os = new ByteArrayOutputStream();
/*     */ 
/* 112 */     MidiDevice.Info localInfo1 = this.synthesizer.getDeviceInfo();
/*     */ 
/* 117 */     MidiDevice.Info localInfo2 = this.sequencer.getDeviceInfo();
/*     */ 
/* 122 */     MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/*     */ 
/* 126 */     for (int i = 0; i < arrayOfInfo.length; ++i) {
/* 127 */       MidiDevice.Info localInfo3 = arrayOfInfo[i];
/* 128 */       System.out.print(localInfo3.toString());
/*     */       try {
/* 130 */         MidiDevice localMidiDevice = MidiSystem.getMidiDevice(localInfo3);
/*     */       }
/*     */       catch (MidiUnavailableException localMidiUnavailableException)
/*     */       {
/* 134 */         System.out.println(" Unavailable");
/*     */       }
/*     */     }
/* 137 */     if (!(this.sequencer.isOpen()))
/*     */       return;
/*     */   }
/*     */ 
/*     */   public void write(int paramInt)
/*     */     throws IOException
/*     */   {
/* 146 */     byte[] arrayOfByte = new byte[1];
/* 147 */     arrayOfByte[0] = new Integer(paramInt).byteValue();
/* 148 */     write(arrayOfByte);
/*     */   }
/*     */ 
/*     */   public void write(byte[] paramArrayOfByte) throws IOException {
/* 152 */     this.os.write(paramArrayOfByte); } 
/*     */   // ERROR //
/*     */   public void play() { // Byte code:
/*     */     //   0: new 45	java/io/ByteArrayInputStream
/*     */     //   3: dup
/*     */     //   4: aload_0
/*     */     //   5: getfield 36	jm/gui/cpn/JmMidiPlayer:os	Ljava/io/ByteArrayOutputStream;
/*     */     //   8: invokevirtual 46	java/io/ByteArrayOutputStream:toByteArray	()[B
/*     */     //   11: invokespecial 47	java/io/ByteArrayInputStream:<init>	([B)V
/*     */     //   14: astore_1
/*     */     //   15: aload_1
/*     */     //   16: invokestatic 48	javax/sound/midi/MidiSystem:getSequence	(Ljava/io/InputStream;)Ljavax/sound/midi/Sequence;
/*     */     //   19: astore_2
/*     */     //   20: aload_0
/*     */     //   21: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   24: invokeinterface 33 1 0
/*     */     //   29: ifeq +6 -> 35
/*     */     //   32: goto +12 -> 44
/*     */     //   35: aload_0
/*     */     //   36: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   39: invokeinterface 32 1 0
/*     */     //   44: aload_0
/*     */     //   45: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   48: aload_2
/*     */     //   49: invokeinterface 49 2 0
/*     */     //   54: aload_0
/*     */     //   55: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   58: invokeinterface 50 1 0
/*     */     //   63: aload_0
/*     */     //   64: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   67: invokeinterface 51 1 0
/*     */     //   72: ifeq +16 -> 88
/*     */     //   75: ldc2_w 52
/*     */     //   78: invokestatic 54	java/lang/Thread:sleep	(J)V
/*     */     //   81: goto -18 -> 63
/*     */     //   84: astore_3
/*     */     //   85: goto -22 -> 63
/*     */     //   88: aload_0
/*     */     //   89: getfield 24	jm/gui/cpn/JmMidiPlayer:sequencer	Ljavax/sound/midi/Sequencer;
/*     */     //   92: invokeinterface 56 1 0
/*     */     //   97: goto +96 -> 193
/*     */     //   100: astore_1
/*     */     //   101: getstatic 2	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   104: new 13	java/lang/StringBuffer
/*     */     //   107: dup
/*     */     //   108: invokespecial 14	java/lang/StringBuffer:<init>	()V
/*     */     //   111: ldc 58
/*     */     //   113: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   116: aload_1
/*     */     //   117: invokevirtual 59	javax/sound/midi/InvalidMidiDataException:getMessage	()Ljava/lang/String;
/*     */     //   120: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   123: invokevirtual 18	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   126: invokevirtual 10	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   129: goto +64 -> 193
/*     */     //   132: astore_1
/*     */     //   133: getstatic 2	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   136: new 13	java/lang/StringBuffer
/*     */     //   139: dup
/*     */     //   140: invokespecial 14	java/lang/StringBuffer:<init>	()V
/*     */     //   143: ldc 60
/*     */     //   145: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   148: aload_1
/*     */     //   149: invokevirtual 61	MidiUnavailableException:getMessage	()Ljava/lang/String;
/*     */     //   152: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   155: invokevirtual 18	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   158: invokevirtual 10	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   161: goto +32 -> 193
/*     */     //   164: astore_1
/*     */     //   165: getstatic 2	java/lang/System:out	Ljava/io/PrintStream;
/*     */     //   168: new 13	java/lang/StringBuffer
/*     */     //   171: dup
/*     */     //   172: invokespecial 14	java/lang/StringBuffer:<init>	()V
/*     */     //   175: ldc 63
/*     */     //   177: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   180: aload_1
/*     */     //   181: invokevirtual 64	IOException:getMessage	()Ljava/lang/String;
/*     */     //   184: invokevirtual 16	java/lang/StringBuffer:append	(Ljava/lang/String;)Ljava/lang/StringBuffer;
/*     */     //   187: invokevirtual 18	java/lang/StringBuffer:toString	()Ljava/lang/String;
/*     */     //   190: invokevirtual 10	java/io/PrintStream:println	(Ljava/lang/String;)V
/*     */     //   193: return
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   75	81	84	java/lang/InterruptedException
/*     */     //   0	97	100	javax/sound/midi/InvalidMidiDataException
/*     */     //   0	97	132	MidiUnavailableException
/*     */     //   0	97	164	IOException } 
/*     */   public void close() { this.sequencer.close();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.JmMidiPlayer
 * JD-Core Version:    0.5.3
 */