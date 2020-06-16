package palindromes;

import palindromes.Palindrome.StringPieces;

public class Solution {

	
	
	static public char[] reverse(char[]c) {
		char[] r = new char[c.length];
		for (int i=0;i<c.length;i++) r[i]=c[c.length-i-1];
		return r;
	}
	
	
	static public String highestValuePalindrome(String s, int k) {
		int len    = s.length()/2;
		String left  = s.substring(0,len);
		String right = s.length()%2==0?s.substring(len):s.substring(len+1);
		String mid   = s.length()%2==0?"":s.substring(len,len+1);
		
		int minmoves = 0;
		for (int i=0;i<len;i++)
			if (left.charAt(i) != right.charAt(len-i-1)) minmoves++;
		int gravy    = k-minmoves;
		if (gravy<0) return "-1";
		char[] pdrome = new char[len];
		for (int i=0;i<len;i++) {
			pdrome[i] = left.charAt(i)>right.charAt(len-i-1)?left.charAt(i):right.charAt(len-i-1);
			if ((gravy==0)||pdrome[i]=='0') continue;
			if (left.charAt(i)!=right.charAt(len-i-1)) {
				gravy--;
				pdrome[i]='9';
				continue;
			}
			if (gravy>1) {
				gravy -= 2;
				pdrome[i]='9';				
			} 
		}
		
		if ((mid.length()==1)&&(gravy>0)) mid = "9"; 
		
		return String.valueOf(pdrome)+mid+String.valueOf(reverse(pdrome));
	}
	
	
	
	public static void main(String[] args) {
		String s="4242312944531334";
		


		
		for (int k=5; k<17;k++)
			System.out.println(highestValuePalindrome(s,k));
//		System.out.printf("moves available %2d min moves %2d moves used %2d\n",k,lmr.palindiff(), stringDiff(s, p));
		
		

	}

}
