package testStrings;

import java.util.HashSet;



public class Branch extends Tree {
	
//	private Branch parent;
	private char chr;	
	private String toString="";
//	private static final boolean DUMMYVALUE = true;
	
	@SuppressWarnings("unused")
	private Branch(){}
	
	protected Branch(Branch branch,String substr) {
		this(branch,0,substr);
		Tree.nodeSignatures.clear();
	}
	
	private Branch(Branch parent ,int index, String substr) {

		this.chr 	  = substr.charAt(index);
		this.toString = parent!= null?parent.toString()+String.valueOf(this.chr):String.valueOf(this.chr);

		//breadth
		if (index == 0 )
			for (int i=1;i<substr.length();i++) new Branch(parent, i, substr);		
		
		// depth 
		String nodeSignature = this.toString()+substr;
		nodeSignature = parent!=null?parent.toString()+nodeSignature:nodeSignature;
		
		if (Tree.nodeSignatures.add(nodeSignature))
			if (substr.length()==2) 
				Tree.words.add(this.toString()+substr.substring(0,index)+substr.substring(index+1));
			else 
				new Branch(this,0,substr.substring(0,index)+substr.substring(index+1));	
	}
	
	@Override
	public String toString() {
		return this.toString;	
	}
	
	@Override
	public int hashCode() {
		return this.toString().hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Branch )) return false;
		Branch other = (Branch)o;
		return this.toString().hashCode()==other.toString().hashCode()?true:false;
	}	
}
