import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Checkers {
	
	private static int[][] board = new int[8][8];
	//me: 1 king: 2
	//comp: -1 king: -2
	private static boolean[][] booleanBoard = new boolean[8][8];
	//true if black & unoccupied 
	private static ArrayList<int[]>  userPieces;
	private static ArrayList<int[]>  compPieces;
	//row me: 0
	//row comp: 7
	private static ArrayList<Integer> moves; 
	
	public static void print(ArrayList<int[]> a){
		for(int[] aa: a){
			for(int b: aa)
				System.out.print(b + ", ");
			System.out.println();
		}
	}
	
	public static void print(boolean[][] b){
		for(boolean[] a: b){
			for(boolean c: a)
				System.out.print(c + "\t");
			System.out.println();
		}
	}
	
	public static void print(int[][] b){
		for(int[] a: b){
			for(int c: a)
				System.out.print(c + " ");
			System.out.println();
		}
	}
	
	public static boolean inBounds(int r, int c){
		return (r>-1 && r<8) && (c>-1 && c<8);
	}
	
	public static void setBoard(ArrayList<int[]> user, ArrayList<int[]> comp){
		for(int r=0; r<8; r++)
			for(int c=0; c<8; c++)
				if((r%2==0 && c%2==0) || (r%2!=0 && c%2!=0))
						booleanBoard[r][c] = true;
		for(int i=0; i<user.size(); i++){
			board[user.get(i)[0]][user.get(i)[1]] = 1;
			booleanBoard[user.get(i)[0]][user.get(i)[1]] = false;
		}
		
		for(int i=0; i<comp.size(); i++){
			board[comp.get(i)[0]][comp.get(i)[1]] = -1;
			booleanBoard[comp.get(i)[0]][comp.get(i)[1]] = false;
		}
	}
	
	//user: row 0
	public static ArrayList<int[]> possibleMoves(int[] checker, boolean king){
		ArrayList<int[]> possible = new ArrayList<int[]>();
		int r = checker[0];
		int c = checker[1];
		for(int i=-1; i<2; i+=2){
			if(inBounds(r+i, c+i)){
				int[] p = {r+i, c+i};
				possible.add(p);
			}
			if(inBounds(r+i, c-i)){
				int[] p = {r+i, c-i};
				possible.add(p);
			}
		}
		for(int i=possible.size()-1; i>=0; i--){
			int possibleR = possible.get(i)[0];
			int possibleC = possible.get(i)[1];
			if((!king && possibleR<checker[0]) || board[possibleR][possibleC]!=-1) //cannot move backwards if not king
				possible.remove(i);
		}
		return possible;
	}
	
	
	
	public static void move(){
		for(int i=0; i<userPieces.size(); i++){
			int[] current = userPieces.get(i);
			boolean king = false;
			if(current[0]==7)
				king = true;
			ArrayList<int[]> possible = possibleMoves(current, king);
			int counter = 0;
			int tempR = (2*possible.get(i)[0])-current[0];
			int tempC = (2*possible.get(i)[1])-current[1];
			int[] temp = {tempR, tempC};
			if(tempR==7)
				king = true;
			boolean jump = inBounds(tempR,tempC) && booleanBoard[tempR][tempC];
			int t = 0;
			while(t<possible.size() && possible.size()>0 && jump){
				counter++;
				tempR = (2*possible.get(t)[0])-current[0]; // fix calculate jump coordinates
				tempC = (2*possible.get(t)[1])-current[1];
				temp[0] = tempR;
				temp[1] = tempC;
				if(tempR==7)
					king = true;
				jump = inBounds(tempR,tempC) && booleanBoard[tempR][tempC];
				if(possible.size()==1)
					possible = possibleMoves(temp, king);
				else
					t++;
			}
			moves.add(counter);
			t=0;
			counter = 0;
		}
	}
	
	public static int greatestMove(){
		int g = 0;
		for(int a: moves)
			g = Math.max(g, a);
		return g;
	}
	
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		boolean run = kb.hasNextInt();
		while(run){
			int user = kb.nextInt();
			userPieces = new ArrayList<int[]>();
			for(int i=0; i<user; i++){
				int[] uPiece = {kb.nextInt()-1,kb.nextInt()-1};
				userPieces.add(uPiece);
			}
			int comp = kb.nextInt();
			compPieces = new ArrayList<int[]>();
			for(int j=0; j<comp; j++){
				int[] cPiece = {kb.nextInt()-1,kb.nextInt()-1};
				compPieces.add(cPiece);
			}
			moves = new ArrayList<Integer>();
			setBoard(userPieces,compPieces);
			move();
			System.out.println(greatestMove());
		}
	}

}
