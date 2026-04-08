package com.mydemo.codechallenges;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PairsTotallingTarget {

	public static void main(String[] args) {
		/*Return all pairs of indexes from an array, whose sum equals to target.
	
		Input:
		Array: [2, 7, 4, -1, 8, 3, 5]
		Target: 6
	
		Output:
		[[0, 2], [1, 3], [3, 4]]
		*/
		// TODO: 
		// How to reduce O(n)2 to O(n) (Simple Sorting - enhance using 2-pointer technique)
		// Approach 1: Use hashmap with one iteration
//		solutionUsingHashmap();
		// Approach 2: Use 2 pointer technique
		solutionUsing2PointerTechnique();
	}
	
	private static void solutionUsing2PointerTechnique() {
		int arr[] = {2, 7, 4, -1, 8, 3, -2};
		int target =  6;
		// Sort it (asc)
		Arrays.sort(arr);
		// loop thru, 
		String res = "[";
		for(int i=0,j=arr.length-1;i<j;) {
			//add up first & last,
			int sum = arr[i]+arr[j];
			// if sum>target, decrement higher pointer
			if(sum>target) {
				j--;
			}
			// if sum<target, increment lower pointer
			else if(sum<target) {
				i++;
			}
			// if sum==target, note both pointers
			else {
				res+=String.format("[%d, %d]",arr[i++], arr[j]);
				if(res.length()>1)
					res+=",";
			}
		}
		res=res.substring(0, res.length()-1) + "]";
		System.out.println(res);
	}

	private static void solutionUsingHashmap() {
		int arr[] = {2, 7, 4, -1, 8, 3, 5};
		int target =  6;
		Set<Integer> set = new HashSet<Integer>();
		String res = "[";
		for(int i=0;i<arr.length;i++) {
			int num=target-arr[i];
			if(set.contains(num)) {
				res+=String.format("[%d, %d]",num, arr[i]);
				if(res.length()>1)
	          		res+=",";
			}
			else {
				set.add(arr[i]);
			}
		}
		res=res.substring(0, res.length()-1) + "]";
		System.out.println(res);
	}

	void perf_expensive_solution() {
		int arr[] = {2, 7, 4, -1, 8, 3, 5};
		int target =  6;
		String str = "[";
		for(int i=0; i<arr.length-1;i++){
			for(int j=i+1; j<arr.length;j++){
					if(arr[i]+arr[j]==target){
		      		if(str.length()>1)
		          		str+=",";
		      		str += String.format("[%d, %d]", i, j);
		      }
			}
		}
		str+="]";
		System.out.println(str);
	}

}
