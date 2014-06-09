/*     */ package jm.gui.graph;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class Statistics
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private double[] elementData;
/*     */   private double largestValue;
/*     */   private int size;
/*     */ 
/*     */   public Statistics(int paramInt)
/*     */   {
/*  37 */     this.largestValue = 0.0D;
/*     */ 
/*  46 */     if (paramInt < 0) {
/*  47 */       throw new IllegalArgumentException("Illegal Capacity: " + paramInt);
/*     */     }
/*     */ 
/*  50 */     this.elementData = new double[paramInt];
/*     */   }
/*     */ 
/*     */   public Statistics()
/*     */   {
/*  56 */     this(100);
/*     */   }
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/*  62 */     int i = this.elementData.length;
/*  63 */     if (this.size < i) {
/*  64 */       double[] arrayOfDouble = this.elementData;
/*  65 */       this.elementData = new double[this.size];
/*  66 */       System.arraycopy(arrayOfDouble, 0, this.elementData, 0, this.size);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/*  73 */     int i = this.elementData.length;
/*  74 */     if (paramInt > i) {
/*  75 */       double[] arrayOfDouble = this.elementData;
/*  76 */       int j = i * 3 / 2 + 1;
/*  77 */       if (j < paramInt) {
/*  78 */         j = paramInt;
/*     */       }
/*  80 */       this.elementData = new double[j];
/*  81 */       System.arraycopy(arrayOfDouble, 0, this.elementData, 0, this.size);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  88 */     return this.size;
/*     */   }
/*     */ 
/*     */   public double largestValue()
/*     */   {
/*  94 */     return this.largestValue;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 101 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */   public boolean contains(double paramDouble)
/*     */   {
/* 107 */     return (indexOf(paramDouble) >= 0);
/*     */   }
/*     */ 
/*     */   public int indexOf(double paramDouble)
/*     */   {
/* 113 */     for (int i = 0; i < this.size; ++i) {
/* 114 */       if (paramDouble == this.elementData[i]) {
/* 115 */         return i;
/*     */       }
/*     */     }
/* 118 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(double paramDouble)
/*     */   {
/* 124 */     for (int i = this.size - 1; i >= 0; --i) {
/* 125 */       if (paramDouble == this.elementData[i]) {
/* 126 */         return i;
/*     */       }
/*     */     }
/* 129 */     return -1;
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 136 */       Statistics localStatistics = (Statistics)super.clone();
/* 137 */       localStatistics.elementData = new double[this.size];
/* 138 */       System.arraycopy(this.elementData, 0, localStatistics.elementData, 0, this.size);
/* 139 */       return localStatistics;
/*     */     }
/*     */     catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 142 */       throw new InternalError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public double[] toArray()
/*     */   {
/* 149 */     double[] arrayOfDouble = new double[this.size];
/* 150 */     System.arraycopy(this.elementData, 0, arrayOfDouble, 0, this.size);
/* 151 */     return arrayOfDouble;
/*     */   }
/*     */ 
/*     */   public double[] toArray(double[] paramArrayOfDouble)
/*     */   {
/* 157 */     if (paramArrayOfDouble.length < this.size) {
/* 158 */       paramArrayOfDouble = new double[this.size];
/*     */     }
/* 160 */     System.arraycopy(this.elementData, 0, paramArrayOfDouble, 0, this.size);
/* 161 */     if (paramArrayOfDouble.length > this.size) {
/* 162 */       paramArrayOfDouble[this.size] = 0.0D;
/*     */     }
/* 164 */     return paramArrayOfDouble;
/*     */   }
/*     */ 
/*     */   public double resetLargestValue()
/*     */   {
/* 170 */     this.largestValue = 0.0D;
/* 171 */     for (int i = 0; i < this.size; ++i) {
/* 172 */       if (get(i) > this.largestValue) {
/* 173 */         this.largestValue = get(i);
/*     */       }
/*     */     }
/* 176 */     return this.largestValue;
/*     */   }
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/* 182 */     rangeCheck(paramInt);
/* 183 */     return this.elementData[paramInt];
/*     */   }
/*     */ 
/*     */   public double set(int paramInt, double paramDouble)
/*     */   {
/* 189 */     rangeCheck(paramInt);
/* 190 */     double d = this.elementData[paramInt];
/* 191 */     this.elementData[paramInt] = paramDouble;
/* 192 */     if (d == this.largestValue)
/* 193 */       resetLargestValue();
/* 194 */     else if (paramDouble > this.largestValue) {
/* 195 */       this.largestValue = d;
/*     */     }
/* 197 */     return d;
/*     */   }
/*     */ 
/*     */   public boolean add(double paramDouble)
/*     */   {
/* 203 */     ensureCapacity(this.size + 1);
/* 204 */     this.elementData[(this.size++)] = paramDouble;
/* 205 */     if (paramDouble > this.largestValue) {
/* 206 */       this.largestValue = paramDouble;
/*     */     }
/* 208 */     return true;
/*     */   }
/*     */ 
/*     */   public boolean add(double[] paramArrayOfDouble)
/*     */   {
/* 214 */     ensureCapacity(this.size + paramArrayOfDouble.length);
/* 215 */     for (int i = 0; i < paramArrayOfDouble.length; ++i) {
/* 216 */       this.elementData[(this.size++)] = paramArrayOfDouble[i];
/* 217 */       if (paramArrayOfDouble[i] > this.largestValue) {
/* 218 */         this.largestValue = paramArrayOfDouble[i];
/*     */       }
/*     */     }
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   public void add(int paramInt, double paramDouble)
/*     */   {
/* 227 */     if ((paramInt > this.size) || (paramInt < 0)) {
/* 228 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*     */     }
/*     */ 
/* 231 */     ensureCapacity(this.size + 1);
/* 232 */     System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + 1, this.size - paramInt);
/*     */ 
/* 234 */     this.elementData[paramInt] = paramDouble;
/* 235 */     this.size += 1;
/* 236 */     if (paramDouble > this.largestValue)
/* 237 */       this.largestValue = paramDouble;
/*     */   }
/*     */ 
/*     */   public double removeIndex(int paramInt)
/*     */   {
/* 244 */     rangeCheck(paramInt);
/* 245 */     double d = this.elementData[paramInt];
/* 246 */     int i = this.size - paramInt - 1;
/* 247 */     if (i > 0) {
/* 248 */       System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
/*     */     }
/*     */ 
/* 251 */     this.elementData[(--this.size)] = 0.0D;
/* 252 */     return d;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 259 */     for (int i = 0; i < this.size; ++i) {
/* 260 */       this.elementData[i] = 0.0D;
/*     */     }
/*     */ 
/* 263 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   protected void removeRange(int paramInt1, int paramInt2)
/*     */   {
/* 269 */     int i = this.size - paramInt2;
/* 270 */     System.arraycopy(this.elementData, paramInt2, this.elementData, paramInt1, i);
/*     */ 
/* 274 */     int j = this.size - (paramInt2 - paramInt1);
/* 275 */     while (this.size != j)
/* 276 */       this.elementData[(--this.size)] = 0.0D;
/*     */   }
/*     */ 
/*     */   private void rangeCheck(int paramInt)
/*     */   {
/* 283 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 284 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*     */   }
/*     */ 
/*     */   private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
/*     */     throws IOException
/*     */   {
/* 294 */     paramObjectOutputStream.defaultWriteObject();
/*     */ 
/* 297 */     paramObjectOutputStream.writeInt(this.elementData.length);
/*     */ 
/* 300 */     for (int i = 0; i < this.size; ++i)
/* 301 */       paramObjectOutputStream.writeDouble(this.elementData[i]);
/*     */   }
/*     */ 
/*     */   private synchronized void readObject(ObjectInputStream paramObjectInputStream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 310 */     paramObjectInputStream.defaultReadObject();
/*     */ 
/* 313 */     int i = paramObjectInputStream.readInt();
/* 314 */     this.elementData = new double[i];
/*     */ 
/* 317 */     for (int j = 0; j < this.size; ++j)
/* 318 */       this.elementData[j] = paramObjectInputStream.readDouble();
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 327 */     if (paramObject == this) {
/* 328 */       return true;
/*     */     }
/* 330 */     if (!(paramObject instanceof Statistics)) {
/* 331 */       return false;
/*     */     }
/*     */ 
/* 334 */     Statistics localStatistics = (Statistics)paramObject;
/* 335 */     if (this.size == localStatistics.size()) {
/* 336 */       for (int i = 0; i < this.size; ++i) {
/* 337 */         double d1 = get(i);
/* 338 */         double d2 = localStatistics.get(i);
/* 339 */         if (d1 != d2) {
/* 340 */           return false;
/*     */         }
/*     */       }
/* 343 */       return true;
/*     */     }
/* 345 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean removeValue(double paramDouble)
/*     */   {
/* 353 */     for (int i = 0; i < this.size; ++i) {
/* 354 */       if (paramDouble == get(i)) {
/* 355 */         removeIndex(i);
/* 356 */         return true;
/*     */       }
/*     */     }
/* 359 */     return false;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 367 */     StringBuffer localStringBuffer = new StringBuffer();
/* 368 */     localStringBuffer.append("[");
/* 369 */     int i = size() - 1;
/* 370 */     for (int j = 0; j <= i; ++j) {
/* 371 */       localStringBuffer.append(String.valueOf(get(j)));
/* 372 */       if (j < i)
/* 373 */         localStringBuffer.append(", ");
/*     */     }
/* 375 */     localStringBuffer.append("]");
/* 376 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.graph.Statistics
 * JD-Core Version:    0.5.3
 */