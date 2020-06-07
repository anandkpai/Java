package testStrings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Strings {

	private static final int a       = (int)'a';
	private static final int STRLEN  = 200;
	private static final Random rand = new Random();
	public String s;
	
	public Strings(String str) {
		this.s = str;
	}
	
	public Strings(int size) {
		char[] c = new char[size];
		for (int i=0;i<size;i++) c[i] = (char)(a + rand.nextInt(25));
		this.s = String.valueOf(c);
	}
	
	public Strings() {
		this(STRLEN);
	}

	@Override
	public String toString() {
		return this.s;		
	}
	
	public char[] dupes() {
		HashSet<Character> chars =  new HashSet<Character> (this.s.length()) ;
		HashSet<Character> dupes = new HashSet<Character> (this.s.length());
		 
		char c;
		for (int i=0;i<this.s.length();i++){
			c = this.s.charAt(i);
			if (chars.contains(c)) dupes.add(c);
			else chars.add(c);
		} 
		
		return dupes.toString().toCharArray();
	}
	
	private void swap(char[] o,int a,int b) 
	{
		char t  = o[a];
		o[a] = o[b];
		o[b] = t;
	}
	
	
	public void reverse()
	{		
		char[] chars = this.s.toCharArray();
		for (int i=0;i<chars.length/2;i++) swap(chars,i,chars.length-i-1); 
		this.s = String.valueOf(chars);
	}
	
	private void recurseReverse(char[] chars,int i, int j){
		if (j > i) { 
			swap(chars,i++,j--);
			recurseReverse(chars,i,j);
		} 			
			
	}
	
	public void recurseReverse()
	{
		char[] chars = this.s.toCharArray();
		recurseReverse(chars,0,chars.length-1);
		this.s = String.valueOf(chars);
	}
	
	
	
	public char firstNonRepeatedChar() {
		char[] chars = this.s.toCharArray();
		for (int i=0;i<chars.length;i++) if (s.indexOf(chars[i], i+1) < 0) return chars[i];  
		return '-';
	}
	
}
