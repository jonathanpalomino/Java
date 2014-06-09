/*     */ package jm.music.net;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.net.Socket;
/*     */ import jm.music.data.Phrase;
/*     */ 
/*     */ public class DSClientConnector extends Thread
/*     */ {
/*     */   private Socket connection;
/*     */   private ObjectInputStream ois;
/*     */   private ObjectOutputStream oos;
/*     */   private DSClient client;
/*  51 */   private Phrase phr = null;
/*     */ 
/*     */   public DSClientConnector(String paramString, int paramInt, DSClient paramDSClient)
/*     */   {
/*     */     try
/*     */     {
/*  65 */       this.connection = new Socket(paramString, paramInt);
/*  66 */       OutputStream localOutputStream = this.connection.getOutputStream();
/*  67 */       this.oos = new ObjectOutputStream(localOutputStream);
/*  68 */       InputStream localInputStream = this.connection.getInputStream();
/*  69 */       this.ois = new ObjectInputStream(localInputStream);
/*     */     } catch (IOException localIOException) {
/*  71 */       System.out.println("The client is having trouble connecting to the specified server.  Please check the server name and port number.");
/*  72 */       System.exit(1);
/*     */     }
/*  74 */     this.client = paramDSClient;
/*  75 */     this.client.setConnection(this);
/*  76 */     start();
/*     */   }
/*     */ 
/*     */   public void run()
/*     */   {
/*     */     try
/*     */     {
/*  88 */       this.client.newObject(this.ois.readObject());
/*     */     }
/*     */     catch (ClassNotFoundException localClassNotFoundException)
/*     */     {
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void sendObject(Object paramObject)
/*     */   {
/*     */     try
/*     */     {
/* 102 */       this.oos.writeObject(paramObject);
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */   }
/*     */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.music.net.DSClientConnector
 * JD-Core Version:    0.5.3
 */