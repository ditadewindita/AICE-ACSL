import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MapQuest {
	
	private static String[] pos = {"A","450","B","140","C","125","D","365","E","250","F","160","G","380","H","235","J","320","K"};
	private static String pointA, pointB;
	private static int speedA, speedB;
	private static int timeA, timeB;
	private static int mtA, mtB;
	
	public static int findDistNeeded(){
		int p1 = 0;
		int p2 = 0;
		int d = 0;
		for(int i=0; i<pos.length; i++){
			if(pos[i].equals(pointA))
				p1=i;
			else if(pos[i].equals(pointB))
				p2=i;
		}
		int c = p1+1;
		while(c<p2){
			d+=Integer.parseInt(pos[c]);
			c+=2;
		}
		return d;
	}
	
	public static void toMT(String a, String b){
		if(a.equals("PM") || (timeA==12 && a.equals("AM")))
			mtA+=12;
		if(b.equals("PM") || (timeB==12 && a.equals("AM")))
			mtB+=12;
	}

	public static String howLong(){
		int totalDist = findDistNeeded();
		int diff = Math.abs(timeA-timeB);
		int timeDiff = Math.min(diff, Math.abs(12-diff));
		double t = ((double)totalDist-(timeDiff*earliestTimeSpeed()))/(speedA+speedB);
		int h = (int)t;
		double min = 60*(t-h);
		if(mtA<mtB)
			h+=timeDiff;
		String m = "0" + Math.round(min);
		if(Math.round(min)>9)
			m = m.substring(1);
		return "" + h + ":" + m;
	}
	
	public static int earliestTimeSpeed(){
		if(mtB<mtA)
			return speedB;
		return speedA;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String[] s = r.readLine().split(", ");
			pointA = s[0];
			pointB = s[1];
			timeA = Integer.parseInt(s[2]);
			timeB = Integer.parseInt(s[4]);
			speedA = Integer.parseInt(s[6]);
			speedB = Integer.parseInt(s[7]);
			mtA = timeA;
			mtB = timeB;
			toMT(s[3], s[5]);
			System.out.println(howLong());
		}
	}

}
