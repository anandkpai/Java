package general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CircularArray {

	static public class CircArray extends ArrayList<Integer> {
		public final int  length;
		public int currpos=0;
		public boolean forward = true;
		
		public CircArray(int[] nums) {
			super(nums.length);
			this.length = nums.length;
			for (int n:nums) this.add(n);
		}
		
		public int fitPos(int pos) {
			pos = pos%this.length;
			pos = pos<0?pos+this.length:pos;
			return pos;
		}
		
		@Override
		public Integer get(int index) {
			index = fitPos(index); 
			this.currpos=index;
			return super.get(index);
		}
		
		public boolean stepToNext() {
			
			currpos  += this.get(currpos);
		return false;		
		}
	}
	
	
    static public boolean circularArrayLoop(int[] nums) {
    	CircArray carr = new CircArray(nums);	
		
    	boolean forward;
    	boolean circ = false;
    	int step, nextstep;
		for (int i=0;i<carr.length;i++) {
			circ = false;
			step = carr.get(i);
			if (step==0) continue;
			forward = step>0?true:false; 
			while (true) {
				step = carr.get(step);
				
			}
		}
		return circ;
    }
	
	public static void main(String[] args) {
		int[] array = {2,-1,1,2,2};
	}

}
