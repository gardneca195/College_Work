public class player{

    public String name;
    public int cash;
    public String [] hand = new String[2];
    public boolean fold;
    public boolean call;
    public boolean sitOut;
    public boolean dealer;

    public player(String name, int cash){
        this.call = false;
        this.fold = false;
        this.sitOut = false;
        this.dealer = false;
        this.name = name;
        this.cash = 1000;
    	hand[0] = "";
    	hand[1] = "";
    }
    public String getName(){
    	return this.name;
    }
    public int ante(){  //removes 10 for the ante 
    	cash -= 10; 
    	return 10;
    }
    public String show(){
    	return this.name+ " : " + hand[0] + " , " + hand[1];
    }
    public void bet(int bet){
        cash -= bet;
    }
    public void freshHand(){
        this.call = false;
        this.call = true;
        this.sitOut = false;
    }

}
    
