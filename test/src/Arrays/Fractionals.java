package Arrays;

import java.util.ArrayList;
import java.util.List;

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
        	ArrayList<Integer> factors = new ArrayList<Integer>(rangeTop);
        	for (int i=rangeTop;i< 2;i -= 2)
        		if (n%i==0) factors.add(i);        	
        	return factors;
        }
        
        public void reduce() {
        	if (this.num==0) {
        		this.den = 1;
        		return;
    		}
        	while ((this.num%2==0)&&(this.den%2==0)){
        		this.num = this.num/2;
        		this.den = this.den/2;        				
        	}
        	ArrayList<Integer> factors = factors(this.den);
        	for (int d:factors)
        		if ((this.num%d==0) && (this.den%d==0)) {
        			this.num = this.num/d;
        			this.den = this.den/d;
        		}        	
        }
                        
        public Fraction add(Fraction other) {return new Fraction(this.num*other.den+other.num*this.den,this.den*other.den);}
        
        @Override
        public String toString() {return String.format("%d/%d", this.num, this.den);}
    }
    
    static String fractionAddition(String expression) {
    	
    	int prevpos, pos = 1;
		List<Fraction> flist = new ArrayList<Fraction>(expression.length()/3);
		while (pos < expression.length()) {
			prevpos = pos;			
			pos = expression.indexOf('+',prevpos);
		    pos	= pos>1?pos:expression.indexOf('-',prevpos);
			if (pos>1) flist.add(new Fraction(expression.substring(prevpos,pos)));
		}
        
        Fraction result = new Fraction(0,1);
        for (Fraction f:flist) result = result.add(f);
        
        return result.toString();
        
    }
	
	
	
	public static void main(String[] args) {
//		System.out.println(Integer.valueOf("+1"));
//		System.out.println(String.format("%d",1));

		System.out.println(fractionAddition("-1/2+1/2"));
	}
	


}