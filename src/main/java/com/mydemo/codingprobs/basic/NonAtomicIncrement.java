package com.mydemo.codingprobs.basic;

public class NonAtomicIncrement implements Runnable{
	static int count=0;
	public static void main(String[] args) {
		for(int i=0;i<100000;i++) {
			new Thread(new NonAtomicIncrement()).start();
		}
		System.out.println("Count="+count);
		// The count is seldom 100000 since count++ is not an atomic operation!
	}

	@Override
	public void run() {
		synchronized(this) {
			count++;
		}
	}
}
