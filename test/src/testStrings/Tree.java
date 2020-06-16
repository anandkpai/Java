package testStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;


public class Tree implements Comparable<Tree>{

	protected static final ArrayList<String> words = new ArrayList<String>();
	protected static final HashSet<String> nodeSignatures = new HashSet<String>() ;
	
	static public void init (String s) {
		words.clear();
		nodeSignatures.clear();
		new Branch(null,s);
	}		
	
	protected Tree() {}

	public static void print() {Tree.words.forEach(s->{System.out.println(s);});}
	
	public static int size() {return Tree.words.size();}
	
	public static String sequence(String s) {
		
		ArrayList<Character> chars = new ArrayList<Character>();
		for (char c:s.toCharArray()) chars.add(c);		
		HashMap<Character,Integer> freqMap = new HashMap<Character,Integer>();
		chars.forEach(c->{freqMap.put(c, freqMap.getOrDefault(c,0)+1);});
		List<Map.Entry<Character,Integer>> freqList = new LinkedList<Map.Entry<Character,Integer>>(freqMap.entrySet()); 

		Comparator<Map.Entry<Character,Integer>> freqCompare = new Comparator<Map.Entry<Character,Integer>>() {
			@Override
			public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}			
		};
		Collections.sort(freqList, freqCompare);

		String seq ="";
		for (Entry<Character,Integer> o : freqList) seq += StringUtils.repeat(o.getKey(), o.getValue()); 
		return seq;
	}



	@Override
	public int compareTo(Tree o) {
		// TODO Auto-generated method stub
		return 0;
	}	
}
