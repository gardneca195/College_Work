/*
 *  Name: Brenden Hedding
 *  Course: CIS 201 - Computer Science I
 *  Section: 001
 *  Assignment: 8
 */

import java.util.*;

public class RockPaperScissors {

      //total duels
    public static int totalDuels = 0;
    //total wins
    public static int wins = 0;
    //totall losses 
    public static int losses = 0; 
    //number of ties
    public static int ties = 0;
  

  public static void main(String [] args) {
      introduction();
      Scanner input = new Scanner(System.in);
      int duels = getDuels(input);
      System.out.println("Number of duels entered: " + duels);
      playManyGames(input, duels);
  }
  
  public static void introduction(){
     //prints out the opening introduction
     System.out.println();
     System.out.println("This program plays duels of Rock-Paper-Scissors");
     System.out.println("against the computer. You'll type in your guess");
     System.out.println("of (R)ock, (P)aper, or (S)cissors and try to");
     System.out.println("beat thee computer as many times as you can.");
     System.out.println();
     System.out.print("Best out of how many duels? (must be odd) ");
     
  }
  
  public static int getDuels(Scanner input) {
     int number = input.nextInt();
    
     while (number % 2 != 1) {
	     System.out.print("Invalid number of duels.");
	     System.out.println("Type a positive odd number!");
	     System.out.print("Best out of how many duels? (must be odd) ");
	     number = input.nextInt();
     } 
     return number;
  }
  
  //decides who wins the game
  public static void playManyGames(Scanner input, int duels ) {
      String user = "";
      String comp = "";
      String winner = "";
      do {
         for (int i = 1; i <= duels;i++) {
            System.out.print("Duel " + i + ": ");
            System.out.print("Rock (r), Paper (p), or Scissors? ");
            user = getUserWeapon(input);
            comp = getRandomWeapon();
            winner = getWinner(user,comp);
            System.out.println();
            if (winner.equals("C")){
                System.out.println("You LOSE!");
                wins++;
            }else if (winner.equals("U")){
                System.out.println("You WIN! =)");
                losses++;
            }else
                 ties++;
          }
         
      
      } while (playAgain(input));
          reportStats(totalDuels, wins, losses);
      
  }
  
  public static boolean playAgain(Scanner input) {
      String yes = "y";
      String word = "";
      System.out.print("Do you want to play again? ");
      word = input.next();
      word = word.toLowerCase();
      if (word.equals(yes))
         return true;
      else 
             return false;
  }
  
  
  public static String getUserWeapon(Scanner input) {
      String weapon = "";
      weapon = input.next().toUpperCase();
      while(!(weapon.equals("R") || weapon.equals("P") || weapon.equals("S"))){
        System.out.println("Please enter an  \"r\" \"p\" or \"s\": ")
        weapon = input.next().toUpperCase();
      }
      return weapon;
  }
  
  public static String getRandomWeapon() {
      Random rand = new Random();
      int random = rand.nextInt(2)+1;
      if(random == 0)
        return "R";
      else if(random == 1)
        return "P";
      else
        return "S";

  }
  public static String getWinner(String user, String comp){
    if(user.equals(comp))
      return "";
    else if(user.equals("R") && comp.equals("S"))
      return "U";
    else if(user.equals("P") && comp.equals("R"))
      return "U";
    else if(user.equals("P") && comp.equals("S"))
      return "C";
    else if (user.equals("S") && comp.equals("P"))
      return "U";
    else if(user.equals("S") && comp.equals("R"))
      return "C";
    else 
      return "C";
  }
  
  public static void reportStats (int totalDuels, int wins, int losses) { 
  System.out.println("Overall results:");
  System.out.println("total duels = " + totalDuels);
  System.out.println("wins = " + wins);
  System.out.println("losses = " + losses);
  System.out.println("ties = " + (ties));
  System.out.println("win % = " + ((wins / totalDuels) * 100));
  System.out.println();
  }
  
  
  
  
}