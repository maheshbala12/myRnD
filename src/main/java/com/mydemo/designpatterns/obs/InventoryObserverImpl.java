package com.mydemo.designpatterns.obs;

public class InventoryObserverImpl implements InventoryObserver {

	String userName;
	public String getObserverName() {
		return userName;
	}

	public InventoryObserverImpl(String userName) {
		this.userName = userName;
		Inventory.registerInterest(this);
	}

	@Override
	public void notifyObserver(String products) {
		System.out.println(userName + ", the updated product list is: " + products);
	}

}
