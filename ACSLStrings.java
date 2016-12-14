package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ACSLStrings {
	
	public static String round(String num, int place){
		int a = num.indexOf(".")+1;
		double d = Double.parseDouble(num);
		int len = num.length();
		if(num.substring(0,1).equals("-") || num.substring(0,1).equals("+"))
			len++;
		if(num.substring(0,1).equals("-")){
			if(Integer.parseInt(num.substring(a))==75)
				return "" + (d-0.05);
			else if(Integer.parseInt(num.substring(a))==74)
				return "" + Double.parseDouble(num.substring(0, num.length()-1));
		}
		
		if(a+place+1>len)
			return "#";
		if(a+place+1==len)
			num = num + "0";
		double decimal = Integer.parseInt(num.substring(a, a+place+1))/10.0;
		return num.substring(0,a-1) + "." + (int)(decimal+0.5);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String in = r.readLine();
			String[] nums = in.split(", ");
			int len = Integer.parseInt(nums[1]);
			String round = round(nums[0], Integer.parseInt(nums[2]));
			if(!round.equals("#") && len>round.length())
				while(round.length()<Integer.parseInt(nums[1]))
					round = "#" + round;
			if(len<round.length()){
				String a = ".";
				for(int j=0; j<Integer.parseInt(nums[2]); j++)
					a+= "#";
				while(a.length()<len)
					a = "#" + a;
				round = a;
			}
			System.out.println(round);
		}
	}

}
