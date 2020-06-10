package testStrings;

import java.util.HashSet;



public class Branch extends Tree {
	
	private Branch parent;
	private char chr;	
	private String toString="";
	private static final boolean DUMMYVALUE = true;
	
	private Branch(){}
	
	protected Branch(Branch branch,String substr) {
		this(branch,0,substr);
		Branch.nodeSignatures.clear();
	}
	
	private Branch(Branch branch,int index, String substr) {

		this.chr 	= substr.charAt(index);
		this.parent = branch;

		if (index == 0 )
			for (int i=1;i<substr.length();i++) new Branch(branch, i, substr);		
		
		// depth 
		String nodeSignature = this.toString()+substr;
		nodeSignature = this.parent!=null?branch.toString()+nodeSignature:nodeSignature;		
		if (Tree.nodeSignatures.putIfAbsent(nodeSignature, DUMMYVALUE)==null)
			if (substr.length()==2) 
				Tree.words.add(this.toString()+substr.substring(0,index)+substr.substring(index+1));
			else 
				new Branch(this,0,substr.substring(0,index)+substr.substring(index+1));
		//breadth

	
	}
	
	@Override
	public String toString() {
		if (this.toString.length()==0) 
			this.toString = this.parent != null?this.parent.toString()+String.valueOf(this.chr):String.valueOf(this.chr);
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
