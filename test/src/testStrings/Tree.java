package testStrings;

import java.util.ArrayList;

public class Tree {

	protected static final ArrayList<Branch> lowest = new ArrayList<Branch>();
		
	static public void makeTree(String s) {	new Branch(null,0,s);	}
	
	protected Tree() {}

	static public void print() {Tree.lowest.forEach(s->{System.out.println(s);});}
	
	static public int size() {return Tree.lowest.size();}
}
