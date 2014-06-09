/*     */ package jm.gui.cpn;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.util.Vector;
/*     */ import jm.JMC;
/*     */ import jm.music.data.Note;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class JStaveActionHandler
/*     */   implements JMC, MouseListener, MouseMotionListener, ActionListener
/*     */ {
/*     */   private JGrandStave theApp;
/*  33 */   private int selectedNote = -1;
/*  34 */   private boolean topTimeSelected = false; private boolean keySelected = false;
/*     */   private int clickedPosY;
/*     */   private int clickedPosX;
/*  35 */   private int storedPitch = 72;
/*  36 */   private double[] rhythmValues = { 104.0D, 103.0D, 102.0D, 101.5D, 101.0D, 100.75D, 100.5D, 100.25D, 0.0D, 0.25D, 0.5D, 0.75D, 1.0D, 1.5D, 2.0D, 3.0D, 4.0D };
/*     */ 
/*     */   JStaveActionHandler(JGrandStave paramJGrandStave)
/*     */   {
/*  40 */     this.theApp = paramJGrandStave; }
/*     */ 
/*     */   public void mouseClicked(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseEntered(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseExited(MouseEvent paramMouseEvent) { }
/*     */ 
/*     */   public void mouseMoved(MouseEvent paramMouseEvent) {
/*     */   }
/*     */ 
/*     */   public void mousePressed(MouseEvent paramMouseEvent) {
/*  52 */     if (!(this.theApp.editable)) return;
/*     */     Integer localInteger1;
/*  56 */     if (this.theApp.notePositions.size() < 2)
/*  57 */       localInteger1 = new Integer(this.theApp.getTotalBeatWidth());
/*     */     else
/*  59 */       localInteger1 = (Integer)this.theApp.notePositions.elementAt(this.theApp.notePositions.size() - 2);
/*     */     Object localObject1;
/*     */     Object localObject2;
/*     */     Integer localInteger2;
/*  61 */     if ((paramMouseEvent.getX() > localInteger1.intValue() + 15) && (paramMouseEvent.getX() < this.theApp.getTotalBeatWidth() + 50))
/*     */     {
/*  64 */       i = 85 - ((paramMouseEvent.getY() + this.theApp.staveDelta - this.theApp.bPos) / 2);
/*     */ 
/*  66 */       localObject1 = new int[] { 1, 3, 6, 8, 10 };
/*  67 */       int k = 1;
/*  68 */       for (int i1 = 0; i1 < localObject1.length; ++i1) {
/*  69 */         if (i % 12 != localObject1[i1]) continue; --i;
/*     */       }
/*  71 */       Note localNote1 = new Note(i, 1.0D);
/*  72 */       localObject2 = this.theApp.getPhrase();
/*  73 */       ((Phrase)localObject2).addNote(localNote1);
/*  74 */       this.theApp.repaint();
/*     */ 
/*  76 */       this.theApp.setCursor(new Cursor(12));
/*     */ 
/*  78 */       this.selectedNote = (((Phrase)localObject2).size() - 1);
/*     */ 
/*  80 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/*  81 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     }
/*     */     else {
/*  84 */       for (i = 0; i < this.theApp.notePositions.size(); i += 2) {
/*  85 */         localObject1 = (Integer)this.theApp.notePositions.elementAt(i);
/*  86 */         localInteger2 = (Integer)this.theApp.notePositions.elementAt(i + 1);
/*  87 */         if ((paramMouseEvent.getX() <= ((Integer)localObject1).intValue()) || (paramMouseEvent.getX() >= ((Integer)localObject1).intValue() + 15) || (paramMouseEvent.getY() + this.theApp.staveDelta <= localInteger2.intValue() + 22) || (paramMouseEvent.getY() + this.theApp.staveDelta >= localInteger2.intValue() + 35)) {
/*     */           continue;
/*     */         }
/*     */ 
/*  91 */         this.theApp.setCursor(new Cursor(13));
/*  92 */         this.selectedNote = (i / 2);
/*  93 */         this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/*  94 */         this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/*  97 */         i = this.theApp.notePositions.size();
/*     */       }
/*     */     }
/*     */ 
/* 101 */     if (this.selectedNote < 0)
/*     */     {
/* 103 */       for (i = 0; i < this.theApp.notePositions.size() - 2; i += 2) {
/* 104 */         localObject1 = (Integer)this.theApp.notePositions.elementAt(i);
/* 105 */         localInteger2 = (Integer)this.theApp.notePositions.elementAt(i + 2);
/* 106 */         if ((paramMouseEvent.getX() <= ((Integer)localObject1).intValue() + 15) || (paramMouseEvent.getX() >= localInteger2.intValue()))
/*     */           continue;
/* 108 */         this.theApp.setCursor(new Cursor(12));
/*     */ 
/* 110 */         int i2 = 85 - ((paramMouseEvent.getY() + this.theApp.staveDelta - this.theApp.bPos) / 2);
/*     */ 
/* 112 */         localObject2 = new int[] { 1, 3, 6, 8, 10 };
/* 113 */         int i3 = 1;
/* 114 */         for (int i4 = 0; i4 < localObject2.length; ++i4) {
/* 115 */           if (i2 % 12 != localObject2[i4]) continue; --i2;
/*     */         }
/* 117 */         Note localNote2 = new Note(i2, 1.0D);
/* 118 */         Phrase localPhrase = this.theApp.getPhrase();
/* 119 */         localPhrase.getNoteList().insertElementAt(localNote2, i / 2 + 1);
/* 120 */         this.theApp.repaint();
/*     */ 
/* 122 */         this.selectedNote = (i / 2 + 1);
/* 123 */         this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 124 */         this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 126 */         i = this.theApp.notePositions.size();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 131 */     int i = this.theApp.rightMargin + this.theApp.clefWidth + this.theApp.keySigWidth;
/* 132 */     if ((paramMouseEvent.getX() > i) && (paramMouseEvent.getX() < i + 10)) {
/* 133 */       this.theApp.setCursor(new Cursor(13));
/* 134 */       this.topTimeSelected = true;
/* 135 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 136 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     }
/*     */ 
/* 140 */     int j = this.theApp.rightMargin + this.theApp.clefWidth;
/* 141 */     int l = 10;
/* 142 */     if (this.theApp.keySigWidth > l) l = this.theApp.keySigWidth;
/* 143 */     if ((paramMouseEvent.getX() > j - 10) && (paramMouseEvent.getX() < j + l)) {
/* 144 */       this.theApp.setCursor(new Cursor(13));
/* 145 */       this.keySelected = true;
/* 146 */       this.clickedPosY = (paramMouseEvent.getY() + this.theApp.staveDelta);
/* 147 */       this.clickedPosX = paramMouseEvent.getX();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseDragged(MouseEvent paramMouseEvent)
/*     */   {
/* 153 */     if (!(this.theApp.editable)) return;
/*     */ 
/* 155 */     if (this.selectedNote >= 0)
/*     */     {
/* 157 */       Phrase localPhrase = this.theApp.getPhrase();
/* 158 */       Note localNote = localPhrase.getNote(this.selectedNote);
/*     */ 
/* 160 */       if ((paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 2) && (this.theApp.getPhrase().getNote(this.selectedNote).getPitch() != -2147483648)) {
/* 161 */         localNote.setPitch(localNote.getPitch() - 1);
/* 162 */         if (localNote.getPitch() < this.theApp.getMinPitch()) localNote.setPitch(this.theApp.getMinPitch());
/*     */ 
/* 164 */         this.clickedPosY += 2;
/*     */ 
/* 166 */         this.theApp.repaint();
/*     */ 
/* 168 */         this.storedPitch = localNote.getPitch();
/*     */       }
/*     */ 
/* 171 */       if ((paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 2) && (this.theApp.getPhrase().getNote(this.selectedNote).getPitch() != -2147483648)) {
/* 172 */         localNote.setPitch(localNote.getPitch() + 1);
/* 173 */         if (localNote.getPitch() > this.theApp.getMaxPitch()) localNote.setPitch(this.theApp.getMaxPitch());
/*     */ 
/* 175 */         this.clickedPosY -= 2;
/* 176 */         this.theApp.repaint();
/* 177 */         this.storedPitch = localNote.getPitch();
/*     */       }
/*     */       double d;
/*     */       int i;
/*     */       int j;
/*     */       int k;
/* 180 */       if (paramMouseEvent.getX() > this.clickedPosX + 6) {
/* 181 */         d = localNote.getRhythmValue();
/* 182 */         i = localNote.getPitch();
/*     */ 
/* 184 */         if (i == -2147483648) d += 100.0D;
/* 185 */         j = this.rhythmValues.length;
/*     */ 
/* 187 */         for (k = 0; k < this.rhythmValues.length - 1; ++k) {
/* 188 */           if (d != this.rhythmValues[k]) continue; localNote.setRhythmValue(this.rhythmValues[(k + 1)]);
/*     */         }
/* 190 */         this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 192 */         if (localNote.getRhythmValue() > 100.0D) {
/* 193 */           localNote.setPitch(-2147483648);
/* 194 */           localNote.setRhythmValue(localNote.getRhythmValue() - 100.0D);
/*     */ 
/* 196 */           localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/*     */         }
/* 198 */         else if (i == -2147483648) { localNote.setPitch(this.storedPitch);
/*     */         }
/* 200 */         this.theApp.repaint();
/*     */       }
/*     */ 
/* 203 */       if (paramMouseEvent.getX() < this.clickedPosX - 6) {
/* 204 */         d = localNote.getRhythmValue();
/* 205 */         i = localNote.getPitch();
/*     */ 
/* 207 */         if (i == -2147483648) d += 100.0D;
/*     */ 
/* 209 */         j = 0;
/* 210 */         for (k = 0; k < this.rhythmValues.length; ++k) {
/* 211 */           if (d != this.rhythmValues[k]) continue; j = k;
/*     */         }
/*     */ 
/* 214 */         if (j > 0) {
/* 215 */           localNote.setRhythmValue(this.rhythmValues[(j - 1)]);
/* 216 */           this.clickedPosX = paramMouseEvent.getX();
/*     */ 
/* 218 */           if (localNote.getRhythmValue() > 100.0D) {
/* 219 */             localNote.setPitch(-2147483648);
/* 220 */             localNote.setRhythmValue(localNote.getRhythmValue() - 100.0D);
/*     */ 
/* 222 */             localNote.setDuration(localNote.getRhythmValue() * 0.9D);
/*     */           }
/* 224 */           else if (i == -2147483648) { localNote.setPitch(this.storedPitch);
/*     */           }
/* 226 */           this.theApp.repaint();
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/* 231 */     if (this.topTimeSelected)
/*     */     {
/* 233 */       if (paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 4) {
/* 234 */         this.theApp.setMetre(this.theApp.getMetre() + 1.0D);
/* 235 */         if (this.theApp.getMetre() > 9.0D) this.theApp.setMetre(9.0D);
/* 236 */         if (this.theApp.getMetre() < 1.0D) this.theApp.setMetre(1.0D);
/* 237 */         this.clickedPosY -= 4;
/* 238 */         this.theApp.repaint();
/* 239 */         this.theApp.updateChange();
/*     */       }
/*     */ 
/* 242 */       if (paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 4) {
/* 243 */         this.theApp.setMetre(this.theApp.getMetre() - 1.0D);
/* 244 */         if (this.theApp.getMetre() < 1.0D) this.theApp.setMetre(1.0D);
/* 245 */         if (this.theApp.getMetre() > 9.0D) this.theApp.setMetre(9.0D);
/* 246 */         this.clickedPosY += 4;
/* 247 */         this.theApp.repaint();
/* 248 */         this.theApp.updateChange();
/*     */       }
/*     */     }
/*     */ 
/* 252 */     if (!(this.keySelected))
/*     */       return;
/* 254 */     if (paramMouseEvent.getY() + this.theApp.staveDelta < this.clickedPosY - 4) {
/* 255 */       this.theApp.setKeySignature(this.theApp.getKeySignature() + 1);
/* 256 */       if (this.theApp.getKeySignature() > 7) this.theApp.setKeySignature(7);
/* 257 */       this.clickedPosY -= 4;
/* 258 */       this.theApp.repaint();
/* 259 */       this.theApp.updateChange();
/*     */     }
/*     */ 
/* 262 */     if (paramMouseEvent.getY() + this.theApp.staveDelta > this.clickedPosY + 4) {
/* 263 */       this.theApp.setKeySignature(this.theApp.getKeySignature() - 1);
/* 264 */       if (this.theApp.getKeySignature() < -7) this.theApp.setKeySignature(-7);
/* 265 */       this.clickedPosY += 4;
/* 266 */       this.theApp.repaint();
/* 267 */       this.theApp.updateChange();
/*     */     }
/*     */   }
/*     */ 
/*     */   public void mouseReleased(MouseEvent paramMouseEvent)
/*     */   {
/* 273 */     if (!(this.theApp.editable)) return;
/*     */ 
/* 275 */     for (int i = 0; i < this.theApp.getPhrase().getNoteList().size(); ++i) {
/* 276 */       if (this.theApp.getPhrase().getNote(i).getRhythmValue() == 0.0D) {
/* 277 */         this.theApp.getPhrase().getNoteList().removeElementAt(i);
/*     */       }
/*     */     }
/*     */ 
/* 281 */     this.theApp.repaint();
/* 282 */     this.theApp.updateChange();
/* 283 */     this.selectedNote = -1;
/* 284 */     this.topTimeSelected = false;
/* 285 */     this.keySelected = false;
/* 286 */     this.theApp.setCursor(new Cursor(0));
/*     */   }
/*     */ 
/*     */   public void actionPerformed(ActionEvent paramActionEvent)
/*     */   {
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.gui.cpn.JStaveActionHandler
 * JD-Core Version:    0.5.3
 */