package trees;

import java.util.LinkedList;
import java.util.Queue;

public class KnightMoves {
	
	
	static public int movesMade;
	static public int legalMovesMade;
	static public final Queue<Tile> jumpOffTiles = new LinkedList<Tile>();
	static public final int board = 8;
	
	public static class Tile{

		public static final int a = 1;
		public static final int b = 2;
		public static final int[] xmoves = {+a,+a,-a,-a,+b,+b,-b,-b};
		public static final int[] ymoves = {+b,-b,+b,-b,+a,-a,+a,-a};
		public int x, y, movesleft;
  
		public Tile(int x,int y, int m) {
			this.x=x;
			this.y=y;
			this.movesleft=m;
			jumpOffTiles.add(this);
			legalMovesMade++;	
		}
		
		static boolean isLegal(int x, int y) {
			return (x<0)||(y<0)||(x>=board)||(y>=board)?true:false;
		}
		
		public void processMove(int i) {
			int newx = this.x+xmoves[i];
			int newy = this.y+ymoves[i];
			if (isLegal(newx, newy)) new Tile(newx,newy, this.movesleft-1); 				
		}
	}
	
	
	public static void main (String[] args) {
		int noOfMoves = 4;
		new Tile(0,0,noOfMoves);
		while (!jumpOffTiles.isEmpty()) {
			Tile t = jumpOffTiles.remove();
			if (t.movesleft==0) continue;
			movesMade += 8;
			for (int i=0;i<8;i++) t.processMove(i);
		}
		System.out.printf("%2d %2d\n", movesMade, legalMovesMade);
	}

}
