package general;

import java.util.ArrayList;
import java.util.List;

public class MaxWater {

	static public class Bar implements Comparable<Bar>{
		private final int height;
		private final int index;
		
		public Bar(int h, int i) {
			this.height=h;
			this.index =i;		
		}
		
		@Override
		public int compareTo(Bar other) {return -Integer.compare(this.height, other.height);}
		
		public int area(Bar other) {return Math.abs(this.index-other.index)*Integer.min(this.height, other.height);	}
	}
	
	
	
	public static void main(String[] args) {
		int[] heights  = {1,8,6,2,5,4,8,3,7};
		List<Bar> bars = new ArrayList<>(heights.length); 
		for (int i=0;i<heights.length;i++) bars.add(new Bar(heights[i],i));
		
	}

}
