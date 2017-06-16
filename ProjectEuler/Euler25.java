import java.util.*;
import java.io.*;
import java.math.*;
public class Euler25{
    // Fibonacci Sequence : Recurrence relation
    public static void main(String [] args){
        BigInteger x = new BigInteger("1");
        BigInteger y = new BigInteger("1");
        BigInteger z = new BigInteger("0");
        int f = 2;
        while(z.toString().length() != 1000){   
            z = x.add(y);
            x = y;
            y = z;
            f++;
            System.out.println(z);
        }   
        System.out.println(f);                   
    }
}
