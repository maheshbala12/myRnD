package com.mydemo.codechallenges;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MergeSortDemo {
	static int recursion=1;

	private static void printArr(int[] arr) {
		Arrays.stream(arr).forEach(i->System.out.print(i+", "));
		System.out.println();
	}
	
	public static void main(String[] args) {
		int arr[]= {87, 15, 34, 63, 47, 24, 76, 92, 58, 41};
		int sortedArr[] = {15, 24, 34, 41, 47, 58, 63, 76, 87, 92};

		System.out.println("Before:");
		printArr(arr);
		//arr = simpleSort(arr);
		arr = mergeSort(arr);
		System.out.println("Is array sorted?: "+(Arrays.compare(arr, sortedArr) == 0));
		
		System.out.println("After:");
		printArr(sortedArr);
	}

	static int[] mergeSort(int[] inputArr){
		int[] returnArr = new int[inputArr.length];
		if(inputArr.length>1) {
			int midPoint = inputArr.length/2;
			
			int[] arr1 = mergeSort(Arrays.copyOfRange(inputArr, 0, midPoint));
			
			int[] arr2 = mergeSort(Arrays.copyOfRange(inputArr, midPoint, inputArr.length));

			// Merging logic
			returnArr = mergeArrays(arr1, arr2);
		}
		else {
			if(inputArr.length==1) {
				returnArr = inputArr;
			}
		}
		recursion++;
		return returnArr;
	}
	
	private static int[] mergeArrays(int[] arr1, int[] arr2) {
		List<Integer> list = Arrays.stream(arr1).boxed().collect(Collectors.toList());
		list.addAll(Arrays.stream(arr2).boxed().collect(Collectors.toList()));
		List<Integer> retList = new ArrayList<Integer>();

		retList=list.stream().sorted().collect(Collectors.toList());
		int[] returnArr = retList.stream().mapToInt(Integer::intValue).toArray();
		return returnArr;
	}	
	
	// Implementation using java stream sorted() method
	/*static int[] simpleSort(int[] inputArr){
		int[] returnArr = new int[inputArr.length];
		returnArr = Arrays.stream(inputArr).sorted().toArray();
		return returnArr;
	}*/
}
