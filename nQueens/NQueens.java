/*
Name: Craig Gardner
Course: CIS 203 - Computer Science II
Assignment: 10
Due: 4/26/2013
*/
import java.util.*;

public class NQueens {

    //Instance Variables
    int count;
    public boolean error; // for testing if the queen is safe.

    public static void main (String [] args) {
        NQueens world = new NQueens(Integer.parseInt(args[0]));
    }

    // Parameters: n 0 number of queens to be placed on an nxn board f queens
    //                 if n is possible
    // Postcondition: print message if n isnt possible prints board of queens
    //               if n is possible
    public NQueens(int n) {
        count = 0;
        Stack<Pair> s = new Stack<Pair>();
        s.push(new Pair(0,0));
        while(s.size()!=n)
            s.push(testQueen(new Pair(0, s.size()),s,n));
        Board board = new Board(s);
        System.out.println(board);
    }

    // Parameters :     p-  queen to be checked
    //                  s - stack of pairs
    //                  n - size limiter
    // PostCondition : if a value of a queen is greater than n print message
    //                       that ther is no posible solution
    //
    // Return :         p - checked queen that is safe
    public Pair testQueen(Pair p, Stack<Pair> s, int n){
        error = false;
        Iterator i = s.iterator();
        while(i.hasNext()){
            error((Pair) i.next(), p, n);
            while(error){
                if(p.x<n-1){
                    p = new Pair(p.x+1, p.y);
                    error = false;
                    i = s.iterator();
                    while(i.hasNext())
                        error((Pair) i.next(), p, n);
                 } else{
                    p = (Pair) s.pop();
                    p = new Pair(p.x+1, p.y);
                    error = false;
                    i = s.iterator();
                    while(i.hasNext())
                        error((Pair) i.next(), p, n);
                        
                }
            count++;
            System.out.println(count);
            }
        }
        if(p.x>=n){
            System.out.println("No solution for " + n + " Queens.");
            System.exit(0);
        }
        return p;
    }
    // Parameters :     temp- queen already in stack 
    //                  p - queen to be chencked
    //                  n - size limiter
    // PreCondition : Error - false
    // Postcondition : Error - true if there is a conflict
    public void error(Pair temp, Pair p, int n){
        if((Math.abs(temp.y - p.y) == Math.abs(temp.x - p.x)) || (temp.x ==
                        p.x) || (temp.y == p.y) || (p.x >= n) || (p.y>= n)) 
            error = true;
    }
}
// You will want a Stack of Pairs. Each Pair is an (x,y) coordinate
// indicating where a queen has been placed on the board
class Pair {

    public int x;
    public int y;
 
    public Pair (int x, int y) {
	    this.x = x;
        this.y = y;
    }

    public String toString() {
	    return "(" + x + "," + y +")";
    }
}

// After your NQueens constructor has constructed a Stack of Pairs that
// represents the solution to the NQueens problem, the NQueens constructor
// should construct a Board object - why? The Board object has a toString()
// method that can then be used to print the board
class Board {
    int [] [] board;

    // construct a Board using Stack of Pairs
    public Board (Stack s) {
        int size = s.size();
	    board = new int [size][size];
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size; j++)
                    board[i][j] = 0;
        Iterator i = s.iterator();
        while (i.hasNext()) {
	    Pair p = (Pair) i.next();
            board[p.x][p.y] = 1;
        }
    }

    public String toString() {
	String s = "";
        int size = board.length;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++)
                if (board[i][j] == 0)
		            s += "| ";
                else
		            s += "|Q";
                s += "|\n";
	}
        return s;
    }

}
