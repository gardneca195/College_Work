import java.*;
public class Euler3{

    public static void main(String [] args){
        long largestPrime = 600851475143L;
        for(long i = 2; i <= Math.sqrt(largestPrime); i++){
            if(checkPrime(i))
                if(largestPrime % i == 0)
                    System.out.println("PRIME: " + i);  
        }
    }
    public static boolean checkPrime(long num){
        for(long i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0)
                return false;
        return true;
    }
}
