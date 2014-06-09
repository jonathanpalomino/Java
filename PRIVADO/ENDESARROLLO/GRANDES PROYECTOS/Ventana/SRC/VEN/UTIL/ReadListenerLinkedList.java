/*     */ package jm.util;
/*     */ 
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ class ReadListenerLinkedList
/*     */ {
/*     */   private ReadListener listener;
/*     */   private ReadListenerLinkedList next;
/*     */ 
/*     */   public ReadListenerLinkedList(ReadListener paramReadListener)
/*     */   {
/*  47 */     this.listener = paramReadListener;
/*  48 */     this.next = null;
/*     */   }
/*     */ 
/*     */   public ReadListenerLinkedList getNext()
/*     */   {
/*  57 */     return this.next;
/*     */   }
/*     */ 
/*     */   public ReadListener getListener()
/*     */   {
/*  66 */     return this.listener;
/*     */   }
/*     */ 
/*     */   public void add(ReadListener paramReadListener)
/*     */   {
/*  76 */     if (paramReadListener == null) {
/*  77 */       return;
/*     */     }
/*  79 */     if (this.next == null) {
/*  80 */       this.next = new ReadListenerLinkedList(paramReadListener);
/*     */     }
/*  82 */     this.next.add(paramReadListener);
/*     */   }
/*     */ 
/*     */   public void remove(ReadListener paramReadListener)
/*     */   {
/*  91 */     if (this.next == null) {
/*  92 */       return;
/*     */     }
/*  94 */     if (paramReadListener == this.next.getListener())
/*  95 */       this.next = this.next.getNext();
/*     */   }
/*     */ 
/*     */   public Score scoreRead(Score paramScore)
/*     */   {
/* 111 */     if (this.listener == null) {
/* 112 */       return paramScore;
/*     */     }
/* 114 */     if (this.next == null) {
/* 115 */       return this.listener.scoreRead(paramScore);
/*     */     }
/* 117 */     return this.next.scoreRead(this.listener.scoreRead(paramScore));
/*     */   }
/*     */ 
/*     */   public void startedReading() {
/* 121 */     if (this.listener == null) {
/* 122 */       return;
/*     */     }
/* 124 */     if (this.next == null) {
/* 125 */       this.listener.startedReading();
/* 126 */       return;
/*     */     }
/* 128 */     this.listener.startedReading();
/* 129 */     this.next.startedReading();
/*     */   }
/*     */ 
/*     */   public void finishedReading()
/*     */   {
/* 142 */     if (this.listener == null) {
/* 143 */       return;
/*     */     }
/* 145 */     if (this.next == null) {
/* 146 */       this.listener.finishedReading();
/* 147 */       return;
/*     */     }
/* 149 */     this.listener.finishedReading();
/* 150 */     this.next.finishedReading();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadListenerLinkedList
 * JD-Core Version:    0.5.3
 */