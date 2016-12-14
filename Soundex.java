import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Soundex {

	private static String[][] soundex = {{"B","F","P","V"}, {"C","G","J","K","Q","S","X","Z"}, {"D","T"}, {"L"}, {"M", "N"}, {"R"}};
	private static String name;
	
	public static boolean inBounds(int a, int b, String s){
		if(a>s.length() || b>s.length())
			return false;
		return true;
	}
	public static int getSoundex(String letter){
		for(int i=0; i<soundex.length; i++)
			for(int j=0; j<soundex[i].length; j++)
				if(soundex[i][j].equals(letter))
					return i+1;
		return -1;
	}
	
	public static boolean disregardLetter(String letter){
		if(letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U") || letter.equals("H") || letter.equals("W") || letter.equals("Y"))
			return true;
		return false;
	}
	
	public static boolean doubleLetters(String letter, String otherLetter){
		if(letter.equals(otherLetter))
			return true;
		return false;
	}
	
	public static boolean doubleSoundex(String letter, String otherLetter){
		if(getSoundex(letter)==getSoundex(otherLetter))
			return true;
		return false;
	}
	
	public static boolean vowel(String letter){
		if(letter.equals("A") || letter.equals("E") || letter.equals("I") || letter.equals("O") || letter.equals("U"))
			return true;
		return false;
	}
	
	public static String createSoundex(String str){
		String s = str.substring(0, 1);
		for(int i=1; i<str.length(); i++){
			String temp = str.substring(i,i+1);
			String afterTemp = "";
			String beforeTemp = str.substring(i-1,i);
			if(inBounds(i+1,i+2,str))
				afterTemp = str.substring(i+1, i+2);
			if(vowel(temp) && !vowel(beforeTemp) && !vowel(afterTemp) && doubleSoundex(beforeTemp, afterTemp))
				s+=getSoundex(afterTemp);
			else if(!vowel(temp) && disregardLetter(temp) && !vowel(beforeTemp) && !vowel(afterTemp) && getSoundex(beforeTemp)==getSoundex(afterTemp)){
				i++;
				continue;
			}
			else if(!disregardLetter(temp) && !doubleLetters(temp,afterTemp) && (!doubleSoundex(temp,afterTemp) && !temp.equals(afterTemp)))
				s+=getSoundex(temp);	
		}
		if(s.length()>4)
			s = s.substring(0,4);
		else 
			while(s.length()<4)
				s+="0";
		return s;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String s = r.readLine();
			String prefix = "";
			int space = s.indexOf(" ");
			if(space<0)
				name = s + " ";
			else {
				name = s.substring(space+1) + " ";
				prefix = s.substring(0, space) + " ";
				if(prefix.equals("MC") || prefix.equals("MAC"))
					prefix = "";
			}
			String fin = "";
			if(prefix.length()>0){
				String pre = createSoundex(prefix);
				pre = pre.substring(0, pre.indexOf("0"));
				String n = createSoundex(name);
				int preLen = pre.length();
				if(preLen<4)
					pre+=createSoundex("x" + n.substring(0, 1)+ " ").substring(1, 2)+ n.substring(1,(4-preLen));
				fin = pre + " and " + n;
			}
			else
				fin = createSoundex(name);
			System.out.println(fin);
		}
	}
	
}
