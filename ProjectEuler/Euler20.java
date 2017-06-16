import java.util.*;
import java.io.*;
import java.math.*;
public class Euler20{

    public static void main(String [] args){
        BigInteger factorial = new BigInteger("1");
        BigInteger sum = new BigInteger("0");
        for(int i = 2; i <= 100; i++)
            factorial = factorial.multiply(new BigInteger("" + i));
        for(int i = 0; i < factorial.toString().length(); i++)
            sum = sum.add(new BigInteger("" + factorial.toString().charAt(i)));   
        System.out.println(sum);
            
    }
}
