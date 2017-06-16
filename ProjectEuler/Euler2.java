public class Euler2{
	
    public static void main(String[] args){
		int sum = 0;
		int first = 1;
		int second = 1;
		int third = 0;
		while(third <= 4000000){
			third = first + second;
			if(third % 2 == 0)
				sum += third;
            first = second;
   		    second = third;
        	System.out.println(third);
        }
        System.out.println(sum);
	}
}
