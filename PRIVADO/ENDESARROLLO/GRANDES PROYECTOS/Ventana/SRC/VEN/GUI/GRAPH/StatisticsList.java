/*     */ package jm.gui.graph;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.Array;
/*     */ 
/*     */ public class StatisticsList
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private transient Statistics[] elementData;
/*     */   private int size;
/*     */   protected transient int modCount;
/*     */ 
/*     */   public StatisticsList(int paramInt)
/*     */   {
/*  41 */     this.modCount = 0;
/*     */ 
/*  46 */     if (paramInt < 0) {
/*  47 */       throw new IllegalArgumentException("Illegal Capacity: " + paramInt);
/*     */     }
/*     */ 
/*  50 */     this.elementData = new Statistics[paramInt];
/*     */   }
/*     */ 
/*     */   public StatisticsList()
/*     */   {
/*  56 */     this(10);
/*     */   }
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/*  62 */     this.modCount += 1;
/*  63 */     int i = this.elementData.length;
/*  64 */     if (this.size < i) {
/*  65 */       Statistics[] arrayOfStatistics = this.elementData;
/*  66 */       this.elementData = new Statistics[this.size];
/*  67 */       System.arraycopy(arrayOfStatistics, 0, this.elementData, 0, this.size);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/*  74 */     this.modCount += 1;
/*  75 */     int i = this.elementData.length;
/*  76 */     if (paramInt > i) {
/*  77 */       Statistics[] arrayOfStatistics = this.elementData;
/*  78 */       int j = i * 3 / 2 + 1;
/*  79 */       if (j < paramInt) {
/*  80 */         j = paramInt;
/*     */       }
/*  82 */       this.elementData = new Statistics[j];
/*  83 */       System.arraycopy(arrayOfStatistics, 0, this.elementData, 0, this.size);
/*     */     }
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/*  90 */     return this.size;
/*     */   }
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/*  96 */     return (this.size == 0);
/*     */   }
/*     */ 
/*     */   public boolean contains(Statistics paramStatistics)
/*     */   {
/* 102 */     return (indexOf(paramStatistics) >= 0);
/*     */   }
/*     */ 
/*     */   public int indexOf(Statistics paramStatistics)
/*     */   {
/*     */     int i;
/* 108 */     if (paramStatistics == null) {
/* 109 */       for (i = 0; i < this.size; ++i)
/* 110 */         if (this.elementData[i] == null)
/* 111 */           return i;
/*     */     }
/*     */     else {
/* 114 */       for (i = 0; i < this.size; ++i) {
/* 115 */         if (paramStatistics.equals(this.elementData[i])) {
/* 116 */           return i;
/*     */         }
/*     */       }
/*     */     }
/* 120 */     return -1;
/*     */   }
/*     */ 
/*     */   public int lastIndexOf(Statistics paramStatistics)
/*     */   {
/*     */     int i;
/* 126 */     if (paramStatistics == null) {
/* 127 */       for (i = this.size - 1; i >= 0; --i) {
/* 128 */         if (this.elementData[i] == null)
/* 129 */           return i;
/*     */       }
/*     */     }
/*     */     else {
/* 133 */       for (i = this.size - 1; i >= 0; --i) {
/* 134 */         if (paramStatistics.equals(this.elementData[i])) {
/* 135 */           return i;
/*     */         }
/*     */       }
/*     */     }
/* 139 */     return -1;
/*     */   }
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 146 */       StatisticsList localStatisticsList = (StatisticsList)super.clone();
/* 147 */       localStatisticsList.elementData = new Statistics[this.size];
/* 148 */       System.arraycopy(this.elementData, 0, localStatisticsList.elementData, 0, this.size);
/* 149 */       localStatisticsList.modCount = 0;
/* 150 */       return localStatisticsList;
/*     */     }
/*     */     catch (CloneNotSupportedException localCloneNotSupportedException) {
/* 153 */       throw new InternalError();
/*     */     }
/*     */   }
/*     */ 
/*     */   public Statistics[] toArray()
/*     */   {
/* 160 */     Statistics[] arrayOfStatistics = new Statistics[this.size];
/* 161 */     System.arraycopy(this.elementData, 0, arrayOfStatistics, 0, this.size);
/* 162 */     return arrayOfStatistics;
/*     */   }
/*     */ 
/*     */   public Statistics[] toArray(Statistics[] paramArrayOfStatistics)
/*     */   {
/* 168 */     if (paramArrayOfStatistics.length < this.size) {
/* 169 */       paramArrayOfStatistics = (Statistics[])(Statistics[])Array.newInstance(paramArrayOfStatistics.getClass().getComponentType(), this.size);
/*     */     }
/*     */ 
/* 172 */     System.arraycopy(this.elementData, 0, paramArrayOfStatistics, 0, this.size);
/* 173 */     if (paramArrayOfStatistics.length > this.size) {
/* 174 */       paramArrayOfStatistics[this.size] = null;
/*     */     }
/* 176 */     return paramArrayOfStatistics;
/*     */   }
/*     */ 
/*     */   public Statistics get(int paramInt)
/*     */   {
/* 184 */     rangeCheck(paramInt);
/* 185 */     return this.elementData[paramInt];
/*     */   }
/*     */ 
/*     */   public Statistics set(int paramInt, Statistics paramStatistics)
/*     */   {
/* 191 */     rangeCheck(paramInt);
/* 192 */     Statistics localStatistics = this.elementData[paramInt];
/* 193 */     this.elementData[paramInt] = paramStatistics;
/* 194 */     return localStatistics;
/*     */   }
/*     */ 
/*     */   public boolean add(Statistics paramStatistics)
/*     */   {
/* 200 */     ensureCapacity(this.size + 1);
/* 201 */     this.elementData[(this.size++)] = paramStatistics;
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */   public void add(int paramInt, Statistics paramStatistics)
/*     */   {
/* 208 */     if ((paramInt > this.size) || (paramInt < 0)) {
/* 209 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*     */     }
/*     */ 
/* 212 */     ensureCapacity(this.size + 1);
/* 213 */     System.arraycopy(this.elementData, paramInt, this.elementData, paramInt + 1, this.size - paramInt);
/*     */ 
/* 215 */     this.elementData[paramInt] = paramStatistics;
/* 216 */     this.size += 1;
/*     */   }
/*     */ 
/*     */   public Statistics remove(int paramInt)
/*     */   {
/* 222 */     rangeCheck(paramInt);
/* 223 */     this.modCount += 1;
/* 224 */     Statistics localStatistics = this.elementData[paramInt];
/* 225 */     int i = this.size - paramInt - 1;
/* 226 */     if (i > 0) {
/* 227 */       System.arraycopy(this.elementData, paramInt + 1, this.elementData, paramInt, i);
/*     */     }
/*     */ 
/* 230 */     this.elementData[(--this.size)] = null;
/* 231 */     return localStatistics;
/*     */   }
/*     */ 
/*     */   public void clear()
/*     */   {
/* 237 */     this.modCount += 1;
/*     */ 
/* 240 */     for (int i = 0; i < this.size; ++i) {
/* 241 */       this.elementData[i] = null;
/*     */     }
/*     */ 
/* 244 */     this.size = 0;
/*     */   }
/*     */ 
/*     */   protected void removeRange(int paramInt1, int paramInt2)
/*     */   {
/* 250 */     this.modCount += 1;
/* 251 */     int i = this.size - paramInt2;
/* 252 */     System.arraycopy(this.elementData, paramInt2, this.elementData, paramInt1, i);
/*     */ 
/* 256 */     int j = this.size - (paramInt2 - paramInt1);
/* 257 */     while (this.size != j)
/* 258 */       this.elementData[(--this.size)] = null;
/*     */   }
/*     */ 
/*     */   private void rangeCheck(int paramInt)
/*     */   {
/* 265 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 266 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*     */   }
/*     */ 
/*     */   private synchronized void writeObject(ObjectOutputStream paramObjectOutputStream)
/*     */     throws IOException
/*     */   {
/* 276 */     paramObjectOutputStream.defaultWriteObject();
/*     */ 
/* 279 */     paramObjectOutputStream.writeInt(this.elementData.length);
/*     */ 
/* 282 */     for (int i = 0; i < this.size; ++i)
/* 283 */       paramObjectOutputStream.writeObject(this.elementData[i]);
/*     */   }
/*     */ 
/*     */   private synchronized void readObject(ObjectInputStream paramObjectInputStream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 292 */     paramObjectInputStream.defaultReadObject();
/*     */ 
/* 295 */     int i = paramObjectInputStream.readInt();
/* 296 */     this.elementData = new Statistics[i];
/*     */ 
/* 299 */     for (int j = 0; j < this.size; ++j)
/* 300 */       this.elementData[j] = ((Statistics)paramObjectInputStream.readObject());
/*     */   }
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 309 */     if (paramObject == this) {
/* 310 */       return true;
/*     */     }
/* 312 */     if (!(paramObject instanceof StatisticsList)) {
/* 313 */       return false;
/*     */     }
/*     */ 
/* 316 */     StatisticsList localStatisticsList = (StatisticsList)paramObject;
/* 317 */     if (this.size == localStatisticsList.size()) {
/* 318 */       for (int i = 0; i < this.size; ++i) {
/* 319 */         Statistics localStatistics1 = get(i);
/* 320 */         Statistics localStatistics2 = localStatisticsList.get(i);
/* 321 */         if (localStatistics1 == null) if (localStatistics2 != null); else if (!(localStatistics1.equals(localStatistics2))) {
/* 322 */           return false;
/*     */         }
/*     */       }
/* 325 */       return true;
/*     */     }
/* 327 */     return false;
/*     */   }
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 333 */     int i = 1;
/* 334 */     for (int j = 0; j < this.size; ++j) {
/* 335 */       Statistics localStatistics = get(j);
/* 336 */       i = 31 * i + ((localStatistics == null) ? 0 : localStatistics.hashCode());
/*     */     }
/* 338 */     return i;
/*     */   }
/*     */ 
/*     */   public boolean remove(Statistics paramStatistics)
/*     */   {
/*     */     int i;
/* 346 */     if (paramStatistics == null) {
/* 347 */       for (i = 0; i < this.size; ++i)
/* 348 */         if (get(i) == null) {
/* 349 */           remove(i);
/* 350 */           return true;
/*     */         }
/*     */     }
/*     */     else {
/* 354 */       for (i = 0; i < this.size; ++i) {
/* 355 */         if (paramStatistics.equals(get(i))) {
/* 356 */           remove(i);
/* 357 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 361 */     return false;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 369 */     StringBuffer localStringBuffer = new StringBuffer();
/* 370 */     localStringBuffer.append("[");
/* 371 */     int i = size() - 1;
/* 372 */     for (int j = 0; j <= i; ++j) {
/* 373 */       localStringBuffer.append(String.valueOf(get(j)));
/* 374 */       if (j < i)
/* 375 */         localStringBuffer.append(", ");
/*     */     }
/* 377 */     localStringBuffer.append("]");
/* 378 */     return localStringBuffer.toString();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.graph.StatisticsList
 * JD-Core Version:    0.5.3
 */