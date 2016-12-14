import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

public class Parity {
	
	public static String hexToBin(String s){
		String str = new BigInteger(s, 16).toString(2);
		while(str.length()!=s.length()*4)
			str = "0" + str;
		return str;
	}
	
	public static String[] codeword(String s){
		ArrayList<String> c = new ArrayList<String>();
		int e = 0;
		for(int i=0; i<s.length(); i++){
			int f = (int)Math.pow(2, e)-1;
			if(f==c.size()){
				c.add("P");
				e++;
				if(f==0){
					c.add("P");
					e++;
				}
			}
			c.add(s.substring(i,i+1));
		}
		
		String[] codeword = new String[c.size()];
		for(int j=0; j<c.size(); j++)
			codeword[j] = c.get(j);

		return codeword;
	}
	
	public static int findP(String[] s){
		for(int i=0; i<s.length; i++)
			if(s[i].equals("P")){
				s[i] = "" + -1;
				return i;
			}
		return -1;
	}
	
	public static int parityBit(String[] s, int posP){
		int sum = 0;
		int increment = (posP+1)*2;
		for(int i=posP; i<s.length; i+=increment){
			if(posP>0){
				int tempInc = i+posP+1;
				if(tempInc>s.length)
					tempInc = s.length;
				for(int j=i; j<tempInc; j++){
					if(!s[j].equals("P"))
						sum+=Integer.parseInt(s[j]);
				}
			}
			else {
				if(!s[i].equals("P"))
					sum+=Integer.parseInt(s[i]);
			}
		}
		return sum;
	}
	
	public static String parity(String[] s, String parity){
		String[] copyS = new String[s.length];
		for(int i=0; i<copyS.length; i++)
			copyS[i] = s[i];
		
		String ret = "";
		int posP = findP(copyS);
		while(posP!=-1){
			int sum = parityBit(s, posP);
			if((sum%2!=0 && parity.equals("EVEN")) || (sum%2==0 && parity.equals("ODD")))
				ret+="1";
			else
				ret+="0";
			posP = findP(copyS);	
		}
		return ret;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<10; i++){
			String[] str = r.readLine().split(", ");
			String binary = hexToBin(str[0]);
			String parity = str[1];
			String[] codeword = codeword(binary);
			System.out.println(parity(codeword, parity));
		}

	}

}
