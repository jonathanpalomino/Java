/*      */ package jm.util;
/*      */ 
/*      */ import java.util.Enumeration;
/*      */ import java.util.Vector;
/*      */ 
/*      */ class Element
/*      */ {
/*      */   private String name;
/* 1512 */   private Vector attributeVector = new Vector();
/*      */ 
/* 1514 */   private Vector childrenVector = new Vector();
/*      */ 
/*      */   public Element(String paramString) {
/* 1517 */     this.name = paramString;
/*      */   }
/*      */ 
/*      */   public void addAttribute(Attribute paramAttribute) {
/* 1521 */     this.attributeVector.addElement(paramAttribute);
/*      */   }
/*      */ 
/*      */   public void appendChildren(Element[] paramArrayOfElement) {
/* 1525 */     for (int i = 0; i < paramArrayOfElement.length; ++i)
/* 1526 */       this.childrenVector.addElement(paramArrayOfElement[i]);
/*      */   }
/*      */ 
/*      */   public String getAttribute(String paramString)
/*      */   {
/* 1531 */     Enumeration localEnumeration = this.attributeVector.elements();
/* 1532 */     while (localEnumeration.hasMoreElements()) {
/* 1533 */       Attribute localAttribute = (Attribute)localEnumeration.nextElement();
/* 1534 */       if (localAttribute.getName().equals(paramString)) {
/* 1535 */         return localAttribute.getValue();
/*      */       }
/*      */     }
/* 1538 */     return "";
/*      */   }
/*      */ 
/*      */   public Element[] getChildren() {
/* 1542 */     Element[] arrayOfElement = new Element[this.childrenVector.size()];
/* 1543 */     this.childrenVector.copyInto(arrayOfElement);
/* 1544 */     return arrayOfElement;
/*      */   }
/*      */ 
/*      */   public String getName() {
/* 1548 */     return this.name;
/*      */   }
/*      */ }

/* Location:           J:\jMusic1.6.01.jar
 * Qualified Name:     jm.util.Element
 * JD-Core Version:    0.5.3
 */