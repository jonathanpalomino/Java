/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Frame;
/*     */ import java.awt.MenuItem;
/*     */ import java.awt.PopupMenu;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.KeyEvent;
/*     */ import java.awt.event.KeyListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class StaveActionHandler
/*     */   implements JMC, MouseListener, MouseMotionListener, ActionListener, KeyListener
/*     */ {
/*     */   private Stave theApp;
/*  36 */   private int selectedNote = -1;
/*  37 */   private boolean topTimeSelected = false; private boolean keySelected = false;
/*     */   private int clickedPosY;
/*     */   private int clickedPosX;
/*  38 */   private int storedPitch = 72;
/*  39 */   private double[] rhythmValues = { 104.0D, 103.0D, 102.0D, 101.5D, 101.0D, 100.75D, 100.5D, 100.25D, 0.0D, 0.25D, 0.5D, 0.75D, 1.0D, 1.5D, 2.0D, 3.0D, 4.0D };
/*     */ 
/*  41 */   private boolean button1Down = false;
/*     */   private PopupMenu noteContextMenu;
/*     */   private MenuItem editNote;
/*     */   private MenuItem repeatNote;
/*     */   private MenuItem makeRest;
/*     */   private MenuItem deleteNote;
/*     */ 
/*     */   StaveActionHandler(Stave paramStave)
/*     */   {
/*  53 */     this.theApp = paramStave;
/*     */ 
/*  55 */     this.noteContextMenu = new PopupMenu();
/*     */ 
/*  57 */     this.editNote = new MenuItem("Edit Note");
/*  58 */     this.editNote.addActionListener(this);
/*  59 */     this.noteContextMenu.add(this.editNote);
/*     */ 
/*  61 */     this.repeatNote = new MenuItem("Repeat Note");
/*  62 */     this.repeatNote.addActionListener(this);
/*  63 */     this.noteContextMenu.add(this.repeatNote);
/*     */ 
/*  65 */     this.makeRest = new MenuItem("Change to Rest");
/*  66 */     this.makeRest.addActionListener(this);
/*  67 */     this.noteContextMenu.add(this.makeRest);
/*     */ 
/*  69 */     this.deleteNote = new MenuItem("Delete Note");
/*  70 */     this.deleteNote.addActionListener(this);
/*  71 */     this.noteContextMenu.add(this.deleteNote);
/*     */ 
/*  73 */     this.theApp.add(this.noteContextMenu);
/*     */   }
/*     */ 
/*     */   boolean inNoteArea(MouseEvent paramMouseEvent)
/*     */   {
/*     */     Integer localInteger;
/*  78 */     if (this.theApp.notePositions.size() < 2)
/*  79 */       localInteger = new Integer(this.theApp.getTotalBeatWidth());
/*     */     else {
/*  81 */       localInteger = (Integer)this.theApp.notePositions.elementAt(this.theApp.notePositions.size() - 2);
/*     */     }
/*  83 */     return ((paramMouseEvent.getX() <= localInteger.intValue() + 15) && (paramMouseEvent.getX() < this.theApp.getTotalBeatWidth() + 50));
/*     */   }
/*     */ 
/*     */   private void searchForSelectedNote(MouseEvent paramMouseEvent)
/*     */   {
/*  90 */     for (int i = 0; i < this.theApp.notePositions.size(); i += 2) {
/*  91 */       Integer localInteger1 = (Integer)this.theApp.notePositions.elementAt(i);
/*  92 */       Integer localInteger2 = (Integer)this.theApp.notePositions.elementAt(i + 1);
/*  93 */       if ((paramMouseEvent.getX() <= localInteger1.intValue()) || (paramMouseEvent.getX() >= localInteger1.intValue() + 15) || (paramMouseEvent.getY() + this.theApp.staveDelta <= localInteger2.intValue() + 22) || (paramMouseEvent.getY() + this.theApp.staveDelta >= localInteger2.intValue() + 35))
/*     */       {
/*     */         continue;
/*     */       }
/*     */ 
/*  98 */       this.selectedNote = (i / 2);
/*  99 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 100 */       this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 102 */       i = this.theApp.notePositions.size();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent)
/*     */   {
/* 109 */     if (((paramMouseEvent.getModifiers() & 0x8) == 0) || (!(inNoteArea(paramMouseEvent))))
/*     */       return;
/* 111 */     searchForSelectedNote(paramMouseEvent);
/* 112 */     if ((this.selectedNote < 0) || (this.selectedNote >= this.theApp.getPhrase().size()))
/*     */       return;
/* 114 */     this.noteContextMenu.show(this.theApp, paramMouseEvent.getX(), paramMouseEvent.getY()); }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent) {
/* 125 */     if ((paramMouseEvent.isPopupTrigger()) || ((paramMouseEvent.getModifiers() & 0x8) != 0) || (!(this.theApp.editable)))
/*     */     {
/* 127 */       return; }
/* 128 */     this.button1Down = true;
/*     */     Object localObject1;
/*     */     Object localObject2;
/* 131 */     if (!(inNoteArea(paramMouseEvent)))
/*     */     {
/* 133 */       i = 85 - ((paramMouseEvent.getY() + this.theApp.staveDelta - this.theApp.bPos) / 2);
/*     */ 
/* 135 */       localObject1 = new int[] { 1, 3, 6, 8, 10 };
/* 136 */       int k = 1;
/* 137 */       for (int i1 = 0; i1 < localObject1.length; ++i1) {
/* 138 */         if (i % 12 != localObject1[i1]) continue; --i;
/*     */       }
/* 140 */       Note localNote1 = new Note(i, 1.0D);
/* 141 */       localObject2 = this.theApp.getPhrase();
/* 142 */       ((Phrase)localObject2).addNote(localNote1);
/* 143 */       this.theApp.repaint();
/*     */ 
/* 145 */       this.theApp.setCursor(new Cursor(12));
/*     */ 
/* 147 */       this.selectedNote = (((Phrase)localObject2).size() - 1);
/*     */ 
/* 149 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 150 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     } else {
/* 152 */       searchForSelectedNote(paramMouseEvent);
/* 153 */       this.theApp.setCursor(new Cursor(13));
/*     */     }
/*     */ 
/* 156 */     if (this.selectedNote < 0)
/*     */     {
/* 158 */       for (i = 0; i < this.theApp.notePositions.size() - 2; i += 2) {
/* 159 */         localObject1 = (Integer)this.theApp.notePositions.elementAt(i);
/* 160 */         Integer localInteger = (Integer)this.theApp.notePositions.elementAt(i + 2);
/* 161 */         if ((paramMouseEvent.getX() <= ((Integer)localObject1).intValue() + 15) || (paramMouseEvent.getX() >= localInteger.intValue()))
/*     */           continue;
/* 163 */         this.theApp.setCursor(new Cursor(12));
/*     */ 
/* 165 */         int i2 = 85 - ((paramMouseEvent.getY() + this.theApp.staveDelta - this.theApp.bPos) / 2);
/*     */ 
/* 167 */         localObject2 = new int[] { 1, 3, 6, 8, 10 };
/* 168 */         int i3 = 1;
/* 169 */         for (int i4 = 0; i4 < localObject2.length; ++i4) {
/* 170 */           if (i2 % 12 != localObject2[i4]) continue; --i2;
/*     */         }
/* 172 */         Note localNote2 = new Note(i2, 1.0D);
/* 173 */         Phrase localPhrase = this.theApp.getPhrase();
/* 174 */         localPhrase.getNoteList().insertElementAt(localNote2, i / 2 + 1);
/* 175 */         this.theApp.repaint();
/*     */ 
/* 177 */         this.selectedNote = (i / 2 + 1);
/* 178 */         this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 179 */         this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 181 */         i = this.theApp.notePositions.size();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 186 */     int i = this.theApp.rightMargin + this.theApp.clefWidth + this.theApp.keySigWidth;
/* 187 */     if ((paramMouseEvent.getX() > i) && (paramMouseEvent.getX() < i + 10)) {
/* 188 */       this.theApp.setCursor(new Cursor(13));
/* 189 */       this.topTimeSelected = true;
/* 190 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 191 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     }
/*     */ 
/* 194 */     int j = this.theApp.rightMargin + this.theApp.clefWidth;
/* 195 */     int l = 10;
/* 196 */     if (this.theApp.keySigWidth > l) l = this.theApp.keySigWidth;
/* 197 */     if ((paramMouseEvent.getX() > j - 10) && (paramMouseEvent.getX() < j + l)) {
/* 198 */       this.theApp.setCursor(new Cursor(13));
/* 199 */       this.keySelected = true;
/* 200 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 201 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent) {
/* 206 */     if ((!(this.button1Down)) || (!(this.theApp.editable))) {
/* 207 */       return;
/*     */     }
/* 209 */     if (this.selectedNote >= 0) {
/* 210 */       Phrase localPhrase = this.theApp.getPhrase();
/* 211 */       Note localNote = localPhrase.getNote(this.selectedNote);
/*     */ 
/* 213 */       if ((paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 2) && (this.theApp.getPhrase().getNote(this.selectedNote).getPitch() != -2147483648)) {
/* 214 */         localNote.setPitch(localNote.getPitch() - 1);
/* 215 */         if (localNote.getPitch() < this.theApp.getMinPitch()) localNote.setPitch(this.theApp.getMinPitch());
/*     */ 
/* 217 */         this.clickedPosY += 2;
/*     */ 
/* 219 */         this.theApp.repaint();
/*     */ 
/* 221 */         this.storedPitch = localNote.getPitch();
/*     */       }
/*     */ 
/* 224 */       if ((paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 2) && (this.theApp.getPhrase().getNote(this.selectedNote).getPitch() != -2147483648)) {
/* 225 */         localNote.setPitch(localNote.getPitch() + 1);
/* 226 */         if (localNote.getPitch() > this.theApp.getMaxPitch()) localNote.setPitch(this.theApp.getMaxPitch());
/*     */ 
/* 228 */         this.clickedPosY -= 2;
/* 229 */         this.theApp.repaint();
/* 230 */         this.storedPitch = localNote.getPitch();
/*     */       }
/*     */       double d;
/*     */       int i;
/*     */       int j;
/*     */       int k;
/* 233 */       if (paramMouseEvent.getX() > this.clickedPosX + 6) {
/* 234 */         d = localNote.getRhythmValue();
/* 235 */         i = localNote.getPitch();
/*     */ 
/* 237 */         if (i == -2147483648) d += 100.0D;
/* 238 */         j = this.rhythmValues.length;
/*     */ 
/* 240 */         for (k = 0; k < this.rhythmValues.length - 1; ++k) {
/* 241 */           if (d == this.rhythmValues[k]) {
/* 242 */             localNote.setRhythmValue(this.rhythmValues[(k + 1)]);
/*     */ 
/* 244 */             localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/*     */           }
/*     */         }
/* 247 */         this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 249 */         if (localNote.getRhythmValue() > 100.0D) {
/* 250 */           localNote.setPitch(-2147483648);
/* 251 */           localNote.setRhythmValue(localNote.getRhythmValue() - 100.0D);
/*     */ 
/* 253 */           localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/*     */         }
/* 255 */         else if (i == -2147483648) { localNote.setPitch(this.storedPitch);
/*     */         }
/* 257 */         this.theApp.repaint();
/*     */       }
/*     */ 
/* 260 */       if (paramMouseEvent.getX() < this.clickedPosX - 6) {
/* 261 */         d = localNote.getRhythmValue();
/* 262 */         i = localNote.getPitch();
/*     */ 
/* 264 */         if (i == -2147483648) d += 100.0D;
/*     */ 
/* 266 */         j = 0;
/* 267 */         for (k = 0; k < this.rhythmValues.length; ++k) {
/* 268 */           if (d != this.rhythmValues[k]) continue; j = k;
/*     */         }
/*     */ 
/* 271 */         if (j > 0) {
/* 272 */           localNote.setRhythmValue(this.rhythmValues[(j - 1)]);
/*     */ 
/* 274 */           localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/* 275 */           this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 277 */           if (localNote.getRhythmValue() > 100.0D) {
/* 278 */             localNote.setPitch(-2147483648);
/* 279 */             localNote.setRhythmValue(localNote.getRhythmValue() - 100.0D);
/*     */ 
/* 281 */             localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/*     */           }
/* 283 */           else if (i == -2147483648) { localNote.setPitch(this.storedPitch);
/*     */           }
/* 285 */           this.theApp.repaint();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 290 */     if (this.topTimeSelected)
/*     */     {
/* 292 */       if (paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 4) {
/* 293 */         this.theApp.setMetre(this.theApp.getMetre() + 1.0D);
/* 294 */         if (this.theApp.getMetre() > 9.0D) this.theApp.setMetre(9.0D);
/* 295 */         if (this.theApp.getMetre() < 1.0D) this.theApp.setMetre(1.0D);
/* 296 */         this.theApp.getPhrase().setNumerator(new Double(Math.round(this.theApp.getMetre())).intValue());
/*     */ 
/* 304 */         this.clickedPosY -= 4;
/* 305 */         this.theApp.repaint();
/* 306 */         this.theApp.updateChange();
/*     */       }
/*     */ 
/* 309 */       if (paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 4) {
/* 310 */         this.theApp.setMetre(this.theApp.getMetre() - 1.0D);
/* 311 */         if (this.theApp.getMetre() < 1.0D) this.theApp.setMetre(1.0D);
/* 312 */         if (this.theApp.getMetre() > 9.0D) this.theApp.setMetre(9.0D);
/* 313 */         this.theApp.getPhrase().setNumerator(new Double(Math.round(this.theApp.getMetre())).intValue());
/*     */ 
/* 321 */         this.clickedPosY += 4;
/* 322 */         this.theApp.repaint();
/* 323 */         this.theApp.updateChange();
/*     */       }
/*     */     }
/*     */ 
/* 327 */     if (!(this.keySelected))
/*     */       return;
/* 329 */     if (paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 4) {
/* 330 */       this.theApp.setKeySignature(this.theApp.getKeySignature() + 1);
/* 331 */       if (this.theApp.getKeySignature() > 7) this.theApp.setKeySignature(7);
/* 332 */       this.clickedPosY -= 4;
/* 333 */       this.theApp.repaint();
/* 334 */       this.theApp.updateChange();
/*     */     }
/*     */ 
/* 337 */     if (paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 4) {
/* 338 */       this.theApp.setKeySignature(this.theApp.getKeySignature() - 1);
/* 339 */       if (this.theApp.getKeySignature() < -7) this.theApp.setKeySignature(-7);
/* 340 */       this.clickedPosY += 4;
/* 341 */       this.theApp.repaint();
/* 342 */       this.theApp.updateChange();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent)
/*     */   {
/* 348 */     this.button1Down = false;
/* 349 */     if (!(this.theApp.editable)) return;
/*     */ 
/* 351 */     for (int i = 0; i < this.theApp.getPhrase().getNoteList().size(); ++i) {
/* 352 */       if (this.theApp.getPhrase().getNote(i).getRhythmValue() == 0.0D) {
/* 353 */         this.theApp.getPhrase().getNoteList().removeElementAt(i);
/*     */       }
/*     */     }
/*     */ 
/* 357 */     this.theApp.repaint();
/* 358 */     this.theApp.updateChange();
/* 359 */     this.selectedNote = -1;
/* 360 */     this.topTimeSelected = false;
/* 361 */     this.keySelected = false;
/* 362 */     this.theApp.setCursor(new Cursor(0));
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent) {
/* 366 */     Phrase localPhrase = this.theApp.getPhrase();
/* 367 */     Note localNote = localPhrase.getNote(this.selectedNote);
/*     */     Object localObject;
/* 368 */     if (paramActionEvent.getSource() == this.editNote) {
/* 369 */       localObject = new Frame("Edit this Note");
/*     */ 
/* 371 */       ((Frame)localObject).setSize(400, 400);
/* 372 */       ((Frame)localObject).setResizable(true);
/* 373 */       NoteEditor localNoteEditor = new NoteEditor((Frame)localObject);
/*     */ 
/* 375 */       localNoteEditor.editNote(localNote, 20, 20);
/*     */     }
/* 377 */     else if (paramActionEvent.getSource() == this.repeatNote) {
/* 378 */       localObject = localNote.copy();
/* 379 */       localPhrase.getNoteList().insertElementAt(localObject, this.selectedNote);
/*     */     }
/* 384 */     else if (paramActionEvent.getSource() == this.makeRest) {
/* 385 */       localNote.setFrequency(-2147483648.0D);
/*     */     }
/* 387 */     else if (paramActionEvent.getSource() == this.deleteNote) {
/* 388 */       localPhrase.getNoteList().removeElementAt(this.selectedNote);
/*     */     }
/* 390 */     this.selectedNote = -1;
/* 391 */     this.theApp.repaint();
/*     */   }
/*     */ 
/*     */   public void keyPressed(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyReleased(KeyEvent paramKeyEvent) {
/*     */   }
/*     */ 
/*     */   public void keyTyped(KeyEvent paramKeyEvent) {
/* 401 */     if (paramKeyEvent.getKeyChar() != '\b') return; this.theApp.deleteLastNote();
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.StaveActionHandler
 * JD-Core Version:    0.5.3
 */