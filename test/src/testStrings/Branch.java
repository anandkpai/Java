package testStrings;

import java.util.HashSet;



public class Branch extends Tree{
	
	private Branch parent;
	private char chr;	
	private String toString="";
	private static final HashSet<String> nodeSignatures = new HashSet<String>();
	
	protected Branch(Branch parent,int index, String substr) {
		this.chr 	= substr.charAt(index);
		this.parent = parent;
		if (index < substr.length()-1) new Branch(parent, index+1, substr);
		
		String nodeSignature = this.toString()+substr;
		nodeSignature = parent==null?nodeSignature:parent.toString()+nodeSignature;
		if (!Branch.nodeSignatures.contains(nodeSignature)){
			Branch.nodeSignatures.add(nodeSignature);
			if (substr.length()==1) Tree.lowest.add(this);
			else new Branch(this,0,substr.substring(0,index)+substr.substring(index+1));
		}
	}
	
	@Override
	public String toString() {
		if (this.toString.length()==0) 
			this.toString = this.parent==null?String.valueOf(this.chr):this.parent.toString()+String.valueOf(this.chr);
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
