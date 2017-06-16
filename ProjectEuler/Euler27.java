import java.util.*;
import java.io.*;
import java.math.*;
public class Euler27{

    public static void main(String [] args){
        long a = 0;
        long b = 0;
        long n = 0;
        long sum = 0;
        long count = 0;
        long large = 0;
        for(long i = 0; i < 1000; i++){
            n = i;
            for(long j = 0; j < 1000; j++){
                a = j;
                for(long k = 0; k < 1000; k++){
                    b = k;
                    sum = n * (n * a) + b;
                    for(long l = 1; l < Math.sqrt(sum); l++){
                        if(checkPrime(sum % l))
                            count++;
                    }
                    if(count > large)
                        large = count;
                        System.out.println(large);
                        count = 0;
                    count = 0;
                }
            }
        }
        System.out.println(a * b);
    }
    public static boolean checkPrime(long num){
        for(long i = 2; i <= Math.sqrt(num); i++)
            if(num % i == 0)
                return false;
        return true;
    }
}
