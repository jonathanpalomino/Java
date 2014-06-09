/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.Dialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.GridBagConstraints;
/*     */ import java.awt.GridBagLayout;
/*     */ import java.awt.Label;
/*     */ import java.awt.List;
/*     */ import java.awt.Panel;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.WindowEvent;
/*     */ import java.awt.event.WindowListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.StringTokenizer;
/*     */ import java.util.Vector;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class ParmScreen extends Dialog
/*     */   implements ActionListener, WindowListener
/*     */ {
/*     */   private List instrumentList;
/*     */   private List volumeList;
/*     */   private List tempoList;
/*     */   private Button instrumentButton;
/*     */   private Button volumeButton;
/*     */   private Button tempoButton;
/*     */   private Button closeButton;
/*     */   private Label instrumentLabel;
/*     */   private Label volumeLabel;
/*     */   private Label tempoLabel;
/*     */   private Phrase phrase;
/*     */ 
/*     */   public ParmScreen(Frame paramFrame)
/*     */   {
/*  62 */     super(paramFrame, "Set Music Parameters", true);
/*  63 */     initializeLists();
/*  64 */     initializeButtons();
/*  65 */     initializeLabels();
/*  66 */     setSize(500, 400);
/*  67 */     placeControls();
/*  68 */     addWindowListener(this);
/*  69 */     setVisible(false);
/*  70 */     pack(); }
/*     */ 
/*     */   public void windowOpened(WindowEvent paramWindowEvent) {
/*     */   }
/*     */ 
/*     */   public void windowClosing(WindowEvent paramWindowEvent) {
/*  76 */     if (paramWindowEvent.getSource() != this) return; dispose();
/*     */   }
/*     */ 
/*     */   public void windowClosed(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowIconified(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowDeiconified(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowActivated(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void windowDeactivated(WindowEvent paramWindowEvent)
/*     */   {
/*     */   }
/*     */ 
/*     */   public void getParms(Phrase paramPhrase, int paramInt1, int paramInt2)
/*     */   {
/* 111 */     this.phrase = paramPhrase;
/* 112 */     setLocation(paramInt1, paramInt2);
/* 113 */     show();
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/* 118 */     if (paramActionEvent.getSource() == this.tempoButton) {
/* 119 */       System.out.print("Adjusting Tempo ");
/* 120 */       System.out.print(this.tempoList.getSelectedItem());
/* 121 */       double d1 = this.phrase.getTempo();
/* 122 */       if (d1 < 10.0D) {
/* 123 */         d1 = 60.0D;
/*     */       }
/* 125 */       double d2 = getTempo(this.tempoList.getSelectedItem());
/* 126 */       this.phrase.setTempo(d2);
/* 127 */       multiplyTimesBy(d1 / d2);
/*     */     }
/* 130 */     else if (paramActionEvent.getSource() == this.volumeButton) {
/* 131 */       setVolume(getVolume(this.volumeList.getSelectedItem()));
/*     */     }
/* 136 */     else if (paramActionEvent.getSource() == this.instrumentButton) {
/* 137 */       this.phrase.setInstrument(getInstrument(this.instrumentList.getSelectedItem()));
/*     */     }
/* 142 */     else if (paramActionEvent.getSource() == this.closeButton) {
/* 143 */       dispose();
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initializeLists() {
/* 148 */     initializeInstrumentList();
/* 149 */     initializeVolumeList();
/* 150 */     initializeTempoList();
/*     */   }
/*     */ 
/*     */   private void initializeInstrumentList()
/*     */   {
/* 161 */     this.instrumentList = new List();
/* 162 */     this.instrumentList.add("Accordion             21");
/* 163 */     this.instrumentList.add("Applausen            126");
/* 164 */     this.instrumentList.add("Bandneon              23");
/* 165 */     this.instrumentList.add("Banjo                105");
/* 166 */     this.instrumentList.add("Bagpipes             109");
/* 167 */     this.instrumentList.add("Bass   (Acoustic)     32");
/* 168 */     this.instrumentList.add("Bass   (Fingerd)      33");
/* 169 */     this.instrumentList.add("Bass   (Fretless)     35");
/* 170 */     this.instrumentList.add("Bass   (Picked)       34");
/* 171 */     this.instrumentList.add("Bass   (Slap)         36");
/* 172 */     this.instrumentList.add("Bass   (Synth)        38");
/* 173 */     this.instrumentList.add("Bass   (Synth)        38");
/* 174 */     this.instrumentList.add("Bassoon               70");
/* 175 */     this.instrumentList.add("Bottle                76");
/* 176 */     this.instrumentList.add("Brass  (Synthetic)    62");
/* 177 */     this.instrumentList.add("Calliope              82");
/* 178 */     this.instrumentList.add("Celeste                8");
/* 179 */     this.instrumentList.add("Cello                 42");
/* 180 */     this.instrumentList.add("Charang               84");
/* 181 */     this.instrumentList.add("Choir                 52");
/* 182 */     this.instrumentList.add("Clarinet              71");
/* 183 */     this.instrumentList.add("Clavinet               7");
/* 184 */     this.instrumentList.add("Contrabass            43");
/* 185 */     this.instrumentList.add("English Horn          69");
/* 186 */     this.instrumentList.add("Fiddle               110");
/* 187 */     this.instrumentList.add("French Horn           60");
/* 188 */     this.instrumentList.add("Flute                 73");
/* 189 */     this.instrumentList.add("Glockenspiel           9");
/* 190 */     this.instrumentList.add("Guitar (Clean)        27");
/* 191 */     this.instrumentList.add("Guitar (Distorted)    30");
/* 192 */     this.instrumentList.add("Guitar Harmonics      31");
/* 193 */     this.instrumentList.add("Guitar (Jazz)         26");
/* 194 */     this.instrumentList.add("Guitar (Muted)        28");
/* 195 */     this.instrumentList.add("Guitar (Nylon)        24");
/* 196 */     this.instrumentList.add("Guitar (Overdrive)    29");
/* 197 */     this.instrumentList.add("Guitar (Steel)        25");
/* 198 */     this.instrumentList.add("Harmonica             22");
/* 199 */     this.instrumentList.add("Harp                  46");
/* 200 */     this.instrumentList.add("Harpsichord           76");
/* 201 */     this.instrumentList.add("Marimba               12");
/* 202 */     this.instrumentList.add("Music Box             10");
/* 203 */     this.instrumentList.add("Oboe                  68");
/* 204 */     this.instrumentList.add("Ocarina               79");
/* 205 */     this.instrumentList.add("Orchestra Hit         55");
/* 206 */     this.instrumentList.add("Organ                 16");
/* 207 */     this.instrumentList.add("Organ (Church)        19");
/* 208 */     this.instrumentList.add("Organ (Reed)          20");
/* 209 */     this.instrumentList.add("Pan Flute             75");
/* 210 */     this.instrumentList.add("Piano                  0");
/* 211 */     this.instrumentList.add("Piano (Electric)       4");
/* 212 */     this.instrumentList.add("Piano (Honkeytonk)     3");
/* 213 */     this.instrumentList.add("Piccolo               72");
/* 214 */     this.instrumentList.add("Recorder              74");
/* 215 */     this.instrumentList.add("Saxophone (Alto)      65");
/* 216 */     this.instrumentList.add("Saxophone (Soprano)   64");
/* 217 */     this.instrumentList.add("Saxophone (Tenor)     66");
/* 218 */     this.instrumentList.add("Saxophone (Baritone)  67");
/* 219 */     this.instrumentList.add("Shakuhachi            77");
/* 220 */     this.instrumentList.add("Steel Drums          114");
/* 221 */     this.instrumentList.add("Strings               48");
/* 222 */     this.instrumentList.add("Strings (Pizzicato)   45");
/* 223 */     this.instrumentList.add("Strings (Slow)        51");
/* 224 */     this.instrumentList.add("Strings (Synth)       50");
/* 225 */     this.instrumentList.add("Strings (Tremolo)     44");
/* 226 */     this.instrumentList.add("Tom-Tom              119");
/* 227 */     this.instrumentList.add("Trombone              57");
/* 228 */     this.instrumentList.add("Trumpet               56");
/* 229 */     this.instrumentList.add("Trumpet (Muted)       59");
/* 230 */     this.instrumentList.add("Tuba                  58");
/* 231 */     this.instrumentList.add("Tubular Bell          14");
/* 232 */     this.instrumentList.add("Timpani               47");
/* 233 */     this.instrumentList.add("Vibraphone            11");
/* 234 */     this.instrumentList.add("Viola                 41");
/* 235 */     this.instrumentList.add("Violin                40");
/* 236 */     this.instrumentList.add("Voice                 53");
/* 237 */     this.instrumentList.add("Vox                   56");
/* 238 */     this.instrumentList.add("Whistle               78");
/* 239 */     this.instrumentList.add("Wood Block           115");
/* 240 */     this.instrumentList.add("Xylophone             13");
/*     */   }
/*     */ 
/*     */   private void initializeVolumeList()
/*     */   {
/* 247 */     this.volumeList = new List();
/* 248 */     int i = 7;
/* 249 */     int j = 256;
/* 250 */     int k = 6;
/* 251 */     for (int l = i; l <= j; l += k)
/*     */     {
/* 253 */       this.volumeList.add(new Integer(l).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void initializeTempoList()
/*     */   {
/* 259 */     this.tempoList = new List();
/* 260 */     for (double d = 36.0D; d < 143.0D; d += 2.0D) {
/* 261 */       this.tempoList.add(new Double(d).toString());
/*     */     }
/* 263 */     for (d = 144.0D; d < 250.0D; d += 4.0D) {
/* 264 */       this.tempoList.add(new Double(d).toString());
/*     */     }
/* 266 */     for (d = 256.0D; d < 404.0D; d += 8.0D)
/* 267 */       this.tempoList.add(new Double(d).toString());
/*     */   }
/*     */ 
/*     */   private void initializeButtons()
/*     */   {
/* 273 */     this.instrumentButton = new Button("Apply");
/* 274 */     this.volumeButton = new Button("Apply");
/* 275 */     this.tempoButton = new Button("Apply");
/* 276 */     this.closeButton = new Button("Close");
/*     */   }
/*     */ 
/*     */   private void initializeLabels()
/*     */   {
/* 281 */     this.instrumentLabel = new Label("Instrument");
/* 282 */     this.volumeLabel = new Label("Volume");
/* 283 */     this.tempoLabel = new Label("Tempo");
/*     */   }
/*     */ 
/*     */   private void placeControls() {
/* 287 */     GridBagLayout localGridBagLayout = new GridBagLayout();
/* 288 */     GridBagConstraints localGridBagConstraints = new GridBagConstraints();
/* 289 */     setLayout(localGridBagLayout);
/*     */ 
/* 291 */     localGridBagConstraints.fill = 1;
/* 292 */     localGridBagConstraints.weightx = 0.5D;
/* 293 */     localGridBagConstraints.gridwidth = 1;
/* 294 */     localGridBagConstraints.gridheight = 1;
/*     */ 
/* 296 */     localGridBagConstraints.gridx = 0;
/* 297 */     localGridBagConstraints.gridy = 0;
/* 298 */     localGridBagConstraints.gridheight = 3;
/* 299 */     localGridBagLayout.setConstraints(this.instrumentLabel, localGridBagConstraints);
/* 300 */     add(this.instrumentLabel);
/* 301 */     localGridBagConstraints.gridx = 1;
/* 302 */     localGridBagLayout.setConstraints(this.instrumentList, localGridBagConstraints);
/* 303 */     add(this.instrumentList);
/* 304 */     localGridBagConstraints.gridwidth = 0;
/* 305 */     localGridBagConstraints.gridx = 2;
/* 306 */     Panel localPanel1 = new Panel();
/* 307 */     localPanel1.add(this.instrumentButton);
/* 308 */     localGridBagLayout.setConstraints(localPanel1, localGridBagConstraints);
/* 309 */     add(localPanel1);
/* 310 */     localGridBagConstraints.gridheight = 1;
/*     */ 
/* 312 */     localGridBagConstraints.gridwidth = 1;
/* 313 */     localGridBagConstraints.gridx = 0;
/* 314 */     localGridBagConstraints.gridy = 3;
/* 315 */     localGridBagLayout.setConstraints(this.volumeLabel, localGridBagConstraints);
/* 316 */     add(this.volumeLabel);
/* 317 */     localGridBagConstraints.gridx = 1;
/* 318 */     localGridBagLayout.setConstraints(this.volumeList, localGridBagConstraints);
/* 319 */     add(this.volumeList);
/* 320 */     localGridBagConstraints.gridwidth = 0;
/* 321 */     localGridBagConstraints.gridx = 2;
/* 322 */     Panel localPanel2 = new Panel();
/* 323 */     localPanel2.add(this.volumeButton);
/* 324 */     localGridBagLayout.setConstraints(localPanel2, localGridBagConstraints);
/* 325 */     add(localPanel2);
/*     */ 
/* 327 */     localGridBagConstraints.gridwidth = 1;
/* 328 */     localGridBagConstraints.gridx = 0;
/* 329 */     localGridBagConstraints.gridy = 4;
/* 330 */     localGridBagLayout.setConstraints(this.tempoLabel, localGridBagConstraints);
/* 331 */     add(this.tempoLabel);
/* 332 */     localGridBagConstraints.gridx = 1;
/* 333 */     localGridBagLayout.setConstraints(this.tempoList, localGridBagConstraints);
/* 334 */     add(this.tempoList);
/* 335 */     localGridBagConstraints.gridwidth = 0;
/* 336 */     localGridBagConstraints.gridx = 2;
/* 337 */     Panel localPanel3 = new Panel();
/* 338 */     localPanel3.add(this.tempoButton);
/* 339 */     localGridBagLayout.setConstraints(localPanel3, localGridBagConstraints);
/* 340 */     add(localPanel3);
/*     */ 
/* 342 */     localGridBagConstraints.gridwidth = 1;
/* 343 */     localGridBagConstraints.gridx = 1;
/* 344 */     localGridBagConstraints.gridy = 5;
/* 345 */     localGridBagLayout.setConstraints(this.closeButton, localGridBagConstraints);
/* 346 */     add(this.closeButton);
/*     */ 
/* 348 */     this.instrumentButton.addActionListener(this);
/* 349 */     this.volumeButton.addActionListener(this);
/* 350 */     this.tempoButton.addActionListener(this);
/* 351 */     this.closeButton.addActionListener(this);
/*     */   }
/*     */ 
/*     */   private static double getTempo(String paramString)
/*     */   {
/* 357 */     return new Double(pullFirst(paramString)).doubleValue();
/*     */   }
/*     */ 
/*     */   private static int getVolume(String paramString)
/*     */   {
/* 362 */     return new Integer(pullLast(paramString)).intValue();
/*     */   }
/*     */ 
/*     */   private static int getInstrument(String paramString)
/*     */   {
/* 367 */     return new Integer(pullLast(paramString)).intValue();
/*     */   }
/*     */ 
/*     */   private void setVolume(int paramInt)
/*     */   {
/* 372 */     Vector localVector = this.phrase.getNoteList();
/* 373 */     for (int i = 0; i < localVector.size(); ++i)
/* 374 */       if (this.phrase.getNote(i).getDynamic() != 0)
/* 375 */         this.phrase.getNote(i).setDynamic(paramInt);
/*     */   }
/*     */ 
/*     */   private void multiplyTimesBy(double paramDouble)
/*     */   {
/* 383 */     Vector localVector = this.phrase.getNoteList();
/* 384 */     System.out.println(paramDouble);
/* 385 */     for (int i = 0; i < localVector.size(); ++i);
/*     */   }
/*     */ 
/*     */   private static String pullFirst(String paramString)
/*     */   {
/* 391 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString);
/* 392 */     String str = "";
/* 393 */     while ((str == "") && (localStringTokenizer.hasMoreTokens())) {
/* 394 */       str = localStringTokenizer.nextToken();
/*     */     }
/* 396 */     return str;
/*     */   }
/*     */ 
/*     */   private static String pullLast(String paramString)
/*     */   {
/* 401 */     StringTokenizer localStringTokenizer = new StringTokenizer(paramString);
/* 402 */     Object localObject = "";
/* 403 */     String str = "";
/* 404 */     while (localStringTokenizer.hasMoreTokens()) {
/* 405 */       str = localStringTokenizer.nextToken();
/* 406 */       if (str != "");
/* 407 */       localObject = str;
/*     */     }
/*     */ 
/* 410 */     return ((String)localObject);
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.ParmScreen
 * JD-Core Version:    0.5.3
 */