package palindromes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Palindrome {

	
	static public class StringPieces{
		public final String left, right;
		public String mid;
		public final int len;
		
		public StringPieces(String l, String r, String m) {this.left = l; this.right=r;this.mid=m;this.len=this.left.length();}
		
		public char lchar(int i) {return this.left.charAt(i);}
		public char rchar(int i) {return this.right.charAt(this.len-i-1);}
				
		public StringPieces(String s) {
			int imid   = s.length()/2;
			boolean odd = (s.length()%2==1);
			this.left  = s.substring(0,imid);
			this.mid   = odd?s.substring(imid, imid+1):"";
			this.right = odd?s.substring(imid+1):s.substring(imid);
			this.len   = this.left.length();
			assert (left.length() == right.length());
		}
		
		public int palindiff() {
			int count = 0;
			for (int i=0;i<this.len;i++)
				if (lchar(i)!=rchar(i)) count++;			
			return count;
		}
		
		public char[] reverse(char[] c) {
			char[] r = new char[c.length];
			for (int i=0;i<c.length;i++) r[i]=c[c.length-i-1];
			return r;
		}
		

		public String plusOneDrome(int gimmes) {
            char[] pdrome = new char[this.len]; 
            for (int i=0;i<this.len;i++) {
                pdrome[i] = lchar(i)>rchar(i)?lchar(i):rchar(i);
                if ((pdrome[i]=='9') || (gimmes ==0)) continue;
                if (lchar(i)!=rchar(i)) {
                    gimmes--;

                    pdrome[i] = '9';
                    continue;
                }
                if (gimmes>1) {
                    gimmes -= 2;
                    pdrome[i] = '9';
                }
            }            
            if ((gimmes>0)&&(this.mid.length()==1)) this.mid = "9"; 
         
            return String.valueOf(pdrome)+this.mid+String.valueOf(reverse(pdrome));
        }		
	}

	static public int stringDiff(String s1, String s2) {
		if (s1.length() != s2.length()) return -1;
		int count = 0;
		for (int i=0;i<s1.length();i++) 
			if (s1.charAt(i)!=s2.charAt(i)) count++;
		return count;
	}
	
	static public String hailMary(String s,int k) {
		if (k>=s.length())       return String.format("%0"+s.length()+"d",0).replace('0', '9');
		int count = 0;
		for (char c:s.toCharArray()) if (c=='9') count++;
		if (count+k>=s.length()) return String.format("%0"+s.length()+"d",0).replace('0', '9');
		return "-1";
		
	}
	
	static public String highestValuePalindrome(String s, int lenS, int k) {
		if (hailMary(s,k)!="-1") return hailMary(s,k);  
		StringPieces p = new StringPieces(s);
		int minmoves = p.palindiff();
		int gimmes   = k - minmoves;
		System.out.printf("Gimmes %d\n", gimmes);
		if (gimmes<0) return "-1";
		
		return p.plusOneDrome(gimmes);
	}
	public static void main(String[] args) {
		
		
		String s="4242312344531334";
		
		int k=6;
		String p = highestValuePalindrome(s,s.length(),k);
		StringPieces lmr = new StringPieces(s);
		System.out.printf("string length %d\n", s.length());
		System.out.printf("moves available %2d min moves %2d moves used %2d\n",k,lmr.palindiff(), stringDiff(s, p));
		
//		for (k=1;k<s.length(); k++) {
//			String p = highestValuePalindrome(s,s.length(),k);
//			System.out.print("palindrome "+p);
//			StringPieces lmr = new StringPieces(s);
//			System.out.printf(" moves available %2d min moves %2d moves used %2d\n",k,lmr.palindiff(), stringDiff(s, p));
//		}
		
//		System.out.println(String.valueOf(new char[s.length()]).replace('\u0000','9'));
//		System.out.println(String.format("%0"+s.length()+"d",0).replace('0', '9'));
//		System.out.println(String.format("%d",(int)Math.pow(10, s.length())-1));
	}
}
