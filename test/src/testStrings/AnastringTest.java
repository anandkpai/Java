package testStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnastringTest {
	
    static public class Anastring implements Comparable<Anastring>{
    	public final String original;
    	public final String sorted;
    	
    	public Anastring(String original) {
    		this.original = original;
    		char[] c      = original.toCharArray();
    		Arrays.sort(c);
    		this.sorted   = String.valueOf(c); 
    	}
    	
    	@Override
    	public int compareTo (Anastring other){return this.sorted.compareTo(other.sorted);}
    	
    	@Override
    	public boolean equals(Object o) {
    		if (o instanceof Anastring) {
    			Anastring other = (Anastring)o;
    			return this.sorted.equals(other.sorted);
			}
    		else return false;
    	}

    	@Override
    	public String toString() {return this.original;}
    	
    	@Override
    	public int hashCode() {return this.sorted.hashCode();}    	
    	
    }
    
    static public class AnaGroup{
    	
    }
        
    static public List<List<String>> groupAnagrams(String[] strs) {
    	List<Anastring> anagsList = new ArrayList<>(strs.length);
    	for (String s:strs) anagsList.add(new Anastring(s));
    	anagsList.sort(Anastring::compareTo);
    	
    	HashMap <Anastring, List<String>> m = new HashMap<Anastring, List<String>>();
    	List<String> current;
    	for (Anastring s:anagsList) {
    		current = m.computeIfAbsent(s, f->new ArrayList<String>());
    		current.add(s.original);
    	}
    	List<List<String>> groups = new ArrayList<>(m.values());
    	return groups ;
    	
    }

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
	    List<List<String>> groupAnagrams = groupAnagrams(strs);
	    for (List<String> l:groupAnagrams) 
	    	System.out.println(l);	    	
	}
}
