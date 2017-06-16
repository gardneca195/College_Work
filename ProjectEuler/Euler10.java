import java.*;
public class Euler10{

    public static void main(String [] args){
        long sum = 17;
        int x = 11;
        while(x < 2000000){
            if(checkPrime(x))
                    sum += x;
            System.out.println("Sum: " + sum);  
            x++;
        }
    }
    public static boolean checkPrime(int num){
        for(long i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0)
                return false;
        return true;
    }
}
