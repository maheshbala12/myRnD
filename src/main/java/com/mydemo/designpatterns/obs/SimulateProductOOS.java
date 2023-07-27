package com.mydemo.designpatterns.obs;

import java.io.BufferedInputStream;
import java.io.IOException;

public class SimulateProductOOS {
	public static void main(String[] args) {
		InventoryObserver mahesh = new InventoryObserverImpl("Mahesh");
		InventoryObserver varun = new InventoryObserverImpl("Varun");
		InventoryObserver poornima = new InventoryObserverImpl("Poornima");
		InventoryObserver tanya = new InventoryObserverImpl("Tanya");

		//"Bluetooth Stereo", "Dell Laptop", "Apple iPad", "Boat Headphone"
		checkoutProd(varun, "Boat Headphone");
		checkoutProd(tanya, "Apple iPad");
		
	}

	private static void checkoutProd(InventoryObserver observer, String product) {
		Inventory inventory = new Inventory();
		System.out.println(String.format("%s checking out %s ...",observer.getObserverName(), product));
		inventory.checkOut(product, observer);
		System.out.println("\nPress any key to continue...");
		try {
			new BufferedInputStream(System.in).read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
