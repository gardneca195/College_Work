import java.util.*;
public class Deck{

    public ArrayList<String> Deck;
    private String num = "23456789";
    private String let = "JQKA";
    public int pointer;

    public Deck(){
        this.Deck = makeDeck(); 
    }
    private ArrayList<String> makeDeck(){
        ArrayList<String> temp = new ArrayList<String>();
         // Adding Diamonds
        for(int i = 0; i < num.length(); i++)
            temp.add(num.charAt(i) + " Diamonds"); 
        temp.add("10 Diamonds");
        for(int i = 0; i < let.length(); i++)
            temp.add(let.charAt(i) + " Diamonds");

        // Adding Spaids
        for(int i = 0; i < num.length(); i++)
            temp.add(num.charAt(i) + " Spaids");
        temp.add("Spaids 10");    
        for(int i = 0; i < let.length(); i++)
            temp.add(let.charAt(i) + " Spaids");

        // Adding Clubs
        for(int i = 0; i < num.length(); i++)
            temp.add(num.charAt(i) + " Clubs");
        temp.add("10 Clubs");    
        for(int i = 0; i < let.length(); i++)
            temp.add(let.charAt(i) + " Clubs");

        // Adding Hearts
        for(int i = 0; i < num.length(); i++)
            temp.add(num.charAt(i) + " Hearts");
        temp.add("10 Hearts"); 
        for(int i = 0; i < let.length(); i++)
           temp.add(let.charAt(i) + " Hearts"); 
        return temp;
    }
    public String deal(){
        return Deck.remove(0);
    }
    public void shuffle(){
        ArrayList<String> temp = new ArrayList<String>();
        Random rand = new Random();
        while(!Deck.isEmpty()){
            int temp2 = rand.nextInt(Deck.size());
            temp.add(Deck.get(temp2));
            Deck.remove(temp2);
        }
        Deck = temp;
    }
}
