import java.util.*;
import java.io.*;
public class Euler8{
    public static void main(String[] args)throws FileNotFoundException{
        File f = new File("num2.txt");
        Scanner sc = new Scanner(f);
        String num = "";
        while(sc.hasNextLine())
            num = num + sc.nextLine();
        System.out.println(num);
        int product = 0;
        int i1 = Integer.parseInt("" + num.charAt(0));
        System.out.println(i1);
        int i2 = Integer.parseInt("" + num.charAt(1));
        System.out.println(i2);
        int i3 = Integer.parseInt("" + num.charAt(2));
        System.out.println(i3);
        int i4 = Integer.parseInt("" + num.charAt(3));
        System.out.println(i4);
        int i5 = Integer.parseInt("" + num.charAt(4));
        System.out.println(i5);
        for(int i = 5; i <= num.length() -1; i++){
            System.out.println("Product: " + product);
            if(product < (i1 * i2 * i3 * i4 * i5))
                product =  (i1 * i2 * i3 * i4 * i5);
            i1 = i2; 
            System.out.println(i1);
            i2 = i3; 
            System.out.println(i2);
            i3 = i4;
            System.out.println(i3);
            i4 = i5;
            System.out.println(i4);
            i5 = Integer.parseInt("" + num.charAt(i));
            System.out.println(i5);
        }
    System.out.println(product);
    }
}
