package trees;

import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

public class ChessBoard {

	static public class Tile{
		int x,y;
		Tile parent;
		static public final HashMap<String, Tile> tilesVisited = new HashMap<String,Tile>();
		static public final Tile INVALID_TILE = new Tile(null,-1,-1);
		
		static public Tile nextTile(Tile p, int x, int y, int n) {
			String key = String.format("%02d%02d",x,y);
			if (tilesVisited.containsKey(key)) return tilesVisited.get(key);
			if ((x<0)||(y<0)) return INVALID_TILE;
			if ((x>=n)||(y>n)) return INVALID_TILE;
			Tile t = new Tile(p,x,y);
			tilesVisited.put(key,t);
			jumpFromTiles.add(t);
			return t;
		}
		
		public Tile(Tile p, int x, int y) {
			this.parent = p;
			this.x      = x;
			this.y      = y;
		}
		
		public int depth() {return this.parent==null?0:1+this.parent.depth();}
	}
	
	
	static Queue<Tile> jumpFromTiles = new LinkedList<Tile>();
	
	static public int movesToTarget(int n, int a, int b) {
	
		int[] xmoves = {+a,+a,-a,-a,+b,+b,-b,-b};
		int[] ymoves = {+b,-b,+b,-b,+a,-a,+a,-a}; 
		
		Tile.tilesVisited.clear();
		jumpFromTiles.clear();
		Tile.nextTile(null,0,0,n);
		
		while (!jumpFromTiles.isEmpty()) {
			Tile t = jumpFromTiles.remove();
			for (int i=0;i<8;i++) {
				Tile next_t = Tile.nextTile(t,t.x+xmoves[i],t.y+ymoves[i], n);
				if ((next_t.x==n-1)&&(next_t.y==n-1)) return next_t.depth();
			}
		}
		
		return -1;
	}
	
	public static void main(String[] args) {
		int n = 5;
		int a = 1;
		int b = 2;
	
		for (int i=1;i<n;i++)
			for (int j =i+1;j<n;j++)
			
		System.out.println(movesToTarget(n, a, b));

	}

}
