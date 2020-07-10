package palindromes;

import java.util.HashMap;

public class Circular {

	
	static public class PalinString{
		public final String s;
		public final int strlen;
		public final HashMap<Integer, Integer> oddcache;
		public final HashMap<Integer, Integer> evencache;
		
		public PalinString(String s) {
			this.s 		= s;
			this.strlen = s.length()	;
			this.oddcache = new HashMap<Integer, Integer>(this.strlen);
			this.evencache = new HashMap<Integer, Integer>(this.strlen);			
			buildCaches();
		}
		
		public void buildCaches() {
			String circle = this.s+this.s+this.s;
			for (int i=0;i<this.strlen;i++) {
				this.oddcache.put(i, getOddMax(circle,this.strlen+i, this.strlen));
				this.evencache.put(i, getEvenMax(circle,this.strlen+i, this.strlen));
			}
		}
		
		
		static public String breakString(String s, int i) {
			String str = "";
			str +=s.substring(0,i).length()>0?s.substring(0,i):"";
			str += ":"+s.substring(i,i+1)+":";
			str +=s.substring(i+1).length()>0?s.substring(i+1):"";
			return str;
		}
			
		public String rotated(int n) {return this.s.substring(n)+this.s.substring(0,n);}
		
		@Override
		public String toString() {
			String p = this.s+"\n";
			String c = this.s+this.s+this.s;
			for (int i=0;i<this.strlen;i++) {
				p+= String.format(" index:%2d oddMax:%2d evenMax:%2d\n" , i, this.oddcache.get(i), this.evencache.get(i));
			}						
			return p;
		}
			
		static public int getOddMax(String c, int index, int maxlen) {
			int spread  = 1;
			int oneSide = (int)maxlen/2+maxlen%2;
			while (((index-spread)>=0)&&((index+spread)<c.length())) {
				if (c.charAt(index-spread)!=c.charAt(index+spread)) break;
				if (spread>oneSide) break;
				spread++;
			}			
			return 2*spread-1;
		}

		static public int getEvenMax(String c, int index, int maxlen) {
			int spread = 0;
			int oneSide = (int)maxlen/2;
			while (((index-spread)>=0)&&((index+spread)+1<c.length())) {
				if (c.charAt(index-spread)!=c.charAt(index+spread+1)) break;
				if (spread>oneSide) break;
				spread++;							
			}
			return 2*spread;
		}
	
		
		static public int getConstrainedOdd(int rindex, int maxpos, int maxval, int strlen) {
			int left  = maxpos>=rindex?maxpos-rindex:strlen+maxpos-rindex-1;
			int right = maxpos>=rindex?strlen+rindex-maxpos-1:rindex-maxpos;
			int maxSize = 2*Integer.min(left,right)-1;
			return Integer.min(maxval, maxSize);
		}
		
		static public int getConstrainedEven(int rindex, int maxpos, int maxval, int strlen) {
			int left  = maxpos>=rindex?maxpos-rindex:strlen+maxpos-rindex-1;
			int right = maxpos>=rindex?strlen+rindex-maxpos-1:rindex-maxpos;
			int maxSize = Integer.min(2*Integer.min(left,right), strlen-strlen%2);
			return Integer.min(maxval, maxSize);
		}
				
		
		public int maxSizeOfPDromeForRotation(int rotationIndex) {
//			String r = this.rotated(rotationIndex);
			int maxval = 0;
			int odd, even;
			for (int i=0;i<this.strlen;i++) { 
				odd     = getConstrainedOdd(rotationIndex	, i, this.oddcache.get(i), this.strlen);
//				odd  = getOddMax(r, val, this.strlen);								
//				even = Integer.max(maxval,getEvenMax(r, val, this.strlen));
				even     = getConstrainedEven(rotationIndex	, i, this.evencache.get(i), this.strlen);
				maxval = Integer.max(maxval, Integer.max(odd,even));
			}
			return maxval;
		}		
	}
	
	
	
	public static int[] circularPalindromes(String s) {
		int[] result =  new int[s.length()];
		PalinString p = new PalinString(s);
		for (int i=0;i<s.length();i++) result[i]=p.maxSizeOfPDromeForRotation(i);
		return result;
	}
	
	public static void main(String[] args) {
		String s = "aaaaabbbbaaaa";
		PalinString p = new PalinString(s);
		System.out.println(p);

		int[] answer = {12,12,10,8,8,9,11,13,11,9,8,8,10};
		int[] result = circularPalindromes(s);
		for (int i=0;i<result.length;i++) System.out.println(String.format("%2d answer %2d result %2d", i, answer[i],result[i]));
//		System.out.println(p.maxSizeOfPDromeForRotation(7));		
	}

}
