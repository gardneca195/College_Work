public class Euler1{

	public static void main(String[] args){
		int sum = 0;
		for(int i = 1; i < 1000000; i++){
			if(i % 3 == 0 || i % 5 == 0){
                System.out.println("Divisible by 3 or 5" + i);
				sum += i;
		    }
		}
		System.out.println(sum);
	}

}
