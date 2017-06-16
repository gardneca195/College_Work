import java.util.*;
import java.io.*;
import java.math.*;
public class Euler12{

    public static void main(String [] args){
        long divisors = 0L;
        long triNum = 0L;
        long x = 1L;
        while(divisors <= 250){
            triNum = triNum + x;
            for(long i = 1L; i < Math.sqrt(triNum); i++){
                if(triNum % i == 0L)
                    divisors = divisors + 1L;
            }
            if(divisors <= 250){
                System.out.println("Triangle Number: " + triNum);
                System.out.println("divisors: " + divisors);
                divisors = 0L;
            }
            System.out.println();
            x = x + 1L;
        }
        System.out.println("Divisors: " + divisors);
        System.out.println("Triangle Number: " + triNum);
             
            
            
                
                


    }
}
