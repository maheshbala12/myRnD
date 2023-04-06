package com.mydemo.designpatterns;

public class DBLogger implements ILogger {

	ILogger logger = null;
	
	public DBLogger(ILogger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public void logMessage(String message) {
		logger.logMessage(message);
		System.out.println("DBLOGS: "+message);
	}

}
