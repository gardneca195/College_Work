import java.util.*;
import java.io.*;

public class AVLClient {
    public static void main (String [] args) throws FileNotFoundException {
		AVLNode root = new AVLNode(10);
		root = root.insert(8, root);
		root = root.insert(5, root);
		root = root.insert(11, root);
		root = root.insert(16, root);
		root = root.insert(2, root);
		root = root.insert(1, root);

		// Please note the the height reported is really the depth
		// of the tree from the root
		System.out.println(root.height);
		System.out.println(root);
    }
}
