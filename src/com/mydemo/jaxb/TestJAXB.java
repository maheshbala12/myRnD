package com.mydemo.jaxb;

import java.io.File;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class TestJAXB {
	public static void main(String[] args) {
		try {
			File file = new File("config.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Config config = (Config) jaxbUnmarshaller.unmarshal(file);
			Channel ch = config.getInputChannels().get(0);
			
			ch = config.getOutputChannels().get(0);
			
			ArrayList<Task> tasks = config.getTasks();
			System.out.println();
			
			
		} catch (JAXBException e) {  
			e.printStackTrace();  
		} 
	}
}
