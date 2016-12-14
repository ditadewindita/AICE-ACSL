package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WordCounting {
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int x = Integer.parseInt(r.readLine());
		for(int i=1; i<=x; i++){
			String word = r.readLine();
			String sentence = r.readLine();
			int c = 0;
			for(int j=0; j<sentence.length()-word.length()+1; j++){
				if(sentence.substring(j, j+word.length()).equalsIgnoreCase(word))
					c++;
			}
			System.out.println("Line #" + i + ": Word " + word + " occurred " + c + " times");
			
			
		}
	}

}
