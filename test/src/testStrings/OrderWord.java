package testStrings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class OrderWord {

	static public String orderString(String s) {
		HashMap<Character, Integer> charmap = new HashMap<>();
		for (char c:s.toCharArray()) charmap.put(c,charmap.getOrDefault(c, 0)+1);
		Comparator<Map.Entry<Character,Integer>> cmp = new Comparator<Map.Entry<Character,Integer>>() {
			@Override 
			public int compare(Map.Entry<Character,Integer> o1, Map.Entry<Character,Integer> o2) {
				return -Integer.compare(o1.getValue(), o2.getValue());
			}
		};
		List<Map.Entry<Character, Integer>> clist = new ArrayList<>(charmap.entrySet());
		clist.sort(cmp);
		int count=0;
		char[] sorted = new char[s.length()];
		for (Map.Entry<Character, Integer> item:clist) 
			for (int i=0;i<item.getValue();i++) sorted[count++] = item.getKey();
		return String.valueOf(sorted);
	}
		
	public static void main(String[] args) {
		System.out.println(orderString("tree"));
	}

}
