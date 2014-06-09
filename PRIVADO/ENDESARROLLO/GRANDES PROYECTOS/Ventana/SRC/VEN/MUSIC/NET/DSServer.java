/*     */ package jm.music.net;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.ServerSocket;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Vector;
/*     */ 
/*     */ public class DSServer extends Thread
/*     */ {
/*     */   private ServerSocket ss;
/*     */   private Vector clientConnections;
/*     */ 
/*     */   public DSServer()
/*     */   {
/*  55 */     this(6767);
/*     */   }
/*     */ 
/*     */   public DSServer(int paramInt)
/*     */   {
/*  65 */     this.clientConnections = new Vector();
/*     */     try {
/*  67 */       this.ss = new ServerSocket(paramInt);
/*     */     } catch (IOException localIOException) {
/*     */     }
/*  70 */     start();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/*  82 */       DSServerConnector localDSServerConnector = new DSServerConnector(this.ss.accept(), this);
/*  83 */       this.clientConnections.addElement(localDSServerConnector);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void broadCast(Object paramObject, DSServerConnector paramDSServerConnector)
/*     */   {
/*  94 */     Enumeration localEnumeration = this.clientConnections.elements();
/*  95 */     while (localEnumeration.hasMoreElements()) {
/*  96 */       DSServerConnector localDSServerConnector = (DSServerConnector)localEnumeration.nextElement();
/*     */ 
/*  98 */       if (localDSServerConnector != paramDSServerConnector) localDSServerConnector.sendObject(paramObject);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void deleteConnection(DSServerConnector paramDSServerConnector)
/*     */   {
/* 106 */     this.clientConnections.removeElement(paramDSServerConnector);
/* 107 */     paramDSServerConnector = null;
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.net.DSServer
 * JD-Core Version:    0.5.3
 */