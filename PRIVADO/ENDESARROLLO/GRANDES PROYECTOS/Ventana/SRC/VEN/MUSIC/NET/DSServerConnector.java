/*     */ package jm.music.net;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ 
/*     */ public class DSServerConnector extends Thread
/*     */ {
/*     */   private Socket connection;
/*     */   private ObjectInputStream ois;
/*     */   private ObjectOutputStream oos;
/*     */   private static DSServer server;
/*     */   private Object obj;
/*     */ 
/*     */   public DSServerConnector(Socket paramSocket, DSServer paramDSServer)
/*     */   {
/*  65 */     server = paramDSServer;
/*  66 */     this.connection = paramSocket;
/*     */     try {
/*  68 */       OutputStream localOutputStream = this.connection.getOutputStream();
/*  69 */       this.oos = new ObjectOutputStream(localOutputStream);
/*  70 */       InputStream localInputStream = this.connection.getInputStream();
/*  71 */       this.ois = new ObjectInputStream(localInputStream);
/*  72 */       System.out.println(paramSocket);
/*     */     } catch (IOException localIOException) {
/*     */     }
/*  75 */     start();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/*  87 */       Object localObject = this.ois.readObject();
/*  88 */       server.broadCast(localObject, this);
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException) {
/*  91 */       System.out.println(localClassNotFoundException);
/*     */     } catch (IOException localIOException) {
/*  93 */       System.out.println(localIOException);
/*  94 */       server.deleteConnection(this);
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendObject(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/* 105 */       this.oos.writeObject(paramObject);
/*     */     } catch (IOException localIOException) {
/* 107 */       server.deleteConnection(this);
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.net.DSServerConnector
 * JD-Core Version:    0.5.3
 */