package trees;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class RedKnight {

    static public int boardSize;    
    static public final Queue<Tile> q = new LinkedList<>();
    static public final HashSet<String> visited = new HashSet<>();

    static public class Tile{

        static public final int[] vertMoves  = {-1,+1,+2,+1,-1,-2};
        static public final int[] horMoves   = {-2,-2,+0,+2,+2,+0};
        static public final String[] moves   = {"UL ","UR ","R ","LR ","LL ","L "};

        private final int x;
        private final int y;
        public final String movehist;
        public final int depth;



        public Tile(int x, int y, String movehist,int depth){
            this.x = x;
            this.y = y;
            this.movehist = movehist;
            this.depth = depth;
            q.add(this);
            visited.add(this.toString());
        }

        @Override 
        public String toString(){return tilePos(this.x,this.y);}

        public static String tilePos(int x, int y){ return String.format("%03d%03d", x,y);}

        public boolean isTarget(int tx, int ty){return ((this.x==tx)&&(this.y==ty));}

        public void processMove(int i){
            int newx = this.x+horMoves[i];
            int newy = this.y+vertMoves[i];
            if ((newx>=boardSize)||(newx<0)||(newy>=boardSize)||(newy<0)) return;
            if (visited.contains(tilePos(newx,newy))) return;
            String newhist = this.movehist+moves[i];
            int newdepth    = this.depth+1;
            new Tile(newx, newy, newhist, newdepth);
        }
    }


    static void printShortestPath(int n, int i_start, int j_start, int i_end, int j_end) {
        boardSize = n+1;
        q.clear();
        visited.clear();

        new Tile(i_start, j_start,"",0);
        while (!q.isEmpty()){
            Tile t = q.remove();
            if (t.isTarget(i_end, j_end)){
                System.out.println(t.depth);
                System.out.println(t.movehist);
                return;
            }
            for (int i=0;i<6;i++) t.processMove(i);
        }
        System.out.println("Impossible");
    }
	
	static public void main (String[] args) {
		printShortestPath(7,6,6,0,1);
		System.out.println();
	}

}
