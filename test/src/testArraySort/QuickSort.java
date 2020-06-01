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
	
	
	public void quicksort(int low,int high)
	{
		int pivot = data[low+(high-low)/2];
		int left  = low;
		int right = high;
		while (left <= right) {
			while (data[left] < pivot) left++;
			while (data[right] > pivot) right--;
			if (left <= right) swap(left++,right--);
		}
		if (left<high) quicksort(left,high);
		if (right>low) quicksort(low,right);
	}
	
	private void swap(int i,int j)
	{
		int tmp = data[i];
		data[i] = data[j];
		data[j] = tmp;
	}
}
