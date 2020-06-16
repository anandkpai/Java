package testStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

class Solution {
    static public HashMap<Character,Integer> chars = new HashMap<Character, Integer>();
    static public ArrayList<Map.Entry<Character,Integer>> charList = new ArrayList<Map.Entry<Character,Integer>>() ;
    static Comparator<Map.Entry<Character, Integer>> freqSort = new Comparator<Map.Entry<Character, Integer>>(){
        @Override
        public int compare(Map.Entry<Character,Integer> k1,Map.Entry<Character,Integer> k2){
            return -Integer.compare(k1.getValue(), k2.getValue());
        }
    };

    static public String repeatString(char c, int n){
        int[] trick = new int[n];
        String s = String.valueOf(trick);
        s.replace("0",String.valueOf(c));
        return s;    
    }
    
    static public String frequencySort(String s) {
        for (char c:s.toCharArray()) chars.put(c,chars.getOrDefault(c,0)+1);
        chars.entrySet().forEach(o->charList.add(o));
        Collections.sort(charList, Solution.freqSort);
        String t = "";
        for (Map.Entry<Character,Integer> o : charList) t += Solution.repeatString(o.getKey(), o.getValue()); 
        return t;
    }
    
    static public void main() {
    	String s = "tree";
    	System.out.println(frequencySort(s));
    }
}