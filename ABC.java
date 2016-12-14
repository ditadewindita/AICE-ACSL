package uhm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ABC {
	
	public static String[][] grid;
	
	public static void fillMiniGrid(String[][] g){
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				grid[i][j] = g[i+1][j+1];
	}
	
	public static void fillGrid(String[][] g, String[] str){
		for(int x=0; x<g.length; x++)
			for(int y=0; y<g[x].length; y++)
				g[x][y] = "O";
		
		for(int i=0; i<str.length; i++){
			if(i<4 || (i>4 && i%2==0)){
				int c = (Integer.parseInt(str[i])%6)-1;
				int r = Integer.parseInt(str[i])/6;
				if(c==-1){
					c = 5;
					r--;
				}
				if(i<4)
					g[r][c] = "X";
				
				if(i>4){					
					int newR = r;
					int newC = c;
					if(r==0){
						newR++;
						while(r<g.length && g[newR][c].equals("X"))
								newR++;
					}
					else if(r==g.length-1){
						newR--;
						while(r>-1 && g[newR][c].equals("X"))
							newR--;
					}
					else if(c==0){
						newC++;
						while(c<g[r].length && g[r][newC].equals("X"))
								newC++;
					}
					else if(c==g[r].length-1){
						newC--;
						while(c>-1 && g[r][newC].equals("X"))
							newC--;
					}
					g[newR][newC] = str[i-1];
				}
				
			}	
		}
	}
	
	public static void fill(){
		String[] alpha = {"A", "B", "C"};
		for(int i=0; i<3; i++){
				String currAlpha = alpha[i];
				for(int j=0; j<grid.length; j++)
					for(int k=0; k<grid[i].length; k++){
						if(!hasLetter(currAlpha, j, k) && grid[j][k].equals("O"))
							grid[j][k] = currAlpha;
					}
			}
	}
	
	public static int rowOs(int a){
		int c = 0;
		for(int i=0; i<grid[a].length; i++)
			if(grid[a][i].equals("O"))
				c++;
		return c;
	}
	
	public static int colOs(int a){
		int c = 0;
		for(int i=0; i<grid.length; i++)
			if(grid[i][a].equals("O"))
				c++;
		return c;
	}
	
	
	public static boolean eliminationFill(){
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++){
				if(rowOs(i)==1){
					int[] pos = getPosOfO(i, "R");
					grid[pos[0]][pos[1]] = missingLetter(grid[i]);
					return true;
				}	
				if(colOs(i)==1){
					int[] pos = getPosOfO(i, "C");
					grid[pos[0]][pos[1]] = missingLetter(makeHorzColArr(i));
					return true;
				}
			}
		return false;
	}
	
	public static String[] makeHorzColArr(int c){
		String[] str = new String[4];
		for(int i=0; i<4; i++)
			str[i] = grid[i][c];
		return str;
	}
	
	public static String missingLetter(String[] s){
		int a = 0;
		int b = 0;
		int c = 0;
		int x = 0;
		for(int i=0; i<grid.length; i++){
			if(s[i].equals("A"))
				a++;
			else if(s[i].equals("B"))
				b++;
			else if(s[i].equals("C"))
				c++;
			else if(s[i].equals("X"))
				x++;
			
		}
		if(a==0)
			return "A";
		else if(b==0)
			return "B";
		else if(c==0)
			return "C";
		else if(x==0)
			return "X";
		return "";
	}
	
	public static boolean filled(){
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				if(grid[i][j].equals("O"))
					return false;
		return true;
	}
	
	public static boolean hasLetter(String letter, int r, int c){
		int x = 0;
		for(int i=0; i<grid.length; i++)
			for(int j=0; j<grid[i].length; j++)
				if((i==r || j==c) && letter.equals(grid[i][j]))
					x++;
		return x!=0;
	}
	
	public static int[] getPosOfO(int a, String str){
		int[] ret = {-1, -1};
		if(str.equals("R")){
			for(int i=0; i<grid[a].length; i++)
				if(grid[a][i].equals("O")){
					ret[0] = a;
					ret[1] = i;
				}
		}
		
		else {
			for(int j=0; j<grid.length; j++)
				if(grid[j][a].equals("O")){
					ret[0] = j;
					ret[1] = a;
				}
		}		
		
		return ret;
	}
	
	public static void print(String[][] a){
		for(String[] x: a){
			for(String y: x)
				System.out.print(y + " ");
			System.out.println();
		}
	}
	
	public static void printLineGrid(){
		String s = "";
		for(String[] x: grid)
			for(String y: x)
				if(!y.equals("X"))
					s+=y;
		System.out.println(s);
	}
	
	public static void print(ArrayList<int[]> a){
		for(int i=0; i<a.size(); i++)
			System.out.println(a.get(i)[0] + " " + a.get(i)[1]);
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String[] str = r.readLine().split(", ");
			String[][] bigGrid = new String[6][6];
			grid = new String[4][4];
			fillGrid(bigGrid, str);
			fillMiniGrid(bigGrid);
			int count = 0;
			while(!filled()){
				while(eliminationFill())
					count++;
				fill();
			}
			printLineGrid();
			//fill();
			//print(grid);
			//printLineGrid();
			//9, 17, 22, 26, 4, A, 7, C, 18, C, 19, C, 32
		}
	}

}
