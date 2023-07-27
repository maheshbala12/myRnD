package com.mydemo.headfirstoops;

public class Remote {
	DogDoor door;
	public Remote(DogDoor door) {
		this.door=door;
	}
	
	void pressButton() {
		if(door.isOpen()) {
			door.closeDoor();
		}
		else {
			door.openDoor();
		}
	}
}
