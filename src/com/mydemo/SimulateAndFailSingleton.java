package com.mydemo;

public class SimulateAndFailSingleton {

	public static void main(String[] args){
		try {
			testFailSingleton();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void testFailSingleton() throws InterruptedException {
		MySingletonManager manager1 = new MySingletonManager();
		MySingletonManager manager2 = new MySingletonManager();
		
		Thread singletonThreadManager1 = new Thread(manager1);
		Thread singletonThreadManager2 = new Thread(manager2);

		singletonThreadManager1.start();
		singletonThreadManager2.start();

		singletonThreadManager1.join();
		singletonThreadManager2.join();

		MySingleton obj1 = manager1.getSingletonObject();
		MySingleton obj2 = manager2.getSingletonObject();

		System.out.println("Objects equal?: " + (obj1 == obj2));

		System.out.println("Object 1: " + obj1);
		System.out.println("Object 2: " + obj2);
	}
}

class MySingleton {

	private static MySingleton singleTonObj;
	private static boolean isFirstThread = true;

	private MySingleton() {
	}

	public static MySingleton getInstance() {
		if (singleTonObj == null) {
			// Introduced intentional delay for the first thread, so second
			// thread is also allowed inside this block, so we get two, rather than a single object

			// This is just to demonstrate how in the absence of synchronization, getInstance()  
			// may create another instance, thereby defeating purpose of a singleton.

			// This explains why using thread synchronization is necessary
			// while implementing singleton design pattern.

			if (isFirstThread) {
				isFirstThread = false;
				try {
					Thread.sleep(300);
				}
				catch (InterruptedException e) {
				}
			}
			singleTonObj = new MySingleton();
		}
		return singleTonObj;
	}
}

class MySingletonManager implements Runnable {
	private MySingleton singletonObject = null;

	public MySingleton getSingletonObject() {
		return singletonObject;
	}

	public void run() {
		singletonObject = MySingleton.getInstance();
	}
}

