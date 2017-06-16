import java.util.*;
import java.lang.*;
import java.io.*;


/**
 * Natural Comparator is a wrapper class for T's compareTo -- 
 * @author EWenderholm
 */

 public class NaturalComparator<E extends Comparable<E>> implements Serializable, Comparator<E>  {

 	/**
 	 * uses E's compareTo method to determine the ordering 
 	 */

 	 public int compare(E a, E b){
 	 	return a.compareTo(b);
 	 }

 	 public boolean equals(Object obj) {
 	 	return true;
 	 }
 }