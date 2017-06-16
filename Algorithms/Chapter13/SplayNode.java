public class SplayNode {

    // Fields
    int data;
    SplayNode left;
    SplayNode right;
    int height;

    // Constructors
    SplayNode (int data) {
	this(data, null, null);
    }

    SplayNode (int data, SplayNode left, SplayNode right) {
	this.data = data;
	this.left = left;
	this.right = right;
	this.height = 0;
    }

    // Param: int value - to insert
    //        SplayNode rt - node that roots this subtree
    // Returns: new root of this subtree
    public SplayNode insert (int value, SplayNode rt) {
	// insert the new value in this tree
	rt = insertHelp(value, rt);
        // then bring the new value to the root
        return splay(rt, value);
    }


    // Param: int value - to insert
    //        playNode rt - node that roots this subtree
    // Returns: new root of this subtree
    private SplayNode insertHelp (int value, SplayNode rt) {
	if (rt == null)
	    return new SplayNode(value, null, null);
	if (value < rt.data)
	    rt.left = insertHelp(value, rt.left);
	else if (value > rt.data)
	    rt.right = insertHelp(value, rt.right);
	return rt;
    }


    // Returns : String representation of tree root at this node
    public String toString() {
	return toString(this);
    }

    // Param: SplayNode rt
    // Returns : String representation of tree root at rt
    private String toString(SplayNode rt) {
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

    // post: prints in reversed preorder the tree with given
    public void printSideways() {
        printSideways(this, 0);
    }

    // post: prints in reversed preorder the tree with given
    //       root, indenting each line to the given level
    private void printSideways(SplayNode root, int level) {
        if (root != null) {
            printSideways(root.right, level + 1);
            for (int i = 0; i < level; i++) {
                System.out.print("    ");
            }
            System.out.println(root.data);
            printSideways(root.left, level + 1);
        }
    }

    // Param: SplayNode - rt of tree
    //        int value - to splay 
    // Returns: new root of this subtree after balancing
    private SplayNode splay (SplayNode rt, int value) {
	// Hint: You can use rotateRight and rotateLeft from AVL trees
	//       You can also use double rotatations from AVL trees
	// stub
	return rt;
    }

    // Param: SplayNode n
    // Returns: height of largest subtree, -1 if n is null
    private int height (SplayNode n) {
	return n == null ? -1 : n.height;
    }
}
