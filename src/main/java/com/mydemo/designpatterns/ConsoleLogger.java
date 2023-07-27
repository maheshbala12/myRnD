package com.mydemo.designpatterns;

public class ConsoleLogger implements ILogger {
	ILogger logger = null;
	
	public ConsoleLogger(ILogger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public void logMessage(String message) {
		logger.logMessage(message);
		System.out.println("CONSOLE: "+message);
	}

}
