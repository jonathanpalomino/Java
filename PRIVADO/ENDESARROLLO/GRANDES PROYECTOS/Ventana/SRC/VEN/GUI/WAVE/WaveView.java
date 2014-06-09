/*     */ package jm.gui.wave;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridLayout;
/*     */ import java.awt.Menu;
/*     */ import java.awt.MenuBar;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.MenuShortcut;
/*     */ import java.awt.Panel;
/*     */ import java.awt.ScrollPane;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.io.PrintStream;
/*     */ import jm.audio.RTMixer;
/*     */ import jm.music.rt.RTLine;
/*     */ import jm.util.AudioRTLine;
/*     */ 
/*     */ public class WaveView
/*     */   implements ActionListener, ComponentListener
/*     */ {
/*     */   private String lastFileName;
/*     */   private String lastDirectory;
/*     */   private WaveFileReader afr;
/*     */   private int width;
/*     */   private int channelHeight;
/*     */   private int amplitude;
/*     */   private int channels;
/*     */   private float[] data;
/*     */   private int resolution;
/*     */   private int segmentSize;
/*     */   private int startPos;
/*     */   private MenuItem size1;
/*     */   private MenuItem size2;
/*     */   private MenuItem size4;
/*     */   private MenuItem size8;
/*     */   private MenuItem size16;
/*     */   private MenuItem size32;
/*     */   private MenuItem size64;
/*     */   private MenuItem size128;
/*     */   private MenuItem size256;
/*     */   private MenuItem size512;
/*     */   private MenuItem size1024;
/*     */   private MenuItem size2048;
/*     */   private MenuItem size4096;
/*     */   private MenuItem openFile;
/*     */   private MenuItem quit;
/*     */   private MenuItem changeColor;
/*     */   private MenuItem vSmall;
/*     */   private MenuItem small;
/*     */   private MenuItem medium;
/*     */   private MenuItem large;
/*     */   private MenuItem huge;
/*     */   private MenuItem times1;
/*     */   private MenuItem times2;
/*     */   private MenuItem times3;
/*     */   private MenuItem times4;
/*     */   private Frame f;
/*     */   private WaveCanvas[] canvases;
/*     */   private ScrollPane sp;
/*     */   private WaveScrollPanel scrollPanel;
/*     */   private boolean whiteColor;
/*     */   private RTMixer mixer;
/*     */   private int lastStartPos;
/*     */ 
/*     */   public WaveView()
/*     */   {
/*  39 */     this.lastFileName = "Drunk.au";
/*  40 */     this.lastDirectory = "";
/*     */ 
/*  43 */     this.width = 600;
/*     */ 
/*  45 */     this.channelHeight = 200;
/*     */ 
/*  47 */     this.amplitude = 1;
/*     */ 
/*  53 */     this.resolution = 256;
/*     */ 
/*  57 */     this.startPos = 0;
/*     */ 
/*  64 */     this.f = new Frame();
/*  65 */     this.canvases = new WaveCanvas[8];
/*  66 */     this.sp = new ScrollPane(2);
/*  67 */     this.scrollPanel = new WaveScrollPanel();
/*  68 */     this.whiteColor = true;
/*     */ 
/*  71 */     this.lastStartPos = -1;
/*     */ 
/*  78 */     openFile();
/*  79 */     drawWave(0, 0);
/*     */   }
/*     */ 
/*     */   public WaveView(String paramString)
/*     */   {
/*  87 */     this(paramString, 0, 0);
/*     */   }
/*     */ 
/*     */   public WaveView(String paramString, int paramInt1, int paramInt2)
/*     */   {
/*  39 */     this.lastFileName = "Drunk.au";
/*  40 */     this.lastDirectory = "";
/*     */ 
/*  43 */     this.width = 600;
/*     */ 
/*  45 */     this.channelHeight = 200;
/*     */ 
/*  47 */     this.amplitude = 1;
/*     */ 
/*  53 */     this.resolution = 256;
/*     */ 
/*  57 */     this.startPos = 0;
/*     */ 
/*  64 */     this.f = new Frame();
/*  65 */     this.canvases = new WaveCanvas[8];
/*  66 */     this.sp = new ScrollPane(2);
/*  67 */     this.scrollPanel = new WaveScrollPanel();
/*  68 */     this.whiteColor = true;
/*     */ 
/*  71 */     this.lastStartPos = -1;
/*     */ 
/*  98 */     this.lastFileName = paramString;
/*  99 */     this.afr = new WaveFileReader(paramString);
/* 100 */     init();
/* 101 */     drawWave(paramInt1, paramInt2);
/* 102 */     this.f.setTitle("jMusic Wave Viewer: " + this.lastFileName);
/*     */   }
/*     */ 
/*     */   public void openFile()
/*     */   {
/* 109 */     FileDialog localFileDialog = new FileDialog(this.f, "Select a 16 bit audio file in .au format (no compression).", 0);
/*     */ 
/* 112 */     localFileDialog.setDirectory(this.lastDirectory);
/* 113 */     localFileDialog.setFile(this.lastFileName);
/* 114 */     localFileDialog.show();
/* 115 */     String str = localFileDialog.getFile();
/* 116 */     if (str != null) {
/* 117 */       this.lastFileName = str;
/* 118 */       this.lastDirectory = localFileDialog.getDirectory();
/* 119 */       this.afr = new WaveFileReader(this.lastDirectory + str);
/* 120 */       updateScrollInfo();
/* 121 */       init();
/* 122 */       setupPanel();
/* 123 */       this.lastFileName = str;
/* 124 */       this.f.setTitle("jMusic Wave Viewer: " + this.lastFileName);
/* 125 */       if (!(this.whiteColor)) {
/* 126 */         changeColor();
/*     */ 
/* 128 */         this.whiteColor = (!(this.whiteColor));
/*     */       }
/* 130 */       if (this.channels <= 2) setHeight(200);
/* 131 */       if (this.channels > 2) setHeight(100);
/* 132 */       if (this.channels > 4) setHeight(50);
/* 133 */       this.f.repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void init() {
/* 138 */     this.channels = this.afr.getChannels();
/* 139 */     if (this.channels > 8) {
/* 140 */       System.out.println("jMusic wave viewer error: Files with more than 8 channels are not supported :(");
/*     */ 
/* 142 */       System.exit(1);
/*     */     }
/*     */ 
/* 145 */     this.segmentSize = (this.width * this.resolution * this.channels);
/*     */ 
/* 148 */     this.data = this.afr.getSamples(this.segmentSize, this.startPos);
/* 149 */     setupChannels();
/* 150 */     if (this.channels <= 2) setHeight(200);
/* 151 */     if (this.channels > 2) setHeight(100);
/* 152 */     if (this.channels > 4) setHeight(50);
/* 153 */     this.scrollPanel.setScrollbarAttributes(this.afr.getWaveSize(), this.width, this.resolution);
/*     */   }
/*     */ 
/*     */   private void drawWave(int paramInt1, int paramInt2) {
/* 157 */     this.f.setName("jMusic Wave Viewer: " + this.lastFileName);
/* 158 */     this.f.setLocation(paramInt1, paramInt2);
/* 159 */     this.f.setLayout(new BorderLayout());
/* 160 */     this.sp.setSize(new Dimension(this.width, (this.channelHeight + 1) * this.channels));
/* 161 */     setupPanel();
/* 162 */     this.f.add(this.sp, "Center");
/*     */ 
/* 164 */     this.scrollPanel.setViewer(this);
/* 165 */     updateScrollInfo();
/* 166 */     this.scrollPanel.setScrollbarAttributes(this.afr.getWaveSize(), this.width, this.resolution);
/* 167 */     this.f.add(this.scrollPanel, "South");
/*     */ 
/* 170 */     MenuBar localMenuBar = new MenuBar();
/* 171 */     Menu localMenu1 = new Menu("Wave", true);
/* 172 */     Menu localMenu2 = new Menu("Height", true);
/* 173 */     Menu localMenu3 = new Menu("Resolution", true);
/* 174 */     Menu localMenu4 = new Menu("Amplitude", true);
/*     */ 
/* 176 */     this.size1 = new MenuItem("1:1");
/* 177 */     this.size1.addActionListener(this);
/* 178 */     localMenu3.add(this.size1);
/*     */ 
/* 180 */     this.size2 = new MenuItem("1:2");
/* 181 */     this.size2.addActionListener(this);
/* 182 */     localMenu3.add(this.size2);
/*     */ 
/* 184 */     this.size4 = new MenuItem("1:4");
/* 185 */     this.size4.addActionListener(this);
/* 186 */     localMenu3.add(this.size4);
/*     */ 
/* 188 */     this.size8 = new MenuItem("1:8");
/* 189 */     this.size8.addActionListener(this);
/* 190 */     localMenu3.add(this.size8);
/*     */ 
/* 192 */     this.size16 = new MenuItem("1:16");
/* 193 */     this.size16.addActionListener(this);
/* 194 */     localMenu3.add(this.size16);
/*     */ 
/* 196 */     this.size32 = new MenuItem("1:32");
/* 197 */     this.size32.addActionListener(this);
/* 198 */     localMenu3.add(this.size32);
/*     */ 
/* 200 */     this.size64 = new MenuItem("1:64");
/* 201 */     this.size64.addActionListener(this);
/* 202 */     localMenu3.add(this.size64);
/*     */ 
/* 204 */     this.size128 = new MenuItem("1:128");
/* 205 */     this.size128.addActionListener(this);
/* 206 */     localMenu3.add(this.size128);
/*     */ 
/* 208 */     this.size256 = new MenuItem("1:256");
/* 209 */     this.size256.addActionListener(this);
/* 210 */     localMenu3.add(this.size256);
/*     */ 
/* 212 */     this.size512 = new MenuItem("1:512");
/* 213 */     this.size512.addActionListener(this);
/* 214 */     localMenu3.add(this.size512);
/*     */ 
/* 216 */     this.size1024 = new MenuItem("1:1024");
/* 217 */     this.size1024.addActionListener(this);
/* 218 */     localMenu3.add(this.size1024);
/*     */ 
/* 220 */     this.size2048 = new MenuItem("1:2048");
/* 221 */     this.size2048.addActionListener(this);
/* 222 */     localMenu3.add(this.size2048);
/*     */ 
/* 228 */     this.openFile = new MenuItem("Open...", new MenuShortcut(79));
/* 229 */     this.openFile.addActionListener(this);
/* 230 */     localMenu1.add(this.openFile);
/*     */ 
/* 232 */     this.changeColor = new MenuItem("Change Color", new MenuShortcut(67));
/* 233 */     this.changeColor.addActionListener(this);
/* 234 */     localMenu1.add(this.changeColor);
/*     */ 
/* 236 */     this.quit = new MenuItem("Quit", new MenuShortcut(81));
/* 237 */     this.quit.addActionListener(this);
/* 238 */     localMenu1.add(this.quit);
/*     */ 
/* 240 */     this.vSmall = new MenuItem("X Small");
/* 241 */     this.vSmall.addActionListener(this);
/* 242 */     localMenu2.add(this.vSmall);
/*     */ 
/* 244 */     this.small = new MenuItem("Small");
/* 245 */     this.small.addActionListener(this);
/* 246 */     localMenu2.add(this.small);
/*     */ 
/* 248 */     this.medium = new MenuItem("Medium");
/* 249 */     this.medium.addActionListener(this);
/* 250 */     localMenu2.add(this.medium);
/*     */ 
/* 252 */     this.large = new MenuItem("Large");
/* 253 */     this.large.addActionListener(this);
/* 254 */     localMenu2.add(this.large);
/*     */ 
/* 256 */     this.huge = new MenuItem("X Large");
/* 257 */     this.huge.addActionListener(this);
/* 258 */     localMenu2.add(this.huge);
/*     */ 
/* 260 */     this.times1 = new MenuItem("x1");
/* 261 */     this.times1.addActionListener(this);
/* 262 */     localMenu4.add(this.times1);
/*     */ 
/* 264 */     this.times2 = new MenuItem("x2");
/* 265 */     this.times2.addActionListener(this);
/* 266 */     localMenu4.add(this.times2);
/*     */ 
/* 268 */     this.times3 = new MenuItem("x3");
/* 269 */     this.times3.addActionListener(this);
/* 270 */     localMenu4.add(this.times3);
/*     */ 
/* 272 */     this.times4 = new MenuItem("x4");
/* 273 */     this.times4.addActionListener(this);
/* 274 */     localMenu4.add(this.times4);
/*     */ 
/* 276 */     localMenuBar.add(localMenu1);
/* 277 */     localMenuBar.add(localMenu2);
/* 278 */     localMenuBar.add(localMenu3);
/* 279 */     localMenuBar.add(localMenu4);
/* 280 */     this.f.setMenuBar(localMenuBar);
/*     */ 
/* 283 */     this.sp.setSize(new Dimension(this.width, (this.channelHeight + 1) * this.channels));
/* 284 */     this.f.pack();
/* 285 */     this.width = this.f.getSize().width;
/* 286 */     this.f.setVisible(true);
/* 287 */     this.f.addComponentListener(this);
/*     */   }
/*     */ 
/*     */   private void updateScrollInfo()
/*     */   {
/* 292 */     this.scrollPanel.setFileName(this.lastFileName);
/* 293 */     this.scrollPanel.setBitSize(this.afr.getBitResolution());
/* 294 */     this.scrollPanel.setSampleRate(this.afr.getSampleRate());
/* 295 */     this.scrollPanel.setChannels(this.afr.getChannels());
/* 296 */     this.scrollPanel.getWaveRuler().setMarkerWidth(this.afr.getSampleRate() / this.resolution);
/* 297 */     this.scrollPanel.setScrollbarValue(this.startPos);
/* 298 */     this.scrollPanel.setScrollbarResolution(this.resolution);
/*     */   }
/*     */ 
/*     */   private void setupChannels()
/*     */   {
/* 303 */     this.channels = this.afr.getChannels();
/* 304 */     float[][] arrayOfFloat = new float[this.channels][this.segmentSize / this.channels];
/* 305 */     int i = 0;
/* 306 */     for (int j = 0; j < this.segmentSize; j += this.channels) {
/* 307 */       for (int k = 0; k < this.channels; ++k) {
/* 308 */         arrayOfFloat[k][i] = this.data[(j + k)];
/*     */       }
/* 310 */       ++i;
/*     */     }
/*     */ 
/* 313 */     for (j = 0; j < this.channels; ++j) {
/* 314 */       this.canvases[j] = new WaveCanvas();
/* 315 */       this.canvases[j].setSize(new Dimension(this.width, this.channelHeight + 1));
/* 316 */       this.canvases[j].setData(arrayOfFloat[j]);
/* 317 */       this.canvases[j].setResolution(this.resolution);
/* 318 */       this.canvases[j].setHeight(this.channelHeight);
/* 319 */       this.canvases[j].setAmplitude(this.amplitude);
/* 320 */       this.canvases[j].setWaveSize(this.afr.getWaveSize());
/*     */     }
/*     */   }
/*     */ 
/*     */   public void setStartPos(int paramInt)
/*     */   {
/* 328 */     this.startPos = paramInt;
/* 329 */     reRead();
/*     */ 
/* 337 */     for (int i = 0; i < this.channels; ++i) {
/* 338 */       this.canvases[i].setFastDraw(false);
/* 339 */       this.canvases[i].repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getStartPos()
/*     */   {
/* 347 */     return this.startPos;
/*     */   }
/*     */ 
/*     */   private void reRead() {
/* 351 */     this.segmentSize = (this.width * this.resolution * this.channels);
/* 352 */     if (this.segmentSize < 0) return;
/*     */ 
/* 354 */     if (this.segmentSize > this.afr.getWaveSize() * this.channels * this.resolution) {
/* 355 */       this.segmentSize = (this.afr.getWaveSize() * this.channels * this.resolution);
/*     */     }
/*     */ 
/* 358 */     if (this.startPos != this.lastStartPos) {
/* 359 */       this.data = this.afr.getSamples(this.segmentSize, this.startPos);
/* 360 */       updateChannelData();
/* 361 */       this.lastStartPos = this.startPos;
/*     */     }
/*     */   }
/*     */ 
/*     */   private void updateChannelData() {
/* 366 */     float[][] arrayOfFloat = new float[this.channels][this.segmentSize / this.channels];
/* 367 */     int i = 0;
/* 368 */     for (int j = 0; j < this.segmentSize; j += this.channels) {
/* 369 */       for (int k = 0; k < this.channels; ++k) {
/* 370 */         arrayOfFloat[k][i] = this.data[(j + k)];
/*     */       }
/* 372 */       ++i;
/*     */     }
/* 374 */     for (j = 0; j < this.channels; ++j) {
/* 375 */       this.canvases[j].setData(arrayOfFloat[j]);
/* 376 */       this.canvases[j].setResolution(this.resolution);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setupPanel() {
/* 381 */     Panel localPanel = new Panel();
/* 382 */     localPanel.setLayout(new GridLayout(this.channels, 1));
/* 383 */     localPanel.setSize(new Dimension(this.width, this.channelHeight * this.channels));
/* 384 */     for (int i = 0; i < this.channels; ++i) {
/* 385 */       localPanel.add(this.canvases[i]);
/*     */     }
/* 387 */     this.sp.add(localPanel);
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 391 */     if (paramActionEvent.getSource() == this.size1) setResolution(1);
/* 392 */     if (paramActionEvent.getSource() == this.size2) setResolution(2);
/* 393 */     if (paramActionEvent.getSource() == this.size4) setResolution(4);
/* 394 */     if (paramActionEvent.getSource() == this.size8) setResolution(8);
/* 395 */     if (paramActionEvent.getSource() == this.size16) setResolution(16);
/* 396 */     if (paramActionEvent.getSource() == this.size32) setResolution(32);
/* 397 */     if (paramActionEvent.getSource() == this.size64) setResolution(64);
/* 398 */     if (paramActionEvent.getSource() == this.size128) setResolution(128);
/* 399 */     if (paramActionEvent.getSource() == this.size256) setResolution(256);
/* 400 */     if (paramActionEvent.getSource() == this.size512) setResolution(512);
/* 401 */     if (paramActionEvent.getSource() == this.size1024) setResolution(1024);
/* 402 */     if (paramActionEvent.getSource() == this.size2048) setResolution(2048);
/* 403 */     if (paramActionEvent.getSource() == this.openFile) openFile();
/* 404 */     if (paramActionEvent.getSource() == this.quit) System.exit(0);
/* 405 */     if (paramActionEvent.getSource() == this.vSmall) setHeight(25);
/* 406 */     if (paramActionEvent.getSource() == this.small) setHeight(50);
/* 407 */     if (paramActionEvent.getSource() == this.medium) setHeight(100);
/* 408 */     if (paramActionEvent.getSource() == this.large) setHeight(200);
/* 409 */     if (paramActionEvent.getSource() == this.huge) setHeight(300);
/* 410 */     if (paramActionEvent.getSource() == this.times1) setAmplitude(1);
/* 411 */     if (paramActionEvent.getSource() == this.times2) setAmplitude(2);
/* 412 */     if (paramActionEvent.getSource() == this.times3) setAmplitude(3);
/* 413 */     if (paramActionEvent.getSource() == this.times4) setAmplitude(4);
/* 414 */     if (paramActionEvent.getSource() != this.changeColor) return; changeColor();
/*     */   }
/*     */ 
/*     */   private void changeColor()
/*     */   {
/* 419 */     for (int i = 0; i < this.channels; ++i) {
/* 420 */       this.canvases[i].toggleColor();
/*     */     }
/* 422 */     this.whiteColor = (!(this.whiteColor));
/*     */   }
/*     */ 
/*     */   public void setResolution(int paramInt)
/*     */   {
/* 430 */     if ((paramInt > 0) && (paramInt <= 2048)) {
/* 431 */       this.resolution = paramInt;
/* 432 */       if (this.afr.getWaveSize() / this.resolution < this.width) this.startPos = 0;
/* 433 */       reRead();
/* 434 */       this.scrollPanel.setResolution(paramInt);
/* 435 */       this.scrollPanel.setScrollbarResolution(paramInt);
/* 436 */       for (int i = 0; i < this.channels; ++i)
/* 437 */         this.canvases[i].setResolution(paramInt);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getResolution()
/*     */   {
/* 448 */     return this.resolution;
/*     */   }
/*     */ 
/*     */   public int getSampleRate()
/*     */   {
/* 455 */     return this.afr.getSampleRate();
/*     */   }
/*     */ 
/*     */   public int getChannels()
/*     */   {
/* 462 */     return this.afr.getChannels();
/*     */   }
/*     */ 
/*     */   public String getFileName()
/*     */   {
/* 469 */     return this.lastDirectory + this.lastFileName;
/*     */   }
/*     */ 
/*     */   public int getWidth()
/*     */   {
/* 476 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void repaint() {
/* 480 */     this.sp.setSize(this.f.getSize().width, this.f.getSize().height);
/* 481 */     for (int i = 0; i < this.channels; ++i) {
/* 482 */       this.canvases[i].setSize(this.f.getSize().width, this.canvases[i].getSize().height);
/* 483 */       this.canvases[i].repaint();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void componentResized(ComponentEvent paramComponentEvent)
/*     */   {
/* 490 */     if (this.f.getSize().width > this.width) {
/* 491 */       this.width = this.f.getSize().width;
/* 492 */       reRead(); } else {
/* 493 */       this.width = this.f.getSize().width; }
/* 494 */     for (int i = 0; i < this.channels; ++i) {
/* 495 */       this.canvases[i].setSize(this.width, this.canvases[i].getSize().height);
/* 496 */       this.canvases[i].setResized(true); }
/*     */   }
/*     */ 
/*     */   public void componentHidden(ComponentEvent paramComponentEvent) {
/*     */   }
/*     */ 
/*     */   public void componentMoved(ComponentEvent paramComponentEvent) {
/*     */   }
/*     */ 
/*     */   public void componentShown(ComponentEvent paramComponentEvent) {
/*     */   }
/*     */ 
/*     */   public void setHeight(int paramInt) {
/* 509 */     this.channelHeight = paramInt;
/* 510 */     setupChannels();
/* 511 */     setupPanel();
/* 512 */     this.sp.setSize(new Dimension(this.width, (this.channelHeight + 1) * this.channels));
/* 513 */     if (!(this.whiteColor)) {
/* 514 */       changeColor();
/*     */ 
/* 516 */       this.whiteColor = (!(this.whiteColor));
/*     */     }
/* 518 */     this.f.pack();
/* 519 */     repaint();
/* 520 */     this.sp.repaint();
/*     */   }
/*     */ 
/*     */   public void setAmplitude(int paramInt)
/*     */   {
/* 529 */     this.amplitude = paramInt;
/* 530 */     for (int i = 0; i < this.channels; ++i)
/* 531 */       this.canvases[i].setAmplitude(paramInt);
/*     */   }
/*     */ 
/*     */   public void playFile()
/*     */   {
/* 536 */     if (this.channels > 2) {
/* 537 */       System.out.println("jMusic Wave View notification: Sorry, only mono and stereo files can be played at present.");
/*     */     }
/*     */     else {
/* 540 */       System.out.println("---- Playing audio file '" + getFileName() + "'... Sample rate = " + this.afr.getSampleRate() + " Channels = " + this.afr.getChannels() + " ----");
/*     */ 
/* 542 */       RTLine[] arrayOfRTLine = { new AudioRTLine(getFileName()) };
/* 543 */       this.mixer = new RTMixer(arrayOfRTLine);
/* 544 */       this.mixer.begin();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void pauseFile()
/*     */   {
/* 553 */     if (this.mixer != null);
/* 554 */     this.mixer.pause();
/*     */   }
/*     */ 
/*     */   public void unPauseFile()
/*     */   {
/* 563 */     if (this.mixer == null) return; this.mixer.unPause();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.wave.WaveView
 * JD-Core Version:    0.5.3
 */