import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class WrapAroundCode {
	
	private static String[] alphabet = {"A","B","C","D","E","F","G","H",
			"I","J","K","L","M","N","O","P","Q","R","S","T","U","V",	
			"W","X","Y","Z"};
	
	public static int getLetterPos(String s){
		for(int i=0; i<alphabet.length; i++)
			if(alphabet[i].equals(s))
				return i;
		return -1;
	}
	
	public static int computeNum(String s, int r){
		int letterNum = getLetterPos(s)+1;
		if(r==1)
			return letterNum*2;
		if(r==2)
			return (letterNum%3)*5;
		if(r==3)
			return (letterNum/4)*-8;
		if(r==4)
			return ((int)Math.sqrt(letterNum))*-12;
		else {
			int sum = 0;
			for(int i=1; i<=letterNum; i++)
				if(letterNum%i==0)
					sum+=i;
			return sum*10;
		}
	}
	
	public static String compute(String start, int n){
		int finNum = getLetterPos(start)+n;
		while(finNum>25)
			finNum-=26;
		while(finNum<0)
			finNum+=26;
		//if(finNum>25)
			//return alphabet[finNum%26];
		return alphabet[finNum];
		
	}
	
	public static void main(String[] args) /*throws IOException*/{
		Scanner kb = new Scanner(System.in);
		//BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String str = kb.next();
			String result = "";
			String temp = "";
			while(!str.equals("$")){
				int n = kb.nextInt();
				temp+=str + "" + n;
				str = kb.next();
			}
			String[] tempStr = temp.split("");
			String[] arr = new String[tempStr.length-1];
			for(int k=0; k<arr.length; k++){
				arr[k]=tempStr[k+1];
			}
			String s = compute("A", computeNum(arr[0],Integer.parseInt(arr[1])));
			result+=s;
			for(int j=2; j<arr.length-1; j+=2){
				result+=compute(s, computeNum(arr[j],Integer.parseInt(arr[j+1])));
				String a = s;
				s = compute(a, computeNum(arr[j],Integer.parseInt(arr[j+1])));
			}
			System.out.println(result);
		}
		
	}


}
