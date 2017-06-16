import java.*;
public class Euler6{
    public static void main(String[] args){
        int squaresSum = 0;
        int sum = 0;
        int sum1 = 0;
        for(int i = 1; i <= 100; i++){
            int temp = i * i;
            squaresSum += temp;
        }
        for(int i = 1; i <= 100; i++){
            sum1 = sum1 + i;
        }
        sum = sum1 * sum1;
        System.out.println(sum - squaresSum);            
    }
}
            
