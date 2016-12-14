import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LISP {
	
	private static ArrayList<String> subs;
	private static String operator;
	
	public static void doStuff(String[] sub){
		int j = Integer.parseInt(sub[1])-1;
		int k = Integer.parseInt(sub[2])-1;
		
		String str = "(" + operator + " ";
		
		if(sub[0].equals("REMOVE")){
			for(int i=0; i<subs.size(); i++){
				if(!(i>=j && i<=k))
					str+=subs.get(i) + " ";
			}
		}
		
		if(sub[0].equals("SORT")){
			ArrayList<String> a = new ArrayList<String>(subs.subList(j, k+1));
			Collections.sort(a);
			for(int i=0; i<subs.size(); i++){
				if(i>=j && i<=k)
					str+=a.get(i-j) + " ";
				else
					str+=subs.get(i) + " ";
			}
		
		}
		
		if(sub[0].equals("REVERSE")){
			int pos = k;
			for(int i=0; i<subs.size(); i++){
				if(i>=j && i<=k){
					str+=subs.get(pos) + " ";
					pos--;
				}
				else
					str+=subs.get(i) + " ";
			}
		}
		
		str = str.substring(0,str.length()-1) + ")";
		System.out.println(str);
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String s = r.readLine();
		s = s.substring(1, s.length()-1);
		operator = s.substring(0, s.indexOf(" ("));
		s = s.substring(s.indexOf("("));
		subs = new ArrayList<String>();
		int a = s.indexOf(")");
		while(a!=-1){
			subs.add(s.substring(0,a+1));
			if(a==s.length()-1)
				s = "";
			else
				s = s.substring(a+2);
			a = s.indexOf(")");
		}
		for(int i=0; i<5; i++){
			String sub = r.readLine();
			
			if(sub.equals("COUNT"))
				System.out.println(subs.size());
			
			else if(sub.equals("MAXIMUM")){
				String m = subs.get(0);
				int mSize = m.split(" ").length;
				for(int j=0; j<subs.size(); j++){
					String[] temp = subs.get(j).split(" ");
					if(temp.length>mSize){
						mSize = temp.length;
						m = subs.get(j);
					}
				}
				System.out.println(m);
			}
			
			else {
				String[] subArr = sub.split(" ");
				doStuff(subArr);
			}
		}
	}

}
