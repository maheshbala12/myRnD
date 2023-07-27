package com.mydemo.designpatterns;

public class DecoraterPattern {
	public static void main(String[] args) {
		ILogger myLogger = new FileLogger(new DBLogger(new FileLogger(new DummyLogger())));
		myLogger.logMessage("Before --> "+Incrementer.getNumber());
		Incrementer.incrementBy10();
		System.out.println();
		myLogger.logMessage("After --> "+Incrementer.getNumber());
	}
}
class Incrementer{
	static int number=10;
	
	public static int getNumber() {
		return number;
	}

	public static void incrementBy10(){
		number+=10;
	}
}
