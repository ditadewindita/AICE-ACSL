import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataCompression {
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String str = r.readLine();
			String[] s = str.split(" ");
			int w = 1;
			for(int j=0; j<s.length; j++){
				int wordCount = 1;
				String temp = s[j].substring(s[j].length()-1);
				if(!temp.matches("[A-Z]"))
					temp = s[j].substring(0,s[j].length()-1);
				else
					temp = s[j];
				for(int k=0; k<s.length; k++)
					if(k!=j && (temp.equals(s[k]) || temp.equals(s[k].substring(0, s[k].length()-1))))
						wordCount++;
				if(wordCount>1){
					String tSTR = str;
					str = str.replaceAll(temp, "" + w);
					if(!tSTR.equals(str))
						w++;
				}
			}
			System.out.println(str);
		}
	}

}