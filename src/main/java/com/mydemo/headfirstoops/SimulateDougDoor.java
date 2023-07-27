package com.mydemo.headfirstoops;

public class SimulateDougDoor {
	public static void main(String[] args) {
		happyFlow();
		happyFlow2();
		
	}
	
	//TODO: V.2: Door closes automatically, dog stuck outside.
	static void alternateFlow(){
			Remote remote = new Remote(new DogDoor());
			System.out.println("Dog barks to be let out..");
			remote.pressButton();
			System.out.println("Dog finishes his business..");
//			System.out.println("Dog barks to be let in..");
//			remote.pressButton();
			System.out.println("Dog gets in, satisfied..");
//			Door closes automatically
		}
	
	//V.2: Door closes automatically after some time.
	static void happyFlow2(){
		Remote remote = new Remote(new DogDoor());
		System.out.println("Dog barks to be let out..");
		remote.pressButton();
		System.out.println("Dog finishes his business..");
//		System.out.println("Dog barks to be let in..");
//		remote.pressButton(); // Not required, since dog is in within time
		System.out.println("Dog gets in, satisfied..");
//		Door closes automatically
	}

	//V.1: Manual way: Door remains open once opened, need close manually
	static void happyFlow(){
		Remote remote = new Remote(new DogDoor());
		System.out.println("Dog barks to be let out..");
		remote.pressButton(); //Door opens
		System.out.println("Dog finishes his business..");
		System.out.println("Dog gets in, satisfied..");
		remote.pressButton(); //Door closes
	}
}
