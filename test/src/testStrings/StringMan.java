package testStrings;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StringMan {

	private static final int STRLEN  = 6;
	private static final Random rand = new Random();
	private static final int A_ASCII = (int)('a'); 
	public String s;
	
	public StringMan(int size) {
		char[] chars = new char[size];
		for (int i=0;i<size;i++) chars[i] = (char)(A_ASCII+rand.nextInt(25));
		this.s = String.valueOf(chars);
	}
	
	public StringMan(int size, int range) {
		char[] chars    = new char[size];
		final int start = A_ASCII+rand.nextInt(25-range);
		for (int i=0;i<size;i++) chars[i] = (char)(start+rand.nextInt(range));
		this.s = String.valueOf(chars);
	}

	
	public StringMan() {
		this(STRLEN);
	} 
	
	
	
	
	public StringMan(String str) {
		this.s = str;		
	} 	
	
	public void reverse() {
		char[] chars = this.s.toCharArray();
		char[] rever = new char[chars.length];
		for (int i=0;i<chars.length;i++) rever[i] = chars[chars.length-1-i];
		this.s = String.valueOf(rever);
	}
		
	static public int factorial(int n) {
		if (n == 1) return 1;
		else return n* factorial(n-1);
	}
	
	public void anagram()
	{
		anagram(this.s);		
	}
		
	private void anagram (String word) {
		ArrayList<String> left = new ArrayList<String>(1);
		HashSet<String> accumulator = new HashSet<String>();
		left.add(""); 
		anagram(left,word, accumulator);
		accumulator.forEach(s->System.out.println(s));
	}
		
	private void anagram (ArrayList<String> left, String word, HashSet<String> accumulator)
	{
		if (word.length()==1) left.forEach(l->accumulator.add(l+word));
		else {
			for (int i=0;i<word.length();i++) {
				char c = word.charAt(i);
				ArrayList<String> newleft = new ArrayList<String>(left.size());
				left.forEach(l->newleft.add(l+c));
				anagram(newleft,word.substring(0, i)+word.substring(i+1), accumulator);
			}				
		}										
	}

	public void sort()
	{
		char[] chars = this.s.toCharArray();
		Arrays.sort(chars);
		this.s = String.valueOf(chars);
	}
	
	public void print() {
		System.out.println(this);;
	}
	
	@Override
	public String toString() {
		return this.s;
	}
	
}
