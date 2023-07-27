package com.mydemo.designpatterns;

public class FileLogger implements ILogger {

	ILogger logger = null;
	
	public FileLogger(ILogger logger) {
		super();
		this.logger = logger;
	}
	
	@Override
	public void logMessage(String message) {
		logger.logMessage(message);
		System.out.println("FILELOGGER: "+message);
	}

}
