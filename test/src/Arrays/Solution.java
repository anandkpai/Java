package Arrays;

import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.ArrayList;

public class Solution {

    public static void moveZeroes(int[] nums) {
        List<int[]> n = Arrays.asList(nums);
    }	
	
    public int myAtoi(String str) {
    	Character[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        ArrayList<Character> nums = new ArrayList<>(Arrays.asList(numbers)) ;
        Character[] maxNumChars = {'2','1','4','7','4','8','3','6','4','8'};
        
        int sign = +1;
        boolean notStarted = true;
        ArrayList<Character> numChars = new ArrayList<Character>(str.length()); 
        
        for (Character c:str.toCharArray()) {
        	if (c==' ')
        		if (notStarted)	continue;
        		else break;
        	if (c=='-') if (notStarted){
        		sign = -1;
        		notStarted	 = false;
        		continue;
        	}
        	else break;
        	if (nums.contains(c)){
        		notStarted = false;
        		numChars.add(c);
        	}
        }
        
        if (numChars.size() == 0) return 0;
        if (numChars.size()>10) return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
        if (numChars.size()==10)
        	for (int i=0;i<maxNumChars.length;i++)
        		if (numChars.get(i)> maxNumChars[i]) 
        			return sign==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                
        int r = 0;
        for (char c:numChars) r = 10*r+(int)c;        	        
        return r*sign;
    }
    
    
	public static void main(String[] args) {
		int[] nums = {1,3,5,0,1,0,12,0};
		moveZeroes(nums);
	}

}
