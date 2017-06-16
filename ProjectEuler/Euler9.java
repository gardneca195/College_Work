import java.util.*;
import java.io.*;
import java.math.*;
public class Euler9{

    public static void main(String [] args)throws FileNotFoundException{
      for(int a = 0; a < 1000; a++)
         for(int b = a + 1; b < 1000; b++)
             for(int c = b + 1; c < 1000; c++)
                 if(((a * a) + (b* b) == (c * c))&&(a+b+c==1000))
                 System.out.println(a*b*c);
                                       
    }
}
