// Class SearchTree stores and prints a binary search tree of
// objects of type E.  E must implement the Comparable<E>
// interface.
import java.util.*;
public class SearchTree<E extends Comparable<E>> {
    private SearchTreeNode<E> overallRoot; // root of overall tree

    // post: constructs an empty search tree
    public SearchTree() {
        overallRoot = null;
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        overallRoot = add(overallRoot, value);
    }

    // post: value added to tree so as to preserve binary search tree
    private SearchTreeNode<E> add(SearchTreeNode<E> root, E value) {
        if (root == null) {
            root = new SearchTreeNode<E>(value);
        } else if (root.data.compareTo(value) >= 0) {
            root.left = add(root.left, value);
        } else {
            root.right = add(root.right, value);
        }
        return root;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(overallRoot, value);
    }   

    // post: returns true if given tree contains value, returns false otherwise
    private boolean contains(SearchTreeNode<E> root, E value) {
        if (root == null) {
            return false;
        } else {
            int compare = value.compareTo(root.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(root.left, value);
            } else {   // compare > 0
                return contains(root.right, value);
            }
        }
    }
    // returns: size of the tree
    public int size(){
        return size(overallRoot);
    }
    // parameters : root - overallRoot
    // returns : size of given tree
    private int size(SearchTreeNode<E> root){
        if(root == null)
            return 0;
        return 1 + size(root.left) + size(root.right);
    }
    // Postcondition: sets tree to an empty tree.
    public void clear(){
        overallRoot = null;
    }
    // returns: minimum value of the tree.
    public E getMin(){
        if(overallRoot == null)
            throw new NoSuchElementException();
        return getMin(overallRoot);
    }
    // Parameter : root - overallRoot
    // returns: minimum value of given tree.
    private E getMin(SearchTreeNode<E> root){
        if(root.left == null)
            return root.data;
        return getMin(root.left);
    }

    //Parameters : other - tree being tested
    // Return : true if all elements in this tree are in the other tree. 
    //          Structure does not matter.
    public boolean containsAll(SearchTree<E> other){
        boolean temp = true;
        if(this.size() < other.size())
           temp = false;
        if(this.size() == 0 && other.size() == 0)
            temp = true;
        containsAll(temp ,overallRoot, other.overallRoot);
        return temp;
    }
    // Parameters : temp - boolean trips if element is not in other tree.
    // Postcondition : temp is tripped
    private void containsAll(boolean temp, SearchTreeNode<E> other,
                                                        SearchTreeNode<E> root){
        if(temp == false)
            return;
        if(root != null)
            if(contains(other, root.data)){
                if(root.left == null)
                    containsAll(temp,other, root);
                if(root.right == null)
                    containsAll(temp,other, root);
            }else 
                temp = false;
    }        


    // Parameters : other - tree to be tested
    // return : true if this tree and other tree are identical
    public boolean equals(SearchTree<E> other){
        return equals(overallRoot, other.overallRoot);
    }
    // Parameters : other - tree to be checked
    //              root - this tree to be tested
    // return : true if two trees are identical
    private boolean equals(SearchTreeNode<E> root, SearchTreeNode<E> other){
        if((root == null && other == null))
            return true;
        else if(root == null || other == null)
            return false;
        else{
            if(root.data.equals(other.data))
                return equals(root.left, other.left) &&
                                                equals(root.right, other.right); 
            else
                return false;
        }
    }    


    // return : true if tree is empty
    public boolean isEmpty(){
        if(overallRoot == null)
            return true;
        return false;
    }

    // Parameters : value - value to be removed
    // PostCondition : removes value passed into method
    public void remove(E value){
        overallRoot = remove(overallRoot, value);
    }
    // Parameters : value - value to be removed
    //              root - tree node that will be searched for the
    //                                             value to be removed.
    // PostCondition : removes value from the tree.
    private SearchTreeNode remove(SearchTreeNode<E> root, E value){
        if(root == null)
            return null;
        else{
            int compare = value.compareTo(root.data);
            if(compare < 0)
                root.left = remove(root.left, value);
            else if(compare > 0)
                root.right = remove(root.right, value);
            else
                if(root.right == null)
                    return root.left;
                else if(root.left == null)
                    return root.right;
                else{
                    root.data = getMin(root.right);
                    root.right = remove(root.right, root.data);
                }
        }
        return root;
    }
                
        

    // post: prints the data of the tree, one per line
    public void print() {
        printInorder(overallRoot);
    }

    // post: prints the data of the tree using an inorder traversal
    private void printInorder(SearchTreeNode<E> root) {
        if (root != null) {
            printInorder(root.left);
            System.out.println(root.data);
            printInorder(root.right);
        }
    }

    private static class SearchTreeNode<E> {
        public E data;                   // data stored in this node
        public SearchTreeNode<E> left;   // left subtree
        public SearchTreeNode<E> right;  //  right subtree

        // post: constructs a leaf node with given data
        public SearchTreeNode(E data) {
            this(data, null, null);
        }

        // post: constructs a node with the given data and links
        public SearchTreeNode(E data, SearchTreeNode<E> left,SearchTreeNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }
}
