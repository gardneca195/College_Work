import java.util.*;

public class BSTSplayTreeTest{
	public static SplayBST<Integer> splay;
	public static void main(String [] args){
		System.out.println("testing SplayBST\n");
		System.out.println("Inserting values into tree");
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a list of numbers ranging from 0-100 without duplicates: ");
		


		while(sc.hasNext()){
			splay = new SplayBST<Integer>();
			String s = "";
			if(sc.hasNext("quit"))
				break;
			else if(sc.hasNext("search")){
				s = sc.next();
				if(sc.hasNext()){
					s = sc.next();
					if(!splay.isEmpty()){
						splay.splaySearch(splay.root,Integer.parseInt(""+s));
						System.out.println("\n");
						splay.byLevel();
					}else
						System.out.println("MUST HAVE FULL TREE!");
				}
			}else{
				System.out.println();
				s = sc.next();
				Scanner i = new Scanner(s);
				ArrayList<Integer> a = new ArrayList<Integer>();
				while(i.hasNext())
					a.add(Integer.parseInt("" + i.next()));
				for(int j = 0;j < a.size(); j++)
					add(a.get(j));

				System.out.println("Printing Level Order Traversal Of Splay Tree: \n");
				splay.byLevel();
				System.out.println("(quit) to quit\n (search <int>) to search for a specific number");
				s = sc.next();
				a.clear();
			}
		}
	}


	// add method calls SplayBST's add method for me
	public static void add(int x){
		splay.add(x);
	}
	public static void print(){
		System.out.print("Level Order : ");splay.byLevel();System.out.print("\n");
	}

 	

}