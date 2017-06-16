import java.util.*;

public class SplayBST<E extends Comparable<E>> {
   
   protected Comparator c;
   Node<E> root;
   int count;

    public SplayBST() {
        root = null;
        count = 0;
        c = (new Comparator<E>()
        {// beggining of anonymous class definition

            public int compare(E a, E b){
                return a.compareTo(b);
            }
            public boolean equals(Object obj){return true;
            }
        }
        );
    }
    public SplayBST(Comparator c){
        root = null;
        count = 0;
        c = c;
    }

    //assumes no duplicates
    public void add(E x) {
        root = splayInsert(root,x); count++;
    }

    // moves node containing x to the root
    public Node<E> search(E x) {
        return root = splaySearch(root,x);
     }

     public boolean isEmpty(){
        if(root == null)return true;
        return false;
     }

    // 4 cases for two levels down in the search path from the root:
    // left-left:  rotate right at root twice
    // left-right: rotate left at left child; rotate right at root
    // right-right: rotate left at root twice
    // right-left: rltate right at right child, rotate left at root

    public Node<E> splayInsert(Node<E> h, E x) {
        if(h == null) return new Node<E>(x);

        if (c.compare(x,h.value)<0) {

            if (h.left == null) {
                h.left = new Node<E>(x);
                return rotateRight(h);
            }

            if (c.compare(h.left.value,x)<0) {
                h.left.left = splayInsert(h.left.left,x);
                h = rotateRight(h);
            }else {
                h.left.right = splayInsert(h.left.right,x);
                h.left = rotateLeft(h.left);
            }
            return rotateRight(h);
        }

        else { //x.compareTo(h.value)>0  
            if (h.right == null) {
                h.right = new Node<E>(x);
                return rotateLeft(h);
            }

            if (c.compare(h.right.value,x)>0) {
                h.right = splayInsert(h.right,x);
                h = rotateLeft(h);
            }else {
                h.right.left = splayInsert(h.right.left,x);
                h.right= rotateRight(h.right);
            }
            return rotateLeft(h);
        }
    }

   /************************************************************************
    * splaySearch 
    * **********************************************************************/
    // splaySearch key in the tree rooted at Node h. If a node with that key exists,
    //   it is splayed to the root of the tree. If it does not, the last node
    //   along the search path for the key is splayed to the root.
    public Node<E> splaySearch(Node<E> h, E x) {
        if (h == null) return null;

        int cmp1 = x.compareTo(h.value);

        if (cmp1 < 0) {
            // key not in tree, so we're done
            if (h.left == null) {
                return h;
            }
            int cmp2 = x.compareTo(h.left.value);
            if (cmp2 < 0) {
                h.left.left = splaySearch(h.left.left, x);
                h = rotateRight(h);
            }
            else if (cmp2 > 0) {
                h.left.right = splaySearch(h.left.right, x);
                if (h.left.right != null)
                    h.left = rotateLeft(h.left);
            }
            
            if (h.left == null) return h;
            else                return rotateRight(h);
        }

        else if (cmp1 > 0) { 
            // key not in tree, so we're done
            if (h.right == null) {
                return h;
            }

            int cmp2 = x.compareTo(h.right.value);
            if (cmp2 < 0) {
                h.right.left  = splaySearch(h.right.left, x);
                if (h.right.left != null)
                    h.right = rotateRight(h.right);
            }
            else if (cmp2 > 0) {
                h.right.right = splaySearch(h.right.right, x);
                h = rotateLeft(h);
            }
            
            if (h.right == null) return h;
            else                 return rotateLeft(h);
        }

        else return h;
    }


    public Node<E> rotateRight(Node<E> h) {
        Node<E> x = h.left;
        h.left = x.right;
        x.right = h;
        return x;
    }
    
    public Node<E> rotateLeft(Node<E> h) {
        Node<E> x = h.right;
        h.right = x.left;
        x.left = h;
        return x;
    }
     /* Function for inorder traversal */
     public void inorder(){
         inorder(root);
     }
     private void inorder(Node<E> r){
         if (r != null)
         {
             inorder(r.left);
             System.out.print(r.value +" ");
             inorder(r.right);
         }
     }
          /* Function for preorder traversal */
     public void preorder(){
         preorder(root);
     }
     private void preorder(Node<E> r){
         if (r != null)
         {
             System.out.print(r.value +" ");
             preorder(r.left);             
             preorder(r.right);
         }
     }
      /* Function for postorder traversal */
     public void postorder(){
         postorder(root);
     }
     private void postorder(Node<E> r){

         if (r != null)
         {
             postorder(r.left);             
             postorder(r.right);
             System.out.print(r.value +" ");
         }
     }   
    public void byLevel(){
        while()
        byLevel(root,0);
    } 

    private void byLevel(Node root, int level){
        Queue<Node<e>> level = new LinkedList<Node<E>>();
        System.out.println(root.value);
        if(root.left!= null)
            byLevel(root.left, level++);
        if(root.right!= null)
            byLevel(root.right,level++);
    }

    private void printTree(Node root){
        Queue<TreeNode> currentLevel = new LinkedList<TreeNode>();
        Queue<TreeNode> nextLevel = new LinkedList<TreeNode>();

        currentLevel.add(root);

        while (!currentLevel.isEmpty()) {
            Iterator<Node<E>> iter = currentLevel.iterator();
            while (iter.hasNext()) {
                TreeNode currentNode = iter.next();
                if (currentNode.left != null) {
                    nextLevel.add(currentNode.left);
                }
                if (currentNode.right != null) {
                    nextLevel.add(currentNode.right);
                }
                System.out.print(currentNode.value + " ");
            }
            System.out.println();
            currentLevel = nextLevel;
            nextLevel = new LinkedList<TreeNode>();

        }
    }


    
    class Node<E extends Comparable<E>> {
        Node<E> left;
        Node<E> right;
        E value;
        
        public Node(E x) { 
            left = null;
            right = null;
            value=x;
        }
    }
    
// NOTE:  I put main in here just to give you an example of a way you can define test cases.  
// THIS SHOULD GO IN A SEPARATE TEST CLASS 
    public static void main(String [] args) {
     SplayBST<Integer> t = new SplayBST<Integer>( );   
        final int NUMS = 40000;   
        final int GAP  =   307;   
   
        System.out.println( "Checking... (no bad output means success)" );   
   
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )   
            t.add( i );   
        System.out.println( "Inserts complete" );   
   
    }   
}