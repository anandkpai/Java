package testStrings;

import java.util.ArrayList;
import java.util.Arrays;

public class Tests {

	public static long factorial(long n) {return n==2?2:n*factorial(n-1);}
	
	public static ArrayList<Integer> dupCharsCount(String s){
		ArrayList<Integer> dupesCount = new ArrayList<Integer>(s.length());
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		char prev = chars[0];
		int dupCounter =1;
		for (int i=1;i<chars.length;i++) {
			if (chars[i]==prev) dupCounter++;
			else {
				if (dupCounter>1) dupesCount.add(dupCounter);
				dupCounter = 1;
				prev = chars[i];
			}				
		}
		if (dupCounter>1) dupesCount.add(dupCounter);
		return dupesCount;
	}
	
	public static long  permutations(String s){
		long n = factorial(s.length());
		ArrayList<Integer> dupesCount = dupCharsCount(s);
		for (int i=0;i<dupesCount.size();i++) n = n/factorial(dupesCount.get(i)); 
		return n; 
	}
	
	
	
	public static void main(String[] args) {

		long start;
		start = System.currentTimeMillis();
		String s= "ababbdaaccdd";
		Tree.init(s);
		long reftime = System.currentTimeMillis()-start;
//		Tree.print();		
		System.out.printf("reference time       \t%12d\n", reftime);
		System.out.printf("Word length          \t%12d\n", s.length());
		System.out.printf("Anagrams calculated  \t%12d\n", Tree.size());
		System.out.printf("Theoretical anagrams \t%12d\n", permutations(s));
		System.out.print("Duplicates Array\t\t");
		System.out.println(dupCharsCount(s));
		
	}	
}
