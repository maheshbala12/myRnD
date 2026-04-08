package com.mydemo.multithreading;

public class CollaboratingNumberPrinters {
	public static void main(String[] args) {
		// 2 threads to print numbers 1-20 in sequence (1 prints even numbers, another prints odd ones)
		PrintNumbers printObj1 = new PrintNumbers(1);
		Thread t1 = new Thread(printObj1, "ODD_Thread");

		PrintNumbers printObj2 = new PrintNumbers(2);
		Thread t2 = new Thread(printObj2, "EVEN_Thread");

		printObj1.setCollaborationThread(printObj2);
		printObj2.setCollaborationThread(printObj1);
		
		t1.start();
		t2.start();
	}
}

class PrintNumbers implements Runnable{
	int number;
	static boolean runFlag = false;
	boolean running;

	PrintNumbers peer;
	public PrintNumbers(int num) {
		super();
		number=num;
		running=runFlag=!runFlag;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}
	
	void setCollaborationThread(PrintNumbers anotherThread) {
		peer = anotherThread;
	}

	@Override
	public synchronized void run() {
		while(number<=20){
			if(running) {
				System.out.println(Thread.currentThread().getName()+": "+number);
				number+=2;
				running=!running;
				peer.setRunning(true);
			}
		}
	}
}