package testStrings;

import java.util.HashSet;

public class Leaf {

	public char chr;
	public Leaf parent;
	static public final HashSet<Leaf> lowest = new HashSet<Leaf>();	
	
	public Leaf(String s) {
		this(null,0,s);				
	}
	
	public Leaf(Leaf parent, int index, String s) {		   
		
		this.chr = s.charAt(index);
		this.parent = parent;		
		
		if (index < s.length()-1) new Leaf(parent,index+1,s);
		
		if (s.length()>1) {
			String substr = s.substring(0,index)+s.substring(index+1);
			new Leaf(this, 0, substr);
		}
		else lowest.add(this);			
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();		
	}
	
	@Override
    public boolean equals(Object o) 
    { 
        if (!(o instanceof Leaf)) 
            return false; 
        Leaf other = (Leaf)o; 
        return this.toString().hashCode()==other.toString().hashCode()?true:false; 
    }
	
	@Override
	public String toString() {
		return  this.parent==null?String.valueOf(this.chr):this.parent.toString()+String.valueOf(this.chr); 		
	}
}
