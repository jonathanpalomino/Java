/*    */ package ven.audio.math;
/*    */ 
/*    */ public abstract class RealFloatFFT
/*    */ {
/*    */   int n;
/*    */ 
/*    */   public RealFloatFFT(int paramInt)
/*    */   {
/* 35 */     if (paramInt <= 0)
/* 36 */       throw new IllegalArgumentException("The transform length must be >=0 : " + paramInt);
/* 37 */     this.n = paramInt; }
/*    */ 
/*    */   protected void checkData(float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
/* 40 */     if (paramInt1 < 0)
/* 41 */       throw new IllegalArgumentException("The offset must be >=0 : " + paramInt1);
/* 42 */     if (paramInt2 < 1)
/* 43 */       throw new IllegalArgumentException("The stride must be >=1 : " + paramInt2);
/* 44 */     if (paramInt1 + paramInt2 * (this.n - 1) + 1 > paramArrayOfFloat.length)
/* 45 */       throw new IllegalArgumentException("The data array is too small for " + this.n + ":" + "i0=" + paramInt1 + " stride=" + paramInt2 + " data.length=" + paramArrayOfFloat.length);
/*    */   }
/*    */ 
/*    */   public void transform(float[] paramArrayOfFloat)
/*    */   {
/* 51 */     transform(paramArrayOfFloat, 0, 1);
/*    */   }
/*    */ 
/*    */   public abstract void transform(float[] paramArrayOfFloat, int paramInt1, int paramInt2);
/*    */ 
/*    */   public float[] toWraparoundOrder(float[] paramArrayOfFloat)
/*    */   {
/* 59 */     return toWraparoundOrder(paramArrayOfFloat, 0, 1);
/*    */   }
/*    */ 
/*    */   public abstract float[] toWraparoundOrder(float[] paramArrayOfFloat, int paramInt1, int paramInt2);
/*    */ 
/*    */   public void backtransform(float[] paramArrayOfFloat)
/*    */   {
/* 69 */     backtransform(paramArrayOfFloat, 0, 1);
/*    */   }
/*    */ 
/*    */   public abstract void backtransform(float[] paramArrayOfFloat, int paramInt1, int paramInt2);
/*    */ 
/*    */   public float normalization()
/*    */   {
/* 77 */     return (1.0F / this.n);
/*    */   }
/*    */ 
/*    */   public void inverse(float[] paramArrayOfFloat) {
/* 81 */     inverse(paramArrayOfFloat, 0, 1);
/*    */   }
/*    */ 
/*    */   public void inverse(float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
/* 85 */     backtransform(paramArrayOfFloat, paramInt1, paramInt2);
/*    */ 
/* 88 */     float f = normalization();
/* 89 */     for (int i = 0; i < this.n; ++i)
/* 90 */       paramArrayOfFloat[(paramInt1 + paramInt2 * i)] *= f;
/*    */   }
/*    */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.audio.math.RealFloatFFT
 * JD-Core Version:    0.5.3
 */