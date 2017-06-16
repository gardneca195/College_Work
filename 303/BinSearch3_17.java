/* Craig Gardner
 * Analysis of Algorithms
 * CIS - 303
 * Assignment 2 
*/


import java.util.*;

public class BinSearch3_17 {

    public static int BIN_CALLS = 0;
    public static int SEQ_ITS = 0;

    public static void main (String [] args) {
	int [] a = new int[1000000];
        fillRandom(a);
        test (a, 9);
	for(int i =0; i < 1000000; i++)
	    a[i] = 9;
        test (a, 9);
	for(int i =0; i < 1000000; i++)
	    a[i] = 2;
        test (a, 9);
	a[999999] = 9;
        test (a, 9);

    }

    public static void test (int [] a, int target) {
        BIN_CALLS = 0;
        SEQ_ITS = 0;
        System.out.println("Using binSearch, smallest position of " + 
                            target +": " + 
                            binSearch(a, target));
        System.out.println("VERIFY: Using seqSearch, smallest position of " + 
                            target +  ": " + 
                            seqSearch(a, target));
        System.out.println("BIN_CALLS " + BIN_CALLS);
        System.out.println("SEQ_ITS " + SEQ_ITS);

    }

    public static void fillRandom (int [] a) {
        Random rand = new Random();
	    int i = 0;
        int value = 1;
        while (i < a.length) {
	        int times = rand.nextInt(100000);
            int count = 0;
            while (i < a.length && count < times) {
		        a[i++] = value;
                count++;
	        }
            value++;
	    }
    }

    // finish binSearch here
    // Returns: FIRST position of the target in the array
    public static int binSearch(int [] a, int target) {
        if(target == a[0]){
            BIN_CALLS++;
            return 0;
        }
        //return position of greatest element <= target
        int l = -1;
        int r = a.length;     // l and r beyond array bounds
        while(l + 1 != r){     // Stop when l and r meet
            BIN_CALLS++;
            int i = (l+r)/2;   // Look at middle of subarray
            if(target < a[i])  // In left half
                r = i;
            if(target == a[i]){ // Found it
                  return i;//checkArray(i, a, target);
             }
            if(target > a[i])  // In the right half
                l = i;
        }

        // Search value is not in array
        if(1 < l)
            return -1;  // No value less than target
        else
            return 1;   // 1 at first value less than target
    }

    public static int checkArray(int pos, int [] a, int target){
        for(int i = pos; i >= 0; i--){
            BIN_CALLS++;
            if(target != a[i])
                return i+1;
        }
        return pos;
    }

    public static int seqSearch(int [] a, int target) {
	for (int i = 0; i < a.length; i++) {
            SEQ_ITS++;
            if (a[i] == target) return i;
	}
        return -1;
    }
}