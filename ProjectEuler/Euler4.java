public class Euler4{
    public static void main(String[]args){
        long palindrome = 0;
        for(int i = 100; i <= 999; i++)
            for(int j = 100; j <= 999; j++){
                long temp = j * i;
                if(checkPalindrome(temp)){
                    System.out.println(j * i);
                    if(temp > palindrome)
                        palindrome = temp;
                }
            }
       System.out.println("Largest Palindrome: " + palindrome);
    }
    public static boolean checkPalindrome(long num){
        String pal = "" + num;
        String temp = "";
        for(int i = pal.length() -1; i >= 0; i--)
            temp += pal.charAt(i);
        if(temp.equals(pal))
            return true;
        return false;
   }
                 
}
