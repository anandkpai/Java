package testArraySort;
import java.util.Random;
import java.util.Iterator;

public class PrimitiveArray {

	private static final int ARRAY_SIZE = 1000;
	private static final int MAX_INT    = 1000;
	private static final Random rand    = new Random();
	public int[] data;
	private boolean sorted=false;
			
	
	public PrimitiveArray(int size, int maxint) {
		data = new int[size];
		for (int i=0;i<size;i++) data[i] = rand.nextInt(maxint);
	}
	
	public PrimitiveArray() {
		this(ARRAY_SIZE, MAX_INT);
	}
		

	@Override
	public String toString() {
		String s="";
		int i;
		for (i=0;i<this.data.length-1;i++) s+= Integer.toString(this.data[i])+',';
		return s+Integer.toString(this.data[i]);
	}
	
	private void swap(int i,int j) {
		if (i!=j) {
			int tmp = this.data[i];
			this.data[i] = this.data[j];
			this.data[j] = tmp;
		}
	}
	
	public void quicksort(){
		if (!sorted) {
			quicksort(0,data.length-1);
			sorted=true;
		}
	}
	
	private void quicksort(int low,int high) {
		int pivot   = this.data[low+(high-low)/2];
		int left 	= low;
		int right   = high;
		
		while (left < right)
		{
			while (this.data[left] < pivot) left++;
			while (this.data[right] > pivot) right--;
			if (left <= right) swap(left++,right--);
		}
		if (left<high) quicksort(left,high);
		if (right>low) quicksort(low,right);				
	}
	
	public void removeDupes() {
		int count = 0;
		int[] tmp = new int[this.data.length];
		tmp[0]    = this.data[0];
		
		this.quicksort();
		for (int i=1;i<this.data.length;i++) if (tmp[count] != this.data[i]) tmp[++count]=this.data[i];
		
		if (count != this.data.length) {			
			int[] newData = new int[count+1];
			for (int i=1;i<count+1;i++) newData[i] = tmp[i];
			this.data = newData;
		}
	}
}
