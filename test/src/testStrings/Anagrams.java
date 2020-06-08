package testStrings;

import java.util.ArrayList;

public class Anagrams {
	
	static public class Strings extends ArrayList<String> {
		
		public Strings(int n) {
			super(n);
		}

		@Override 
		public String toString() {
			String s="";
			int i; for (i=0;i<this.size()-1;i++) s+=this.get(i)+"\n";;
			return s+this.get(i);		
		}
		
		public void append(int i, char c) {
			this.set(i, this.get(i)+String.valueOf(c));
		}
		
		@Override
		public Strings clone() {return (Strings)super.clone();}
	}
	
	
	private String s="";
 		
	public Anagrams(String s) {
		this.s = s;
	}

	static private int factorial(int n) {return n==2?2:n*factorial(n-1);}
	
	public void anagram() {anagram(new Strings(0),this.s);};
			
	private void anagram(Strings left, String substr ) {
		if (substr.length()==0) {
			System.out.println(left);
			return;
		}
		for (int i=0;i<substr.length();i++) {
			char chr = substr.charAt(i);
			if (left.isEmpty()) {
				Strings newleft = new Strings(factorial(substr.length()-1)); 
				newleft.add(String.valueOf(chr));
				anagram(newleft,substr.substring(0,i)+substr.substring(i+1));
			}
			else {
				Strings newleft = (Strings)left.clone();
				for (int j=0;j<newleft.size();j++) newleft.append(j, chr);
				anagram(newleft,substr.substring(0,i)+substr.substring(i+1));
			}								
		}
	}
	
}
