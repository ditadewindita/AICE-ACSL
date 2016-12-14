import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ACSLTrees {
	
	private static ArrayList<Node> tree;
	
	public static Node createTree(Node n, String s){
		int level = (n.level) + 1;
		if(s.compareTo(n.letter)>0){
			if(n.rightNode==null){
				Node a = new Node(s, level);
				n.setRight(a);
				return a;
			}
			else
				createTree(n.rightNode, s);
		}
		else {
			if(n.leftNode==null){
				Node a = new Node(s, level);
				n.setLeft(a);
				return a;
			}
			else
				createTree(n.leftNode, s);
		}
		return null;
	}
	
	public static int[] stuff(ArrayList<Node> tree){
		int leaf = 0;
		int external = 0;
		int externalLength = 0;
		for(Node a: tree){
			if(a.leftNode==null && a.rightNode==null){ // LEAF NODES
				leaf++;
				external+=2;
				externalLength+=(a.level+1)*2;
			}
			else if(a.leftNode==null || a.rightNode==null){ // EXTERNAL NODES
				external++;
				externalLength+=a.level+1;
			}
		}
		int[] arr = {leaf, external, externalLength};
		return arr;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		for(int i=0; i<5; i++){
			String s = r.readLine();
			Node root = new Node(s.substring(0, 1), 0);
			for(int j=1; j<s.length(); j++){
				String temp = s.substring(j, j+1);
				createTree(root, temp);
			}
			tree = root.makeList();
			Collections.sort(tree); // GREATEST --> LEAST
			int depth = tree.get(0).level;
			int sum = 0;
			for(Node a: tree)
				sum+=a.level;
			int[] arr = stuff(tree);
			System.out.println("Depth = " + depth);
			System.out.println("Leaf nodes = " + arr[0]);
			System.out.println("External nodes = " + arr[1]);
			System.out.println("Internal path length = " + sum);
			System.out.println("External path length = " + arr[2]);
			/*for(Node a: tree)
				System.out.print(a.letter);
			System.out.println();*/
		}	
	}	
}

class Node implements Comparable {
	
	public Node leftNode;
	public Node rightNode;
	public int level;
	public String letter;
	public char letterChar;
	
	public Node(String s, int l){
		letter = s;
		level = l;
		letterChar = s.charAt(0);
	}
	
	public void setRight(Node a){ rightNode = a; }
	public void setLeft(Node a){ leftNode = a; }
	
	public void makeList(ArrayList<Node> tree){
		if(leftNode!=null){
			tree.add(leftNode);
			leftNode.makeList(tree);
		}
		if(rightNode!=null){
			tree.add(rightNode);
			rightNode.makeList(tree);
		}
	}
	
	public ArrayList<Node> makeList(){
		ArrayList<Node> nodes = new ArrayList<Node>();
		nodes.add(this);
		makeList(nodes);
		return nodes;
	}
	
	public int compareTo(Object o){
		Node a = (Node)o;
		if(level==a.level){
			if(letterChar>a.letterChar)
				return 1;
			if(letterChar<a.letterChar)
				return -1;
			return 0;
		}
		if(level<a.level)
			return 1;
		return -1;
	}
	
}
