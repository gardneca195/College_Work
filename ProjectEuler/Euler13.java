import java.util.*;
import java.io.*;
import java.math.*;
public class Euler13{

    public static void main(String [] args)throws FileNotFoundException{
        File f = new File("num2.txt");
        Scanner sc = new Scanner(f);
        BigInteger big = new BigInteger(sc.nextLine());
        while(sc.hasNextLine())
            big = big.add(new BigInteger(sc.nextLine()));
        
        System.out.println("First ten digits: " +
                            big.toString().substring(0,10));
    }
}
