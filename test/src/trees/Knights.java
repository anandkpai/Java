
package trees;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
import java.util.LinkedList;






public class Knights {
	
	public static class Tile{
		int x,y;
		Tile parent;
		
		public Tile(Tile parent, int x, int y) {
			this.parent = parent;
			this.x      = x;
			this.y      = y;
			visited.add(tileString(x,y));
		}
		
		public int depth() {return this.parent==null?0:1+this.parent.depth();}
		
		@Override 
		public int hashCode() {return tileString(this.x,this.y).hashCode();}
		
		@Override
		public boolean equals(Object other) { return (other instanceof Tile)&&(this.hashCode()==other.hashCode())?true:false;}
		
		
	}
	
	static public String tileString(int x, int y) {return String.format("%02d%02d",x,y);}
	
	static public HashSet<String> visited = new HashSet<String>();
	static public Queue<Tile> visit       = new LinkedList<Tile>();
	
	static public boolean isLegal(int x, int y, int n) {
		if ((x<0)||(y<0)||(x>=n)||(y>=n)) return false;
		if (visited.contains(tileString(x,y))) return false;		
		return true;
	}
	
	static int movesToTarget(int n, int a, int b) {
		int quickcheck = specialCaseLinear(n, (a+b));
		if (quickcheck != -1) { 
			return 2*quickcheck;
		}
		int[] xvals = {+a,+a,-a,-a,+b,+b,-b,-b};
		int[] yvals = {+b,-b,+b,-b,+a,-a,+a,-a};
		visited.clear();
		visit.clear();
		
		visit.add(new Tile(null,0,0));
		
		while (!visit.isEmpty()) {
			Tile t = visit.remove();
			for (int i=0;i<8;i++) {
				int xnew = t.x+xvals[i];
				int ynew = t.y+yvals[i];
				if ((xnew == n-1) && (ynew==n-1)) return t.depth()+1;
				if (isLegal(xnew, ynew, n)) visit.add(new Tile(t,xnew,ynew)); 
			}				
		}
		return -1;
	}
	
	
	static int specialCaseLinear(int n, int i) {return (n-1)%i==0?(n-1)/i:-1;} 
	
		
	public static int[][] knightlOnAChessboard(int n) {
		int[][] results = new int[n-1][n-1];		
		for (int i=1;i<n;i++) results[i-1][i-1] = specialCaseLinear(n,i);		
    	for (int i=1;i<n;i++)
    		for (int j=i+1;j<n;j++) {
    			results[i-1][j-1] = movesToTarget(n,i,j);
    			results[j-1][i-1] = results[i-1][j-1];
    		}    			    			    			    			
		return results;
    }

    

	public static void main(String[] args) {

    	int n = 25;
//    	int a = 3; int b= 1;
//		movestoTarget( n,  a,  b);
		
    	
        int[][] result = knightlOnAChessboard(n);
        int i, j;
        for (i=0;i<result.length;i++) {
        	for (j=0;j<result[i].length-1;j++) {
        		System.out.print(String.format("%02d",result[i][j])+",");        		
        	}        		
        	System.out.println(String.format("%02d",result[i][j]));    		
        }
    }

}
