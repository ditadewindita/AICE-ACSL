import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Collatz {
	
	public static long conjecture(long x) {
	    long c = x;
	    long d = 1;
	    while(c!=1){
	      d++;
	      if(c%2==0)
	        c = c/2;
	      else
	        c = (c*3)+1;
	    }
	    return d;
	  }
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String a = r.readLine();
		long x = (long)Integer.parseInt(a);
		System.out.println(conjecture(x));
	}

}
