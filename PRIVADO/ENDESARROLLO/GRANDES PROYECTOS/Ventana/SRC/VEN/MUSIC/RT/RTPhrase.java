/*    */ package ven.music.rt;
/*    */ 
/*    */ import vena.JMC;
/*    */ import ven.audio.Instrument;
/*    */ import ven.music.data.Note;
/*    */ import ven.music.data.Phrase;
/*    */ 
/*    */ public class RTPhrase extends RTLine
/*    */   implements JMC
/*    */ {
/*    */   private Phrase phrase;
/*    */   private int noteCounter;
/*    */   private boolean waitForStartTime;
/*    */   private Note aRest;
/*    */ 
/*    */   public RTPhrase(Phrase paramPhrase, Instrument paramInstrument)
/*    */   {
/* 44 */     this(paramPhrase, new Instrument[] { paramInstrument });
/*    */   }
/*    */ 
/*    */   public RTPhrase(Phrase paramPhrase, Instrument[] paramArrayOfInstrument)
/*    */   {
/* 56 */     super(paramArrayOfInstrument);
/*    */ 
/* 34 */     this.noteCounter = 0;
/* 35 */     this.waitForStartTime = true;
/* 36 */     this.aRest = new Note(-2147483648, 1.0D);
/*    */ 
/* 57 */     this.phrase = paramPhrase;
/* 58 */     if (paramPhrase.getTempo() != -1.0D) setTempo(paramPhrase.getTempo());
/* 59 */     if (paramPhrase.getStartTime() != 0.0D) return; this.waitForStartTime = false;
/*    */   }
/*    */ 
/*    */   public synchronized Note getNote()
/*    */   {
/* 67 */     if (this.waitForStartTime)
/*    */     {
/* 69 */       this.waitForStartTime = false;
/* 70 */       this.aRest.setRhythmValue(this.phrase.getStartTime());
/* 71 */       return this.aRest;
/*    */     }
/*    */ 
/* 74 */     if (this.noteCounter < this.phrase.getSize())
/*    */     {
/* 76 */       return this.phrase.getNote(this.noteCounter++);
/*    */     }
/*    */ 
/* 80 */     this.aRest.setRhythmValue(1.0D);
/* 81 */     return this.aRest;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.rt.RTPhrase
 * JD-Core Version:    0.5.3
 */