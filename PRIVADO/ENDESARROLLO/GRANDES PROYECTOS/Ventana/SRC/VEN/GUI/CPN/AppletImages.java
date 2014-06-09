/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.applet.Applet;
/*     */ import java.awt.Image;
/*     */ import java.net.URL;
/*     */ 
/*     */ public class AppletImages
/*     */   implements Images
/*     */ {
/*     */   private final Image trebleClef;
/*     */   private final Image bassClef;
/*     */   private final Image semibreve;
/*     */   private final Image minimUp;
/*     */   private final Image minimDown;
/*     */   private final Image crotchetUp;
/*     */   private final Image crotchetDown;
/*     */   private final Image quaverDown;
/*     */   private final Image quaverUp;
/*     */   private final Image semiquaverDown;
/*     */   private final Image semiquaverUp;
/*     */   private final Image semibreveRest;
/*     */   private final Image minimRest;
/*     */   private final Image crotchetRest;
/*     */   private final Image quaverRest;
/*     */   private final Image semiquaverRest;
/*     */   private final Image dot;
/*     */   private final Image sharp;
/*     */   private final Image flat;
/*     */   private final Image natural;
/*     */   private final Image one;
/*     */   private final Image two;
/*     */   private final Image three;
/*     */   private final Image four;
/*     */   private final Image five;
/*     */   private final Image six;
/*     */   private final Image seven;
/*     */   private final Image eight;
/*     */   private final Image nine;
/*     */   private final Image delete;
/*     */   private final Image tieOver;
/*     */   private final Image tieUnder;
/*     */ 
/*     */   public AppletImages(Applet paramApplet, URL paramURL)
/*     */   {
/* 116 */     this.trebleClef = paramApplet.getImage(paramURL, "trebleClef.gif");
/* 117 */     this.bassClef = paramApplet.getImage(paramURL, "bassClef.gif");
/* 118 */     this.crotchetDown = paramApplet.getImage(paramURL, "crotchetDown.gif");
/* 119 */     this.crotchetUp = paramApplet.getImage(paramURL, "crotchetUp.gif");
/* 120 */     this.quaverDown = paramApplet.getImage(paramURL, "quaverDown.gif");
/* 121 */     this.quaverUp = paramApplet.getImage(paramURL, "quaverUp.gif");
/* 122 */     this.semiquaverDown = paramApplet.getImage(paramURL, "semiquaverDown.gif");
/* 123 */     this.semiquaverUp = paramApplet.getImage(paramURL, "semiquaverUp.gif");
/* 124 */     this.minimDown = paramApplet.getImage(paramURL, "minimDown.gif");
/* 125 */     this.minimUp = paramApplet.getImage(paramURL, "minimUp.gif");
/* 126 */     this.semibreve = paramApplet.getImage(paramURL, "semibreve.gif");
/* 127 */     this.dot = paramApplet.getImage(paramURL, "dot.gif");
/* 128 */     this.semiquaverRest = paramApplet.getImage(paramURL, "semiquaverRest.gif");
/* 129 */     this.quaverRest = paramApplet.getImage(paramURL, "quaverRest.gif");
/* 130 */     this.crotchetRest = paramApplet.getImage(paramURL, "crotchetRest.gif");
/* 131 */     this.minimRest = paramApplet.getImage(paramURL, "minimRest.gif");
/* 132 */     this.semibreveRest = paramApplet.getImage(paramURL, "semibreveRest.gif");
/* 133 */     this.sharp = paramApplet.getImage(paramURL, "sharp.gif");
/* 134 */     this.flat = paramApplet.getImage(paramURL, "flat.gif");
/* 135 */     this.natural = paramApplet.getImage(paramURL, "natural.gif");
/* 136 */     this.one = paramApplet.getImage(paramURL, "one.gif");
/* 137 */     this.two = paramApplet.getImage(paramURL, "two.gif");
/* 138 */     this.three = paramApplet.getImage(paramURL, "three.gif");
/* 139 */     this.four = paramApplet.getImage(paramURL, "four.gif");
/* 140 */     this.five = paramApplet.getImage(paramURL, "five.gif");
/* 141 */     this.six = paramApplet.getImage(paramURL, "six.gif");
/* 142 */     this.seven = paramApplet.getImage(paramURL, "seven.gif");
/* 143 */     this.eight = paramApplet.getImage(paramURL, "eight.gif");
/* 144 */     this.nine = paramApplet.getImage(paramURL, "nine.gif");
/* 145 */     this.delete = paramApplet.getImage(paramURL, "delete.gif");
/* 146 */     this.tieOver = paramApplet.getImage(paramURL, "tieOver.gif");
/* 147 */     this.tieUnder = paramApplet.getImage(paramURL, "tieUnder.gif");
/*     */   }
/*     */ 
/*     */   public Image getTrebleClef() {
/* 151 */     return this.trebleClef;
/*     */   }
/*     */ 
/*     */   public Image getBassClef() {
/* 155 */     return this.bassClef;
/*     */   }
/*     */ 
/*     */   public Image getSemibreve() {
/* 159 */     return this.semibreve;
/*     */   }
/*     */ 
/*     */   public Image getMinimUp() {
/* 163 */     return this.minimUp;
/*     */   }
/*     */ 
/*     */   public Image getMinimDown() {
/* 167 */     return this.minimDown;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetUp() {
/* 171 */     return this.crotchetUp;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetDown() {
/* 175 */     return this.crotchetDown;
/*     */   }
/*     */ 
/*     */   public Image getQuaverUp() {
/* 179 */     return this.quaverUp;
/*     */   }
/*     */ 
/*     */   public Image getQuaverDown() {
/* 183 */     return this.quaverDown;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverUp() {
/* 187 */     return this.semiquaverUp;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverDown() {
/* 191 */     return this.semiquaverDown;
/*     */   }
/*     */ 
/*     */   public Image getSemibreveRest() {
/* 195 */     return this.semibreveRest;
/*     */   }
/*     */ 
/*     */   public Image getMinimRest() {
/* 199 */     return this.minimRest;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetRest() {
/* 203 */     return this.crotchetRest;
/*     */   }
/*     */ 
/*     */   public Image getQuaverRest() {
/* 207 */     return this.quaverRest;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverRest() {
/* 211 */     return this.semiquaverRest;
/*     */   }
/*     */ 
/*     */   public Image getDot() {
/* 215 */     return this.dot;
/*     */   }
/*     */ 
/*     */   public Image getSharp() {
/* 219 */     return this.sharp;
/*     */   }
/*     */ 
/*     */   public Image getFlat() {
/* 223 */     return this.flat;
/*     */   }
/*     */ 
/*     */   public Image getNatural() {
/* 227 */     return this.natural;
/*     */   }
/*     */ 
/*     */   public Image getOne() {
/* 231 */     return this.one;
/*     */   }
/*     */ 
/*     */   public Image getTwo() {
/* 235 */     return this.two;
/*     */   }
/*     */ 
/*     */   public Image getThree() {
/* 239 */     return this.three;
/*     */   }
/*     */ 
/*     */   public Image getFour() {
/* 243 */     return this.four;
/*     */   }
/*     */ 
/*     */   public Image getFive() {
/* 247 */     return this.five;
/*     */   }
/*     */ 
/*     */   public Image getSix() {
/* 251 */     return this.six;
/*     */   }
/*     */ 
/*     */   public Image getSeven() {
/* 255 */     return this.seven;
/*     */   }
/*     */ 
/*     */   public Image getEight() {
/* 259 */     return this.eight;
/*     */   }
/*     */ 
/*     */   public Image getNine() {
/* 263 */     return this.nine;
/*     */   }
/*     */ 
/*     */   public Image getDelete() {
/* 267 */     return this.delete;
/*     */   }
/*     */ 
/*     */   public Image getTieOver() {
/* 271 */     return this.tieOver;
/*     */   }
/*     */ 
/*     */   public Image getTieUnder() {
/* 275 */     return this.tieUnder;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.AppletImages
 * JD-Core Version:    0.5.3
 */