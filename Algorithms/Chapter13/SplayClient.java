import java.util.*;
import java.io.*;

public class SplayClient {
    public static void main (String [] args) throws FileNotFoundException {
	SplayNode root = new SplayNode(10);
	root = root.insert(8, root);
	root = root.insert(5, root);
	root = root.insert(14, root);
	// Please note that the height of the tree reported is really
	// the max depth of the tree from the root
	System.out.println(root.height);
	System.out.println(root);
	System.out.println("You can also display tree this way...");
	root.printSideways();
    }
}
