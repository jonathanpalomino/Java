/*      */ package jm.util;
/*      */ 
/*      */ class XMLStyles
/*      */ {
/* 1242 */   public static final XMLStyle[] styles = { new StandardXMLStyle() };
/*      */ 
/*      */   public static boolean isValidScoreTag(String paramString)
/*      */   {
/* 1247 */     for (int i = 0; i < styles.length; ++i) {
/* 1248 */       if (paramString.equals(styles[i].getScoreTagName())) {
/* 1249 */         return true;
/*      */       }
/*      */     }
/* 1252 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean isValidPartTag(String paramString) {
/* 1256 */     for (int i = 0; i < styles.length; ++i) {
/* 1257 */       if (paramString.equals(styles[i].getPartTagName())) {
/* 1258 */         return true;
/*      */       }
/*      */     }
/* 1261 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean isValidPhraseTag(String paramString) {
/* 1265 */     for (int i = 0; i < styles.length; ++i) {
/* 1266 */       if (paramString.equals(styles[i].getPhraseTagName())) {
/* 1267 */         return true;
/*      */       }
/*      */     }
/* 1270 */     return false;
/*      */   }
/*      */ 
/*      */   public static boolean isValidNoteTag(String paramString) {
/* 1274 */     for (int i = 0; i < styles.length; ++i) {
/* 1275 */       if (paramString.equals(styles[i].getNoteTagName())) {
/* 1276 */         return true;
/*      */       }
/*      */     }
/* 1279 */     return false;
/*      */   }
/*      */ 
/*      */   public static String getTitleAttributeValue(Element paramElement) {
/* 1283 */     for (int i = 0; i < styles.length; ++i) {
/* 1284 */       String str = paramElement.getAttribute(styles[i].getTitleAttributeName());
/*      */ 
/* 1286 */       if (!(str.equals(""))) {
/* 1287 */         return str;
/*      */       }
/*      */     }
/* 1290 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getTempoAttributeValue(Element paramElement) {
/* 1294 */     for (int i = 0; i < styles.length; ++i) {
/* 1295 */       String str = paramElement.getAttribute(styles[i].getTempoAttributeName());
/*      */ 
/* 1297 */       if (!(str.equals(""))) {
/* 1298 */         return str;
/*      */       }
/*      */     }
/* 1301 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getVolumeAttributeValue(Element paramElement) {
/* 1305 */     for (int i = 0; i < styles.length; ++i) {
/* 1306 */       String str = paramElement.getAttribute(styles[i].getVolumeAttributeName());
/*      */ 
/* 1308 */       if (!(str.equals(""))) {
/* 1309 */         return str;
/*      */       }
/*      */     }
/* 1312 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getKeySignatureAttributeValue(Element paramElement) {
/* 1316 */     for (int i = 0; i < styles.length; ++i) {
/* 1317 */       String str = paramElement.getAttribute(styles[i].getKeySignatureAttributeName());
/*      */ 
/* 1320 */       if (!(str.equals(""))) {
/* 1321 */         return str;
/*      */       }
/*      */     }
/* 1324 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getKeyQualityAttributeValue(Element paramElement) {
/* 1328 */     for (int i = 0; i < styles.length; ++i) {
/* 1329 */       String str = paramElement.getAttribute(styles[i].getKeyQualityAttributeName());
/*      */ 
/* 1332 */       if (!(str.equals(""))) {
/* 1333 */         return str;
/*      */       }
/*      */     }
/* 1336 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getNumeratorAttributeValue(Element paramElement) {
/* 1340 */     for (int i = 0; i < styles.length; ++i) {
/* 1341 */       String str = paramElement.getAttribute(styles[i].getNumeratorAttributeName());
/*      */ 
/* 1343 */       if (!(str.equals(""))) {
/* 1344 */         return str;
/*      */       }
/*      */     }
/* 1347 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getDenominatorAttributeValue(Element paramElement) {
/* 1351 */     for (int i = 0; i < styles.length; ++i) {
/* 1352 */       String str = paramElement.getAttribute(styles[i].getDenominatorAttributeName());
/*      */ 
/* 1354 */       if (!(str.equals(""))) {
/* 1355 */         return str;
/*      */       }
/*      */     }
/* 1358 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getChannelAttributeValue(Element paramElement) {
/* 1362 */     for (int i = 0; i < styles.length; ++i) {
/* 1363 */       String str = paramElement.getAttribute(styles[i].getChannelAttributeName());
/*      */ 
/* 1365 */       if (!(str.equals(""))) {
/* 1366 */         return str;
/*      */       }
/*      */     }
/* 1369 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getInstrumentAttributeValue(Element paramElement) {
/* 1373 */     for (int i = 0; i < styles.length; ++i) {
/* 1374 */       String str = paramElement.getAttribute(styles[i].getInstrumentAttributeName());
/*      */ 
/* 1377 */       if (!(str.equals(""))) {
/* 1378 */         return str;
/*      */       }
/*      */     }
/* 1381 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getPanAttributeValue(Element paramElement) {
/* 1385 */     for (int i = 0; i < styles.length; ++i) {
/* 1386 */       String str = paramElement.getAttribute(styles[i].getPanAttributeName());
/*      */ 
/* 1389 */       if (!(str.equals(""))) {
/* 1390 */         return str;
/*      */       }
/*      */     }
/* 1393 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getStartTimeAttributeValue(Element paramElement) {
/* 1397 */     for (int i = 0; i < styles.length; ++i) {
/* 1398 */       String str = paramElement.getAttribute(styles[i].getStartTimeAttributeName());
/*      */ 
/* 1401 */       if (!(str.equals(""))) {
/* 1402 */         return str;
/*      */       }
/*      */     }
/* 1405 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getAppendAttributeValue(Element paramElement) {
/* 1409 */     for (int i = 0; i < styles.length; ++i) {
/* 1410 */       String str = paramElement.getAttribute(styles[i].getAppendAttributeName());
/*      */ 
/* 1413 */       if (!(str.equals(""))) {
/* 1414 */         return str;
/*      */       }
/*      */     }
/* 1417 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getPitchAttributeValue(Element paramElement) {
/* 1421 */     for (int i = 0; i < styles.length; ++i) {
/* 1422 */       String str = paramElement.getAttribute(styles[i].getPitchAttributeName());
/*      */ 
/* 1425 */       if (!(str.equals(""))) {
/* 1426 */         return str;
/*      */       }
/*      */     }
/* 1429 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getFrequencyAttributeValue(Element paramElement) {
/* 1433 */     for (int i = 0; i < styles.length; ++i) {
/* 1434 */       String str = paramElement.getAttribute(styles[i].getFrequencyAttributeName());
/*      */ 
/* 1437 */       if (!(str.equals(""))) {
/* 1438 */         return str;
/*      */       }
/*      */     }
/* 1441 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getDynamicAttributeValue(Element paramElement)
/*      */   {
/* 1446 */     for (int i = 0; i < styles.length; ++i) {
/* 1447 */       String str = paramElement.getAttribute(styles[i].getDynamicAttributeName());
/*      */ 
/* 1450 */       if (!(str.equals(""))) {
/* 1451 */         return str;
/*      */       }
/*      */     }
/* 1454 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getRhythmValueAttributeValue(Element paramElement) {
/* 1458 */     for (int i = 0; i < styles.length; ++i) {
/* 1459 */       String str = paramElement.getAttribute(styles[i].getRhythmValueAttributeName());
/*      */ 
/* 1462 */       if (!(str.equals(""))) {
/* 1463 */         return str;
/*      */       }
/*      */     }
/* 1466 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getDurationAttributeValue(Element paramElement) {
/* 1470 */     for (int i = 0; i < styles.length; ++i) {
/* 1471 */       String str = paramElement.getAttribute(styles[i].getDurationAttributeName());
/*      */ 
/* 1474 */       if (!(str.equals(""))) {
/* 1475 */         return str;
/*      */       }
/*      */     }
/* 1478 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getOffsetAttributeValue(Element paramElement) {
/* 1482 */     for (int i = 0; i < styles.length; ++i) {
/* 1483 */       String str = paramElement.getAttribute(styles[i].getOffsetAttributeName());
/*      */ 
/* 1486 */       if (!(str.equals(""))) {
/* 1487 */         return str;
/*      */       }
/*      */     }
/* 1490 */     return "";
/*      */   }
/*      */ 
/*      */   public static String getSampleStartTimeAttributeValue(Element paramElement)
/*      */   {
/* 1495 */     for (int i = 0; i < styles.length; ++i) {
/* 1496 */       String str = paramElement.getAttribute(styles[i].getSampleStartTimeAttributeName());
/*      */ 
/* 1499 */       if (!(str.equals(""))) {
/* 1500 */         return str;
/*      */       }
/*      */     }
/* 1503 */     return "";
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.XMLStyles
 * JD-Core Version:    0.5.3
 */