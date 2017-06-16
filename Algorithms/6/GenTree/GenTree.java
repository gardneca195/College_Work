/** Source code example for "A Practical Introduction to Data
    Structures and Algorithm Analysis, 3rd Edition (Java)" 
    by Clifford A. Shaffer
    Copyright 2008-2011 by Clifford A. Shaffer
*/

// modified by S. Haller 3/31/2014

public class GenTree {

    private GTNode rt;

    public GenTree() { 
      rt = null; 
    }

    public void clear() { 
      rt = null; 
    }

    public GTNode root() { 
       return rt; 
    }

    public void newroot(int value, GTNode first, GTNode sib) {
       clear();
       rt = new GTNode(value, null, first, sib);
       if (first != null) first.setParent(rt);
       if (sib != null) sib.setParent(null);
    }

    public void newleftchild(int value) {
        GTNode temp = new GTNode (value, rt, null,
				  rt.leftmostChild());
        rt.insertFirst(temp);
    }
}
