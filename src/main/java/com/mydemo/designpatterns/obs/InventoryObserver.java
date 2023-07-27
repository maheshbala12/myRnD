package com.mydemo.designpatterns.obs;

public interface InventoryObserver {
	String getObserverName();
	void notifyObserver(String prod);
}


