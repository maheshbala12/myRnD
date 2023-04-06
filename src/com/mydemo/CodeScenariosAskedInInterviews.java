package com.mydemo;

public class CodeScenariosAskedInInterviews {
 	public static void main(String[] args) {
		/**
		 * Asked in LoudCloud interview , Marol, Andheri.
		 */
		
		// Following array contains unsorted integer values 
		// & also has an intentional, single duplicate element for prob #1
		
		int[] intVals = {23,2,57,48,10,91,57,39,66};
		// prob #1
		//sortArrayAndShowTheDuplicateIntegerValue(intVals);
		
		// prob #2
		sortArrayAndShowSecondHighestIntegerValue(intVals);
		
		/**
		 * 
		 */
	}
	
	private static void sortArrayAndShowTheDuplicateIntegerValue(int[] vals){
		int prevVal;
		sortIntArray(vals);
		if(vals.length>1){
			prevVal = vals[0];
			for(int i=1;i<vals.length;i++){
				if(prevVal == vals[i]){
					System.out.println("Duplicate number:" + prevVal);
					break;
				}
				prevVal = vals[i];
			}
		}
	}
	
	private static void sortArrayAndShowSecondHighestIntegerValue(int[] vals){
		sortIntArray(vals);
		if(vals.length>1){
			System.out.println("Second highest number:" + vals[vals.length-2]);
		}
	}

	private static void displayArray(int[] intVals) {
		for (int i : intVals) {
			System.out.print(i+", ");
		}
		System.out.println("\n");
	}
	
	private static void sortIntArray(int[] vals){
		System.out.println("Original array:");
		displayArray(vals);
		
		int smallestNumIndex;
		for(int counter=0; counter<vals.length-1; counter++){
			smallestNumIndex = counter;
			for(int innerCounter=counter+1; innerCounter<vals.length; innerCounter++){
				if(vals[innerCounter]<vals[smallestNumIndex]){
					smallestNumIndex = innerCounter;
				}
			}
			if(smallestNumIndex != counter){
				// Swap values (without using a third variable)
				//System.out.println("Before: " + vals[smallestNumIndex] + ":" + vals[counter]);
				vals[smallestNumIndex] += vals[counter];
				vals[counter] = vals[smallestNumIndex] - vals[counter];
				vals[smallestNumIndex] -= vals[counter];
				//System.out.println("After: " + vals[smallestNumIndex] + ":" + vals[counter]);
			}
		}
		
		System.out.println("Sorted array:");
		displayArray(vals);
	}

}




