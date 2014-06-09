/*    */ package ven.music.data;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class Tempo
/*    */ {
/* 15 */   public static double ANDANTE = 140.0D;
/*    */ 
/* 18 */   public static double DEFAULT_TEMPO = 60.0D;
/* 19 */   public static double DEFAULT_LOW = 1.0E-007D;
/* 20 */   public static double DEFAULT_HIGH = 1000000.0D;
/*    */ 
/* 22 */   private double value = DEFAULT_TEMPO;
/*    */ 
/* 24 */   private double lowestTempo = DEFAULT_LOW;
/*    */ 
/* 26 */   private double highestTempo = DEFAULT_HIGH;
/*    */ 
/*    */   public Tempo()
/*    */   {
/*    */   }
/*    */ 
/*    */   public Tempo(double paramDouble) {
/* 33 */     this.value = paramDouble;
/*    */   }
/*    */ 
/*    */   public void setTempo(double paramDouble) {
/* 37 */     this.value = setInBounds(paramDouble);
/*    */   }
/*    */ 
/*    */   private double setInBounds(double paramDouble)
/*    */   {
/* 45 */     if (paramDouble <= this.lowestTempo)
/* 46 */       paramDouble = this.lowestTempo;
/* 47 */     else if (paramDouble >= this.highestTempo)
/* 48 */       paramDouble = this.highestTempo;
/* 49 */     return paramDouble;
/*    */   }
/*    */ 
/*    */   public double getPerMinute() {
/* 53 */     return this.value;
/*    */   }
/*    */ 
/*    */   public double getPerSecond() {
/* 57 */     return (60.0D / this.value);
/*    */   }
/*    */ 
/*    */   public void setHighestTempo(double paramDouble) {
/* 61 */     paramDouble = checkBelowZero(paramDouble);
/* 62 */     this.highestTempo = paramDouble;
/*    */   }
/*    */ 
/*    */   public double getHighestTempo() {
/* 66 */     return this.highestTempo;
/*    */   }
/*    */ 
/*    */   public void setLowestTempo(double paramDouble) {
/* 70 */     paramDouble = checkBelowZero(paramDouble);
/* 71 */     this.lowestTempo = paramDouble;
/*    */   }
/*    */ 
/*    */   private double checkBelowZero(double paramDouble) {
/* 75 */     if (paramDouble < 0.0D) {
/* 76 */       System.out.println("lowestTempo must be positive number!");
/* 77 */       System.out.println("setting it to 0.001");
/* 78 */       paramDouble = 0.001D;
/*    */     }
/* 80 */     return paramDouble;
/*    */   }
/*    */ 
/*    */   public double getLowestTempo() {
/* 84 */     return this.lowestTempo;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.data.Tempo
 * JD-Core Version:    0.5.3
 */