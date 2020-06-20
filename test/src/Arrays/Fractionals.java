package Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fractionals {
	
    static public class Fraction{
        public int num;
        public int den;
        
        public Fraction (String s)
        {
            int breakPt = s.indexOf('/');
            this.num    = Integer.valueOf(s.substring(0,breakPt));
            this.den    = Integer.valueOf(s.substring(breakPt+1));                
        }
        
        public Fraction (int n, int d)
        {
            this.num    = n;
            this.den    = d;
            this.reduce();
        }

        public void negate() {this.num = -this.num;}
        
        static public ArrayList<Integer> factors(int n){
        	int rangeTop = (int) Math.sqrt(n);
            rangeTop = rangeTop%2==0?rangeTop+1:rangeTop;
        	ArrayList<Integer> factors = new ArrayList<Integer>(rangeTop);
        	for (int i=rangeTop;i> 2;i -= 2)
        		if (n%i==0) factors.add(i);        	
        	return factors;
        }
        
        public void reduce() {
        	
            if (this.num==0) {
        		this.den = 1;
        		return;
    		}
            
            if ((this.num%this.den==0)){
                this.num = this.num/this.den;
                this.den = 1;
                return;
            }            
            
            if (this.den==1) return;
            
        	while ((this.num%2==0)&&(this.den%2==0)){
        		this.num = this.num/2;
        		this.den = this.den/2;        				
        	}
            
        	ArrayList<Integer> factors = factors(this.den);
        	for (int d:factors)
        		while ((this.num%d==0) && (this.den%d==0)) {
        			this.num = this.num/d;
        			this.den = this.den/d;
        		}
        	if (this.den<0) {
        		this.den = -this.den;
        		this.num = -this.num;
        	}
        }
                        
        public Fraction add(Fraction other) {
        	if (this.den == other.den) 
        		return new Fraction(this.num+other.num,this.den);
        	return new Fraction(this.num*other.den+other.num*this.den,this.den*other.den);
    	}
        
        @Override
        public String toString() {return String.format("%d/%d", this.num, this.den);}
    }

    static List<String> b(String expression)
    {    	    	
    	if ((expression.charAt(0)!='+')&&(expression.charAt(0)!='-')) expression = "+"+expression;
    	ArrayList<Integer> signPositions  = new ArrayList<Integer>(expression.length()/3);
    	int pos = 0;
    	while (pos>-1) {
    		pos  = expression.indexOf('+',pos+1);
    		if (pos>0) signPositions.add(pos);
    	}
    	pos = 0;
    	while (pos>-1) {
    		pos  = expression.indexOf('-',pos+1);
    		if (pos>0) signPositions.add(pos);
    	}
    	
    	signPositions.sort(Integer::compare);    	
    	List<String> flist = new ArrayList<String>(expression.length()/3);
		int prevpos = 0;
		for (int nextpos:signPositions) {
			flist.add(expression.substring(prevpos, nextpos));
			prevpos = nextpos;				
		}
		flist.add(expression.substring(prevpos));
    	return flist;
    }
    
    
    static String fractionAddition(String expression) {    	
        Fraction result = new Fraction(0,1);
        for (String s:fractionStrings(expression)) result = result.add(new Fraction(s));             
        return result.toString();
    }
	
	static List<String> fractionStrings(String s){
    	 if ((s.charAt(0)!='+')&&(s.charAt(0)!='-')) s = "+"+s;		
		 Pattern p = Pattern.compile("(\\+|\\-)([0-9]{1,}\\/[0-9]{1,})");
    	 if ((s.charAt(0)!='+')&&(s.charAt(0)!='-')) s = "+"+s;		 
		 Matcher m = p.matcher(s);
		 List<String> matches = new ArrayList<>();
		 while (m.find()) matches.add(m.group(0));
		 return matches;
	}
	
	public static void main(String[] args) {
//		System.out.println(Integer.valueOf("+1"));
//		System.out.println(String.format("%d",1));

		String s = "3/4-2/1+4/9-5/1-2/9";
		System.out.println(fractionAddition(s));
		System.out.println(new Fraction(-1953,324));
		System.out.println(fractionStrings(s));
	}
	


}