/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

/** Implementation of General Tree Nodes: Left Child/
    Right Sibling implementation (with parent pointer) */

// modified by S. Haller 3/31/2014
public class GTNode {

   private GTNode rent;
   private GTNode leftchild;
   private GTNode rightsib;
   private int element;

   public GTNode(int value) {
      rent = leftchild = rightsib = null;
      element = value;
   }

   public GTNode(int value, GTNode par,
                            GTNode leftc, GTNode rights) {
       element = value;
       rent = par; 
       leftchild = leftc; 
       rightsib = rights;
   }  

   public int value() { 
       return element; 
   }

   public boolean isLeaf() { 
       return leftchild == null; 
   }

   public GTNode parent() { 
       return rent; 
   }

   public GTNode leftmostChild() { 
       return leftchild; 
   }

   public GTNode rightSibling() { 
       return rightsib; 
   }

   public void setValue(int value) { 
       element = value; 
   }

   public void setParent(GTNode par) {
       rent = par; 
   }

   public void insertFirst(GTNode node) {
       node.rent = this;
       node.rightsib = leftchild;
       leftchild = node;
   }

   public void insertNext(GTNode node) {
       node.rent = rent;
       node.rightsib = rightsib;
       rightsib = node;
   }

   public void removeFirst() {
       if (leftchild == null) return;
       leftchild = leftchild.rightsib;
   }

   public void removeNext() {
      if (rightsib == null) return;
      rightsib = rightsib.rightsib;
   }

    public String toString() {
	return "" + element;
    }
}
