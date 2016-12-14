import java.util.Scanner;

public class Conic {
	
	public static int[] turnInt(String[] a){
		int[] n = new int[a.length];
		for(int i=0; i<a.length; i++)
			n[i]=Integer.parseInt(a[i]);
		return n;
	}
	
	public static int[] findCenter(int[] a){
		int[] num = new int[2];
		int x = (a[3]/a[0])/2;
		int y = (a[4]/a[2])/2;
		num[0] = -1*x; //x coordinate
		num[1] = -1*y; //y coordinate
		return num;
	}
	
	public static int findNewF(int[] a){
		int f = 0;
		int b = ((int)Math.pow(((a[3]/a[0])/2),2))*a[0];
		int c = ((int)Math.pow(((a[4]/a[2])/2),2))*a[2];
		f = (-1*a[5]) + b + c; //other side equation
		return f;
	}
	
	public static boolean ifCircle(int[] a){
		int x = findNewF(a)/a[0];
		int y = findNewF(a)/a[2];
		return x==y;
	}
	
	public static int getMajorAxis(int[] a){
		int x = findNewF(a)/a[0];
		int y = findNewF(a)/a[2];
		if(x>y)
			return (int)Math.sqrt(x)*2;
		return (int)Math.sqrt(y)*2;
	}
	
	public static void main(String[] args){
		Scanner kb = new Scanner(System.in);
		while(kb.hasNext()){
			String s = kb.nextLine();
			String[] numString = s.split(",");
			int[] numInt = turnInt(numString);
			
			//CIRCLE
			if(ifCircle(numInt)){
				int[] center = findCenter(numInt);
				int f = findNewF(numInt);
				int radius = (int)(Math.sqrt(f/numInt[0]));
				System.out.println("Circle, (" + center[0] + "," + center[1] + "), " + radius);
			}
			
			//ELLIPSE
			else {
				int[] center = findCenter(numInt);
				int axis = getMajorAxis(numInt);
				System.out.println("Ellipse, (" + center[0] + "," + center[1] + "), " + axis);
			}
		}
		kb.close();
	} //main
}
