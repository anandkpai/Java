package testArraySort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class SortArray {
	
	static final int DEFAULT_ARRAY_CAPACITY = 100;
	static final int MAX_RANDOM_INT_VALUE   = 10000;
	static Random rand 		  				= new Random();
	
	ArrayList<Integer>  data ;

    @Override
    public String toString() { 
        return data.toString(); 
    } 
    		
	static void randomfill(ArrayList<Integer>d,int size, int max_val)
	{	
		for (int i = 0; i < size ; i++)	{d.add(i, rand.nextInt(max_val));}						
	}
	
	public SortArray(int n ) {
		data = new ArrayList<Integer>(n);
		randomfill(data, n, MAX_RANDOM_INT_VALUE);
	}

	public SortArray(int n, int m ) {
		data = new ArrayList<Integer>(n);
		randomfill(data, n, m);
	}
		
	public SortArray() {
		data = new ArrayList<Integer>(DEFAULT_ARRAY_CAPACITY);		
		randomfill(data, DEFAULT_ARRAY_CAPACITY, MAX_RANDOM_INT_VALUE);
	}
	
	public SortArray(ArrayList<Integer> d) {
		this.data = d;
	}
		
	public SortArray clone() {
		return new SortArray((ArrayList<Integer>) this.data.clone());
	}

	public ArrayList<ArrayList<Integer>> splitOnVal(final int p)
	{
		ArrayList<Integer> less    = new ArrayList<Integer> (this.data.size());
		ArrayList<Integer> equal   = new ArrayList<Integer> (this.data.size());
		ArrayList<Integer> greater = new ArrayList<Integer> (this.data.size());
		Iterator<Integer>  values  = this.data.iterator();    
		int value;
		while (values.hasNext())
		{
			value = values.next();
			if (p == value) equal.add(value);
			else if (p <  value) less.add(value);
			else greater.add(value);							
		}
		this.data = less;
//		equal.trimToSize();
//		greater.trimToSize();
		ArrayList<ArrayList<Integer>> returnContainer = new ArrayList<ArrayList<Integer>>(2);
		returnContainer.add(equal);
		returnContainer.add(greater);		
		return returnContainer;												
	}	    
		
	public void quickSort()
	{
		if (this.data.size() >  1)
		{
			int midval        = this.data.get(this.data.size()/2 - 1);
			ArrayList<ArrayList<Integer>> parts = this.splitOnVal(midval);			
			SortArray equal   = new SortArray(parts.get(0));
			SortArray greater = new SortArray(parts.get(1));
			this.quickSort();
			this.data.addAll(equal.data);
			if (greater != null) 
			{
				greater.quickSort();
				this.data.addAll(greater.data);
			}			
		}
	}
	
	public SortArray split()
	{
		if (this.data.size() < 2) {
			return null;
		} 
		int midpos        			 = this.data.size()/2 - 1 ;
		ArrayList<Integer> rightdata = new ArrayList<Integer>(this.data.subList(midpos, this.data.size()-1));
		this.data.subList(midpos, this.data.size()-1).clear();
//		this.data.trimToSize();
		return new SortArray(rightdata);
	}

	private void merge(SortArray right)
	{
		ArrayList<Integer> merged = new ArrayList<Integer>(this.data.size()+right.data.size());
		int leftIndex 			  = 0;
		int rightIndex 			  = 0; 
		while ((leftIndex < this.data.size()) & (rightIndex < right.data.size())){
			if (this.data.get(leftIndex) < right.data.get(rightIndex)) merged.add(this.data.get(leftIndex++));
			else merged.add(right.data.get(rightIndex++));}
		if (this.data.size()  > leftIndex ) merged.addAll(this.data.subList(leftIndex, this.data.size()));
		if (right.data.size() > rightIndex) merged.addAll(right.data.subList(rightIndex, right.data.size()));
		this.data = merged;
	}
	
	public void mergeSort()
	{
		if (this.data.size() >  1)
		{
			SortArray right = this.split();
			this.mergeSort();
			right.mergeSort();
			this.merge(right);
		}		
	}
	
	public void inplaceQuickSort(int low,int high){
		int pivot = this.data.get(low+(high-low)/2);
		int left  = low;
		int right = high;		
		while(left<=right) {
			while (data.get(left)<pivot) left++;
			while (data.get(right)>pivot) right--;
			if (left<=right) swap(left++,right--);			
		}
		if (right>low) inplaceQuickSort(low,right);
		if (left<high) inplaceQuickSort(left,high);
	}
	
	private void swap(int left, int right){
		int tmp = this.data.get(left);
		this.data.set(left, this.data.get(right));
		this.data.set(right,tmp);
	}

}
