import java.util.*;
import java.io.*;
import java.math.*;
public class Euler19{

    public static void main(String [] args){
        int year = 1900;
        int day = 1;
        int month = 1
        while(year <= 2000){
            if(month == 1 || month == 3 || month == 5|| month == 7 || 
                                                    month == 10 || month == 12)
                day += 31;
            else if((month == 2) && (year % 4 == 0) || (year % 400 == 0)
                                                            && (year % 100 == 0))
                day += 29;
            else if(month == 2)
                day += 28;
            else if(month == 4 || month == 6 || month == 8 || month == 9
                                                                 || month == 11)
                day += 30
            if((year % 4 == 0) || (year % 400 == 0) && (year % 100 == 0)) && (day == 366))
                year++;
            else if(day == 365)
                year++;
            if
                
            
    }
}
