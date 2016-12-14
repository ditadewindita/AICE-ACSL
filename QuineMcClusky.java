import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class QuineMcClusky {
	
	public static boolean sameX(String a, String b){
		for(int i=0; i<a.length(); i++)
			if(a.substring(i, i+1).equals("X") && !a.substring(i, i+1).equals(b.substring(i, i+1)))
				return false;
		return true;
	}
	
	public static int index(String a){
		int c = 0;
		for(int i=0; i<a.length(); i++)
			if(a.substring(i, i+1).equals("1"))
				c++;
		return c;
	}
	
	public static boolean checkX(String a, int x){
		int c = 0;
		for(int i=0; i<a.length(); i++)
			if(a.substring(i, i+1).equals("X"))
				c++;
		if(c>x)
			return false;
		return true;
	}
	
	public static boolean higherIndex(String a, String b){
		if(index(a)+1==index(b) || index(b)+1==index(a))
			return true;
		return false;
	}
	
	public static String match(String a, String b){
		String ret = "";
		for(int i=0; i<a.length(); i++)
			if(!a.substring(i, i+1).equals(b.substring(i, i+1)))
				ret+="X";
			else
				ret+=a.substring(i, i+1);
		return ret;
	}
	
	public static String[] toBinary(String[] a, int term){
		String[] ret = new String[a.length];
		for(int i=0; i<a.length; i++){
			String b = Integer.toBinaryString(Integer.parseInt(a[i]));
			while(b.length()<term)
				b = "0" + b;
			ret[i] = b;
		}
		return ret;
	}
	
	public static ArrayList<String> simplify(String[] values){
		ArrayList<String> s = new ArrayList<String>();
		for(int i=0; i<values.length; i++)
			for(int j=0; j<values.length; j++){
				String a = values[i];
				String b = values[j];
				if(higherIndex(a, b) && sameX(a, b)){
					String c = match(a, b);
					if(checkX(c, 1) && !s.contains(c))
						s.add(c);
				}
			}
		return s;
	}
	
	public static ArrayList<String> simplify(ArrayList<String> v, int x){
		ArrayList<String> s = new ArrayList<String>();
		for(int i=0; i<v.size(); i++)
			for(int j=0; j<v.size(); j++){
				String a = v.get(i);
				String b = v.get(j);
				if(higherIndex(a, b) && sameX(a, b)){
					String c = match(a, b);
					if(checkX(c, x) && !s.contains(c))
						s.add(c);
				}
			}
		return s;
	}
	
	public static boolean canSimplifyMore(ArrayList<String> s){
		for(int i=0; i<s.size(); i++)
			for(int j=i+1; j<s.size(); j++){
				String a = s.get(i);
				String b = s.get(j);
				if(sameX(a, b) && higherIndex(a, b))
					return true;
			}
		return false;
	}
	
	public static String booleanExpression(ArrayList<String> s){
		String ret = "";
		int ascii = 65;
		for(String a: s){
			for(int i=0; i<a.length(); i++){
				if(a.substring(i, i+1).equals("0"))
					ret+=(char)(ascii+32);
				else if(a.substring(i, i+1).equals("1"))
					ret+=(char)ascii;
				ascii++;
			}
			ret+=" + ";
			ascii = 65;
		}
		return ret.substring(0, ret.length()-3);
	}

	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int term = 3;
		for(int i=0; i<5; i++){
			String in = r.readLine();
			String[] values = in.substring(0, in.length()-4).split(", ");
			ArrayList<String> s = simplify(toBinary(values, term));
			int x = 2;
			while(canSimplifyMore(s)){
				s = simplify(s, x);
				x++;
			}
			System.out.println(booleanExpression(s));
			if(i>3)
				term++;
		}
		
	}
}
