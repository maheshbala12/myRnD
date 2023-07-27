package com.mydemo.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement  
@XmlAccessorType(XmlAccessType.FIELD)
public class Task {
	@XmlElement
	String inputChannel;
	
	@XmlElement
	String outputChannel;
	
	@XmlElement
	String dataSelection;
	
	@XmlElementWrapper(name="scheduler")
	@XmlElement(name="timeToFetch")
	ArrayList<String> scheduler;
	
	public ArrayList<String> getScheduler() {
		return scheduler;
	}
	public void setScheduler(ArrayList<String> scheduler) {
		this.scheduler = scheduler;
	}
	public String getInputChannel() {
		return inputChannel;
	}
	public void setInputChannel(String inputChannel) {
		this.inputChannel = inputChannel;
	}
	public String getOutputChannel() {
		return outputChannel;
	}
	public void setOutputChannel(String outputChannel) {
		this.outputChannel = outputChannel;
	}
	public String getDataSelection() {
		return dataSelection;
	}
	public void setDataSelection(String dataSelection) {
		this.dataSelection = dataSelection;
	}
}
