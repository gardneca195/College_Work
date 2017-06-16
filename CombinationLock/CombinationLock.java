/*
Craig Gardner



*/
import java.*;
public class CombinationLock{

    // Instance variables
    private ListNode list; // list - list node of all symbols and data
    private int top;
    
    //Constructor
    
    public CombinationLock(){
        this.list = new ListNode();
        makeList();
        this.top = 0;
        makeLock();
    }    

    // Post Condition - prints out the figure of the lock
    public void makeLock(){
        // space
        System.out.println();

        // top if < 10 we make 13 spaces for the integer
        // else 12 spaces for the intger
        if(top < 10){
            System.out.println(makeSpaces(13) + top);
        }else{
            System.out.println(makeSpaces(12) + top);
        }            

        // makes the top symbol  and top middle of the lock 
        for(int i = 0; i <= 9; i++){
            if(i == 0){
                System.out.println(makeSpaces(13) + list.getSymbol(top));
            // makes sure that top - i does not go negative
            }else{
                if(top - i > -1){
                    System.out.print(makeSpaces(13 - i) + list.getSymbol(top - i));
                }else 
                    System.out.print(makeSpaces(13-i) + list.getSymbol(top - i + 40));
                    
                // makes sure top + i does not go above forty and resets to 0
                if(top + i > 39){
                    int temp = 0;
                    System.out.println(makeSpaces(2* i -1) +
                                                     list.getSymbol(temp + i));
                }else
                    System.out.println(makeSpaces(2 * i - 1)
                                                 + list.getSymbol(top + i));
            }
        }

        // middle of the lock
        // makes sure top - 10 does not go below 0
        if(top - 10 < 0){
            String spaces = " ";
            System.out.print(spaces + (top - 10 + 40) + 
                                              list.getSymbol(top -10 + 40));
        }else
            String spaces = "  ";
            System.out.print(spaces + list.get(top - 10) + 
                                                  list.getSymbol(top - 10));
        // makes sure top + 10 does not exceed 39
        if(top + 10 > 39){
            String spaces = " ";
            System.out.println(makeSpaces(19) + list.getSymbol((top + 10)
                                           % 10)+ list.get((top +10) % 10));
        }else{ 
            String spaces = "  ";
            System.out.println(makeSpaces(19) + list.getSymbol(top + 10) 
                                                       + list.get(top + 10));
        }
        int temp = 1;
        // bottom middle of the lock
        for(int i = 9; i >= 1; i--){
            if(top - 10 -  temp < 0){
                System.out.print(makeSpaces(13-i)+ list.getSymbol(top + 20 + i));
            }else 
                System.out.print(makeSpaces(13-i)+ list.getSymbol(top-10 - temp));
            if(top + 10 + temp > 39){
                System.out.println(makeSpaces(2*i-1) + list.getSymbol((temp -1)));
            }else
                System.out.println(makeSpaces(2*i-1)+ list.getSymbol(top + 10 +temp));
            temp++;
        }
        // bottom of the lock.
        if( top + 20 >= 40){
            //symbol
            System.out.println(makeSpaces(13)+list.getSymbol(top - 20));
            // get
            System.out.println(makeSpaces(13) + list.get(top%10));
        }else{
            //symbol
            System.out.println(makeSpaces(13) + list.getSymbol(top + 20));
            //get
            System.out.println(makeSpaces(13) + list.get(top + 20));
        }
        System.out.println();
    }
    // PostCondition - sets list to original default list
    public void makeList(){
        ListNode temp = new ListNode();
        for(int i = 0; i <= 39; i++)
            temp.add(i);
        list = temp;
    }

    // Returns - String spaces - amount of spaces
    // Parameters - x - amount of spaces you want
    private String makeSpaces(int x){
        String spaces = "";
        for(int i = 1; i <= x; i++)
            spaces += " ";
        return spaces;
    }

    // Parameters - int pos - position to turn the lock to.
    //              boolean direction - direction false turn lock to the left 
    //                                            true turn lock to the right
    // Post Condition - sets the symbols to the correct position
    public void turn(boolean direction, int pos){
        if(direction == true){
            if(top == pos){
                top--;
                list.changeSymbol(top + 1);
            }while(top != pos){
                 list.changeSymbol(top);
                top--;
                if(this.top == -1)
                    this.top = 39;
                System.out.print(list.getSymbol(top));
            }
            System.out.println();
            makeLock();
            makeList();
        }
        if(direction == false){
            if(top == pos){
                this.top++;
                list.changeSymbol(top-1);
            }while(top != pos){
                list.changeSymbol(top);
                 this.top++;
                if(this.top ==40)
                    this.top = 0;
                System.out.print(list.getSymbol(top));
            }
            System.out.println();
            makeLock();
            makeList();
        }

    }
}






class ListNode {

    //Instance Variables
    public int data;       // data stored in this node
    public ListNode next;  // link to next node in the list
    public String symbol; // symbol stored in this node

    //Constructors
    
    // post: constructs a node with data 0 and null link
    public ListNode() {
        this.data = 0;
        this.next = null;
        this.symbol = "-";
    }

    // post: constructs a node with given data and null link
    public ListNode(int data) {
        this.data = data;
        this.next = null;
        this.symbol = "-";
    }

    // post: constructs a node with given data and given link
    public ListNode(int data, ListNode next) {
        this.data = data;
        this.next = next;
        this.symbol = "-";
    }
    
    // post : returns the position of the first occurrence of the given
    //        value (-1 if not found)
    public void add(int value){
        if (this.next == null) {
            this.next = new ListNode(value);
        } else {
            ListNode current = this.next;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new ListNode(value);
        }
    }
    
     // pre : 0 <= i < size()
    // post: returns a reference to the node at the given index
    public ListNode nodeAt(int index){
        ListNode current = this.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }
        // pre : 0 <= index < size()
    // post: returns the integer at the given index in the list
    public int get(int index) {
        return nodeAt(index).data;
    }
    // pre : 0 <= index < size().
    // post : returns the symbol at the given index in the list.
    public String getSymbol(int index){
        return nodeAt(index).symbol;
    }
    // pre : 0 <= index < size().
    // post : returns the symbol changed either a "+" or a "-"
    public void changeSymbol(int index){
        if(nodeAt(index).symbol.equals("-"))
            nodeAt(index).symbol = "+";
        else 
            nodeAt(index).symbol = "-";
    }
    
}
