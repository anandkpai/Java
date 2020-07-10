package testStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AnastringTest {

	static public String sorted(String s) {
		char[] t = s.toCharArray();
		Arrays.sort(t);
		return String.valueOf(t);
	}
        
    static public List<List<String>> groupAnagrams(String[] strs) {
    	HashMap <String, List<String>> m = new HashMap<String, List<String>>();
    	for (String s:strs) m.computeIfAbsent(sorted(s), f->new ArrayList<String>()).add(s);
    	return  new ArrayList<>(m.values());
    }

	public static void main(String[] args) {
		String[] strs = {"eat","tea","tan","ate","nat","bat"};
	    List<List<String>> groupAnagrams = groupAnagrams(strs);
	    for (List<String> l:groupAnagrams) 
	    	System.out.println(l);
	    
	}
}
