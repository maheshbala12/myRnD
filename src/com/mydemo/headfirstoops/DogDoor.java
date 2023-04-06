package com.mydemo.headfirstoops;

public class DogDoor {
	static boolean open = false;
	public static boolean isOpen() {
		return open;
	}
	
	public static void openDoor() {
		System.out.println("---(Door opens)");
		DogDoor.open = true;
	}
	
	public static void closeDoor() {
		System.out.println("---(Door closes)");
		DogDoor.open = false;
	}
}
