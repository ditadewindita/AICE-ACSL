import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Wine {
	
	private static String[][] grid;
	private static int wineH;
	private static int wineW;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(r.readLine());
		for(int i=1; i<=n; i++){
			String dimensions = r.readLine();
			String[] d = dimensions.split(" ");
			grid = new String[Integer.parseInt(d[0])][Integer.parseInt(d[1])];
			wineH = Integer.parseInt(d[2]);
			wineW = Integer.parseInt(d[3]);
			
			for(int j=0; j<Integer.parseInt(d[0]); j++)
				grid[j] = r.readLine().split("");
			
			int box = 0;
			for(int rr=0; rr<grid.length; rr++)
				for(int c=0; c<grid[0].length; c++)
					if(grid[rr][c].equals("#"))
						box++;
			System.out.println("Case " + i + ": Gatsby has " + box/(wineH*wineW) + " wine bottles in his wine cellar.");
					
		}
	}

}
