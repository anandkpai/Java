package testArraySort;

import java.util.ArrayList;
import java.util.Random;

public class TestArrays {

	static Random rand 	= new Random();
	static final int ARRAYLEN  = 100;
	static final int MAXINT    = 100;
	

	

	
	public static void main(String[] args) {
//		int[] dinit =  { 1,2,3,4,10,6,7,8,9,5 };		
		
//		ArraySort  init   = new ArraySort(ARRAYLEN,MAXINT);
////		trick.sort(null);
//		ArraySort  ref    = (ArraySort) init.clone();
//		ArraySort  mtest  = (ArraySort) init.clone();
//		ArraySort intest  = (ArraySort) init.clone();
//		ArraySort  ftest  = (ArraySort) init.clone();
//		
//		long start;
//		start = System.currentTimeMillis();
//		ref.sort(null);
//		long reftime = System.currentTimeMillis()-start;
//		System.out.printf("reference time \t%6d\n",reftime);
		
	PrimitiveArray a = new PrimitiveArray(ARRAYLEN,MAXINT);
	System.out.println(a);
	a.quicksort();
	System.out.println(a);
	a.removeDupes();
	System.out.println(a);
}

}