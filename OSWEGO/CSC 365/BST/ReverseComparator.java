import java.util.Comparator;
import java.lang.Comparable;

/**
 * Reverse Comparator is a wrapper class for T's compareTo -- 
 * @author EWenderholm
 */

 public class ReverseComparator<E extends Comparable<E>> implements Comparator<E>{

 	/**
 	 * uses T's compareTo method to determin the ordering
 	 */

 	public int compare(E a, E b){
 		return b.compareTo(a);
 	}

 	public boolean equals(Object obj){
 		return true;
 	}
 	
 }