package com.mydemo.designpatterns.obs;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
	static List<InventoryObserver> obsObjs = new ArrayList<>();
	static List<String> products = null;
	
	static {
		products= new ArrayList<String>(List.of("Bluetooth Stereo", "Dell Laptop", "Apple iPad", "Boat Headphone"));
		System.out.println("Products (Opening stock):" + products.toString() + "\n");
	}
	
	static public void registerInterest(InventoryObserver obs){
		obsObjs.add(obs);
	}
	
	public boolean checkOut(String prod, InventoryObserver obs) {
		products.remove(prod);
		obsObjs.stream().filter(o -> obs!=o).forEach(o->o.notifyObserver(products.toString()));
		return true;
	}
}
