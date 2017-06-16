import java.util.*;
public class Euler17{

    public static void main(String [] args){
        ArrayList<String> ones = new ArrayList<String>();
        ArrayList<String> tens = new ArrayList<String>();
        ArrayList<String> special = new ArrayList<String>();   
        ones.add("one");ones.add("two");ones.add("three");ones.add("four");ones.add("five");
        ones.add("six");ones.add("seven");ones.add("eight");ones.add("nine");special.add("ten");
        special.add("eleven");special.add("twelve");special.add("thirteen");
        special.add("fourteen");special.add("fifteen");special.add("sixteen");
        special.add("seventeen");special.add("eighteen");special.add("nineteen");
        tens.add("Twenty");tens.add("thirty");tens.add("forty");tens.add("fifty");
        tens.add("sixty");tens.add("seventy");tens.add("eighty");tens.add("ninety");
        int count = 0;
        int singles = 0;
        int doubles = 0;
        int specials = 0;
        String zero = "";
        for(int i = 0; i < 9; i++){
            singles += ones.get(i).length();  //count ones
        }
        System.out.println(singles + " 1");
        for(int i = 0; i < 10; i++){
            specials += special.get(i).length(); //count from 10- 19
        }
        System.out.println(specials  + " 2");
        for(int i = 0; i < 8; i++){
             doubles += 10*(tens.get(i).length()); // count 20-99
        }
        doubles = (doubles + (8*36)); // 1-9 occur 8 more times
        System.out.println(doubles+" 3");
        count = singles + doubles + specials; //1-99  
        System.out.println(count+" 4");
        count = count * 9; //1-99 occur 9 times
        System.out.println(count+" 5");
        count = count + singles*100; //1-9 occur 100 times
        count = count + "hundred".length()*9; //hundred occurs 9 times
        System.out.println(count+" 6");
        count = count + "hundredand".length()*9*99;//hudred and occurs 9*99 times
        System.out.println(count+" 7");
        count = count + singles + doubles + specials;//add 1-99 once more
        count += "onethousand".length(); // add onethousand length to count
        System.out.println(count);

        

    }
}
