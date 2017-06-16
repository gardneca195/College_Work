public class test{
    public static void main(String[] args){
        Deck deck = new Deck();
        for(int i = 0; i < 52; i++)
            System.out.print(deck.get(i) + " ");
       deck.shuffle();
       System.out.println();
        for(int i = 0; i < 52; i++)
            System.out.print(deck.get(i) + " ");
    }
}
