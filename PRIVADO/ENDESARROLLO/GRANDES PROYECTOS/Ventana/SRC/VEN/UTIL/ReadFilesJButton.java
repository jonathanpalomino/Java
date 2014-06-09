/*     */ package jm.util;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.File;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFileChooser;
/*     */ import jm.music.data.Score;
/*     */ 
/*     */ public class ReadFilesJButton extends JButton
/*     */ {
/*  55 */   public static final Mode SINGLE_FILE_MODE = new Mode("Single File", null);
/*     */ 
/*  61 */   public static final Mode MULTIPLE_FILES_MODE = new Mode("Multiple Files", null);
/*     */ 
/*  67 */   public static final Mode FOLDER_MODE = new Mode("Folder", null);
/*     */   private Mode mode;
/*     */   private ReadListenerLinkedList readListenerList;
/*     */   private JFileChooser chooser;
/*     */   private Component owner;
/*     */ 
/*     */   public ReadFilesJButton(Component paramComponent)
/*     */   {
/* 121 */     this(paramComponent, MULTIPLE_FILES_MODE);
/*     */   }
/*     */ 
/*     */   public ReadFilesJButton(Component paramComponent, Mode paramMode)
/*     */   {
/* 103 */     this.chooser = new JFileChooser();
/*     */ 
/* 135 */     this.owner = paramComponent;
/* 136 */     setMode(paramMode);
/* 137 */     addActionListener(new ActionListener(paramComponent, paramMode) { private final Component val$owner;
/*     */       private final ReadFilesJButton.Mode val$mode;
/*     */ 
/*     */       public void actionPerformed(ActionEvent paramActionEvent) { 1 local1 = new Runnable() {
/*     */           public void run() {
/* 141 */             ReadFilesJButton.this.readListenerList.startedReading();
/*     */ 
/* 143 */             int i = ReadFilesJButton.this.chooser.showOpenDialog(ReadFilesJButton.1.this.val$owner);
/* 144 */             if (i != 0) {
/* 145 */               return;
/*     */             }
/*     */ 
/* 148 */             if (ReadFilesJButton.1.this.val$mode == ReadFilesJButton.SINGLE_FILE_MODE) {
/* 149 */               ReadFilesJButton.this.processFile(ReadFilesJButton.this.chooser.getSelectedFile());
/* 150 */             } else if (ReadFilesJButton.1.this.val$mode == ReadFilesJButton.MULTIPLE_FILES_MODE) {
/* 151 */               ReadFilesJButton.this.processFiles(ReadFilesJButton.this.chooser.getSelectedFiles());
/* 152 */             } else if (ReadFilesJButton.1.this.val$mode == ReadFilesJButton.FOLDER_MODE) {
/* 153 */               File localFile = ReadFilesJButton.this.chooser.getSelectedFile();
/* 154 */               if (localFile.isDirectory())
/*     */               {
/* 162 */                 String[] arrayOfString = localFile.list(new ReadFilenameFilter());
/*     */ 
/* 164 */                 for (int j = 0; j < arrayOfString.length; ++j) {
/* 165 */                   ReadFilesJButton.this.processFile(new File(localFile.getAbsolutePath(), arrayOfString[j]));
/*     */                 }
/*     */               }
/*     */ 
/*     */             }
/*     */ 
/* 171 */             if (ReadFilesJButton.this.readListenerList != null)
/* 172 */               ReadFilesJButton.this.readListenerList.finishedReading();
/*     */           }
/*     */         };
/* 176 */         Thread localThread = new Thread(local1, "processThread");
/* 177 */         localThread.start();
/*     */       }
/*     */     });
/*     */   }
/*     */ 
/*     */   private void processFile(File paramFile)
/*     */   {
/* 195 */     Score localScore = Read.midiOrJmWithSwingMessaging(paramFile, this.owner);
/* 196 */     if (localScore == null) {
/* 197 */       return;
/*     */     }
/* 199 */     if (this.readListenerList != null)
/* 200 */       localScore = this.readListenerList.scoreRead(localScore);
/*     */   }
/*     */ 
/*     */   private void processFiles(File[] paramArrayOfFile)
/*     */   {
/* 214 */     if (paramArrayOfFile == null) {
/* 215 */       return;
/*     */     }
/* 217 */     for (int i = 0; i < paramArrayOfFile.length; ++i)
/* 218 */       processFile(paramArrayOfFile[i]);
/*     */   }
/*     */ 
/*     */   public void setMode(Mode paramMode)
/*     */   {
/* 230 */     this.mode = paramMode;
/* 231 */     if (paramMode == SINGLE_FILE_MODE) {
/* 232 */       setText("Read File");
/* 233 */       this.chooser.setDialogTitle("Select a MIDI or jMusic file to import");
/* 234 */       this.chooser.setMultiSelectionEnabled(false);
/* 235 */       this.chooser.setFileSelectionMode(0);
/* 236 */     } else if (paramMode == MULTIPLE_FILES_MODE) {
/* 237 */       setText("Read Files");
/* 238 */       this.chooser.setDialogTitle("Select MIDI and/or jMusic files to import");
/* 239 */       this.chooser.setMultiSelectionEnabled(true);
/* 240 */       this.chooser.setFileSelectionMode(0);
/* 241 */     } else if (paramMode == FOLDER_MODE) {
/* 242 */       setText("Read Folder");
/* 243 */       this.chooser.setDialogTitle("Select a folder of MIDI or jMusic files to import");
/*     */ 
/* 245 */       this.chooser.setMultiSelectionEnabled(false);
/* 246 */       this.chooser.setFileSelectionMode(1);
/*     */     }
/*     */   }
/*     */ 
/*     */   public Mode getMode()
/*     */   {
/* 258 */     return this.mode;
/*     */   }
/*     */ 
/*     */   public void addReadListener(ReadListener paramReadListener)
/*     */   {
/* 269 */     if (paramReadListener == null) {
/* 270 */       return;
/*     */     }
/* 272 */     if (this.readListenerList == null)
/* 273 */       this.readListenerList = new ReadListenerLinkedList(paramReadListener);
/*     */     else
/* 275 */       this.readListenerList.add(paramReadListener);
/*     */   }
/*     */ 
/*     */   public void removeReadListener(ReadListener paramReadListener)
/*     */   {
/* 287 */     if (this.readListenerList == null) {
/* 288 */       return;
/*     */     }
/* 290 */     if (this.readListenerList.getListener() == paramReadListener)
/* 291 */       this.readListenerList = this.readListenerList.getNext();
/*     */   }
/*     */ 
/*     */   private static class Mode
/*     */   {
/*     */     private final String name;
/*     */ 
/*     */     private Mode(String paramString)
/*     */     {
/*  89 */       this.name = paramString;
/*     */     }
/*     */ 
/*     */     Mode(String paramString, ReadFilesJButton.1 param1)
/*     */     {
/*  74 */       this(paramString);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.ReadFilesJButton
 * JD-Core Version:    0.5.3
 */