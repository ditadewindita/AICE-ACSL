
public class PlayPass {
	
	public static String playPass(String s, int n){
		return changeString(s,n);
	}
	
	public static String changeString(String s, int n){
		int len = s.length();
		String fin = "";
		//shift + complimentary
		for(int j=0; j<len; j++){
			char c = s.charAt(j);
			int tempCharASCII = (int)c;
			//if space
			if(tempCharASCII==32)
				fin=" "+fin;
			//complement to 9 - num
			else if(tempCharASCII<58 && tempCharASCII>47){
				int charToNum = Integer.parseInt("" + (char)tempCharASCII);
				charToNum = 9-charToNum;
				fin= charToNum+fin;
			}
			//change letter
			else if((tempCharASCII<91 && tempCharASCII>64) || (tempCharASCII<123 && tempCharASCII>96)){
				int newLetter = tempCharASCII+n;
				if((tempCharASCII<91 && tempCharASCII>64 && newLetter>90) || (tempCharASCII>96 && tempCharASCII<123 && newLetter>122))
					newLetter-=26;
				String str = ""+(char)newLetter;
				//upper + lowercase
				if(j%2==0)
					fin=str.toUpperCase()+fin;
				else
					fin=str.toLowerCase()+fin;
			}
			else
				fin=c+fin;
		}
	
		return fin;
	}

	public static void main(String[] args) {
		System.out.println(playPass("born in 2015!", 1));
	}

}
