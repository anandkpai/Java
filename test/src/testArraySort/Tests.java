package testArraySort;

import java.util.ArrayList;
import java.util.Random;

public class Tests {

	static Random rand 	= new Random();
	static final int ARRAYLEN  = 10000000;
	static final int MAXINT    = 1000000;
	

	

	
	public static void main(String[] args) {
		SortArray  mstest = new SortArray(ARRAYLEN);
		SortArray  qtest  = mstest.clone();
		SortArray  intest = mstest.clone();
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
		
		assert (ref.data != intest.data);	
		start = System.currentTimeMillis();
		intest.inplaceQuickSort(0, intest.data.size()-1);
		long intime = System.currentTimeMillis()-start;		
		assert (ref.data == intest.data);		
		System.out.printf("inplaceqSort\t%6d   \t%6.2fx\n", intime, (((float)intime)/reftime));					
		
	}

}