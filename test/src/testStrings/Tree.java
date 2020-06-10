package testStrings;

import java.util.ArrayList;
import java.util.HashMap;


public class Tree {

	protected static final ArrayList<String> words = new ArrayList<String>();
	protected static final HashMap<String, Boolean> nodeSignatures = new HashMap<String, Boolean>() ;
			
	static public void init (String s) {
		words.clear();
		nodeSignatures.clear();
		new Branch(null,s);
	}		
	
	protected Tree() {}

	public static void print() {Tree.words.forEach(s->{System.out.println(s);});}
	
	public static int size() {return Tree.words.size();}
	
}
