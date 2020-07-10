package testStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution {
    
    static public String decode(Map.Entry<Character, Integer> keyVal) {
    	return String.format("%0"+keyVal.getValue() +"d",0).replace('0',keyVal.getKey());
    }
    
    static public String frequencySort(String s) {
    	HashMap<Character,Integer> charCount = new HashMap<Character, Integer>(s.length());
    	for (char c:s.toCharArray()) charCount.put(c, charCount.getOrDefault(c, 0)+1);
    	Comparator<Map.Entry<Character, Integer>> charCompare = new  
    			Comparator<Map.Entry<Character, Integer>>() {
				@Override
				public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
					return 	-Integer.compare(o1.getValue(), o2.getValue());
				}
    		};
		List<Map.Entry<Character, Integer>> clist = new ArrayList<> (charCount.entrySet());
		clist.sort(charCompare);
		String r="";
		for (Map.Entry<Character, Integer> c:clist) r+=decode(c);
		return r;
    	}
    
	public static void main(String[] args) {
		String s="tree";
		System.out.println(frequencySort(s));		
	}
}
