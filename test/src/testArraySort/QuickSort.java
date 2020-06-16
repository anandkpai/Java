package testArraySort;
import java.util.Random;

public class QuickSort {

	int[] data;
	static final int ARRAY_SIZE  = 10;
	static final int MAX_RND_INT = 100;
	static final Random  rand    = new Random(); 
	
	public QuickSort() {
		data = new int[ARRAY_SIZE];
		for (int i = 0; i < ARRAY_SIZE; i++) data[i] = rand.nextInt(MAX_RND_INT);		
	}
	
	public QuickSort(int arraySize,int maxRndInt) {
		data = new int[arraySize];
		for (int i = 0; i < arraySize; i++) data[i] = rand.nextInt(maxRndInt);		
	}
	
	@Override
	public String toString() {
		String s="[";
		int i;
		for (i=0;i<data.length-1;i++) s+= String.format("%d",data[i])+",";
		return s+String.format("%d",data[i])+"]";
	}
	
	public void quicksort(int low,int high)
	{
		int pivot = data[low+(high-low)/2];
		int i = low;
		int j = high;		
		while (i<j) {
			while (data[i]<pivot) i++;
			while (data[j]>pivot) j--;			
			swap(i++,j--);			
		}
		if (i<high) quicksort(i,high);
		if (j>low)  quicksort(low,j);
	}
	
	private void swap(int i,int j)
	{
		if (i==j) return;
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
}

