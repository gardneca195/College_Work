/** A JUnit test class for the general tree. */

import java.io.*;

public class GenTreeDemo {

    public static void main(String [] args) {
       // Exercise 6.3 pre and post order check
       GenTree tree = new GenTree();
       preorder(tree.root());
       postorder(tree.root());
       tree.newroot(1, null, null);
       GTNode ptr = tree.root();
       ptr.insertFirst(new GTNode(2));
       ptr = ptr.leftmostChild();
       ptr.insertNext(new GTNode(3));
       ptr.insertNext(new GTNode(4));
       preorder(tree.root()); System.out.println();
       postorder(tree.root()); System.out.println();

       //Exercise 6.4 numberOfNodes
       tree = new GenTree();
       tree.newroot(1, null, null);
       ptr = tree.root();
       ptr.insertFirst(new GTNode(2));
       ptr = ptr.leftmostChild();
       ptr.insertNext(new GTNode(4));
       ptr.insertNext(new GTNode(3));
       ptr.insertFirst(new GTNode(5));
       ptr = ptr.leftmostChild();
       ptr.insertNext(new GTNode(6));
       ptr.insertFirst(new GTNode(7));
       ptr = ptr.leftmostChild();
       ptr.insertNext(new GTNode(8));
       preorder(tree.root()); System.out.println();
       postorder(tree.root()); System.out.println();
       System.out.println("number of nodes = " + numberOfNodes(tree.root()));
  }


  /** Postorder traversal for general trees */
  // WRITE IT HERE Exercise 6.3


  /** number of nodes in general tree */
  // WRITE IT HERE Exercise 6.4


  /** Preorder traversal for general trees */
  static  void preorder(GTNode rt) {
      if (rt == null) return;

     System.out.print(rt + " ");
     if (rt.isLeaf()) 
	 return;

     // else
     GTNode temp = rt.leftmostChild();
     while (temp != null) {
        preorder(temp);
        temp = temp.rightSibling();
    }
  }


}
