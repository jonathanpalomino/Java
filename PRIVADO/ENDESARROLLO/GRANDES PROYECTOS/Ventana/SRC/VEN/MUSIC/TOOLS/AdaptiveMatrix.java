/*     */ package jm.music.tools;
/*     */ 
/*     */ import I;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ public final class AdaptiveMatrix
/*     */ {
/*     */   private int depth;
/*     */   private Hashtable weightMatrix;
/*     */   private Hashtable countMatrix;
/*     */   private int indexRange;
/*     */ 
/*     */   public AdaptiveMatrix(int[] paramArrayOfInt, int paramInt1, int paramInt2)
/*     */   {
/* 103 */     this.countMatrix = new Hashtable();
/* 104 */     this.weightMatrix = new Hashtable();
/* 105 */     this.depth = paramInt1;
/* 106 */     this.indexRange = paramInt2;
/* 107 */     calcCount(paramArrayOfInt);
/* 108 */     calcWeight();
/*     */   }
/*     */ 
/*     */   public AdaptiveMatrix(String paramString)
/*     */   {
/* 120 */     read(paramString);
/*     */   }
/*     */ 
/*     */   public void update(int[] paramArrayOfInt)
/*     */   {
/* 131 */     calcCount(paramArrayOfInt);
/* 132 */     calcWeight();
/*     */   }
/*     */ 
/*     */   public int[] generate(int paramInt, int[] paramArrayOfInt)
/*     */   {
/* 145 */     if (paramArrayOfInt.length != this.depth) {
/* 146 */       System.err.println("[WARNING] Wrong seed length for this Matrix depth");
/* 147 */       return null;
/*     */     }
/* 149 */     int[] arrayOfInt1 = new int[paramInt];
/* 150 */     Object localObject1 = "";
/* 151 */     int[] arrayOfInt2 = new int[paramArrayOfInt.length];
/*     */ 
/* 153 */     for (int i = 0; i < paramArrayOfInt.length; ++i) {
/* 154 */       arrayOfInt1[i] = paramArrayOfInt[i];
/* 155 */       arrayOfInt2[i] = paramArrayOfInt[i];
/* 156 */       localObject1 = ((String)localObject1) + paramArrayOfInt[i] + " ";
/*     */     }
/* 158 */     Object localObject2 = localObject1;
/*     */ 
/* 160 */     if (!(this.weightMatrix.containsKey(localObject1))) {
/* 161 */       System.err.println("[WARNING] This seed is unavailable .. try another");
/* 162 */       return null;
/*     */     }
/*     */ 
/* 165 */     for (int j = paramArrayOfInt.length; j < arrayOfInt1.length; ++j) {
/* 166 */       if (!(this.weightMatrix.containsKey(localObject1))) {
/* 167 */         localObject1 = localObject2;
/* 168 */         paramArrayOfInt = arrayOfInt2;
/*     */       }
/* 170 */       double[] arrayOfDouble = (double[])(double[])this.weightMatrix.get(localObject1);
/* 171 */       localObject1 = "";
/* 172 */       for (int k = 1; k < paramArrayOfInt.length; ++k) {
/* 173 */         localObject1 = ((String)localObject1) + paramArrayOfInt[k] + " ";
/* 174 */         paramArrayOfInt[(k - 1)] = paramArrayOfInt[k];
/*     */       }
/* 176 */       double d1 = Math.random();
/* 177 */       double d2 = 0.0D;
/* 178 */       for (int l = 0; l < arrayOfDouble.length; ++l) {
/* 179 */         d2 += arrayOfDouble[l];
/* 180 */         if (d2 > d1) {
/* 181 */           arrayOfInt1[j] = l;
/* 182 */           localObject1 = ((String)localObject1) + l + " ";
/* 183 */           paramArrayOfInt[(this.depth - 1)] = l;
/* 184 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 188 */     return ((I)arrayOfInt1);
/*     */   }
/*     */ 
/*     */   public void read(String paramString)
/*     */   {
/* 195 */     AdaptiveMatrix localAdaptiveMatrix = null;
/*     */     try {
/* 197 */       File localFile = new File(paramString);
/* 198 */       FileInputStream localFileInputStream = new FileInputStream(localFile);
/* 199 */       ObjectInputStream localObjectInputStream = new ObjectInputStream(localFileInputStream);
/* 200 */       localAdaptiveMatrix = (AdaptiveMatrix)localObjectInputStream.readObject();
/*     */     } catch (Exception localException) {
/* 202 */       localException.printStackTrace();
/*     */     }
/* 204 */     this.depth = localAdaptiveMatrix.getDepth();
/* 205 */     this.indexRange = localAdaptiveMatrix.getIndexRange();
/* 206 */     this.countMatrix = localAdaptiveMatrix.getCountMatrix();
/* 207 */     this.weightMatrix = localAdaptiveMatrix.getWeightMatrix();
/*     */   }
/*     */ 
/*     */   public void write(String paramString)
/*     */   {
/*     */     try
/*     */     {
/* 215 */       File localFile = new File(paramString);
/* 216 */       FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
/* 217 */       ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localFileOutputStream);
/* 218 */       localObjectOutputStream.writeObject(this);
/*     */     } catch (Exception localException) {
/* 220 */       localException.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void print()
/*     */   {
/* 229 */     System.out.println();
/* 230 */     System.out.println("MATRIX");
/* 231 */     System.out.println("----------------");
/* 232 */     Enumeration localEnumeration = this.weightMatrix.keys();
/* 233 */     while (localEnumeration.hasMoreElements()) {
/* 234 */       String str = (String)localEnumeration.nextElement();
/* 235 */       double[] arrayOfDouble = (double[])(double[])this.weightMatrix.get(str);
/* 236 */       System.out.print(str + "\t: ");
/* 237 */       for (int i = 0; i < arrayOfDouble.length; ++i) {
/* 238 */         System.out.print(" " + arrayOfDouble[i]);
/*     */       }
/* 240 */       System.out.println();
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getDepth()
/*     */   {
/* 249 */     return this.depth;
/*     */   }
/*     */ 
/*     */   public Hashtable getWeightMatrix()
/*     */   {
/* 257 */     return this.weightMatrix;
/*     */   }
/*     */ 
/*     */   public Hashtable getCountMatrix()
/*     */   {
/* 265 */     return this.countMatrix;
/*     */   }
/*     */ 
/*     */   public int getIndexRange()
/*     */   {
/* 273 */     return this.indexRange;
/*     */   }
/*     */ 
/*     */   private void calcCount(int[] paramArrayOfInt)
/*     */   {
/* 293 */     for (int i = this.depth - 1; i < paramArrayOfInt.length - 1; ++i) {
/* 294 */       String str = "";
/* 295 */       int[] arrayOfInt1 = new int[this.indexRange];
/* 296 */       int j = 0; for (int k = this.depth - 1; j < this.depth; --k) {
/* 297 */         str = str + paramArrayOfInt[(i - k)] + " ";
/*     */ 
/* 296 */         ++j;
/*     */       }
/*     */ 
/* 299 */       if (this.countMatrix.containsKey(str)) {
/* 300 */         int[] arrayOfInt2 = (int[])(int[])this.countMatrix.get(str);
/* 301 */         arrayOfInt2[paramArrayOfInt[(i + 1)]] += 1;
/* 302 */         this.countMatrix.put(str, arrayOfInt2);
/*     */       } else {
/* 304 */         arrayOfInt1[paramArrayOfInt[(i + 1)]] += 1;
/* 305 */         this.countMatrix.put(str, arrayOfInt1);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void calcWeight()
/*     */   {
/* 317 */     Enumeration localEnumeration = this.countMatrix.keys();
/* 318 */     while (localEnumeration.hasMoreElements()) {
/* 319 */       String str = (String)localEnumeration.nextElement();
/* 320 */       int[] arrayOfInt = (int[])(int[])this.countMatrix.get(str);
/* 321 */       int i = 0;
/* 322 */       for (int j = 0; j < arrayOfInt.length; ++j) {
/* 323 */         i += arrayOfInt[j];
/*     */       }
/* 325 */       double[] arrayOfDouble = new double[this.indexRange];
/* 326 */       for (int k = 0; k < arrayOfInt.length; ++k) {
/* 327 */         arrayOfDouble[k] = (arrayOfInt[k] / i);
/*     */       }
/* 329 */       this.weightMatrix.put(str, arrayOfDouble);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.tools.AdaptiveMatrix
 * JD-Core Version:    0.5.3
 */