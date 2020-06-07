package testArraySort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ArraySort extends ArrayList<Integer> {
	private static final int ARRAY_SIZE = 100;
	private static final int MAX_INT    = 100;
	private static final Random rand    = new Random();
	
	public ArraySort(int arraysize,int maxint) {
		super(arraysize);
		for (int i=0;i<arraysize;i++) this.add(rand.nextInt(maxint));
	}
	
	public ArraySort() {
		this(ARRAY_SIZE,MAX_INT);
	}
	
	public ArraySort(int arraysize) {
		super(arraysize);
	}

	
	private void swap(int i,int j) {
		int tmp = this.get(i);
		this.set(i, this.get(j));
		this.set(j,tmp);
	}
	
	private void quicksort (int low, int high){
		int pivot = this.get(low+(high-low)/2);
		int left  = low;
		int right = high;
		
		while (left < right) {
			while (this.get(left)<pivot) left++;
			while (this.get(right)>pivot) right--;
			if (left <= right) swap(left++,right--); 
		}
		if (left < high) this.quicksort(left,high);
		if (right > low) this.quicksort(low,right);		
	}
	
	
	public void quicksort() {
		this.quicksort(0,this.size()-1);
	}
	
	private ArraySort split() {
		ArraySort other = (ArraySort) this.clone();
		int mid = this.size()/2;
		this.removeRange(mid,this.size());
		other.removeRange(0, mid+1);
		return other;
	}
	
	private void merge(ArraySort other) {
		int left  = 0;
		int right = 0;
		ArraySort me  = (ArraySort)this.clone();
		this.clear();
		this.ensureCapacity(this.size()+other.size());
		while (left<me.size() & right < other.size()) {
			if (me.get(left) < other.get(right)) this.add(me.get(left++));
			else this.add(other.get(right++));
		}		
		if (left<me.size()) this.addAll(me.subList(left+1, me.size()));		
		if (right<other.size()) this.addAll(other.subList(right+1, other.size()));
	}
	
	public void mergesort() {
		if (this.size()>1) {
			ArraySort other = this.split();
			this.mergesort();
			other.mergesort();
			this.merge(other);
		}
			
	}
	
	private void triage(int val, int pivot,ArraySort equals,ArraySort greaters){
		if (val < pivot) this.add(val);
		else if (val == pivot) equals.add(val);
		else greaters.add(val);
	}
	
	public void filtersort()
	{
		if (this.size() > 1){
			int pivot          = this.get(this.size()/2-1) ;
			ArraySort myClone  = (ArraySort)this.clone()   ;
			ArraySort equals   = new ArraySort(this.size());
			ArraySort greaters = new ArraySort(this.size());
			this.clear();
			myClone.forEach(val->triage(val, pivot, equals, greaters));
			this.filtersort()    ;
			this.addAll(equals)  ;
			greaters.filtersort();
			this.addAll(greaters);
		}			
	}
	
	public void maxmin()
	{
		int max, min;
		
	}
	
}
