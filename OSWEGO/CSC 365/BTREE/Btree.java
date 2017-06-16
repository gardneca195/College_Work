import java.util.*;
import java.io.*;
import java.lang.*;

public class Btree{
	private static final int M = 4; // max keys per B-tree node = m-1
	public BtreeN root = new BtreeN();   		// root of the B-tree
	protected Comparator c;		
	
	public Btree(){
		c = (new Comparator<String>()
        {// beggining of anonymous class definition

            public int compare(String a, String b){
                return a.compareTo(b);
            }
            public boolean equals(Object obj){return true;
            }
        }
        );
	}

	public Btree(Comparator compare){
		c = compare;
	}
	public void setRoot(BtreeN h){
		root = h;
	}
	public boolean add(String value){
		return root.insert(root,value,null,0);
	}
	public boolean search(String value){
		System.out.println("Searching for : " + value);
        System.out.println(".\n.\n.");
        return root.search(root, value, null);
	}

		class BtreeN{
			private String [] keys;
			private BtreeN[] links;
			

			private BtreeN(){
				keys = new String[M-1];
				links = new BtreeN[M];

			}
			private BtreeN(String value){
				keys = new String[M-1];
				keys[0] = value;
				links = new BtreeN[M];
			}

			private boolean isLeaf(BtreeN h){
				for(int i = 0; i < M; i++)
					if(h.links[i] != null)
						return false;
				return true;
			}


			private boolean isFull(BtreeN h){
				for(int i = 0; i < M-1; i++)
					if(h.keys[i] == null)
						return false;
				return true;
			}

			


			private boolean search(BtreeN h, String value,BtreeN parent){
				if(h == null){    
					return false;
				}
				if(isFull(h))
					split(h,parent);
				if(c.compare(value,h.keys[0]) < 0)		// if the value is less than keys[0] search the leftmost tree
					return search(h.links[0], value, h);
				
				for(int i = 0; i < M-1; i++)					// if the value is greater than keys[i] search the right subtree
					if(c.compare(value,h.keys[0]) > 0)
						return search(h.links[i+1],value,h);
				for(int i = 0; i < M-1; i++)
					if(c.compare(value,h.keys[i]) == 0)
						return true;
				if(isLeaf(h))
					return add(value);
				return false;
				
			}

			private boolean insert(BtreeN h, String value, BtreeN parent, int depth){
                System.out.println("Depth: " + depth);
                for(int i = 0;i<M-1;i++)
                	System.out.println(h.keys[i]);
				if(isFull(h)){
						h = split(h,parent);
						
						//parent = temp[1];
				
					if(c.compare(value,h.keys[0]) < 0)
							return insert(h.links[0],value,h,depth+1);
					for(int i = 0; i < M-1; i++)					// if the value is greater than keys[i] search the right subtree
						if(c.compare(value,h.keys[i])>0)
							return insert(h.links[i+1],value,h,depth+1);
				}else if(isLeaf(h)){
					h = insertKey(h,value);
					return true;
				}
				
				if(c.compare(value,h.keys[0]) < 0)
						return insert(h.links[0],value,h,depth+1);
				for(int i = 0; i < M-1; i++)					// if the value is greater than keys[i] search the right subtree
					if(c.compare(value,h.keys[i])>0){
						return insert(h.links[i+1],value,h,depth+1);
				}System.out.println("EveryTime");		
		
				for(int i = 0; i < M-1; i++)
					if(c.compare(value,h.keys[i]) == 0)
						System.out.println("Already in tree");
				
				return false;
			}

			private BtreeN splitRoot(BtreeN h){
				System.out.println("           SPLITROOT");
				int middle = (M/2-1);						// middle of array based on indexing

				BtreeN newRoot = new BtreeN();	
				newRoot.keys[0] = h.keys[middle];	
				BtreeN rSib = new BtreeN();
				BtreeN lSib = new BtreeN();
				for(int i = 0; i <= middle; i++){
					rSib.links[i] = h.links[M/2+i];
					lSib.links[i] = h.links[i];
				}
				for(int i = 0; i < middle;i++){
					rSib.keys[i] = h.keys[M/2+i];
					lSib.keys[i] = h.keys[i];
				}

				h = null;
				newRoot.links[0] = lSib;				// new root links are the 2 new BtreeN nodes
				newRoot.links[1] = rSib;	
				setRoot(newRoot);
				return newRoot;
				
			}

			private BtreeN split(BtreeN h, BtreeN parent){
				if(parent == null)
					return splitRoot(h);
				System.out.println("              SPLIT");
				int middle = (M/2-1);
				BtreeN rSib = new BtreeN();
				BtreeN lSib = new BtreeN();
				for(int i = 0; i <= middle; i++){
					rSib.links[i] = h.links[M/2+i];
					lSib.links[i] = h.links[i];
				}
				for(int i = 0; i < middle;i++){
					rSib.keys[i] = h.keys[M/2+i];
					lSib.keys[i] = h.keys[i];
				}
				String mid = h.keys[middle];
				
				int pos = 0;
				parent = insertKey(parent,mid);
				for(int i = 0; i<M; i++){
					if(parent.links[i] == h){
						parent.links[i] = lSib;
						pos = i+1;
					}
				}
				parent = linksMoveOnePos(parent,pos);
				parent.links[pos] = rSib;
				h = null;
				return parent;

			}
 			private BtreeN insertLinks(BtreeN parent, BtreeN temp){
            	int pos = numberOfKeys(temp);
            	String hold = temp.keys[pos-1];
            	if(c.compare(hold,parent.keys[0]) < 0)
            		pos = 0;
            	for(int i = 0; i < numberOfKeys(parent); i++){
            		if(c.compare(hold,parent.keys[i])>0)
            			pos = i+1;
            	}
            	parent = linksMoveOnePos(parent,pos);
            	parent.links[pos] = temp;
            	return parent;
            }

            private BtreeN linksMoveOnePos(BtreeN h, int start){
            	for(int i = start; i<M-1;i++){
                    h.links[i+1] = h.links[i];
                }
                return h;
        	}

			private int numberOfLinks(BtreeN h){
				int i = 0;
				while(h.links[i] != null)
					i++;
				return i--;
			}
			private int numberOfKeys(BtreeN h){
				for(int i = 0; i <M-1; i++)
					if(h.keys[i] == null)
						return i;
				return 7;
			}
			
			private BtreeN transferLinks(BtreeN temp, BtreeN old){
				for(int i = 0; i < M/2; i++){
					temp.links[i] = old.links[i+(M/2)];
				}
				return temp;
			}

			private int numKeys(BtreeN h){
				for(int i = 0;i<M-1;i++)
					if(h.keys[i] == null)
						return i-1;
				return -1;
			}

			// Inserts key into node 
            private BtreeN insertKey(BtreeN h, String value){
            	int i = findPosToAdd(h,value);
            	h = moveOnePos(h,i,value);
            	return h;
            } 


			private int findPosToAdd(BtreeN temp, String value){
				for(int i = 0; i<M-2; i++){
                    	if(temp.keys[i] == null)
                        	return i;
                       	else if(temp.keys[i].compareTo(value)>0)
                       		return i+1;
                }
               	if(temp.keys[M-2] == null)
               		return M-1;
               	return 0;
			}
			private BtreeN transferKeys(BtreeN temp, BtreeN old){
				for(int i = 0; i < M/2-1;i++)
					temp.keys[i] = old.keys[i+M/2];
				return temp;
			}

			private BtreeN moveOnePos(BtreeN h, int start,String value){
				for(int i = start; i<M-2;i++)
					h.keys[i+1] = h.keys[i];
				h.keys[start] = value;
				return h;
			}
		}

	public static void main(String[] args) throws FileNotFoundException{
		Btree b = new Btree();
		File file = new File("words.txt");
		if(!file.exists())
			System.exit(1);
		Scanner sc = new Scanner(file);
		String next = sc.nextLine();
		int i = 0;
		while(i<1000){
			System.out.println("adding : " + next);
			System.out.println(b.add(next));
			next = sc.nextLine();
			i++;
		}
		System.out.println("all are added");
	}
}