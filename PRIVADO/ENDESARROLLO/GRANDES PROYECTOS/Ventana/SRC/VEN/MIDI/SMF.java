/*     */ package jm.midi;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.DataInputStream;
/*     */ import java.io.DataOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.midi.event.EndTrack;
/*     */ import jm.midi.event.Event;
/*     */ import jm.midi.event.VoiceEvt;
/*     */ 
/*     */ public final class SMF
/*     */   implements JMC
/*     */ {
/*     */   private short fileType;
/*     */   private short numOfTracks;
/*     */   private int numOfBytes;
/*     */   private short ppqn;
/*     */   private Vector trackList;
/*     */   private boolean VERBOSE;
/*     */ 
/*     */   public SMF()
/*     */   {
/*  64 */     this(1, 480);
/*     */   }
/*     */ 
/*     */   public SMF(short paramShort1, short paramShort2)
/*     */   {
/*  53 */     this.VERBOSE = false;
/*     */ 
/*  73 */     this.fileType = paramShort1;
/*  74 */     this.ppqn = paramShort2;
/*  75 */     this.numOfBytes = 0;
/*  76 */     this.numOfTracks = 0;
/*  77 */     this.trackList = new Vector();
/*     */   }
/*     */ 
/*     */   public void setVerbose(boolean paramBoolean) {
/*  81 */     this.VERBOSE = paramBoolean;
/*     */   }
/*     */ 
/*     */   public Vector getTrackList() {
/*  85 */     return this.trackList;
/*     */   }
/*     */ 
/*     */   public short getPPQN() {
/*  89 */     return this.ppqn;
/*     */   }
/*     */ 
/*     */   public void clearTracks()
/*     */   {
/*  94 */     if (this.trackList.isEmpty())
/*     */       return;
/*  96 */     this.trackList.removeAllElements();
/*     */   }
/*     */ 
/*     */   public void read(InputStream paramInputStream)
/*     */     throws IOException
/*     */   {
/* 114 */     byte[] arrayOfByte = new byte[paramInputStream.available()];
/* 115 */     paramInputStream.read(arrayOfByte);
/* 116 */     ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(arrayOfByte);
/*     */ 
/* 118 */     DataInputStream localDataInputStream = new DataInputStream(localByteArrayInputStream);
/*     */ 
/* 120 */     if (!(this.trackList.isEmpty())) {
/* 121 */       this.trackList.removeAllElements();
/*     */     }
/*     */ 
/* 124 */     if (localDataInputStream.readInt() != 1297377380) {
/* 125 */       throw new IOException("This is NOT a MIDI file !!!");
/*     */     }
/* 127 */     localDataInputStream.readInt();
/*     */     try
/*     */     {
/* 131 */       this.fileType = localDataInputStream.readShort();
/* 132 */       if (this.VERBOSE) System.out.println("MIDI file type = " + this.fileType);
/* 133 */       this.numOfTracks = localDataInputStream.readShort();
/* 134 */       if (this.VERBOSE) System.out.println("Number of tracks = " + this.numOfTracks);
/* 135 */       this.ppqn = localDataInputStream.readShort();
/* 136 */       if (this.VERBOSE) System.out.println("ppqn = " + this.ppqn);
/*     */     } catch (IOException localIOException) {
/* 138 */       System.out.println(localIOException);
/* 139 */       localIOException.printStackTrace();
/*     */     }
/*     */ 
/* 150 */     for (int i = 0; i < this.numOfTracks; ++i) {
/* 151 */       readTrackChunk(localDataInputStream);
/*     */     }
/* 153 */     paramInputStream.close();
/* 154 */     localDataInputStream.close();
/*     */   }
/*     */ 
/*     */   public void write(OutputStream paramOutputStream)
/*     */     throws IOException
/*     */   {
/* 169 */     DataOutputStream localDataOutputStream = new DataOutputStream(paramOutputStream);
/*     */ 
/* 171 */     this.numOfTracks = (short)this.trackList.size();
/*     */     try
/*     */     {
/* 174 */       localDataOutputStream.writeInt(1297377380);
/* 175 */       localDataOutputStream.writeInt(6);
/* 176 */       localDataOutputStream.writeShort(1);
/* 177 */       localDataOutputStream.writeShort(this.numOfTracks);
/* 178 */       localDataOutputStream.writeShort(this.ppqn);
/*     */     }
/*     */     catch (Exception localException) {
/* 181 */       localException.printStackTrace();
/*     */     }
/*     */ 
/* 184 */     Enumeration localEnumeration = this.trackList.elements();
/* 185 */     while (localEnumeration.hasMoreElements()) {
/* 186 */       Track localTrack = (Track)localEnumeration.nextElement();
/* 187 */       writeTrackChunk(localDataOutputStream, localTrack);
/*     */     }
/* 189 */     paramOutputStream.flush();
/* 190 */     paramOutputStream.close();
/* 191 */     localDataOutputStream.flush();
/* 192 */     localDataOutputStream.close();
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 199 */     Enumeration localEnumeration = this.trackList.elements();
/* 200 */     while (localEnumeration.hasMoreElements()) {
/* 201 */       Track localTrack = (Track)localEnumeration.nextElement();
/* 202 */       localTrack.print();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void skipATrack(RandomAccessFile paramRandomAccessFile)
/*     */     throws IOException
/*     */   {
/* 209 */     if (this.VERBOSE) System.out.println("Skipping the tempo track . . .");
/* 210 */     paramRandomAccessFile.readInt();
/* 211 */     paramRandomAccessFile.skipBytes(paramRandomAccessFile.readInt());
/*     */   }
/*     */ 
/*     */   private void readTrackChunk(DataInputStream paramDataInputStream)
/*     */     throws IOException
/*     */   {
/* 225 */     Track localTrack = new Track();
/*     */ 
/* 227 */     this.trackList.addElement(localTrack);
/* 228 */     int i = 0;
/* 229 */     if (this.VERBOSE) System.out.println("Reading Track ..........");
/*     */ 
/* 231 */     if (paramDataInputStream.readInt() != 1297379947) {
/* 232 */       throw new IOException("Track started in wrong place!!!!  ABORTING");
/*     */     }
/*     */ 
/* 235 */     paramDataInputStream.readInt();
/*     */ 
/* 238 */     int k = 0; int l = 0;
/*     */ 
/* 240 */     Object localObject = null;
/*     */     while (true)
/*     */     {
/*     */       try {
/* 244 */         i = MidiUtil.readVarLength(paramDataInputStream);
/*     */ 
/* 246 */         paramDataInputStream.mark(2);
/* 247 */         int j = paramDataInputStream.readUnsignedByte();
/*     */ 
/* 249 */         if (j < 128) {
/* 250 */           j = k;
/*     */ 
/* 252 */           paramDataInputStream.reset();
/*     */         }
/*     */         int i1;
/* 255 */         if (j >= 255) {
/* 256 */           i1 = paramDataInputStream.readUnsignedByte();
/* 257 */           l = MidiUtil.readVarLength(paramDataInputStream);
/* 258 */           localObject = MidiUtil.createMetaEvent(i1);
/* 259 */         } else if (j >= 240) {
/* 260 */           System.out.println("SysEX---");
/* 261 */           l = MidiUtil.readVarLength(paramDataInputStream);
/* 262 */         } else if (j >= 128) {
/* 263 */           i1 = (short)(j / 16);
/* 264 */           short s = (short)(j - (i1 * 16));
/* 265 */           VoiceEvt localVoiceEvt = (VoiceEvt)MidiUtil.createVoiceEvent(i1);
/* 266 */           localVoiceEvt.setMidiChannel(s);
/* 267 */           localObject = localVoiceEvt;
/* 268 */           if (localObject == null) {
/* 269 */             throw new IOException("MIDI file read error: invalid voice event type!");
/*     */           }
/*     */         }
/* 272 */         k = j;
/*     */       } catch (Exception localException) {
/* 274 */         localException.printStackTrace();
/* 275 */         System.exit(1);
/*     */       }
/* 277 */       if (localObject != null)
/*     */       {
/* 280 */         ((Event)localObject).setTime(i);
/* 281 */         ((Event)localObject).read(paramDataInputStream);
/*     */ 
/* 283 */         localTrack.addEvent((Event)localObject);
/*     */ 
/* 285 */         if (localObject instanceof EndTrack);
/* 286 */         return;
/*     */       }
/*     */ 
/* 289 */       paramDataInputStream.skipBytes(l);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void writeTrackChunk(DataOutputStream paramDataOutputStream, Track paramTrack)
/*     */     throws IOException
/*     */   {
/* 302 */     if (this.VERBOSE) System.out.println("Writing MIDI Track");
/*     */ 
/* 305 */     ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
/* 306 */     DataOutputStream localDataOutputStream = new DataOutputStream(localByteArrayOutputStream);
/* 307 */     int i = 1297379947;
/* 308 */     Enumeration localEnumeration = paramTrack.getEvtList().elements();
/* 309 */     localEnumeration = paramTrack.getEvtList().elements();
/*     */ 
/* 311 */     while (localEnumeration.hasMoreElements()) {
/* 312 */       Event localEvent = (Event)localEnumeration.nextElement();
/* 313 */       localEvent.write(localDataOutputStream);
/*     */     }
/*     */ 
/* 317 */     paramDataOutputStream.writeInt(i);
/* 318 */     paramDataOutputStream.writeInt(localByteArrayOutputStream.size());
/* 319 */     paramDataOutputStream.write(localByteArrayOutputStream.toByteArray(), 0, localByteArrayOutputStream.size());
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.midi.SMF
 * JD-Core Version:    0.5.3
 */