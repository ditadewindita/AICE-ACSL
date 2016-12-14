import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CHMOD {

	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String[] arr = r.readLine().split(", ");
			int[] intArr = {Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), Integer.parseInt(arr[2]), Integer.parseInt(arr[3])};
			String binary = "";
			String permissions = "";
			for(int j=1; j<intArr.length; j++){
				String currB = Integer.toBinaryString(intArr[j]);
				while(currB.length()<3)
					currB = "0" + currB;
				for(int k=0; k<currB.length(); k++){
					if(currB.substring(k,k+1).equals("1")){
						if(k==0)
							permissions+="r";
						if(k==1)
							permissions+="w";
						if(k==2){
							if(j==intArr[0] && (intArr[0]==1 || intArr[0]==2))
								permissions+="s";
							else if(j==3 && intArr[0]==4)
								permissions+="t";
							else
								permissions+="x";
						}
					}
					else
						permissions+="-";
				}
				binary+= currB + " ";
				permissions+=" ";
			}
			
			System.out.println(binary + "and " + permissions.substring(0,permissions.length()-1));
				
		}
		
	}
}
