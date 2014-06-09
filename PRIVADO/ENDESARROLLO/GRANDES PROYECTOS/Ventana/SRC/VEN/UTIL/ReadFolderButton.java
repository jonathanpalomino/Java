/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.Button;
/*     */ import java.awt.FileDialog;
/*     */ import java.awt.Frame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import java.io.FilenameFilter;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ReadFolderButton extends Button
/*     */ {
/*     */   private ReadListenerLinkedList readListenerList;
/*     */ 
/*     */   public ReadFolderButton(Frame paramFrame)
/*     */   {
/*  59 */     super("Read Folder");
/*  60 */     FileDialog localFileDialog = new FileDialog(paramFrame, "Select a file to read all Midi and JMusic within that file's folder", 0);
/*     */ 
/*  63 */     ReadFilenameFilter localReadFilenameFilter = new ReadFilenameFilter();
/*  64 */     localFileDialog.setFilenameFilter(localReadFilenameFilter);
/*     */ 
/*  66 */     addActionListener(new ActionListener(localFileDialog, localReadFilenameFilter, paramFrame) { private final FileDialog val$load;
/*     */       private final FilenameFilter val$filter;
/*     */       private final Frame val$owner;
/*     */ 
/*     */       public void actionPerformed(ActionEvent paramActionEvent) { this.val$load.show();
/*     */ 
/*  70 */         String str = this.val$load.getDirectory();
/*  71 */         if (str == null) {
/*  72 */           return;
/*     */         }
/*  74 */         String[] arrayOfString = new File(str).list(this.val$filter);
/*  75 */         for (int i = 0; i < arrayOfString.length; ++i) {
/*  76 */           Score localScore = Read.midiOrJmWithAWTMessaging(str, arrayOfString[i], this.val$owner);
/*     */ 
/*  79 */           if ((localScore != null) && (ReadFolderButton.this.readListenerList != null)) {
/*  80 */             ReadFolderButton.this.readListenerList.scoreRead(localScore);
/*     */           }
/*     */         }
/*  83 */         if (ReadFolderButton.this.readListenerList != null)
/*  84 */           ReadFolderButton.this.readListenerList.finishedReading();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   public void addReadListener(ReadListener paramReadListener)
/*     */   {
/*  99 */     if (paramReadListener == null) {
/* 100 */       return;
/*     */     }
/* 102 */     if (this.readListenerList == null)
/* 103 */       this.readListenerList = new ReadListenerLinkedList(paramReadListener);
/*     */     else
/* 105 */       this.readListenerList.add(paramReadListener);
/*     */   }
/*     */ 
/*     */   public void removeReadListener(ReadListener paramReadListener)
/*     */   {
/* 117 */     if (this.readListenerList == null) {
/* 118 */       return;
/*     */     }
/* 120 */     if (this.readListenerList.getListener() == paramReadListener)
/* 121 */       this.readListenerList = this.readListenerList.getNext();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadFolderButton
 * JD-Core Version:    0.5.3
 */