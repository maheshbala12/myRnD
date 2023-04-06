package com.mydemo.jaxb;

import javax.xml.bind.annotation.XmlElement;

public class Scheduler {
	String timeToFetch;
	@XmlElement	
	public String getTimeToFetch() {
		return timeToFetch;
	}
	public void setTimeToFetch(String timeToFetch) {
		this.timeToFetch = timeToFetch;
	}
}
