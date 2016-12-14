package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Adventure {
	
	public static boolean zero(int[][] a){
		for(int i=0; i<a.length; i++)
			for(int j=0; j<a[i].length; j++)
				if(a[i][j]!=0)
					return false;
		return true;
	}
	
	public static void print(int[][] a){
		for(int[] x: a){
			for(int y: x)
				System.out.print(y + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	public static int multiply(int[][] a){
		int[][] matrix = a.clone();
		int c = 1;
		while(!zero(a)){
			for(int i=0; i<a.length; i++){
				int[] curr = matrix[i];
				int sum = 0;
				for(int j=0; j<a.length; j++){
					for(int k=0; k<a.length; k++)
						sum+=a[k][j]*curr[k];
					matrix[i][j] = sum;
				}
			}
			print(matrix);
			c++;
		}
		return c;
	}
	
	/*public static int longest(String[] str, String pg, int num, int len){
		if(pg.equals("ENDING"))
			return len;
		else if(!pg.equals("-1 -1")){
			String[] strCopy = str;
			String[] nums = pg.split(" ");
			int curr = Integer.parseInt(nums[0]);
			String x = "-1";
			if(nums.length>1){
				if(curr==-1){
					curr = Integer.parseInt(nums[1]);
					x = "-1 -1";
				}
				else
					x+=nums[1];
			}
			return longest(strCopy, strCopy[curr-1], curr, len++);
		}
		else
			return len;
	}*/
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(r.readLine());
		for(int i=1; i<=x; i++){
			int pages = Integer.parseInt(r.readLine());
			String[] str = new String[pages];
			for(int j=0; j<pages; j++)
				str[j] = r.readLine();
			int[][] matrix = new int[pages][pages];
			for(int k=0; k<pages; k++)
				if(!str[k].equals("ENDING")){
					String[] nums = str[k].split(" ");
					for(int l=0; l<nums.length; l++)
						matrix[k][Integer.parseInt(nums[l])-1] = 1;
				}
			print(matrix);
			System.out.println("Book #" + i + ": The longest story is "  + multiply(matrix) + " pages.");
		}
	}

}
