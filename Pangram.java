package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pangram {
	
	public static final String[] alpha = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	
	public static String pangram(String s){
		s = s.toLowerCase();
		String fin = "";
		for(int i=0; i<alpha.length; i++)
			if(!s.contains(alpha[i]))
				fin+=alpha[i];
		return fin;
	}

	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(r.readLine());
		for(int i=0; i<x; i++){
			String s = r.readLine();
			String fin = pangram(s);
			if(fin.equals(""))
				System.out.println("pangram");
			else
				System.out.println("missing " + fin);
		}
	}
}
