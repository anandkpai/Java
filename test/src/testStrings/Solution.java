package testStrings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    
    public boolean isPowerOfThree(int n) {
        if (n==0) return false;
        if (n==1) return true;
        String s = Integer.toString(n,3);
        if (s.length()==1) return Integer.valueOf(s)%3==0?true:false;
        return Integer.valueOf(s.substring(1))==0?true:false;
    }

    static public String code(char c, int n) {
    	return String.format("%020d", n)+String.valueOf(c);
    }
    
    static public String decode(String coded) {
    	return String.format("%0"+ Integer.valueOf(coded.substring(0, 20))+"d",0).replace("0",coded.substring(20));
    }

    
    static public String frequencySort(String s) {
    	HashMap<Character, Integer> charCount = new HashMap<Character, Integer>(s.length());
    	for (char c:s.toCharArray()) charCount.put(c, charCount.getOrDefault(c, 0)+1);
    	String[] codes = new String[charCount.size()];
    	int index=0;
    	for (char c:charCount.keySet()) codes[index++]=code(c, charCount.get(c)) ;
    	Arrays.sort(codes);
    	String r = "";
    	while (index>0) r += decode(codes[--index]);
    	return r;
    }
    

	
	
	public static void main(String[] args) {

		String s="tree";
		System.out.println(s.indexOf('r'));
		
	}

}
