/*    */ package jm.audio.synth;
/*    */ 
/*    */ import jm.audio.AOException;
/*    */ import jm.audio.AudioObject;
/*    */ 
/*    */ public final class Window extends AudioObject
/*    */ {
/*    */   private int type;
/*    */   private boolean direction;
/*    */ 
/*    */   public Window(AudioObject paramAudioObject, int paramInt, boolean paramBoolean)
/*    */   {
/* 50 */     super(paramAudioObject, "[Window]");
/* 51 */     this.type = paramInt;
/* 52 */     this.direction = paramBoolean;
/*    */   }
/*    */ 
/*    */   public int work(float[] paramArrayOfFloat)
/*    */     throws AOException
/*    */   {
/* 65 */     int i = this.previous[0].nextWork(paramArrayOfFloat);
/*    */     int j;
/* 66 */     if (this.direction) {
/* 67 */       for (j = 0; j < i; ++j)
/* 68 */         paramArrayOfFloat[j] *= (float)Math.sin(3.141592653589793D * j / i);
/*    */     }
/*    */     else {
/* 71 */       for (j = 0; j < i; ++j) {
/* 72 */         paramArrayOfFloat[j] /= (float)Math.sin(3.141592653589793D * j / i);
/*    */       }
/*    */     }
/* 75 */     return i;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.synth.Window
 * JD-Core Version:    0.5.3
 */