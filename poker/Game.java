import java.util.*;
public class Game{

    public  int pot;
    public  int firstBetter;
    public  int prevBet;

    public  ArrayList<player> playerList;
    public  ArrayList<String> tableCards;

    public boolean again;  


    public void main(String[]args){
        playerList = play();
        firstBetter = 0;
        start(playerList);
    }
    public ArrayList<player> play(){
        ArrayList<player> playerList = new ArrayList<player>();

        System.out.println("Welcome to Lose It All Poker!");
        System.out.println("Here you will test your skillz and " +
                            "pretend to be the best poker player ever.");
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Lets get Started!");
        System.out.print("Enter First Player Name: ");      //first player initialization
        String name = sc.next();
        System.out.println();
        playerList.add(new player(name));
        System.out.println("Thank You!");
        System.out.println();
        System.out.println("How many Players Will be joining you?"); // dertermining size of game
        int players = sc.nextInt();
        for(int i = 0; i < players; i++){
            System.out.print("Enter player " + i+2 + "'s name: ");    
            name = sc.next();
            System.out.println();
            playerList.add(new player(name, cash));
        }
        System.out.println("Thank You " + playerList.get(0).Name);
        System.out.println("Lets Get Started!");
        System.out.println("Press \"A\" to Ante up all players!");
        char E = 'A';
        String S = sc.next().toUpperCase();
        char C = S.charAt(0);
        for(int i = 0; i < playerList.size(); i++){
            if(E == C){
                pot += playerList.get(i).ante();
            }
        }
        for(int i = 0; i <playerList.size(); i++)
            System.out.println(playerList.get(i).name  + " : " + 
                                            playerList.get(i).cash);
        System.out.println("Pot: " + pot);

        System.out.println("Thank you!");
        System.out.println("Here are your cards!");
        return playerList;
    }
    public  void start(ArrayList<player> list){
        Deck deck = new Deck();
        deck.shuffle();
        again = true;
        Scanner sc = new Scanner(System.in);
        while(again){
            boolean check = false;
            if(firstBetter >= list.size())
                firstBetter %= list.size();
            for(int i = 0; i < playerList.size(); i++){
                for(int j = 0; j < 2; j++){
                    playerList.get(i).hand[j] = deck.deal(); // Dealing cards for players
                }                                       // 2 cards at a time to each
            }
            deck.deal(); // Burning a card
            System.out.println();
            for(int i = 0; i < list.size(); i++){
                System.out.println(list.get(i).show());
                System.out.println();
            }
            System.out.print(list.get(firstBetter).name + " would you like to (bet or check)?");
            String play = sc.next();
            if(play.equals("bet")){
                int bet = sc.nextInt();
                list.get(firstBetter).bet(bet);
            if(play.equals("check")){
                check = ;
            }
            prevBet = bet;
            list.get(firstBetter).bet(bet);
            pot += bet;
            bets(firstBetter);
            while(check){
                System.out.print(list.get(firstBetter).name + " would you like to (raise or call)");
                
                System.out.println("The Previous bet was " + prevBet);
                play = sc.next();
                if(play.equals("raise")){
                    System.out.println("how much do you want to raise?  ");
                    int raise = sc.nextInt();
                    playerList.get(current).bet(raise + prevBet);
                    prevBet += raise;
                    check;
                }


//FIXME -------------------------------------------------------------------------


                else if(play.equals("check")){
                    check = true;
                }
                bets(firstBetter);
            }       






            System.out.println("Another Hand? (yes/no)");
            String check = sc.next();
            if(check.equals("no"))
                again = false;
            for(int i = 0; i<playerList.size(); i++)
                playerlist.get(i).freshHand();
            firstBetter++;
        }
    }
    public  void bets(int firstBetter){
        boolean allThrough = false;
        int pos = 1;
        Scanner sc = new Scanner(System.in);
        while (!allThrough){
            int current = firstBetter + pos;
            if(current >= playerList.size())
                current %= playerList.size();
            System.out.println(playerList.get(current).name + " would you like to(call, raise, fold)?");
            String play = sc.next();
            if(play.equals("raise")){
                System.out.println("how much do you want to raise?  ");
                int raise = sc.nextInt();
                playerList.get(current).bet(raise + prevBet);
                prevBet += raise;
            } 
            if(play.equals("call"))
                playerList.get(current).bet(prevBet);
            if(play.equals("fold"))
                playerList.get(current).fold = true;
            pos++;
            if(current == firstBetter-1)
                allThrough = true;
        }

    }
}
