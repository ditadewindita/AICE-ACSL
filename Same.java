import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Same {
	
	public static int can(String[] nums){
		int max = (int)Math.pow(10, 6);
		for(int i=0; i<nums.length; i++){
			int curr = Integer.parseInt(nums[i]);
			for(int j=i+1; j<nums.length; j++){
				int d = curr*2;
				int s = curr*7; 
				int h = curr*11;
				while((d==Integer.parseInt(nums[j]) || s==Integer.parseInt(nums[j]) || h==Integer.parseInt(nums[j])) && (d!=max && s!=max && h!=max)){
					d = d*2;
					s = s*7;
					h = h*11;
					return 1;
				}
			}
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String s = r.readLine();
		int n = Integer.parseInt(s);
		//11, 2, 7
		for(int i=0; i<n; i++){
			int numAmt = Integer.parseInt(r.readLine());
			String num = r.readLine();
			String[] nums = num.split(" ");
			System.out.println(can(nums));
		}
	}

}
