package com.mydemo;

import java.util.LinkedList;

public class SurvivalGame {
	public static void main(String[] args) {
		// Initialize linked list
		LinkedList<Integer> list = new LinkedList<Integer>();
		for(int i=1;i<=100;i++)
			list.add(i);
		playGameOfSurvival(list);
		
		// If size=1, then print result & return
		// Else go on killing alternate person
		// If last person reached, continue with first person
		// Repeat the process with remaining members
	}
	
	static void playGameOfSurvival(LinkedList<Integer> list){
		list.forEach(System.out::println);
		System.out.println("\n");

		if(list.size()==1){
			return;
		}
		else{
			for(int i=(list.size()%2==0?1:0); i<list.size(); i++){
				list.remove(i);
			}
			playGameOfSurvival(list);
		}
	}
}
