import java.util.*;
import java.io.*;
import java.math.*;
public class Euler42{

    public static void main(String [] args)throws FileNotFoundException{
        File f = new File("word.txt");
        Scanner sc = new Scanner(f);
        String s = sc.next();
        s = s.replace(',',' ').replace('\"','');
        System.out.println(s);
        ArrayList<String> strings = new ArrayList<String>();
        int triNum = 0;
        ArrayList<Integer> tri = new ArrayList<Integer>();
        tri.add(1);
        tri.add(3);
        int x = 3;
        while(tri.size() < 100){
            tri.add(x + tri.get(x - 2));
            x++;
        }
        String temp = "";
        while(sc.hasNext())
            strings.add(sc.next());
        for(String s : strings){
            temp = s.substring(1,s.length()-1);
            System.out.println(temp);
            for(int i = 0; i <= temp.length(); i++){
                triNum += (int)(temp.charAt(i) + ('A' - 'a')); 
            }if(true)
                break;
       }
       }
}
