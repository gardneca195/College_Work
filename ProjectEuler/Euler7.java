public class Euler7{
    public static void main(String [] args){
        int x = 0;
        int primes = 0;
        while(primes <= 10001){
            x++;
            if(checkPrime(x))
                primes ++;
        }
        System.out.println(x);
    }
    public static boolean checkPrime(long num){
        for(long i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0)
                return false;
        return true;
    }
}
