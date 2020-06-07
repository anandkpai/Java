package ArrayGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;
import java.util.*;



public class Solution {

	public static class Range{
		public int start, end;
		static public final Range error = new Range(-1,-1);
		public Range(int s, int e){
			this.start = s;
			this.end   = e;					
		}
	
		public boolean contains(int val) {
			return (val>=this.start)&&(val<=this.end)?true:false;
		}
		
		public boolean isConnected(int jump,Range other) {
			for (int i=this.start+jump;i<this.end+jump+1;i++)
				if (other.contains(i)) return true; 
			return false;
		}
		
		@Override
		public String toString() {
			return "("+String.format("%2d", this.start)+","+String.format("%2d", this.end)+")";
		}
	}
	
	public static class RangeList extends ArrayList<Range>{
		public RangeList(int[] game){
			super(game.length);
			int start, end, index=0;
			while (index<game.length) {
				while(index<game.length && game[index]!=0) index++;
				if (index < game.length) {
					start = index;
					while(index<game.length && game[index]==0) index++;
					end   = index-1; 
					this.add(new Range(start,end));
				} 
			}												
		}
		
		public int findNextConnectedRange(int index,int jump) {
			int runIndex=index+1;
			for (runIndex=index+1;runIndex<this.size();runIndex++){
				if (this.get(runIndex).start - this.get(index).end > jump) return -1;				
				if (this.get(index).isConnected(jump, this.get(runIndex))) return runIndex; 
			}
			return -1;
		}
		
	}
	

    public static boolean canWin(int leap, int[] game) {

    	RangeList goRanges = new RangeList(game);
//    	System.out.println(goRanges);
    	leap = leap<1?1:leap;
    	int index = 0;
    	while (index < goRanges.size()) {
    		if ((goRanges.get(index).end + leap)>= game.length) return true;
    		index = goRanges.findNextConnectedRange(index, leap);
    		if (index == -1) return false; 
    	}    		
    	return true;
    }

    public static void main(String[] args) throws Exception {
//    	File text = new File("Z:\\users\\anand\\pyscripts\\misc\\input09.txt");
//        Scanner scan = new Scanner(text);
//        int q = scan.nextInt();
//        int count = 0;
//        while (q-- > 0) {
//            int n = scan.nextInt();
//            int leap = scan.nextInt();
//            
//            int[] game = new int[n];
//            for (int i = 0; i < n; i++) {
//                game[i] = scan.nextInt();
//            }
//            System.out.println( (canWin(leap, game)) ? "YES" : "NO" );
//        }
//        scan.close();
    	int[] game = {0,0,0,1,1,0,1,0,1,0,0,0,1,1,1,0,0,0,1,1,1,0,1,1,0,1,1,0,1,0,0,0,0,1,0,0,1,1,1,1,0,1,0,0,1,1,0,1,1,1,1,1,1,1,1,0,1,0};
    	int leap = 0;
    	System.out.print( (canWin(leap, game)) ? "YES" : "NO" );
    }
}