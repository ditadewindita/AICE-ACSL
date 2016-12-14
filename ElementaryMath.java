package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ElementaryMath {
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String s = r.readLine();
		while(!s.equals("")){
			String[] str = s.split(" ");
			int a = Integer.parseInt(str[0]);
			String op = str[1];
			int b = Integer.parseInt(str[2]);
			if(op.equals("*"))
				System.out.println(a*b);
			else if(op.equals("+"))
				System.out.println(a+b);
			else if(op.equals("-"))
				System.out.println(a-b);
			else if(op.equals("/"))
				System.out.println(a/b);
			s = r.readLine();
		}
		r.close();
		
	}

}
