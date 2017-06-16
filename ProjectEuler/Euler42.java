import java.util.*;
import java.io.*;
import java.math.*;
public class Euler42{

    public static void main(String [] args)throws FileNotFoundException{
        File f = new File("word.txt");
        Scanner sc = new Scanner(f);
        String s2 = sc.next();
        s2 = s2.replace(',',' ').replace('"',' ');
        Scanner sc2 = new Scanner(s2);
        ArrayList<String> strings = new ArrayList<String>();
        while(sc2.hasNext())
            strings.add(sc2.next());
        int triNum = 0;
        ArrayList<Integer> tri = new ArrayList<Integer>();
        tri.add(1);
        tri.add(3);
        tri.add(6);
        int x = 4;
        while(tri.size() < 100){
            tri.add(x + tri.get(x - 2));
            x++;
        }
        System.out.println(tri);
        String temp = "";
        int count = 0;
        for(int j = strings.size() - 1; j>=0; j--){
            for(int i = strings.get(j).length() -1; i >= 0; i--){
                System.out.println(strings.get(j));
                triNum += (strings.get(j).charAt(i) - ('A'));
                System.out.println(triNum);
            } 
            if(tri.contains(triNum))
                count ++;
                strings.remove(j);
                triNum = 0;
            triNum = 0;
       }
       System.out.println(count);
   }
}
