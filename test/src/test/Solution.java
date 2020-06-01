package test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;


public class Solution 
{
	static Random rand 	= new Random();
	static final int DEFAULT_ARRAY_CAPACITY = 100;
	static final int MAX_RANDOM_INT_VALUE   = 5000;
		
	public static void main(String[] args) {
		int[] dupes = new int[DEFAULT_ARRAY_CAPACITY] ;
		System.out.printf("before filling \t%6d\n",dupes.length);
		for (int i=0;i<DEFAULT_ARRAY_CAPACITY;i++) System.out.println(dupes[i]);
		
		Random rand = new Random();
		for (int i=0;i<DEFAULT_ARRAY_CAPACITY;i++) dupes[i] = rand.nextInt(MAX_RANDOM_INT_VALUE);
		System.out.printf("after filling \t%6d\n",dupes.length);
		
		HashSet<Integer>  vals  = new HashSet<Integer>(DEFAULT_ARRAY_CAPACITY);
		boolean found 			= false;										
		for (int i=0;i<DEFAULT_ARRAY_CAPACITY;i++) {
			if (vals.contains(dupes[i])) {
				System.out.printf("found \t%6d\n", dupes[i]);
				found = true;
				break;
			}
			else vals.add(dupes[i]);
		}
		if (!found) System.out.println("no duplicates found");

	}
}