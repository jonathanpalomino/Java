/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.awt.Toolkit;
/*     */ 
/*     */ public class ToolkitImages
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
/*     */   public ToolkitImages()
/*     */   {
/* 108 */     Toolkit localToolkit = Toolkit.getDefaultToolkit();
/*     */ 
/* 110 */     this.trebleClef = localToolkit.getImage(Stave.class.getResource("graphics/trebleClef.gif"));
/*     */ 
/* 112 */     this.bassClef = localToolkit.getImage(Stave.class.getResource("graphics/bassClef.gif"));
/*     */ 
/* 114 */     this.crotchetDown = localToolkit.getImage(Stave.class.getResource("graphics/crotchetDown.gif"));
/*     */ 
/* 116 */     this.crotchetUp = localToolkit.getImage(Stave.class.getResource("graphics/crotchetUp.gif"));
/*     */ 
/* 118 */     this.quaverDown = localToolkit.getImage(Stave.class.getResource("graphics/quaverDown.gif"));
/*     */ 
/* 120 */     this.quaverUp = localToolkit.getImage(Stave.class.getResource("graphics/quaverUp.gif"));
/*     */ 
/* 122 */     this.semiquaverDown = localToolkit.getImage(Stave.class.getResource("graphics/semiquaverDown.gif"));
/*     */ 
/* 124 */     this.semiquaverUp = localToolkit.getImage(Stave.class.getResource("graphics/semiquaverUp.gif"));
/*     */ 
/* 126 */     this.minimDown = localToolkit.getImage(Stave.class.getResource("graphics/minimDown.gif"));
/*     */ 
/* 128 */     this.minimUp = localToolkit.getImage(Stave.class.getResource("graphics/minimUp.gif"));
/*     */ 
/* 130 */     this.semibreve = localToolkit.getImage(Stave.class.getResource("graphics/semibreve.gif"));
/*     */ 
/* 132 */     this.dot = localToolkit.getImage(Stave.class.getResource("graphics/dot.gif"));
/*     */ 
/* 134 */     this.semiquaverRest = localToolkit.getImage(Stave.class.getResource("graphics/semiquaverRest.gif"));
/*     */ 
/* 136 */     this.quaverRest = localToolkit.getImage(Stave.class.getResource("graphics/quaverRest.gif"));
/*     */ 
/* 138 */     this.crotchetRest = localToolkit.getImage(Stave.class.getResource("graphics/crotchetRest.gif"));
/*     */ 
/* 140 */     this.minimRest = localToolkit.getImage(Stave.class.getResource("graphics/minimRest.gif"));
/*     */ 
/* 142 */     this.semibreveRest = localToolkit.getImage(Stave.class.getResource("graphics/semibreveRest.gif"));
/*     */ 
/* 144 */     this.sharp = localToolkit.getImage(Stave.class.getResource("graphics/sharp.gif"));
/*     */ 
/* 146 */     this.flat = localToolkit.getImage(Stave.class.getResource("graphics/flat.gif"));
/*     */ 
/* 148 */     this.natural = localToolkit.getImage(Stave.class.getResource("graphics/natural.gif"));
/*     */ 
/* 150 */     this.one = localToolkit.getImage(Stave.class.getResource("graphics/one.gif"));
/*     */ 
/* 152 */     this.two = localToolkit.getImage(Stave.class.getResource("graphics/two.gif"));
/*     */ 
/* 154 */     this.three = localToolkit.getImage(Stave.class.getResource("graphics/three.gif"));
/*     */ 
/* 156 */     this.four = localToolkit.getImage(Stave.class.getResource("graphics/four.gif"));
/*     */ 
/* 158 */     this.five = localToolkit.getImage(Stave.class.getResource("graphics/five.gif"));
/*     */ 
/* 160 */     this.six = localToolkit.getImage(Stave.class.getResource("graphics/six.gif"));
/*     */ 
/* 162 */     this.seven = localToolkit.getImage(Stave.class.getResource("graphics/seven.gif"));
/*     */ 
/* 164 */     this.eight = localToolkit.getImage(Stave.class.getResource("graphics/eight.gif"));
/*     */ 
/* 166 */     this.nine = localToolkit.getImage(Stave.class.getResource("graphics/nine.gif"));
/*     */ 
/* 168 */     this.delete = localToolkit.getImage(Stave.class.getResource("graphics/delete.gif"));
/*     */ 
/* 170 */     this.tieOver = localToolkit.getImage(Stave.class.getResource("graphics/tieOver.gif"));
/*     */ 
/* 172 */     this.tieUnder = localToolkit.getImage(Stave.class.getResource("graphics/tieUnder.gif"));
/*     */   }
/*     */ 
/*     */   public Image getTrebleClef()
/*     */   {
/* 177 */     return this.trebleClef;
/*     */   }
/*     */ 
/*     */   public Image getBassClef() {
/* 181 */     return this.bassClef;
/*     */   }
/*     */ 
/*     */   public Image getSemibreve() {
/* 185 */     return this.semibreve;
/*     */   }
/*     */ 
/*     */   public Image getMinimUp() {
/* 189 */     return this.minimUp;
/*     */   }
/*     */ 
/*     */   public Image getMinimDown() {
/* 193 */     return this.minimDown;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetUp() {
/* 197 */     return this.crotchetUp;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetDown() {
/* 201 */     return this.crotchetDown;
/*     */   }
/*     */ 
/*     */   public Image getQuaverUp() {
/* 205 */     return this.quaverUp;
/*     */   }
/*     */ 
/*     */   public Image getQuaverDown() {
/* 209 */     return this.quaverDown;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverUp() {
/* 213 */     return this.semiquaverUp;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverDown() {
/* 217 */     return this.semiquaverDown;
/*     */   }
/*     */ 
/*     */   public Image getSemibreveRest() {
/* 221 */     return this.semibreveRest;
/*     */   }
/*     */ 
/*     */   public Image getMinimRest() {
/* 225 */     return this.minimRest;
/*     */   }
/*     */ 
/*     */   public Image getCrotchetRest() {
/* 229 */     return this.crotchetRest;
/*     */   }
/*     */ 
/*     */   public Image getQuaverRest() {
/* 233 */     return this.quaverRest;
/*     */   }
/*     */ 
/*     */   public Image getSemiquaverRest() {
/* 237 */     return this.semiquaverRest;
/*     */   }
/*     */ 
/*     */   public Image getDot() {
/* 241 */     return this.dot;
/*     */   }
/*     */ 
/*     */   public Image getSharp() {
/* 245 */     return this.sharp;
/*     */   }
/*     */ 
/*     */   public Image getFlat() {
/* 249 */     return this.flat;
/*     */   }
/*     */ 
/*     */   public Image getNatural() {
/* 253 */     return this.natural;
/*     */   }
/*     */ 
/*     */   public Image getOne() {
/* 257 */     return this.one;
/*     */   }
/*     */ 
/*     */   public Image getTwo() {
/* 261 */     return this.two;
/*     */   }
/*     */ 
/*     */   public Image getThree() {
/* 265 */     return this.three;
/*     */   }
/*     */ 
/*     */   public Image getFour() {
/* 269 */     return this.four;
/*     */   }
/*     */ 
/*     */   public Image getFive() {
/* 273 */     return this.five;
/*     */   }
/*     */ 
/*     */   public Image getSix() {
/* 277 */     return this.six;
/*     */   }
/*     */ 
/*     */   public Image getSeven() {
/* 281 */     return this.seven;
/*     */   }
/*     */ 
/*     */   public Image getEight() {
/* 285 */     return this.eight;
/*     */   }
/*     */ 
/*     */   public Image getNine() {
/* 289 */     return this.nine;
/*     */   }
/*     */ 
/*     */   public Image getDelete() {
/* 293 */     return this.delete;
/*     */   }
/*     */ 
/*     */   public Image getTieOver() {
/* 297 */     return this.tieOver;
/*     */   }
/*     */ 
/*     */   public Image getTieUnder() {
/* 301 */     return this.tieUnder;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.ToolkitImages
 * JD-Core Version:    0.5.3
 */