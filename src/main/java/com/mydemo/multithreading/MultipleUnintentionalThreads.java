package com.mydemo.multithreading;

public class MultipleUnintentionalThreads {
	public static void main(String[] args) {
		new Thread(new SomeTask()).start(); // Thread 2
	}
}

class SomeTask implements Runnable {

	public SomeTask() {
		super();
		new Thread(this).start();// Thread 1
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}

}
