/*     */ package jm.midi;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Frame;
/*     */ import java.awt.List;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.Toolkit;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.io.PrintStream;
/*     */ import javax.sound.midi.InvalidMidiDataException;
/*     */ import javax.sound.midi.MetaMessage;
/*     */ import javax.sound.midi.MidiDevice;
/*     */ import javax.sound.midi.MidiDevice.Info;
/*     */ import javax.sound.midi.MidiMessage;
/*     */ import javax.sound.midi.MidiSystem;
/*     */ import javax.sound.midi.Receiver;
/*     */ import javax.sound.midi.ShortMessage;
/*     */ import javax.sound.midi.SysexMessage;
/*     */ import javax.sound.midi.Transmitter;
/*     */ 
/*     */ public abstract class MidiCommunication
/*     */   implements Receiver
/*     */ {
/*     */   private Receiver midiReceiver;
/*  41 */   private boolean waitingToSetup = true;
/*     */ 
/*     */   // ERROR //
/*     */   public MidiCommunication() { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 5	java/lang/Object:<init>	()V
/*     */     //   4: aload_0
/*     */     //   5: iconst_1
/*     */     //   6: putfield 1	jm/midi/MidiCommunication:waitingToSetup	Z
/*     */     //   9: aload_0
/*     */     //   10: invokespecial 6	jm/midi/MidiCommunication:setupMidiInput	()V
/*     */     //   13: aload_0
/*     */     //   14: getfield 1	jm/midi/MidiCommunication:waitingToSetup	Z
/*     */     //   17: ifeq +16 -> 33
/*     */     //   20: ldc2_w 7
/*     */     //   23: invokestatic 9	java/lang/Thread:sleep	(J)V
/*     */     //   26: goto -13 -> 13
/*     */     //   29: astore_1
/*     */     //   30: goto -17 -> 13
/*     */     //   33: return
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   20	26	29	java/lang/Exception } 
/*     */   // ERROR //
/*     */   public MidiCommunication(boolean paramBoolean) { // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: invokespecial 5	java/lang/Object:<init>	()V
/*     */     //   4: aload_0
/*     */     //   5: iconst_1
/*     */     //   6: putfield 1	jm/midi/MidiCommunication:waitingToSetup	Z
/*     */     //   9: iload_1
/*     */     //   10: ifeq +27 -> 37
/*     */     //   13: aload_0
/*     */     //   14: invokespecial 6	jm/midi/MidiCommunication:setupMidiInput	()V
/*     */     //   17: aload_0
/*     */     //   18: getfield 1	jm/midi/MidiCommunication:waitingToSetup	Z
/*     */     //   21: ifeq +16 -> 37
/*     */     //   24: ldc2_w 7
/*     */     //   27: invokestatic 9	java/lang/Thread:sleep	(J)V
/*     */     //   30: goto -13 -> 17
/*     */     //   33: astore_2
/*     */     //   34: goto -17 -> 17
/*     */     //   37: return
/*     */     //
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   24	30	33	java/lang/Exception } 
/*     */   public MidiCommunication(int paramInt1, int paramInt2) { MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/*  83 */     setMidiInputSelection(paramInt1, arrayOfInfo);
/*  84 */     setMidiOutputSelection(paramInt2, arrayOfInfo);
/*     */   }
/*     */ 
/*     */   public void midiSetup()
/*     */   {
/*  92 */     setupMidiInput();
/*     */   }
/*     */ 
/*     */   public abstract void handleMidiInput(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*     */ 
/*     */   public void sendMidiOutput(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/*     */     try
/*     */     {
/* 115 */       ShortMessage localShortMessage = new ShortMessage();
/* 116 */       localShortMessage.setMessage(paramInt1, paramInt2, paramInt3, paramInt4);
/* 117 */       this.midiReceiver.send(localShortMessage, -1L);
/*     */     }
/*     */     catch (InvalidMidiDataException localInvalidMidiDataException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void send(MidiMessage paramMidiMessage, long paramLong)
/*     */   {
/* 128 */     byte[] arrayOfByte = paramMidiMessage.getMessage();
/* 129 */     int i = paramMidiMessage.getStatus();
/*     */     int j;
/* 131 */     if (paramMidiMessage instanceof ShortMessage)
/*     */     {
/* 133 */       j = (arrayOfByte[0] & 0xFF) >> 4;
/* 134 */       int k = arrayOfByte[0] & 0xF;
/* 135 */       int l = arrayOfByte[1];
/* 136 */       int i1 = -1;
/* 137 */       if (arrayOfByte.length > 2) {
/* 138 */         i1 = arrayOfByte[2];
/*     */       }
/*     */ 
/* 142 */       if (j != 15) {
/* 143 */         handleMidiInput(i - k, k, l, i1);
/*     */       }
/* 147 */       else if (i == 248) {
/* 148 */         System.out.print("MIDI Clock message");
/*     */       }
/* 150 */       else if (i == 254) {
/* 151 */         System.out.print("MIDI Active sensing message");
/*     */       }
/*     */       else
/*     */       {
/* 155 */         System.out.print("A non-identified MIDI system message " + i);
/*     */       }
/*     */ 
/*     */     }
/* 161 */     else if (paramMidiMessage instanceof SysexMessage)
/*     */     {
/* 163 */       System.out.println();
/* 164 */       System.out.print("Sysex MIDI message <<");
/* 165 */       for (j = 0; j < arrayOfByte.length; ++j) {
/* 166 */         System.out.print(" " + arrayOfByte[j]);
/*     */       }
/* 168 */       System.out.println(">>");
/*     */     }
/* 172 */     else if (paramMidiMessage instanceof MetaMessage)
/*     */     {
/* 174 */       System.out.println();
/* 175 */       System.out.print("Meta MIDI Message {");
/* 176 */       for (j = 0; j < arrayOfByte.length; ++j) {
/* 177 */         System.out.print(" " + arrayOfByte[j]);
/*     */       }
/* 179 */       System.out.println("}");
/*     */     }
/*     */     else
/*     */     {
/* 184 */       System.out.println("Unknown MIDI message [");
/* 185 */       for (j = 0; j < arrayOfByte.length; ++j) {
/* 186 */         System.out.print(" " + arrayOfByte[j]);
/*     */       }
/* 188 */       System.out.println("]");
/*     */     }
/*     */   }
/*     */ 
/*     */   public void close()
/*     */   {
/*     */   }
/*     */ 
/*     */   private void setupMidiInput()
/*     */   {
/*     */     try
/*     */     {
/* 201 */       Frame localFrame = new Frame("MIDI Input port: Double-click to select.");
/*     */ 
/* 203 */       MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/* 204 */       List localList = new List();
/* 205 */       fillFrame(localFrame, localList, arrayOfInfo);
/* 206 */       localFrame.setVisible(true);
/*     */ 
/* 208 */       1 local1 = new MouseAdapter(localList, arrayOfInfo, localFrame) { private final List val$dataList;
/*     */         private final MidiDevice.Info[] val$info;
/*     */         private final Frame val$f;
/*     */ 
/*     */         public void mouseClicked(MouseEvent paramMouseEvent) { if (paramMouseEvent.getClickCount() == 2) {
/* 211 */             int i = this.val$dataList.getSelectedIndex();
/* 212 */             MidiCommunication.this.setMidiInputSelection(i, this.val$info);
/* 213 */             this.val$f.setVisible(false);
/* 214 */             MidiCommunication.this.setupMidiOutput();
/*     */           }
/*     */         }
/*     */       };
/* 218 */       localList.addMouseListener(local1);
/*     */     }
/*     */     catch (Exception localException) {
/* 221 */       System.out.println(localException);
/* 222 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setupMidiOutput()
/*     */   {
/*     */     try {
/* 229 */       MidiDevice.Info[] arrayOfInfo = MidiSystem.getMidiDeviceInfo();
/*     */ 
/* 231 */       Frame localFrame = new Frame("MIDI Output port: Double-click to select.");
/* 232 */       List localList = new List();
/* 233 */       fillFrame(localFrame, localList, arrayOfInfo);
/*     */ 
/* 235 */       2 local2 = new MouseAdapter(localList, arrayOfInfo, localFrame) { private final List val$dataList;
/*     */         private final MidiDevice.Info[] val$info;
/*     */         private final Frame val$f;
/*     */ 
/*     */         public void mouseClicked(MouseEvent paramMouseEvent) { if (paramMouseEvent.getClickCount() == 2) {
/* 238 */             int i = this.val$dataList.getSelectedIndex();
/* 239 */             MidiCommunication.this.setMidiOutputSelection(i, this.val$info);
/* 240 */             this.val$f.setVisible(false);
/* 241 */             MidiCommunication.access$302(MidiCommunication.this, false);
/*     */           }
/*     */         }
/*     */       };
/* 245 */       localList.addMouseListener(local2);
/* 246 */       localFrame.setVisible(true);
/*     */     } catch (Exception localException) {
/* 248 */       System.out.println(localException);
/* 249 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void fillFrame(Frame paramFrame, List paramList, MidiDevice.Info[] paramArrayOfInfo)
/*     */   {
/*     */     try {
/* 256 */       paramFrame.setSize(340, 200);
/* 257 */       paramFrame.setLocation(Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 170, Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 100);
/*     */ 
/* 259 */       String[] arrayOfString = new String[paramArrayOfInfo.length];
/* 260 */       arrayOfString[0] = "" + paramArrayOfInfo[0];
/* 261 */       arrayOfString[1] = "" + paramArrayOfInfo[1];
/* 262 */       for (int i = 2; i < paramArrayOfInfo.length; ++i) {
/* 263 */         arrayOfString[i] = MidiSystem.getMidiDevice(paramArrayOfInfo[i]).toString();
/*     */       }
/* 265 */       for (i = 0; i < paramArrayOfInfo.length; ++i) {
/* 266 */         paramList.add(arrayOfString[i]);
/*     */       }
/* 268 */       ScrollPane localScrollPane = new ScrollPane();
/* 269 */       localScrollPane.add(paramList);
/* 270 */       paramFrame.add(localScrollPane);
/*     */     } catch (Exception localException) {
/* 272 */       System.out.println(localException);
/* 273 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setMidiInputSelection(int paramInt, MidiDevice.Info[] paramArrayOfInfo)
/*     */   {
/*     */     try
/*     */     {
/* 285 */       MidiDevice localMidiDevice = MidiSystem.getMidiDevice(paramArrayOfInfo[paramInt]);
/*     */ 
/* 287 */       localMidiDevice.open();
/* 288 */       Transmitter localTransmitter = localMidiDevice.getTransmitter();
/* 289 */       localTransmitter.setReceiver(this);
/*     */     }
/*     */     catch (Exception localException) {
/* 292 */       System.out.println("Exception in PlumStone main ()");
/* 293 */       System.out.println(localException);
/* 294 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setMidiOutputSelection(int paramInt, MidiDevice.Info[] paramArrayOfInfo)
/*     */   {
/*     */     try
/*     */     {
/* 304 */       MidiDevice localMidiDevice = MidiSystem.getMidiDevice(paramArrayOfInfo[paramInt]);
/* 305 */       localMidiDevice.open();
/* 306 */       this.midiReceiver = localMidiDevice.getReceiver();
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/* 310 */       System.out.println("Exception in PlumStone main ()");
/* 311 */       System.out.println(localException);
/* 312 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.MidiCommunication
 * JD-Core Version:    0.5.3
 */