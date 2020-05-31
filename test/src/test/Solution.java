package test;

import java.util.ArrayList;
import java.util.Random;

public class Solution {

	static Random rand 	= new Random();
	static final int ARRAYLEN  = 100;
	static final int MAXINT    = 100;
	

	

	
	public static void main(String[] args) {
		SortArray  mstest = new SortArray(10000000);
		SortArray  qtest  = mstest.clone();
		SortArray  ref    = mstest.clone();
		
		long start;
		start = System.currentTimeMillis();
		ref.data.sort(null);
		long reftime = System.currentTimeMillis()-start;
		System.out.printf("reference time \t%6d\n",reftime);

		assert (ref.data != qtest.data);	
		start = System.currentTimeMillis();		
		qtest.quickSort();
		long qtime = System.currentTimeMillis()-start;		
		assert (ref.data == qtest.data);
		System.out.printf("quicksort \t%6d   \t%6.2fx\n",qtime, ((float)qtime/reftime));
						
		assert (ref.data != mstest.data);	
		start = System.currentTimeMillis();
		mstest.mergeSort();
		long mstime = System.currentTimeMillis()-start;		
		assert (ref.data == mstest.data);		
		System.out.printf("mergesort \t%6d   \t%6.2fx\n",mstime, (((float)mstime)/reftime));
		
		
	
		
		
	}

}