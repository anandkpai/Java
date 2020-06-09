package testStrings;

import java.util.HashSet;



public class Branch extends Tree implements Runnable {
	
	private Branch parent;
	private char chr;	
	private String toString="";
	private static int threadCount = 0;
	private int index;
	private String substr;
	
	private Branch(){}
	
	protected Branch(Branch branch,String substr) {
		this(branch,0,substr);
		Branch.nodeSignatures.clear();
	}
	
	private Branch(Branch branch,int index, String substr) {
		final int DUMMYVALUE = 1;
		this.chr 	= substr.charAt(index);
		this.parent = branch;
		this.index  = index;
		this.substr = substr;
		
		if  (Branch.threadCount<Tests.MAX_THREADS) this.run();
		else {
		if (index < substr.length()-1) new Branch(branch, index+1, substr);
		
		String nodeSignature = this.toString()+substr;
		nodeSignature = this.parent != null?branch.toString()+nodeSignature:nodeSignature;
		
		if (Tree.nodeSignatures.putIfAbsent(nodeSignature, DUMMYVALUE)==null)
			if (substr.length()==2) 
				Tree.words.add(this.toString()+substr.substring(0,index)+substr.substring(index+1));
			else 
				new Branch(this,0,substr.substring(0,index)+substr.substring(index+1));
		}
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

	@Override
	public void run() {
		final int DUMMYVALUE = 1;
		Branch.threadCount++;
		Branch branch = this.parent;
		if (index < substr.length()-1) new Branch(branch, index+1, substr);
		
		String nodeSignature = this.toString()+substr;
		nodeSignature = this.parent != null?branch.toString()+nodeSignature:nodeSignature;
		
		if (Tree.nodeSignatures.putIfAbsent(nodeSignature, DUMMYVALUE)==null)
			if (substr.length()==2) 
				Tree.words.add(this.toString()+substr.substring(0,index)+substr.substring(index+1));
			else 
				new Branch(this,0,substr.substring(0,index)+substr.substring(index+1));		
		Branch.threadCount--;
	}	
}
