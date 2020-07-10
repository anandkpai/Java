package testStrings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AnaTree {

	public static class AnaChar{
		private final char c;
		private final AnaChar parent;
		private final String substr ;
		public static final List<String> anagrams = new ArrayList<>();
		private static final HashMap<AnaChar, HashSet<String>> nodePoints = new HashMap<>();
		
		static public void buildTree(String s) {
			anagrams.clear();
			new AnaChar(s, 0, null);	
		}
		
		private AnaChar(String s, int index,AnaChar p ) {
			this.parent = p;
			this.c      = s.charAt(index);
			this.substr = this.parent==null?String.valueOf(this.c):this.parent.toString()+String.valueOf(this.c);
			
			if (index==0) for (int i=1;i<s.length();i++) new AnaChar(s, i, p);
			if (s.length() == 1) anagrams.add(this.toString());
			else {
				String right = s.substring(0,index)+s.substring(index+1);
				HashSet<String> strings =  (nodePoints.computeIfAbsent(p, f->(new HashSet<String>())));
				if (strings.add(String.valueOf(this.c)+right)) new AnaChar(right, 0, this);
			} 
		}		
		@Override
		public String toString() {return this.substr ;}
		
		
	}
	
	
	public static void main(String[] args) {
		AnaChar.buildTree("aabc");
		System.out.println(AnaChar.anagrams.size());
		for (String s: AnaChar.anagrams) System.out.println(s);

	}

}
