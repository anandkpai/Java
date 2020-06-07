package trees;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	static public class Tile{
		private int x=0;
		private int y=0;
		private Tile parent=null;
		static public Tile target=null;
		
		static public Queue<Tile>  queue = new LinkedList<Tile>();
		static private HashSet<String> pos  = new HashSet<String>();
		
		static private String key(int x, int y) {return String.format("%02d%02d", x,y);}
		
		private Tile (Tile parent, int x, int y) {
			this.parent = parent;
			this.x      = x;
			this.y      = y;
			Tile.queue.add(this);
			Tile.pos.add(key(x,y));
		} 
		
		static public void init() {
			Tile.pos.clear();
			Tile.queue.clear();
			Tile.target=null;
			new Tile(null, 0,0);
		}
		
		private void processMove(int n, int a, int b) {
			if ((this.x+a==n-1)&&(this.y+b==n-1)) {
				Tile.target = new Tile(this, this.x+a, this.y+b);
				Tile.queue.clear();
				return;
			}
			if ((this.x+a <0)||(this.y+b<0)) return;
			if ((this.x+a >n-1)||(this.y+b>n-1)) return;
			if (Tile.pos.contains(key(this.x+a, this.y+b))) return;
			new Tile(this, this.x+a, this.y+b);
		}
		
		public void processAllMoves(int n, int a, int b) {
			processMove(n, a, b); if (Tile.target != null) return;
			processMove(n, a,-b); if (Tile.target != null) return;
			processMove(n,-a, b); if (Tile.target != null) return;
			processMove(n,-a,-b); if (Tile.target != null) return;
			processMove(n, b, a); if (Tile.target != null) return;
			processMove(n, b,-a); if (Tile.target != null) return;
			processMove(n, b, a); if (Tile.target != null) return;
			processMove(n,-b,-a); if (Tile.target != null) return;
			processMove(n,-b, a); if (Tile.target != null) return;
		}				
		
		public int depth() { return this.parent==null?0:1+this.parent.depth();}
	}
	

	
    public static int movestoTarget(int n, int a, int b) {
    	Tile.init();
    	while(!Tile.queue.isEmpty()) Tile.queue.remove().processAllMoves(n, a, b);    	
    	return Tile.target==null?-1:Tile.target.depth();
	}
	
	
	public static int specialCaseLinear(int n, int i) {
		return (n-1)%i==0?(n-1)/i:-1;
	}	
	
	
	static int[][] knightlOnAChessboard(int n) {
		int[][] results = new int[n-1][n-1];		
		for (int i=1;i<n;i++) results[i-1][i-1] = specialCaseLinear(n,i);		
    	for (int i=1;i<n;i++)
    		for (int j=i+1;j<n;j++) {
    			results[i-1][j-1] = movestoTarget(n,i,j);
    			results[j-1][i-1] = results[i-1][j-1];
    		}    			    			    			    			
		return results;
    }

    

	public static void main(String[] args) {

    	int n = 5;
//    	int a = 3; int b= 1;
//		movestoTarget( n,  a,  b);
		
    	
        int[][] result = knightlOnAChessboard(n);
        for (int i=0;i<result.length;i++) {
        	for (int j=0;j<result[i].length;j++) {
        		System.out.print(String.format("%2d",result[i][j])+",");        		
        	}        		
        	System.out.println();    		
        }
    }
}