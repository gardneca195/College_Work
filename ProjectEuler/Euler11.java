import java.util.*;
import java.io.*;
public class Euler11{
	public static void main(String[] args)throws FileNotFoundException{
		final long startTime = System.currentTimeMillis();
		File file = new File("num.txt");
		Scanner sc = new Scanner(file);
		int [][] grid = new int [20][20];
		for(int i = 0; i<grid.length; i++){
			for(int j = 0; j<grid.length; j++)
				grid [i][j] = sc.nextInt();
		}

		int up = up(grid);
		System.out.println("\nup "+up+"\n");
		int right = right(grid);
		System.out.println("right "+right+"\n");
		int diagonalNeg = diagonalNeg(grid);
		System.out.println("- Slope "+diagonalNeg+"\n");
		int diagonalPos = diagonalPos(grid);
		System.out.println("+ Slope "+diagonalPos+"\n");
		final long endTime = System.currentTimeMillis();
		System.out.println("Total execution time: " + (endTime - startTime) );
	}

	public static int up(int [][] grid){
		int one;
		int two;
		int three;
		int four;
		int max = 0;
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 17; j++){
				one = grid[i][j];
				two = grid[i][j+1];
				three = grid[i][j+2];
				four = grid[i][j+3];
				int temp = one*two*three*four;
				if(max<temp)
					max = temp;
			}	
		}
		return max;
	}
	public static int right(int [][] grid){
		int one;
		int two;
		int three;
		int four;
		int max = 0;
		for(int i = 0; i < 17; i++){
			for(int j = 0; j < 20; j++){
				one = grid[i][j];
				two = grid[i+1][j];
				three = grid[i+2][j];
				four = grid[i+3][j];
				int temp = one*two*three*four;
				if(temp > max)
					max = temp;
			}	
		}
		return max;
	}
	public static int diagonalNeg(int [][] grid){
		int one;
		int two;
		int three;
		int four;
		int max = 0;
		for(int i = 0; i < 17; i++){
			for(int j = 0; j < 17; j++){
				one = grid[i][j];
				two = grid[i+1][j+1];
				three = grid[i+2][j+2];
				four = grid[i+3][j+3];
				int temp = one*two*three*four;
				if(temp > max)
					max = temp;
			}	
		}
		return max;
	}
	public static int diagonalPos(int [][] grid){
		int one;
		int two;
		int three;
		int four;
		int max = 0;
		for(int i = 0; i < 17; i++){
			for(int j = 3; j < 20 ; j++){
				one = grid[i][j];
				two = grid[i+1][j-1];
				three = grid[i+2][j-2];
				four = grid[i+3][j-3];
				int temp = one*two*three*four;
				if(temp > max)
					max = temp;
			}	
		}
		return max;
	}
}