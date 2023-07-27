package com.mydemo.dsa;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BinaryTreeOPs {
	public static void main(String[] args) {
		int nums[]= {0,56,45,12,65,78,27,37,52,61,14,84,63,97,10};
		//TODO: Update & re-arrange the tree (hint: Use index*2 for each level to re-arrange for a particular node in the tree)
		displayCompleteBinaryTree(nums);
	}
	
	// Display complete binary representation
	static void displayCompleteBinaryTree(int numArr[]){
		int seed=1;
		int numTabs = (int) Math.ceil(Math.sqrt(numArr.length));
		for(int index=0;index<numArr.length;) {
			String tabs = getTabs(numTabs);
			System.out.print(tabs);
			
			for(int k=1;k<=seed;k++)
				System.out.print(numArr[index++] + "\t  \t" );
			seed*=2;
			numTabs--;
			System.out.println();
		}
	}

	private static String getTabs(int i) {
		String[] arr = new String[i];
		Arrays.fill(arr, "\t");
		return Stream.of(arr).collect(Collectors.joining());
	}
	
}
