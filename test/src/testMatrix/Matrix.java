package testMatrix;
import java.util.ArrayList;
import java.util.HashMap;


public class Matrix {

	 		
	public static class RowCol {
		
		public int row,col;
		
		public RowCol(int r,int c) {
			this.row = r;
			this.col = c;	
		}
		@Override
		public String toString() {
			return "("+String.format("%2d",this.row)+","+String.format("%2d",this.col)+")";
		}
	}
				
	private class InternalMap extends HashMap<Integer, RowCol>{
		
		public InternalMap(int size) {
			super(size);
		}

		@Override 
		public RowCol get(Object i) {
			return this.get((int)i);
		}
				
		public RowCol get(int i) {
			i = (this.size()+i%this.size())%this.size();
			return super.get(i);
		}
		
		@Override 
		public String toString() {
			String s = "";
			int i;
			for (i=0; i < this.size()-1;i++) s+=this.get(i).toString()+",";
			return s+this.get(i).toString();
		}
		
	}

	public int[][] data;
	public InternalMap m;
	
	public Matrix(RowCol rc) {		
		this.data = new int[rc.row][rc.col];		
		this.createMap();
		for (int i=0;i<m.size();i++) this.putkey(i, i);
	}
	
	public Matrix() {super();}

	public Matrix cloneStruct() {	
		int rowlen = this.data.length;
		int collen = this.data[0].length;		
		InternalMap otherm = (InternalMap)this.m.clone();		
		Matrix other       = new Matrix();
		other.data         = new int[rowlen][collen] ;
		other.m            = otherm;
		return other;
	}
		
	public int get(RowCol rc) {
		return this.data[rc.row][rc.col];
	}
	
	public void put(int val, RowCol rc) {
		this.data[rc.row][rc.col] = val;
	}
	
	public int getkey(int k) {
		RowCol rc = this.m.get(k);
		return this.get(rc);
	}
	
	public void putkey(int val, int k) {
		RowCol rc = this.m.get(k);		
		this.put(val,rc);
	}
	
	@Override
	public String toString() {
		int rowlen = this.data.length;
		int collen = this.data[0].length;		
		String s="";
		int i,j;
		for (i=0;i<rowlen;i++) {
			for (j=0;j<collen-1;j++) s+=String.format("%02d",this.data[i][j])+",";
			s+= String.format("%02d",this.data[i][j])+"\n";
		}
		return s; 
	}
		
	private void createMap() {
		int rowlen = data.length;
		int collen = data[0].length;
		m = new InternalMap(rowlen*collen);
		
		int count = 0;
		int d = 0;
		
		while ((rowlen> 0) & (collen> 0)) {
			if (rowlen == 1 ) for (int c=0;c<collen;c++) this.m.put(count++, new RowCol(0+d,c+d));
			else if  (collen == 1 ) for (int r=0;r<rowlen;r++) this.m.put(count++, new RowCol(r+d,collen-1+d));
			else {
				for (int c=0;c<collen-1;c++) this.m.put(count++, new RowCol(0+d,c+d)); 
				for (int r=0;r<rowlen-1;r++) this.m.put(count++, new RowCol(r+d,collen-1+d));
				for (int c=collen-1;c>0;c--) this.m.put(count++, new RowCol(rowlen-1+d,c+d)); 
				for (int r=rowlen-1;r>0;r--) this.m.put(count++, new RowCol(r+d,0+d));
			}
			rowlen -= 2; collen -= 2; d++;
		}
	}
	
	public Matrix rotatedbyN(int n)
	{
		Matrix other = this.cloneStruct();
		for (int i=0;i<this.m.size();i++) other.putkey(i, this.getkey(i+n));
		return other;
	}
}
