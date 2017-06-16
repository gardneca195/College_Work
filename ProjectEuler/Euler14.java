import java.util.*;
public class Euler14{
	// Fine longest collatz sequence;
	public static void main(String[] args){
		ArrayList<Long> chain = new ArrayList<Long>();
		

		for(Long i = 1000000L; i > 500000L; i--){
			ArrayList<Long> temp = new ArrayList<Long>();Long n = i;
			temp.add(n);
			while(n!=1){
				if((n%2) == 0){
					n= (n/2);
					temp.add(n);
				}else if(n!=1){
					n = ((n*3)+1);
					temp.add(n);
				}
			}
			if(temp.size() > chain.size())
				chain = temp;
		}
		System.out.println(chain.get(0));
		System.out.println(chain.size());

	}

}