public class AVLNode {

    // Fields
    int data;
    AVLNode left;
    AVLNode right;
    int height;

    // Constructors
    AVLNode (int data) {
	this(data, null, null);
    }

    AVLNode (int data, AVLNode left, AVLNode right) {
	this.data = data;
	this.left = left;
	this.right = right;
	this.height = 0;
    }


    // Param: int value - to insert
    //        AVLNode rt - node that roots this subtree
    // Returns: new root of this subtree
    public AVLNode insert (int value, AVLNode rt) {
    	if (rt == null)
    	    return new AVLNode(value, null, null);
    	if (value < rt.data)
    	    rt.left = insert(value, rt.left);
    	else if (value > rt.data)
    	    rt.right = insert(value, rt.right);
            // else duplicate, do nothing
    	return balance(rt);

    }


    // Returns : String representation of tree root at this node
    public String toString() {
	   return toString(this);
    }

    // Param: AVLNode rt
    // Returns : String representation of tree root at rt
    private String toString(AVLNode rt) {
    	if (rt == null)
    	    return "";
        if (rt.left == null && rt.right == null)
    	    return rt.data + " ";
    	String result = rt.data + " ";
        if (rt.left != null)
    	    result += toString(rt.left);
        if (rt.right != null)
    	    result += toString(rt.right);
    	return result;
    }


    // Param: AVLNode - rt - node to check for balance
    // Returns: new root of this subtree after balancing
    private AVLNode balance (AVLNode rt) {
        if (rt == null)
	       return rt;
        if (height(rt.left) - height(rt.right) > 1)
	       if (height (rt.left.left) >= height(rt.left.right))
		      rt = rotateRight(rt);
	       else
		      rt = doubleRotateLeftRight(rt);
	       else if (height(rt.right) - height(rt.left) > 1)
	           if (height(rt.right.right) >= height(rt.right.left))
		          rt = rotateLeft(rt);
	           else
		          rt = doubleRotateRightLeft(rt);
	    rt.height = Math.max(height(rt.left), height(rt.right)) + 1;
	    return rt;
    }

    // Param: AVLNode n
    // Returns: height of largest subtree, -1 if n is null
    private int height (AVLNode n) {
	   return n == null ? -1 : n.height;
    }

    private int max(int lhs, int rhs){
        return lhs > rhs ? lhs : rhs;
    }

    // Param: AVLNode rt
    // Returns: new root after single rotation of this rt right
    private AVLNode rotateRight(AVLNode rt) {
       AVLNode temp = rt.left;
       rt.left = temp.right;
       temp.right = rt;
       return temp;
    }

    // Param: AVLNode rt
    // Returns: new root after single rotation of this rt left
    private AVLNode rotateLeft(AVLNode rt) {
       AVLNode temp = rt.right;
       rt.right = temp.left;
       temp.left = rt;
	   return temp;
    }

    private AVLNode doubleRotateLeftRight(AVLNode rt) {
	   rt.left = rotateLeft(rt.left);
       return rotateRight(rt);
    }

    private AVLNode doubleRotateRightLeft(AVLNode rt) {
        rt.right = rotateRight(rt.right);
        return rotateLeft(rt);
    }
}
