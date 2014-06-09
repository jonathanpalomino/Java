/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ReadFileButton extends Button
/*     */ {
/*     */   private ReadListenerLinkedList readListenerList;
/*     */ 
/*     */   public ReadFileButton(Frame paramFrame)
/*     */   {
/*  59 */     super("Read File");
/*  60 */     FileDialog localFileDialog = new FileDialog(paramFrame, "Select a Midi or jMusic file to import", 0);
/*     */ 
/*  63 */     localFileDialog.setFilenameFilter(new ReadFilenameFilter());
/*     */ 
/*  65 */     addActionListener(new ActionListener(localFileDialog, paramFrame) { private final FileDialog val$load;
/*     */       private final Frame val$owner;
/*     */ 
/*     */       public void actionPerformed(ActionEvent paramActionEvent) { this.val$load.show();
/*     */ 
/*  69 */         Score localScore = Read.midiOrJmWithAWTMessaging(this.val$load.getDirectory(), this.val$load.getFile(), this.val$owner);
/*     */ 
/*  72 */         if (localScore == null) {
/*  73 */           return;
/*     */         }
/*  75 */         if (ReadFileButton.this.readListenerList != null) {
/*  76 */           localScore = ReadFileButton.this.readListenerList.scoreRead(localScore);
/*  77 */           ReadFileButton.this.readListenerList.finishedReading();
/*     */         }
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public void addReadListener(ReadListener paramReadListener)
/*     */   {
/*  92 */     if (paramReadListener == null) {
/*  93 */       return;
/*     */     }
/*  95 */     if (this.readListenerList == null)
/*  96 */       this.readListenerList = new ReadListenerLinkedList(paramReadListener);
/*     */     else
/*  98 */       this.readListenerList.add(paramReadListener);
/*     */   }
/*     */ 
/*     */   public void removeReadListener(ReadListener paramReadListener)
/*     */   {
/* 110 */     if (this.readListenerList == null) {
/* 111 */       return;
/*     */     }
/* 113 */     if (this.readListenerList.getListener() == paramReadListener)
/* 114 */       this.readListenerList = this.readListenerList.getNext();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadFileButton
 * JD-Core Version:    0.5.3
 */