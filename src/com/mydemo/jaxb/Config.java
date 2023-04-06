package com.mydemo.jaxb;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement  
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
	@XmlElementWrapper(name="inputChannels")
	@XmlElement(name="channel")
	ArrayList<Channel> inputChannels;
	
	@XmlElementWrapper(name="outputChannels")
	@XmlElement(name="channel")
	ArrayList<Channel> outputChannels;

	@XmlElementWrapper(name="tasks")
	@XmlElement(name="task")
	ArrayList<Task> tasks;
	
	public ArrayList<Channel> getInputChannels() {
		return inputChannels;
	}
	public void setInputChannels(ArrayList<Channel> inputChannels) {
		this.inputChannels= inputChannels;
	}

	public ArrayList<Channel> getOutputChannels() {
		return outputChannels;
	}
	public void setOutputChannels(ArrayList<Channel> outputChannels) {
		this.outputChannels = outputChannels;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<Task> task) {
		this.tasks = task;
	}
}
