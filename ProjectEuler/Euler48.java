import java.math.*;
public class Euler48{
    public static void main(String[] args){
        BigInteger big = new BigInteger("1");
        BigInteger big2 = new BigInteger("1");
        int x = 1000;
        while(x >= 0){
            big2 = new BigInteger("" + x); 
            big2 = big2.pow(x);
            System.out.println(big2.toString());
            big = big.add(big2);
            x--;
        }
        System.out.println(big.toString());
    }
}
