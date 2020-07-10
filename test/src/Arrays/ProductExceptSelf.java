package Arrays;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductExceptSelf {
	
	static HashMap<Integer, Integer> left = new HashMap<>();
	static HashMap<Integer, Integer> right = new HashMap<>();

	static public int getLeft(int[] nums,int i) {
		return left.computeIfAbsent(i, f->(i==0?1:nums[i-1]*getLeft(nums, i-1)));
	}
	static public int getRight(int[] nums,int i) {
		return right.computeIfAbsent(i, f->(i==(nums.length-1)?1:nums[i+1]*getRight(nums, i+1)));
	}
	
    static public  int[] productExceptSelf(int[] nums) {
    	int[] product = new int[nums.length];
    	for (int i=0;i<nums.length;i++) product[i]= getLeft(nums,i)*getRight(nums, i);
    	return product;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4};
		int[] prod = productExceptSelf(nums);
		for (int i=0;i<nums.length;i++)	System.out.println(prod[i]);
	}
}
