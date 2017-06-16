/* Craig Gardner
 * CSC 365
 * 9/21/2014
 * BST
 */

import java.util.*;
import java.io.*;


public class BST<E extends Comparable<E>> implements Serializable {

	public Node<E> root;

	public BST() {
		root = new Node<E>(new NaturalComparator<E>());
	}
	public BST(Comparator<E> c){
		root = new Node<E>(c);
	}

	public class Node<E extends Comparable<E>> implements Serializable {
	
		private Comparator<E> ordering;
		//instantiation variables
		public E data;
		public Node<E> left;
		public Node<E> right;
		int xCoord;
	
		
		//constructor
		public Node() {
			data = null;
			left = null;
			right = null;
			xCoord = 1;
   		}
   		public Node(Comparator<E> c){
   			data = null;
   			left = null;
   			right = null;
   			xCoord = 1;
   			ordering = ordering(c);
   		}
			
		public boolean sameAs(E e) {
			return (e.compareTo(data) == 0);
		}

		//returns number of nodes branching from node
		public int size() {
			if (isEmpty()) return 0;
			else return 1 + (left.size() + right.size());
		}

		//returns generic data
		public E data() {
			return this.data;
		}
		
		//returns true if data is null
		public boolean isEmpty() {
			return data == null;
		}
		
		//returns true if subtree contains data
		public boolean contains(E e) {
			if (isEmpty()) return false;
			else if (sameAs(e)) return true;
			else return (left.contains(e) | right.contains(e));
		}

		//returns in order iterator
		public InOrder<E> inOrder() {
			return new InOrder();
        }

		//finds node based on data
		public Node find (E data) {
			if (isEmpty()) return null;
			else if (sameAs(data)) return this;
			else {
				Node l = left.find(data);
				if (l != null) return l;
				Node r = right.find(data);
				if (r != null) return r;
				return null;
			}
		}

		//returns parent of node
		public Node parent() {
			if (isEmpty()) return new Node();
			E node = (E)root.data;
			if ((ordering.compare(node,data) == 0))
				return new Node();
			else {
				Node parent = null;
				Node current = root;
				while (!current.sameAs(data)) {
					if (current.left.contains(data)) {
						parent = current;
						current = current.left;
					} else if (current.right.contains(data)) {
						parent = current;
						current = current.right;
					} else break;
				} 
				return parent;
			}
		}

		// returns predecessor of node to assist with removal
		public Node predecessor() {
			if (!left.isEmpty()) {			
				Node current = left;
				while (!current.right.isEmpty())
					current = current.right;
				return current;
			} else return new Node();
		}
		// returns successor of node to assist with removal
		public Node successor() {
			if (!right.isEmpty()) {
				Node current = right;
				while (!current.left.isEmpty())
					current = current.left;
				return current;
			} else return new Node();
		}
		
		//removes node from tree	
		public boolean remove(E e) {
			if (isEmpty() || !contains(e)) 
				return false;
			if (ordering.compare(e,data) < 0) {
		        return !left.isEmpty() ? left.remove(e) : false;
			} else if (ordering.compare(e,data) > 0) {
				return !right.isEmpty() ? right.remove(e) : false;
			} else {
				if (!left.isEmpty()) {
					data = (E)predecessor().data;
					left.remove(data);
				} else if (!right.isEmpty())  {
					data = (E)successor().data;
					right.remove(data);
				} else clear();
				return true;
			}
		}

		//empties tree
		public void clear() {
			if (isEmpty()) return;
			else {
				left.clear();
				data = null;
				right.clear();
			}
		}	

		//returns height of tree
		public int height() {
			if (isEmpty()) return 0;
			else return 1 + max(left.height(), right.height());
		}

		//returns greatest height
		private int max(int a, int b) {
			return a > b ? a : b;
		}

		//returns level of node
		public int level() {
			if (isEmpty()) return 0;
			else return 1 + parent().level(); 
		}

		//puts node into proper place in tree
		public void put(E e) {
			if (isEmpty()) add(e);
			else {
				if (e.compareTo(data) < 0)
					left.put(e);
				else 
					right.put(e);
			}
		}

		//creates new node and instantiates children
		public void add(E data) {
			this.data = data;
			this.left = new Node<E>();
			this.right = new Node<E>();
		}

		//returns inverted tree
		public void reflect() {
			if (isEmpty()) return;
			else {
				Node temp = left;
				left = right;
				right = temp;
				left.reflect();
				right.reflect();
			}
		}

		//InOrder iterator class
        	public class InOrder<E> implements Iterator<E> {
			//instantiation
			Stack<Node> s = new Stack<Node>();
			int x = 0;
			//constructor
			public InOrder() {
				build(root);
			}

			private void build(Node n) {
				if (n.isEmpty()) return;
				else {
					build(n.right);
					s.push(n);
					n.xCoord = ++x;
					build(n.left);
				}
			}

			//returns true if there are more nodes, via inorder
			public boolean hasNext() {
				return !s.empty();
			}
			
			//returns next data in order
			public E next() {
				if (!hasNext()) { return (E) data;
				} else {
					Node current = s.pop();
					E e = (E) current.data;
					return e;
				}
			}

			//not used
			public void remove() {}
		}

		public Comparator<E> ordering(Comparator<E> c) {
			return c;
		}
	}
}